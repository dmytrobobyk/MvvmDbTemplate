package com.dmytro.mvvmdbtemplate.common.util


/**
 * This class is a wrapper on data, which ViewModel passes to View.
 * There are 5 possible states:
 * 1) LOADING: Status == LOADING, data is nullable, throwable is null.
 * 2) SYNCING: Status == SYNCING, data is not null, throwable is null.
 * 3) SUCCESS: Status == SUCCESS, data is not null, throwable is null.
 * 4) ERROR: Status == ERROR, data is nullable, throwable is not null.
 * 5) RESTORED (after error): Status == RESTORED, data is nullable, throwable is null.
 */
class Resource<out D> private constructor(val status: Status,
                                          val data: D?,
                                          val throwable: Throwable?) {

    enum class Status {
        LOADING,
        SYNCING,
        SUCCESS,
        ERROR,
        RESTORED
    }

    companion object {

        fun <D> loading(data: D? = null) = Resource(Status.LOADING, data, null)

        fun <D> syncing(data: D) = Resource(Status.SYNCING, data, null)

        fun <D> success(data: D) = Resource(Status.SUCCESS, data, null)

        fun <D> error(throwable: Throwable, data: D? = null) = Resource(Status.ERROR, data, throwable)

        fun <D> restore(data: D? = null) = Resource(Status.RESTORED, data, null)

    }

    override fun equals(other: Any?): Boolean =
            if (this === other) {
                true
            } else {
                @Suppress("UNCHECKED_CAST")
                (other as? Resource<D>)?.let {
                    when {
                        this.status != other.status -> false
                        this.data != other.data -> false
                        this.throwable != other.throwable -> false
                        else -> true
                    }
                } ?: false
            }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (throwable?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource(status=$status, data=$data, throwable=$throwable)"
    }
}