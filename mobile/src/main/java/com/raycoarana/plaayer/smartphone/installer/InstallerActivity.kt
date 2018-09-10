package com.raycoarana.plaayer.smartphone.installer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.common.fromAppComponentOf
import com.raycoarana.plaayer.core.di.ActivityModule
import com.raycoarana.plaayer.databinding.ActivityInstallerBinding
import javax.inject.Inject

class InstallerActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: InstallerViewModel

    private lateinit var binding: ActivityInstallerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fromAppComponentOf(this).plus(ActivityModule(this)).inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_installer)
        binding.viewModel = viewModel
    }
}
