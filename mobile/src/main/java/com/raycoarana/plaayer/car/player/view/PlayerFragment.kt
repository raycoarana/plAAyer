package com.raycoarana.plaayer.car.player.view

import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.support.car.media.CarAudioManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView as ExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoListener
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.main.view.CarActivity
import com.raycoarana.plaayer.car.player.presenter.PlayerPresenter
import com.raycoarana.plaayer.core.di.DaggerCarFragment
import javax.inject.Inject

class PlayerFragment : DaggerCarFragment(), PlayerView {
    companion object {
        const val KEY_PLAY_WHEN_READY = "playWhenReady"
        const val KEY_WINDOW = "timelineWindow"
        const val KEY_POSITION = "position"

        const val ARGS_DATA_ID = "channelId"
        const val ARGS_DATA_URL = "url"
        const val ARGS_URL_TYPE = "urlType"
    }

    enum class UrlType {
        FILE,
        HLS
    }

    @Inject
    lateinit var carAudioManager: CarAudioManager

    @Inject
    lateinit var presenter: PlayerPresenter

    private lateinit var mPlayerView: ExoPlayerView
    private var player: SimpleExoPlayer? = null

    private lateinit var timelineWindow: Timeline.Window
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private var trackSelector: DefaultTrackSelector? = null
    private var shouldAutoPlay: Boolean = false
    private lateinit var bandwidthMeter: BandwidthMeter

    private var playWhenReady: Boolean = false
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_activity_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        mediaDataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "mediaPlayerSample"), bandwidthMeter as DefaultBandwidthMeter)
        timelineWindow = Timeline.Window()

        val carUiController = (context as CarActivity).carUiController


        mPlayerView = view.findViewById(R.id.player_view) as ExoPlayerView
        mPlayerView.requestFocus()
        mPlayerView.setControllerVisibilityListener {
            if (it == View.VISIBLE) {
                carUiController.statusBarController.showAppHeader()
                carUiController.menuController.showMenuButton()
            } else {
                carUiController.statusBarController.hideAppHeader()
                carUiController.menuController.hideMenuButton()
            }
        }

        val channelId = arguments?.getInt(ARGS_DATA_ID, -1)
        val url = arguments?.getString(ARGS_DATA_URL)
        val urlType = arguments?.getString(ARGS_URL_TYPE)

        presenter.initialize(this, channelId, url, urlType)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            presenter.onReady()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            presenter.onReady()
        }

        requestAudioFocus()
    }

    override fun initializePlayer(urlType: UrlType, url: String) {
        val videoTrackSelectionFactory: TrackSelection.Factory = AdaptiveTrackSelection.Factory(bandwidthMeter)

        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        player?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        mPlayerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        mPlayerView.player = player

        player?.playWhenReady = shouldAutoPlay

        val mediaSource: MediaSource = when (urlType) {
            UrlType.FILE -> ExtractorMediaSource.Factory(mediaDataSourceFactory)
            UrlType.HLS -> HlsMediaSource.Factory(mediaDataSourceFactory)
        }.createMediaSource(Uri.parse(url))

        val haveStartPosition: Boolean = currentWindow != C.INDEX_UNSET
        if (haveStartPosition) {
            player?.seekTo(currentWindow, playbackPosition)
        }

        player?.addListener(object : Player.EventListener {
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
                Log.d("CAR", "onPlaybackParametersChanged")
            }

            override fun onSeekProcessed() {
                Log.d("CAR", "onSeekProcessed")
            }

            override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
                Log.d("CAR", "onTracksChanged")
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
                Log.d("CAR", "onPlayerError: ${error?.message}")
                player?.prepare(mediaSource, !haveStartPosition, false)
            }

            override fun onLoadingChanged(isLoading: Boolean) {
                Log.d("CAR", "onLoadingChanged: $isLoading")
            }

            override fun onPositionDiscontinuity(reason: Int) {
                Log.d("CAR", "onPositionDiscontinuity: $reason")
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                Log.d("CAR", "onRepeatModeChanged: $repeatMode")
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                Log.d("CAR", "onShuffleModeEnabledChanged: $shuffleModeEnabled")
            }

            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {
                Log.d("CAR", "onTimelineChanged")
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.d("CAR", "onPlayerStateChanged: $playWhenReady $playbackState")
            }

        })
        player?.prepare(mediaSource, !haveStartPosition, true)
    }

    override fun setStreams(currentStreamLabel: String, allStreamLabels: List<String>) {
        // TODO: Initialize stream quality adapter
    }


    private fun requestAudioFocus() {
        try {
            carAudioManager.requestAudioFocus(null, carAudioManager.getAudioAttributesForCarUsage(CarAudioManager.CAR_AUDIO_USAGE_DEFAULT), AudioManager.AUDIOFOCUS_GAIN, 0)
        } catch (e: Exception) {
            Log.d("CAR", "RequestAudioFocus exception: $e")
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            presenter.onPause()
        }

        abandonAudioFocus()
    }

    private fun abandonAudioFocus() {
        try {
            carAudioManager.abandonAudioFocus(null, carAudioManager.getAudioAttributesForCarUsage(CarAudioManager.CAR_AUDIO_USAGE_DEFAULT))
        } catch (e: Exception) {
            Log.d("CAR", "AbandonAudioFocus exception: $e")
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            presenter.onPause()
        }
    }

    override fun releasePlayer() {
        if (player != null) {
            updateStartPosition()
            shouldAutoPlay = player?.playWhenReady ?: false
            player?.release()
            player = null
            trackSelector = null
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

    override fun onComponentReady() {
        component.inject(this)
    }
}