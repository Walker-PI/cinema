#!/usr/bin/env bash
# 编译+部署cinema站点

# 需要配置如下参数
# 项目路径, 在Execute Shell中配置项目路径, pwd 就可以获得该项目路径
# export PROJ_PATH=这个jenkins任务在部署机器上的路径

# 输入你的环境上tomcat的全路径
# export TOMCAT_APP_PATH=tomcat在部署机器上的路径

### base 函数
killTomcat()
{
    pid=`ps -ef|grep tomcat|grep java|awk '{print $2}'`
    echo "tomcat Id list :$pid"
    if [ "$pid" = "" ]
    then
      echo "no tomcat pid alive"
    else
      kill -9 $pid
    fi
}


# 不用maven构建时需要的maven命令
cd $PROJ_PATH/cinema
 mvn clean install -DskipTests
#mvn clean install

# 重命名
mv $PROJ_PATH/cinema/target/cinema-0.0.1-SNAPSHOT.war $PROJ_PATH/cinema/target/cinema.war


# 停tomcat
# killTomcat


# 删除原有工程
rm -rf $TOMCAT_APP_PATH/webapps/ROOT
rm -f $TOMCAT_APP_PATH/webapps/ROOT.war
rm -f $TOMCAT_APP_PATH/webapps/cinema.war

# 复制新的工程
cp $PROJ_PATH/cinema/target/cinema.war $TOMCAT_APP_PATH/webapps/

cd $TOMCAT_APP_PATH/webapps/

mv cinema.war ROOT.war

# 启动Tomcat
cd $TOMCAT_APP_PATH/
sh bin/startup.sh

#cd $TOMCAT_APP_PATH/webapps/
#java -jar ROOT.war --httpPort=80


