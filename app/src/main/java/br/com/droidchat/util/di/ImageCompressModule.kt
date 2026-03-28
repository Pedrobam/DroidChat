package br.com.droidchat.util.di

import br.com.droidchat.util.image.ImageCompressor
import br.com.droidchat.util.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ImageCompressModule {

    @Binds
    fun bindImageCompressor(impl: ImageCompressorImpl): ImageCompressor

}