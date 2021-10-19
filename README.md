# 👩‍🏫 프로젝트 소개

![동네히어로 동영상](https://user-images.githubusercontent.com/74531573/137953050-34d36225-ab9a-4de4-940e-36d2d77fd0b8.gif)

**동네히어로** 는 위치기반 심부름 서비스입니다.

누군가는 도움이 필요할 때 자유롭게 요청할 수 있고, 또 누군가는 우리 집 근처의 크고 작은 요청을 해결해 주는 히어로가 될 수 있습니다(용돈벌이는 덤).

**당신도 누군가의 히어로가 되어주세요!**
    


# 🙋‍♀️ 개발자

- 장혜민 ([https://github.com/hyemin-jang](https://github.com/hyemin-jang))
- 이태경 ([https://github.com/gaetaegoo](https://github.com/gaetaegoo))
- 이기환 ([https://github.com/GiHwan123](https://github.com/GiHwan123))
- 박세은 ([https://github.com/seeun214](https://github.com/seeun214))


# 📅 개발기간

2021.10.10 ~ 2021.10.18

# 💻 개발환경

#### Language
`Java (JDK 1.8)` `JavaScript` `HTML`

#### Library
`Vue`

#### Framework
`Spring Boot 2.5.5`

#### IDE
`Eclipse photon` `Spring Tool Suite 3.9.14`

#### Database
`Oracle XE 11`

#### Others
`Kakao oven` `Postman` `ERDCloud`

---

# 🔗 서비스 개념도
![Untitled (1)](https://user-images.githubusercontent.com/74531573/137953223-61f4a13d-a23a-410d-900f-9368b7ff0906.png)

![Untitled](https://user-images.githubusercontent.com/74531573/137953239-90cb05c2-ea67-46d5-9f6f-9a6bd405220b.png)


# ⚙ 모델링

![Untitled (7)](https://user-images.githubusercontent.com/74531573/137953537-4754a121-bccb-40bb-9196-c3e951674413.png)


# 🕹 주요 기능

### [ 회원 ]
![Untitled (2)](https://user-images.githubusercontent.com/74531573/137953259-43a8cd2a-b6db-4f44-95f5-5b2329f11830.png)

### [심부름 등록]

1. 새로운 심부름 등록
2. `내 심부름`에서 진행 상황 조회 가능 
    - 지원자 내역 확인
    - 지원자 수락 ⇒ 등록자와 지원자 매칭!
    - 심부름 완료 처리
3. 심부름 완료 처리 후에는 `마이페이지` 에서 내가 등록했던 모든 심부름 내역 확인 가능
4. 등록한 심부름 수정
5. 등록한 심부름 삭제

![Untitled (6)](https://user-images.githubusercontent.com/74531573/137953322-9ec869df-0e5a-456f-a306-d2f942a50e3b.png)


### [심부름 지원]

1. 다른 사람이 등록한 심부름에 지원
2. `내 심부름` 에서 매칭 여부 확인 가능
3. 심부름 완료 후에는 `마이페이지` 에서 내가 지원했던 모든 심부름 내역 확인 가능
4. 심부름 지원 취소

![Untitled (4)](https://user-images.githubusercontent.com/74531573/137953335-09e62856-f72e-477e-8bc2-269b2e86e0a2.png)


### [심부름 조회]

1. 사용자 접속 위치 기반 조회 - 등록된 심부름 지도에 마커로 표시
2. `내 심부름` : 현재 진행 중인 내가 등록한 심부름 / 내가 지원한 심부름 확인 가능
3. `마이페이지` : 완료된 내가 등록한 심부름 / 내가 지원한 심부름 전체 내역 확인 가능

![Untitled (5)](https://user-images.githubusercontent.com/74531573/137953349-f97ff233-7c40-4d02-ba27-f20016e824a5.png)


# 💥 ver 2.0을 위한 개선할 점

- 진정한 위치 기반 서비스 구현 : 심부름 조회시 `사용자 접속 위치 인근의 게시물만` 출력되게 하기
- 심부름 조회 필터링 (카테고리별 조회.. 등)
- `회원 점수` 기능 추가 ⇒ 지원자 수락 시에 성별, 나이 외 활용할 추가적인 정보 제공
    - 심부름 수락 처리시 도와준 히어로에게 ⭐별점⭐ 보내기
    - 수행한 심부름 및 도움 받은 심부름 카운트 후 점수 합산 및 신뢰도 부여
- 게시글 페이징 처리하기

