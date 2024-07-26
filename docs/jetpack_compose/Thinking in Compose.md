# [Thingking in Compose](https://developer.android.com/develop/ui/compose/mental-model)

# The declarative programming paradigm
Historically, an Android view hierarchy has been representable as a tree of UI widgets.
findViewById()
These methods change the internal state of the widget.

Over the last several years, the entire industry has started shifting to a declarative UI model

regenerating the entire screen from scratch

This approach avoids the complexity of manually updating a stateful view hierarchy

# The declarative paradigm shift

You update the UI by calling the same composable function

