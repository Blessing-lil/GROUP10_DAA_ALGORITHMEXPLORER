
package com.algolab;

import java.util.Arrays;
import java.util.ArrayList;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import com.algolab.sorting.visualizer.SortingEngine;
import com.algolab.sorting.visualizer.SortingStep;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

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
        sidebar.setStyle(
        "-fx-background-color: linear-gradient(to bottom, #351722, #542638, #3D202D);" +
        "-fx-border-color: rgba(255,255,255,0.12);" +
        "-fx-border-width: 0 1 0 0;"
);
        Label logo = new Label("ALGORITHM\nEXPLORER");
logo.setStyle(
        "-fx-text-fill: #FFFFFF;" +
        "-fx-font-size: 24px;" +
        "-fx-font-weight: bold;" +
        "-fx-line-spacing: 5px;"
);

        Label menuLabel = new Label("MAIN MENU");
        menuLabel.setStyle(
        "-fx-text-fill: #D6A6A0;" +
        "-fx-font-size: 10px;" +
        "-fx-font-weight: bold;" +
        "-fx-letter-spacing: 2px;"
);

        Button dashboardButton = createNavButton("Dashboard");
        Button recursionButton = createNavButton("Recursion Lab");
        Button dataButton = createNavButton("Data Structures Lab");
        Button sortingButton = createNavButton("Sorting Lab");
        Button analysisButton = createNavButton("Time Complexity Analysis");
        Button[] navButtons = {
        dashboardButton,
        recursionButton,
        dataButton,
        sortingButton,
        analysisButton
};

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

        showPage(root, createDashboard(root));
        setActiveNavButton(dashboardButton, navButtons);

        // NAVIGATION
        // NAVIGATION
dashboardButton.setOnAction(e -> {
    showPage(root, createDashboard(root));
    setActiveNavButton(dashboardButton, navButtons);
});

recursionButton.setOnAction(e -> {
    showPage(root, createRecursionPage());
    setActiveNavButton(recursionButton, navButtons);
});

dataButton.setOnAction(e -> {
    showPage(root, createDataStructuresPage(root));
    setActiveNavButton(dataButton, navButtons);
});

sortingButton.setOnAction(e -> {
    showPage(root, createSortingPage(root));
    setActiveNavButton(sortingButton, navButtons);
});

analysisButton.setOnAction(e -> {
    showPage(root, createComplexityPage());
    setActiveNavButton(analysisButton, navButtons);
});
        Scene scene = new Scene(root, 1200, 780);

        scene.getStylesheets().add(
        getClass().getResource("/styles/algolab.css").toExternalForm()
);

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
    // SIDEBAR BUTTON
private Button createNavButton(String text) {

    Button button = new Button(text);

    button.setMaxWidth(Double.MAX_VALUE);
    button.setAlignment(Pos.CENTER_LEFT);
    button.setPadding(new Insets(13, 14, 13, 14));

    button.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #F5E9EC;" +
            "-fx-font-size: 13px;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
    );

    button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: rgba(214,166,160,0.22);" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: rgba(214,166,160,0.45);" +
            "-fx-border-radius: 10;" +
            "-fx-cursor: hand;"
    ));

    button.setOnMouseExited(e -> {

    if (Boolean.TRUE.equals(button.getUserData())) {

        button.setStyle(
                "-fx-background-color: linear-gradient(to right, #8A5061, #754252);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: rgba(255,255,255,0.45);" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 1;"
        );

    } else {

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #F5E9EC;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );
    }
});
    return button;
}

// ACTIVE NAVIGATION STYLE
private void setActiveNavButton(
        Button activeButton, Button[] navButtons) {

    for (Button button : navButtons) {
        button.setUserData(button == activeButton);
        if (button == activeButton) {

            button.setStyle(
                    "-fx-background-color: linear-gradient(to right, #8A5061, #754252);" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: rgba(255,255,255,0.45);" +
                    "-fx-border-radius: 10;" +
                    "-fx-border-width: 1;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #F5E9EC;" +
                    "-fx-font-size: 13px;" +
                    "-fx-background-radius: 10;" +
                    "-fx-cursor: hand;"
            );
        }
    }
}

    
// DASHBOARD
private VBox createDashboard(BorderPane root) {

    VBox page = new VBox(25);
    page.setPadding(new Insets(32));
    page.setStyle(
        "-fx-background-color: linear-gradient(to bottom right, #E8DCE0, #DDE3EC, #EEE5D9);"
);
    // WELCOME BANNER
    VBox hero = new VBox(12);
    hero.setPadding(new Insets(30, 32, 30, 32));
    hero.setStyle(
        "-fx-background-color: linear-gradient(to right, #512331, #783C50, #9A6471);" +
        "-fx-background-radius: 22;" +
        "-fx-border-color: rgba(255,255,255,0.55);" +
        "-fx-border-radius: 22;" +
        "-fx-border-width: 1;"
);

hero.setEffect(new DropShadow(
        24,
        Color.rgb(76, 35, 48, 0.22)
));

    Label eyebrow = new Label("ALGORITHM EXPLORER  /  LEARNING STUDIO");
    eyebrow.setStyle(
            "-fx-text-fill: #E8C9C5;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
    );

    Label heading = new Label("Welcome back, Explorer!");
    heading.setStyle(
            "-fx-font-size: 30px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
    );

    Label description = new Label(
            "Discover algorithms, experiment with data structures, " +
            "and understand how code performs."
    );
    description.setWrapText(true);
    description.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #F5E8E8;"
    );

    hero.getChildren().addAll(eyebrow, heading, description);

    // QUICK OVERVIEW
    HBox stats = new HBox(15);
    stats.getChildren().addAll(
            createDashboardStat("04", "LEARNING MODULES", "#642B3A"),
            createDashboardStat("06+", "CORE TOPICS", "#8297B0"),
            createDashboardStat("∞", "WAYS TO EXPLORE", "#78958B")
    );

    // MODULE SECTION
    VBox moduleSection = new VBox(15);

    Label sectionTitle = new Label("Explore your learning space");
    sectionTitle.setStyle(
            "-fx-font-size: 21px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #3F3032;"
    );

    Label sectionDescription = new Label(
            "Choose a module and start experimenting."
    );
    sectionDescription.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #817477;"
    );

    GridPane cards = new GridPane();
    cards.setHgap(18);
    cards.setVgap(18);

    cards.getColumnConstraints().addAll(
            columnConstraint(),
            columnConstraint()
    );

    VBox recursionCard = createDashboardCard(
            "↻", "Recursion Lab",
            "Explore Fibonacci, Factorial and Tower of Hanoi.",
            "#D6A6A0"
    );

    VBox dataCard = createDashboardCard(
            "◇", "Data Structures Lab",
            "Discover stacks, queues, linked lists and balanced brackets.",
            "#8297B0"
    );

    VBox sortingCard = createDashboardCard(
            "↗", "Sorting Lab",
            "Explore and compare different sorting algorithms.",
            "#78958B"
    );

    VBox analysisCard = createDashboardCard(
            "◷", "Time Complexity Analysis",
            "Benchmark algorithms and compare their performance.",
            "#D49A54"
    );

    // PRESERVE EXISTING NAVIGATION
    recursionCard.setOnMouseClicked(e ->
            showPage(root, createRecursionPage()));

    dataCard.setOnMouseClicked(e ->
            showPage(root, createDataStructuresPage(root)));

    sortingCard.setOnMouseClicked(e ->
            showPage(root, createSortingPage(root)));

    analysisCard.setOnMouseClicked(e ->
            showPage(root, createComplexityPage()));

    cards.add(recursionCard, 0, 0);
    cards.add(dataCard, 1, 0);
    cards.add(sortingCard, 0, 1);
    cards.add(analysisCard, 1, 1);

    moduleSection.getChildren().addAll(
            sectionTitle,
            sectionDescription,
            cards
    );

    page.getChildren().addAll(hero, stats, moduleSection);

    return page;
}

// DASHBOARD STATISTICS
private VBox createDashboardStat(
        String value, String caption, String accent) {

    Label number = new Label(value);
    number.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + accent + ";"
    );

    Label label = new Label(caption);
    label.setStyle(
            "-fx-font-size: 10px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #817477;"
    );

    VBox stat = new VBox(6, number, label);
    stat.setPadding(new Insets(15, 20, 15, 20));
    stat.setAlignment(Pos.CENTER_LEFT);
    stat.setMaxWidth(Double.MAX_VALUE);
    stat.setStyle(
        "-fx-background-color: #FFFFFF;" +
        "-fx-background-radius: 16;" +
        "-fx-border-color: #D6A6A0;" +
        "-fx-border-width: 1.5;" +
        "-fx-border-radius: 16;"
);

stat.setEffect(new DropShadow(
        16,
        Color.rgb(65, 40, 50, 0.09)
));

    HBox.setHgrow(stat, Priority.ALWAYS);

    return stat;
}

