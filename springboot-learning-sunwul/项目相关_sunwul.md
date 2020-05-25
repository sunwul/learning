# 此项目目前较为完整的一个功能集成了eureka,zuul,swagger,springboot admin对数据库进行基本的CRUD操作,其它模块相对独立

运行顺序:
	每个微服务都是一个"eureka-client",所以需要先启动eureka-server,这个微服务是eureka注册中心
	其它的微服务无先后顺序(一般先启动网关)

相关链接与端口(已为每个服务都编写了swagger接口文档,可以直接在链接后加上/swagger-ui.html访问swagger接口文档)
  > eureka-server(eureka注册中心): localhost:9001
	eureka-client(测试服务): localhost:8057
	userinfo(用户信息模块): localhost:8056
	zuul(zuul网关服务中心): localhost:8090

各服务接口访问路径(eureka注册中心与zuul网关服务中心只做服务发现与转发功能)
	服务启动后,以下路径可直接在浏览器访问,测试服务是否启动成功,各服务调用是否正常,其它服务可以看项目,这里就不放出来了
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

##### redis
    RabbitMQ 地址: http://localhost:6379 密码 sunwul
##### MQ
    ActiveMQ 版本:5.15.11 地址: http://localhost:8161  默认用户名与密码 admin/admin
    RabbitMQ 版本:3.8.2 地址: http://localhost:15672 用户名密码 sunwul/123
##### Hystix
    关于负载只研究了很短时间,参考网上做了一个很简单的服务间调用,还有待进步
##### ELK
    版本: elasticsearch-7.5.2 + logstash-7.5.2 +  kibana-7.5.2
    地址: localhost:9200 + localhost:9600 + localhost:5601
  > ELK环境按自己机器部署的来改动,我在自己的机器里测试了很多的功能,所以可能环境有点乱,这里就不放出来了
##### 关于项目依赖继承的问题
    由于是刚开始学,对于依赖的问题没有处理好,可能会出现依赖版本混乱的问题,但是不影响功能,关于springboot的依赖管理以及父子继承依赖的问题,可以看另一个md文档-> springboot工程中的问题   ;重新学习了下依赖管理与继承,写的很基础,但也对依赖管理有了比较清楚的认识,至少不会出现像这个项目这样依赖混乱,自己都摸不着头脑的问题了
