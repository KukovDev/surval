//
// Этот код загружает спрайты, звуки, и так далее.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.utils.*;

public class LoadAssets {
    public Resources resources; // Ресурсы.

    // Атлас блоков:
    public TextureAtlas blocks_atlas;

    // Атлас живых существ:
    public TextureAtlas alives_atlas;

    // Атлас ui:
    public TextureAtlas ui_atlas;

    // Блоки:
    public Array<Sprite> Snow;    // Снежная поверхность.
    public Array<Sprite> Stone;   // Каменная поверхность.
    public Array<Sprite> Bonfire; // Костёр.
    public Sprite NullBlock;      // Неизвестно.

    // Живые существа:
    public Array<Sprite> Player; // Игрок.

    // Шрифты:
    public BitmapFont PixelFont;

    // UI:
    public Array<Sprite> UI; // Интерфейс.


    // Функция вызывается автоматически при создании экземпляра этого класса:
    public LoadAssets() {
        // Атлас блоков:
        blocks_atlas = new TextureAtlas(Gdx.files.internal("sprites/atlases/blocks.atlas"));

        // Атлас живых существ:
        alives_atlas = new TextureAtlas(Gdx.files.internal("sprites/atlases/alives.atlas"));

        // Атлас интерфейса:
        ui_atlas = new TextureAtlas(Gdx.files.internal("sprites/atlases/ui.atlas"));

        // Блоки:
        Snow = new Array<>();
        Stone = new Array<>();
        Bonfire = new Array<>();

        // Живые существа:
        Player = new Array<>();

        // Интерфейс:
        UI = new Array<>();
    }

    // Загружает ассеты:
    public void AssetsLoad() {
        LoadBlocks();
        LoadAlives();
        LoadFonts();
        LoadUI();

        resources = new Resources(); // Ресурсы.
    }

    // Удаляет всё что было загружено:
    public void AssetsDispose() {
        blocks_atlas.dispose();
        alives_atlas.dispose();
        ui_atlas.dispose();
        PixelFont.dispose();
        resources.dispose();

        Snow.clear();
        Stone.clear();
        Bonfire.clear();
        Player.clear();
        UI.clear();

        System.gc();
    }

    // Загрузить текстуры блоков:
    void LoadBlocks() {
       Snow.addAll(
               blocks_atlas.createSprite("snow1"),
               blocks_atlas.createSprite("snow2"),
               blocks_atlas.createSprite("snow3")
       );

       Stone.addAll(
               blocks_atlas.createSprite("stone1"),
               blocks_atlas.createSprite("stone2"),
               blocks_atlas.createSprite("stone3")
       );

       Bonfire.addAll(
               blocks_atlas.createSprite("bonfire1"),
               blocks_atlas.createSprite("bonfire2"),
               blocks_atlas.createSprite("bonfire3"),
               blocks_atlas.createSprite("bonfire4"),
               blocks_atlas.createSprite("bonfire5"),
               blocks_atlas.createSprite("bonfire6"),
               blocks_atlas.createSprite("bonfire7"),
               blocks_atlas.createSprite("bonfire8"),
               blocks_atlas.createSprite("bonfire new"),
               blocks_atlas.createSprite("bonfire old")
       );

       NullBlock = blocks_atlas.createSprite("nullblock");
    }

    // Загрузка живых существ:
    void LoadAlives() {
        Player.addAll(
                alives_atlas.createSprite("player-stand"),
                alives_atlas.createSprite("player-run1"),
                alives_atlas.createSprite("player-run2")
        );
    }

    // Загрузить шрифты:
    void LoadFonts() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 14;
        parameters.color = new Color(1f, 1f, 1f, 1f);
        PixelFont = Main.FontUpdateParameters("fonts/pixel.ttf", parameters);
    }

    // Загрузить интерфейс:
    void LoadUI() {
        UI.addAll(
                ui_atlas.createSprite("hotbarcell"),
                ui_atlas.createSprite("hotbartargetcell")
        );
    }
}
