

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.Date;


public class JavaRobotPainter implements  IRobotPainter{

    private Robot robot;
    private Runtime runtime;

    public JavaRobotPainter(String programName)
    {
        Setup();
        StartProgram(programName);

    }

    private void Setup()
    {
        try {
            robot = new Robot();
        }
        catch (AWTException awTexception)
        {
            awTexception.printStackTrace();
        }
    }

    private void StartProgram(String programName)
    {
        runtime = Runtime.getRuntime();
        try {
            runtime.exec(programName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        robot.delay(1000);
        robot.mouseMove(500,500);
        LeftClick();
    }

    /***
     * Draw a line from a start to an end coordinate
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void DrawLine(int x1, int y1, int x2, int y2)
    {
        int xMov = 0;
        int yMov = 0;
        int xMovInterval = 0;
        int yMovInterval = 0;

        int noOfMoves = 0;
        if(Math.abs(x2-x1) > Math.abs(y2-y1))
        {
            noOfMoves = Math.abs(x1-x2);
        }
        else
        {
            noOfMoves = Math.abs(y1-y2);
        }

        MouseRelease();
        robot.mouseMove(x1,y1);

        xMovInterval = (x2-x1) / noOfMoves;
        yMovInterval = (y2-y1) / noOfMoves;


        LeftClick();

        for (int i = 0; i < noOfMoves; i++) {

            xMov += xMovInterval;
            yMov += yMovInterval;
            robot.mouseMove(x1+xMov,y1+yMov);
            robot.delay(5);
            MousePress();
        }
        MouseRelease();

    }


    /***
     * Move to a coordinate without drawing
     * @param x1
     * @param y1
     */
    public void MoveTo(int x1, int y1)
    {
        robot.mouseMove(x1,y1);
    }

    /**
     * Draw a circle with steps of 10 degrees
     * @param radius
     * @param centerX
     * @param centerY
     */
    public void DrawCircle(int radius, int centerX, int centerY)
    {
        int degreeStep = 1;
        int noOfSteps = 360/degreeStep;
        Double angel = 0.0;

        for (int i = 0; i < noOfSteps; i++) {

            //angel+=degreeStep;
            angel = 2 * Math.PI * i / noOfSteps;

            Double x = centerX + radius * Math.cos( angel);
            Double y = centerY  + radius * Math.sin( angel);

            MoveTo(  x.intValue(),y.intValue());
            robot.delay(2);
            LeftClick();
        }
        MouseRelease();
    }



    //region Robot Mouse Helper methods
    private  void MousePress()
    {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    private  void MouseRelease()
    {
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    private  void LeftClick()
    {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private  void DoubleleftClick()
    {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

    }
    //endregion

}
