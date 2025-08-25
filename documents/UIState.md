https://developer.android.com/topic/architecture/ui-layer#define-ui-state

<img scr = ../preview.png width="500">

UI는 사용자에게 보여주는 시각적 표시라면 UI state는 이 정보를 담고 있다.
UI는 사용자가 보는 것 이라면, UI state는 앱이 사용자이 봐야하는 것을 말하는 것이다.
UI는 UIstate의 시각젹 표시이며, UIstate가 변경 시 UI에 즉시 반영 되어야 한다.

```
UIstate 만 봐도 어떤 정보들이 화면에 표시되는지 할 수 있어야할 것 같다.
```

### UI를 기준으로 요구 사항 만들어 보기
- 피드에서 '사용자 정보'를 포함한다.(이름, 프로필 사진 등)
- 피드에 '이미지를 업로드' 할 수 있다.(여러장 페이지 형식)
- 피드에 '좋아요'를 누를 수 있다. 내가 좋아요 한 피드는 항상 피드에 표시한다. (좋아요 갯수도 표시)
- 피드에 '코멘트'를 달 수 있다.(코멘트 갯수 표시)
- 피드를 다른 앱에 '공유' 할 수 있다.
- 피드를 '즐겨찾기' 할 수 있다.
- 피드에 사용자가 '리뷰한 내용', '평점', '업로드한 날짜' 등을 표시한다.

요구사항 기준으로 샘플 화면과 같은 '디자인'과 '기획서'가 나왔다고 가정한다.

UIState 정의하기.

화면에 보이는 정보를 그대로 데이터로 정의하면 되기 때문에 별로 어려운 작업은 아닌 것 같다.
변수명에 대한 정의를 잘해야 할 것 같다.
UIState 생성을 용이하게 하기위해 defalut value를 설정.
UIState의 빈값, 샘플값, 변환등과 같은 확장 기능에 대해서는 FeedItemUiStateExtension.kt에 따로 정의하였다.

[FeedItemUiState.kt](/library/src/main/java/com/sarang/torang/data/basefeed/FeedItemUiState.kt)
[FeedItemUiStateExtension.kt](/library/src/main/java/com/sarang/torang/data/basefeed/FeedItemUiStateExtension.kt)