import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Flowchart extends JPanel {

    private List<String> components;

    public Flowchart() {
        this.components = new ArrayList<>();
    }

    public void generateFlowchart(String analysisResult) {
        components.clear();
        String[] lines = analysisResult.split("\n");

        
        for (String line : lines) {
            if (line.contains("If/Else")) {
                components.add("Conditional");
            } else if (line.contains("Loops")) {
                components.add("Loop");
            } else if (line.contains("Try/Catch")) {
                components.add("Exception Handling");
            } else if (line.contains("Input")) {
                components.add("Input Handling");
            }
        }

        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        
        int x = 50;
        int y = 50;
        int boxWidth = 120;
        int boxHeight = 40;
        int yOffset = 70;

        
        for (String component : components) {
            g2d.drawRect(x, y, boxWidth, boxHeight);
            g2d.drawString(component, x + 10, y + 25);

            
            y += yOffset;
            g2d.drawLine(x + boxWidth / 2, y - yOffset + boxHeight, x + boxWidth / 2, y - 10);
            g2d.drawLine(x + boxWidth / 2 - 5, y - 15, x + boxWidth / 2, y - 10);
            g2d.drawLine(x + boxWidth / 2 + 5, y - 15, x + boxWidth / 2, y - 10);
        }
    }

    
    public void exportFlowchart(String filePath) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d); 
        g2d.dispose();

        try {
            File file = new File(filePath);
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(this, "Flowchart exported to " + filePath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting flowchart: " + e.getMessage());
        }
    }
}
