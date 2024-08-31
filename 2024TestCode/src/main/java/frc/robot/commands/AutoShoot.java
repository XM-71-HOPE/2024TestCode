package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.RobotContainer;
import frc.robot.Library.LimelightHelper.LimelightHelpers;
import frc.robot.Library.team2910.control.PidController;
import frc.robot.Library.team3476.net.editing.LiveEditableValue;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.BlockerConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.AutoShootConstants;

/** An example command that uses an example subsystem. */
public class AutoShoot extends Command {
  AutoShootState m_State;
  double m_TargetDegree;
  double m_TargetRPS;
  PIDController m_PidController;

  public AutoShoot() {
    addRequirements(RobotContainer.m_Shooter);
    addRequirements(RobotContainer.m_Arm);
    addRequirements(RobotContainer.m_Blocker);
  }

  enum AutoShootState {
    Aim, Accelerate, Shoot, End;
  }

  void Aim() {
    RobotContainer.m_Shooter.SetRPS(AutoShootConstants.RPSInAdvance); /// Pre Accelerating
    RobotContainer.m_Arm.SetArmDegree(m_TargetDegree);
    if(RobotContainer.m_Arm.IsAtTargetDegree()){
      m_State = AutoShootState.Accelerate;
    }
  }

  void Accelerate() {
    RobotContainer.m_Arm.SetArmDegree(m_TargetDegree);
    RobotContainer.m_Shooter.SetRPS(m_TargetRPS);
    if (RobotContainer.m_Arm.IsAtTargetDegree() && RobotContainer.m_Shooter.IsAtTargetRPS()) {
      m_State = AutoShootState.Shoot;
    }

  }

  void Shoot() {
    if (RobotContainer.m_Blocker.HasNote()) {
      RobotContainer.m_Blocker.SetOutPut(BlockerConstants.GiveNoteOutput);
    } else {
      m_State = AutoShootState.End;
    }
  }

  @Override
  public void initialize() {
    m_State = AutoShootState.Accelerate;
    m_TargetDegree = AutoShootConstants.ArmAngle;
    m_TargetRPS = AutoShootConstants.ShooterRPS;
  }

  @Override
  public void execute() {
    if (m_State == AutoShootState.Aim) {
      Aim();
    }
    if (m_State == AutoShootState.Accelerate) {
      Accelerate();
    }
    if (m_State == AutoShootState.Shoot) {
      Shoot();
    }

  }

  @Override
  public void end(boolean interrupted) {
    // RobotContainer.m_Swerve.drive(new Translation2d(), 0., false);
    RobotContainer.m_Arm.SetArmDegree(ArmConstants.ArmDefaultDegree);
    RobotContainer.m_Shooter.SetPCT(0.);
    ;
  }

  @Override
  public boolean isFinished() {
    if (m_State == AutoShootState.End)
      return true;
    // if (RobotContainer.m_Controller.getAButton())
    //   return false;
    // else
    //   return true;
    return false;
  }
}