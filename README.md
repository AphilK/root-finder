# Root Finder

  \#\# Table of Contents

  * [About the Project](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#about-the-project)
  * [Features](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#features)
  * [Getting Started](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#getting-started)
      * [Prerequisites](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#prerequisites)
      * [Installation](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#installation)
  * [Usage](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#usage)
  * [Supported Methods](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#supported-methods)
  * [Examples]([https://www.google.com/search?q=%23](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#)examples)
  * [Contributing]([https://www.google.com/search?q=%23](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#)contributing)
  * [License]([https://www.google.com/search?q=%23](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#)license)
  * [Contact]([https://www.google.com/search?q=%23](https://github.com/AphilK/root-finder/tree/main?tab=readme-ov-file#)contact)

## About the Project

Root Finder is a Java program designed to efficiently calculate the roots of a given mathematical function using several well-known numerical methods. This project serves as a practical implementation and comparison of these iterative techniques, providing a robust tool for solving equations of the form $f(x) = y$.

Whether you're a student studying numerical analysis, a developer needing a quick root-finding solution, or simply curious about these algorithms, Root Finder aims to be a clear and functional resource.

## Features

  * **Multiple Root-Finding Methods:** Implements Bisection, Secant, Fixed-Point Iteration, and Newton-Raphson methods.
  * **Configurable Parameters:** Allows users to set tolerance, maximum iterations, and initial guesses for each method.
  * **Clear Output:** Provides iteration details, estimated root, and error in a easy-to-read table.
  * **Modular Design:** Easy to extend with new root-finding algorithms.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

  * **Java Development Kit (JDK) 8 or higher:** You can download it from [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or using a package manager.

    ```bash
    java -version
    ```

    Ensure the output shows a version of 1.8 or higher.

### Installation

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/root-finder.git
    ```

2.  **Navigate to the project directory:**

    ```bash
    cd root-finder
    ```

3.  **Compile the Java source files:**

    ```bash
    javac src/*.java
    ```

## Usage

To run the Root Finder program, execute the compiled Java classes from the project root directory.

```bash
java -cp src RootFinder
```

## Supported Methods

This project implements the following numerical root-finding methods:

1.  **Bisection Method:** A robust, bracketing method that repeatedly halves an interval guaranteed to contain a root. Requires an interval $[a, b]$ where $f(a)$ and $f(b)$ have opposite signs.
2.  **Secant Method:** An open method that approximates the derivative using a secant line. Requires two initial guesses $x\_0$ and $x\_1$.
3.  **Fixed-Point Iteration:** Transforms $f(x) = 0$ into $x = g(x)$ and iteratively applies $x\_{n+1} = g(x\_n)$. Requires an initial guess $x\_0$ and a suitable $g(x)$ function.
4.  **Newton-Raphson Method:** An open method that uses the tangent line to approximate the root. Requires an initial guess $x\_0$ and the derivative of the function, $f'(x)$.

## Examples

Here are some conceptual examples of how you might use the program for different functions and methods. (You should replace these with actual examples that work with your code.)

**Example 1: Finding the root of $f(x) = x^2 + 3x - cos(x) - 2.45$ using the Bisection Method**

```
// Function f(x) = x^2 + 3x - cos(x) - 2.45
public static double f(double x) {
    return Math.pow(x, 2) + 3 * x - Math.cos(x) - 2.45;
}
```

**Expected (Conceptual) Output:**

```
Initiating program...
Please type the lower limit (a): 1
Please type the upper limit (b): 2
Please type the tolerance (e.g., 0.001): 0,001
Root found between: a = 0.8, b = 2.1
Please choose an option:
1. Use the bisection method
2. Use the secant method
3. Use the fixed-point iteration method
4. Use the newton-raphson method
5. Leave the program
1
Bisection method:
n       a               b               Xn              f(Xn)           tol
0       0,8000000000    2,1000000000    1,4500000000    3,8819972306    1,3000000000
1       0,8000000000    1,4500000000    1,1250000000    1,7594484832    0,6500000000
2       0,8000000000    1,1250000000    0,9625000000    0,7924360330    0,3250000000
3       0,8000000000    0,9625000000    0,8812500000    0,3341643394    0,1625000000
4       0,8000000000    0,8812500000    0,8406250000    0,1115280971    0,0812500000
5       0,8000000000    0,8406250000    0,8203125000    0,0018574067    0,0406250000
6       0,8000000000    0,8203125000    0,8101562500    -0,0525633553   0,0203125000
7       0,8101562500    0,8203125000    0,8152343750    -0,0253876028   0,0101562500
8       0,8152343750    0,8203125000    0,8177734375    -0,0117737492   0,0050781250
9       0,8177734375    0,8203125000    0,8190429688    -0,0049603333   0,0025390625
10      0,8190429688    0,8203125000    0,8196777344    -0,0015520037   0,0012695312
11      0,8196777344    0,8203125000    0,8199951172    0,0001525664    0,0006347656
Root found: 0.8199951171875
Do you want to continue? (y/n)
```

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star\! Thanks again\!

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

-----

## Contact

Your Email - joaosilvanascimento.jops@gmail.com

Project Link: [https://github.com/Aphilk/root-finder](https://www.google.com/search?q=https://github.com/aphilk/root-finder)

-----
