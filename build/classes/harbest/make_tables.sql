CREATE TABLE Category(
	C_ID INT NOT NULL,
    C_Name VARCHAR(20)
);

CREATE TABLE Current_Order(
	CO_ID INT NOT NULL,
    CO_Product_Name VARCHAR(70),
    CO_Product_Cost INT,
    CO_Product_Quantity SMALLINT,
    CO_Product_Sale VARCHAR(3),
    CO_Category INT,
    CONSTRAINT PK_Current_Order PRIMARY KEY (CO_ID),
    CONSTRAINT FK_Current_Order_Category FOREIGN KEY (CO_Category) REFERENCES Category(C_ID)
);

CREATE TABLE Products(
	P_ID INT NOT NULL,
    P_Name VARCHAR(70),
    P_Cost INT,
    P_Sale VARCHAR(3),
    P_Recommendation BOOL,
    P_Category INT,
    P_Image VARCHAR(255),
    CONSTRAINT PK_Products PRIMARY KEY (P_ID),
    CONSTRAINT FK_Products_Category FOREIGN KEY (P_Category) REFERENCES Category(C_ID)
);

INSERT INTO Category VALUES
(1, "Seeds"),
(2, "Soils"),
(3, "Fertilizers"),
(4, "Pesticides"),
(5, "Tools");

ALTER TABLE Category
ADD CONSTRAINT PK_Category PRIMARY KEY (C_ID);

INSERT INTO Products VALUES
-- (1, "Corn Seeds (Zea Mays) 50pcs/bag", 200, "0%", TRUE, 1, "/assets/images/corn-seeds.jpg"),
-- (2, "Soybeans (Glycine Max) 20pcs/bag", 300, "10%", FALSE, 1, "/assets/images/soybean-seeds.jpg")
(3, "8KG Loam Soil", 150, "0%", FALSE, 2, "assets/images/loam-soil.png"),
(4, "1KG Clay Soil", 60, "0%", FALSE, 2, "assets/images/clay-soil.png"),
(5, "1KG Urea (46-0-0)", 50, "0%", FALSE, 3, "assets/images/urea.png"),
(6, "1KG Duofos (0-20-0)", 60, "0%", FALSE, 3, "assets/images/duofos.png"),
(7, "Garden Trowel 1pc", 18, "0%", TRUE, 5, "assets/images/trowel.png"),
(8, "Garden Hoe 1pc", 100, "0%", FALSE, 5, "assets/images/hoe.png"),
(9, "1L 480SL Non-Selective Roundup Glyphosate Herbicide", 750, "0%", FALSE, 4, "assets/images/roundup-glyphosate-herbicide.png"),
(10, "1L David Grays Chlorpyrifos 40EC", 1500, "0%", FALSE, 4, "assets/images/david-grays-chlorpyrifos.png"),
(11, "2KG Compost Soil", 150, "20%", FALSE, 2, "assets/images/compost-soil.jpg"),
(12, "1.5KG Peat Soil", 300, "0%", FALSE, 2, "assets/images/peat-soil.jpg"),
(13, "Pumpkin Seeds", 170, "0%", FALSE, 1, "assets/images/pumpkin-seed.jpg"),
(14, "Flax Seeds", 140, "0%", FALSE, 1, "assets/images/flax-seed.jpg"),
(15, "Hand Spade", 100, "0%", FALSE, 5, "assets/images/spade-tool.jpg"),
(16, "Hand Hoe", 100, "0%", FALSE, 5, "assets/images/hoe-tool.jpg"),
(17, "SEVIN Insecticide", 120, "10%", FALSE, 4, "assets/images/insecticide-pesticide.jpg"),
(18, "Plant Fuingicide", 50, "0%", FALSE, 4, "assets/images/fungicide-pesticide.jpg"),
(19, "Soft Rock Phosphate", 30, "0%", FALSE, 3, "assets/images/phosphate-fertilizer.png"),
(20, "Garden Trust Fertilizer", 31, "0%", FALSE, 3, "assets/images/organic-fertilizer.jpg"),
(21, "Yara Mila Unik 16 Fertilizer (NPK 16-16-16)", 79, "0%", FALSE, 3, "assets/images/yara-mila.jpg"),
(22, "Chicken Manure: Greenland Bio Organic Fertilizer", 24, "0%", FALSE, 3, "/assets/images/chicken-manure.jpg"),
(23, "Vemicast - Worm Manure", 179, "0%", FALSE, 3, "assets/images/vermicast.jpg"),
(24, "Gloves", 35, "0%", FALSE, 5, "assets/images/gloves.jpg"),
(25, "Double Side Hoe", 69, "0%", FALSE, 5, "assets/images/double-side-hoe.jpg"),
(26, "Sunflower Seeds", 250, "0%", FALSE, 1, "assets/images/sunflower.jpg"),
(27, "Sesame Seeds", 250, "0%", FALSE, 1, "assets/images/sesame.jpg"),
(28, "Chia Seeds", 250, "0%", FALSE, 1, "assets/images/chia.jpg"),
(29, "Bell Pepper Seeds 75pcs/bag", 60, "0%", FALSE, 1, "assets/images/bell-pepper.jpg"),
(30, "Cherry Tomato Seeds 25pcs/bag", 60, "0%", FALSE, 1, "assets/images/cherry-tomato.jpg"),
(31, "1KG Grow More (20-5-30)", 270, "0%", FALSE, 3, "assets/images/growmore.png"),
(32, "1KG Masterblend Premium Fertilizer (Tomato Formula) (4-18-38)", 350, "0%", FALSE, 3, "assets/images/masterblend.png"),
(33, "Neem Plant Spray", 100, "0%", FALSE, 4, "assets/images/neem.png"),
(34, "Pulsar 2.5EC", 800, "0%", FALSE, 4, "assets/images/pulsar.png"),
(35, "Dibbler", 150, "0%", FALSE, 5, "assets/images/dibbler.png"),
(36, "Pruning Shears", 200, "0%" , FALSE, 5, "assets/images/pruning-shears.jpg");

SELECT * FROM Products

-- FETCHING TIME

