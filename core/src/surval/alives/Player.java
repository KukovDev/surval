//
// Класс игрока.
//

package surval.alives;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import surval.core.*;

public class Player extends Alive {
    private boolean IsFlip;
    private float AnimStep;
    float AnimSpeed;
    int AnimFrames;

    public Player(Vector2 Pos) {
        this.Pos = Pos;

        HP = 100;          // Очки здоровья.
        Speed = 8f;        // Скорость передвижения.
        Width = 160;       // Ширина текстуры.
        Height = 160;      // Высота текстуры.
        AnimSpeed = 0.15f; // Скорость анимации.
        AnimFrames = 2;    // Кол-во спрайтов анимации.
        IsFlip = false;    // Отразить текстуру?
        ID = "player";     // Айди существа -> Игрок.
    }

    // Функция обновления существа:
    public void Update(float DeltaTime) {
        Vector2 OldPos = new Vector2(Pos);
        float SpeedVar = Speed;

        // Управление: // TODO сделать поддержку сенсорного экрана:
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) SpeedVar = Speed*2f;

        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            Pos.y += SpeedVar * DeltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Pos.x -= SpeedVar * DeltaTime;
            IsFlip = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            Pos.y -= SpeedVar * DeltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Pos.x += SpeedVar * DeltaTime;
            IsFlip = false;
        }

        // Анимация:
        // Если ни одна клавиша не нажимается то установить текстуру:
        if(!Gdx.input.isKeyPressed(Input.Keys.W)     &&
           !Gdx.input.isKeyPressed(Input.Keys.A)     &&
           !Gdx.input.isKeyPressed(Input.Keys.S)     &&
           !Gdx.input.isKeyPressed(Input.Keys.D)     &&
           !Gdx.input.isKeyPressed(Input.Keys.UP)    &&
           !Gdx.input.isKeyPressed(Input.Keys.LEFT)  &&
           !Gdx.input.isKeyPressed(Input.Keys.DOWN)  &&
           !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            AnimStep = 0f;
        } else { // Если нажимаются, то анимировать:
            // Проверка на передвижение:
            if(!Objects.equals(Pos, OldPos)) {
                AnimStep += AnimSpeed * DeltaTime;
                if(AnimStep >= AnimFrames+1) AnimStep = 1f;
            } else AnimStep = 0f;
        }

    }

    // Функция отрисовки существа:
    public void Draw(SpriteBatch batch) {
        if(IsFlip) {
            batch.draw(Main.AssetsData.Player.get((int)AnimStep),
                    Pos.x-Width/2f+Width, Pos.y-Height/2f, -Width, Height);
        } else {
            batch.draw(Main.AssetsData.Player.get((int)AnimStep),
                    Pos.x-Width/2f, Pos.y-Height/2f, Width, Height);
        }
    }
}
