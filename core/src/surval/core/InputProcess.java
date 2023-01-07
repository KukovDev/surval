//
// Этот код реализует поддержку ввода.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;

public class InputProcess implements InputProcessor {
    public static float scroll;
    public static boolean touchdrag;

    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        touchdrag = true;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        scroll = amountY;
        return false;
    }
}
