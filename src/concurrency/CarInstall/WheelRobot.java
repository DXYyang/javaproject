package concurrency.CarInstall;

/**
 * Created by admin on 2017/8/10.
 */
public class WheelRobot extends Robot {
    public WheelRobot(RobotPool p) {

        super(p);
    }
    @Override
    protected void performService() {
     System.out.println(this+" installing Wheels");
     assembler.car().addWheels();
    }
}
