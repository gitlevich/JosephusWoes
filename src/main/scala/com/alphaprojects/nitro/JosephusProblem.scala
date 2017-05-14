package com.alphaprojects.nitro

object JosephusProblem extends App {
  require(args.length == 2, s"two arguments expected, was ${args.length}")

  val circleSize = args(0).toInt
  val step = args(1).toInt

  println(s"Given the circle size $circleSize and step $step, " +
    s"the survivor will be in position ${survivor(circleSize, step)}")

  /**
    * This method is here just to demonstrate that I am aware of the limitations of non-tail recursion,
    * and to avoid stack overflow, it falls back to an iterative implementation when it decides, quite
    * arbitrarily, that the circle size is too large for the recursive implementation.
    *
    * @param circleSize The number of people standing in the circle, waiting to be killed
    * @param step How many people the evil executioner will skip when determining the next target
    * @return The lucky one
    */
  def survivor(circleSize: Int, step: Int): Int = {
    require(circleSize >= 1, s"expected: circle size >= 1, was $circleSize")
    require(step > 1, s"expected: step > 1, was $step")

    (circleSize, step) match {
      case (_, 2) => survivorIterativelyStepEqualsTwoSpecialCase(circleSize)
      case (c, _) if notVeryLarge(c) => survivorRecursively(circleSize, step)
      case _ => survivorIteratively(circleSize, step)
    }
  }

  /**
    * This method is here solely to show that I know what a recursion is. Unfortunately, I am not smart enough
    * to implement it as a tail recursion, so this method will produce a stack overflow when the circle size
    * is large enough (in my tests, greater than ~6500)
    * It should perform in Q(n)
    */
  private def survivorRecursively(circleSize: Int, step: Int): Int = circleSize match {
    case 1 => 1
    case _ => ((survivorRecursively(circleSize - 1, step) + step - 1) % circleSize) + 1
  }

  /**
    * This is generally a preferred way to implement this solution as it will not cause a stack overflow.
    * It will perform in Q(n)
    */
  private def survivorIteratively(circleSize: Int, step: Int): Int =
    (1 to circleSize).foldLeft(1)((r, c) => (r + step - 1) % c + 1)

  /**
    * Finally, this is the special case for step=2 that makes calculation perform in O(1)
    */
  private def survivorIterativelyStepEqualsTwoSpecialCase(circleSize: Int): Int =
    2 * (circleSize - Integer.highestOneBit(circleSize)) + 1

  private def notVeryLarge(circleSize: Int): Boolean = circleSize < 3000
}
