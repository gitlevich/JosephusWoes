package com.alphaprojects.nitro

import org.scalatest.{FunSuite, Matchers}
import JosephusProblem._

class JosephusProblemSpec extends FunSuite with Matchers {
  private val circleSizes = 1 to 16

  test("should require circle of at least 1") {
    assertThrows[IllegalArgumentException] {
      survivor(n = 0, k = 2)
    }
  }

  test("should require step greater than 0") {
    assertThrows[IllegalArgumentException] {
      survivor(n = 4, k = 0)
    }
  }

  test("should hold for step 2") {
    val expectedSolutions = Seq(1, 1, 3, 1, 3, 5, 7, 1, 3, 5, 7, 9, 11, 13, 15, 1)
    val actualSolutions = circleSizes.map(circleSize => survivor(circleSize, 2))

    actualSolutions shouldEqual expectedSolutions
  }

  test("should hold for step other than 2") {
    val expectedSolutions = Seq(1, 2, 2, 1, 4, 1, 4, 7, 1, 4, 7, 10, 13, 2, 5, 8)
    val actualSolutions = circleSizes.map(circleSize => survivor(circleSize, 3))

    actualSolutions shouldEqual expectedSolutions
  }

  test("what happens when circle size equals step (the first person gets eliminated after the first round?)") {
    val expectedSolutions = Seq(1, 1, 2, 2, 2, 4, 5, 4, 8, 8, 7, 11, 8, 13, 4, 11)
    val actualSolutions = circleSizes.map(circleSize => survivor(circleSize, circleSize))

    actualSolutions shouldEqual expectedSolutions
  }

  test("what happens when circle size = 3 and step = 2") {
    val circleSize = 3
    val step = 2

    survivor(circleSize, step) shouldEqual circleSize
  }

  test("recursive implementation should produce same result as iterative") {
    val iterativeResult = circleSizes.map(circleSize => survivor(circleSize, 4))
    val recursiveResult = circleSizes.map(circleSize => survivorRecursively(circleSize, 4))

    iterativeResult shouldEqual recursiveResult
  }

}