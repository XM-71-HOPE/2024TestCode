// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.ManualDrive;
import frc.robot.subsystems.*;

public class RobotContainer {

  public static TankChassis m_tankChassis = null;
  public static CommandXboxController m_Controller = new CommandXboxController(0);
  public static Shooter m_Shooter = Shooter.GetInstance();
  public static Blocker m_Blocker = Blocker.GetInstance();
  public static Arm m_Arm = Arm.GetInstance();
  public static TankChassis m_Chassis = TankChassis.m_Instance.GetInstance();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_Chassis.setDefaultCommand(new ManualDrive());
    m_Controller.a().whileTrue(new AutoShoot());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
