package com.alphaprojects.nitro

object JosephusProblem extends App {
  require(args.length == 2, s"two arguments expected, was ${args.length}")

  val circleSize = args(0).toInt
  val step = args(1).toInt

  println(s"Given the circle size $circleSize and step $step, the survivor will be in position ${survivor(circleSize, step)}")

  def survivor(circleSize: Int, step: Int): Int =
    if (notVeryLarge(circleSize))
      survivorRecursively(circleSize, step)
    else
      survivorIteratively(circleSize, step)


  def survivorRecursively(circleSize: Int, step: Int): Int = {
    require(circleSize >= 1, s"expected: circle size >= 1, was $circleSize")
    require(step > 1, s"expected: step > 1, was $step")

    (circleSize, step) match {
      case (1, _) => 1
      case (_, 2) => 2 * (circleSize - Integer.highestOneBit(circleSize)) + 1
      case _ => ((survivorRecursively(circleSize - 1, step) + step - 1) % circleSize) + 1
    }
  }

  def survivorIteratively(circleSize: Int, step: Int): Int = {
    (1 to circleSize).foldLeft(1)((r, c) => (r + step - 1) % c + 1)
  }

  private def notVeryLarge(circleSize: Int): Boolean = circleSize < 3000
}
