# 影院管理系统(TMS)软件体系结构文档

## 1. 文档修改历史

| 修改人员 | 日期      | 修改原因               | 版本号 |
| :------: | --------- | ---------------------- | ------ |
|   安皓   | 2019.4.12 | 草稿                   | 1.0    |
|   安皓   | 2019.4.20 | 增加部分业务逻辑层规范 | 1.0.1  |
|   范也 | 2019.4.20 | 增加界面和数据层规范 | 1.0.2  |
|   安皓   | 2019.4.20 | 增加模块的职责 | 1.0.3  |
|  罗民胜  | 2019.4.20 | 增加部分业务逻辑层规范 | 1.0.4 |
|  全体人员  | 2019.4.20 | 初版 | 1.1.0 |
|  安皓  | 2019.6.16 | 增加第三阶段 | 1.2.0 |
## 2. 目录

<!-- TOC -->

- [影院管理系统(TMS)软件体系结构文档](#影院管理系统tms软件体系结构文档)
    - [1. 文档修改历史](#1-文档修改历史)
    - [2. 目录](#2-目录)
    - [3. 引言](#3-引言)
        - [3.1 编制目的](#31-编制目的)
        - [3.2 词汇表](#32-词汇表)
        - [3.3 参考资料](#33-参考资料)
    - [4. 产品概述](#4-产品概述)
    - [5. 逻辑视图](#5-逻辑视图)
    - [6. 组合视图](#6-组合视图)
        - [6.1 开发包图](#61-开发包图)
        - [6.2 运行时进程](#62-运行时进程)
        - [6.3 物理部署](#63-物理部署)
    - [7. 架构设计](#7-架构设计)
        - [7.1 模块职责](#71-模块职责)
        - [7.2 用户界面层分解](#72-用户界面层分解)
            - [7.2.1 职责](#721-职责)
            - [7.2.2 接口规范](#722-接口规范)
        - [7.3 业务逻辑层分解](#73-业务逻辑层分解)
            - [7.3.1 职责](#731-职责)
            - [7.3.2 接口规范](#732-接口规范)
        - [7.4 数据层分解](#74-数据层分解)
            - [7.4.1 职责](#741-职责)
            - [7.4.2 接口规范](#742-接口规范)
        - [7.5 信息视角](#75-信息视角)
            - [7.5.1 数据持久化对象](#751-数据持久化对象)
            - [7.5.2 TXT持久化格式](#752-txt持久化格式)
            - [7.5.3 数据库表](#753-数据库表)

<!-- /TOC -->

## 3. 引言

### 3.1 编制目的

​	本报告详细完成对影院管理系统(TMS)需求阶段1、2和3的概要设计，达到详细指导设计和开发的目的，同时实现和测试人员及用户的沟通。

​	本报告面向开发人员、测试人员和最终用户而编写，是了解系统的导航。

### 3.2 词汇表

| 词汇名称 | 词汇含义                                 | 备注 |
| :------: | ---------------------------------------- | ---- |
|   TMS    | Theater Management System ，影院管理系统 | 无   |



### 3.3 参考资料

1. 《软件工程与计算(卷二)——软件开发的技术基础》
2. 《影院管理系统用例文档1.0》
3. 《影院管理系统需求规格说明1.0》
4. 《影院管理系统详细设计文档1.0》

## 4. 产品概述

​	参考影院管理系统用例文档和影院管理系统软件需求规格说明文档中对产品的概要描述。

## 5. 逻辑视图

- 处理静态设计模型

  - 影院管理系统中，选择了分层的体系结构风格，将系统分为三层(展示层、业务逻辑层和数据层)，较好地实现了对于该系统的整个抽象。展示层包含对静态网页、静态网页渲染的实现及向业务逻辑层发送请求，业务逻辑层包含对于业务逻辑的实现、处理展示层发送的请求和向数据层发送请求，数据层负责访问数据、数据的持久化和处理业务逻辑层发送的请求。

- 示意图

[![体系结构风格包图.png](https://i.loli.net/2019/04/12/5cb07da0e9836.png)](https://i.loli.net/2019/04/12/5cb07da0e9836.png)

[![体系结构逻辑设计方案.png](https://i.loli.net/2019/06/16/5d065da21965128016.png)](https://i.loli.net/2019/06/16/5d065da21965128016.png)

  

## 6. 组合视图

### 6.1 开发包图

- 表示软件组件在开发时环境中的静态组织
  - 描述开发包以及相互间的依赖
  
  - 绘制开发包图
    
    [![体系结构包图 (1).png](https://i.loli.net/2019/06/16/5d06634d90f0c17789.png)](https://i.loli.net/2019/06/16/5d06634d90f0c17789.png)
    
    - 开发包图类似上述示意图画法

| 开发包 | 依赖的其他开发包                |
| :----: | ------------------------------- |
| AdminManageUI | Promotionbl, Managebl |
| AdminStatisticUI | Statisticbl |
| UserMovieUI | Statisticbl, Salesbl, UserHomeUI |
| UserHomeUI | Managebl |
| UserUI | Promotionbl, Userbl, UserHomeUI |
| Promotionbl | VO |
| Statisticbl | VO, java.util |
| Managebl | VO |
| Salesbl | VO, javax.servlet.http, java.util |
| Userbl | VO |
| PromotionblImpl | PO, VO, PromotionMapper, org.springframework, java.sql, AlipayHandle, UserblImpl |
| StatisticblImpl | StatisticMapper, PO, VO, ManageblImpl |
| ManageblImpl | ManageMapper, PO, VO |
| SalesblImpl | SalesMapper, PO, VO, UserblImpl, ManageblImpl, PromotionblImpl, AlipayHandle, java.text, javax.servlet |
| UserblImpl | UserMapper, PO, VO |
| PromotionMapper | org.apache.ibatis, PO |
| StatisticMapper | org.apache.ibatis, PO |
| ManageMapper | org.apache.ibatis, PO |
| SalesMapper | org.apache.ibatis, PO |
| UserMapper | org.apache.ibatis, PO |
| PromotionDataMapper | mybatis.org, PO |
| StatisticDataMapper | mybatis.org, PO |
| ManageDataMapper | mybatis.org, PO |
| SalesDataMapper | mybatis.org, PO |
| UserDataMapper | mybatis.org, PO |
| VO |  |
| PO |  |
| config | org.springframework |
| alipayHandle | com.alipay.api, config |
| java.util | |
| javax.servlet.http | |
| org.springframework | |
| org.apache.ibatis | |
| java.sql | |
|  java.text | |
| com.alipay.api | |
| mybatis.org | |
| database | JDBC |


### 6.2 运行时进程

- 在TMS中，会有多个客户端进程和多个服务器端进程。其进程图如下图所示。客户端进程在客户端机器上运行，服务器端进程是在服务器上运行。

- 示意图：
  - [![进程图.png](https://i.loli.net/2019/04/20/5cbb104b3d618.png)](https://i.loli.net/2019/04/20/5cbb104b3d618.png)

### 6.3 物理部署

- TMS中客户端浏览器通过HTTP协议向服务器端请求数据

- 示意图

  - [![部署图.png](https://i.loli.net/2019/04/20/5cbb0a6369353.png)](https://i.loli.net/2019/04/20/5cbb0a6369353.png)

## 7. 架构设计

客户端模块和服务器端模块如下图所示。客户端各层和服务器端各层的职责见图后表。

### 7.1 模块职责

- 模块视图
- [![客户端模块图.png](https://i.loli.net/2019/04/20/5cbaeca515cb9.png)](https://i.loli.net/2019/04/20/5cbaeca515cb9.png)
- [![服务器端模块图.png](https://i.loli.net/2019/04/20/5cbaeca52efcd.png)](https://i.loli.net/2019/04/20/5cbaeca52efcd.png)
- 客户端各层职责

|     层     | 职责     |
| :--------: | -------- |
| 启动模块 | 负责初始化网络通信机制，启动用户界面 |
| 用户界面层 | 基于图形用户界面的影院客户端用户界面 |
| 客户端网络模块 | 通过HTTP协议和服务器端通信 |

- 服务器端各层职责

|     层     | 职责     |
| :--------: | -------- |
| 启动模块 | 负责初始化网络通信机制，启动用户界面 |
| 业务逻辑层 | 负责响应用户请求并对其进行逻辑处理，并将处理后的数据发送给网络模块 |
| 数据层 | 负责数据的持久化和向业务逻辑层传递数据 |
| 服务器端网络模块 | 通过HTTP协议和客户端通信 |

​	每一层只是使用下方直接接触的层，层与层之间的调用是通过接口的调用来完成的。层与层之间的接口调用见下图。

- 层之间调用接口

<table border="1">
  <tr>
    <td>接口</td>
    <td>服务调用方</td>
    <td>服务提供方</td>
  </tr>
  <tr>
    <td>promotionbl</td>
    <td rowspan="5">客户端展示层</td>
    <td rowspan="5">服务器端业务逻辑层</td>
  </tr>
  <tr>
    <td>salesbl</td>
  </tr>
  <tr>
    <td>managementbl</td>
  </tr>
  <tr>
    <td>statisticbl</td>
  </tr>
  <tr>
    <td>userbl</td>
  </tr>
  <tr>
    <td>promotionMapper</td>
    <td rowspan="5">服务器端业务逻辑层</td>
    <td rowspan="5">服务器端数据层</td>
  </tr>
  <tr>
    <td>salesMapper</td>
  </tr>
  <tr>
    <td>managementMapper</td>
  </tr>
  <tr>
    <td>statisticMapper</td>
  </tr>
  <tr>
    <td>userMapper</td>
  </tr>
</table>

​	借用想看电影用例来说明层之间的调用。如下图所示。每一层之间都是由上层依赖了一个接口（需接口），而下层来实现这个接口（供接口）。movielikeblservice提供了movielike界面所需要的所有业务逻辑。movielikedataservice提供了对数据库的增、删、查等操作。这样的实现就大大降低了层与层之间的耦合。

[![想看电影用例之间调用的接口.png](https://i.loli.net/2019/04/20/5cbafdf564905.png)](https://i.loli.net/2019/04/20/5cbafdf564905.png)

### 7.2 用户界面层分解
根据需求，系统提供14个界面：登录界面、注册界面、电影票界面、会员卡界面、观众主界面、电影详情界面、历史记录界面、电影列表界面、管理排片界面、管理影院界面、管理电影界面、统计界面、管理活动界面、管理退票策略界面

界面跳转如图所示
[![用户界面跳转.png](https://i.loli.net/2019/06/17/5d06f8847a3ac18354.png)](https://i.loli.net/2019/06/17/5d06f8847a3ac18354.png)

<center><b>用户界面跳转</b></center>

#### 7.2.1 职责
界面实现采用HTML技术，为每个界面构建一个html文档，包括对应的css和js文件。利用ajax技术与逻辑层进行通信。



#### 7.2.2 接口规范


<center><b>用户界面层模块的接口规范</b></center>
因为应用html、 css 、js技术，所以界面层模块不需要调用，不需要前置条件。用户打开任何一个界面的地址，界面自动显示。
<hr/>

<table border="1">
  <tr>
    <td rowspan="3">Loginui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示登录界面</td>
  </tr><tr>
    <td rowspan="3">Registerui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示注册界面</td>
  </tr><tr>
    <td rowspan="3">Ticketui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已登录，且用户为观众类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示电影票界面</td>
  </tr> <tr>
    <td rowspan="3">VIPCardui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已登录，且用户为观众类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示会员卡界面</td>
  </tr> <tr>
    <td rowspan="3">Audienceui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已登录，且用户为观众类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示观众主界面</td>
  </tr>
  <tr>
    <td rowspan="3">Historyui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为观众类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示用户消费历史记录界面</td>
  </tr>
    <tr>
    <td rowspan="3">MovieListui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为观众类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示电影列表界面</td>
  </tr>
    <tr>
    <td rowspan="3">MovieDetailui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登陆，且用户为观众类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示电影详情界面</td>
  </tr>
      <tr>
    <td rowspan="3">ArrangeScheduleui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为员工类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示排片界面</td>
  </tr>
  <tr>
    <td rowspan="3">Statisticui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为员工类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示统计界面</td>
  </tr>
  <tr>
    <td rowspan="3">ArrangeMovieui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为员工类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示电影管理界面</td>
  </tr>
  <tr>
    <td rowspan="3">ArrangeHallui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为员工类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示影厅管理界面</td>
  </tr>
  <tr>
    <td rowspan="3">ArrangeActivityui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为员工类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示活动管理界面</td>
  </tr>
  <tr>
    <td rowspan="3">ArrangeRefundui</td>
    <td>语法</td>
    <td>无</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户已经登录，且用户为员工类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>显示退票策略管理界面</td>
  </tr>
</table>

#### 7.2.3 需要的服务接口

|         服务名         | 服务                   |
| :--------------------: | ---------------------- |
| bl.*bl | 每个界面都需要对应的业务逻辑接口 |



### 7.3 业务逻辑层分解

​	业务逻辑层包括多个针对界面的业务逻辑处理对象。比如，user对象负责登录界面、历史记录界面的相关逻辑，statistics对象用于处理统计界面和电影详情界面的相关逻辑。业务逻辑层的设计如图所示。

[![业务逻辑层的设计.png](https://i.loli.net/2019/06/17/5d0719f4e696d29408.png)](https://i.loli.net/2019/06/17/5d0719f4e696d29408.png)

#### 7.3.1 职责
<center>业务逻辑层模块的职责</center>

| 模块 | 职责             |
| -------- | :----- |
|   userbl   | 负责实现对应登录界面、注册界面、历史记录界面所需要的服务 |
|   promotionbl   | 负责实现活动管理界面、会员卡界面所需要的服务 |
|   salesbl   | 负责实现电影详情界面、电影票界面所需要的服务 |
|   statisticbl   | 负责实现统计界面、电影详情界面所需要的服务 |
|   managementbl   | 负责实现电影列表界面、观众界面、排片界面、管理影院界面、管理电影界面、管理活动界面、管理退票策略界面所需要的服务 |


#### 7.3.2 接口规范

<center><b>userbl模块的接口规范</b></center>

<hr/>

<center><b>提供的服务</b></center>

<hr/>

<table border="1">
  <tr>
    <td rowspan="3">userbl.AccountService.registerAccount</td>
    <td>语法</td>
    <td>public ResponseVO registerAccount(UserForm userForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>id,password符合数据格式要求</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查找是否存在相应的User，根据其结果返回注册验证的结果</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">userbl.AccountService.login</td>
    <td>语法</td>
    <td>public ResponseVO login(UserForm userForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>id,password符合数据格式要求</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查找是否存在user及用户名密码是否配对，根据其结果返回登录验证的结果</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">userbl.HistoryService.getAllHistory</td>
    <td>语法</td>
    <td>public ResponseVO getAllHistory(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查找是否存在相应的User，根据其结果返回用户的历史记录</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">userbl.HistoryService.getAllHistory</td>
    <td>语法</td>
    <td>public ResponseVO getItemHistory(int historyId,String type)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查找是否存在相应的历史记录，根据其结果返回历史记录的具体信息，如果是买票的历史记录，会返回ticketId和movieName，如果是充值的历史记录，会返回充值后的会员卡余额</td>
  </tr>
</table>



<hr/>

<center><b>需要的服务（需要接口）</b></center>

<hr/>



| 服务名 |    服务     |
| :----: | ---------- |
| data.user.AccountMapper.createNewAccount(UserForm userForm) | 在数据库中创建UserPO对象 |
| data.user.AccountMapper.getAccountByName(String name) | 在数据库中根据用户名查找UserPO对象 |
| data.user.AccountMapper.getAccountById(int userId) | 在数据库中根据用户ID查找UserPO对象 |
| data.user.AccountMapper.selectVIPUserBySpendAmount(double amount) | 在数据库中根据用户消费金额查找UserPO对象 |
| data.user.HistpryMapper.selectAllHistory(int userId) | 在数据库中根据用户ID查找用户所有的消费记录 |
| data.user.HistpryMapper.selectTicket(int historyId) | 在数据库中根据历史ID查找当前历史下的所有购票记录 |
| data.user.HistpryMapper.selectChargeBalance(int historyId) | 在数据库中根据历史ID查找当前历史下的所有充值记录 |
| data.user.HistpryMapper.insertHistory(History history) | 在数据库中插入一条新的记录 |
| data.user.HistpryMapper.insertHistoryCharge(historyId,balance) | 在数据库中插入一条新的充值记录 |
| data.user.HistpryMapper.insertHistoryMovie(historyId,movieName,ticketId) | 在数据库中插入一条新的购票记录 |

<hr/>

<center><b>statisticsbl模块的接口规范</b></center>

<hr/>

<center><b>提供的服务</b></center>



<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.MovieLikeService.likeMovie</td>
    <td>语法</td>
    <td>public ResponseVO likeMovie(int userId, int movieId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是观众类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>插入用户的想看记录，该电影的想看人数增加</td>
  </tr>
</table>



<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.MovieLikeService.unlikeMovie</td>
    <td>语法</td>
    <td>public ResponseVO unLikeMovie(int userId,int movieId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是观众类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除用户的想看记录，该电影的想看人数增加</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.MovieLikeService.getCountOfLikes</td>
    <td>语法</td>
    <td>public ResponseVO getCountOfLikes(int movieId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录；电影ID在数据库中存在</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回当前电影的想看人数</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.MovieLikeService.getLikeNumsGroupByDate</td>
    <td>语法</td>
    <td>public ResponseVO getLikeNumsGroupByDate(int movieId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录；电影ID在数据库中存在</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回电影每日的想看人数</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.StatisticsService.getScheduleRateByDate</td>
    <td>语法</td>
    <td>public ResponseVO getScheduleRateByDate(Date date)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回某日各影片排片率统计数据</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.StatisticsService.getTotalBoxOffice</td>
    <td>语法</td>
    <td>public ResponseVO getTotalBoxOffice()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回获取所有电影的累计票房,包含已下架的电影</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.StatisticsService.getAudiencePriceSevenDays</td>
    <td>语法</td>
    <td>public ResponseVO getAudiencePriceSevenDays()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回过去7天内每天客单价，对客单价的说明参见《TMS需求规格说明》数据需求</td>
  </tr>
</table>


<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.StatisticsService.getMoviePlacingRateByDate</td>
    <td>语法</td>
    <td>public ResponseVO getMoviePlacingRateByDate(Date date)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回所有电影某天的上座率，对上座率的说明参见《TMS需求规格说明》数据需求</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">statisticsbl.StatisticsService.getPopularMovies</td>
    <td>语法</td>
    <td>public ResponseVO getPopularMovies(int days, int movieNum)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是员工类型，且用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回最近days天内，最受欢迎的movieNum个电影，对受欢迎的评估标准的说明参见《TMS需求规格说明》数据需求</td>
  </tr>
</table>




<hr/>

<center><b>需要的服务（需要接口）</b></center>



| 服务名 |    服务     |
| :----: | ---------- |
| blImpl.management.schedule.MovieServiceForBl.getMovieById(movieId) | 通过电影ID查找电影 |
| data.statistics.MovieLikeMapper.insertOneLike(int movieId ,int userId) | 在数据库中插入一条想看记录 |
| data.statistics.MovieLikeMapper.deleteOneLike(int movieId ,int userId) | 在数据库中删除一条想看记录 |
| data.statistics.MovieLikeMapper.selectLikeNums(int movieId) | 根据movieId查找电影的想看人数 |
| data.statistics.MovieLikeMapper.selectLikeMovie(int movieId ,int userId) | 根据movieId和userId查找用户的想看记录 |
| data.statistics.MovieLikeMapper.getDateLikeNum(int movieId) | 获得某个电影的喜爱的人数按日期统计 |
| data.statistics.StatisticsMapper.selectMovieScheduleTimes(Date date, Date nextDate) | 查询date日期每部电影的排片次数 |
| data.statistics.StatisticsMapper.selectMovieTotalBoxOffice() | 查询所有电影的总票房（包括已经下架的，降序排列） |
| data.statistics.StatisticsMapper.selectB2StartAndEnd( Date startDate, Date endDate) | 查询在指定时间区间内所有电影的总票房（降序排列） |
| data.statistics.StatisticsMapper.selectAudiencePrice(Date date,Date nextDate) | 查询某天每个客户的购票金额 |
| data.statistics.StatisticsMapper.selectTicketNumByScheduleId(int scheduleId); | 查询某个排片下订单成功的订单数目 |
| data.statistics.StatisticsMapper.selectSimpleScheduleItemByDate( Date requireDate, Date nextDate) | 查询特定日期的排片信息（按电影的ID）排序 |

<hr/>

<center><b>salesbl模块的接口规范</b></center>

<hr/>

<center><b>提供的服务</b></center>



<table border="1">
  <tr>
    <td rowspan="3">salesbl.PayService.getOrderTypeByID(int orderId)</td>
    <td>语法</td>
    <td>public ResponseVO getOrderTypeByID(int orderId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回查找订单类型</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.addTicket</td>
    <td>语法</td>
    <td>public ResponseVO addTicket(TicketForm ticketForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>Ticket的列和行没有重复</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>插入一条新的ticket</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.generateOrder</td>
    <td>语法</td>
    <td>public ResponseVo generateOrder(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>生成订单</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.getBySchedule</td>
    <td>语法</td>
    <td>public ResponseVO getBySchedule(int scheduleId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>获得该场次的被锁座位和场次信息</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.getTicketByUser</td>
    <td>语法</td>
    <td>public void getTicketByUser(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>获得用户买过的票</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.completeByVIPCard</td>
    <td>语法</td>
    <td>public ResponseVO completeByVIPCard(List<Integer> id, int couponId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>ticket已经被add</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成购票,流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.cancelTicket</td>
    <td>语法</td>
    <td>public ResponseVO cancelTicket(List<Integer> id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>ticket存在</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除订票</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.getCanRefund</td>
    <td>语法</td>
    <td>public ResponseVO getCanRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回一个数组对应ticket能否退票【1能  0不能】</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.getRefund</td>
    <td>语法</td>
    <td>public ResponseVO getRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>电影票能退票</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回用户退票后应该收取的手续费</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.completeRefund</td>
    <td>语法</td>
    <td>public ResponseVO completeRefund(List<Integer> ticketId, double refund)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>电影票能退</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成退票   需要根据用户使用的支付方式【1使用vip_card支付， 2使用支付宝支付】 退钱</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.makePayRequest</td>
    <td>语法</td>
    <td>public ResponseVO makePayRequest(List<Integer> id, int couponId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>向支付宝发起支付请求并跳转网页</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.issueTicket</td>
    <td>语法</td>
    <td>public ResponseVO issueTicket(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>购票已完成且票未出票</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>出票</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">salesbl.TicketService.mentionCompleteByAli</td>
    <td>语法</td>
    <td>public void mentionCompleteByAli(HttpServletRequest request);</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>已向支付宝发起支付请求且订单状态改变</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>更新订单和票的状态</td>
  </tr>
</table>


<hr/>

<center><b>需要的服务（需要接口）</b></center>



| 服务名 |    服务     |
| :----: | ---------- |
| blImpl.promotion.vip.PayServiceForBl.insertOrder | 插入新订单 |
| blImpl.management.hall.HallServiceForBl.getHallById | 通过ID查找Hall |
| blImpl.user.HistoryServiceForBl.insertTicketHistory | 插入购票历史 |
| blImpl.management.schedule.ScheduleServiceForBl.getScheduleItemById | 通过ID查找排片 |
| blImpl.promotion.activity.CouponServiceForBl.checkCoupon | 检查优惠券是否在使用时间内 |
| blImpl.promotion.activity.CouponServiceForBl.giveCoupon | 向用户赠送当前日期内可赠送的优惠券 |
| blImpl.promotion.activity.CouponServiceForBl.deleteCoupon | 删除用户的优惠券 |
| blImpl.promotion.activity.CouponServiceForBl.getCouponsInDate | 查找当前日期内能用的优惠券 |
| blImpl.promotion.refundStrategy.RefundStrategyServiceForBl.getAllRefundStrategy | 查找当前所有的退票策略 |
| blImpl.promotion.vip.VIPServiceForBl.getCardByUserId | 根据用户ID查找对应的VIPCard |
| blImpl.promotion.vip.VIPServiceForBl.updateBalance | 更新会员卡余额 |
| data.sales.PayMapper.insertOrder | 在数据库中插入新订单 |
| data.sales.PayMapper.insertTicketsInOrder | 在数据库中插入订单-票的映射 |
| data.sales.PayMapper.updateOrderStatus | 更新订单状态 |
| data.sales.PayMapper.selectOrderByID | 通过ID查找订单 |
| data.sales.TicketMapper.selectTicketByScheduleIdAndSeat | 通过排片ID和座位号查找票 |
| data.sales.TicketMapper.insertTicket | 在数据库中插入一张票 |
| data.sales.TicketMapper.insertTickets | 在数据库中插入多张票 |
| data.sales.TicketMapper.deleteTicket | 在数据库中删除票 |
| data.sales.TicketMapper.updateTicketState | 更新票的状态 |
| data.sales.TicketMapper.selectTicketsBySchedule | 查找当前电影排片中的所有票 |
| data.sales.TicketMapper.selectTicketById | 通过ID查找票                                   |
| data.sales.TicketMapper.selectTicketByUser | 通过用户ID查找用户持有的票 |
| data.sales.TicketMapper.selectTicketByOrder | 通过订单号查找订单内的票 |
| data.sales.TicketMapper.updateTicketingState | 更新出票状态 |
| data.sales.TicketMapper.updateUseCoupon | 更新票是否使用了优惠券的状态 |
| data.sales.TicketMapper.cleanExpiredTicket | 将已买票但15分钟后仍未支付的票的状态设为已失效 |

<hr/>

<center><b>promotionbl</b>模块的接口规范</center>
<hr/>

<center><b>提供的服务</b></center>



<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.ActivityService.publishActivity</td>
    <td>语法</td>
    <td>public ResponseVO publishActivity(ActivityForm activityForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工已经登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>添加新的活动，活动格式参考《TMS需求规格说明》数据格式要求</td>
  </tr>
</table>


<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.ActivityService.getActivities</td>
    <td>语法</td>
    <td>public ResponseVO getActivities()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工已经登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回所有活动，活动格式参考《TMS需求规格说明》数据格式要求</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.CouponService.getCouponsByUser</td>
    <td>语法</td>
    <td>public ResponseVO getCouponsByUser(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回观众用户所有的优惠券</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.CouponService.addCoupon</td>
    <td>语法</td>
    <td>public ResponseVO addCoupon(CouponForm couponForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工已经登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>发布新的优惠券</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.CouponService.issueCoupon</td>
    <td>语法</td>
    <td>public ResponseVO issueCoupon(int couponId,int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工已经登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>向单个观众赠送优惠券</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.CouponService.issueCoupons</td>
    <td>语法</td>
    <td>public ResponseVO issueCoupons(int couponId,List<Integer>userIds)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工已经登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>向多个用户赠送优惠券</td>
  </tr>
</table>


<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.CouponService.getVIPBySpend</td>
    <td>语法</td>
    <td>public ResponseVO getVIPBySpend(double spend)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工已经登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回超过指定消费金额的VIP观众的列表</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.addVIPCard</td>
    <td>语法</td>
    <td>public ResponseVO addVIPCard(int userId,double fare)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>已经完成购买会员卡的支付</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>添加VIP用户</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.getCardById</td>
    <td>语法</td>
    <td>public ResponseVO getCardById(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回一个VIPCardVO</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.getVIPInfo</td>
    <td>语法</td>
    <td>public ResponseVO getVIPInfo()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是VIP</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回用户的VIP卡信息</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.charge</td>
    <td>语法</td>
    <td>public ResponseVO charge(VIPCardForm vipCardForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>用户是VIP用户，用户已经完成会员卡充值的支付流程</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>给用户的会员卡充值</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.getCardByUserId</td>
    <td>语法</td>
    <td>public ResponseVO getCardByUserId(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据用户ID返回其会员卡信息</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.upgradeCard</td>
    <td>语法</td>
    <td>public ResponseVO upgradeCard(int userId,double fare)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>已经完成升级会员卡的支付</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>将用户的会员卡进行升级</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.makeChargeRequest</td>
    <td>语法</td>
    <td>public ResponseVO makeChargeRequest(VIPCardForm vipCardForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>向支付宝发起充值会员卡请求</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.makeBuyCardRequest</td>
    <td>语法</td>
    <td>public ResponseVO makeBuyCardRequest(int userId,double fare)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>向支付宝发起买卡的支付请求</td>
  </tr>
</table>


<table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPService.makeUpgradeCardRequest</td>
    <td>语法</td>
    <td>public ResponseVO makeUpgradeCardRequest(int userId,double fare)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>向支付宝发起升级卡的支付请求</td>
  </tr>

  <table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPStrategyService.publishVIPStrategy</td>
    <td>语法</td>
    <td>public ResponseVO publishVIPStrategy(List<CardItemForm> cardItemFormList)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除原有的VIP优惠策略，发布新的策略</td>
  </tr>
  <table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPStrategyService.getAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO getAllStrategy()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回当前的VIP优惠策略</td>
  </tr>

  <table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPStrategyService.deleteStrategyByID</td>
    <td>语法</td>
    <td>public ResponseVO deleteStrategyByID(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除单个策略</td>
  </tr>

  <table border="1">
  <tr>
    <td rowspan="3">bl.promotion.VIPStrategyService.changeStrategy</td>
    <td>语法</td>
    <td>public ResponseVO changeStrategy(int id,CardItemForm cardItemForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>修改单个策略</td>
  </tr>
</table>




<hr/>

<center><b>需要的服务（需要接口）</b></center>



| 服务名 |    服务     |
| :----: | ---------- |
| blImpl.user.AccountServiceForBl..getVIPUserListBySpendAmount | 根据VIP用户消费总金额在数据库中查找超过金额数的所有用户 |
| blImpl.alipayHandle.AliPayHandle.executePayRequest | 执行alipay请求 |
| blImpl.user.HistoryServiceForBl.insertCardHistory | 插入购卡历史 |
| blImpl.user.HistoryServiceForBl.insertChargeHistory | 插入充值历史 |
| data.promotion.ActivityMapper.insertActivity | 在数据库中插入新的活动 |
| data.promotion.ActivityMapper.insertActivityAndMovie | 在数据库中插入新活动和参加活动电影的映射 |
| data.promotion.ActivityMapper.selectById | 在数据库中通过ID查找活动 |
| data.promotion.ActivityMapper.selectActivities | 在数据库中查找所有活动 |
| data.promotion.CouponMapper.selectCouponByUser | 在数据库中通过用户ID查找用户持有优惠券 |
| data.promotion.CouponMapper.selectProperCoupon | 在数据库中查找能够作用于当前排片的优惠券 |
| data.promotion.CouponMapper.insertCouponUser | 在数据库中插入优惠券和用户的映射 |
| data.promotion.CouponMapper.deleteCouponUser | 在数据库中删除优惠券和用户的映射 |
| data.promotion.RefundStrgMapper.selectAllStrategy | 在数据库中查找所有退票策略 |
| data.promotion.RefundStrgMapper.deleteAllStrategy() | 在数据库中删除所有退票策略 |
| data.promotion.RefundStrgMapper.insertStrategy | 在数据库中插入一条退票策略 |
| data.promotion.VIPCardMapper.insertOneCard | 在数据库中插入一张会员卡 |
| data.promotion.VIPCardMapper.selectCardById | 在数据库中通过ID查找会员卡 |
| data.promotion.VIPCardMapper.updateVipType | 升级用户会员卡类型 |
| data.promotion.VIPCardMapper.updateCardBalance | 更新用户会员卡余额 |
| data.promotion.VIPCardMapper.selectCardByUserId | 通过用户ID查找用户持有的会员卡 |
| data.promotion.VIPStrgMapper.insertVIPStrategy | 在数据库中插入多条VIP优惠策略 |
| data.promotion.VIPStrgMapper.selectAllStrategy | 在数据库中查找所有的会员卡优惠策略 |
| data.promotion.VIPStrgMapper.deleteAllStrategies | 在数据库中删除所有VIP优惠策略 |
| data.promotion.VIPStrgMapper.deleteStrategyByID | 在数据库中通过ID删除单条VIP优惠策略 |
| data.promotion.VIPStrgMapper.updateStrategy | 在数据库中通过ID更新单条VIP优惠策略 |
<hr/>

<center><b>managementbl模块的接口规范</b></center>
<hr/>

<center><b>提供的服务</b></center>



<table border="1">
  <tr>
    <td rowspan="3">bl.management.HallService.searchAllHall</td>
    <td>语法</td>
    <td>public ResponseVO searchAllHall()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回所有影厅及其信息，关于影厅信息，参考《TMS需求规格说明》数据格式需求</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.HallService.searchHall</td>
    <td>语法</td>
    <td>public ResponseVO searchHall(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据id搜索影厅并返回，关于影厅信息，参考《TMS需求规格说明》数据格式需求</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.HallService.addHall</td>
    <td>语法</td>
    <td>public ResponseVO addHall(HallFormVO hallFormVO)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>添加单个影厅</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.HallService.deleteHall</td>
    <td>语法</td>
    <td>public ResponseVO deleteHall(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除单个影厅</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.HallService.changeHallInfo</td>
    <td>语法</td>
    <td>public ResponseVO changeHallInfo(int id,HallFormVO hallFormVO)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>更改单个影厅信息</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.addMovie</td>
    <td>语法</td>
    <td>public ResponseVO addMovie(MovieForm addMovieForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>上架电影</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.searchOneMovieByIdAndUserId</td>
    <td>语法</td>
    <td>public ResponseVO searchOneMovieByIdAndUserId(int id, int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据id和userid搜索电影，可以知道这个用户是否点过想看这部电影</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.searchAllMovie</td>
    <td>语法</td>
    <td>public ResponseVO searchAllMovie()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>搜索全部电影</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.searchAllOrderByLikeNum</td>
    <td>语法</td>
    <td>public ResponseVO searchAllOrderByLikeNum(int num)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回指定数目的电影，根据喜爱人数排序</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.searchAllOutMovie</td>
    <td>语法</td>
    <td>public ResponseVO searchAllOutMovie(int num)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回指定数目尚未上映电影</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.searchOtherMoviesExcludeOff</td>
    <td>语法</td>
    <td>public ResponseVO searchOtherMoviesExcludeOff()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>搜索全部电影(不包括已经下架的)</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.getMovieByKeyword</td>
    <td>语法</td>
    <td>public ResponseVO getMovieByKeyword(String keyword)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据关键字搜索电影</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.pullOfBatchOfMovie</td>
    <td>语法</td>
    <td>public ResponseVO pullOfBatchOfMovie(MovieBatchOffForm movieBatchOffForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>批量下架电影</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.updateMovie</td>
    <td>语法</td>
    <td>public ResponseVO updateMovie(MovieForm updateMovieForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>更新电影信息</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.MovieService.deleteMovie</td>
    <td>语法</td>
    <td>public ResponseVO deleteMovie(List<Integer> movieIds)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除电影</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.management.RoleService.addRole</td>
    <td>语法</td>
    <td>public ResponseVO addRole(RoleForm roleForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>添加一个新的角色</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.management.RoleService.deleteRoleByUsername</td>
    <td>语法</td>
    <td>public ResponseVO deleteRoleByUsername(String userName)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>通过用户名删除用户</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.RoleService.searchAllManagerAndStaff</td>
    <td>语法</td>
    <td>public ResponseVO searchAllManagerAndStaff()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查询所有管理员和职工，先显示管理员再显示员工，按用户名顺序排序</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.RoleService.searchUserByUserName</td>
    <td>语法</td>
    <td>public ResponseVO searchUserByUserName(String userName)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据用户名查找用户</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.RoleService.searchUserById</td>
    <td>语法</td>
    <td>public ResponseVO searchUserById(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据用户Id查找用户</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.RoleService.changeUserType</td>
    <td>语法</td>
    <td>public ResponseVO changeUserType(int userId,int userType)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>将对应user的用户类型进行更改</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.addSchedule</td>
    <td>语法</td>
    <td>public ResponseVO addSchedule(ScheduleForm scheduleForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>添加排片信息，对于排片信息具体内容的描述见《TMS需求规格说明文档》</td>
  </tr>
</table>
<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.searchScheduleSevenDays</td>
    <td>语法</td>
    <td>public ResponseVO searchScheduleSevenDays(int hallId, Date startDate)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查询包括从起始时间开始的7天排片计划</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.setScheduleView</td>
    <td>语法</td>
    <td>public ResponseVO setScheduleView(ScheduleViewForm scheduleViewForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>设置排片对观众的可见的天数</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.deleteBatchOfSchedule</td>
    <td>语法</td>
    <td>public ResponseVO deleteBatchOfSchedule(ScheduleBatchDeleteForm scheduleBatchDeleteForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>批量删除排片信息</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.updateSchedule</td>
    <td>语法</td>
    <td>public ResponseVO updateSchedule(ScheduleForm scheduleForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>修改排片信息</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.getScheduleById</td>
    <td>语法</td>
    <td>public ResponseVO getScheduleById(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据id获取schedule</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.getScheduleView</td>
    <td>语法</td>
    <td>public ResponseVO getScheduleView()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查询排片对观众的可见的天数</td>
  </tr>
</table>

<table border="1">
  <tr>
    <td rowspan="3">bl.management.ScheduleService.searchAudienceSchedule</td>
    <td>语法</td>
    <td>public ResponseVO searchAudienceSchedule(int movieId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>影院员工登录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>查询观众看到的排片信息</td>
  </tr>
</table>



<hr/>

<center><b>需要的服务（需要接口）</b></center>



| 服务名 |    服务     |
| :----: | ---------- |
| data.management.HallMapper.selectHallById | 在数据库中通过ID查找影厅 |
| data.management.HallMapper.insertHall | 在数据库中插入影厅 |
| data.management.HallMapper.deleteHall | 在数据库中删除影厅 |
| data.management.HallMapper.updateHall | 在数据库中更新影厅 |
| data.management.MovieMapper.selectMovieByIdAndUserId | 根据id和userId查找电影 |
| data.management.MovieMapper.selectAllMovie | 展示所有电影 |
| data.management.MovieMapper.selectOtherMoviesExcludeOff | 展示所有电影(不包括已经下架的) |
| data.management.MovieMapper.selectMovieByKeyword | 根据关键字搜索电影 |
| data.management.MovieMapper.updateMovieStatusBatch | 批量更新电影状态 |
| data.management.MovieMapper.updateMovie | 修改电影 |
| data.management.MovieMapper.deleteMovie | 删除电影 |
| data.management.MovieMapper.selectMovieById | 根据id查找电影 |
| data.management.MovieMapper.selectMovieOrderByLikeNum | 展示指定数目电影并根据想看人数排序 |
| data.management.RoleMapper.insertUser | 插入一个新的用户 |
| data.management.RoleMapper.deleteUser | 通过用户名删除用户 |
| data.management.RoleMapper.selectAllManagerAndStaff | 查找所有管理员和员工 |
| data.management.RoleMapper.selectUserByName | 通过用户名查找用户 |
| data.management.RoleMapper.selectUserByID | 通过用户的ID查找用户 |
| data.management.RoleMapper.updateUserType | 更改用户类型 |
| data.management.ScheduleMapper.insertOneSchedule | 插入一条排片信息 |
| data.management.ScheduleMapper.updateScheduleById | 根据id修改排片信息 |
| data.management.ScheduleMapper.selectScheduleById | 根据id查找排片信息 |
| data.management.ScheduleMapper.selectView | 查询排片限制信息 |
| data.management.ScheduleMapper.selectScheduleByMovieId | 查询movieId的所有排片信息 |
| data.management.ScheduleMapper.selectScheduleByMovieIdList | 查询所有涉及到movieIdList中电影的排片信息 |
| data.management.ScheduleMapper.selectSchedule | 查询从startDate开始到endDate为止的某hall的排片信息 |
| data.management.ScheduleMapper.selectViewCount | 查询view的记录数，以此判断后续操作是插入还是修改 |
| data.management.ScheduleMapper.insertOneView | 插入观众可见排片限制 |
| data.management.ScheduleMapper.updateOneView | 修改观众可见排片限制 |
| data.management.ScheduleMapper.deleteScheduleBatch | 批量删除排片信息 |
| data.management.ScheduleMapper.selectScheduleConflictByHallIdAndTime | 查询起止时间是否有冲突(不包括与自身的冲突) |
| data.management.ScheduleMapper.selectScheduleBatch | 批量查询排片信息 |
| data.management.ScheduleMapper.selectScheduleByHallId | 根据hall_id查找对应影厅下的当前排片 |

<hr/>
### 7.4 数据层分解

数据层主要给业务逻辑层提供数据访问服务，包括对于持久化数据的增、删、改、查。业务逻辑需要的服务由对应的Mapper接口提供（比如，movielikeblImpl需要的服务由movielikeMapper接口提供），持久化数据的保存利用数据库，下图所示抽象了数据服务。

[![数据层接口.png](https://i.loli.net/2019/06/17/5d074631bca3d92688.png)](https://i.loli.net/2019/06/17/5d074631bca3d92688.png)

<center>图 数据层接口示意</center>

#### 7.4.1 职责

|    模块     | 职责                                                         |
| :---------: | ------------------------------------------------------------ |
|   *Mapper   | 持久化数据库的接口，提供集体载入、集体保存，具体的增删改查功能提供见上图 |
| *MapperImpl | 基于Mysql数据库的持久化数据库的接口，提供集体载入、集体保存，具体的增删改查功能提供见上图 |

#### 7.4.2 接口规范

<center><b>数据层模块的接口规范<b></center>
<hr>
<table border="1">
  <tr>
    <td rowspan="3">*DataService.select</td>
    <td>语法</td>
    <td>public *PO select(int id) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据ID查找并返回相应的PO结果</td>
  </tr>
  <tr>
    <td rowspan="3">*DataService.selectAll</td>
    <td>语法</td>
    <td>public List<*PO> selectAll() throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>无</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据ID查找并返回相应的List<PO>结果</td>
  </tr>
      <tr>
    <td rowspan="3">*DataService.insert*PO</td>
    <td>语法</td>
    <td>public int insert*PO(*PO po) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>同样的ID的po在Mapper中不存在</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>在数据库中加入一个po记录</td>
  </tr>
  <tr>
    <td rowspan="3">*DataService.insert*POs</td>
    <td>语法</td>
    <td>public int insert*POs(List<*PO> pos) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>同样的ID的po在Mapper中不存在</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>在数据库中加入多个po记录</td>
  </tr>
    <tr>
    <td rowspan="3">*DataService.delete*PO</td>
    <td>语法</td>
    <td>public void delete*PO(*PO po) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>在数据库中存在相同id的PO</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除一个PO</td>
  </tr>
  <tr>
    <td rowspan="3">*DataService.delete*POs</td>
    <td>语法</td>
    <td>public void delete*POs(List<*PO> pos) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>在数据库中存在相同id的PO</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>删除多个PO</td>
  </tr>
  <tr>
    <td rowspan="3">*DataService.update*PO</td>
    <td>语法</td>
    <td>public void update*PO(*PO po) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>在数据库中存在相同id的PO</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>更新一个PO</td>
  </tr>
  <tr>
    <td rowspan="3">*DataService.update*POs</td>
    <td>语法</td>
    <td>public void update*POs(List<*PO> pos) throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>在数据库中存在相同id的PO</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>更新多个PO</td>
  </tr>
  <tr>
    <td rowspan="3">*DataService.clean</td>
    <td>语法</td>
    <td>public void clean() throws Exception</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>到达指定时间</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>更新数据库</td>
  </tr>
</table>

*可以替换为Hall, Movie, Role, Schedule, Activity, Coupon, RefundStrategy, VIPCard, VIPStrategy, Pay, Ticket, MovieLike, Statistics, Account, History，具体方法提供参见图"数据层接口示意”。

### 7.5 信息视角

#### 7.5.1 数据持久化对象

|对象|属性|
|-|-|
| ActivityPO | id，活动名称，活动描述，活动开始时间，活动截止时间，优惠电影列表，优惠券规格 |
| AudiencePricePO | 用户id，消费的总价钱 |
| CardItemPO | id，门槛金额，优惠金额 |
| ChargeItemPO | id，充值金额，用户id，充值时间 |
| CouponPO | id，优惠券名称，优惠券描述，优惠券使用门槛，优惠券优惠金额，可用时间，失效时间 |
| DatelikePO | 喜爱人数，喜爱时间 |
| HallPO | id，名字，列数，行数 |
| HistoryPO | id，支付方式，完成支付时间，消费金额，消费类型，用户id |
| HistoryMoviePO | 历史id，电影名，电影票id |
| MoviePO | id，名字，海报url，编剧，主演，类型，制片国家/地区，语言，上映时间，上映时间，下架时间 |
| MovieScheduleTimePO | 电影id，排片次数，电影名字 |
| MovieTotalBoxOfficePO | 电影id，票房，电影名 |
| OrderPO | id，订单名，订单内容，订单价格，订单状态 |
| RefundStrgPO | 距离电影开始播放时间，手续费比例，|
| ScheduleItemPO | id，影厅id，影厅名称，电影id，电影名，开始放映时间，结束放映时间，票价|
| SimpleScheduleItemPO | id，影厅id，电影id，电影名 |
| TicketPO | id，用户id，排片id，列号，行号 |
| UserPO | id，用户名，密码，用户头像URL，用户类型，邮箱地址|
| VIPCardPO | 价格，描述，用户id，会员卡id，会员卡余额，办卡日期，vip卡类型 |

持久化对象定义如下所示（使用了lombok.Data，不再描述getter和setter方法）：
```java

@Data
public class Activity {

    private int id;
    /**
     * 优惠活动名称
     */
    private String name;
    /**
     * 优惠活动描述
     */
    private String description;
    /**
     * 优惠活动开始时间
     */
    private Timestamp startTime;
    /**
     * 优惠活动截止时间
     */
    private Timestamp endTime;
    /**
     * 优惠电影列表
     */
    private List<Movie> movieList;
    /**
     * 优惠券规格
     */
    private Coupon coupon;


    public Activity() {

    }

    public ActivityVO getVO() {
        ActivityVO vo = new ActivityVO();
        vo.setId(id);
        vo.setCoupon(coupon);
        vo.setDescription(description);
        vo.setEndTime(endTime);
        vo.setStartTime(startTime);
        vo.setName(name);
        vo.setMovieList(movieList.stream().map(movie -> {
            MovieVO mvo = new MovieVO();
            mvo.setId(movie.getId());
            mvo.setName(movie.getName());
            mvo.setPosterUrl(movie.getPosterUrl());
            mvo.setDirector(movie.getDirector());
            mvo.setScreenWriter(movie.getScreenWriter());
            mvo.setStarring(movie.getStarring());
            mvo.setType(movie.getType());
            mvo.setCountry(movie.getCountry());
            mvo.setLanguage(movie.getLanguage());
            mvo.setStartDate(movie.getStartDate());
            mvo.setLength(movie.getLength());
            mvo.setDescription(movie.getDescription());
            mvo.setStatus(movie.getStatus());
            mvo.setIslike(movie.getIslike());
            mvo.setLikeCount(movie.getLikeCount());
            return mvo;
        } ).collect(Collectors.toList()));
        return vo;

    }

}

@Data
public class AudiencePrice {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户消费金额
     */
    private Double totalPrice;

}

@Data
public class CardItem {
    int id;

    /**
     * 门槛金额
     */
    double target;

    /**
     * 优惠金额
     */

    double add;
    public CardItem(){}
    public CardItem(CardItemForm cardItemForm){
        this.target = cardItemForm.getTarget();
        this.add = cardItemForm.getAdd();
    }
}

@Data
public class ChargeItem {
    int id;

    /**
     * 充值金额
     */
    double amount;

    /**
     * 用户id
     */
    int user_id;

    /**
     * 充值时间
     */
    Timestamp timestamp;
}

@Data
public class Coupon {
    /**
     * 优惠券id
     */
    private int id;
    /**
     * 优惠券描述
     */
    private String description;
    /**
     * 优惠券名称
     */
    private String name;
    /**
     * 优惠券使用门槛
     */
    private double targetAmount;
    /**
     * 优惠券优惠金额
     */
    private double discountAmount;
    /**
     * 可用时间
     */
    private Timestamp startTime;
    /**
     * 失效时间
     */
    private Timestamp endTime;

}

@Data
public class DateLike {
    /**
     * 喜爱人数
     */
    private int likeNum;

    /**
     * 喜爱时间
     */
    private Date likeTime;

}

public class Hall {
    private Integer id;

    /**
     * 影厅名称
     */
    private String name;

    /**
     * 影厅行数
     */
    private Integer column;

    /**
     * 影厅列数
     */
    private Integer row;

}

@Data
public class History {
    int id;

    /**
     * 支付类型
     */
    int payType;

    /**
     * 支付完成时间
     */
    Timestamp time;

    /**
     * 消费金额
     */
    double fare;

    /**
     * 消费种类
     */
    String type;

    /**
     * 用户id
     */
    int userId;

    public History(){}

    public History(int payType,Timestamp timestamp,double fare,String type,int userId){
        this.payType=payType;
        this.time=timestamp;
        this.fare=fare;
        this.type=type;
        this.userId = userId;
    }


}

@Data
public class HistoryMovie {

    /**
     * 历史记录id
     */
    int historyId;

    /**
     * 电影名
     */
    String movieName;

    /**
     * 电影票id
     */
    int ticketId;
}

@Data
public class Movie {
    /**
     * 电影id
     */
    private Integer id;
    /**
     * 电影名称
     */
    private String name;
    /**
     * 海报url
     */
    private String posterUrl;
    /**
     * 导演
     */
    private String director;
    /**
     * 编剧
     */
    private String screenWriter;
    /**
     * 主演
     */
    private String starring;
    /**
     * 电影类型
     */
    private String type;
    /**
     * 制片国家/地区
     */
    private String country;
    /**
     * 语言
     */
    private String language;
    /**
     * 上映时间
     */
    private Date startDate;

    private Date endDate;

    /**
     * 片长
     */
    private Integer length;
    /**
     * 描述
     * @return
     */
    private String description;
    /**
     * 电影状态，0：上架状态，1：下架状态
     */
    private Integer status;
    /**
     * 是否想看,0:未标记想看，1：已标记想看
     */
    private Integer islike;
    /**
     * 想看人数
     */
    private Integer likeCount;

     /**
     * 大海报URL
     */
    private String bigPosterUrl;

}

@Data
public class MovieScheduleTime {
    private Integer movieId;
    /**
     * 排片次数
     */
    private Integer time;

    /**
     * 电影名
     */
    private String name;

}

@Data
public class MovieTotalBoxOffice {

    /**
     * 电影id
     */
    private int movieId;

    /**
     * 票房(单位：元)，(PS:如果后续数据量大，可自行处理单位，如改成单位：万元)
     */
    private Double boxOffice;

    /**
     * 电影名
     */
    private String name;
    
}

@Data
public class Order {
    int id;

    /**
     * 订单名
     */
    String name;

    /**
     * 订单内容
     */
    String content;

    /**
     * 订单价格
     */
    double price;

    /**
     * 订单状态
     */
    int status;

    public Order(){}
    public Order(String name,String content,double price,int status){
        this.name=name;
        this.content=content;
        this.price=price;
        this.status=status;
    }
}

@Data
public class RefundStrg {

    /**
     * 以数轴的形式表示距离距离电影开始播放的时间
     * 比如-60 表示距离电影播放还有60分钟
     * 15表示电影已经播放了15分钟
     */
    private int endMinute;

    /**
     *  应该收取的手续费比例 用小数表示
     *  比如0.1 表示收取10%
     */

    private double percent;

    public RefundStrg(){

    }

    public RefundStrg(RefundStrgVO refundStrgVO){
        this.endMinute = refundStrgVO.getEndMinute();
        this.percent = refundStrgVO.getPercent();
    }
}

@Data
/**
 * @author fjj
 * @date 2019/4/12 3:34 PM
 */
public class ScheduleItem {
    /**
     * id
     */
    private Integer id;
    /**
     * 影厅id
     */
    private Integer hallId;
    /**
     * 影厅名称
     */
    private String hallName;
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 电影名
     */
    private String movieName;
    /**
     * 开始放映时间
     */
    private Date startTime;
    /**
     * 结束放映时间
     */
    private Date endTime;
    /**
     * 票价
     */
    private double fare;

}

@Data
public class SimpleScheduleItem {
    private Integer id;

    /**
     * 影厅id
     */
    private Integer hallId;

    /**
     * 电影id
     */
    private Integer movieId;

    /**
     * 电影名
     */
    private String name;

}

@Data
public class Ticket {

    /**
     * 电影票id
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 排片id
     */
    private int scheduleId;
    /**
     * 列号
     */
    private int columnIndex;
    /**
     * 排号
     */
    private int rowIndex;


    /**
     * 订单状态：
     * 0：未完成 1：已完成 2:已失效
     */
    private int state;

    /**
     * 出票状态
     * 0：没出票  1：已出票
     */

    private int ticketingState;

    /**
     * 是否使用优惠券
     * 0：没使用  >= 1：使用 useCoupon = couponId
     */
    private int useCoupon;

    private Timestamp time;

}

@Data
public class User {
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像海报
     */
    private String photoURL;

    /**
     * 用户类型
     */
    private int userType;

    /**
     * 用户邮箱
     */
    private String email;
    
}

@Data
public class VIPCard {

    public static final double price = 25;

    public static final String description="";

    /**
     * 用户id
     */
    private int userId;

    /**
     * 会员卡id
     */
    private int id;

    /**
     * 会员卡余额
     */
    private double balance;

    /**
     * 办卡日期
     */
    private Timestamp joinDate;

    /**
     * vip卡的类型 0：普通卡（没有充值优惠）  1 会员卡（有充值优惠）
     */
    private int vipType;


    public VIPCard() {

    }

}
```

#### 7.5.2 数据库表

数据库中包含activity表，activity_movie表，Ali_orders表，charge_order表，coupon表，coupon_user表，hall表，history表，history_charge表，history_movie表，movie表，movie_like表，order_ticket表，orders表，refund_strg表，schedule表，ticket表，user表，view表，vip_card表，vip_strg表。