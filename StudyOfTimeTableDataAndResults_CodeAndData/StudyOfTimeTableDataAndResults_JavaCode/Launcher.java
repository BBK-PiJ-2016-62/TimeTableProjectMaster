import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public class Launcher {

    //all paths of data files and the path of the folder for storing result files are stated here!

    //path and file name of data files
    //Draft Time Table
    public static final String draftTimeTablePath = "StudyOfTimeTableDataAndResults\\DraftTimeTable.csv";
    //module list
    public static final String moduleListPath = "StudyOfTimeTableDataAndResults\\ModuleList.csv";
    //room details list
    public static final String roomListPath= "StudyOfTimeTableDataAndResults\\RoomList.csv";

    //path of the folder for storing result files
    public static final String pathOfTheFolderForKeepingResultFiles= "StudyOfTimeTableDataAndResults";

    //"ModuleList_KeepForAWhile.csv" "SM0477.csv"

    public static void main(String[] args) {

        CheckRoomSizeSufficiency crss = new CheckRoomSizeSufficiencyImpl();
        InputAndTidyUpTimeTable iattt=new InputAndTidyUpTimeTableImpl();
        TeachingOverlapAndHours toah = new TeachingOverlapAndHoursImpl();
        CheckTutorialGroupSufficiency ctgs = new CheckTutorialGroupSufficiencyImpl();

        ArrayList<ArrayList<String>> inputTimeTable=iattt.inputAndTidyTimeTable();
        JFrame f=new JFrame("Study of Timetable");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font=new Font("Consolas",Font.ITALIC,15);
        JButton b1=new JButton("Click here to find: \"Task 1: Clashes in teacher same time in different classes\"");
        b1.setBounds(10,15,1100,30);
        b1.setHorizontalAlignment(10);
        b1.setFont(font);
        b1.setForeground(Color.BLUE);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> teachersTimeCrashesResult =
                        new ArrayList<>(toah.teachersHavingTimeClashes(inputTimeTable));
                String outputFileName = produceCurrentTimeTextFileNameAndNotifyOnScreen
                        (teachersTimeCrashesResult, "Task1");
                JOptionPane.showMessageDialog(
                        null, "Result for Task 1 is obtained and is stored in file "
                                +outputFileName+" which is " +
                                "in the folder "+pathOfTheFolderForKeepingResultFiles+" on Desktop.");
            }});
        JButton b2=new JButton("Click here to find: \"Task 2: Teachers who teach more than six hours a day.\"");
        b2.setBounds(10,60,1100,30);
        b2.setHorizontalAlignment(10);
        b2.setFont(font);
        b2.setForeground(Color.BLUE);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<String> teachersMoreThanSixHoursADayResult =
                        new ArrayList<>(toah.teachesTeachMoreThanSixHoursADay(inputTimeTable));
                String outputFileName = produceCurrentTimeTextFileNameAndNotifyOnScreen
                        (teachersMoreThanSixHoursADayResult, "Task2");
                JOptionPane.showMessageDialog(
                        null, "Result for Task 2 is obtained and is stored in file "
                                +outputFileName+" which is " +
                                "in the folder "+pathOfTheFolderForKeepingResultFiles+" on Desktop.");
            }
        });
        JButton b3=new JButton("Click here to find: " +
                "\"Task 3: Whether groups set for each module need to be increased or reduced and for how many.\"")/*("Click here to find: \"Teachers who teach more than six hours a day.\"")*/;
        b3.setBounds(10,105,1100,30);
        b3.setHorizontalAlignment(10);
        b3.setFont(font);
        b3.setForeground(Color.BLUE);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<String> groupsSetForEachModuleSufficiencyResult =
                        new ArrayList<>(ctgs.checkTutorialGroupSufficiency(inputTimeTable));
                String outputFileName = produceCurrentTimeTextFileNameAndNotifyOnScreen
                        (groupsSetForEachModuleSufficiencyResult, "Task3");
                JOptionPane.showMessageDialog(
                        null, "Result for Task 3 is obtained and is stored in file "
                                +outputFileName+" which is " +
                                "in the folder "+pathOfTheFolderForKeepingResultFiles+" on Desktop.");
            }
        });
        JButton b4=new JButton("Click here to find: \"Task 4: Whether size of lecture rooms or labs assigned to each " +
                "module is ok with student numbers.\"");
        b4.setBounds(10,150,1100,30);
        b4.setHorizontalAlignment(10);
        b4.setFont(font);
        b4.setForeground(Color.BLUE);
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<String> roomSizeSufficiencyResult =
                        new ArrayList<>(crss.tutGroupAndRoomSize(inputTimeTable));
                String outputFileName = produceCurrentTimeTextFileNameAndNotifyOnScreen
                        (roomSizeSufficiencyResult, "Task4");
                JOptionPane.showMessageDialog(
                        null, "Result for Task 4 is obtained and is stored in file "
                                +outputFileName+" which is " +
                                "in the folder "+pathOfTheFolderForKeepingResultFiles+" on Desktop.");
            }
        });
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.setLocation(250,200);
        f.setSize(1200,250);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static String produceCurrentTimeTextFileNameAndNotifyOnScreen(ArrayList<String> result, String taskNum) {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmm");
        String outPutTaskFileName = " "; //taskNum+"_"+ldt.format(dtf)+ "Result.csv";
        if (taskNum.trim().toLowerCase().equals("task1")||taskNum.trim().toLowerCase().equals("task2"))
            outPutTaskFileName=taskNum+"_"+ldt.format(dtf)+ "Result.csv";
        else outPutTaskFileName=taskNum+"_"+ldt.format(dtf)+ "Result.txt";;
        ArrayList<String> tempStrings= new ArrayList<>();
        try {
            FileWriter writer = new FileWriter
                    (pathOfTheFolderForKeepingResultFiles+"\\"+outPutTaskFileName);
            tempStrings.add(taskNum+" result:");
            tempStrings.add("");
            result.forEach(s->tempStrings.add(s));
            tempStrings.add("\n  -- This result is produced at  "
                    +ldt.format(DateTimeFormatter.ofPattern("h:mma  d MMMM yyyy")));
            for(String str: tempStrings){
                writer.write(str+"\n");
            }
            writer.close();
        } catch (IOException er) {
            System.out.println(er);
        }
        return outPutTaskFileName;
    }
}

