DROP TABLE users purge;

CREATE TABLE users (
    user_id VARCHAR2(20) PRIMARY KEY,
    user_ps VARCHAR2(20),
    user_name VARCHAR2(20),
    user_sex VARCHAR2(4),
    user_email VARCHAR2(40),
    user_e_active_key VARCHAR2(255),
    user_phone VARCHAR2(20),
    user_birthday NUMBER,
    user_active_state NUMBER,
    user_reg_date TIMESTAMP,
    user_latest_login TIMESTAMP,
    user_recommend_active TIMESTAMP
);

select * from users;

SELECT *
		FROM users
		WHERE user_id = xogus8689
		AND user_ps = a5639892;
