//
// Этот код создаёт карту.
//

package surval.core;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import surval.blocks.*;
import java.util.*;

public class World {
    public List<List<Block>> BlockList; // Список блоков.
    public int Width;                   // Ширина карты.
    public int Height;                  // Высота карты.
    public byte BlockSize = 64;         // Размер блока.

    public World(int Width, int Height) {
        BlockList = new ArrayList<>();
        this.Width = Width;
        this.Height = Height;
    }

    // Функция генерации мира:
    public void Generate() {
        for(int x=0;x<Width;x++) {
            BlockList.add(new ArrayList<Block>());
            for(int y=0;y<Height;y++) {
                BlockList.get(x).add(new SnowLandBlock(x, y)); // Добавить блок.
            }
        }
    }

    // Установить список блоков:
    public void SetBlockList(List<List<Block>> BlockList) {
        this.BlockList = BlockList;
    }

    // Обновление блоков:
    void BlockUpdate(int x, int y, SpriteBatch batch, OrthographicCamera camera) {
        int BlockPosX = x*BlockSize;
        int BlockPosY = y*BlockSize;

        // TODO Это можно оптимизировать. Сделаю когда нибудь не сейчас :)
        // Проверка на то, видит ли камера, блок:
        Rectangle BlockRect = new Rectangle(BlockPosX, BlockPosY, BlockSize, BlockSize); // Rect блока.
        Rectangle CameraRect = new Rectangle( // Rect камеры с учётом масштабирования камеры:
                camera.position.x - (camera.viewportWidth * camera.zoom) / 2,
                camera.position.y - (camera.viewportHeight * camera.zoom) / 2,
                camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);

        if(BlockRect.overlaps(CameraRect)) {
            BlockList.get(x).get(y).Draw(batch, BlockPosX, BlockPosY, BlockSize);
        }
    }

    // Функция отрисовки карты:
    public void Draw(SpriteBatch batch, OrthographicCamera camera) {
        for(int x=0;x<BlockList.size();x++) {
            for(int y=0;y<BlockList.get(x).size();y++) {
                BlockUpdate(x, y, batch, camera);
            }
        }
    }
}
