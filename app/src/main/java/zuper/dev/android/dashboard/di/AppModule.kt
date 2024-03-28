package zuper.dev.android.dashboard.di

import zuper.dev.android.dashboard.data.DataRepository

interface AppModule {
    val dataRepository : DataRepository
}