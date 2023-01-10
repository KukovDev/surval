//
// Класс блока Stoneland.
//

package surval.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import surval.core.*;

public class StonelandBlock extends Block {
    public Sprite sprite;

    public StonelandBlock(int PosX, int PosY) {
        Pos.x = PosX; Pos.y = PosY;
        ID = "stoneland";
        Type = "land";
        sprite = Main.AssetsData.Stone.get((int)(Math.random()*3));
    }

    public void Draw(SpriteBatch batch, int BlockSize) {
        batch.draw(sprite, Pos.x*BlockSize, Pos.y*BlockSize, BlockSize+1, BlockSize+1);
    }
}
