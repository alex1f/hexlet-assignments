package exercise;

// BEGIN
public class MaxThread extends Thread{
    private int[] numbers;
    private Integer maximum = 0;

    public MaxThread(int[] numbers){
        this.numbers = numbers;
    }

    @Override
    public void run() {
        maximum = findArrayMaximum();
    }

    public Integer findArrayMaximum(){
        int maximum = numbers[0];
        if (numbers.length > 1){
            for (int i = 1; i < numbers.length; i++){
                if (numbers[i] > maximum){
                    maximum = numbers[i];
                }
            }
        }
        return maximum;
    }

    public Integer getMaximum() {
        return maximum;
    }
}
// END
