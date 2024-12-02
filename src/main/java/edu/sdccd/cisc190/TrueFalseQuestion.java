package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
public class TrueFalseQuestion extends Question {
    private final String correctAnswer;
/*Class Declaration:
The TrueFalseQuestion class extends the Question class, meaning it inherits all the functionality of the Question class and can add its own specific features.
Adds a new field, correctAnswer, which stores the correct "True" or "False" answer for this question type.
 */
    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }
/*Takes two parameters: questionText (the question being asked) and correctAnswer (the correct "True" or "False" answer).
Calls the parent class (Question) constructor using super(questionText) to initialize the question text.
Stores the correctAnswer in the private field.
 */
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
/*Checks the Player's Answer:
Overrides the checkAnswer method from the Question class to provide specific behavior for true/false questions.
Compares the player's answer (answer) with the correct answer (correctAnswer).
Uses equalsIgnoreCase to ensure the comparison is case-insensitive (e.g., "TRUE" and "true" will be treated as the same).
 */
}
/*
This class represents a "True/False" type question in the quiz.
It inherits basic question functionality from the Question class while adding behavior specific to true/false questions.
It stores the correct answer and checks if a given answer matches it (ignoring case).
 */