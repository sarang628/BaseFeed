# BaseFeed Module

Feed 정보를 보여주는 UI<br>
Feed 항목은 여러 화면에서 필요로 함.<br>
모듈화 하여 다른 화면에 적용 시, 일관성을 유지하며 쉽게 적용할 수 있도록 개발.

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

UI는 UI element + UI state로 구성<br>
기존 UI 개발에는 UI element와 데이터가 하나의 클래스 안에서 함께 개발 했다면,<br>
새로운 아키텍처는 이를 분리할 수 있도록 설계 되었다.

화면에서 필요로 하는 '데이터를 세트'로 만들어 화면에 계속 호출하는 방식인데,<br>
기존 방식에 이 '데이터 세트'를 적용하면 데이터가 바뀔때마다 모든 elements에 적용해야되서<br>
render이 매우 느려질 수 있는데, 새로운 아키텍처에서는 데이터가 여러번 호출되도 성능상에<br>
문제가 되지 않도록 만들었다.

#### UI element

화면에 표시되는 실제 UI를 만드는 작업. jetpack compose를 사용해 제작했다.<br>
[UIElement 개발 과정](/documents/UIElement.md)<br>
[FeedItem.kt](/library/src/main/java/com/sarang/torang/compose/feed/FeedItem.kt)

#### UI state

UI element(화면에 표시되는 실제 UI)에 필요한 데이터.<br>
UI는 사용자에게 보여주는 시각적 표시라면 UI state는 이 '정보'를 담고 있다.<br>
[UIState 개발 과정](/documents/UIState.md)<br>
[FeedItemUiState.kt](/library/src/main/java/com/sarang/torang/data/basefeed/FeedItemUiState.kt)

#### viewmodel

UI state를 생성하고 필요한 로직을 담고 있는 클래스를 state holder라 부른다. 전형적인 구현은 viewmodel이다.<br>
[StateHolder](/documents/StateHolder.md)<br>
[FeedListViewModel](app/src/main/java/com/sarang/torang/compose/FeedListViewModel.kt)


#### [UDF의 이해](https://developer.android.com/topic/architecture#unidirectional-data-flow)

새로운 아키텍처에는 UDF에 대한 이해가 필요하다.

두 가지 특징이 있다.
첫 번째는 데이터가 흐른다(Flow) 물을 틀면 새로운 물이 계속 쏟아지듯이 새로운 데이터가 계속 쏟아진다.<br>
이 데이터를 잡에서 바꿔도 소용이 없다. 오로지 데이터를 읽기만 해야한다.<br>
코틀린 Flow를 사용하면 데이터를 바꾸려고 시도해본들 val 타입으로 새로운 데이터가 쏟아지므로 변경 자체가 불가능하다.<br>

두번째는 데이터 변경이 불가능하면 화면이 고정되서 아무것도 할 수 없다.<br> 
UI에서 중재자(ViewModel)에게 이벤트 전달하면 로직을 수행 후<br>
새로운 데이터가 쏟아질 수 있도록 해준다.<br> 
데이터는 오직 한 곳에서 관리하여 Single Source of Truth 이론을 적용 하도록 한다.

UDF는 '이론'이며<br>
이를 구현하기위해 코틀린의 Flow나 안드로이드의 LiveData를 사용<br>
중자재로 ViewModel을 사용해야 UDF이론을 앱에 적용 할 수 있다.



## development note

- gradle.build.kts 로 변경
- FeedItem 으로 이름 변경 및 constraint layout 적용 (250821)