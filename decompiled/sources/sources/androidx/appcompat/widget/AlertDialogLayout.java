package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(Context r1) {
        super(r1);
    }

    public AlertDialogLayout(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    private void forceUniformWidth(int r10, int r11) {
        int r4 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30);
        int r0 = 0;
    L3:
        if (r0 >= r10) goto L11;
        View r3 = this.getChildAt(r0);
        if (r3.getVisibility() == 8) goto L9;
        LinearLayoutCompat.LayoutParams r1 = (LinearLayoutCompat.LayoutParams) r3.getLayoutParams();
        if (((LinearLayout.LayoutParams) r1).width != (-1)) goto L9;
        int r8 = ((LinearLayout.LayoutParams) r1).height;
        ((LinearLayout.LayoutParams) r1).height = r3.getMeasuredHeight();
        AlertDialogLayout r2 = this;
        int r6 = r11;
        r2.measureChildWithMargins(r3, r4, 0, r6, 0);
        ((LinearLayout.LayoutParams) r1).height = r8;
    L10:
        r0 = r0 + 1;
        AlertDialogLayout this = r2;
        r11 = r6;
    L9:
        r2 = this;
        r6 = r11;
        goto L10
    }

    private static int resolveMinimumHeight(View r3) {
        int r0 = ViewCompat.getMinimumHeight(r3);
        if (r0 <= 0) goto L6;
        return r0;
    L6:
        if ((r3 instanceof ViewGroup) == false) goto L11;
        ViewGroup r32 = (ViewGroup) r3;
        if (r32.getChildCount() != 1) goto L11;
        return resolveMinimumHeight(r32.getChildAt(0));
    L11:
        return 0;
    }

    private void setChildFrame(View r1, int r2, int r3, int r4, int r5) {
        r1.layout(r2, r3, r4 + r2, r5 + r3);
    }

    private boolean tryOnMeasure(int r17, int r18) {
        int r3 = getChildCount();
        View r4 = null;
        View r6 = null;
        View r7 = null;
        int r8 = 0;
    L4:
        if (r8 >= r3) goto L24;
        View r10 = getChildAt(r8);
        if (r10.getVisibility() == 8) goto L23;
        int r9 = r10.getId();
        if (r9 != R$id.topPanel) goto L12;
        r4 = r10;
        goto L23
    L12:
        if (r9 != R$id.buttonPanel) goto L15;
        r6 = r10;
        goto L23
    L15:
        if (r9 != R$id.contentPanel) goto L17;
    L20:
        if (r7 != null) goto L21;
        r7 = r10;
        goto L23
    L21:
        return false;
    L17:
        if (r9 == R$id.customPanel) goto L20;
        return false;
    L23:
        r8 = r8 + 1;
        goto L4
    L24:
        int r82 = View.MeasureSpec.getMode(r18);
        int r102 = View.MeasureSpec.getSize(r18);
        int r11 = View.MeasureSpec.getMode(r17);
        int r12 = getPaddingTop() + getPaddingBottom();
        if (r4 == null) goto L27;
        r4.measure(r17, 0);
        r12 = r12 + r4.getMeasuredHeight();
        int r42 = View.combineMeasuredStates(0, r4.getMeasuredState());
    L28:
        if (r6 == null) goto L30;
        r6.measure(r17, 0);
        int r13 = resolveMinimumHeight(r6);
        int r14 = r6.getMeasuredHeight() - r13;
        r12 = r12 + r13;
        r42 = View.combineMeasuredStates(r42, r6.getMeasuredState());
    L31:
        if (r7 == null) goto L36;
        if (r82 != 0) goto L34;
        int r15 = 0;
    L35:
        r7.measure(r17, r15);
        int r152 = r7.getMeasuredHeight();
        r12 = r12 + r152;
        r42 = View.combineMeasuredStates(r42, r7.getMeasuredState());
    L37:
        int r103 = r102 - r12;
        if (r6 == null) goto L43;
        int r122 = r12 - r13;
        int r142 = Math.min(r103, r14);
        if (r142 <= 0) goto L42;
        r103 = r103 - r142;
        r13 = r13 + r142;
    L42:
        r6.measure(r17, View.MeasureSpec.makeMeasureSpec(r13, TLObject.FLAG_30));
        r12 = r122 + r6.getMeasuredHeight();
        r42 = View.combineMeasuredStates(r42, r6.getMeasuredState());
    L43:
        if (r7 == null) goto L46;
        if (r103 <= 0) goto L46;
        r7.measure(r17, View.MeasureSpec.makeMeasureSpec(r152 + r103, r82));
        r12 = (r12 - r152) + r7.getMeasuredHeight();
        r42 = View.combineMeasuredStates(r42, r7.getMeasuredState());
    L46:
        int r62 = 0;
        int r72 = 0;
    L47:
        if (r62 >= r3) goto L52;
        View r83 = getChildAt(r62);
        if (r83.getVisibility() == 8) goto L51;
        r72 = Math.max(r72, r83.getMeasuredWidth());
    L51:
        r62 = r62 + 1;
        goto L47
    L52:
        setMeasuredDimension(View.resolveSizeAndState(r72 + (getPaddingLeft() + getPaddingRight()), r17, r42), View.resolveSizeAndState(r12, r18, 0));
        if (r11 == 1073741824) goto L67;
        forceUniformWidth(r3, r18);
        return true;
    L67:
        return true;
    L34:
        r15 = View.MeasureSpec.makeMeasureSpec(Math.max(0, r102 - r12), r82);
        goto L35
    L36:
        r152 = 0;
        goto L37
    L30:
        r13 = 0;
        r14 = 0;
        goto L31
    L27:
        r42 = 0;
        goto L28
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        int r112 = getPaddingLeft();
        int r142 = r14 - r12;
        int r122 = r142 - getPaddingRight();
        int r143 = (r142 - r112) - getPaddingRight();
        int r0 = getMeasuredHeight();
        int r1 = getChildCount();
        int r2 = getGravity();
        int r3 = r2 & 112;
        int r22 = r2 & 8388615;
        if (r3 != 16) goto L5;
        int r132 = getPaddingTop() + (((r15 - r13) - r0) / 2);
    L9:
        Drawable r152 = getDividerDrawable();
        int r02 = 0;
        if (r152 != null) goto L12;
        int r153 = 0;
    L13:
        if (r02 >= r1) goto L36;
        View r4 = this.getChildAt(r02);
        if (r4 != null) goto L17;
    L34:
        AlertDialogLayout r32 = this;
    L35:
        r02 = r02 + 1;
        AlertDialogLayout this = r32;
        goto L13
    L17:
        if (r4.getVisibility() == 8) goto L34;
        int r7 = r4.getMeasuredWidth();
        int r8 = r4.getMeasuredHeight();
        LinearLayoutCompat.LayoutParams r9 = (LinearLayoutCompat.LayoutParams) r4.getLayoutParams();
        int r33 = ((LinearLayout.LayoutParams) r9).gravity;
        if (r33 >= 0) goto L21;
        r33 = r22;
    L21:
        int r34 = GravityCompat.getAbsoluteGravity(r33, this.getLayoutDirection()) & 7;
        if (r34 != 1) goto L24;
        int r35 = (((r143 - r7) / 2) + r112) + ((LinearLayout.LayoutParams) r9).leftMargin;
        int r5 = ((LinearLayout.LayoutParams) r9).rightMargin;
    L28:
        int r36 = r35 - r5;
    L26:
        int r52 = r36;
        if (this.hasDividerBeforeChildAt(r02) == false) goto L33;
        r132 = r132 + r153;
    L33:
        int r6 = r132 + ((LinearLayout.LayoutParams) r9).topMargin;
        r32 = this;
        r32.setChildFrame(r4, r52, r6, r7, r8);
        r132 = r6 + (r8 + ((LinearLayout.LayoutParams) r9).bottomMargin);
        goto L35
    L24:
        if (r34 == 5) goto L27;
        r36 = ((LinearLayout.LayoutParams) r9).leftMargin + r112;
        goto L26
    L27:
        r35 = r122 - r7;
        r5 = ((LinearLayout.LayoutParams) r9).rightMargin;
        goto L28
    L36:
        return;
    L12:
        r153 = r152.getIntrinsicHeight();
        goto L13
    L5:
        if (r3 == 80) goto L7;
        r132 = getPaddingTop();
        goto L9
    L7:
        r132 = ((getPaddingTop() + r15) - r13) - r0;
        goto L9
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int r2, int r3) {
        if (tryOnMeasure(r2, r3) == true) goto L6;
        super.onMeasure(r2, r3);
        return;
    }
}
