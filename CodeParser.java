public class CodeParser {

    private String code;

    public CodeParser(String code) {
        this.code = code;
    }

    public String analyze() {
        String[] lines = code.split("\n");
        int ifCount = 0, loopCount = 0, tryCatchCount = 0, inputCount = 0;

        for (String line : lines) {
            line = line.trim();

         
            if (line.startsWith("if") || line.contains("else")) {
                ifCount++;
            }
          
            else if (line.startsWith("for") || line.startsWith("while")) {
                loopCount++;
            }
           
            else if (line.contains("try") || line.contains("catch")) {
                tryCatchCount++;
            }
           
            else if (line.contains("Scanner") || line.contains("args")) {
                inputCount++;
            }
        }

        
        StringBuilder result = new StringBuilder();
        result.append("Control Structures Found:\n");
        result.append("- If/Else Statements: ").append(ifCount).append("\n");
        result.append("- Loops (For/While): ").append(loopCount).append("\n");
        result.append("- Try/Catch Blocks: ").append(tryCatchCount).append("\n");
        result.append("- Input Statements: ").append(inputCount).append("\n");

        return result.toString();
    }
}
