<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0046)http://res.kuyumall.com/document/standard.html -->
<html lang="en_us">
	<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http_equiv="X_UA_Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <title>锁知识点整理</title>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
    </head>
    <body youdao="bind">

	<%@ include file="/jsp/common/headbread.jsp" %>

<div class="heading" id="css">
  <h2>锁（java, DB）</h2>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>什么是锁</h3>
        <p>对资源的访问权限进行控制</p>
        <p>如果把一个资源（对象）比喻成屋子。就好像你进入了屋子锁上了门。你家人和贼都进不去了。</p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
关键字：synchronized
介绍： 作为在java中实现资源同步，“重量级”锁。同步加锁的是一个对象或者一个类，而不是代码。
关键词：volatile
介绍： 作为Java中的轻量级锁，当多线程中一个线程操作后可以保证其他线程可见，也就是书上所说的“可见性”，另外一个就是“重排序”。
 关键词：ReentrantLock（在JDK1.5中java.util.concurrent.*包，此包有一些并发操作的类库）
介绍： ReentrantLock，这个锁主要是能显示的添加锁和释放锁，好处是更加灵活，能够更加准确的控制锁，也能确保系统的稳定，比如说“重连”。
关键词:软件包 java.util.concurrent.locks 
介绍：为锁和等待条件提供一个框架的接口和类，它不同于内置同步和监视器。
...
        </pre>
		</div>
    </div>
</div>


<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>synchronized</h3>
        <p>一：Java锁Synchronized，对象锁和类锁举例：（com.kuyu.study.LockStudy）</p>
        <p>二：synchronized关键字可标记方法和代码块：（com.kuyu.study.LockStudy）</p>
        <ol>
        	<li>实例方法和代码块</li>
        	<li>静态方法和代码块</li>
        </ol>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<p>Java Synchronized，对象锁和类锁举例:<a href="http://www.cnblogs.com/cangqiongbingchen/p/5806757.html" target="_blank">http://www.cnblogs.com/cangqiongbingchen/p/5806757.html</a></p>
<p>Java synchronized 介绍 ：<a href="http://blog.csdn.net/suifeng3051/article/details/48711405" target="_blank">http://blog.csdn.net/suifeng3051/article/details/48711405</a></p>
<p>java synchronized关键字的用法：<a href="http://zhh9106.iteye.com/blog/2151791" target="_blank">http://zhh9106.iteye.com/blog/2151791</a></p>
        </pre>
		</div>
    </div>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>死锁</h3>
        <p>死锁是两个甚至多个线程被永久阻塞时的一种运行局面。死锁的原因：由于两个甚至多个线程相互等待对方已被锁定的资源。</p>
        <h3>死锁发生的条件</h3>
        <p/>
        <ol>
			<li><b>互斥条件</b>：线程对资源的访问是排他性的，如果一个线程对占用了某资源，那么其他线程必须处于等待状态，直到资源被释放。</li>
			<li><b>请求和保持条件</b>：线程T1至少已经保持了一个资源R1占用,但又提出对另一个资源R2请求，而此时，资源R2被其他线程T2占用，于是该线程T1也必须等待，但又对自己保持的资源R1不释放。</li>
			<li><b>不剥夺条件</b>：线程已获得的资源，在未使用完之前，不能被其他线程剥夺，只能在使用完以后由自己释放。</li>
			<li><b>环路等待条件</b>：在死锁发生时，必然存在一个“进程-资源环形链”，即：{p0,p1,p2,...pn},进程p0（或线程）等待p1占用的资源，p1等待p2占用的资源，pn等待p0占用的资源。（最直观的理解是，p0等待p1占用的资源，而p1而在等待p0占用的资源，于是两个进程就相互等待）</li>
		</ol>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
图解死锁：
<p><a href="${pageContext.request.contextPath}/image/deadlock.jpg" target="_blank"><img src="${pageContext.request.contextPath}/image/deadlock.jpg"></a></p>
<h3>避免死锁</h3>
<li>避免嵌套封锁：这是死锁最主要的原因的，如果你已经有一个资源了就要避免封锁另一个资源。如果你运行时只有一个对象封锁，那是几乎不可能出现一个死锁局面的。</li>
<li>只对有请求的进行封锁：你应当只想你要运行的资源获取封锁，比如在上述程序中我在封锁的完全的对象资源。但是如果我们只对它所属领域中的一个感兴趣，那我们应当封锁住那个特殊的领域而并非完全的对象。</li>
<li>避免无限期的等待：如果两个线程正在等待对象结束，无限期的使用线程加入，如果你的线程必须要等待另一个线程的结束，若是等待进程的结束加入最好准备最长时间。</li>
<a href="http://www.importnew.com/9668.html" target="_blank">Java死锁范例以及如何分析死锁:http://www.importnew.com/9668.html</a><br/>
<a href="http://blog.csdn.net/zll793027848/article/details/8670546" target="_blank"> JAVA实现的一个简单的死锁（附解释）:http://blog.csdn.net/zll793027848/article/details/8670546</a>
        </pre>
		</div>
    </div>
