@file:Suppress("UNUSED_PARAMETER")

package lesson6

import kotlin.system.exitProcess

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */

fun longestCommonSubSequence(first: String, second: String): String {
    var matrix = Array(first.length + 1, { Array(second.length + 1, { 0 }) })
    var previous = Array(first.length + 1, { Array(second.length + 1, { Pair<Int, Int>(0, 0) }) })
    for (i in 1..first.length) {
        for (j in 1..second.length) {
            when {
                first[i - 1] == second[j - 1] -> {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1
                    previous[i][j] = Pair(i - 1, j - 1)
                }
                matrix[i - 1][j] >= matrix[i][j - 1] -> {
                    matrix[i][j] = matrix[i - 1][j]
                    previous[i][j] = Pair(i - 1, j)
                }
                else -> {
                    matrix[i][j] = matrix[i][j - 1]
                    previous[i][j] = Pair(i, j - 1)
                }
            }
        }
    }
    return printLCSS(first.length, second.length, previous, first)
}

//Сложность = O(n*m), Ресурсоемкость = O(n*m)

fun printLCSS(i: Int, j: Int, prev: Array<Array<Pair<Int, Int>>>, word: String): String {
    var b = ""
    var k = i
    var l = j
    while (k != 0 && l != 0) {
        when {
            prev[k][l] == Pair(k - 1, l - 1) -> {
                b += word[k - 1]
                k--
                l--
            }
            prev[k][l] == Pair(k - 1, l) -> k--
            else -> l--
        }
    }
    return b.reversed()
}


/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return listOf()
    val resList = mutableListOf<Int>()
    val n = list.size
    val previous = Array(n, { 0 })
    val array = Array(n, { 0 })
    for (i in 0 until n) {
        array[i] = 1
        previous[i] = -1
        for (j in 0 until i) {
            if (list[j] < list[i] && array[j] + 1 > array[i]) {
                array[i] = array[j] + 1
                previous[i] = j
            }
        }
    }
    var position = 0
    var length = array[0]
    for (k in 0 until n) {
        if (array[k] > length) {
            position = k
            length = array[k]
        }
    }
    while (position != -1) {
        resList.add(list[position])
        position = previous[position]
    }
    resList.reverse()
    return resList
}

//Трудоемкость = O(n*n), Ресурсоемкость = O(n)
/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5