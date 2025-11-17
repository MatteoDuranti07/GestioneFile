public class Cavallo extends Thread {

    private final String name;
    private int sleepTime;

    public Cavallo(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Cavallo " + name + " inizia la corsa!");
        for (int i = 1; i <= 10; i++) {
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println(name + " è stato azzoppato!");
                return;
            }
            System.out.println(name + " cavalca - passo " + i);
        }

        // Aggiorna il vincitore in modo sincronizzato
        synchronized (GaraCavalli.class) {
            if (GaraCavalli.getFirst() == null) {
                GaraCavalli.setFirst(name);
            }
        }
    }
    protected int getSleepTime() {
        return sleepTime;
    }

    protected void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
    public String getHorseName() {
        return name;
    }

    /**
     * Interrompe il thread del cavallo, simulando un’interruzione della corsa.
     */
    public void setInterrupt() {
        interrupt();
    }
}