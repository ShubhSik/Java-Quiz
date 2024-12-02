package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
public abstract class Question {//This defines an abstract class called Question.
    private final String questionText;
//A private variable to store the question text.
    public Question(String questionText) {
        this.questionText = questionText;
    }
/*A constructor to set the question text when the object is created.
Saves the given question text into the questionText variable.
 */
    public String getQuestionText() {
        return questionText;
    }
/*A method to get (or read) the question text.
Returns the saved question text when called.
 */
    public abstract boolean checkAnswer(String answer);
//An abstract method. Subclasses must implement and check if the given answer is correct.
}
//This Question class handles storing the question text and leaves the answer-checking logic for other classes to figure out.