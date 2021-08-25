package Audio;

import java.io.File;

import javafx.scene.media.AudioClip;

public class sounds {
	
	public static void clickSound()
	{
		String path = new File("src/Audio/MouseClickShort2.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	public static void bellSound()
	{
		String path = new File("src/Audio/Big Bell.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	public static void flashBackSound()
	{
		String path = new File("src/Audio/Flashback.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	public static void welcomeMSound()
	{
		String path = new File("src/Audio/WelcomeManager.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	
	
//	private void clickSound() {
//		String asrc = ".//MouseClick.wav";
//		String src = getClass().getResource("MouseClick.wav").toString();
//		System.out.println(src);
//		click = new AudioClip(src);
//		String bip = "MouseClick.wav";
//		Media hit = new Media(new File(bip).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(hit);
//		mediaPlayer.play();
//		System.out.println("/MouseClick.mp3");
//		//click.play();
//	}
}

