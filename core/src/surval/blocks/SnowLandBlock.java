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
        this.PosX = PosX;
        this.PosY = PosY;
        ID = "snowland";
        texture = Main.AssetsData.snow.get((int)(Math.random()*3));
    }

    public void Draw(SpriteBatch batch, int BlockPosX, int BlockPosY, int BlockSize) {
        batch.draw(texture, BlockPosX, BlockPosY, BlockSize, BlockSize);
    }
}
