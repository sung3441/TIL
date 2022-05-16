# @Valid 사용 시 공백이 껴있을 때

> @Valid 어노테이션으로 객체를 검증할 때 검증 대상에 공백이 껴있으면 유효성에 어긋나는 경우가 있다.  
> 따라서 @Valid에 접근하기 전 공백을 처리할 수 있는 구간이 필요하다.

<br>

# @InitBinder

> @InitBinder 어노테이션을 붙여서 객체를 가져오기 전 수행할 메서드를 지정할 수 있다.  
> 기존에는  Validator 인터페이스를 구현한 클래스를 만들고, validate() 메서드를 호출했다고 한다.  
> 공백을 제거하는 메서드를 다음처럼 작성할 수 있다.

``` java
@InitBinder
public void initBinder(WebDataBinder dataBinder) { // WebDataBinder이란 커맨드 객체를 바인딩하는 객체
  StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
  dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
}
```

# References
- [@InitBinder와 WebDataBinder의 쓰임](https://live-everyday.tistory.com/m/227)
