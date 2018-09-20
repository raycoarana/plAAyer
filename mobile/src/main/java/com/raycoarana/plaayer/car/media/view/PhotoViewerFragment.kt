package com.raycoarana.plaayer.car.media.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.media.viewmodel.PhotoViewerViewModel
import com.raycoarana.plaayer.core.di.DaggerCarFragment
import com.raycoarana.plaayer.databinding.CarFragmentPhotoBinding
import javax.inject.Inject

class PhotoViewerFragment : DaggerCarFragment() {

    @Inject
    lateinit var viewModel: PhotoViewerViewModel

    private lateinit var binding: CarFragmentPhotoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.initialize(arguments?.getString(ARGS_DATA_URL) ?: "")

        binding = CarFragmentPhotoBinding.bind(view)
        binding.viewModel = viewModel
    }

    override fun onComponentReady() {
        component.inject(this)
    }

    companion object {
        const val ARGS_DATA_URL = "data"
    }
}
