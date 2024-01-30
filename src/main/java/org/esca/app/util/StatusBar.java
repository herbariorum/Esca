
package org.esca.app.util;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public final class StatusBar extends JPanel{

    private final JLabel messagem;
    
    public StatusBar(int width) {
        super();
        super.setPreferredSize(new Dimension(width, 35));
        super.setLayout(new BorderLayout());
        super.setBorder(new EmptyBorder(10, 10, 10, 20));
     
        super.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        super.setBackground(new Color(241, 242, 243));
        this.messagem = new JLabel();
        this.setPreferredSize(new Dimension(width, 30));
        this.messagem.setVerticalAlignment(SwingConstants.CENTER);
        this.messagem.setBorder(new EmptyBorder(5, 20, 5, 10));
        add(this.messagem, BorderLayout.LINE_START);
        
        
    }
    
    public void setMessagem(String msg){
        this.messagem.setText(msg);
    }
    
}
