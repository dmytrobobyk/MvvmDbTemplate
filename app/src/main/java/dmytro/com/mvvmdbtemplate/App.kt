package dmytro.com.mvvmdbtemplate

import android.support.multidex.MultiDexApplication
import dmytro.com.mvvmdbtemplate.di.AppModule
import dmytro.com.mvvmdbtemplate.di.DaggerAppComponent
import timber.log.Timber

class App : MultiDexApplication() {

    val component by lazy {
        DaggerAppComponent.builder()
                .context(this)
                .plus(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

        Timber.plant(Timber.DebugTree())
    }
}