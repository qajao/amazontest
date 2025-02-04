# Amazon QA Engineer test

v1.0 January, 22 2024.

## Context

Hi, this is my test for Amazon I implemented It follows a Page Object Model (POM) structure using PageFactory. I used TestNg and Hamcrest for validation.

## Requirements

- Java 23
- Maven

## What we expect?

I implemented the required functions using POM, validating the best elements, using POM, data-driven tests, and Hamcrest for assertions. 

## How to run

``mvn clean test -DsuiteXmlFile=testng.xml `` to run Firex and Chrome or 
``mvn clean test`` by firefox default
``mvn clean test -Dbrowser=firefox``
``mvn clean test -Dbrowser=chrome``

