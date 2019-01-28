package com.dmytro.mvvmdbtemplate.screens.movies.di

import com.dmytro.mvvmdbtemplate.common.di.ActivityScope
import com.dmytro.mvvmdbtemplate.di.AppComponent
import com.dmytro.mvvmdbtemplate.screens.movies.MoviesActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [MoviesModule::class], dependencies = [AppComponent::class])
interface MoviesComponent {
    fun inject(target: MoviesActivity)

    fun activity(): MoviesActivity

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MoviesActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: MoviesModule): Builder

        fun build(): MoviesComponent
    }
}