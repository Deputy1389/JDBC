set autocommit off;
drop table Year2014 PURGE;
create table Year2014 (
	FiscalYear				integer,
	St						varchar(200),
	County					varchar(200),
	LEAEntityID         	integer,
	LEACTDSNumber			integer,
	LEAName					varchar(200),
	SchoolEntityID    		integer,
	SchoolCTDSNumber		integer,
	SchoolName				varchar(200),
	CharterSchool			varchar(200),
	MathMeanScaleScore		integer,
	MathFallsFarBelowPer	integer,
	MathApproachesPer		integer,
	MathMeetsPer			integer,
	MathExceedsPer			integer,
	MathPassingPer			integer,
	ReaMeanScaleScore		integer,
	ReaFallsFarBelowPer		integer,
	ReaApproachesPer		integer,
	ReaMeetsPer				integer,
	ReaExceedsPer			integer,
	ReaPassingPer			integer,
	WriMeanScaleScore		integer,
	WriFallsFarBelowPer		integer,
	WriApproachesPer		integer,
	WriMeetsPer				integer,
	WriExceedsPer			integer,
	WriPassingPer			integer,
	SciMeanScaleScorePer	integer,
	SciFallsFarBelowPer		integer,
	SciApproachesPer		integer,
	SciMeetsPer				integer,
	SciExceedsPer			integer,
	SciPassingintegerPer 	integer,
    primary key (SchoolEntityID)
);