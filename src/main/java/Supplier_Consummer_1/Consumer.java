package Supplier_Consummer_1;

import java.util.Random;

public class Consumer implements Runnable {
    private final Warehouse warehouse;
    private final Random random = new Random();
    private final String name;

    public Consumer(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int amount = random.nextInt(10) + 1; // Случайное количество товара для удаления
                int sleepTime = random.nextInt(5000) + 1000; // Случайное время сна
                System.out.println(name + " засыпает на " + sleepTime + " мс.");
                Thread.sleep(sleepTime); // Засыпаем
                System.out.println(name + " забирает " + amount + " единиц товара со склада.");
                warehouse.removeGoods(amount, name); // Удаляем товар со склада
                System.out.println(name + " забрал " + amount + " единиц товара.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }
    }
}
