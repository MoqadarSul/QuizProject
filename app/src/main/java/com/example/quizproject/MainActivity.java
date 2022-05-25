package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.sax.EndElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int questionCount =0;
    int correctAnswers = 0;
    Question[] myArray;
    int arrayLength;
    boolean boxSelected;
    CheckBox selectedBox;
    ArrayList<String> arrlist;
    RadioButton selectedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view){
        Question one = new Question(1, "Who is Canadian Prime Minister", "Justin Trudeau", "Donald Trump", "Barack Obama", "Justin Trudeau", "Andrew Shear", "button");
        Question two = new Question(2, "what is Canadian Capital", "Ottawa", "Toronto", "Ottawa", "Montreal", "Vancouver", "button");
        //NOTE: CHECKBOX HAS MULTIPLE ANSWERS
        Question three = new Question(3, "Select which city is in Canada?", "montreal", "paris", "winnipeg", "berlin", "checkbox");
        String[] correctAnswersCheckBox = {"montreal", "winnipeg"};
        three.setAnswerArray(correctAnswersCheckBox);
        Question four = new Question(4, "What year are we currently in?", "2022", "2019", "2023", "2018", "2022", "button");
        Question five = new Question(5, "What is the human population Currently?", "7 Billion", "3 Billion", "2 Billion", "7 Billion", "6 Billion", "button");
        Question six = new Question(6, "What month has 28 days?", "february", "textEdit");
        Question seven = new Question(7, "Canada is located in North America?", "true", "true", "false", "radioButton");
        myArray = new Question[7];
        myArray[0] = one;
        myArray[1] = two;
        myArray[2] = three;
        myArray[3] = four;
        myArray[4] = five;
        myArray[5] = six;
        myArray[6] = seven;
        arrayLength = myArray.length;
        Button btnReset = (Button) findViewById(R.id.reset);
        btnReset.setEnabled(true);
        setVisibility();
        prepareQuestion();
    }

    public void prepareQuestion(){
        if(questionCount == 7){
         endOfQuestion();
        }
        else if(myArray[questionCount].getTypeQuestion() == "button"){
            buttonAnalysis();
        }else if(myArray[questionCount].getTypeQuestion() == "checkbox"){
            checkBoxAnalysis();
        }else if(myArray[questionCount].getTypeQuestion() == "textEdit"){
            textEntryAnalysis();
        }else if(myArray[questionCount].getTypeQuestion() == "radioButton"){
            radioButtonAnalysis();
        }
    }

    public void onCheckboxClicked(View view){
        CheckBox box1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox box2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox box3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox box4 = (CheckBox) findViewById(R.id.checkbox4);
        arrlist = new ArrayList<String>();
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            Button btnNext = (Button) findViewById(R.id.buttonNext);
            btnNext.setEnabled(true);
        }
        if(box2.isChecked()){
            arrlist.add(box2.getText().toString());
        }if(box3.isChecked()){
            arrlist.add(box3.getText().toString());
        }if(box4.isChecked()){
            arrlist.add(box4.getText().toString());
        }if(box1.isChecked()){
        arrlist.add(box1.getText().toString());
        }
    }

    public void btnNextQuestion(View view){
        int localScore = 0;
        String[] correctAnswersArray = myArray[questionCount].getAnswerArray();//["montreal", "winnipeg"]
        if(correctAnswersArray.length == arrlist.toArray().length){
            for(String i: correctAnswersArray){
                if(arrlist.contains(i)){
                    localScore++;
                }
            }
        }if(localScore == correctAnswersArray.length){
            correctAnswers++;
        }
        questionCount++;
        prepareQuestion();
    }

    public void btnClicked(View view){
        String answer = myArray[questionCount].getAnswer().toString();
        Button clicked = (Button)view;
        String chosenAnswer = clicked.getText().toString();
        if(answer == chosenAnswer){
            correctAnswers++;
        }
        questionCount++;
        prepareQuestion();
    }

    public void radioButtonClick(View view){
        boolean checked = ((RadioButton) view).isChecked();
        Button btnClicked = (Button) findViewById(R.id.submitButton);
        switch(view.getId()) {
            case R.id.radio_true:
                if (checked)
                    selectedRadio = (RadioButton) findViewById(R.id.radio_true);
                    btnClicked.setEnabled(true);
                break;
            case R.id.radio_false:
                if (checked)
                    selectedRadio = (RadioButton) findViewById(R.id.radio_false);
                btnClicked.setEnabled(true);
                break;
        }
    }

    public void radioButtonSubmit(View view){

        if(selectedRadio.getText().toString().equals(myArray[questionCount].getAnswer())){
            correctAnswers++;
        }
        questionCount++;
        prepareQuestion();
    }

    public void textInputQuestion(View view){
        EditText userEntry = (EditText) findViewById(R.id.userEntryAnswer);
        String userEnteredtext = userEntry.getText().toString();
        String correctAnswer = myArray[questionCount].getAnswer();

        if(correctAnswer.equals(userEnteredtext)){
            correctAnswers++;
        }
        questionCount++;
        prepareQuestion();
    }

    public void resetQuiz(View view){

        LinearLayout buttonSectionView = (LinearLayout) findViewById(R.id.buttonSection);
        buttonSectionView.setVisibility(View.GONE);
        LinearLayout topHeadingView = (LinearLayout) findViewById(R.id.questionTopHeadings);
        topHeadingView.setVisibility(View.INVISIBLE);
        LinearLayout checkBoxView = (LinearLayout) findViewById(R.id.checkBoxSection);
        checkBoxView.setVisibility(View.GONE);
        LinearLayout radioButtonsView = (LinearLayout) findViewById(R.id.radioBtnSection);
        radioButtonsView.setVisibility(View.GONE);
        LinearLayout scoreView = (LinearLayout) findViewById(R.id.scoreSection);
        scoreView.setVisibility(View.GONE);
        LinearLayout textView = (LinearLayout) findViewById(R.id.textEntryLinearLayout);
        textView.setVisibility(View.GONE);
        EditText userEntry = (EditText) findViewById(R.id.userEntryAnswer);
        if(userEntry.getText().length()>0){
            userEntry.getText().clear();
        }
        Button btnNext = (Button) findViewById(R.id.buttonNext);
        btnNext.setEnabled(false);
        if(selectedRadio != null){
            selectedRadio.setChecked(false);
        };


        CheckBox box1 = (CheckBox) findViewById(R.id.checkbox1);
        box1.setChecked(false);
        box1.setEnabled(true);
        CheckBox box2 = (CheckBox) findViewById(R.id.checkbox2);
        box2.setChecked(false);
        box2.setEnabled(true);
        CheckBox box3 = (CheckBox) findViewById(R.id.checkbox3);
        box3.setChecked(false);
        box3.setEnabled(true);
        CheckBox box4 = (CheckBox) findViewById(R.id.checkbox4);
        box4.setChecked(false);
        box4.setEnabled(true);
        TextView beginText = (TextView)  findViewById(R.id.beginText);
        beginText.setVisibility(View.VISIBLE);
        LinearLayout questionTitles = (LinearLayout) findViewById(R.id.questionTopHeadings);
        questionTitles.setVisibility(View.GONE);
        LinearLayout buttonView = (LinearLayout) findViewById(R.id.scoreSection);
        buttonView.setVisibility(View.GONE);
        questionCount =0;
        correctAnswers = 0;
        arrayLength = 0;
    }

    public void setVisibility(){
        LinearLayout questionTitles = (LinearLayout) findViewById(R.id.questionTopHeadings);
        questionTitles.setVisibility(View.VISIBLE);
        TextView beginText = (TextView)  findViewById(R.id.beginText);
        beginText.setVisibility(View.GONE);
    }

    public void buttonAnalysis(){
        LinearLayout checkBoxView = (LinearLayout) findViewById(R.id.checkBoxSection);
        checkBoxView.setVisibility(View.GONE);
        LinearLayout myView = (LinearLayout) findViewById(R.id.buttonSection);
        myView.setVisibility(View.VISIBLE);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        TextView questionArea = (TextView) findViewById(R.id.questionHeader);
        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);
        questionArea.setText(myArray[questionCount].getQuestion().toString());
        questionNumber.setText(" "+myArray[questionCount].getqNumber());
        btn1.setText(myArray[questionCount].getOption1().toString());
        btn2.setText(myArray[questionCount].getOption2().toString());
        btn3.setText(myArray[questionCount].getOption3().toString());
        btn4.setText(myArray[questionCount].getOption4().toString());
    }

    public void checkBoxAnalysis(){
        LinearLayout buttonView = (LinearLayout) findViewById(R.id.buttonSection);
        buttonView.setVisibility(View.GONE);
        LinearLayout myView = (LinearLayout) findViewById(R.id.checkBoxSection);
        myView.setVisibility(View.VISIBLE);
        CheckBox box1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox box2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox box3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox box4 = (CheckBox) findViewById(R.id.checkbox4);
        TextView questionArea = (TextView) findViewById(R.id.questionHeaderCheckBox);
        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);
        questionArea.setText(myArray[questionCount].getQuestion().toString());
        questionNumber.setText(" "+myArray[questionCount].getqNumber());
        box1.setText(myArray[questionCount].getOption1().toString());
        box2.setText(myArray[questionCount].getOption2().toString());
        box3.setText(myArray[questionCount].getOption3().toString());
        box4.setText(myArray[questionCount].getOption4().toString());
    }

    public void textEntryAnalysis(){
        LinearLayout buttonView = (LinearLayout) findViewById(R.id.buttonSection);
        buttonView.setVisibility(View.GONE);
        LinearLayout checkBoxView = (LinearLayout) findViewById(R.id.checkBoxSection);
        checkBoxView.setVisibility(View.GONE);
        LinearLayout myView = (LinearLayout) findViewById(R.id.textEntryLinearLayout);
        myView.setVisibility(View.VISIBLE);
        TextView questionArea = (TextView) findViewById(R.id.questionHeaderTextView);
        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);
        questionArea.setText(myArray[questionCount].getQuestion().toString());
        questionNumber.setText(" "+myArray[questionCount].getqNumber());
    }

    public void radioButtonAnalysis(){
        LinearLayout buttonView = (LinearLayout) findViewById(R.id.buttonSection);
        buttonView.setVisibility(View.GONE);
        LinearLayout checkBoxView = (LinearLayout) findViewById(R.id.checkBoxSection);
        checkBoxView.setVisibility(View.GONE);
        LinearLayout textEntryView = (LinearLayout) findViewById(R.id.textEntryLinearLayout);
        textEntryView.setVisibility(View.GONE);
        LinearLayout radioButtonsView = (LinearLayout) findViewById(R.id.radioBtnSection);
        radioButtonsView.setVisibility(View.VISIBLE);
        TextView questionArea = (TextView) findViewById(R.id.questionHeaderRadiobutton);
        TextView questionNumber = (TextView) findViewById(R.id.questionNumber);
        questionArea.setText(myArray[questionCount].getQuestion().toString());
        questionNumber.setText(" "+myArray[questionCount].getqNumber());
    }

    public void endOfQuestion(){
        LinearLayout radioButtonsView = (LinearLayout) findViewById(R.id.radioBtnSection);
        radioButtonsView.setVisibility(View.GONE);
        LinearLayout buttonView = (LinearLayout) findViewById(R.id.scoreSection);
        buttonView.setVisibility(View.VISIBLE);
        LinearLayout questionHeader = (LinearLayout) findViewById(R.id.questionTopHeadings);
        questionHeader.setVisibility(View.INVISIBLE);

        TextView correctNumber = (TextView) findViewById(R.id.questionsCorrectCounter);
        TextView slashView = (TextView) findViewById(R.id.slashScore);
        TextView totalQuestions = (TextView) findViewById(R.id.questionsTotalCount);
        correctNumber.setText(""+correctAnswers);
        slashView.setText(" / ");
        totalQuestions.setText(""+arrayLength);

        Context context = getApplicationContext();
        CharSequence text = "Out of "+myArray.length+" questions You Scored "+correctAnswers;
        text += "\n Press Reset to reset and start again";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}