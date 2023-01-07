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



    @Override // Функция вызывается один раз при переключении на этот скрин:
    public void show() {
        batch = new SpriteBatch();

        // Создать камеру:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        CameraTarget = new Vector2(0, 0);

        AssetsData = Main.AssetsData; // Сохранить ассеты.

        world = new World(1024, 1024);
        world.Generate();

        alives = new ArrayList<>();
        alives.add(new Player(new Vector2((world.Width/2f)*world.BlockSize, (world.Height/2f)*world.BlockSize)));
        camera.position.x = alives.get(0).Pos.x; camera.position.y = alives.get(0).Pos.y;
    }

    // Тут вся логика:
    public void Update() {
        Gdx.graphics.setTitle("FPS: " + Main.GetFPS() + " | " +
                              "Map-Size: W-" + world.Width + " H-" + world.Height + " | " +
                              "Camera-Zoom: " + camera.zoom + " | " +
                              "Delta-Time: " + DeltaTime);

        // Проходиться по существам и обновлять их:
        for(Alive alive : alives) {
            alive.Update(DeltaTime);
            if(Objects.equals(alive.ID, AliveType.Player))
                CameraTarget = alive.Pos; // Передвигать камеру.
        }
    }

    // Тут всё что должно отрисовываться:
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.

        // Получение дельты:
        DeltaTime = Gdx.graphics.getDeltaTime() * 60; // Изменяя последнее число, можно менять скорость протекания процессов в игре.

        Update();                                     // Обновление основной логики.
        CameraUpdate();                               // Обновить камеру.
        batch.setProjectionMatrix(camera.combined);   // Использовать систему координат, указанную камерой.

        // Отрисовка спрайтов:
        batch.begin();
        world.Draw(batch, camera); // Отрисовка карты.

        // Проходиться по существам и отрисовывать их:
        for(Alive alive : alives) {
            alive.Draw(batch);
        }

        batch.end();
    }

    @Override // Функция вызывается при изменении размера окна:
    public void resize(int width, int height) {
        // Обновить разрешение камеры:
        camera.viewportWidth = width; camera.viewportHeight = height;
        camera.update();
    }

    @Override // Функция вызывается при расфокусировке этого приложения:
    public void pause() {
        // Какой-то код.
    }

    @Override // Функция вызывается при фокусировке на это приложение:
    public void resume() {
        // Какой-то код.
    }

    @Override // Функция вызывается один раз при переключении на другой скрин:
    public void hide() {
        // Какой-то код.
    }

    @Override // Функция вызывается при закрытии приложения:
    public void dispose() {
        // Пропишите всё что использует ОЗУ.
        AssetsData.AssetsDispose();
    }

    // Функция обновления камеры:
    void CameraUpdate() {
        float CameraMoveSpeed = 0.05f; // Скорость передвижения камеры.
        float CameraZoomSpeed = 0.1f;  // Скорость зума камеры.
        float CameraZoomMin = 0.75f;   // Максимальное приближение.
        float CameraZoomMax = 3f;      // Максимальное отдаление.

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
