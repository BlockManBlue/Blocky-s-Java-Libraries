import javax.swing.*;
import java.awt.*;
public class TextDialog {
    String title = "???";
    String message = "You shouldn't see me.";
    int distance = 0;
    ImageIcon textBox = null, profPic = null;
    String[] sounds = null;
    Font titleFont, messageFont;
    Color titleColor = Color.black, messageColor = Color.black;

    public TextDialog(ImageIcon box, Font title, Font message){
        textBox = box;
        titleFont = title;
        messageFont = message;
    }

    public void update(long elapsedTime){
        distance++;
        if(distance >= message.length()) distance = message.length() - 1;
        if(sounds != null && distance < message.length()){
            int sel = BTools.randInt(0, sounds.length);
            Audio.play(sounds[sel]);
        }
    }

    public void render(Graphics g, JPanel panel){
        textBox.paintIcon(panel, g, 0, panel.getHeight() - textBox.getIconHeight());
        g.setFont(titleFont);
        g.setColor(titleColor);
        TextBox.draw(title, g, 5, panel.getHeight() - textBox.getIconHeight() + (titleFont.getSize() / 2), TextBox.LEFT);
        g.setFont(messageFont);
        g.setColor(messageColor);
        int max = panel.getWidth() - 10;
        if(profPic != null) max -= profPic.getIconWidth();
        String text = "";
        for(int i = 0; i <= distance; i++) text += message.charAt(i);
        TextBox.draw(text, g, 5, panel.getHeight() - textBox.getIconHeight() + (titleFont.getSize() / 2) + (int)(messageFont.getSize() * 1.5), TextBox.LEFT, max);
        if(profPic != null){
            profPic.paintIcon(panel, g, panel.getWidth() - profPic.getIconWidth() - 5, panel.getHeight() - profPic.getIconHeight() - 5);
        }
    }

    public void setDialog(String title, String message, ImageIcon prof){
        distance = 0;
        this.title = title;
        this.message = message;
    }
}
