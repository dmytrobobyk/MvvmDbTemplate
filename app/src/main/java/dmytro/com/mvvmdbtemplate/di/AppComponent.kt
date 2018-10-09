package dmytro.com.mvvmdbtemplate.di

import dagger.BindsInstance
import dagger.Component
import dmytro.com.database.AppDatabase
import dmytro.com.database.dao.MovieDao
import dmytro.com.mvvmdbtemplate.App
import dmytro.com.mvvmdbtemplate.common.rx.RxWorkers
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: App)

    fun app(): App

    fun workers(): RxWorkers

    fun appDatabase(): AppDatabase

    fun movieDao(): MovieDao

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(app: App): Builder

        fun plus(module: AppModule): Builder

        fun build(): AppComponent
    }
}