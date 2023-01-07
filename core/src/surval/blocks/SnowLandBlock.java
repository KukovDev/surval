//
// Класс блока Snowland.
//

package surval.blocks;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import surval.core.Main;

public class SnowLandBlock extends Block {
    public Texture texture;

    public SnowLandBlock(int PosX, int PosY) {
        Pos.x = PosX; Pos.y = PosY;
        ID = "snowland";
        texture = Main.AssetsData.Snow.get((int)(Math.random()*3));
    }

    public void Draw(SpriteBatch batch, int BlockSize) {
        batch.draw(texture, Pos.x * BlockSize, Pos.y * BlockSize, BlockSize, BlockSize);
    }
}
