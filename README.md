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
  + Wait for worker threads                     2'
  + No intervention in CPU scheduling           4'
  + Satisfy the worst[3] degree of concurrency  4'
  + The link structure is not corrupted         5'
  + The size is correct                         5'
  + Fine-grained synchronization                9'
+ Requirement C (15')
  + Memoize the owner of each node                  5'
  + Inside `remove()`, validate the node's owner    5'
  + Inside `insert()`, validate the node's owner    5'

NOTE:

[1] Such as using `Thread.sleep()`, or any form of I/O **INSIDE EACH LOOP** to intervene the normal execution of the code.

[2] `Thread.start()`, or `submit()` to a `ThreadPool`.

[3] The worst concurrency we can accept is locking the whole table at each call of insert/remove

Punishment for such cases:
+ Messy indent                         -4'
+ Not recommended naming convention    -2'