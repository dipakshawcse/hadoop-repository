-- Load Data from file
LOAD DATA LOCAL INPATH '/user/data/employee.txt' INTO TABLE partitioned_temp;

-- Load data from another hive table
LOAD DATA LOCAL INPATH '/user/data/employee.txt' 
INTO TABLE partitioned_test_managed
PARTITION (yearofexperience=3);

-- Load data from file
INSERT OVERWRITE TABLE partitioned_test_managed
PARTITION(dateValue=20160101) 
SELECT * FROM partitioned_temp;

-- Load data from another hive table
INSERT OVERWRITE TABLE partitioned_test_managed
PARTITION(dateValue=20160101) 
SELECT * FROM partitioned_temp;

-- Alter the hive table to add partition
ALTER TABLE partitioned_test_external 
ADD PARTITION (yearofexperience=3) 
LOCATION '/user/hiveData/yearofexperience=3';

-- Load data as Dynamic partition
INSERT OVERWRITE TABLE partitioned_test_external_dyn
PARTITION(yearofexperience) 
SELECT empId,firstname,lastname,city,mobile,yearofexperience FROM partitioned_temp;