#依赖镜像
FROM nginx:1.18

#复制配置文件，设置为默认配置
COPY nginx.conf /etc/nginx/nginx.conf

#复制静态web页面
COPY springcloud-ofo /usr/share/nginx/html/springcloud-ofo/

#指定工作目录
#WORKDIR /usr/share/nginx/html

#运行指令
CMD ["nginx","-g","daemon off;"]

