package ParalaxBackground;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import net.dermetfan.utils.Pair;

public class Parallax {
    private static Integer s_x= Gdx.graphics.getWidth();
    private static Integer s_y=Gdx.graphics.getHeight();
    public static void InitBackgrounds(Array<Pair<Float, Texture>> array, int textureQuantity, String prefix, float stepX){
        Texture texture = new Texture(Gdx.files.internal(prefix+"0.png"));
        for (Integer i = 0; i < textureQuantity; i++) {
            Float x_tex= s_y.floatValue()/texture.getHeight()*texture.getWidth();
            Pixmap textureRT = new Pixmap(Gdx.files.internal(prefix+i.toString()+".png"));
            Pixmap screenRT = new Pixmap(x_tex.intValue(), s_y, textureRT.getFormat());
            screenRT.drawPixmap(textureRT,
                    0, 0, textureRT.getWidth(), textureRT.getHeight(),
                    0, 0, screenRT.getWidth(), screenRT.getHeight()
            );
            texture = new Texture(screenRT);
            textureRT.dispose();
            screenRT.dispose();
            texture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
            texture.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
            array.add(new Pair<Float, Texture>((i+1)*stepX,texture));
        }
    }
}
