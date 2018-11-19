package lesson3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinHeadSetTest : AbstractHeadTailTest() {

    @BeforeEach
    fun fillTree() {
        fillTree(KtBinaryTree())
    }

    private fun <T : Comparable<T>> createKotlinTree(): CheckableSortedSet<T> = KtBinaryTree()

    private fun doHeadSetTest(create: () -> CheckableSortedSet<Int>) {
        val list = mutableListOf<Int>(4, 2, 1, 3, 5, 7, 6, 8) //my test
        val binarySet = create()
        for (element in list)
            binarySet += element
        assertEquals(sortedSetOf(1, 2, 3, 4), binarySet.headSet(5))

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

    @Test
    @Tag("Normal")
    fun headSetTest() {
        doHeadSetTest { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun headSetRelationTest() {
        doHeadSetRelationTest()
    }

    private fun doTailSetTest(create: () -> CheckableSortedSet<Int>) {
        val list = mutableListOf<Int>(4, 2, 1, 3, 5, 7, 6, 8) //my test
        val binarySet = create()
        for (element in list)
            binarySet += element
        assertEquals(sortedSetOf(5, 6, 7, 8), binarySet.tailSet(5))

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

    @Test
    @Tag("Normal")
    fun tailSetTest() {
        doTailSetTest { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun tailSetRelationTest() {
        doTailSetRelationTest()
    }

    private fun doSubSetTest(create: () -> CheckableSortedSet<Int>) {
        val list = mutableListOf<Int>(4, 2, 1, 3, 5, 7, 6, 8) //обычный тест
        val binarySet = create()
        for (element in list) {
            binarySet += element
        }
        val tree = binarySet.subSet(2, 6)
        assertEquals(binarySet.subSet(2, 6), sortedSetOf(2, 3, 4, 5))

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

    @Test
    @Tag("Impossible")
    fun subSetTest() {
        doSubSetTest { createKotlinTree() }
    }
}