USE [master]
GO

/* For security reasons the login is created disabled and with a random password. */
/****** Object:  Login [TriviaMaster]    Script Date: 2019-03-08 09:26:55 ******/
CREATE LOGIN [TriviaMaster] WITH PASSWORD=N'E7DP78TBHM3eBRff4A87g7eBXzDfjn6FaM5Romz+hIA=', DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

ALTER LOGIN [TriviaMaster] DISABLE
GO

ALTER SERVER ROLE [sysadmin] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [securityadmin] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [serveradmin] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [setupadmin] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [processadmin] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [diskadmin] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [dbcreator] ADD MEMBER [TriviaMaster]
GO

ALTER SERVER ROLE [bulkadmin] ADD MEMBER [TriviaMaster]
GO

