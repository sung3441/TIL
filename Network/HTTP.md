# HTTP (Hyper Text Transfer Protocol)

- 클라이언트와 서버 간 데이터를 주고 받기 위한 프로토콜(규칙).
  - 어떤 식으로 통신할 지 미리 정해 놓은 형식
- 여기서 데이터는 텍스트, 이미지, 동영상 등 모든 종류를 말한다.
- HTTP에는 TCP, UDP 방식이 있으며 80 포트를 사용한다.

### TCP (Transmission Control Protocol)

- 1:1 연결을 지향하고, 신뢰할 수 있는 통신을 제공 (클라이언트와 서버의 1:1 통신)
- 데이터를 전송하기 전 연결을 확인한다. (3 way handshake, 4 way handshake)
- 상대방이 메시지를 수신했는지 확인이 가능하고, 메시지 순서를 보장한다.
- 다만, 이런 신뢰성 때문에 UDP 방식 보다 속도가 느리다.

### UDP (User Datagram Protocol)

- 1:1, 1:N 비연결을 지항하고, 신뢰할 수 없는 통신을 제공
- TCP 와 다르게 데이터를 전송하기 전 연결을 확인하는 과정이 없다.
- 메시지를 수신했는지 확인이 불가하고, 순서를 예측할 수 없다.
- TCP보다 속도가 빠르고, 오버헤드가 적다.

# HTTP 구조

기본적으로 요청(Request)과 응답(Response) 구조이다.  
클라이언트가 요청를 서버에 보내면 서버는 요청을 처리하여 응답을 보내는 구조이다.  
클라이언트와 서버의 모든 통신은 요청과 응답으로 이루어진다.

### Request Message

Request Message는 3가지 부분으로 나뉜다.
1. Start line
2. Headers
3. Body

__Start line__ 은 Request Message의 시작 부분이며, 3가지 구성 요소를 가지고 있다.
1. HTTP method
2. Request target
3. HTTP version

```
GET           /login.do        HTTP/1.1
[HTTP Method] [Request target] [HTTP version]
```

- [HTTP Method] 각 method마다 요청의 의도를 담는다.
  - GET: 자원 요청
  - POST: 새로운 자원 생성
  - PUT: 자원 수정
  - DELETE: 자원 삭제
  - 이 외에도 HEAD, OPTIONS, PATCH 등 많은 method가 있다.
  - [HTTP request methods](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods)
- [Request target] HTTP Request가 전송되는 목표 주소
- [HTTP version] HTTP의 현재 버젼

__Headers__ 는 Request에 대한 추가 정보를 담고 있다.


- [HTTP header](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers)

```
Accept: text/html
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: example.com
```

__Body__ 는 Request가 전송하는 데이터를 담는다. POST 요청일 경우 html form 데이터를 포함.

```
{
	"username": "admin",
	"password": "1234"
}
```

### Request Message

Response Message도 Request Message와 마찬가지로 3부분으로 나뉜다.
- Status Line
- Headers
- Body

__Status line__ 은 Response의 상태를 나타내며, 3가지 구성 요소를 가지고 있다.
1. HTTP version
2. Status Code
3. Status Text

```
HTTP/1.1       200           "로그인 성공"
[HTTP version] [Status Code] [Status Text]
```

__headers__ 와 __body__ 는 Request와 크게 다른 부분이 없다.

# 참고
- [개발자를 위한 웹 기술 > HTTP](https://developer.mozilla.org/ko/docs/Web/HTTP)
- [[간단정리] HTTP Request/Response 구조](https://hahahoho5915.tistory.com/62)