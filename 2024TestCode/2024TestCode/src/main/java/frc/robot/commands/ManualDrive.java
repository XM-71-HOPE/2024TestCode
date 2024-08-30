package frc.robot.commands;


import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Constants.ChassisConstants;
import frc.robot.subsystems.TankChassis;

public class ManualDrive extends Command{
    double _LeftV = 0.;
    double _RghtV = 0.;
    @Override
    public void initialize(){
        addRequirements(RobotContainer.m_tankChassis);
    }

    @Override
    public void execute(){
        double controllerX = RobotContainer.m_Controller.getLeftX();
        double controllerY = RobotContainer.m_Controller.getRightY();
        double LRDelta = controllerY*ChassisConstants.TurningDV;        //LeftV-RghtV, positive turns clockwise
        double BaseSpd = controllerX*ChassisConstants.MaxSpd;
        double LeftV = BaseSpd + LRDelta/2.;
        double RghtV = BaseSpd - LRDelta/2.;

        /* Optimization */

        if (Math.abs(LeftV)>=ChassisConstants.MaxSpd){
            LeftV = Math.signum(LeftV)*ChassisConstants.MaxSpd;
            RghtV = Math.signum(LeftV)*ChassisConstants.MaxSpd-LRDelta;
        }
        if(Math.abs(RghtV)>=ChassisConstants.MaxSpd){
            RghtV = Math.signum(RghtV)*ChassisConstants.MaxSpd;
            LeftV = Math.signum(RghtV)*ChassisConstants.MaxSpd+LRDelta;
        }

        /* Drive */
        RobotContainer.m_tankChassis.SetVelocity(LeftV, RghtV);
        
    }


    

    @Override 
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
