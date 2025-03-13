CREATE DATABASE GiayHub
GO
USE GiayHub
GO

DROP DATABASE GiayHub

CREATE TABLE Roles (
    RoleID INT PRIMARY KEY,
    RoleName NVARCHAR(255) NOT NULL
);

CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(255) NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
    RoleID INT,
    Images NVARCHAR(MAX),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);
 

CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    FullName NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
	Address NVARCHAR(MAX),
	RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

CREATE TABLE Admins (
    AdminID INT PRIMARY KEY,
    FullName NVARCHAR(255) NOT NULL,
    Email NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName NVARCHAR(255) NOT NULL,
    Description NVARCHAR(MAX),
    Price DECIMAL(10, 2) NOT NULL,
    StockQuantity INT,
    Size NVARCHAR(50),
    Color NVARCHAR(50)
);

CREATE TABLE Suppliers (
    SupplierID INT PRIMARY KEY,
    SupplierName NVARCHAR(255) NOT NULL,
    ContactName NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
    Address NVARCHAR(MAX)
);

CREATE TABLE ImportProducts (
    ImportID INT PRIMARY KEY,
    ProductID INT,
    SupplierID INT,
    ImportDate DATE,
    Quantity INT,
    ImportPrice DECIMAL(10, 2),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
);

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    Status NVARCHAR(50),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE OrderDetails (
    OrderDetailsID INT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    TotalPrice DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE Invoices (
    InvoiceID INT PRIMARY KEY,
    OrderID INT,
    IssueDate DATE,
    TotalMoney DECIMAL(10, 2),
    PaymentMethod NVARCHAR(50),
    PaymentStatus NVARCHAR(50),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

GO
-- Roles
INSERT INTO Roles (RoleID, RoleName) VALUES
(1, 'Admin'),
(2, 'Customer');

GO
-- Users
INSERT INTO Users (Username, Password, Email, PhoneNumber, RoleID, Images) VALUES
('admin1', 'pass1', 'admin1@example.com', '555-0001', 1, NULL),
('customer1', 'pass2', 'customer1@example.com', '555-0101', 2, NULL),
('customer2', 'pass3', 'customer2@example.com', '555-0202', 2, NULL)

GO
-- Customers
INSERT INTO Customers (CustomerID, FullName, Email, PhoneNumber, Address, RoleID) VALUES
(1, 'John Doe', 'john@example.com', '555-0101', '123 Main St', 2),
(2, 'Jane Smith', 'jane@example.com', '555-0202', '456 Oak Ave', 2);

GO
-- Admins
INSERT INTO Admins (AdminID, FullName, Email, PhoneNumber, RoleID) VALUES
(1, 'Alice Johnson', 'alice@example.com', '555-0010', 1),
(2, 'Bob Wilson', 'bob@example.com', '555-0020', 1);

GO
-- Products
INSERT INTO Products (ProductID, ProductName, Description, Price, StockQuantity, Size, Color) VALUES
(1, 'Cotton T-Shirt', 'Comfortable cotton t-shirt', 19.99, 100, 'M', 'Blue'),
(2, 'Slim Fit Jeans', 'Stretch denim jeans', 49.99, 50, '32W', 'Black'),
(3, 'Running Shoes', 'Lightweight sports shoes', 89.99, 30, '42', 'White');

GO
-- Suppliers
INSERT INTO Suppliers (SupplierID, SupplierName, ContactName, PhoneNumber, Address) VALUES
(1, 'FashionHub', 'Sarah Lee', '555-1000', '789 Fashion Ave'),
(2, 'SportGear Ltd', 'Mike Chen', '555-2000', '456 Athletic Rd');

GO
-- ImportProducts
INSERT INTO ImportProducts (ImportID, ProductID, SupplierID, ImportDate, Quantity, ImportPrice) VALUES
(101, 1, 1, '2023-01-15', 100, 10.00),
(102, 2, 1, '2023-02-01', 50, 25.00),
(103, 3, 2, '2023-03-10', 30, 50.00);

GO
-- Orders
INSERT INTO Orders (OrderID, CustomerID, OrderDate, Status) VALUES
(1001, 1, '2023-04-01', 'Delivered'),
(1002, 2, '2023-04-05', 'Processing');

GO
-- OrderDetails
INSERT INTO OrderDetails (OrderDetailsID, OrderID, ProductID, Quantity, TotalPrice) VALUES
(5001, 1001, 1, 2, 39.98),
(5002, 1001, 3, 1, 89.99),
(5003, 1002, 2, 1, 49.99);

GO
-- Invoices
INSERT INTO Invoices (InvoiceID, OrderID, IssueDate, TotalMoney, PaymentMethod, PaymentStatus) VALUES
(2001, 1001, '2023-04-02', 129.97, 'Credit Card', 'Paid'),
(2002, 1002, '2023-04-06', 49.99, 'PayPal', 'Pending')

SELECT * FROM Users

SELECT COUNT(*) as rowNumber, UserName, Password 
FROM Users
WHERE Username = 'admin1'
AND [Password] = 'pass1'
GROUP BY UserName, Password