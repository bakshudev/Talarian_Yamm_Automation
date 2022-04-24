# Talarian_Yamm_Automation Project
Automation project for YAMM Product from the Talarian company

I have added the users kengenouel, arturbektalarian and claudiu-talarian as collaborators to this repository.

## Strategy followed for the tech test
1. Carefully go through 1) tech test details and different requirements, 2) YAMM features & functionality, 3) Install YAMM and play around with it for some time to understand the tech test requirements
2. Setup prerequisites as mentioned in the tech test document
3. Now, I have prepared the strategy to be implemented for the tech test. From here, all the below steps and processes followed can be found in GitHub repository & GitHub commits.
4. Created GitHub repository Talarian_Yamm_Automation and cloned that in Eclipse [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/a74d8d8ce12287ea8f8b680ec86f5907d3fb799e)
5. Now, created  1) Maven project & folder structure, 2) pom.xml & dependencies, 3) raw script generated Yamm_Email_Sending_Tests.java using Playwright [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/af612af19ace893ea162119e78490b64f916299d)
6. Perfecting the test script with 1) using chrome browser & proper browsercontext, 2) defining best locator strategies and web elements where possible, 3) adding required wait logic for the test script and for different elements. This was the most challenging part. [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/f7c8162c469b99863ddf077decad2c71957131c2)
7. Refactoring the test script by 1) introducing TestNG framework, 2) adding multiple test methods, 3) adding reusable methods, 4) making ready for automation framework [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/259d25a01ea234859bd06112dd773a6aba1f6d0a)
8. Adding TestNG test-output folder to git ignore file [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/a28ccb2fe01e0b208db54f685464a53b414fab77)
9. Adding Yamm_Gmail_Receiving_Tests.java with few test methods to verify Gmail Emails [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/0e69b94b085498b1dc3e4b5d78887d8d6f5c7239)
10. Adding TestBase.java to hold all shared code from all test classes and refactoring test classes accordingly [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/c86a475dda90ec3b6380cb30f92b368423782bcc)
11. Creating Automation Framework structure with required packages and dummy files for demonstration [GitHub Commit Link](https://github.com/bakshudev/Talarian_Yamm_Automation/commit/6ff70a68c81352b61fb4c64375ea3df93ae6903f)

I have created the automation project & overall folder structure for web testing and API testing. Please find the [Automation Framework Folder Structure Here](https://drive.google.com/file/d/1_8aB3qxNOQaVe0JQFXXcggvXUKGPaxXE/view).

## Running a Test Class or Test Suite

I have used the TestNG framework to implement the test classes and test suites. The IDE used is Eclipse IDE for Java Developers.

We can easily run an individual test class or test suite. We just need to open the required test class or test suite and run it from the toolbar => run icon or right click context menu => run as option. Please refer to the below screenshots for different run options.

* [How to run a TestNG Test Class?](https://drive.google.com/file/d/1-F1u2GpEO9K_RX4niPQJGKCr3o1Cc0kN/view)
* [How to run a TestNG Test Suite?](https://drive.google.com/file/d/1XSPjIEyNz-2YY_DGukDK2sGawUNlU76M/view)


## Test Class - Yamm_Email_Sending_Tests

This test class has below 3 tests. The test names are clear and self explanatory. Please find the required details for them below.

* test_Yamm_Email_Sending_To_Multiple_Receipients_Sent_Count_Message(): This test verifies the email sent count message for multiple receipts from YAMM App.
* test_Yamm_Email_Sending_To_Single_Receipient_Sent_Count_Message(): This test verifies the email sent count message for single receipt from YAMM App.
* test_Yamm_Email_Sending_To_No_Receipient_Alert_Message(): This test verifies YAMM alert message when there are no receipts to send emails in the Google sheet. 

All these 3 tests are working & passing in my system. Please find the [test class running screenshot here](https://drive.google.com/file/d/1dxxVI6PxGHInaR_Aew99nG83QJxEo5XJ/view).

## Test Class - Yamm_Gmail_Receiving_Tests

This test class has below 3 tests. The test names are clear and self explanatory. Please find the required details for them below.

* test_Yamm_Gmail_Receiving_Single_Receipient_Email_Header(): This test opens the Gmail and searches for the email from the test test_Yamm_Email_Sending_To_Single_Receipient_Sent_Count_Message(). Then, it opens it and verifies the email header. 
* test_Yamm_Gmail_Receiving_Multiple_Receipients_Email_Count(): This test opens the Gmail and searches for the emails from the test_Yamm_Email_Sending_To_Multiple_Receipients_Sent_Count_Message(). Then, it verifies the email count based on the number of search entries.
* test_Yamm_Gmail_Receiving_Single_Receipient_Email_Count(): This test opens the Gmail and searches for the emails from  test_Yamm_Email_Sending_To_Single_Receipient_Sent_Count_Message(). Then, it verifies the email count based on the number of search entries.

All the 3 tests are running fine on my system and there are no run time exceptions. The 2 tests based on the email count are failing due to incorrect total email count I am getting from Gmail search results. I need to find the best locator to get the correct total email count. This needs further investigation. Please find the [test class running screenshot here](https://drive.google.com/file/d/1YvUefYzoDPn3aqc5lS4uil8mWVfm0wmL/view).

## Important Notes From Bakshu

* Thank you for your consideration and for giving me this tech test opportunity. Indeed, it is a very challenging one and I really enjoyed it. I have also worked on your YAMM product and understood a few of its features. I really love the product.
* The most challenging parts of the tech test are - 1) finding the best locator strategies, 2) dealing with wait logic for different elements, 3) dealing with YAMM frame
* I tried to perfect/refactor the code as much as possible to make it easy to understand. 
* I tried to make the code production ready as much as possible. As always, one can do further enhancements, refactoring or exception handling. But, we need to give more time for that.
* I have created and used the Google account bakshu.test@gmail.com for this tech test. The account password is YammTest#1.
* I couldn't find a way to read Google Sheets => YAMM Merge Status column cells using Playwright. It seems there is no way and we need to use Google Sheets API. So, I haven't written tests for validating Merge Status column cells. This needs further investigation. If you insist, I can proceed further with these tests.
* Now, I am going to work on the questions mentioned in the tech test document. I will send you the answers very soon.
* As I am working on this test/interview assessment after the office hours, it is taking time. Please understand that.

## Further Implementation

Please let me know if any further tests or implementation you want me to do in this automation project. I will be happy to do them.
