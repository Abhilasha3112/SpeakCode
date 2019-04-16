

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;

public class TaggerExample {

    public String[] tag(String text) throws IOException, ClassNotFoundException {

        //MaxentTagger maxentTagger = new MaxentTagger("english-left3words-distsim.tagger");
         MaxentTagger maxentTagger = new MaxentTagger(MaxentTagger.DEFAULT_JAR_PATH);

        String tag = maxentTagger.tagString(text);
        String[] eachTag = tag.split("\\s+");
        System.out.println("Word      " + "Standford tag");
        System.out.println("----------------------------------");
        StringBuilder sb=new StringBuilder();
        String number=null;
        for(int i = 0; i< eachTag.length; i++) {
            if(eachTag[i].split("_")[1].equals("VB"))
            {
                continue;
            }

           System.out.println(eachTag[i]);
            if(eachTag[i].split("_")[1].equals("CD"))
            {
               number= eachTag[i].split("_")[0];
                break;
            }
            else
                sb.append(eachTag[i].split("_")[0]+" ");
         }

        return new String[]{sb.toString(),number};
    }
}