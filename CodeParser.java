package SoftwareEngineeringPro1;

public class CodeParser {

    private String code;

    public CodeParser(String code) {
        this.code = code;
    }

    public String analyze() {
        String[] lines = code.split("\n");
        int ifCount = 0, loopCount = 0, tryCatchCount = 0;
        int inputCount = 0, outputCount = 0, comparisonCount = 0, operationCount = 0;
        int nestedLoopLevel = 0, maxNestedLoopLevel = 0;
        int nestedConditionLevel = 0, maxNestedConditionLevel = 0;

        for (String line : lines) {
            line = line.trim();

            // Detect if/else conditions
            if (line.startsWith("if") || line.contains("else")) {
                ifCount++;
                nestedConditionLevel++;
                maxNestedConditionLevel = Math.max(maxNestedConditionLevel, nestedConditionLevel);
                if (line.contains(">") || line.contains("<") || line.contains("==") ||
                    line.contains(">=") || line.contains("<=")) {
                    comparisonCount++;
                }
            }
            // Detect loops (for/while)
            else if (line.startsWith("for") || line.startsWith("while")) {
                loopCount++;
                nestedLoopLevel++;
                maxNestedLoopLevel = Math.max(maxNestedLoopLevel, nestedLoopLevel);
            }
            // Detect try-catch blocks
            else if (line.contains("try") || line.contains("catch")) {
                tryCatchCount++;
            }
            // Detect input handling (e.g., Scanner)
            else if (line.contains("Scanner") || line.contains("args") || line.contains(".read")) {
                inputCount++;
            }
            // Detect output handling (e.g., System.out.println)
            else if (line.contains("System.out.println") || line.contains(".write")) {
                outputCount++;
            }
            // Detect arithmetic operations
            else if (line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/")) {
                operationCount++;
            }

            // Handle the end of blocks
            if (line.equals("}")) {
                nestedLoopLevel = Math.max(0, nestedLoopLevel - 1);
                nestedConditionLevel = Math.max(0, nestedConditionLevel - 1);
            }
        }

        // Build the analysis result string
        StringBuilder result = new StringBuilder();
        result.append("Detailed Control Structures Found:\n");
        result.append("- If/Else Statements: ").append(ifCount).append("\n");
        result.append("- Loops (For/While): ").append(loopCount).append("\n");
        result.append("- Maximum Nested Loop Level: ").append(maxNestedLoopLevel).append("\n");
        result.append("- Maximum Nested Condition Level: ").append(maxNestedConditionLevel).append("\n");
        result.append("- Try/Catch Blocks: ").append(tryCatchCount).append("\n");
        result.append("- Input Statements: ").append(inputCount).append("\n");
        result.append("- Output Statements: ").append(outputCount).append("\n");
        result.append("- Comparisons: ").append(comparisonCount).append("\n");
        result.append("- Arithmetic Operations: ").append(operationCount).append("\n");

        return result.toString();
    }
}
