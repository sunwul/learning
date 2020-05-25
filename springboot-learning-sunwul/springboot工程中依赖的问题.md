## dependencyManagement

maven使用dependencyManagement 元素来提供一种管理依赖版本号的方式

==通常会在一个组织或者项目的最顶层的父POM 中看到dependencyManagement 元素==

使用pom.xml中的dependencyManagement 元素能让所有在子项目中引用一个依赖而不用显式的列出版本号.

maven会沿着父子层次向上走,直到找到一个拥有dependencyManagement 元素的项目,然后它就会使用这个dependencyManagement 元素中指定的版本号.

例如在父项目pom中:

```xml
    <!--子模块继承之后,提供的作用: 锁定版本+子module不用写groupId和version-->    
	<dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.2</version>
            </dependency>
            ...
        <dependencies>
    <dependencyManagement>
```

然后在子项目中就可以在添加 mysql-connector-java 时不用指定版本号,例如:

```xml
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <!--只要子模块不单独指定版本,就会继承父项目中依赖的版本-->
            </dependency>
            ...
        <dependencies>
```

这样做的好处就是: 整个工程的依赖都统一,如果有多个子项目都引用同一个依赖,则可以避免在每个使用的子项目里都声明一个版本号,这样当想升级或切换到另一个版本时.只需要在顶层父项目中更新,而不需要一个一个子模块去修改配置; 另外,如果某个子项目需要使用另一个版本的依赖,只需要在子模块中单独声明version就可以了.

* ==dependencyManagement 只是声明依赖,并锁定版本号,并不实现引入, 因此子项目需要显式的声明需要用到的依赖==
* ==如果不在子项目中声明依赖,是不会从父项目中继承下来的; 只有子项目中写了该依赖项,并且没有指定版本号,才会从父项目中继承该项, 并且version和scope都读取自父pom==
* 如果子项目中单独指定了自己使用到的某个依赖的版本号,则该子项目使用的依赖版本不会从父项目中继承

### dependencyManagement总结:

> dependencyManagement 只在父工程使用,并且只是声明依赖,并不实现引入
>
> 真正实现依赖引入的是 dependencies
