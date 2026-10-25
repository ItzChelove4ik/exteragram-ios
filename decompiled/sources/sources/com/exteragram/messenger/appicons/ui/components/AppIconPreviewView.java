package com.exteragram.messenger.appicons.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.appicons.AppIcon;
import com.exteragram.messenger.appicons.AppIconPreviewLoader;
import com.exteragram.messenger.preferences.utils.IconShapeHelper;
import java.util.HashMap;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
public class AppIconPreviewView extends View implements AppIconPreviewLoader.Callback {
    private static final Map<String, Path> shapeCache = null;
    private Bitmap bitmap;
    private float bitmapAlpha;
    private boolean bitmapIsExact;
    private final Paint bitmapPaint;
    private BitmapShader bitmapShader;
    private boolean crossfade;
    private AppIcon icon;
    private final Rect iconBounds;
    private final Path iconPath;
    private final Paint placeholderPaint;
    private Runnable previewReadyListener;
    private int requestedSize;
    private final Theme.ResourcesProvider resourcesProvider;
    private float scale;
    private final Matrix shaderMatrix;
    private Bitmap underlayBitmap;
    private final Paint underlayPaint;
    private BitmapShader underlayShader;

    static {
        shapeCache = new HashMap();
    }

    public AppIconPreviewView(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.bitmapPaint = new Paint(3);
        this.underlayPaint = new Paint(3);
        this.placeholderPaint = new Paint(1);
        this.iconPath = new Path();
        this.iconBounds = new Rect();
        this.shaderMatrix = new Matrix();
        this.scale = 1.0f;
        this.resourcesProvider = r3;
    }

    private void drawBitmap(Canvas r4, Bitmap r5, BitmapShader r6, Paint r7, int r8) {
        Matrix r0 = this.shaderMatrix;
        Rect r1 = this.iconBounds;
        r0.setTranslate(r1.left, r1.top);
        this.shaderMatrix.preScale(this.iconBounds.width() / r5.getWidth(), this.iconBounds.height() / r5.getHeight());
        r6.setLocalMatrix(this.shaderMatrix);
        r7.setAlpha(r8);
        r4.drawPath(this.iconPath, r7);
    }

    private void requestPreview() {
        if (this.icon != null) goto L5;
        return;
    L5:
        if (getWidth() <= 0) goto L23;
        int r0 = getWidth() - (AndroidUtilities.dp(5.0f) * 2);
        if (r0 <= 0) goto L24;
        this.requestedSize = r0;
        Bitmap r1 = AppIconPreviewLoader.getCached(this.icon, r0);
        if (r1 == null) goto L16;
        setContent(r1, true);
        Runnable r3 = this.previewReadyListener;
        if (r3 == null) goto L22;
        r3.run();
        return;
    L22:
        return;
    L16:
        Bitmap r12 = AppIconPreviewLoader.getAnyCached(this.icon);
        if (r12 == null) goto L19;
        setContent(r12, false);
    L19:
        AppIconPreviewLoader.load(this.icon, r0, this);
        return;
    L24:
        return;
    }

    private void setContent(Bitmap r2, boolean r3) {
        Bitmap r0 = this.bitmap;
        if (r0 != r2) goto L6;
        this.bitmapIsExact |= r3;
        return;
    L6:
        if (r0 != null) goto L8;
    L11:
        boolean r02 = false;
    L12:
        this.bitmap = r2;
        this.bitmapShader = null;
        this.bitmapIsExact = r3;
        if (r02 == false) goto L15;
        return;
    L15:
        if (this.crossfade == true) goto L20;
        if (r3 == false) goto L20;
        if (this.underlayBitmap != null) goto L20;
        float r22 = 1.0f;
    L21:
        this.bitmapAlpha = r22;
        return;
    L20:
        r22 = 0.0f;
        goto L21
    L8:
        if (this.bitmapIsExact == true) goto L11;
        if (r3 == false) goto L11;
        r02 = true;
        goto L12
    }

    private Path shape(float r4) {
        String r3 = r4 + "_" + AndroidUtilities.density + "_" + ExteraConfig.getUseSystemIconShape();
        Map<String, Path> r0 = shapeCache;
        Path r1 = r0.get(r3);
        if (r1 != null) goto L6;
        Path r42 = IconShapeHelper.INSTANCE.getFinalIconShapePath(r4, r4, 0.28f * r4);
        r0.put(r3, r42);
        return r42;
    L6:
        return r1;
    }

