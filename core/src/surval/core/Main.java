//
// Этот код является стартовой точкой этого приложения.
// Этот код настраивает окно и переключается на один из скринов.
// Так же этот код содержит прочие полезные функции.
//

package surval.core;

import com.badlogic.gdx.*;
import surval.screens.*;

public class Main extends Game {
	public static LoadAssets AssetsData; // Класс ассетов.
	public static final int FPS = 60;    // Установленный FPS.

	@Override // Функция вызывается один раз при запуске приложения:
	public void create() {
		Gdx.input.setInputProcessor(new InputDesktop()); // Сделать класс InputDesktop() основным обработчиком ввода.

		// Настройка окна:
		Gdx.graphics.setTitle("surval");    // Заголовок окна.
		Gdx.graphics.setResizable(true);    // Масштабируемость окна.
		Gdx.graphics.setForegroundFPS(FPS); // Установить FPS.
		Gdx.graphics.setVSync(false);       // Вертикальная синхронизация.

		AssetsData = new LoadAssets(); // Создать экземпляр класса.
		AssetsData.AssetsLoad();       // Загрузить ассеты.

		setScreen(new GameScreen()); // Переключиться на другой скрин.
	}

	@Override // Функция вызывается один раз при закрытии приложения:
	public void dispose() {
		AssetsData.AssetsDispose();
	}

	// Функция для получения текущего FPS:
	public static int GetFPS() {
		return Gdx.graphics.getFramesPerSecond();
	}

	// Функция для получения скролла мыши на PC:
	public static float GetMouseScroll() {
		float scroll = InputDesktop.scroll;
		InputDesktop.scroll = 0f;
		return scroll;
	}
}
