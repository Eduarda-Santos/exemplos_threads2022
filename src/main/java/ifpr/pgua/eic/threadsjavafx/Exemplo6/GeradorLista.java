package ifpr.pgua.eic.threadsjavafx.Exemplo6;

import java.util.ArrayList;
import java.util.Random;

public class GeradorLista{

    private ArrayList<Integer> lista;

    public GeradorLista(){
        lista = new ArrayList<>();
    }

    public ArrayList<Integer> getLista(){
        return lista;
    }

    public void novaLista(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
        Random rnd = new Random();
        lista.clear();
        for(int i=0;i<10;i++){
            lista.add(rnd.nextInt(100));
        }
        
    }


}
