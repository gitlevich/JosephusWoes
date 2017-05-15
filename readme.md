## A solution to Josephus problem

To run, in the project root execute the following:

`sbt "run n k"`

where:
- `n` is the number of people standing in the circle, waiting to be killed 
- `k` is the position of the person who gets killed after the round, after k-1 people got skipped

#### References

- Problem description and algorithm: https://en.wikipedia.org/wiki/Josephus_problem 
- Expected numbers against which the tests validate the solution are available at http://www.wou.edu/~burtonl/josephus.html
