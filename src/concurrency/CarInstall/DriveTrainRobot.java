package concurrency.CarInstall;

/**
 * Created by admin on 2017/8/10.
 */
public class DriveTrainRobot extends Robot{
    public DriveTrainRobot(RobotPool p) {
        super(p);
    }
    protected void performService(){
        System.out.println(this+" installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}
