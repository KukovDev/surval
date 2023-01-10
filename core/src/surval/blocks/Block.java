//
// Класс блока.
//

package surval.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Block {
    public Vector2 Pos;           // Позиция блока.
    public String ID;             // Айди блока.
    public String Type;           // Тип блока. Либо "land"(блок-поверхность) либо "block"(блок).
    public Block BackgroundBlock; // Фоновый блок.

    public Block() {
        Pos = new Vector2();
    }

    // Функция обновления блока:
    public void Update(float DeltaTime) {
        // Код этой функции прописывается отдельно в каждом классе.
    }

    // Функция для отрисовки блока:
    public void Draw(SpriteBatch batch, int BlockSize) {
        // Код этой функции прописывается отдельно в каждом классе.
    }
}
