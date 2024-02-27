CREATE TABLE reply (
    reply_num NUMBER PRIMARY KEY, -- 댓글번호, index로 사용됨
    board_num NUMBER, -- 게시물 번호를 참조하는 외래키
    reply_id VARCHAR2(40), -- 댓글 작성자의 ID
    reply_content VARCHAR2(500), -- 댓글 내용
    reply_reg_date TIMESTAMP, -- 댓글 등록 시간
    CONSTRAINT fk_board_num FOREIGN KEY (board_num) REFERENCES board(board_num)
);

select * from reply;

drop table reply purge;

DROP TRIGGER reply_trigger;

commit;

-- board_num 의 값을 자동으로 증가시키기위해 시퀸스를 생성한다.
create SEQUENCE reply_num
    start with 1
    increment by 1
    nomaxvalue;
    
-- 트리거를 생성하여 시퀸스의 다음 값을 가져와서 board_num에 할당한다.
create or replace trigger reply_trigger
before insert on reply
for each row
begin
    select reply_num.nextval into :new.reply_num from dual;
end;
/