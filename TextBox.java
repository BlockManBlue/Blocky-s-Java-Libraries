import java.awt.*;
import java.util.ArrayList;
public class TextBox {

    String text;
    int alignment;
    static final int LEFT = 0, CENTER = 1, RIGHT = 2;
    static final int NOMAXWIDTH = -1;
    private Font font;
    boolean debug = false;

    public TextBox(String text, int alignment, Font font){
        this.text = text;
        this.alignment = alignment;
        this.font = font;
    }

    public void draw(Graphics g, int x, int y){
        g.setFont(font);
        if(alignment == LEFT){
            g.drawString(text, x, y + font.getSize() / 2);
        }
        if(alignment == CENTER){
            int textWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text, x - textWidth / 2, y + font.getSize() / 2);
        }
        if(alignment == RIGHT){
            int textWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text, x - textWidth, y + font.getSize() / 2);
        }

        if(debug){
            g.setColor(Color.red);
            if(alignment == LEFT){
                g.drawRect(x, y - font.getSize() / 2, g.getFontMetrics().stringWidth(text), font.getSize());
            }
            if(alignment == CENTER){
                g.drawRect(x - g.getFontMetrics().stringWidth(text) / 2, y - font.getSize() / 2, g.getFontMetrics().stringWidth(text), font.getSize());
            }
            if(alignment == RIGHT){
                g.drawRect(x - g.getFontMetrics().stringWidth(text), y - font.getSize() / 2, g.getFontMetrics().stringWidth(text), font.getSize());
            }
        }
    }

    public void draw(Graphics g, int x, int y, int maxWidth){
        g.setFont(font);
        ArrayList<String> lines = new ArrayList<String>();
        String newString = "";
        for(int i = 0; i < text.length(); i++){
            newString += text.charAt(i);
            if((g.getFontMetrics().stringWidth(newString) >= maxWidth && maxWidth != NOMAXWIDTH) || text.charAt(i) == '\n' || i == text.length() - 1){
                // long enough, next line
                lines.add(newString);
                newString = "";
            }
        }

        for(int i = 0; i < lines.size(); i++){
            if(alignment == LEFT){
                g.drawString(lines.get(i), x, y + font.getSize() / 2 + font.getSize() * i);
            }
            if(alignment == CENTER){
                int textWidth = g.getFontMetrics().stringWidth(lines.get(i));
                g.drawString(lines.get(i), x - textWidth / 2, y + font.getSize() / 2 + font.getSize() * i);
            }
            if(alignment == RIGHT){
                int textWidth = g.getFontMetrics().stringWidth(lines.get(i));
                g.drawString(lines.get(i), x - textWidth, y + font.getSize() / 2 + font.getSize() * i);
            }
        }

        if(debug){
            g.setColor(Color.red);
            if(alignment == LEFT){
                g.drawRect(x, y - font.getSize() / 2, maxWidth, font.getSize());
            }
            if(alignment == CENTER){
                g.drawRect(x - maxWidth / 2, y - font.getSize() / 2, maxWidth, font.getSize());
            }
            if(alignment == RIGHT){
                g.drawRect(x - maxWidth, y - font.getSize() / 2, g.getFontMetrics().stringWidth(text), font.getSize());
            }
        }
    }

    public static void draw(String text, Graphics g, int x, int y, int alignment){
        TextBox textBox = new TextBox(text, alignment, g.getFont());
        textBox.draw(g, x, y);
    }
    public static void draw(String text, Graphics g, int x, int y, int alignment, int maxWidth){
        TextBox textBox = new TextBox(text, alignment, g.getFont());
        textBox.draw(g, x, y, maxWidth);
    }
}
