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

  public AutoShoot(int _ButtonID){
    addRequirements(RobotContainer.m_Shooter);
    addRequirements(RobotContainer.m_Arm);
  }
  enum AutoShootState {
    Aim,Accelerate,Shoot,End;
  }
  
  void Aim(){
        double _Omega=0.; 
        RobotContainer.m_Shooter.SetRPS(1000);
        if(LimelightHelpers.getTV("limelight"))
        {
          if(!m_PidController.atSetpoint())
          {
            //_Omega=m_PidController.calculate(LimelightHelpers.getTX(RobotContainer.m_SPKRLimelight));//从摄像头获取数据计算角度
          }
          else
          {
          //RobotContainer.m_Swerve.drive(new Translation2d(), _Omega, false);//底盘角度纠正
          m_State=AutoShootState.Accelerate;
          }
        }
        //RobotContainer.m_Swerve.drive(new Translation2d(), _Omega, false);//底盘角度纠正
  }

  void Accelerate(){
    RobotContainer.m_Arm.SetArmDegree(m_TargetDegree);
    RobotContainer.m_Shooter.SetRPS(m_TargetRPS);
    if(RobotContainer.m_Arm.IsAtTargetDegree()&&RobotContainer.m_Shooter.IsAtTargetRPS())
    {
        m_State=AutoShootState.Shoot;
    }
        
  }

  void Shoot(){
    if(RobotContainer.m_Blocker.HasNote())
    {
      RobotContainer.m_Blocker.SetOutPut(BlockerConstants.GiveNoteOutput);
    }
    else
    {
      m_State=AutoShootState.End;
    }
  }


  @Override
  public void initialize() {
    m_State=AutoShootState.Accelerate;
    m_PidController=new PIDController(AutoShootConstants.kP, AutoShootConstants.kI, 0.);
    m_PidController.setSetpoint(0.);
    //m_PidController.setIZone(5);
    m_PidController.setTolerance(AutoShootConstants.DegreeTolerance);
    m_PidController.reset();
  }

  @Override
  public void execute() {
    if(m_State==AutoShootState.Aim){
        Aim();
    }
    if(m_State==AutoShootState.Accelerate){
        Accelerate();
    }
    if(m_State==AutoShootState.Shoot){
        Shoot();
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    //RobotContainer.m_Swerve.drive(new Translation2d(), 0., false);
    RobotContainer.m_Arm.SetArmDegree(ArmConstants.ArmDefaultDegree);
    RobotContainer.m_Shooter.SetPCT(0.);;
  }

  @Override
  public boolean isFinished() {
    if(m_State==AutoShootState.End) return true;
    if(RobotContainer.m_Controller.getAButton()) return false;
    else return true;
  }
}