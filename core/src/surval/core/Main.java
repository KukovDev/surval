package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	// Прочие поля:
	private OrthographicCamera camera; // Камера 2D.


	@Override // Функция вызывается один раз при запуске приложения:
	public void create() {
		// Настройка окна:
		Gdx.graphics.setTitle("surval");   // Заголовок окна.
		Gdx.graphics.setResizable(true);   // Масштабируемость окна.
		Gdx.graphics.setForegroundFPS(60); // Установить FPS.
		Gdx.graphics.setVSync(false);      // Вертикальная синхронизация.


		// Создать камеру:
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override // Функция вызывается FPS-количество раз в секунду:
	public void render() {
		ScreenUtils.clear(0f, 0f, 0f, 1f); // Очистить экран.

	}

	@Override // Функция вызывается при изменении размера окна:
	public void resize(int width, int height) {
		// Обновить разрешение камеры:
		camera.viewportWidth = width; camera.viewportHeight = height;
		camera.update();
	}

	@Override // Функция вызывается при расфокусировке приложения:
	public void pause() {
		// Какой-то код.
	}

	@Override // Функция вызывается при фокусировке приложения:
	public void resume() {
		// Какой-то код.
	}

	@Override // Функция вызывается при закрытии приложения:
	public void dispose() {
		// Пропишите всё что использует ОЗУ.
	}
}
