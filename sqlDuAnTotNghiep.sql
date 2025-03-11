-- Tạo cơ sở dữ liệu GiàyHub
CREATE DATABASE GiayHub;
GO
USE GiayHub
GO

DROP DATABASE GiayHub

-- Bảng quản lý tài khoản người dùng (chung cho cả khách hàng và nhân viên)
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY,
    UserName NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    FullName NVARCHAR(100),
    Email NVARCHAR(100) UNIQUE,
    PhoneNumber NVARCHAR(15) UNIQUE,
    CreatedDate DATETIME DEFAULT GETDATE()
);

-- Bảng khách hàng (liên kết với Users)
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY IDENTITY,
    UserID INT UNIQUE, -- Mỗi khách hàng có một tài khoản User
    Address NVARCHAR(255),
    CreatedDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE SET NULL
);

-- Bảng nhân viên (liên kết với Users)
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY IDENTITY,
    UserID INT UNIQUE, -- Mỗi nhân viên có một tài khoản User
    RoleID INT,
    Status BIT DEFAULT 1, -- 1: Hoạt động, 0: Nghỉ việc
    CreatedDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE SET NULL,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

-- Bảng vai trò nhân viên
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY IDENTITY,
    RoleName NVARCHAR(50) NOT NULL UNIQUE
);

-- Bảng sản phẩm
CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY,
    ProductName NVARCHAR(100) NOT NULL UNIQUE,
    Price DECIMAL(18, 2) NOT NULL,
    Stock INT DEFAULT 0,
    Description NVARCHAR(255),
    CreatedDate DATETIME DEFAULT GETDATE()
);

-- Bảng đơn hàng (liên kết với Customers)
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY IDENTITY,
    CustomerID INT NOT NULL,
    OrderDate DATETIME DEFAULT GETDATE(),
    Status NVARCHAR(50) DEFAULT 'Pending', -- Pending, Completed, Cancelled
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

-- Bảng chi tiết đơn hàng
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY IDENTITY,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT DEFAULT 1,
    Price DECIMAL(18, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Bảng hóa đơn (liên kết với Orders)
CREATE TABLE Invoices (
    InvoiceID INT PRIMARY KEY IDENTITY,
    OrderID INT NOT NULL UNIQUE,
    InvoiceDate DATETIME DEFAULT GETDATE(),
    TotalAmount DECIMAL(18, 2) NOT NULL,
    Status NVARCHAR(50) DEFAULT 'Unpaid', -- Unpaid, Paid, Cancelled
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE
);

-- Bảng phương thức thanh toán
CREATE TABLE PaymentMethods (
    PaymentMethodID INT PRIMARY KEY IDENTITY,
    MethodName NVARCHAR(50) NOT NULL UNIQUE
);

-- Bảng thanh toán (liên kết với Invoices và Users)
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY IDENTITY,
    InvoiceID INT NOT NULL,
    UserID INT NOT NULL, -- Ai thực hiện thanh toán? (Khách hàng hoặc nhân viên thu ngân)
    PaymentMethodID INT NOT NULL,
    Amount DECIMAL(18, 2) NOT NULL,
    PaymentDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE SET NULL,
    FOREIGN KEY (PaymentMethodID) REFERENCES PaymentMethods(PaymentMethodID)
);


-- Thêm dữ liệu mẫu vào bảng Roles (Vai trò nhân viên)
INSERT INTO Roles (RoleName) VALUES 
    ('Admin'),
    ('Sales'),
    ('Warehouse');

-- Thêm dữ liệu mẫu vào bảng Employees (Nhân viên)
INSERT INTO Employees (FullName, Email, PhoneNumber, RoleID, Status) VALUES 
    ('John Smith', 'john.smith@example.com', '1234567890', 1, 1),
    ('Emily Brown', 'emily.brown@example.com', '0987654321', 2, 0),
    ('Michael Johnson', 'michael.j@example.com', '1122334455', 3, 1);

-- Thêm dữ liệu mẫu vào bảng Users (Người dùng)
INSERT INTO Users (UserName, Password, FullName, Email, PhoneNumber) VALUES 
    ('admin', 'admin123', 'Admin User', 'admin@example.com', '0912345678'),
    ('sales', 'sales123', 'Sales User', 'sales@example.com', '0998765432'),
    ('warehouse', 'warehouse123', 'Warehouse User', 'warehouse@example.com', '0887654321');

