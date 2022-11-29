package ifpr.pgua.eic.threadsjavafx.Cmd;

public class AppFibo {
    /*
        public static long fibo(int n) {
            if(n>1){
                return fibo(n-1)+fibo(n-2);
            }else{
                return 1;
            }
     }

    public static void main(String[] args) {
        long res = fibo(50);
        System.out.println(res);
    }*/

    public static void main(String[] args) throws Exception{
        Fibo f = new Fibo(50);
        Thread thread = new Thread(f);

        thread.start();

        while(!f.isTerminou()){
            try{
                System.out.print(". ");
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Interrompido...");
            }
            
        }
        
    }
}
