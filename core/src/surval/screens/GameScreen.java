//
// Этот код реализует игру.
// Этот код - первое на что переключается первичный код игры (surval.core.Main).
//

package surval.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import java.util.*;
import surval.alives.*;
import surval.blocks.*;
import surval.core.*;

public class GameScreen implements Screen {
    // Прочие поля:
    private SpriteBatch batch;             // Партия спрайтов для отрисовки.
    private SpriteBatch uibatch;           // Партия спрайтов для отрисовки ui.
    private OrthographicCamera uicamera;   // Камера интерфейса.
    private OrthographicCamera camera;     // Игровая камера 2D.
    private Vector2 CameraTarget;          // Цель за которой будет следить камера.
    private float CameraZoomTarget = 1f;   // Цель зума камеры. Это поле, текущего (не плавного) зума.
    private float DeltaTime = 1;           // Дельта времени.
    private World world;                   // Карта.
    private List<Alive> alives;            // Список существ.
    private ShapeRenderer shapeRenderer;   // Надо для рисования фигур.
    private boolean IsVisibleDevPanel;     // Показывать ли панель разработчика.


    @Override public void show() {
        batch = new SpriteBatch();
        uibatch = new SpriteBatch();

        // Создать камеру:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        CameraTarget = new Vector2(0, 0);

        // Создать ui камеру:
        uicamera = new OrthographicCamera();
        uicamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Создание карты:
        world = new World(1024, 1024);
        world.Generate();
        Vector2 bonfireblockpos = new Vector2(world.Width/2f, world.Height/2f);
        world.SetBlock(new BonfireBlock((int)bonfireblockpos.x, (int)bonfireblockpos.y), bonfireblockpos);

        // Создание списка существ и добавление игрока:
        alives = new ArrayList<>();
        alives.add(new Player(new Vector2(world.Width/2f*world.BlockSize-world.BlockSize,
                                          world.Height/2f*world.BlockSize+(world.BlockSize/2f))));
        camera.position.x = alives.get(0).Pos.x; camera.position.y = alives.get(0).Pos.y;

        shapeRenderer = new ShapeRenderer();
    }

    // Тут вся логика:
    public void Update() {
        // Если нажимается CTRL или SYM, и английская 'P', показать панель разработчика. Если нажата ESC, скрыть:
        if((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SYM)) &&
            Gdx.input.isKeyPressed(Input.Keys.P))          { IsVisibleDevPanel = true;  }
        else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { IsVisibleDevPanel = false; }

        // Проходиться по существам и обновлять их:
        for(Alive alive : alives) {
            alive.Update(DeltaTime);
            if(Objects.equals(alive.ID, "player"))
                CameraTarget = alive.Pos; // Передвигать камеру.
        }

        // TODO переделать -> перенести в отдельный обработчик работы с блоками и сделать поддержку сенсорного экрана:
        // Установить блок правой кнопкой мыши:
        if(InputProcess.touchdownbutton == Input.Buttons.LEFT) {
            Vector2 hoverpos = new Vector2(world.GetHoverPos(camera));
            Block block = new BonfireBlock((int)hoverpos.x, (int)hoverpos.y);
            try {
                if(!Objects.equals(world.BlockList[(int) hoverpos.x][(int) hoverpos.y].ID, block.ID)) {
                    world.SetBlock(block, hoverpos);
                }
            } catch(Exception ignored) { }
        }
        // Установить блок правой левой мыши:
        if(InputProcess.touchdownbutton == Input.Buttons.RIGHT) {
            Vector2 hoverpos = new Vector2(world.GetHoverPos(camera));
            try {
                if(world.BlockList[(int)hoverpos.x][(int)hoverpos.y].BackgroundBlock != null) {
                    world.BreakBlock(hoverpos);
                }
            } catch(Exception ignored) { }
        }
    }

    // Тут всё что должно отрисовываться:
    @Override public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.

        // Получение дельты:
        DeltaTime = Gdx.graphics.getDeltaTime() * 60; // Изменяя последнее число, можно менять скорость протекания процессов в игре.

        Update();                                     // Обновление основной логики.
        CameraUpdate();                               // Обновить камеру.
        uicamera.update();                            // Обновить камеру интерфейса.

        // Отрисовка спрайтов:
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        world.Draw(batch, camera, DeltaTime); // Отрисовка карты.
        // Проходиться по существам и отрисовывать их:
        for(Alive alive : alives) {
            alive.Draw(batch);
        }
        batch.end();

        // Отрисовка ui:
        uibatch.setProjectionMatrix(uicamera.combined);

        // Отрисовать панель разработчика:
        if(IsVisibleDevPanel)
            Main.DrawDevPanel(shapeRenderer, uibatch, uicamera, DeltaTime, world.GetAlivePos(alives.get(0).Pos),
                    world.GetHoverPos(camera), new Vector2(world.Width, world.Height));

        uibatch.begin();
        // Отрисовка интерфейса...
        uibatch.end();
    }

    @Override public void resize(int width, int height) {
        // Обновить разрешение ui камеры:
        uicamera.viewportWidth = width; uicamera.viewportHeight = height;
        uicamera.update();

        // Обновить разрешение камеры:
        camera.viewportWidth = width; camera.viewportHeight = height;
        camera.update();
    }

    @Override public void pause() {
        // Какой-то код.
    }

    @Override public void resume() {
        // Какой-то код.
    }

    @Override public void hide() {
        // Какой-то код.
    }

    @Override public void dispose() {
        // Пропишите всё что использует ОЗУ.
        Main.AssetsData.AssetsDispose();
        batch.dispose();
        uibatch.dispose();
        shapeRenderer.dispose();
    }

    // Функция обновления камеры:
    void CameraUpdate() {
        float CameraMoveSpeed = 0.05f; // Скорость передвижения камеры.
        float CameraZoomSpeed = 0.1f;  // Скорость зума камеры.
        float CameraZoomMin = 0.5f;    // Максимальное приближение.
        float CameraZoomMax = 3f;      // Максимальное отдаление.
        if(Gdx.app.getType() == Application.ApplicationType.Android) CameraZoomMax = 1.5f;

        // Плавное перемещение камеры к цели:
        camera.position.x += ((CameraTarget.x - camera.position.x) * CameraMoveSpeed) * DeltaTime;
        camera.position.y += ((CameraTarget.y - camera.position.y) * CameraMoveSpeed) * DeltaTime;

        // Зум камеры:
        CameraZoomTarget += Main.GetScroll() / 4;
        camera.zoom += ((CameraZoomTarget - camera.zoom) * CameraZoomSpeed) * DeltaTime;

        // Установить ограничение масштабирования камеры:
        if(camera.zoom < CameraZoomMin) { camera.zoom = CameraZoomMin; }
        if(camera.zoom > CameraZoomMax) { camera.zoom = CameraZoomMax; }
        if(CameraZoomTarget < CameraZoomMin) { CameraZoomTarget = CameraZoomMin; }
        if(CameraZoomTarget > CameraZoomMax) { CameraZoomTarget = CameraZoomMax; }

        camera.update();
    }
}
