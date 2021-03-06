## ReactiveX入门
### 什么是ReactiveX
### 为什么使用ReactiveX
![img.png](img.png)
### 支持语言列表
![img_1.png](img_1.png)
### 示例
- HelloWorld
- 常用操作符
  - subscribe
  - map
  - from
  - Group
  - groupby
- hot vs cold
  - subject
  - multicast
- 订阅与被订阅的结合  
  - 多对一
    - merge, concat, zip
  - 一对多
    - publish
    - replay
    - cache
- 多线程
  - observeOn,subscribeOn
  - parallel,sequential
  - [flatMap,concatMap,concatMapEager](https://dzone.com/articles/rxjava-flatmap-vs-concatmap-vs-concatmapeager)
  - blockingSubscribe,Schedulers.mainThread
- 流控制和背压
  - 分组 
    - buffer
    - window
  - 采样  
    - throttle
    - debounce
  - 背压
    - flowable
    - 背压异常
    - 背压处理方式
    - generate
- 自定义操作
  - compose
  - lift,ObservableOperator

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

## 应用场景
### Android应用
- RxBinding
- RxLife
### 后端应用
- RxNetty
### Demo
- 多线程下载(com.cx.tools.multidownload)


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
- [206 StatusCode](https://www.cnblogs.com/simonbaker/p/5190675.html)
- [Subject](https://blog.csdn.net/weixin_42814000/article/details/105956035)
- [hot vs cold](https://stackoverflow.com/questions/32190445/hot-and-cold-observables-are-there-hot-and-cold-operators)
- [设计模式](https://refactoring.guru/design-patterns)

### 参考书籍(微信读书都有)
- [reactive-programming-with-rxjava](https://piemonj.gitbooks.io/reactive-programming-with-rxjava/content/)
- Learning-RxJava-Build-concurrent-applications-using-reactive-programming
- RxJava反应式编程(这个翻译有点问题，怎么翻译都行，看你怎么理解)