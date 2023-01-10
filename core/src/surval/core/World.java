//
// Этот код создаёт карту.
//

package surval.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import surval.blocks.*;

import java.util.Objects;

public class World {
    public Block[][] BlockList; // Список блоков.
    public int Width;           // Ширина карты.
    public int Height;          // Высота карты.
    public byte BlockSize = 64; // Размер блока.

    public World(int Width, int Height) {
        this.Width = Width;
        this.Height = Height;
        BlockList = new Block[this.Width][this.Height];
    }

    // Функция генерации мира:
    public void Generate() {
        for(int x=0;x<Width;x++) {
            for(int y=0;y<Height;y++) {
                BlockList[x][y] = new SnowLandBlock(x, y); // Добавить блок.
            }
        }
    }

    // TODO оптимизировать! вывести в отдельный поток.
    // Функция отрисовки карты:
    public void Draw(SpriteBatch batch, OrthographicCamera camera, float DeltaTime) {
        for(int x=0;x<Width;x++) {
            for(int y=0;y<Height;y++) {
                // Проверка на то, видит ли камера, блок:
                Rectangle BlockRect = new Rectangle(x*BlockSize, y*BlockSize, BlockSize, BlockSize); // Rect блока.
                Rectangle CameraRect = new Rectangle( // Rect камеры с учётом масштабирования камеры:
                        camera.position.x - (camera.viewportWidth * camera.zoom) / 2,
                        camera.position.y - (camera.viewportHeight * camera.zoom) / 2,
                        camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);

                if(BlockRect.overlaps(CameraRect)) {
                    BlockList[x][y].Update(DeltaTime); // TODO убрать это отсюда!
                    BlockList[x][y].Draw(batch, BlockSize);
                }
            }
        }
    }

    // Получение позиции наведения на карте:
    public Vector2 GetHoverPos(OrthographicCamera camera) {
        // Суть такова:
        // Вы должны сначала получить позицию карты на экране,
        // добавить позицию мыши/пальца умноженную на зум,
        // и всё это вместе взятое поделить целочисленно на размер ячейки (блока 1x1).

        Vector2 hoverpos = new Vector2();
        int MapPosX   = (int) -(camera.position.x - (camera.viewportWidth  * camera.zoom) / 2); // Позиция карты на экране по ширине.
        int MapPosY   = (int) -(camera.position.y - (camera.viewportHeight * camera.zoom) / 2); // Позиция карты на экране по высоте.
        int TouchPosX = (int)  (Gdx.input.getX() * camera.zoom);                                // Позиция мыши/пальца по ширине.
        int TouchPosY = (int)  (-(Gdx.input.getY() - camera.viewportHeight) * camera.zoom);     // Позиция мыши/пальца по высоте.

        hoverpos.x = (float)-(MapPosX-TouchPosX)/BlockSize; // Получить наведение на карте по ширине.
        hoverpos.y = (float)-(MapPosY-TouchPosY)/BlockSize; // Получить наведение на карте по высоте.

        // Если наведение меньше 0:
        if(hoverpos.x < 0) hoverpos.x = hoverpos.x-1;
        if(hoverpos.y < 0) hoverpos.y = hoverpos.y-1;

        // Округлить:
        hoverpos.x = (int)hoverpos.x;
        hoverpos.y = (int)hoverpos.y;

        return hoverpos;
    }

    // Получение позиции существа на карте:
    public Vector2 GetAlivePos(Vector2 AlivePos) {
        Vector2 alivepos = new Vector2();
        alivepos.x = AlivePos.x/BlockSize;
        alivepos.y = AlivePos.y/BlockSize;

        // Если наведение меньше 0:
        if(alivepos.x < 0) alivepos.x = alivepos.x-1;
        if(alivepos.y < 0) alivepos.y = alivepos.y-1;

        // Округлить:
        alivepos.x = (int)alivepos.x;
        alivepos.y = (int)alivepos.y;
        return alivepos;
    }

    // Установить блок:
    public void SetBlock(Block block, Vector2 hoverpos) {
        while(BlockList[(int)hoverpos.x][(int)hoverpos.y].BackgroundBlock != null) {
            BreakBlock(hoverpos);
        }

        if(!Objects.equals(BlockList[(int)hoverpos.x][(int)hoverpos.y].ID, block.ID)) {
            block.BackgroundBlock = BlockList[(int) hoverpos.x][(int) hoverpos.y];
            BlockList[(int)hoverpos.x][(int)hoverpos.y] = block;
        }
    }

    // Удалить блок:
    public void BreakBlock(Vector2 hoverpos) {
        if(BlockList[(int)hoverpos.x][(int)hoverpos.y].BackgroundBlock != null) {
            Block bgblock = BlockList[(int)hoverpos.x][(int)hoverpos.y].BackgroundBlock;
            BlockList[(int)hoverpos.x][(int)hoverpos.y] = null;
            BlockList[(int)hoverpos.x][(int)hoverpos.y] = bgblock;
        }
    }
}
