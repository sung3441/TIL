# SOLID (객체 지향 설계)

SOLID는 2000년대 초반에 로버트 마틴이 명명한 객체 지향 프로그래밍 및 설계의 다섯 가지 기본 원칙을 얘기한다.  
프로그래머가 시간이 지나도 유지 보수와 확장이 쉬운 시스템을 만들고자 할 때 이 원칙들을 함께 적용할 수 있다.  
SOLID 원칙들은 프로그래머가 소스 코드가 읽기 쉽고 확장하기 쉽게 될 때까지 소프트웨어 소스 코드를 리팩터링하여  
**코드 냄새**를 제거하기 위해 적용할 수 있는 일종의 지침이라고 한다.

> 결론적으로 깔끔한 코드가 목표인 개발 원칙인 것 같다.


# SRP (Single Responsibility Principle) 단일 책임 원칙

> 하나의 객체가 하나의 책임만 가져야 하는 것.  
> 하나의 객체가 자신이 할 수 있는 것과 해야 하는 것만 수행할 수 있도록 설계되어야 한다.
> SRP 원칙을 잘 지킨 객체는 재사용성, 수정, 유지보수에 용이하다. 

하나의 책임이라는 범위?가 너무 어려웠다.  
대충이라도 머리에 넣기 위해 다음과 같은 예를 만들었다.

```java
// SRP가 지켜지지 않은 예
class Job {
  void programming() {
    System.out.print("개발을 한다.");
  }
  
  void research() {
    System.out.print("연구를 한다.");
  }
  
  void sing() {
    System.out.print("노래를 한다.");
  }
}
```

위에서 작성한 Job 클래스는 각각 다른 직업의 업무들이 뒤섞여 있다.  
객체의 책임을 다음과 같이 분리할 수 있다.

```java
// SRP를 지키기 위해 책임 분리
interface Job {
  void work();
}

class Developer implements Job {
  @Override
  void work() {
    System.out.print("개발을 한다.");
  }
}

class Scientist implements Job {
  @Override
  void work() {
    System.out.print("연구를 한다.");
  }
}

class Singer implements Job {
  @Override
  void work() {
    System.out.print("노래를 한다.");
  }
}
```
각 직업 객체들이 하나의 업무만 책임지고 있다.

# OCP (Open Closed Principle) 개방 폐쇄 원칙

> 기존의 코드를 변경하지 않고(Closed) 기능을 수정하거나 추가할 수 있도록(Open) 설계해야 함.  
> 즉, 객체의 변화에 따라 행동이 의존적으로 변하지 않게 작성하라는 얘기이다.

다음과 같은 Person 객체가 있다.
```java
class Person {
  void programming() {
    System.out.println("개발을 한다.");
  }
}

class Main {
  private Person person;

  public Main(Person person) {
    this.person = person;
  }
  
  void work() {
    person.programming();
  }
}
```

어느 날 Person 객체가 개발자를 그만두고 가수로 전직한 상황이라 programming 메서드가 sing 메서드로 변경 됐다.  
위 같은 설계로는 Main 클래스의 work 메서드에서 에러가 날 것이다.  

```java
class Person {
  // void programming() {
  //   System.out.print("개발을 한다.");
  // }
  
  void sing() {
    System.out.print("노래를 한다.");
  }
}

class Main {
  private Person person;

  public Main(Person person) {
    this.person = person;
  }
  
  void work() {
    person.programming(); // Error
  }
}
```

위 코드처럼 Person 객체의 업무 메서드는 언제는 변할 수 있으므로 문제가 발생하기 쉽다. work를 interface로 분리하자.

```java
interface Job {
  void work();
}

class Person implements Job {
  @Override
  void work() {
    // programming();
    sing();
  }
  
  private void programming() {
    System.out.print("개발을 한다.")
  }
  
  private void sing() {
    System.out.print("노래를 한다.")
  }
}

class Main {
  private Person person;
  
  public Main(Person person) {
    this.person = person;
  }
  
  void makeMoney() {
    person.work();
  }
}
```
위 코드는 work 메서드에 변동이 생겨도 코드에 문제가 없다. 또한 programming 메서드 자체는 수정할 수 없고(Closed),
새로운 메서드인 sing을 만들어 확장에는 열려 있다(Open).

# LSP (Liskov Substitution Principle) 리스코프 치환 원칙

> 자료형 B가 자료형 A의 하위형이라면 프로그램의 속성 변경 없이 자료형 A의 객체를 자료형 B의 객체로 치환할 수 있어야 하는 원칙.  
> 즉, 부모 클래스의 인스턴스 대신 자식 클래스의 인스턴스를 사용해도 문제가 없어야 한다.

<br>

가장 흔한 예로 직사각형과 정사각형이 있다. 정사각형은 직사각형의 성질을 갖기 때문에,  
직사각형(부모) - 정사각형(자식) 관계로 정의할 수 있다. 그럼 정사각형이 직사각형을 대체할 수 있는지 코드로 보자. 

