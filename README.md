# BaseFeed
메인 화면, 프로필 화면 등 다양한 화면에서 피드를 사용하여 공통 컴포넌트 모듈로 제공

## convention
### package
- compose로 구현한 UI는 compose 패키지에 저장
- uistate는 uistate 패키지에 저장
<img src="./screenshot/package.png" alt="image" width="300" height="auto">


## Architecture
### UI layer
UiState
```
/*피드 UIState*/
data class FeedUiState(
    val reviews: List<Review>,  // 리뷰 리스트
    val isLoaded: Boolean,      // 최초 리스트 데이터 로드되었는지?
    val isRefreshing: Boolean   // 갱신중인지 여부
)

// 리스트 표시 여부
val FeedUiState.isVisibleList: Boolean get() = isLoaded && !reviews.isEmpty()

// 리스트가 비어있는지 여부
val FeedUiState.isEmpty: Boolean get() = isLoaded && reviews.isEmpty()

fun testFeedUiState(): FeedUiState {
    return FeedUiState(
        reviews = testReviewList(),
        isLoaded = true,
        isRefreshing = false
    )
}
```


## Unit test
- 최초 로딩 중일 때 리스트가 나오면 안된다.
- 최초 로딩이 되었고 리스트가 비어있다면 리스트가 비어있음을 표시한다.
- 최초 로딩이 되었고 리스트가 있다면 리스트를 표시한다.

## 화면
<img src="./screenshot/demonstrate.gif" alt="image" width="300" height="auto">