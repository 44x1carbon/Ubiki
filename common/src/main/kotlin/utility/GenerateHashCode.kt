package utility

fun generateHashCode(vararg args:Any?): Int
    = args.dropLast(1).fold(0) { tmp, value -> tmp + ((value?.hashCode() ?: 0) * 31) } + (args.last()?.hashCode() ?: 0)
