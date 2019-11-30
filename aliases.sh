#!/bin/bash

function clean  { git clean -dfqX ; }
function x      { git update-index --chmod=+x $1 ; }
function reload { git fetch ; git rebase origin/master ; source aliases.sh ; }

INPUT_DIR=InputFolder
OUTPUT_DIR=OutputFolder
REPO_DIR=/home/cloudera/git/vtad4f/Spark_Demo

function spark
{
   hadoop fs -rm -r $INPUT_DIR
   hadoop fs -mkdir $INPUT_DIR
   hadoop fs -copyFromLocal $REPO_DIR/input/*.txt $INPUT_DIR
   
   hadoop fs -rm -r $OUTPUT_DIR
   
   spark-shell -i $REPO_DIR/PageRank.scala
   
   hadoop fs -cat $OUTPUT_DIR/part-00000 | sort -k2 -r | head -n 10
}

