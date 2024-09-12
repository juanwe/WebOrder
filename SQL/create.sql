-- 用户账户表
CREATE TABLE UserAccount (
                             UserID INT AUTO_INCREMENT PRIMARY KEY, -- 用户ID
                             Name VARCHAR(100),                     -- 姓名
                             Password VARCHAR(100)                  -- 用户密码
);

-- 产品表
CREATE TABLE Product (
                         ProductID INT AUTO_INCREMENT PRIMARY KEY,  -- 产品ID
                         ProductName VARCHAR(100),                  -- 产品名称
                         Price DECIMAL(10, 2),                      -- 单价
                         Description TEXT,                          -- 商品描述
                         Shipping DECIMAL(10, 2),                   -- 运费
                         Weight DECIMAL(10,2),                      -- 重量
                         Image VARCHAR(255)                         -- 商品图片存放位置
);

-- 购物篮表
CREATE TABLE ShoppingCart (
                              CartID INT AUTO_INCREMENT PRIMARY KEY,-- 购物篮ID
                              UserID INT,                           -- 用户ID
                              TotalPrice DECIMAL(10, 2),            -- 总价
                              TotalWeight DECIMAL(10, 2),           -- 总重量
                              FOREIGN KEY (UserID) REFERENCES UserAccount(UserID)
);

-- 购物篮项目表
CREATE TABLE CartItem (
                          ItemID INT AUTO_INCREMENT PRIMARY KEY,    -- 项目ID
                          CartID INT,                               -- 购物篮ID
                          ProductID INT,                            -- 产品ID
                          Quantity INT,                             -- 数量
                          FOREIGN KEY (CartID) REFERENCES ShoppingCart(CartID),
                          FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- 订单表
CREATE TABLE `Order` (
                         OrderID INT AUTO_INCREMENT PRIMARY KEY,    -- 订单ID
                         UserID INT,                                -- 用户ID
                         OrderDate DATE,                            -- 订单日期
                         TotalPrice DECIMAL(10, 2),                 -- 总价
                         ShippingMethod VARCHAR(50),                -- 运输方式
                         TotalWeight DECIMAL(10, 2),                -- 总重量
                         FOREIGN KEY (UserID) REFERENCES UserAccount(UserID)
);

-- 订单项目表
CREATE TABLE OrderItem (
                           ItemID INT AUTO_INCREMENT PRIMARY KEY,   -- 项目ID
                           OrderID INT,                             -- 订单ID
                           ProductID INT,                           -- 产品ID
                           Quantity INT,                            -- 数量
                           Price DECIMAL(10, 2),                    -- 单价
                           FOREIGN KEY (OrderID) REFERENCES `Order`(OrderID),
                           FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- 评价表
CREATE TABLE Review (
                        ReviewID INT AUTO_INCREMENT PRIMARY KEY,    -- 评价ID
                        ProductID INT,                              -- 产品ID
                        UserID INT,                                 -- 用户ID
                        Rating INT,                                 -- 评价星级
                        Comment TEXT,                               -- 评价内容
                        ReviewDate DATE,                            -- 评价日期
                        FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
                        FOREIGN KEY (UserID) REFERENCES UserAccount(UserID)
);
