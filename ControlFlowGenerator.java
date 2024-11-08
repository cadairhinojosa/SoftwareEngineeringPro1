package SoftwareEngineeringPro1;

import java.util.ArrayList;
import java.util.List;

public class ControlFlowGenerator {
    private String code;
    private List<String> analysisResults;
    private Flowchart flowchartPanel;

    public ControlFlowGenerator(Flowchart flowchart) {
        this.code = "";
        this.analysisResults = new ArrayList<>();
        this.flowchartPanel = flowchart;
    }

    public void inputCode(String code) {
        this.code = code;
    }

    public String analyzeCode() {
        int ifCount = 0, loopCount = 0, tryCatchCount = 0;
        int inputCount = 0, outputCount = 0, comparisonCount = 0, operationCount = 0;
        flowchartPanel.clearComponents();
        String[] lines = code.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("if") || line.startsWith("else")) {
                ifCount++;
                flowchartPanel.addComponent("If/Else");
            }
            else if (line.startsWith("for") || line.startsWith("while")) {
                loopCount++;
                flowchartPanel.addComponent("Loops");
            }
            else if (line.contains("try") || line.contains("catch")) {
                tryCatchCount++;
                flowchartPanel.addComponent("Try/Catch");
            }
            else if (line.contains("Scanner") || line.contains("args")) {
                inputCount++;
                //flowchartPanel.addComponent("If/Else");
            }
            else if (line.contains("System.out.println")) {
                outputCount++;
                flowchartPanel.addComponent("Output");
            }
            else if (line.contains(">") || line.contains("<") || line.contains("==") || line.contains(">=") || line.contains("<=")) {
                comparisonCount++;
                flowchartPanel.addComponent("Comparisons");

            }
            else if (line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/")) {
                operationCount++;
                flowchartPanel.addComponent("Arithmetic");
            }
        }

        // Build the analysis result string
        StringBuilder result = new StringBuilder();
        result.append("If/Else Statements: ").append(ifCount).append("\n");
        result.append("Loops (For/While): ").append(loopCount).append("\n");
        result.append("Try/Catch Blocks: ").append(tryCatchCount).append("\n");
        result.append("Input Statements: ").append(inputCount).append("\n");
        result.append("Output Statements: ").append(outputCount).append("\n");
        result.append("Comparisons: ").append(comparisonCount).append("\n");
        result.append("Arithmetic Operations: ").append(operationCount).append("\n");

        return result.toString();
    }
}
