# the database was created by all group members

#DDL: create SEJ database
DROP DATABASE IF EXISTS SEJ;
CREATE DATABASE SEJ;
USE SEJ;

# create table 'addresses' 
CREATE TABLE addresses 
(
    address_id				INT				PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    address_street_number	VARCHAR(50)		NOT NULL,
    address_city     		VARCHAR(50)		NOT NULL,
    address_zip_code		INT				NOT NULL, 
    address_country			VARCHAR(50)		NOT NULL
);

# create table 'customers'payments
CREATE TABLE customers
(
	customer_id 		        INT				PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    customer_first_name			VARCHAR(45)     NOT NULL,
    customer_last_name			VARCHAR(45)     NOT NULL, 
    customer_gender				VARCHAR(45)     NOT NULL,
    customer_birthday			VARCHAR(45)		NOT NULL, 
    customer_nationality	    VARCHAR(45)     NOT NULL,
    passport_number				INT 			NOT NULL,
    passport_expiration_date	VARCHAR(45) 	NOT NULL,
    address_id					INT				NOT NULL,
    customer_email				VARCHAR(45)     NOT NULL,
    customer_phone_number		VARCHAR(45)     NOT NULL,
 
	CONSTRAINT customers_fk_addresses
		FOREIGN KEY (address_id)
		REFERENCES addresses (address_id)
);

# create table 'employees' 
CREATE TABLE employees
(
	employee_id		INT		PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    employee_first_name		VARCHAR(50)		NOT NULL,
    employee_last_name		VARCHAR(50)		NOT NULL,
    employee_title			VARCHAR(50)     NOT NULL,
    employee_username		VARCHAR(50)		NOT NULL	UNIQUE,
    employee_password		VARCHAR(50)		NOT NULL	UNIQUE
);

# create table 'planes'
CREATE TABLE planes
(
	plane_id				INT				PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    plane_type				VARCHAR(45)		NOT NULL,
    first_class_seats		INT,
    business_class_seats 	INT,
    economy_class_seats 	INT
);

# create table 'legs'
CREATE TABLE legs
(
	leg_id 				  INT				PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    departure_airport	  VARCHAR(60)		NOT NULL,
    arrival_airport		  VARCHAR(60)       NOT NULL,
    departure_date 		  VARCHAR(60)		NOT NULL,
    departure_time        VARCHAR(60) 		NOT NULL,
    arrival_date 		  VARCHAR(60) 		NOT NULL,    
    arrival_time		  VARCHAR(60) 		NOT NULL,
    first_seat_booked	  INT				DEFAULT 0,
    business_seat_booked  INT  				DEFAULT 0,
    coach_seat_booked	  INT 				DEFAULT 0,
    price_first_class	  INT				NOT NULL
);

# create table 'tickets'
CREATE TABLE tickets
(
	ticket_id           INT				PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    leg_id				INT				NOT NULL,
    customer_id			INT				NOT NULL,
    ticket_class		INT				NOT NULL,
    ticket_seat_number	INT 			NOT NULL,
    ticket_price		INT				NOT NULL,
    ticket_state		VARCHAR(45)		NOT NULL,
    CONSTRAINT tickets_fk_legs
		FOREIGN KEY (leg_id)
		REFERENCES legs (leg_id),
	CONSTRAINT tickets_fk_customers
		FOREIGN KEY (customer_id)
		REFERENCES customers (customer_id)
);

# create table 'schedule'
CREATE TABLE schedules (
  plane_id    		INT		 REFERENCES 	planes (plane_id) ON UPDATE CASCADE ON DELETE CASCADE,
  leg_id 			INT		 REFERENCES 	legs (leg_id) ON UPDATE CASCADE,
  CONSTRAINT schedules_pkey PRIMARY KEY (plane_id, leg_id)
);

# create table 'orders'
CREATE TABLE orders 
(
	order_id 		INT		PRIMARY KEY		NOT NULL	UNIQUE 	  AUTO_INCREMENT,
    employee_id		INT 	NOT NULL,
    customer_id		INT 	NOT NULL,
    CONSTRAINT orders_fk_employees
		FOREIGN KEY (employee_id)
		REFERENCES employees (employee_id),
	CONSTRAINT orders_fk_customers
		FOREIGN KEY (customer_id)
		REFERENCES customers (customer_id)
);

