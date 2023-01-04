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
import surval.core.*;

public class GameScreen implements Screen {
    // Прочие поля:
    private SpriteBatch batch;         // Партия спрайтов для отрисовки.
    private OrthographicCamera camera; // Камера 2D.
    private LoadAssets AssetsData;     // Ассеты.
    private Vector2 CameraTarget;      // Цель за которой будет следить камера.
    private float DeltaTime = 1;       // Дельта времени.
    private World world;               // Карта.



    @Override // Функция вызывается один раз при переключении на этот скрин:
    public void show() {
        batch = new SpriteBatch();

        // Создать камеру:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        CameraTarget = new Vector2(0, 0);

        AssetsData = Main.AssetsData; // Сохранить ассеты.

        world = new World(512, 512);
        world.Generate();
    }

    @Override // Функция вызывается FPS-количество раз в секунду:
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.

        // Получение дельты:
        if((float)Main.FPS/Gdx.graphics.getFramesPerSecond() != Double.POSITIVE_INFINITY)
            DeltaTime = (float)Main.FPS / Gdx.graphics.getFramesPerSecond();

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            CameraTarget.y += 10 * DeltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            CameraTarget.x -= 10 * DeltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            CameraTarget.y -= 10 * DeltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            CameraTarget.x += 10 * DeltaTime;


        CameraUpdate();                               // Обновить камеру.
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
        batch.setProjectionMatrix(camera.combined);   // Использовать систему координат, указанную камерой.

        // Отрисовка спрайтов:
        batch.begin();

        batch.draw(AssetsData.snow.get(0), 0f, 0f);
        world.Draw(batch, camera); // Отрисовка карты.

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

        camera.update();

        // Плавное перемещение камеры к цели:
        camera.position.x += ((CameraTarget.x - camera.position.x) * CameraMoveSpeed) * DeltaTime;
        camera.position.y += ((CameraTarget.y - camera.position.y) * CameraMoveSpeed) * DeltaTime;
    }
}
