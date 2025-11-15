# BaseFeed Module

- 음식점 리뷰 정보를 보여주는 UI.
- Feed 항목은 여러 화면에서 필요.
- 모듈화로 타 화면 적용 시, 일관성 유지와 쉽게 적용 가능.

# Preview

<img src="./screenshot/demonstrate.gif" alt="image" width="300" >

# How to use

<img src="./screenshot/preview.png" alt="image" width="300" >

```
dependencies {
	implementation 'com.github.sarang628:BaseFeed:{refer to jitpack}'
}	
```
[jitpack 최신 버전 확인](https://jitpack.io/#sarang628/BaseFeed)

```
@Preview(showBackground = true, backgroundColor = 0xFFFDFDF6)
@Composable
fun PreviewFeed() {
    FeedItem(/* Preview */
        uiState = FeedItemUiState.Sample
    )
}
```

# Feature

- [Jetpack Compose](documents/JetPackCompose.md)
  - [UI element](documents/UIElement.md)
  - Tween animation(Like)
- Android Architecture
    - [UI layer](/documents/UILayer.md)
        - UI state
        - [viewmodel (state holder)](/documents/ViewModel.md)


# Review
- ConstraintLayout은 코드 가독성은 좋게 만들 수 있으나, 수정하기 쉽지 않음. 
- 하나의 ConstraintLayout에 모든 컴포넌트를 배치하는건 무리한 시도였던 것 같음.

## development note

- gradle.build.kts 로 변경
- FeedItem 으로 이름 변경 및 constraint layout 적용 (250821)
- FeedItem에 UILayer(UI elements, UI state, Viewmodel) 이론 학습 및 적용 (250825)