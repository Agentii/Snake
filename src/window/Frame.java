package window;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Frame extends BorderPane {
	
	private static final int SHADOW_SIZE = 10;
	public static final int DFLT_WIDTH = 2 * SHADOW_SIZE;
	public static final int DFLT_HEIGHT = SHADOW_SIZE + Topbar.TOPBAR_HEIGHT;
	
	static Stage stage;

	public Frame(Stage primaryStage) {
		stage = primaryStage;
		stage.initStyle(StageStyle.TRANSPARENT);

		setPadding(new Insets(0, SHADOW_SIZE, SHADOW_SIZE, SHADOW_SIZE));
		setStyle("-fx-background-color: transparent;"
				+ String.format("-fx-effect: dropshadow(gaussian, black, %d, 0, 0, 2);", SHADOW_SIZE));
		setTop(new Topbar());
	}
}
