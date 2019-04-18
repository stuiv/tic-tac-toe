package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Game game;

    private SparseIntArray Board = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Board.put(R.id.button0, 0);
        Board.put(R.id.button1, 1);
        Board.put(R.id.button2, 2);
        Board.put(R.id.button3, 3);
        Board.put(R.id.button4, 4);
        Board.put(R.id.button5, 5);
        Board.put(R.id.button6, 6);
        Board.put(R.id.button7, 7);
        Board.put(R.id.button8, 8);

        game = new Game();
        TextView game_state_text  = findViewById(R.id.gamestate);
        game_state_text.setText("");
        TextView newtext  = findViewById(R.id.Status);
        newtext.setText("✕");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    public void resetbutton(View v) {
        Button button = (Button) v;
        TextView game_state_text  = findViewById(R.id.gamestate);
        if (button.getId() == R.id.resetbutton) {
            resetClicked();
            game_state_text.setText("");
        }
    }

    public void tileClicked(View view) {
        int button_nr = Board.get(view.getId());
        int column = button_nr % 3;
        int row = button_nr / 3;

        game.choose(row, column);
        UI_game();
        TextView game_state_text  = findViewById(R.id.gamestate);
        switch (game.won()) {
            case PLAYER_ONE:
                game_state_text.setText("◯ Wins!");
                break;
            case PLAYER_TWO:
                game_state_text.setText("✕ Wins!");
                break;
            case DRAW:
                game_state_text.setText("Draw!");
                break;
        }

    }
    public void resetClicked() {
        game = new Game();
        UI_game();
        TextView game_state_text  = findViewById(R.id.gamestate);
        game_state_text.setText("Start New Game");
    }

    private void UI_game() {

        TextView newtext  = findViewById(R.id.Status);
        if(game.playerOneTurn) {
            newtext.setText("✕");
        } else {
            newtext.setText("◯");
        }

        for (int i = 0; i < 9; i++) {
            int button_ID = Board.keyAt(i);
            int button_nr = Board.get(button_ID);
            Button button = findViewById(button_ID);

            int column = button_nr % 3;
            int row = button_nr / 3;

            switch (game.getTileState(row, column)) {
                case CROSS:
                    button.setText("✕");
                    break;
                case CIRCLE:
                    button.setText("◯");
                    break;
                case BLANK:
                    button.setText("");
                    break;
                case INVALID:
                    TextView game_state_text  = findViewById(R.id.gamestate);
                    game_state_text.setText("Invalid!!!");

                    return;
            }
        }
    }
}
