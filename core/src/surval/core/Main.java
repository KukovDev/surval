//
// Этот код является стартовой точкой этого приложения.
// Этот код настраивает окно и переключается на один из скринов.
//

package surval.core;

import com.badlogic.gdx.*;
import surval.screens.MainMenuScreen;

public class Main extends Game {
	@Override // Функция вызывается один раз при запуске приложения:
	public void create() {
		// Настройка окна:
		Gdx.graphics.setTitle("surval");   // Заголовок окна.
		Gdx.graphics.setResizable(true);   // Масштабируемость окна.
		Gdx.graphics.setForegroundFPS(60); // Установить FPS.
		Gdx.graphics.setVSync(false);      // Вертикальная синхронизация.

		setScreen(new MainMenuScreen()); // Переключиться на другой скрин.
	}
}