-- Thêm dữ liệu mẫu vào bảng Products (Sản phẩm)
INSERT INTO Products (ProductName, Price, Stock, Description) VALUES 
    ('Nike Air Max', 120.00, 50, 'Giày thể thao Nike'),
    ('Adidas Ultraboost', 150.00, 30, 'Giày chạy bộ Adidas'),
    ('Puma Suede Classic', 80.00, 20, 'Giày thời trang Puma'),
    ('New Balance 574', 100.00, 40, 'Giày casual New Balance'),
    ('Converse Chuck Taylor', 60.00, 60, 'Giày cổ điển Converse');

-- Thêm dữ liệu mẫu vào bảng Customers (Khách hàng) với các quốc gia Châu Âu ngẫu nhiên
INSERT INTO Customers (FullName, Email, PhoneNumber, Address) VALUES 
    ('Alice Dupont', 'alice.dupont@example.fr', '33612345678', 'Paris, France'),
    ('Luca Bianchi', 'luca.bianchi@example.it', '393123456789', 'Rome, Italy'),
    ('Sophie Müller', 'sophie.muller@example.de', '491234567890', 'Berlin, Germany'),
    ('David Smith', 'david.smith@example.uk', '441234567890', 'London, United Kingdom'),
    ('Pablo García', 'pablo.garcia@example.es', '34912345678', 'Madrid, Spain');

INSERT INTO Orders (CustomerID, Status) VALUES 
    (1, 'Completed'),
    (2, 'Pending'),
    (3, 'Cancelled'),
    (4, 'Completed'),
    (5, 'Pending');


-- Thêm dữ liệu mẫu vào bảng OrderDetails (Chi tiết đơn hàng)
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price) VALUES 
    (1, 1, 2, 120.00),
    (1, 2, 1, 150.00),
    (2, 3, 1, 80.00),
    (3, 4, 2, 100.00),
    (4, 5, 1, 60.00);

-- Thêm dữ liệu mẫu vào bảng Invoices (Hóa đơn)
INSERT INTO Invoices (OrderID, TotalAmount, Status) VALUES 
    (1, 390.00, 'Paid'),
    (2, 80.00, 'Unpaid'),
    (4, 60.00, 'Paid');

-- Thêm dữ liệu mẫu vào bảng PaymentMethods (Phương thức thanh toán)
INSERT INTO PaymentMethods (MethodName) VALUES 
    ('Credit Card'),
    ('Cash'),
    ('Bank Transfer');

INSERT INTO Payments (InvoiceID, PaymentMethodID, Amount) VALUES 
    (3, 1, 390.00),  -- Thay 1 bằng 3 cho đúng InvoiceID
    (4, 2, 60.00);   -- InvoiceID = 4 là hợp lệ

SELECT * FROM Invoices

SELECT TABLE_NAME 
FROM INFORMATION_SCHEMA.TABLES 
WHERE TABLE_TYPE = 'BASE TABLE' 
  AND TABLE_CATALOG = 'GiayHub'

DECLARE @sql NVARCHAR(MAX) = '';  -- Khai báo và khởi tạo biến @sql

SELECT @sql += 'SELECT ''' + TABLE_NAME + ''' AS TableName, * FROM [' + TABLE_NAME + ']; '
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_TYPE = 'BASE TABLE' 
  AND TABLE_CATALOG = 'GiayHub';

-- Kiểm tra chuỗi SQL được tạo ra (tùy chọn)
-- SELECT @sql;

EXEC sp_executesql @sql;  -- Thực thi câu lệnh SQL động

SELECT COUNT(*) FROM Users WHERE UserName = 'admin' AND [Password] = 'admin123'

SELECT * FROM ORDERS

SELECT * FROM Invoices

SELECT * FROM Employees

SELECT * FROM Users

SELECT * FROM Products

SELECT * FROM OrderDetails

SELECT * FROM Customers

SELECT COUNT(*) as rowNumber, UserName, [Password], FullName FROM Users WHERE UserName = 'admin' AND [Password] = 'admin123' GROUP BY UserName, [Password], FullName

ALTER TABLE Customers ADD UserID INT UNIQUE;
ALTER TABLE Customers ADD FOREIGN KEY (UserID) REFERENCES Users(UserID);

GO

ALTER TABLE Employees ADD UserID INT UNIQUE;
ALTER TABLE Employees ADD FOREIGN KEY (UserID) REFERENCES Users(UserID);
