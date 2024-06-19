package Reader_Writer_2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Database {
    private int readers = 0; // Количество активных читателей
    private boolean writer = false; // Флаг наличия активного писателя
    private final Lock lock = new ReentrantLock();
    private final Condition canRead = lock.newCondition();
    private final Condition canWrite = lock.newCondition();

    public void startReading(String readerName) throws InterruptedException {
        lock.lock();
        try {
            while (writer) {
                System.out.println(readerName + " ждет, пока писатель завершит работу.");
                canRead.await(); // Ожидаем, пока писатель завершит работу
            }
            readers++;
            System.out.println(readerName + " подключился. Активных читателей: " + readers);
            displayState();
        } finally {
            lock.unlock();
        }
    }

    public void stopReading(String readerName) {
        lock.lock();
        try {
            readers--;
            System.out.println(readerName + " отключился. Активных читателей: " + readers);
            if (readers == 0) {
                canWrite.signal(); // Уведомляем писателей, что больше нет активных читателей
            }
            displayState();
        } finally {
            lock.unlock();
        }
    }

    public void startWriting(String writerName) throws InterruptedException {
        lock.lock();
        try {
            while (readers > 0 || writer) {
                System.out.println(writerName + " ждет, пока завершат работу читатели или другой писатель.");
                canWrite.await(); // Ожидаем, пока все читатели и писатели завершат работу
            }
            writer = true;
            System.out.println(writerName + " подключился.");
            displayState();
        } finally {
            lock.unlock();
        }
    }

    public void stopWriting(String writerName) {
        lock.lock();
        try {
            writer = false;
            System.out.println(writerName + " отключился.");
            canRead.signalAll(); // Уведомляем читателей, что писатель завершил работу
            canWrite.signal(); // Уведомляем писателей, что писатель завершил работу
            displayState();
        } finally {
            lock.unlock();
        }
    }

    private void displayState() {
        System.out.println("Состояние базы данных: Читатели = " + readers + ", Писатель = " + writer);
    }
}
