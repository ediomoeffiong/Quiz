import java.util.ArrayList;
import java.util.Scanner;

//General class for questions
abstract class Question {
    protected String questionText;
    protected String answer;

    public Question(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public abstract boolean checkAnswer(String response);

    public abstract void display();
}

//For multiple choice questions
class MultipleChoiceQuestion extends Question {
    private ArrayList<String> options;

    public MultipleChoiceQuestion(String questionText, String answer, ArrayList<String> options) {
        super(questionText, answer);
        this.options = options;
    }

    @Override
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(answer);
    }

    @Override
    public void display() {
        System.out.println(questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((char) ('A' + i) + ": " + options.get(i));
        }
    }
}

// Class for true or false questions
class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String questionText, String answer) {
        super(questionText, answer);
    }

    @Override
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(answer);
    }

    @Override
    public void display() {
        System.out.println(questionText + " (True/False)");
    }
}

public class Main {
    private ArrayList<Question> questions;

    public Main() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            question.display();
            System.out.print("Your answer: ");
            String response = scanner.nextLine();

            if (question.checkAnswer(response)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + question.answer);
            }

            System.out.println();
        }

        System.out.println("You scored " + score + " out of " + questions.size());
        scanner.close();
    }

    public static void main(String[] args) {
        Main quiz = new Main();

        ArrayList<String> options1 = new ArrayList<>();
        options1.add("Java");
        options1.add("Python");
        options1.add("C++");
        options1.add("Ruby");
        quiz.addQuestion(new MultipleChoiceQuestion("Which programming language is this quiz written in?", "A", options1));

        quiz.addQuestion(new TrueFalseQuestion("Java is a statically-typed language.", "True"));

        quiz.addQuestion(new TrueFalseQuestion("Java is a programming language.", "True"));

        quiz.start();
    }
}
