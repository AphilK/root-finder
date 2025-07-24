import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;
import java.text.DecimalFormat;

public class RootFinder {
    // Decimal format for 10 decimal places
    static DecimalFormat df = new DecimalFormat("0.0000000000");

    public static void main(String [] args) {
        // Scanner object for input
        Scanner scanner = new Scanner(System.in);

        //Variable declaration
        double limA, limB, tol, choice;
        int c = 0, option;
        String response;

        System.out.println("Initiating program...");

        do{
            System.out.print("Please type the lower limit (a): ");

            // Input validation for lower and upper limits
            try{    
                limA = scanner.nextDouble();
                System.out.print("Please type the upper limit (b): ");
                limB = scanner.nextDouble();

                if(limA >= limB) {
                    System.out.println("The lower limit must be less than the upper limit. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number using comma (,) as the decimal separator.");
                scanner.nextLine(); // Clear the invalid input
                limA = limB = 0; // Force the loop to continue
            }
        } while (limA >= limB);

        do {
            System.out.print("Please type the tolerance (e.g., 0.001): ");

            // Input validation for tolerance
            try {
                tol = scanner.nextDouble();
                if(tol <= 0 || tol >= 1) {
                    System.out.println("Tolerance must be greater than zero and lesser than 1. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number using comma (,) as the decimal separator.");
                scanner.nextLine(); // Clear the invalid input
                tol = -1; // Force the loop to continue
            }
        } while (tol <= 0 || tol >= 1);

        while (RootFinder.f(limA) * RootFinder.f(limB) > 0) { // Verifies if there is a root in the interval [limA, limB]
            if (c % 2 == 0) {
                limA -= 0.1;    // Decrease the lower limit by 0.1
            } else {
                limB += 0.1;    // Increase the upper limit by 0.1
            }
            c++;
        }

        // Print the limits where the root is found
        System.out.println("Root found between: a = " + limA + ", b = " + limB);

        // Boolean used to control the program loop
        boolean continueProgram = true;

        // Main menu loop
        while (continueProgram) {
            System.out.println("Please choose an option:");
            System.out.println("1. Use the bisection method");
            System.out.println("2. Use the secant method");
            System.out.println("3. Use the fixed-point iteration method");
            System.out.println("4. Use the newton-raphson method");
            System.out.println("5. Leave the program");

            // Input validation for menu options
            try {
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        // Call the bisection method
                        System.out.println("Root found: " + bisection(limA, limB, tol, RootFinder::f));

                        // Ask if the user wants to continue
                        System.out.println("Do you want to continue? (y/n)");
                        response = scanner.next();
                        if (!response.equalsIgnoreCase("y")) {
                            continueProgram = false;
                        }
                        break;

                    case 2:
                        // Call the secant method
                        System.out.println("Root found: " + secant(limA, limB, tol, RootFinder::f, RootFinder::fDerivative));

                        // Ask if the user wants to continue
                        System.out.println("Do you want to continue? (y/n)");
                        response = scanner.next();
                        if (!response.equalsIgnoreCase("y")) {
                            continueProgram = false;
                        }
                        break;
                    case 3:
                        // Call the fixed-point iteration method
                        do {
                            System.out.println("Please type the initial guess for the fixed-point iteration method (bigger than a, lesser than b):");
                            
                            // Input validation for the initial guess
                            try {
                                choice = scanner.nextDouble();
                                if (choice <= limA || choice >= limB) {
                                    System.out.println("The initial guess must be greater than a and less than b. Please try again.");
                                } else if(Math.abs(RootFinder.gDerivative(choice)) > 1) {
                                    System.out.println("Please pick a point where it converges. Please try again.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number using comma (,) as the decimal separator.");
                                scanner.nextLine(); // Clear the invalid input
                                choice = 0; // Force the loop to continue
                            }
                        } while ((choice <= limA || choice >= limB) && Math.abs(RootFinder.gDerivative(choice)) > 1);
                        System.out.println("Root found: " + fixedPointIteration(limA, limB, choice, tol, RootFinder::g, RootFinder::gDerivative));

                        // Ask if the user wants to continue
                        System.out.println("Do you want to continue? (y/n)");
                        response = scanner.next();
                        if (!response.equalsIgnoreCase("y")) {
                            continueProgram = false;
                        }
                        break;

                        case 4:
                        // Call the newton-raphson method
                        do {
                            System.out.println("Please type the initial guess for the fixed-point iteration method (bigger than a, lesser than b):");
                            
                            // Input validation for the initial guess
                            try {
                                choice = scanner.nextDouble();
                                if (choice <= limA || choice >= limB) {
                                    System.out.println("The initial guess must be greater than a and less than b. Please try again.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number using comma (,) as the decimal separator.");
                                scanner.nextLine(); // Clear the invalid input
                                choice = 0; // Force the loop to continue
                            }
                        } while (choice <= limA || choice >= limB);
                        System.out.println("Root found: " + newtonRaphson(limA, limB, choice, tol, RootFinder::f, RootFinder::fDerivative));

                        // Ask if the user wants to continue
                        System.out.println("Do you want to continue? (y/n)");
                        response = scanner.next();
                        if (!response.equalsIgnoreCase("y")) {
                            continueProgram = false;
                        }
                        break;

                        case 5:
                        // Exit the program
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        
                    default:
                        System.out.println("Invalid option. Please try again.");
                        // Loop back to the menu
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Clear the invalid input
                // Loop back to the menu
            }
        }
        
        // Close the scanner to prevent resource leaks
        scanner.close();
    }

    // Function definitions

    // Function f(x) = x^2 + 3x - cos(x) - 2.45
    public static double f(double x) {
        return Math.pow(x, 2) + 3 * x - Math.cos(x) - 2.45;
    }

    // Derivative of f(x)
    // f'(x) = 2x + 3 + sin(x)
    public static double fDerivative(double x) {
        return 2 * x + 3 + Math.sin(x);
    }


    // Second derivative of f(x)
    // f''(x) = 2 + cos(x)
    public static double fSecondDerivative(double x) {
        return 2 + Math.cos(x);
    }

    // Function g(x) = (x^2 + cos(x) + 2.45) / 3
    // This is the function used for fixed-point iteration
    public static double g(double x) {
        return (Math.pow(x, 2) + Math.cos(x) + 2.45) / 3;
    }

    // Derivative of g(x)
    // g'(x) = (2x + sin(x)) / 3
    public static double gDerivative(double x) {
        return (2 * x + Math.sin(x)) / 3;
    }

    public static double bisection(double a, double b, double tol, Function<Double, Double> f) {
        // Display the headers for the bisection method output
        System.out.println("Bisection method:");
        System.out.println("n\ta\t\tb\t\tXn\t\tf(Xn)\t\ttol");

        // Initialize variables for the bisection method
        int n = 0;
        double x1 = (a + b) / 2.0;

        do {
            // Print the current iteration values
            System.out.println(n + "\t" + df.format(a) + "\t" + df.format(b) + "\t" + df.format(x1) + "\t" + df.format(f.apply(x1)) + "\t" + df.format(Math.abs(b - a)));
            
            if(f.apply(x1) == 0) {
                break; // Leave the loop if the root is found
            } else if (f.apply(x1) < 0 && f.apply(a) < 0 || f.apply(x1) > 0 && f.apply(a) > 0) {
                a = x1; // The root is in the interval [x1, b]
            } else {
                b = x1; // The root is in the interval [a, x1]
            }
            x1 = (a + b) / 2.0; // Calculate the midpoint of the interval [a, b]
            n++; // Increment the iteration counter
            // Check if the absolute difference between a and b is less than the tolerance
        } while (Math.abs(b - a) >= tol);

        // Print the final values after the loop
        System.out.println(n + "\t" + df.format(a) + "\t" + df.format(b) + "\t" + df.format(x1) + "\t" + df.format(f.apply(x1)) + "\t" + df.format(Math.abs(b - a)));
        return (a + b) / 2.0; // Return the final approximation
    }

    public static double secant(double a, double b, double tol, Function<Double, Double> f, Function<Double, Double> f2) {
        // Display the headers for the secant method output
        System.out.println("Secant method:");
        System.out.println("n\tXn\t\tf(Xn)\t\ttol");

        // Define if it's the case 1 or case 2
        if(f2.apply(a) * f.apply(a) < 0) {
            // Case 1: a fixed and b variable
            double x0 = a;
            double x1 = x0 + (f.apply(x0)/ (f.apply(b) - f.apply(x0))) * (x0 - b);
            int n = 1;

            // Print the initial values
            System.out.println(n + "\t" + df.format(x0) + "\t" + df.format(f.apply(x0)) + "\t" + df.format(Math.abs(x1 - x0)));

            // Loop until the absolute difference between x1 and x0 is less than the tolerance
            while(Math.abs(x1 - x0) >= tol) {
                x0 = x1;
                x1 = x0 + (f.apply(x0)/ (f.apply(b) - f.apply(x0))) * (x0 - b);
                n++;
                System.out.println(n + "\t" + df.format(x0) + "\t" + df.format(f.apply(x0)) + "\t" + df.format(Math.abs(x1 - x0)));
            }
            return x1; // Return the root found by the secant method

        } else if(f2.apply(a) * f.apply(a) > 0) {
            // Case 2: a variable and b fixed
            double x0 = b;
            double x1 = x0 + (f.apply(x0)/ (f.apply(a) - f.apply(x0))) * (x0 - a);
            int n = 1;
            System.out.println(n + "\t" + df.format(x0) + "\t" + df.format(f.apply(x0)) + "\t" + df.format(Math.abs(x1 - x0)));

            while(Math.abs(x1 - x0) >= tol) {
                x0 = x1;
                x1 = x0 + (f.apply(x0)/ (f.apply(a) - f.apply(x0))) * (x0 - a);
                n++;
                System.out.println(n + "\t" + df.format(x0) + "\t" + df.format(f.apply(x0)) + "\t" + df.format(Math.abs(x1 - x0)));
            }
            return x1; // Return the root found by the secant method
        } else {
            System.out.println("The function does not have a root in the interval [" + a + ", " + b + "] using the secant method.");
            return Double.NaN; // Return NaN if no root is found    
        }
        
    }

    public static double fixedPointIteration(double a, double b, double choice, double tol, Function<Double, Double> g, Function<Double, Double> g1) {
        // Print the headers for the fixed-point iteration method output        
        System.out.println("Fixed-point iteration method:");
        System.out.println("n\tXn\t\tf(Xn)\t\ttol");

        // Initialize variables for the fixed-point iteration method
        double x1 = choice;
        double x2 = g.apply(x1);
        int n = 1;

        // Print the initial values
        System.out.println(n + "\t" + df.format(x1) + "\t" + df.format(f(x1)) + "\t" + df.format(Math.abs(x2 - x1)));

        while (Math.abs(x2 - x1) >= tol) {
            x1 = x2;    // Update x1 to the latest value
            x2 = g.apply(x1);   // Apply the function g to x1 to get the next approximation
            n++;
            System.out.println(n + "\t" + df.format(x1) + "\t" + df.format(f(x1)) + "\t" + df.format(Math.abs(x2 - x1)));
            
        }

        return x2; // Return the root found by the fixed-point iteration method
    }

    public static double newtonRaphson(double a, double b, double choice, double tol, Function<Double, Double> f, Function<Double, Double> fDerivative) {
        // Print the headers for the Newton-Raphson method output
        System.out.println("Newton-Raphson method:");
        System.out.println("n\tXn\t\tf(Xn)\t\tf'(Xn)\t\ttol");

        // Initialize variables for the Newton-Raphson method
        double x1 = choice;

        // Newton-Raphson iteration starts with the first approximation
        double x2 = x1 - (f.apply(x1) / fDerivative.apply(x1));
        int n = 1;

        // Print the initial values
        System.out.println(n + "\t" + df.format(x1) + "\t" + df.format(f.apply(x1)) + "\t" + df.format(fDerivative.apply(x1)) + "\t" + df.format(Math.abs(x2 - x1)));

        while (Math.abs(x2 - x1) >= tol) {
            x1 = x2;    // Update x1 to the latest value
            x2 = x1 - (f.apply(x1) / fDerivative.apply(x1));    // Apply the Newton-Raphson formula to get the next approximation
            n++;
            System.out.println(n + "\t" + df.format(x1) + "\t" + df.format(f.apply(x1)) + "\t" + df.format(fDerivative.apply(x1)) + "\t" + df.format(Math.abs(x2 - x1)));
        }

        return x2; // Return the root found by the Newton-Raphson method
    }
}