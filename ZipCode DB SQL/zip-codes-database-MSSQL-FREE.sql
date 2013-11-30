/*
------------------------------------------------------------------
Provider:  Zip-Codes.com
Product:   U.S. ZIP Code Database Free
------------------------------------------------------------------
This SQL Creates a new table named ZIPCodes, 
related indexes, and extended column information.

This script is designed to work with MS SQL Server 2000 & 2005

Actions:
  1.) Drop Table ZIPCodes if it exists
  2.) Creates Table named ZIPCodes
  3.) Creates Indexes on table ZIPCodes
  4.) Creates Extended Column Information

Last Updated: 12/21/2007
------------------------------------------------------------------
*/


/* 1.) Drop Table if it Exists */
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[ZIPCodes]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[ZIPCodes]
GO


/* 2.) Create Table */
CREATE TABLE [dbo].[ZIPCodes] (
	[ZipCode] [char] (5) NOT NULL,
	[City] [varchar] (35) NULL,
	[State] [char] (2),
	[Latitude] [decimal](12, 4),
	[Longitude] [decimal](12, 4),
	[Classification] [varchar] (1) NULL,
	[Population] [int]
) ON [PRIMARY]
GO


/* 3.) Create Indexes on most searched fields */
CREATE  INDEX [Index_ZIPCodes_ZipCode]				 ON [dbo].[ZIPCodes]([ZipCode]) ON [PRIMARY]
CREATE  INDEX [Index_ZIPCodes_State]				 ON [dbo].[ZIPCodes]([State]) ON [PRIMARY]
CREATE  INDEX [Index_ZIPCodes_City]					 ON [dbo].[ZIPCodes]([City]) ON [PRIMARY]
CREATE  INDEX [Index_ZIPCodes_Latitude]				 ON [dbo].[ZIPCodes]([Latitude]) ON [PRIMARY]
CREATE  INDEX [Index_ZIPCodes_Longitude]			 ON [dbo].[ZIPCodes]([Longitude]) ON [PRIMARY]
GO


/* 4.) Create Extended Column Information */
exec sp_addextendedproperty N'MS_Description', N'U.S. Zip Code Database – Standard (from www.zip-codes.com)', N'user', N'dbo', N'table', N'ZIPCodes'
exec sp_addextendedproperty N'MS_Description', N'00000-99999 Five digit numeric ZIP Code of the area.', N'user', N'dbo', N'table', N'ZIPCodes', N'column', N'ZipCode'
exec sp_addextendedproperty N'MS_Description', N'2 letter state name abbreviation.', N'user', N'dbo', N'table', N'ZIPCodes', N'column', N'State'
exec sp_addextendedproperty N'MS_Description', N'Geographic coordinate as a point measured in degrees north or south of the equator.', N'user', N'dbo', N'table', N'ZIPCodes', N'column', N'Latitude'
exec sp_addextendedproperty N'MS_Description', N'Geographic coordinate as a point measured in degrees east or west of the Greenwich Meridian.', N'user', N'dbo', N'table', N'ZIPCodes', N'column', N'Longitude'
GO