# Exception
예외(Exception)란 예기치 못한 상황에서 발생하는 프로그램 오류이다.  
입력된 값을 처리할 수 없거나, 참조하려는 값이 잘못된 겅우도 전부 예외인데,  
이러한 Exception은 개발자가 직접 예측하고 미리 조치할 수 있는 경우가 대다수이다.  
개발자가 예외를 처리하는 코드를 작성할 수가 있고, 예외가 발생했을 때 대처가 잘된  
프로그램은 견고해진다. 따라서 좋은 프로그램을 위해서는 필수적인 요소이다.  

Exception은 컴파일러의 예외처리 여부에 따라 나눌 수 있다.

- CheckedException
```
CheckedException은 컴파일 시 발생되는 예외이고, 개발자의 예외처리가 필수적이다.  
주로 잘못된 리소스를 입력하거나, 클래스를 잘못 적는 등 개발자의 실수가 대부분이다.
```

- UnCheckedException
``` 
UnCheckedException은 컴파일 후 런타임에 발생할 수 있는 예외이고, 예외처리가 필수는 아니다.  
개발를 중 가장 많이 보게 되는 NullPointerException이나, ArrayIndexOutOfBoundsException도 여기에 포함된다.
```

<br>

# Error
예외(Exception)와 다르게 에러(Error)는 하드웨어나, JVM 등 시스템에 이상이 생겨 개발자가 미리 예측할 수 없는 것들이다.  
이건 개발자가 예측해서 처리할 수 없으므로 예외처리 대상에서 제외된다. 에러의 가장 대표적인 예로는 StackOverflowError가 있다.

<br>

# Try-Catch
``` java
try {
	...
} catch (예외1) {
	...
} catch (예외2) {
	...
}
```
try문 안에서 수행하는 코드 중 예외가 발생하면 일치하는 catch문 안으로 들어간다. 만약 try문에서 예외1이 발생하면 예외1 catch문으로,  
예외2가 발생하면 예외2 catch문으로 들어간다. 예외가 없을 경우 아무 catch문도 들어가지 않는다.

예외가 발생하든 안하든, 어떤 에러가 발생하든 꼭 수행해야되는 작업이 있을 수 있다.
그래서 try-catch문은 finally를 지원한다.

``` java
try {
	...
} catch (예외1) {
	...
} catch (예외2) {
	...
} finally {
	무조건 실행되는 작업...
}
```

<br>

# Throws
Throws는 말 그대로 예외를 위로 던진다. 여기서 위는 본인을 호출한 쪽이 된다.

``` java
void testA () throws Exception {
	testB()
}

void testB () throws Exception {
	testC()
}

void testC () throws Exception {
	...
}

```

> 이처럼 testC에서 발생한 예외는 testB로 전달되고, testB에서 testA로 전달된다. CheckedException은 반드시 처리해야되는 예외이지만,  
> 이처럼 Throws를 통해 예외처리를 할 책임을 미룬다고 하여 책임 회피라고도 부른다.
