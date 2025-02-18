package ifpr.pgua.eic.threadsjavafx.Exemplo6;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controle {

    // Create the Label
    @FXML
    private Label lbRelogio;

    @FXML
    private Label lbStatus;

    @FXML
    private TextArea taContent;

    @FXML
    private TextField tfStatus;

    @FXML
    private TextField tfLista;

    @FXML
    private ProgressIndicator progresso;

    private DateTimeFormatter df=DateTimeFormatter.ofPattern("HH:mm:ss");
    private GeradorLista geradorLista;

    public void initialize(){

        geradorLista = new GeradorLista();

        Task<Void> relogio = controleRelogio();
        lbRelogio.textProperty().bind(relogio.messageProperty());
        // Run the task in a background thread
        Thread backgroundThread = new Thread(relogio);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();

        progresso.setVisible(true);

    }

    public Task<Void> controleRelogio(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while(true){
                    String str = df.format(LocalDateTime.now());
                    this.updateMessage(str);
                    progresso.setVisible(false);
                    Thread.sleep(1000);

                }
            }
        };
    }

    public Task<Void> atualizadorLista(){
        return new Task<Void>(){
            @Override
            protected Void call() throws Exception{
                while(true){
                    Thread.sleep(5000);
                    Platform.runLater(()->{
                        tfLista.setText("Criando Lista...");
                        
                    });

                    geradorLista.novaLista();
                    Platform.runLater(()->{
                        tfLista.setText(geradorLista.getLista().toString());
                    });
                }
            }
        };
    }


    @FXML
    public void exit(){
        Platform.exit();
    }

}
