피드 항목은 요소들이 많아 어떻게 하면 가독성, 관리하기 좋은 코드를 만들 수 있는지 고민해봤다.

## Row, Column을 사용. 상단, 중앙, 하단으로 나눠 구현

피드 항목을 3개로 나눠서 한번에 모든것을 구현하는 부담을 줄여 보았다.

compose 함수 4개 미리보기 함수 4개 총 8개의 함수가 만들어져 한 파일에 함수와 라인 수가

너무 많아지는 느낌이여서 다른 방법을 사용해보기로 함.

## ConstraintLayout 사용

XML ConstraintLayout은 드래그 엔 드롭으로 편리하게 UI를 구현할 수 있었는데
Compose ConstraintLayout은 그렇지 않다.

Row와 Column을 중첩으로 하더라도 XML의 LinearLayout이나 RelativeLayout을 중첩하면 발생하는
비효율을 개선해 궂이 ConstraintLayout을 사용할 필요가 없다.

하지만 나에겐 ConstraintLayout을 사용하는게 가독성 측면에서 좋아보였다.

하나의 함수에 모든 요소를 중첩없이 배치 할 수 있어

모든 요소를 같은 레벨에서 볼 수 있다.

ConstraintSet()을 배치 기능만 명확하게 분리할 수도 있다.

<img src = "../screenshot/feeditem_readability.png" width="1000" />

나에게만 읽기 좋아보이는 코드일 수 있다는 함정이 있기때문에 다른 사람들의 의견이 필요하다.