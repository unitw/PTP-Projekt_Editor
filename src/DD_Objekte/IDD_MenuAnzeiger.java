/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_Objekte;

import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public interface IDD_MenuAnzeiger {

    public boolean isHasfocus();

    public void setHasfocus(boolean hasfocus);

    public void showMenu(JPanel panel);

}
