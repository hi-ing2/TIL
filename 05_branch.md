# 5. branch

>여러 버전으로 쪼개질 때 해당되는 라인들이 가지처럼 뻗어나가는 기능을 말한다. (평행세계)

- git `branch`:  세계 조회

- git `branch` `[이름]`: 세계 생성

  - `-d` `[이름]` : 세계 제거

- git `switch` `[이름]`: 세계 이동

  - `-c` `[이름]`: 세계 생성 후 이동

- git `merge` `[병합을 할 세계 이름]`: 세계 병합 

  - `--no --ff` `[병합을 할 세계 이름]`: fast-foward  병합을 원하지 않을 경우 사용

  ###### \* 병합이 ***'되'*** 는 세계를 입력해야 함

   

  ```shell
  mhs34@DESKTOP-47QE027 MINGW64 ~/branch (master)
  $ git branch
  * master
  
  mhs34@DESKTOP-47QE027 MINGW64 ~/branch (master)
  $ git branch new
  
  mhs34@DESKTOP-47QE027 MINGW64 ~/branch (master)
  $ git branch
  * master
    new
  
  mhs34@DESKTOP-47QE027 MINGW64 ~/branch (master)
  $ git switch new
  Switched to branch 'new'
  
  mhs34@DESKTOP-47QE027 MINGW64 ~/branch (new)
  ```

  

# Merge Confilct

> merge시 갈등이 생기는 경우가 존재한다.

### 	conflict 발생: 같은 파일에서 다른내용이 있는 경우

1.  `fast-forward` merge (빨리감기 병합) : branch 분기가 일어났지만,  merge 시점에서 branch 한쪽에서만 commit들이 쌓여 있는 경우 (그대로 피병합쪽으로 이동 후 합쳐짐.)

   (ex. new에만 commit이 있고, master에는 없을 때)

2.  `auto` merge : merge 시점에 양쪽 branch에 commit들이 쌓여 있지만, conflict가 발생하지 않는 경우

3.  `merge conflict` : merge 시점에 양쪽 branch에 commit들이 쌓여 있고, conflict가 발생하는 경우

   - 동일 파일 내에 상충하는 내용이 있을 경우