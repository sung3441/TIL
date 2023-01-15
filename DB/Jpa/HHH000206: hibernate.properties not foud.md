# HHH000206: hibernate.properties not foud 에러 해결

jdk 11 + jpa를 사용하면 HHH000206 에러가 발생한다.  

### 원인, 해결

jdk 11에서는 JAXB 라이브러리를 내장하고 있지 않아서 의존성을 따로 추가를 해야한다.

```
// maven
<denpency>
  <groupId>javax.xml.bind</groupId>
  <artifactId>jaxb-api</artifactId>
  <version>${jaxb.version}</version>
</denpency>
```
