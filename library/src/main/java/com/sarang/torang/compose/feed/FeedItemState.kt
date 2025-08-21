package com.sarang.torang.compose.feed

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver

open class FeedItemState {
}

internal class DefaultFeedItemState() : FeedItemState() {

    companion object {
        /**
         * To keep current page and current page offset saved
         */
        val Saver: Saver<DefaultFeedItemState, *> = listSaver(
            save = {
                listOf("")
            },
            restore = {
                DefaultFeedItemState()
            }
        )
    }
}