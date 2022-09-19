# Can’t Connect To MySQL Server On ‘xxx.xxx.xxx.xxx'(10061)
HeidiSQL로 ubuntu mysql에 접속하려고 하니 다음과 같은 오류가 발생했다.

```netstat -lntp``` 명령어를 쳐서 포트를 확인하니 결과가 다음과 같다.
```
...
tcp   0   0   127.0.0.1:3306      0.0.0.0:*       LISTEN     -
tcp   0   0   0.0.0.0:22          0.0.0.0:*       LISTEN     -
...
```

<br>

127.0.0.1 말고 vm_ip:3306 으로 접속을 시도 했더니 포트에서 막힌 것이다. 다음 경로의 파일을 열어주자.  
> vi /etc/mysql/mysql.conf.d/mysqld.cnf

<br>

파일을 내리다 보면 ```bind-address  =  127.0.0.1```이라고 있을 것이다. 이는 localhost만 허용한다는 뜻.  
파일을 ```bind-address  = *```으로 수정하고 mysql 재시작.
> service mysql restart

<br>

다시 ```netstat -lntp``` 명령어로 포트를 조회하면 아래처럼 ```:::3306```을 확인할 수 있다.
```
...
tcp6  0   0   ::::3306            :::*            LISTEN     -
...
```

HeidiSQL과 같은 툴로 다시 시도하면 접속에 연결에 성공한다.
