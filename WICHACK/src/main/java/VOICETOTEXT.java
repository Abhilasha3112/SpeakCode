//import com.google.cloud.speech.v1.*;

/*import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
*/
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
//import sun.tools.java.Environment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class VOICETOTEXT {

  /*  SpeechClient speechClient;
    {
        try {
            speechClient = SpeechClient.create();
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.FLAC;
            int sampleRateHertz = 44100;
            String languageCode = "en-US";
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(encoding)
                    .setSampleRateHertz(sampleRateHertz)
                    .setLanguageCode(languageCode)
                    .build();

            String filename = "C:/Users/roopa/Downloads/RecordAudio.wav";
            File file = new File(filename);

            String uri = file.toURI().toString();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(uri)
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
  public void voicetotext(Frame f) throws Exception {
 /* SpeechToText service = new SpeechToText();
service.setUsernameAndPassword("roopa.shekarhk@gmail.com", "Roopa@13");

    File audio = new File("D:/RecordAudio.wav");

        RecognizeOptions options = null;
        try {
            options = new RecognizeOptions.Builder()
                    .audio(audio)
                    .contentType(HttpMediaType.AUDIO_WAV)
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        IamOptions option1 = new IamOptions.Builder()
                .accessToken("sMH0sC5AtIiPCpiBUh3N2sKf0JJv0gVYRgGW8szCbFzy")
                .build();
        IamOptions option2 = new IamOptions.Builder()
                .apiKey("sMH0sC5AtIiPCpiBUh3N2sKf0JJv0gVYRgGW8szCbFzy")// optional - the default value is https://iam.bluemix.net/identity/token
                .build();
        service.setIamCredentials(option2);
        service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");
        SpeechRecognitionResults transcript = service.recognize(options).execute();
System.out.println(transcript);*/


      /**
       * Demonstrates using the Speech API to transcribe an audio file.
       */

      // Instantiates a client
      try (SpeechClient speechClient = SpeechClient.create()) {

          // The path to the audio file to transcribe
          String fileName = "D:/RecordAudio.wav";
          // String value = Environment.GetEnvironmentVariable("GOOGLE_APPLICATION_CREDENTIALS");
          // Reads the audio file into memory
          Path path = Paths.get(fileName);
          byte[] data = Files.readAllBytes(path);
          ByteString audioBytes = ByteString.copyFrom(data);

          // Builds the sync recognize request
          RecognitionConfig config = RecognitionConfig.newBuilder()
                  .setEncoding(AudioEncoding.LINEAR16)
                  .setSampleRateHertz(16000)
                  .setLanguageCode("en-US")
                  .build();
          RecognitionAudio audio = RecognitionAudio.newBuilder()
                  .setContent(audioBytes)
                  .build();

          // Performs speech recognition on the audio file
          RecognizeResponse response = speechClient.recognize(config, audio);
          List<SpeechRecognitionResult> results = response.getResultsList();

          for (SpeechRecognitionResult result : results) {
              // There can be several alternative transcripts for a given chunk of speech. Just use the
              // first (most likely) one here.
              SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
              System.out.printf("Transcription: %s%n", alternative.getTranscript());
              JavaSoundRecorder javaSoundRecorder=new JavaSoundRecorder();
              javaSoundRecorder.mapping(alternative.getTranscript(),f);
          }
      }
  }

}