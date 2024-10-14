# Languages-and-Compliation-Coursework

## Table of Contents
1. [Description](#description)
2. [Running the Project](#running-the-project)
3. [Example Usage](#example-usage)

## Description
A university coursework to implement a recursive descent parser for the following grammar:
```bash
<start> ::= <start> “+” <term> | <term> | <start> “-” <term>
<term> ::= <term> “*” <factor> | <factor>
<factor> ::= “(” <start> “)” | <float>
```

The coursework consisted of 3 main tasks:
- Parsing strings and checking if they are part of the given grammar
- Providing meaningful error messages for strings that do not match the grammar
- Evaluating the arithmetic expression and displaying the result

## Running the Project
To run the project, compile all Java files in the ```src``` directory and run the ```Driver``` class:
```bash
javac src/*.java
java Driver
```

## Example Usage
Below are some examples of different inputs and their evaluations or error messages:
> [!CAUTION]
> Each character in the input string should be separated by a space except for floating-point numbers. For example, in ( 5 + 5.4 ), there should be spaces between the + and the two numbers, as well as between the brackets and the numbers.

#### Error Examples
- Example 1 <br><br>
![image](https://github.com/user-attachments/assets/cb70809b-dff6-4f7f-8b5c-d8feb8239302)
- Example 2 <br><br>
![image](https://github.com/user-attachments/assets/19f6c832-433e-4e65-9b8a-da9fcb85ee5a)

#### Evaluation Examples
- Example 1 <br><br>
![image](https://github.com/user-attachments/assets/2dad6d5a-3cfe-47e2-a7d1-2aa307c45465)
- Example 2 <br><br>
![image](https://github.com/user-attachments/assets/217a09a8-75ee-494d-a3bd-40b09ba1248e)
- Example 3 <br><br>
![image](https://github.com/user-attachments/assets/9dff4383-8f3c-41d0-b216-eb2402943c75)
