# Selenium practice

A Java Maven project using Selenium WebDriver and TestNG.

## Prerequisites

- JDK 17 or newer
- Maven 3.9 or newer
- Google Chrome

Selenium Manager automatically resolves the matching ChromeDriver when the test starts.

## Run the test suite

```powershell
mvn test
```

The starter test opens `https://www.example.com/` in headless Chrome and verifies the page title.
