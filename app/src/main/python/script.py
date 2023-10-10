import numpy as np

def perform_operation(operation, number1, number2):
    num1 = int(number1)
    num2 = int(number2)
    
    if operation == "Addition":
        result = num1 + num2
    elif operation == "Multiplication":
        result = num1 * num2
    elif operation == "Subtraction":
        result = num1 - num2
    elif operation == "Division":
        if num2 == 0:
            return "Cannot divide by zero"
        result = num1 / num2
    else:
        return "Invalid operation"
    
    return f"Result of {operation} is {result}"
