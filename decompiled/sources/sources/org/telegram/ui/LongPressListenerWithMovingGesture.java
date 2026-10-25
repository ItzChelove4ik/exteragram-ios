package org.telegram.ui;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.Components.GestureDetector2;

/* loaded from: classes6.dex */
public abstract class LongPressListenerWithMovingGesture implements View.OnTouchListener {
    GestureDetector2 gestureDetector2;
    private int[] location;
    Rect rect;
    private View selectedMenuView;
    float startFromX;
    float startFromY;
    boolean subItemClicked;
    ActionBarPopupWindow submenu;
    boolean tapConfirmedOrCanceled;
    View view;

    /* renamed from: org.telegram.ui.LongPressListenerWithMovingGesture$1, reason: invalid class name */
    public class AnonymousClass1 implements GestureDetector2.OnGestureListener {
        final /* synthetic */ LongPressListenerWithMovingGesture this$0;

        public AnonymousClass1(LongPressListenerWithMovingGesture r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onDown(MotionEvent r3) {
            View r0 = this.this$0.view;
            if (r0 == null) goto L5;
            r0.setPressed(true);
            this.this$0.view.setSelected(true);
            this.this$0.view.drawableHotspotChanged(r3.getX(), r3.getY());
        L5:
            return true;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onFling(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            return false;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public void onLongPress(MotionEvent r1) {
            LongPressListenerWithMovingGesture r0 = this.this$0;
            if (r0.view == null) goto L6;
            r0.onLongPress();
            return;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
            return false;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public void onShowPress(MotionEvent r1) {
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r3) {
            LongPressListenerWithMovingGesture r32 = this.this$0;
            if (r32.tapConfirmedOrCanceled == false) goto L5;
            return false;
        L5:
            View r33 = r32.view;
            if (r33 == null) goto L9;
            r33.callOnClick();
            this.this$0.tapConfirmedOrCanceled = true;
            return true;
        L9:
            return false;
        }

        @Override // org.telegram.ui.Components.GestureDetector2.OnGestureListener
        public void onUp(MotionEvent r2) {
            View r22 = this.this$0.view;
            if (r22 == null) goto L6;
            r22.setPressed(false);
            this.this$0.view.setSelected(false);
        L6:
            if (LongPressListenerWithMovingGesture.m16023$$Nest$fgetselectedMenuView(this.this$0) == null) goto L11;
            LongPressListenerWithMovingGesture r23 = this.this$0;
            if (r23.subItemClicked == true) goto L12;
            LongPressListenerWithMovingGesture.m16023$$Nest$fgetselectedMenuView(r23).callOnClick();
            this.this$0.subItemClicked = true;
            return;
        L12:
            return;
        }
    }

    /* renamed from: -$$Nest$fgetselectedMenuView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m16023$$Nest$fgetselectedMenuView(LongPressListenerWithMovingGesture r0) {
        return r0.selectedMenuView;
    }

    public LongPressListenerWithMovingGesture() {
        this.rect = new Rect();
        GestureDetector2 r0 = new GestureDetector2(new AnonymousClass1(this));
        this.gestureDetector2 = r0;
        this.location = new int[2];
        r0.setIsLongpressEnabled(true);
    }

    public abstract void onLongPress();

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View r11, MotionEvent r12) {
        this.view = r11;
        if (r12.getAction() != 0) goto L5;
        this.startFromX = r12.getX();
        this.startFromY = r12.getY();
        this.tapConfirmedOrCanceled = false;
    L5:
        this.gestureDetector2.onTouchEvent(r12);
        if (this.submenu == null) goto L24;
        if (this.subItemClicked == true) goto L24;
        if (r12.getAction() != 2) goto L24;
        this.view.getLocationOnScreen(this.location);
        float r112 = r12.getX() + this.location[0];
        float r3 = r12.getY() + this.location[1];
        this.submenu.getContentView().getLocationOnScreen(this.location);
        int[] r4 = this.location;
        float r113 = r112 - r4[0];
        float r32 = r3 - r4[1];
        this.selectedMenuView = null;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r42 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) this.submenu.getContentView();
        int r5 = 0;
    L13:
        if (r5 >= r42.getItemsCount()) goto L24;
        View r6 = r42.getItemAt(r5);
        r6.getHitRect(this.rect);
        r6.getTag();
        if (r6.getVisibility() != 0) goto L22;
        if (r6.isClickable() == false) goto L22;
        if (this.rect.contains((int) r113, (int) r32) == true) goto L21;
        r6.setPressed(false);
        r6.setSelected(false);
        goto L22
    L21:
        r6.setPressed(true);
        r6.setSelected(true);
        r6.drawableHotspotChanged(r113, r32 - r6.getTop());
        this.selectedMenuView = r6;
    L22:
        r5 = r5 + 1;
    L24:
        if (r12.getAction() != 2) goto L28;
        if (Math.abs(r12.getX() - this.startFromX) <= (AndroidUtilities.touchSlop * 2.0f)) goto L28;
    L29:
        this.tapConfirmedOrCanceled = true;
        this.view.setPressed(false);
        this.view.setSelected(false);
    L31:
        if (r12.getAction() == 1) goto L33;
    L44:
        return true;
    L33:
        if (this.subItemClicked == true) goto L44;
        if (this.tapConfirmedOrCanceled == true) goto L44;
        View r114 = this.selectedMenuView;
        if (r114 == null) goto L40;
        r114.callOnClick();
        this.subItemClicked = true;
        goto L44
    L40:
        if (this.submenu != null) goto L44;
        View r10 = this.view;
        if (r10 == null) goto L44;
        r10.callOnClick();
    L28:
        if (Math.abs(r12.getY() - this.startFromY) <= (AndroidUtilities.touchSlop * 2.0f)) goto L31;
        goto L29
    }

    public void setSubmenu(ActionBarPopupWindow r1) {
        this.submenu = r1;
    }
}
