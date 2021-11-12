fun safeCall(runner: () -> Unit) {
    try {
        runner()
    } catch (ignored: Throwable) {
    }
}