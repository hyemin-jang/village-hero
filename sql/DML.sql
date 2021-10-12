-- [회원]
insert into member values(member_seq.nextval, 'email1@google.com', '111', '111', '세으니', '20', 'F', '010-1111-1111', '경기도 파주시', '배달', NULL, NULL, NULL, 0, 0);
insert into member values(member_seq.nextval, 'email2@google.com', '222', '222', '기화니', '30', 'M', '010-2222-2222', '경기도 용인시', '청소', NULL, NULL, NULL, 0, 0);
insert into member values(member_seq.nextval, 'email3@google.com', '222', '222', '혜미니', '25', 'F', '010-3333-3333', '서울시 마포구', '돌봄', NULL, NULL, NULL, 0, 0);

-- [심부름 등록]
insert into errand values(errand_seq.nextval, 1, 10000, '2021-10-11', '바퀴벌레 잡아 주실 분ㅠㅠ', '집에 바퀴벌레가 너무 많아요ㅠㅠ 처리 가능한 용자분 찾습니다!', '벌레', '파주', '2021-10-11', 1);
insert into errand values(errand_seq.nextval, 2, 20000, '2021-10-12', '강아지 돌보는 분 급구!!', '혼자 여행 가서 그런데 강아지 사랑으로 돌봐주실 분ㅎㅎ', '돌봄', '서울특별시 강서구 등촌동 524-13', '2021-10-12', 2); 
insert into errand values(errand_seq.nextval, 3, 15000, '2021-10-13', '급하게 서류 전달해 주실 분!?', '급한 건데 몸이 아파서 대신 전달해 주실 분 계실까요~', '배달', '서울시 강서구 등촌로39가길 15-6', '2021-10-13', 1);

-- [심부름 지원]
insert into apply values(apply_seq.nextval, 3, 1, '파주지역 이거저거 배달 가능합니다! 불러주세요~', '2021-10-14', 0);
insert into apply values(apply_seq.nextval, 1, 2, '청소 및 모든 집안일 겁나 잘합니다ㅎㅎ', '2021-10-14', 0);
insert into apply values(apply_seq.nextval, 2, 3, '마당있는 전원주택입니다^^반려친구들과 편하게 방문하세요^^', '2021-10-14', 1);

commit;


