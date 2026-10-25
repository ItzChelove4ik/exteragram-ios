package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import androidx.appcompat.R$attr;

/* loaded from: classes3.dex */
public class AppCompatRatingBar extends RatingBar {
    private final AppCompatProgressBarHelper mAppCompatProgressBarHelper;

    public AppCompatRatingBar(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.ratingBarStyle);
    }

    public AppCompatRatingBar(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatProgressBarHelper r12 = new AppCompatProgressBarHelper(this);
        this.mAppCompatProgressBarHelper = r12;
        r12.loadFromAttributes(r2, r3);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int r2, int r3) {
        monitor-enter(this);
        super.onMeasure(r2, r3);     // Catch: Throwable -> L7
        Bitmap r32 = this.mAppCompatProgressBarHelper.getSampleTile();     // Catch: Throwable -> L7
        if (r32 == null) goto L9;
        setMeasuredDimension(View.resolveSizeAndState(r32.getWidth() * getNumStars(), r2, 0), getMeasuredHeight());     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
