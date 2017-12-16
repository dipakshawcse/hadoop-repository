-- Load data into the hive table 
LOAD DATA LOCAL INPATH '/home/NN/HadoopRepo/Hive/data' INTO TABLE complexInHive;

-- Fetch the data from hive table
 select * from complexInHive;

-- Find employee who have Informatica skill knowledge?
select * from complexInHive lateral view explode(skill) exploded_table as exp where exp= "INFORMATICA";

-- Find no. of employee who are from kolkata?
select * from complexInHive where permanent_address.city='kolkata';

-- Find all employee details who is having 2 years of hadoop experience?
select * from complexInHive where experience_with_domain['HADOOP']>=2;