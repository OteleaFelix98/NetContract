package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import Model.Contract;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainApplication implements Initializable {

    @FXML
    public TableView<Contract> tblCustomer;

    @FXML
    public TableColumn<Contract, Integer> clmClientId;

    @FXML
    public TableColumn<Contract, String> clmFirstName, clmClientLastName, clmClientAddress,
            clmNetSpeed, clmNetTraffic, clmPeriod;

    @FXML
    public TextField tfSearch;

    public static ObservableList<Contract> contractList = FXCollections.observableArrayList();

    public static int id = 0;
    public static Contract selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clmClientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmClientLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clmClientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmNetSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        clmNetTraffic.setCellValueFactory(new PropertyValueFactory<>("traffic"));
        clmPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        this.tblCustomer.setItems(contractList);
        this.tblCustomer.autosize();

    }

    @FXML
    public void searchRecord(KeyEvent ke)  {
          FilteredList<Contract> filterData = new FilteredList<>(contractList, p -> true);
        tfSearch.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(c -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (c.getFirstName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (c.getLastName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (c.getAddress().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                return false;
            });
            SortedList<Contract> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tblCustomer.comparatorProperty());
            tblCustomer.setItems(sortedList);
                       
            
        });

    }



    @FXML
    public void btnAddOnAction(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getClassLoader().getResource("View/AddContract.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent event) {

        selectedItem = this.tblCustomer.getSelectionModel().getSelectedItem();
        if (this.tblCustomer.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Record not selected!");
            alert.setContentText("Please select one record first!");
            alert.initStyle(StageStyle.UNIFIED);
            alert.showAndWait();
        } else {
            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                root = fxmlLoader.load(getClass().getClassLoader().getResource("View/UpdateRecord.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.tblCustomer.refresh();

    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        Contract selected = tblCustomer.getSelectionModel().getSelectedItem();
        if (!tblCustomer.getSelectionModel().isEmpty()) {
            contractList.remove(selected);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Record successfully deleted.");
            alert.setContentText("Deleted record: \n " + selected.getFirstName() + " " + selected.getLastName());
            alert.initStyle(StageStyle.UNIFIED);
            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Record not selected!");
            alert.setContentText("Please select one record first!");
            alert.initStyle(StageStyle.UNIFIED);
            alert.showAndWait();
        }
    }

    public static void addItemToList(Contract contract) {
        if (contract.getId() == null) {
            id++;
            contract.setId(id + "");
        }
        contractList.add(contract);
    }

}
