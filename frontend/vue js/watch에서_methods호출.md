# watch 
> vue에서 watch는 설정한 데이터를 감시하다가 변화가 생기면 특정 함수를 실행하는 역할을한다.

``` javascript
// watch 문법
...
data() {
  return {
    test: '감시받을 대상'
  }
},
watch: {
  test: function() {
    // 감시받는 대상인 test에 변화가 생길 시 수행할 로직
    ...
  },
},

// 변경 전 값과 변경 후 값을 파라미터로 받을 수 있다.
watch: {
  test: function(변경후, 변경전) {
    ...
  },
},
...
```

# watch에서 바로 함수 호출 시 문제
```javascript
...
watch: {
  test: function() {
    this.printHello()
  },
},
methods: {
  printHello() {
    console.log('Hello')
  },
},
...
```
위 코드처럼 함수를 호출 시 다음과 같은 에러를 띄운다.
> [Vue warn]: Error in callback for watcher test: "TypeError: Cannot read properties of undefined (reading 'printHello')"

아래 코드처럼 함수를 호출하니 정상 동작했다.

```javascript
...
watch: {
  test: {
    handler() {
      this.printHello()
    },
  },
},
methods: {
  printHello() {
    console.log('Hello')
  },
},
...
```

<br>

# 참조
[How to call function from watch?](https://stackoverflow.com/questions/35755027/how-to-call-function-from-watch)
