package uk.co.sentinelweb.subtitler.domain

data class TimedText(
    val text:String,
    val start:Long,
    val duration:Long
)