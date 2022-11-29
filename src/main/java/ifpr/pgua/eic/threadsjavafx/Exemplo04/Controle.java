package ifpr.pgua.eic.threadsjavafx.Exemplo04;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controle {

    // Create the TextArea
    @FXML
    private TextArea taContent;

    // Create the Label
    @FXML
    private Label lbStatus;

    @FXML
    private TextField tfStatus;

    // Create the Buttons
    @FXML
    private Button btStart;

    @FXML
    private Button btExit;


    @FXML
    private ProgressBar pbProgresso;

    @FXML
    public void startTask()
    {


        Task<Void> task = runTask();


        pbProgresso.progressProperty().bind(task.progressProperty());
        lbStatus.textProperty().bind(task.messageProperty());
        tfStatus.textProperty().bind(task.messageProperty());


        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public Task<Void> runTask()
    {
        return new Task<Void>() {
            @Override
            //call similar ao metodo run
            protected Void call() throws Exception {
                for(int i = 1; i <= 10; i++)
                {
                    try
                    {
                        // Get the Status
                        final String status = "Processing " + i + " of " + 10;

                        this.updateProgress(i,10);
                        this.updateMessage(status);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                taContent.appendText(status+"\n");
                            }
                        });
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };


    }


    @FXML
    public void exit(){
        Platform.exit();
    }

}
