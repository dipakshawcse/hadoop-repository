CREATE EXTERNAL TABLE `package_report_temp`(
  `localcode` array<map<string,string>> ,
  `lencode` string ,
  `articlecode` string ,
  `productid` string ,
  `packagingid` string ,
  `eucode` string ,
  `productlifecyclestatus` string ,
  `salesuomlegacy` string ,
  `displayunitofmeasure` string ,
  `contentquantitylegacy` string ,
  `contentuomlegacy` string ,
  `conversionfactortobaseunit` string ,
  `webid` string ,
  `webidexport` array<map<string,string>> ,
  `packaginglinelegacy` array<map<string,string>> ,
  `promotionalproductpackagingline` string ,
  `webproducttitleadvantagelegacy` array<map<string,string>> ,
  `maingtin` string ,
  `gtin` map<string,array<string>> ,
  `duplicatepackagings` string )
ROW FORMAT SERDE
  'org.openx.data.jsonserde.JsonSerDe'
STORED AS INPUTFORMAT
  'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION
  'hdfs://centos7-1.0102wnbqrtjuzfcm5j10ce4rjb.bx.internal.cloudapp.net:8020/report/packageReport';
