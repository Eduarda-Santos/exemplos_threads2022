package ifpr.pgua.eic.threadsjavafx.Exemplo03;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class Controle {

    // Create the TextArea
    @FXML
    private TextArea taContent;

    // Create the Label
    @FXML
    private Label lbStatus;

    // Create the Buttons
    @FXML
    private Button btStart;

    @FXML
    private Button btExit;

    @FXML
    public void startTask()
    {
        // Create a Runnable
        Runnable task = new Runnable()
        {
            public void run()
            {
                runTask();
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public void runTask()
    {
        for(int i = 1; i <= 10; i++)
        {
            try
            {
                // Get the Status
                final String status = "Processing " + i + " of " + 10;

                // Aqui serão atualizados os componentes da interface gráfica

                /*Platform.runLater(new Runnable()
                //para executar depois, atualiza os componentes da ig
                {
                    @Override
                    public void run()
                    {
                        lbStatus.setText(status);

                        taContent.appendText(status+"\n");

                    }
                });*/
                
                Platform.runLater(() -> {
                    lbStatus.setText(status);

                    taContent.appendText(status+"\n");

                });

                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void exit(){
        Platform.exit();
    }

}
