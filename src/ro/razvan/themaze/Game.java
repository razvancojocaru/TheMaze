package ro.razvan.themaze;

import java.util.Timer;
import java.util.TimerTask;

import ro.razvan.maze3d.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Game extends SelectLevel {

	
	//Matricea labirint(1 pentru perete, 0 pentru culoar)
	//(2 pentru pozitie, 3 pentru exit)
	byte[][] MazeMatr;
	int level;
	
	//Hardcodare de neam prost
	byte[][] lvl1 = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1},
			{1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};
	
	byte[][] lvl2 = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};
	
	byte[][] lvl3 = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
			{1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
			{1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};

	byte[][] lvl4 = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
			{1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1},
			{1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
			{1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1},
			{1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1},
			{1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};
	
	//Pozitia curenta
	int vert, oriz;
	
	//Coordonatele urmatoare
	int maxheight, maxwidth;
	
	//Numarul de patratele vizibile
	int nrviz = 11;
	
	//Dimensiunea unei imagini patrate (latura)
	int dim_img;
	
	//Orientarea caracterului (0-sus ; 1-dreapta ; 2-jos ; 3-stanga)
	int directie = 0;
	
	//SurfaceView, holdere si bitmap
	SurfaceView surf;
	SurfaceHolder holder;
	Paint paint;
	Bitmap path;
	Bitmap wall;
	Bitmap cup;
	Bitmap cright;
	Bitmap cdown;
	Bitmap cleft;
	Bitmap gameover;
	
	BitmapDrawable drawpath;
	BitmapDrawable drawwall;
	BitmapDrawable drawcup;
	BitmapDrawable drawcright;
	BitmapDrawable drawcdown;
	BitmapDrawable drawcleft;
	BitmapDrawable drawgameover;
	
	//Timer
	private TextView timerValue;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	int mins;
	int secs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		timerValue = (TextView) findViewById(R.id.timerValue);
		startTime = SystemClock.uptimeMillis();
		customHandler.postDelayed(updateTimerThread, 0);
		
		//Alegere level
		Intent intent = getIntent ();
		level = intent.getIntExtra("lvl", 0);
		maxheight = intent.getIntExtra("height", 0);
		maxwidth = intent.getIntExtra("width", 0);
		
		switch (level) {
		case 1:
			MazeMatr = lvl1;
			break;
		case 2:
			MazeMatr = lvl2;
			break;
		case 3:
			MazeMatr = lvl3;
			break;
		default:
			MazeMatr = lvl4;
			break;
		}

		
		//Hardcodare
		vert = maxheight - 3;
		oriz = 2;
		MazeMatr[vert][oriz] = 2;
		
		
		//Initializari pentru grafice
		surf = (SurfaceView)findViewById(R.id.map);
		holder = surf.getHolder();
		paint = new Paint();
		drawpath = (BitmapDrawable) getResources().getDrawable(R.drawable.path);
		drawwall = (BitmapDrawable) getResources().getDrawable(R.drawable.wall);
		drawcup = (BitmapDrawable) getResources().getDrawable(R.drawable.cup);
		drawcright = (BitmapDrawable) getResources().getDrawable(R.drawable.cright);
		drawcdown = (BitmapDrawable) getResources().getDrawable(R.drawable.cdown);
		drawcleft = (BitmapDrawable) getResources().getDrawable(R.drawable.cleft);
		drawgameover = (BitmapDrawable) getResources().getDrawable(R.drawable.gameover);
		
		path = drawpath.getBitmap();
		wall = drawwall.getBitmap();
		cup = drawcup.getBitmap();
		cright = drawcright.getBitmap();
		cdown = drawcdown.getBitmap();
		cleft = drawcleft.getBitmap();
		gameover = drawgameover.getBitmap();
		
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		int dispheight = display.getHeight();
		
		dim_img = dispheight / nrviz;
		
		wall = Bitmap.createScaledBitmap(wall, dim_img, dim_img, false);
		path = Bitmap.createScaledBitmap(path, dim_img, dim_img, false);
		cup = Bitmap.createScaledBitmap(cup, dim_img, dim_img, false);
		cright = Bitmap.createScaledBitmap(cright, dim_img, dim_img, false);
		cdown = Bitmap.createScaledBitmap(cdown, dim_img, dim_img, false);
		cleft = Bitmap.createScaledBitmap(cleft, dim_img, dim_img, false);
		gameover = Bitmap.createScaledBitmap(gameover, dim_img, dim_img, false);
				
		
		//Initializare butoane
		Button stanga = (Button)findViewById(R.id.sgtstanga);
		Button jos = (Button)findViewById(R.id.sgtjos);
		Button dreapta = (Button)findViewById(R.id.sgtdreapta);
		Button sus = (Button)findViewById(R.id.sgtsus);
		
		
		//Prima afisare
		holder.addCallback(new Callback() {
			
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub			
			}
			
			public void surfaceCreated(final SurfaceHolder holder) {
				// aici obtinem canvasul, il editam si il postam inapoi
				printMatr();				
			}
			
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub	
			}
		});
			

		//Comenzi pentru control butoane
		stanga.setOnClickListener(
	    		new View.OnClickListener()
				{
					public void onClick(View view)
					{
			            goLeft();
					}
				
				});
		
		dreapta.setOnClickListener(
	    		new View.OnClickListener()
				{
					public void onClick(View view)
					{
			            goRight();
					}
				
				});
		
		sus.setOnClickListener(
	    		new View.OnClickListener()
				{
					public void onClick(View view)
					{
			            goUp();
					}
				
				});
		
		jos.setOnClickListener(
	    		new View.OnClickListener()
				{
					public void onClick(View view)
					{
			            goDown();
					}
				
				});
		
