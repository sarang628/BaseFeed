package com.sarang.base_feed

import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.*
import com.example.torang_core.data.data.ReviewAndImage
import com.example.torang_core.data.model.Favorite
import com.example.torang_core.data.model.Feed
import com.example.torang_core.data.model.Like
import com.example.torang_core.data.model.ReviewImage
import com.example.torang_core.dialog.FeedDialogEventAdapter
import com.example.torang_core.dialog.FeedMyDialogEventAdapter
import com.example.torang_core.dialog.NotLoggedInFeedDialogEventAdapter
import com.example.torang_core.repository.FeedListRepository
import com.example.torang_core.util.Event
import com.example.torang_core.util.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseFeedViewModel(private val feedRepository: FeedListRepository) : ViewModel(),
    FeedMyDialogEventAdapter, FeedDialogEventAdapter, NotLoggedInFeedDialogEventAdapter {

    /** 피드를 갱신하는 플레그 */
    private val _forceUpdate = MutableLiveData(false)

    /** 데이터 로딩중 여부 */
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    /** 맛집 상세화면 이동 */
    private val _openTorangDetail = MutableLiveData<Event<Int>>()
    val openTorangDetail: LiveData<Event<Int>> = _openTorangDetail

    /** 피드 상세화면 이동 */
    private val _openFeedDetail = MutableLiveData<Event<Int>>()
    val openFeedDetail: LiveData<Event<Int>> = _openFeedDetail

    /** 프로필 이동 */
    private val _openProfile = MutableLiveData<Event<Int>>()
    val openProfile: LiveData<Event<Int>> = _openProfile

    /** 피드 삭제 다이얼로그 보이기 */
    private val _showDeleteFeed = MutableLiveData<Event<Int>>()
    val showDeleteFeed: LiveData<Event<Int>> = _showDeleteFeed

    /** 피드 수정 이동 */
    private val _modifyFeed = MutableLiveData<Event<Int>>()
    val modifyFeed: LiveData<Event<Int>> = _modifyFeed

    /** 피드 신고 이동 */
    private val _reportFeed = MutableLiveData<Event<Int>>()
    val reporFeed: LiveData<Event<Int>> = _reportFeed

    /** 피드 공유 이동 */
    private val _shareFeed = MutableLiveData<Event<Int>>()
    val shareFeed: LiveData<Event<Int>> = _shareFeed

    /** 사진 선택 이동 */
    private val _openPicture = MutableLiveData<Event<ReviewImage>>()
    val openPicture: LiveData<Event<ReviewImage>> = _openPicture

    /** 메뉴 클릭 이벤트 start */
    private val _menu = MutableLiveData<Event<Feed>>()
    val menu: LiveData<Event<Feed>> = _menu

    val _myMenu = MutableLiveData<Event<Feed>>()
    val myMenu: LiveData<Event<Feed>> = _myMenu

    private val _notLoggedInMenu = MutableLiveData<Event<Feed>>()
    val notLoggedInMenu: LiveData<Event<Feed>> = _notLoggedInMenu
    /** 메뉴 클릭 이벤트 end */

    /** 이 게시물에 포함되는 이유 */
    private val _reasonContainThisPost = MutableLiveData<Event<Int>>()
    val reasonContainThisPost: LiveData<Event<Int>> = _reasonContainThisPost

    /** 팔로우 취소 */
    private val _unfollow = MutableLiveData<Event<Feed>>()
    val unfollow: LiveData<Event<Feed>> = _unfollow

    /** 숨기 */
    private val _hide = MutableLiveData<Event<Feed>>()
    val hide: LiveData<Event<Feed>> = _hide

    /** 신고 */
    private val _report = MutableLiveData<Event<Int>>()
    val report: LiveData<Event<Int>> = _report

    /** 다른 앱에 개시.. */
    private val _postOtherApp = MutableLiveData<Event<Int>>()
    val postOtherApp: LiveData<Event<Int>> = _postOtherApp

    /** 댓글 기능 해제 */
    private val _lockReply = MutableLiveData<Event<Int>>()
    val lockReply: LiveData<Event<Int>> = _lockReply

    /** 보관 */
    private val _store = MutableLiveData<Event<Int>>()
    val store: LiveData<Event<Int>> = _store

    /** 삭제 */
    private val _delete = MutableLiveData<Event<Int>>()
    val delete: LiveData<Event<Int>> = _delete

    /** 수정 */
    private val _modify = MutableLiveData<Event<Feed>>()
    val modify: LiveData<Event<Feed>> = _modify

    /** 좋아요 수 숨기기 */
    private val _hideLikeCount = MutableLiveData<Event<Int>>()
    val hideLikeCount: LiveData<Event<Int>> = _hideLikeCount

    protected val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val testImage = MutableLiveData<List<ReviewImage>>()

    fun openTorangDetail(reviewId: Int) {
        Logger.d(reviewId)
        _openTorangDetail.value = Event(reviewId)
    }

    fun openFeedDetail(reviewId: Int) {
        Logger.d(reviewId)
        _openFeedDetail.value = Event(reviewId)
    }

    fun showDeleteFeed(reviewId: Int) {
        Logger.d(reviewId)
        _showDeleteFeed.value = Event(reviewId)
    }

    fun showMenu(feed: Feed) {
        Logger.d("$feed")
        viewModelScope.launch {
            if (feedRepository.user1() == null) {
                Logger.d("_notLoggedInMenu")
                _notLoggedInMenu.value = Event(feed)
            } else {
                feedRepository.user1()?.let {
                    if (it.userId == feed.userId) {
                        Logger.d("_myMenu")
                        _myMenu.value = Event(feed)
                    } else {
                        Logger.d("_menu")
                        _menu.value = Event(feed)
                    }
                }
            }
        }
    }

    fun showMenu(reviewAndImage: ReviewAndImage) {
        Logger.d("$reviewAndImage")
        val feed = Feed.parse(reviewAndImage)
        viewModelScope.launch {
            if (feedRepository.user1() == null) {
                _notLoggedInMenu.value = Event(feed)
            } else {
                feedRepository.user1()?.let {
                    if (it.userId == feed.userId) {
                        _myMenu.value = Event(feed)
                    } else {
                        _menu.value = Event(feed)
                    }
                }
            }
        }
    }

    fun reportFeed(reviewId: Int) {
        Logger.d(reviewId)
        _reportFeed.value = Event(reviewId)
    }

    fun shareFeed(reviewId: Int) {
        Logger.d(reviewId)
        _shareFeed.value = Event(reviewId)
    }

    fun openProfile(userId: Int) {
        Logger.d(userId)
        _openProfile.value = Event(userId)
    }

    fun openPicture(pictureId: ReviewImage) {
        Logger.d(pictureId)
        _openPicture.value = Event(pictureId)
    }

    /** 피드 라이브데이터 */
    val feeds: LiveData<List<Feed>> = _forceUpdate.switchMap {
        _dataLoading.value = true
        viewModelScope.launch {
            try {
                feedRepository.loadFeed()
                _dataLoading.value = false
            } catch (e: Exception) {
                _errorMessage.postValue("오류가 발생했습니다:\n$e")
                _dataLoading.value = false
            }
        }
        feedRepository.getFeed().distinctUntilChanged().switchMap {
            filterTasks(it)
        }
    }

    fun filterTasks(result: List<Feed>): LiveData<List<Feed>> {
        val data = MutableLiveData<List<Feed>>()
        data.postValue(result)
        return data
    }

    fun getReviewImages(reviewId: Int): LiveData<List<ReviewImage>> {
        return feedRepository.getReviewImages(reviewId).distinctUntilChanged()
    }

    // 좋아요
    fun clickLike(v: View, reviewId: Int) {
        v.isSelected = !v.isSelected
        v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.anim_like))
        viewModelScope.launch {
            try {
                feedRepository.like(reviewId)
            } catch (e: Exception) {
                Logger.e(e.toString())
            }
        }
    }

    // 즐겨찾기
    fun clickFavorite(v: View, reviewId: Int) {
        v.isSelected = !v.isSelected
        v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.anim_like))
        viewModelScope.launch {
            try {
                feedRepository.favorite(reviewId)
            } catch (e: Exception) {
                Logger.e(e.toString())
            }
        }
    }

    // 좋아요 바인딩 함수
    fun isLike(reviewId: Int): LiveData<Like> {
        return feedRepository.getLike(reviewId)
    }

    /**
     * 피드 요청
     */
    fun refresh() {
        _forceUpdate.value = true
    }

    fun isFavorite(reviewId: Int): LiveData<Favorite> {
        return feedRepository.getFavorite(reviewId)
    }

    override fun report(reviewId: Int) {
        _report.value = Event(reviewId)
    }

    override fun reasonContainThisPost(reviewId: Int) {
        _reasonContainThisPost.value = Event(reviewId)
    }

    override fun hide(feed: Feed) {
        _hide.value = Event(feed)
    }

    override fun unfollow(feed: Feed) {
        _unfollow.value = Event(feed)
    }

    override fun report1(reviewId: Int) {
        _report.value = Event(reviewId)
    }

    override fun report2(reviewId: Int) {
        _report.value = Event(reviewId)
    }

    override fun postOtherApp(reviewId: Int) {
        _postOtherApp.value = Event(reviewId)
    }

    override fun lockReply(reviewId: Int) {
        _lockReply.value = Event(reviewId)
    }

    override fun store(reviewId: Int) {
        _store.value = Event(reviewId)
    }

    override fun delete(reviewId: Int) {
        _delete.value = Event(reviewId)
    }

    override fun modify(feed: Feed) {
        _modify.value = Event(feed)
    }

    override fun hideLikeCount(reviewId: Int) {
        _hideLikeCount.value = Event(reviewId)
    }
}