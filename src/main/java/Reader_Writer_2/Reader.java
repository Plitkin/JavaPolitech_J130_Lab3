package Reader_Writer_2;

import java.util.Random;

public class Reader implements Runnable {
    private final Database database;
    private final Random random = new Random();
    private final String name;

    public Reader(Database database, String name) {
        this.database = database;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int sleepTime = random.nextInt(5000) + 1000; // Случайное время сна перед чтением
                Thread.sleep(sleepTime);
                System.out.println(name + " хочет читать.");
                database.startReading(name); // Начало чтения
                int readTime = random.nextInt(3000) + 1000; // Случайное время чтения
                Thread.sleep(readTime);
                database.stopReading(name); // Окончание чтения
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }
    }
}
