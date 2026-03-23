/**
 * Interface representing computer's choice algorithm
 */
public interface ComputerChoiceAlgorithm {

    /**
     * Choose a sign based on algorithm
     */
    Sign chooseSign();

    /**
     * Gets name of algorithm to display
     */
    String getAlgorithmName();
}
