# BaseFeed Module

다양한 화면에서 사용할 수 있는 Feed 모듈

# Preview
<img src="./screenshot/demonstrate.gif" alt="image" width="300" height="auto">

## How to use
<img src="./screenshot/preview.png" alt="image" width="600" height="auto">

```
dependencies {
	implementation 'com.github.sarang628:BaseFeed:6307ce6df9'
}	
```

```
/**
 * Feed 항목
 * @param review 리뷰 데이터
 * @param isZooming pinch zoom 여부
 * @param progressTintColor ratingBar 색
 * @param favoriteColor 즐겨찾기 색
 * @param onImage 이미지 클릭
 * @param onProfile 프로필 클릭
 * @param onLike 좋아요 클릭
 * @param onComment 코멘트 클릭
 * @param onShare 공유 클릭
 * @param onFavorite 즐겨찾기 클릭
 * @param onMenu 메뉴 클릭
 * @param onName 사용자명 클릭
 * @param onRestaurant 음식점명 클릭
 * @param onLikes 좋아요 클릭
 * @param imageLoadCompose 공통 이미지 compose
 */
@Composable
fun Feed(
    review: Review,
    isZooming: ((Boolean) -> Unit),
    progressTintColor: Color = Color(0xffe6cc00),
    favoriteColor: Color = Color(0xffe6cc00),
    onImage: ((Int) -> Unit),
    onProfile: (() -> Unit),
    onLike: (() -> Unit),
    onComment: (() -> Unit),
    onShare: (() -> Unit),
    onFavorite: (() -> Unit),
    onMenu: (() -> Unit),
    onName: (() -> Unit),
    onRestaurant: (() -> Unit),
    onLikes: (() -> Unit),
    imageLoadCompose: @Composable (Modifier, String, Dp?, Dp?, ContentScale?) -> Unit,
)
```

## Feature

- Jetpack Compose
- Dependency injection with Hilt
- Compose ConstraintLayout
- Localization
- ExpandableText

## convention

### package

- compose로 구현한 UI는 compose 패키지에 저장
- uistate는 uistate 패키지에 저장
  <img src="./screenshot/package.png" alt="image" width="300" height="auto">

## Architecture

### UI element

[Feed.kt](./library/src/main/java/com/sarang/torang/compose/feed/Feed.kt)
[Feeds.kt](./library/src/main/java/com/sarang/torang/compose/feed/Feeds.kt)

### UI layer

UiState

```
sealed interface FeedsUiState {
    object Loading : FeedsUiState
    object Empty : FeedsUiState
    data class Success(val reviews: List<Review>) : FeedsUiState
    data class Error(val message: String) : FeedsUiState
}
```

## Update
gradle.build.kts 로 변경
