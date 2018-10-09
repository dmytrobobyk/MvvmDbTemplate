package dmytro.com.mvvmdbtemplate.screens.movies.di

import dagger.BindsInstance
import dagger.Component
import dmytro.com.database.dao.MovieDao
import dmytro.com.mvvmdbtemplate.App
import dmytro.com.mvvmdbtemplate.common.di.ActivityScope
import dmytro.com.mvvmdbtemplate.di.AppComponent
import dmytro.com.mvvmdbtemplate.di.AppModule
import dmytro.com.mvvmdbtemplate.screens.movies.MoviesActivity

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