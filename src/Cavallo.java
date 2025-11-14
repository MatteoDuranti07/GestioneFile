public class Cavallo extends Thread {

    /** Nome del cavallo. */
    private final String name;

    /** Tempo di pausa tra un passo e l'altro (in millisecondi). */
    private int sleepTime;

    /**
     * Costruttore che inizializza un cavallo con il nome specificato.
     *
     * @param name nome del cavallo
     */
    public Cavallo(String name) {
        super();
        this.name = name;
    }

    /**
     * Metodo principale del thread che simula la corsa del cavallo.
     * <p>
     * Il cavallo percorre 10 passi, dormendo per {@code sleepTime} millisecondi tra un passo e l’altro.
     * Se viene interrotto, termina la corsa segnalando di essere stato “azzoppato”.
     * </p>
     */
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

    /**
     * Restituisce il tempo di pausa tra i passi del cavallo.
     *
     * @return tempo di pausa (in millisecondi)
     */
    protected int getSleepTime() {
        return sleepTime;
    }

    /**
     * Imposta il tempo di pausa tra i passi del cavallo.
     *
     * @param sleepTime tempo di pausa (in millisecondi)
     */
    protected void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * Restituisce il nome del cavallo.
     *
     * @return nome del cavallo
     */
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