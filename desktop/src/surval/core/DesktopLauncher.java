package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.badlogic.gdx.tools.texturepacker.*;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		try { // Обработчик ошибок нужен затем что если игра скомпилирована, он не пытался создать новые атласы.
			// Атласы:
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.minWidth = 512;
			settings.minHeight = 512;
			// Атлас блоков:
			TexturePacker.process(settings,
					"core/assets/sprites/blocks/",
					"core/assets/sprites/atlases/", "blocks");
			// Атлас существ:
			TexturePacker.process(settings,
					"core/assets/sprites/alives/",
					"core/assets/sprites/atlases/", "alives");
			// Атлас интерфейса:
			TexturePacker.process(settings,
					"core/assets/sprites/ui/",
					"core/assets/sprites/atlases/", "ui");
		} catch(Exception ignored) { }

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowIcon(Files.FileType.Internal, "sprites/icons/icon.png"); // Установить иконку окна (кроме MacOS).
		config.setWindowedMode(960, 540);                                     // Установить размер окна.
		new Lwjgl3Application(new Main(), config);                                        // Применить конфигурацию.
	}
}
