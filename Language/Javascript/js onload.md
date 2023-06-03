# js에서 페이지가 로드 되면 자동으로 실행되는 전역 콜백 함수

- 페이지의 모든 요소들이 로드되어야 호출 가능
- 한 페이지에서 하나의 window.onload() 함수만 적용
- 바디가 파싱되기 전에 스크립트문을 만나면 오류가 발생할 수 있는데, onload를 이용하면 안전하게 사용할 수 있다.

```javascript
<script>
window.onload = function() {
    // 페이지가 로드되고 실행할 작업
}
</script>
```
