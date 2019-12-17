## push_pull(github 사용법)

### 업로드

1. `remote add [저장소별명] [원격저장소 주소]`: 원격 저장소 생성

2. `remote -v`: 원격 저장소 정보 확인

3. `push origin master`: 해당 폴더에 대한 파일 업로드 

   

### 다운로드

#### 1. 최초 다운로드

> 원하는 경로로 이동한 다음 `clone`을 이용한다. (원격 저장소 디렉토리 **포함** 복사)

- `clone` [주소]: 해당 주소에 있는 폴더 및 파일들을 해당 경로에 그대로 복사 

  ```shell
  $ git clone https://github.com/hi-ing2/test_project.git
  ```




#### 2. 최초 이후 다운로드

> 원하는 경로로 이동한 다음 `pull`을 이용한다. (원격 저장소 디렉토리 **제외** 복사)

- `pull` : 해당 주소에 있는 파일들을 해당 경로에 그대로 복사 

  ```shell
  $ git pull origin master
  ```

