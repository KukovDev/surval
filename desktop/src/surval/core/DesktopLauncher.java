package surval.core;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setWindowIcon(Files.FileType.Internal, "sprites/icons/icon.png"); // Установить иконку окна (кроме MacOS).
		config.setWindowedMode(960, 540); // Установить размер окна.

		new Lwjgl3Application(new Main(), config); // Применить конфигурацию.
	}
}
