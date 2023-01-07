//
// Этот код загружает спрайты, звуки, и так далее.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;

public class LoadAssets {
    // Блоки:
    public Array<Texture> Snow;    // Снежная поверхность.
    public Array<Texture> Stone;   // Каменная поверхность.
    public Array<Texture> Bonfire; // Костёр.
    public Texture NullBlock;      // Неизвестно.

    // Живые существа:
    public Array<Texture> Player; // Игрок.


    // Функция вызывается автоматически при создании экземпляра этого класса:
    public LoadAssets() {
        // Блоки:
        Snow = new Array<>();
        Stone = new Array<>();
        Bonfire = new Array<>();

        // Живые существа:
        Player = new Array<>();
    }

    // Загружает ассеты:
    public void AssetsLoad() {
        LoadBlocks();
        LoadAlives();
    }

    // Удаляет всё что было загружено:
    public void AssetsDispose() {
        Snow = ArrayTextureDispose(Snow);
        Stone = ArrayTextureDispose(Stone);
        Bonfire = ArrayTextureDispose(Bonfire);
    }

    // Загрузить текстуры блоков:
    void LoadBlocks() {
       Snow.addAll(
                LoadTexture("sprites/blocks/env/snow1.png"),
                LoadTexture("sprites/blocks/env/snow2.png"),
                LoadTexture("sprites/blocks/env/snow3.png"));

       Stone.addAll(
                LoadTexture("sprites/blocks/env/stone1.png"),
                LoadTexture("sprites/blocks/env/stone2.png"),
                LoadTexture("sprites/blocks/env/stone3.png"));

       Bonfire.addAll(
                LoadTexture("sprites/blocks/bonfire/bonfire1.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire2.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire3.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire4.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire5.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire6.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire7.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire8.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire new.png"),
                LoadTexture("sprites/blocks/bonfire/bonfire out.png"));

       NullBlock = LoadTexture("sprites/blocks/env/nullblock.png");
    }

    // Загрузка живых существ:
    void LoadAlives() {
        Player.addAll(
                LoadTexture("sprites/alives/player/stand.png"),
                LoadTexture("sprites/alives/player/run1.png"),
                LoadTexture("sprites/alives/player/run2.png"));
    }

    // Удаляет все текстуры из переданного в него списка текстур:
    Array<Texture> ArrayTextureDispose(Array<Texture> list) {
        // Функция проходится по списку и удаляет текстуру:
        for(Texture texture : list) {
            texture.dispose();
        } list.clear();
        return list;
    }

    // Загружает и возвращает текстуру:
    Texture LoadTexture(String path) {
        return new Texture(Gdx.files.internal(path));
    }
}
