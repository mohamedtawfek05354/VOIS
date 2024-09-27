import pandas as pd

# Load the CSV file into a DataFrame
df = pd.read_csv('E:\_VOIS\Employees.csv')

# 1- Remove any duplicates in the table
df.drop_duplicates(inplace=True) # inplace is to ensure that the changes are in dataframe so there's no one new
print("1. Remove any duplicates")

#2- Remove any decimal places in the Age column

df['Age'] = df['Age'].astype(int) #convert the column to int so the decimal will remove 
print("2. Remove decimal places in the Age column")

# 3- Convert the USD salary to EGP
Rate = 48.37
df['Salary(EGP)'] = df['Salary(USD)'] * Rate

# 4- Print in the console some stats like:

# 	- Average age
average_age = df['Age'].mean()
print(f"Average Age: {average_age:.4f}")

# 	- Median Salaries
median_salary_egp = df['Salary(EGP)'].median()
print(f"Median Salary (EGP): {median_salary_egp:.2f}")

# 	- Ration between males and female employees
gender_cell_counts = df['Gender'].value_counts()
print(f"gender_cell_counts :{gender_cell_counts}")
if 'M' in gender_cell_counts and 'F' in gender_cell_counts:
    ratio_male_female = gender_cell_counts['M'] / gender_cell_counts['F']
    print(f"Male to Female Ratio: {ratio_male_female:.2f}")
else:
    print("Insufficient data to calculate Male to Female ratio.")

# 5- After performing the previous steps, write the data in a new CSV file
df.to_csv('E:\_VOIS\Task3.csv', index=False)

print("Task 3 complate and data is uploaded to 'Task3.csv'.")
