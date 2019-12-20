# Python

## packages installer for python(pip)/패키지 관리시스템

### 1. pip list

```shell
$ pip list
Package           Version
----------------- -------
astroid           2.3.3
autopep8          1.4.4
colorama          0.4.3
isort             4.3.21
lazy-object-proxy 1.4.3
mccabe            0.6.1
pip               19.2.3
pycodestyle       2.5.0
pylint            2.4.4
setuptools        41.2.0
six               1.13.0
wrapt             1.11.2
#pip 목록을 불러온다.
```

### 2. pip 가상 환경 적용 방법

```shell
mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ python -m venv venv # pip 가상 환경 조건이 구성된 venv 폴더를 만든다

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ source venv/scripts/activate # activate를 실행하여 가상환경을 활성화 시킨다.
(venv)

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ pip list # 가상 환경에 대한 pip 목록들이 등장한다.
Package    Version
---------- -------
pip        19.2.3
setuptools 41.2.0
WARNING: You are using pip version 19.2.3, however version 19.3.1 is available.
You should consider upgrading via the 'python -m pip install --upgrade pip' command.
(venv)

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ deactivate # 작업이 끝난후 가상환경을 비활성화 시킨다.

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ rm -rf venv/ # 작업 완료 후 삭제를 원할시 사용한다.

```

- 유용한 툴 : jupyter notebook, (pip) pandas, (pip) requests

### 3. pip 가상 환경에 대한 requirements.txt 생성

> 다른 환경에서 작업을 위한 패키지 설치목록 파일을 만듬. git bash를 통해 auto-install도 가능함.

```shell
(venv)
mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ pip freeze
certifi==2019.11.28
chardet==3.0.4
idna==2.8
requests==2.22.0
urllib3==1.25.7

(venv)
mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ pip freeze > requirments.txt # freeze에 있는 설치 명령 정보들을 requirments.txt에 담아
							   # txt파일로 생성을 한다. (업무 전 필수 지침서 개념으로 이해)

(venv)
mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ cat requirments.txt # 해당 txt파일 안에 입력이 잘 되어 있는지 보여준다.
certifi==2019.11.28
chardet==3.0.4
idna==2.8
requests==2.22.0
urllib3==1.25.7

(venv)
mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$pip install -r requriements.txt #txet상태로 노출하여 모듈과 버전과 그대로 인스톨 하기 위함

(venv)
mhs34@DESKTOP-47QE027 MINGW64 ~/TIL/python (master)
$ rm -rf requirements.txt # 필요 시 삭제한다.
```

#### - 팁 : .gitignore 파일 사용하여 일부 파일만 push하기

>  http://gitignore.io/ 에서 push에서 제외시키기 원하는 키워드를 입력한 뒤 출력해주는 해당 text를 전부 복사한다. .git이 있는 최상위 폴더에 .gitignore 파일 안에 생성한 뒤,  해당 text를 전부 붙여 넣는다. 마지막으로 push를 한다.

```shell
mhs34@DESKTOP-47QE027 MINGW64 ~
$ cd TIL/ #제외 push를 하고싶은 최상위 폴더(.git이 있는 폴더)로 이동한다.

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL (master)
$ touch .gitignore #.gitignore 숨김파일 생성

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL (master)
$ ls -a # 생성 확인
./          00_markdown_basic.md  04_master_git.md       08_Python_pip.md
../         01_cli.md             05_branch.md           README.md
.git/       02_Github_TIL.md      06_branch_vs_fork.md   python/
.gitignore  03_push_pull.md       07_프로그래밍_언어.md

mhs34@DESKTOP-47QE027 MINGW64 ~/TIL (master)
$ vi .gitignore # 필요한 부분 즉석으로도 수정 가능하다.

```

