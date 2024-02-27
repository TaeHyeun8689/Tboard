DROP TABLE BOARD PURGE;

CREATE TABLE board (
    board_num NUMBER PRIMARY KEY,
    board_title VARCHAR2(100),
    board_content VARCHAR2(500),
    board_writer VARCHAR2(20),
    board_hit NUMBER,
    board_recommend NUMBER,
    board_reg_date TIMESTAMP,
    board_reply_count NUMBER
    
);

-- board_num 의 값을 자동으로 증가시키기위해 시퀸스를 생성한다.
create SEQUENCE board_num
    start with 1
    increment by 1
    nomaxvalue;
    
-- 트리거를 생성하여 시퀸스의 다음 값을 가져와서 board_num에 할당한다.
create or replace trigger board_trigger
before insert on board
for each row
begin
    select board_num.nextval into :new.board_num from dual;
end;
/
--위 트리거는 board 테이블에 새로운 행이 삽입되기 전에 실행됩니다. 삽입되는 각 행에 대해 시퀸스의 nextval을 가져와서 board_num에 할당한다.

commit;

DROP TRIGGER board_trigger;

SELECT * FROM BOARD;

delete from board where board_num = 24;

SELECT COUNT(*)
		FROM
		board;

