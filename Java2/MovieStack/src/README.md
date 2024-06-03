# MovieStack

Your are tired of asking your "friend" to watch the movies you like. So you want to give your friend a _stack_ of movies to watch, representing a watchlist.

Let's automate this process by creating a new data structure class called `MovieStack`, which should act as a stack of `Movie` objects. It should implement the `push()` and `pop()` operations of a stack ADT, and any number of helper methods as necessary.

However, as it's spooky season, you want your friend to watch movies of _Horror_ genre before other movies in the `MovieStack` in a first-in-first-out fashion. This means horror movies should be _queued_ above the _stack_ of other movies within the `MovieStack` class. If there is at least one horror movie in the `MovieStack`, it should be popped before non-horror movies in the stack. Movies of all other genre should follow the default stack behavior when there are no horror movies in the stack.

Check `SpookyTest` for expected test cases, including the return of `null` from an empty `MovieStack` and dynamic insertions to a `MovieStack` (out of order `push()` and `pop()`).

Upload your `MovieStack.java` on zyBooks before the deadline.
