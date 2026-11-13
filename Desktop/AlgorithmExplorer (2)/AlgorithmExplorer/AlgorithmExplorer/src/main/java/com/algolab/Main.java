package com.algolab;

import com.algolab.algorithms.Fibonacci;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algolab.algorithms.BubbleSort;
import com.algolab.algorithms.SelectionSort;
import com.algolab.algorithms.InsertionSort;
import com.algolab.algorithms.MergeSort;


public class Main extends Application {
    private static final String BURGUNDY = "#5A1C1C";
    private static final String LIGHT_GREY = "#F4F4F4";

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT_GREY + ";");

        VBox sidebar = new VBox(18);
        sidebar.setPadding(new Insets(28, 18, 25, 18));
        sidebar.setPrefWidth(230);
        sidebar.setMinWidth(210);
        sidebar.setStyle("-fx-background-color: " + BURGUNDY + ";");

        Label logo = new Label("ALGORITHM\nEXPLORER");
        logo.setStyle("-fx-text-fill:white;-fx-font-size:23px;-fx-font-weight:bold;");
        Label menuLabel = new Label("MAIN MENU");
        menuLabel.setStyle("-fx-text-fill:#D9BDBD;-fx-font-size:11px;-fx-font-weight:bold;");

        Button dashboardButton = createNavButton("Dashboard");
        Button recursionButton = createNavButton("Recursion Lab");
        Button dataButton = createNavButton("Data Structures Lab");
        Button sortingButton = createNavButton("Sorting Lab");
        Button analysisButton = createNavButton("Time Complexity Analysis");
        sidebar.getChildren().addAll(logo, new Separator(), menuLabel,
                dashboardButton, recursionButton, dataButton, sortingButton, analysisButton);

        ScrollPane sidebarScroll = new ScrollPane(sidebar);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebarScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebarScroll.setPrefViewportWidth(230);
        sidebarScroll.setStyle("-fx-background:" + BURGUNDY + ";-fx-background-color:" + BURGUNDY + ";-fx-border-color:transparent;");
        root.setLeft(sidebarScroll);
        showPage(root, createDashboard());

        dashboardButton.setOnAction(e -> showPage(root, createDashboard()));
        recursionButton.setOnAction(e -> showPage(root, createRecursionPage()));
        dataButton.setOnAction(e -> showPage(root, createModulePage(
                "Data Structures Lab", "Explore arrays, stacks, queues and linked lists.")));
        sortingButton.setOnAction(e -> showPage(root, createSortingPage()));
        analysisButton.setOnAction(e -> showPage(root, createModulePage(
                "Time Complexity Analysis", "Measure algorithm performance and study computational complexity.")));

        Scene scene = new Scene(root, 1200, 780);
        stage.setTitle("Algorithm Explorer");
        stage.setMinWidth(950);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    private void showPage(BorderPane root, VBox page) {
        ScrollPane scroll = new ScrollPane(page);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setStyle("-fx-background:" + LIGHT_GREY + ";-fx-background-color:" + LIGHT_GREY + ";-fx-border-color:transparent;");
        root.setCenter(scroll);
    }

    private Button createNavButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(12));
        button.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-font-size:13px;-fx-cursor:hand;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:#793333;-fx-text-fill:white;-fx-font-size:13px;-fx-background-radius:6;-fx-cursor:hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-font-size:13px;-fx-cursor:hand;"));
        return button;
    }

    private VBox createDashboard() {
        VBox page = new VBox(22);
        page.setPadding(new Insets(35));
        page.setStyle("-fx-background-color:" + LIGHT_GREY + ";");
        Label heading = new Label("Welcome to Algorithm Explorer");
        heading.setStyle("-fx-font-size:28px;-fx-font-weight:bold;-fx-text-fill:" + BURGUNDY + ";");
        Label description = new Label("An interactive learning environment for exploring algorithms, data structures and their performance.");
        description.setWrapText(true);
        GridPane cards = new GridPane();
        cards.setHgap(20); cards.setVgap(20);
        cards.getColumnConstraints().addAll(columnConstraint(), columnConstraint());
        cards.add(createCard("Recursion Lab", "Explore Fibonacci, Factorial and Tower of Hanoi."), 0, 0);
        cards.add(createCard("Data Structures Lab", "Learn stacks, queues, arrays and linked lists."), 1, 0);
        cards.add(createCard("Sorting Lab", "Explore and compare sorting algorithms."), 0, 1);
        cards.add(createCard("Time Complexity Analysis", "Benchmark algorithms and compare performance."), 1, 1);
        page.getChildren().addAll(heading, description, new Separator(), createSectionTitle("Learning Modules"), cards);
        return page;
    }

    private ColumnConstraints columnConstraint() {
        ColumnConstraints c = new ColumnConstraints();
        c.setPercentWidth(50); c.setHgrow(Priority.ALWAYS);
        return c;
    }

    private VBox createCard(String title, String description) {
        Label cardTitle = new Label(title);
        cardTitle.setStyle("-fx-font-size:18px;-fx-font-weight:bold;-fx-text-fill:" + BURGUNDY + ";");
        Label cardDescription = new Label(description); cardDescription.setWrapText(true);
        Label openLabel = new Label("Open module →");
        openLabel.setStyle("-fx-text-fill:" + BURGUNDY + ";-fx-font-weight:bold;");
        VBox card = new VBox(15, cardTitle, cardDescription, openLabel);
        card.setPadding(new Insets(23)); card.setPrefHeight(155); card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle("-fx-background-color:white;-fx-background-radius:10;-fx-border-color:#DDDDDD;-fx-border-radius:10;-fx-cursor:hand;");
        return card;
    }

    // ------------------------- RECURSION LAB -------------------------
    private VBox createRecursionPage() {
        VBox page = new VBox(18); page.setPadding(new Insets(28));
        page.setStyle("-fx-background-color:" + LIGHT_GREY + ";");
        Label description = new Label("Explore recursive problem solving through three important computer science problems.");
        description.setWrapText(true);
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab fibonacciTab = new Tab("Fibonacci", createFibonacciInterface());
        Tab factorialTab = new Tab("Factorial", createFactorialInterface());
        Tab hanoiTab = new Tab("Tower of Hanoi", createHanoiInterface());
        tabs.getTabs().addAll(fibonacciTab, factorialTab, hanoiTab);
        page.getChildren().addAll(createSectionTitle("Recursion Lab"), description, tabs);
        return page;
    }

    private VBox createFibonacciInterface() {
        VBox content = new VBox(18); content.setPadding(new Insets(22));
        Label explanation = new Label("The Fibonacci sequence is a mathematical sequence in which each number is obtained by adding the two numbers before it.");
        explanation.setWrapText(true);
        Label formula = new Label("F(n) = F(n - 1) + F(n - 2)\n\nBase cases:\nF(0) = 0\nF(1) = 1");
        formula.setStyle("-fx-font-size:15px;-fx-font-weight:bold;-fx-text-fill:" + BURGUNDY + ";");
        TextField numberInput = new TextField(); numberInput.setPromptText("Enter a number between 0 and 30");
        ComboBox<String> algorithmChoice = new ComboBox<>();
        algorithmChoice.getItems().addAll("Naive Recursion", "Memoized Recursion", "Bottom-up Dynamic Programming");
        algorithmChoice.setValue("Naive Recursion"); algorithmChoice.setMaxWidth(Double.MAX_VALUE);
        Button calculateButton = createActionButton("Run Algorithm");
        Button compareButton = createSecondaryButton("Compare All Algorithms");
        Label resultLabel = new Label("Your calculated result will appear here."); resultLabel.setWrapText(true);
        Label comparisonLabel = new Label("Comparison results will appear here."); comparisonLabel.setWrapText(true);
        Label complexity = new Label("Naive Recursion: O(2^n) time, O(n) space.\n\nMemoized Recursion: O(n) time, O(n) space.\n\nBottom-up Dynamic Programming: O(n) time, O(n) space.");
        complexity.setWrapText(true);
        calculateButton.setOnAction(e -> runFibonacci(numberInput, algorithmChoice, resultLabel));
        compareButton.setOnAction(e -> compareFibonacci(numberInput, comparisonLabel));
        content.getChildren().addAll(createSectionTitle("Fibonacci Sequence"),
                createInfoCard("Concept and Mathematical Definition", explanation, formula),
                createInfoCard("Run an Experiment", new Label("Input number (n)"), numberInput,
                        new Label("Select algorithm"), algorithmChoice, new HBox(10, calculateButton, compareButton)),
                createInfoCard("Execution Results", resultLabel),
                createInfoCard("Performance Comparison", comparisonLabel),
                createInfoCard("Time and Space Complexity", complexity));
        return content;
    }

    private void runFibonacci(TextField input, ComboBox<String> choice, Label output) {
        try {
            int n = Integer.parseInt(input.getText().trim());
            if (n < 0 || n > 30) { output.setText("Please enter a whole number between 0 and 30."); return; }
            int[] counter = {0}; long[] memo = new long[n + 1]; Arrays.fill(memo, -1);
            String selected = choice.getValue(); long start = System.nanoTime(); long result;
            switch (selected) {
                case "Naive Recursion": result = Fibonacci.fibonacci(n, counter); break;
                case "Memoized Recursion": result = Fibonacci.fibonacciMemoized(n, counter, memo); break;
                default: result = Fibonacci.fibonacciBottomUp(n, counter);
            }
            long elapsed = System.nanoTime() - start;
            output.setText(String.format("Algorithm: %s%nInput: %d%nResult: %d%nOperations: %d%nRuntime: %.6f ms", selected, n, result, counter[0], elapsed / 1_000_000.0));
        } catch (NumberFormatException ex) { output.setText("Please enter a valid whole number."); }
        catch (Exception ex) { output.setText("Could not run the algorithm: " + ex.getMessage()); }
    }

    private void compareFibonacci(TextField input, Label output) {
        try {
            int n = Integer.parseInt(input.getText().trim());
            if (n < 0 || n > 30) { output.setText("Please enter a whole number between 0 and 30."); return; }
            StringBuilder report = new StringBuilder("Input: ").append(n).append("\n\n");
            int[] naiveCounter = {0}; long start = System.nanoTime();
            long naiveResult = Fibonacci.fibonacci(n, naiveCounter); long naiveTime = System.nanoTime() - start;
            int[] memoCounter = {0}; long[] memo = new long[n + 1]; Arrays.fill(memo, -1);
            start = System.nanoTime(); long memoResult = Fibonacci.fibonacciMemoized(n, memoCounter, memo); long memoTime = System.nanoTime() - start;
            int[] bottomCounter = {0}; start = System.nanoTime();
            long bottomResult = Fibonacci.fibonacciBottomUp(n, bottomCounter); long bottomTime = System.nanoTime() - start;
            report.append(String.format("Naive Recursion%nResult: %d | Operations: %d | Runtime: %.6f ms%n%n", naiveResult, naiveCounter[0], naiveTime / 1_000_000.0));
            report.append(String.format("Memoized Recursion%nResult: %d | Operations: %d | Runtime: %.6f ms%n%n", memoResult, memoCounter[0], memoTime / 1_000_000.0));
            report.append(String.format("Bottom-up DP%nResult: %d | Operations: %d | Runtime: %.6f ms", bottomResult, bottomCounter[0], bottomTime / 1_000_000.0));
            output.setText(report.toString());
        } catch (NumberFormatException ex) { output.setText("Please enter a valid whole number."); }
        catch (Exception ex) { output.setText("Could not compare algorithms: " + ex.getMessage()); }
    }

    private VBox createFactorialInterface() {
        VBox content = new VBox(18); content.setPadding(new Insets(22));
        Label explanation = new Label("The factorial of a non-negative integer is the product of all positive integers less than or equal to that number."); explanation.setWrapText(true);
        Label formula = new Label("n! = n × (n - 1) × (n - 2) × ... × 1\n\nBase case:\n0! = 1");
        formula.setStyle("-fx-font-size:15px;-fx-font-weight:bold;-fx-text-fill:" + BURGUNDY + ";");
        TextField numberInput = new TextField(); numberInput.setPromptText("Enter a number between 0 and 20");
        ComboBox<String> algorithmChoice = new ComboBox<>(); algorithmChoice.getItems().addAll("Recursive", "Iterative"); algorithmChoice.setValue("Recursive");
        Button calculateButton = createActionButton("Run Algorithm"); Button compareButton = createSecondaryButton("Compare Both Algorithms");
        Label resultLabel = new Label("Your calculated result will appear here.");
        Label comparisonLabel = new Label("Comparison results will appear here.");
        calculateButton.setOnAction(e -> resultLabel.setText("Factorial backend connection is the next integration step."));
        compareButton.setOnAction(e -> comparisonLabel.setText("Factorial comparison will be connected in the next integration step."));
        Label complexity = new Label("Recursive: O(n) time, O(n) space.\n\nIterative: O(n) time, O(1) auxiliary space.");
        content.getChildren().addAll(createSectionTitle("Factorial"), createInfoCard("Concept and Mathematical Definition", explanation, formula),
                createInfoCard("Run an Experiment", new Label("Input number (n)"), numberInput, new Label("Select algorithm"), algorithmChoice, new HBox(10, calculateButton, compareButton)),
                createInfoCard("Execution Results", resultLabel), createInfoCard("Performance Comparison", comparisonLabel), createInfoCard("Time and Space Complexity", complexity));
        return content;
    }

    private VBox createHanoiInterface() {
        VBox content = new VBox(18); content.setPadding(new Insets(22));
        Label explanation = new Label("Tower of Hanoi is a mathematical puzzle consisting of three pegs and disks of different sizes. The objective is to move all disks from the source peg to the destination peg."); explanation.setWrapText(true);
        Label formula = new Label("T(n) = 2T(n - 1) + 1\n\nMinimum number of moves = 2^n - 1");
        TextField unused = new TextField();
        ComboBox<Integer> diskChoice = new ComboBox<>(); diskChoice.getItems().addAll(1,2,3,4,5,6,7,8); diskChoice.setValue(3);
        Slider speedSlider = new Slider(1,5,3); speedSlider.setShowTickLabels(true); speedSlider.setShowTickMarks(true); speedSlider.setMajorTickUnit(1); speedSlider.setSnapToTicks(true);
        Label minimumMoves = new Label("Minimum moves required: 7"); Label moveCounter = new Label("Moves completed: 0");
        diskChoice.setOnAction(e -> minimumMoves.setText("Minimum moves required: " + ((1 << diskChoice.getValue()) - 1)));
        HBox pegs = new HBox(35); pegs.setAlignment(Pos.BOTTOM_CENTER); pegs.setPadding(new Insets(25)); pegs.setMinHeight(220);
        for (int i=1;i<=3;i++) {
            VBox peg = new VBox(8); peg.setAlignment(Pos.BOTTOM_CENTER);
            Label pole = new Label("│"); pole.setStyle("-fx-font-size:100px;-fx-text-fill:#888888;");
            Label base = new Label("━━━━━━━━"); base.setStyle("-fx-font-size:17px;-fx-text-fill:" + BURGUNDY + ";");
            Label name = new Label(i==1 ? "SOURCE" : i==2 ? "AUXILIARY" : "DESTINATION"); name.setStyle("-fx-font-weight:bold;");
            peg.getChildren().addAll(pole,base,name); HBox.setHgrow(peg,Priority.ALWAYS); pegs.getChildren().add(peg);
        }
        Button start = createActionButton("Start"); Button pause = createSecondaryButton("Pause"); Button next = createSecondaryButton("Next Move"); Button reset = createSecondaryButton("Reset");
        Label status = new Label("Animation instructions will appear here.");
        start.setOnAction(e -> status.setText("Tower of Hanoi animation will be connected in a later integration step."));
        pause.setOnAction(e -> status.setText("Pause functionality will be connected later."));
        next.setOnAction(e -> status.setText("Next move functionality will be connected later."));
        reset.setOnAction(e -> { moveCounter.setText("Moves completed: 0"); status.setText("Puzzle reset."); });
        Label complexity = new Label("Time Complexity: O(2^n)\nSpace Complexity: O(n)");
        content.getChildren().addAll(createSectionTitle("Tower of Hanoi"), createInfoCard("Concept and Mathematical Definition", explanation, formula),
                createInfoCard("Puzzle Settings", new Label("Number of disks"), diskChoice, new Label("Animation speed"), speedSlider, minimumMoves),
                createInfoCard("Puzzle Visualisation", pegs), createInfoCard("Animation Controls", new HBox(10,start,pause), new HBox(10,next,reset), moveCounter,status),
                createInfoCard("Time and Space Complexity", complexity));
        return content;
    }

    // ------------------------- SORTING LAB -------------------------
    private static class SortResult {
        final List<int[]> steps = new ArrayList<>();
        int comparisons;
        int swaps; // Includes shifts/moves for insertion and merge sort.
        long executionTime;
        int[] sortedArray;
    }

    private VBox createSortingPage() {
        VBox page = new VBox(20); page.setPadding(new Insets(30));
        page.setStyle("-fx-background-color:" + LIGHT_GREY + ";");
        Label description = new Label("Enter numbers, select a sorting algorithm and watch each step of the sorting process."); description.setWrapText(true);
        Label inputLabel = new Label("Enter numbers separated by commas:");
        TextField inputField = new TextField("5, 2, 8, 1, 4"); inputField.setPromptText("Example: 5, 2, 8, 1, 4");
        Label algorithmLabel = new Label("Select sorting algorithm:");
        ComboBox<String> algorithmChoice = new ComboBox<>();
        algorithmChoice.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort");
        algorithmChoice.setValue("Bubble Sort"); algorithmChoice.setMaxWidth(Double.MAX_VALUE);

        Button startButton = createActionButton("Start Sorting");
        Button nextButton = createSecondaryButton("Next Step");
        Button autoButton = createSecondaryButton("Auto Play");
        Button resetButton = createSecondaryButton("Reset");
        HBox buttons = new HBox(10,startButton,nextButton,autoButton,resetButton); buttons.setAlignment(Pos.CENTER_LEFT);

        HBox arrayDisplay = new HBox(12); arrayDisplay.setAlignment(Pos.CENTER); arrayDisplay.setPadding(new Insets(20)); arrayDisplay.setMinHeight(100);
        Label statusLabel = new Label("Enter numbers and click Start Sorting."); statusLabel.setWrapText(true);
        Label comparisonsLabel = new Label("Comparisons: 0");
        Label swapsLabel = new Label("Swaps / movements: 0");
        Label timeLabel = new Label("Execution Time: 0 ns");
        Label stepsLabel = new Label("Step: 0");
        Label sortedLabel = new Label("Sorted result: not available yet."); sortedLabel.setWrapText(true);
        VBox performanceCard = createInfoCard("Performance Results", comparisonsLabel, swapsLabel, timeLabel, stepsLabel, sortedLabel);

        SortResult[] result = {null}; int[] currentStep = {0}; Timeline[] timeline = {null};

        startButton.setOnAction(e -> {
            if (timeline[0] != null) timeline[0].stop();
            try {
                int[] numbers = parseNumbers(inputField.getText());
                if (numbers.length > 100) { statusLabel.setText("Please enter no more than 100 numbers."); return; }
                String selected = algorithmChoice.getValue();
                result[0] = runSelectedSort(numbers, selected);
                currentStep[0] = 0;
                displaySortingArray(arrayDisplay, result[0].steps.get(0));
                comparisonsLabel.setText("Comparisons: " + result[0].comparisons);
                swapsLabel.setText("Swaps / movements: " + result[0].swaps);
                timeLabel.setText("Execution Time: " + result[0].executionTime + " ns");
                stepsLabel.setText("Step: 0 / " + (result[0].steps.size()-1));
                sortedLabel.setText("Sorted result: " + Arrays.toString(result[0].sortedArray));
                statusLabel.setText(selected + " is ready. Click Next Step or Auto Play to view the process.");
            } catch (NumberFormatException ex) {
                result[0] = null; arrayDisplay.getChildren().clear();
                statusLabel.setText("Invalid input. Enter whole numbers separated by commas, for example: 5, 2, 8, 1.");
            } catch (IllegalArgumentException ex) {
                result[0] = null; statusLabel.setText(ex.getMessage());
            } catch (Exception ex) {
                result[0] = null; statusLabel.setText("Could not sort numbers: " + ex.getMessage());
            }
        });

        nextButton.setOnAction(e -> {
            if (result[0] == null) { statusLabel.setText("Click Start Sorting first."); return; }
            if (timeline[0] != null) timeline[0].stop();
            if (currentStep[0] < result[0].steps.size()-1) {
                currentStep[0]++;
                displaySortingArray(arrayDisplay,result[0].steps.get(currentStep[0]));
                stepsLabel.setText("Step: " + currentStep[0] + " / " + (result[0].steps.size()-1));
                statusLabel.setText(currentStep[0] == result[0].steps.size()-1 ? "Sorting completed!" : "Viewing step " + currentStep[0] + ".");
            } else statusLabel.setText("Sorting is already completed.");
        });

        autoButton.setOnAction(e -> {
            if (result[0] == null) { statusLabel.setText("Click Start Sorting first."); return; }
            if (currentStep[0] >= result[0].steps.size()-1) { statusLabel.setText("Sorting is already completed."); return; }
            if (timeline[0] != null) timeline[0].stop();
            timeline[0] = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                if (result[0] != null && currentStep[0] < result[0].steps.size()-1) {
                    currentStep[0]++;
                    displaySortingArray(arrayDisplay,result[0].steps.get(currentStep[0]));
                    stepsLabel.setText("Step: " + currentStep[0] + " / " + (result[0].steps.size()-1));
                }
            }));
            timeline[0].setCycleCount(result[0].steps.size()-currentStep[0]-1);
            timeline[0].setOnFinished(event -> statusLabel.setText("Sorting completed!"));
            timeline[0].play(); statusLabel.setText("Auto playing...");
        });

        resetButton.setOnAction(e -> {
            if (timeline[0] != null) timeline[0].stop();
            result[0] = null; currentStep[0] = 0; arrayDisplay.getChildren().clear();
            comparisonsLabel.setText("Comparisons: 0"); swapsLabel.setText("Swaps / movements: 0");
            timeLabel.setText("Execution Time: 0 ns"); stepsLabel.setText("Step: 0");
            sortedLabel.setText("Sorted result: not available yet."); statusLabel.setText("Reset complete. Enter numbers and start again.");
        });

        page.getChildren().addAll(createSectionTitle("Sorting Algorithms"), description,
                createInfoCard("Sorting Configuration", inputLabel,inputField,algorithmLabel,algorithmChoice,buttons),
                createInfoCard("Array Visualization", arrayDisplay,statusLabel),performanceCard);
        return page;
    }

    private int[] parseNumbers(String text) {
        if (text == null || text.trim().isEmpty()) throw new IllegalArgumentException("Please enter some numbers.");
        String[] parts = text.trim().split(",", -1);
        int[] numbers = new int[parts.length];
        for (int i=0;i<parts.length;i++) {
            if (parts[i].trim().isEmpty()) throw new NumberFormatException("Empty number");
            numbers[i] = Integer.parseInt(parts[i].trim());
        }
        return numbers;
    }

    private SortResult runSelectedSort(int[] input, String algorithm) {
        SortResult r = new SortResult();
        int[] a = input.clone();
        r.steps.add(a.clone());
        long start = System.nanoTime();
        switch (algorithm) {
            case "Bubble Sort": bubbleSort(a,r); break;
            case "Selection Sort": selectionSort(a,r); break;
            case "Insertion Sort": insertionSort(a,r); break;
            case "Merge Sort": mergeSort(a,0,a.length-1,r); break;
            default: throw new IllegalArgumentException("Please select a sorting algorithm.");
        }
        r.executionTime = System.nanoTime()-start;
        r.sortedArray = a.clone();
        // Ensure the final sorted state is always available as the last animation frame.
        if (!Arrays.equals(r.steps.get(r.steps.size()-1),a)) r.steps.add(a.clone());
        return r;
    }

    private void bubbleSort(int[] a, SortResult r) {
        for (int i=0;i<a.length-1;i++) {
            boolean changed = false;
            for (int j=0;j<a.length-i-1;j++) {
                r.comparisons++;
                if (a[j] > a[j+1]) {
                    int temp=a[j]; a[j]=a[j+1]; a[j+1]=temp;
                    r.swaps++; changed=true; r.steps.add(a.clone());
                }
            }
            if (!changed) break;
        }
    }

    private void selectionSort(int[] a, SortResult r) {
        for (int i=0;i<a.length-1;i++) {
            int min=i;
            for (int j=i+1;j<a.length;j++) {
                r.comparisons++;
                if (a[j] < a[min]) min=j;
            }
            if (min != i) {
                int temp=a[i]; a[i]=a[min]; a[min]=temp;
                r.swaps++; r.steps.add(a.clone());
            }
        }
    }

    private void insertionSort(int[] a, SortResult r) {
        for (int i=1;i<a.length;i++) {
            int key=a[i], j=i-1;
            while (j>=0) {
                r.comparisons++;
                if (a[j] > key) {
                    a[j+1]=a[j]; r.swaps++; r.steps.add(a.clone()); j--;
                } else break;
            }
            a[j+1]=key;
            if (j+1 != i && (r.steps.isEmpty() || !Arrays.equals(r.steps.get(r.steps.size()-1),a))) {
                r.swaps++; r.steps.add(a.clone());
            }
        }
    }

    private void mergeSort(int[] a, int left, int right, SortResult r) {
        if (left >= right) return;
        int mid = left + (right-left)/2;
        mergeSort(a,left,mid,r);
        mergeSort(a,mid+1,right,r);
        merge(a,left,mid,right,r);
    }

    private void merge(int[] a, int left, int mid, int right, SortResult r) {
        int[] temp = new int[right-left+1];
        int i=left, j=mid+1, k=0;
        while (i<=mid && j<=right) {
            r.comparisons++;
            if (a[i] <= a[j]) temp[k++]=a[i++]; else temp[k++]=a[j++];
        }
        while (i<=mid) temp[k++]=a[i++];
        while (j<=right) temp[k++]=a[j++];
        for (int x=0;x<temp.length;x++) { a[left+x]=temp[x]; r.swaps++; }
        r.steps.add(a.clone());
    }

    private void displaySortingArray(HBox container, int[] array) {
        container.getChildren().clear();
        for (int number : array) {
            Label value = new Label(String.valueOf(number));
            value.setMinSize(55,55); value.setAlignment(Pos.CENTER);
            value.setStyle("-fx-background-color:" + BURGUNDY + ";-fx-text-fill:white;-fx-font-size:18px;-fx-font-weight:bold;-fx-background-radius:8;");
            container.getChildren().add(value);
        }
    }

    // ------------------------- SHARED UI HELPERS -------------------------
    private VBox createModulePage(String title, String description) {
        VBox page = new VBox(20); page.setPadding(new Insets(30)); page.setStyle("-fx-background-color:" + LIGHT_GREY + ";");
        Label details = new Label(description); details.setWrapText(true);
        page.getChildren().addAll(createSectionTitle(title),createInfoCard("Module Overview",details));
        return page;
    }

    private VBox createInfoCard(String title, Node... components) {
        Label heading = new Label(title);
        heading.setStyle("-fx-font-size:16px;-fx-font-weight:bold;-fx-text-fill:" + BURGUNDY + ";");
        VBox card = new VBox(13); card.setPadding(new Insets(20)); card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle("-fx-background-color:white;-fx-background-radius:9;-fx-border-color:#E2E2E2;-fx-border-radius:9;");
        card.getChildren().add(heading); card.getChildren().addAll(components);
        return card;
    }

    private Label createSectionTitle(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:" + BURGUNDY + ";");
        return label;
    }

    private Button createActionButton(String text) {
        Button button = new Button(text); button.setPadding(new Insets(11,20,11,20));
        button.setStyle("-fx-background-color:" + BURGUNDY + ";-fx-text-fill:white;-fx-font-weight:bold;-fx-background-radius:6;-fx-cursor:hand;");
        return button;
    }

    private Button createSecondaryButton(String text) {
        Button button = new Button(text); button.setPadding(new Insets(11,20,11,20));
        button.setStyle("-fx-background-color:#EEEEEE;-fx-text-fill:" + BURGUNDY + ";-fx-font-weight:bold;-fx-background-radius:6;-fx-border-color:#DDDDDD;-fx-border-radius:6;-fx-cursor:hand;");
        return button;
    }

    public static void main(String[] args) { launch(args); }
}