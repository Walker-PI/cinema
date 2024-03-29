# 需求规格说明

<br>


#### 目录
- [1. 文档修改历史](#1-文档修改历史)
- [2. 引言](#2-引言)
    - [2.1 目的](#11-目的)
    - [2.2 范围](#12-范围)
    - [2.3 参考文献](#13-参考文献)
- [3. 总体描述](#2-总体描述)
    - [3.1 项目前景](#21-项目前景)
        - [3.1.1 背景与机遇](#211-背景与机遇)
        - [3.1.2 业务需求](#212-业务需求)
    - [3.2 项目功能](#22-项目功能)
    - [3.3 用户特征](#23-用户特征)
    - [3.4 约束](#24-约束)
    - [3.5 假设与依赖](#25-假设与依赖)
- [4 详细需求描述](#3-详细需求描述)
    - [4.1 对外接口需求](#31-对外接口需求)
        - [4.1.1 用户界面](#311-用户界面)
        - [4.1.2 通信接口](#312-通信接口)
    - [4.2 功能需求](#32-功能需求)
        - [4.2.1 管理电影](#321-上架电影)
            - [4.2.1.1 特征描述](#3211-特征描述)
            - [4.2.1.2 刺激/响应序列](#3212-刺激响应序列)
            - [4.2.1.3 相关功能需求](#3213-相关功能需求)
        - [4.2.2 统计想看人数](#322-统计想看人数)
            - [4.2.2.1 特征描述](#3221-特征描述)
            - [4.2.2.2 刺激/响应序列](#3222-刺激响应序列)
            - [4.2.3.3 相关功能需求](#3233-相关功能需求)
        - [4.2.3 查看电影详情](#323-查看电影详情)
            - [4.2.3.1 特征描述](#3231-特征描述)
            - [4.2.3.2 刺激/响应序列](#3232-刺激响应序列)
            - [4.2.3.3 相关功能需求](#3233-相关功能需求-1)
        - [4.2.4  标记某电影为想看](#324-标记某电影为想看)
            - [4.2.4.1 特征描述](#3241-特征描述)
            - [4.2.4.2 刺激/响应序列](#3242-刺激响应序列)
            - [4.2.4.3 相关功能需求](#3243-相关功能需求)
        - [4.2.5  搜索电影](#325-搜索电影)
            - [4.2.5.1 特征描述](#3251-特征描述)
            - [4.2.5.2 刺激/响应序列](#3252-刺激响应序列)
            - [4.2.5.3 相关功能需求](#3253-相关功能需求)
        - [4.2.6  用户登录](#326-用户登录注册)
            - [4.2.6.1 特征描述](#3261-特征描述)
            - [4.2.6.2 刺激/响应序列](#3262-刺激响应序列)
            - [4.2.6.3 相关功能需求](#3263-相关功能需求)
        - [4.2.7  添加排片信息](#327-添加排片信息)
            - [4.2.7.1 特征描述](#3271-特征描述)
            - [4.2.7.2 刺激/响应序列](#3272-刺激响应序列)
            - [4.2.7.3 相关功能需求](#3273-相关功能需求)
        - [4.2.8  查看排片信息](#328-查看排片信息)
            - [4.2.8.1 特征描述](#3281-特征描述)
            - [4.2.8.2 刺激/响应序列](#3282-刺激响应序列)
            - [4.2.8.3 相关功能需求](#3283-相关功能需求)
        - [4.2.9  查看统计数据](#329-查看统计数据)
            - [4.2.9.1 特征描述](#3291-特征描述)
            - [4.2.9.2 刺激/响应序列](#3292-刺激响应序列)
            - [4.2.9.3 相关功能需求](#3293-相关功能需求)
        - [4.2.10  发布优惠活动](#3210-发布优惠活动)
            - [4.2.10.1 特征描述](#32101-特征描述)
            - [4.2.10.2 刺激/响应序列](#32102-刺激响应序列)
            - [4.2.10.3 相关功能需求](#32103-相关功能需求)
        - [4.2.11  购买会员卡](#3211-购买会员卡)
            - [4.2.11.1 特征描述](#32111-特征描述)
            - [4.2.11.2 刺激/响应序列](#32112-刺激响应序列)
            - [4.2.11.3 相关功能需求](#32113-相关功能需求)
        - [4.2.12  充值会员卡](#3212-充值会员卡)
            - [4.2.12.1 特征描述](#32121-特征描述)
            - [4.2.12.2 刺激/响应序列](#32122-刺激响应序列)
            - [4.2.12.3 相关功能需求](#32123-相关功能需求)
        - [4.2.13  发布会员卡](#3213-发布会员卡)
            - [4.2.13.1 特征描述](#32131-特征描述)
            - [4.2.13.2 刺激/响应序列](#32132-刺激响应序列)
            - [4.2.13.3 相关功能需求](#32133-相关功能需求)
        - [4.2.14  查看历史记录](#329-查看历史记录)
            - [4.2.14.1 特征描述](#3291-特征描述)
            - [4.2.14.2 刺激/响应序列](#3292-刺激响应序列)
            - [4.2.14.3 相关功能需求](#3293-相关功能需求)
        - [4.2.15  用户退票](#329-用户退票)
            - [4.2.15.1 特征描述](#3291-特征描述)
            - [4.2.15.2 刺激/响应序列](#3292-刺激响应序列)
            - [4.2.15.3 相关功能需求](#3293-相关功能需求)
    - [4.3 非功能需求](#33-非功能需求)
        - [4.3.1  安全性](#331-安全性)
        - [4.3.2  可维护性](#332-可维护性)
        - [4.3.3  易用性](#333-易用性)
        - [4.3.4  可靠性](#334-可靠性)
        - [4.3.5  业务规则](#335-业务规则)
        - [4.3.6  约束](#336-约束)
    - [4.4  数据需求](#34-数据需求)
        - [4.4.1  数据定义](#341-数据定义)
        - [4.4.2  默认数据](#342-默认数据)
        - [4.4.3  数据格式要求](#343-数据格式要求)



<hr>

## 2. 引言

本系统由1024studio小组开发

### 2.1 目的

    本文描述了影院购票系统的功能需求和非功能需求。开发小组的软件系统实现与验证都以此为依据。


### 2.2 范围
    影院系统是为xxx影院开发的业务系统，开发的目标一是帮助该影院处理日常业务，包括上架电影和统计电影想看人数。二是帮助观众更加便利地了解电影，进行购票，包括搜索电影，查看详情和将电影标记为想看。三是帮助影院管理排片信息，帮助观众获取当日有关电影的统计数据。四是帮助该影院完成发布会员卡、录入新的影厅信息、影院角色的增删改查、赠送给用户优惠券。增加更人性化的退票功能，查看消费历史功能。

### 2.3 参考文献
    1) IEEE标准
    2) 软件工程与计算(卷二):软件开发的技术基础
    3) 影院购票系统用例文档



## 3. 总体描述

<br>


### 3.1 项目前景

<br>


#### 3.1.1 背景与机遇
>我们开发的软件系统面向的影院是一家开在广场美食街旁边的影院，共有8个影厅。原先的影院售票流程一直通过前台销售人员来处理，顾客只有来到前台，并且排队之后才能获得拍片信息，票价以及电影详情信息。
>
>随着广场的发展，美食街的顾客越来越多，许多顾客都希望吃过饭后或者饭前来影院看电影休闲一下，但是却对前台排起的长队望而却步。因此影院现在急需一个自动网页购票系统，顾客如果不需要其他服务，可以直接通过手机浏览拍片信息，购票、充值。这样将大大提高销售效率，降低销售成本，提高影院的竞争力。
>
>其次影院面临的另一个问题是有的影片场场爆满，有的影片却无人问津。通过统计顾客在浏览影院网页时标记的喜好信息，可以事先判断影片可能的卖座程度，由此决定拍片的频率和时间。
>
>影院购票系统包括一个数据集中服务器和多个客户端，面向影院观众，管理者，以及售票员。数据集中服务器将数据集中存储并维护。用户通过客户端(网页)完成购票和浏览最新上架电影信息。

#### 3.1.2 业务需求
    BR1:系统使用三个月后，影院销售成本以及人员成本减半
        范围：人员工资，拍片时间成本，空闲座位成本，电影票成本。
        度量：检查员工数量，和平均每部电影剩余空座变化率。
        最好情况：50%。
        最可能情况：30%。
        最坏情况：10%。
    BR2:系统使用两个月后，顾客回头率提升50%。
        最好情况：100%。
        最可能情况：30%。
        最坏情况：10%。
    BR3:系统使用6个月后，影院利润增加40%。
        度量：检查每部电影所赚利润。
    BR4:系统使用两个星期后，排队人数减少。
        范围：前台排队人数和进场排队人数。
        最好情况：减少到至多3人排队。
        最可能情况：5人。
        最坏情况：7人。

### 3.2 项目功能
**SF1:** 录入即将上架的电影及其信息。

**SF2:** 记录电影的想看人数。

**SF3:** 掌握用户账号信息。

**SF4:** 根据用户的输入内容搜索到影片。

**SF5:** 显示影片详情。

**SF6:** 显示”想看“”标记和想看人数。

**SF7:** 处理电影的上架和下架

**SF8:** 添加、查看、管理排片信息

**SF9:** 查看统计数据

**SF10:** 发布优惠活动

**SF11:** 用户购买电影票

**SF12:** 用户购买会员卡、查看会员卡相关信息

**SF13:** 管理员下架电影、修改电影信息

**SF14:** 查看电影票

**SF15:** 发布会员卡

**SF16:** 修改会员卡优惠策略

**SF17:** 增加退票策略

**SF18:** 管理影厅信息

**SF19:** 影院角色的增删改查

**SF20:** 退票功能

**SF21:** 查看历史消费和充值记录

**SF22:** 赠送优惠券


### 3.3 用户特征

|**经理**|**影院有1~2个总经理，他们每周更新一次上架电影。包括加入新电影，下架不受欢迎的电影和放映期结束的电影。对于不同的电影，要考虑电影对应的客户的年龄段，文化水平和经济水平。在节假日举办促销活动。同时为某些电影明星或者电影专题举办专题放映活动。在电影院设置其他娱乐设施，比如电玩、按摩椅、娃娃机、饮食等。与周边饭店商场合作，分发优惠券,销售电影周边产品。决定不同客户的优惠额度。经理事务繁忙，计算机水平不高，希望系统提供必要的报表。**|
|-------|:---------|
|**观众**|**观众普遍倾向于使用网络购票，但是往往因为需要选座和购买爆米花、可乐等食品或者需要刷会员卡的原因必须到前台排队，因为影院观众众多，排片时间不够合理，往往进场也要等很长时间。观众一半是在旁边的饭店吃过饭之后来看电影，有时错过了放映，要等很长时间。观众计算机水平一般，希望系统能够解决所有订票问题。**|

### 3.4 约束


**CON1:** 系统将运行在Windows操作系统上。

**CON2:** 系统使用Web界面。

**CON3:** 项目要使用持续集成方法进行开发。

**CON4:** 在开发中,开发者要提交需求规格说明文档、设计描述文档和测试报告。


### 3.5 假设与依赖

**AE1:** 新电影要在上映一周前录入系统。

**AE2:** 想看人数能够真实反映出电影的受欢迎程度。

**AE3:** 修改相关信息后，网站要即使更新。

**AE4:** 历史纪录要真实准确。。



## 4 详细需求描述

### 4.1 对外接口需求

#### 4.1.1 用户界面

**UI1:** 上架电影与下架电影：影院工作人员进入工作人员界面，选择功能按钮为“上架电影”，点击后系统自动给出框架，如电影海报（url）、时长（从键盘输入，以分钟为单位）、语种（有列表进行选择）、导演以及参演人员（肖像照片从本地上传）、电影详情（给出文本框进行键盘输入）。所有都填写完毕后，工作人员选择确认按钮进行上传，若系统审核到某一项没有填写内容，会提示“xx信息没有填写”并显示上传失败。若系统审核没有问题则提示上传成功。每个上架后的电影有一个按钮，可将此电影进行下架操作。
<img src="https://i.loli.net/2019/06/18/5d08ab8275f0b32072.png" alt="电影上架.png" title="电影上架.png" />

**UI2:** 影院统计数据：包含上座率，票房，客单价，热门电影。
<img src="https://i.loli.net/2019/06/18/5d08b045a2f9559378.png" alt="统计.png" title="统计.png" />

**UI3:** 查看电影详情：在电影列表界面，观众点击某电影链接，系统应该展开电影详情界面。
<img src="https://i.loli.net/2019/06/18/5d08aa986700022510.png" alt="电影详情.png" title="电影详情.png" />

**UI4:** 标记某电影为想看：标记初始状态为“空心”，观众点击标记，标记变为“红心”。

**UI5:** 搜索电影：电影列表界面右上角有搜索图标，观众点击搜索图标，系统会弹出搜索框，观众在搜索框输入电影名称或者上映时间，系统应该展开电影详情界面或者上映时间内的电影列表界面，如果系统搜索不到该电影，系统会显示搜索不到电影，如果上映时间不符合格式或者不在一周以内，系统会显示时间输入错误。
<img src="https://i.loli.net/2019/06/18/5d08aae48c25f38275.png" alt="搜索.png" title="搜索.png" />

**UI6:** 登录注册：用户进入登录界面，系统显示用户名输入框，密码输入框，下方是“登录”按钮和“立即注册”按钮，用户点击“登录”按钮，就会进入影院界面，如果用户输入的信息不符合格式，系统提示输入无效（参见功能需求中的登录注册）；用户点击“立即注册”按钮，进入注册界面，系统显示用户名输入框，密码输入框，密码再次确认输入框，下方是“注册”按钮和“立即登录”按钮，用户点击“注册”按钮，系统显示注册成功，如果用户输入的信息不符合格式，系统提示输入无效（参见功能需求中的登录注册），用户点击“立即登录”按钮，用户进入登录界面。
<img src="https://i.loli.net/2019/06/18/5d08ab152335e33145.png" alt="登录.png" title="登录.png" />

**UI7:** 购买电影票：在电影列表界面，观众点击某电影链接，系统应该展开电影详情界面，选择购票。观众选择座位，锁座，进入支付页面，选择会员卡支付还是银行卡支付。支付完成后跳转到支付成功页面。
<img src="https://i.loli.net/2019/06/18/5d08b1ff79f3251092.png" alt="购票.png" title="购票.png" />

**UI8:** 购买会员卡：在“卡包”页面，若此用户还不是会员，可选择购买会员卡成为会员。若该用户已经成为会员，则可选择充值会员卡，为自己的会员卡充值。该页面显示会员卡号，会员卡余额。
<img src="https://i.loli.net/2019/06/18/5d08b1ff704a091487.png" alt="card.png" title="card.png" />

**UI9:** 查看历史纪录：该页面负责显示用户的历史充值记录和历史消费记录。历史充值记录包含该用户历次充值会员卡的金额，时间。历史消费记录包含该用户的购买会员卡记录和购买电影票记录。每条记录都附有查看详情按钮，来查看该记录的更多详情。
<img src="https://i.loli.net/2019/06/18/5d08abb50671895685.png" alt="历史消费记录.png" title="历史消费记录.png" />

**UI10:** 查看电影票：在该页面，用户可查看历史购买的电影票的详情。包含电影名称，购买的座位。电影的开始时间，结束时间。电影票的购买时间。
<img src="https://i.loli.net/2019/06/18/5d08ae98e3bc260902.png" alt="电影票.png" title="电影票.png" />

**UI11:** 发布优惠券：在该页面，管理员来添加优惠券，并可发布优惠券。
<img src="https://i.loli.net/2019/06/18/5d08aca8995be43335.png" alt="优惠.png" title="优惠.png" />

**UI12:** 发布活动：在该页面，管理员来发布近期的活动。包括活动名称、活动描述、开始日期、结束日期、奖卷名称、奖券描述、需满金额、优惠金额和参与的电影。
<img src="https://i.loli.net/2019/06/18/5d08ad1ed078a38403.png" alt="活动.png" title="活动.png" />

**UI13:** 退票策略：在该页面，管理员设置退票策略。要设置距电影开场时间、收取手续费占电影票价这些参数，来决定此退票策略的内容。
<img src="https://i.loli.net/2019/06/18/5d08adb6f1ba890108.png" alt="退票.png" title="退票.png" />


#### 4.1.2 通信接口

**CI:** 客户端与服务器使用RMI的方式进行通信。。


### 4.2 功能需求

#### 4.2.1  上架电影
##### 4.2.1.1 特征描述

当有新电影即将上映前，影院工作人员开始上架电影，完成新电影信息的录入。

优先级=高

##### 4.2.1.2 刺激/响应序列

刺激：影院工作人员输入影片信息。

响应：系统录入并显示影片信息。

刺激：影院工作人员取消上架任务。

响应：系统关闭上架任务。

刺激：影院工作人员删除已输入内容。

响应：系统在电影列表中删除该内容。

##### 4.2.1.3 相关功能需求

| Show.Input<br>Show.Input.Store<br>Show.Input.Movies<br>Show.Input.Cancel<br>Show.Input.Del<br>Show.Input.Invalid | 系统应该允许影院工作人员在上架电影时进行键盘输入<br>在工作人员输入内容结束后，系统要执行保存任务，参见Show.Store<br>在工作人员输入内容时，系统执行电影信息输入任务，参见Show.Movies<br>在工作人员输入取消命令时，系统关闭当前上架任务，开始新的上架任务<br>在工作人员删除已输入内容时，执行删除已输入内容命令，参见Show.Del<br>当影院工作人员输入的不符合格式时，系统要提示输入无效 |
| :----------------- | :--------------------|
| **Show.Store.Null<br>Show.Store.Movies<br>Show.Store.End**   | **在工作人员未输入任何电影信息就结束输入时，系统不做任何处理<br>在工作人员输入电影信息后结束输入时，系统执行保存任务<br>系统完成保存任务后，工作人员可以请求结束上架任务，系统执行结束上架任务，参见Show.End** |
| **Show.Movies<br>Show.Movies.List**                          | **系统显示输入电影的信息<br>在显示电影信息0.5秒之后，系统显示已输入电影列表，并将新输入电影信息添加到列表中** |
| **Show.Del.Null<br>Show.Del.Movies**                     | **在工作人员未输入任何电影信息就输入删除已输入电影信息命令时，系统不予响应<br>在工作人员删除电影信息时，系统在电影列表中删除相应内容** |
| **Show.End<br>Show.End.Timeout<br>Show.End.Update<br>Show.End.Close** | **系统应该允许工作人员要求结束上架任务<br>在上架电影开始2个小时后还没有接到工作人员请求时，系统取消上架任务<br>在工作人员要求结束上架任务时，系统更新数据,参见Show.Update<br>在工作人员确认上架任务完成时，系统关闭上架任务，参见Show.Close** |
| **Show.Update<br>Show.Update.Movies**                    | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新<br>系统更新电影信息** |
| **Show.Close.Next**                                          | **系统关闭本次上架任务，开始新的上架任务**                   |


#### 4.2.2  统计预售电影想看人数

##### 4.2.2.1 特征描述

影院工作人员查看电影的想看人数。

优先级=高

##### 4.2.2.2 刺激/响应序列

刺激：影院工作人员输入要查看想看人数的影片名称。

响应：系统显示影片想看人数。

##### 4.2.2.3 相关功能需求

| Statistic.Input<br>Statistic.Input.Search<br>Statistic.Cancel<br>Statistic.Input.Del<br>Statistic.Input.Movies<br>Statistic.Input.Invalid | 系统应该允许影院工作人员在统计想看人数时进行键盘输入<br>在工作人员结束输入时，系统执行搜索任务，参见Statistic.Search<br>在工作人员输入取消命令时，系统关闭当前统计任务，开始一个新的统计任务<br>在工作人员输入删除已输入电影命令时，执行删除已输入电影命令，参见Statistic.Del<br>在工作人员输入电影列表中存在的电影时，系统执行电影输入任务，参见Statistic.Movies<br>在工作人员输入系统内没有的电影名称时，系统提示搜索无效 |
| :----------------- | :--------------------|
| **Statistic.Search.Null<br>Statistic.Search.Movies<br>Statistic.Search.End** | **在工作人员未输入任何电影就结束输入时，系统不做任何处理<br>在工作人员输入电影后结束输入时，系统执行搜索任务<br>系统完成搜索任务后，工作人员可以请求结束统计任务，系统执行结束统计任务，参见Statistic.End** |
| **Statistic.Del.Null<br>Statistic.Del.Movies**               | **在工作人员未输入任何电影就输入删除已输入电影命令时，系统不予响应<br>在工作人员删除输入电影时，系统在输入框中删除相应内容** |
| **Statistic.Movies<br>Statistic.Movies.Amount**              | **系统显示输入电影的信息<br>系统显示电影的想看人数**         |
| **Statistic.End<br>Statistic.End.Timeout** <br>**Statistic.End.Update** <br>**Statistic.End.Close** | **系统应该允许工作人员要求结束统计任务<br>在统计人数开始2个小时后还没有接到工作人员请求时，系统取消统计任务** <br>**在工作人员要求结束统计任务时，系统更新数据,参见Statistic.Update**<br> **在工作人员确认统计任务完成时，系统关闭统计任务，参见Statistic.Close** |
| **Show.Update**                                              | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新** |
| **Statistic.Close.Next**                                     | **系统关闭本次统计任务，开始新的统计任务**                   |



#### 4.2.3  查看电影详情

##### 4.2.3.1 特征描述

观众浏览影片列表时，点击详情链接获取影片信息。

优先级=低

##### 4.2.3.2 刺激/响应序列

刺激：观众点击影片详情链接。

响应：系统显示影片信息，包括电影海报，名称，上映时间，演职人员，内容简介。

刺激：观众点击退出详情页面。

响应：系统返回上一页面。

##### 4.2.3.3 相关功能需求

| Browse.Click<br>Browse.Click.Detail     | 系统应该允许观众在浏览影片列表时使用鼠标点击<br>在观众使用鼠标点击详情链接时，系统执行显示详情任务,参见Browse.Detail |
| :----------------- | :--------------------|
| **Browse.Detail <br>Browse.Detail.End** | **系统显示影片信息，包括电影海报，名称，上映时间，演职人员，内容简介，想看人数 <br>在观众请求结束查看电影详情时，系统返回影片列表界面，参见Browse.End** |
| **Browse.End<br>Browse.End.Close**      | **系统应该允许观众要求结束显示详情任务<br>在观众确认显示详情任务完成时，系统关闭显示详情任务，参见Browse.Close** |
| **Browse.Close**                        | **系统关闭本次任务**                                         |



#### 4.2.4  标记某电影为想看

##### 4.2.4.1 特征描述

观众查看影片详情时，将该电影标记为“想看”。

优先级=中

##### 4.2.4.2 刺激/响应序列

刺激：观众点击“想看”标记。

响应：系统更新想看人数，“想看”标记("空心“)变成被标记状态(”红心“)。

刺激：观众取消“想看”标记。

响应：系统更新想看人数，“想看”标记(”空心“)变成未被标记状态("红心")。

##### 4.2.4.3 相关功能需求

| **Mark.Click <br>Mark.Click.Sign  **                             | 系统应该允许观众使用鼠标点击<br>在观众用鼠标点击”想看“标记 时,系统执行标记任务，参见Mark.Sign |
| :----------------- | :--------------------|
| **Mark.Sign<br>Mark.Sign.Yes<br>Mark.Sign.No<br>Mark.Sign.End** | **系统显示“想看“标记<br>在观众点击标记表示想看时，"想看”标记("空心“)变成被标记状态(”红心“)<br>在观众点击标记取消想看时，“想看”标记(”红心“)变成未被标记状态("空心")<br>每次点击后，系统执行结束标记任务，参见Mark.End** |
| **Mark.End.Update<br>Mark.End.Close**                        | **结束标记任务时,系统更新数据，参见Mark.Update<br>系统关闭标记任务，参见Mark.Close** |
| **Mark.Update<br>Mark.Update.Amount**                        | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新<br>系统更新想看人数** |
| **Mark.Close**                                               | **系统关闭本次任务**|



#### 4.2.5  搜索电影

##### 4.2.5.1 特征描述

观众登录进入系统后可以根据关键词搜索想要了解的电影。

优先级=中

##### 4.2.5.2 刺激/响应序列

刺激：观众输入关键词。

响应：系统根据关键词显示查找到的电影并存储搜索记录。

刺激：观众取消搜索任务。

响应：系统关闭搜索任务。

刺激：观众删除已输入内容。

响应：系统在输入框中删除该内容。

刺激：观众删除搜索记录。

响应：系统在存储列表中删除该纪录。

##### 4.2.5.3 相关功能需求

| Search.Input<br>Search.Input.Return<br>Search.Input.Records<br>Search.Input.Cancel<br>Search.Input.Del<br>Search.Input.Movies<br>Search.Input.Invalid | 系统应该允许观众进行键盘输入<br>在观众结束输入后，系统执行返回搜索结果任务，参见Search.Return<br>在观众结束输入后，系统执行变更搜索记录任务,参见Search.Records<br>在观众输入取消命令时，系统关闭当前搜索任务，开始新的搜索任务<br>在观众输入删除已输入内容命令时，执行删除已输入内容命令，参见Search.Del<br>在观众输入电影列表中存在的关键词时，系统执行关键词输入任务，参见Search.Movies<br>在观众输入无效关键词时，系统显示输入无效 |
| :----------------- | :--------------------|
| **Search.Return.Null<br>Search.Return.Movies<br>Search.Return.End<br>** | **在观众未输入任何内容时，系统不做任何处理<br>在观众输入内容后结束输入时，系统要执行返回搜索结果任务<br>系统完成返回结果任务后，观众可以请求结束搜索任务，系统执行结束搜索任务处理，参见Search.End** |
| **Search.Records.Null**<br>**Search.Records.List**<br>**Search.Records.Del**<br>**Search.Records.End**<br> | **在观众未输入任何内容时，系统不做任何处理**<br>**在观众点击搜索历史记录时，系统显示记录列表**<br>**在观众进行删除搜索记录时，系统删除该记录**<br>**在系统完成搜索记录变更任务后，观众可以请求结束变更搜索记录任务，系统执行结束变更搜索记录任务** |
| **Search.Del.Null<br>Search.Del.Records**<br> **Search.Del.Movies** | **在观众未输入任何内容就删除输入内容时，系统不予响应<br>在观众从历史搜索记录列表中选择待删除记录时，系统在历史纪录列表中删除该记录**<br>**在观众从已输入内容中删除部分内容时，系统在输入框中删除相应内容** |
| **Search.Movies<br>Search.Movies.Information**               | **系统在输入框中显示已输入的内容<br>系统显示电影信息**       |
| **Search.End<br>Search.End.Timeout<br>Search.End.Update<br>Search.End.Close** | **系统应该允许观众要求结束搜索任务<br>在搜索开始2个小时后还没有接到观众请求时，系统取消搜索任务<br>在观众要求结束搜索任务时，系统更新数据,参见Search.Update<br>在观众确认搜索任务完成时，系统关闭搜索任务，参见Search.Close** |
| **Search.Update<br>Search.Update.Records**                   | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新<br>系统更新存储信息** |
| **Search.Close.Next**                                        | **系统关闭本次搜索任务，开始新的搜索任务**                   |

#### 4.2.6 登录注册

##### 4.2.6.1 特征描述

若用户已有账户，用户可直接通过用户名密码登录进入系统；若用户没有账户，用户需要注册一个账户，然后便可以用注册的用户名密码登录进入系统。

优先级=高

##### 4.2.6.2 刺激/响应序列

刺激：用户输入用户名和密码，点击登录按钮

响应：系统显示登录后的界面

刺激：用户点击立即注册

响应：系统显示用户名输入框，密码输入框，密码再次确认输入框

刺激：用户输入用户名和密码，点击注册按钮

响应：系统显示注册成功

刺激：用户点击立即登录

响应：系统显示登录界面

##### 4.2.6.3 相关功能需求

| SignUp.Input<br>SignUp.Input.Login<br>SignUp.Input.Register<br>SignUp.Input.Valid<br>SignUp.Input.Invalid<br>SignUp.Input.None<br>SignUp.Input.Error | 系统应该允许用户进行键盘输入<br>用户点击"立即登录"按钮，系统进入登录界面，参见SignUp.Login<br>用户点击"立即注册"按钮，系统进入注册界面,参见SignUp.Register<br>用户输入用户名和密码，系统进入影院界面<br>用户输入其他标识时，系统显示输入无效，参见Format3,Format4<br>用户输入的用户名不存在时，系统显示用户名不存在<br>用户输入的用户名存在但密码错误，系统显示密码错误 |
| :----------------- | :--------------------|
| **SignUp.Login.Valid**<br>**SignUp.Login.Invalid**<br>**SignUp.Login.End** | **用户输入用户名和密码，系统进入影院界面，参见SignUp.Input**<br>**用户输入的用户名和密码无效时，系统显示输入无效，参见SignUp.Input**<br>**用户可以请求结束登录任务，参见SignUp.End** |
| **SignUp.Register.Valid**<br>**SignUp.Register.Invalid**<br>**SignUp.Register.Existed**<br>**SignUp.Register.Unconformity**<br>**Sign.Up.Register.End** | **用户输入用户名，密码，和再次确认密码，系统显示注册成功**<br>**用户输入其他标识时，系统显示输入无效，参见Format3,Format4**<br>**用户输入的用户名已经存在，系统显示已经有人用过这个名字**<br>**用户两次输入的密码不同，系统显示两次输入密码必须一样<br>用户可以请求结束注册任务，参见SignUp.End** |
| **SignUp.End**<br>**SignUp.End.Update**<br>**SignUp.End.Close** | **系统应该允许用户要求结束登录注册任务**<br>**在用户要求结束登录注册任务时，系统更新数据,参见SignUp.Update**<br>**在用户确认登录注册任务完成时，系统关闭登录注册任务，参见SignUp.Close** |
| **SignUp.Update**<br>**SignUp.Update.Information**<br><br>**SignUp.Update.Amount** | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新**<br>**系统更新用户个人信息，包括观众：搜索记录，想看电影；影院工作人员：上架电影记录，搜索记录**<br>**系统更新用户数量** |
| **SignUp.Close**                                             | **系统关闭本次登录注册任务**|

#### 3.2.7 添加排片信息

##### 3.2.7.1 特征描述

管理员根据影厅，日期，来添加电影的排片信息，并注意处理排片冲突。

优先级=中

##### 3.2.7.2 刺激/响应序列

刺激：用户请求添加排片信息

响应：系统显示添加排片信息的页面并提示用户选择日期和影厅

刺激：用户选择日期和影厅并确认

响应：系统展示该日期下该影厅可供排片的时间段和影片列表

刺激：用户选择时间段和影片，并填写票价后确认

响应：系统提示添加成功

##### 4.2.7.3 相关功能需求
| Schedule.Click <br>Schedule.Input<br>Schedule.Click.Choose                               | 系统应该允许管理员使用鼠标点击<br>系统应该允许管理员使用键盘输入<br>在管理员用鼠标点击排片后,系统执行任务，参见Schedule.choose |
|:----------------- | :--------------------|
| **Schedule.Choose<br>Schdule.Choose.Date<br>Schedule.Choose.Hall<br>Schedule.Choose.Price<br>Schedule.Choose.End** | **系统显示排片信息的选择页面<br>管理员选择要排片的时间<br>管理员选择要排片的影厅<br>管理员手动输入票价并确认<br>每次点击后，系统执行排片结束任务，参见Schedule.End** |
| **Schedule.End.Update<br>Schedule.End.Close**                        | **结束排片任务时,系统更新数据，参见Schedule.Update<br>系统关闭排片任务任务，参见Schedule.Close** |
| **Schedule.Update<br>Schedule.Update.Amount**                        | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新<br>系统更新排片信息** |
| **Schedule.Close**                                               | **系统关闭本次排片任务**|

#### 4.2.8 查看排片信息

##### 4.2.8.1 特征描述

可以根据日期，影厅来查看排片信息，并能切换影厅和影片视角

##### 4.2.8.2 刺激/响应序列

刺激：用户请求查看排片信息

响应：系统提示用户选择日期、影厅及视角

刺激：用户选择日期、影厅和视角并确认

响应:系统根据用户的选择，按照所选视角展示该日期和影厅的排片信息表

##### 4.2.8.3 相关功能需求

| Schedule.User.Click <br>Schedule.User.Click.Choose                               | 系统应该允许用户使用鼠标点击<br>在用户用鼠标点击排片后,系统执行任务，参见Schedule.User.choose |
| :----------------- | :--------------------|
| **Schedule.User.Choose<br>Schdule.User.Choose.Date<br>Schedule.User.Choose.Hall<br>Schedule.User.Choose.End** | **系统显示排片信息的选择页面<br>用户选择要查看的时间<br>用户选择要查看的影厅<br>每次点击后，系统执行排片结束任务，参见Schedule.User.End** |
| **Schedule.User.End.Show<br>Schedule.User.End.Close**                        | **系统根据选择的日期和影厅呈现排片信息表<br>系统关闭排片任务任务，参见Schedule.Close** |
| **Schedule.User.Close** | **系统关闭本次查看排片任务** |


#### 4.2.9 查看统计数据

##### 4.2.9.1 特征描述

影院可以查看电影的各项统计数据，有助于排片决策

##### 4.2.9.2 刺激/响应序列

刺激：用户请求查看统计数据

响应：系统提示用户选择想查看的统计数据

刺激：用户选择想查看的统计数据并确认

响应: 系统展示该项统计数据

##### 4.2.9.3　相关功能需求
| Statistics.Click <br>Statistic.Input<br>Statistic.Click.Choose | 系统应该允许用户使用鼠标点击<br>系统应该允许用户用键盘输入<br>在用户用鼠标点击排片后,系统执行任务，参见Statistics.Show |
| :----------------- | :--------------------|
| **Statistics.Show<br>**                                      | **系统显示统计信息<br>系统执行排片结束任务，参见Statistics.Close** |
| **Statisics.Close** | **系统关闭本次查看统计数据任务** |


#### 4.2.10 发布优惠活动

##### 4.2.10.1 特征描述

影院可以发布优惠活动来促进观众购票消费

##### 4.2.10.2 刺激/响应序列

刺激：用户请求发布优惠活动

响应 : 系统提示输入活动进行时间区间，优惠券的使用有效期，优惠金额及参与条件

刺激：用户输入活动进行时间区间，优惠券使用有效期，优惠金额，参与条件并确认

响应: 系统提示优惠活动发布成功

##### 4.2.10.3 相关功能需求

| **Coupon.Click<br>Coupon.Input<br>Coupon.Input.Condition  **                             | 系统应该允许用户使用鼠标点击<br>系统应该允许用户使用键盘输入<br>在用户用鼠标点击发布优惠活动时系统执行任务，参见Coupon.Condition |
| :----------------- | :--------------------|
|**Coupon.Condition<br>Coupon.Condition.Date<br>Coupon.Condition.validity<br>Coupon.Condition.Price<br>Coupon.Condition.Require<br>Coupon.End** | **系统显示发布优惠活动页面<br>用户选择该优惠活动的时间区间<br>用户选择该优惠活动的有效期<br>用户选择该优惠活动的参与条件<br>用户介绍发布优惠活动，详情见Coupon.End** |
| **Coupon.End.Update<br>Coupon.End.Close**                        | **结束发布活动后,系统更新数据，参见Coupon.Update<br>系统关闭发布任务，参见Coupon.Close** |
| **Coupon.Update<br>Coupon.Update.Event**                        | **系统更新重要数据，整个更新过程组成一个事务，要么全部更新，要么全部不更新<br>系统将活动上线** |
| **Coupon.Close**                                               | **系统关闭本次任务**                                         |


#### 4.2.11 购买会员卡

##### 4.2.11.1 特征描述

会员卡仅支持充值优惠，无折扣

##### 4.2.11.2 刺激/响应序列 

刺激：用户点击购买会员卡

响应：系统显示会员卡特权信息，包括价格、充值优惠信息等

刺激：用户点击购买

响应：系统进入订单确认界面

刺激：用户确认订单，系统跳转第三方支付界面

响应：用户输入密码，确认支付后系统显示更新后的会员卡信息

##### 4.2.11.3 相关功能需求

| **VIPCard.Click<br>Vipcard.Input<br>Vipcard.show<br>Vipcard.Click.Buy  **                             | 系统应该允许用户使用鼠标点击<br>系统应该允许用户使用键盘输入<br>系统显示会员卡的特权信息<br>在用户用鼠标点击购买会员卡时系统执行任务，参见Vipcard.Buy |
| :----------------- | :--------------------|
| **Vipcard.Buy<br>Vipcard.Buy.Input<br>Vipcard.End** | **系统显示购买会员卡窗口<br>用户输入银行卡号和密码进行购买<br>用户完成购买详情见VIPcard.End<br>** |
| **Vipcard.End.Update<br>Coupon.End.Close**                        | **结束购买后,系统更新用户状态，参见Vipcard.Update<br>系统关闭购买任务，参见Vipcard.Close** |
| **Vipcard.Update<br>Vipcard.Update.Button**                        | **系统更新用户状态，成为会员<br>系统将购买会员卡按钮更新为充值会员卡** |
| **VipCard.Close**                                               | **系统关闭本次任务**                                         |


#### 4.2.12 充值会员卡

##### 4.2.12.1 特征描述

用户完成了购买会员卡后，成为会员。此时用户可以选择充值金额或手动输入金额

##### 4.2.12.2 刺激/响应序列

刺激：用户进入“卡包界面”

响应：系统显示会员卡信息

刺激：用户点击充值

响应：系统显示充值金额选择界面

刺激：用户点击充值金额或输入充值金额

响应：系统显示订单信息，包括充值金额、支付金额等信息

刺激：用户确认订单，进行支付

响应：系统跳转第三方支付界面

刺激：用户输入密码，完成支付

响应：系统显示更新后的会员卡信息

##### 4.2.12.3 相关功能需求
| **VIPCard.Click<br>Vipcard.Input<br>Vipcard.show<br>Vipcard.Click.Recharge  **                             | 系统应该允许用户使用鼠标点击<br>系统应该允许用户使用键盘输入<br>系统显示会员卡的特权信息<br>在用户用鼠标点击充值会员卡时系统执行任务，参见Vipcard.Recharge |
|:----------------- | :--------------------|
| **Vipcard.Recharge<br>Vipcard.Recharge.Input<br>Vipcard.End** | **系统显示充值会员卡窗口<br>用户输入银行卡号和密码进行充值<br>用户完成购买详情见VIPcard.End<br>** |
| **Vipcard.End.Update<br>Vipcard.End.Close**                        | **结束购买后,系统更新用户会员卡信息，参见Vipcard.Update<br>系统关闭购买任务，参见Vipcard.Close** |
| **Vipcard.Update**                        | **系统更新用户会员卡信息，更新余额** |
| **VipCard.Close**                                               | **系统关闭本次任务**                                         |


#### 4.2.13 发布会员卡

##### 4.2.13.1 特征描述

影院员工选择发布会员卡充值优惠策略，会员卡：会员卡可用于购票，享有优惠。

##### 4.2.13.2 刺激/响应序列

刺激：影院员工选择发布会员卡充值优惠策略

响应：系统跳转到发布会员卡页面

刺激： 用户输入相关信息并确认

响应 ： 系统完成录入会员卡信息并发布

##### 4.2.13.3 相关功能需求

| **VIPCard.Click<br>Vipcard.Input<br>Vipcard.show<br>Vipcard.Click.Release  **                             | 系统应该允许用户使用鼠标点击<br>系统应该允许用户使用键盘输入<br>系统显示当前发布的会员卡信息<br>在用户用鼠标点击发布会员卡时系统执行任务，参见Vipcard.Release |
|:---------------- | :--------------------|
| **Vipcard.Release<br>Vipcard.Release.Input<br>Vipcard.End** | **系统显示发布会员卡窗口<br>用户输入发布的会员卡的相关信息<br>用户完成发布详情见VIPcard.End<br>** |
| **Vipcard.End.Update<br>Vipcard.End.Close**                        | **结束购买后,系统更新用户购买会员卡的信息，参见Vipcard.Update<br>系统关闭购买任务，参见Vipcard.Close** |
| **Vipcard.Update**                        | **系统更新用户会员卡信息，更新价格** |
| **VipCard.Close**                                               | **系统关闭本次任务**                                         |


#### 4.2.14 查看历史纪录

##### 4.2.14.1 特征描述

该页面供用户查看历史充值记录，历史消费记录，每条记录附有查看详情按钮供用户查看该记录的详情。

##### 4.2.14.2 刺激/响应序列

刺激：用户查看充值记录

响应：系统展示用户充值记录

刺激：用户选择某一项充值记录查看详情

响应：系统展示该用户选择的记录的详情

##### 4.2.14.3 相关功能需求

| **History.Click<br>History.show<br>History.Click.Detail  **                             | **系统应该允许用户使用鼠标点击<br>系统显示当前的历史记录和消费记录<br>在用户用鼠标点击查看详情时系统执行任务，参见Vipcard.Detail** |
| :----------------- | :-------------------- |
| **Vipcard.Detail<br>Vipcard.Detail.show<br>Vipcard.End** | **系统显示详情窗口<br>用户输入发布的会员卡的相关信息<br>用户完成查看详情见History.Close<br>** |
| **VipCard.Close**                                               | **系统关闭本次任务**                                         |

#### 4.2.15 用户退票

##### 4.2.15.1 特征描述

当订单已支付并未出票时，用户可以在线退票

##### 4.2.15.2 刺激/响应序列

刺激：用户进入“我的电影票”界面

响应：系统显示该用户最近的购买电影票记录

刺激：用户选择电影票并选择退票

响应：系统确定该符合退票规则后，进行退票。

##### 4.2.15.3 相关功能需求

| **Ticket.Click<br>Ticket.show<br>Ticket.Click.Refund  **                             | **系统应该允许用户使用鼠标点击<br>系统显示当前发布的会员卡信息<br>在用户用鼠标点击退票时系统执行任务，参见Ticket.Refund** |
| :----------------- | :--------------------|
| **Ticket.Refund<br>Ticket.Refund.Click<br>Ticket.End** | **系统显示最近购买电影票信息<br>用户选择某个电影票进行退票<br>系统接受并完成退票详情见Ticket.End<br>** |
| **Ticket.End.Update<br>Vipcard.End.Close**                        | **结束购买后,系统更新用户购买电影票记录的信息，参见Ticket.Refund.Update<br>系统关闭此任务，参见Vipcard.Close** |
| **Ticket.Refund.Update**                        | **系统更新购买电影票列表，删除该信息** |
| **Ticket.Refund.Close**                                               | **系统关闭本次任务**                                         |




### 4.3 非功能需求


#### 4.3.1  安全性

**Safety1:** 系统应该按照用户身份验证用户的访问权限：观众和影院排片人员的访问权限参见功能需求3.2

**Safety2：** 系统应该设定一个默认的秘钥，用于验证影院排片人员注册账号

**Safety3：** 系统又一个默认的管理员账户，该账户只用于修改系统的验证秘钥



#### 4.3.2  可维护性

**Modifiability1：**在系统的秘钥发生改变时，系统要能够4人在1天内完成

**Modifiability2：**如果登录、注册的数据格式发生改变，系统要能够在1人1天内完成

**Modifiability3：**如果电影要增加除开已有信息以外的信息等(例如当前票房等)，系统要能够在0.25个人月内完成

**Modifiability4：**如果系统要增加新的功能(如购买电影票、发布优惠条件等)，系统要能够在8个人月内完成


#### 4.3.3  易用性

**Usability1：**初次接触系统的顾客能够通过前往帮助页面知道如何搜索电影、查看电影详情和标记电影为想看

**Usability2：**使用系统1个月内的影院排片人员进行排片的效率要达到2部/小时，错误率要小于1%



#### 4.3.4  可靠性

**Reliability1:** 在客户端与服务器通信时，如果网络故障，系统不能出现故障。

* **Reliability1.1：**客户端应该检测到故障，并尝试重新连接网络3次，每次15秒。

* **Reliability1.1.1：**重新连接后，客户端应该继续之前的工作。

* **Reliability1.1.2：**如果重新连接不成功，客户端应该等待5分钟后再次尝试重新连接。

* **Reliability1.1.2.1：**重新连接后，客户端应该继续之前的工作。

* **Reliability1.1.2.2：**如果重新连接仍然不成功，客户端报警。


#### 4.3.5  业务规则

**BR1：**适用(电影名，参照日期)的电影搜索
>(电影名=电影列表中某部电影的电影名)而且((搜索日期晚于等于电影上架日期)或者(搜索日期晚于等于电影预售日期))
**BR1：**适用(电影名，参照日期)的电影查看详情
>(电影名=电影列表中某部电影的电影名)而且((搜索日期晚于等于电影上架日期)或者(搜索日期晚于等于电影预售日期))

**BR3：**适用(电影名，参照日期)的被标记想看电影
>(电影名=电影列表中某部电影的电影名)而且(((搜索日期晚于等于电影上架日期)而且(搜索日期早于等于电影下架日期))或者((搜索日期晚于等于电影预售日期)而且(搜索日期早于等于电影上架日期)))




#### 4.3.6  约束

**IC1：**系统要部署在两个及以上的服务器上，在服务器宕机时能够及时切换服务器解决
**IC2:**系统应当支持多个客户端连接同一服务器



### 4.4  数据需求


#### 4.4.1  数据定义

**DR1：**系统需要存储的数据实体及其关系参见用例文档附图

**DR2：**系统需要存储1年内的电影上架和下架记录

**DR3：**系统需要存储用户的基本信息，包括用户名和密码

**DR4：**系统需要存储电影的基本信息，包括电影编号、名称、简介、宣传图片、上映日期、类型、地区、语言、导演、主演、编剧和相关排片信息

**DR5：**系统需要存储电影被想看的次数以及想看电影的用户的用户名



#### 4.4.2  默认数据

默认数据用于以下四种情况：
- 新上架的电影信息缺项
- 新预售的电影信息缺项
- 排片人员编辑数据时不小心清空了电影相关数据
- 用户登陆后时不小心清空了个人信息

**Default1：**电影名称默认为编号
**Default2：**简介默认为编号

**Default3：**宣传图片默认为影院的宣传图片

**Default4：**电影上映日期默认为当天

**Default5：**类型默认为编号

**Default6：**地区默认为无

**Default7：**语言默认为无

**Default8：**导演默认为编号

**Default9：**主演默认为编号

**Default10：**编剧默认为编号

**Default11：**想看标记默认为不想看

**Default12：**排片信息默认为暂无排片

**Default13：**用户名默认为用户A



#### 4.4.3  数据格式要求

**Format1：**用户名长度为4-10个字符，

**Format2：**密码长度为6-12个字符

**Format3：**电影上映日期的格式是yyyy-mm-dd hh:mm:ss

**Format4：**电影类型的格式是tt/tt，如动作、动作/喜剧、动作/喜剧/动画

**Format5：**电影导演的格式是dd/dd，如张艺谋、张艺谋/冯小刚、张艺谋/冯小刚/周星驰

**Format6：**电影主演的格式是aa/aa，如汤姆·克鲁斯、汤姆·克鲁斯/强·沃特、汤姆·克鲁斯/强·沃特/亨利·科泽尼

**Format7:**电影海存储为电影海报的url

### 4.5  其他需求

安装需求

**Install1：**在安装系统时，要初始化系统密钥、电影库等数据

**Install2：**系统投入使用时，需要对排片人员进行3天的培训
