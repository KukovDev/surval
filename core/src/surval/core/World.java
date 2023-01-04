//
// Этот код создаёт карту из чанков.
//

package surval.core;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import surval.blocks.*;
import java.util.*;

public class World {
    public List<List<Block>> tilemap;
    public int Width;           // Ширина карты.
    public int Height;          // Высота карты.
    public byte BlockSize = 64; // Размер блока.

    public World(int Width, int Height) {
        tilemap = new ArrayList<>();
        this.Width = Width;
        this.Height = Height;
    }

    // Функция генерации мира:
    public void Generate() {
        byte blocktype = 0;

        for(int x=0;x<Width;x++) {
            tilemap.add(new ArrayList<Block>());
            for(int y=0;y<Height;y++) {
                tilemap.get(x).add(new SnowlandBlock(x, y));
                blocktype++; if(blocktype > 2) blocktype = 0;
            }
        }
    }

    // Обновление блоков:
    void BlockUpdate(int x, int y, SpriteBatch batch, OrthographicCamera camera) {
        int BlockPosX = x*BlockSize;
        int BlockPosY = y*BlockSize;
        Rectangle BlockRect = new Rectangle(BlockPosX, BlockPosY, BlockSize, BlockSize);
        Rectangle CameraRect = new Rectangle(
                camera.position.x - camera.viewportWidth / 2 - BlockSize,
                camera.position.y - camera.viewportHeight / 2 - BlockSize,
                camera.viewportWidth + (BlockSize * 2), camera.viewportHeight + (BlockSize * 2));

        if(BlockRect.overlaps(CameraRect)) {
            tilemap.get(x).get(y).Draw(batch, BlockPosX, BlockPosY, BlockSize);
        }
    }

    // Функция отрисовки карты:
    public void Draw(SpriteBatch batch, OrthographicCamera camera) {
        for(int x=0;x<tilemap.size();x++) {
            for(int y=0;y<tilemap.get(x).size();y++) {
                BlockUpdate(x, y, batch, camera);
            }
        }
    }
}
