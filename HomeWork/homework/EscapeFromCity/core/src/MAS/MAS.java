package MAS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class MAS {
    private  static Music music;
    private static float m_volume=1.f;
    private static float s_volume=0.5f;
    public static  void SetDefaulthMusicVolume(float volume){
        m_volume = volume;
    }
    public static  void SetDefaulthSoundVolume(float volume){
        s_volume = volume;
    }
    public static  void SetMusicActive(boolean isEnable){
        if(music!=null){
            if(isEnable){
                music.play();
            }
            else {
                music.pause();
            }
        }
    }
    public static  void ToggleMusicEnabled(){
        if(music!=null){
            if(!music.isPlaying()){
                music.play();
            }
            else {
                music.pause();
            }
        }
    }
    public static void StopCurrentAndStreamThisMusic(FileHandle path){
        music = Gdx.audio.newMusic(path);
        music.setVolume(m_volume);
        music.setLooping(true);
        music.play();
    }
    public static void PlaySound(FileHandle path){
//        Sound sound = Gdx.audio.newSound(path);
//        sound.play(s_volume);
    }
}
