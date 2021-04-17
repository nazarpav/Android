package Main.Player;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.badlogic.gdx.utils.Array;

import net.dermetfan.utils.Pair;

import java.util.HashMap;
import java.util.Map;

import utils.util;

public abstract class Actor implements ApplicationListener {
    protected Map<AnimMap, Animation> animations = new HashMap<AnimMap, Animation>();
    public Body body;
    protected Animation _currentAnimation;
    protected Sprite currentFrame;
    protected SpriteBatch spriteBatch;
    protected AnimMap _currentstate;
    private boolean _isFlip=false;
    private float stateTime;
    private boolean _isPause=false;
    protected boolean _isLooping=true;
    public Vector2 GetPosition(){
        if(body!=null){
               return body.getPosition();
        }
        return  new Vector2(0,0);
    }
    public Vector2 GetPositionTex(){
        if(currentFrame!=null){
               return new Vector2(currentFrame.getX(),currentFrame.getY());
        }
        return  new Vector2(0,0);
    }
    @Override
    public void create() {
        spriteBatch= new SpriteBatch();
        currentFrame=new Sprite();
        stateTime=util.rnd.nextFloat()%50;
    }
    public void SetVelocity(float x, float y){
        body.setLinearVelocity(x, y);
    }
    protected void InitPhysisc(World world,Vector2 w_h,Vector2 pos){
        body = util.InitBody(world,body,w_h,pos, BodyDef.BodyType.DynamicBody,0.1f,0.7f,0.3f);
        body.setFixedRotation(true);
    }
    public void Update(){

    }
    public void SetAnimState(AnimMap state){
        Animation newState =  animations.get(state);
        if(newState!=null){
            if((_currentstate==AnimMap.Attack||_currentstate==AnimMap.Shield)&&stateTime<0.5f)return;
            _currentAnimation=newState;
            _currentstate=state;
            if(state==AnimMap.Death||state==AnimMap.Attack||state==AnimMap.Shield||state==AnimMap.TakeHit){
                _isLooping=false;
            }
            else{
                _isLooping=true;
            }
            stateTime=0.f;
        }
    }
    public AnimMap GetCurrentState(){
        return _currentstate;
    }
    public void SetIsFlip(boolean val){
        _isFlip=val;
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void render() {
        if((_currentstate==AnimMap.Attack||_currentstate==AnimMap.TakeHit||_currentstate==AnimMap.Shield)&&_currentAnimation.isAnimationFinished(stateTime)){
            SetAnimState(AnimMap.Idle);
        }
        if(_isPause||animations.isEmpty())return;
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame.setRegion((TextureRegion) _currentAnimation.getKeyFrame(stateTime, _isLooping));
        if(_isFlip&&!currentFrame.isFlipX()||!_isFlip&&currentFrame.isFlipX()){currentFrame.flip(true,false);}
        spriteBatch.begin();
        currentFrame.setPosition(GetPosition().x,GetPosition().y);
        spriteBatch.draw(currentFrame, currentFrame.getX(),currentFrame.getY());
        spriteBatch.end();
        Update();
    }
    @Override
    public void pause() {
        _isPause=true;
    }
    @Override
    public void resume() {
        _isPause=false;
    }
    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