```java
// 직사각형
class Rectangle {
  double width;
  double height;
  
  public double getWidth() {
    return width;
  }
  
  public double getHeight() {
    return height;
  }
  
  public void setWidth(double width) {
    this.width = width;
  }
  
  public void setHeight(double height) {
    this.height = height;
  }
  
  public double getArea() {
    return width * height;
  }
}

// 정사각형
class Square extends Rectangle {
  
  @Override
  public void setWidth(double width) {
    this.width = width;
    this.height = width;
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
    this.width = height;
  }
}

class Main {
  public static void main(String[] args) {
    Rectangle rect = new Rectangle();
    rect.setWidth(3);
    rect.setHeight(2);
    System.out.println(rect.getArea()); // 결과 : 6.0
    
    Square square = new Square();
    square.setWidth(3);
    square.setHeight(2);
    System.out.println(square.getArea()); // 결과 : 4.0
  }
}
```

위의 코드를 실행해보면 getArea 메서드의 결과가 다른 것을 알 수 있다.  
따라서 Square(자식) 객체는 Rectangle(부모) 객체를 대체할 수 없으므로, LSP를 위반한다.  
> LSP의 핵심은 자식 클래스가 항상 부모 클래스의 역할을 충실히 수행하는 것

# ISP (Interface Segregation Principle) 인터페이스 분리 원칙

> 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.  
> 클라이언트 입장에서 사용하는 기능만 제공하도록 인터페이스를 분리하는 것이다.  
> 어떻게 보면 SRP와 비슷하지만, SRP는 단순히 객체의 책임을 나눴다면 ISP는 인터페이스로 분리하는 것이다.


```java
interface Developer {
  void 프론트엔드_업무();
  void 백엔드_업무();
  void 안드로이드_업무();
}

class FrontEndDeveloper implements Developer {
  @Override
  void 프론트엔드_업무() { ... }
  
  @Override
  void 백엔드_업무() { ... } // 사용하지 않는 인터페이스가 변경되어도 수정해야함
  
  @Override
  void 안드로이드_업무() { ... } // 사용하지 않는 인터페이스가 변경되어도 수정해야함
}
```

위 같은 코드는 FrontEndDeveloper에는 필요없는 백엔드_업무 메서드와, 안드로이드_업무 메서드를 Override하고 있으므로  
Developer 인터페이스에서 두 메서드에 리턴 타입 등 변화가 생기면 FrontEndDeveloper 클래스에도 수정이 필요하다.  
이는 ISP를 위반한다. ISP를 적용하면 다음과 같이 인터페이스를 분리할 수 있다.

```java
interface FrontEnd {
  void 프론트엔드_업무();
}

interface BackEnd {
  void 백엔드_업무();
}

interface Android {
  void 안드로이드_업무();
}

class FrontEndDeveloper implements FronEnd {
  @Override
  void 프론트엔드_업무() {
    System.out.print("프론트 엔드 업무");
  }
}
```

이제 백엔드_업무, 안드로이드_업무 메서드가 변해도 FrontEndDeveloper 객체에는 영향을 끼치지 않는다.

# DIP (Dependency Inversion Principle) 의존 역전 원칙

> 추상화에 의존해야지, 구체화에 의존하면 안된다. 추상화는 세부 사항에 의존해서는 안된다.  
> 핵심은 의존 관계를 맺을 때 변하기 쉬운 것에 의존하는게 아니라, 변하지 않는 것에 의존하는 것이다.  
> 의존성 주입이 DIP 지키는 방법 중 하나이다.


```java
// DIP를 위반한 예
class KakaoLogin {
  void signIn() {
    System.out.print("Kakao Login");
  }
}

class LoginService {
  private KakaoLogin login;
  
  public LoginService(KakaoLogin login) {
    this.login = login;
  }
  
  void login() {
    login.signIn();
  }
}
```

다음과 같은 코드를 사용하던 도중 로그인을 KakaoLogin이 아닌 GoogleLogin을 사용한다고 했을 때
KakaoLogin 클래스에 의존하는 LoginService 클래스의 코드를 변경해야된다. 이는 변경에 전혀 유연하지 않다.  
다음은 추상화에 의존하는 경우다.

```java
interface Login {
  void signIn();
}

class KakaoLogin {
  @Override
  void signIn() {
    System.out.print("Kakao Login");
  }
}

class GoogleLogin {
  @Override
  void signIn() {
    System.out.print("Google Login")
  }
}

class LoginService {
  private Login login;
  
  public LoginService(Login login) {
    this.login = login;
  }
  
  void login() {
    login.signIn();
  }
}
```

LoginService 어디에도 구현체에(KakaoLogin, GoogleLogin) 의존하는 코드를 찾아볼 수 없다.  
다음과 같이 추상화(Login)에만 의존하는 경우 KakaoLogin을 사용했더라도 LoginService 코드는 수정하지 않고,  
의존성 주입을 통해 GoogleLogin을 사용할 수 있다.

# References
- [[Java] 객체지향 설계 5원칙 - SOLID](https://sehun-kim.github.io/sehun/solid/)
- [SOLID LSP(Liskov Substitution Principle)이란? 리스코프 치환 원칙](https://huisam.tistory.com/entry/LSP)
- [인터페이스 분리 원칙 (ISP, Interface Segregation Principle)](https://jaeseongdev.github.io/development/2021/04/25/ISP(%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4-%EB%B6%84%EB%A6%AC-%EC%9B%90%EC%B9%99)/#google_vignette)
- [IoC, DI, DIP 개념잡기](https://vagabond95.me/posts/about-ioc-dip-di/)
