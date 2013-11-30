/*
------------------------------------------------------------------
Provider:  Zip-Codes.com
Product:   U.S. ZIP Code Database Free
------------------------------------------------------------------
This SQL Creates a new table named ZIPCodes, 
related indexes, and extended column information.

This script is designed to work with MySQL

Actions:
  1.) Drop Table ZIPCodes if it exists
  2.) Creates Table named ZIPCodes
  3.) Creates Indexes on table ZIPCodes
  4.) Creates Extended Column Information

Last Updated: 07/07/2011
------------------------------------------------------------------
*/


/* 1.) Drop Table if it Exists */
DROP TABLE IF EXISTS ZIPCodes;



/* 2.) Create Table */
CREATE TABLE ZIPCodes (
	ZipCode char(5) NOT NULL,
	City varchar(35) NULL,
	State char(2),
	Latitude decimal(12, 4),
	Longitude decimal(12, 4),
	Classification varchar(1) NULL,
	Population int
);



/* 3.) Create Indexes on most searched fields */
CREATE INDEX Index_ZIPCodes_ZipCode					 ON ZIPCodes (ZipCode);
CREATE INDEX Index_ZIPCodes_State					 ON ZIPCodes (State);
CREATE INDEX Index_ZIPCodes_City					 ON ZIPCodes (City);
CREATE INDEX Index_ZIPCodes_Latitude				 ON ZIPCodes (Latitude);
CREATE INDEX Index_ZIPCodes_Longitude				 ON ZIPCodes (Longitude);



/* 4.) Create Extended Column Information */
ALTER TABLE ZIPCodes COMMENT = 'U.S. Zip Code Database – Free (from www.zip-codes.com)';