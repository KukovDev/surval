//
// Класс блока.
//

package surval.blocks;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Block {
    public Vector2 Pos;
    public String ID;

    public Block() {
        Pos = new Vector2();
    }

    // Функция для отрисовки блока:
    public void Draw(SpriteBatch batch, int BlockSize) {
        // Код этой функции прописывается отдельно в каждом классе.
    }
}
