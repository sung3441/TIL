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
// SRP 원칙을 지키기 위해 책임 분리
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

# OCP (Open Closed Principle) 개방 폐쇄 원칙

> 기존의 코드를 변경하지 않고(Closed) 기능을 수정하거나 추가할 수 있도록(Open) 설계해야 함.  
> 즉, 객체의 변화에 따라 행동이 의존적으로 변하지 않게 작성하라는 얘기이다.

다음과 같은 Person 객체가 있다.
```java
class Person {
  private final Developer developer;
  
  public Person(Developer developer) {
    this.developer = developer;
  }
  
  void makeMoney() {
    developer.work();
  }
}

class Developer {
  void work() {
    System.out.print("개발을 한다.");
  }
}
```

어느 날 Person 객체가 개발자를 그만두고 가수로 전직한 상황이면 MakeMoney 메서드에서  
System.out.print("노래를 한다.") 로직이 수행되어야 한다.  
위 같은 설계로는 Person객체가 의존하고 있는 Developer객체를 교체해야 하므로 OCP 원칙에 어긋난다.  

```java
class Person {
  private final Job job;
  
  public Person(Job job) {
    this.job = job;
  }
  
  void makeMoney() {
    job.work();
  }
}

interface Job {
  void work();
}

class Singer implements Job {
  @Override
  void work() {
    System.out.print("노래를 한다.");
  }
}

class Developer implement Job {
  @Override
  void work() {
    System.out.print("노래를 한다.");
  }
}

```

다음과 같이 Developer 구현체와 Singer 구현체에게 work 메서드의 구현을 맡긴다면  
Person 객체는 수정할 필요 없이(Closed) 직업 구현체의 work 메서드를 재정의하면 된다.(Open)

# LSP (Liskov Substitution Principle) 리스코프 치환

> 부모 클래스와 자식 클래스 사이의 행위에는 일관성이 있어야 한다는 원칙이다.  
> 즉, 부모 클래스의 인스턴스 대신 자식 클래스의 인스턴스를 사용해도 문제가 없어야 한다.

<br>

가장 흔한 예로 직사각형과 정사각형이 있다.
정사각형은 직사각형의 성질을 갖기 때문에, 정사각형을 직사각형으로 표현할 수 있다.

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

# DIP (Dependency Inversion Principle) 의존 역전 
