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

        UI.HotBarCellsList[0] = Main.AssetsData.resources.Bonfire;
        UI.HotBarCellsList[2] = Main.AssetsData.resources.NullRes;
        UI.HotBarCellsList[5] = Main.AssetsData.resources.Stoneland;
        UI.HotBarCellsList[6] = Main.AssetsData.resources.Snowland;
    }

    // Тут вся логика:
    public void Update() {
        // Если нажата клавиша CTRL или SYM, и английская 'P', показать панель разработчика. Если нажата ESC, скрыть:
        if((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SYM)) &&
            Gdx.input.isKeyPressed(Input.Keys.P))          { IsVisibleDevPanel = true;  }
        else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { IsVisibleDevPanel = false; }

        // Если нажата клавиша CTRL или SYM, менять выбранный ресурс в горячей панели:
        if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SYM)) {
            UI.HotBarTargetCell += InputProcess.scroll;
            Main.GetScroll();
        } else { // Иначе масштабировать камеру:
            CameraZoomTarget += Main.GetScroll() / 4;
        }

        // Обновлять блоки карты в отдельном потоке:
        new Thread(new Runnable(){
            @Override
            public void run() {
                world.Update(DeltaTime); // Отрисовка карты.
            }
        }).start();

        // Проходиться по существам и обновлять их в отдельном потоке:
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(Alive alive : alives) {
                    alive.Update(DeltaTime);
                    if(Objects.equals(alive.ID, "player")) CameraTarget = alive.Pos; // Переместить цель камеры.
                }
            }
        }).start();

        // TODO переделать -> перенести в отдельный обработчик работы с блоками и сделать поддержку сенсорного экрана:
        // Установить блок левой кнопкой мыши:
        if(InputProcess.touchdownbutton == Input.Buttons.LEFT) {
            Vector2 hoverpos = new Vector2(world.GetHoverPos(camera));
            Block block;

            // Костёр:
            if(!Objects.equals(UI.HotBarCellsList[UI.HotBarTargetCell], null)) {
                if(Objects.equals(UI.HotBarCellsList[UI.HotBarTargetCell], Main.AssetsData.resources.Bonfire))
                    block = new BonfireBlock((int)hoverpos.x, (int)hoverpos.y);
                    // Снежная поверхность:
                else if(Objects.equals(UI.HotBarCellsList[UI.HotBarTargetCell], Main.AssetsData.resources.Snowland))
                    block = new SnowlandBlock((int) hoverpos.x, (int) hoverpos.y);
                    // Каменная поверхность:
                else if(Objects.equals(UI.HotBarCellsList[UI.HotBarTargetCell], Main.AssetsData.resources.Stoneland))
                    block = new StonelandBlock((int)hoverpos.x, (int)hoverpos.y);
                    // Иначе, поставить блок 'null':
                else block = new NullBlock((int)hoverpos.x, (int)hoverpos.y);

                try {
                    world.SetBlock(block, hoverpos);
                } catch(Exception ignored) { }
            }
        }
        // Удалить блок правой кнопкой мыши:
        if(InputProcess.touchdownbutton == Input.Buttons.RIGHT) {
            Vector2 hoverpos = new Vector2(world.GetHoverPos(camera));
            try {
                world.BreakBlock(hoverpos);
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
        world.Draw(batch, camera); // Отрисовка карты.

        // Проходиться по существам и отрисовывать их:
        // TODO сделать проверку на то, видит ли камера, существо.
        for(Alive alive : alives) {
            alive.Draw(batch);
        }
        batch.end();

        // Отрисовка ui:
        uibatch.setProjectionMatrix(uicamera.combined);
        UI.DrawHotBar(uibatch, uicamera); // Отрисовать хот бар инвентаря.
        // Отрисовать панель разработчика:
        if(IsVisibleDevPanel)
            UI.DrawDevPanel(shapeRenderer, uibatch, uicamera, world.GetAlivePos(new Vector2(camera.position.x, camera.position.y)),
                    camera.zoom, DeltaTime, world.GetAlivePos(alives.get(0).Pos),
                    world.GetHoverPos(camera), new Vector2(world.Width, world.Height));
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
        camera.zoom += ((CameraZoomTarget - camera.zoom) * CameraZoomSpeed) * DeltaTime;

        // Установить ограничение масштабирования камеры:
        if(camera.zoom < CameraZoomMin) { camera.zoom = CameraZoomMin; }
        if(camera.zoom > CameraZoomMax) { camera.zoom = CameraZoomMax; }
        if(CameraZoomTarget < CameraZoomMin) { CameraZoomTarget = CameraZoomMin; }
        if(CameraZoomTarget > CameraZoomMax) { CameraZoomTarget = CameraZoomMax; }

        camera.update();
    }
}
