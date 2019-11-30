

import scala.collection.mutable.ListBuffer


val PATTERN = "[^\\s\"\\[\\]',]+".r


class MyArray extends Array[Double]
{
   
}


def MyMap(line : String) : List[(String, MyArray)] = {
   
   val pages = PATTERN.findAllIn(line).toArray
   
   val ret = ListBuffer[(String, MyArray)]()
   ret += ((pages(0), Array(1.0 - 0.85, pages.length - 1.0, 0.0)))
   
   for (i <- 1 to (pages.length - 1)) {
      ret += ((pages(i), Array(0.85 * 1.0 / (pages.length - 1.0), 0.0, 1.0)))
   }
   return ret.toList
}


def MyReduce(a : MyArray, b : MyArray) : MyArray = {

   return Array(
      a(0) + b(0),
      a(1) + b(1),
      a(2) + b(2)
   )
}


val lines = sc.textFile("InputFolder/PRData.txt")
val pages = lines.flatMap(MyMap)
val ranks = pages.reduceByKey(MyReduce)

ranks.saveAsTextFile("OutputFolder")

System.exit(0)

