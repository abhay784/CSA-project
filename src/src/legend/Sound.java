package legend;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[]= new URL[30];
	
	public Sound() {
		soundURL[0]= getClass().getResource("/sound/2023-05-22 18-29-45.wav");
		soundURL[1]= getClass().getResource("/sound/2023-05-22 18-21-26.wav");
		soundURL[2]= getClass().getResource("/sound/2023-05-22 18-16-28.wav");
	}
	public void setFile(int i)
	{
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		clip.start();
		//hi
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	
}
