# vue-cookies 패키지 사용해서 브라우저에 저장된 쿠키 가져오기

### 설치

```node
npm install vue-cookies
```

### 추가

```javascript
// main js

...
import VueCookies from 'vue-cookies';

Vue.use(VueCookies);
```

### 사용
F12(개발자 도구) > Application > Storage > Cookies 에 들어가서 테스트를 위한 쿠키 생성

example
``` javascript
// test.vue

...
mounted() {
	const cookieKey = 'test';
	const cookieValue = this.$cookies.get(cookieKey);
	console.log(cookieValue;)
}
...
```