    private void updatePaths() {
        this.iconPath.rewind();
        if (getWidth() <= 0) goto L10;
        float r0 = (getWidth() / AndroidUtilities.density) - 10.0f;
        if (r0 > 0.0f) goto L8;
        return;
    L8:
        Matrix r1 = new Matrix();
        r1.setTranslate(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        shape(r0).transform(r1, this.iconPath);
        this.iconBounds.set(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), getWidth() - AndroidUtilities.dp(5.0f), getHeight() - AndroidUtilities.dp(5.0f));
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        if (this.iconPath.isEmpty() == false) goto L6;
        return;
    L6:
        if (this.scale == 1.0f) goto L9;
        boolean r2 = true;
    L8:
        boolean r7 = r2;
        if (r7 == false) goto L13;
        r11.save();
        float r22 = this.scale;
        r11.scale(r22, r22, getWidth() / 2.0f, getHeight() / 2.0f);
    L13:
        if (this.bitmap == null) goto L18;
        float r23 = this.bitmapAlpha;
        if (r23 >= 1.0f) goto L18;
        this.bitmapAlpha = Math.min(1.0f, r23 + 0.07272727f);
        invalidate();
    L18:
        if (this.underlayBitmap == null) goto L26;
        if (this.bitmapAlpha >= 1.0f) goto L26;
        if (this.underlayShader != null) goto L24;
        Bitmap r3 = this.underlayBitmap;
        Shader.TileMode r4 = Shader.TileMode.CLAMP;
        BitmapShader r24 = new BitmapShader(r3, r4, r4);
        this.underlayShader = r24;
        this.underlayPaint.setShader(r24);
    L24:
        drawBitmap(r11, this.underlayBitmap, this.underlayShader, this.underlayPaint, 255);
    L26:
        if (this.bitmap == null) goto L34;
        if (this.bitmapShader != null) goto L30;
        Bitmap r25 = this.bitmap;
        Shader.TileMode r32 = Shader.TileMode.CLAMP;
        BitmapShader r1 = new BitmapShader(r25, r32, r32);
        this.bitmapShader = r1;
        this.bitmapPaint.setShader(r1);
    L30:
        drawBitmap(r11, this.bitmap, this.bitmapShader, this.bitmapPaint, (int) (this.bitmapAlpha * 255.0f));
        if (this.bitmapAlpha < 1.0f) goto L36;
        this.underlayBitmap = null;
        this.underlayShader = null;
    L36:
        if (r7 == false) goto L39;
        r11.restore();
        return;
    L39:
        return;
    L34:
        if (this.underlayBitmap != null) goto L36;
        int r26 = Theme.key_switchTrack;
        this.placeholderPaint.setColor(ColorUtils.blendARGB(ColorUtils.setAlphaComponent(Theme.getColor(r26, this.resourcesProvider), 56), ColorUtils.setAlphaComponent(Theme.getColor(r26, this.resourcesProvider), 114), (((float) Math.sin((((SystemClock.uptimeMillis() % 900) / 900.0f) * 3.141592653589793d) * 2.0d)) * 0.5f) + 0.5f));
        r11.drawPath(this.iconPath, this.placeholderPaint);
        postInvalidateOnAnimation();
        goto L36
    L9:
        r2 = false;
        goto L8
    }

    @Override // com.exteragram.messenger.appicons.AppIconPreviewLoader.Callback
    public void onPreviewReady(AppIcon r2, int r3, Bitmap r4) {
        if (this.icon == r2) goto L5;
        return;
    L5:
        if (this.requestedSize != r3) goto L13;
        setContent(r4, true);
        invalidate();
        Runnable r1 = this.previewReadyListener;
        if (r1 == null) goto L12;
        r1.run();
        return;
    L12:
        return;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        updatePaths();
        requestPreview();
    }

    public void setCrossfade(boolean r1) {
        this.crossfade = r1;
    }

    public void setIcon(AppIcon r4) {
        if (this.icon != r4) goto L5;
        return;
    L5:
        this.icon = r4;
        if (this.crossfade == false) goto L12;
        Bitmap r42 = this.bitmap;
        if (r42 == null) goto L12;
        if (this.bitmapAlpha < 1.0f) goto L12;
        this.underlayBitmap = r42;
        this.underlayShader = null;
    L12:
        this.bitmap = null;
        this.bitmapShader = null;
        this.bitmapIsExact = false;
        this.bitmapAlpha = 0.0f;
        requestPreview();
        invalidate();
    }

    public void setOnPreviewReady(Runnable r1) {
        this.previewReadyListener = r1;
    }

    public void setPreviewScale(float r2) {
        if (this.scale != r2) goto L5;
        return;
    L5:
        this.scale = r2;
        invalidate();
    }
}
