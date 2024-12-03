package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
public abstract class Question {//This defines an abstract class called Question.
    private final String questionText;
//A private variable to store the question text.
    public Question(String questionText) {
        this.questionText = questionText;
    }
/* Constructor:
Initializes the question text.
Saves the given text into the questionText variable.
*/
    public String getQuestionText() {
        return questionText;
    }
//Returns the question text when called.
    public abstract boolean checkAnswer(String answer);
//An abstract method. Subclasses must implement and check if the given answer is correct.
}
//This Question class handles storing the question text and leaves the answer-checking logic for other classes to figure out.