


DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS accounts;




CREATE TABLE accounts(
                              uid              VARCHAR(255) PRIMARY KEY,
                              email            VARCHAR(255) NOT NULL UNIQUE,
                              created_on       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_on       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              last_login       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              logged_in        TINYINT(1) DEFAULT 0
);


CREATE TABLE profiles (
                          id VARCHAR(36) PRIMARY KEY, -- UUID for unique profile ID
                          account_uid VARCHAR(255) NOT NULL, -- Foreign key to the accounts table
                          first_name VARCHAR(255), -- Full name or display name
                          last_name VARCHAR(255),
                          date_of_birth DATE, -- Optional, for additional info
                          gender ENUM('male', 'female', 'other'),
                          height DECIMAL(4,1),
                          weight DECIMAL(5,2), 
                          unit_system ENUM('metric', 'imperial') DEFAULT 'metric',
                          FOREIGN KEY (account_uid) REFERENCES accounts(uid) ON DELETE CASCADE,
                          INDEX idx_account_uid (account_uid)
);
