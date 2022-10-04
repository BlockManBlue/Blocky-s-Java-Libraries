import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio {

	public static void main(String FileName) throws Exception, IOException {
		// TODO Auto-generated method stub
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(FileName).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		clip.start();
	}

}
