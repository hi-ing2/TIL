# E82 (IBSEM)

## 목차

### 1. 질문 정리

### 2. 개념 정리

### 3. 용어 정리



#### 1. 질문 정리

- load / unload 개념 재정리
- active / passive vehicle 차이
- process / production equipment 차이
- internal / storage buffer 차이
- trigger의 주체가 누구인가? (TSC State Model의 경우, host 인가? TSC 인가?)
- TRANSFER Command State Model 에서 13번 transition은 언제 일어나는 것인가?
- WAITING 7.3.3.3 상세 설명
  - waiting은 정확히 어떤 과정을 말하는가?
- carrier란? == 카세트
- remote command는 host와 oms 간에 하는건가?
- 알람 리스트에서 캐리어 핸드 오프 병렬 인풋/아웃풋 실패는무엇인가?
- remote command에서 stage는 정확히 무엇을 의미하는가?
- Host Command Parameters에서 replace는 무엇을 교체하는 건가?
- normal 시나리오에 대한 물리적/논리적 부분 개념 재정리
  - transferring은 carry / pick ?
  - carrierinstall은 acquire 중 어느 시점에서 진행?
- 12.2.2.1시나리오 해석
  - 출발지 - 목적지 transfer 과정을 두번하는게 맞지않는가?

- 12.3.3.1 시나리오 해석
  - HOST가 어떻게 알람을 해결했는지 궁금하다.

#### 2. 개념 정리

- transfer unit / transport unit

  > `transfer` 는 load/unload에서의 개념, `transport`는 from/to에서의 개념

- TSC State Model

  - `TSC INIT` :  초기 상태
  - `PAUSED` :  일시정지 된 상태 (정지 상태, 임무 없음)
  - `PAUSING` : 일시정지 요청 상태 (아직 작업중인 상태)
  - `AUTO` : 일반적인 운영 상태

  > AUTO -> PAUSING -> PAUSED -> AUTO -> ... (순환구조)

- TRANSFER Command State Model

  - `QUEUED` : 초기 상태
  - `WAITING` : 출발지로 이동 중인 상태
  - `TRANSFERRING` : 출발지 도착 후 픽업 중인 상태
  - `PAUSED` : 완료 된 상태
  - `CANCELING` : NON-ACTVIE에서 취소 요청 상태
  - ` ABORTING` : ACTIVE에서 취소 요청 상태

- VEHICLE State Model

  - `ENROUTE` : 목적지를 향해 이동 중인 상태
  - `PARKED` : 특정 모션을 취하기전의 정지 상태 (일시적 휴무상태)
  - `ACQURING` :  LOADING
  - `DEPOSITING` : UNLOADING
  - `NOT ASSINGED` : 쉽게 말해 대기 상태
  - `REMOVED` : 제거된 상태
  
-  Carrier State Model

  - `INSTALLED` : 캐리어 설치 유무

- Port State Model

  - `OUT OF SERVICE` : 포트 비활성화 상태
  - `TRANSFER BLOCKED` : READY TO LOAD / UNLOAD 둘다 아닌 상태
  - `READY TO LOAD` : 포트에 캐리어 올릴 수 있는 상태
  - `READY TO UNLOAD` :  포트에서 캐리어를 내릴 수 있는 상태

- Collection Event Table : 데이터 수집(변경) 이벤트 테이블

  > 하나의 이벤트(상태변경)에 대한 결과에 대해서 정리해 놓은 테이블

- Non-Transition Collection Event Table : 상태 확인 이벤트 테이블

  > 상태 확인하는 것에 대해서 정리 해놓은 테이블

- Variable Data Items : 데이터 항목 요구 사항 목록

  - class(ID 분류/종류)
    - SV (STATE VALUE)
    - DVVAL (DATA VALUE?)
  - log에서 분석 할때 사용

- Remote commands : 원격 명령

- Alarm List Table

  - 차량방해 (시간초과)
  - 운송시스템 장비 고장
  - 캐리어 핸드 오프 병렬 인풋/아웃풋 실패
  - 데이터베이스 오류

- Host Command Parameters : 호스트 명령 요소들

- replace 시 에는 두번째 적재물을 acquire 부터하고  첫번째 적재물을 deposit을 한다.

- Carrier ID Error 시나리오

  1. 정상적으로 캐리어는 적재되어 있지만, 리드에 실패하는 경우
  2. A 캐리어가 적재되어 있는 상태에서 리드 후, B 캐리어로 인식되는 경우
     - 기존 DB를 유지하는 시나리오와 신규 DB로 교체하는 시나리오, 두가지 방법이 있다.
  3. 정상적으로 캐리어는 적재되어 있지만, DB에 없는 캐리어로 인식되는 경우

- IBSEM에 필요한 SEMI E30 추가 기능

  - 통신 설정,
- 동적 이벤트 보고서 구성,
  - 변수 데이터 수집,
  - 상태 데이터 수집,
  - 경보 관리,
  - 장비 상수,
  - 장비 터미널 서비스,
  - 시계
  - 제어 (호스트 시작).

#### 3. 용어 정리

`scope` : 영역

`TSC` : Transport System Controller

`Terminology` : 전문 용어

`FOUP` : a closed carrier for holding wafers

`acquiring` == `loading`

`semiconductor` : 반도체

`ITS` :Interbay or Intrabay Transport System

`acuqire` : 습득하다, 획득하다

`load` : 짐을 싣다

`deposit` : 두다, 놓다

`unload` : 짐을 내려놓다

`TS` : Transport System

`HSMS` : High Speed SECS Message Service

`internal proprietary communications`: 내부 독점 대화권?



