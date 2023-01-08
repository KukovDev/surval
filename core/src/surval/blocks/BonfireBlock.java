//
// Класс блока костра.
//

package surval.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import surval.core.*;

public class BonfireBlock extends Block {
    public float AnimStep;
    public float AnimSpeed;  // Скорость анимации.
    public float AnimFrames; // Кол-во спрайтов анимации.

    public BonfireBlock(int PosX, int PosY) {
        Pos.x = PosX; Pos.y = PosY;
        ID = "bonfire";

        AnimSpeed = 0.15f;
        AnimFrames = 8;
    }

    public void Update(float DeltaTime) {
        AnimStep += AnimSpeed * DeltaTime;
        if(AnimStep >= AnimFrames) AnimStep = 0f;
    }

    public void Draw(SpriteBatch batch, int BlockSize) {
        try { BackgroundBlock.Draw(batch, BlockSize); }
        catch (Exception e) {
            batch.draw(Main.AssetsData.NullBlock, Pos.x*BlockSize, Pos.y*BlockSize, BlockSize+1, BlockSize+1);
        }
        batch.draw(Main.AssetsData.Bonfire.get((int)AnimStep), Pos.x*BlockSize, Pos.y*BlockSize,BlockSize+1, BlockSize+1);
    }
}
