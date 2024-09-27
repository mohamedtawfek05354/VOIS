# Automated Testing and Data Analysis Project
--------------------------------------------
This project consists of two automated tasks using Selenium/Java and one data manipulation task using Python. The project demonstrates test automation best practices, the use of design patterns, and data-driven testing in Selenium for the first two tasks and they will work **headless**. the data driven in the first two tasks with **JSON** and I am using **Config.properties** as well for storing and inserting data. For the third task, Python is used to clean and analyze a CSV file.

## Table of Contents
- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Task Descriptions](#task-descriptions)
  - [Task 1: Amazon Automation](#task-1-amazon-automation)
  - [Task 2: KSRTC Bus Booking Automation](#task-2-ksrtc-bus-booking-automation)
  - [Task 3: CSV Data Analysis with Python](#task-3-csv-data-analysis-with-python)
- [Test Cases](#test-cases)
  - [Task 1: Amazon Automation Test Cases](#task-1-amazon-automation-test-cases)
  - [Task 2: KSRTC Bus Booking Test Cases](#task-2-ksrtc-bus-booking-test-cases)
- [Running Tests](#running-tests)
- [Design Patterns and Best Practices](#design-patterns-and-best-practices)
- [Contact](#contact)

## Project Overview
This project automates two tasks using Selenium and Java, focusing on browser-based test scenarios, including navigating through Amazon and KSRTC bus booking systems. The third task is a data analysis and manipulation task using Python.

All Selenium-based test cases are written to run at a browser resolution of 1024x768 pixels and follow best practice coding standards for test automation and maintainability. The Python task focuses on cleaning and analyzing data from a CSV file.

## Prerequisites
Ensure you have the following installed on your machine:

- **Java JDK (11 or higher)**: Required to run the automation scripts. [Download Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven**: For managing project dependencies and building the Java project. [Install Maven](https://maven.apache.org/install.html)
- **Selenium WebDriver**: Used for browser automation. The project defaults to Google Chrome. [Install WebDriver](https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/)
- **TestNG**: Testing framework integrated with the project. It should be installed with Maven dependencies. [Learn more about TestNG](https://testng.org/doc/documentation-main.html)
- **Python (3.x)**: For Task 3. Ensure you have Python installed to run the data manipulation script. [Download Python](https://www.python.org/downloads/)
- **Pandas**: Python library for data manipulation. You can install it by running `pip install pandas`.

## Installation
### Clone the Repository
First, clone the repository to your local machine using:
```bash
git clone https://github.com/mohamedtawfek05354/VOIS.git
cd VOIS

###Install Dependencies
For the Java part (Tasks 1 & 2), install the project dependencies using Maven:
-mvn clean install
For the Python part (Task 3), install the required libraries:
-pip install pandas

**Task Descriptions**
Task 1: Amazon Automation
Automate two scenarios on Amazon's website:

Scenario 1:

Open Amazon.
Search for "car accessories."
Select the first item and add it to the cart.
Verify the item has been added to the cart successfully.
Scenario 2:

Navigate to "Today's Deals" on Amazon.
Filter by "Headphones" and "Grocery" on the left side.
Choose the discount filter "10% off or more."
Go to the fourth page and select any item to add to the cart.
Task 2: KSRTC Bus Booking Automation
Automate the bus booking process on the KSRTC website:

Open KSRTC.
Select "CHIKKAMAGALURU" to "BENGALURU" from popular routes.
Choose the arrival date.
Search for buses.
Select a seat and choose boarding and dropping points.
Fill in the "Customer" and "Passenger" details using the provided phone number 6789125987.
Navigate to the payment page (no need to submit payment).
Task 3: CSV Data Analysis with Python
Load the data from the provided CSV file and perform the following operations:

Remove duplicates.
Remove decimal places from the "Age" column.
Convert "Salary" from USD to EGP.
Print in the console:
Average age
Median salary
The ratio of male to female employees.
Save the cleaned data into a new CSV file.
Test Cases
Task 1: Amazon Automation Test Cases
Search for an item and add it to the cart:

Verify that Amazon's homepage loads correctly.
Search for "car accessories" and check the search results.
Add the first item to the cart.
Assert that the cart contains the selected item.
Filter deals and add items to the cart:

Open the "Today's Deals" section.
Filter by "Headphones" and "Grocery."
Apply the "10% off or more" filter.
Navigate to the fourth page, select an item, and add it to the cart.
Verify that the item has been added successfully.
Task 2: KSRTC Bus Booking Test Cases
Select a route and search for buses:

Verify that the KSRTC homepage loads.
Select the route "CHIKKAMAGALURU" to "BENGALURU."
Select the arrival date.
Search for available buses.
Book a seat and fill in the details:

Select an available bus and seat.
Choose boarding and dropping points.
Fill in customer details.
Navigate to the payment page without submitting the payment.

#Running Tests
Task 1 & Task 2 (Selenium/Java)
You can run the tests using Maven:
mvn clean test
Run the Python script for data manipulation:
python Task3.py
Design Patterns and Best Practices

#Best Practices
The project follows several industry-standard best practices for test automation and incorporates key design patterns and frameworks to ensure maintainability, scalability, and efficiency. Below are the key practices applied:

Page Object Model (POM):

Each web page is modeled as a Java class, encapsulating the elements and behaviors associated with that page. This pattern promotes code reusability and better separation of concerns.
Data-Driven Testing:

Test scenarios are designed to be flexible and reusable with multiple data sets by implementing TestNG’s @DataProvider. This allows the test cases to be parameterized, reducing duplication and increasing test coverage.
JSON Data Source: In addition to the traditional approach of using Excel or CSV, this project also demonstrates data-driven testing using JSON as the input data source. JSON files are parsed to supply data to test cases, enabling flexible and structured test case design.
Logging with Log4j:

For effective logging, Log4j is implemented. It provides robust logging capabilities, ensuring that each test execution is thoroughly documented with relevant info, debug, warning, and error logs.
This helps track down issues in test execution, understand the test flow, and generate clear logs for debugging.
Example of how Log4j is configured and used in the project:
Allure Reports:
Allure is used for generating detailed, visually appealing reports. It captures important test execution details, including step-by-step results, screenshots on failure, and a complete overview of passed, failed, and skipped tests.
It integrates seamlessly with TestNG and provides useful debugging and test-reporting insights.
Example of how to use Allure:


Maintainability and Design Patterns:

The code follows SOLID principles to ensure that it is maintainable, scalable, and easy to extend.
The Single Responsibility Principle ensures that each class or method has a well-defined purpose, enhancing the maintainability and readability of the codebase.
Factory Pattern is applied to manage WebDriver instances, allowing for flexible browser configurations and parallel execution.
Reusable Methods:

Frequently used actions like opening a browser, clicking buttons, and form-filling are encapsulated into reusable methods to avoid duplication and improve efficiency.
Methods that interact with web elements are structured to handle exceptions and wait for elements dynamically, making the tests more stable and robust.
By applying these practices, the project is designed to be highly maintainable, easy to extend, and robust, ensuring that test scripts are adaptable to changing requirements or applications.


Contact
For any inquiries or issues related to this project, please contact:

GitHub:mohamedtawfeek05354@github.com
Email: mohamedtawfeek05354@gmail.com
