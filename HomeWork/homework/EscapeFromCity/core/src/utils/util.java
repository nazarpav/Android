package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.nazar_pavliuk.game.GDX_main;

import java.util.Random;

public class util {
    public static int s_x = Gdx.graphics.getWidth();
    public static int s_y = Gdx.graphics.getHeight();
    public static Float testResolutionX=800.f;
    public static Float testResolutionY=400.f;
    static Skin skin;
    public static Random rnd = new Random();
    static {
        skin = new Skin(Gdx.files.internal("mskin/defskin.json"));
    }
    public static int Scaleer(int in){
        Integer res =Gdx.graphics.getHeight()/testResolutionY.intValue();
        int f = res*in;
        return f;
    }
    public static float Scaleer(float in){
       return  (Gdx.graphics.getHeight()/testResolutionY)*in;
    }
    public static Vector3 ToScreen(Vector2 pos) {
        return GDX_main.cam.unproject(new Vector3(pos,0));
    }
    public static int ScaleerY(int in){
        return Gdx.graphics.getHeight()/testResolutionY.intValue()*in;
    }
    public static Body InitBody(World world,Body body, Vector2 w_h,Vector2 pos, BodyDef.BodyType type,float density,float friction,float restitution){
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = type;
            body = world.createBody(bodyDef);
            bodyDef.position.set(pos.x, pos.y);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(w_h.x, w_h.y);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density =density;
            fixtureDef.friction = friction;
            fixtureDef.restitution = restitution;
             body.createFixture(fixtureDef);
            shape.dispose();
            return body;
    }
    public static int ScaleerX(int in){
        return Gdx.graphics.getWidth()/testResolutionX.intValue()*in;
    }
    public static float ScaleerY(float in){
        return Gdx.graphics.getHeight()/testResolutionY*in;
    }
    public static float ScaleerX(float in){
        return Gdx.graphics.getWidth()/testResolutionX*in;
    }
    public static TextButton CreateDefTextButton(String text,float x, float y, int button_width, int button_height,int buttonNumber, ClickListener click){
        final TextButton button = new TextButton(text, util.GetDefSkin() , "default");
        button.setWidth(util.ScaleerX(button_width));
        button.setHeight(util.ScaleerY(button_height));
        button.setPosition(x,y);
        button.getLabel().setFontScale(util.Scaleer(1));
        button.addListener(click);
        return button;
    }
    public static ImageButton CreateDefImageButton(FileHandle imDown, FileHandle imUp, Vector2 pos, Vector2 size, ClickListener click){
        final ImageButton button = new ImageButton(new TextureRegionDrawable(new Texture(imUp)),new TextureRegionDrawable(new Texture(imDown)));
        button.getImage().setScaling(Scaling.fill);
        button.getImage().setScale(util.ScaleerY(1.f));
        button.getImage().setWidth(size.x);
        button.getImage().setHeight(size.y);
        button.setPosition(pos.x,pos.y);
        button.addListener(click);
        return button;
    }
    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    TextureRegion[] idleFrames;
    SpriteBatch spriteBatch;
    TextureRegion currentFrame;

    public static Animation CreateAnimation(FileHandle path, int frameCols, int frameRows, float frameTime){
        Texture walkSheet = new Texture(path);
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/frameCols, walkSheet.getHeight()/frameRows);
        TextureRegion[] frames = new TextureRegion[frameCols * frameRows];
        int index = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return new Animation(frameTime, frames);
    }
    public static Skin GetDefSkin(){
        return  skin;
    }
}
