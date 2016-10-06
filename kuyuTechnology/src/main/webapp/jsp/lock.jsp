<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0046)http://res.kuyumall.com/document/standard.html -->
<html lang="en_us"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http_equiv="X_UA_Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <title>酷友垂直电商中心知识点整理</title>

    <link rel="stylesheet" href="../static/style.css">
  
    </head>

    <body youdao="bind">

<header class="masthead">
  <div class="container">
    <span class="icon">✍</span>
    <h1>酷友垂直电商中心知识点整理</h1>
    <p class="lead">其中涉及事务、锁（java, DB）、多线程知识点整理</p>
    <p class="lead">当前版本1.0.0</p>
  </div>
</header>


    <a href="http://res.kuyumall.com/document/standard.html#top" id="toTop"></a>
<div class="heading" id="toc">
    <h2>Table of contents</h2>
</div>
<div class="section toc">
    <div class="col">
        <h4><a href="##">知识点</a></h4>
        <ul>
            <li><a href="/jsp/transaction.jsp">事务</a></li>
            <li><a href="/jsp/lock.jsp">锁（java, DB）</a></li>
            <li><a href="/jsp/multithreading.jsp">多线程知识点整理</a></li>
        </ul>
    </div>
</div>

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
        <p>一：Java锁Synchronized，对象锁和类锁举例：（详情看代码示例）</p>
        <p>二：synchronized关键字可标记四种代码块：（详情看代码示例）</p>
        <ol>
        	<li>实例方法</li>
        	<li>实例方法中的代码块</li>
        	<li>静态方法</li>
        	<li> 静态方法中的代码块</li>
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
        <p>死锁的原因：由于两个线程相互等待对方已被锁定的资源</p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<a href="http://www.importnew.com/9668.html" target="_blank">Java死锁范例以及如何分析死锁:http://www.importnew.com/9668.html</a><br/>
<a href="http://blog.csdn.net/zll793027848/article/details/8670546" target="_blank"> JAVA实现的一个简单的死锁（附解释）:http://blog.csdn.net/zll793027848/article/details/8670546</a>
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
Java多线程-锁:<a href="http://www.linuxidc.com/Linux/2016-09/134801.htm">http://www.linuxidc.com/Linux/2016-09/134801.htm</a>
        </pre>
		</div>
    </div>
</div>
<hr>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>数据库锁</h3>
        <p>死锁的原因是由于 两个线程相互等待 对方已被锁定的资源</p>
        <ol>
            <li>Positioning</li>
        </ol>
        <p>Positioning 处在第一位，因为他可以使一个元素脱离正常文本流，并且覆盖盒模型相关的样式。盒模型紧跟其后，因为他决定了一个组件的大小和位置。</p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
        public int getOrderCount(OrderMainQueryModel qm) {
			return myDao.getOrderCount(qm);
		}
        </pre>
		</div>
    </div>
</div>
</body></html>