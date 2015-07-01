/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Objekte;

import java.awt.image.BufferedImage;

/**
 *
 * @author 3welge
 */
public class DD_Schatztruhe {

    int xpos;
    int ypos;
    BufferedImage BackgroundImg;

    public BufferedImage getBackgroundImg() {
        return BackgroundImg;
    }

    public void setBackgroundImg(BufferedImage BackgroundImg) {
        this.BackgroundImg = BackgroundImg;
    }

    public DD_Schatztruhe(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

}
