# master 권한

- ### .git 의 유무

  > 최상위 디렉토리에 `.git`이 존재한다면 하위 디렉토리에는 **절대** .git이 있어서는 안된다.
  >
  > `.git`이 각 디렉토리에 중복되게 존재한다면, 해당 디렉토리 입장에서는 어디서 관리 및 명령을 받아야 되는지 혼동하기 때문이다.

  - `rm` -r :  디렉토리 삭제시 하위 경로와 파일 삭제
  - `rm` -f  :  강제 삭제

  ```shell
  $ rm -r .git #.git은 디렉토리로 취급
  $ ls -a
  ./  ../ #.git이 유무 확인
  ```

  

  