// DASHBOARD MODULE CARD
private VBox createDashboardCard(
        String symbol,
        String title,
        String description,
        String accent) {

    Label icon = new Label(symbol);
    icon.setStyle(
            "-fx-font-size: 23px;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-color: " + accent + ";" +
            "-fx-background-radius: 12;" +
            "-fx-padding: 10 15 10 15;"
    );

    Label cardTitle = new Label(title);
    cardTitle.setStyle(
            "-fx-font-size: 17px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #4A3036;"
    );

    Label cardDescription = new Label(description);
    cardDescription.setWrapText(true);
    cardDescription.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #817477;" +
            "-fx-line-spacing: 3px;"
    );

    Label openLabel = new Label("Explore module  →");
    openLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + accent + ";"
    );

    VBox card = new VBox(13, icon, cardTitle, cardDescription, openLabel);
    card.setPadding(new Insets(22));
    card.setPrefHeight(190);
    card.setMaxWidth(Double.MAX_VALUE);
    card.setStyle(
        "-fx-background-color: #FFFFFF;" +
        "-fx-background-radius: 19;" +
        "-fx-border-color: " + accent + ";" +
        "-fx-border-width: 2;" +
        "-fx-border-radius: 19;" +
        "-fx-cursor: hand;"
);
card.setEffect(new DropShadow(
        18,
        Color.rgb(80, 35, 50, 0.12)
));

    // HOVER EFFECT
    card.setOnMouseExited(e -> {
    card.setStyle(
            "-fx-background-color: rgba(255,255,255,0.72);" +
            "-fx-background-radius: 19;" +
            "-fx-border-color: rgba(255,255,255,0.95);" +
            "-fx-border-width: 1.4;" +
            "-fx-border-radius: 19;" +
            "-fx-cursor: hand;"
    );

    card.setEffect(new DropShadow(
            18,
            Color.rgb(80, 35, 50, 0.12)
    ));

    card.setTranslateY(0);
});

return card;
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

    // SHARED LAB PAGE STYLING
private void applyLabPageStyle(VBox page) {

    page.getStyleClass().add("lab-page");

    page.setStyle(
            "-fx-background-color: linear-gradient(" +
            "to bottom right, #F7F4F1, #EEE7E8, #E9EDF2);"
    );
}

    // RECURSION LAB
    private VBox createRecursionPage() {

        VBox page = new VBox(18);
page.setPadding(new Insets(28));
applyLabPageStyle(page);
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

        card.getStyleClass().add("lab-panel");
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
applyLabPageStyle(page);
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

    // DATA STRUCTURES LAB MENU
    private VBox createDataStructuresPage(BorderPane root) {

        VBox page = new VBox(22);
page.setPadding(new Insets(35));
applyLabPageStyle(page);
        Label heading = new Label("Data Structures Lab");
        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BURGUNDY + ";"
        );

        Label description = new Label(
                "Explore, operate and visualise different data structures."
        );
        description.setWrapText(true);

        GridPane modules = new GridPane();
        modules.setHgap(20);
        modules.setVgap(20);

        modules.getColumnConstraints().addAll(
                columnConstraint(),
                columnConstraint()
        );

        String[] names = {
                "Stack",
                "Linear Queue",
                "Circular Queue",
                "Singly Linked List",
                "Doubly Linked List",
                "Balanced Brackets"
        };

        String[] descriptions = {
                "Explore LIFO operations: Push, Pop and Peek.",
                "Explore FIFO operations: Enqueue and Dequeue.",
                "Explore circular queue operations.",
                "Explore nodes connected in one direction.",
                "Explore nodes connected in both directions.",
                "Check whether brackets are balanced."
        };

        for (int i = 0; i < names.length; i++) {

            final String moduleName = names[i];

            VBox card = createCard(
                    names[i],
                    descriptions[i]
            );

            card.setOnMouseClicked(e ->
                    showPage(root, createModuleLabPage(
                            root, moduleName
                    )));

            modules.add(card, i % 2, i / 2);
        }

        page.getChildren().addAll(
                heading,
                description,
                new Separator(),
                createSectionTitle("Choose a Module"),
                modules
        );

        return page;
    }

    // INDIVIDUAL MODULE PAGE
    
        private VBox createModuleLabPage(BorderPane root, String moduleName) {

        VBox page = new VBox(20);
page.setPadding(new Insets(30));
applyLabPageStyle(page);

        Button backButton = createSecondaryButton("← Back to Data Structures");
        backButton.setOnAction(e ->
                showPage(root, createDataStructuresPage(root)));

        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.CENTER_RIGHT);

        Label heading = new Label(moduleName + " Lab");
        heading.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: " + BURGUNDY + ";");

        page.getChildren().addAll(topBar, heading);

        switch (moduleName) {

    case "Stack":
        page.getChildren().add(createStackInterface());
        break;

    case "Linear Queue":
        page.getChildren().add(createLinearQueueInterface());
        break;

    case "Circular Queue":
        page.getChildren().add(createCircularQueueInterface());
        break;

    case "Singly Linked List":
        page.getChildren().add(createSinglyLinkedListInterface());
        break;

    case "Doubly Linked List":
        page.getChildren().add(createDoublyLinkedListInterface());
        break;

    case "Balanced Brackets":
        page.getChildren().add(createBalancedBracketsInterface());
        break;    

    default:
        VBox content = createInfoCard(
                "Module Information",
                new Label("Welcome to the " + moduleName + " module."),
                new Label("This interactive lab will be implemented next.")
        );

        page.getChildren().add(content);
}

        return page;
        }


