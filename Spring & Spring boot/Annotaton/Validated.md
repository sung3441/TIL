# @Validated groups 그룹별 유효성 검사

하나의 객체를 가지고 여러 상황에서 @Valid 유효성 검사를 할 때 문제가 생기는 부분이 있다.

```java
public class User {

    @NotEmpty(message = "사용자명은 필수 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "그룹명은 필수 값입니다.")
    private String groupName;
}
```

위 객체를 보면 사용자명과 이메일, 그룹명은 @NotEmpty 필수 값이다.  
사용자를 직접 추가할 때는 사용자명, 이메일을 입력하고 부서를 선택해주기 때문에 문제가 없다.

```java
@PostMapping(value = "/users")
public Response signIn(@Valid User user) {
    // ... 유저 추가 로직
}
```

위 코드처럼 @Valid를 추가하면 어노테이션으로 달려있는 유효성 검증을 진행한다.  
문제가 생기는 상황은 다음이었다.

### 사용자 엑셀로 가져오기

사용자를 엑셀로 가져올 시 이름, 이메일, 그룹명을 입력받아 추가하는 형식인데. 그룹명이 없을 시 전체 부서로 입력된다.

```java
@PostMapping(value = "/users/excel")
public Response excelImport(@Valid List<User> userList) {
    // ... 엑셀 처리 로직
}
```

위 코드처럼 @Valid로 유효성 검사를 진행하면 GroupName이 없기 때문에 MethodArgumentNotValidExcetion이 발생.  

따라서 Excel을 가져와 처리하는 메서드에서 다른 필드는 검사하되 GroupName의 유효성은 검증하고 싶지 않았다.

### 해결 방법

@Validated 를 사용하면 groups 별로 유효성을 처리할 수 있었다. 먼저 그룹으로 묶을 interface, class가 필요하다.

```java
public class ValidateGroups {
    public interface UserAddGroup {}
}
```
```java
public class User {

    @NotEmpty(message = "사용자명은 필수 값입니다.", groups = ValidateGroups.group.class)
    private String name;
    
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;
  
    @NotEmpty(message = "그룹명은 필수 값입니다.")
    private String groupName;
}
```

기존 유효성 검증 어노테이션은 따로 groups 미설정 시 Default.class 이라는 그룹에 속해있다.  
이제 @Valid를 사용하면 기본그룹만 검증하므로 GroupName 필드도 검증하고 싶으면 다음 처럼 코드를 수정해야한다.

```java
@PostMapping(value = "/users")
public Response signIn(@Validated({Default.class, ValidateGroups.UserAddGroup.class}) User user) {
    // ... 유저 추가 로직
}

@PostMapping(value = "/users/excel")
public Response excelImport(@Valid List<User> userList) {
    // ... 엑셀 처리 로직
}
```

@Validated를 사용하고 인자로 검증할 그룹을 넘겨주면 된다. Default 그룹 외에도 group이 달려있는 필드도 검증한다.  
excel에서 사용자를 가져올 때 @Valid를 사용하면 Default 그룹만 검증하기 때문에 groupName의 검증을 피할 수 있다.
