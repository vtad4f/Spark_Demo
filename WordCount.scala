val loadfile = sc.textFile("InputFolder/input.txt")
val fm = loadfile.flatMap(line => line.split(" "))
val mp = fm.map(word => (word,1))
val counts = mp.reduceByKey(_+_)
System.out.println(counts.collect().mkString("\n"))
//spark-shell -i '/home/cloudera/git/Spark_Demo/WordCount.scala'
