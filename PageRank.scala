
val pattern = "[\\s\"\\[\\]',]+".r


val loadfile = sc.textFile("InputFolder/PRData.txt")
val words = loadfile.flatMap(line => pattern.findAllIn(line))

val wordMap = words.map(word => (word,1))
val wordCount = wordMap.reduceByKey((v1, v2) => v1 + v2)

// val sorted = wordCount.sortBy(-_._2)
// val filter = sorted.filter(_._2 > 1)

wordCount.saveAsTextFile("OutputFolder")

System.exit(0)

