/**
  *
  * Copyright (C) 2016 Zalando SE
  *
  * This software may be modified and distributed under the terms
  * of the MIT license.  See the LICENSE file for details.
  */
package org.zalando.znap.source.nakadi

import akka.http.scaladsl.model.headers.CustomHeader

import scala.util.Try

/**
  * HTTP header for Nakadi cursor.
  */
class XNakadiCursors(partition: String, offset: String) extends CustomHeader {
  require(Try(partition.toInt).isSuccess)
  require(offset == "BEGIN" || (offset != null && offset.length > 0))

  override def name(): String = "X-Nakadi-Cursors"

  override def value(): String = {
    s"""[{"partition": "$partition", "offset":"$offset"}]"""
  }

  override def renderInRequests(): Boolean = true
  override def renderInResponses(): Boolean = false
}
