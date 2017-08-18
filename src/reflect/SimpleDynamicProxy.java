package reflect;

import java.lang.reflect.Proxy;

/**
 * Created by admin on 2017/8/14.
 */
public class SimpleDynamicProxy {
  public static void consumer(RealObject iface){
      iface.doSomething();
      iface.somethingElse("bonobo");
      iface.hello();
  }

    public static void main(String[] args) {
        RealObject real=new RealObject();
//        consumer(real);
        Interface2 proxy=( Interface2) Proxy.newProxyInstance(Interface2.class.getClassLoader(),
                new Class[]{Interface.class,Interface2.class},
                new DynamicProxyHandler(real));
        proxy.hello();
    }
}
