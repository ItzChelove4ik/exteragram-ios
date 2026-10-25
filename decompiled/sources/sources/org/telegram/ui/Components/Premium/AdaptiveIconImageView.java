package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.preferences.utils.IconShapeHelper;
import java.util.HashMap;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class AdaptiveIconImageView extends ImageView {
    private static final Map<Integer, Drawable.ConstantState> drawableStateCache = null;
    private static final Map<String, Path> shapeCache = null;
    private int backgroundOuterPadding;
    private Drawable foreground;
    Path iconPath;
    private boolean loading;
    private int outerPadding;
    Path outlinePath;
    private final Paint placeholderPaint;

    static {
        shapeCache = new HashMap();
        drawableStateCache = new HashMap();
    }

    public AdaptiveIconImageView(Context r2) {
        super(r2);
        Paint r22 = new Paint(1);
        this.placeholderPaint = r22;
        this.iconPath = new Path();
        this.outlinePath = new Path();
        this.outerPadding = AndroidUtilities.dp(5.0f);
        this.backgroundOuterPadding = AndroidUtilities.dp(42.0f);
        r22.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_switchTrack), 85));
    }

    private Drawable loadDrawable(int r3) {
        Map<Integer, Drawable.ConstantState> r2 = drawableStateCache;
        monitor-enter(r2);
        Drawable.ConstantState r0 = r2.get(Integer.valueOf(r3));     // Catch: Throwable -> L24
        monitor-exit(r2);     // Catch: Throwable -> L24
        if (r0 != null) goto L8;
        Drawable r02 = ContextCompat.getDrawable(ApplicationLoader.applicationContext, r3);
        if (r02 != null) goto L13;
        return null;
    L13:
        Drawable.ConstantState r1 = r02.getConstantState();
        if (r1 == null) goto L23;
        monitor-enter(r2);
        r2.put(Integer.valueOf(r3), r1);     // Catch: Throwable -> L20
        monitor-exit(r2);     // Catch: Throwable -> L20
        return r1.newDrawable(ApplicationLoader.applicationContext.getResources()).mutate();
    L20:
        th = move-exception;
        throw th;
    L23:
        return r02;
    L8:
        return r0.newDrawable(ApplicationLoader.applicationContext.getResources()).mutate();
    L24:
        th = move-exception;
        throw th;
    }

    private void updatePath() {
        this.iconPath.rewind();
        this.outlinePath.rewind();
        float r0 = getWidth() / AndroidUtilities.density;
        if (r0 > 0.0f) goto L5;
        return;
    L5:
        float r1 = r0 - ((getPaddingLeft() / AndroidUtilities.density) * 2.0f);
        float r3 = r0 - 4.0f;
        float r4 = (r1 / r0) * 16.0f;
        float r02 = (r3 / r0) * 16.0f;
        String r5 = "i_" + r1 + "_" + r4;
        Map<String, Path> r7 = shapeCache;
        Path r8 = r7.get(r5);
        if (r8 != null) goto L8;
        r8 = IconShapeHelper.INSTANCE.getFinalIconShapePath(r1, r1, r4);
        r7.put(r5, r8);
    L8:
        String r12 = "o_" + r3 + "_" + r02;
        Path r42 = r7.get(r12);
        if (r42 != null) goto L11;
        r42 = IconShapeHelper.INSTANCE.getFinalIconShapePath(r3, r3, r02);
        r7.put(r12, r42);
    L11:
        Matrix r03 = new Matrix();
        r03.setTranslate(getPaddingLeft(), getPaddingTop());
        r8.transform(r03, this.iconPath);
        r03.setTranslate(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        r42.transform(r03, this.outlinePath);
    }

    @Override // android.view.View
    public void draw(Canvas r7) {
        if (this.iconPath.isEmpty() == true) goto L5;
        r7.clipPath(this.iconPath);
    L5:
        r7.save();
        r7.scale((this.backgroundOuterPadding / getWidth()) + 1.0f, (this.backgroundOuterPadding / getHeight()) + 1.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        super.draw(r7);
        r7.restore();
        if (this.loading == true) goto L8;
    L10:
        Drawable r0 = this.foreground;
        if (r0 == null) goto L14;
        int r1 = this.outerPadding;
        r0.setBounds(-r1, -r1, getWidth() + this.outerPadding, getHeight() + this.outerPadding);
        this.foreground.draw(r7);
        return;
    L14:
        return;
    L8:
        if (this.iconPath.isEmpty() == true) goto L10;
        int r02 = Theme.key_switchTrack;
        this.placeholderPaint.setColor(ColorUtils.blendARGB(ColorUtils.setAlphaComponent(Theme.getColor(r02), 56), ColorUtils.setAlphaComponent(Theme.getColor(r02), 114), (((float) Math.sin((((SystemClock.uptimeMillis() % 900) / 900.0f) * 3.141592653589793d) * 2.0d)) * 0.5f) + 0.5f));
        r7.drawPath(this.iconPath, this.placeholderPaint);
        postInvalidateOnAnimation();
        goto L10
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        updatePath();
    }

    public void setBackgroundOuterPadding(int r1) {
        this.backgroundOuterPadding = r1;
    }

    public void setForeground(int r1) {
        this.foreground = loadDrawable(r1);
        invalidate();
    }

    public void setOuterPadding(int r1) {
        this.outerPadding = r1;
    }

    public void setPadding(int r1) {
        setPadding(r1, r1, r1, r1);
    }
}
