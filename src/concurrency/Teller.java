package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/7.
 */
public class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;
    private int customerServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {
        customers = cq;
    }

    @Override
    public synchronized int compareTo(Teller o) {
        return customerServed < o.customerServed ? -1 : (customerServed == o.customerServed ? 0 : 1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {

                    while (!servingCustomerLine) {
                        wait();
                    }
                    Customer customer = customers.take();
                    TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                    customerServed++;

                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public synchronized void doSomethingElse() {
        customerServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving:" + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller " + id + " ";
    }

    public String shortString() {
        return "T" + id;
    }
}
