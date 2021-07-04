## ReactiveX入门
### 什么是ReactiveX
### 为什么使用ReactiveX
### 示例
- HelloWorld
- 常用操作符
  - map
  - from
  - subscribe
- 线程切换
  - observeOn,subscribeOn
  - parallel,sequential
- 多线程并发
  - [flatMap,concatMap,concatMapEager](https://dzone.com/articles/rxjava-flatmap-vs-concatmap-vs-concatmapeager)
  - parallel,sequential
- 数据分组
  - Buffer
  - Window
- Flowable

### 重点讲解
- observeOn和subscribeOn
- Map和FlatMap
- flowable和observable

### 动手实现RxJava
- [设计模式](https://refactoringguru.cn/design-patterns)
    - Observer(com.cx.patterns.observer)
    - Decorator(com.cx.patterns.decorator)
    - Builder(com.cx.patterns.builder)
- 观察者实现(com.cx.rx)
- Map和FlatMap实现(com.cx.rx.impl.MapObservable)
- 线程切换原理及实现

### 相关框架和工具
- Flink
- Spark
- WebFlux
- Vert.x

### 参考资料
- [官网和文档](http://reactivex.io/)
- [初学者系列](https://www.jianshu.com/p/36e0f7f43a51)