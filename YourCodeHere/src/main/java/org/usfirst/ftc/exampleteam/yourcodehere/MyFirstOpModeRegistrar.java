package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.swerverobotics.library.SynchronousOpMode;

/**
 * A skeletal example of a do-nothing first OpMode. Go ahead and change this code
 * to suit your needs, or create sibling OpModes adjacent to this one in the same
 * Java package.
 */
@TeleOp(name="Left Right Motor")
public class MyFirstOpModeRegistrar extends SynchronousOpMode
    {
    // Declare Motors
        DcMotor motorLeft = null;
        DcMotor motorRight = null;

    @Override public void main() throws InterruptedException
    {
        // Initialize motors
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        // Set motor channel modes
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Reverse left drive motors so we don't spin when applying full power
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        //  Wait for the game to start
        waitForStart();

        // Go go gadget robot!
        while (opModeIsActive())
            {
            if (updateGamepads())
                {
                    // Tank drive
                    motorLeft.setPower(Range.clip(gamepad1.left_stick_y, -1, 1));
                    //System.out.println("Left:"+gamepad1.left_stick_y);
                    motorRight.setPower(Range.clip(gamepad1.right_stick_y, -1, 1));
                    //System.out.println("Left:"+gamepad1.right_stick_y);
                }
            telemetry.update();
            idle();
            }
        }
    }
