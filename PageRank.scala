

import scala.collection.mutable.ListBuffer


val pattern = "[^\\s\"\\[\\]',]+".r

def MyMap(line : String) : List[(String, Array[Double])] = {
   
   val pages = pattern.findAllIn(line).toArray
   
   val ret = ListBuffer[(String, Array[Double])]()
   ret += ((pages(0), Array(1.0 - 0.85, pages.length - 1.0, 0.0)))
   
   for (i <- 1 to (pages.length - 1)) {
      ret += ((pages(i), Array(0.85 * 1.0 / (pages.length - 1.0), 0.0, 1.0)))
   }
   return ret.toList
}

val lines = sc.textFile("InputFolder/PRData.txt")
val pages = lines.flatMap(MyMap)
val ranks = pages.reduceByKey((v1, v2) => v1 + v2)

ranks.saveAsTextFile("OutputFolder")

System.exit(0)

