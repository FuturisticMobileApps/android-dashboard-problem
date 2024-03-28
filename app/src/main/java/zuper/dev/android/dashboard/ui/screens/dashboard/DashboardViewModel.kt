package zuper.dev.android.dashboard.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import zuper.dev.android.dashboard.data.DataRepository
import zuper.dev.android.dashboard.data.model.InvoiceApiModel
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.ui.components.StatusChart

class DashboardViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val _jobsState = MutableStateFlow<List<StatusChart>>(mutableListOf())
    val jobsState: Flow<List<StatusChart>> = _jobsState.asStateFlow()

    private val _invoiceState = MutableStateFlow<List<StatusChart>>(emptyList())
    val invoiceState: Flow<List<StatusChart>> = _invoiceState.asStateFlow()

    var jobList = listOf<JobApiModel>()


    init {
        viewModelScope.launch {
            async { observeJobs() }.onJoin
            async { observeInvoices() }.onJoin
        }
    }

    private suspend fun observeJobs() {
        dataRepository.observeJobs().collect { jobs ->
            jobList = jobs
            _jobsState.value = convertToStatusCharts(
                jobs,
                jobStatusColorMap,
                JobApiModel::status
            )
        }
    }

    private suspend fun observeInvoices() {
        dataRepository.observeInvoices().collect { invoices ->
            _invoiceState.value = convertToStatusCharts(
                invoices,
                invoiceStatusColorMap,
                InvoiceApiModel::status
            ).sortedByDescending { it.count }
        }
    }

}