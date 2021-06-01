# VCS Updater 개선안

## ver. 20210528

1. 시작프로그램 자동등록 O


2. 서버에서 서비스 켬/끔(vcs on off 처럼) 
- 제한 사항 : 공유폴더로 지정되어 있어야함. 즉, 공유 폴더만 활성화 해주면 실행가능.
CMD 관리자모드로 실행.
1. VCScontrolservice를 최초로 실행해서 공유로직을 실행시키거나.,
2. 직접 vcs나 c:를 공유폴더 및 드라이브로 설정해주면?
또
1. ipc측 레지스트리 명령 한번 터줘야됨.
또, 
1. 로컬보안정책 암호없이 접근할수있도록 변경해줘야됨.
또,
5. 로컬보안정책 네트워크에서 이 액세스 거부 풀어줘야됨.
    또,
    1.uac 최하로 줄어야됨
    결과: 위 5개 조건이 부합해야 가능하다. 현병주 대리한테 윈도우 설치 셋업 관련 문의
    너무 비효율적...(하지만 현재 SEMI OHT IB811경우에는 잘되긴함..)
6. 디폴트 경로 = c:\VCS\vcscontrolservice

**최초 실행 시,db의 아이피 읽어오면 문제없을것으로 보임. 로컬네임은?? config 로 default 잡고, 최초 실행시 localname 수정할수 있도록 ㄱㄱ
localname data 없을시 -> config default name -> 수정할 수 있도록 팝업창으로 보여줌




3. 서버에서 서비스 프로그램 전송하기(vcs 파일처럼) 
1. 공유폴더(C:\)로 전송
2. psexec cmd 압축풀기 명령
3. psexec cmd로 실행(2번 사항)




4. 서버에서 컨피그 원격 설정 (우클릭)

부가: 조인 되었는데 안됫다고하는거 (재현: 서비스쪽에서 꺼졌다 켜졌을 때, 서비스는 이상없음, 서버쪽은 false 상태)
- 대안 : 서비스프로그램을 재실행하면되지 않을까?

그리하여 나온 명령어 완전체 ...
1차 : PsExec.exe -i 1 -d -h -s -w c:\vcs\vcscontrolservice -u shinsung -p "" \\10.1.1.116 c:\vcs\vcscontrolservice\vcscontrolservice.exe

- 옵션 기재 순서에 따라 실행 영향을 받는거 같은 걸로 판단됨.

2차 : PsExec.exe -u shinsung -p "" -h -s -i 1 -d  \\127.0.0.1 -w c:\vcs\vcscontrolservice \\10.1.1.116 c:\vcs\vcscontrolservice\vcscontrolservice.exe



## 주의사항

1. cmd 명령은 항상 ms에서 제공해주는 입력 순서에 맞게 입력!
2. 코드상에서 줄바꿈은 절대 하지말 것, 띄어쓰기도 왠만하면 다 맞춰줄것!