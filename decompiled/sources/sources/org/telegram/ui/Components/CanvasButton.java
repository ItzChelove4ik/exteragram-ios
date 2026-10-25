package org.telegram.ui.Components;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;

/* loaded from: classes3.dex */
public class CanvasButton {
    private static final int[] pressedState = null;
    boolean buttonPressed;
    private Runnable delegate;
    CornerPath drawingPath;
    ArrayList<RectF> drawingRects;
    private boolean longPressEnabled;
    Runnable longPressRunnable;
    Runnable longPressRunnableInner;
    Paint maskPaint;
    Paint paint;
    private final View parent;
    private boolean pathCreated;
    CornerPathEffect pathEffect;
    float roundRadius;
    boolean rounded;
    Drawable selectorDrawable;
    int usingRectCount;

    /* renamed from: org.telegram.ui.Components.CanvasButton$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ CanvasButton this$0;

        public AnonymousClass1(CanvasButton r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.checkTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
            CanvasButton.m9107$$Nest$fgetparent(this.this$0).performHapticFeedback(0);
            Runnable r9 = this.this$0.longPressRunnable;
            if (r9 == null) goto L6;
            r9.run();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.CanvasButton$2, reason: invalid class name */
    public class AnonymousClass2 extends Drawable {
        final /* synthetic */ CanvasButton this$0;
        final /* synthetic */ Paint val$maskPaint2;

