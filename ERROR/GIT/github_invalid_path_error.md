## error: invalid path 'xxx/xxx'

clone, pull 등을 할 떄 파일 이름에 특수 문자가 있어서 시스템이 파일을 인식 못하는 에러 

### 해결 방법

1. 에러가 발생한 프로젝트 디렉터리로 이동
2. 해당 디렉터리에서 터미널, git bash 열기
3. __git config core.protectNTFS false__ 명령어 입력