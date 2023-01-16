# Mybatis에서 foreach 사용 (동적 쿼리)

### foreach 속성

- collection: 전달받을 인자, List 또는 Array 타입만 가능
- item: 전달 받은 인자를 꺼내서 사용할 이름, 아래 코드에서 "member"와 같은 역할
```java
for (Member member : members) ...
```
- open: 반복이 시작될 때 맨 앞에 올 문자
- close: 반복이 시작될 때 맨 뒤에 올 문자
- separator: 반복될 때 인자 사이에 출력할 문자열, 보통 콤마(",")를 넣어서 값을 구분하는 역할로 사용한다.
- index: 0부터 시작해서 반복마다 1씩 증가

### 사용법

```java
String[] colors = {"red", "blue", "yellow"};
mapper.findAllByColorIn(colors); // findAllByColorIn 쿼리 호출
```

```sql
<select id="findAllByColorIn" resultType="com.example.Car">
	SELECT * FROM CAR c
	WHERE c.color IN
	<foreach collection="array" item="color" open="(" close=")" separator=",">
		#{color}
	</foreach>
	
	# 실제 실행되는 sql
	SELECT * FROM CAT c
	WHERE c.color IN ('red', 'blue', 'yellow')
</select>
```

위 mybatis foreach를 java 코드로 표현하면 다음과 같다.

```java
int index = 0;
System.out.print("(");
for (String color : colors) {
	index++;
	System.out.print(color);
	if (index < colors.length) {
		System.out.print(", ");
	}
	index++;
}
System.out.print(")");
// (red, blue, yellow)
```

#### 위 방법은 array를 바로 넘긴 경우이고, Map에 담아서 넘긴 경우 collection 부분에 key값을 넣어주면 된다.

``` java
String[] colors = {"red", "blue", "yellow"};

HashMap<String, String[]> map = new HashMap();
map.put("colors", colors);

mapper.findAllByColorIn(map); // findAllByColorIn 쿼리 호출

// <foreach collection="colors" item="color" open="(" close=")" separator=",">
```

#### array가 아니라 list로 바로 넘기는 경우 collection="list"
