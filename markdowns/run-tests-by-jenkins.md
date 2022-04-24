## Run tests by Jenkins

### 1. Create a Job in Jenkins

1. This Project can be integrated with Jenkins easily
2. Login to Jenkins
3. Create FreeStyle Project

![](./attachments/jenkins-freestyle.png)

4. Create Job Parameters to set all Properties, based on Test Need

![](./attachments/jenkins-parameter-create.png)

5. Repeat the same step for all Parameters
6. Create Parameter with name as `branchName`, and use the same in Git(Branches to build)
7. This will allow to use any branch to pickup and execute Test

![](./attachments/jenkins-branch-name.png)

8. Configure goal and Options

![](./attachments/jenkins-goal.png)


### 2. Create a build with your parameters

![](./attachments/jenkins-build-with-param.png)
![](./attachments/jenkins-build-param.png)

 * If Selenium Grid execution is not needed, Please set seleniumGridUrl as empty.


### 3. Check Tests Execution Report(Extent Report) Link

1. Extent Report for Each Build can be seen, by opening the build

![](./attachments/jenkins-extent-report.png)

2. All Required Details can be viewed from Extent Report

![](./attachments/extent-report-dashboard.png)
![](./attachments/extent-report-testDetails.png)

3. On Failure Screenshots and Logs will be displayed in Report
