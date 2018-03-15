(Get-Content C:\Users\Patrick\Documents\CS460\Program3\Year2010insert.sql) | 
Foreach-Object {$_ -replace '"', ""} | 
Set-Content C:\Users\Patrick\Documents\CS460\Program3\Year2010insert.sql

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\Year2011insert.sql) | 
Foreach-Object {$_ -replace '"', ""} | 
Set-Content C:\Users\Patrick\Documents\CS460\Program3\Year2011insert.sql

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\Year2012insert.sql) | 
Foreach-Object {$_ -replace '"', ""} | 
Set-Content C:\Users\Patrick\Documents\CS460\Program3\Year2012insert.sql

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\Year2013insert.sql) | 
Foreach-Object {$_ -replace '"', ""} | 
Set-Content C:\Users\Patrick\Documents\CS460\Program3\Year2013insert.sql

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\Year2014insert.sql) | 
Foreach-Object {$_ -replace '"', ""} | 
Set-Content C:\Users\Patrick\Documents\CS460\Program3\Year2014insert.sql
