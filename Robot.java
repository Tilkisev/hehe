// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax m_leftDrive = new PWMSparkMax(0);
  private final PWMSparkMax m_rightDrive = new PWMSparkMax (1);
  private final DifferentialDrive m_robotDrive =
      new DifferentialDrive(m_leftDrive::set, m_rightDrive::set);
  private final XboxController m_controller = new XboxController(0);
  private final Timer m_timer = new Timer();
  public VictorSP SaseH = new VictorSP(2);
 /*tipi kütüphanAdı degiskenAdı = new kütüphaneAdı (port)*/ 
  public Robot ()  {
    SendableRegistry.addChild(m_robotDrive, m_leftDrive);
    SendableRegistry.addChild(m_robotDrive, m_rightDrive);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  /*robot baslatıldıgında tek seferlik calısası gerekenler */
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive.setInverted(true);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  /*zamanlayıcı sıfırlama felan */
  @Override
  public void autonomousInit() {
    m_timer.reset();
  }

  /** This function is called periodically during autonomous. */
  /* otonom kodu calıstıgı andan itibaren sürekli calısması gerekenler*/
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
     m_robotDrive.arcadeDrive(0.5, 0.0, false);
    } else {
      m_robotDrive.stopMotor(); // stop robot
    }
  }
   /*bir defaya mahsus kodlar */
  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}
/*------------------------------------------------------------------------ */
  public void hizli () {
    if (m_controller.getRawButton(1)){
  SaseH.set(1);
}
   else {
    SaseH.set(0);
   }

  }
  public void hizAyarlama() {

    if (m_controller.getRawButton(7)){
      m_robotDrive.setMaxOutput(1);
    }
    else if (m_controller.getRawButton(6))
    m_robotDrive.setMaxOutput(0.5);
    
  } 
  /*------------------------------------------------- */
 
  /** This function is called periodically during teleoperated mode. */
  /*uzaktan kontrol islem belirleme */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.setMaxOutput(0.5);
    m_robotDrive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX());
     hizli();
     hizAyarlama();
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() { }





}





