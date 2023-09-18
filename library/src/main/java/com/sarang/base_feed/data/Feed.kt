package com.sarang.base_feed.data

data class Feed(
    val reviewId: Int,              /* 리뷰ID*/
    val userId: Int,                /* 사용자ID */
    val name: String,               /* 이름 */
    val restaurantName: String,     /* 식당명 */
    val rating: Float,              /* 평점 */
    val profilePictureUrl: String,  /* 사용자 프로필 URL */
    val likeAmount: Int,            /* 좋아요 총 개수 */
    val commentAmount: Int,         /* 코멘트 총 개수 */
    val author: String? = null,             /* 첫번째 코멘트 사용자 */
    val author1: String? = null,            /* 두번째 코멘트 사용자 */
    val author2: String? = null,            /* 세번째 코멘트 사용자 */
    val isLike: Boolean,            /* 좋아요 여부 */
    val isFavorite: Boolean,        /* 즐겨찾기 여부 */
    val comment: String? = null,            /* 첫번째 코멘트 */
    val comment1: String? = null,           /* 두번쨰 코멘트 */
    val comment2: String? = null,           /* 세번쨰 코멘트 */
    val reviewImages: List<String>, /* 리뷰 이미지들 */
    val contents: String            /* 내용 */
)
