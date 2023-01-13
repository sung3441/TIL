# insert 된 row의 key값 가져오기 (autoincrement인 경우)

mybatis에서 방금 insert된 데이터의 seq가 필요한 경우가 있다.  
seq 구하자고 다시 select를 하자니 seq외의 유니크 값이 없을 수도 있고, 자원도 낭비 되는 것 같다.  

```
<insert id="save" parmeterType="Member" useGeneratedKeys="true" keyProperty="id">
  // insert 문
</insert>
```

- useGeneratedKeys="true"
- keyProperty="id"

이렇게 옵션을 설정하면 insert에 파라미터로 썼던 Member 객체의 id 필드에 autoincrement된 값이 채워진다.

> ex) Member의 seq 필드 이름을 memberSeq로 사용하는 경우, <insert id="save" parmeterType="Member" useGeneratedKeys="true" keyProperty="memberSeq">  
> keyProperty 옵션 값을 바꿔주면 된다.
