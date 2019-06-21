# 影院管理系统（TMS）第三阶段软件详细设计描述文档

## 1. 文档修改历史

| 修改人员 | 日期     | 修改原因                                                     | 版本号 |
| :------: | -------- | ------------------------------------------------------------ | ------ |
|   周钰坤   | 2019.6.19 | 完成文档所有内容 | 1.0    |


## 2. 目录

<!-- TOC -->

- [影院管理系统（TMS）第三阶段软件详细设计描述文档](#影院管理系统tms第三阶段软件详细设计描述文档)
    - [1. 文档修改历史](#1-文档修改历史)
    - [2. 目录](#2-目录)
    - [3. 引言](#3-引言)
        - [3.1 编制目的](#31-编制目的)
        - [3.2 词汇表](#32-词汇表)
        - [3.3 参考资料](#33-参考资料)
    - [4. 产品描述](#4-产品描述)
    - [5. 系统结构设计概述](#5-系统结构设计概述)
    - [6. 结构视角](#6-结构视角)
        - [6.1 业务逻辑层的分解](#61-业务逻辑层的分解)
            - [6.1.1 模块概述](#611-模块概述)
            - [6.1.2 整体结构概述](#612-整体结构概述)
            - [6.1.3 业务逻辑层的设计原理概述](#613-业务逻辑层的设计原理概述)
            - [6.1.4 类的设计图、业务实现的顺序图及状态图概述](#614-类的设计图、业务实现的顺序图及状态图概述)
            - [6.1.5 userbl模块](#615-userbl模块)
            - [6.1.6 salesbl模块](#616-salesbl模块)
            - [6.1.7 managementbl模块](#617-managementbl模块)
            - [6.1.8 promotionbl模块](#618-promotionbl模块)
            
    - [](#)
    - [7. 依赖视角](#7-依赖视角)

<!-- /TOC -->



## 3. 引言

### 3.1 编制目的

	本报告详细完成对影院管理系统第三阶段的详细设计，达到指导后续软件构造的目的，同时实现和测试人员及用户的沟通。
	
	本报告面向开发人员、测试人员以及最终用户而编写，是了解系统的导航。

### 3.2 词汇表

| 词汇名称 |   词汇含义   |         备注          |
| :------: | :----------: | :-------------------: |
|   TMS    | 影院管理系统 | Theater Manage System |

### 3.3 参考资料

1. 《软件工程与计算(卷二)——软件开发的技术基础》
2. 《影院管理系统(TMS)第三阶段需求规格说明》
3. 《影院管理系统(TMS)第三阶段体系结构文档》
4. 《影院管理系统(TMS)第三阶段用例文档》

## 4. 产品描述

	参考影院管理系统第三阶段用例文档和影院管理系统第三阶段需求规格说明文档中对产品的概要描述。

## 5. 系统结构设计概述


    参考影院管理系统(TMS)第三阶段体系结构文档中对体系结构设计的概述。


## 6. 结构视角

### 6.1 业务逻辑层的分解

	业务逻辑层的开发包图参见影院管理系统(TMS)第三阶段体系结构文档图4。

#### 6.1.1 模块概述

    各bl模块承担的需求均参见第三阶段需求规格说明文档功能需求及相关非功能需求。
    
    各bl模块的职责和接口参见第三阶段体系结构文档7.3.2bl模块的接口规范，比如：
    
    salesbl模块承担的需求参见第三阶段需求规格说明文档登录注册的功能需求及相关非功能需求。
    
    salesbl模块的职责和接口参见第三阶段体系结构文档7.3.2salesbl模块的接口规范。
    
    其他不再赘述。

#### 6.1.2 整体结构概述

    根据体系结构的设计，将系统分为了展示层、业务逻辑层和数据层。每一层之间为了增加灵活性，我们会添加接口。以salesbl的ticket业务为例，在展示层和业务逻辑层之间，我们添加TicketService，即bl.sales.TicketService接口；在业务逻辑层和数据层之间，我们添加data.sales.TicketMapper接口。为了隔离业务逻辑职责和逻辑控制职责，我们增加了TicketController，这样TicketController会将登录注册的业务逻辑委托给TicketService，由TicketServiceImpl对象具体实现业务。Ticket作为用户的持久化对象被添加到设计模型。其他功能的业务逻辑大致和salesbl相同，对于业务逻辑整体结构的补充见下方对应功能bl模块的整体结构。

#### 6.1.3 业务逻辑层的设计原理概述

    利用委托式控制风格，每个界面需要访问的业务逻辑由各自的控制器委托给不同的领域对象，利用不同的领域对象来进行数据传输，以达到展示层、业务逻辑层和数据层之间交互的目的。
    
    其他略。

#### 6.1.4 类的设计图、业务实现的顺序图及状态图概述

    由于整体所有的业务设计采用Controller、Service及ServiceImpl三者配合的实现方式，所以第三阶段所有的业务的类的设计及其相似。由于Service对业务具体实现的高度封装，业务实现流程的顺序图也几乎相同。又第三阶段新增的业务基本为事物的增删改查，所以业务的状态图也基本相似。所以本文档在此处列出类的设计图，业务实现的顺序图及业务流程状态图的总览，在之后的模块叙述里不再赘述或展示与此相关的图。

<center>图1 模块内具体业务的各个类的设计</center>	

[![模块内类的设计图.png](https://i.loli.net/2019/06/19/5d09ffa53901850166.png)](https://i.loli.net/2019/06/19/5d09ffa53901850166.png)

<center>图2 创建观众用户的顺序图</center>

[![业务实现的顺序图.png](https://i.loli.net/2019/06/19/5d09ff1b7b4ab35922.png)](https://i.loli.net/2019/06/19/5d09ff1b7b4ab35922.png)

#### 6.1.5 userbl模块

(1)整体结构

	在设计中，HistoryController用于分发任务即历史消费相关的查询历史纪录、查询历史充值等任务。在观众用户进行点击查询操作后，由HistoryController向HistoryService接口发出通知，再由HistoryController进行任务分发,由HistoryServiceImpl实现HistoryService接口，完成业务。HistoryController本身不需要持久化对象数据。
	
	展示层和逻辑层间接口：HitoryService
	
	逻辑层和数据层间接口：无
	
	PO：History
	
	userbl与History相关的各个类的职责如表1所示。

<center>表1 userbl模块与History相关的各个类的职责</center>


|        模块         | 职责                                                     |
| :-----------------: | -------------------------------------------------------- |
| HistoryController  | 负责分发任务|
| HistoryService  | 设计为接口，负责提供对外的业务实现接口|
| HistoryServiceImpl | 负责实现HistoryService接口，通过内部实现、调用其他包的对外接口等方式实现观众用户需要的服务 |
(2) 模块内部类的接口规范

	HistoryController和HistoryServiceImpl的接口规范如表2和表3所示。HistoryService仅起到接口作用，而具体实现都由相应的serviceImpl实现完成，所以在本文档中直接记录ServiceImpl的接口规范。

<center>表2 HistoryController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">HistoryController.getAllHistory</td>
    <td>语法</td>
    <td>public ResponseVO getAllHistory(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户点击查看消费历史</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HistoryService的getAllHistory方法</td>
  </tr>
   <tr>
    <td rowspan="3">HistoryController.getItemHistory</td>
    <td>语法</td>
    <td>public ResponseVO getItemHistory(int historyId,String type)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户点击查看单项历史记录</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HistoryService的getItemHistory方法</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>HistoryService.getAllHistory(int userId)</td>
    <td colspan="2"> <center>创建一个HistoryVO对象的List并返回</center></td>
  </tr>
  <tr>
    <td>HistoryService.getItemHistory(int historyId,String type)</td>
    <td colspan="2"> <center>创建一个HistoryVO对象并返回</center></td>
  </tr>
  </table>
<center>表3 HistoryServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">HistoryServiceImpl.getAllHistory</td>
    <td>语法</td>
    <td>public ResponseVO getAllHistory(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HistoryController.getAllHistory方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>创建一个HistoryVO对象的List并返回</td>
  </tr>
   <tr>
    <td rowspan="3">HistoryServiceImpl.getItemHistory</td>
    <td>语法</td>
    <td>public ResponseVO getItemHistory(int historyId,String type)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HistoryController.getItemHistory方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>创建一个HistoryVO对象并返回</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>...</td>
    <td colspan="2"> <center>...</center></td>
  </tr>
  </table>



(3) 业务逻辑层的动态模型

	见6.1.4的业务流程顺序图。


(4) 业务逻辑层的设计原理

	HistoryController只负责进行任务分发，不需要持久化对象数据,HistoryServiceImpl负责具体的业务实现，需要History持久化对象数据。



#### 6.1.6 salesbl模块

	(1)整体结构

	在设计中，TicketController用于分发任务即第三阶段新增的退票Refund任务。在观众用户进行点击退票后，由TicketController向TicketService接口发出通知，再由TicketController进行任务分发,由TicketServiceImpl实现TicketService接口，完成业务。TicketController本身不需要持久化对象数据。
	
	展示层和逻辑层间接口：TicketService
	
	逻辑层和数据层间接口：无
	
	PO：无
	
	salesbl与退票相关的各个类的职责如表4所示。

<center>表4 salesbl模块与Refund相关的各个类的职责</center>


|        模块         | 职责                                                     |
| :-----------------: | -------------------------------------------------------- |
| TicketController  | 负责分发任务|
| TicketService  | 设计为接口，负责提供对外的业务实现接口|
| TicketServiceImpl | 负责实现TicketService接口，负责通过内部实现、调用其他包的对外接口等方式实现观众用户需要的服务 |
(2) 模块内部类的接口规范

	TicketController和TicketServiceImpl的接口规范如表5和表6所示。TicketService仅起到接口作用，而具体实现都由相应的serviceImpl实现完成，所以在本文档中直接记录ServiceImpl的接口规范。

<center>表5 TicketController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">TicketController.getCanRefund</td>
    <td>语法</td>
    <td>public ResponseVO getCanRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户点击退款</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用TicketService的getCanRefund方法</td>
  </tr>
  <tr>
    <td rowspan="3">TicketController.getRefund</td>
    <td>语法</td>
    <td>public ResponseVO getRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户点击退款后加载页面信息时发起request</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用TicketService的getRefund方法</td>
  </tr>
  <tr>
  <tr>
    <td rowspan="3">TicketController.completeRefund</td>
    <td>语法</td>
    <td>public ResponseVO completeRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户点击确认退款</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用TicketService的completeRefund方法</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>TicketService.getCanRefund(List<Integer> ticketId)</td>
    <td colspan="2"> <center>创建一个List ticketstate以表明订单的能否退款并返回</center></td>
  </tr>
  <tr>
    <td>TicketService.getRefund(List<Integer> ticketId)</td>
    <td colspan="2"> <center>根据退票策略生成一个refund金额并返回</center></td>
  </tr>
   <tr>
    <td>TicketService.completeRefund(List<Integer> ticketId)</td>
    <td colspan="2"> <center>完成退款操作</center></td>
  </tr>
  </table>
<center>表6 TicketServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">TicketServiceImpl.getCanRefund</td>
    <td>语法</td>
    <td>public ResponseVO getCanRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得TicketController.getCanRefund方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>创建一个List ticketstate以表明订单的能否退款并返回</td>
  </tr>
   <tr>
    <td rowspan="3">TicketServiceImpl.getRefund</td>
    <td>语法</td>
    <td>public ResponseVO getRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得TicketController.getRefund方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据退票策略生成一个refund金额并返回</td>
  </tr>
   <tr>
    <td rowspan="3">TicketServiceImpl.completeRefund</td>
    <td>语法</td>
    <td>public ResponseVO completeRefund(List<Integer> ticketId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得TicketController.completeRefund方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成退票操作</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>RefundStrategyService.getAllRefundStrategy</td>
    <td colspan="2"> <center>获得退票策略</center></td>
  </tr>
  </table>



(3) 业务逻辑层的动态模型

	见6.1.4的业务流程顺序图。


(4) 业务逻辑层的设计原理

	TicketController只负责进行任务分发，不需要持久化对象数据,TicketServiceImpl负责具体的业务实现，不需要持久化对象数据。

#### 6.1.7 managementbl模块

	(1)整体结构

	managementbl模块被用于处理管理相关的业务逻辑，包括影厅的增加、影厅信息的修改、增加和修改退票策略、影厅角色的增删改查。模块内包括HallController、HallStrategyService与HallStrategyServiceImpl; RoleController、RoleService与RoleServiceImpl; RefundStrategyController、RefundStrategyService与RefundStrategyServiceImpl三个模块的类。以影厅管理为例，HallController负责响应外部的请求并将请求与HallService的业务接口映射，HallServiceImpl负责实现业务的具体实现。hall是作为影厅信息的持久化对象被添加到设计模型中。
	
	展示层和逻辑层间接口：HallStrategyService、RoleService、RefundStrategyService
	
	逻辑层和数据层间接口：无
	
	PO：Hall、RefundStrg、User
	
	managementbl模块第三阶段相关业务的各个类的职责如表7所示。

<center>表7 managementbl模块第三阶段相关业务的各个类的职责</center>


|        模块         | 职责                                                     |
| :-----------------: | -------------------------------------------------------- |
| HallController  | 负责分发任务|
| HallService  | 设计为接口，负责提供对外的业务实现接口|
| HallServiceImpl | 负责实现HallService接口，负责通过内部实现、调用其他包的对外接口等方式实现观众用户需要的服务 |
| RoleController  | 负责分发任务|
| RoleService  | 设计为接口，负责提供对外的业务实现接口|
| RoleServiceImpl | 负责实现RoleService接口，负责通过内部实现、调用其他包的对外接口等方式实现观众用户需要的服务 |
| RefundStrategyController  | 负责分发任务|
| RefundStrategyService  | 设计为接口，负责提供对外的业务实现接口|
| RefundStrategyServiceImpl | 负责实现RefundStrategyService接口，负责通过内部实现、调用其他包的对外接口等方式实现观众用户需要的服务 |
(2) 模块内部类的接口规范

	HallController、HallServiceImpl、RoleController、RoleServiceImpl、RefundStrategyController、RefundStrategyServiceImpl的接口规范如表8、表9、表10、表11、表12和表13所示。HallService、RoleService、RefundStrategyService三个类仅起到接口作用，而具体实现都由相应的serviceImpl实现完成，所以在本文档中直接记录ServiceImpl的接口规范。


  <center>表8 HallController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">HallController.addHall</td>
    <td>语法</td>
    <td>public ResponseVO addHall(HallFormVO hallFormVO)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户填写完影厅信息表单后点击添加影厅</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HallService的addHall方法，返回一个操作成功的信息</td>
  </tr>
  <tr>
    <td rowspan="3">HallController.deleteHall</td>
    <td>语法</td>
    <td>public ResponseVO deleteHall(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户点击删除影厅</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HallService的deleteHall方法，返回操作成功与否的信息</td>
  </tr>
  <tr>
    <td rowspan="3">HallController.updateHall</td>
    <td>语法</td>
    <td>public ResponseVO updateHall(int id, HallFormVO hallFormVO)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户点击修改影厅信息及填写完修改信息后点击确认修改</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HallService的changeHallInfo方法，返回操作成功与否的信息</td>
  </tr>
  <tr>
    <td rowspan="3">HallController.searchAllHall</td>
    <td>语法</td>
    <td>public ResponseVO searchAllHall()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户的影厅管理界面加载时发起request</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HallService的searchAllHall方法，返回一个包含所有影厅信息的HallVOList</td>
  </tr>
    <tr>
    <td rowspan="3">HallController.searchHallByID</td>
    <td>语法</td>
    <td>public ResponseVO searchHallByID(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>观众用户点击选座后发起request</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用HallService的searchHall方法，返回id对应的影厅的信息</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>HallService.addHall(HallFormVO hallFormVO)</td>
    <td colspan="2"> <center>完成增加影厅的操作，返回操作成功信息</center></td>
  </tr>
  <tr>
    <td>HallService.deleteHall(int id)</td>
    <td colspan="2"> <center>完成根据id删除影厅的操作，返回操作成功的信息</center></td>
  </tr><tr>
    <td>HallService.changeHallInfo(int id,HallFormVO hallFormVO)</td>
    <td colspan="2"> <center>完成影厅信息修改操作</center></td>
  </tr>
   <tr>
    <td>HallService.searchAllHall()</td>
    <td colspan="2"> <center>返回一个包含所有影厅信息的HallVOList</center></td>
  </tr>
  <tr>
    <td>HallService.searchHall(int id)</td>
    <td colspan="2"> <center>返回id对应的影厅的信息</center></td>
  </tr>
  </table>
   <center>表9 HallServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">HallServiceImpl.addHall</td>
    <td>语法</td>
    <td>public ResponseVO addHall(HallFormVO hallFormVO)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HallController.addHall的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成增加影厅的操作，返回操作成功信息</td>
  </tr>
  <tr>
    <td rowspan="3">HallServiceImpl.deleteHall</td>
    <td>语法</td>
    <td>public ResponseVO deleteHall(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HallController.deleteHall的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成根据id删除影厅的操作，返回操作成功的信息</td>
  </tr>
  <tr>
    <td rowspan="3">HallServiceImpl.changeHallInfo</td>
    <td>语法</td>
    <td>public ResponseVO changeHallInfo(int id, HallFormVO hallFormVO)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HallController.updateHallInfo的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成影厅信息修改操作</td>
  </tr>
  <tr>
    <td rowspan="3">HallServiceImpl.searchAllHall</td>
    <td>语法</td>
    <td>public ResponseVO searchAllHall()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HallController.searchAllHall的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回一个包含所有影厅信息的HallVOList</td>
  </tr>
    <tr>
    <td rowspan="3">HallServiceImpl.searchHall</td>
    <td>语法</td>
    <td>public ResponseVO searchHall(int id)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得HallController.searchHall的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回id对应的影厅的信息</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>......</td>
    <td colspan="2"> <center>......</center></td>
  </tr>
  
  </table>
  <center>表10 RoleController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">RoleController.addRole</td>
    <td>语法</td>
    <td>public ResponseVO addRole(RoleForm roleForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户点击添加影院员工</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RoleService的addRole方法</td>
  </tr>
  <tr>
    <td rowspan="3">RoleController.deleteRole</td>
    <td>语法</td>
    <td>public ResponseVO deleteRole(String username)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户点击删除员工</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RoleService的deleteRoleByUsername方法</td>
  </tr>
  <tr>
    <td rowspan="3">RoleController.updateUserType</td>
    <td>语法</td>
    <td>public ResponseVO updateUserType(int userId,int userType)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户点击修改影院成员的角色类型</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RoleService的changeUserType方法</td>
  </tr>
  <tr>
    <td rowspan="3">RoleController.searchAllRole</td>
    <td>语法</td>
    <td>public ResponseVO getAllManagerAndStaff()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员用户的影厅管理界面加载时发起request</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RoleService的searchAllManagerAndStaff()方法，返回一个包含所有员工信息的UserList</td>
  </tr>
    <tr>
    <td rowspan="3">RoleController.getUserByUsername</td>
    <td>语法</td>
    <td>public ResponseVO getUserByUsername(String username)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员根据用户名查找员工</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RoleService的searchUserByUserName方法，返回username对应的员工的信息</td>
  </tr>
  <tr>
    <td rowspan="3">RoleController.getUserById</td>
    <td>语法</td>
    <td>public ResponseVO getUserById(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>前端根据用户id查找员工</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RoleService的searchUserById方法，返回id对应的员工的信息</td>
  </tr>
  
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>RoleService.addRole(RoleForm roleForm)</td>
    <td colspan="2"> <center>完成增加员工操作</center></td>
  </tr>
  <tr>
    <td>RoleService.deleteRoleByUsername(String userName)</td>
    <td colspan="2"> <center>完成根据username删除员工的操作</center></td>
  </tr><tr>
    <td>RoleService.changeUserType(int userId,int userType)</td>
    <td colspan="2"> <center>完成员工角色类型信息修改操作</center></td>
  </tr>
   <tr>
    <td>RoleService.searchAllManagerAndStaff()</td>
    <td colspan="2"> <center>返回一个包含所有员工信息的UserList</center></td>
  </tr>
  <tr>
    <td>RoleService.searchUserByUserName(String userName)</td>
    <td colspan="2"> <center>返回username对应的员工的信息</center></td>
  </tr>
  <tr>
    <td>RoleService.searchUserById(int userId)</td>
    <td colspan="2"> <center>返回id对应的员工的信息</center></td>
  </tr>
  </table>
<center>表11 RoleServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">RoleServiceImpl.addRole</td>
    <td>语法</td>
    <td>public ResponseVO addRole(RoleForm roleForm)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RoleController.addRole方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成增加员工操作</td>
  </tr>
  <tr>
    <td rowspan="3">RoleServiceImpl.deleteRoleByUsername</td>
    <td>语法</td>
    <td>public ResponseVO deleteRoleByUsername(String username)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RoleController.deleteRole方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成根据username删除影院角色的操作</td>
  </tr>
  <tr>
    <td rowspan="3">RoleServiceImpl.changeUserType</td>
    <td>语法</td>
    <td>public ResponseVO changeUserType(int userId,int userType)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RoleController.updateUserType方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成id对应的员工的角色类型userType的修改工作</td>
  </tr>
  <tr>
    <td rowspan="3">RoleServiceImpl.searchAllManagerAndStaff</td>
    <td>语法</td>
    <td>public ResponseVO searchAllManagerAndStaff()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RoleController.getAllManagerAndStaff方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回一个包含所有员工信息的UserList</td>
  </tr>
    <tr>
    <td rowspan="3">RoleServiceImpl.searchUserByUserName</td>
    <td>语法</td>
    <td>public ResponseVO searchUserByUserName(String username)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RoleController.getUserByUsername方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回username对应的员工的信息</td>
  </tr>
  <tr>
    <td rowspan="3">RoleServiceImpl.searchUserById</td>
    <td>语法</td>
    <td>public ResponseVO searchUserById(int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RoleController.getUserById方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回id对应的员工的信息</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>......</td>
    <td colspan="2"> <center>......</center></td>
  </tr>
  </table>
  <center>表12 RefundStrategyController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">RefundStrategyController.getAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO getAllStrategy()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员的退票策略管理界面加载时发起request</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RefundStrategyService.getAllStrategy方法，返回退票策略信息</td>
  </tr>
  <tr>
    <td rowspan="3">RefundStrategyController.updateAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO updateAllStrategy(List<RefundStrgVO> refundStrgVOList)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员修改退票策略后点击确认</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用RefundStrategyService.updateAllStrategy方法，完成修改操作</td>
  </tr>
  
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>RefundStrategyService.getAllStrategy()</td>
    <td colspan="2"> <center>创建一个包含所有退票策略信息的RefundStrgToRefundStrgVO并返回</center></td>
  </tr>
  <tr>
    <td>RefundStrategyService.updateAllStrategy(List<RefundStrgVO> refundStrgVOList)</td>
    <td colspan="2"> <center>根据refundStrgVOList里修改后的退票策略信息完成退票策略修改操作</center></td>
  </tr>
  </table>
<center>表13 RefundStrategyServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">RefundStrategyServiceImpl.getAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO getAllStrategy()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RefundStrategyController.getAllStrategy方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>创建一个包含所有退票策略信息的RefundStrgToRefundStrgVO并返回</td>
  </tr>
  <tr>
    <td rowspan="3">RefundStrategyServiceImpl.updateAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO updateAllStrategy(List<RefundStrgVO> refundStrgVOList)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得RefundStrategyController.updateAllStrategy方法的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>根据refundStrgVOList里修改后的退票策略信息完成退票策略修改操作</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>......</td>
    <td colspan="2"> <center>......</center></td>
  </tr>
  </table>


(3) 业务逻辑层的动态模型

	见6.1.4的业务流程顺序图。


(4) 业务逻辑层的设计原理

	与6.1.5的user模块类似，略。

#### 6.1.8 promotionbl模块

(1)整体结构

	promotionbl模块被用于处理促销相关的业务逻辑，包括发布会员卡充值策略、赠送优惠券。模块内包括VIPStrategyController、VIPStrategyService与VIPStrategyServiceImpl和CouponController、CouponService与CouponServiceImpl两个模块的类。VIPStrategyController负责响应外部的请求并将请求与VIPStrategyService的业务接口映射，VIPStrategyServiceImpl负责实现业务的具体实现。CardItem是作为会员信息的持久化对象被添加到设计模型中。

	展示层和逻辑层间接口：VIPStrategyservice、CouponService
	
	逻辑层和数据层间接口：无
	
	PO：CardItem
	
	promotionbl模块关于发布会员卡充值策略、赠送优惠券业务的各个类的职责如表14所示。

<center>表14 promotionbl关于发布会员卡充值策略、赠送优惠券业务的各个类的职责</center>

|      模块      | 职责                                                   |
| :------------: | ------------------------------------------------------ |
| VIPStrategyController  | 负责模块内部发布会员卡充值策略业务的分发任务 |
| VIPStrategyService  | 设计为接口，负责提供发布会员卡充值策略的业务实现的接口 |
| VIPStrategyServiceImpl | 负责实现VIPStrategyService接口，提供发布会员卡充值策略的具体业务实现      |
| CouponController  | 负责模块内部赠送优惠券业务的分发任务 |
| CouponService | 设计为接口，负责提供赠送优惠券的业务实现的接口      |
| CouponServiceImpl  | 负责实现CouponService接口，负责模块内部赠送优惠券业务的分发任务 |

(2) 模块内部类的接口规范
	
    VIPStrategyController、VIPStrategyServiceImpl、CouponController和CouponServiceImpl的接口规范如表15、表16、表17和表18所示。VIPStrategyService、CouponService仅起到接口作用，而具体实现都由相应的serviceImpl实现完成，所以在本文档中直接记录ServiceImpl的接口规范。

<center>表15 VIPStrategyController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">VIPStrategyController.publishVIPStrategy</td>
    <td>语法</td>
    <td>public ResponseVO publishVIPStrategy(List<CardItemForm> cardItemFormList)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员填写好充值优惠点击确认</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用VipStrategyService.publishVIPStrategy方法，完成充值优惠发布操作</td>
  </tr>
<tr>
    <td rowspan="3">VIPStrategyController.getAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO getAllStrategy()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员的活动管理页面加载时发起request</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用VipStrategyService.getAllStrategy方法，返回所有的充值优惠信息</td>
  </tr>

  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>VIPStrategyServiceImpl.publishVIPStrategy(List<CardItemForm>cardItemFormList)</td>
    <td colspan="2"> <center>完成充值优惠发布操作</center></td>
  </tr>
  <tr>
    <td>VIPStrategyServiceImpl.getAllStrategy()</td>
    <td colspan="2"> <center>返回所有的充值优惠信息</center></td>
  </tr>
  </table>
<center>表16 VIPStrategyServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
    <tr>
    <td rowspan="3">VIPStrategyServiceImpl.publishVIPStrategy</td>
    <td>语法</td>
    <td>public ResponseVO publishVIPStrategy(List<CardItemForm> cardItemFormList)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得VIPStrategyController.publishVIPStrategy的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成充值优惠发布操作</td>
  </tr>
<tr>
    <td rowspan="3">VIPStrategyServiceImpl.getAllStrategy</td>
    <td>语法</td>
    <td>public ResponseVO getAllStrategy()</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得VIPStrategyController.getAllStrategy的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>返回所有的充值优惠信息</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>......</td>
    <td colspan="2"> <center>......</center></td>
  </tr>
  
  </table>
  <center>表17 CouponController的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
  <tr>
    <td rowspan="3">CouponController.issueCoupons</td>
    <td>语法</td>
    <td>public ResponseVO issueCoupons(List<Integer>userIds,@RequestParam int couponId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>管理员选择赠送优惠券</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>调用CouponService.issueCoupons方法，完成赠送优惠券操作</td>
  </tr>


  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>CouponService.issueCoupon(int couponId, int userId)</td>
    <td colspan="2"> <center>完成赠送优惠券操作</center></td>
  </tr>
  </table>
<center>表18 CouponServiceImpl的接口规范</center>

<table border="1">
  <tr>
    <td colspan="3"><center>提供的服务（供接口）</center></td>
  </tr>
    <tr>
    <td rowspan="3">CouponServiceImpl.issueCoupon</td>
    <td>语法</td>
    <td>public ResponseVO issueCoupon(int couponId, int userId)</td>
  </tr>
  <tr>
    <td>前置条件</td>
    <td>获得CouponController.issueCoupon的调用</td>
  </tr>
  <tr>
    <td>后置条件</td>
    <td>完成赠送优惠券操作</td>
  </tr>
  <tr>
    <td colspan="3"><center>需要的服务（需接口）</center></td>
  </tr>
  <tr>
    <td>服务名</td>
    <td colspan="2"><center>服务</center></td>
  </tr>
  <tr>
    <td>......</td>
    <td colspan="2"> <center>......</center></td>
  </tr>
  
  </table>

(3) 业务逻辑层的动态模型

	见6.1.4的业务流程顺序图。


(4) 业务逻辑层的设计原理

	与6.1.5的user模块类似，略。






## 7. 依赖视角

[![体系结构开发风格包图 (1).png](https://i.loli.net/2019/06/19/5d0a02a9371d656976.png)](https://i.loli.net/2019/06/19/5d0a02a9371d656976.png)