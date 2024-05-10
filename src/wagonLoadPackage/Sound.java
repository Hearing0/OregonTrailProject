package wagonLoadPackage;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Sound.java
 * Created on 5/01/2024
 * By Rod Piton
 * 
 * Creates the Sound and sets a volume for it.
 * Grabs the file and creates a volumeUp and volumeDown so it can be adjusted
 * uses clip to know when to start playing the audio file
 * Creates a mute void to know what value to silence the audio
 * uses current and previous volume to adjust
 */

public class Sound {

	Clip clip;

	float previousVolume = 0;
	float currentVolume = -17;

	FloatControl fc;

	boolean mute = false;

	public void setFile(URL url) {

		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(sound);
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		}

		catch (Exception e) {

		}

	}

	public void play(URL url) {
		clip.setFramePosition(0);
		clip.start();
	}

	public void loop(URL url) {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void Stop(URL url) {
		clip.stop();
	}

	public void volumeUp() {
		currentVolume += 1.0f;
		if (currentVolume > 6.0f) {
			currentVolume = 6.0f;
		}
		fc.setValue(currentVolume);
	}

	public void volumeDown() {
		currentVolume -= 1.0f;
		if (currentVolume < -80.0f) {
			currentVolume = -80.0f;
		}
		fc.setValue(currentVolume);
	}

	public void volumeMute() {
		if (mute == false) {
			previousVolume = currentVolume;
			currentVolume = -80.0f;
			fc.setValue(currentVolume);
			mute = true;

		} else if (mute == true) {
			currentVolume = previousVolume;
			fc.setValue(currentVolume);
			mute = false;
		}
	}
}
