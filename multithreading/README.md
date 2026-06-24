# Java Multithreading Quick Notes

This folder has two starter examples:

- `DemoUsingThreadClass.java`
- `DemoUsingRunnableInterface.java`

## 1) `Thread` class vs `Runnable`

### Extend `Thread`
- Create class: `class MyThread extends Thread`
- Override `run()`
- Start with `t.start()`

Use when:
- You only need a quick thread demo.

Limitation:
- Java allows single inheritance, so you cannot extend another class.

### Implement `Runnable`
- Create class: `class MyTask implements Runnable`
- Put task logic in `run()`
- Pass object to thread: `new Thread(task).start()`

Use when:
- You want better design and reusability.
- You may still need to extend another class.
- You want to submit the same task to thread pools later.

Recommendation:
- Prefer `Runnable` in most real projects.

## 2) Important methods

- `start()`: creates a new thread and then calls `run()` on that new thread.
- `run()`: task body. Calling `run()` directly does **not** create a new thread.
- `join()`: wait for another thread to finish.
- `sleep(ms)`: pause current thread for given milliseconds.
- `setName(name)`: set thread name for debug/logging.
- `getState()`: check current thread state.

## 3) How to run these examples

Open terminal in this folder:

```powershell
cd "C:\Users\BIT\OneDrive\Desktop\notes\OOPS\LLD\Java\multithreading"
```

Compile:

```powershell
javac DemoUsingThreadClass.java DemoUsingRunnableInterface.java
```

Run:

```powershell
java DemoUsingThreadClass
java DemoUsingRunnableInterface
```

## 4) Next files to study (`../synchronisation`)

If you want "thread class etc + advanced":

- `ExecutorDemo.java`: fixed thread pool (`ExecutorService`)
- `CallableFutureDemo.java`: return value from background task using `Future`
- `DemoTypesSyn.java`: synchronized methods and race condition protection
- `Producer_Consumer.java`: classic wait/notify example

Run the same way: `javac FileName.java` then `java FileName`.
