package lesson3

import java.util.SortedSet
import kotlin.NoSuchElementException
import jdk.nashorn.internal.objects.NativeArray.pop
import java.util.Stack


// Attention: comparable supported but comparator is not
class KtBinaryTree<T : Comparable<T>> : AbstractMutableSet<T>(), CheckableSortedSet<T> {

    private var root: Node<T>? = null

    override var size = 0
        private set

    private class Node<T>(var value: T) {

        var left: Node<T>? = null

        var right: Node<T>? = null

    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        size++
        return true
    }

    override fun checkInvariant(): Boolean =
            root?.let { checkInvariant(it) } ?: true

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    override fun remove(element: T): Boolean {
        TODO()
    }


    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? =
            root?.let { find(it, value) }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    inner class BinaryTreeIterator : MutableIterator<T> {

        private var current: Node<T>? = null

        private fun inOrderTraversal(): List<Node<T>> {
            var stack = Stack<Node<T>>()
            var curNode = root
            var left = true
            var list = mutableListOf<Node<T>>()

            if (root != null) {
                stack.push(curNode)

                while (stack.count() > 0) {
                    if (left) {
                        while (curNode?.left != null) {
                            stack.push(curNode)
                            curNode = curNode?.left
                        }
                    }

                    list.add(curNode!!)

                    if (curNode?.right != null) {
                        curNode = curNode.right
                        left = true
                    } else {
                        curNode = stack.pop()
                        left = false
                    }
                }
            }
            return list
        }
        //Трудоемкость = O(n * m), Ресурсоемкость = O(n * m)

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private var list = inOrderTraversal()
        private var iter = list.iterator()

        private fun findNext(): Node<T>? {
            return iter.next()
        }
        //Трудоемкость = O(n * m), Ресурсоемкость = O(n * m)

        override fun hasNext(): Boolean = findNext() != null

        override fun next(): T {
            current = findNext()
            return (current ?: throw NoSuchElementException()).value
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        override fun remove() {
            TODO()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        val set = sortedSetOf<T>()
        val iterator = BinaryTreeIterator()
        var x = iterator.next()
        try {
            while (x < fromElement) {
                x = iterator.next()
            }
            while (x >= fromElement && x < toElement){
                set.add(x)
                x = iterator.next()
            }
        } catch (e: Exception){}
        return set
    }
    //Трудоемкость = O(n * m * p), Ресурсоемкость = O(n * m * p)

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    override fun headSet(toElement: T): SortedSet<T> {
        val set = subSet(first(), toElement)
        if(size != 1) set.add(first())
        return set
    }
    //Трудоемкость = O(n * m * p), Ресурсоемкость = O(n * m * p)

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    override fun tailSet(fromElement: T): SortedSet<T> {
        val set = subSet(fromElement, last())
        if (size != 1) set.add(last())
        return set
    }
    //Трудоемкость = O(n * m * p), Ресурсоемкость = O(n * m * p)

    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
