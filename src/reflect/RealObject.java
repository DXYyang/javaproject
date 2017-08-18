package reflect;

/**
 * Created by admin on 2017/8/14.
 */
public class RealObject implements Interface,Interface2{
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
    System.out.println("somethingElse "+args);
    }

    @Override
    public void hello() {
        System.out.println("hello");
    }
}
