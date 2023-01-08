//
// Этот код является стартовой точкой этого приложения.
// Этот код настраивает окно и переключается на один из скринов.
// Так же этот код содержит прочие полезные функции.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.input.*;
import com.sun.org.apache.xpath.internal.operations.Or;
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

	// Получить новый шрифт:
	public static BitmapFont FontUpdateParameters(String FontLink, FreeTypeFontGenerator.FreeTypeFontParameter parameters) {
		return new FreeTypeFontGenerator(Gdx.files.internal(FontLink)).generateFont(parameters);
	}

	// Нарисовать заполненный прямоугольник:
	public static void DrawRectFilled(int PosX, int PosY, int Width, int Height, Color color,
									  ShapeRenderer shapeRenderer, OrthographicCamera camera) {
		// Если смешивание цветов не включено, включаем так:
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		// Не забудьте задать матрицу проекции таким образом:
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(color);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(PosX, PosY, Width, Height);
		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	// Отрисовать панель разработчика:
	public static void DrawDevPanel(ShapeRenderer shapeRenderer, SpriteBatch batch, OrthographicCamera camera) {
		DrawRectFilled(
				(int)(camera.position.x-((camera.viewportWidth/2)*camera.zoom)),
				(int)(camera.position.y-((camera.viewportHeight/2)*camera.zoom)),
				(int)(192*camera.zoom),
				(int)(camera.viewportHeight*camera.zoom),
				new Color(0, 0, 0, .75f), shapeRenderer, camera);
		batch.begin();
		AssetsData.PixelFont.getData().setScale(camera.zoom);
		AssetsData.PixelFont.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(),
				(int)(camera.position.x-((camera.viewportWidth/2)*camera.zoom))+4,
				(int)(camera.position.y-((camera.viewportHeight/2)*camera.zoom))+(camera.viewportHeight*camera.zoom));
		batch.end();
	}
}
