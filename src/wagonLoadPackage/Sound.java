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

	//variables
	Clip clip;
	
	float previousVolume = 0;
	float currentVolume = -17;
	
	FloatControl fc;
	
	boolean mute = false;
	
	/**
	 * grabs the music file and throws exception if not found
	 * @param url - location of the sound file
	 */
	public void setFile(URL url) {
		
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(sound);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		}
		
		catch(Exception e) {
			
		}
		
	}
	
	/**
	 * plays the music file when called
	 * @param url - location of the sound file
	 */
	public void play(URL url) {
		clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 * loops the music file to ensure it is heard throughout the whole game
	 * @param url - location of the sound file
	 */
	public void loop(URL url) {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * stops the music from playing when called
	 * @param url - location of the sound file
	 */
	public void Stop(URL url) {
		clip.stop();
	}
	
	/**
	 * allows the volume of the music to be increased
	 */
	public void volumeUp() {
		 currentVolume += 1.0f;
		 if  (currentVolume > 6.0f) {
			 currentVolume = 6.0f;
		 }
		 fc.setValue(currentVolume);
	}
	
	/**
	 * allows the volume of the music to be decreased
	 */
    public void volumeDown() {
    	 currentVolume -= 1.0f;
		 if  (currentVolume < -80.0f) {
			 currentVolume = -80.0f;
		 }
		 fc.setValue(currentVolume);
	}
	
    /**
     * allows the music to be muted
     */
    public void volumeMute() {
    	if(mute == false) {
    		previousVolume = currentVolume;
    		currentVolume = -80.0f;
    		fc.setValue(currentVolume);
    		mute = true;
    				
    	}
    	else if(mute == true ) {
    		currentVolume = previousVolume;
    		fc.setValue(currentVolume);
    		mute = false;
    	}
	}
}
