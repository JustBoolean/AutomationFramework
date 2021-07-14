# AutomationFramework
Test Automation Framework for Google Books API

## HOW TO
Written in java with REST ASSURED library for API Testing as well as cucumber for BDD.

To run with only Cucumber JUnit tests run TestRunner.java.

For a detailed html report with cucumber-reporting (found in target/cucumber-reports) run using maven command "mvn verify test" in cmd prompt in directory.

Compatible with jenkins.


### Details
Pings google books api using Get method, we can search their volumes using a keyword and then recieve a list of books, 
we can then also learn more about a specific book given a volume_id from said list.
