//
// Класс игрока.
//

package surval.alives;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import surval.core.Main;

public class Player extends Alive {
    public Texture stand;
    public Texture[] run;
    private boolean IsFlip;
    private Texture CurrentTexture;
    private float AnimStep;
    float AnimSpeed;
    int AnimFrames;

    public Player(Vector2 Pos) {
        this.Pos = Pos;
        stand = Main.AssetsData.Player.get(0);
        run = new Texture[] {Main.AssetsData.Player.get(1), Main.AssetsData.Player.get(2)};

        HP = 100;               // Очки здоровья.
        Speed = 10f;            // Скорость передвижения.
        Width = 192;            // Ширина текстуры.
        Height = 192;           // Высота текстуры.
        AnimSpeed = 0.15f;      // Скорость анимации.
        AnimFrames = 2;         // Кол-во спрайтов анимации.
        IsFlip = false;         // Отразить текстуру?
        ID = AliveType.Player;  // Айди существа -> Игрок.
        CurrentTexture = stand; // Текущая текстура.
    }

    // Функция обновления существа:
    public void Update(float DeltaTime) {
        // Управление:
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            Pos.y += Speed * DeltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Pos.x -= Speed * DeltaTime;
            IsFlip = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            Pos.y -= Speed * DeltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Pos.x += Speed * DeltaTime;
            IsFlip = false;
        }

        // Анимация:
        // Если ни одна клавиша не нажимается то установить текстуру:
        if(!Gdx.input.isKeyPressed(Input.Keys.W) &&
           !Gdx.input.isKeyPressed(Input.Keys.A) &&
           !Gdx.input.isKeyPressed(Input.Keys.S) &&
           !Gdx.input.isKeyPressed(Input.Keys.D) &&
           !Gdx.input.isKeyPressed(Input.Keys.UP) &&
           !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
           !Gdx.input.isKeyPressed(Input.Keys.DOWN) &&
           !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            CurrentTexture = stand;
        } else { // Если нажимаются, то анимировать:
            CurrentTexture = run[(int)AnimStep];
            AnimStep += AnimSpeed * DeltaTime;
            if(AnimStep >= AnimFrames) AnimStep = 0f;
        }
    }

    // Функция отрисовки существа:
    public void Draw(SpriteBatch batch) {
        if(IsFlip) {
            batch.draw(CurrentTexture, Pos.x-Width/2f+Width, Pos.y-Height/2f, -Width, Height);
        } else {
            batch.draw(CurrentTexture, Pos.x-Width/2f, Pos.y-Height/2f, Width, Height);
        }
    }
}
