   
-- [회원]
insert into member values(member_seq.nextval, 'se@google.com', '111', '세으니', '1997', '01', '01', 'F', '010-1111-1111', '경기도 파주시 다율동 해올로 20', '배달/장보기', '과외/공부', NULL, 0, 0);
insert into member values(member_seq.nextval, 'gh@google.com', '222', '기화니', '1995', '10', '10', 'M', '010-2222-2222', '용인시 기흥구 동백3로 11번길 21', '청소/집안일', NULL, NULL, 0, 0);
insert into member values(member_seq.nextval, 'hm@google.com', '123', '혜미니', '1994', '04', '28', 'F', '010-3333-3333', '서울시 강서구 등촌로39가길 15-6', '돌봄/동행', '벌레/쥐잡기', NULL, 0, 0);
insert into member values(member_seq.nextval, 'tk@google.com', '123', '태구', '1989', '12', '12', 'M', '010-4444-4444', '서울시 강북구 수유동', '돌봄/동행', '운전/카풀', '설치/조립/운반', 0, 0);


-- [심부름 등록]
insert into errand values(errand_seq.nextval, 1, '2021-10-11', '바퀴벌레 잡아 주실 분ㅠㅠ', '집에 바퀴벌레가 너무 많아요ㅠㅠ 처리 가능한 용자분 찾습니다!', '벌레/쥐잡기', '서울시 강서구 등촌로 223', '2021-10-11', 5000, '1', NULL);
insert into errand values(errand_seq.nextval, 2, '2021-10-12', '강아지 돌보는 분 급구!!', '혼자 여행 가서 그런데 강아지 사랑으로 돌봐주실 분ㅎㅎ', '돌봄/동행', '서울특별시 강서구 등촌동 524-13', '2021-10-20', 20000, '2', NULL); 
insert into errand values(errand_seq.nextval, 3, '2021-10-13', '급하게 서류 전달해 주실 분!?', '급한 건데 몸이 아파서 대신 전달해 주실 분 계실까요~', '배달/장보기', '서울시 강서구 등촌로39가길 15-6', '2021-10-13', 15000, '0', NULL);
insert into errand values(errand_seq.nextval, 2, '2021-10-15', '저희 강아지 오토바이 좀 태워주세요', '저희 강아지가 오토바이를 너무 궁금해하는데 한번 태워주실 분 구합니다', '기타', '서울시 강서구 등촌로39가길 15-6', '2021-10-17', 10000, '3', '2021-10-18');
insert into errand values(errand_seq.nextval, 4, '2021-10-16', '과외 구합니다', '요즘 코딩이 대세라면서요? 초3남아 코딩과외 구합니다.', '과외/공부', '서울 중구 태평로1가 31', '2021-10-16', 30000, '0', NULL);
insert into errand values(errand_seq.nextval, 3, '2021-10-15', '1일 남친 구해요', '친구 결혼식에 전남친이 올텐데 그날 남친대행 구합니다.', '역할대행', '경기도 파주시 다율동 해올로 20 146-5', '2021-10-24', 50000, '0', NULL);
insert into errand values(errand_seq.nextval, 1, '2021-10-16', '코드좀 대신 짜주세요..', '너무 힘드러여 ㅠㅠ 뇌가 쪼그라들것 같아요', '과외/공부', '파주시 운정1동 해솔로 20', '2021-10-16', 5000, '0', NULL);

-- [심부름 지원]
insert into apply values(apply_seq.nextval, 1, 2, '청소 및 모든 집안일 겁나 잘합니다ㅎㅎ', '2021-10-11', 2);
insert into apply values(apply_seq.nextval, 1, 3, '등촌동 벌레잡이입니다. 맡겨주세요.', '2021-10-11', 1);
insert into apply values(apply_seq.nextval, 2, 3, '마당있는 전원주택입니다^^반려친구들과 편하게 방문하세요^^', '2021-10-14', 2);
insert into apply values(apply_seq.nextval, 4, 1, '우와 강아지야 내가 태워줄게', '2021-10-15', 1);

commit;

