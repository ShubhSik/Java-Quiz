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
/* Constructor:
Calls the parent Question constructor to set the question text.
Initializes the correct answer and choices.
*/
    public String[] getChoices() {
        return choices;
    }
//A method to return the array of choices so they can be displayed or accessed later.
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
//Checks if the player's answer matches the correct one
}
//This class extends a general Question class to create a multiple-choice question.

