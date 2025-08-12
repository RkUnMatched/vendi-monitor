# 两个线程交替打印程序

这个项目展示了如何实现两个线程交替打印数字的经典并发编程问题。

## 文件说明

### 1. AlternatingThreads.java
- **实现方式**: 使用 `synchronized` 关键字和 `wait()/notify()` 机制
- **特点**: 传统的Java线程同步方式，代码简洁易懂
- **核心机制**: 
  - 使用共享的 `Object lock` 作为同步锁
  - 通过 `boolean isEvenTurn` 标志控制线程执行顺序
  - 使用 `wait()` 让线程等待，`notify()` 唤醒等待的线程

### 2. AlternatingThreadsWithLock.java
- **实现方式**: 使用 `ReentrantLock` 和 `Condition` 接口
- **特点**: 更灵活的锁机制，可以创建多个等待条件
- **核心机制**:
  - 使用 `ReentrantLock` 替代 `synchronized`
  - 创建两个 `Condition` 对象分别控制奇数和偶数线程
  - 使用 `await()` 和 `signal()` 进行线程通信

### 3. alternating_threads.py
- **实现方式**: 使用Python的 `threading` 模块和 `Event` 对象
- **特点**: Python风格的实现，使用事件机制控制线程执行
- **核心机制**:
  - 使用 `threading.Event` 对象控制线程执行顺序
  - 通过 `wait()` 和 `set()/clear()` 方法控制线程状态
  - 使用 `threading.Lock` 保护共享资源

## 运行方法

### Java版本
```bash
# 编译
javac AlternatingThreads.java
javac AlternatingThreadsWithLock.java

# 运行
java AlternatingThreads
java AlternatingThreadsWithLock
```

### Python版本
```bash
python3 alternating_threads.py
```

## 输出示例
```
开始交替打印...
奇数线程: 1
偶数线程: 2
奇数线程: 3
偶数线程: 4
奇数线程: 5
偶数线程: 6
奇数线程: 7
偶数线程: 8
奇数线程: 9
偶数线程: 10
交替打印完成！
```

## 核心原理

1. **线程同步**: 确保两个线程按顺序执行，不会同时访问共享资源
2. **线程通信**: 一个线程完成后通知另一个线程继续执行
3. **状态控制**: 使用标志位或事件对象控制线程的执行顺序
4. **资源保护**: 使用锁机制保护共享变量，避免数据竞争

## 应用场景

- 面试题：经典的线程同步问题
- 学习并发编程的基础概念
- 理解线程间的协作机制
- 掌握不同编程语言的线程实现方式

## 扩展思考

- 如何实现三个或更多线程的交替执行？
- 如何优化性能，减少线程切换的开销？
- 如何处理线程异常和中断？
- 如何实现更复杂的线程协作模式？
