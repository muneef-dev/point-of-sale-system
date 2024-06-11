# Point of Sale (POS) System

The Point of Sale (POS) System is a comprehensive application developed using Java, JavaFX, and MySQL, aimed at streamlining sales and inventory management within retail environments. It prioritizes user-friendly interfaces and efficient programming to effectively meet the needs of the retail industry.

## Table of Contents

- [Introduction](#introduction)
- [Functionality](#functionality)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Dependencies](#dependencies)
- [Authors](#authors)

## Introduction

The Point of Sale (POS) System is a standalone application developed to automate various sales and inventory management tasks within a retail environment. Its functionalities include product management, sales processing, inventory management, customer management, and reporting. The system aims to provide a user-friendly interface and efficient programming to meet the retail industry's needs effectively.

## Functionality

### User Authentication and Authorization

- Implement a secure login system for retail staff with role-based access control.
- Differentiate access levels for administrators, cashiers, and inventory managers.

### Product Management

- Allow the registration of new products with essential details such as name, SKU, price, and stock level.
- Provide the ability to update and maintain existing product information.
- Generate and manage unique product IDs.
- Search for existing products by ID or name.

### Sales Processing

- Implement a user-friendly interface for processing sales transactions.
- Enable staff to add products to a sales transaction, apply discounts, and process payments.
- Provide options for different payment methods including cash, credit card, and mobile payments.
- Print receipts for completed transactions.

### Inventory Management

- Implement alerts for low inventory levels to facilitate timely restocking.
- Enable administrators to update inventory status manually.
- Track inventory movements and adjustments.

### Customer Management

- Allow the registration of new customers with essential details such as name, contact information, and purchase history.
- Provide the ability to update and maintain existing customer information.
- Search for existing customers by ID or name.

### Reporting and Analytics

- Generate reports on sales, inventory levels, and customer purchases.
- Analyze sales data for performance monitoring and business insights.

### Security Measures

- Implement secure password policies and mechanisms for user authentication.

## Usage

To use this application:

1. Clone the repository to your local machine.
2. Import the project into your preferred Java IDE.
3. Set up a MySQL database using the provided SQL scripts.
4. Modify the database connection details in the configuration files.
5. Build and run the application.
6. Access the application through the provided GUI and explore its functionalities.

## Database Schema

The database schema consists of several entities including User, Product, Sale, Customer, Inventory, and Payment. Each entity has specific attributes and relationships defined within the schema.

## Dependencies

This project utilizes the following dependencies:

- MySQL Connector/J: JDBC driver for connecting Java applications to MySQL databases.
- JFoenix: JavaFX Material Design Library.
- ControlsFX: Additional JavaFX UI Controls.
- Lombok: Java library to reduce boilerplate code.
- JasperReports: Reporting tool for generating reports in Java applications.
- ZXing: Library for generating QR codes.

## Authors

This project was developed by [@Muneef-dev](https://github.com/muneef-dev).

