## ReactiveX入门
### 什么是ReactiveX
### 为什么使用ReactiveX
### 示例
- HelloWorld
- 常用操作符
  - subscribe
  - map
  - from
  - Group
  - 分组
    - merge, concat, zip
    - groupby
  - 多播
    - publish
- 线程切换
  - observeOn,subscribeOn
  - parallel,sequential
- 多线程并发
  - [flatMap,concatMap,concatMapEager](https://dzone.com/articles/rxjava-flatmap-vs-concatmap-vs-concatmapeager)
  - parallel,sequential
  - blockingSubscribe,Schedulers.mainThread
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

### 应用
- 多线程下载


### 相关框架和工具
- Flink
- Spark
- WebFlux
- Vert.x

### 参考资料
- [官网和文档](http://reactivex.io/)
- [初学者系列](https://www.jianshu.com/p/36e0f7f43a51)
- [flatMap,concatMap,concatMapEager](https://dzone.com/articles/rxjava-flatmap-vs-concatmap-vs-concatmapeager)
- [Understanding RxJava subscribeOn and observeOn](https://proandroiddev.com/understanding-rxjava-subscribeon-and-observeon-744b0c6a41ea)