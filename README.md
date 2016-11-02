IMPterpreter is a fictive programming language interpreter that is able to understand expressions, variables, operators, values and statements.

It was developed in March 2016 as an assignment. Its purpose was to introduce the course called Programming Paradigms. 

### Example of concept
```java
x = 1;
i = 0;
while (i < 10) {
	x = x * 2;
	i = i + 1;
}
return x;
```

The program correctly identifies and solves the instructions, returning 1024. If a program does not contain a return, an error message will be displayed. Same
 will happen if a variable is not in scope (uninitialized).

As a rule of input, the instructions given are through a string which uses brackets for scope delimitation.

The string for the above example is:
**[; [= x 1] [; [= i 0] [; [while [< i 10] [; [= x [* x 2]] [= i [+ i 1]]]] [return x]]]]**

### Features
IMPterpreter supports the following:
* variables: a, xyz, myVariable
* expressions: 2+4, 10*20, 3>2, a==4 
* assignments: x=3
* if statement
  * if without else
  * if with else
* while loop
* return

### Usage
The program has to be written respecting the grammar mentioned above and saved in a String in main. Then call the evalProgram method with the string as argument.
Alternatively, you could look into PublicTests which is a class written using JUnit and either run it or modify it as you like in order to simulate scenarios.
