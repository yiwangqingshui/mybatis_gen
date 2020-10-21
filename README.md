# spring_mybatis_gen

#### 项目介绍
当前的80%项目需要的SQL语句都是些简单的语句，秉着大道至简的原则，我们开发了spring结合mybatis的代码生成框架，提高开发效率，统一优化基础SQL语句



#### 使用说明---test-ssss

* 配置maven插件

  ~~~
  <plugin>
     <groupId>com.github.yiwangqingshui</groupId>
     <artifactId>mybatis-maven-plugin</artifactId>
     <version>1.0.0</version>
     <configuration>
           <outputDirectory>../common/dal/website-log</outputDirectory>
               <url>jdbc:mysql://127.0.0.1:3306/**</url>
               <username>**</username>
               <password>**</password>
               <packageName>com.yiwangqingshui.dal.website</packageName>
               <pageFlag>no</pageFlag>
               <tablePrefix>t_</tablePrefix>
               <dbType>mysql</dbType>
      </configuration>
  </plugin>
  ~~~

  

* 执行maven操作

  ~~~
  mvn mybatis:gen
  [INFO] 开始执行maven插件
  ===========输入需要生成的表==============
  -- 多表用 ; (分号分隔)分隔
  -- q 退出
  ===========输入需要生成的表==============
  这里输入你需要生成表的名称
  
  ~~~

  

####  参数介绍

|   参数 |说明               |默认值
|:---      |:---               |:---|
|  outputDirectory|生成代码目录，建议dal层分开编写|${project.baseDir}|
|url|数据库连接地址|无|
|username|数据量用户名|无|
|password|数据库密码|无|
|pageFlag |是否需要分页|yes|
|tablePrefix|去掉表前缀的显示,例如：表名称t_user,生成问实体类User|t_|
|dbType|数据库类型，目前只支持mysql，oracle|mysql|
|packageName|代码的包名称|com.yiwangqingshui.dal.xxx|



### 注意事项

出于对数据库表约定俗成的定义表名称全部是大写，目前的框架生成的xml配置文件表名称全部是大写（出于对标准的一个敬畏之心），当你使用mysql数据库linux版本的时候，请配置取消表大小写的验证。具体配置如下。

* linux版本mysql取消表大小写的配置

  ~~~
  #在mysql的配置加载文件中设置
  
  #取消表明大小写的验证
  lower_case_table_names=1
  ~~~

  

### table字段支持情况

秉着一个对大多数约定俗成的需求需要不支持一些数据库表大字段的支持，在日常的使用场景中，数据库本身就不建议存储大字段如：（clob，blob）这些字段的存储，数据库大字段不支持

### 问题解答
如果在运行的过程中出现
~~~
  Failure to find com.oracle:ojdbc6:jar:11.2.0.1.0   则需要自己绑定oraclejar包
  绑定方式：
  mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.1.0 -Dpackaging=jar -  Dfile=G:\app\smc\product\11.2.0\dbhome_1\jdbc\lib\ojdbc6.jar
 把本地的oracle jar包放入本地maven仓库中
~~~


### 结束语

​      根据目前几年的代码开发的一个感受，dal层的代码其实就是基础common内容，本身就不会嵌入业务的相关开发，他是一个公共部分，使用代码自动生成可以更好的优化和统一修改有问题的SQL语句。

​      该项目是本人第一个开源项目很多东西都是一步一步在走，代码写的不是很严谨希望大师们指教小弟一定虚心接受，另外感谢dalgen的作者，以前一直使用的该代码自动生成，在使用的过程中一些配置比较繁琐，从而触发自己想写一个插件的尝试。希望广大编程爱好者一起交流，请留言。。。。。
