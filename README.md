# BaseFeed Module
메인 화면, 프로필 화면 등 다양한 화면에서 사용할 수 있는 공통 컴포넌트 모듈로 제공

## Requirement
### functional
- 공통 콘텐츠 항목
    - 사용자가 업로드 한 콘텐츠를 항목으로 보여 준다. 
    - 다른 모듈에서 사용 할 수 있도록 모듈화 한다.

- 좋아요 및 즐겨찾기:
  - 사용자는 포스팅에 좋아요/즐겨찾기를 누를 수 있어야 한다. 
  - 좋아요 및 댓글은 실시간으로 반영되어야 하며, 알림 기능을 통해 사용자에게 알려져야 한다.

- 게시물 관리:
  - 사용자는 자신의 게시물을 삭제할 수 있어야 한다.
  - 게시물의 신고/수정은 각 모듈로 전달 한다.

- 광고 표시 
  - 피드에는 광고가 표시될 수 있으며, 광고가 사용자 경험을 방해하지 않도록 조절되어야 합니다.

- 다국어 지원 
  - 다국어 지원을 통해 다양한 언어로 포스팅이 가능해야 합니다.

## Architecture, Libraries
- App Architecture
  - Layered architecture
  - Dependency injection with Hilt
- Mordern Android
  - Compose
    - ConstraintLayout

## convention
### package
- compose로 구현한 UI는 compose 패키지에 저장
- uistate는 uistate 패키지에 저장
<img src="./screenshot/package.png" alt="image" width="300" height="auto">

## Architecture
### UI element

### UI layer
UiState
```
sealed interface FeedsUiState {
    object Loading : FeedsUiState
    object Empty : FeedsUiState
    data class Success(val reviews: List<Review>) : FeedsUiState
    data class Error(val message: String) : FeedsUiState
}
```


## Unit test
- 최초 로딩 중일 때 리스트가 나오면 안된다.
- 최초 로딩이 되었고 리스트가 비어있다면 리스트가 비어있음을 표시한다.
- 최초 로딩이 되었고 리스트가 있다면 리스트를 표시한다.

## 화면
<img src="./screenshot/demonstrate.gif" alt="image" width="300" height="auto">