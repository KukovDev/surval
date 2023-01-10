// Класс интерфейса.

package surval.core;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;

public class UI {
    public static final int HotBarCells = 8;                          // Кол-во ячеек горячей панели.
    public static String[] HotBarCellsList = new String[HotBarCells]; // Ресурсы в горячей панели.
    public static int HotBarTargetCell;                               // Выделение ячейки горячей панели.

    // Отрисовать горячую панель инвентаря:
    public static void DrawHotBar(SpriteBatch uibatch, OrthographicCamera uicamera) {
        uibatch.begin();
        for(int x=0;x<HotBarCells;x++) {
            float PosX = uicamera.position.x+48f*x-48f*HotBarCells/2;
            float PosY = uicamera.position.y-uicamera.viewportHeight/2;

            // Выделение ячейки:
            if(HotBarTargetCell > HotBarCells-1) HotBarTargetCell = 0;
            if(HotBarTargetCell < 0) HotBarTargetCell = HotBarCells-1;
            if(x == HotBarTargetCell) uibatch.draw(Main.AssetsData.UI.get(1), PosX, PosY+48+4f, 48f, 12f);

            uibatch.draw(Main.AssetsData.UI.get(0), PosX, PosY, 48f, 48f); // Отрисовать ячейку.

            try {
                uibatch.draw(Main.AssetsData.resources.Resources.get(HotBarCellsList[x]),
                        PosX+8f, PosY+8f, 32f, 32f);
            } catch(Exception ignored) { }
        }
        uibatch.end();
    }

    // Отрисовать панель разработчика:
    public static void DrawDevPanel(ShapeRenderer shapeRenderer, SpriteBatch uibatch, OrthographicCamera uicamera,
                                    float DeltaTime, Vector2 PlayerPos, Vector2 HoverPos, Vector2 WorldSize) {
        Main.DrawRectFilled(
                uicamera.position.x-((uicamera.viewportWidth/2)*uicamera.zoom),
                uicamera.position.y-((uicamera.viewportHeight/2)*uicamera.zoom),
                256*uicamera.zoom,
                uicamera.viewportHeight*uicamera.zoom,
                new Color(0, 0, 0, .85f), shapeRenderer, uicamera);

        uibatch.begin();
        float CameraPosX = uicamera.position.x-(uicamera.viewportWidth/2);
        float CameraPosY = uicamera.position.y+(uicamera.viewportHeight/2);

        Main.AssetsData.PixelFont.draw(uibatch, "<Dev-Panel>",
                CameraPosX+75, CameraPosY-4);

        Main.AssetsData.PixelFont.draw(uibatch, "FPS: " + Main.GetFPS(),
                CameraPosX+4, CameraPosY-4*8);

        Main.AssetsData.PixelFont.draw(uibatch, "DeltaTime: " + DeltaTime,
                CameraPosX+4, CameraPosY-4*12);

        Main.AssetsData.PixelFont.draw(uibatch, "Player Pos: x=" + (int)PlayerPos.x + ", y=" + (int)PlayerPos.y,
                CameraPosX+4, CameraPosY-4*16);

        Main.AssetsData.PixelFont.draw(uibatch, "Hover Pos: x=" + (int)HoverPos.x + ", y=" + (int)HoverPos.y,
                CameraPosX+4, CameraPosY-4*20);

        Main.AssetsData.PixelFont.draw(uibatch, "World Size: W=" + (int)WorldSize.x + ", H=" + (int)WorldSize.y,
                CameraPosX+4, CameraPosY-4*24);
        uibatch.end();
    }
}
