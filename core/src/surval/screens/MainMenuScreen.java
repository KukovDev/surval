//
// Этот код реализует основное меню игры.
// Этот код - первое на что переключается первичный код игры (surval.core.Main).
//

package surval.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    // Прочие поля:
    private OrthographicCamera camera; // Камера 2D.



    @Override // Функция вызывается один раз при переключении на этот скрин:
    public void show() {
        // Создать камеру:
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override // Функция вызывается FPS-количество раз в секунду:
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.

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
    }
}
