package ua.praktik.controller;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ua.praktik.model.Book;
import ua.praktik.repository.BookRepository;
import ua.praktik.util.AppConfig;

public class MainController {
    @FXML
    private Button findBtn;

    @FXML
    private ComboBox<String> choiceMouse;

    @FXML
    private Button getBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TextField inputText;

    @FXML
    private ProgressBar progressBar;

    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            AppConfig.class);

    BookRepository bookRepository = ctx.getBean(BookRepository.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    private TableView<Book> userTable;

    @FXML
    private ImageView largeImageView;

    @FXML
    void onAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddBookDialog.fxml"));
            Parent root = loader.load();

            AddBookDialogController dialogController = loader.getController();

            Dialog<Book> dialog = new Dialog<>();
            dialog.setTitle("Додати нову книгу");
            dialog.setHeaderText("Введіть дані для нової книги:");

            dialog.getDialogPane().setContent(root);

            ButtonType addButton = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    if (dialogController.validateInput()) {
                        return dialogController.getBookFromFields();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Помилка");
                        alert.setHeaderText("Неправильний формат числових даних");
                        alert.setContentText("Будь ласка, перевірте, що рік, ціна та кількість сторінок є числами.");
                        alert.showAndWait();
                        return null;
                    }
                }
                return null;
            });

            Optional<Book> result = dialog.showAndWait();

            result.ifPresent(book -> {
                progressBar.setVisible(true);
                progressBar.setProgress(-1);
                addBtn.setDisable(true);

                CompletableFuture.runAsync(() -> {
                    bookRepository.insert(book);
                }).thenRunAsync(() -> {
                    Platform.runLater(() -> {
                        onGet(new ActionEvent());

                        progressBar.setVisible(false);
                        addBtn.setDisable(false);

                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Успішно");
                        successAlert.setHeaderText("Книгу додано");
                        successAlert
                                .setContentText("Книга '" + book.getTitle() + "' була успішно додана до бази даних.");
                        successAlert.showAndWait();
                    });
                }).exceptionally(throwable -> {
                    Platform.runLater(() -> {
                        progressBar.setVisible(false);
                        addBtn.setDisable(false);

                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Помилка");
                        errorAlert.setHeaderText("Не вдалося додати книгу");
                        errorAlert.setContentText("Сталася помилка при додаванні книги: " + throwable.getMessage());
                        errorAlert.showAndWait();
                    });
                    return null;
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка");
            alert.setHeaderText("Не вдалося відкрити форму додавання");
            alert.setContentText("Сталася помилка при завантаженні форми додавання книги: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void onGet(ActionEvent event) {
        progressBar.setVisible(true);
        progressBar.setProgress(-1);
        getBtn.setDisable(true);

        CompletableFuture.supplyAsync(
                () -> {
                    try {
                        // Емуляція затримки для показу загрузки
                        Thread.sleep(1000);
                    } catch (final InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return bookRepository.findAll();
                })
                .thenAcceptAsync(
                        books -> {
                            Platform.runLater(
                                    () -> {
                                        userTable.getItems().setAll(books);
                                        progressBar.setVisible(false);
                                        getBtn.setDisable(false);
                                    });
                        })
                .exceptionally(
                        ex -> {
                            Platform.runLater(
                                    () -> {
                                        System.err.println("Помилка завантаження даних: " + ex.getMessage());
                                        ex.printStackTrace();
                                        progressBar.setVisible(false);
                                        getBtn.setDisable(false);
                                    });
                            return null;
                        });
    }

    @FXML
    void onFind(ActionEvent event) {
        String selectedField = choiceMouse.getValue();
        String searchText = inputText.getText();

        if (searchText == null || searchText.trim().isEmpty()) {
            onGet(event);
            return;
        }

        progressBar.setVisible(true);
        progressBar.setProgress(-1);
        getBtn.setDisable(true);

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500); // емуляція затримки
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            switch (selectedField) {
                case "title":
                    return bookRepository.findByTitle(searchText);
                case "author":
                    return bookRepository.findByAuthor(searchText);
                case "isbn":
                    return bookRepository.findByIsbn(searchText);
                case "publisher":
                    return bookRepository.findByPublisher(searchText);
                case "publicationYear":
                    try {
                        int year = Integer.parseInt(searchText);
                        return bookRepository.findByPublicationYear(year);
                    } catch (NumberFormatException e) {
                        return bookRepository.findAll();
                    }
                case "imagePath":
                    return bookRepository.findAll().stream()
                            .filter(book -> book.getImagePath() != null && book.getImagePath().contains(searchText))
                            .collect(java.util.stream.Collectors.toList());
                default:
                    return bookRepository.findAll();
            }
        })
                .thenAcceptAsync(books -> {
                    Platform.runLater(() -> {
                        userTable.getItems().setAll(books);
                        progressBar.setVisible(false);
                        getBtn.setDisable(false);
                    });
                })
                .exceptionally(ex -> {
                    Platform.runLater(() -> {
                        System.err.println("Помилка пошуку: " + ex.getMessage());
                        ex.printStackTrace();
                        progressBar.setVisible(false);
                        getBtn.setDisable(false);
                    });
                    return null;
                });
    }

    @SuppressWarnings("unchecked")
    @FXML
    void initialize() {
        final TableColumn<Book, Integer> idCol = new TableColumn<>("ID");
        final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        final TableColumn<Book, Integer> yearCol = new TableColumn<>("Year");
        final TableColumn<Book, String> publisherCol = new TableColumn<>("Publisher");
        final TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        final TableColumn<Book, Integer> pageCountCol = new TableColumn<>("Pages");
        final TableColumn<Book, String> imageCol = new TableColumn<>("Image");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        pageCountCol.setCellValueFactory(new PropertyValueFactory<>("pageCount"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("imagePath"));

        userTable
                .getColumns()
                .addAll(idCol, titleCol, authorCol, isbnCol, yearCol, publisherCol, priceCol, pageCountCol, imageCol);

        List<String> searchOptions = List.of("title", "author", "isbn", "publicationYear", "publisher", "imagePath");
        choiceMouse.getItems().setAll(searchOptions);
        choiceMouse.setValue("title");

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (userTable.getSelectionModel().getSelectedItem() != null) {
                Book selectedBook = userTable.getSelectionModel().getSelectedItem();
                updateLargeImageView(selectedBook.getImagePath());
            }
        });
    }

    private void updateLargeImageView(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image image;
                if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
                    image = new Image(imagePath, 300, 200, true, true);
                } else {
                    image = new Image("file:" + imagePath, 300, 200, true, true);
                }
                largeImageView.setImage(image);
            } catch (Exception e) {
                System.err.println("Помилка при завантаженні зображення: " + e.getMessage());
                largeImageView.setImage(null);
            }
        } else {
            largeImageView.setImage(null);
        }
    }
}
