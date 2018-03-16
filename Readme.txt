Sean Gallagher
Program 3

Inserts standaradised testing data to database

Included are multiple files

Java
Prog3.java					--driver
CSVToDB.java				--Used to cleanup and create sql files


SQL							--Used to create and fill tables
createCounties.sql			--creates 15 county tables
countyInsert.sql			--inserts into county tables

create1.sql					--creates table for 2010
create2.sql					--creates table for 2012
create3.sql					--creates table for 2013
create4.sql					--creates table for 2014
create5.sql					--creates table for 2015
createAll.sql				--runs create1-5
Year2010insert.sql			--inserts values into table
Year2011insert.sql			--inserts values into table
Year2012insert.sql			--inserts values into table
Year2013insert.sql			--inserts values into table
Year2014insert.sql			--inserts values into table
insertAll.sql				--runs YearXinsert for each year

Scripts						--Used to clean up csv data
CleanUp.ps1					--removes null values
RemoveAsterisk.ps1			--removes asterisk --wasn't used
RemoveDubQ.ps1				--removes double quotes
