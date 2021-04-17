package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nazar_pavliuk.game.GDX_main;
import com.nazar_pavliuk.game.scene;

import MAS.MAS;
import Main.Player.AnimMap;
import utils.util;

public class GameUI {
    Stage _stage;
    public GameUI(Stage stage){
        float paddingX=util.Scaleer(5.f);
        float paddingY=util.Scaleer(5.f);
        Vector2 baseSize = new Vector2(util.Scaleer(74.f),util.Scaleer(60.f));
        String basePath="game_ui/";
        String leftButton = "arrowLeft.png";
        Vector2 sizeLeftButton = new Vector2(baseSize);
        Vector2 posLeftButton = new Vector2(paddingX,paddingY);
        String upButton = "arrowUp.png";
        Vector2 sizeUpButton = new Vector2(baseSize);
        Vector2 posUpButton = new Vector2(paddingX+sizeUpButton.x,paddingY);
        String downButton = "arrowDown.png";
        Vector2 sizeDownButton = new Vector2(baseSize);
        Vector2 posDownButton = new Vector2(util.s_x-(paddingX+sizeDownButton.x)*2,paddingY);
        String rightButton = "arrowRight.png";
        Vector2 sizeRightButton = new Vector2(baseSize);
        Vector2 posRightButton = new Vector2(util.s_x-sizeRightButton.x-paddingX,paddingY);
        String pauseButton = "pause.png";
        Vector2 sizePauseButton = new Vector2(baseSize);
        Vector2 posPauseButton = new Vector2(util.s_x-sizePauseButton.x-paddingX,util.s_y-paddingY-sizePauseButton.y);
        String progressBar = "progress.png";
        Vector2 sizeProgressBar = new Vector2(util.Scaleer(150.f),util.Scaleer(20.f));
        Vector2 posProgressBar = new Vector2(util.s_x/2.f-sizeProgressBar.x/2,util.s_y-paddingY-sizeProgressBar.y);
        _stage=stage;
        _stage.addActor(util.CreateDefImageButton (Gdx.files.internal(basePath+"_"+leftButton),Gdx.files.internal(basePath+leftButton), posLeftButton,sizeLeftButton,new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetVelocity(-260.f,0.f);
                GDX_main.Instance().player.SetIsFlip(true);
                GDX_main.Instance().player.SetAnimState(AnimMap.Move);
                MAS.PlaySound(Gdx.files.internal("sounds/buttonpres.mp3"));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetVelocity(0.f,0.f);
                GDX_main.Instance().player.SetAnimState(AnimMap.Idle);
                super.touchUp(event, x, y, pointer, button);
            }
          }));
        _stage.addActor(util.CreateDefImageButton (Gdx.files.internal(basePath+"_"+downButton),Gdx.files.internal(basePath+downButton), posDownButton,sizeDownButton,new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetVelocity(0.f,-80.f);
                GDX_main.Instance().player.SetAnimState(AnimMap.Shield);
                MAS.PlaySound(Gdx.files.internal("sounds/buttonpres.mp3"));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetVelocity(0.f,0.f);
                GDX_main.Instance().player.SetAnimState(AnimMap.Idle);
                super.touchUp(event, x, y, pointer, button);
            }
        }));
        _stage.addActor(util.CreateDefImageButton (Gdx.files.internal(basePath+"_"+upButton),Gdx.files.internal(basePath+upButton), posUpButton,sizeUpButton,new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetAnimState(AnimMap.Attack);
                GDX_main.Instance().player.SetVelocity(0.f,80.f);
                MAS.PlaySound(Gdx.files.internal("sounds/buttonpres.mp3"));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                GDX_main.Instance().player.SetVelocity(0.f,0.f);
                super.touchUp(event, x, y, pointer, button);
            }
        }));
        _stage.addActor(util.CreateDefImageButton (Gdx.files.internal(basePath+"_"+rightButton),Gdx.files.internal(basePath+rightButton), posRightButton,sizeRightButton,new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetVelocity(80.f,0.f);
                GDX_main.Instance().player.SetIsFlip(false);
                GDX_main.Instance().player.SetAnimState(AnimMap.Move);
                MAS.PlaySound(Gdx.files.internal("sounds/buttonpres.mp3"));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GDX_main.Instance().player.SetVelocity(0.f,0.f);
                GDX_main.Instance().player.SetAnimState(AnimMap.Idle);
                super.touchUp(event, x, y, pointer, button);
            }
        }));
        _stage.addActor(util.CreateDefImageButton (Gdx.files.internal(basePath+"_"+pauseButton),Gdx.files.internal(basePath+pauseButton), posPauseButton,sizePauseButton,new ClickListener() {@Override public void clicked(InputEvent event, float x, float y) {
            MAS.PlaySound(Gdx.files.internal("sounds/buttonpres.mp3"));
            GDX_main.Instance().SetScene(scene.Menu);
        }}));
        _stage.addActor(util.CreateDefImageButton (Gdx.files.internal(basePath+"_"+progressBar),Gdx.files.internal(basePath+progressBar), posProgressBar,sizeProgressBar,new ClickListener() {@Override public void clicked(InputEvent event, float x, float y) {
        }}));
    }
}
