package dewn2dcaterpillar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dewn2d
 */
public class LeaderboardFXMLController implements Initializable {

    private String boardName;
    private Stage stage;
    public Scene gameScene;
    public FXMLDocumentController gameController;
    public String boardScore;
    File file = new File("src/leaderboard.txt");

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea leaderTextArea;

    @FXML
    private Button exitButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private void saveAction(ActionEvent event) {

        boardName = nameTextField.getText();
        leaderTextArea.appendText(boardName + "     " + boardScore + "\n");
        BufferedWriter writer = null;
        FileWriter fwriter = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fwriter = new FileWriter(file, true);
            writer = new BufferedWriter(fwriter);
            writer.write(boardName + "     " + boardScore + "\n");

            writer.close();
            fwriter.close();

            System.out.println("Data successfully appended at the end of file");

        } catch (IOException ex) {
            System.out.println("Exception occurred:");
        }

    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void backToGameAction(ActionEvent event) {
        stage.setScene(gameScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                leaderTextArea.appendText( line + "\n" );
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
        }
    }

    public void start(Stage stage) {
        this.stage = stage;
    }

}
