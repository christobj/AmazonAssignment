## Amazon Assignment

## Libraries Used :

1. Selenium - Web Automation
2. TestNG - Unit Testing Library
3. Extent Reports - Reporting Library to generate beautiful html reports
4. WebDriverManager - Executables management
5. Lombok - To reduce boilerplate codes
6. Slf4j - Logging Library to generate Logs
7. Open CSV - To read Test Data from CSV File

- Note : All the aforementioned libraries are open-source and does not include any license.

## Capabilities :

1. This framework has the capability to run the tests in local and remote selenium grid.
2. It can also be extended to run on clouds like browser stack.
3. Web Tests can be run on chrome or edge browsers.
4. Core Configuration can be changed from config.properties inside CoreProject/src/test/resources
5. All Common utility methods available in CoreProject
6. Can be used for Multiple Projects (Example: Amazon Project)
7. Amazon Project Configurations can be changed or added from amazon.properties inside AmazonProject/src/test/resources
8. If you need to check the same Test for Multiple Data, add you Test Data in CSV file inside AmazonAssignment\AmazonProject\src\test\java\com\assignment\testData

## Running Tests :

# Local Test development/debugging in IDE

[Any Supported IDE](./markdowns/run-tests-by-intellij.md)

# Run Test and get Report link by Jenkins build

[Jenkins build](./markdowns/run-tests-by-jenkins.md)

## Adding More Tests: 

1. Tests should be added as per the convention followed. New testng tests should be created inside the AmazonProject/src/test/java folder.
2. All the tests should extend CoreTest.java
3. Create Page Files for each New Page
4. All the Page File should extend CorePage.java
5. All Helper Methods needed for Execution should be written in CorePage.java

### Need to use same Framework for New Project ?

1. Create new Module under AmazonAssignment Project
2. Create a seperate Property File and Listener for a Project
3. Follow the same rules as above 
