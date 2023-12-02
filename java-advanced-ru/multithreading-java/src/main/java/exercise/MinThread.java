package exercise;

// BEGIN
public class MinThread extends Thread{
    private final int[] numbers;
    private Integer minNumber = 0;

    public MinThread(int[] numbers){
        this.numbers = numbers;
    }

    @Override
    public void run() {
        minNumber = findArrayMinimum();
    }

    public Integer findArrayMinimum(){
        int minimum = numbers[0];
        if (numbers.length > 1){
            for (int i = 1; i < numbers.length; i++){
                if (numbers[i] < minimum){
                    minimum = numbers[i];
                }
            }
        }
        return minimum;
    }

    public Integer getMinNumber() {
        return minNumber;
    }
}
// END
