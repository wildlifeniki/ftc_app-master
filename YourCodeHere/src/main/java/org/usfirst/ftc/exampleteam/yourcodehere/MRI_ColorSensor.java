import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name="Color Sensor")
public class MRI_ColorSensor extends LinearOpMode {

    ColorSensor colorSensor;
    TouchSensor touch;
    DeviceInterfaceModule CDI;

    @Override
    public void runOpMode() throws InterruptedException  {
        colorSensor = hardwareMap.colorSensor.get("color");
        touch = hardwareMap.touchSensor.get("t");
        CDI = hardwareMap.deviceInterfaceModule.get("Device Interface Module 1");

        boolean touchState = false;
        boolean LEDState = true;

        waitForStart();

        colorSensor.enableLed(LEDState);

        float hsvValues[] = {0, 0, 0};

        while (opModeIsActive()) {

            if (!touchState && touch.isPressed()) {
                touchState = true;
                LEDState = !LEDState;
                colorSensor.enableLed(LEDState);
            }
            if (!touch.isPressed()) {
                touchState = false;
            }

            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            telemetry.addData("2 Clear", colorSensor.alpha());
            telemetry.addData("3 Red ", colorSensor.red());
            telemetry.addData("4 Green", colorSensor.green());
            telemetry.addData("5 Blue", colorSensor.blue());
            telemetry.addData("6 Hue", hsvValues[0]);
            telemetry.addData("7 Touch", touch.isPressed());

            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                CDI.setLED(1, true);                    //Red on
                CDI.setLED(0, false);                   //Blue off
            }
            else if(colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
                CDI.setLED(1, false);                   //Red off
                CDI.setLED(0, true);                    //Blue on
            }
            else{
                CDI.setLED(1, false);                   //red off
                CDI.setLED(0, false);                    //blue off
            }

            waitOneFullHardwareCycle();

        }

    }
}