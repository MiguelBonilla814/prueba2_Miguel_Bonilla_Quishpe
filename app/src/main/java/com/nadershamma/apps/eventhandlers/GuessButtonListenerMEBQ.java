package com.nadershamma.apps.eventhandlers;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nadershamma.apps.androidfunwithflags.MainActivityFragmentMEBQ;
import com.nadershamma.apps.androidfunwithflags.R;
import com.nadershamma.apps.androidfunwithflags.ResultsDialogFragmentMEBQ;
import com.nadershamma.apps.lifecyclehelpers.QuizViewModelMEBQ;

public class GuessButtonListenerMEBQ implements OnClickListener {
    private MainActivityFragmentMEBQ mainActivityFragmentMEBQ;
    private Handler handler;

    public GuessButtonListenerMEBQ(MainActivityFragmentMEBQ mainActivityFragmentMEBQ) {
        this.mainActivityFragmentMEBQ = mainActivityFragmentMEBQ;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.mainActivityFragmentMEBQ.getQuizViewModel().getCorrectCountryName();
        this.mainActivityFragmentMEBQ.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.mainActivityFragmentMEBQ.getQuizViewModel().setCorrectAnswers(1);
            this.mainActivityFragmentMEBQ.getAnswerTextView().setText(answer + "!");
            this.mainActivityFragmentMEBQ.getAnswerTextView().setTextColor(
                    this.mainActivityFragmentMEBQ.getResources().getColor(R.color.correct_answer));

            this.mainActivityFragmentMEBQ.disableButtons();

            if (this.mainActivityFragmentMEBQ.getQuizViewModel().getCorrectAnswers()
                    == QuizViewModelMEBQ.getFlagsInQuiz()) {
                ResultsDialogFragmentMEBQ quizResults = new ResultsDialogFragmentMEBQ();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.mainActivityFragmentMEBQ.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(QuizViewModelMEBQ.getTag(),
                            "GuessButtonListener: this.mainActivityFragment.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                mainActivityFragmentMEBQ.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.mainActivityFragmentMEBQ.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}
