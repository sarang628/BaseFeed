## [Jetpack Compose](https://developer.android.com/compose)
- Jetpack Compose는 네이티브 UI 빌드를 위한 안드로이드의 권장 모던 toolkit
- 안드로이드 UI 개발을 '단순'하고 '빠르게' 해준다.


## [선언형 프로그래밍 패러다임](https://developer.android.com/develop/ui/compose/mental-model#paradigm)
- 전통적으로 뷰 계층(hierarchy)은 트리 형태로 표현
  - 앱의 상태가 바뀌면 해당 뷰의 요소를 찾아 변경하는 방식 사용
  - 화면이 복잡해 질수록 오류 발생이 높아짐
- 전반적인 산업에서 선언형 모델로 전환
- 전체 화면을 다시 그리되 변경이 필요한 것만 적용.
- 화면을 다시 그리는 것은 비용이 높음
- 컴포즈는 필요한 부분만 다시 그리는 최적화된 방법인 recomposition을 적용

## [Composable 함수 예](https://developer.android.com/develop/ui/compose/mental-model?utm_source=android-studio-app&utm_medium=app#simple-example)

- 어노테이션(Annotation)
  - 컴포즈 컴파일러에 이 함수는 UI가 될 것임을 알림
- 데이터 입력(Data input)
  - 앱의 로직을 UI로 보여주는 파라미터
- UI를 표시(UI display)
  - UI를 표시해주는 컴포즈 라이브러리를 호출
- 리턴 값 없음(No return value)
  - 리턴 값 없이 화면에 UI를 방출
- 속성들(Properties)
  - 빠르고, 멱등성, 사이드 이펙트 free
  - 전역변수나 random() 등을 사용하지 않아 (동시에)여러번 호출해도 결과가 동일 = 멱등성(idempotent)
  - 속성이나 전역변수 변경들을 하지 않고 UI만 그림

## [선언형 프로그래밍 패러다임 전환 (The declarative paradigm shift)](https://developer.android.com/develop/ui/compose/mental-model?utm_source=android-studio-app&utm_medium=app#declarative-paradigm-shift)
