package androidx.mediarouter.app;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatSeekBar;

/* loaded from: classes4.dex */
class MediaRouteVolumeSlider extends AppCompatSeekBar {
    private static final String TAG = "MediaRouteVolumeSlider";
    private int mBackgroundColor;
    private final float mDisabledAlpha;
    private boolean mHideThumb;
    private int mProgressAndThumbColor;
    private Drawable mThumb;

    public MediaRouteVolumeSlider(Context r2) {
        this(r2, null);
    }

    public MediaRouteVolumeSlider(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.seekBarStyle);
    }

    public MediaRouteVolumeSlider(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(r1);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (isEnabled() == false) goto L5;
        int r0 = 255;
    L6:
        Drawable r1 = this.mThumb;
        int r2 = this.mProgressAndThumbColor;
        PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
        r1.setColorFilter(r2, r3);
        this.mThumb.setAlpha(r0);
        Drawable r12 = getProgressDrawable();
        if ((r12 instanceof LayerDrawable) == false) goto L9;
        LayerDrawable r13 = (LayerDrawable) getProgressDrawable();
        Drawable r22 = r13.findDrawableByLayerId(R.id.progress);
        r13.findDrawableByLayerId(R.id.background).setColorFilter(this.mBackgroundColor, r3);
        r12 = r22;
    L9:
        r12.setColorFilter(this.mProgressAndThumbColor, r3);
        r12.setAlpha(r0);
        return;
    L5:
        r0 = (int) (this.mDisabledAlpha * 255.0f);
        goto L6
    }

    public void setColor(int r1) {
        setColor(r1, r1);
    }

    public void setColor(int r5, int r6) {
        if (this.mProgressAndThumbColor == r5) goto L9;
        if (Color.alpha(r5) == 255) goto L7;
        Log.e(TAG, "Volume slider progress and thumb color cannot be translucent: #" + Integer.toHexString(r5));
    L7:
        this.mProgressAndThumbColor = r5;
    L9:
        if (this.mBackgroundColor != r6) goto L11;
        return;
    L11:
        if (Color.alpha(r6) == 255) goto L13;
        Log.e(TAG, "Volume slider background color cannot be translucent: #" + Integer.toHexString(r6));
    L13:
        this.mBackgroundColor = r6;
    }

    public void setHideThumb(boolean r2) {
        if (this.mHideThumb != r2) goto L5;
        return;
    L5:
        this.mHideThumb = r2;
        if (r2 == false) goto L8;
        Drawable r22 = null;
    L9:
        super.setThumb(r22);
        return;
    L8:
        r22 = this.mThumb;
        goto L9
    }

    @Override // android.widget.AbsSeekBar
    public void setThumb(Drawable r2) {
        this.mThumb = r2;
        if (this.mHideThumb == false) goto L5;
        r2 = null;
    L5:
        super.setThumb(r2);
    }
}
