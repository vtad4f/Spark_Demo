

import scala.collection.mutable.ListBuffer


val PATTERN = "[^\\s\"\\[\\]',]+".r


def MyMap(line : String) : List[(String, Array[Double])] = {
   
   val pages = PATTERN.findAllIn(line).toArray
   
   val ret = ListBuffer[(String, Array[Double])]()
   ret += ((pages(0), Array(1.0 - 0.85, pages.length - 1.0, 0.0)))
   
   for (i <- 1 to (pages.length - 1)) {
      ret += ((pages(i), Array(0.85 * 1.0 / (pages.length - 1.0), 0.0, 1.0)))
   }
   return ret.toList
}


def MyReduce(a : Array[Double], b : Array[Double]) : Array[Double] = {

   return Array(
      a(0) + b(0),
      a(1) + b(1),
      a(2) + b(2)
   )
}


def ToString(pair : ((String, Array[Double]))) : String = {

   return pair._1 + " " + pair._2.mkString(" ")
}


val lines   = sc.textFile("InputFolder/PRData.txt")
val pages   = lines.flatMap(MyMap)
val ranks   = pages.reduceByKey(MyReduce)
val strings = ranks.map(ToString)

strings.saveAsTextFile("OutputFolder")

System.exit(0)

