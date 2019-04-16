import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpeakCode {
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public static String classname=null;

    public static void main(String[] args) {
        final Frame f=new Frame("SpeakCode-Everyone can Code");
        f.setSize(800,800);
        final TextArea tf=new TextArea();
        final TextArea tf5=new TextArea();
        tf5.setBounds(50,700, 600,50);
        tf.setBounds(50,50, 600,600);
        Button b=new Button("Click Here To Record");
        b.setBounds(50,650,160,30);
        Button b1=new Button("Run the Code");
        b1.setBounds(255,650,160,30);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // tf.setText("Welcome to Javatpoint.");
                JavaSoundRecorder javaSoundRecorder=new JavaSoundRecorder();
                javaSoundRecorder.recordSound(f);
               // System.out.println("dfdfdd");
            }
        });
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    //runProcess("pwd");
                    System.out.println("**********");
//                    runProcess("cd C:/Users/abhil/IdeaProjects/untitled1/src/");
                    runProcess("javac -d C:/Users/abhil/IdeaProjects/untitled1/src/ABC/ C:/Users/abhil/IdeaProjects/untitled1/src/" +classname+".java");
                    System.out.println("**********");
                    //runProcess("java C:/Users/abhil/IdeaProjects/untitled1/src/"+classname+".class");
                    Process p=Runtime.getRuntime().exec("java C:/Users/abhil/IdeaProjects/untitled1/src/ABC/"+classname+" > C:/Users/abhil/IdeaProjects/untitled1/src/a.txt");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });


        f.add(b);f.add(tf);

        f.add(tf5);
        f.add(b1);
       // f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }
    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
}