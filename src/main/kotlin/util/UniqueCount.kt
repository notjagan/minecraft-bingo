package util

sealed class UniqueCount {
    open class Num(val count: Int) : UniqueCount(), Comparable<Int> by count
    object Any : Num(1)
    object All : UniqueCount()
}
