package com.alphaprojects.nitro

import org.scalatest.{FunSuite, Matchers}

// Verification source: http://www.wou.edu/~burtonl/josephus.html
class JosephusProblemSpec extends FunSuite with Matchers {
  private val circleSizes = 1 to 16

  test("should hold for step 2") {
    val expectedSolutions = Seq(1, 1, 3, 1, 3, 5, 7, 1, 3, 5, 7, 9, 11, 13, 15, 1)
    val actualSolutions = circleSizes.map(circleSize => JosephusProblem.survivorRecursively(circleSize, 2))

    actualSolutions shouldEqual expectedSolutions
  }

  test("should hold for step 3") {
    val expectedSolutions = Seq(1, 2, 2, 1, 4, 1, 4, 7, 1, 4, 7, 10, 13, 2, 5, 8)
    val actualSolutions = circleSizes.map(circleSize => JosephusProblem.survivorRecursively(circleSize, 3))

    actualSolutions shouldEqual expectedSolutions
  }

  test("should hold for step 8") {
    val expectedSolutions = Seq(1, 1, 3, 3, 1, 3, 4, 4, 3, 1, 9, 5, 13, 7, 15, 7)
    val actualSolutions = circleSizes.map(circleSize => JosephusProblem.survivorRecursively(circleSize, 8))

    actualSolutions shouldEqual expectedSolutions
  }

  test("what happens when circle size equals step") {
    val actualSolutions = (2 to 30).map(circleSize => JosephusProblem.survivorRecursively(circleSize, circleSize))
    println(actualSolutions.mkString(" "))
  }

  test("what happens when circle size = 3 and step = 2") {
    val circleSize = 3
    val step = 2

    JosephusProblem.survivorRecursively(circleSize, step) shouldEqual circleSize
  }

}