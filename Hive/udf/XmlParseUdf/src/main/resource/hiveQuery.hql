-- Create temp hive table
CREATE EXTERNAL TABLE companyxml(xmldata STRING) LOCATION '/user/dipak/hive/companyxml/company.xml';

-- Drop the view if exist and create view 
DROP VIEW companyview;
CREATE VIEW companyview(id,name,email,houseno,street,city,state,pincode,country,passport,visa,mobile,phone) 
AS SELECT 
xpath(xmldata,'Company/Employee/Id/text()'),
xpath(xmldata,'Company/Employee/Name/text()'),
xpath(xmldata,'Company/Employee/Email/text()'),
xpath(xmldata,'Company/Employee/Address/HouseNo/text()'),
xpath(xmldata,'Company/Employee/Address/Street/text()'),
xpath(xmldata,'Company/Employee/Address/City/text()'),
xpath(xmldata,'Company/Employee/Address/State/text()'),
xpath(xmldata,'Company/Employee/Address/Pincode/text()'),
xpath(xmldata,'Company/Employee/Address/Country/text()'),
xpath(xmldata,'Company/Employee/Passport/text()'),
xpath(xmldata,'Company/Employee/Visa/text()'),
xpath(xmldata,'Company/Employee/Contact/Mobile/text()'),
xpath(xmldata,'Company/Employee/Contact/Phone/text()')
FROM companyxml;

-- Add created udf jar
ADD JAR /home/NN/HadoopRepo/Hive/udf/XmlParseUdf-0.0.1-SNAPSHOT.jar;

-- Create a temp function
CREATE TEMPORARY FUNCTION xmlUDF as 'hive.udf.XmlEmptyParse';

-- Drop the view and recreate again using created udf function
DROP VIEW companyview;
CREATE VIEW companyview(id,name,email,houseno,street,city,state,pincode,country,passport,visa,mobile,phone)
 AS SELECT
 xpath(xmldata,'Company/Employee/Id/text()'),
 xpath(xmldata,'Company/Employee/Name/text()'),
 xpath(xmldata,'Company/Employee/Email/text()'),
 xpath(xmldata,'Company/Employee/Address/HouseNo/text()'),
 xpath(xmldata,'Company/Employee/Address/Street/text()'),
 xpath(xmldata,'Company/Employee/Address/City/text()'),
 xpath(xmldata,'Company/Employee/Address/State/text()'),
 xpath(xmldata,'Company/Employee/Address/Pincode/text()'),
 xpath(xmldata,'Company/Employee/Address/Country/text()'),
 xpath(xmldata,'Company/Employee/Passport/text()'),
 xpath(xmlUDF(xmldata),'Company/Employee/Visa/text()'),
 xpath(xmldata,'Company/Employee/Contact/Mobile/text()'),
 xpath(xmlUDF(xmldata),'Company/Employee/Contact/Phone/text()')
 FROM companyxml;

 