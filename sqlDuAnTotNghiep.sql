-- Tạo cơ sở dữ liệu GiàyHub
CREATE DATABASE GiayHub;
GO
USE GiayHub
GO

DROP DATABASE GiayHub

CREATE TABLE Roles (
    RoleID INT IDENTITY(1,1) PRIMARY KEY,
    RoleName VARCHAR(50) NOT NULL CHECK (RoleName IN ('Admin', 'Employee'))
);

GO

CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15),
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

EXEC sp_rename 'Users.PasswordHash', 'Password', 'COLUMN';

ALTER TABLE Users
ADD Images VARCHAR(20)

GO

CREATE TABLE Products (
    ProductID INT IDENTITY(1,1) PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    Price DECIMAL(10,2) NOT NULL,
    StockQuantity INT NOT NULL
);

GO

CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    CustomerName VARCHAR(100) NOT NULL,
    OrderDate DATETIME DEFAULT GETDATE(),
    Status VARCHAR(20) DEFAULT 'Pending'
);

GO

CREATE TABLE OrderDetails (
    OrderDetailID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

GO

CREATE TABLE Payments (
    PaymentID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT,
    PaymentMethod VARCHAR(20) NOT NULL,
    PaymentStatus VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

GO

CREATE TABLE Invoices (
    InvoiceID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT,
    IssueDate DATETIME DEFAULT GETDATE(),
    TotalAmount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

GO

CREATE TABLE Employees (
    EmployeeID INT IDENTITY(1,1) PRIMARY KEY,
    FullName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15),
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

GO

CREATE TABLE Reports (
    ReportID INT IDENTITY(1,1) PRIMARY KEY,
    GeneratedDate DATETIME DEFAULT GETDATE(),
    TotalOrders INT DEFAULT 0,
    CompletedOrders INT DEFAULT 0,
    PendingOrders INT DEFAULT 0,
    ProcessingOrders INT DEFAULT 0,
    TotalSales DECIMAL(10,2) DEFAULT 0, 
    TopProduct VARCHAR(100), 
    BestEmployee VARCHAR(100)
);

GO

-- Insert sample data
INSERT INTO Roles (RoleName) VALUES ('Admin'), ('Employee');

INSERT INTO Users (Username, PasswordHash, Email, PhoneNumber, RoleID, Images) VALUES
('admin1', 'hashedpassword1', 'admin1@example.com', '0123456789', 1),
('employee1', 'hashedpassword2', 'employee1@example.com', '0987654321', 2);

INSERT INTO Products (ProductName, Description, Price, StockQuantity) VALUES
('Nike Air Max', 'Running shoes', 120.50, 10),
('Adidas Ultraboost', 'Comfortable sneakers', 150.00, 15),
('Puma RS-X', 'Stylish streetwear shoes', 110.75, 20);

INSERT INTO Orders (CustomerName, OrderDate, Status) VALUES
('John Doe', GETDATE(), 'Pending'),
('Jane Smith', GETDATE(), 'Processing');

INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price) VALUES
(1, 1, 2, 120.50),
(1, 2, 1, 150.00),
(2, 3, 1, 110.75);

INSERT INTO Payments (OrderID, PaymentMethod, PaymentStatus) VALUES
(1, 'Credit Card', 'Completed'),
(2, 'PayPal', 'Pending');

INSERT INTO Invoices (OrderID, IssueDate, TotalAmount) VALUES
(1, GETDATE(), 391.00),
(2, GETDATE(), 110.75);

INSERT INTO Employees (FullName, Email, PhoneNumber, RoleID) VALUES
('Alice Johnson', 'alice@example.com', '0123456789', 2),
('Bob Williams', 'bob@example.com', '0987654321', 2);

INSERT INTO Reports (GeneratedDate, TotalOrders, CompletedOrders, PendingOrders, ProcessingOrders, TotalSales, TopProduct, BestEmployee) VALUES
(GETDATE(), 5, 3, 1, 1, 5000.00, 'Nike Air Max', 'Alice Johnson');

SELECT * FROM Users