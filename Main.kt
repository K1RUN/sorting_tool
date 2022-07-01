package sorting

import java.io.File
import java.util.Scanner

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    fun mergedSort(arr: Array<Int>) {
        if (arr.size > 1) {
            val med = arr.size / 2
            val l = arr.slice(0 until med).toTypedArray()
            val r = arr.slice(med..arr.lastIndex).toTypedArray()
            mergedSort(l)
            mergedSort(r)
            var k = 0
            var i = 0
            var j = 0
            while (i < l.size && j < r.size) {
                if (l[i] < r[j]) {
                    arr[k] = l[i]
                    i++
                } else {
                    arr[k] = r[j]
                    j++
                }
                k++
            }
            while (i < l.size) {
                arr[k] = l[i]
                i++
                k++
            }
            while (j < r.size) {
                arr[k] = r[j]
                j++
                k++
            }
        }
    }
    fun printMethod(string: String) {
        if ("-outputFile" in args) {
            val fileOut = File(args[args.indexOf("-outputFile") + 1])
            fileOut.appendText("\n$string")
        } else {
            print(string)
        }
    }
    var type = "natural"
    var dataType = "long"
    try {
        dataType = args[args.indexOf("-dataType") + 1]
    } catch (e: IndexOutOfBoundsException) {
        println("No data type defined!")
    }
    try {
        if ("-sortingType" in args) {
            type = args[args.indexOf("-sortingType") + 1]
        }
    } catch (e: IndexOutOfBoundsException) {
        println("No sorting type defined!")
    }
    when (type) {
        "natural" -> when (dataType) {
            "long" -> {
                var each = emptyArray<Int>()
                while (s.hasNext()) {
                    each += s.next().toInt()
                }
                mergedSort(each)
                printMethod(
                    """Total numbers: ${each.size}.
                |Sorted data: ${each.joinToString(separator = " ")}
                |""".trimMargin()
                )
            }
            "line" -> {
                var each = emptyArray<String>()
                while (s.hasNext()) {
                    each += s.nextLine()
                }
                each.sort()
                printMethod(
                    """Total lines: ${each.size}.
                    """.trimMargin()
                )
                each.forEach { printMethod("$it\n") }
            }
            else -> {
                var each = emptyArray<String>()
                while (s.hasNext()) {
                    each += s.next()
                }
                each.sort()
                printMethod("""Total words: ${each.size}.""".trimMargin())
            }
        }
        "byCount" -> when (dataType) {
            "long" -> {
                var each = emptyArray<Int>()
                while (s.hasNext()) {
                    each += s.next().toInt()
                }
                each.sort()
                var eachMap = each.groupingBy { it }.eachCount()
                eachMap = eachMap.toList().sortedBy { (key, value) -> value }.toMap()
                printMethod("Total numbers: ${each.size}.\n")
                eachMap.forEach { (k, v) -> printMethod("$k: $v time(s), ${((v.toDouble() / each.size) * 100).toInt()}%\n") }
            }
            "word" -> {
                var each = emptyArray<String>()
                while (s.hasNext()) {
                    each += s.next()
                }
                each.sort()
                var eachMap = each.groupingBy { it }.eachCount()
                eachMap = eachMap.toList().sortedBy { (key, value) -> value }.toMap()
                printMethod("Total words: ${each.size}.\n")
                eachMap.forEach { (k, v) -> printMethod("$k: $v time(s), ${((v.toDouble() / each.size) * 100).toInt()}%\n") }
            }
            "line" -> {
                var each = emptyArray<String>()
                while (s.hasNext()) {
                    each += s.nextLine()
                }
                each.sort()
                var eachMap = each.groupingBy { it }.eachCount()
                eachMap = eachMap.toList().sortedBy { (key, value) -> value }.toMap()
                printMethod("Total lines: ${each.size}.\n")
                eachMap.forEach { (k, v) -> printMethod("$k: $v time(s), ${((v.toDouble() / each.size) * 100).toInt()}%\n") }
            }
            else -> {
                var each = emptyArray<String>()
                while (s.hasNext()) {
                    each += s.next()
                }
                each.sort()
                printMethod("""Total words: ${each.size}.""".trimMargin())
            }
        }
    }
}
