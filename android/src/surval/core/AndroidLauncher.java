package surval.core;

import android.content.pm.*;
import android.os.*;
import com.badlogic.gdx.backends.android.*;

public class AndroidLauncher extends AndroidApplication {
	@Override protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		initialize(new Main(), config);
	}
}
