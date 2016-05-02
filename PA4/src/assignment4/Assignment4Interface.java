package assignment4;

import java.util.List;

public interface Assignment4Interface
{
    /**
     * Computes the word ladder from startWord to endWord.
     *
     * NOTE: This is NOT the MakeLadder recursive method which is in the lab manual. You are to implement
     * that method on your own, or a variant of it if you decide you wish to do something iterative, such as
     * breadth first search. This method is simply provided to ease in grading. This method will be called
     * by the testing script, and the List of Strings it returns (or exception thrown) should be valid.
     *
     * HINT: This method should call other methods you write, please do not have all your code in this method.
     *
     * @param startWord The starting word in the word ladder.
     * @param endWord The ending word in the word ladder.
     * @return A list of strings that represents the word ladder. The 0th index should contain
     * the startWord and the last position should contain endWord. All intermediate words should
     * be different by exactly one letter.
     * @throws NoSuchLadderException is thrown if no word ladder can be generated from startWord and endWord.
     */
    List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException;

    /**
     * Determines whether or not a word ladder is valid. NOTE: this method is NOT part of the requirements
     * of the lab, but it would be a great idea to implement it so you can programatically verify if the word ladder
     * you return in the above method is correct.
     * For a word ladder to be valid the 0th index must be startWord, the endWord must be in the last index position,
     * and all intermediate words need to be exactly one distance apart.
     * @param startWord The starting word in the word ladder.
     * @param endWord The ending word in the word ladder.
     * @param wordLadder The wordLadder to check if the solution is valid
     * @return True if the word ladder is correct, false otherwise.
     */
    boolean validateResult(String startWord, String endWord, List<String> wordLadder);
}
