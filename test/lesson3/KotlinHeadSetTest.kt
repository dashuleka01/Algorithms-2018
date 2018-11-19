package lesson3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import kotlin.test.Test

class KotlinHeadSetTest : AbstractHeadTailTest() {

    @BeforeEach
    fun fillTree() {
        fillTree(KtBinaryTree())
    }

    private fun <T : Comparable<T>> createKotlinTree(): CheckableSortedSet<T> = KtBinaryTree()

    @Test
    @Tag("Normal")
    fun headSetTest() {
        doHeadSetTest()
        myHeadSetTest { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun headSetRelationTest() {
        doHeadSetRelationTest()
    }

    @Test
    @Tag("Normal")
    fun tailSetTest() {
        doTailSetTest()
        myTailSetTest { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun tailSetRelationTest() {
        doTailSetRelationTest()
    }


    @Test
    @Tag("Impossible")
    fun subSetTest() {
        mySubSetTest { createKotlinTree() }
        doSubSetTest()
    }
}