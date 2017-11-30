package com.example.laktionov.githubsearcher.custom;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {

    private static final String CIRCLE = "circle";
    public static final int IMAGE_SIZE = 48;

    @Override
    public Bitmap transform(Bitmap source) {
        int x = (source.getWidth() - IMAGE_SIZE) / 2;
        int y = (source.getHeight() - IMAGE_SIZE) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, IMAGE_SIZE, IMAGE_SIZE);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(IMAGE_SIZE, IMAGE_SIZE, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float radius = IMAGE_SIZE / 2f;
        canvas.drawCircle(radius, radius, radius, paint);
        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return CIRCLE;
    }
}
