package frc.robot;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import java.awt.geom.Point2D;

public class Constants {
    public static class ChassisConstants {
        public static final int LeftMotorID = 1;
        public static final int RghtMotorID = 2;
        public static final NeutralModeValue MotorNeutralMode = NeutralModeValue.Brake;
        public static final double MotorCurrentLimit = 0;
        public static final double MotorkP = 0;
        public static final double MotorkI = 0;
        public static final double MotorkD = 0;
        public static final double DriveGearRatio = 0;
        public static final InvertedValue LMotorInverted = null;
        public static final InvertedValue RMotorInverted = null;
        public static final double WheelDiameter = 0;
        public static final double TurningDV = 0;
        public static final double MaxSpd = 0;

    }

    public static class GlobalConstants {
        public static final float INF = (float) 1e10;
    }

    public static class ArmConstants {

        public static final int ArmLeft_ID = 0;
        public static final int ArmRight_ID = 0;
        public static final double ArmDefaultDegree = 0;
        public static final double ArmAcceleration = 0;
        public static final double ArmVelocity = 0;
        public static final double ArmTolerence = 0;
        public static double kG;
        public static double kD;
        public static double kP;
        public static double kI;
        public static double kS;

    }

    public static class BlockerConstants {
        public static final int Blocker_ID = 30;
        public static final int Sensor_ID = 2;
    
        public static final double NoteOutOutput = -1.;//传递球到shooter前
        public static final double NoteInOutput = 1.;//传递球到shooter前
        public static final double GiveNoteOutput = 1.;//shooter加速完毕后给球
        public static final double AMPOutput = -1.;
      }

    public static class ShooterConstants {
        public static final int ShooterLeft_ID=0;
        public static final int ShooterRight_ID=23;
    
        public static final double kP = 0.1;
        public static final double kI = 0.;
        public static final double kD = 0.012;
        public static final double kS = 0.25;
        public static final double kV = 0.11458;
    
        public static final double ShooterDifferenceTolerence = 0.5;
        public static final double ShooterSpeedTolerence = 1.5;
    
        public static final double ShooterAMPRPS = 0.;
        public static final double ShooterManualSPKRPS = 0.;
      }

    public static class AutoShootConstants{
        public static final double kP=0.12;
        public static final double kI=0.0005;
        public static final double kD=0.0;
        public static final double DegreeTolerance=3.;
        public static final double VelocityTolerance=4.;
        public static final double RPSInAdvance=30;
        public static final double HightDifference=1.;//单位暂定meter

        public static final Point2D[] ArmPoints={
          new Point2D.Double(18.04,-33),
          new Point2D.Double(13.31,-39),
          new Point2D.Double(7.15,-50),
          new Point2D.Double(2.86,-56),
          new Point2D.Double(-2.31,-62),
          new Point2D.Double(-6.91,-70)
        };
        public static final Point2D[] RPSPoints={
          new Point2D.Double(18.04,40),
        
          new Point2D.Double(-2.34,50),
        
          new Point2D.Double(-6.89,60)
        };

        public static final Point2D[] DisToArmPoints = {
        
          new Point2D.Double(0.9,-33),
          new Point2D.Double(1.37,-37),
          new Point2D.Double(2.08,-45),
          new Point2D.Double(2.54,-55),
          new Point2D.Double(3.08,-61),
        
          new Point2D.Double(3.5,-63),
          new Point2D.Double(4.07,-65),
          new Point2D.Double(5.16,-67),
          new Point2D.Double(5.5,-70)
        };

        public static final Point2D[] DisToRPSPoints = {
          new Point2D.Double(1.66,40),
        
          new Point2D.Double(3.07,50),
        
          new Point2D.Double(5.16,55)
        };

        public static final InterpolatingDoubleTreeMap ArmTable=new InterpolatingDoubleTreeMap();
        public static final InterpolatingDoubleTreeMap RPSTable=new InterpolatingDoubleTreeMap();
        public static final InterpolatingDoubleTreeMap DisToArmTable = new InterpolatingDoubleTreeMap();
        public static final InterpolatingDoubleTreeMap DisToRPSTable = new InterpolatingDoubleTreeMap();
        public static final double MaxRange = 6;
        public static final double CoastVelocity = 2.;
  }

}
