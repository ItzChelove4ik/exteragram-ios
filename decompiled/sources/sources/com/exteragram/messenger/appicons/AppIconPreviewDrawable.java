package com.exteragram.messenger.appicons;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.exteragram.messenger.appicons.AppIconPreviewLoader;
import com.exteragram.messenger.preferences.utils.IconShapeHelper;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes4.dex */
public class AppIconPreviewDrawable extends Drawable implements AppIconPreviewLoader.Callback {
    private Bitmap bitmap;
    private final AppIcon icon;
    private final Matrix matrix;
    private final Paint paint;
    private final Path path;
    private BitmapShader shader;
    private final int size;

    public AppIconPreviewDrawable(AppIcon r5, int r6) {
        this.paint = new Paint(3);
        this.matrix = new Matrix();
        Path r0 = new Path();
        this.path = r0;
        this.icon = r5;
        this.size = r6;
        float r1 = r6 / AndroidUtilities.density;
        r0.set(IconShapeHelper.INSTANCE.getFinalIconShapePath(r1, r1, 0.28f * r1));
        Bitmap r02 = AppIconPreviewLoader.getCached(r5, r6);
        if (r02 == null) goto L6;
        this.bitmap = r02;
        return;
    L6:
        AppIconPreviewLoader.load(r5, r6, this);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        if (this.bitmap != null) goto L6;
        return;
    L6:
        if (this.shader != null) goto L8;
        Bitmap r1 = this.bitmap;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        BitmapShader r0 = new BitmapShader(r1, r2, r2);
        this.shader = r0;
        this.paint.setShader(r0);
    L8:
        Rect r02 = getBounds();
        r5.save();
        r5.translate(r02.left, r02.top);
        this.matrix.setScale(this.size / this.bitmap.getWidth(), this.size / this.bitmap.getHeight());
        this.shader.setLocalMatrix(this.matrix);
        r5.drawPath(this.path, this.paint);
        r5.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.exteragram.messenger.appicons.AppIconPreviewLoader.Callback
    public void onPreviewReady(AppIcon r2, int r3, Bitmap r4) {
        if (this.icon == r2) goto L5;
        return;
    L5:
        if (this.size != r3) goto L10;
        this.bitmap = r4;
        this.shader = null;
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.paint.setAlpha(r2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
