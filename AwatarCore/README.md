# AwatarCore
This application is a submodule of AWATAR-Framework which is used as API for data exchange. This Framework belongs to the master thesis projekt of '' from Alexander Hoedl at FH Campus02.
This application is a spring boot application which represents the backend of the AWATAR-Framework. It provides an REST Interface to create test cases (see Awatar-Creator) or execute existing test cases (see Awatar-Runner). As test automation runtime cucumber (see cucumber.io) is used.

## Project setup

### Requirements
- Java JDK 8 (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- Spring Boot Requirements (https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/html/getting-started-system-requirements.html)

After installation of all required applications you can import the Project in IDE (e.g. IntelliJ) and start the application

### Developer Setup
- Clone this repository to your local machine
- Switch to directory
- Adapt settings in application.properties in src/main/resources

### Inport in IDE IntelliJ (Testet with V. 2019.1)
- Open IntelligJ
- File -> Open -> Select AwatarCore directory -> OK (if requested open in new window)

Run Project in IDE by starting ```Start Spring Boot REST Application``

## REST Interface
A working POSTMAN-Collection is stored under: ```\AwatarCore\resources\AWATAR.postman_collection.json```

- /awatar/creator
    - /stepDefinitions: Returns all available step definitions to execute. Returns available step defintions in JSON-Format:
```
{
    "stepDefinition": {
        "WHEN": [
            {
                "methodName": "user_with_username_and_password_log_into",
                "stepDefinition": "User with username {string} and password {string} log into",
                "parameters": [
                    "string",
                    "string"
                ]
            },
            {
                "methodName": "open_website_with_url",
                "stepDefinition": "Open website with URL {string} ",
                "parameters": [
                    "string"
                ]
            }
        ],
        "GIVEN": [
            {
                "methodName": "login_page_with_url_is_shown",
                "stepDefinition": "Login page with url {string} is shown",
                "parameters": [
                    "string"
                ]
            }
        ],
        "THEN": [
            {
                "methodName": "dashboard_is_shown",
                "stepDefinition": "Dashboard is shown",
                "parameters": []
            }
        ]
    }
}
```
- /awatar/runner
    - /execute: Execute given Test-Feature in JSON-Format
```
{
    "feature": "Login successful?",
    "scenario": "Log into Dashboard",
    "given": ["Login page with url \"https://www.google.at\" is shown","today is Sunday"],
    "when": ["User with username \"admin\" and password \"pa44w0rd\" log into"],
    "then":["Dashboard is shown"]
}
```