# create table 'schedule'
CREATE TABLE orders_tickets (
  order_id    		INT		 REFERENCES 	orders (order_id) ON UPDATE CASCADE ON DELETE CASCADE,
  ticket_id 		INT		 REFERENCES 	tickets (ticket_id) ON UPDATE CASCADE,
  CONSTRAINT orders_tickets_pkey PRIMARY KEY (order_id, ticket_id)
);

INSERT INTO addresses VALUES
	(DEFAULT, 'Gronjørdsvej 1', 'Copenhagen', '2300', 'Denmark'),
    (DEFAULT, 'Amagerbrogade 151', 'Copenhagen', '2300', 'Denmark'),
    (DEFAULT, 'Amagerfælledvej 135', 'Copenhagen', '2300', 'Denmark'),
    (DEFAULT, 'Frederiksborggade 11', 'Copenhagen', '1360', 'Denmark'),
    (DEFAULT, 'Falkoner Alle 75', 'Frederiksberg', '2000', 'Denmark');
    
    
INSERT INTO customers VALUES
	(DEFAULT, 'Martin', 'Lauren', 'Male', '08/10/1993', 'American', 45973175, '01/01/2020', 1, 'mar_lauren@yahoo.com', '71459637'),
    (DEFAULT, 'Victor', 'Jensen', 'Male', '25/05/1990', 'Danish', 97314608, '01/03/2019', 2, 'victor_jen90@gmail.com', '51521305'),
    (DEFAULT, 'Andreas', 'Berg', 'Male', '12/06/1985', 'Norwegian', 93479214, '01/05/2018', 3, 'berg.andreas@yahoo.com', '31614665'),
    (DEFAULT, 'Emily', 'Haugen', 'Female', '12/09/1987', 'Danish', 65491279, '15/10/2020', 4, 'emily_haughen@gmail.com', '13513279');
    
INSERT INTO employees VALUES
	(DEFAULT, 'John', 'Conan', 'admin', 'Johnny', 'conan1'),
    (DEFAULT, 'Andrea', 'Beam', 'admin', 'Andy', 'beam1'),
    (DEFAULT, 'Mach', 'Esch', 'customer_service', 'Mac', 'esch1'),
    (DEFAULT, 'Garret', 'Fendley', 'customer_service', 'Gary', 'fen1'),
    (DEFAULT, 'Ashlie', 'Harper', 'customer_service', 'Ashy', 'harper1');
    
INSERT INTO planes VALUES
	(DEFAULT, 'Airbus A330-200', 12, 42, 183),
    (DEFAULT, 'Boeing 777-200', 18, 49, 236),
    (DEFAULT, 'Boeing 777-300', 18, 42, 420),
    (DEFAULT, 'Airbus A340-300', 46, 28, 171),
    (DEFAULT, 'Airbus A330-300', 34, 35, 195);
    
