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