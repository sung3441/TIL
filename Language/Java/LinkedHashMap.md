# LinkedHashMap

Map을 사용하면서 순서가 보장 되어야 할 때 사용

```java

HashMap hm = new HashMap();

// 입력된 순서 보장 x
hm.put("A", "memberA");
hm.put("B", "memberB");
hm.put("C", "memberC");

// 순서 랜덤
// no key value
// 1  C   memberC
// 2  A   memberA
// 3  B   memberB

LinkedHashMap lhm = new LinkedHashMap();

// 입력된 키의 순서가 보장
lhm.put("A", "memberA");
lhm.put("B", "memberB");
lhm.put("C", "memberC");

// 입력한 순서 보장
// no key value
// 1  A   memberA
// 2  B   memberB
// 3  C   memberC
```
