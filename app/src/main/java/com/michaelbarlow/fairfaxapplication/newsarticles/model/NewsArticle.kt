package com.michaelbarlow.fairfaxapplication.newsarticles.model

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

    /**
     * To get the smallest image, we find the image with the smallest
     * width attribute.
     *
     * There is a chance that there are no images related to the story, which
     * the app should handle gracefully
     */
    fun getSmallestImageURL():String? {
        return relatedImages?.minBy { it.width }?.url
    }

}
