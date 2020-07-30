
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("View/MainApplication.fxml"));
        } catch (IOException e) {
            System.out.println(e);
        }
        primaryStage.setScene(new Scene(root));
        Image icon = new Image("image/inet.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

}
