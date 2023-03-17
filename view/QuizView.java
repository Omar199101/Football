package view;

//package com.example.footballquiz;
import controller.Controller;
import java.awt.Font;
import java.awt.event.ActionEvent;
import model.Question;
import javax.swing.*;
import java.awt.event.ActionListener;

public class QuizView {

    private Controller controller;
    private JFrame frame;
    private JLabel questionLabel;
    private JLabel questionNumber;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JButton prevButton;
    private Font font = new Font("Arial", Font.PLAIN, 16);

    public QuizView(Controller controller) {
        this.controller = controller;
        frame = new JFrame("Football Quiz");
//        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 300);
        frame.setLayout(null);
        questionNumber = new JLabel();
        questionNumber.setBounds(600, 10, 100, 40);
        questionNumber.setFont(font);
        frame.add(questionNumber);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 600, 30);
        questionLabel.setFont(font);
        frame.add(questionLabel);

        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        optionButtons[0] = new JRadioButton();
        optionButtons[0].setBounds(50, 100, 300, 30);
        optionButtons[0].setFont(font);
        optionGroup.add(optionButtons[0]);
        frame.add(optionButtons[0]);

        optionButtons[1] = new JRadioButton();
        optionButtons[1].setBounds(400, 100, 300, 30);
        optionButtons[1].setFont(font);
        optionGroup.add(optionButtons[1]);
        frame.add(optionButtons[1]);

        optionButtons[2] = new JRadioButton();
        optionButtons[2].setBounds(50, 150, 300, 30);
        optionButtons[2].setFont(font);
        optionGroup.add(optionButtons[2]);
        frame.add(optionButtons[2]);

        optionButtons[3] = new JRadioButton();
        optionButtons[3].setBounds(400, 150, 300, 30);
        optionButtons[3].setFont(font);
        optionGroup.add(optionButtons[3]);
        frame.add(optionButtons[3]);
        nextButton = new JButton("Next");
        nextButton.setBounds(350, 220, 100, 30);
        nextButton.setFont(font);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nextQuestion();
            }
        });
        frame.add(nextButton);

        prevButton = new JButton("Previous");
        prevButton.setBounds(50, 220, 100, 30);
        prevButton.setFont(font);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.prevQuestion();
            }
        });
        disablePrevButton();
        frame.add(prevButton);
    }

    public String getUserAnswer() {
        JRadioButton userChoice = null;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                userChoice = optionButtons[i];
                break;
            }
        }
        if (userChoice != null) {
            return userChoice.getText();
        }
        return "";
    }

    public void display() {
        frame.setVisible(true);
    }

    public void updateQuestion(Question question, int currentQuestionNum, int totalQuestionNum) {
        questionNumber.setText("Quiz: " + currentQuestionNum + "/" + totalQuestionNum);
        questionLabel.setText(question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
        }
    }

    public void clearSelection() {
        optionGroup.clearSelection();
    }

    public void enablePrevButton() {
        this.prevButton.setEnabled(true);
    }

    public void disablePrevButton() {
        this.prevButton.setEnabled(false);
    }

    public void enableNextButton() {
        this.nextButton.setEnabled(true);
    }

    public void disableNextButton() {
        this.nextButton.setEnabled(false);
    }

    public void setNextButtonTextToFinish() {
        this.nextButton.setText("Finish");
    }

    public void setNextButtonTextToNext() {
        this.nextButton.setText("Next");
    }

    public void showGameOverMessage(String message, int score) {
        disablePrevButton();
        disableNextButton();
        JOptionPane.showMessageDialog(frame, "Game Over! Your score is: " + score, "Football Quiz", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
