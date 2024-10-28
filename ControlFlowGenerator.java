public class ControlFlowGenerator {
    private Flowchart flowchart;
    private CodeParser codeParser;
    private ErrorManager errorManager;
    private PerformanceManager performanceManager;

    public ControlFlowGenerator() {
        this.flowchart = new Flowchart();
        this.codeParser = new CodeParser("");
        this.errorManager = new ErrorManager();
        this.performanceManager = new PerformanceManager();
    }

    public void inputCode(String code) {
        this.codeParser = new CodeParser(code);
    }

    public String analyzeCode() {
        try {
            return codeParser.analyze(); 
        } catch (Exception e) {
            errorManager.handleError(e);
            return "Error during analysis: " + e.getMessage(); 
        }
    }

    public String generateFlowchart() {
        String analysisResult = codeParser.analyze();
        return flowchart.generateFlowchart(analysisResult); 
    }
}
