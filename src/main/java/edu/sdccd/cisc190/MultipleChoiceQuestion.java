package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
public class MultipleChoiceQuestion extends Question {//Defines a MultipleChoiceQuestion class that inherits (extends) from a Question class.
    private final String correctAnswer;//A private variable to store the correct answer for the question.
    private final String[] choices;//A private array to hold the multiple-choice options for the question.

    public MultipleChoiceQuestion(String questionText, String correctAnswer, String[] choices) {
        super(questionText);
        this.correctAnswer = correctAnswer;
        this.choices = choices;
    }
/*A constructor that:
Calls the parent Question class constructor to set the question text (super(questionText)).
Saves the correct answer and choices provided when the object is created.
 */
    public String[] getChoices() {
        return choices;
    }
//A method to return the array of choices so they can be displayed or accessed later.
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
/*This method overrides the checkAnswer method from the Question class.
It checks if the given answer matches the correct one, ignoring case differences. Returns true if it matches, otherwise false.
 */
}
/*This class extends a general Question class to create a multiple-choice question.

It adds a list of answer choices (choices) and a correct answer (correctAnswer).
The checkAnswer method ensures the user's response matches the correct answer, regardless of case.
 */