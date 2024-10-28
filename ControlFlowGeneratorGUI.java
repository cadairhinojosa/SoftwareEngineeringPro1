import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; 

public class ControlFlowGeneratorGUI extends JFrame {

    private JTextArea codeInputArea;
    private JTextArea outputArea;
    private ControlFlowGenerator generator;
    private Flowchart flowchartPanel;

    public ControlFlowGeneratorGUI() {
        super("Java Control Flow Generator");
        this.generator = new ControlFlowGenerator();
        this.flowchartPanel = new Flowchart();
        setupGUI();
    }

    private void setupGUI() {
        setLayout(new BorderLayout());

        
        codeInputArea = new JTextArea();
        codeInputArea.setBorder(BorderFactory.createTitledBorder("Enter Java Code Here"));
        JScrollPane inputScroll = new JScrollPane(codeInputArea);

        
        outputArea = new JTextArea();
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        outputArea.setEditable(false);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputScroll, outputScroll);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);

        
        flowchartPanel.setPreferredSize(new Dimension(400, 400));
        flowchartPanel.setBorder(BorderFactory.createTitledBorder("Flowchart"));
        add(flowchartPanel, BorderLayout.EAST);

        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        
        JButton analyzeButton = new JButton("Analyze Code");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeInputArea.getText();
                if (!code.isEmpty()) {
                    generator.inputCode(code);
                    String analysisResult = generator.analyzeCode(); 
                    outputArea.setText(analysisResult); 
                } else {
                    outputArea.setText("Please enter Java code.");
                }
            }
        });

        
        JButton generateButton = new JButton("Generate Flowchart");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String analysisResult = generator.analyzeCode();
                flowchartPanel.generateFlowchart(analysisResult); 
                outputArea.append("\n[Flowchart updated]");
            }
        });

        
        JButton exportButton = new JButton("Export Flowchart");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Flowchart as PNG");
                int userSelection = fileChooser.showSaveDialog(ControlFlowGeneratorGUI.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    flowchartPanel.exportFlowchart(fileToSave.getAbsolutePath() + ".png");
                }
            }
        });

        buttonPanel.add(analyzeButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(exportButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
