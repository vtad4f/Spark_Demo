#!/bin/bash

function clean  { git clean -dfqX ; }
function x      { git update-index --chmod=+x $1 ; }
function reload { git fetch ; git rebase origin/master ; source aliases.sh ; }


function spark  { spark-shell -i /home/maria_dev/Spark_Demo/SimpleWordCount.scala ; }

