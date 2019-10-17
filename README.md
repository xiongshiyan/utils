# utils

#### 项目介绍
项目工具类，以后在此项目中添加功能也尽可能是添加那些依赖少的，公共的东西

### 使用方式
下载本项目，gradle clean build得到的jar包引入工程即可。

或者使用gradle或者maven引入，最新为utils:1.8.3

```gradle
compile ("top.jfunc.common:utils:${version}")
```
```maven
<dependency>
    <groupId>top.jfunc.common</groupId>
    <artifactId>utils</artifactId>
    <version>${version}</version>
</dependency>
```