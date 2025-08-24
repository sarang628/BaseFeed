# BaseFeed Module

Feed 정보를 보여주는 UI<br>
Feed 항목은 여러 화면에서 필요로 함.<br>
모듈화 하여 일관성을 유지하며 쉽게 적용할 수 있도록 개발.

# Preview

<img src="./screenshot/demonstrate.gif" alt="image" width="300" >

## How to use

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

## Feature

- Jetpack Compose
- Android Architecture
    - UI layer
        - UI element
        - UI state
        - viewmodel (state holder)

## Architecture

### UI layer

UI layer의 주요 기능은 다음과 같다.<br>
1. 앱 데이터를 소비. UI가 쉽게 그릴 수 있는 데이터로 변환
2. UI를 그릴 수 있는 데이터 소비. 사용자에게 보여줄 UI element에 대입.
3. 사용자의 이벤트를 받아 데이터에 반영

#### UI element

피드 항목 ui element. jetpack compose를 사용해 제작<br>
[UIElement](/documents/UIElement.md)<br>
[FeedItem.kt](/library/src/main/java/com/sarang/torang/compose/feed/FeedItem.kt)

#### UI state

UI element에 필요한 데이터.<br>
UI는 사용자에게 보여주는 시각적 표시라면 UI state는 이 정보를 담고 있다.<br>
[UIState](/documents/UIState.md)<br>
[FeedItemUiState.kt](/library/src/main/java/com/sarang/torang/data/basefeed/FeedItemUiState.kt)

#### viewmodel

UI state를 생성하고 필요한 로직을 담고 있는 클래스를 state holder라 부른다. 전형적인 구현은 viewmodel이다.<br>
[StateHolder](/documents/StateHolder.md)<br>
[FeedListViewModel](app/src/main/java/com/sarang/torang/compose/FeedListViewModel.kt)


## development note

- gradle.build.kts 로 변경
- FeedItem 으로 이름 변경 및 constraint layout 적용 (250821)