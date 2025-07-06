// Card.java
package org.example.studycards;

import java.util.Objects;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = validateText(question, "question");
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = validateText(answer, "answer");
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    private String validateText(String text, String fieldName) {
        Objects.requireNonNull(text, fieldName + " cannot be null");
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return text.trim();
    }

    @Override
    public String toString() {
        return "Card{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Card)) return false;
        Card other = (Card) obj;
        return question.equals(other.question) && answer.equals(other.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
