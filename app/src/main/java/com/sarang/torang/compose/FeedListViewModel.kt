package com.sarang.torang.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.di.basefeed_di.toReview
import com.sarang.torang.repository.feed.FeedLoadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * 뷰모델은 '화면단 UI state' 와 'data layer 접근' 관리를 위한 권장 구현 방법.
 * configuration 변경에도 데이터가 생존.
 * 뷰모델은 앱의 이벤트를 적용할 로직을 정의하고 업데이트 된 state를 결과로 생성.
 *
 * BaseFeed 모듈의 목적은 공통 피드 Item에 대한 제공.
 * ViewModel을 다양하게 활용할 기능이 없음
 *
 * data layer로 부터 feed 리스트를 받고 UIState로 변환하는 기능 적용.
 */
@HiltViewModel
class FeedListViewModel @Inject constructor(feedLoadRepository: FeedLoadRepository) : ViewModel() {
    val feedUiState: StateFlow<List<FeedItemUiState>> = feedLoadRepository.feeds.map {
        it?.map { feed -> feed.toReview() } ?: listOf()
    }.stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5_000), listOf() )
}
