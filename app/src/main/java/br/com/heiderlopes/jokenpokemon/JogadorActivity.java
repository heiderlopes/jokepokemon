package br.com.heiderlopes.jokenpokemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.heiderlopes.jokenpokemon.model.Player;

public class JogadorActivity extends AppCompatActivity {

    private EditText etNomeJogador;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private RadioGroup rgSexo;
    private ImageView ivPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);
        etNomeJogador = (EditText) findViewById(R.id.etNomeJogador);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        ivPlayer = (ImageView) findViewById(R.id.ivPlayer);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);

        rbMasculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPlayer.setImageResource(R.drawable.player_masculino);
            }
        });

        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rbFeminino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivPlayer.setImageResource(R.drawable.player_feminino);
            }
        });
    }

    public void iniciarJogo(View v) {
        Intent i = new Intent(this, GameActivity.class);

        i.putExtra("sexoJogador", rgSexo.getCheckedRadioButtonId() == R.id.rbMasculino?
                Player.SEXO_MASCULINO : Player.SEXO_FEMININO);

        i.putExtra("nomeJogador", etNomeJogador.getText().toString());
        startActivity(i);
    }
}
