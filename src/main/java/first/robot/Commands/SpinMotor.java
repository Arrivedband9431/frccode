package first.robot.Commands;
import org.wpilib.command2.Command;
import com.ctre.phoenix6.hardware.TalonFX;


public class SpinMotor extends Command {
    private final TalonFX Motor_1;
    private final TalonFX Motor_2;
    public SpinMotor(TalonFX Motor_1, TalonFX Motor_2) { 
        this.Motor_1 = Motor_1;
        this.Motor_2 = Motor_2;
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Motor_1.setThrottle(0.5);
        Motor_2.setThrottle(0.5);
    }
    @Override
    public void end(boolean interrupted) {
        Motor_1.setThrottle(0.0);
        Motor_2.setThrottle(0.0);
    }
    @Override
    public boolean isFinished() {
        return false;
    }

}
