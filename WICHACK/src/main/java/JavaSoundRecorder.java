import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
//import javax.sound.sampled;

/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class JavaSoundRecorder {
    // record duration, in milliseconds
    static final long RECORD_TIME = 6000;  // 1 minute

    // path of the wav file
    File wavFile = new File("D:/RecordAudio.wav");

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line from which audio data is captured
    TargetDataLine line;
    TextArea tf5=new TextArea();
    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    /**
     * Captures the sound and record into a WAV file
     */
    void start(Frame f) {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");
             tf5=(TextArea)f.getComponent(2);
            tf5.setText("Start Recording");
            // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    void finish(Frame f) throws Exception {
        line.stop();
        line.close();
        System.out.println("Finished");
        tf5=(TextArea)f.getComponent(2);
        tf5.setText("Recording Stopped");
        VOICETOTEXT voiceto=new VOICETOTEXT();
        voiceto.voicetotext(f);

        /*WaveFileWriter wfw = new WaveFileWriter();
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 2, 44100, false);
        AudioFormat monoFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false);

        byte[] audioData = dataout.toByteArray();
        int length = audioData.length;
        ByteArrayInputStream bais = new ByteArrayInputStream(audioData);

        AudioInputStream stereoStream = new AudioInputStream(bais,format,length);
        AudioInputStream monoStream = new AudioInputStream(stereoStream,format,length/2);
        AudioInputStream monoStream = new AudioInputStream(stereoStream,format,length/2);

        wfw.write(monoStream, AudioFileFormat.Type.WAVE, new File(""));*/


    }
    String finalcode=null;
    void mapping(String text,Frame f)
    {
        String classname=null;
        HashMap<String,String> hm=new HashMap<String, String>();
        hm.put("class","import java.io.*; \n public class classname { \n /*Properties*/\n /*Methods*/\n }\n/*Classes*/");
        hm.put("main method","public static void main(String args[]) {\n/*Commands*/\n}");
        hm.put("print","System.out.println();\n");
        hm.put("forloop","for(int i=0;i<;i++) {\n/*Commands*/\n}");
        SpeakCode b1=new SpeakCode();

       // HashMap<String,String> hm=mapping();
        String str=text;
        if(str.contains("create class")) {
            if (hm.containsKey("class")) {
                String code = hm.get("class");
                if (str.contains("class")) {
                    classname = str.substring(str.lastIndexOf("class") + 6);
                   // b1.setClassname(classname);
                    SpeakCode.classname=classname;
                    String newcode = code.replace("classname", classname);
                    PrintWriter writer = null;
                    try {
                        File file = new File("D:/");
                        OutputStream out = new FileOutputStream(classname + ".txt");
                        byte[] b = code.getBytes();
                        BufferedWriter w = null;

                        out = new FileOutputStream(new File("C:/Users/abhil/IdeaProjects/untitled1/src/" + classname + ".java"));
                        try {
                            out.write(newcode.getBytes(), 0, newcode.length());
                            finalcode=newcode;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                     //   writer.close();
                        //  writer = new PrintWriter("D:/"+classname+".txt", "UTF-8");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                   // writer.println(code);
                    // writer.println("The second line");
                   // writer.close();
                }
            }

            //return hm;
        }
        else if(str.contains("main method"))
        {
            if (hm.containsKey("main method")) {
                String code = hm.get("main method");
              //  if (str.contains("main method")) {

                    PrintWriter writer = null;
                    try {
                        System.out.println( b1.getClassname() );
                        System.out.println(SpeakCode.classname);
                        File file = new File("C:/Users/abhil/IdeaProjects/untitled1/src/" +  b1.getClassname() + ".java");
                       FileInputStream fis = new FileInputStream(file);
                        FileReader fr = new FileReader("C:/Users/abhil/IdeaProjects/untitled1/src/" +  SpeakCode.classname+ ".java");
                        FileWriter fw = new FileWriter("C:/Users/abhil/IdeaProjects/untitled1/src/" +  SpeakCode.classname + ".java", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw);
                        Scanner in = new Scanner(new FileReader("C:/Users/abhil/IdeaProjects/untitled1/src/" +  SpeakCode.classname+ ".java"));
                        StringBuilder sb = new StringBuilder();
                        String match = "/*Methods*/";
                       List<String> lines =  Files.readAllLines(Paths.get("C:/Users/abhil/IdeaProjects/untitled1/src/" +  SpeakCode.classname+ ".java"));
                        for (String line : lines) {
                            System.out.println(line);
                            sb.append(line+"\n");
                            if(match.equals(line.trim())){

                                //String content = line;
                                sb.append(code + "\n");

                               // out.println(sb);
                            }
                        }

                        FileOutputStream out1 = new FileOutputStream(new File("C:/Users/abhil/IdeaProjects/untitled1/src/" + SpeakCode.classname + ".java"));
                        try {
                            //out1.write(String.valueOf(sb.toString().getBytes()), 0, sb.toString().length());
                            out1.write(sb.toString().getBytes(), 0, sb.length());
                            finalcode=sb.toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        in.close();
                        String outString = sb.toString();

                        try {
                            System.out.println("Total file size to read (in bytes) : "
                                    + fis.available());


                        int content;
                        StringBuilder sb1=new StringBuilder();

                        System.out.println(sb.toString());
                         finalcode=sb.toString();
                        //  writer = new PrintWriter("D:/"+classname+".txt", "UTF-8");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                 //   writer.println(finalcode);
                    // writer.println("The second line");
                   // writer.close();
               // }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        else if(str.contains("print") && str.contains("times"))
        {
            TaggerTest tag1=new TaggerTest();
            try {
                String[] content1=tag1.tag(str);
                int number=Integer.parseInt(content1[1]);
                String value=content1[0];
                if (hm.containsKey("forloop")) {
                    String code1 = hm.get("forloop");
                   String code=code1.replace("<","<"+number);
                   // if (str.contains("")) {
                        // Button1.classname=classname;
                     //   String newcode = code.replace("/*Commands*/", classname);
                    try {
                       // System.out.println( b1.getClassname() );
                        //System.out.println(Button1.classname);
                        StringBuilder sb = new StringBuilder();
                        String match = "/*Commands*/";
                        List<String> lines =  Files.readAllLines(Paths.get("C:/Users/abhil/IdeaProjects/untitled1/src/" +  SpeakCode.classname+ ".java"));
                        StringBuilder sb2=new StringBuilder();
                        for (String line : lines) {
                            System.out.println(line);
                            sb2.append(line);
                            if(match.equals(line.trim()))
                            {

                                //String content = line;
                                sb2.append("\n"+code);
                             sb2= new StringBuilder(sb2.toString().replaceFirst("/\\*Commands\\*/",""));

                              //  flag = true;

                                // out.println(sb);
                            }

                           // sb2.append(line);
                            sb2.append("\n");

                        }

                        FileOutputStream out1 = new FileOutputStream(new File("C:/Users/abhil/IdeaProjects/untitled1/src/" + SpeakCode.classname + ".java"));
                        try {
                            //out1.write(String.valueOf(sb.toString().getBytes()), 0, sb.toString().length());
                            out1.write(sb2.toString().getBytes(), 0, sb2.length());
                            finalcode=sb2.toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                       // String outString = sb2.toString();

                       // int content;
                       // StringBuilder sb1=new StringBuilder();

                       // System.out.println(sb2);
                        //finalcode=sb2.toString();
                         mapping(value,f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                        // writer.println(code);
                        // writer.println("The second line");
                        // writer.close();
                  //  }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(str.contains("print"))
        {
            if (hm.containsKey("print")) {
                String code1 = hm.get("print");
                String code2 = "(\""+str.substring(str.lastIndexOf("print") + 6)+"\"";
                //  if (str.contains("main method")) {
                   String code=code1.replace("(",code2);
                try {
                    System.out.println( b1.getClassname() );
                    System.out.println(SpeakCode.classname);
                    StringBuilder sb = new StringBuilder();
                    String match = "/*Commands*/";
                    StringBuilder sb2=new StringBuilder();
                    List<String> lines =  Files.readAllLines(Paths.get("C:/Users/abhil/IdeaProjects/untitled1/src/" +  SpeakCode.classname+ ".java"));
                    boolean flag = false;
                    for (String line : lines) {
                        //System.out.println(line);

                           // sb.append(line + "\n");

                        if(match.equals(line.trim()))
                        {

                            //String content = line;
                            sb2.append(code);
                           // sb.toString().replaceFirst("/\\*Commands\\*/","");
                           flag = true;

                            // out.println(sb);
                        }

                                sb2.append(line);
                                        sb2.append("\n");
                                flag = false;

                    }

                    FileOutputStream out1 = new FileOutputStream(new File("C:/Users/abhil/IdeaProjects/untitled1/src/" + SpeakCode.classname + ".java"));
                    try {
                        //out1.write(String.valueOf(sb.toString().getBytes()), 0, sb.toString().length());
                        finalcode="";
                        finalcode=sb2.toString();
                        out1.write(sb2.toString().getBytes(), 0, sb2.length());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                     // String outString = sb2.toString();

                      //  int content;
                      //  StringBuilder sb1=new StringBuilder();

                       // System.out.println(sb2.toString());
                       // finalcode=sb2.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        final TextField tf1=new TextField();
        tf1.setBounds(50,50, 150,200);
         System.out.println(finalcode);
       // tf1.setText("ggggg");
       // f.add(tf1);
        TextArea tf2= (TextArea) f.getComponent(1);
        tf2.setText(finalcode);
        f.setLayout(null);
        f.setVisible(true);
        //System.out.println("DFdfd");

    }
    /**
     * Entry to run the program
     */
    public static void recordSound(Frame f) {
        final JavaSoundRecorder recorder = new JavaSoundRecorder();
        final Frame f1=f;
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    recorder.finish(f1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stopper.start();

        // start recording
        recorder.start(f);
    }
}