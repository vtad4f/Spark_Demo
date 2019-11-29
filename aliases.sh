#!/bin/bash

function clean  { git clean -dfqX ; }
function x      { git update-index --chmod=+x $1 ; }
function reload { git fetch ; git rebase origin/master ; source aliases.sh ; }

SPARK_DIR=/home/cloudera/git/vtad4f/Spark_Demo

function spark
{
   hadoop fs -rm -r InputFolder
   hadoop fs -mkdir InputFolder
   hadoop fs -copyFromLocal "$SPARK_DIR/input/input1.txt"
   
   spark-shell -i $SPARK_DIR/SimpleWordCount.scala
   
   hadoop fs -rm -r OutputFolder
   hadoop fs -cat OutputFolder/part-00000
}

