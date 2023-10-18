package AD221.Smirnov;
import java.util.LinkedList;
import java.util.Queue;

class Item {
    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        // Перевірка на негативне значення ціни
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    // Метод для підвищення ціни на вказаний відсоток
    public void increasePrice(float percent) {
        price += price * percent / 100;
    }

    // Метод для зниження ціни на вказаний відсоток
    public void decreasePrice(float percent) {
        price -= price * percent / 100;
    }

    public float getPrice() {
        return price;
    }
}

class Cart {
    private Item[] items;
    private int maxSize;
    private Queue<Item> queue;

    public Cart(int maxSize) {
        this.maxSize = maxSize;
        items = new Item[maxSize];
        queue = new LinkedList<>(); // Використовуємо чергу (Queue) на базі LinkedList
    }

    public void addItem(Item item) {
        if (queue.size() < maxSize) {
            queue.offer(item); // Додаємо товар в чергу
        } else {
            System.out.println("Кошик повний, не можна додавати більше товарів.");
        }
    }

    public void removeItem() {
        if (!queue.isEmpty()) {
            queue.poll(); // Видаляємо перший товар з черги
        } else {
            System.out.println("Кошик порожній, немає товарів для видалення.");
        }
    }

    public float calculateTotalPrice() {
        float totalPrice = 0;
        for (Item item : queue) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public void increasePrices(float percent) {
        for (Item item : queue) {
            item.increasePrice(percent);
        }
    }

    public void decreasePrices(float percent) {
        for (Item item : queue) {
            item.decreasePrice(percent);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(10);

        cart.addItem(new Item("Товар 1", 100.0f));
        cart.addItem(new Item("Товар 2", 50.0f));
        cart.addItem(new Item("Товар 3", 75.0f));
        cart.addItem(new Item("Товар 4", 120.0f));
        cart.addItem(new Item("Товар 5", 80.0f));

        System.out.println("Сума цін товарів у кошику: " + cart.calculateTotalPrice());

        cart.increasePrices(15);
        System.out.println("Сума цін після підвищення на 15%: " + cart.calculateTotalPrice());

        cart.decreasePrices(30);
        System.out.println("Сума цін після зниження на 30%: " + cart.calculateTotalPrice());
    }
}
