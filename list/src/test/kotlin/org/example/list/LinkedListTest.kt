package org.example.list

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class LinkedListTest {
    @Test
    fun `a new list is empty`() {
        val list = LinkedList()

        list.size() shouldBe 0
    }

    @Test
    fun `adds the elements in order`() {
        val list = LinkedList()

        list.add("one")
        list.add("two")

        list.size() shouldBe 2
        list.get(0) shouldBe "one"
        list.get(1) shouldBe "two"
    }

    @Test
    fun `removes an existing element`() {
        val list = LinkedList()

        list.add("one")
        list.add("two")

        list.remove("one") shouldBe true

        list.size() shouldBe 1
        list.get(0) shouldBe "two"

        list.remove("two") shouldBe true
        list.size() shouldBe 0
    }

    @Test
    fun `does not remove a missing element`() {
        val list = LinkedList()

        list.add("one")
        list.add("two")

        list.remove("three") shouldBe false
        list.size() shouldBe 2
    }
}
