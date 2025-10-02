to check if you run the data base here what to do:

- mysql -h sulnwdk5uwjw1r2k.cbetxkdyhwsb.us-east-1.rds.amazonaws.com -u uwx7fxs8lp119wxj -p
    - password in properties
- mysql> SHOW DATABASES (to see what databases)
- USE gsa87t3oul4l1w46 (we in that db)
- SHOW TABLES

# MySQL configuration for main application
spring.datasource.url=jdbc:mysql://sulnwdk5uwjw1r2k.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/mlu4yl93793v3hqy
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=t9c12j62077nwvgi
spring.datasource.password=aik2kseiahl8p48z
spring.jpa.hibernate.ddl-auto=update

# Database connection pool settings
spring.datasource.hikari.maximum-pool-size=3
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=1200000