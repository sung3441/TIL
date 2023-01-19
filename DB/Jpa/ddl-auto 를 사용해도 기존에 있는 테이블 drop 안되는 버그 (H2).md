# ddl-auto 를 사용해도 기존에 있는 테이블 drop 안되는 버그 (H2)

H2 최신 버전에서 발생하는 문제이다.

### 해결법

H2 데이터베이스를 1.4.199 버전 혹은 그 이전 버전을 사용하면 된다.  
이전 버전을 사용해도 여전히 안되거나 최신 버전을 사용해야 되는 경우  
hibernate 버전을 5.4.13.Final로 변경하면 테이블이 정상적으로 drop된다.

```xml
// pom.xml
<dependencies>
    <!-- JPA 하이버네이트 -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>5.4.13.Final</version>
    </dependency>
    
    // ...
</dependencies>
```