INSERT INTO legs VALUES
	(DEFAULT, 'CPH Copenhagen Airport', 'OTP Bucharest Henri Coandă International Airport', '08/10/2016', '10:21', '08/10/2016', '12:33', 10, 25, 150, 1500),
    (DEFAULT, 'CPH Copenhagen Airport', 'BER Berlin Tegel Airport', '01/06/2016', '06:00', '01/06/2016', '07:15', 12, 40, 201, 1000),
    (DEFAULT, 'CPH Copenhagen Airport', 'VIE Vienna International Airport', '29/10/2016',  '17:05', '29/10/2016', '18:55',  6, 30, 200, 1200),
    (DEFAULT, 'CPH Copenhagen Airport', 'GOR Gorna Oryahovitsa Airport', '15/06/2016', '18:30', '13/08/2016', '21:00',  15, 28, 170, 1500),
    (DEFAULT, 'CPH Copenhagen Airport', 'BUD Budapest Airport', '30/07/2016', '12:12', '30/07/2016', '14:45', 13, 28, 195, 1300),
    (DEFAULT, 'CPH Copenhagen Airport', 'BUD Budapest Airport', '20/07/2016', '22:15', '20/07/2016', '01:55', 13, 28, 199, 1300),
    (DEFAULT, 'CPH Copenhagen Airport', 'BUD Budapest Airport', '30/07/2016', '15:30', '30/07/2016', '19:45', 10, 20, 200, 1300),
    (DEFAULT, 'CPH Copenhagen Airport', 'FRA Frankfurt Airport', '12/10/2016', '12:45', '12/10/2016', '13:50', 12, 26, 179, 1200),
    (DEFAULT, 'CPH Copenhagen Airport', 'MUC Munich Airport', '15/09/2016', '06:15', '15/09/2016', '08:20', 4, 24, 124, 1200),
    (DEFAULT, 'CPH Copenhagen Airport', 'MMA Montpellier–Méditerranée Airport', '10/10/2016', '18:50', '10/10/2016', '21:00', 8, 22, 118, 2000),
    (DEFAULT, 'CPH Copenhagen Airport', 'OTP Bucharest Henri Coandă International Airport', '18/12/2016', '09:55', '18/12/2016', '12:15', 8, 16, 155, 1500),
    (DEFAULT, 'CPH Copenhagen Airport', 'ORL Orly Airport', '24/12/2016', '12:25', '24/12/2016', '15:00', 12, 26, 100, 1600),
    (DEFAULT, 'CPH Copenhagen Airport', 'BRU Brussels Airport', '03/07/2016', '05:48', '03/07/2016', '07:15', 12, 26, 123, 1500),
    (DEFAULT, 'CPH Copenhagen Airport', 'BHX Birmingham International Airport', '19/08/2016', '17:35', '19/08/2016', '19:00', 16, 24, 180, 1600),
    (DEFAULT, 'CPH Copenhagen Airport', 'BRS Bristol Airport', '15/06/2016', '18:00', '15/06/2016', '19:35', 12, 18, 199, 1600),
    (DEFAULT, 'CPH Copenhagen Airport', 'EMA East Midlands Airport', '30/06/2016', '20:30', '30/06/2016', '22:00', 14, 28, 185, 1600),
    (DEFAULT, 'CPH Copenhagen Airport', 'LTN Luton Airport', '09/08/2016', '08:15', '09/08/2016', '09:55', 8, 16, 231, 1800),
    (DEFAULT, 'CPH Copenhagen Airport', 'MAN Manchester Airport', '10/10/2016', '10:35', '10/10/2016', '12:15', 12, 22, 178, 1500),
    (DEFAULT, 'ABZ Aberdeen Airport', 'BFS Belfast International Airport', '21/11/2016', '13:37', '21/11/2016', '15:00', 16, 26, 199, 1000);
    
INSERT INTO tickets VALUES
	(DEFAULT, 1, 1, 1, 199, 1500, 'booked'),
    (DEFAULT, 2, 2, 1, 97, 1300, 'booked'),
    (DEFAULT, 3, 3, 2, 11, 1300, 'booked'),
    (DEFAULT, 4, 3, 3, 13, 1000, 'confirmed'),
    (DEFAULT, 4, 4, 3, 14, 1105, 'confirmed');
 
# plane id, leg id
INSERT INTO schedules VALUES
	(1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (2, 6),
    (3, 7),
    (5, 8),
    (2, 9),
    (1, 10),
    (4, 11),
    (4, 12),
    (1, 13),
    (2, 14),
    (3, 15),
    (5, 16),
    (2, 17),
	(1, 18),
    (3, 19);

# order id, employee id, customer id
INSERT INTO orders VALUES
	(DEFAULT, 2, 1),
    (DEFAULT, 2, 2),
    (DEFAULT, 4, 3),
    (DEFAULT, 1, 4);

# order id, ticket id
INSERT INTO orders_tickets VALUES
	(1, 1),
	(2, 2),
    (3, 3),
    (3, 4),
    (4, 5);