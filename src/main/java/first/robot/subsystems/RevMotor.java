package first.robot.subsystems;

import org.wpilib.command2.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;

public class RevMotor extends SubsystemBase {

    private final TalonFX Motor_1;
    private final TalonFX Motor_2;

    public RevMotor(TalonFX Motor_1, TalonFX Motor_2) { 
        this.Motor_1 = Motor_1;
        this.Motor_2 = Motor_2;
    }

    public void runShooter(double voltage) {
        Motor_1.setThrottle(voltage);
        Motor_2.setThrottle(voltage);
    }
    public void stopShooter() {
        Motor_1.setThrottle(0);
        Motor_2.setThrottle(0);
    }

    private final double RPM_TOLERANCE = 50.0; 

    public boolean isAtTargetRPM(double targetRPM) {
        double motor1RPM = Motor_1.getVelocity().getValueAsDouble() * 60.0;
        double motor2RPM = Motor_2.getVelocity().getValueAsDouble() * 60.0;

        return Math.abs(motor1RPM - targetRPM) <= RPM_TOLERANCE && 
               Math.abs(motor2RPM - targetRPM) <= RPM_TOLERANCE;
    }
}
        
