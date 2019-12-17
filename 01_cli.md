# CLI

>  커맨드라인 인터페이스



## 터미널 명령어들

- `git [옵션명]` : git에 대한 명령

  - `status`: 상태정보 출력

  - `--version`: 버전정보 출력

  - `init`: 해당 디렉토리 이하 master 권한 지정

  - `add [파일명]`: git에 파일을 등록 (사진대 올리는 작업)

  - `config --global user.email`

  - `config --global user.name`

  - `commit -m [설명]`: (사진대 있는 파일을 사진을 찍는 작업)

  - `log`: (사진 찍은 기록을 봄)

    - `--oneline` : 한줄로 간략히 출력

      ``` shell
      $ git log --oneline
      ```

  - `checkout [commit명]`: 세이브 지점(과거 버전)을 관찰 (과거를 볼 수 있음)

  

- `ls`: 폴더 내부의 파일 & 폴더를 나열
  
  - `-a`: 숨김 파일 & 폴더도 나열
- `pwd`: 현재 폴더 경로 출력
- `mkdir [폴더명]`: 폴더 생성
- `cd [폴더명]`: 폴더 변경
- `touch [파일명]`: 파일 생성

