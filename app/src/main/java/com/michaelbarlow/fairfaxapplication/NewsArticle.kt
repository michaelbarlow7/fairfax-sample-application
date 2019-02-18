package com.michaelbarlow.fairfaxapplication

data class NewsArticle(val id: Long = -1,
                       val headline: String? = null,
                       val theAbstract: String? = null,
                       val byLine: String? = null,
                       val timeStamp: Long = -1,
                       val url: String? = null,
                       val relatedImages: List<RelatedImage>? = null) {

    data class RelatedImage(val id: Long = -1,
                            val url: String? = null,
                            val width: Int = 0,
                            val height: Int = 0)

    fun getSmallestImageURL():String? {
        return relatedImages?.minBy { it.width }?.url
    }

}
