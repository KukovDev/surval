//
// Этот код является стартовой точкой этого приложения.
// Этот код настраивает окно и переключается на один из скринов.
//

package surval.core;

import com.badlogic.gdx.*;
import surval.screens.*;

public class Main extends Game {
	public static LoadAssets AssetsData; // Класс ассетов.

	@Override // Функция вызывается один раз при запуске приложения:
	public void create() {
		// Настройка окна:
		Gdx.graphics.setTitle("surval");   // Заголовок окна.
		Gdx.graphics.setResizable(true);   // Масштабируемость окна.
		Gdx.graphics.setForegroundFPS(60); // Установить FPS.
		Gdx.graphics.setVSync(false);      // Вертикальная синхронизация.

		AssetsData = new LoadAssets(); // Создать экземпляр класса.
		AssetsData.AssetsLoad();       // Загрузить ассеты.

		setScreen(new GameScreen()); // Переключиться на другой скрин.
	}

	@Override // Функция вызывается один раз при закрытии приложения:
	public void dispose() {
		AssetsData.AssetsDispose();
	}
}
