package com.example.wonka_staff

import android.app.Application
import com.example.wonka_staff.DI.AppDIModule
import com.example.wonka_staff.DI.NetworkDIModule
import com.example.wonka_staff.DI.ViewModelDIModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(NetworkDIModule.create())
        import(ViewModelDIModule.create())
        import(AppDIModule(application = this@App).create())
    }
}