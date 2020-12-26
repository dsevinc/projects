package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button00,button01,button02,button10,button11,button12,button20,button21,button22;
    Button newGameButton;
    TextView playerTurnText;
    TextView gameStatusText;
    TicTacToeGame game;
    CellValue nextMove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button00 = (Button) findViewById(R.id.button00);
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        button10 = (Button) findViewById(R.id.button10);
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button20 = (Button) findViewById(R.id.button20);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        playerTurnText = (TextView) findViewById(R.id.playerTurnText);
        gameStatusText = (TextView) findViewById(R.id.gameStatusText);
        playerTurnText.setText(R.string.xTurn);
        game = new TicTacToeGame();

        newGameButton.setOnClickListener(this);

        button00.setOnClickListener(this);
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);

        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);

        button20.setOnClickListener(this);
        button21.setOnClickListener(this);
        button22.setOnClickListener(this);
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
                case R.id.button00:
                    game.play(0);
                    button00.setText(move);
                    break;
                case R.id.button01:
                    game.play(1);
                    button01.setText(move);
                    break;
                case R.id.button02:
                    game.play(2);
                    button02.setText(move);
                    break;
                case R.id.button10:
                    game.play(3);
                    button10.setText(move);
                    break;
                case R.id.button11:
                    game.play(4);
                    button11.setText(move);
                    break;
                case R.id.button12:
                    game.play(5);
                    button12.setText(move);
                    break;
                case R.id.button20:
                    game.play(6);
                    button20.setText(move);
                    break;
                case R.id.button21:
                    game.play(7);
                    button21.setText(move);
                    break;
                case R.id.button22:
                    game.play(8);
                    button22.setText(move);
                    break;
                case R.id.newGameButton:
                    game = new TicTacToeGame();
                    button00.setText("");
                    button01.setText("");
                    button02.setText("");
                    button10.setText("");
                    button11.setText("");
                    button12.setText("");
                    button20.setText("");
                    button21.setText("");
                    button22.setText("");
                    playerTurnText.setText(R.string.xTurn);

            }
        } else {
            switch (view.getId()) {
                case R.id.newGameButton:
                    game = new TicTacToeGame();
                    button00.setText("");
                    button01.setText("");
                    button02.setText("");
                    button10.setText("");
                    button11.setText("");
                    button12.setText("");
                    button20.setText("");
                    button21.setText("");
                    button22.setText("");
                    playerTurnText.setText(R.string.xTurn);

            }
        }
        gameStatusText.setText(game.getGameState().name());
    }
}