## [Jetpack Compose](https://developer.android.com/compose)
- Jetpack Compose는 네이티브 UI 빌드를 위한 안드로이드의 권장 모던 toolkit
- 안드로이드 UI 개발을 '단순'하고 '빠르게' 해준다.


## [선언형 프로그래밍 패러다임](https://developer.android.com/develop/ui/compose/mental-model#paradigm)
- 전통적으로 뷰 계층(hierarchy)은 트리 형태로 표현되어왔음.
- 앱의 상태가 바뀌면 해당 뷰의 요소를 찾아 변경하는 방식 사용.
- 화면이 복잡해 질수록 오류 발생이 높아짐.
- 시간이 지나 전반적인 산업에서 선언형 모델로 전환을 하고 있음.
- 전체 화면을 다시 그리되 변경이 필요한 것만을 적용.
- 화면을 다시 그리는 것은 비용이 높음
- 컴포즈는 필요한 부분만 다시 그리는 최적화된 방법인 recomposition을 적용