# Swapcard QA Engineer

v1.0 January, 05 2024.

## Context

Welcome! If you reach this project you are interested in our **QA Engineer** position.

Here we have an initial test case, badly written, waiting for your skills to improve code and stability.

We expect, at end, you will have good arguments to endorse your code.

## Requirements

- Java 17+
- Maven

## What we expect?

This test will help us to understand how you could go with our daily tasks.

Clone this repo and change it.

The tests run against Amazon.com.

### Objective

1. Refactory the initial test code.
2. Add a new test case with the following steps:

- Look for either "Jorge Amado", "Nick Page"
- Filter products found by Books
- Open a book page
- Confirm at least 70% of people liked the book with 5 stars

### Key points

- Use TestNG testing framework (already included in the POM).
- Open the browser as incognito.
- Find better locators for WebElements, when applicable.
- Use PageObject Model pattern.
- It needs to run on both Firefox and Chrome (by default).

## Tips

- Create new packages and folders as you wish.
- You may import anything you would like it could help, as Hamcrest, Lombok, else.
- Rewrite with best practices. _We would like to see what you know!_
- Comment your code.

### Plus (not required, though)

- Usage of PageFactory
- Data-Driven Testing

## Known issues:

- Amazon blocks robots and you may need to enter code manually while testing or even retry.
- If you run too much you may consume all available books for a while.

## How to run

``mvn clean test`` or ``mvn clean test -Dbrowser=firefox``

## How to share the solution

Please create a GitHub repository, or in any other public repository, and share the path with us.

_We can do it!_

_Good luck!!_
