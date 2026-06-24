class Item {
    String name;
    int price;

    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class Slot {
    Item item;
    int quantity;

    Slot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}