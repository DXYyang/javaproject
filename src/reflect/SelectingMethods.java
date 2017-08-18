package reflect;

import java.lang.reflect.Proxy;

/**
 * Created by admin on 2017/8/14.
 */
public class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy=(SomeMethods) Proxy.newProxyInstance(SelectingMethods.class.getClassLoader()
                ,new Class[]{SomeMethods.class},new MethodSelector(new Implementation()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting("bonobo");
        proxy.boring3();
    }
}
