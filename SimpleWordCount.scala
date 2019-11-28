

val loadfile = sc.textFile("input/input1.txt")
val words = loadfile.flatMap(line => line.split(" "))
val wordMap = words.map(word => (word,1))
val wordCount = wordMap.reduceByKey(_+_)
val sorted = wordCount.sortBy(-_._2)
val filter = sorted.filter(_._2 > 1)
filter.saveAsTextFile("OutputFolder")
System.exit(0)

