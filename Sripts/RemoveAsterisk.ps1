(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2010.csv) | 
Foreach-Object {$_ -replace "\*", ""} | 
Set-Content Get-Content C:\Users\Patrick\Documents\CS460\Program3\2010.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2011.csv) | 
Foreach-Object {$_ -replace "\*", ""} | 
Set-Content Get-Content C:\Users\Patrick\Documents\CS460\Program3\2011.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2012.csv) | 
Foreach-Object {$_ -replace "\*", ""} | 
Set-Content Get-Content C:\Users\Patrick\Documents\CS460\Program3\2012.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2013.csv) | 
Foreach-Object {$_ -replace "\*", ""} | 
Set-Content Get-Content C:\Users\Patrick\Documents\CS460\Program3\2013.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2014.csv) | 
Foreach-Object {$_ -replace "\*", ""} | 
Set-Content Get-Content C:\Users\Patrick\Documents\CS460\Program3\2014.csv

