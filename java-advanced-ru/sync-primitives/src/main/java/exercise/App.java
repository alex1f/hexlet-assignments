package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList();
        ListThread threadA = new ListThread(list);
        ListThread threadB = new ListThread(list);

        threadA.start();
        threadB.start();

        try {
            threadA.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            threadB.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.getSize());
        // END
    }
}

