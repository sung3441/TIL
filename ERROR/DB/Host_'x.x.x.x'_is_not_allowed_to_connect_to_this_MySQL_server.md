# Host 'xxx.xxx.xxx.xxx' is not allowed to connect to this MySQL server
Ubuntu에서 직접 mysql 명령어를 치면 접속이 되지만, 외부에서 접속시도 시 위 오류가 발생했다.  
지정한 사용자가 외부에서 접근할 수 있는 권한이 없기 때문이다. 내부에서 접속 시 host는 localhost되지만,  
외부에서 접속 시 접속을 시도하는 PC의 IP가 호스트가 된다. 따라서 해당 IP에도 권한을 부여하면 된다.

<br>

외부 엑세스를 허용하는 사용자 생성
```
> CREATE USER 'YOUR NAME'@'YOUR IP' IDENTIFIED BY 'YOUR PASSWORD';
> GRANT ALL PRIVILEGES ON *.* TO 'YOUR NAME'@'YOUR IP';
> FLUSH PRIVILEGES;
```
'YOUR IP' 부분에 '%'를 넣으면 들어오는 모든 아이피에 해당된다.

설정 후 외부에서 다시 접속을 시도하면 연결에 성공한다.
