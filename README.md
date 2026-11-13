### Algorithm Explorer

Group 10 – Design and Analysis of Algorithms (CSC2107)

**1. Project Overview**

Algorithm Explorer is a Java-based educational application designed to help students understand, explore and compare algorithms through practical demonstrations and performance analysis.

The application provides an interactive environment where users can explore algorithm operations, observe their behaviour and study how input size affects execution time and computational complexity.

**2. Project Objectives**

- Demonstrate how different algorithms work.
- Help students understand algorithm efficiency.
- Compare algorithms using execution time and operation counts.
- Visualize algorithm performance using charts and tables.
- Connect theoretical time complexity with experimental results.

**3. Technologies Used**

Technology| Purpose
Java 17| Main programming language
JavaFX 21| Graphical user interface
Maven| Project management and dependencies
VS Code| Development environment
Git and GitHub| Version control and collaboration

**4. Main Features**

A. Recursion Lab

Explores recursive algorithms through practical demonstrations, including:

- Fibonacci sequence
- Factorial
- Tower of Hanoi

B. Sorting Lab

Demonstrates and compares:

- Bubble Sort
- Selection Sort
- Insertion Sort
- Merge Sort
- Quick Sort (First Element Pivot)
- Quick Sort (Randomized Pivot)

C. Time Complexity Analysis

Provides experimental analysis of algorithm performance using:

- Execution time (milliseconds)
- Number of comparisons
- Number of swaps
- Number of moves
- Average execution time across multiple runs

The analysis uses different input arrangements, including random, sorted and reverse-sorted data.

D. Data Structures Lab

Provides a section for exploring data structures and their operations.

**5. Requirements**

Before running the application, ensure you have:

- Java Development Kit (JDK) 17 or later.
- Maven installed.
- Visual Studio Code or another Java IDE.

**6. How to Run the Project**

Step 1: Clone the repository

'''git clone https://github.com/Blessing-lil/GROUP10_DAA_ALGORITHMEXPLORER.git'''

Step 2: Enter the project directory

cd GROUP10_DAA_ALGORITHMEXPLORER

Step 3: Run the application

'''mvn clean javafx:run'''

**7. Algorithm Performance Analysis**

The project evaluates sorting algorithms using different input sizes and input arrangements.


Algorithm| Best Case| Average Case| Worst Case
|---|---|---|---|
Bubble Sort| O(n)| O(n²)| O(n²)
Selection Sort| O(n²)| O(n²)| O(n²)
Insertion Sort| O(n)| O(n²)| O(n²)
Merge Sort| O(n log n)| O(n log n)| O(n log n)
Quick Sort| O(n log n)| O(n log n)| O(n²)

The experimental results help users understand how theoretical complexity relates to actual algorithm performance.

**8. Team Members**

Group 10

1. Barbra Blessing L.S 
2. Namatovu Misha 
3. Asiimwe Eva michelle 
4. Nalukwago Victoria 
5. Mirembe Gloria
6. Muhumuza Evelyn 

**9. Future Improvements**

- Expand the Data Structures Lab.
- Add more algorithms for comparison.
- Improve algorithm visualization.
- Support additional performance metrics.
- Improve experimental reporting and analysis.

Algorithm Explorer – Learn, Explore and Analyse Algorithms.
