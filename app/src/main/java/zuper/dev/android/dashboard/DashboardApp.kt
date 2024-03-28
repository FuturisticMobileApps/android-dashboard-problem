package zuper.dev.android.dashboard

import android.app.Application
import zuper.dev.android.dashboard.di.AppModule
import zuper.dev.android.dashboard.di.AppModuleImpl

class DashboardApp : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
    }
}