package com.nazar_pavliuk.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.logging.FileHandler;

import Main.Enemy.FlyingEye;
import Main.Enemy.Goblin;
import Main.Enemy.Mushroom;
import Main.Player.Player;
import ParalaxBackground.ParalaxBackground;
import ParalaxBackground.ParallaxBackgroundGame;
import UI.GameUI;
import UI.MainMenuUI;
import utils.util;

public class GDX_main extends ApplicationAdapter {
    private TiledMap t_map;
    public static OrthographicCamera cam;
    static  GDX_main instance;
    public World world;
    public Player player;
    Body body;
    public FlyingEye flyingEye;
    public Goblin goblin;
    public Mushroom mushroom;
    ParalaxBackground bgMenu;
    public ParallaxBackgroundGame bgGame;
    MainMenuUI mainMenuUI;
    GameUI gameUI;
    private  static SpriteBatch batch;
    static Stage _stage;
    public scene _scene;
    float unitScale =2.f;
    OrthogonalTiledMapRenderer renderer;
    Box2DDebugRenderer debugRenderer;
    public SpriteBatch GetBatch(){return batch;}
    public static GDX_main Instance() {
        return instance;
    }
    public void SetScene(scene scene_){
        _scene=scene_;
        _stage.clear();
        switch (_scene) {
            case Menu:
                mainMenuUI= new MainMenuUI(_stage);
                break;
            case Game:
                gameUI = new GameUI(_stage);
                break;
        }
        Gdx.input.setInputProcessor(_stage);
    }
    @Override
    public void create() {
        if (instance == null) {
            instance = this;
        }
        Box2D.init();
        cam = new OrthographicCamera(util.s_x, util.s_y);
        debugRenderer= new Box2DDebugRenderer();
        world = new World(new Vector2(0, -98f), true);
        t_map = new TmxMapLoader().load(Gdx.files.internal("t_map/EscapeFromCity.tmx").path());
        renderer = new OrthogonalTiledMapRenderer(t_map, unitScale);
        cam.update();
        renderer.setView(cam);
        _stage = new Stage(new ScreenViewport());
        _scene=scene.Menu;
        batch= new SpriteBatch();
        bgMenu= new ParalaxBackground();
        bgMenu.Create();
        bgGame= new ParallaxBackgroundGame();
        bgGame.Create();
        mainMenuUI= new MainMenuUI(_stage);
        player= new Player();
        player.create();
        player.CreatePhysics(world,new Vector2(200,350));
        flyingEye= new FlyingEye();
        flyingEye.create();
        goblin= new Goblin();
        goblin.create();
        mushroom= new Mushroom();
        mushroom.create();
        body = util.InitBody(world,body,new Vector2(600,1),new Vector2(200,350), BodyDef.BodyType.StaticBody,1.f,0.5f,0.5f);
        body.setTransform(new Vector2(200,150),0);
        player.body.setTransform(new Vector2(200,150),0);
        Gdx.input.setInputProcessor(_stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        switch (_scene) {
            case Menu:
                bgMenu.Draw(batch,1.f,Gdx.graphics.getDeltaTime());
                break;
            case Game:
//                bgGame.DrawBG(batch,1.f,Gdx.graphics.getDeltaTime());
//                bgGame.DrawFG(batch,1.f,Gdx.graphics.getDeltaTime());
                world.step(Gdx.graphics.getDeltaTime(), 6, 2);
                UpdateCamera(Gdx.graphics.getDeltaTime(),player.GetPosition());
                cam.update();
                renderer.setView(cam);
                renderer.render();
                batch.setProjectionMatrix(cam.combined);
                player.render();
                debugRenderer.render(world, cam.combined);
                Gdx.app.log("LOG", player.GetPosition().toString());
                Gdx.app.log("LOG Texture", player.GetPositionTex().toString());

                flyingEye.render();
                goblin.render();
                mushroom.render();
                break;
        }
        batch.end();
        _stage.act(Gdx.graphics.getDeltaTime());
        _stage.draw();
    }
    public void UpdateCamera(float dt, Vector2 pos){
        Vector3 target = new Vector3(pos,0);
        final float speed=dt,ispeed=1.0f-speed;
       Vector3 cameraPosition = cam.position;
       cameraPosition.scl(ispeed);
       target.scl(speed);
       cameraPosition.add(target);
    }
    @Override
    public void dispose() {
        batch.dispose();
        bgMenu.dispose();
        t_map.dispose();
    }
}
