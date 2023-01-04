//
// Этот код загружает спрайты, звуки, и так далее.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;

public class LoadAssets {
    // Прочие поля:
    public Array<Texture> snow;
    public Array<Texture> bonfire;
    public Texture nullblock;



    // Функция вызывается автоматически при создании экземпляра этого класса:
    public LoadAssets() {
        snow = new Array<>();
        bonfire = new Array<>();
    }

    // Загружает ассеты:
    public void AssetsLoad() {
        snow.addAll(
                LoadTexture("sprites/blocks/env/snow1.png"),
                LoadTexture("sprites/blocks/env/snow2.png"),
                LoadTexture("sprites/blocks/env/snow3.png"));

        bonfire.addAll(
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

        nullblock = LoadTexture("sprites/blocks/env/nullblock.png");
    }

    // Удаляет всё что было загружено:
    public void AssetsDispose() {
        snow = ArrayTextureDispose(snow);
        bonfire = ArrayTextureDispose(bonfire);
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
