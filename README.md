# 社交系统
#### 项目介绍
      《社交系统》采用目前主流的微服务系统架构SpringBoot+SpringCloud+SpringData进行开发，前端技术采用Vue.js。系统整体分为三大部分：微服务、网站前台、网站管理后台。功能模块包括文章、问答、招聘、活动、吐槽、交友、用户中心、搜索中心及第三方登录等。
      本项目融合了Docker容器化部署、第三方登录、SpringBoot、SpringCloud、SpringData、人工智能、爬虫、RabbitMQ等技术。
#### 开发环境、开发工具
      1、JDK1.8
      2、idea2019
      3、git
      4、Navicat Premium
#### SpringData查询和分页
      1、利用Specification接口实现复杂查询。
      2、Pageable实现分页。
      3、常用查询方法：in、join、equal等。
      4、优点：不需要写映射，少写方法和对应字段。
      5、缺点：SpringData主张表和对象的关系映射，但实际业务错综复杂，需要编写复杂的SQL解决，这方面没有mybatis好用。
      6、SpringDataJPA命名规则生成SQL语句。
      7、SpringDataJPA适合单表，MyBatis适合多表。
#### Redis(微服务使用缓存)
      1、添加jar包(pom.xml)
            <dependency>
		  	<groupId>org.springframework.boot</groupId>
		  	<artifactId>spring-boot-starter-data-redis</artifactId>
	  	</dependency>
      2、修改配置文件(application.yml)
              redis:
                  host: 127.0.0.1
      3、在service层注入RedisTemplate类，然后通过其中的方法获取缓存中的信息，或者将信息存入缓存中。
      4、修改、删除数据时，将缓存中对应的数据删除；只有查询时才缓存中没有的数据存入缓存（查询：将对应数据存如redis；修改和删除：将缓存中对应数据删除）。
      5、设置过期时间：第一次将数据存入redis时，设置：redisTemplate.opsForValue().set(key,对象信息,时间, TimeUnit(时间单位函数))。
#### SpringBoot缓存
      1、在启动类添加@EnableCaching注解。
      2、在查询方法上写@Cacheable注解表示添加缓存。
      3、在修改和删除方法上写@CacheEvict注解表示删除缓存。
      4、实际上是将查到的数据存入到了java虚拟机中，缺点：不能设置过期时间。
