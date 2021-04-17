package Main.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import utils.util;

public class Player extends Actor {
    @Override
    public void create() {
        super.create();
        String basePath = "player/animations/";
        _currentAnimation = util.CreateAnimation(Gdx.files.internal(basePath+"Idle.png"),4,1,0.08f);
        animations.put(AnimMap.Idle,_currentAnimation);
        animations.put(AnimMap.Move, util.CreateAnimation(Gdx.files.internal(basePath+"Walk.png"),4,1,0.08f));
        animations.put(AnimMap.Attack, util.CreateAnimation(Gdx.files.internal(basePath+"Attack.png"),8,1,0.06f));
        animations.put(AnimMap.Death, util.CreateAnimation(Gdx.files.internal(basePath+"Death.png"),4,1,0.1f));
        animations.put(AnimMap.TakeHit, util.CreateAnimation(Gdx.files.internal(basePath+"TakeHit.png"),4,1,0.08f));
        animations.put(AnimMap.Shield, util.CreateAnimation(Gdx.files.internal(basePath+"Shield.png"),4,1,0.06f));
    }
    public void CreatePhysics(World world, Vector2 pos){
        InitPhysisc(world,new Vector2(30,50),pos);
    }
    @Override
    public void render() {
       super.render();
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}