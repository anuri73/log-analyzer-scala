package com.log.app

import scala.util.matching.Regex

case class ApacheAccessLog(
                              ipAddress: String,
                              clientIdentity: String,
                              userId: String,
                              dateTime: String,
                              method: String,
                              endpoint: String,
                              protocol: String,
                              httpCode: Short,
                              length: Long
                          ) {}

object ApacheAccessLog {
    val PATTERN: Regex = """^(\S+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})] "(\S+) (\S+) (\S+)" (\d{3}) (\d+)""".r

    def parseLogLine(log: String): ApacheAccessLog = {
        log match {
            case PATTERN(ipAddress, clientIdentity, userId, dateTime, method, endpoint, protocol, httpCode, length) =>
                ApacheAccessLog(ipAddress, clientIdentity, userId, dateTime, method, endpoint, protocol, httpCode.toShort, length.toLong)
            case _ => throw new RuntimeException(s"""Can not parse log line: $log""")
        }

    }
}
