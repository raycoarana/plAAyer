package com.raycoarana.plaayer

import android.net.Uri
import android.os.Bundle
import com.google.android.apps.auto.sdk.CarActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PlaayerCarActivity : CarActivity() {

    companion object {
        const val KEY_PLAY_WHEN_READY = "play_when_ready"
        const val KEY_WINDOW = "timelineWindow"
        const val KEY_POSITION = "position"
    }

    private lateinit var mPlayerView: PlayerView
    private var player: SimpleExoPlayer? = null

    private lateinit var timelineWindow: Timeline.Window
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private var trackSelector: DefaultTrackSelector? = null
    private var shouldAutoPlay: Boolean = false
    private lateinit var bandwidthMeter: BandwidthMeter

    private var playWhenReady: Boolean = false
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plaayer)

        if (savedInstanceState == null) {
            playWhenReady = true
            currentWindow = 0
            playbackPosition = 0
        } else {
            playWhenReady = savedInstanceState.getBoolean(KEY_PLAY_WHEN_READY)
            currentWindow = savedInstanceState.getInt(KEY_WINDOW)
            playbackPosition = savedInstanceState.getLong(KEY_POSITION)
        }

        shouldAutoPlay = true
        bandwidthMeter = DefaultBandwidthMeter()
        mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"), bandwidthMeter as DefaultBandwidthMeter)
        timelineWindow = Timeline.Window()

        carUiController.statusBarController.hideAppHeader()
        carUiController.menuController.hideMenuButton()
        carUiController.drawerController.closeDrawer()
    }

    private fun initializePlayer() {
        mPlayerView = findViewById(R.id.player_view) as PlayerView
        mPlayerView.requestFocus()

        val videoTrackSelectionFactory: TrackSelection.Factory = AdaptiveTrackSelection.Factory(bandwidthMeter)

        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        player?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        mPlayerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        mPlayerView.player = player

        player?.playWhenReady = shouldAutoPlay

        val mediaSource: MediaSource = ExtractorMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))

        val haveStartPosition: Boolean = currentWindow != C.INDEX_UNSET
        if (haveStartPosition) {
            player?.seekTo(currentWindow, playbackPosition)
        }

        player?.prepare(mediaSource, !haveStartPosition, false)
    }

    private fun releasePlayer() {
        if (player != null) {
            updateStartPosition()
            shouldAutoPlay = player?.playWhenReady ?: false
            player?.release()
            player = null
            trackSelector = null
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        updateStartPosition()

        outState.putBoolean(KEY_PLAY_WHEN_READY, playWhenReady)
        outState.putInt(KEY_WINDOW, currentWindow)
        outState.putLong(KEY_POSITION, playbackPosition)
        super.onSaveInstanceState(outState)
    }

    private fun updateStartPosition() {
        playbackPosition = player?.currentPosition ?: 0
        currentWindow = player?.currentWindowIndex ?: 0
        playWhenReady = player?.playWhenReady ?: false
    }
}