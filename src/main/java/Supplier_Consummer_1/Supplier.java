package Supplier_Consummer_1;

import java.util.Random;

public class Supplier implements Runnable {
    private final Warehouse warehouse;
    private final Random random = new Random();
    private final String name;

    public Supplier(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int amount = random.nextInt(10) + 1; // Случайное количество товара для добавления
                int sleepTime = random.nextInt(5000) + 1000; // Случайное время сна
                System.out.println(name + " засыпает на " + sleepTime + " мс.");
                Thread.sleep(sleepTime); // Засыпаем
                System.out.println(name + " доставляет " + amount + " единиц товара на склад.");
                warehouse.addGoods(amount, name); // Добавляем товар на склад
                System.out.println(name + " доставил " + amount + " единиц товара.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
        }
    }
}
