package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class BetterRatingView extends View {
    private Bitmap filledStar;
    private Bitmap hollowStar;
    private OnRatingChangeListener listener;
    private int numStars;
    private Paint paint;
    private int selectedRating;

    public interface OnRatingChangeListener {
        void onRatingChanged(int r1);
    }

    public BetterRatingView(Context r2) {
        super(r2);
        this.paint = new Paint();
        this.numStars = 5;
        this.selectedRating = 0;
        this.filledStar = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rating_star_filled).extractAlpha();
        this.hollowStar = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rating_star).extractAlpha();
    }

    public int getRating() {
        return this.selectedRating;
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.numStars) goto L14;
        Paint r1 = this.paint;
        if (r0 >= this.selectedRating) goto L8;
        int r2 = Theme.key_dialogTextBlue;
    L9:
        r1.setColor(Theme.getColor(r2));
        if (r0 >= this.selectedRating) goto L12;
        Bitmap r12 = this.filledStar;
    L13:
        r6.drawBitmap(r12, AndroidUtilities.dp(48.0f) * r0, 0.0f, this.paint);
        r0 = r0 + 1;
        goto L4
    L12:
        r12 = this.hollowStar;
        goto L13
    L8:
        r2 = Theme.key_dialogTextHint;
        goto L9
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        setMeasuredDimension((this.numStars * AndroidUtilities.dp(32.0f)) + ((this.numStars - 1) * AndroidUtilities.dp(16.0f)), AndroidUtilities.dp(32.0f));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        float r0 = AndroidUtilities.dp(-8.0f);
        int r1 = 0;
    L4:
        if (r1 >= this.numStars) goto L23;
        if (r6.getX() <= r0) goto L15;
        if (r6.getX() >= (AndroidUtilities.dp(48.0f) + r0)) goto L15;
        int r4 = r1 + 1;
        if (this.selectedRating == r4) goto L15;
        this.selectedRating = r4;
        OnRatingChangeListener r62 = this.listener;
        if (r62 == null) goto L14;
        r62.onRatingChanged(r4);
    L14:
        invalidate();
        return true;
    L15:
        r0 = r0 + AndroidUtilities.dp(48.0f);
        r1 = r1 + 1;
        goto L4
    L23:
        return true;
    }

    public void setOnRatingChangeListener(OnRatingChangeListener r1) {
        this.listener = r1;
    }
}
