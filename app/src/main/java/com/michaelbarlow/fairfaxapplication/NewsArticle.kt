package com.michaelbarlow.fairfaxapplication

data class NewsArticle(val id: Long = -1,
                       val headline: String?,
                       val theAbstract: String?,
                       val byLine: String?,
                       val timeStamp: Long = -1,
                       val url: String?,
                       val relatedImages: List<RelatedImage>?) {

    data class RelatedImage(val id: Long = -1,
                            val url: String?,
                            val width: Int = 0,
                            val height: Int = 0)

    val smallestImageURL:String? by lazy {
        relatedImages?.minBy { it.width }?.url
    }

}
