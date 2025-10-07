# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.7-SNAPSHOT/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.7-SNAPSHOT/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.7-SNAPSHOT/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


### add JAVA 17 bash
* sudo apt update
* sudo apt install openjdk-17-jdk
* sudo update-alternatives --config java
* export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
* export PATH=$JAVA_HOME/bin:$PATH
* source ~/.bashrc
* java -version

### add JAVA 17 For APPLE MAC zshell
* brew install openjdk@17
* export PATH="/usr/local/opt/openjdk@17/bin:$PATH"
* export JAVA_HOME=$(/usr/libexec/java_home -v 17)
