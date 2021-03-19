USE [CAFESTORE]
GO
/****** Object:  StoredProcedure [dbo].[SP_INS_DATBAN]    Script Date: 31/12/2020 01:31:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[SP_INS_DATBAN] (
	@THOIGIAN NVARCHAR(10),
	@NGAYDAT NVARCHAR(15),
	@TENKH NVARCHAR(50),
	@SDT NVARCHAR(15),
	@NGAYDEN VARCHAR(25),
	@GHICHU NVARCHAR(100)
) AS
BEGIN
	INSERT INTO DATBAN(THOIGIAN,NGAYDAT,TENKH,SDT,NGAYDEN,GHICHU)
	VALUES (@THOIGIAN,@NGAYDAT,@TENKH,@SDT,CONVERT(datetime,@NGAYDEN,103),@GHICHU)
END

