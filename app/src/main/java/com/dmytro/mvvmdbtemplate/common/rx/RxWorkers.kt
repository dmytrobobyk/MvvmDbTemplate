package com.dmytro.mvvmdbtemplate.common.rx

import io.reactivex.Scheduler

class RxWorkers(val subscribeWorker: Scheduler, val observeWorker: Scheduler)