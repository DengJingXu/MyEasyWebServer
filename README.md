


    个人手写的简单web服务器，但是存在个bug暂时没有能力解决， **应该是线程问题** ，用浏览器访问时 **有时** 会产生两个Socket client连接（即两个请求），如访问localhost:8888/时正常应该是产生一个请求的Socket。 **可能是浏览器问题** ，因为用api测试工具测试都只产生一个Sockey（正常）。

有关主要的类如下：
- com.myserver.core. **Server**   :用于启动程序，里面不处理请求，而是将分发请求到Dispatcher线程类处理
- com.myserver.core. **Dispatcher** :线程类，接收请求和响应回去，用从WebApp类获取的对应的Servlet 处理请求和响应
- com.myserver.core. **Request** :封装请求内容
- com.myserver.core. **Response** :封装响应内容
- com.myserver.core. **Servlet** （接口）  ：给用户实现的接口，编写要响应的内容
- com.myserver.core.constant. **Constant** :常量类
- com.myserver.core.po. **Enity** :从web.xml解析后的servlet-name,servlet-class的实体类
- com.myserver.core.po. **Mapping** :从web.xml解析后的servlet-name,url-pattern的实体类
- com.myserver.core.sax. **WebApp**   :SAX解析xml主类，解析构建WebContext上下文，并通过该上下文请求地址(url)获取对应的类路径，通过反射构造对应的Serlvet
- com.myserver.core. **WebContext**   :存储所有的servlet和servlet-mapping的信息
- com.myserver.core.PHandler   :SAX解析处理类，继承于DefaultHandler，解析web.xml，并保存










