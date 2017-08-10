package concurrency.restaurant;

import concurrency.restaurant.menu.Food;

/**
 * Created by admin on 2017/8/8.
 */
public class Plate {
    private  final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}
