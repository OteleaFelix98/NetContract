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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateRecordController implements Initializable {

    @FXML
    public TextField tfFirstName, tfLastName, tfSelectId;

    @FXML
    public TextArea taAddress;

    @FXML
    public ComboBox cbSpeed, cbTraffic, cbPeriod, cbListId;

    @FXML
    public Button btnSave, btnClose;

    public static Contract contract;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        contract = MainApplication.selectedItem;
       
        this.tfSelectId.setText(contract.getId());
        this.tfFirstName.setText(contract.getFirstName());
        this.tfLastName.setText(contract.getLastName());
        this.taAddress.setText(contract.getAddress());
        this.cbSpeed.setValue(contract.getSpeed());
        this.cbTraffic.setValue(contract.getTraffic());
        this.cbPeriod.setValue(contract.getPeriod());
        
        ObservableList<String> speed = FXCollections.observableArrayList(
                "2 Mbps",
                "5 Mbps",
                "10 Mbps",
                "20 Mbps",
                "50 Mbps",
                "100 Mbps"
        );
        cbSpeed.setItems(speed);

        ObservableList<String> traffic = FXCollections.observableArrayList(
                "1 GB",
                "5 GB",
                "10 GB",
                "100 GB",
                "Unlimited"
        );
        cbTraffic.setItems(traffic);

        ObservableList<String> period = FXCollections.observableArrayList(
                "1 Year",
                "2 Year"
        );
       cbPeriod.setItems(period);
       this.tfSelectId.setEditable(false);
       this.tfFirstName.requestFocus();
    }

    public UpdateRecordController() {

    }

    @FXML
    public void btnSaveOnAction(ActionEvent event) {

        contract.setId(this.tfSelectId.getText());
        contract.setFirstName(this.tfFirstName.getText());
        contract.setLastName(this.tfLastName.getText());
        contract.setAddress(this.taAddress.getText());
        contract.setSpeed(this.cbSpeed.getValue().toString());
        contract.setTraffic(this.cbTraffic.getValue().toString());
        contract.setPeriod(this.cbPeriod.getValue().toString());
        
        if(!contract.getId().equals("")){
        for (int i = 0; i < MainApplication.contractList.size(); i++) {
            if (MainApplication.contractList.get(i).getId().equals(this.tfSelectId.getText())) {
                MainApplication.contractList.remove(i);
            }
        }
        MainApplication.addItemToList(contract);
        }
        Close();
    }

    public void Close() {
        Stage stage = (Stage) this.btnSave.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnSelectOnAction(ActionEvent event) {

        for (Contract c : MainApplication.contractList) {
            if (c.getId().equals(this.tfSelectId.getText())) {
                this.tfFirstName.setText(c.getFirstName());
                this.tfLastName.setText(c.getLastName());
                this.taAddress.setText(c.getAddress());
                this.cbSpeed.setValue(c.getSpeed());
                this.cbTraffic.setValue(c.getTraffic());
                this.cbPeriod.setValue(c.getPeriod());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Not found!");
                alert.setHeaderText("ID not found!");
                alert.setContentText("Please select an existing ID!");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    public void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }

}
