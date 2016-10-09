<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<!-- saved from url=(0046)http://res.kuyumall.com/document/standard.html -->
<html lang="en_us"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http_equiv="X_UA_Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <title>多线程知识点整理</title>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
    </head>
    <body youdao="bind">

	<%@ include file="/jsp/common/headbread.jsp" %>

<div class="heading" id="css">
  <h2>多线程</h2>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>进程和线程  </h3>
        <p>线程是在进程的基础上，进程是在操作系统的基础上。</p>
        <ol>
        	<li>进程，特点</li>
        	<li>线程，特点</li>
        	<li>相同点和区别</li>
        </ol>
        <p><b>多进程是指操作系统能同时运行多个任务（程序）。</b></p>
		<p><b>多线程是指在同一程序中有多个顺序流在执行。</b></p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<p>1：进程（进程是资源分配的最小单位）
    进程是程序执行的一个实例。
    进程的特点，每一个进程都有自己的独立的一块内存空间、一组资源系统。其内部数据和状态都是完全独立的。进程之间切换开销大。
</p>
<p>2：线程（线程是cpu调度的最小单位）
    线程是进程的一个实体，同一类线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换开销小。
    一个进程包含至少1~n个线程。
</p>
<p>3：相同点和区别
（1）：相同点
    线程和进程一样分为五个阶段：创建、就绪、运行、阻塞、终止。均可并发执行。
（2）：区别
	<li>地址空间：进程内的一个执行单元；进程至少有一个线程；它们共享进程的地址空间;而进程有自己独立的地址空间</li>
	<li>资源拥有：进程是资源分配和拥有的单位，同一个进程内的线程共享进程的资源</li>
	<li>调度单元：线程是处理器调度的基本单位，但进程不是</li>
</p>
<p><a href="http://blog.csdn.net/suifeng3051/article/details/49251959"  target="_blank">java 线程详解</a></p>
<p><a href="http://blog.csdn.net/evankaka/article/details/44153709"  target="_blank">Java多线程学习（吐血超详细总结）</a></p>
<p><a href="http://www.cnblogs.com/lwbqqyumidi/p/3804883.html"  target="_blank">Java总结篇系列：Java多线程（一）</a></p>
        </pre>
		</div>
    </div>
</div>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>线程的创建  </h3>
        <p>多线程的创建，两种方式：</p>
        <ol>
        	<li>继承（extends） Thread</li>
        	<li>实现 （implement）Runnable</li>
        	<li>实现 （implement）Callable</li>
        </ol>
        <p>在创建多线程的时候，实现Runable创建线程会比较好。因java的单继承，继承Thread,使程序的灵活性和可扩展性降低。</p>
        <p style="color: red;">
        	注： <ol>
        		 <li>不能对同一线程对象两次调用start()方法。</li>
        		 <li>start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。</li>
        		</ol>
        </p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
具体的代码示例：
1.继承Thread类，重写该类的run()方法。 <b>（具体代码：com.kuyu.study.TestExThread）</b>
2.实现Runnable接口，并重写该接口的run()方法，该run()方法同样是线程执行体，创建Runnable实现类的实例，
并以此实例作为Thread类的target来创建Thread对象，该Thread对象才是真正的线程对象。<b>（具体代码：com.kuyu.study.TestImRunnable）</b>
3.使用Callable和Future接口创建线程。具体是创建Callable接口的实现类，并实现call()方法。并使用FutureTask类来包装Callable实现类的对象，
且以此FutureTask对象作为Thread对象的target来创建线程。<b>（具体代码：com.kuyu.study.TestImCallable）</b>
<p/>
总结：
实现Runnable接口比继承Thread类所具有的优势：
1）：适合多个相同的程序代码的线程去处理同一个资源
2）：可以避免java中的单继承的限制
3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类

        </pre>
		</div>
    </div>
</div>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>线程的状态</h3>
        <p>线程的五种状态：</p>
        <ol>
        	<li>创建</li>
        	<li>就绪</li>
        	<li>运行</li>
        	<li>阻塞</li>
        	<li>终止</li>
        </ol>
        <a href="${pageContext.request.contextPath}/image/multithread_status.jpg" target="_blank"><img alt="" src="${pageContext.request.contextPath}/image/multithread_status.jpg"></a>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<p>五种状态分别如下：
