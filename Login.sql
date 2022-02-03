CREATE DATABASE LOGIN 
GO
USE LOGIN
GO
CREATE TABLE Registration
(
	username varchar(20) PRIMARY KEY,
	password varchar(30),
	lastname nvarchar(100),
	isAdmin bit
)
CREATE TABLE Cart
(
	cartId char(3) primary key,
	productName varchar(30),
	price money,
	quantity int,
)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('tuongdii', '20122001', N'Tường Duy', 1)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('duynam', '11022001', N'Duy Nam', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('keylin', '10092001', N'Hà Giang', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('fuongnle', '22042001', N'Nguyên Phương', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('vivaan', '24122001', N'Thị Ngà', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('C.V.T', '01012001', N'Vĩnh Trinh', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('minh2571', '01012001', N'Hồng Minh', 0)
GO
SELECT * FROM Registration

INSERT INTO Cart(cartId, productName, price, quantity) VALUES ('C01', 'InnerB', 320000, 50)
GO
INSERT INTO Cart(cartId, productName, price, quantity) VALUES ('C02', 'Obagi BHA', 680000, 10)
GO
INSERT INTO Cart(cartId, productName, price, quantity) VALUES ('C03', 'Derma forte', 120000, 50)
GO
INSERT INTO Cart(cartId, productName, price, quantity) VALUES ('C04', 'Niacinamide TO', 220000, 50)
GO
INSERT INTO Cart(cartId, productName, price, quantity) VALUES ('C05', 'VitaminC balance', 120000, 50)
GO
SELECT * FROM Cart