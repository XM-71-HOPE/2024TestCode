package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.sim.ChassisReference;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.*;
import frc.robot.Library.NumberLimiter;

public class TankChassis extends SubsystemBase {
    public static TankChassis m_Instance;

    public TankChassis GetInstance() { // to ensure a static access
        return m_Instance == null ? m_Instance = new TankChassis() : m_Instance;
    }

    private static TalonFX m_LeftDriveMotor = new TalonFX(ChassisConstants.LeftMotorID, "rio");
    private static TalonFX m_RghtDriveMotor = new TalonFX(ChassisConstants.RghtMotorID, "rio");
    TalonFXConfiguration m_LeftConfig = new TalonFXConfiguration();
    TalonFXConfiguration m_RghtConfig = new TalonFXConfiguration();

    private double m_targetLeftSpeed = 0., m_targetRghtSpeed = 0.;

    final MotionMagicVelocityVoltage m_VelocityVoltage = new MotionMagicVelocityVoltage(0, 0, false, 0, 0, false, false, false);
    

    public TankChassis() {
        MotorConfig();
    }

    private void MotorConfig() {
        /* Neutral Mode */
        m_LeftConfig.MotorOutput.NeutralMode = ChassisConstants.MotorNeutralMode;

        /* Current Limitting */
        m_LeftConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
        m_LeftConfig.CurrentLimits.SupplyCurrentLimit = ChassisConstants.MotorCurrentLimit;

        /* Gear Ratio and Wrapping Config */
        m_LeftConfig.Feedback.SensorToMechanismRatio = ChassisConstants.DriveGearRatio;
        m_LeftConfig.ClosedLoopGeneral.ContinuousWrap = true;

        /* PID Config */
        m_LeftConfig.Slot0.kP = ChassisConstants.MotorkP;
        m_LeftConfig.Slot0.kI = ChassisConstants.MotorkI;
        m_LeftConfig.Slot0.kD = ChassisConstants.MotorkD;

        /* LR Sync */
        m_RghtConfig = m_LeftConfig;

        /* Inverts */
        m_LeftConfig.MotorOutput.Inverted = ChassisConstants.LMotorInverted;
        m_RghtConfig.MotorOutput.Inverted = ChassisConstants.RMotorInverted;

        /* Application */
        m_LeftDriveMotor.getConfigurator().apply(m_LeftConfig);
        m_RghtDriveMotor.getConfigurator().apply(m_RghtConfig);

    }

    public void SetVelocity(double _LeftV, double _RghtV) { // param is m/s
        m_targetLeftSpeed = NumberLimiter.Limit(-ChassisConstants.MaxSpd, ChassisConstants.MaxSpd, _LeftV);
        m_targetRghtSpeed = NumberLimiter.Limit(-ChassisConstants.MaxSpd, ChassisConstants.MaxSpd, _RghtV);

        double _LeftOmega = m_targetLeftSpeed / (ChassisConstants.WheelDiameter * Math.PI); // param is rps;
        double _RghtOmega = m_targetRghtSpeed / (ChassisConstants.WheelDiameter * Math.PI); // param is rps;

        /* Drive the Motors */
        m_LeftDriveMotor.setControl(m_VelocityVoltage.withVelocity(_LeftOmega));
        m_RghtDriveMotor.setControl(m_VelocityVoltage.withVelocity(_RghtOmega));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("LTargetSpd", m_targetLeftSpeed);
        SmartDashboard.putNumber("RTargetSpd", m_targetRghtSpeed);
    }
}
