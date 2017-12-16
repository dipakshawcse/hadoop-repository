-- Create Temp table
CREATE TABLE partitioned_temp(
empId INT,
firstname STRING,
lastname STRING,
city STRING,
mobile STRING,
yearofexperience INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED as TEXTFILE;


-- Create partitioned_test_managed table
CREATE TABLE partitioned_test_managed(
 empId INT,
 firstname STRING,
 lastname STRING,
 city STRING,
 mobile STRING) 
PARTITIONED BY (yearofexperience INT)
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
STORED as TEXTFILE;

-- Create external table
CREATE TABLE EXTERNAL partitioned_test_external(
empId INT,
firstname STRING,
lastname STRING,
city STRING,
mobile STRING) 
PARTITIONED BY (yearofexperience INT)
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
STORED as TEXTFILE
LOCATION '/user/dipak/hiveData/yearofexperience=3';


-- Create partitioned_test_external_dyn table
CREATE TABLE EXTERNAL partitioned_test_external_dyn(
empId INT,
firstname STRING,
lastname STRING,
city STRING,
mobile STRING) 
PARTITIONED BY (yearofexperience INT)
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n' 
STORED as TEXTFILE
LOCATION '/user/dipak/hiveData';