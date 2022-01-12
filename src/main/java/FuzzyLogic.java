import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class FuzzyLogic {
    private static final String FCL_FILE = "src/main/resources/ap.fcl";

    public static void main(String[] args) {
        FIS fis = FIS.load(FCL_FILE,false);
        FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();
        fuzzyRuleSet.chart();
    }
}
