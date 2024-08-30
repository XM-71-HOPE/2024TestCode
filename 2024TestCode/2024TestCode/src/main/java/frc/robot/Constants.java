package frc.robot;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

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
}
