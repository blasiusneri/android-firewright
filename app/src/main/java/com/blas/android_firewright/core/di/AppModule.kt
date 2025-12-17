package com.blas.android_firewright.core.di

import android.content.Context
import coil.ImageLoader
import com.blas.android_firewright.main.util.ImageLoaderUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWelcomeText(): String = "Hello from Hilt"

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoaderUtil.loadImage(context, okHttpClient)
    }
}