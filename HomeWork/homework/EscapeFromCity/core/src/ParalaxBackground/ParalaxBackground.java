package ParalaxBackground;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import net.dermetfan.utils.Pair;

public class ParalaxBackground{
    final private Integer backgroundsQuantity=6;
    private Array<Pair<Float, Texture>>backgrounds = new Array<Pair<Float, Texture>>();
    int s_x=Gdx.graphics.getWidth();
    int s_y=Gdx.graphics.getHeight();
    Float x=0.f;
    public void Create(){
        Parallax.InitBackgrounds(backgrounds,backgroundsQuantity,"MenuBackground/",0.2f);
    }
    public void Draw(SpriteBatch bath, Float parentAlpha,Float dt){
        x-=1.f;
        for (Pair<Float, Texture>i:backgrounds) {
            Float _x=x*i.getKey();
            bath.draw(i.getValue(),0.f,0.f,_x.intValue(),0,s_x,s_y);
        }
    }
    public void dispose() {
        for (Pair<Float, Texture>i:backgrounds) {
           i.getValue().dispose();
        }
    }
}
