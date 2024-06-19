package Supplier_Consummer_1;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(100); // Создаем склад с вместимостью 100 единиц товара
        Thread supplier1 = new Thread(new Supplier(warehouse, "Поставщик 1")); // Создаем первого поставщика
        Thread supplier2 = new Thread(new Supplier(warehouse, "Поставщик 2")); // Создаем второго поставщика
        Thread consumer1 = new Thread(new Consumer(warehouse, "Потребитель 1")); // Создаем первого потребителя
        Thread consumer2 = new Thread(new Consumer(warehouse, "Потребитель 2")); // Создаем второго потребителя

        supplier1.start(); // Запускаем поток первого поставщика
        supplier2.start(); // Запускаем поток второго поставщика
        consumer1.start(); // Запускаем поток первого потребителя
        consumer2.start(); // Запускаем поток второго потребителя
    }
}

