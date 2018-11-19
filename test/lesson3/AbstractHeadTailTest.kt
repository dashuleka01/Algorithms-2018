package lesson3

import java.util.SortedSet
import kotlin.test.*

abstract class AbstractHeadTailTest {
    private lateinit var tree: SortedSet<Int>

    protected fun fillTree(empty: SortedSet<Int>) {
        this.tree = empty
        //В произвольном порядке добавим числа от 1 до 10
        tree.add(5)
        tree.add(1)
        tree.add(2)
        tree.add(7)
        tree.add(9)
        tree.add(10)
        tree.add(8)
        tree.add(4)
        tree.add(3)
        tree.add(6)
    }


    protected fun doHeadSetTest() {
        var set: SortedSet<Int> = tree.headSet(5)
        assertEquals(true, set.contains(1))
        assertEquals(true, set.contains(2))
        assertEquals(true, set.contains(3))
        assertEquals(true, set.contains(4))
        assertEquals(false, set.contains(5))
        assertEquals(false, set.contains(6))
        assertEquals(false, set.contains(7))
        assertEquals(false, set.contains(8))
        assertEquals(false, set.contains(9))
        assertEquals(false, set.contains(10))


        set = tree.headSet(127)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

    }



    protected fun myHeadSetTest(create: () -> CheckableSortedSet<Int>) {
        val list = mutableListOf<Int>(4, 2, 1, 3, 5, 7, 6, 8) //my test

        list.clear() //краевой тест
        list.add(2)
        val binarySet1 = create()
        for (element in list) {
            binarySet1 += element
        }
        assertEquals(sortedSetOf(), binarySet1.headSet(0))

        list.clear() //тест на производительность
        val binarySet2 = create()
        for (i in 1..10000)
            list.add(i)
        list.shuffle()
        for (element in list)
            binarySet2 += element
        list.remove(10000)
        assertEquals(list.toSortedSet(), binarySet2.headSet(10000))
    }

    protected fun doTailSetTest() {
        var set: SortedSet<Int> = tree.tailSet(5)
        assertEquals(false, set.contains(1))
        assertEquals(false, set.contains(2))
        assertEquals(false, set.contains(3))
        assertEquals(false, set.contains(4))
        assertEquals(true, set.contains(5))
        assertEquals(true, set.contains(6))
        assertEquals(true, set.contains(7))
        assertEquals(true, set.contains(8))
        assertEquals(true, set.contains(9))
        assertEquals(true, set.contains(10))

        set = tree.tailSet(-128)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

    }

    protected fun myTailSetTest(create: () -> CheckableSortedSet<Int>) {
        val list = mutableListOf<Int>(4, 2, 1, 3, 5, 7, 6, 8) //my test

        list.clear() //краевой тест
        list.add(1)
        val binarySet1 = create()
        for (element in list) {
            binarySet1 += element
        }
        assertEquals(sortedSetOf(), binarySet1.tailSet(2))

        list.clear() //тест на производительность
        val binarySet2 = create()
        for (i in 1..10000)
            list.add(i)
        list.shuffle()
        for (element in list)
            binarySet2 += element
        assertEquals(list.toSortedSet(), binarySet2.tailSet(1))
    }

    protected fun doHeadSetRelationTest() {
        val set: SortedSet<Int> = tree.headSet(7)
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
        tree.add(0)
        assertTrue(set.contains(0))
        set.remove(4)
        assertFalse(tree.contains(4))
        tree.remove(6)
        assertFalse(set.contains(6))
        tree.add(12)
        assertFalse(set.contains(12))
        assertEquals(5, set.size)
        assertEquals(10, tree.size)
    }

    protected fun doTailSetRelationTest() {
        val set: SortedSet<Int> = tree.tailSet(4)
        assertEquals(7, set.size)
        assertEquals(10, tree.size)
        tree.add(12)
        assertTrue(set.contains(12))
        set.remove(4)
        assertFalse(tree.contains(4))
        tree.remove(6)
        assertFalse(set.contains(6))
        tree.add(0)
        assertFalse(set.contains(0))
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
    }

    protected fun doSubSetTest() {
        var set: SortedSet<Int> = tree.subSet(3, 6)
        assertEquals(false, set.contains(1))
        assertEquals(false, set.contains(2))
        assertEquals(true, set.contains(3))
        assertEquals(true, set.contains(4))
        assertEquals(true, set.contains(5))
        assertEquals(false, set.contains(6))
        assertEquals(false, set.contains(7))
        assertEquals(false, set.contains(8))
        assertEquals(false, set.contains(9))
        assertEquals(false, set.contains(10))

        set = tree.subSet(-128, 128)
        for (i in 1..10)
            assertEquals(true, set.contains(i))
    }

    protected fun mySubSetTest(create: () -> CheckableSortedSet<Int>) {
        val list = mutableListOf<Int>(4, 2, 1, 3, 5, 7, 6, 8) //обычный тест

        list.clear() //краевой тест
        list.add(1)
        val binarySet1 = create()
        for (element in list) {
            binarySet1 += element
        }
        assertEquals(binarySet1.subSet(2, 3), sortedSetOf())

        list.clear() //тест на производительность
        val binarySet2 = create()
        for (i in 1..10000)
            list.add(i)
        list.shuffle()
        for (element in list)
            binarySet2 += element
        list.remove(10000)
        assertEquals(list.toSortedSet(), binarySet2.subSet(1, 10000))
    }

}