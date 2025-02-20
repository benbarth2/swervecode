// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants.RollerConstants;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

/** Class to run the rollers over CAN */
public class CANRollerSubsystem extends SubsystemBase {
  private final SparkMax rollerMotor;

  public CANRollerSubsystem() {
    rollerMotor = new SparkMax(13, MotorType.kBrushed);
    rollerMotor.setCANTimeout(250);

    SparkMaxConfig rollerConfig = new SparkMaxConfig();
    rollerConfig.voltageCompensation(10);
    rollerConfig.smartCurrentLimit(60);
    rollerMotor.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Ensure motor is stopped on initialization
    rollerMotor.set(0);
    rollerMotor.stopMotor();
  }


  @Override
  public void periodic() {
  }

  /** This is a method that makes the roller spin */
  public void runRoller(double forward, double reverse) {
    rollerMotor.set(forward - reverse);
  }
}