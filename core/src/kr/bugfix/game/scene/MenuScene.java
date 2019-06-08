package kr.bugfix.game.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import javax.xml.soap.Text;

import kr.bugfix.game.Manager.ButtonManager;
import kr.bugfix.game.Manager.SceneManager;
import kr.bugfix.game.UI.Button;
import kr.bugfix.game.datastruct.StageInfo;
import kr.bugfix.game.system.GameEnv;

public class MenuScene
        extends BaseScene
{
    private final int BUTTON_SELECT_LEFT = 1;
    private final int BUTTON_SELECT_RIGHT = 2;
    private final int BUTTON_START = 3;
    private final int BUTTON_EXIT = 4;

    Texture backgroundImage;
    TextureRegion textureRegion;


    TextureRegion thumbnailTextrue;

    ButtonManager buttonManager;


    public MenuScene() {
        super();
        stage = new Stage(viewport);

        init();
    }

    @Override
    public void init() {
        // 게임시작전 점수를 0으로 초기화.
        GameEnv.getInstance().resetScore();

        backgroundImage = new Texture(Gdx.files.internal("menu_bg.png"));
        textureRegion = new TextureRegion(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());

        buttonManager = new ButtonManager();
        Button btn;
        btn = new Button(BUTTON_SELECT_LEFT, "select_btn.png", "select_btn.png");
        btn.setPosition(120, Gdx.graphics.getHeight()/2);
        buttonManager.add(btn);
        btn = new Button(BUTTON_SELECT_RIGHT, "select_btn.png", "select_btn.png");
        btn.setPosition(Gdx.graphics.getWidth()-120, Gdx.graphics.getHeight()/2);
        buttonManager.add(btn);
        btn = new Button(BUTTON_EXIT, "exit_down.png", "exit_up.png");
        btn.setPosition(Gdx.graphics.getWidth()-100, 65);
        buttonManager.add(btn);
        btn = new Button(BUTTON_START, "start_btn.png", "start_btn.png");
        btn.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-100);
        buttonManager.add(btn);

        changeThumbnail(GameEnv.getInstance().stageIndex);
    }

    private void changeThumbnail(int index)
    {
        StageInfo stageInfo = GameEnv.getInstance().getStageInfo(index);
        Texture tmp = new Texture(stageInfo.thumbnail);
        thumbnailTextrue = new TextureRegion(tmp, 0, 0, tmp.getWidth(), tmp.getHeight());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void esc() {

    }

    @Override
    public boolean eventTouchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean eventTouchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean eventTouchUp(int screenX, int screenY, int pointer, int button) {
        int bid = buttonManager.checkPress(screenX, screenY);
        Gdx.app.log("TOUCH EVENT", "ID:" + bid + " // X:Y" + screenX + ":" + screenY);
        switch (bid)
        {
            case BUTTON_EXIT:
                Gdx.app.exit();
                break;

            case BUTTON_SELECT_RIGHT:
                // 선곡 오른쪽 버튼
                if (GameEnv.getInstance().stageIndex < GameEnv.getInstance().getStageSize() - 1)
                {
                    GameEnv.getInstance().stageIndex++;
                    changeThumbnail(GameEnv.getInstance().stageIndex);
                }
                break;

            case BUTTON_SELECT_LEFT:
                // 선곡 왼쪽 버튼
                if (GameEnv.getInstance().stageIndex == 0) break;
                GameEnv.getInstance().stageIndex--;
                changeThumbnail(GameEnv.getInstance().stageIndex);
                break;

            case BUTTON_START:
                SceneManager.getInstance().changeScene(new PlayScene());
                break;
        }
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(textureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(thumbnailTextrue, 298, 178, 365, 185);
        buttonManager.render(batch);
        batch.end();

        update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
    }

}