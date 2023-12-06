# Record

레코드는 불변 객체를 쉽게 생성할 수 있는 클래스이다.  
JDK14에서 등장했고 JDK 16에서 정식 스펙으로 포함되었다고 한다.  

## Record 사용 전

```java
public final class Member {
    private final String name;
    private final int age;
    
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
```

Record를 사용하기 전 Member 클래스를 만든다면 다음과 같이 만들 것이다.  
- 모든 필드와 클래스는 final
- 모든 필드를 주입 받는 생성자
- 필드에 접근할 수 있는 메서드 (getter)

## Record 사용 후

```java
public record Member(String name, int age) {
}
```

Record를 사용하면 다음 내용을 생성해준다.
- 필드 캡슐화
- 생성자 메서드
- getters()
- equals()
- hashCode()
- toString()

컴파일러가 컴파일 타임에 코드를 추가하기 때문에 직접 명시하지 않아도 사용할 수 있는 것이다.  
차이점이 있다면 레코드는 필드를 가져올 때 필드 이름을 그대로 사용한다.

```java
public class Main {
    public static void main(String[] args) {
        Member member = new Member("java", 10);
        String name = member.name();
        int age = member.age();
    }
}
```

## Record를 JPA Entity로 사용?

레코드는 final 클래스이고, abstract로 선언할 수 없다. JPA의 지연로딩을 사용하려면 엔티티 객체의 프록시 객체를 생성하는데,  
프록시 객체는 원본 객체를 상속하여 생성된 확장 클래스이다. 레코드는 상속이 불가능 하기 때문에 엔티티로 사용할 수 없다.