private VBox createStackInterface() {

    VBox mainContent = new VBox(20);

    ArrayList<Integer> stack = new ArrayList<>();
    int capacity = 10;
    int[] operationCount = {0};

    // INPUT
    Label inputLabel = new Label("Enter an integer:");
    inputLabel.setStyle("-fx-font-weight: bold;");

    TextField inputField = new TextField();
    inputField.setPromptText("Enter a number...");
    inputField.setMaxWidth(250);

    // BUTTONS
    Button pushButton = createActionButton("Push");
    Button popButton = createSecondaryButton("Pop");
    Button peekButton = createSecondaryButton("Peek");
    Button displayButton = createSecondaryButton("Display");
    Button clearButton = createSecondaryButton("Clear");

    HBox buttonRow = new HBox(10);
    buttonRow.setAlignment(Pos.CENTER_LEFT);
    buttonRow.getChildren().addAll(
            pushButton, popButton, peekButton,
            displayButton, clearButton
    );

    // STATUS
    Label statusLabel = new Label("Ready to perform stack operations.");
    statusLabel.setWrapText(true);
    statusLabel.setStyle("-fx-text-fill: " + BURGUNDY + ";");

    // METRICS
    Label operationLabel = new Label("Operations: 0");
    Label timeLabel = new Label("Execution time: 0.000 ms");

    HBox metrics = new HBox(25);
    metrics.getChildren().addAll(operationLabel, timeLabel);

    // OUTPUT
    TextArea outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setWrapText(true);
    outputArea.setPrefRowCount(5);
    outputArea.setPromptText("Operation results will appear here...");

    // VISUAL STACK
    VBox stackVisual = new VBox(8);
    stackVisual.setAlignment(Pos.BOTTOM_CENTER);
    stackVisual.setPadding(new Insets(15));

    Label stackTitle = new Label("STACK VISUALIZATION");
    stackTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    Label capacityLabel = new Label("Capacity: " + capacity);

    VBox stackContainer = new VBox(10);
    stackContainer.setAlignment(Pos.CENTER);
    stackContainer.setPadding(new Insets(15));
    stackContainer.setMinWidth(220);
    stackContainer.getStyleClass().add("lab-panel");

    stackContainer.getChildren().add(stackVisual);

    // REFRESH STACK VISUAL
    Runnable refreshStack = () -> {

        stackVisual.getChildren().clear();

        if (stack.isEmpty()) {

            Label emptyLabel = new Label("Stack is empty");
            emptyLabel.setStyle("-fx-text-fill: #888888;");

            stackVisual.getChildren().add(emptyLabel);

        } else {

            for (int i = stack.size() - 1; i >= 0; i--) {

                Label element = new Label(String.valueOf(stack.get(i)));

                element.setAlignment(Pos.CENTER);
                element.setPrefSize(160, 40);

                element.setStyle(
                        "-fx-background-color: " + BURGUNDY + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;"
                );

                stackVisual.getChildren().add(element);

                if (i == stack.size() - 1) {

                    Label topLabel = new Label("↑ TOP");

                    topLabel.setStyle(
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: " + BURGUNDY + ";"
                    );

                    stackVisual.getChildren().add(0, topLabel);
                }
            }
        }

        capacityLabel.setText(
                "Elements: " + stack.size() + " / " + capacity
        );
    };

    // UPDATE METRICS
    java.util.function.LongConsumer updateMetrics = startTime -> {

        operationCount[0]++;

        double elapsed =
                (System.nanoTime() - startTime) / 1_000_000.0;

        operationLabel.setText(
                "Operations: " + operationCount[0]
        );

        timeLabel.setText(
                String.format("Execution time: %.6f ms", elapsed)
        );
    };

    // PUSH
    pushButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        try {

            int value = Integer.parseInt(inputField.getText().trim());

            if (stack.size() >= capacity) {

                statusLabel.setText(
                        "Stack Overflow! The stack is full."
                );

                outputArea.appendText(
                        "ERROR: Cannot push " + value +
                        ". Stack capacity is " + capacity + ".\n"
                );

            } else {

                stack.add(value);

                statusLabel.setText(
                        "Successfully pushed " + value + "."
                );

                outputArea.appendText(
                        "PUSH: " + value + "\n"
                );

                refreshStack.run();
            }

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                    "Invalid input! Please enter an integer."
            );

        }

        updateMetrics.accept(startTime);
    });

    // POP
    popButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (stack.isEmpty()) {

            statusLabel.setText(
                    "Stack Underflow! There are no elements to pop."
            );

            outputArea.appendText(
                    "ERROR: Cannot pop from an empty stack.\n"
            );

        } else {

            int removed = stack.remove(stack.size() - 1);

            statusLabel.setText(
                    "Successfully popped " + removed + "."
            );

            outputArea.appendText(
                    "POP: Removed " + removed + "\n"
            );

            refreshStack.run();
        }

        updateMetrics.accept(startTime);
    });

    // PEEK
    peekButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (stack.isEmpty()) {

            statusLabel.setText(
                    "Stack is empty. Nothing to peek."
            );

            outputArea.appendText(
                    "ERROR: Cannot peek into an empty stack.\n"
            );

        } else {

            int top = stack.get(stack.size() - 1);

            statusLabel.setText(
                    "Top element is " + top + "."
            );

            outputArea.appendText(
                    "PEEK: " + top + "\n"
            );
        }

        updateMetrics.accept(startTime);
    });

    // DISPLAY
    displayButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (stack.isEmpty()) {

            outputArea.appendText(
                    "Stack is empty.\n"
            );

        } else {

            StringBuilder result = new StringBuilder();

            result.append("Stack (TOP → BOTTOM): ");

            for (int i = stack.size() - 1; i >= 0; i--) {

                result.append(stack.get(i));

                if (i > 0) {
                    result.append(" → ");
                }
            }

            result.append("\n");

            outputArea.appendText(result.toString());

            statusLabel.setText(
                    "Stack displayed successfully."
            );
        }

        updateMetrics.accept(startTime);
    });

    // CLEAR
    clearButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        stack.clear();

        refreshStack.run();

        statusLabel.setText(
                "Stack cleared successfully."
        );

        outputArea.appendText(
                "CLEAR: Stack emptied.\n"
        );

        updateMetrics.accept(startTime);
    });

    // LAYOUT
    VBox controls = new VBox(15);
    controls.setPadding(new Insets(20));

    controls.getChildren().addAll(
            inputLabel,
            inputField,
            buttonRow,
            statusLabel,
            metrics,
            new Label("Operation History"),
            outputArea
    );

    controls.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    HBox workspace = new HBox(20);
    workspace.setAlignment(Pos.TOP_CENTER);

    HBox.setHgrow(controls, Priority.ALWAYS);

    workspace.getChildren().addAll(
            controls,
            stackContainer
    );

    mainContent.getChildren().addAll(
            workspace,
            capacityLabel,
            stackTitle
    );

    refreshStack.run();

    return mainContent;
}

    
private VBox createLinearQueueInterface() {

    VBox mainContent = new VBox(20);

    ArrayList<Integer> queue = new ArrayList<>();
    int capacity = 10;
    int[] operationCount = {0};

    Label inputLabel = new Label("Enter an integer:");

    TextField inputField = new TextField();
    inputField.setPromptText("Enter a number...");
    inputField.setMaxWidth(250);

    Button enqueueButton = createActionButton("Enqueue");
    Button dequeueButton = createSecondaryButton("Dequeue");
    Button frontButton = createSecondaryButton("Front");
    Button rearButton = createSecondaryButton("Rear");
    Button displayButton = createSecondaryButton("Display");
    Button clearButton = createSecondaryButton("Clear");

    HBox buttons = new HBox(10);
buttons.setSpacing(8);
buttons.setAlignment(Pos.CENTER_LEFT);

buttons.getChildren().addAll(
        enqueueButton, dequeueButton,
        frontButton, rearButton,
        displayButton, clearButton
);      
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            

    Label statusLabel = new Label("Ready to perform queue operations.");
    statusLabel.setWrapText(true);
    statusLabel.setStyle("-fx-text-fill: " + BURGUNDY + ";");

    Label operationLabel = new Label("Operations: 0");
    Label timeLabel = new Label("Execution time: 0.000 ms");

    HBox metrics = new HBox(25);
    metrics.getChildren().addAll(operationLabel, timeLabel);

    TextArea outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setWrapText(true);
    outputArea.setPrefRowCount(5);
    outputArea.setPromptText("Operation history...");

    VBox queueVisual = new VBox(8);
    queueVisual.setAlignment(Pos.CENTER);
    queueVisual.setPadding(new Insets(15));

    VBox queueContainer = new VBox(10);
    queueContainer.setAlignment(Pos.CENTER);
    queueContainer.setPadding(new Insets(15));
    queueContainer.setMinWidth(250);
    queueContainer.getStyleClass().add("lab-panel");
    Label queueTitle = new Label("QUEUE VISUALIZATION");
    queueTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    Label capacityLabel = new Label("Elements: 0 / " + capacity);

    queueContainer.getChildren().addAll(queueTitle, queueVisual);

    Runnable refreshQueue = () -> {

        queueVisual.getChildren().clear();

        if (queue.isEmpty()) {

            Label empty = new Label("Queue is empty");
            empty.setStyle("-fx-text-fill: #888888;");
            queueVisual.getChildren().add(empty);

        } else {

            Label frontLabel = new Label("FRONT ↓");
            frontLabel.setStyle(
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + BURGUNDY + ";"
            );

            queueVisual.getChildren().add(frontLabel);

            for (int value : queue) {

                Label element = new Label(String.valueOf(value));

                element.setAlignment(Pos.CENTER);
                element.setPrefSize(160, 40);

                element.setStyle(
                        "-fx-background-color: " + BURGUNDY + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;"
                );

                queueVisual.getChildren().add(element);
            }

            Label rearLabel = new Label("↑ REAR");
            rearLabel.setStyle(
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + BURGUNDY + ";"
            );

            queueVisual.getChildren().add(rearLabel);
        }

        capacityLabel.setText(
                "Elements: " + queue.size() + " / " + capacity
        );
    };

    java.util.function.LongConsumer updateMetrics = startTime -> {

        operationCount[0]++;

        double elapsed =
                (System.nanoTime() - startTime) / 1_000_000.0;

        operationLabel.setText(
                "Operations: " + operationCount[0]
        );

        timeLabel.setText(
                String.format("Execution time: %.6f ms", elapsed)
        );
    };

    // ENQUEUE
    enqueueButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        try {

            int value = Integer.parseInt(inputField.getText().trim());

            if (queue.size() >= capacity) {

                statusLabel.setText(
                        "Queue Overflow! The queue is full."
                );

                outputArea.appendText(
                        "ERROR: Cannot enqueue " + value + ".\n"
                );

            } else {

                queue.add(value);

                statusLabel.setText(
                        "Successfully enqueued " + value + "."
                );

                outputArea.appendText(
                        "ENQUEUE: " + value + "\n"
                );

                refreshQueue.run();
            }

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                    "Invalid input! Enter an integer."
            );
        }

        updateMetrics.accept(startTime);
    });

    // DEQUEUE
    dequeueButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            statusLabel.setText(
                    "Queue Underflow! The queue is empty."
            );

            outputArea.appendText(
                    "ERROR: Cannot dequeue from an empty queue.\n"
            );

        } else {

            int removed = queue.remove(0);

            statusLabel.setText(
                    "Successfully dequeued " + removed + "."
            );

            outputArea.appendText(
                    "DEQUEUE: Removed " + removed + "\n"
            );

            refreshQueue.run();
        }

        updateMetrics.accept(startTime);
    });

    // FRONT
    frontButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            statusLabel.setText("Queue is empty.");

        } else {

            int value = queue.get(0);

            statusLabel.setText("Front element: " + value);

            outputArea.appendText("FRONT: " + value + "\n");
        }

        updateMetrics.accept(startTime);
    });

    // REAR
    rearButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            statusLabel.setText("Queue is empty.");

        } else {

            int value = queue.get(queue.size() - 1);

            statusLabel.setText("Rear element: " + value);

            outputArea.appendText("REAR: " + value + "\n");
        }

        updateMetrics.accept(startTime);
    });

    // DISPLAY
    displayButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            outputArea.appendText("Queue is empty.\n");

        } else {

            outputArea.appendText(
                    "QUEUE (FRONT → REAR): " + queue + "\n"
            );

            statusLabel.setText("Queue displayed successfully.");
        }

        updateMetrics.accept(startTime);
    });

    // CLEAR
    clearButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        queue.clear();

        refreshQueue.run();

        statusLabel.setText("Queue cleared successfully.");

        outputArea.appendText("CLEAR: Queue emptied.\n");

        updateMetrics.accept(startTime);
    });

    VBox controls = new VBox(15);
    controls.setPadding(new Insets(20));

    controls.getChildren().addAll(
            inputLabel,
            inputField,
            buttons,
            statusLabel,
            metrics,
            new Label("Operation History"),
            outputArea
    );

    controls.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    HBox workspace = new HBox(20);
    workspace.setAlignment(Pos.TOP_CENTER);
    HBox.setHgrow(controls, Priority.ALWAYS);

    workspace.getChildren().addAll(
            controls,
            queueContainer
    );

    mainContent.getChildren().addAll(
            workspace,
            capacityLabel
    );

    refreshQueue.run();

    return mainContent;
}

    
private VBox createCircularQueueInterface() {

    VBox mainContent = new VBox(20);
    mainContent.setPadding(new Insets(10));

    final int capacity = 10;

com.algolab.datastructures.CircularQueue queue =
        new com.algolab.datastructures.CircularQueue(capacity);

int[] operationCount = {0};

    // INPUT
    Label inputLabel = new Label("Enter an integer:");

    TextField inputField = new TextField();
    inputField.setPromptText("Enter a number...");
    inputField.setMaxWidth(250);

    // BUTTONS
    Button enqueueButton = createActionButton("Enqueue");
    Button dequeueButton = createSecondaryButton("Dequeue");
    Button frontButton = createSecondaryButton("Front");
    Button rearButton = createSecondaryButton("Rear");
    Button displayButton = createSecondaryButton("Display");
    Button clearButton = createSecondaryButton("Clear");

    HBox firstRow = new HBox(10);
    firstRow.setAlignment(Pos.CENTER_LEFT);
    firstRow.getChildren().addAll(enqueueButton, dequeueButton, frontButton);

    HBox secondRow = new HBox(10);
    secondRow.setAlignment(Pos.CENTER_LEFT);
    secondRow.getChildren().addAll(rearButton, displayButton, clearButton);

    Label statusLabel = new Label("Ready to perform circular queue operations.");
    statusLabel.setWrapText(true);
    statusLabel.setStyle("-fx-text-fill: " + BURGUNDY + ";");

    Label operationLabel = new Label("Operations: 0");
    Label timeLabel = new Label("Execution time: 0.000 ms");

    HBox metrics = new HBox(25);
    metrics.getChildren().addAll(operationLabel, timeLabel);

    TextArea outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setWrapText(true);
    outputArea.setPrefRowCount(5);
    outputArea.setPromptText("Operation history...");

    // CIRCULAR QUEUE VISUALIZATION
    Label visualTitle = new Label("CIRCULAR QUEUE VISUALIZATION");

    visualTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    HBox queueVisual = new HBox(8);
    queueVisual.setAlignment(Pos.CENTER);
    queueVisual.setPadding(new Insets(15));

    queueVisual.getStyleClass().add("lab-panel");
    Label positionLabel = new Label("Elements: 0 / " + capacity);

    Label pointerLabel = new Label("Front: -     Rear: -");

    // REFRESH VISUALIZATION
    
    // REFRESH VISUALIZATION
    Runnable refreshQueue = () -> {

        queueVisual.getChildren().clear();

        for (int i = 0; i < capacity; i++) {

            VBox slot = new VBox(5);
            slot.setAlignment(Pos.CENTER);
            slot.setPadding(new Insets(8));
            slot.setMinWidth(55);
            slot.setPrefWidth(60);

            Label indexLabel = new Label("Index " + i);

            indexLabel.setStyle(
                    "-fx-font-size: 10px;" +
                    "-fx-text-fill: #777777;"
            );

            boolean occupied = queue.isOccupied(i);

            Label valueLabel = new Label(
                    occupied
                            ? String.valueOf(queue.getValueAtIndex(i))
                            : "-"
            );

            valueLabel.setStyle(
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + BURGUNDY + ";"
            );

            Label pointer = new Label("");

            if (!queue.isEmpty()
                    && i == queue.getFrontIndex()
                    && i == queue.getRearIndex()) {

                pointer.setText("F/R");

            } else if (!queue.isEmpty()
                    && i == queue.getFrontIndex()) {

                pointer.setText("FRONT");

            } else if (!queue.isEmpty()
                    && i == queue.getRearIndex()) {

                pointer.setText("REAR");
            }

            pointer.setStyle(
                    "-fx-font-size: 9px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + BURGUNDY + ";"
            );

            slot.getChildren().addAll(
                    indexLabel,
                    valueLabel,
                    pointer
            );

            slot.setStyle(
                    "-fx-background-color: " +
                    (occupied ? "#F0DDDD" : "#F4F4F4") +
                    ";" +
                    "-fx-border-color: " + BURGUNDY + ";" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;"
            );

            queueVisual.getChildren().add(slot);
        }

        positionLabel.setText(
                "Elements: " + queue.size() + " / " + capacity
        );

        if (queue.isEmpty()) {

            pointerLabel.setText("Front: -     Rear: -");

        } else {

            pointerLabel.setText(
                    "Front: " + queue.getFrontIndex()
                    + "     Rear: " + queue.getRearIndex()
            );
        }
    };


    // UPDATE METRICS
    java.util.function.LongConsumer updateMetrics = startTime -> {

        operationCount[0]++;

        double elapsed =
                (System.nanoTime() - startTime) / 1_000_000.0;

        operationLabel.setText(
                "Operations: " + operationCount[0]
        );

        timeLabel.setText(
                String.format("Execution time: %.6f ms", elapsed)
        );
    };

    // ENQUEUE
    
    // ENQUEUE
    enqueueButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        try {

            int value = Integer.parseInt(inputField.getText().trim());

            if (queue.isFull()) {

                statusLabel.setText(
                        "Queue Overflow! The circular queue is full."
                );

                outputArea.appendText(
                        "ERROR: Cannot enqueue " + value + ". Queue is full.\n"
                );

            } else {

                queue.enqueue(value);

                statusLabel.setText(
                        "Successfully enqueued " + value + "."
                );

                outputArea.appendText(
                        "ENQUEUE: " + value +
                        " inserted at index " + queue.getRearIndex() + "\n"
                );

                refreshQueue.run();
            }

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                    "Invalid input! Please enter an integer."
            );
        }

        updateMetrics.accept(startTime);
    });


    // DEQUEUE
    
    // DEQUEUE
    dequeueButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            statusLabel.setText(
                    "Queue Underflow! The circular queue is empty."
            );

            outputArea.appendText(
                    "ERROR: Cannot dequeue from an empty queue.\n"
            );

        } else {

            int removedIndex = queue.getFrontIndex();
            int removed = queue.dequeue();

            statusLabel.setText(
                    "Successfully dequeued " + removed + "."
            );

            outputArea.appendText(
                    "DEQUEUE: Removed " + removed +
                    " from index " + removedIndex + "\n"
            );

            refreshQueue.run();
        }

        updateMetrics.accept(startTime);
    });


    // FRONT
    
    // FRONT
    frontButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            statusLabel.setText("Circular queue is empty.");

        } else {

            statusLabel.setText(
                    "Front element: " + queue.peek()
            );

            outputArea.appendText(
                    "FRONT: " + queue.peek() + "\n"
            );
        }

        updateMetrics.accept(startTime);
    });

    // REAR
    
    // REAR
    rearButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            statusLabel.setText("Circular queue is empty.");

        } else {

            statusLabel.setText(
                    "Rear element: " + queue.getRearElement()
            );

            outputArea.appendText(
                    "REAR: " + queue.getRearElement() + "\n"
            );
        }

        updateMetrics.accept(startTime);
    });


    // DISPLAY
    
    // DISPLAY
    displayButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (queue.isEmpty()) {

            outputArea.appendText("Circular queue is empty.\n");

        } else {

            StringBuilder result = new StringBuilder();

            result.append("QUEUE (FRONT → REAR): ");

            java.util.List<Integer> elements = queue.getElements();

            for (int i = 0; i < elements.size(); i++) {

                result.append(elements.get(i));

                if (i < elements.size() - 1) {
                    result.append(" → ");
                }
            }

            outputArea.appendText(result + "\n");

            statusLabel.setText(
                    "Circular queue displayed successfully."
            );
        }

        updateMetrics.accept(startTime);
    });

    // CLEAR
    
    // CLEAR
    
    // CLEAR
    clearButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        queue.clear();

        refreshQueue.run();

        statusLabel.setText(
                "Circular queue cleared successfully."
        );

        outputArea.appendText("CLEAR: Circular queue emptied.\n");

        updateMetrics.accept(startTime);
    });

    // CONTROLS
    VBox controls = new VBox(15);
    controls.setPadding(new Insets(20));

    controls.getChildren().addAll(
            inputLabel,
            inputField,
            firstRow,
            secondRow,
            statusLabel,
            metrics,
            new Label("Operation History"),
            outputArea
    );

    controls.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    VBox visualization = new VBox(12);

    visualization.setPadding(new Insets(15));

    visualization.getChildren().addAll(
            visualTitle,
            queueVisual,
            positionLabel,
            pointerLabel
    );

    visualization.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    ScrollPane visualScroll = new ScrollPane(visualization);
    visualScroll.setFitToWidth(true);
    visualScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    visualScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    visualScroll.setPrefHeight(190);

    mainContent.getChildren().addAll(
            controls,
            visualScroll
    );

    refreshQueue.run();

    return mainContent;
}

   
    
