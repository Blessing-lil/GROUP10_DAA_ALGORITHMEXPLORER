
package com.algolab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.algolab.algorithms.Factorial;
import com.algolab.algorithms.Fibonacci;
import com.algolab.algorithms.TowerofHanoi;
import com.algolab.analysis.AnalysisResult;
import com.algolab.analysis.ComplexityAnalyzer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static final String BURGUNDY = "#5A1C1C";
    private static final String LIGHT_GREY = "#F4F4F4";

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + LIGHT_GREY + ";");

        // SIDEBAR
        VBox sidebar = new VBox(18);
        sidebar.setPadding(new Insets(28, 18, 25, 18));
        sidebar.setPrefWidth(230);
        sidebar.setMinWidth(210);
        sidebar.setStyle("-fx-background-color: " + BURGUNDY + ";");

        Label logo = new Label("ALGORITHM\nEXPLORER");
        logo.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;"
        );

        Label menuLabel = new Label("MAIN MENU");
        menuLabel.setStyle(
                "-fx-text-fill: #D9BDBD;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Button dashboardButton = createNavButton("Dashboard");
        Button recursionButton = createNavButton("Recursion Lab");
        Button dataButton = createNavButton("Data Structures Lab");
        Button sortingButton = createNavButton("Sorting Lab");
        Button analysisButton = createNavButton("Time Complexity Analysis");

        sidebar.getChildren().addAll(
                logo,
                new Separator(),
                menuLabel,
                dashboardButton,
                recursionButton,
                dataButton,
                sortingButton,
                analysisButton
        );

        ScrollPane sidebarScroll = new ScrollPane(sidebar);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebarScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebarScroll.setPrefViewportWidth(230);
        sidebarScroll.setStyle(
                "-fx-background: " + BURGUNDY +
                "; -fx-background-color: " + BURGUNDY +
                "; -fx-border-color: transparent;"
        );

        root.setLeft(sidebarScroll);

        showPage(root, createDashboard());

        // NAVIGATION
        dashboardButton.setOnAction(e ->
                showPage(root, createDashboard()));

        recursionButton.setOnAction(e ->
                showPage(root, createRecursionPage()));

        dataButton.setOnAction(e ->
                showPage(root, createModulePage(
                        "Data Structures Lab",
                        "Explore arrays, stacks, queues and linked lists."
                )));

        sortingButton.setOnAction(e ->
                showPage(root, createModulePage(
                        "Sorting Lab",
                        "Explore and compare different sorting algorithms."
                )));

        analysisButton.setOnAction(e ->
                showPage(root, createComplexityPage()));

        Scene scene = new Scene(root, 1200, 780);

        stage.setTitle("Algorithm Explorer");
        stage.setMinWidth(950);
        stage.setMinHeight(650);
        stage.setScene(scene);
        stage.show();
    }

    // DISPLAY PAGE INSIDE SCROLLABLE WORKSPACE
    private void showPage(BorderPane root, VBox page) {

        ScrollPane scroll = new ScrollPane(page);

        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scroll.setStyle(
                "-fx-background: " + LIGHT_GREY +
                "; -fx-background-color: " + LIGHT_GREY +
                "; -fx-border-color: transparent;"
        );

        root.setCenter(scroll);
    }

    // SIDEBAR BUTTON
    private Button createNavButton(String text) {

        Button button = new Button(text);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPadding(new Insets(12));

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #793333;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;"
        ));

        return button;
    }

    // DASHBOARD
    private VBox createDashboard() {

        VBox page = new VBox(22);
        page.setPadding(new Insets(35));
        page.setStyle("-fx-background-color: " + LIGHT_GREY + ";");

        Label heading = new Label("Welcome to Algorithm Explorer");

        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label description = new Label(
                "An interactive learning environment for exploring algorithms, " +
                "data structures and their performance."
        );

        description.setWrapText(true);

        GridPane cards = new GridPane();
        cards.setHgap(20);
        cards.setVgap(20);

        cards.getColumnConstraints().addAll(
                columnConstraint(),
                columnConstraint()
        );

        cards.add(createCard(
                "Recursion Lab",
                "Explore Fibonacci, Factorial and Tower of Hanoi."
        ), 0, 0);

        cards.add(createCard(
                "Data Structures Lab",
                "Learn stacks, queues, arrays and linked lists."
        ), 1, 0);

        cards.add(createCard(
                "Sorting Lab",
                "Explore and compare sorting algorithms."
        ), 0, 1);

        cards.add(createCard(
                "Time Complexity Analysis",
                "Benchmark algorithms and compare performance."
        ), 1, 1);

        page.getChildren().addAll(
                heading,
                description,
                new Separator(),
                createSectionTitle("Learning Modules"),
                cards
        );

        return page;
    }

    private ColumnConstraints columnConstraint() {

        ColumnConstraints c = new ColumnConstraints();
        c.setPercentWidth(50);
        c.setHgrow(Priority.ALWAYS);

        return c;
    }

    // DASHBOARD CARD
    private VBox createCard(String title, String description) {

        Label cardTitle = new Label(title);

        cardTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label cardDescription = new Label(description);
        cardDescription.setWrapText(true);

        Label openLabel = new Label("Open module →");

        openLabel.setStyle(
                "-fx-text-fill: " + BURGUNDY + ";" +
                "-fx-font-weight: bold;"
        );

        VBox card = new VBox(
                15,
                cardTitle,
                cardDescription,
                openLabel
        );

        card.setPadding(new Insets(23));
        card.setPrefHeight(155);
        card.setMaxWidth(Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #DDDDDD;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        );

        return card;
    }

    // RECURSION LAB
    private VBox createRecursionPage() {

        VBox page = new VBox(18);
        page.setPadding(new Insets(28));
        page.setStyle("-fx-background-color: " + LIGHT_GREY + ";");

        Label heading = new Label("Recursion Lab");

        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label description = new Label(
                "Explore recursive problem solving through three important " +
                "computer science problems."
        );

        description.setWrapText(true);

        TabPane tabs = new TabPane();

        tabs.setTabClosingPolicy(
                TabPane.TabClosingPolicy.UNAVAILABLE
        );

        Tab fibonacciTab = new Tab("Fibonacci");
        Tab factorialTab = new Tab("Factorial");
        Tab hanoiTab = new Tab("Tower of Hanoi");

        fibonacciTab.setContent(
                createTabScroll(createFibonacciInterface())
        );

        factorialTab.setContent(
                createTabScroll(createFactorialInterface())
        );

        hanoiTab.setContent(
                createTabScroll(createHanoiInterface())
        );

        tabs.setPrefHeight(560);
        tabs.setMinHeight(450);
        VBox.setVgrow(tabs, Priority.ALWAYS);

        tabs.getTabs().addAll(
                fibonacciTab,
                factorialTab,
                hanoiTab
        );

        page.getChildren().addAll(
                heading,
                description,
                tabs
        );

        return page;
    }

    // SCROLLABLE TAB CONTENT
    private ScrollPane createTabScroll(Node content) {

        ScrollPane scroll = new ScrollPane(content);

        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setPannable(true);

        scroll.setStyle(
                "-fx-background: " + LIGHT_GREY +
                "; -fx-background-color: " + LIGHT_GREY +
                "; -fx-border-color: transparent;"
        );

        return scroll;
    }

    // FIBONACCI INTERFACE
    private VBox createFibonacciInterface() {

        VBox content = new VBox(18);
        content.setPadding(new Insets(22));

        Label title = createSectionTitle("Fibonacci Sequence");

        Label explanation = new Label(
                "The Fibonacci sequence is a series of numbers where each number " +
                "is the sum of the two numbers before it. The sequence starts with 0 and 1."
        );

        explanation.setWrapText(true);

        Label formula = new Label(
                "F(0) = 0\n" +
                "F(1) = 1\n" +
                "F(n) = F(n - 1) + F(n - 2)"
        );

        formula.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        VBox conceptCard = createInfoCard(
                "Concept and Mathematical Definition",
                explanation,
                formula
        );

        Label inputLabel = new Label("Enter the value of n");

        TextField numberInput = new TextField();
        numberInput.setPromptText("Enter a number (0–40)");

        Label algorithmLabel = new Label("Choose an algorithm");

        ComboBox<String> algorithmChoice = new ComboBox<>();

        algorithmChoice.getItems().addAll(
                "Naive Recursion",
                "Memoized Recursion",
                "Bottom-up Dynamic Programming"
        );

        algorithmChoice.setValue("Naive Recursion");
        algorithmChoice.setMaxWidth(Double.MAX_VALUE);

        Button runButton = createActionButton("Run Algorithm");
        Button compareButton = createSecondaryButton("Compare All Algorithms");

        Label resultLabel = new Label("Your result will appear here.");
        resultLabel.setWrapText(true);

        Label comparisonLabel = new Label(
                "Comparison results will appear here."
        );

        comparisonLabel.setWrapText(true);

        runButton.setOnAction(e -> {

            try {

                int n = Integer.parseInt(numberInput.getText().trim());

                if (n < 0 || n > 40) {
                    resultLabel.setText(
                            "Please enter a number between 0 and 40."
                    );
                    return;
                }

                int[] counter = {0};
                long startTime = System.nanoTime();

                long result;

                String selected = algorithmChoice.getValue();

                if (selected.equals("Naive Recursion")) {

                    result = Fibonacci.fibonacci(n, counter);

                } else if (selected.equals("Memoized Recursion")) {

                    long[] memo = new long[n + 1];
                    Arrays.fill(memo, -1);

                    result = Fibonacci.fibonacciMemoized(
                            n, counter, memo
                    );

                } else {

                    result = Fibonacci.fibonacciBottomUp(
                            n, counter
                    );
                }

                long endTime = System.nanoTime();

                double runtime = (endTime - startTime) / 1_000_000.0;

                resultLabel.setText(
                        "Algorithm: " + selected + "\n" +
                        "Input: " + n + "\n" +
                        "Fibonacci Result: " + result + "\n" +
                        "Operations: " + counter[0] + "\n" +
                        "Runtime: " + String.format("%.6f", runtime) + " ms"
                );

            } catch (NumberFormatException ex) {

                resultLabel.setText(
                        "Please enter a valid whole number."
                );
            }
        });

        compareButton.setOnAction(e -> {

            try {

                int n = Integer.parseInt(numberInput.getText().trim());

                if (n < 0 || n > 40) {
                    comparisonLabel.setText(
                            "Please enter a number between 0 and 40."
                    );
                    return;
                }

                StringBuilder output = new StringBuilder();

                int[] naiveCounter = {0};
                long start1 = System.nanoTime();

                long naiveResult = Fibonacci.fibonacci(
                        n, naiveCounter
                );

                long end1 = System.nanoTime();

                double naiveTime = (end1 - start1) / 1_000_000.0;

                int[] memoCounter = {0};
                long[] memo = new long[n + 1];
                Arrays.fill(memo, -1);

                long start2 = System.nanoTime();

                long memoResult = Fibonacci.fibonacciMemoized(
                        n, memoCounter, memo
                );

                long end2 = System.nanoTime();

                double memoTime = (end2 - start2) / 1_000_000.0;

                int[] bottomCounter = {0};

                long start3 = System.nanoTime();

                long bottomResult = Fibonacci.fibonacciBottomUp(
                        n, bottomCounter
                );

                long end3 = System.nanoTime();

                double bottomTime = (end3 - start3) / 1_000_000.0;

                output.append("Input: ").append(n).append("\n\n");

                output.append("NAIVE RECURSION\n");
                output.append("Result: ").append(naiveResult).append("\n");
                output.append("Operations: ").append(naiveCounter[0]).append("\n");
                output.append("Runtime: ").append(String.format("%.6f", naiveTime)).append(" ms\n\n");

                output.append("MEMOIZED RECURSION\n");
                output.append("Result: ").append(memoResult).append("\n");
                output.append("Operations: ").append(memoCounter[0]).append("\n");
                output.append("Runtime: ").append(String.format("%.6f", memoTime)).append(" ms\n\n");

                output.append("BOTTOM-UP DYNAMIC PROGRAMMING\n");
                output.append("Result: ").append(bottomResult).append("\n");
                output.append("Operations: ").append(bottomCounter[0]).append("\n");
                output.append("Runtime: ").append(String.format("%.6f", bottomTime)).append(" ms\n");

                comparisonLabel.setText(output.toString());

            } catch (NumberFormatException ex) {

                comparisonLabel.setText(
                        "Please enter a valid whole number."
                );
            }
        });

        Label complexity = new Label(
                "Naive Recursion: O(2^n) time \n" +
                "Memoized Recursion: O(n) time \n" +
                "Bottom-up Dynamic Programming: O(n) time"
        );

        complexity.setWrapText(true);

        content.getChildren().addAll(
                title,
                conceptCard,
                createInfoCard(
                        "Run an Experiment",
                        inputLabel,
                        numberInput,
                        algorithmLabel,
                        algorithmChoice,
                        new HBox(10, runButton, compareButton)
                ),
                createInfoCard("Execution Results", resultLabel),
                createInfoCard("Performance Comparison", comparisonLabel),
                createInfoCard("Time and Space Complexity", complexity)
        );

        return content;
    }

    // FACTORIAL INTERFACE
    private VBox createFactorialInterface() {

        VBox content = new VBox(18);
        content.setPadding(new Insets(22));

        Label title = createSectionTitle("Factorial");

        Label explanation = new Label(
                "The factorial of a non-negative integer is the product of all " +
                "positive integers less than or equal to that number. " +
                "The factorial of zero is 1."
        );

        explanation.setWrapText(true);

        Label formula = new Label(
                "0! = 1\n" +
                "n! = n × (n - 1) × ... × 2 × 1"
        );

        formula.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        VBox conceptCard = createInfoCard(
                "Concept and Mathematical Definition",
                explanation,
                formula
        );

        Label inputLabel = new Label("Enter a number (0 to 20)");

        TextField numberInput = new TextField();
        numberInput.setPromptText("Enter a number");

        Label algorithmLabel = new Label("Choose an algorithm");

        ComboBox<String> algorithmChoice = new ComboBox<>();

        algorithmChoice.getItems().addAll(
                "Recursive",
                "Iterative"
        );

        algorithmChoice.setValue("Recursive");
        algorithmChoice.setMaxWidth(Double.MAX_VALUE);

        Button calculateButton = createActionButton("Run Algorithm");

        Button compareButton = createSecondaryButton(
                "Compare Both Algorithms"
        );

        Label resultLabel = new Label(
                "Your calculated result will appear here."
        );

        resultLabel.setWrapText(true);

        Label comparisonLabel = new Label(
                "Comparison results will appear here."
        );

        comparisonLabel.setWrapText(true);

        calculateButton.setOnAction(e -> {

            try {

                int n = Integer.parseInt(numberInput.getText().trim());

                if (n < 0 || n > 20) {
                    resultLabel.setText(
                            "Please enter a number between 0 and 20."
                    );
                    return;
                }

                int[] counter = {0};
                long startTime = System.nanoTime();

                long result;

                String selected = algorithmChoice.getValue();

                if (selected.equals("Recursive")) {
                    result = Factorial.factorialRecursive(n, counter);
                } else {
                    result = Factorial.factorialIterative(n, counter);
                }

                long endTime = System.nanoTime();

                double runtime = (endTime - startTime) / 1_000_000.0;

                resultLabel.setText(
                        "Algorithm: " + selected + "\n" +
                        "Input: " + n + "\n" +
                        "Factorial Result: " + result + "\n" +
                        "Operations: " + counter[0] + "\n" +
                        "Runtime: " + String.format("%.6f", runtime) + " ms"
                );

            } catch (NumberFormatException ex) {

                resultLabel.setText(
                        "Please enter a valid whole number."
                );
            }
        });

        compareButton.setOnAction(e -> {

            try {

                int n = Integer.parseInt(numberInput.getText().trim());

                if (n < 0 || n > 20) {
                    comparisonLabel.setText(
                            "Please enter a number between 0 and 20."
                    );
                    return;
                }

                int[] recursiveCounter = {0};

                long start1 = System.nanoTime();

                long recursiveResult = Factorial.factorialRecursive(
                        n, recursiveCounter
                );

                long end1 = System.nanoTime();

                double recursiveTime = (end1 - start1) / 1_000_000.0;

                int[] iterativeCounter = {0};

                long start2 = System.nanoTime();

                long iterativeResult = Factorial.factorialIterative(
                        n, iterativeCounter
                );

                long end2 = System.nanoTime();

                double iterativeTime = (end2 - start2) / 1_000_000.0;

                comparisonLabel.setText(
                        "Input: " + n + "\n\n" +

                        "RECURSIVE\n" +
                        "Result: " + recursiveResult + "\n" +
                        "Operations: " + recursiveCounter[0] + "\n" +
                        "Runtime: " + String.format("%.6f", recursiveTime) + " ms\n\n" +

                        "ITERATIVE\n" +
                        "Result: " + iterativeResult + "\n" +
                        "Operations: " + iterativeCounter[0] + "\n" +
                        "Runtime: " + String.format("%.6f", iterativeTime) + " ms"
                );

            } catch (NumberFormatException ex) {

                comparisonLabel.setText(
                        "Please enter a valid whole number."
                );
            }
        });

        Label complexity = new Label(
                "Recursive: O(n) time\n\n" +
                "Iterative: O(n) time"
        );

        complexity.setWrapText(true);

        content.getChildren().addAll(
                title,
                conceptCard,
                createInfoCard(
                        "Run an Experiment",
                        inputLabel,
                        numberInput,
                        algorithmLabel,
                        algorithmChoice,
                        new HBox(10, calculateButton, compareButton)
                ),
                createInfoCard("Execution Results", resultLabel),
                createInfoCard("Performance Comparison", comparisonLabel),
                createInfoCard("Time and Space Complexity", complexity)
        );

        return content;
    }

    // TOWER OF HANOI INTERFACE
    private VBox createHanoiInterface() {

        VBox content = new VBox(18);
        content.setPadding(new Insets(22));

        Label title = createSectionTitle("Tower of Hanoi");

        Label explanation = new Label(
                "Tower of Hanoi is a mathematical puzzle consisting of three pegs " +
                "and disks of different sizes. The objective is to move all disks " +
                "from the source peg to the destination peg."
        );

        explanation.setWrapText(true);

        Label formula = new Label(
                "T(n) = 2T(n - 1) + 1\n\n" +
                "Minimum number of moves = 2^n - 1"
        );

        formula.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        VBox conceptCard = createInfoCard(
                "Concept and Mathematical Definition",
                explanation,
                formula
        );

        // PUZZLE SETTINGS
        Label diskLabel = new Label("Number of disks");

        ComboBox<Integer> diskChoice = new ComboBox<>();

        diskChoice.getItems().addAll(
                1, 2, 3, 4, 5, 6, 7, 8
        );

        diskChoice.setValue(3);

        Label speedLabel = new Label("Animation speed");

        Slider speedSlider = new Slider(1, 5, 3);

        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setSnapToTicks(true);

        Label minimumMoves = new Label(
                "Minimum moves required: 7"
        );

        Label moveCounter = new Label(
                "Moves completed: 0"
        );

        VBox settingsCard = createInfoCard(
                "Puzzle Settings",
                diskLabel,
                diskChoice,
                speedLabel,
                speedSlider,
                minimumMoves
        );

        // THREE PEG CONTAINERS
        HBox pegs = new HBox(35);

        pegs.setAlignment(Pos.BOTTOM_CENTER);
        pegs.setPadding(new Insets(25));
        pegs.setMinHeight(260);

        // SOURCE PEG
        VBox sourcePeg = new VBox(5);
        sourcePeg.setAlignment(Pos.BOTTOM_CENTER);

        Label sourcePole = new Label("│");
        sourcePole.setStyle(
                "-fx-font-size: 100px;" +
                "-fx-text-fill: #888888;"
        );

        VBox sourceDisks = new VBox(3);
        sourceDisks.setAlignment(Pos.BOTTOM_CENTER);
        sourceDisks.setMinHeight(130);

        Label sourceBase = new Label("━━━━━━━━");
        sourceBase.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label sourceName = new Label("SOURCE PEG");
        sourceName.setStyle("-fx-font-weight: bold;");

        sourcePeg.getChildren().addAll(
                sourcePole,
                sourceDisks,
                sourceBase,
                sourceName
        );

        // HELPER PEG
        VBox helperPeg = new VBox(5);
        helperPeg.setAlignment(Pos.BOTTOM_CENTER);

        Label helperPole = new Label("│");
        helperPole.setStyle(
                "-fx-font-size: 100px;" +
                "-fx-text-fill: #888888;"
        );

        VBox helperDisks = new VBox(3);
        helperDisks.setAlignment(Pos.BOTTOM_CENTER);
        helperDisks.setMinHeight(130);

        Label helperBase = new Label("━━━━━━━━");
        helperBase.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label helperName = new Label("HELPER PEG");
        helperName.setStyle("-fx-font-weight: bold;");

        helperPeg.getChildren().addAll(
                helperPole,
                helperDisks,
                helperBase,
                helperName
        );

        // DESTINATION PEG
        VBox destinationPeg = new VBox(5);
        destinationPeg.setAlignment(Pos.BOTTOM_CENTER);

        Label destinationPole = new Label("│");
        destinationPole.setStyle(
                "-fx-font-size: 100px;" +
                "-fx-text-fill: #888888;"
        );

        VBox destinationDisks = new VBox(3);
        destinationDisks.setAlignment(Pos.BOTTOM_CENTER);
        destinationDisks.setMinHeight(130);

        Label destinationBase = new Label("━━━━━━━━");
        destinationBase.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label destinationName = new Label("DESTINATION PEG");
        destinationName.setStyle("-fx-font-weight: bold;");

        destinationPeg.getChildren().addAll(
                destinationPole,
                destinationDisks,
                destinationBase,
                destinationName
        );

        HBox.setHgrow(sourcePeg, Priority.ALWAYS);
        HBox.setHgrow(helperPeg, Priority.ALWAYS);
        HBox.setHgrow(destinationPeg, Priority.ALWAYS);

        pegs.getChildren().addAll(
                sourcePeg,
                helperPeg,
                destinationPeg
        );

        VBox visualCard = createInfoCard(
                "Puzzle Visualisation",
                pegs
        );

        // MOVE STORAGE
        String[] moves = new String[255];

        int[] moveCount = {0};
        int[] currentMove = {0};
        int[] completedMoves = {0};

        // GENERATE THE MOVES
        Runnable generateMoves = () -> {

            moveCount[0] = 0;
            currentMove[0] = 0;

            Arrays.fill(moves, null);

            TowerofHanoi.solve(
                    diskChoice.getValue(),
                    "SOURCE",
                    "HELPER",
                    "DESTINATION",
                    moves,
                    moveCount
            );
        };

        // CREATE THE INITIAL DISKS
        Runnable updateDisks = () -> {

            sourceDisks.getChildren().clear();
            helperDisks.getChildren().clear();
            destinationDisks.getChildren().clear();

            int numberOfDisks = diskChoice.getValue();

            // Smallest disk is placed at the top.
            // Largest disk is placed at the bottom.
            for (int disk = 1; disk <= numberOfDisks; disk++) {

                double diskWidth = 40 + (disk * 15);

                Rectangle diskShape = new Rectangle(
                        diskWidth,
                        16
                );

                diskShape.setArcWidth(8);
                diskShape.setArcHeight(8);

                diskShape.setStyle(
                        "-fx-fill: " + BURGUNDY + ";"
                );

                sourceDisks.getChildren().add(diskShape);
            }
        };

        // INITIALISE THE PUZZLE
        generateMoves.run();
        updateDisks.run();

        // BUTTONS
        Button startButton = createActionButton("Start");
        Button pauseButton = createSecondaryButton("Pause");
        Button nextButton = createSecondaryButton("Next Move");
        Button resetButton = createSecondaryButton("Reset");

        Label status = new Label(
                "Press Start to begin the puzzle."
        );

        status.setWrapText(true);

        // AUTOMATIC ANIMATION
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // PERFORM ONE MOVE
        Runnable moveNext = () -> {

            if (currentMove[0] >= moveCount[0]) {

                timeline.stop();
                status.setText("Puzzle completed!");
                return;
            }

            String instruction = moves[currentMove[0]];

            String[] parts = instruction.split(" from | to ");

            String source = parts[1];
            String destination = parts[2];

            VBox fromPeg = null;
            VBox toPeg = null;

            // IDENTIFY THE SOURCE PEG
            if (source.equals("SOURCE")) {
                fromPeg = sourceDisks;
            } else if (source.equals("HELPER")) {
                fromPeg = helperDisks;
            } else if (source.equals("DESTINATION")) {
                fromPeg = destinationDisks;
            }

            // IDENTIFY THE DESTINATION PEG
            if (destination.equals("SOURCE")) {
                toPeg = sourceDisks;
            } else if (destination.equals("HELPER")) {
                toPeg = helperDisks;
            } else if (destination.equals("DESTINATION")) {
                toPeg = destinationDisks;
            }

            // MOVE THE TOP DISK
            if (fromPeg != null && toPeg != null
                    && !fromPeg.getChildren().isEmpty()) {

                Rectangle disk = (Rectangle)
                        fromPeg.getChildren().remove(0);

                toPeg.getChildren().add(0, disk);

                currentMove[0]++;
                completedMoves[0]++;

                moveCounter.setText(
                        "Moves completed: " + completedMoves[0]
                );

                status.setText(instruction);
            }

            // CHECK WHETHER THE PUZZLE IS FINISHED
            if (currentMove[0] >= moveCount[0]) {

                timeline.stop();
                status.setText("Puzzle completed!");
            }
        };

        // SET ANIMATION SPEED
        Runnable updateAnimationSpeed = () -> {

            double delay = 1200 - (speedSlider.getValue() * 200);

            timeline.getKeyFrames().setAll(
                    new KeyFrame(
                            Duration.millis(delay),
                            e -> moveNext.run()
                    )
            );
        };

        updateAnimationSpeed.run();

        // START BUTTON
        startButton.setOnAction(e -> {

            if (currentMove[0] >= moveCount[0]) {

                status.setText(
                        "Puzzle completed. Press Reset to play again."
                );

                return;
            }

            status.setText("Animation running...");

            timeline.play();
        });

        // PAUSE BUTTON
        pauseButton.setOnAction(e -> {

            timeline.pause();

            status.setText("Animation paused.");
        });

        // NEXT MOVE BUTTON
        nextButton.setOnAction(e -> {

            timeline.pause();

            moveNext.run();
        });

        // RESET BUTTON
        resetButton.setOnAction(e -> {

            timeline.stop();

            generateMoves.run();

            completedMoves[0] = 0;

            updateDisks.run();

            moveCounter.setText("Moves completed: 0");

            status.setText("Puzzle reset. Press Start to begin again.");
        });

        // CHANGE NUMBER OF DISKS
        diskChoice.setOnAction(e -> {

            timeline.stop();

            generateMoves.run();

            completedMoves[0] = 0;

            updateDisks.run();

            minimumMoves.setText(
                    "Minimum moves required: " +
                    ((1 << diskChoice.getValue()) - 1)
            );

            moveCounter.setText("Moves completed: 0");

            status.setText(
                    "Puzzle updated. Press Start to begin."
            );
        });

        // CHANGE ANIMATION SPEED
        speedSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> {

                    boolean wasRunning =
                            timeline.getStatus() == Timeline.Status.RUNNING;

                    timeline.stop();

                    updateAnimationSpeed.run();

                    if (wasRunning && currentMove[0] < moveCount[0]) {
                        timeline.play();
                    }
                }
        );

        VBox animationCard = createInfoCard(
                "Animation Controls",
                new HBox(10, startButton, pauseButton),
                new HBox(10, nextButton, resetButton),
                moveCounter,
                status
        );

        Label complexity = new Label(
                "Time Complexity: O(2^n)\n\n" +
                "Space Complexity: O(n)"
        );

        complexity.setWrapText(true);

        content.getChildren().addAll(
                title,
                conceptCard,
                settingsCard,
                visualCard,
                animationCard,
                createInfoCard(
                        "Time and Space Complexity",
                        complexity
                )
        );

        return content;
    }

    // REUSABLE INFORMATION CARD
    private VBox createInfoCard(
            String title,
            Node... components
    ) {

        Label heading = new Label(title);

        heading.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        VBox card = new VBox(13);

        card.setPadding(new Insets(20));
        card.setMaxWidth(Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: #E2E2E2;" +
                "-fx-border-radius: 9;"
        );

        card.getChildren().add(heading);
        card.getChildren().addAll(components);

        return card;
    }

    // SECTION TITLE
    private Label createSectionTitle(String text) {

        Label label = new Label(text);

        label.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        return label;
    }

    // PRIMARY BUTTON
    private Button createActionButton(String text) {

        Button button = new Button(text);

        button.setPadding(new Insets(11, 20, 11, 20));

        button.setStyle(
                "-fx-background-color: " + BURGUNDY + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // SECONDARY BUTTON
    private Button createSecondaryButton(String text) {

        Button button = new Button(text);

        button.setPadding(new Insets(11, 20, 11, 20));

        button.setStyle(
                "-fx-background-color: #EEEEEE;" +
                "-fx-text-fill: " + BURGUNDY + ";" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-border-color: #DDDDDD;" +
                "-fx-border-radius: 6;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // TIME COMPLEXITY ANALYSIS PAGE
    private VBox createComplexityPage() {

        VBox page = new VBox(20);
        page.setPadding(new Insets(35));
        page.setStyle("-fx-background-color: " + LIGHT_GREY + ";");

        Label heading = new Label("Time Complexity Analysis");
        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label description = new Label(
                "Benchmark sorting algorithms with different input sizes and arrangements."
        );
        description.setWrapText(true);

        ComboBox<String> algorithmChoice = new ComboBox<>();
        algorithmChoice.getItems().addAll(
                "All Algorithms",
                "Bubble Sort",
                "Selection Sort",
                "Insertion Sort",
                "Merge Sort",
                "Quick Sort (First Pivot)",
                "Quick Sort (Random Pivot)"
        );
        algorithmChoice.setValue("All Algorithms");
        algorithmChoice.setMaxWidth(Double.MAX_VALUE);

        ComboBox<Integer> sizeChoice = new ComboBox<>();
        sizeChoice.getItems().addAll(
                100, 500, 1_000, 2_000, 5_000, 10_000, 50_000, 100_000
        );
        sizeChoice.setValue(1_000);
        sizeChoice.setMaxWidth(Double.MAX_VALUE);

        ComboBox<String> inputTypeChoice = new ComboBox<>();
        inputTypeChoice.getItems().addAll("Random", "Sorted", "Reverse Sorted");
        inputTypeChoice.setValue("Random");
        inputTypeChoice.setMaxWidth(Double.MAX_VALUE);

        Button runButton = createActionButton("Run Analysis");
        ProgressIndicator progress = new ProgressIndicator();
        progress.setPrefSize(26, 26);
        progress.setVisible(false);
        Label status = new Label("Choose options and run a benchmark.");
        status.setWrapText(true);

        TableView<AnalysisResult> resultsTable = new TableView<>();
        resultsTable.setPlaceholder(new Label("Run an analysis to see results."));
        resultsTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );
        resultsTable.setPrefHeight(230);

        TableColumn<AnalysisResult, String> algorithmColumn =
                new TableColumn<>("Algorithm");
        algorithmColumn.setCellValueFactory(cell ->
                new javafx.beans.property.ReadOnlyStringWrapper(
                        cell.getValue().getAlgorithm()
                ));

        TableColumn<AnalysisResult, String> timeColumn =
                new TableColumn<>("Average Time (ms)");
        timeColumn.setCellValueFactory(cell ->
                new javafx.beans.property.ReadOnlyStringWrapper(
                        String.format("%.3f", cell.getValue().getExecutionTimeMs())
                ));

        TableColumn<AnalysisResult, String> comparisonsColumn =
                new TableColumn<>("Comparisons");
        comparisonsColumn.setCellValueFactory(cell ->
                new javafx.beans.property.ReadOnlyStringWrapper(
                        String.format("%.1f", cell.getValue().getComparisons())
                ));

        TableColumn<AnalysisResult, String> swapsColumn =
                new TableColumn<>("Swaps");
        swapsColumn.setCellValueFactory(cell ->
                new javafx.beans.property.ReadOnlyStringWrapper(
                        String.format("%.1f", cell.getValue().getSwaps())
                ));

        TableColumn<AnalysisResult, String> movesColumn =
                new TableColumn<>("Moves");
        movesColumn.setCellValueFactory(cell ->
                new javafx.beans.property.ReadOnlyStringWrapper(
                        String.format("%.1f", cell.getValue().getMoves())
                ));

        resultsTable.getColumns().addAll(
                algorithmColumn,
                timeColumn,
                comparisonsColumn,
                swapsColumn,
                movesColumn
        );

        NumberAxis quadraticXAxis = new NumberAxis();
        quadraticXAxis.setLabel("Input size");
        NumberAxis quadraticYAxis = new NumberAxis();
        quadraticYAxis.setLabel("Average execution time (ms)");
        LineChart<Number, Number> quadraticChart =
                new LineChart<>(quadraticXAxis, quadraticYAxis);
        quadraticChart.setTitle("O(n²) Algorithms");
        quadraticChart.setAnimated(false);
        quadraticChart.setCreateSymbols(false);
        quadraticChart.setPrefHeight(320);

        NumberAxis efficientXAxis = new NumberAxis();
        efficientXAxis.setLabel("Input size");
        NumberAxis efficientYAxis = new NumberAxis();
        efficientYAxis.setLabel("Average execution time (ms)");
        LineChart<Number, Number> efficientChart =
                new LineChart<>(efficientXAxis, efficientYAxis);
        efficientChart.setTitle("O(n log n) Algorithms");
        efficientChart.setAnimated(false);
        efficientChart.setCreateSymbols(false);
        efficientChart.setPrefHeight(320);

        Map<String, TreeMap<Integer, Double>> chartTimes = new HashMap<>();
        Map<String, XYChart.Series<Number, Number>> chartSeries = new HashMap<>();
        addChartSeries(quadraticChart, chartTimes, chartSeries, "Bubble Sort");
        addChartSeries(quadraticChart, chartTimes, chartSeries, "Selection Sort");
        addChartSeries(quadraticChart, chartTimes, chartSeries, "Insertion Sort");
        addChartSeries(efficientChart, chartTimes, chartSeries, "Merge Sort");
        addChartSeries(efficientChart, chartTimes, chartSeries, "Quick Sort (First Pivot)");
        addChartSeries(efficientChart, chartTimes, chartSeries, "Quick Sort (Random Pivot)");

        String[] chartInputType = {null};
        runButton.setOnAction(event -> {
            int size = sizeChoice.getValue();
            String inputType = inputTypeChoice.getValue();
            String selectedAlgorithm = algorithmChoice.getValue();

            if (size > 10_000
                    && ("Bubble Sort".equals(selectedAlgorithm)
                    || "Selection Sort".equals(selectedAlgorithm)
                    || "Insertion Sort".equals(selectedAlgorithm))) {
                showAnalysisWarning(
                        "Quadratic algorithms are limited to input sizes of 10,000 or less."
                );
                return;
            }

            boolean orderedInput = !"Random".equals(inputType);
            if (size > 10_000 && orderedInput
                    && "Quick Sort (First Pivot)".equals(selectedAlgorithm)) {
                showAnalysisWarning(
                        "First-pivot quicksort can take quadratic time on sorted or reverse-sorted data. " +
                        "Choose Merge Sort or Quick Sort (Random Pivot) for this benchmark."
                );
                return;
            }

            runButton.setDisable(true);
            progress.setVisible(true);
            status.setText("Running three repetitions in the background...");

            Task<List<AnalysisResult>> analysisTask = new Task<>() {
                @Override
                protected List<AnalysisResult> call() {
                    if (size <= 10_000) {
                        return ComplexityAnalyzer.analyzeAll(size, inputType);
                    }
                    return ComplexityAnalyzer.analyzeEfficient(size, inputType);
                }
            };

            analysisTask.setOnSucceeded(done -> {
                List<AnalysisResult> results = analysisTask.getValue();
                if (!inputType.equals(chartInputType[0])) {
                    chartTimes.values().forEach(TreeMap::clear);
                    chartInputType[0] = inputType;
                }

                for (AnalysisResult result : results) {
                    chartTimes.get(result.getAlgorithm()).put(
                            size, result.getExecutionTimeMs()
                    );
                    refreshChartSeries(result.getAlgorithm(), chartTimes, chartSeries);
                }

                resultsTable.getItems().setAll(results.stream()
                        .filter(result -> "All Algorithms".equals(selectedAlgorithm)
                                || selectedAlgorithm.equals(result.getAlgorithm()))
                        .toList());

                String omitted = size > 10_000 && orderedInput
                        ? " Quadratic sorts and first-pivot quicksort are omitted for this input."
                        : size > 10_000
                                ? " Quadratic sorts are omitted above 10,000."
                                : "";
                status.setText(
                        "Completed " + resultsTable.getItems().size() +
                        " displayed benchmark(s) for " + size + " " + inputType +
                        " values. Results are averages of three runs." + omitted
                );
                progress.setVisible(false);
                runButton.setDisable(false);
            });

            analysisTask.setOnFailed(done -> {
                Throwable failure = analysisTask.getException();
                progress.setVisible(false);
                runButton.setDisable(false);
                status.setText("Analysis failed.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Analysis Failed");
                alert.setHeaderText("The sorting benchmark could not be completed.");
                alert.setContentText(failure == null || failure.getMessage() == null
                        ? "An unexpected error occurred."
                        : failure.getMessage());
                alert.showAndWait();
            });

            Thread analysisThread = new Thread(
                    analysisTask, "sorting-complexity-analysis"
            );
            analysisThread.setDaemon(true);
            analysisThread.start();
        });

        GridPane controls = new GridPane();
        controls.setHgap(12);
        controls.setVgap(10);
        controls.add(new Label("Algorithm"), 0, 0);
        controls.add(algorithmChoice, 1, 0);
        controls.add(new Label("Input size"), 2, 0);
        controls.add(sizeChoice, 3, 0);
        controls.add(new Label("Input type"), 4, 0);
        controls.add(inputTypeChoice, 5, 0);
        controls.add(runButton, 6, 0);
        controls.add(progress, 7, 0);
        controls.add(status, 0, 1, 8, 1);
        for (int column = 1; column < 8; column++) {
            ColumnConstraints constraint = new ColumnConstraints();
            constraint.setHgrow(Priority.ALWAYS);
            controls.getColumnConstraints().add(constraint);
        }

        page.getChildren().addAll(
                heading,
                new Separator(),
                description,
                createInfoCard("Benchmark Controls", controls),
                createInfoCard("Results", resultsTable),
                createInfoCard("Performance Charts", quadraticChart, efficientChart)
        );
        return page;
    }

    private void addChartSeries(
            LineChart<Number, Number> chart,
            Map<String, TreeMap<Integer, Double>> chartTimes,
            Map<String, XYChart.Series<Number, Number>> chartSeries,
            String algorithm
    ) {
        TreeMap<Integer, Double> times = new TreeMap<>();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(algorithm);
        chartTimes.put(algorithm, times);
        chartSeries.put(algorithm, series);
        chart.getData().add(series);
    }

    private void refreshChartSeries(
            String algorithm,
            Map<String, TreeMap<Integer, Double>> chartTimes,
            Map<String, XYChart.Series<Number, Number>> chartSeries
    ) {
        XYChart.Series<Number, Number> series = chartSeries.get(algorithm);
        series.getData().setAll(chartTimes.get(algorithm).entrySet().stream()
                .map(entry -> new XYChart.Data<Number, Number>(
                        entry.getKey(), entry.getValue()
                ))
                .toList());
    }

    private void showAnalysisWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.setTitle("Unsupported Benchmark");
        alert.setHeaderText("Choose a safer algorithm and input size.");
        alert.showAndWait();
    }

    // OTHER MODULE PAGES
    private VBox createModulePage(
            String title,
            String description
    ) {

        VBox page = new VBox(20);

        page.setPadding(new Insets(35));

        page.setStyle(
                "-fx-background-color: " + LIGHT_GREY + ";"
        );

        Label heading = createSectionTitle(title);

        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label details = new Label(description);
        details.setWrapText(true);

        page.getChildren().addAll(
                heading,
                details
        );

        return page;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
