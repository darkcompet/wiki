# Multiple threading (concurrent)


## wait(), notify(), notifyAll()

Suppose we have some running threads and a `lock` to sync them.

- `Object.wait()`: release monitor of the `lock`, and pause current thread (move to waiting state).
- `Object.notify()`: tell a (for eg,. randomly) waiting-thread try to acquire monitor again.
- `Object.notifyAll()`: tell other waiting-threads try to acquire monitor again.

-> They provide a producer-consumer model.


## ReentrantLock vs ReadWriteLock

They are same same but have own several below characteristics:

- `ReentrantLock`:
	- Only one thread can hold the object-monitor at a time.
	So only holding-thread can access resource at the time -> it is maybe slow for some case.

- `ReadWriteLock`:
	- Multiple reader threads can access resource at a time.
	- Once writer-thread hold the lock, then other threads (reader and writer) will be blocked.
