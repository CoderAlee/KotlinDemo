import java.text.SimpleDateFormat

fun String.print() {
    println(" Time = [ ${nowTime()} ] Thread = [${Thread.currentThread().name}], Message = $this")
}

fun transToString(time: Long): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(time)
}

fun nowTime() = transToString(System.currentTimeMillis())