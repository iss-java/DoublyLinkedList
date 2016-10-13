# Debug Hacks

Total points: 40

+ Watchpoint                  7'
+ Modifying variable          7'
+ Conditional breakpoint      8'
+ Switching threads           8'
+ CyclicBarrier               10'

# Doubly Linked List with Concurrency

+ Requirement A (15')
  + Loop at least two times                  5'
  + Lock each get-compute-set workflow       3'
  + No intervention[1] in CPU scheduling     4'
  + Correctly[2] starting a thread           1'
  + Wait for worker threads                  2'
+ Requirement B (30')
  + Wait for worker threads by using barriers          5'
  + No intervention in CPU scheduling                  5'
  + Satisfy the worst[3] degree of concurrency         5'
  + The link structure is not corrupted [5]            5'
  + The size is equal to the actual size [5]           5'
  + No deadlock [4][5]                                 5'
+ Requirement C (15')
  + Memoize the owner of each node                        3'
  + Set the owner of head and tail                        4'
  + Inside `remove()`, validate the node's owner          4'
  + Inside `insert()`, validate the node's owner          4'

NOTE:

* [1] Such as using `Thread.sleep()`, or any form of I/O **INSIDE EACH LOOP** to intervene the normal execution of the code.
* [2] `Thread.start()`, or `submit()` to a `ThreadPool`.
* [3] The worst concurrency we can accept is locking the whole table at each call of insert/remove
* [4] If a deadlock is discovered and you are using Thread.sleep() to wait for worker threads, only 5' are punished.
* [5] If you are attempting to implement a fine-grained(细粒度) synchronization but failed to resolve the deadlock problem, then only 3' are punished.

Punishment for such cases:
+ Messy indent                         -4'
+ Not recommended naming convention    -2'