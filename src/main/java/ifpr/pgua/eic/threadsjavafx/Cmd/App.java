package ifpr.pgua.eic.threadsjavafx.Cmd;

import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
    
        Printer[] printers = new Printer[5];
        Thread[] threads = new Thread[printers.length];

        Random rnd = new Random();

        for(int i=0;i<printers.length;i++){
            printers[i] = new Printer(i, 5, 1000+rnd.nextInt(1000), "Uma mensagem da Printer "+i);
            threads[i] = new Thread(printers[i]);
            threads[i].start();
        }

        for(int i=0;i<printers.length;i++){
            threads[i].join();
        }

        System.out.println("Finalizando...!!!");


    }
}