private VBox createSinglyLinkedListInterface() {

    VBox mainContent = new VBox(20);
    mainContent.setPadding(new Insets(10));

    // NODE STRUCTURE
    // SINGLY LINKED LIST BACKEND
com.algolab.datastructures.SinglyLinkedList list =
        new com.algolab.datastructures.SinglyLinkedList();

int[] operationCount = {0};
    // INPUT
    Label inputLabel = new Label("Enter an integer:");

    TextField inputField = new TextField();
    inputField.setPromptText("Enter a number...");
    inputField.setMaxWidth(250);

    // BUTTONS
    Button insertButton = createActionButton("Insert");
    Button deleteButton = createSecondaryButton("Delete");
    Button searchButton = createSecondaryButton("Search");
    Button traverseButton = createSecondaryButton("Traverse");
    Button clearButton = createSecondaryButton("Clear");

    HBox firstRow = new HBox(10);
    firstRow.setAlignment(Pos.CENTER_LEFT);
    firstRow.getChildren().addAll(
            insertButton, deleteButton, searchButton
    );

    HBox secondRow = new HBox(10);
    secondRow.setAlignment(Pos.CENTER_LEFT);
    secondRow.getChildren().addAll(
            traverseButton, clearButton
    );

    // STATUS
    Label statusLabel = new Label(
            "Ready to perform linked list operations."
    );

    statusLabel.setWrapText(true);
    statusLabel.setStyle("-fx-text-fill: " + BURGUNDY + ";");

    // METRICS
    Label operationLabel = new Label("Operations: 0");
    Label timeLabel = new Label("Execution time: 0.000 ms");

    HBox metrics = new HBox(25);
    metrics.getChildren().addAll(operationLabel, timeLabel);

    // HISTORY
    TextArea outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setWrapText(true);
    outputArea.setPrefRowCount(5);
    outputArea.setPromptText("Operation history...");

    // VISUALIZATION
    Label visualTitle = new Label("SINGLY LINKED LIST VISUALIZATION");

    visualTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    HBox listVisual = new HBox(8);
    listVisual.setAlignment(Pos.CENTER_LEFT);
    listVisual.setPadding(new Insets(15));

    Label sizeLabel = new Label("Number of nodes: 0");

    ScrollPane visualScroll = new ScrollPane(listVisual);
    visualScroll.setFitToHeight(true);
    visualScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    visualScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    visualScroll.setPrefHeight(130);
    visualScroll.setStyle("-fx-background: white;");

    // REFRESH VISUALIZATION
    
    // REFRESH VISUALIZATION
    Runnable refreshList = () -> {

        listVisual.getChildren().clear();

        java.util.List<Integer> elements = list.getElements();

        if (list.isEmpty()) {

            Label emptyLabel = new Label("HEAD → NULL (Empty list)");

            emptyLabel.setStyle(
                    "-fx-text-fill: #888888;" +
                    "-fx-font-weight: bold;"
            );

            listVisual.getChildren().add(emptyLabel);

        } else {

            for (int i = 0; i < elements.size(); i++) {

                VBox nodeBox = new VBox(5);
                nodeBox.setAlignment(Pos.CENTER);
                nodeBox.setPadding(new Insets(8));
                nodeBox.setMinWidth(75);

                Label nodeTitle = new Label(
                        i == 0 ? "HEAD" : "NODE"
                );

                nodeTitle.setStyle(
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: " + BURGUNDY + ";"
                );

                Label dataLabel = new Label(
                        String.valueOf(elements.get(i))
                );

                dataLabel.setStyle(
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
                );

                Label nextLabel = new Label("NEXT →");

                nextLabel.setStyle(
                        "-fx-font-size: 10px;" +
                        "-fx-text-fill: white;"
                );

                nodeBox.getChildren().addAll(
                        nodeTitle, dataLabel, nextLabel
                );

                nodeBox.setStyle(
                        "-fx-background-color: " + BURGUNDY + ";" +
                        "-fx-background-radius: 6;"
                );

                listVisual.getChildren().add(nodeBox);

                if (i < elements.size() - 1) {

                    Label arrow = new Label("→");

                    arrow.setStyle(
                            "-fx-font-size: 20px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: " + BURGUNDY + ";"
                    );

                    listVisual.getChildren().add(arrow);
                }
            }

            Label nullLabel = new Label("→ NULL");

            nullLabel.setStyle(
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + BURGUNDY + ";"
            );

            listVisual.getChildren().add(nullLabel);
        }

        sizeLabel.setText(
                "Number of nodes: " + list.size()
        );
    };

    // METRICS
    java.util.function.LongConsumer updateMetrics = startTime -> {

        operationCount[0]++;

        double elapsed =
                (System.nanoTime() - startTime) / 1_000_000.0;

        operationLabel.setText(
                "Operations: " + operationCount[0]
        );

        timeLabel.setText(
                String.format("Execution time: %.6f ms", elapsed)
        );
    };

    // INSERT AT END
    // INSERT AT END
insertButton.setOnAction(e -> {

    long startTime = System.nanoTime();

    try {

        int value = Integer.parseInt(inputField.getText().trim());

        list.insert(value);

        statusLabel.setText(
                "Successfully inserted " + value + "."
        );

        outputArea.appendText(
                "INSERT: Added " + value + " at the end.\n"
        );

        refreshList.run();

        inputField.clear();

    } catch (NumberFormatException ex) {

        statusLabel.setText(
                "Invalid input! Please enter an integer."
        );
    }

    updateMetrics.accept(startTime);
});
    // DELETE BY VALUE
    // DELETE BY VALUE
deleteButton.setOnAction(e -> {

    long startTime = System.nanoTime();

    try {

        int value = Integer.parseInt(inputField.getText().trim());

        if (list.isEmpty()) {

            statusLabel.setText(
                    "Cannot delete. The linked list is empty."
            );

            outputArea.appendText(
                    "ERROR: Cannot delete from an empty list.\n"
            );

        } else if (list.delete(value)) {

            statusLabel.setText(
                    "Successfully deleted " + value + "."
            );

            outputArea.appendText(
                    "DELETE: Removed " + value + ".\n"
            );

            refreshList.run();

        } else {

            statusLabel.setText(
                    value + " does not exist in the list."
            );

            outputArea.appendText(
                    "ERROR: " + value + " was not found.\n"
            );
        }

    } catch (NumberFormatException ex) {

        statusLabel.setText(
                "Invalid input! Please enter an integer."
        );
    }

    updateMetrics.accept(startTime);
});
    
    // SEARCH
searchButton.setOnAction(e -> {

    long startTime = System.nanoTime();

    try {

        int value = Integer.parseInt(inputField.getText().trim());

        int position = list.search(value);

        if (position != -1) {

            statusLabel.setText(
                    value + " found at position " + position + "."
            );

            outputArea.appendText(
                    "SEARCH: " + value +
                    " found at position " + position + ".\n"
            );

        } else {

            statusLabel.setText(
                    value + " does not exist in the list."
            );

            outputArea.appendText(
                    "SEARCH: " + value + " not found.\n"
            );
        }

    } catch (NumberFormatException ex) {

        statusLabel.setText(
                "Invalid input! Please enter an integer."
        );
    }

    updateMetrics.accept(startTime);
});
    
    // TRAVERSE
traverseButton.setOnAction(e -> {

    long startTime = System.nanoTime();

    if (list.isEmpty()) {

        statusLabel.setText("The linked list is empty.");

        outputArea.appendText(
                "TRAVERSE: Empty linked list.\n"
        );

    } else {

        String result = list.traverse();

        outputArea.appendText(
                "TRAVERSE: " + result + "\n"
        );

        statusLabel.setText(
                "Linked list traversed successfully."
        );
    }

    updateMetrics.accept(startTime);
});
    
    // CLEAR
clearButton.setOnAction(e -> {

    long startTime = System.nanoTime();

    list.clear();

    refreshList.run();

    statusLabel.setText(
            "Linked list cleared successfully."
    );

    outputArea.appendText(
            "CLEAR: Linked list emptied.\n"
    );

    updateMetrics.accept(startTime);
});

    // CONTROLS
    VBox controls = new VBox(15);
    controls.setPadding(new Insets(20));

    controls.getChildren().addAll(
            inputLabel,
            inputField,
            firstRow,
            secondRow,
            statusLabel,
            metrics,
            new Label("Operation History"),
            outputArea
    );

    controls.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    VBox visualization = new VBox(12);
    visualization.setPadding(new Insets(15));

    visualization.getChildren().addAll(
            visualTitle,
            visualScroll,
            sizeLabel
    );

    visualization.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    mainContent.getChildren().addAll(
            controls,
            visualization
    );

    refreshList.run();

    return mainContent;
}

    
private VBox createDoublyLinkedListInterface() {

    VBox mainContent = new VBox(20);
    mainContent.setPadding(new Insets(10));

    // NODE STRUCTURE
    class Node {
        int data;
        Node previous;
        Node next;

        Node(int data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }
    }

    Node[] head = {null};
    Node[] tail = {null};

    int[] operationCount = {0};

    // INPUT
    Label inputLabel = new Label("Enter an integer:");

    TextField inputField = new TextField();
    inputField.setPromptText("Enter a number...");
    inputField.setMaxWidth(250);

    // BUTTONS
    Button insertButton = createActionButton("Insert");
    Button deleteButton = createSecondaryButton("Delete");
    Button searchButton = createSecondaryButton("Search");
    Button forwardButton = createSecondaryButton("Forward");
    Button backwardButton = createSecondaryButton("Backward");
    Button clearButton = createSecondaryButton("Clear");

    HBox firstRow = new HBox(10);
    firstRow.setAlignment(Pos.CENTER_LEFT);
    firstRow.getChildren().addAll(
            insertButton, deleteButton, searchButton
    );

    HBox secondRow = new HBox(10);
    secondRow.setAlignment(Pos.CENTER_LEFT);
    secondRow.getChildren().addAll(
            forwardButton, backwardButton, clearButton
    );

    // STATUS
    Label statusLabel = new Label(
            "Ready to perform doubly linked list operations."
    );

    statusLabel.setWrapText(true);
    statusLabel.setStyle("-fx-text-fill: " + BURGUNDY + ";");

    // METRICS
    Label operationLabel = new Label("Operations: 0");
    Label timeLabel = new Label("Execution time: 0.000 ms");

    HBox metrics = new HBox(25);
    metrics.getChildren().addAll(operationLabel, timeLabel);

    // HISTORY
    TextArea outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setWrapText(true);
    outputArea.setPrefRowCount(5);
    outputArea.setPromptText("Operation history...");

    // VISUALIZATION
    Label visualTitle = new Label("DOUBLY LINKED LIST VISUALIZATION");

    visualTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    HBox listVisual = new HBox(8);
    listVisual.setAlignment(Pos.CENTER_LEFT);
    listVisual.setPadding(new Insets(15));

    Label sizeLabel = new Label("Number of nodes: 0");

    ScrollPane visualScroll = new ScrollPane(listVisual);
    visualScroll.setFitToHeight(true);
    visualScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    visualScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    visualScroll.setPrefHeight(150);

    // REFRESH VISUALIZATION
    Runnable refreshList = () -> {

        listVisual.getChildren().clear();

        if (head[0] == null) {

            Label emptyLabel = new Label("HEAD → NULL (Empty list)");

            emptyLabel.setStyle(
                    "-fx-text-fill: #888888;" +
                    "-fx-font-weight: bold;"
            );

            listVisual.getChildren().add(emptyLabel);

        } else {

            Node current = head[0];
            int count = 0;

            while (current != null) {

                VBox nodeBox = new VBox(5);
                nodeBox.setAlignment(Pos.CENTER);
                nodeBox.setPadding(new Insets(8));
                nodeBox.setMinWidth(90);

                Label nodeTitle = new Label(
                        count == 0 ? "HEAD" :
                        current.next == null ? "TAIL" : "NODE"
                );

                nodeTitle.setStyle(
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
                );

                Label previousLabel = new Label("PREV");

                Label dataLabel = new Label(
                        String.valueOf(current.data)
                );

                dataLabel.setStyle(
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
                );

                Label nextLabel = new Label("NEXT");

                previousLabel.setStyle(
                        "-fx-font-size: 9px;" +
                        "-fx-text-fill: white;"
                );

                nextLabel.setStyle(
                        "-fx-font-size: 9px;" +
                        "-fx-text-fill: white;"
                );

                nodeBox.getChildren().addAll(
                        nodeTitle,
                        previousLabel,
                        dataLabel,
                        nextLabel
                );

                nodeBox.setStyle(
                        "-fx-background-color: " + BURGUNDY + ";" +
                        "-fx-background-radius: 6;"
                );

                listVisual.getChildren().add(nodeBox);

                if (current.next != null) {

                    Label arrow = new Label("⇄");

                    arrow.setStyle(
                            "-fx-font-size: 20px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: " + BURGUNDY + ";"
                    );

                    listVisual.getChildren().add(arrow);
                }

                current = current.next;
                count++;
            }
        }

        int count = 0;
        Node current = head[0];

        while (current != null) {
            count++;
            current = current.next;
        }

        sizeLabel.setText("Number of nodes: " + count);
    };

    // METRICS
    java.util.function.LongConsumer updateMetrics = startTime -> {

        operationCount[0]++;

        double elapsed =
                (System.nanoTime() - startTime) / 1_000_000.0;

        operationLabel.setText(
                "Operations: " + operationCount[0]
        );

        timeLabel.setText(
                String.format("Execution time: %.6f ms", elapsed)
        );
    };

    // INSERT AT END
    insertButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        try {

            int value = Integer.parseInt(inputField.getText().trim());

            Node newNode = new Node(value);

            if (head[0] == null) {

                head[0] = newNode;
                tail[0] = newNode;

            } else {

                tail[0].next = newNode;
                newNode.previous = tail[0];

                tail[0] = newNode;
            }

            statusLabel.setText(
                    "Successfully inserted " + value + "."
            );

            outputArea.appendText(
                    "INSERT: Added " + value + " at the end.\n"
            );

            refreshList.run();
            inputField.clear();

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                    "Invalid input! Please enter an integer."
            );
        }

        updateMetrics.accept(startTime);
    });

    // DELETE BY VALUE
    deleteButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        try {

            int value = Integer.parseInt(inputField.getText().trim());

            Node current = head[0];

            while (current != null && current.data != value) {
                current = current.next;
            }

            if (current == null) {

                statusLabel.setText(
                        value + " does not exist in the list."
                );

                outputArea.appendText(
                        "DELETE: " + value + " not found.\n"
                );

            } else {

                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    head[0] = current.next;
                }

                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    tail[0] = current.previous;
                }

                statusLabel.setText(
                        "Successfully deleted " + value + "."
                );

                outputArea.appendText(
                        "DELETE: Removed " + value + ".\n"
                );

                refreshList.run();
            }

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                    "Invalid input! Please enter an integer."
            );
        }

        updateMetrics.accept(startTime);
    });

    // SEARCH
    searchButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        try {

            int value = Integer.parseInt(inputField.getText().trim());

            Node current = head[0];
            int position = 0;

            while (current != null && current.data != value) {
                current = current.next;
                position++;
            }

            if (current != null) {

                statusLabel.setText(
                        value + " found at position " + position + "."
                );

                outputArea.appendText(
                        "SEARCH: " + value +
                        " found at position " + position + ".\n"
                );

            } else {

                statusLabel.setText(
                        value + " does not exist in the list."
                );

                outputArea.appendText(
                        "SEARCH: " + value + " not found.\n"
                );
            }

        } catch (NumberFormatException ex) {

            statusLabel.setText(
                    "Invalid input! Please enter an integer."
            );
        }

        updateMetrics.accept(startTime);
    });

    // FORWARD TRAVERSAL
    forwardButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (head[0] == null) {

            statusLabel.setText("The linked list is empty.");

            outputArea.appendText(
                    "FORWARD: Empty linked list.\n"
            );

        } else {

            StringBuilder result = new StringBuilder("HEAD → ");

            Node current = head[0];

            while (current != null) {

                result.append(current.data).append(" ⇄ ");
                current = current.next;
            }

            result.append("NULL");

            outputArea.appendText(
                    "FORWARD: " + result + "\n"
            );

            statusLabel.setText(
                    "Forward traversal completed."
            );
        }

        updateMetrics.accept(startTime);
    });

    // BACKWARD TRAVERSAL
    backwardButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        if (tail[0] == null) {

            statusLabel.setText("The linked list is empty.");

            outputArea.appendText(
                    "BACKWARD: Empty linked list.\n"
            );

        } else {

            StringBuilder result = new StringBuilder("TAIL → ");

            Node current = tail[0];

            while (current != null) {

                result.append(current.data).append(" ⇄ ");
                current = current.previous;
            }

            result.append("NULL");

            outputArea.appendText(
                    "BACKWARD: " + result + "\n"
            );

            statusLabel.setText(
                    "Backward traversal completed."
            );
        }

        updateMetrics.accept(startTime);
    });

    // CLEAR
    clearButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        head[0] = null;
        tail[0] = null;

        refreshList.run();

        statusLabel.setText(
                "Doubly linked list cleared successfully."
        );

        outputArea.appendText(
                "CLEAR: Doubly linked list emptied.\n"
        );

        updateMetrics.accept(startTime);
    });

    // CONTROLS
    VBox controls = new VBox(15);
    controls.setPadding(new Insets(20));

    controls.getChildren().addAll(
            inputLabel,
            inputField,
            firstRow,
            secondRow,
            statusLabel,
            metrics,
            new Label("Operation History"),
            outputArea
    );

    controls.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    VBox visualization = new VBox(12);
    visualization.setPadding(new Insets(15));

    visualization.getChildren().addAll(
            visualTitle,
            visualScroll,
            sizeLabel
    );

    visualization.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    mainContent.getChildren().addAll(
            controls,
            visualization
    );

    refreshList.run();

    return mainContent;
}

    
private VBox createBalancedBracketsInterface() {

    VBox mainContent = new VBox(20);
    mainContent.setPadding(new Insets(10));

    int[] operationCount = {0};

    // INTRODUCTION
    Label explanation = new Label(
            "Enter an expression to check whether its brackets are balanced."
    );

    explanation.setWrapText(true);

    Label examples = new Label(
            "Examples: {[()]}, (a+b), {[(])}, ((()))"
    );

    examples.setStyle(
            "-fx-text-fill: #777777;" +
            "-fx-font-style: italic;"
    );

    // INPUT
    Label inputLabel = new Label("Enter your expression:");

    TextField expressionField = new TextField();
    expressionField.setPromptText("e.g. {[(a+b)*c]}");

    Button checkButton = createActionButton("Check Brackets");
    Button clearButton = createSecondaryButton("Clear");

    HBox buttons = new HBox(10);
    buttons.setAlignment(Pos.CENTER_LEFT);
    buttons.getChildren().addAll(checkButton, clearButton);

    // RESULT
    Label resultLabel = new Label("Waiting for an expression...");

    resultLabel.setWrapText(true);
    resultLabel.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    // METRICS
    Label operationLabel = new Label("Checks performed: 0");
    Label timeLabel = new Label("Execution time: 0.000 ms");

    HBox metrics = new HBox(25);
    metrics.getChildren().addAll(operationLabel, timeLabel);

    // STEP-BY-STEP EXPLANATION
    Label stepsTitle = new Label("Step-by-Step Analysis");

    stepsTitle.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    TextArea stepsArea = new TextArea();
    stepsArea.setEditable(false);
    stepsArea.setWrapText(true);
    stepsArea.setPrefRowCount(9);
    stepsArea.setPromptText(
            "The checking process will appear here..."
    );

    // HISTORY
    Label historyTitle = new Label("Operation History");

    historyTitle.setStyle(
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + BURGUNDY + ";"
    );

    TextArea historyArea = new TextArea();
    historyArea.setEditable(false);
    historyArea.setWrapText(true);
    historyArea.setPrefRowCount(4);
    historyArea.setPromptText("Previous results...");

    // CHECK BRACKETS
    checkButton.setOnAction(e -> {

        long startTime = System.nanoTime();

        String expression = expressionField.getText();

        stepsArea.clear();

        if (expression == null || expression.trim().isEmpty()) {

            resultLabel.setText(
                    "Please enter an expression first."
            );

            stepsArea.setText(
                    "No expression was provided."
            );

            return;
        }

        // STACK USED FOR BRACKET CHECKING
        ArrayList<Character> stack = new ArrayList<>();

        boolean balanced = true;
        String errorMessage = "";

        StringBuilder steps = new StringBuilder();

        steps.append("Expression: ")
                .append(expression)
                .append("\n\n");

        steps.append("Starting with an empty stack.\n\n");

        for (int i = 0; i < expression.length(); i++) {

            char current = expression.charAt(i);

            // OPENING BRACKETS
            if (current == '(' || current == '{' || current == '[') {

                stack.add(current);

                steps.append("Position ")
                        .append(i + 1)
                        .append(": Found opening bracket '")
                        .append(current)
                        .append("'. Push onto stack.\n");

                steps.append("Stack: ")
                        .append(stack)
                        .append("\n\n");
            }

            // CLOSING BRACKETS
            else if (current == ')' || current == '}' || current == ']') {

                if (stack.isEmpty()) {

                    balanced = false;

                    errorMessage =
                            "Closing bracket '" + current +
                            "' at position " + (i + 1) +
                            " has no matching opening bracket.";

                    steps.append("Position ")
                            .append(i + 1)
                            .append(": Found closing bracket '")
                            .append(current)
                            .append("', but the stack is empty.\n");

                    steps.append("ERROR: No matching opening bracket.\n");

                    break;
                }

                char opening = stack.get(stack.size() - 1);

                boolean matches =
                        (opening == '(' && current == ')') ||
                        (opening == '{' && current == '}') ||
                        (opening == '[' && current == ']');

                if (matches) {

                    stack.remove(stack.size() - 1);

                    steps.append("Position ")
                            .append(i + 1)
                            .append(": Found closing bracket '")
                            .append(current)
                            .append("'. It matches '")
                            .append(opening)
                            .append("'. Pop from stack.\n");

                    steps.append("Stack: ")
                            .append(stack)
                            .append("\n\n");

                } else {

                    balanced = false;

                    errorMessage =
                            "Mismatched brackets: '" + opening +
                            "' cannot be closed by '" + current +
                            "' at position " + (i + 1) + ".";

                    steps.append("Position ")
                            .append(i + 1)
                            .append(": Found '")
                            .append(current)
                            .append("', but the top of the stack is '")
                            .append(opening)
                            .append("'.\n");

                    steps.append("ERROR: Brackets do not match.\n");

                    break;
                }
            }

            // IGNORE ORDINARY CHARACTERS
            else {

                steps.append("Position ")
                        .append(i + 1)
                        .append(": '")
                        .append(current)
                        .append("' is not a bracket. Ignore it.\n\n");
            }
        }

        // CHECK FOR UNMATCHED OPENING BRACKETS
        if (balanced && !stack.isEmpty()) {

            balanced = false;

            errorMessage =
                    "Unclosed opening bracket(s) remain: " + stack;

            steps.append("\nThe expression ended, but the stack is not empty.\n");

            steps.append("Unmatched opening brackets: ")
                    .append(stack)
                    .append("\n");
        }

        // FINAL RESULT
        if (balanced) {

            resultLabel.setText(
                    "BALANCED! All brackets match correctly."
            );

            steps.append("\nFINAL RESULT: BALANCED\n");

            steps.append("The stack is empty. All brackets match.");

            historyArea.appendText(
                    "BALANCED: " + expression + "\n"
            );

        } else {

            resultLabel.setText(
                    "NOT BALANCED! " + errorMessage
            );

            steps.append("\nFINAL RESULT: NOT BALANCED\n");

            steps.append(errorMessage);

            historyArea.appendText(
                    "NOT BALANCED: " + expression +
                    "\nReason: " + errorMessage + "\n"
            );
        }

        stepsArea.setText(steps.toString());

        // UPDATE METRICS
        operationCount[0]++;

        double elapsed =
                (System.nanoTime() - startTime) / 1_000_000.0;

        operationLabel.setText(
                "Checks performed: " + operationCount[0]
        );

        timeLabel.setText(
                String.format("Execution time: %.6f ms", elapsed)
        );
    });

    // CLEAR
    clearButton.setOnAction(e -> {

        expressionField.clear();
        stepsArea.clear();

        resultLabel.setText(
                "Waiting for an expression..."
        );

        stepsArea.setPromptText(
                "The checking process will appear here..."
        );
    });

    // INPUT CARD
    VBox inputCard = new VBox(12);

    inputCard.setPadding(new Insets(20));

    inputCard.getChildren().addAll(
            explanation,
            examples,
            inputLabel,
            expressionField,
            buttons,
            resultLabel,
            metrics
    );

    inputCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    // ANALYSIS CARD
    VBox analysisCard = new VBox(12);

    analysisCard.setPadding(new Insets(20));

    analysisCard.getChildren().addAll(
            stepsTitle,
            stepsArea
    );

    analysisCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    // HISTORY CARD
    VBox historyCard = new VBox(12);

    historyCard.setPadding(new Insets(20));

    historyCard.getChildren().addAll(
            historyTitle,
            historyArea
    );

    historyCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 8;"
    );

    mainContent.getChildren().addAll(
            inputCard,
            analysisCard,
            historyCard
    );

    return mainContent;
}
    



    public static void main(String[] args) {
        launch(args);
    }



