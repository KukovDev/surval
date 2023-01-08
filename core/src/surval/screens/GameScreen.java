//
// Этот код реализует игру.
// Этот код - первое на что переключается первичный код игры (surval.core.Main).
//

package surval.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import java.util.*;
import surval.alives.*;
import surval.blocks.*;
import surval.core.*;

public class GameScreen implements Screen {
    // Прочие поля:
    private SpriteBatch batch;           // Партия спрайтов для отрисовки.
    private LoadAssets AssetsData;       // Ассеты.
    private OrthographicCamera camera;   // Камера 2D.
    private Vector2 CameraTarget;        // Цель за которой будет следить камера.
    private float CameraZoomTarget = 1f; // Цель зума камеры. Это поле, текущего (не плавного) зума.
    private float DeltaTime = 1;         // Дельта времени.
    private World world;                 // Карта.
    private List<Alive> alives;          // Список существ.


    @Override public void show() {
        batch = new SpriteBatch();

        // Создать камеру:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        CameraTarget = new Vector2(0, 0);

        AssetsData = Main.AssetsData; // Ассеты.

        // Создание карты:
        world = new World(1024, 1024);
        world.Generate();

        // Создание списка существ и добавление игрока:
        alives = new ArrayList<>();
        alives.add(new Player(new Vector2((world.Width)*world.BlockSize/2f, (world.Height)*world.BlockSize/2f)));
        camera.position.x = alives.get(0).Pos.x; camera.position.y = alives.get(0).Pos.y;
    }

    // Тут вся логика:
    public void Update() {
        Gdx.graphics.setTitle("FPS: " + Main.GetFPS());

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
            if(!Objects.equals(world.BlockList[(int) hoverpos.x][(int) hoverpos.y].ID, block.ID)) {
                world.SetBlock(block, hoverpos);
            }
        }
        // Установить блок правой левой мыши:
        if(InputProcess.touchdownbutton == Input.Buttons.RIGHT) {
            Vector2 hoverpos = new Vector2(world.GetHoverPos(camera));
            if(world.BlockList[(int)hoverpos.x][(int)hoverpos.y].BackgroundBlock != null) {
                world.BreakBlock(hoverpos);
            }
        }
    }

    // Тут всё что должно отрисовываться:
    @Override public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.

        // Получение дельты:
        DeltaTime = Gdx.graphics.getDeltaTime() * 60; // Изменяя последнее число, можно менять скорость протекания процессов в игре.

        Update();                                     // Обновление основной логики.
        CameraUpdate();                               // Обновить камеру.
        batch.setProjectionMatrix(camera.combined);   // Использовать систему координат, указанную камерой.

        // Отрисовка спрайтов:
        batch.begin();
        world.Draw(batch, camera, DeltaTime); // Отрисовка карты.
        // Проходиться по существам и отрисовывать их:
        for(Alive alive : alives) {
            alive.Draw(batch);
        }
        batch.end();
    }

    @Override public void resize(int width, int height) {
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
        AssetsData.AssetsDispose();
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
