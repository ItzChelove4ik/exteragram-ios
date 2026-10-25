package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class ContentFrameLayout extends FrameLayout {
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context r2) {
        this(r2, null);
    }

    public ContentFrameLayout(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ContentFrameLayout(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.mDecorPadding = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor != null) goto L6;
        this.mFixedHeightMajor = new TypedValue();
    L6:
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor != null) goto L6;
        this.mFixedHeightMinor = new TypedValue();
    L6:
        return this.mFixedHeightMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor != null) goto L6;
        this.mFixedWidthMajor = new TypedValue();
    L6:
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor != null) goto L6;
        this.mFixedWidthMinor = new TypedValue();
    L6:
        return this.mFixedWidthMinor;
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor != null) goto L6;
        this.mMinWidthMajor = new TypedValue();
    L6:
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor != null) goto L6;
        this.mMinWidthMinor = new TypedValue();
    L6:
        return this.mMinWidthMinor;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        OnAttachListener r0 = this.mAttachListener;
        if (r0 == null) goto L6;
        r0.onAttachedFromWindow();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OnAttachListener r0 = this.mAttachListener;
        if (r0 == null) goto L6;
        r0.onDetachedFromWindow();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r14, int r15) {
        DisplayMetrics r0 = getContext().getResources().getDisplayMetrics();
        boolean r3 = true;
        if (r0.widthPixels >= r0.heightPixels) goto L5;
        boolean r1 = true;
    L6:
        int r2 = View.MeasureSpec.getMode(r14);
        int r5 = View.MeasureSpec.getMode(r15);
        if (r2 != Integer.MIN_VALUE) goto L22;
        if (r1 == false) goto L10;
        TypedValue r10 = this.mFixedWidthMinor;
    L11:
        if (r10 == null) goto L22;
        int r11 = r10.type;
        if (r11 == 0) goto L22;
        if (r11 != 5) goto L17;
        float r102 = r10.getDimension(r0);
    L16:
        int r103 = (int) r102;
    L20:
        if (r103 <= 0) goto L22;
        Rect r112 = this.mDecorPadding;
        r14 = View.MeasureSpec.makeMeasureSpec(Math.min(r103 - (r112.left + r112.right), View.MeasureSpec.getSize(r14)), TLObject.FLAG_30);
        boolean r104 = true;
    L23:
        if (r5 != Integer.MIN_VALUE) goto L38;
        if (r1 == false) goto L26;
        TypedValue r52 = this.mFixedHeightMajor;
    L27:
        if (r52 == null) goto L38;
        int r113 = r52.type;
        if (r113 == 0) goto L38;
        if (r113 != 5) goto L33;
        float r53 = r52.getDimension(r0);
    L32:
        int r54 = (int) r53;
    L36:
        if (r54 <= 0) goto L38;
        Rect r114 = this.mDecorPadding;
        r15 = View.MeasureSpec.makeMeasureSpec(Math.min(r54 - (r114.top + r114.bottom), View.MeasureSpec.getSize(r15)), TLObject.FLAG_30);
        goto L38
    L33:
        if (r113 != 6) goto L35;
        int r115 = r0.heightPixels;
        r53 = r52.getFraction(r115, r115);
        goto L32
    L35:
        r54 = 0;
        goto L36
    L26:
        r52 = this.mFixedHeightMinor;
    L38:
        super.onMeasure(r14, r15);
        int r142 = getMeasuredWidth();
        int r55 = View.MeasureSpec.makeMeasureSpec(r142, TLObject.FLAG_30);
        if (r104 == true) goto L57;
        if (r2 != Integer.MIN_VALUE) goto L57;
        if (r1 == false) goto L43;
        TypedValue r12 = this.mMinWidthMinor;
    L44:
        if (r12 == null) goto L57;
        int r22 = r12.type;
        if (r22 == 0) goto L57;
        if (r22 != 5) goto L50;
        float r02 = r12.getDimension(r0);
    L49:
        int r03 = (int) r02;
    L53:
        if (r03 <= 0) goto L55;
        Rect r13 = this.mDecorPadding;
        r03 = r03 - (r13.left + r13.right);
    L55:
        if (r142 >= r03) goto L57;
        r55 = View.MeasureSpec.makeMeasureSpec(r03, TLObject.FLAG_30);
    L58:
        if (r3 == false) goto L61;
        super.onMeasure(r55, r15);
        return;
    L61:
        return;
    L50:
        if (r22 != 6) goto L52;
        int r04 = r0.widthPixels;
        r02 = r12.getFraction(r04, r04);
        goto L49
    L52:
        r03 = 0;
        goto L53
    L43:
        r12 = this.mMinWidthMajor;
    L57:
        r3 = false;
        goto L58
    L17:
        if (r11 != 6) goto L19;
        int r116 = r0.widthPixels;
        r102 = r10.getFraction(r116, r116);
        goto L16
    L19:
        r103 = 0;
        goto L20
    L10:
        r10 = this.mFixedWidthMajor;
    L22:
        r104 = false;
        goto L23
    L5:
        r1 = false;
        goto L6
    }

    public void setAttachListener(OnAttachListener r1) {
        this.mAttachListener = r1;
    }

    public void setDecorPadding(int r2, int r3, int r4, int r5) {
        this.mDecorPadding.set(r2, r3, r4, r5);
        if (isLaidOut() == false) goto L6;
        requestLayout();
        return;
    }
}
