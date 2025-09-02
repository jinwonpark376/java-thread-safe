# Thread-Safety in Java: Bank Account Example

## 📌 Summary
In a multi-threaded environment, simultaneous access to a shared resource can lead to data inconsistency issues.

This example compares different approaches to handling the situation where two threads attempt to withdraw from the same BankAccount simultaneously.

---
## 1. Access Without Synchronization

- **Scenario**  
  Two threads attempt to withdraw money from the same BankAccount object at the same time.
- **Problem**  
  Both threads may pass the **validation logic** (balance check) simultaneously, causing the account balance to go negative.
- **Example(BankAccountV1)**

## 2. Using the `synchronized` keyword

- **Mechanism**  
  Applying `synchronized` to a method or block ensures that **only one thread** can execute that code section at a time.
- **Pros**  
  - Easy to implement.
  - Minimal code changes.
- **Cons**  
  - Threads may be forced into indefinite waiting. 
  - Fairness issues (threads may not acquire the lock in order).
- **Example(BankAccountV2)**

## 3. Using `Locks`

- **Mechanism**  
  Introduced in Java 1.5 to improve from the drawbacks of `synchronized`.
- **Pros**  
  - `trylock` prevents indefinite waiting by allowing timeout-based lock attempts.
  - `ReentrantLock` can be configured with **fair mode**, ensuring the longest-waiting thread acquires the lock first.
  
- **Cons**
    - If `lock.unlock()` is not properly placed, deadlock can occur.
    - Fairness can be achieved, but it comes with reduced performance.
- **Example(BankAccountV3)**

## 4. Atomic Operation. CAS(Compare-And-Set)

- **Mechanism**  
  Uses hardware-level **atomic operations** to ensure thread safety **without locks**. 
  
  Instead of blocking threads, concurrent access is allowed, but updates rely on hardware CAS instructions to avoid race conditions.
- **Pros**
    - **No Locks** → no context switching, resulting in high performance.
    - Efficient for frequent read/update operations on a single variable.
- **Cons**
    - Not suitable for updating multiple shared resources simultaneously (e.g., transferring between two accounts requires both withdrawal and deposit).
    - In high contention scenarios, repeated CAS retries may increase CPU usage.
- **Example(BankAccountV4)**
- Notes  
  In this example, **BankAccountV4** implements the “balance check → deduction” process using a CAS loop, ensuring atomicity without locks.
  This is highly efficient in low-contention environments where only single-value updates are required.

## 5. Alternative Synchronization Approaches
The above examples focus on single-server scenarios. In modern architectures, applications are often scaled horizontally, making concurrency issues more complex. Ensuring consistency across multiple nodes requires additional strategies, such as:

- **Distributed Locks** (e.g., Redis, ZooKeeper)
- **Database-Based Locks** (e.g., row-level locking, advisory locks)
- **Optimistic Locking** (e.g., version fields, unique constraints)
  
Each method comes with trade-offs in complexity, performance, and fault tolerance, but they provide essential mechanisms for handling concurrency in distributed systems.
