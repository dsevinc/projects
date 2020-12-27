package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button0,button1,button2,button3,button4,button5,button6,button7,button8;
    Button newGameButton;
    TextView playerTurnText;
    TextView gameStatusText;
    TicTacToeGame game;
    CellValue nextMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        playerTurnText = (TextView) findViewById(R.id.playerTurnText);
        gameStatusText = (TextView) findViewById(R.id.gameStatusText);
        playerTurnText.setText(R.string.xTurn);
        game = new TicTacToeGame();

        newGameButton.setOnClickListener(this);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (game.getGameState().equals(GameState.PLAYING)) {
            nextMove=game.nextCellValue();
            String move = "";
            if (nextMove == CellValue.O) {
                playerTurnText.setText(R.string.xTurn);
                move = "O";
            } else if (nextMove == CellValue.X) {
                playerTurnText.setText(R.string.oTurn);
                move = "X";
            }
            switch (view.getId()) {
                case R.id.button0:
                    game.play(0);
                    button0.setText(move);
                    break;
                case R.id.button1:
                    game.play(1);
                    button1.setText(move);
                    break;
                case R.id.button2:
                    game.play(2);
                    button2.setText(move);
                    break;
                case R.id.button3:
                    game.play(3);
                    button3.setText(move);
                    break;
                case R.id.button4:
                    game.play(4);
                    button4.setText(move);
                    break;
                case R.id.button5:
                    game.play(5);
                    button5.setText(move);
                    break;
                case R.id.button6:
                    game.play(6);
                    button6.setText(move);
                    break;
                case R.id.button7:
                    game.play(7);
                    button7.setText(move);
                    break;
                case R.id.button8:
                    game.play(8);
                    button8.setText(move);
                    break;
                case R.id.newGameButton:
                    resetGame();
            }
        } else {
            switch (view.getId()) {
                case R.id.newGameButton:
                    resetGame();
            }
        }
        gameStatusText.setText(game.getGameState().name());
    }

    public void resetGame() {
        game = new TicTacToeGame();
        button0.setText("");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        playerTurnText.setText(R.string.xTurn);
    }
}