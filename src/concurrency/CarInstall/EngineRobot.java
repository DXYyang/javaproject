package concurrency.CarInstall;

/**
 * Created by admin on 2017/8/10.
 */
public class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool){
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this+" installing engine");
        assembler.car().addEngine();
    }

}
