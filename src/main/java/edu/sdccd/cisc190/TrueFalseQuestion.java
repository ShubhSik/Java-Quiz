package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
public class TrueFalseQuestion extends Question {
    private final String correctAnswer;
    // Stores the correct "True" or "False" answer.
    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }
/* Constructor:Sets the question text and correct answer.
Uses super() to call the parent Question class for initializing the question text.
*/
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
//Compares the player's answer with the correct answer
}
//This class represents a "True/False" type question in the quiz.
