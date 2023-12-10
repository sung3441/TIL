### .gitignore 파일 적용 안될 때

.gitignore 파일에 application-db.yml 경로를 추가해도 예외 되지 않는 현상이 있었다.  

#### 원인

__.gitignore__ 파일은 Git으로 추적하지 않을 파일 및 디렉토리를 지정하는데 사용이 된다.  
즉, __.gitignore__ 파일에 패턴을 추가해도, 이미 트래킹 중인 파일은 무시되지 않는다.

#### 해결

1. gitignore 파일에 패턴을 추가하고 커밋
2. 이미 트래킹 중인 파일을 로컬 저장소와 원격 저장소에서 제거

```gitexclude
# 트래킹 중인 파일 제거
git rm --cached src/main/resources/application-db.yml

# 변경 사항 커밋
git commit -m "Remove sensitive files from tracking"

# 변경 사항 푸시
git push origin <branch>
```

위 작업을 진행하면 이후 __.gitignore__ 에 추가한 패턴이 적용되어 해당 파일이 추적되지 않는다.  
단, 이미 푸시한 파일이 있다면 주의해서 작업하자.