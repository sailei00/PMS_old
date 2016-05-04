1、项目发布在Tomcat服务器上，必须设置为Tomcat的默认app，否则系统无法正常使用
2、在Tomcat的 conf\server.xml中，必须在 <Connector connectionTimeout="20000"  URIEncoding="UTF-8" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>中，
		配置URIEncoding="UTF-8"，否则get方式的请求中的中文会有乱码
3、部署应用时要修改jdbc.properties中的数据库信息
4、数据库使用mysql-installer-community-5.6.28.0，5.7.x版本在windows server 2008下安装会报错。