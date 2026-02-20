# BaseFeed Module

- 음식점 리뷰 **'리스트 항목(FeedItem)'** UI.
- **'모듈화'** 로 여러 화면에 **'일관성'** 있게 적용 가능.

# Preview

<img src="./screenshot/preview.png" alt="image" width="300" >

# Usage

- jitpack 사용
- [jitpack 최신 버전 확인](https://jitpack.io/#sarang628/BaseFeed)

```
dependencies {
	implementation 'com.github.sarang628:BaseFeed:{refer to jitpack}'
}	
```

```
@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed() {
    FeedItem(/* Preview */
        uiState = FeedItemUiState.Sample
    )
}
```

# Tech Stack

- [Jetpack Compose](documents/compose/1_JetPackCompose.md)
  - UI element
    - [LazyColumn](documents/AppLayout/LazyColumn.md)
  - Tween animation(Like)
- Android Architecture
    - [UI layer](/documents/architecture/UiLayerLibraries/UILayer.md)
        - UI state
        - [viewmodel (state holder)](/documents/architecture/ViewModel.md)
- [Jetpack Compose Performance](/documents/performance/JetpackComposePerformance.md)
  - Performance
    - Stability

# FeedItem UI 개발
[보기](developement_note/FeedItem.md)

# Review
## ConstraintLayout
- 하나의 ConstraintLayout에 모든 컴포넌트를 배치하는건 무리한 시도
- ConstraintLayout은 코드 가독성은 만족 했지만 수정 어려움
- preview 항목 선택 시 이동되는 코드가 매치되지 않았음
- Row, Column, Box의 조합으로 UI를 배치하는게 관리 쉬움

## development note

- gradle.build.kts 로 변경
- FeedItem 으로 이름 변경 및 constraint layout 적용 (250821)
- FeedItem에 UILayer(UI elements, UI state, Viewmodel) 이론 학습 및 적용 (250825)