</div>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>活锁、饥饿</h3>
        <p>活锁是指线程1可以使用资源，但它很礼貌，让其他线程先使用资源，线程2也可以使用资源，但它很绅士，也让其他线程先使用资源。这样你让我，我让你，最后两个线程都无法使用资源。</p>
        <h3>饥饿</h3>
        <p>饥饿：是指如果线程T1占用了资源R，线程T2又请求封锁R，于是T2等待。T3也请求资源R，当T1释放了R上的封锁后，系统首先批准了T3的请求，T2仍然等待。然后T4又请求封锁R，当T3释放了R上的封锁之后，系统又批准了T4的请求......，T2可能永远等待。</p>
        <p/>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
图解活锁：
<p><a href="${pageContext.request.contextPath}/image/livelock.png" target="_blank"><img src="${pageContext.request.contextPath}/image/livelock.png"></a></p>
图解饥饿：
<a href="${pageContext.request.contextPath}/image/hunger.png" target="_blank"><img src="${pageContext.request.contextPath}/image/hunger.png"></a>
        </pre>
		</div>
    </div>
</div>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>多线程-锁locks</h3>
        <p>自 Java 5 开始，java.util.concurrent.locks 包中包含了一些锁的实现，因此你不用去实现自己的锁了。但是你仍然需要去了解怎样使用这些锁。</p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<p>Java多线程-锁:<a href="http://www.linuxidc.com/Linux/2016-09/134801.htm" target="_blank">http://www.linuxidc.com/Linux/2016-09/134801.htm</a></p>
<p>Java多线程之并发锁:<a href="http://www.linuxidc.com/Linux/2012-03/57069.htm" target="_blank">http://www.linuxidc.com/Linux/2012-03/57069.htm</a></p>
<p>Java多线程之多线程的锁机制:<a href="http://www.linuxidc.com/Linux/2016-07/133403.htm" target="_blank">http://www.linuxidc.com/Linux/2016-07/133403.htm</a></p>
<p>Java多线程并发锁和原子操作，你真的了解吗？:<a href="http://blog.csdn.net/luohuacanyue/article/details/7796352" target="_blank">http://blog.csdn.net/luohuacanyue/article/details/7796352</a></
        </pre>
		</div>
    </div>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>数据库锁</h3>
        <p/>
        <ol>
        	<li>锁的概念</li>
        	<li>锁的分类</li>
        	<li>锁的粒度</li>
        </ol>
        </p>
        <h4>附：</h4>
        <p>数据库锁：<a href="http://www.cnblogs.com/zhouqianhua/archive/2011/04/15/2017049.html" target="_blank">http://www.cnblogs.com/zhouqianhua/archive/2011/04/15/2017049.html</a></p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<h4>1:概念</h4>
<p>数据库是一个多用户使用的共享资源。当多个用户并发地存取数据时，在数据库中就会产生多个事务同时存取同一数据的情况。若对并发操作不加控制就可能会读取和存储不正确的数据，破坏数据库的一致性。
加锁是实现数据库并发控制的一个非常重要的技术。当事务在对某个数据对象进行操作前，先向系统发出请求，对其加锁。加锁后事务就对该数据对象有了一定的控制，在该事务释放锁之前，其他的事务不能对此数据对象进行更新操作。</p>
<h4>2:分类</h4>
<p><b>共享（S)锁</b>：多个事务可封锁一个共享页；任何事务都不能修改该页； 通常是该页被读取完毕，S锁立即被释放。</p> 
<p><b>排它（X)锁</b>：仅允许一个事务封锁此页；其他任何事务必须等到X锁被释放才能对该页进行访问；X锁一直到事务结束才能被释放。</p> 
<p><b>更新（U)锁</b>：更新锁在修改操作的初始化阶段用来锁定可能要被修改的资源，这样可以避免使用共享锁造成的死锁现象。</p>
<p>因为使用共享锁时，修改数据的操作分为两步，首先获得一个共享锁，读取数据，然后将共享锁升级为排它锁，然后再执行修改操作。这样如果同时有两个或多个事务同时对一个事务申请了共享锁，在修改数据的时候，这些事务都要将共享锁升级为排它锁。这时，这些事务都不会释放共享锁而是一直等待对方释放，这样就造成了死锁。如果一个数据在修改前直接申请更新锁，在数据修改的时候再升级为排它锁，就可以避免死锁。</p>
<h4>3:粒度</h4>
<p>锁定在较小的粒度的资源 --行 ，增大系统并发量，同时也增大系统开销。维护锁多</p>
<p>锁定在较大的粒度的资源 --表 ，降低系统并发量，同时也降低系统开销。维护锁少</p>
        </pre>
		</div>
    </div>
</div>
</body></html>