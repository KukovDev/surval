//
// Класс ресурсов.
// В частности нужен для визуализации их существования.
//

package surval.core;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;

public class Resources {
    public ArrayMap<String, Sprite> Resources;
    public final String Bonfire   = "bonfire-res";
    public final String Snowland  = "snowland-res";
    public final String Stoneland = "stoneland-res";
    public final String NullRes   = "nullRes-res";

    public Resources() {
        Resources = new ArrayMap<>();

        Resources.put(Bonfire, Main.AssetsData.Bonfire.get(0));
        Resources.put(Snowland, Main.AssetsData.Snow.get(0));
        Resources.put(Stoneland, Main.AssetsData.Stone.get(0));
        Resources.put(NullRes, Main.AssetsData.NullBlock);
    }

    public void dispose() {
        Resources.clear();
    }
}
