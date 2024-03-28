package zuper.dev.android.dashboard.di

import zuper.dev.android.dashboard.data.DataRepository
import zuper.dev.android.dashboard.data.remote.ApiDataSource

class AppModuleImpl() : AppModule {
    override val dataRepository: DataRepository
            by lazy { DataRepository(ApiDataSource()) }
}