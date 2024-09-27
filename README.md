Automated Testing and Data Analysis Project
This project consists of two automated tasks using Selenium/Java and one data manipulation task using Python. The project demonstrates test automation best practices, the use of design patterns, and data-driven testing in Selenium for the first two tasks. For the third task, Python is used to clean and analyze a CSV file.

Table of Contents
Project Overview
Prerequisites
Installation
Task Descriptions
Task 1: Amazon Automation
Task 2: KSRTC Bus Booking Automation
Task 3: CSV Data Analysis with Python
Test Cases
Running Tests
Design Patterns and Best Practices
Contact
Project Overview
This project automates two tasks using Selenium and Java, focusing on browser-based test scenarios, including navigating through Amazon and KSRTC bus booking systems. The third task is a data analysis and manipulation task using Python.

All Selenium-based test cases are written in a way to run at a browser resolution of 1024x768 pixels and follow best practice coding standards for test automation and maintainability. The Python task focuses on cleaning and analyzing data from a CSV file.

Prerequisites
Ensure you have the following installed on your machine:

Java JDK (11 or higher): Required to run the automation scripts. Download Java
Maven: For managing project dependencies and building the Java project. Install Maven
Selenium WebDriver: Used for browser automation. The project defaults to Google Chrome. Install WebDriver
TestNG: Testing framework integrated with the project. It should be installed with Maven dependencies. Learn more about TestNG
Python (3.x): For Task 3. Ensure you have Python installed to run the data manipulation script. Download Python
Pandas: Python library for data manipulation. You can install it by running pip install pandas.
Installation
Clone the Repository
First, clone the repository to your local machine using:

bash
Copy code
git clone https://github.com/your-username/automation-project.git
cd automation-project
Install Dependencies
For the Java part (Tasks 1 & 2), install the project dependencies using Maven:

bash
Copy code
mvn clean install
For the Python part (Task 3), install the required libraries:

bash
Copy code
pip install -r requirements.txt
Task Descriptions
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
Ratio of male to female employees.
Save the cleaned data into a new CSV file.
Test Cases
Task 1: Amazon Automation Test Cases
Search for an item and add it to the cart:

Verify that Amazon's homepage loads correctly.
Search for "car accessories" and check search results.
Add the first item to the cart.
Assert that the cart contains the selected item.
Filter deals and add item to the cart:

Open the "Today's Deals" section.
Filter by "Headphones" and "Grocery."
Apply the "10% off or more" filter.
Navigate to the fourth page, select an item, and add it to the cart.
Verify that the item has been added successfully.
Task 2: KSRTC Bus Booking Test Cases
Select route and search for buses:

Verify that the KSRTC homepage loads.
Select the route "CHIKKAMAGALURU" to "BENGALURU."
Select the arrival date.
Search for available buses.
Book a seat and fill in details:

Select an available bus and seat.
Choose boarding and dropping points.
Fill in customer details.
Navigate to the payment page without submitting payment.
Running Tests
Task 1 & Task 2 (Selenium/Java)
You can run the tests using Maven:

bash
Copy code
mvn test
Task 3 (Python)
Run the Python script for data manipulation:

bash
Copy code
python task3_data_analysis.py
Design Patterns and Best Practices
The project follows best practices for test automation and design patterns such as:

Page Object Model (POM): Each web page is represented as a Java class, encapsulating the elements and behaviors for that page.
Data-Driven Testing: The project leverages data-driven test cases using TestNG’s @DataProvider, enabling easy testing with multiple data sets.
Maintainability: The code follows SOLID principles to ensure scalability and maintainability.
Reusable Methods: Commonly used actions such as opening a page, clicking buttons, and filling forms are encapsulated in reusable methods.
Contact
For any inquiries or issues related to this project, please contact:

GitHub:mohamedtawfeek05354@github.com
Email: mohamedtawfeek05354@gmail.com
