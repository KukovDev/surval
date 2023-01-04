//
// Этот код реализует игру.
// Этот код - первое на что переключается первичный код игры (surval.core.Main).
//

package surval.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import surval.core.*;

public class GameScreen implements Screen {
    // Прочие поля:
    private SpriteBatch batch;         // Партия спрайтов для отрисовки.
    private OrthographicCamera camera; // Камера 2D.
    private LoadAssets AssetsData;     // Ассеты.



    @Override // Функция вызывается один раз при переключении на этот скрин:
    public void show() {
        batch = new SpriteBatch();


        // Создать камеру:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 0, 0);


        AssetsData = Main.AssetsData; // Сохранить ассеты.
    }

    @Override // Функция вызывается FPS-количество раз в секунду:
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.
        camera.update();                              // Обновить камеру.
        batch.setProjectionMatrix(camera.combined);   // Использовать систему координат, указанную камерой.

        // Отрисовка спрайтов:
        batch.begin();

        batch.draw(AssetsData.snow.get(0), 0f, 0f);

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
}
