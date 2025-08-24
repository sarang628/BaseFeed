```
[State holders](https://developer.android.com/topic/architecture/ui-layer#state-holders)

UI state를 생성하고 필요한 로직을 담고 있는 클래스를 state holder라 부른다.

전형적인 구현은 ViewModel 이다.
News app 에서는 NewsViewModel을 state holder로 사용

Key Point: 뷰모델은 '화면단 UI state' 와 'data layer 접근' 관리를 위한 권장 구현 방법.
configuration 변경에도 데이터가 생존.

뷰모델은 앱의 이벤트를 적용할 로직을 정의하고 업데이트 된 state를 결과로 생성.

UI와 state 생산자 사이 상호의존을 만드는데는 많은 방법이 있다.
UI와 뷰모델 사이 상호작용은 이벤트 입력과 state 출력을 보장하는데 대부분 사용할 수 있다.
```

#

ViewModel을 구현하기 전 UI elemets와 UI State가 구현되어있으면 좋겠지만, 순서와 상관없이 ViewModel을<br>
구현 할 수도 있다고 생각한다.<br>

UI element와 View model 사이를 UI State 데이터가 인터페이스 하기 때문에 UI State만 이해하고 있다면 변화에 대해<br>
어렵지 않게 적응 할 수 있다. 대신 UI State를 보면 쉽게 이해할 수 있게 설계를 해야한다.