1. 新建状态（New）：新创建了一个线程对象。
2. 就绪状态（Runnable）：线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于可运行线程池中，变得可运行，等待获取CPU的使用权。
3. 运行状态（Running）：就绪状态的线程获取了CPU，执行程序代码。
4. 阻塞状态（Blocked）：塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。
5. 死亡状态（Dead）：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。
</p>
<p>
其中阻塞又可能是由以下几种情况造成：
（1）调用 sleep(毫秒数)，使线程进入“睡眠”状态。在规定的时间内，这个线程是不会运行的。
（2）用 suspend()暂停了线程的执行。除非线程收到 resume()消息，否则不会返回“可运行”状态。
（3）用 wait()暂停了线程的执行。除非线程收到 nofify()或者 notifyAll()消息，否则不会变成“可运行“。
（4）线程正在等候一些 IO（输入输出）操作完成。
（5）线程试图调用另一个对象的“同步”方法，但那个对象处于锁定状态，暂时无法使用。
</p>
        </pre>
		</div>
    </div>
</div>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>线程的优先级</h3>
        <p>Java线程有优先级，优先级高的线程会获得较多的运行机会。</p>
        <p><b style="color: red;">注：具有较高线程优先级的线程对象仅表示此线程具有较多的执行机会，而非优先执行。</b></p>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
<p>五种状态分别如下：
Java线程的优先级用整数表示，取值范围是1~10，Thread类有以下三个静态常量：
<li>static int MAX_PRIORITY	线程可以具有的最高优先级，取值为10。</li>
<li>static int MIN_PRIORITY	线程可以具有的最低优先级，取值为1。</li>
<li>static int NORM_PRIORITY	分配给线程的默认优先级，取值为5。</li>
Thread类的setPriority()和getPriority()方法分别用来设置和获取线程的优先级。
</p>
        </pre>
		</div>
    </div>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>多线程思维导图</h3>
        <ol>
        	<li>初级思维导图</li>
        	<li>中级思维导图</li>
        </ol>
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
      	<p><a href="${pageContext.request.contextPath}/image/primary_multthread_xmind.png" target="_blank">多线程初级思维导图</a></p>
        <p><a href="${pageContext.request.contextPath}/image/medium_multthread_xmind.jpg" target="_blank">多线程中级思维导图</a></p>
        </pre>
		</div>
    </div>
</div>

<div class="heading" id="css">
  <h2>多线程和并发编程</h2>
</div>
<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>并发和并行</h3>
        <p>总结：</p>
        <ol>
        	<li>并发：单处理器 多任务，逻辑上同时发生，时间段；</li>
        	<li>并行：多处理器 多任务，物理上同时发生，时间点。</li>
        </ol>
        <p>举例（比喻）：一个人一次吃三个苹果（并发），三个人一次吃三个苹果（并行）。</p>
        
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
        
<p>Concurrent and Parallel Programming：<a href="http://joearms.github.io/2013/04/05/concurrent-and-parallel-programming.html" target="_blank">http://joearms.github.io/2013/04/05/concurrent-and-parallel-programming.html</a></p>
<p>并发和并行：<a href="http://3961409.blog.51cto.com/3951409/759708" target="_blank">http://3961409.blog.51cto.com/3951409/759708</a></p>
        
        </pre>
		</div>
    </div>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3>线程池</h3>
        <p>总结：</p>
        
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
        
<p>Java并发编程：线程池的使用<a href="http://www.cnblogs.com/dolphin0520/p/3932921.html" target="_blank">http://www.cnblogs.com/dolphin0520/p/3932921.html</a></p>
<p>Java并发编程与技术内幕:线程池深入理解<a href="http://blog.csdn.net/evankaka/article/details/51489322" target="_blank">http://blog.csdn.net/evankaka/article/details/51489322</a></p>
        
        </pre>
		</div>
    </div>
</div>

<div class="section" id="css_declaration_order">
    <div class="col">
        <h3> Java并发编程</h3>
        
    </div>
    <div class="col">
        <div class="highlight">
        <pre>
        
<p>Java 多线程 并发编程<a href="http://blog.csdn.net/escaflone/article/details/10418651" target="_blank">http://blog.csdn.net/escaflone/article/details/10418651</a></p>
<p>Java并发编程与技术内幕：<a href="http://blog.csdn.net/Evankaka/article/category/6262786" target="_blank">http://blog.csdn.net/Evankaka/article/category/6262786</a></p>
<p>Java并发编程<a href="http://www.cnblogs.com/dolphin0520/category/602384.html" target="_blank">http://www.cnblogs.com/dolphin0520/category/602384.html</a></p>
        
        </pre>
		</div>
    </div>
</div>


<p/>
<p/>
<p/>
<p/>
</body></html>