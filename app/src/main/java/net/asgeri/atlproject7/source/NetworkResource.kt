package net.asgeri.atlproject7.source

sealed class NetworkResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : NetworkResource<T>(data)
    class Error<T>(message: String?) : NetworkResource<T>(null, message)
}
