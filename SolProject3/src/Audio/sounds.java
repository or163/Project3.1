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
	
	public static void welcomeSound()
	{
		String path = new File("src/Audio/welcome.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	
	public static void bonapatiteSound()
	{
		String path = new File("src/Audio/bonapatite.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	
	public static void cashSound()
	{
		String path = new File("src/Audio/Cash Register.mp3").getAbsolutePath();
		AudioClip click = new AudioClip(new File(path).toURI().toString());
		click.play();
	}
	
	static String path = new File("src/Audio/Walt's American Restaurant complete music loop (1_4).mp3").getAbsolutePath();
	static AudioClip bgs = new AudioClip(new File(path).toURI().toString());
	public static void backgroundMusic()
	{
		bgs.setVolume(0.2);
		if(bgs.isPlaying())
			bgs.stop();
		else {
			bgs.play();
			bgs.setCycleCount(100);
		}
	}
	
	public static void backgroundMusicMute()
	{
		bgs.stop();
	}
	
//	public static void background(Media me, MediaPlayer mp, MediaView mv) {
//		String path = new File("src/Audio/Restaurant Ad Video3.mp4").getAbsolutePath();
//    	me = new Media(new File(path).toURI().toString());
//    	mp = new MediaPlayer(me);
//    	mv.setMediaPlayer(mp);
//    	mv.toBack();
//    	mp.setAutoPlay(true);
//    	mp.setVolume(0);
//    	mp.setCycleCount(MediaPlayer.INDEFINITE);
//    	DoubleProperty width = mv.fitWidthProperty();
//    	DoubleProperty height = mv.fitHeightProperty();
//    	width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
//    	height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
//	}
	
	
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

