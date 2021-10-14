package com.ricardo.dadoswarii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int dadosamarelos, dadosvermelhos;
    float full, half, low;

    Animation anim, anim1;

    ImageView ama01, ama02, ama03, ver01, ver03, ver02, defAereo, ataAereo;

    boolean dadoA1, dadoA2, dadoA3, dadoV1, dadoV2, dadoV3;

    Random rand01, rand02, rand03;
    TextView titulo, again, amarlose, vermlose;

    int A1, A2, A3, V1, V2, V3, amalose, verlose;

    int aleat01 = 0;

    Object imagensDefesaAereo[] = {R.drawable.azul00, R.drawable.azul00, R.drawable.azul01,
            R.drawable.azul01, R.drawable.azul03, R.drawable.azul03};

    Object imagensAtaqueAereo[] = {R.drawable.azul00, R.drawable.azul00, R.drawable.azul00,
            R.drawable.jato01, R.drawable.jato01, R.drawable.jato02};

    Object imagensAmarelo[] = {R.drawable.amarelo01, R.drawable.amarelo02, R.drawable.amarelo03,
            R.drawable.amarelo04, R.drawable.amarelo05, R.drawable.amarelo06};

    Object imagensVermelho[] = {R.drawable.vermelho01, R.drawable.vermelho02, R.drawable.vermelho03,
            R.drawable.vermelho04, R.drawable.vermelho05, R.drawable.vermelho06};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dadosamarelos = dadosvermelhos = 0;

        full = 1.0F;
        half = 0.5F;
        low = 0.2F;





        titulo = (TextView) findViewById(R.id.titulo);

        iniciar();

        anim = AnimationUtils.loadAnimation(this, R.anim.rotatedir);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.rotateesq);

        contagemDados();

    }

    private void iniciar(){

        setContentView(R.layout.activity_main);

        amarlose = (TextView) findViewById(R.id.amalose);
        amarlose.setVisibility(View.INVISIBLE);
        vermlose = (TextView) findViewById(R.id.vermlose);
        vermlose.setVisibility(View.INVISIBLE);

        A1 = A2 = A3 = V1 = V2 = V3 = 0;
        dadoA1 = dadoA2 = dadoA3 = dadoV1 = dadoV2 = dadoV3 = false;

        ver01 = (ImageView) findViewById(R.id.vermelho01);
        ver02 = (ImageView) findViewById(R.id.vermelho02);
        ver03 = (ImageView) findViewById(R.id.vermelho03);
        ver01.setAlpha((float) half);
        ver02.setAlpha((float) half);
        ver03.setAlpha((float) half);

        ama01 = (ImageView) findViewById(R.id.amarelo01);
        ama02 = (ImageView) findViewById(R.id.amarelo02);
        ama03 = (ImageView) findViewById(R.id.amarelo03);
        ama01.setAlpha((float) half);
        ama02.setAlpha((float) half);
        ama03.setAlpha((float) half);

        verificaAmarelosSelecionados();
        verificaVermelhosSelecionados();
        verificaSePodeRodar();
        verificaDefesaAerea();
        verificaAtaqueAerea();


    }

    private void verificaSePodeRodar() {
        /*fica "ouvindo* para saber SE PODE RODAR OS DADOS*/
        again = (TextView) findViewById(R.id.titulo);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dadosamarelos > 0 && dadosvermelhos > 0) {
                    if (!dadoA1)  ama01.setAlpha((float) low);
                    if (!dadoA2)  ama02.setAlpha((float) low);
                    if (!dadoA3)  ama03.setAlpha((float) low);
                    if (!dadoV1)  ver01.setAlpha((float) low);
                    if (!dadoV2)  ver02.setAlpha((float) low);
                    if (!dadoV3)  ver03.setAlpha((float) low);
                    sortearOrdenarAmarelos();
                    sortearOrdenarVermelhos();
                    contagemDados();
                }
            }
        });
    }

    private void verificaDefesaAerea(){
        /*fica "ouvindo* dado DESEFA AÉREA*/
        defAereo = (ImageView) findViewById(R.id.azulobola01);
        defAereo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateDadoDefesa();
            }
        });
    }

    private void verificaAtaqueAerea(){
        /*fica "ouvindo* dado ATAQUE AÉREA*/
        ataAereo = (ImageView) findViewById(R.id.azujatola01);
        ataAereo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateDadoAtaque();
            }
        });
    }

    private void contagemDados() {

        if (!dadoA1)  A1 = 0;
        if (!dadoA2)  A2 = 0;
        if (!dadoA3)  A3 = 0;
        if (!dadoV1)  V1 = 0;
        if (!dadoV2)  V2 = 0;
        if (!dadoV3)  V3 = 0;


        ArrayList<Integer> amarelos = new ArrayList<>();
            amarelos.add(A1);
            amarelos.add(A2);
            amarelos.add(A3);
        Collections.sort(amarelos);

        ArrayList<Integer> vermelhos = new ArrayList<>();
            vermelhos.add(V1);
            vermelhos.add(V2);
            vermelhos.add(V3);
        Collections.sort(vermelhos);

        amalose = verlose = 0;

            if (amarelos.get(2).equals(0) && vermelhos.get(2).equals(0)) {
                    //se ambos não nulos ninguem ganha e ninguem perder
            } else if (amarelos.get(2).equals(0) || vermelhos.get(2) > amarelos.get(2)){
                    // se o amarelo é nulo o vermelho ganha
                    verlose++;
            } else  amalose++;

        if (amarelos.get(1).equals(0) && vermelhos.get(1).equals(0)) {
            //se ambos não nulos ninguem ganha e ninguem perder
        } else if (amarelos.get(1).equals(0) || vermelhos.get(1) > amarelos.get(1)){
            // se o amarelo é nulo o vermelho ganha
            verlose++;
        } else  amalose++;

        if (amarelos.get(0).equals(0) && vermelhos.get(0).equals(0)) {
            //se ambos não nulos ninguem ganha e ninguem perder
        } else if (amarelos.get(0).equals(0) || vermelhos.get(0) > amarelos.get(0)){
            // se o amarelo é nulo o vermelho ganha
            verlose++;
        } else  amalose++;

        String verperdeu = "";
        String amaperdeu = "";

        if (verlose == 0) {
            verperdeu = "";
        } else verperdeu = String.valueOf(verlose);

        if (amalose == 0) {
            amaperdeu = "";
        } else amaperdeu = String.valueOf(amalose);

        vermlose.setVisibility(View.VISIBLE);
        vermlose.setText(amaperdeu);
        amarlose.setVisibility(View.VISIBLE);
        amarlose.setText(verperdeu);

    }

    public void sortearOrdenarAmarelos(){

        rand01 = new Random();
        int aux01 = rand01.nextInt(5) + 1;
        rand02 = new Random();
        int aux02 = rand02.nextInt(5) + 1;
        rand03 = new Random();
        int aux03 = rand03.nextInt(5) + 1;

        ArrayList<Integer> sorteados = new ArrayList<>();
            sorteados.add(aux01);
            sorteados.add(aux02);
            sorteados.add(aux03);

        Collections.sort(sorteados);

        A1 = sorteados.get(0);
        A2 = sorteados.get(1);
        A3 = sorteados.get(2);

        ama01.startAnimation(anim);
        ama01.setImageResource((Integer) imagensAmarelo[A1 - 1]);
        ama02.startAnimation(anim1);
        ama02.setImageResource((Integer) imagensAmarelo[A2 - 1]);
        ama03.startAnimation(anim);
        ama03.setImageResource((Integer) imagensAmarelo[A3 - 1]);





    }

    public void sortearOrdenarVermelhos(){


        rand01 = new Random();
        int aux01 = rand01.nextInt(5) + 1;
        rand02 = new Random();
        int aux02 = rand02.nextInt(5) + 1;
        rand03 = new Random();
        int aux03 = rand03.nextInt(5) + 1;

        ArrayList<Integer> sorteados = new ArrayList<>();
        sorteados.add(aux01);
        sorteados.add(aux02);
        sorteados.add(aux03);

        Collections.sort(sorteados);

        V1 = sorteados.get(0);
        V2 = sorteados.get(1);
        V3 = sorteados.get(2);

        ver01.setImageResource((Integer) imagensVermelho[V1 - 1]);
        ver01.startAnimation(anim);
        ver02.setImageResource((Integer) imagensVermelho[V2 - 1]);
        ver02.startAnimation(anim1);
        ver03.setImageResource((Integer) imagensVermelho[V3 - 1]);
        ver03.startAnimation(anim);

    }

    public void verificaAmarelosSelecionados(){
        ama01.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ama01.setAlpha((float) full);
                dadosamarelos++;
                dadoA1 = true;
                return true;
            }
        });

        ama02.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ama02.setAlpha((float) full);
                dadosamarelos++;
                dadoA2 = true;
                return true;
            }
        });

        ama03.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ama03.setAlpha((float) full);
                dadosamarelos++;
                dadoA3 = true;
                return true;
            }
        });

    }

    public void verificaVermelhosSelecionados(){

        ver01.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ver01.setAlpha((float) full);
                dadosvermelhos++;
                dadoV1 = true;
                return true;
            }
        });

        ver02.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ver02.setAlpha((float) full);
                dadosvermelhos++;
                dadoV2 = true;
                return true;
            }
        });

        ver03.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ver03.setAlpha((float) full);
                dadosvermelhos++;
                dadoV3 = true;
                return true;
            }
        });

    }


    public void rotateDadoDefesa(){
        rand01 = new Random();
        aleat01 = rand01.nextInt(6);
        defAereo.startAnimation(anim);
        defAereo.setImageResource((Integer) imagensDefesaAereo[aleat01]);
    }

    public void rotateDadoAtaque(){
        rand01 = new Random();
        aleat01 = rand01.nextInt(6);
        ataAereo.startAnimation(anim1);
        ataAereo.setImageResource((Integer) R.drawable.azul00);
        ataAereo.setImageResource((Integer) imagensAtaqueAereo[aleat01]);
    }


    public void sair(View view) {
        finish();
        System.exit(0);
    }

    public void again(View view) {
      iniciar();
    }
}