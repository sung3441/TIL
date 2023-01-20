# vue에서 clipboard 기능 사용

#### Vue-clipboard 라이브러리 설치

> npm install --save vue-clipboard2

#### vue-clipboard 사용

```vue.js
// main.js

import Vue from 'vue'
import VueClipboard from 'vue-clipboard2'

// Vue.use에 등록
Vue.use(VueClipboard)
```
```javascript
// index.html

<script src="vue.min.js"></script>
```
```javascript
// use

method: {
  copy() {
    this.$copyText('복사할 내용').then(() => {
      // 복사 완료 후 코드..
    })
  }
}
```
