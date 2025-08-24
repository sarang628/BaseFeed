# BaseFeed Module

다양한 화면에서 사용할 수 있는 Feed 모듈

# Preview
<img src="./screenshot/demonstrate.gif" alt="image" width="300" >

## How to use
<img src="./screenshot/preview.png" alt="image" width="300" >

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

## Feature

- Jetpack Compose
- Android Architecture
  - UI layer
    - UI element
    - UI state
    - viewmodel (state holder)

## Architecture

### UI layer

#### UI element
피드 항목 ui element. jetpack compose를 사용해 제작<br>
[UIElement](/documents/UIElement.md)<br>
[FeedItem.kt](/library/src/main/java/com/sarang/torang/compose/feed/FeedItem.kt)

#### UI state
ui element에 필요한 데이터. 
[UIState](/documents/UIState.md)<br>
[FeedItemUiState.kt](/library/src/main/java/com/sarang/torang/data/basefeed/FeedItemUiState.kt)

#### viewmodel 
UI state를 생성하고 필요한 로직을 담고 있는 클래스를 state holder라 부른다. 전형적인 구현은 viewmodel이다.<br>
[StateHolder](/documents/StateHolder.md)<br>
[FeedListViewModel](app/src/main/java/com/sarang/torang/compose/FeedListViewModel.kt)

## development note
- gradle.build.kts 로 변경
- FeedItem 으로 이름 변경 및 constraint layout 적용 (250821)