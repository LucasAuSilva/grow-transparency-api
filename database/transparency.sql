CREATE TABLE Users 
( 
 id INT PRIMARY KEY AUTO_INCREMENT,  
 name VARCHAR(n) NOT NULL,  
 last_name VARCHAR(n) NOT NULL,  
 password VARCHAR(n) NOT NULL,  
 role INT,  
 org_id INT,  
); 

CREATE TABLE Roles 
( 
 id INT PRIMARY KEY AUTO_INCREMENT,  
 name VARCHAR(n),  
 description VARCHAR(n),  
); 

CREATE TABLE Organization 
( 
 id INT PRIMARY KEY AUTO_INCREMENT,  
 name VARCHAR(n) NOT NULL,  
); 

CREATE TABLE Project 
( 
 id INT PRIMARY KEY AUTO_INCREMENT,  
 name VARCHAR(n) NOT NULL,  
 description VARCHAR(n) NOT NULL,  
 price FLOAT NOT NULL,  
 hours INT NOT NULL,  
 status INT NOT NULL,  
 points INT NOT NULL,  
 link VARCHAR(n),  
 archive VARCHAR(n),  
 org_id INT,  
); 

ALTER TABLE Users ADD FOREIGN KEY(role) REFERENCES Roles (role)
ALTER TABLE Users ADD FOREIGN KEY(org_id) REFERENCES Organization (org_id)
ALTER TABLE Project ADD FOREIGN KEY(org_id) REFERENCES Organization (org_id)