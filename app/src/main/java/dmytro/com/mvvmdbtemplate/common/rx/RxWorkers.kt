package dmytro.com.mvvmdbtemplate.common.rx

import io.reactivex.Scheduler

class RxWorkers(val subscribeWorker: Scheduler, val observeWorker: Scheduler)