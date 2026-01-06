# BaseFeed Module

- 여러 화면에서 필요로 하는 음식점 리뷰 '리스트 항목' UI.
- 모듈화로 일관성 있게 적용 가능.

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

# Development Technology

- [Jetpack Compose](documents/JetPackCompose.md)
  - UI element
  - Tween animation(Like)
- Android Architecture
    - [UI layer](/documents/UILayer.md)
        - UI state
        - [viewmodel (state holder)](/documents/ViewModel.md)

# FeedItem UI 개발
[보기](documents/FeedItem.md)

# Review
- ConstraintLayout은 코드 가독성(x, 배치)은 좋게 만들 수 있으나, 수정하기 쉽지 않음.
- composable를 선택하면 preview에 매치되는 항목이 맞지 않아 햇갈렸음.
- 하나의 ConstraintLayout에 모든 컴포넌트를 배치하는건 무리한 시도였음.
- Row, Column, Box의 조합으로 UI를 배치하는게 더 쉬웠음.

## development note

- gradle.build.kts 로 변경
- FeedItem 으로 이름 변경 및 constraint layout 적용 (250821)
- FeedItem에 UILayer(UI elements, UI state, Viewmodel) 이론 학습 및 적용 (250825)