        public AnonymousClass2(CanvasButton r1, Paint r2) {
            this.this$0 = r1;
            this.val$maskPaint2 = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            CanvasButton r0 = this.this$0;
            if (r0.usingRectCount <= 1) goto L6;
            CanvasButton.m9108$$Nest$mdrawInternal(r0, r4, r0.maskPaint);
            return;
        L6:
            CanvasButton.m9108$$Nest$mdrawInternal(r0, r4, this.val$maskPaint2);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: -$$Nest$fgetparent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m9107$$Nest$fgetparent(CanvasButton r0) {
        return r0.parent;
    }

    /* renamed from: -$$Nest$mdrawInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9108$$Nest$mdrawInternal(CanvasButton r0, Canvas r1, Paint r2) {
        r0.drawInternal(r1, r2);
    }

    static {
        pressedState = new int[]{R.attr.state_enabled, R.attr.state_pressed};
    }

    public CanvasButton(View r6) {
        this.drawingRects = new ArrayList();
        this.paint = new Paint(1);
        this.longPressRunnableInner = new AnonymousClass1(this);
        this.roundRadius = AndroidUtilities.dp(12.0f);
        this.parent = r6;
        Paint r2 = this.paint;
        CornerPathEffect r3 = new CornerPathEffect(this.roundRadius);
        this.pathEffect = r3;
        r2.setPathEffect(r3);
        Paint r22 = new Paint(1);
        this.maskPaint = r22;
        r22.setFilterBitmap(true);
        this.maskPaint.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(12.0f)));
        this.maskPaint.setColor(-1);
        Paint r0 = new Paint(1);
        r0.setFilterBitmap(true);
        r0.setColor(-1);
        BaseCell.RippleDrawableSafe r23 = new BaseCell.RippleDrawableSafe(new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{Theme.getColor(Theme.key_listSelector) & 436207615}), null, new AnonymousClass2(this, r0));
        this.selectorDrawable = r23;
        r23.setCallback(r6);
    }

    private boolean contains(int r6, int r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.usingRectCount) goto L10;
        if (this.drawingRects.get(r1).contains(r6, r7) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private void drawInternal(Canvas r12, Paint r13) {
        int r0 = this.usingRectCount;
        int r1 = 0;
        if (r0 > 1) goto L5;
        if (r0 != 1) goto L57;
        Drawable r02 = this.selectorDrawable;
        if (r02 == null) goto L48;
        r02.setBounds((int) this.drawingRects.get(0).left, (int) this.drawingRects.get(0).top, (int) this.drawingRects.get(0).right, (int) this.drawingRects.get(0).bottom);
    L48:
        if (this.rounded == false) goto L51;
        r13.setPathEffect(null);
        float r03 = Math.min(this.drawingRects.get(0).width(), this.drawingRects.get(0).height()) / 2.0f;
        r12.drawRoundRect(this.drawingRects.get(0), r03, r03, r13);
        return;
    L51:
        r13.setPathEffect(this.pathEffect);
        r12.drawRoundRect(this.drawingRects.get(0), 0.0f, 0.0f, r13);
        return;
    L57:
        return;
    L5:
        if (this.pathCreated == true) goto L39;
        CornerPath r04 = this.drawingPath;
        if (r04 != null) goto L9;
        this.drawingPath = new CornerPath(2);
    L10:
        int r05 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L11:
        int r6 = this.usingRectCount;
        if (r1 >= r6) goto L38;
        int r7 = r1 + 1;
        if (r7 >= r6) goto L18;
        float r62 = this.drawingRects.get(r1).right;
        float r8 = this.drawingRects.get(r7).right;
        if (Math.abs(r62 - r8) >= AndroidUtilities.dp(4.0f)) goto L18;
        RectF r9 = this.drawingRects.get(r7);
        RectF r10 = this.drawingRects.get(r1);
        float r63 = Math.max(r62, r8);
        r10.right = r63;
        r9.right = r63;
    L18:
        if (r1 != 0) goto L20;
    L21:
        r05 = (int) this.drawingRects.get(r1).bottom;
    L22:
        if (r1 != 0) goto L24;
    L25:
        r3 = (int) this.drawingRects.get(r1).right;
    L26:
        if (r1 != 0) goto L28;
    L29:
        r4 = (int) this.drawingRects.get(r1).left;
    L30:
        if (r1 != 0) goto L32;
    L33:
        r5 = (int) this.drawingRects.get(r1).top;
    L34:
        this.drawingPath.addRect(this.drawingRects.get(r1), Path.Direction.CCW);
        Drawable r14 = this.selectorDrawable;
        if (r14 == null) goto L37;
        r14.setBounds(r4, r5, r3, r05);
    L37:
        r1 = r7;
        goto L11
    L32:
        if (this.drawingRects.get(r1).top >= r5) goto L34;
    L28:
        if (this.drawingRects.get(r1).left >= r4) goto L30;
    L24:
        if (this.drawingRects.get(r1).right <= r3) goto L26;
    L20:
        if (this.drawingRects.get(r1).bottom <= r05) goto L22;
    L38:
        this.drawingPath.closeRects();
        this.pathCreated = true;
        goto L39
    L9:
        r04.rewind();
    L39:
        r13.setPathEffect(this.pathEffect);
        CornerPath r11 = this.drawingPath;
        if (r11 == null) goto L56;
        r12.drawPath(r11, r13);
        return;
    }

    public void addRect(RectF r3) {
        int r0 = this.usingRectCount + 1;
        this.usingRectCount = r0;
        if (r0 <= this.drawingRects.size()) goto L5;
        this.drawingRects.add(new RectF());
    L5:
        this.drawingRects.get(this.usingRectCount - 1).set(r3);
    }

    public void cancelRipple() {
        Drawable r0 = this.selectorDrawable;
        if (r0 == null) goto L6;
        r0.setState(StateSet.NOTHING);
        this.selectorDrawable.jumpToCurrentState();
        return;
    }

    public boolean checkTouchEvent(MotionEvent r6) {
        int r0 = (int) r6.getX();
        int r1 = (int) r6.getY();
        if (r6.getAction() != 0) goto L15;
        if (contains(r0, r1) == false) goto L39;
        this.buttonPressed = true;
        Drawable r62 = this.selectorDrawable;
        if (r62 == null) goto L9;
        r62.setHotspot(r0, r1);
        this.selectorDrawable.setState(pressedState);
    L9:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnableInner);
        if (this.longPressEnabled == false) goto L12;
        AndroidUtilities.runOnUIThread(this.longPressRunnableInner, ViewConfiguration.getLongPressTimeout());
    L12:
        this.parent.invalidate();
        return true;
    L39:
        return this.buttonPressed;
    L15:
        if (r6.getAction() == 1) goto L27;
        if (r6.getAction() == 3) goto L27;
        if (r6.getAction() != 2) goto L39;
        if (this.buttonPressed == false) goto L39;
        Drawable r63 = this.selectorDrawable;
        if (r63 == null) goto L39;
        r63.setHotspot(r0, r1);
    L27:
        if (this.buttonPressed == true) goto L29;
    L37:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnableInner);
        goto L39
    L29:
        if (r6.getAction() != 1) goto L33;
        Runnable r64 = this.delegate;
        if (r64 == null) goto L33;
        r64.run();
    L33:
        this.parent.playSoundEffect(0);
        Drawable r65 = this.selectorDrawable;
        if (r65 == null) goto L36;
        r65.setState(StateSet.NOTHING);
    L36:
        this.buttonPressed = false;
        this.parent.invalidate();
        goto L37
    }

    public void draw(Canvas r2) {
        drawInternal(r2, this.paint);
        Drawable r1 = this.selectorDrawable;
        if (r1 == null) goto L6;
        r1.draw(r2);
        return;
    }

    public void rewind() {
        this.pathCreated = false;
        this.usingRectCount = 0;
    }

    public void setColor(int r1) {
        setColor(r1, r1);
    }

    public void setColor(int r2, int r3) {
        this.paint.setColor(r2);
        Drawable r1 = this.selectorDrawable;
        if (r1 == null) goto L6;
        Theme.setSelectorDrawableColor(r1, r3, true);
        return;
    }

    public void setDelegate(Runnable r1) {
        this.delegate = r1;
    }

    public void setLongPress(Runnable r2) {
        this.longPressEnabled = true;
        this.longPressRunnable = r2;
    }

    public void setRect(int r2, int r3, int r4, int r5) {
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r2, r3, r4, r5);
        setRect(r0);
    }

    public void setRect(RectF r1) {
        rewind();
        addRect(r1);
    }

    public void setRounded(boolean r1) {
        this.rounded = r1;
    }
}
