// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.command2.Command;
import org.wpilib.command2.Commands;
import org.wpilib.command2.button.CommandXboxController;
import org.wpilib.hardware.bus.CANPort;

import first.robot.Commands.ChangeAngle;
import first.robot.Commands.Shoot;
import first.robot.Commands.SpinMotor;
import first.robot.subsystems.RevMotor;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;


public class RobotContainer {

  CANBus id_1 = new CANBus(CANPort.CAN_D1);
  final public TalonFX Motor_1 = new TalonFX(1,id_1);


  CANBus id_2 = new CANBus(CANPort.CAN_D2);
  final public TalonFX Motor_2 = new TalonFX(2,id_2);

  CANBus id_3 = new CANBus(CANPort.CAN_D3);
  final public TalonFX FeederMotor = new TalonFX(3, id_3);

  CANBus id_4 = new CANBus(CANPort.CAN_D4);
  final public TalonFX AngleMotor = new TalonFX(4, id_4);

  private final RevMotor m_revMotor = new RevMotor(Motor_1, Motor_2);


  CommandXboxController driver = new CommandXboxController(0);
  

  //-------------------------------------------


  //-------------------configurtion setup-------
  public void configureMotor(TalonFX MotorConfig) {
    TalonFXConfiguration toApply = new TalonFXConfiguration();

    MotorConfig.getConfigurator().apply(new TalonFXConfiguration());
    toApply.CurrentLimits.StatorCurrentLimit = 80.0; 
    toApply.CurrentLimits.StatorCurrentLimitEnable = true;
    toApply.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    toApply.MotorOutput.NeutralMode = com.ctre.phoenix6.signals.NeutralModeValue.Coast;



    // var status = MotorConfig.getConfigurator().apply(toApply, 0.050);
    // if (!status.isOK()) {
    //     System.out.println("Could not configure motor: " + status.toString());
    //   }
  }
  //-------------------------------------------




  //pid_control spin1 = new pid_control();

  public RobotContainer() {
    Slot0Configs pidGains = new Slot0Configs();
    pidGains.kP = 0.0;
    pidGains.kD = 0.0;
    pidGains.kI = 0.0;
    Motor_1.getConfigurator().apply(pidGains);


    //-------------------------------------------
    configureMotor(Motor_1);
    configureMotor(Motor_2);
    configureMotor(FeederMotor);
    configureMotor(AngleMotor);
    //-------------------------------------------

    configureBindings();
  }

  private void configureBindings() {

      //------ create the command

      //------- all of my commands
      driver.rightBumper().whileTrue(new ChangeAngle(AngleMotor, 1.0));
      driver.leftBumper().whileTrue(new ChangeAngle(AngleMotor, -1.0));
      driver.a().whileTrue(new SpinMotor(Motor_1,Motor_2));
      driver.rightTrigger().whileTrue(new Shoot(m_revMotor, FeederMotor));
      //-------





  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
