package org.sky.auto.document;

import org.databene.benerator.anno.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.AutoBase;
import org.sky.auto.base.JUnitBase;
import org.sky.auto.document.listener.MyListener;
import org.sky.auto.document.listener.MyWebDriverListener;
import org.sky.auto.document.page.BaiduPage;
import org.sky.auto.driver.Browser;
import org.sky.auto.proxy.ProxyRunnerListener;
import org.sky.auto.runner.BaseJUnitAutoRunner;


/**注解@ThreadRunner是一个提供了多线程运行的注解，需要配合着上面的runwith来使用。只要是继承DefaultAutoRunner的运行器都可以实现多线程功能*/
@RunWith(BaseJUnitAutoRunner.class)
@ThreadRunner(threads=4)
public class NormalModuleCase extends JUnitBase{
	/**这种使用方式是当我们自己遍写测试用例的时候，在集成构建测试的时候会有些不方便
	 * 在这个例子类中所有的例子都是基于Junit的，TestNG的例子单独作为应用。
	 * 
	 * */
	@Test
	public void BaiduTest(){
		/**风格和以前是没有区别的。就是最开始的框架的模型，不需要任何继承形式*/
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		/**以前的元素放在page.xml文件里面，现在做了一些更改信息文件需要放在resource下的source.xml
		 * 里面，现在为PageObject模式提供了xml形式，如果我们只是用AutoBase这种编写形式不用任何模式的话<br>
		 * 只需要把元素定义在source.xml里面就行，如果在用到page模式，则需要把page类用到的元素放在和<br>
		 * 类名一样的xml文件中，文件名都是小写。也是放在resource文件夹里面。后面提供一个模式的例子
		 * */
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		//AutoBase.closeAllWindow();
	}
	/**这个方法主要是用来测试参数化的，假如我们测试过程中需要把这个方法加入一定的参数进行参数化测试则可以通过一些手段来实现
	 * 我提供的完全是通过重写JUnit的runner来实现的这个过程，开启参数化过程的话需要在类名上面加上一个注解.在实例里面我已经
	 * 加上了，这个是我为框架提供的默认的运行器。@source注解是告诉这个方法要运用的参数文件在哪里。在这里我们只提供了xls文件
	 * 格式的使用。不能够使用xlxs的文件格式，所以我们创建的excel在创建之后保存之后要注意格式。xls文件放在项目的根目录下面
	 * 现在只用来说简单的参数化过程，这个参数化过程是非常强大的。
	 * */
	@Test
	@Source("test.xls")
	public void BaiduTestByParams(String text){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		/***为了放我们的日志打印出来的效果更明显，所以我特别定义了一些专门的元素类,最主要的包括table类和checkbox类*/
		AutoBase.textField("百度首页-搜索框").sendKeys(text);
		/***等程序执行过程中我们可以看一下日志信息。能够比较直观的观察到运行的过程*/
		AutoBase.button("百度首页-搜索按钮").click();
		AutoBase.closeAllWindow();
	}
	
	/**这个类是为了展示现在可以使用新的定义形式，解决了层级定位和frame的烦恼。只需要把元素定义在frame里面就行，但是Frame只支持一层，<br>
	 * 如果存在多层的情况是暂时不支持的。那个时候可以通过AutoBase的api的方法来进行解决。可以看一下xml文件观察一下定义的方式。
	 * */
	@Test
	public void IT168Test(){
		AutoBase.open(Browser.Firefox,"http://www.it168.com");
		//AutoBase.moveToElement(AutoBase.element("笔记本浮层"));
		AutoBase.sElement("笔记本浮层").mouseOver();
		AutoBase.element("笔记本链接").click();
		AutoBase.sleep(3000);
		//AutoBase.closeAllWindow();
	}
	/**这个例子是用来说明page类怎么使用的，我在org.sky.auto.document.page包里面定义了需要用到的page类*/
	@Test 
	public void BaiduPageTest(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		
		/**不需要重新new 一个对象就可以通过一样的风格来直接使用里面的方法。写法都是一样的。就是需要把类名字传入进去，知道使用的是哪个页面。*/
		AutoBase.page().load(BaiduPage.class).search("北京");
		//AutoBase.closeAllWindow();
	}
	
	/**这个方法是介绍一下我提供的默认监听器功能，比如我想让代码在执行点击操作之后做些什么。看一下实现代码
	 * 监听器实现的方法在RunnerListener接口中，大家只要点开它包含的方法看一下就可以，字面意思就能说明
	 * 它的功能。具体的javadoc会以后提供。
	 * MyListener类在org.sky.auto.document.listener中。可以看一下实现的方法和到底是怎么操作的。
	 * */
	@Test
	public void ListenerTest(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		/**下面这行代码就是加载我们需要的监听器。可以加载多个监听器。都是通过注册的形式来进行添加。
		 * 通过unregister()的方法能够让我们卸载我们注册过的监听器。
		 * */
		ProxyRunnerListener.register(new MyListener());
		AutoBase.sElement("百度首页-搜索框").sendKeys("北京");
		AutoBase.sElement("百度首页-搜索按钮").click();
		//AutoBase.closeAllWindow();
	}
	
	
	/**这个方法是我自己扩展的webdriver，因为webdriver已经提供了一部分的监听器方法,有兴趣的可以实验一下，不过需要借助selenium的api
	 * 它自己提供的方法我没有做封装。并且封装的方法有点少。不过如果想自己在底层的基础上再做开发的话，可以作为扩展。
	 * 它提供的是在一些webdriver原生的监听方法。我继承在autodriverEventListener类里面了，方便我们调用，不用继承接口了。
	 * */
	@Test
	public void WebDriverListenerTest(){
		AutoBase.open(Browser.Firefox, "http://www.baidu.com");
		/**这个MyWebDriverListener监听器类也是放在org.sky.auto.document.listener包里面*/
		AutoBase.getAutoDriver().register(new MyWebDriverListener());
		/**这种监听方式配合element方法来使用，因为element是我提供的原生的webdriver的方法*/
		AutoBase.element("百度首页-搜索框").sendKeys("北京");
		AutoBase.element("百度首页-搜索按钮").click();
		//AutoBase.closeAllWindow();
	}
}
