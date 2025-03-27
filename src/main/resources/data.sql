DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    uid VARCHAR(255) PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    logged_in TINYINT(1) DEFAULT 0,
    latitude DECIMAL(9,6) NULL, 
    longitude DECIMAL(9,6) NULL
);

CREATE TABLE profiles (
    id VARCHAR(36) PRIMARY KEY,
    account_uid VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender ENUM('male', 'female', 'other') NOT NULL,
    height DECIMAL(4,1) NOT NULL,
    weight DECIMAL(5,2) NOT NULL, 
    unit_system ENUM('metric', 'imperial') DEFAULT 'metric',
    FOREIGN KEY (account_uid) REFERENCES accounts(uid) ON DELETE CASCADE,
    INDEX idx_account_uid (account_uid)
);

CREATE TABLE user_goals (
    id VARCHAR(36) PRIMARY KEY,
    account_uid VARCHAR(255) NOT NULL,
    isActive TINYINT(1) DEFAULT 1,
    bmr DECIMAL(6,2),
    goal_weight DECIMAL(5,2),
    goal_timeline INT,
    goal ENUM('maintain', 'cut', 'bulk') NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_on TIMESTAMP NULL,
    FOREIGN KEY (account_uid) REFERENCES accounts(uid) ON DELETE CASCADE,
    INDEX idx_account_uid (account_uid)
);

CREATE TABLE goal_progress (
    id VARCHAR(36) PRIMARY KEY,
    goal_id VARCHAR(36) NOT NULL,
    recorded_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    weight DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (goal_id) REFERENCES user_goals(id) ON DELETE CASCADE,
    INDEX idx_goal_id (goal_id)
);



CREATE TABLE daily_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_uid VARCHAR(255) NOT NULL,
    log_date DATE NOT NULL,
    UNIQUE (account_uid, log_date)
    -- You can add other metadata fields if needed
);


CREATE TABLE food_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    daily_log_id INT NOT NULL, 
    account_uid VARCHAR(255) NOT NULL,         -- Foreign key to user's account
    food_id VARCHAR(255) NOT NULL,              -- Reference ID for the meal
    food_name VARCHAR(255) NOT NULL,
    food_calories DECIMAL(5,2) NOT NULL  
    food_unit VARCHAR(255) NOT NULL,
    food_brand VARCHER(244),                      -- Total calories for the meal
    quantity DECIMAL(5,2) NOT NULL,                               -- How many servings, etc.
    meal_type ENUM('breakfast', 'lunch', 'dinner', 'snack') NOT NULL,
    log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,                     -- Date the meal was logged
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (account_uid) REFERENCES accounts(uid) ON DELETE CASCADE,
    FOREIGN KEY (daily_log_id) REFERENCES daily_log(id) ON DELETE CASCADE,
    INDEX idx_log_date (log_date), 
    INDEX idx_meal_id (meal_id),
    INDEX idx_daily_log_id (daily_log_id)
);
