# Spring boot에서 resource cache 설정

```
# application.properties
spring.web.resources.cache.period=60 # resource에 대해서 60초 동안 캐시
```
```java
// java 코드로 설정
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(60);
    }
}
```
