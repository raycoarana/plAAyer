package com.raycoarana.plaayer.car.tv.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import javax.inject.Inject

class LiveTvChannelGridViewModel @Inject constructor(
        navigator: Navigator
) {
    val items : List<LiveTvChannelItemViewModel> = listOf(
            LiveTvChannelItemViewModel(navigator, "La1", "", R.drawable.channel_tve1),
            LiveTvChannelItemViewModel(navigator, "La2", "", R.drawable.channel_tve2),
            LiveTvChannelItemViewModel(navigator, "Antena 3", "https://livepull1.secure.footprint.net/geoantena3mpp/bitrate_3.m3u8", R.drawable.channel_antena3),
            LiveTvChannelItemViewModel(navigator, "Cuatro", "https://livehlsdai-i.akamaihd.net/hls/live/571643/cuatro/bitrate_3.m3u8", R.drawable.channel_cuatro),
            LiveTvChannelItemViewModel(navigator, "Tele5", "https://livehlsdai-i.akamaihd.net/hls/live/571640/telecinco/bitrate_3.m3u8", R.drawable.channel_telecinco),
            LiveTvChannelItemViewModel(navigator, "laSexta", "https://livepull1.secure.footprint.net/lasextampp/bitrate_3.m3u8", R.drawable.channel_la_sexta),
            LiveTvChannelItemViewModel(navigator, "neox", "https://livepull1.secure.footprint.net/geoneoxmpp/bitrate_3.m3u8", R.drawable.channel_neox),
            LiveTvChannelItemViewModel(navigator, "mega", "https://livepull1-i.akamaized.net/geomegampp/bitrate_3.m3u8", R.drawable.channel_mega),
            LiveTvChannelItemViewModel(navigator, "nova", "https://livepull1.secure.footprint.net/geonovampp/bitrate_3.m3u8", R.drawable.channel_nova),
            LiveTvChannelItemViewModel(navigator, "atreseries", "https://livepull1-i.akamaized.net/geoa3seriesmpp/bitrate_3.m3u8", R.drawable.channel_atreseries),
            LiveTvChannelItemViewModel(navigator, "BeMad", "https://mdslivehlsb-i.akamaihd.net/hls/live/623615/bemad/bitrate_3.m3u8", R.drawable.channel_bemad),
            LiveTvChannelItemViewModel(navigator, "Energy", "https://mdslivehlsb-i.akamaihd.net/hls/live/623617/energy/bitrate_3.m3u8", R.drawable.channel_energy),
            LiveTvChannelItemViewModel(navigator, "Boing", "https://mdslivehlsb-i.akamaihd.net/hls/live/623616/boing/bitrate_3.m3u8", R.drawable.channel_boing),
            LiveTvChannelItemViewModel(navigator, "Divinity", "https://mdslivehls-i.akamaihd.net/hls/live/571648/divinity/bitrate_1.m3u8", R.drawable.channel_divinity),
            LiveTvChannelItemViewModel(navigator, "FdF", "https://mdslivehls-i.akamaihd.net/hls/live/571650/fdf/bitrate_3.m3u8", R.drawable.channel_fdf),
            LiveTvChannelItemViewModel(navigator, "Teledeporte", "http://rtvev4-live.hss.adaptive.level3.net/egress/ahandler/rtvegl1/tdp_onlydash_lv3_aosv4_gl1/tdp_onlydash_lv3_aosv4_gl1.isml/tdp_onlydash_lv3_aosv4_gl1-audio=128000-video=1500000.m3u8", R.drawable.channel_tdp),
            LiveTvChannelItemViewModel(navigator, "24h", "http://rtvev4-live.hss.adaptive.level3.net/egress/ahandler/rtvegl8/24h_onlydash_lv3_aosv4_gl8/24h_onlydash_lv3_aosv4_gl8.isml/24h_onlydash_lv3_aosv4_gl8-audio=128000-video=1500000.m3u8", R.drawable.channel_24h),
            LiveTvChannelItemViewModel(navigator, "GOL", "https://webgol-hls-live-overon.akamaized.net/OveronHttpOrigin/_definst_/webgol_gol24h_MB_1896/chunklist.m3u8?_token_=ip=83.37.182.250~exp=1537133872~acl=%2F%2A~hmac=b9abf071e9ea795d577129e9a31128e981277d4c", R.drawable.channel_gol),
            LiveTvChannelItemViewModel(navigator, "Paramount", "http://paramount.live.flumotion.com/live/chunks.m3u8", R.drawable.channel_paramont)
    )
}
