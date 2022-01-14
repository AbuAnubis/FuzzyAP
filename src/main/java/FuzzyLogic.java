import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

import javax.crypto.ExemptionMechanismException;

public class FuzzyLogic {
    private static final String FCL_FILE = "src/main/resources/ap.fcl";

    private FIS fis;
    private FuzzyRuleSet fuzzy;

    public FuzzyLogic(boolean showCharts) {
        fis = FIS.load(FCL_FILE,false);
        fuzzy = fis.getFuzzyRuleSet();
        if (showCharts) {
            fuzzy.chart();
        }
    }

    public void setValues(double speed, double alt_delta, double ground) {
        fuzzy.setVariable("speed", speed);
        fuzzy.setVariable("alt_delta", alt_delta);
        fuzzy.setVariable("ground", ground);
    }

    public double getValue() {
        fuzzy.evaluate();
        return fuzzy.getVariable("pitch").getLatestDefuzzifiedValue();
    }

    public static void main(String[] args) {
        FIS fis = FIS.load(FCL_FILE,false);
        FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();
        fuzzyRuleSet.chart();
    }
}
