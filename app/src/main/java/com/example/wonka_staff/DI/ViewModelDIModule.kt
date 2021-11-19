package com.example.wonka_staff.DI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wonka_staff.viewModel.StaffViewModel
import org.kodein.di.*
import org.kodein.type.erased

object ViewModelDIModule : DIBaseModule("ViewModelDIModule") {

    override val builder: DI.Builder.() -> Unit = {
        bind<ViewModelProvider.Factory>() with singleton {
            DIViewModelFactory(di)
        }
        bind<StaffViewModel>() with provider { StaffViewModel(instance()) }
    }

    class DIViewModelFactory(private val di: DI) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return di.direct.Instance(erased(modelClass))
        }

    }

}