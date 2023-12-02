package exercise;

// BEGIN
public class ListThread extends Thread{
    private SafetyList target;


    public ListThread(SafetyList list){
        target = list;
    }


    @Override
    public void run() {
        for (int i = 1; i < 1001; i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            target.add(i);
        }
    }
}
// END
