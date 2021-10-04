package com.log.app

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object LogAnalyzer extends App {
    val sparkConfig = new SparkConf()
        .setMaster("local")
        .setAppName("Log analyzer in scala")
    val sc = new SparkContext(sparkConfig)

    if (args.length == 0) {
        System.out.println("Must specify an access logs file.")
        System.exit(-1)
    }

    val logFile = args(0)

    val accessLogs: RDD[ApacheAccessLog] = sc
        .textFile(logFile)
        .map(ApacheAccessLog.parseLogLine).cache()

    val contentSizes: RDD[Long] = accessLogs.map(_.length).cache()

    println("Content Size Avg: %s, Min: %s, Max: %s".format(
        contentSizes.reduce(_ + _) / contentSizes.count,
        contentSizes.min,
        contentSizes.max))

    sc.stop()
}
