package exercise;

class SafetyList {
    // BEGIN
    private int[] numbers;
    private int currentIndex;
    private double expansionFactor = 1.5;

    public SafetyList(){
        this.numbers = new int[10];
        currentIndex = 0;
    }

    public synchronized void add(int number){
        if (currentIndex > numbers.length - 1){
            expandArray();
        }
        numbers[currentIndex] = number;
        currentIndex++;
    }

    public int get(int index){
        return numbers[index];
    }

    public int getSize(){
        return currentIndex;
    }

    private void expandArray(){
        int[] expanded = new int[(int) (numbers.length * expansionFactor)];
        for (int i = 0; i < numbers.length; i++){
            expanded[i] = numbers[i];
        }
        numbers = expanded;
    }
    // END
}