/*
 * 		//Neterminat - butoane onTouch
		stanga.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(final View v, MotionEvent event) {	

				switch (event.getAction() & MotionEvent.ACTION_MASK) {

			    case MotionEvent.ACTION_DOWN:
			        v.setPressed(true);
			        	// Start action ...
			        	
			        Thread closeActivity = new Thread(new Runnable() {
			        	  @Override
			        	  public void run() {
			        	    try {
			        	    while (v.isPressed()) {
			        	      Thread.sleep(200);
			        	      // Do some stuff
			        	      goLeft();
			        	    }
			        	    } catch (Exception e) {
			        	      e.getLocalizedMessage();
			        	    }
			        	  }
			        	});
			        
			        closeActivity.start();
			        break;
			    case MotionEvent.ACTION_UP:
			    case MotionEvent.ACTION_OUTSIDE:
			        v.setPressed(false);
			        // Stop action ...
			        break;
			    case MotionEvent.ACTION_POINTER_DOWN:
			    	v.setPressed(false);
			        break;
			    case MotionEvent.ACTION_POINTER_UP:
			    	v.setPressed(false);
			        break;
			    case MotionEvent.ACTION_MOVE:
			        break;
			    }

				return true;
			}
		});
		
		jos.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(final View v, MotionEvent event) {	

				switch (event.getAction() & MotionEvent.ACTION_MASK) {

			    case MotionEvent.ACTION_DOWN:
			        v.setPressed(true);
			        	// Start action ...
			        	
			        Thread closeActivity = new Thread(new Runnable() {
			        	  @Override
			        	  public void run() {
			        	    try {
			        	    while (v.isPressed()) {
			        	      Thread.sleep(200);
			        	      // Do some stuff
			        	      goDown();
			        	    }
			        	    } catch (Exception e) {
			        	      e.getLocalizedMessage();
			        	    }
			        	  }
			        	});
			        
			        closeActivity.start();
			        break;
			    case MotionEvent.ACTION_UP:
			    case MotionEvent.ACTION_OUTSIDE:
			        v.setPressed(false);
			        // Stop action ...
			        break;
			    case MotionEvent.ACTION_POINTER_DOWN:
			        break;
			    case MotionEvent.ACTION_POINTER_UP:
			        break;
			    case MotionEvent.ACTION_MOVE:
			        break;
			    }

				return true;
			}
		});
		
		dreapta.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(final View v, MotionEvent event) {	

				switch (event.getAction() & MotionEvent.ACTION_MASK) {

			    case MotionEvent.ACTION_DOWN:
			        v.setPressed(true);
			        	// Start action ...
			        	
			        Thread closeActivity = new Thread(new Runnable() {
			        	  @Override
			        	  public void run() {
			        	    try {
			        	    while (v.isPressed()) {
			        	      Thread.sleep(200);
			        	      // Do some stuff
			        	      goRight();
			        	    }
			        	    } catch (Exception e) {
			        	      e.getLocalizedMessage();
			        	    }
			        	  }
			        	});
			        
			        closeActivity.start();
			        break;
			    case MotionEvent.ACTION_UP:
			    case MotionEvent.ACTION_OUTSIDE:
			        v.setPressed(false);
			        // Stop action ...
			        break;
			    case MotionEvent.ACTION_POINTER_DOWN:
			        break;
			    case MotionEvent.ACTION_POINTER_UP:
			        break;
			    case MotionEvent.ACTION_MOVE:
			        break;
			    }

				return true;
			}
		});	
		
		
		sus.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(final View v, MotionEvent event) {	

				switch (event.getAction() & MotionEvent.ACTION_MASK) {

			    case MotionEvent.ACTION_DOWN:
			        v.setPressed(true);
			        	// Start action ...
			        	
			        Thread closeActivity = new Thread(new Runnable() {
			        	  @Override
			        	  public void run() {
			        	    try {
			        	    while (v.isPressed()) {
			        	      Thread.sleep(200);
			        	      // Do some stuff
			        	      goUp();
			        	    }
			        	    } catch (Exception e) {
			        	      e.getLocalizedMessage();
			        	    }
			        	  }
			        	});
			        
			        closeActivity.start();
			        break;
			    case MotionEvent.ACTION_UP:
			    case MotionEvent.ACTION_OUTSIDE:
			        v.setPressed(false);
			        // Stop action ...
			        break;
			    case MotionEvent.ACTION_POINTER_DOWN:
			        break;
			    case MotionEvent.ACTION_POINTER_UP:
			        break;
			    case MotionEvent.ACTION_MOVE:
			        break;
			    }

				return true;
			}
		});		
*/
	}


	public void goLeft() {
		
		if (MazeMatr[ vert ][ oriz-1 ] == 0) {
			MazeMatr[ vert ][ oriz ] = 0;
			MazeMatr[ vert ][ oriz-1 ] = 2;
			oriz = oriz - 1;
			directie = 3;
		} else if (MazeMatr[ vert ][ oriz-1 ] == 3) {	
			GameOver();
		}
		printMatr();	
		
	}
	
	public void goRight() {
		
		if (MazeMatr[ vert ][ oriz+1 ] == 0) {
			MazeMatr[ vert ][ oriz ] = 0;
			MazeMatr[ vert ][ oriz+1 ] = 2;
			oriz = oriz + 1;
			directie = 1;
		} else if (MazeMatr[ vert ][ oriz+1 ] == 3) {
			GameOver();			
		}
		printMatr();

	}
	
	public void goDown() {
		if (MazeMatr[ vert+1 ][ oriz ] == 0) {
			MazeMatr[ vert ][ oriz ] = 0;
			MazeMatr[ vert+1 ][ oriz ] = 2;
			vert = vert + 1;
			directie = 2;
		} else if (MazeMatr[ vert+1 ][ oriz ] == 3) {
			GameOver();
		}
		printMatr();
	}
	
	public void goUp() {
		
		if (MazeMatr[ vert-1 ][ oriz ] == 0) {
			MazeMatr[ vert ][ oriz ] = 0;
			MazeMatr[ vert-1 ][ oriz ] = 2;
			vert = vert - 1;
			directie = 0;
		} else if (MazeMatr[ vert-1 ][ oriz ] == 3) {
			GameOver();
		}
		printMatr();

	}
	
	public void GameOver() {

		timeSwapBuff += timeInMilliseconds;
		customHandler.removeCallbacks(updateTimerThread);
		showDialog(1);
		
	}
	
	
	public void printMatr() {
		Canvas canvas = holder.lockCanvas();
		int extrax = 0;
		int extray = 0;
		
		//Cazuri pentru vizibilitate partiala
		//Limite verticale/orizontale inferioare si superioare
		int limv1, limv2, limo1, limo2;
		
		//Verticala
		if ( vert <= (nrviz/2) ) {
			limv1 = 0;
			limv2 = nrviz;
		} else if ( vert >= maxheight - (nrviz/2 + 1) ) {
			limv1 = maxheight - nrviz;
			limv2 = maxheight; 
		} else {
			limv1 = vert - (nrviz/2);
			limv2 = vert + (nrviz/2) + 1;
		}
		
		//Orizontala
		if ( oriz <= (nrviz/2) ) {
			limo1 = 0;
			limo2 = nrviz;
		} else if ( oriz >= maxwidth - (nrviz/2 + 1) ) {
			limo1 = maxwidth - nrviz;
			limo2 = maxwidth; 
		} else {
			limo1 = oriz - (nrviz/2);
			limo2 = oriz + (nrviz/2) + 1;
		}
		
		for (int i=limv1; i<limv2; i++) {
			for (int j=limo1; j<limo2; j++) {
				if (MazeMatr[i][j] == 0) {
					canvas.drawBitmap(path, extrax, extray, paint);
					extrax += dim_img; 
				}
				if (MazeMatr[i][j] == 1) {
					canvas.drawBitmap(wall, extrax, extray, paint);
					extrax += dim_img; 
				}
				if (MazeMatr[i][j] == 2) {
					switch (directie) {
					case 0:
							canvas.drawBitmap(cup, extrax, extray, paint);
							break;
					case 1:
							canvas.drawBitmap(cright, extrax, extray, paint);
							break;
					case 2:
							canvas.drawBitmap(cdown, extrax, extray, paint);
							break;
					default:
							canvas.drawBitmap(cleft, extrax, extray, paint);
							break;
					}
					extrax += dim_img; 
				}
				if (MazeMatr[i][j] == 3) {
					canvas.drawBitmap(gameover, extrax, extray, paint);
					extrax += dim_img; 
				}
			}
			extrax = 0;
			extray += dim_img;
		}
		holder.unlockCanvasAndPost(canvas);
	}
	

    public Dialog onCreateDialog(int id) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Good Game!\n" + "Time: " + mins + ":" + secs)
               .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent raspuns = new Intent ();
                	   setResult (0, raspuns);
                	   finish();
			           System.exit(0);
                   }
               })
               .setNegativeButton("Next", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	// cream obiectul de raspuns
                	   Intent raspuns = new Intent ();

                	   switch (level) {
                	   case 1:
                		   setResult (2, raspuns);
                		   break;
                	   case 2:
                		   setResult (3, raspuns);
                		   break;
                	   case 3:
                		   setResult (4, raspuns);
                		   break;
                	   default:
                		   setResult (0, raspuns);
                		   break;
                	   }

                	   // inchidem formularul
                	   finish ();
                   }
               });

        return builder.create();
    }


    private Runnable updateTimerThread = new Runnable() {
    		 
    		        public void run() {
    		 
    		            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
    		 
    		            updatedTime = timeSwapBuff + timeInMilliseconds;
    		 
    		            secs = (int) (updatedTime / 1000);
    		            mins = secs / 60;
    		            secs = secs % 60;
    		            timerValue.setText("" + mins + ":"
    		                    + String.format("%02d", secs));
    		            customHandler.postDelayed(this, 0);
    		        }
    		 
    		    };
    	
}
