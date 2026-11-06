package first.robot.Commands;

import org.wpilib.command2.Command;

import com.ctre.phoenix6.hardware.TalonFX;

public class ChangeAngle extends Command {

    TalonFX AngleMotor;
    double Direction;
    
    public ChangeAngle(TalonFX AngleMotor , double Direction) {
        this.AngleMotor = AngleMotor;
        this.Direction = Direction;
    }

    
    @Override
    public void initialize() {
    }
    @Override
    public void execute() {
        AngleMotor.setThrottle(0.5*Direction);
    }
    @Override
    public void end(boolean interrupted) {
        AngleMotor.setThrottle(0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

