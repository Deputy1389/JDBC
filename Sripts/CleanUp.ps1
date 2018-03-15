(gc C:\Users\Patrick\Documents\CS460\Program3\aims_and_aimsa_2010.csv | ?{$_ -notmatch ",\Z"}) |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2010.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2010.csv) | 
select -Skip 1 |
select-string -pattern ",," -notmatch |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2010.csv





(gc C:\Users\Patrick\Documents\CS460\Program3\aims_and_aimsa_2011.csv | ?{$_ -notmatch ",\Z"}) |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2011.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2011.csv) | 
select -Skip 1 |
select-string -pattern ",," -notmatch |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2011.csv





(gc C:\Users\Patrick\Documents\CS460\Program3\aims_and_aimsa_2012.csv | ?{$_ -notmatch ",\Z"}) |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2012.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2012.csv) | 
select -Skip 1 |
select-string -pattern ",," -notmatch |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2012.csv





(gc C:\Users\Patrick\Documents\CS460\Program3\aims_and_aimsa_2013.csv | ?{$_ -notmatch ",\Z"}) |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2013.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2013.csv) | 
select -Skip 1 |
select-string -pattern ",," -notmatch |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2013.csv





(gc C:\Users\Patrick\Documents\CS460\Program3\aims_and_aimsa_2014.csv | ?{$_ -notmatch ",\Z"}) |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2014.csv

(Get-Content C:\Users\Patrick\Documents\CS460\Program3\2014.csv) | 
select -Skip 1 |
select-string -pattern ",," -notmatch |
Set-Content C:\Users\Patrick\Documents\CS460\Program3\2014.csv