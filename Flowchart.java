import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Flowchart extends JPanel {

    private List<FlowchartComponent> components;
    private int panelWidth = 800;
    private int panelHeight = 600;

    public Flowchart() {
        this.components = new ArrayList<>();
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    // Method to generate the flowchart based on the analysis result
    public void generateFlowchart(String analysisResult) {
        components.clear();
        String[] lines = analysisResult.split("\n");

        for (String line : lines) {
            line = line.trim();

            if (line.contains("If/Else")) {
                components.add(new FlowchartComponent("Condition", "Decision"));
            } else if (line.contains("Loops")) {
                components.add(new FlowchartComponent("Loop", "Loop"));
            } else if (line.contains("Try/Catch")) {
                components.add(new FlowchartComponent("Exception Handling", "Try/Catch"));
            } else if (line.contains("Input")) {
                components.add(new FlowchartComponent("Input", "Input"));
            } else if (line.contains("Output")) {
                components.add(new FlowchartComponent("Output", "Output"));
            } else if (line.contains("Comparisons")) {
                components.add(new FlowchartComponent("Comparison", "Decision"));
            } else if (line.contains("Arithmetic")) {
                components.add(new FlowchartComponent("Operation", "Process"));
            }
        }

        // Update panel size to account for the number of components
        panelHeight = components.size() * 120 + 200;
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        // Trigger repaint to update the visual flowchart
        revalidate();
        repaint();
    }

    // Method to export the flowchart as an image
    public void exportFlowchart(String filePath) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        this.paint(g2d);  // Paint the current flowchart onto the image
        g2d.dispose();

        try {
            ImageIO.write(image, "png", new File(filePath));
            JOptionPane.showMessageDialog(this, "Flowchart exported successfully!", 
                                          "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting flowchart: " + e.getMessage(), 
                                          "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 200;  // Base X coordinate (keeps everything vertically aligned)
        int y = 50;  // Initial Y coordinate
        int width = 160;
        int height = 50;
        int yOffset = 100;  // Offset between components

        // Draw start oval
        drawOval(g2d, x, y, width, height, "Start");
        y += yOffset;

        // Draw components
        for (FlowchartComponent component : components) {
            String label = component.getLabel();
            String type = component.getType();

            // Draw different shapes for components
            if ("Decision".equals(type)) {
                drawDiamond(g2d, x, y, width, height, label);
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "True");
                y += yOffset;
            } else if ("Loop".equals(type)) {
                drawRectangle(g2d, x, y, width, height, label);
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "");
                y += yOffset;
            } else if ("Try/Catch".equals(type)) {
                drawRectangle(g2d, x, y, width, height, label);
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "");
                y += yOffset;
            } else if ("Input".equals(type)) {
                drawParallelogram(g2d, x, y, width, height, label + " (var)");
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "");
                y += yOffset;
            } else if ("Output".equals(type)) {
                drawParallelogram(g2d, x, y, width, height, label + " (var)");
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "");
                y += yOffset;
            } else if ("Comparison".equals(type)) {
                drawDiamond(g2d, x, y, width, height, label);
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "True");
                y += yOffset;
            } else if ("Operation".equals(type)) {
                drawRectangle(g2d, x, y, width, height, label);
                drawArrow(g2d, x + width / 2, y + height, x + width / 2, y + yOffset, "");
                y += yOffset;
            }
        }

        // Draw stop oval
        drawOval(g2d, x, y, width, height, "Stop");
    }

    // Helper method to draw ovals for start/stop
    private void drawOval(Graphics2D g2d, int x, int y, int width, int height, String text) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x, y, width - 40, height);
        g2d.setColor(Color.BLACK);
        drawCenteredString(g2d, text, x, y, width - 40, height);
    }

    // Helper method to draw diamonds for decisions
    private void drawDiamond(Graphics2D g2d, int x, int y, int width, int height, String text) {
        int[] xPoints = {x + width / 2, x + width, x + width / 2, x};
        int[] yPoints = {y, y + height / 2, y + height, y + height / 2};
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillPolygon(xPoints, yPoints, 4);
        g2d.setColor(Color.BLACK);
        drawCenteredString(g2d, text, x, y, width, height);
    }

    // Helper method to draw rectangles for loops and operations
    private void drawRectangle(Graphics2D g2d, int x, int y, int width, int height, String text) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.BLACK);
        drawCenteredString(g2d, text, x, y, width, height);
    }

    // Helper method to draw parallelograms for inputs/outputs
    private void drawParallelogram(Graphics2D g2d, int x, int y, int width, int height, String text) {
        int[] xPoints = {x, x + 20, x + width, x + width - 20};
        int[] yPoints = {y, y + height, y + height, y};
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillPolygon(xPoints, yPoints, 4);
        g2d.setColor(Color.BLACK);
        drawCenteredString(g2d, text, x, y, width, height);
    }

    // Helper method to draw arrows
    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2, String label) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(x1, y1, x2, y2);
        if (!label.isEmpty()) {
            g2d.drawString(label, (x1 + x2) / 2, (y1 + y2) / 2 - 5);
        }
        g2d.setStroke(new BasicStroke(1));
    }

    // Helper method to draw centered strings
    private void drawCenteredString(Graphics2D g2d, String text, int x, int y, int width, int height) {
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        int xPos = x + (width - metrics.stringWidth(text)) / 2;
        int yPos = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(text, xPos, yPos);
    }
}
