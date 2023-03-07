# A problem occurred configuring root project ' ' ~~~

```
A problem occurred configuring root project ''.
> Could not resolve all files for configuration ':classpath'.
   > Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.0.4.
     Required by:
         project : > org.springframework.boot:org.springframework.boot.gradle.plugin:3.0.4
      > No matching variant of org.springframework.boot:spring-boot-gradle-plugin:3.0.4 was found. The consumer was configured to find a runtime of a library compatible with Java 8, packaged as a jar, and its dependencies declared externally, as well as attribute 'org.gradle.plugin.api-version' with value '7.6.1' but:
```

- start.spring.io에서 spring boot 3.x 버젼으로 받았는데 build에 계속 실패한다.
- 3.x 버젼은 자바 17부터 지원하는데 11을 쓰고 있어서 발생한 문제였다.
- java를 17로 올리거나 spring boot 2.x를 쓰자
