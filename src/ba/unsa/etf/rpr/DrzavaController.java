package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox<Grad> choiceGrad;
    public ChoiceBox<Grad> choiceGradNajveci;
    public RadioButton radioIsti;
    public RadioButton radioDrugi;
    private Drzava drzava;
    private ObservableList<Grad> listGradovi;

    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi) {
        this.drzava = drzava;
        listGradovi = FXCollections.observableArrayList(gradovi);
    }

    @FXML
    public void initialize() {
        radioIsti.setSelected(true);
        choiceGrad.setItems(listGradovi);
        choiceGradNajveci.setItems(listGradovi);

        if (drzava != null) {
            fieldNaziv.setText(drzava.getNaziv());
            choiceGrad.getSelectionModel().select(drzava.getGlavniGrad());
            choiceGradNajveci.getSelectionModel().select(drzava.getNajveciGrad());
        } else {
            choiceGrad.getSelectionModel().selectFirst();
            choiceGradNajveci.getSelectionModel().selectFirst();
        }
        choiceGradNajveci.setDisable(true);
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        if (drzava == null) drzava = new Drzava();
        drzava.setNaziv(fieldNaziv.getText());
        drzava.setGlavniGrad(choiceGrad.getSelectionModel().getSelectedItem());
        drzava.setNajveciGrad(choiceGradNajveci.getSelectionModel().getSelectedItem());
        closeWindow();
    }

    public void clickCancel(ActionEvent actionEvent) {
        drzava = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void radioIstiAction(ActionEvent actionEvent){
        // disable
        if(radioDrugi.isSelected()) radioDrugi.setSelected(false);
        //if(radioIsti.isSelected()) radioIsti.setSelected(false);
        choiceGradNajveci.setDisable(true);
        choiceGradNajveci.getSelectionModel().select(choiceGrad.getSelectionModel().getSelectedItem());
    }

    public void radioDrugiAction(ActionEvent actionEvent){
        //onda bira sta hoce
        if(radioIsti.isSelected()) radioIsti.setSelected(false);
        //if(radioDrugi.isSelected()) radioDrugi.setSelected(false);
        choiceGradNajveci.setDisable(false);
    }
}
