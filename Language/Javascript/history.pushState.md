# history.pushState()

최근 게시판에서 비동기로 페이징 처리를 할 일이 생겼다.  
비동기다 보니, url에 변경이 없었고, 새로고침이 되는 순간 페이징이나 검색어가 초기화 됐다.

따라서 게시글을 비동기로 가져오면서, url만 변경하기로 했다.

```javascript
const formData = $('#form').serialize(); // form 태그에, serialize() 메서드를 쓰면 name값들이 직렬화된다. ex) name=deny&age=20 
history.pushState(null, null, `/${formData}`);
```
