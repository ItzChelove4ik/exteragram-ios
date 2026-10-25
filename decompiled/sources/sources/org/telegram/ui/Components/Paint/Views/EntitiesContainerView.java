package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.zxing.common.detector.MathUtils;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public abstract class EntitiesContainerView extends FrameLayout {
    private boolean cancelled;
    private EntitiesContainerViewDelegate delegate;
    public boolean drawForThumb;
    private boolean hasTransformed;
    private float previousScale;
    private float px;
    private float py;

    public interface EntitiesContainerViewDelegate {
        void onEntityDeselect();

        EntityView onSelectedEntityRequest();
    }

    public EntitiesContainerView(Context r1, EntitiesContainerViewDelegate r2) {
        super(r1);
        this.previousScale = 1.0f;
        this.delegate = r2;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        if (this.drawForThumb == false) goto L9;
        if ((r3 instanceof ReactionWidgetEntityView) == false) goto L9;
        return true;
    L9:
        return super.drawChild(r2, r3, r4);
    }

    public int entitiesCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= getChildCount()) goto L10;
        if ((getChildAt(r0) instanceof EntityView) == false) goto L9;
        r1 = r1 + 1;
    L9:
        r0 = r0 + 1;
        goto L4
    L10:
        return r1;
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View r7, int r8, int r9, int r10, int r11) {
        if ((r7 instanceof TextPaintView) == false) goto L6;
        ViewGroup.MarginLayoutParams r102 = (ViewGroup.MarginLayoutParams) r7.getLayoutParams();
        r7.measure(ViewGroup.getChildMeasureSpec(r8, (((getPaddingLeft() + getPaddingRight()) + r102.leftMargin) + r102.rightMargin) + r9, r102.width), View.MeasureSpec.makeMeasureSpec(0, 0));
        return;
    L6:
        super.measureChildWithMargins(r7, r8, r9, r10, r11);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        EntityView r0 = this.delegate.onSelectedEntityRequest();
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r6.getPointerCount() != 1) goto L29;
        int r2 = r6.getActionMasked();
        if (r2 != 0) goto L11;
        this.hasTransformed = false;
        r0.hasPanned = false;
        r0.hasReleased = false;
        this.px = r6.getX();
        this.py = r6.getY();
        this.cancelled = false;
    L30:
        return true;
    L11:
        if (this.cancelled == false) goto L13;
    L19:
        if (r2 != 1) goto L21;
    L22:
        r0.hasPanned = false;
        r0.hasReleased = true;
        if (this.hasTransformed == true) goto L27;
        EntitiesContainerViewDelegate r62 = this.delegate;
        if (r62 == null) goto L27;
        r62.onEntityDeselect();
    L27:
        invalidate();
        return false;
    L21:
        if (r2 != 3) goto L30;
    L13:
        if (r2 != 2) goto L19;
        float r1 = r6.getX();
        float r63 = r6.getY();
        if (this.hasTransformed == false) goto L17;
    L18:
        this.hasTransformed = true;
        r0.hasPanned = true;
        r0.pan(r1 - this.px, r63 - this.py);
        this.px = r1;
        this.py = r63;
        goto L30
    L17:
        if (MathUtils.distance(r1, r63, this.px, this.py) <= AndroidUtilities.touchSlop) goto L30;
    L29:
        r0.hasPanned = false;
        r0.hasReleased = true;
        this.hasTransformed = false;
        this.cancelled = true;
        invalidate();
        goto L30
    }
}
