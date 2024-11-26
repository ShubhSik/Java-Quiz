package edu.sdccd.cisc190;

public class MultipleChoiceQuestion extends Question {
    private String correctAnswer;
    private String[] choices;

    public MultipleChoiceQuestion(String questionText, String correctAnswer, String[] choices) {
        super(questionText);
        this.correctAnswer = correctAnswer;
        this.choices = choices;
    }

    public String[] getChoices() {
        return choices;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
