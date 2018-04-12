package com.example.android.moneygrid;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private SeekBar bar;
    private Handler handler;
    private Update update;
    private int spot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid=findViewById(R.id.grid);
        bar=findViewById(R.id.bar);
        handler=new Handler();
        update=new Update();
        handler.postDelayed(update,1000);

        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        resetGrid();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        for(int i=0; i<9; i++) {
            ImageView imageView=(ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            imageView.setImageDrawable(null);
            grid.addView(imageView);
        }
    }

    public void resetGrid() {
        grid.removeAllViews();
        int dimension = bar.getProgress()*3+3;
        grid.setColumnCount(dimension);
        grid.setRowCount(dimension);
        int size = dimension*dimension;
        spot=0;
        for(int i=0; i<size; i++) {
            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, grid, false);
            imageView.setImageDrawable(null);
            grid.addView(imageView);
        }
    }

    private class Update implements Runnable {

        @Override
        public void run() {
            ImageView buffalowildwings = (ImageView) grid.getChildAt(spot);
            buffalowildwings.setImageDrawable(null);

            int dimension = bar.getProgress()*3+3;
            int size = dimension*dimension;

            Random rand = new Random();
            spot=rand.nextInt(size);
            ImageView iv = (ImageView) grid.getChildAt(spot);
            Drawable d = getResources().getDrawable(R.drawable.fischbach);
            iv.setImageDrawable(d);
            handler.postDelayed(update,1000);

        }
    }
}
