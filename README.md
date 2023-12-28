# BaseFeed Module
메인 화면, 프로필 화면 등 다양한 화면에서 피드를 사용하여 공통 컴포넌트 모듈로 제공

## Requirement
### functional
- 포스팅 및 콘텐츠 공유
    - 사용자는 텍스트, 사진, 동영상 등 다양한 형식의 콘텐츠를 업로드할 수 있어야 합니다. 
    - 다양한 크기와 종류의 미디어를 지원하며, 적절한 해상도로 보여져야 합니다.

- 피드 정렬 및 필터링 
  - 최신 포스팅이 상단에 표시되도록 피드를 정렬해야 합니다. 
  - 사용자는 친구, 팔로잉한 계정 등의 옵션을 통해 피드를 필터링할 수 있어야 합니다.

- 좋아요 및 댓글:
  - 사용자는 포스팅에 좋아요를 누를 수 있어야 하며, 댓글을 작성할 수 있어야 합니다. 
  - 좋아요 및 댓글은 실시간으로 반영되어야 하며, 알림 기능을 통해 사용자에게 알려져야 합니다.

- 스토리 및 하이라이트:
  - 사용자는 24시간 동안 유지되는 스토리를 게시할 수 있어야 하며, 피드에 스토리가 표시되어야 합니다. 
  - 하이라이트 기능을 통해 특정 스토리를 계속해서 피드에 유지할 수 있어야 합니다.

- 게시물 관리:
  - 사용자는 게시물을 수정하거나 삭제할 수 있어야 하며, 프라이버시 설정을 통해 공개 범위를 제어할 수 있어야 합니다.

- 태그 및 위치 정보
  - 사용자는 포스팅에 다른 사용자를 태그하거나 위치 정보를 추가할 수 있어야 합니다.

- 알림 및 활동 피드 
  - 사용자는 자신의 활동에 대한 알림을 받을 수 있어야 하며, 팔로우한 계정의 활동을 확인할 수 있는 활동 피드가 제공되어야 합니다.

- 광고 표시 
  - 피드에는 광고가 표시될 수 있으며, 광고가 사용자 경험을 방해하지 않도록 조절되어야 합니다.

- 접근성 및 다국어 지원
  - 피드는 모바일 및 데스크톱 플랫폼에서 모두 접근 가능해야 합니다. 
  - 다국어 지원을 통해 다양한 언어로 포스팅이 가능해야 합니다.

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
data class Review(
    val user: User,
    val restaurant: Restaurant,
    val reviewId: Int,
    val rating: Float,
    val reviewImages: List<String> = ArrayList(),
    val contents: String,
    val comments: List<Comment>?,
    val likeAmount: Int,
    val commentAmount: Int,
    val isLike: Boolean,
    val isFavorite: Boolean,
)
```


## Unit test
- 최초 로딩 중일 때 리스트가 나오면 안된다.
- 최초 로딩이 되었고 리스트가 비어있다면 리스트가 비어있음을 표시한다.
- 최초 로딩이 되었고 리스트가 있다면 리스트를 표시한다.

## 화면
<img src="./screenshot/demonstrate.gif" alt="image" width="300" height="auto">