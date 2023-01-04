//
// Класс блока Null.
//

package surval.blocks;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import surval.core.Main;

public class NullBlock extends Block {
    public Texture texture;

    public NullBlock(int PosX, int PosY) {
        this.PosX = PosX;
        this.PosY = PosY;
        ID = "nullblock";
        texture = Main.AssetsData.nullblock;
    }

    public void Draw(SpriteBatch batch, int BlockPosX, int BlockPosY, int BlockSize) {
        batch.draw(texture, BlockPosX, BlockPosY, BlockSize, BlockSize);
    }
}
