# Comp680NutritionApp
Goal:
To create a website that assists people with their daily Calorie and other Nutrients Count and even provides a weekly/monthly report that helps fitness enthusiasts track their Calorie and other Nutrients intake, on demand.
Key part being, the application helps you estimate your Nutrient count with the available food you have at home, and reminds you of the remaining Nutrient count that should be consumed for the day, each time you enter your meal.

Objectives:
User will setup his/ her profile : Enter height, weight and age. Our website will calculate the expected Nutrients intake the user should follow to stay fit.
User will keep entering the food he/she has and the website will maintain the Nutrients count based on food eaten by the user and give a notification when user is about to reach his/her daily Nutrients count limit.
The website will provide weekly/monthly reports to track fitness and calorie count.
Reports can be viewed as well as downloaded

User Story 1: Authentication and Profile setup to come up with various Nutrients count
Tasks
Login Page
Login page to support authentication through sites like facebook, gmail, etc.
Create table to save user data.
Save User details to the database
Profile form including basic information like Name, DOB, Address, Phone number, etc
Page to take age, weight and height as input.
Page to edit age, weight and height to recalculate

User Story 2: Setup Datasets for the Nutrients count and Datasets to provide workout suggestions
Tasks
Gather data sets for Nutrients calculation
Create tables to store data for Nutrients count
Define relationship between tables (if required)
Convert the raw data from the data sets to table format by inserting data into tables

User Story 3: Portal to take food had as input and calculate the Nutrients count.
Tasks
Page to calculate the Nutrients count
Service to fetch data from table and calculate the intake Nutrients count.
Set Up a table to maintain the Nutrients count record.
Store the data in a table
Update the data in table as Nutrients count increases.
Set the Nutrients count to zero for new day
Maintain data per user
Delete the data as and when user deletes its profile from the system

User Story 4:Page to provide alert notifications when reaching Nutrients limit
Tasks
Design Pop up notification
Give notification when Nutrients exceed.
Check to compare Nutrients count to the daily limit 
Notification when Nutrients count exceeds.
Return back to website on OK

User Story 5:Page to Provide weekly/monthly reports to keep a track of Nutrients count
Tasks
Add menu option for weekly/monthly report
Add page to take dates as input 
Service to Fetch data between dates to be displayed
Page to display data
Display data in tabular form

User Story 6: Download functionality
Tasks
Download option to download reports
Service to export data in excel file format and allow user to download on Client side.
