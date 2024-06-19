package Supplier_Consummer_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private int goods = 0; // Количество товаров на складе
    private final Lock lock = new ReentrantLock(); // Блокировка для синхронизации
    private final Condition notEmpty = lock.newCondition(); // Условие, когда склад не пустой
    private final Condition notFull = lock.newCondition(); // Условие, когда склад не полный
    private final int capacity; // Вместимость склада

    public Warehouse(int capacity) {
        this.capacity = capacity;
    }

    public void addGoods(int amount, String supplierName) throws InterruptedException {
        lock.lock();
        try {
            while (goods + amount > capacity) { // Ждем, если на складе недостаточно места
                System.out.println(supplierName + " ждёт, пока освободится место на складе.");
                notFull.await();
            }
            goods += amount;
            System.out.println(supplierName + " добавил " + amount + " единиц товара. Остаток на складе: " + goods);
            notEmpty.signalAll(); // Уведомляем, что склад больше не пустой
        } finally {
            lock.unlock();
        }
    }

    public void removeGoods(int amount, String consumerName) throws InterruptedException {
        lock.lock();
        try {
            while (goods < amount) { // Ждем, если на складе недостаточно товара
                System.out.println(consumerName + " ждёт, пока на складе появится товар.");
                notEmpty.await();
            }
            goods -= amount;
            System.out.println(consumerName + " забрал " + amount + " единиц товара. Остаток на складе: " + goods);
            notFull.signalAll(); // Уведомляем, что склад больше не полный
        } finally {
            lock.unlock();
        }
    }
}
