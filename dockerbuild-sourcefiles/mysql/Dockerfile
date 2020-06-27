FROM mysql:5.7.30

#添加数据库
ENV MYSQL_DATABASE ofo

COPY ofo.sql /docker-entrypoint-initdb.d/

