// Класс интерфейса.

package surval.core;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;

public class UI {
    // Отрисовать горячую панель инвентаря:
    public static void DrawHotBar(SpriteBatch uibatch, OrthographicCamera uicamera) {
        float ScaleHotBar = 2f;
        int Cells = 6;
        uibatch.begin();
        for(int x=0;x<Cells;x++) {
            uibatch.draw(Main.AssetsData.UI.get(0),
                    uicamera.position.x+Main.AssetsData.UI.get(0).getWidth()*ScaleHotBar*x-
                            Main.AssetsData.UI.get(0).getWidth()*ScaleHotBar*Cells/2,
                    uicamera.position.y-uicamera.viewportHeight/2,
                    Main.AssetsData.UI.get(0).getWidth()*ScaleHotBar+1f,
                    Main.AssetsData.UI.get(0).getHeight()*ScaleHotBar+1f);
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
