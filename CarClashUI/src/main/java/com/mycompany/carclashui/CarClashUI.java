package com.mycompany.carclashui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class CarClashUI extends Application {

    private VBox overviewPanel;
    private VBox historyPanel;
    private Button btnOverview, btnHistory;
    private Label toastLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Player Profile - Horizon");

        // --- TOAST NOTIFICATION HUD ---
        toastLabel = new Label("FEATURE COMING SOON");
        toastLabel.setStyle(
            "-fx-background-color: rgba(10, 14, 22, 0.95);" +
            "-fx-text-fill: #ffd000;" +
            "-fx-font-family: 'Rajdhani';" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: 800;" +
            "-fx-padding: 10px 24px;" +
            "-fx-background-radius: 6px;" +
            "-fx-border-color: #ffd000;" +
            "-fx-border-radius: 6px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(255,208,0,0.5), 20, 0, 0, 0);"
        );
        toastLabel.setTranslateY(-100);
        toastLabel.setVisible(false);

        // --- 1. TOP NAV HEADER ---
        Label backArrow = new Label("‹");
        backArrow.setStyle("-fx-font-size: 22px; -fx-font-weight: 900; -fx-text-fill: white;");
        
        Label titleLabel = new Label("PROFILE");
        titleLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: 900; -fx-text-fill: white; -fx-font-style: italic;");
        
        HBox backGroup = new HBox(12, backArrow, titleLabel);
        backGroup.setAlignment(Pos.CENTER_LEFT);

        Label crLabel = new Label("CR  0");
        crLabel.setStyle("-fx-text-fill: white; -fx-font-weight: 800; -fx-font-size: 11px;");
        StackPane crBadge = new StackPane(crLabel);
        crBadge.setStyle("-fx-background-color: #ec4899; -fx-background-radius: 10px; -fx-padding: 3 10 3 10;");

        Label starLabel = new Label("★  0");
        starLabel.setStyle("-fx-text-fill: #eab308; -fx-font-weight: 800; -fx-font-size: 11px;");

        Label gemLabel = new Label("◆  0");
        gemLabel.setStyle("-fx-text-fill: #38bdf8; -fx-font-weight: 800; -fx-font-size: 11px;");

        HBox currencyCluster = new HBox(16, crBadge, starLabel, gemLabel);
        currencyCluster.setAlignment(Pos.CENTER);
        currencyCluster.setStyle(
            "-fx-background-color: rgba(15, 18, 26, 0.9);" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 6px 16px;"
        );

        BorderPane topNav = new BorderPane();
        topNav.setLeft(backGroup);
        topNav.setRight(currencyCluster);
        topNav.setPadding(new Insets(16, 28, 6, 28));

        // --- 2. SIDEBAR TABS ---
        btnOverview = createTabBtn("⊞   OVERVIEW", true);
        btnHistory = createTabBtn("⏱   HISTORY", false);
        Button btnStats = createTabBtn("📊   STATS", false);
        Button btnCars = createTabBtn("🚗   CARS", false);
        Button btnCustomize = createTabBtn("🖌   CUSTOMIZE", false);
        Button btnBadges = createTabBtn("🏆   BADGES", false);

        btnOverview.setOnAction(e -> switchTab("overview"));
        btnHistory.setOnAction(e -> switchTab("history"));
        
        btnStats.setOnAction(e -> showToast("EXTENDED STATS COMING SOON"));
        btnCars.setOnAction(e -> showToast("GARAGE SHOWCASE COMING SOON"));
        btnCustomize.setOnAction(e -> showToast("LIVERY CUSTOMIZER COMING SOON"));
        btnBadges.setOnAction(e -> showToast("BADGE TIERS LOCKED"));

        VBox sidebarNav = new VBox(8, btnOverview, btnHistory, btnStats, btnCars, btnCustomize, btnBadges);

        Label brandTitle = new Label("CAR CLASH");
        brandTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: 900; -fx-text-fill: white;");
        Label brandSub = new Label("HORIZON");
        brandSub.setStyle("-fx-font-size: 11px; -fx-font-weight: 900; -fx-text-fill: #ef4444;");
        VBox brandBox = new VBox(2, brandTitle, brandSub);
        brandBox.setPadding(new Insets(20, 0, 0, 4));

        VBox leftSidebar = new VBox(0, sidebarNav, brandBox);
        leftSidebar.setPrefWidth(180);
        leftSidebar.setMinWidth(180);
        leftSidebar.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(leftSidebar, Priority.ALWAYS);

        // --- 3. OVERVIEW PANEL ---
        Label avatarLetter = new Label("M");
        avatarLetter.setStyle("-fx-font-size: 22px; -fx-font-weight: 900; -fx-text-fill: white; -fx-font-style: italic;");
        StackPane avatarBox = new StackPane(avatarLetter);
        avatarBox.setMinSize(56, 56);
        avatarBox.setStyle("-fx-background-color: #090c14; -fx-background-radius: 28px;");
        
        StackPane avatarGlow = new StackPane(avatarBox);
        avatarGlow.setMinSize(64, 64);
        avatarGlow.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, #a855f7, #3b82f6);" +
            "-fx-background-radius: 32px;" +
            "-fx-padding: 3px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(168,85,247,0.4), 15, 0, 0, 0);"
        );

        Label nameLabel = new Label("MUSAIB");
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: 900; -fx-text-fill: white;");

        Label vipPill = new Label("LEGEND");
        vipPill.setStyle("-fx-background-color: #8b5cf6; -fx-text-fill: white; -fx-font-size: 8px; -fx-font-weight: 900; -fx-padding: 2px 6px; -fx-background-radius: 3px;");
        HBox nameRow = new HBox(8, nameLabel, vipPill);
        nameRow.setAlignment(Pos.CENTER_LEFT);

        Label levelBadge = new Label("1");
        levelBadge.setStyle("-fx-background-color: #8b5cf6; -fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: 900; -fx-padding: 2px 6px; -fx-background-radius: 3px;");

        Label xpCount = new Label("0 / 1000 XP");
        xpCount.setStyle("-fx-font-size: 10px; -fx-font-weight: 800; -fx-text-fill: #94a3b8;");

        Pane xpFill = new Pane();
        xpFill.setPrefWidth(0);
        xpFill.setPrefHeight(4);
        xpFill.setStyle("-fx-background-color: linear-gradient(to right, #8b5cf6, #c084fc); -fx-background-radius: 2px;");

        Pane xpTrack = new Pane(xpFill);
        xpTrack.setPrefWidth(180);
        xpTrack.setPrefHeight(4);
        xpTrack.setStyle("-fx-background-color: rgba(255,255,255,0.12); -fx-background-radius: 2px;");

        HBox xpRow = new HBox(10, levelBadge, xpCount, xpTrack);
        xpRow.setAlignment(Pos.CENTER_LEFT);

        Label joinedMeta = new Label("JOINED: RECENT  •  PLAYER ID: 8691455308");
        joinedMeta.setStyle("-fx-font-size: 8.5px; -fx-font-weight: 700; -fx-text-fill: #94a3b8;");

        VBox userMetaDetails = new VBox(4, nameRow, xpRow, joinedMeta);
        HBox heroIdentity = new HBox(16, avatarGlow, userMetaDetails);
        heroIdentity.setAlignment(Pos.CENTER_LEFT);
        heroIdentity.setPadding(new Insets(16, 22, 16, 22));

        // HERO CARD WITH DIRECT ImageView BACKGROUND LAYER
        ImageView bannerImgView = new ImageView();
        try {
            Image bannerImg = new Image(getClass().getResourceAsStream("/image/pr1.png"));
            bannerImgView.setImage(bannerImg);
        } catch(Exception e) {}
        bannerImgView.setFitWidth(750);
        bannerImgView.setFitHeight(115);
        bannerImgView.setPreserveRatio(false);

        Pane darkOverlay = new Pane();
        darkOverlay.setStyle("-fx-background-color: linear-gradient(to right, rgba(8,10,16,0.96) 0%, rgba(8,10,16,0.85) 50%, rgba(8,10,16,0.3) 100%);");

        StackPane heroCard = new StackPane(bannerImgView, darkOverlay, heroIdentity);
        heroCard.setStyle("-fx-border-color: rgba(255,255,255,0.12); -fx-border-radius: 8px; -fx-background-radius: 8px;");

        // Stats Tiles Row
        HBox statsGrid = new HBox(10,
            createStatTile("TIME DRIVEN", "0h 0m", "Time spent in game"),
            createStatTile("RACES WON", "0", "Total Wins"),
            createStatTile("RACES PLAYED", "0", "Total Races"),
            createStatTile("TOTAL SCORE", "0", "Career Points"),
            createStatTile("DISTANCE", "0 km", "Total Distance"),
            createStatTile("CASH EARNED", "0", "Total Credits")
        );
        for(Node node : statsGrid.getChildren()) {
            HBox.setHgrow(node, Priority.ALWAYS);
            VBox.setVgrow(node, Priority.ALWAYS);
        }
        statsGrid.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(statsGrid, Priority.ALWAYS);

        // Favorite Car Box
        Label favTitle = new Label("FAVORITE CAR");
        favTitle.setStyle("-fx-font-size: 8px; -fx-font-weight: 800; -fx-text-fill: #64748b;");
        Label favCarName = new Label("BUGATTI CHIRON");
        favCarName.setStyle("-fx-font-size: 13px; -fx-font-weight: 900; -fx-text-fill: white; -fx-font-style: italic;");
        Label carClass = new Label("CLASS S | 900");
        carClass.setStyle("-fx-background-color: #8b5cf6; -fx-text-fill: white; -fx-font-size: 8.5px; -fx-font-weight: 900; -fx-padding: 2px 6px; -fx-background-radius: 3px;");

        VBox favCarLeft = new VBox(4, favTitle, favCarName, carClass);

        ImageView carImgView = new ImageView();
        try {
            Image carImg = new Image(getClass().getResourceAsStream("/image/n1.png"));
            carImgView.setImage(carImg);
        } catch(Exception e) {}
        carImgView.setFitWidth(160);
        carImgView.setFitHeight(75);
        carImgView.setPreserveRatio(true);

        HBox favCarCenter = new HBox(15, favCarLeft, carImgView);
        favCarCenter.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(carImgView, Priority.ALWAYS);

        Label garageLink = new Label("VIEW IN GARAGE  >");
        garageLink.setStyle("-fx-font-size: 9.5px; -fx-font-weight: 800; -fx-text-fill: #94a3b8;");

        VBox favCarBox = new VBox(12, favCarCenter, garageLink);
        favCarBox.setPadding(new Insets(14, 18, 14, 18));
        favCarBox.setStyle("-fx-background-color: rgba(15,18,26,0.8); -fx-border-color: rgba(255,255,255,0.06); -fx-border-radius: 8px; -fx-background-radius: 8px;");
        HBox.setHgrow(favCarBox, Priority.ALWAYS);
        VBox.setVgrow(favCarBox, Priority.ALWAYS);

        // Achievements Box
        Label achieveTitle = new Label("RECENT ACHIEVEMENTS");
        achieveTitle.setStyle("-fx-font-size: 9.5px; -fx-font-weight: 900; -fx-text-fill: #94a3b8;");
        Label viewAll = new Label("VIEW ALL");
        viewAll.setStyle("-fx-font-size: 8.5px; -fx-font-weight: 800; -fx-text-fill: #64748b;");

        BorderPane achieveHeader = new BorderPane();
        achieveHeader.setLeft(achieveTitle);
        achieveHeader.setRight(viewAll);

        HBox achieveItems = new HBox(12,
            createAchieveBadge("SPEED DEMON", "Reach 300 KM/H"),
            createAchieveBadge("CLEAN RUN", "No damage race"),
            createAchieveBadge("DRIFT KING", "Score 25,000 pts")
        );
        for(Node node : achieveItems.getChildren()) {
            HBox.setHgrow(node, Priority.ALWAYS);
        }

        VBox achievementsBox = new VBox(12, achieveHeader, achieveItems);
        achievementsBox.setPadding(new Insets(14, 18, 14, 18));
        achievementsBox.setStyle("-fx-background-color: rgba(15,18,26,0.8); -fx-border-color: rgba(255,255,255,0.06); -fx-border-radius: 8px; -fx-background-radius: 8px;");
        HBox.setHgrow(achievementsBox, Priority.ALWAYS);
        VBox.setVgrow(achievementsBox, Priority.ALWAYS);

        HBox lowerSplit = new HBox(14, favCarBox, achievementsBox);
        lowerSplit.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(lowerSplit, Priority.ALWAYS);

        overviewPanel = new VBox(12, heroCard, statsGrid, lowerSplit);
        overviewPanel.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(overviewPanel, Priority.ALWAYS);

        // --- 4. HISTORY PANEL ---
        Label historyTitle = new Label("CAREER RESULTS (LAST RACES)");
        historyTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: 900; -fx-font-style: italic; -fx-text-fill: #ffd000;");

        Label placeholderHistory = new Label("No recent race records found. Complete a race to view career telemetry!");
        placeholderHistory.setStyle("-fx-text-fill: #64748b; -fx-font-size: 12px; -fx-font-weight: bold;");
        
        VBox histContent = new VBox(15, historyTitle, placeholderHistory);
        histContent.setPadding(new Insets(25));
        histContent.setStyle("-fx-background-color: rgba(15,18,26,0.9); -fx-border-color: rgba(255,255,255,0.08); -fx-border-radius: 8px; -fx-background-radius: 8px;");
        VBox.setVgrow(histContent, Priority.ALWAYS);

        historyPanel = new VBox(12, histContent);
        historyPanel.setVisible(false);
        historyPanel.setManaged(false);
        historyPanel.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(historyPanel, Priority.ALWAYS);

        StackPane contentStack = new StackPane(overviewPanel, historyPanel);
        StackPane.setAlignment(contentStack, Pos.TOP_LEFT);
        contentStack.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(contentStack, Priority.ALWAYS);
        VBox.setVgrow(contentStack, Priority.ALWAYS);

        // --- MAIN LAYOUT ---
        HBox mainLayout = new HBox(20, leftSidebar, contentStack);
        mainLayout.setPadding(new Insets(8, 28, 16, 28));
        mainLayout.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(mainLayout, Priority.ALWAYS);
        VBox.setVgrow(mainLayout, Priority.ALWAYS);

        StackPane root = new StackPane(toastLabel);
        StackPane.setAlignment(toastLabel, Pos.TOP_CENTER);

        try {
            Image bgImg = new Image(getClass().getResourceAsStream("/image/main3.png"));
            BackgroundImage bg = new BackgroundImage(bgImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
            root.setBackground(new Background(bg));
        } catch(Exception e) {
            root.setStyle("-fx-background-color: #06080d;");
        }

        VBox container = new VBox(0, topNav, mainLayout);
        container.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(mainLayout, Priority.ALWAYS);
        root.getChildren().add(container);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void switchTab(String tabName) {
        if (tabName.equals("overview")) {
            overviewPanel.setVisible(true);
            overviewPanel.setManaged(true);
            historyPanel.setVisible(false);
            historyPanel.setManaged(false);

            btnOverview.setStyle("-fx-background-color: #ffd000; -fx-text-fill: black; -fx-font-weight: 900; -fx-alignment: CENTER_LEFT; -fx-font-size: 10.5px; -fx-background-radius: 4px; -fx-padding: 8 14 8 14;");
            btnHistory.setStyle("-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-font-weight: 800; -fx-alignment: CENTER_LEFT; -fx-font-size: 10.5px; -fx-background-radius: 4px; -fx-padding: 8 14 8 14;");
        } else if (tabName.equals("history")) {
            overviewPanel.setVisible(false);
            overviewPanel.setManaged(false);
            historyPanel.setVisible(true);
            historyPanel.setManaged(true);

            btnHistory.setStyle("-fx-background-color: #ffd000; -fx-text-fill: black; -fx-font-weight: 900; -fx-alignment: CENTER_LEFT; -fx-font-size: 10.5px; -fx-background-radius: 4px; -fx-padding: 8 14 8 14;");
            btnOverview.setStyle("-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-font-weight: 800; -fx-alignment: CENTER_LEFT; -fx-font-size: 10.5px; -fx-background-radius: 4px; -fx-padding: 8 14 8 14;");
        }
    }

    private void showToast(String msg) {
        toastLabel.setText(msg);
        toastLabel.setVisible(true);
        toastLabel.setTranslateY(24);

        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
        delay.setOnFinished(event -> {
            toastLabel.setVisible(false);
            toastLabel.setTranslateY(-100);
        });
        delay.play();
    }

    private Button createTabBtn(String text, boolean isActive) {
        Button btn = new Button(text);
        String style = isActive ? 
            "-fx-background-color: #ffd000; -fx-text-fill: black; -fx-font-weight: 900;" :
            "-fx-background-color: transparent; -fx-text-fill: #94a3b8; -fx-font-weight: 800;";
        btn.setStyle(style + "-fx-alignment: CENTER_LEFT; -fx-font-size: 10.5px; -fx-background-radius: 4px; -fx-padding: 8 14 8 14;");
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }

    private VBox createStatTile(String title, String val, String sub) {
        Label t = new Label(title);
        t.setStyle("-fx-font-size: 8px; -fx-font-weight: 800; -fx-text-fill: #94a3b8;");
        Label v = new Label(val);
        v.setStyle("-fx-font-size: 16px; -fx-font-weight: 900; -fx-text-fill: white; -fx-font-style: italic;");
        Label s = new Label(sub);
        s.setStyle("-fx-font-size: 7.5px; -fx-font-weight: 700; -fx-text-fill: #64748b;");

        VBox tile = new VBox(4, t, v, s);
        tile.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(tile, Priority.ALWAYS);
        tile.setPadding(new Insets(10, 12, 10, 12));
        tile.setStyle("-fx-background-color: rgba(15,18,26,0.8); -fx-border-color: rgba(255,255,255,0.06); -fx-border-radius: 8px; -fx-background-radius: 8px;");
        return tile;
    }

    private VBox createAchieveBadge(String name, String sub) {
        Label nameLbl = new Label(name);
        nameLbl.setStyle("-fx-font-size: 8.5px; -fx-font-weight: 900; -fx-text-fill: white;");
        Label subLbl = new Label(sub);
        subLbl.setStyle("-fx-font-size: 7.5px; -fx-text-fill: #64748b;");

        StackPane iconCircle = new StackPane(new Label("★"));
        iconCircle.setMinSize(32, 32);
        iconCircle.setMaxSize(32, 32);
        iconCircle.setStyle("-fx-background-color: rgba(255,255,255,0.06); -fx-border-color: rgba(255,255,255,0.2); -fx-border-radius: 16px; -fx-background-radius: 16px; -fx-text-fill: #ffd000;");

        VBox item = new VBox(6, iconCircle, nameLbl, subLbl);
        item.setAlignment(Pos.CENTER);
        return item;
    }

    public static void main(String[] args) {
        launch(args);
    }
}