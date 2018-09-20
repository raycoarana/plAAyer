package com.raycoarana.plaayer.car.media.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.media.domain.model.MediaItem
import com.raycoarana.plaayer.car.media.viewmodel.VideoGridViewModel
import com.raycoarana.plaayer.core.di.DaggerCarFragment
import com.raycoarana.plaayer.core.ui.MarginItemDecorator
import com.raycoarana.plaayer.databinding.CarFragmentMediaGridBinding
import javax.inject.Inject

class MediaGridFragment : DaggerCarFragment() {

    @Inject
    lateinit var viewModel: VideoGridViewModel

    private lateinit var binding: CarFragmentMediaGridBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_fragment_media_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mediaItemType = MediaItem.Type.valueOf(arguments?.getString(ARG_MEDIA_TYPE)
                ?: MediaItem.Type.VIDEO_ITEM.toString())

        binding = CarFragmentMediaGridBinding.bind(view, component)
        if (mediaItemType == MediaItem.Type.VIDEO_ITEM) {
            binding.items.addItemDecoration(MarginItemDecorator(context!!, R.dimen.carrousel_horizontal_space, R.dimen.carrousel_vertical_space))
        }
        binding.items.layoutManager = when(mediaItemType) {
            MediaItem.Type.PHOTO_ITEM -> GridLayoutManager(context, PHOTO_COLUMNS)
            else -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.viewModel = viewModel

        viewModel.init(
                mediaItemType,
                arguments?.getString(ARG_PATH) ?: "/",
                arguments?.getString(ARG_PARENT_PATH)
        )
    }

    override fun onComponentReady() {
        component.inject(this)
    }

    companion object {
        const val PHOTO_COLUMNS = 3

        const val ARG_MEDIA_TYPE = "media_type"
        const val ARG_PATH = "path"
        const val ARG_PARENT_PATH = "parent_path"

        fun build(mediaType: MediaItem.Type, path: String = "/", parentPath: String? = null): MediaGridFragment {
            val fragment = MediaGridFragment()
            val arguments = Bundle()

            arguments.putString(ARG_MEDIA_TYPE, mediaType.toString())
            arguments.putString(ARG_PATH, path)
            parentPath.let {
                arguments.putString(ARG_PARENT_PATH, it)
            }
            fragment.arguments = arguments
            return fragment
        }
    }
}
