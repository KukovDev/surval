//
// Класс блока Snowland.
//

package surval.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import surval.core.*;

public class SnowLandBlock extends Block {
    public Sprite sprite;

    public SnowLandBlock(int PosX, int PosY) {
        Pos.x = PosX; Pos.y = PosY;
        ID = "snowland";
        sprite = Main.AssetsData.Snow.get((int)(Math.random()*3));
    }

    public void Draw(SpriteBatch batch, int BlockSize) {
        batch.draw(sprite, Pos.x*BlockSize, Pos.y*BlockSize, BlockSize+1, BlockSize+1);
    }
}
