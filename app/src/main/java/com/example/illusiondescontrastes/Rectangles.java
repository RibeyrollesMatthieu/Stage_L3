package com.example.illusiondescontrastes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class Rectangles extends View {
	private final int SCREEN_WIDTH;
	private final int SCREEN_HEIGHT;
	private final int SIZE;

	private Paint paint;

	private int rightSquareAlpha;

	private float generateRandomColor() {
		Random rnd = new Random(  );

		return rnd.nextFloat();
	}

	private void drawLeftSquare( Canvas canvas ) {
		final int EXT_LEFT = ( this.SCREEN_WIDTH / 5 * 3 ) - ( 2 * this.SIZE);
		final int EXT_TOP = this.SIZE;

		paint.setColor( Color.WHITE );
		canvas.drawPaint( paint );

		/* outer rectangle */
		paint.setColor( Color.rgb( 150, 150, 150) );
		canvas.drawRect( 0, 0, this.SCREEN_WIDTH / 2, this.SCREEN_HEIGHT - 100, paint );

		/* inner rectangle */
		paint.setColor( Color.argb( 255 / 2, 100, 100, 100 ) );
		canvas.drawRect( EXT_LEFT, EXT_TOP, EXT_LEFT + this.SIZE, EXT_TOP + this.SIZE, paint );
	}

	private void drawRightSquare( Canvas canvas, int alpha ) {
		final int LEFT = ( this.SCREEN_WIDTH / 5 * 2 ) + this.SIZE;
		final int TOP = this.SIZE;

		paint.setColor( Color.argb( alpha, 100, 100, 100 ) );
		canvas.drawRect( LEFT, TOP, LEFT + this.SIZE, TOP + this.SIZE, paint );
	}

	@Override
	public void onDraw( Canvas canvas ) {
		drawLeftSquare( canvas );
		drawRightSquare( canvas, this.rightSquareAlpha );
	}

	public Rectangles( Context ctx, int screen_width, int screen_height, int rightSquareAlpha ) {
		super( ctx );

		this.SIZE = screen_height / 3;
		this.SCREEN_WIDTH = screen_width;
		this.SCREEN_HEIGHT = screen_height;

		this.paint = new Paint();

		this.rightSquareAlpha = rightSquareAlpha;
	}
}
