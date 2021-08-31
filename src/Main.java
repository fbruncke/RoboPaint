public class Main {

    public static void main(String[] args) {

        //create the robot painter and start the program to paint on
        IRobotPainter jrp = new JavaRobotPainter("mspaint.exe");


        //Draw triangle
        jrp.DrawLine(300,500,600,800);
        jrp.DrawLine(600,800,300,800);
        jrp.DrawLine(300,800,300,500);


        jrp.DrawCircle(100,300,300);


        System.exit(0);

    }

}
