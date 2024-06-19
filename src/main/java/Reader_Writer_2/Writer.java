package Reader_Writer_2;

import java.util.Random;

public class Writer implements Runnable {
    private final Database database;
    private final Random random = new Random();
    private final String name;

    public Writer(Database database, String name) {
        this.database = database;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int sleepTime = random.nextInt(5000) + 1000; // Случайное время сна перед записью
                Thread.sleep(sleepTime);
                System.out.println(name + " хочет писать.");
                database.startWriting(name); // Начало записи
                int writeTime = random.nextInt(3000) + 1000; // Случайное время записи
                Thread.sleep(writeTime);
                database.stopWriting(name); // Окончание записи
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }
    }
}
