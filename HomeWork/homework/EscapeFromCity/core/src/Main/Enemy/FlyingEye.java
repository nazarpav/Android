package Main.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import Main.Player.Actor;
import Main.Player.AnimMap;
import utils.util;

public class FlyingEye extends Actor {
    @Override
    public void create() {
        super.create();
        String basePath = "enemy/flying_eye/";
        _currentAnimation = util.CreateAnimation(Gdx.files.internal(basePath+"Flight.png"),8,1,0.1f);
        animations.put(AnimMap.Idle,_currentAnimation);
        animations.put(AnimMap.Move, util.CreateAnimation(Gdx.files.internal(basePath+"Flight.png"),8,1,0.1f));
        animations.put(AnimMap.Attack, util.CreateAnimation(Gdx.files.internal(basePath+"Attack.png"),8,1,0.1f));
        animations.put(AnimMap.Death, util.CreateAnimation(Gdx.files.internal(basePath+"Death.png"),4,1,0.13f));
        animations.put(AnimMap.TakeHit, util.CreateAnimation(Gdx.files.internal(basePath+"TakeHit.png"),4,1,0.1f));
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

