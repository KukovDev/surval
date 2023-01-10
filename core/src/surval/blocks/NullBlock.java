//
// Класс блока Null.
//

package surval.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import surval.core.*;

public class NullBlock extends Block {
    public NullBlock(int PosX, int PosY) {
        Pos.x = PosX; Pos.y = PosY;
        ID = "nullblock";
        Type = "block";
    }

    public void Draw(SpriteBatch batch, int BlockSize) {
        batch.draw(Main.AssetsData.NullBlock, Pos.x*BlockSize, Pos.y*BlockSize, BlockSize+1, BlockSize+1);
    }
}
