-- PRODUCTS
create table products(
ProductID varchar(20),
Price numeric(10,2),
Category varchar(20),
ProductName varchar(20),
Quantity int,
primary key(ProductID));

insert into products values
('mob111',6000,'mobile','LG',300),
('mob112',6500,'mobile','SAMSUNG',300),
('mob113',10000,'mobile','REDMI',400),
('mob114',12000,'mobile','SAMSUMG',200),
('ac111',20000,'ac','LG',200),
('ac112',35000,'ac','LG',330),
('ac113',25000,'ac','VOLTAS',280),
('ac114',32000,'ac','HUAWEI',100),
('tv111',23000,'tv','LG',200),
('tv112',46000,'tv','SONY',500),
('tv113',36000,'tv','SONY',340),
('tv114',51000,'tv','SAMSUNG',200);

select * from products;

-- SUPPLIERS
create table suppliers(
SupplierID varchar(20),
SupplierName varchar(20),
joined date,
primary key(SupplierID));

insert into suppliers values
('supp111','Amit',date '2012-02-08'),
('supp112','Sambit',date '2016-01-23'),
('supp113','Modi', date '2020-08-12');

select * from suppliers;

-- SUPPLIES
create table supplies(
SupplierID varchar(20),
ProductID varchar(20),
primary key(SupplierId,ProductID),
foreign key (SupplierID) references suppliers(SupplierID),
foreign key (ProductID) references products(ProductID));

insert into supplies values
('supp111','ac111'),
('supp112','ac111'),
('supp111','ac112'),
('supp113','tv111'),
('supp113','mob111'),
('supp112','mob111'),
('supp111','tv113');

select * from supplies;

-- AC
create table ac(
ProductID varchar(20),
Capacity numeric(10,2),
Voltage int,
Min_Temp int,
primary key (ProductID),
foreign key (ProductID) references products(ProductID));

insert into ac values
('ac111',1,5,18),
('ac112',2,3,16),
('ac113',1.5,4,18),
('ac114',2,6,16);

select * from ac;


-- TV
create table tv(
ProductID varchar(20),
Size numeric(10,2),
Resolution numeric(20,2),
USB_port varchar(20),
primary key (ProductID),
foreign key (ProductID) references products(ProductID));

insert into tv values
('tv111',23,480,'no'),
('tv112',43,1080,'yes'),
('tv113',32,480,'yes'),
('tv114',42,20,'yes');

select * from tv;

-- MOBILE
create table mobile(
ProductID varchar(20),
RAM numeric(10,2),
Battery int,
Display int,
primary key (ProductID),
foreign key (ProductID) references products(ProductID));

insert into mobile values
('mob111',3,3000,5),
('mob112',4,3050,5),
('mob113',4,4000,6),
('mob114',6,4500,6);

select * from mobile;

-- CUSTOMERS
create table customers(
CustomerID varchar(20),
UserName varchar(20),
Mobile varchar(10),
Town varchar(20),
District varchar(20),
State varchar(20),
Email varchar(50) unique,
password varchar(20),
primary key(CustomerID)); 

insert into customers values
('cust111','myWish','7619048338','gurs','kannauj','up','may@gmail.com','xyz');

select * from customers;

-- SHIPPER
create table shippers(
ShipperID varchar(20),
ShipperName varchar(20),
phoneNo varchar(10),
primary key(ShipperID));

insert into shippers values
('ship111','Govinda','7818812345'),
('ship112','Akshay','9618112345'),
('ship113','Kapil','8618822345');

select * from shippers;

-- ORDER
create table orders(
OrderID varchar(20),
CustomerID varchar(20),
ShipperID varchar(20),
PaymentID varchar(20),
Amount numeric(10,2),
OrderStatus varchar(20),
PromisedDate date,
DeliveryCharges numeric(20,2),
Placed_date date,
primary key(OrderID),
foreign key(CustomerID) references customers(CustomerID),
foreign key(ShipperID) references shippers(ShipperID));

insert into orders values
('ord111','cust111','ship111','pay111',6000,'completed',date '2019-02-08',0,date '2019-02-02'),
('ord112','cust111','ship112','pay112',23000,'completed',date '2020-03-15',0,date '2020-03-11');

select * from orders;


-- CONTAINS
create table contains(
OrderID varchar(20),
ProductID varchar(20),
QuantityOrdered int,
primary key(OrderID,ProductID),
foreign key(OrderID) references orders(OrderID),
foreign key(ProductID) references products(ProductID));

insert into contains values
('ord111','mob111',1),
('ord112','tv112',1);

select * from contains;

-- ADMIN
create table admintable(
AdminEmail varchar(20) unique,
Password varchar(20),
primary key(AdminEmail));

insert into admintable value('admin','admin123');

create table cart(
cartid varchar(20),
CustomerID varchar(20),
primary key(cartid),
foreign key(CustomerID) references customers(CustomerID)
);

insert into cart values
('cart3','cust3'),
('cart4','cust4');

select * from cart;

create table cartContains(
cartid varchar(20),
ProductID varchar(20),
Quantity numeric(2,0),
foreign key(cartid) references cart(cartid),
foreign key(ProductID) references products(ProductID),
primary key(cartid,ProductID)
);

insert into cartcontains values
('cart111','ac111',1);

select * from cartcontains;

-- Some Queries 

select cartcontains.ProductID,cartcontains.Quantity,products.ProductName,products.Price
from customers,cart,cartContains,products
where customers.Email='may@gmail.com' and customers.CustomerID=cart.CustomerID 
and cart.cartid=cartcontains.cartid   and cartcontains.ProductID=products.ProductID;


select products.ProductID,products.Price,cartContains.Quantity 
from products,cartContains,cart,customers 
where products.ProductID=cartContains.ProductID and
 cartContains.cartid=cart.cartid and
 cart.customerID=customers.customerID and
 customers.Email='may@gmail.com';
 
 
select x.CustomerID,count(x.OrderID) as count from
(select customers.CustomerID,orders.OrderID from
customers,orders,contains,supplies 
where customers.CustomerID=orders.CustomerID and 
orders.OrderID=contains.OrderID and 
contains.ProductID=supplies.ProductID and 
supplies.SupplierID='supp111') as x
group by x.CustomerID
having count>=2;

UPDATE products 
SET Quantity=Quantity+2
where ProductID='ac111';


select * from orders,contains,products 
where orders.OrderID=contains.OrderID and 
contains.ProductID=products.ProductID and 
contains.QuantityOrdered>=1 and Placed_date='2020-03-11' and
 contains.ProductID='tv112';
 
select * from orders where Placed_date='2020-03-11';

select * from suppliers,supplies,products
where suppliers.SupplierID=supplies.SupplierID and
supplies.ProductID=products.ProductID and products.Category='ac';

select OrderID from orders where Placed_date<'2020-02-03' and Placed_date>'2020-02-01';