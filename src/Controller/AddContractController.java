package Controller;

import Model.Contract;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddContractController implements Initializable {

    @FXML
    public TextField tfFirstName;

    @FXML
    public TextField tfLastName;

    @FXML
    public TextArea taAddress;

    @FXML
    public ComboBox cbSpeed, cbTraffic, cbPeriod;

    @FXML
    public Button btnSave, btnClose;
    
    @FXML
    public CheckBox cbCloseOption ;

    public int id = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        cbCloseOption.setSelected(true);
        ObservableList<String> speed = FXCollections.observableArrayList(
                "2 Mbps",
                "5 Mbps",
                "10 Mbps",
                "20 Mbps",
                "50 Mbps",
                "100 Mbps"
        );
        cbSpeed.setItems(speed);
        cbSpeed.setValue("Select:");

        ObservableList<String> traffic = FXCollections.observableArrayList(
                "1 GB",
                "5 GB",
                "10 GB",
                "100 GB",
                "Unlimited"
        );
        cbTraffic.setItems(traffic);
        cbTraffic.setValue("Select:");

        ObservableList<String> period = FXCollections.observableArrayList(
                "1 Year",
                "2 Year"
        );
        cbPeriod.setItems(period);
        cbPeriod.setValue("Select:");

    }

    public AddContractController() {

    }

    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        boolean added = true;
        Contract contract = new Contract();
        
        contract.setFirstName(this.tfFirstName.getText());
        contract.setLastName(this.tfLastName.getText());
        contract.setAddress(this.taAddress.getText());
        contract.setSpeed(this.cbSpeed.getValue().toString());
        contract.setTraffic(this.cbTraffic.getValue().toString());
        contract.setPeriod(this.cbPeriod.getValue().toString());
        
        if (contract.isValidContract()) {
            MainApplication.addItemToList(contract);
            this.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save");
            alert.setHeaderText("Successfully saved!");
            alert.setContentText("You have inserted one new record successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Contract!");
            alert.setContentText("Please complete all fields!");
            alert.showAndWait();
            added = false;
        }
        if (this.cbCloseOption.isSelected() && added) {
            Stage stage = (Stage) this.btnSave.getScene().getWindow();
            stage.close();
        }
    }
    
    @FXML
    public void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }

    public void clear() {
        this.tfFirstName.setText("");
        this.tfLastName.setText("");
        this.taAddress.setText("");
        cbSpeed.setValue("Select:");
        cbTraffic.setValue("Select:");
        cbPeriod.setValue("Select:");
        tfFirstName.requestFocus();

    }

}
