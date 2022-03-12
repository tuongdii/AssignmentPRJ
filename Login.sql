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
CREATE TABLE Product (
    id             VARCHAR(30)     NOT NULL,
    name            NVARCHAR(50)    NOT NULL,
    price           MONEY           NOT NULL,
    description     NVARCHAR(200)   NOT NULL,
    quantity        INT             NOT NULL,
    CONSTRAINT PK_Product PRIMARY KEY (id),
)
GO
CREATE TABLE Orders(
	id int IDENTITY,
	fullname nvarchar(100),
	total money,
	CONSTRAINT PK_Orders PRIMARY KEY (id),
)
GO
CREATE TABLE OrderDetail(
	id              INT             IDENTITY,
    ordersId        INT             NOT NULL,
    productId             VARCHAR(30)     NOT NULL,
    price           MONEY           NOT NULL,
    quantity        INT             NOT NULL,
    total           MONEY           NOT NULL,
    CONSTRAINT PK_OrderDetail PRIMARY KEY (id),
    CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (productId) REFERENCES Product(id),
    CONSTRAINT FK_OrderDetail_Orders FOREIGN KEY (ordersId) REFERENCES Orders(id)
)
GO
CREATE TRIGGER Trigger_OrderDetail
ON OrderDetail AFTER INSERT
AS
BEGIN
	Declare @ordersId INT;
	Declare @productId  VARCHAR(30);
	Declare @quantity INT;
	Declare @total MONEY;

	SELECT @ordersId = ordersId, @productId = productId, @quantity = quantity, @total = total
	FROM inserted

	IF((SELECT quantity FROM Product WHERE id = @productId) >= @quantity)
	BEGIN
		UPDATE Product
		SET quantity = quantity - @quantity
		WHERE id = @productId

		UPDATE Orders
		SET total = total + @total
		WHERE id = @ordersId
	END
	ELSE
		ROLLBACK TRAN;
END
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


INSERT INTO Product (id, name, price, description, quantity)
VALUES 
('BOOK001', 'Java', 80, 'Java fundamentel book', 10),
('BOOK002', 'OOP', 70, 'OOP fundamentel book', 10),
('BOOK003', 'Servlet', 120, 'Servlet fundamentel book', 10),
('BOOK004', 'JSP', 200, 'JSP fundamentel book', 10),
('BOOK005', 'Netbeans', 50, 'Netbeans fundamentel book', 10),
('BOOK006', 'JavaBeans', 125, 'JavaBeans fundamentel book', 10),
('BOOK007', 'Tomcat', 150, 'Tomcat fundamentel book', 10),
('BOOK008', 'JSTL', 75, 'JSTL fundamentel book', 10)
GO
