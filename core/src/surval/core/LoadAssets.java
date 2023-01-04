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



    // Функция вызывается автоматически при создании экземпляра этого класса:
    public LoadAssets() {
        snow = new Array<>();
    }

    // Загружает ассеты:
    public void AssetsLoad() {
        snow.addAll(LoadTexture("sprites/blocks/env/snow1.png"),
                    LoadTexture("sprites/blocks/env/snow2.png"),
                    LoadTexture("sprites/blocks/env/snow3.png"));
    }

    // Удаляет всё что было загружено:
    public void AssetsDispose() {
        snow = ArrayTextureDispose(snow);
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
