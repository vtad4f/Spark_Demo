#!/bin/bash

function clean  { git clean -dfqX ; }
function x      { git update-index --chmod=+x $1 ; }
function reload { git fetch ; git rebase origin/master ; source aliases.sh ; }

INPUT_DIR=InputFolder
REPO_DIR=/home/cloudera/git/vtad4f/Spark_Demo

function spark
{
   hadoop fs -rm -r $INPUT_DIR
   hadoop fs -mkdir $INPUT_DIR
   hadoop fs -copyFromLocal $REPO_DIR/input/*.txt $INPUT_DIR
   
   spark-shell -i $REPO_DIR/SimpleWordCount.scala
   
   hadoop fs -rm -r OutputFolder
   hadoop fs -cat OutputFolder/part-00000
}

