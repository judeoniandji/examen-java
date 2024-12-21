package com.commande.views;
import com.commande.entities.Article;
import com.commande.entities.Client;
import com.commande.entities.Commande;
import com.commande.entities.CommandeArticle;
import com.commande.services.ArticleService;
import com.commande.services.ClientService;
import com.commande.services.CommandeArticleService;
import com.commande.services.CommandeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class CommandeController {
    @FXML
    private TextField txtTelephone, txtNomPrenom, txtAdresse, txtPrix, txtQuantite;

    @FXML
    private ComboBox<Article> cbArticles;

    @FXML
    private TableView<CommandeArticle> tableArticles;

    @FXML
    private Label lblTotal;

    private final ClientService clientService = new ClientService();
    private final ArticleService articleService = new ArticleService();
    private final CommandeService commandeService = new CommandeService();
    private final CommandeArticleService commandeArticleService = new CommandeArticleService();

    private Client currentClient;
    private final ObservableList<CommandeArticle> articlesInOrder = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Charger les articles dans le ComboBox
        List<Article> articles = articleService.findAll();
        cbArticles.setItems(FXCollections.observableArrayList(articles));

        // Configurer la TableView
        tableArticles.setItems(articlesInOrder);
        // Configurez les colonnes ici
    }

    @FXML
    private void onSearchClient() {
        String telephone = txtTelephone.getText();
        currentClient = clientService.findByTelephone(telephone);

        if (currentClient != null) {
            txtNomPrenom.setText(currentClient.getNom() + " " + currentClient.getPrenom());
            txtAdresse.setText(currentClient.getAdresse());
            enableOrderSection(true);
        } else {
            showAlert("Client introuvable", "Aucun client trouvé avec ce numéro de téléphone.", Alert.AlertType.WARNING);
            enableOrderSection(false);
        }
    }

    @FXML
    private void onAddArticle() {
        Article selectedArticle = cbArticles.getValue();
        if (selectedArticle == null) return;

        double prix = Double.parseDouble(txtPrix.getText());
        int quantite = Integer.parseInt(txtQuantite.getText());

        CommandeArticle ca = new CommandeArticle(selectedArticle, prix, quantite);
        articlesInOrder.add(ca);

        updateTotal();
    }

    @FXML
    private void onValidateOrder() {
        if (currentClient == null) return;

        Commande commande = new Commande();
        commande.setClient(currentClient);
        commandeService.save(commande);

        for (CommandeArticle ca : articlesInOrder) {
            ca.setCommande(commande);
            commandeArticleService.save(ca);
        }

        showAlert("Commande validée", "La commande a été enregistrée avec succès.", Alert.AlertType.INFORMATION);
        resetForm();
    }

    private void updateTotal() {
        double total = articlesInOrder.stream().mapToDouble(ca -> ca.getPrix() * ca.getQuantite()).sum();
        lblTotal.setText(String.format("%.2f", total));
    }

    private void enableOrderSection(boolean enable) {
        cbArticles.setDisable(!enable);
        txtPrix.setDisable(!enable);
        txtQuantite.setDisable(!enable);
    }

    private void resetForm() {
        txtTelephone.clear();
        txtNomPrenom.clear();
        txtAdresse.clear();
        articlesInOrder.clear();
        lblTotal.setText("0.0");
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
