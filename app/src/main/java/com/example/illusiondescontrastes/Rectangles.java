package com.example.illusiondescontrastes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class Rectangles extends View {
	private final int SCREE_WIDTH;
	private final int SIZE;

	private Paint paint;

	private int generateRandomColor() {
		Random rnd = new Random(  );

		return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
	}

	private void drawLeftSquare( Canvas canvas ) {
		final int EXT_LEFT = ( this.SCREE_WIDTH / 2 ) - ( 2 * this.SIZE);
		final int EXT_TOP = this.SIZE;

		paint.setColor( generateRandomColor() );
		canvas.drawRect( EXT_LEFT, EXT_TOP, EXT_LEFT + this.SIZE, EXT_TOP + this.SIZE, paint );

		paint.setColor( generateRandomColor() );
		canvas.drawRect( EXT_LEFT + 20, EXT_TOP + 20, EXT_LEFT + this.SIZE - 20, EXT_TOP + this.SIZE - 20, paint );
	}

	private void drawRightSquare( Canvas canvas ) {
		final int LEFT = ( this.SCREE_WIDTH / 2 ) + this.SIZE;
		final int TOP = this.SIZE;

		paint.setColor( generateRandomColor() );
		canvas.drawRect( LEFT, TOP, LEFT + this.SIZE - 20, TOP + this.SIZE - 20, paint );
	}

	@Override
	public void onDraw( Canvas canvas ) {
		drawLeftSquare( canvas );
		drawRightSquare( canvas );
	}

	public Rectangles( Context ctx, int screen_width, int screen_height) {
		super( ctx );

		this.SIZE = screen_height / 3;
		this.SCREE_WIDTH = screen_width;

		this.paint = new Paint();

	}
}
