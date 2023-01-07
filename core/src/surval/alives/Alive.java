//
// Класс живых существ.
//

package surval.alives;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Alive {
    public Vector2 Pos; // Позиция.
    public int HP;      // Очки здоровья.
    public float Speed; // Скорость передвижения.
    public int Width;   // Ширина.
    public int Height;  // Высота.
    public String ID;   // Айди существа.

    public Alive() {
        Pos = new Vector2();
    }

    // Функция обновления существа:
    public void Update(float DeltaTime) {
        // Код этой функции прописывается отдельно в каждом классе.
    }

    // Функция отрисовки существа:
    public void Draw(SpriteBatch batch) {
        // Код этой функции прописывается отдельно в каждом классе.
    }
}
