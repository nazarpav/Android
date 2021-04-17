package ParalaxBackground;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nazar_pavliuk.game.GDX_main;

import net.dermetfan.utils.Pair;

public class ParallaxBackgroundGame {
    final private Integer backgroundsQuantity=2;
    final private Integer foregroundsQuantity=1;
    private Array<Pair<Float, Texture>> backgrounds = new Array<Pair<Float, Texture>>();
    private Array<Pair<Float, Texture>> foregrounds = new Array<Pair<Float, Texture>>();
    int s_x= Gdx.graphics.getWidth();
    int s_y=Gdx.graphics.getHeight();
    Float x_posBG=0.f;
    Float x_posFG=0.f;
    float step=0.f;
    public void Create(){
        Parallax.InitBackgrounds(backgrounds,backgroundsQuantity,"city/background_",0.5f);
        Parallax.InitBackgrounds(foregrounds,foregroundsQuantity,"city/foreground_",1.f);
    }
    public void DrawBG(SpriteBatch bath, Float parentAlpha, Float dt){
        x_posBG+=step;
        for (Pair<Float, Texture>i:backgrounds) {
            Float x =x_posBG*i.getKey();
            bath.draw(i.getValue(),0.f,0.f,x.intValue(),0,s_x,s_y);
        }
    }
    public void DrawFG(SpriteBatch bath, Float parentAlpha, Float dt){
        x_posFG+=step;
        for (Pair<Float, Texture>i:foregrounds) {
            Float x =x_posFG*i.getKey();
            bath.draw(i.getValue(),0.f,0.f,x.intValue(),0,s_x,s_y);
        }
        GDX_main.Instance().cam.translate(step,0,0);
    }
    public void dispose() {
        for (Pair<Float, Texture>i:backgrounds) {
            i.getValue().dispose();
        }
    }
    public void MoveX(float _step){
        step=_step;
    }
}