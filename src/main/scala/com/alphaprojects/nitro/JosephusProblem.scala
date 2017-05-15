package com.alphaprojects.nitro

object JosephusProblem extends App {
  require(args.length == 2, s"two arguments expected, was ${args.length}")

  val circleSize = args(0).toInt
  val step = args(1).toInt

  println(s"Given the circle size $circleSize and step $step, " +
    s"the survivor will be in position ${survivor(circleSize, step)}")

  /**
    * @param n The number of people standing in the circle, waiting to be killed
    * @param k The position of the unfortunate one to be executed next
    * @return  The lucky one
    */
  def survivor(n: Int, k: Int): Int = {
    require(n > 0, s"expected: circle size > 0, was $n")
    require(k > 0, s"expected: step > 0, was $k")

    (n, k) match {
      case (_, 2) => specialCaseStepEquals2(n)
      case _ => survivorIteratively(n, k)
    }
  }

  /**
    * An iterative solution with runtime growing on the order of Q(n)
    * Can be used in place of the recursive solution (and looks simpler)
    */
  def survivorIteratively(n: Int, k: Int): Int =
    (1 to n).foldLeft(1)((r, c) => (r + k - 1) % c + 1)

  /**
    * The special case for step=2 that optimizes the calculation to run in constant time (O(1))
    */
  private def specialCaseStepEquals2(n: Int): Int =
    2 * (n - Integer.highestOneBit(n)) + 1


  /**
    * Recursive solution is here for illustration only: not being tail-recursive, this function
    * will produce stack overflow when the circle size is too large (in my tests, > ~3500).
    *
    * Its runtime should grow on the order of the size of the input (Q(n))
    */
  def survivorRecursively(n: Int, k: Int): Int = {
    n match {
      case 1 => 1
      case _ => ((survivorRecursively(n - 1, k) + k - 1) % n) + 1
    }
  }

}