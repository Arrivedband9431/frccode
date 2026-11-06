package first.robot.Commands;

import com.ctre.phoenix6.hardware.TalonFX;
import first.robot.subsystems.RevMotor;
import org.wpilib.command2.Command;
public class Shoot extends Command {
    
    private final RevMotor m_revMotor;
    private final TalonFX m_feeder;  
    private final double m_targetRPM = 5000.0;

    public Shoot(RevMotor revMotor, TalonFX feeder) { 
        this.m_revMotor = revMotor;
        this.m_feeder = feeder;

        addRequirements(m_revMotor);
    }
    
    @Override
    public void initialize() {
        m_revMotor.runShooter(1.0); 
    }

    @Override
    public void execute() {
        m_revMotor.runShooter(1.0);

        if (m_revMotor.isAtTargetRPM(m_targetRPM)) { 
            m_feeder.setThrottle(0.5); 
        } else {
            m_feeder.setThrottle(0.0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_feeder.setThrottle(0.0);
        m_revMotor.stopShooter();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
