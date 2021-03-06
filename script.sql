USE [TriviaGame]
GO
/****** Object:  User [TriviaMaster]    Script Date: 2019-03-08 09:18:42 ******/
CREATE USER [TriviaMaster] FOR LOGIN [TriviaMaster] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_datareader] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_denydatareader] ADD MEMBER [TriviaMaster]
GO
ALTER ROLE [db_denydatawriter] ADD MEMBER [TriviaMaster]
GO
/****** Object:  Table [dbo].[Scores]    Script Date: 2019-03-08 09:18:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Scores](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[userid] [bigint] NULL,
	[total_score] [int] NULL,
	[current_score] [int] NULL,
 CONSTRAINT [PK_Scores] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 2019-03-08 09:18:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NULL,
	[is_online] [bit] NULL,
	[name] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[scoreid] [bigint] NULL,
 CONSTRAINT [PK__users__3213E83FC03C023C] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Scores]  WITH CHECK ADD  CONSTRAINT [FK_Scores_users] FOREIGN KEY([userid])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[Scores] CHECK CONSTRAINT [FK_Scores_users]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_users_Scores] FOREIGN KEY([scoreid])
REFERENCES [dbo].[Scores] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK_users_Scores]
GO
