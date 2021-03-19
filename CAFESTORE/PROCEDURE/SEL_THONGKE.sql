SELECT H.MABAN, CT.TONGTIEN, H.THANHTIEN, CT.MANV, CT.NGAYMUA FROM CTHD CT, HOADON H 
WHERE CT.MAHD = H.MAHD
AND CT.NGAYMUA 
	BETWEEN CAST('2020-11-9 9:00' AS DATETIME) 
	AND CAST('2020-11-11 8:00' AS DATETIME)

insert into HOADON (MABAN,MAHD,MASP,SLMUA,THANHTIEN)
values (2,2,1,2,30000)

insert into CTHD(MACTHD,MAHD,MANV,NGAYMUA,TONGTIEN) VALUES
(2,2,'NV01','2020-11-9 9:00',30000)

update CTHD SET MANV='NV01' WHERE MACTHD = 1