private VBox createSortingPage(BorderPane root) {

    VBox page = new VBox(22);
page.setPadding(new Insets(30));
applyLabPageStyle(page);
    Label title = new Label("Sorting Algorithms");
    title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

    Label subtitle = new Label(
        "Enter numbers, select a sorting algorithm and watch each step."
    );

    // CONFIGURATION
    VBox configuration = new VBox(15);
    configuration.setPadding(new Insets(25));
    configuration.setStyle(
        "-fx-background-color: white;" +
        "-fx-background-radius: 12;"
    );

    Label configTitle = new Label("Sorting Configuration");
    configTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    Label inputLabel = new Label("Enter numbers separated by commas:");

    TextField inputField = new TextField("5, 2, 8, 1, 4");
    inputField.setMaxWidth(Double.MAX_VALUE);

    Label algorithmLabel = new Label("Select sorting algorithm:");

    ComboBox<String> algorithmBox = new ComboBox<>();
    algorithmBox.getItems().addAll(
        "Bubble Sort",
        "Selection Sort",
        "Insertion Sort",
        "Merge Sort",
        "Quick Sort"
    );
    algorithmBox.setValue("Bubble Sort");
    algorithmBox.setMaxWidth(Double.MAX_VALUE);

    Button startButton = new Button("Start Sorting");
    Button nextButton = new Button("Next Step");
    Button autoButton = new Button("Auto Play");
    Button resetButton = new Button("Reset");

    String primaryStyle =
        "-fx-background-color: #800020;" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-padding: 12 20;";

    String secondaryStyle =
        "-fx-background-color: #eeeeee;" +
        "-fx-text-fill: #333333;" +
        "-fx-font-weight: bold;" +
        "-fx-padding: 12 20;";

    startButton.setStyle(primaryStyle);
    nextButton.setStyle(secondaryStyle);
    autoButton.setStyle(secondaryStyle);
    resetButton.setStyle(secondaryStyle);

// Make buttons respond when the mouse moves over them

startButton.setCursor(javafx.scene.Cursor.HAND);
nextButton.setCursor(javafx.scene.Cursor.HAND);
autoButton.setCursor(javafx.scene.Cursor.HAND);
resetButton.setCursor(javafx.scene.Cursor.HAND);

// Add explanations when hovering

startButton.setTooltip(new javafx.scene.control.Tooltip(
    "Prepare the sorting process using your selected algorithm."
));

nextButton.setTooltip(new javafx.scene.control.Tooltip(
    "Move forward and see the next sorting operation."
));

autoButton.setTooltip(new javafx.scene.control.Tooltip(
    "Automatically play all the sorting steps."
));

resetButton.setTooltip(new javafx.scene.control.Tooltip(
    "Return to the original array and start again."
));

// Hover colours

startButton.setOnMouseEntered(e -> startButton.setStyle(
    "-fx-background-color: #a33352; -fx-text-fill: white;" +
    "-fx-font-weight: bold; -fx-padding: 12 20;"
));

startButton.setOnMouseExited(e -> startButton.setStyle(primaryStyle));

nextButton.setOnMouseEntered(e -> nextButton.setStyle(
    "-fx-background-color: #d5d5d5; -fx-text-fill: #333333;" +
    "-fx-font-weight: bold; -fx-padding: 12 20;"
));

nextButton.setOnMouseExited(e -> nextButton.setStyle(secondaryStyle));

autoButton.setOnMouseEntered(e -> autoButton.setStyle(
    "-fx-background-color: #d5d5d5; -fx-text-fill: #333333;" +
    "-fx-font-weight: bold; -fx-padding: 12 20;"
));

autoButton.setOnMouseExited(e -> autoButton.setStyle(secondaryStyle));

resetButton.setOnMouseEntered(e -> resetButton.setStyle(
    "-fx-background-color: #d5d5d5; -fx-text-fill: #333333;" +
    "-fx-font-weight: bold; -fx-padding: 12 20;"
));

resetButton.setOnMouseExited(e -> resetButton.setStyle(secondaryStyle));


    HBox controls = new HBox(12,
        startButton, nextButton, autoButton, resetButton
    );

    controls.setAlignment(Pos.CENTER_LEFT);

    configuration.getChildren().addAll(
        configTitle,
        inputLabel,
        inputField,
        algorithmLabel,
        algorithmBox,
        controls
    );

    // ARRAY VISUALIZATION
    VBox visualization = new VBox(20);
    visualization.setPadding(new Insets(25));
    visualization.setStyle(
        "-fx-background-color: white;" +
        "-fx-background-radius: 12;"
    );

    Label visualizationTitle = new Label("Array Visualization");
    visualizationTitle.setStyle(
        "-fx-font-size: 20px; -fx-font-weight: bold;"
    );

    FlowPane arrayDisplay = new FlowPane(12, 12);
    arrayDisplay.setAlignment(Pos.CENTER);
    arrayDisplay.setPrefWrapLength(800);

    Label stepDescription = new Label("Ready to begin sorting.");

    Label instructionLabel = new Label(
    "STEP 1: Enter your numbers, select an algorithm, then click Start Sorting."
);

instructionLabel.setWrapText(true);
instructionLabel.setStyle(
    "-fx-font-size: 14px;" +
    "-fx-font-weight: bold;" +
    "-fx-text-fill: #800020;"
);
    visualization.getChildren().addAll(
    visualizationTitle,
    instructionLabel,
    arrayDisplay,
    stepDescription
);

    // PERFORMANCE RESULTS
    VBox results = new VBox(15);
    results.setPadding(new Insets(25));
    results.setStyle(
        "-fx-background-color: white;" +
        "-fx-background-radius: 12;"
    );

    Label resultsTitle = new Label("Performance Results");
    resultsTitle.setStyle(
        "-fx-font-size: 20px; -fx-font-weight: bold;"
    );

    Label comparisonsLabel = new Label("Comparisons: 0");
    Label movementsLabel = new Label("Swaps / movements: 0");
    Label timeLabel = new Label("Execution Time: 0 ns");
    Label stepLabel = new Label("Step: 0 / 0");
    Label sortedLabel = new Label("Sorted result: []");

    results.getChildren().addAll(
        resultsTitle,
        comparisonsLabel,
        movementsLabel,
        timeLabel,
        stepLabel,
        sortedLabel
    );

    // SORTING STATE
    SortingEngine engine = new SortingEngine();

    final List<SortingStep>[] steps = new List[]{
    new java.util.ArrayList<>()
};
   final int[][] originalArray = {new int[0]};

    final int[] currentStep = {0};
    final long[] startTime = {0};

    Timeline timeline = new Timeline();

    // DISPLAY ARRAY
    java.util.function.Consumer<int[]> displayArray = values -> {

        arrayDisplay.getChildren().clear();

        for (int value : values) {

            Label box = new Label(String.valueOf(value));

            box.setAlignment(Pos.CENTER);
            box.setMinSize(65, 65);
            box.setPrefSize(65, 65);

            box.setStyle(
                "-fx-background-color: #800020;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;"
            );

            arrayDisplay.getChildren().add(box);
        }
    };

    // DISPLAY CURRENT STEP
    java.util.function.Consumer<SortingStep> displayStep = step -> {

        displayArray.accept(step.getArray());

        comparisonsLabel.setText(
            "Comparisons: " + step.getComparisons()
        );

        movementsLabel.setText(
            "Swaps / movements: " + step.getMovements()
        );

        stepDescription.setText(step.getDescription());

        stepLabel.setText(
            "Step: " + currentStep[0] + " / " + (steps[0].size() - 1)
        );

        sortedLabel.setText(
            "Sorted result: " +
            java.util.Arrays.toString(step.getArray())
        );
    };

    // START SORTING
    startButton.setOnAction(e -> {

        timeline.stop();

        try {

            String[] tokens = inputField.getText()
                .trim()
                .split("[,\\s]+");

            originalArray[0] = new int[tokens.length];

            for (int i = 0; i < tokens.length; i++) {
                originalArray[0][i] = Integer.parseInt(tokens[i]);
            }

            if (originalArray[0].length == 0) {
                throw new NumberFormatException();
            }

            steps[0] = engine.generateSteps(
                originalArray[0],
                algorithmBox.getValue()
            );

            currentStep[0] = 0;
            startTime[0] = System.nanoTime();

            displayStep.accept(steps[0].get(0));

            timeLabel.setText("Execution Time: 0 ns");

        } catch (Exception ex) {

            stepDescription.setText(
                "Invalid input! Enter integers separated by commas."
            );
        }
    });

    // NEXT STEP
    nextButton.setOnAction(e -> {

        timeline.stop();

        if (steps[0].isEmpty()) {
            stepDescription.setText("Click Start Sorting first.");
            return;
        }

        if (currentStep[0] < steps[0].size() - 1) {

            currentStep[0]++;

            displayStep.accept(steps[0].get(currentStep[0]));

            timeLabel.setText(
                "Execution Time: " +
                (System.nanoTime() - startTime[0]) + " ns"
            );

        } else {

            stepDescription.setText("Sorting completed!");
        }
    });

    // AUTO PLAY
    autoButton.setOnAction(e -> {

        if (steps[0].isEmpty()) {
            stepDescription.setText("Click Start Sorting first.");
            return;
        }

        timeline.stop();

        timeline.getKeyFrames().setAll(
            new KeyFrame(Duration.millis(700), event -> {

                if (currentStep[0] < steps[0].size() - 1) {

                    currentStep[0]++;

                    displayStep.accept(steps[0].get(currentStep[0]));

                    timeLabel.setText(
                        "Execution Time: " +
                        (System.nanoTime() - startTime[0]) + " ns"
                    );

                } else {

                    timeline.stop();
                    stepDescription.setText("Sorting completed!");
                }
            })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    });

    // RESET
    resetButton.setOnAction(e -> {

        timeline.stop();

        steps[0].clear();
        currentStep[0] = 0;

        displayArray.accept(originalArray[0]);

        comparisonsLabel.setText("Comparisons: 0");
        movementsLabel.setText("Swaps / movements: 0");
        timeLabel.setText("Execution Time: 0 ns");
        stepLabel.setText("Step: 0 / 0");

        sortedLabel.setText(
            "Sorted result: " +
            java.util.Arrays.toString(originalArray[0])
        );

        stepDescription.setText("Array reset.");
    });

    page.getChildren().addAll(
        title,
        subtitle,
        configuration,
        visualization,
        results
    );

    return page;
}


}
