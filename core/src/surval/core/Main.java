//
// Этот код является стартовой точкой этого приложения.
// Этот код настраивает окно и переключается на один из скринов.
// Так же этот код содержит прочие полезные функции.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.input.*;
import surval.screens.*;

public class Main extends Game {
	public static LoadAssets AssetsData; // Класс ассетов.
	public static final int FPS = 60;    // Установленный FPS.


	@Override public void create() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputProcess());
		multiplexer.addProcessor(new GestureDetector(new GestureHandler()));
		Gdx.input.setInputProcessor(multiplexer);

		// Настройка окна:
		Gdx.graphics.setTitle("surval");    // Заголовок окна.
		Gdx.graphics.setResizable(true);    // Масштабируемость окна.
		Gdx.graphics.setForegroundFPS(FPS); // Установить FPS.
		Gdx.graphics.setVSync(false);       // Вертикальная синхронизация.

		AssetsData = new LoadAssets(); // Создать экземпляр класса.
		AssetsData.AssetsLoad();       // Загрузить ассеты.

		setScreen(new GameScreen()); // Переключиться на другой скрин.
	}

	@Override public void dispose() {
		AssetsData.AssetsDispose();
	}

	// Функция для получения текущего FPS:
	public static int GetFPS() {
		return Gdx.graphics.getFramesPerSecond();
	}

	// Передвигается ли мышь/палец:
	public static boolean IsTouchDrag() {
		boolean istouchdrag = InputProcess.touchdrag;
		InputProcess.touchdrag = false;
		return istouchdrag;
	}

	// Функция для получения скролла на PC и мобильных устройствах:
	public static float GetScroll() { // TODO функция на мобильных устройствах может работать не корректно.
		float scroll = InputProcess.scroll;
		if(scroll != 0f) InputProcess.scroll = 0f;
		else {
			if(Gdx.input.isTouched(0) && Gdx.input.isTouched(1) && IsTouchDrag()) {
				if(GestureHandler.zoomdist < GestureHandler.oldzoomdist) { scroll = 1f; }
				else if(GestureHandler.zoomdist > GestureHandler.oldzoomdist) { scroll = -1f; }
				else { scroll = 0f;  }
			}
		}
		return scroll;
	}
}
