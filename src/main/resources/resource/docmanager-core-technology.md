# docmanager 项目使用到的核心技术点

### 一、 框架选型

+ Spring
+ SpringMVC
+ Hibernate
+ Shiro （是一款用户权限认证框架）

### 二、数据库
+ Mysql数据库

### 三、技术细节
+ 前端以`bootstrap`作为基础样式库，并结合自定样式。
+ 利用bootsrap第三方文件上传主键，结合Spring文件上传工具包`commons-fileupload`，完成文件上传功能。
+ 使用Apache开源poi工具套件，并结合自定义注解`@ExcleMapping`实现在线导出Excel功能
+ 使用第三方开放api实现Word文件在线预览功能。
+ 页面呈现部分借助jsp模版技术实现页面渲染。
+ 数据库链接方面，使用阿里巴巴开源的`druid`数据库链接池，提高数据库链接利用率，以减少资源浪费。
+ 项目构建方面，使用`maven`，作为jar包的依赖管理，方便对第三方jar包的管理。