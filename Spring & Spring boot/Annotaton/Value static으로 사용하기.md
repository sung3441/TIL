# @Value 변수 static으로 사용하기

__@Value__ 어노테이션을 쓰면 Profile 별로 분리된 설정 파일의 값을 가져올 수 있다.  
하지만 이 @Value 어노에티션를 static 변수에 적용하면, static 변수에는 항상 null이 반환된다.  

```java
@Component
public class PropertiesUtil {
  
    @Value("${test.value}")
    public static String value;
    
}
```

위 코드에서 value 변수의 값은 항상 null이다.  

다음 코드로 이를 해결할 수 있다.

```java
@Component
public class PropertiesUtil {
    
    public static String value;
  
    @Value("${test.value}")
    public void setValue(String newValue) {
        value = newValue;
    }
}
```

이제 ```PropertiesUtil.value```로 사용할 수 있다.
