package br.com.heiderlopes.jokenpokemon;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import br.com.heiderlopes.jokenpokemon.model.Player;


public class GameActivity extends AppCompatActivity {

    private Random numeroAleatorio;

    private TextView tvNomeJogador;
    private ImageView ivSexo;

    private TextView tvNumeroVitorias;
    private TextView tvNumeroEmpates;
    private TextView tvNumeroDerrotas;
    private TextView tvResultadoRodada;

    private Button btBulbasaur;
    private Button btSquirtle;
    private Button btCharmander;

    private ImageView ivPokemonPlayer1;
    private ImageView ivPokemonPlayer2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tvNomeJogador = (TextView) findViewById(R.id.tvNomeJogador);
        ivSexo  = (ImageView)findViewById(R.id.ivSexo);

        tvNumeroVitorias = (TextView) findViewById(R.id.tvNumeroVitorias);
        tvNumeroEmpates = (TextView)findViewById(R.id.tvNumeroEmpates);
        tvNumeroDerrotas= (TextView)findViewById(R.id.tvNumeroDerrotas);
        tvResultadoRodada = (TextView)findViewById(R.id.tvResultadoRodada);
        btBulbasaur = (Button) findViewById(R.id.btBulbassaur);
        btSquirtle = (Button) findViewById(R.id.btSquirtle);
        btCharmander = (Button) findViewById(R.id.btCharmander);

        ivPokemonPlayer1 = (ImageView) findViewById(R.id.ivPokemonPlayer1);
        ivPokemonPlayer2 = (ImageView) findViewById(R.id.ivPokemonPlayer2);

        if(getIntent() != null) {
            String nome = getIntent().getExtras().getString("nomeJogador");
            tvNomeJogador.setText(nome);
            int sexoJogador = getIntent().getExtras().getInt("sexoJogador");
            if(sexoJogador == Player.SEXO_MASCULINO) {
                ivSexo.setImageResource(R.drawable.masculino);
            } else {
                ivSexo.setImageResource(R.drawable.feminino);
            }
        }


        numeroAleatorio = new Random();


        btSquirtle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ivPokemonPlayer1.setImageResource(R.drawable.p1_squirtle);
                realizaJogadaPC(1);
            }
        });

        btCharmander.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ivPokemonPlayer1.setImageResource(R.drawable.p1_charmander);
                realizaJogadaPC(2);
            }
        });

        btBulbasaur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ivPokemonPlayer1.setImageResource(R.drawable.p1_bulbasaur);
                realizaJogadaPC(3);
            }
        });
    }

    public void realizaJogadaPC(int jogadaPlayer) {
        int jogadaPC;
        jogadaPC = numeroAleatorio.nextInt(3) + 1;

        if (jogadaPC == 1) {
            ivPokemonPlayer2.setImageResource(R.drawable.p2_squirtle);
            if (jogadaPlayer == 1)
                empate();
            else if (jogadaPlayer == 2)
                derrota();
            else
                vitoria();
        } else if (jogadaPC == 2) {
            ivPokemonPlayer2.setImageResource(R.drawable.p2_charmander);
            if (jogadaPlayer == 1)
                vitoria();
            else if (jogadaPlayer == 2)
                empate();
            else
                derrota();
        } else if (jogadaPC == 3) {
            ivPokemonPlayer2.setImageResource(R.drawable.p2_bulbasaur);
            if (jogadaPlayer == 1)
                derrota();
            else if (jogadaPlayer == 2)
                vitoria();
            else
                empate();
        }
    }

    private void vitoria() {
        tvResultadoRodada.setText("GANHOU");
        tvNumeroVitorias.setText(String.valueOf(Integer.parseInt(tvNumeroVitorias.getText().toString()) + 1));
        tvResultadoRodada.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundVitoria));
    }

    private void empate(){
        tvResultadoRodada.setText("EMPATOU");
        tvNumeroEmpates.setText(String.valueOf(Integer.parseInt(tvNumeroEmpates.getText().toString()) + 1));
        tvResultadoRodada.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundEmpate));
    }

    private void derrota() {
        tvResultadoRodada.setText("DERROTA");
        tvNumeroDerrotas.setText(String.valueOf(Integer.parseInt(tvNumeroDerrotas.getText().toString()) + 1));
        tvResultadoRodada.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundDerrota));
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    private void showMessage(String mensagem) {

        /*AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Reset...");
        alertDialog.setMessage(mensagem);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });
        //alertDialog.setIcon(R.drawable.icon);
        alertDialog.show();*/
        //Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}