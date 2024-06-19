package Reader_Writer_2;

public class Main {
    public static void main(String[] args) {
        Database database = new Database(); // Создаем базу данных
        Thread reader1 = new Thread(new Reader(database, "Читатель 1")); // Создаем первого читателя
        Thread reader2 = new Thread(new Reader(database, "Читатель 2")); // Создаем второго читателя
        Thread writer1 = new Thread(new Writer(database, "Писатель 1")); // Создаем первого писателя
        Thread writer2 = new Thread(new Writer(database, "Писатель 2")); // Создаем второго писателя

        reader1.start(); // Запускаем поток первого читателя
        reader2.start(); // Запускаем поток второго читателя
        writer1.start(); // Запускаем поток первого писателя
        writer2.start(); // Запускаем поток второго писателя
    }
}
