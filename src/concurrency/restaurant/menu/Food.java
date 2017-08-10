package concurrency.restaurant.menu;

/**
 * Created by admin on 2017/8/8.
 */
public interface Food {
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }
    enum MainCourse implements Food{
        LASAGNE,BURRITO,PAD_THAI,
        LENTILS,HUMMOUS,VINDALOO;
    }
    enum Dessert implements Food{
        TIRAMISU,GELATO,BLACK_FOREST_CAKE,
        FRUIT,CREME_CARAMEL;
    }
    enum Coffee implements Food{
        BALCK_COFFEE,DECAF_COFFEE,ESPRESSO,
        LATTE,CAPPUCCINO,TEA,HERB_TEA;
    }

}
