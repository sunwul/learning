# 项目目前集成了eureka,zuul,swagger,springboot admin

运行顺序:
	每个微服务都是一个"eureka-client",所以需要先启动eureka-server,这个微服务是eureka注册中心
	其它的微服务无先后顺序

相关链接与端口(已为每个服务都编写了swagger接口文档,可以直接在链接后加上/swagger-ui.html访问swagger接口文档)
  > eureka-server(eureka注册中心): localhost:9001
	eureka-client(测试服务): localhost:8057
	userinfo(用户信息模块): localhost:8056
	zuul(zuul网关服务中心): localhost:8090

各服务接口访问路径(eureka注册中心与zuul网关服务中心只做服务发现与转发功能)
	服务启动后,以下路径可直接在浏览器访问,其它服务可以看项目,这里就不放出来了
	eureka-client(测试服务): localhost:8057/hello/world
	userinfo(用户信息模块): localhost:8056/getcs (需调用数据库数据)
	
经zuul转发后的接口访问路径(所有路径都由http://localhost:8090转发)
   > http://localhost:8090/userinfo调用http://localhost:8056中所有服务
	http://localhost:8090/userinfocs调用http://localhost:8056中所有服务
	eureka-client的/hello/world:
		http://localhost:8090/eureka-client-hello/hello/world
		http://localhost:8090/world/hello/world

	http://localhost:8090/user调用http://localhost:8056中所有服务
	http://localhost:8090/us调用http://localhost:8056中所有服务

swagger已经与eureka整合,直接点击eureka注册中心的Status项下的链接可以直接查看相关服务的接口文档

    springboot Admin 地址: http://localhost:8091 用户名密码 sunwul/123
    ELK 地址: http://localhost:8060
# 其它模块

##### redis相关
    RabbitMQ 地址: http://localhost:6379 密码 sunwul
##### MQ相关
    ActiveMQ 地址: http://localhost:8161  默认用户名与密码 admin/admin
    RabbitMQ 地址: http://localhost:15672 用户名密码 sunwul/123