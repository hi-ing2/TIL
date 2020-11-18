# OHT

> 가볍고, 작은 물류들에 주로 사용

> 다익스트라 알고리즘 - 노드 간 최단거리 알고리즘

> ADVANCED 다익스트라 알고리즘 - 상황별 최적의 최단거리 알고리즘

> AI 기반 최단거리 알고리즘
>
> 여러 상황에 대한 VEHICLE 통과시간을 AI에 입력

> 정체 UP -> Q값  UP / 정체 DOWN -> Q값 DOWN

> ### 핵심은 뭐다? 교통상황에 대한 최단거리 알고리즘이 AI 기반에서 더 효율적이어야 한다.(세부적, 구체적 결과 및 출력을 보여야함.)

### 레일

> INTRA BAY (SUB) / INTER BAY (MAIN)



- E82 IBSEM STANDARD 적용 라인 : OHT, OHS, AGV!! 

### OHT 구조

MCS - CIM - VEHICLE1(VCP) - MOTION

​                   \ VEHICLE2 - MOTION

​                   \ VEHICLE3 - MOTION

​                   \ VEHICLE4 - MOTION



- VCP
  - 하위 구조 : Motion, Sensor, ID Reader, PIO

- WCF
- Variable data Items
  - class(ID 분류/종류)
    - SV (STATE VALUE)
    - DVVAL (DATA VALUE?)

