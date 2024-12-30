-- Create the hr_leave_management database
CREATE DATABASE hr_leave_management;

-- Switch to the hr_leave_management database
USE hr_leave_management;

-- Create the userinfo table
CREATE TABLE userinfo (
  id INT AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  active BOOLEAN,
  last_name VARCHAR(30),
  first_name VARCHAR(30) NOT NULL,
  password VARCHAR(255),
  role VARCHAR(20),
  PRIMARY KEY (id)
);

-- Insert a sample manager user
INSERT INTO userinfo (email, active, last_name, first_name, password, role) 
VALUES ('manager@email.com', TRUE, 'Manager', 'Project', '$2a$10$pNOQHgXFKcfrqzdgsPI2oOBJlXaGiAWQqh8fy2sAV9cOtpzfiyBf.', 'MANAGER');

-- Create the leave_details table
CREATE TABLE leave_details (
  id INT AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  employee_name VARCHAR(50) NOT NULL,
  from_date DATETIME NOT NULL,
  to_date DATETIME NOT NULL,
  leave_type VARCHAR(50) NOT NULL,
  reason VARCHAR(300) NOT NULL,
  duration INT,
  accept_reject_flag BOOLEAN,
  active BOOLEAN,
  PRIMARY KEY (id)
);

-- Optional: Insert a sample leave request
INSERT INTO leave_details (username, employee_name, from_date, to_date, leave_type, reason, duration, accept_reject_flag, active)
VALUES ('jdoe', 'John Doe', '2024-01-01 09:00:00', '2024-01-05 18:00:00', 'Vacation', 'Family trip', 5, NULL, TRUE);
