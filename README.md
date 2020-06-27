#项目简介

>此项目是使用了spring cloud技术重做本人第一个git项目ofo,当时是在学校学完完整java技术一时兴起打算把从编码到配置服务器再到发布完整的来一次巩固，也是算是我的启蒙项目，非常有意义。目前各种技术更新迭代的速度非常快，这次就使用了spring cloud微服务技术重做了原项目。

##项目目录
- ofo-entity
- ofo-eureka-server
- ofo-feign
- ofo-gateway
- ofo-hystrix-dashboard-turbine
- ofo-service

##其它文件目录
>这里使用了docker技术打包多个镜像，模拟微服务集群部署。

- dockerbuild-sourcefiles
	- 此目录下是包含打包好的jar以及制作docker镜像的dockerfile源文件
- dockerbuild-sourcefiles
	- 此目录中的两个文件是docker-compose的运行脚本，可以在安装docker-compose后运行`docker-compose -f “文件名” up -d`即可一键启动脚本中所有的微服务。镜像会自动从我的docker hub中拉取，然后运行。由于各个微服务中都是通过服务名去调用同一网段中的其它微服务，脚本中的所有镜像都是在一个docker网桥中，名称是ofo-network。

- web
	-	这个目前是前端页面的静态网页，所有的js脚本我都有写明注释，方便阅读，此目录也已经制作成docker镜像。 

- db-sources
	- 这里面是数据库文件

## `注意事项：`
	
- 项目目录中的配置文件未配置集群，都是以单例目前本地运行的，感兴趣的可以自行下载文件后，修改集群部署。
- 另外docker-compose脚本分了两个版本，毕竟一下子跑那么多的java微服务，还是很吃硬件的，其中带smile是消减了部分微服务后的集群，若运行docker-compose.yml这个脚本，建议内存8G以上，CPU4核心以上，否则可能会触发系统的回收机制，导致部分微服务会被KILL掉，造成服务不可用。
	
