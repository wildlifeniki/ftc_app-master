/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Auto-test")  // @Autonomous(...) is the other common choice
public class TemplateOpMode_Linear_test extends LinearOpMode {

    //  Steps:
    //      1. Initialize robot
    //      2. Drive forward
    //      3. Turn left (90)
    //      4. Drive forward
    //      5. Line follow
    //      6. Detect color
    //      7. Push this button or opposite button (depending on color)
    //      8. Back up
    //      9. Turn right (90)
    //      10. Drive forward (to line)
    //      11. Turn left
    //      12. Line follow
    //      13. Detect color
    //      14. Push this button or opposite button (depending on color)


    //  STATES:
    //      1. Initialize
    //      2.  Drive to line
    //      3.  Detect Line
    //      4.  Line Follow
    //      5.  Read Color
    //      6.  Move Pusher
    //      7.  Push Button

    private enum state {
        INITIALIZE,
        DRIVE_TO_LINE,
        DETECT_LINE,
        LINE_FOLLOW,
        READ_COLOR,
        MOVE_PUSHER,
        PUSH_BUTTON,
        STOP,
    }





    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    private state currentstate = state.INITIALIZE;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            switch (currentstate)
            {
                case INITIALIZE:
                    // initialize motors, sensors, servo
                    // set encoders to 0
                    telemetry.addData("state", "intitialize") ;
                    sleep(500);
                    currentstate = state.DRIVE_TO_LINE ;
                    break;

                case DRIVE_TO_LINE:
                    // start both motors
                    // drive both motors forward "x" distance (rotations)
                    // turn left(red)/right(blue)90 degrees
                    // drive both motors forward "x" distance (rotations)
                    telemetry.addData("state", "driving") ;
                    sleep(500);
                    currentstate = state.DETECT_LINE ;
                    break;

                case DETECT_LINE:
                    // enable color sensor
                    // drive both motors forward slowly until detect line
                    telemetry.addData("state", "detecting line") ;
                    sleep(500);
                    currentstate = state.LINE_FOLLOW ;
                    break;

                case LINE_FOLLOW:
                    // follow edge of line
                    // enable the core range sensor
                    // drive to minimum sensing distance
                    // enable the distance sensor
                    // drive to 10 cm away from beacon
                    telemetry.addData("state", "following line- duh OwO") ;
                    sleep(500);
                    currentstate = state.READ_COLOR ;
                    break;

                case READ_COLOR:
                    // enable top color sensor
                    // read color (blue/red)
                    telemetry.addData("state", "reading colors, because you can do that now") ;
                    sleep(500);
                    currentstate = state.MOVE_PUSHER ;
                    break;

                case MOVE_PUSHER:
                    // move slider to correct position
                    telemetry.addData("state", "moving the thing that pushes the buttons") ;
                    sleep(500);
                    currentstate = state.PUSH_BUTTON ;
                    break;

                case PUSH_BUTTON:
                    // drive forward
                    // back up
                    telemetry.addData("state", "PUSHING THE BUTTONS FOR SPARTA!") ;
                    sleep(500);
                    currentstate = state.STOP ;
                    break;

                default:
                    break;
            }

            telemetry.update();

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}
