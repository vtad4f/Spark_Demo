
val pattern = "[^\\s\"\\[\\]',]+".r

def MyMap(line : String) : Array[Array[Double]] = {
   
   val pages = pattern.findAllIn(line)
   
   val ret = Array.ofDim(pages.length(), 2)
   ret(0)(0) = pages(0)
   ret(0)(1) = Array( 1.0 - 0.85, pages.length - 1.0, 0.0 )
   
   for (i <- 1 to (pages.length - 1)) {
      ret(i)(0) = pages(i)
      ret(i)(1) = Array( 0.85 * 1.0 / (pages.length - 1.0), 0.0, 1.0 )
   }
   return ret
}

val lines = sc.textFile("InputFolder/PRData.txt")
val pages = lines.flatMap(MyMap(line))
val ranks = pages.reduceByKey((v1, v2) => v1 + v2)

ranks.saveAsTextFile("OutputFolder")

System.exit(0)

