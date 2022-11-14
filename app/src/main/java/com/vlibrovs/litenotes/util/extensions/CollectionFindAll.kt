package com.vlibrovs.litenotes.util.extensions

inline fun <T> Collection<T>.findAll(
    predicate: (T) -> Boolean
): List<T> {
    val list: MutableList<T> = mutableListOf()
    for (element in this) {
        if (predicate(element)) list.add(element)
    }
    return list
}