CREATE DATABASE LOGIN 
GO
USE LOGIN
GO
CREATE TABLE Registration
(
	username varchar(20) PRIMARY KEY,
	password char(32),
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
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('tuongdii', '44c1bc87d6067efc72a3f7ba290cf297', N'Tường Duy', 1)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('duynam', '6224818cfe08329f1c9d542b3eba96fa', N'Duy Nam', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('keylin', '9ff35bcde5e98584e0ceffcb72e58825', N'Hà Giang', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('fuongnle', 'aa1b2cf1c270235ded6605f9ba3e3897', N'Nguyên Phương', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('vivaan', '9573a2e9f1a1b0b7c224eee12acc2445', N'Thị Ngà', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('C.V.T', '21c70385c4350b2ba165637ba7dc28e3', N'Vĩnh Trinh', 0)
GO
INSERT INTO Registration(username, password, lastname, isAdmin) VALUES ('minh2571', '21c70385c4350b2ba165637ba7dc28e3', N'Hồng Minh', 0)
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