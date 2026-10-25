package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.R$styleable;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.ref.WeakReference;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class ViewStubCompat extends View {
    private int mInflatedId;
    private WeakReference<View> mInflatedViewRef;
    private LayoutInflater mInflater;
    private int mLayoutResource;

    public interface OnInflateListener {
    }

    public ViewStubCompat(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ViewStubCompat(Context r3, AttributeSet r4, int r5) {
        super(r3, r4, r5);
        this.mLayoutResource = 0;
        TypedArray r32 = r3.obtainStyledAttributes(r4, R$styleable.ViewStubCompat, r5, 0);
        this.mInflatedId = r32.getResourceId(R$styleable.ViewStubCompat_android_inflatedId, -1);
        this.mLayoutResource = r32.getResourceId(R$styleable.ViewStubCompat_android_layout, 0);
        setId(r32.getResourceId(R$styleable.ViewStubCompat_android_id, -1));
        r32.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r1) {
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas r1) {
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public View inflate() {
        ViewParent r0 = getParent();
        if ((r0 instanceof ViewGroup) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("ViewStub must have a non-null ViewGroup viewParent");
        return null;
    L5:
        if (this.mLayoutResource == 0) goto L19;
        ViewGroup r02 = (ViewGroup) r0;
        LayoutInflater r1 = this.mInflater;
        if (r1 != null) goto L10;
        r1 = LayoutInflater.from(getContext());
    L10:
        View r12 = r1.inflate(this.mLayoutResource, r02, false);
        int r2 = this.mInflatedId;
        if (r2 == (-1)) goto L13;
        r12.setId(r2);
    L13:
        int r22 = r02.indexOfChild(this);
        r02.removeViewInLayout(this);
        ViewGroup.LayoutParams r3 = getLayoutParams();
        if (r3 == null) goto L16;
        r02.addView(r12, r22, r3);
    L17:
        this.mInflatedViewRef = new WeakReference(r12);
        return r12;
    L16:
        r02.addView(r12, r22);
        goto L17
    L19:
        f$$ExternalSyntheticBUOutline1.m("ViewStub must have a valid layoutResource");
        return null;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int r1) {
        this.mInflatedId = r1;
    }

    public void setLayoutInflater(LayoutInflater r1) {
        this.mInflater = r1;
    }

    public void setLayoutResource(int r1) {
        this.mLayoutResource = r1;
    }

    public void setOnInflateListener(OnInflateListener r1) {
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        WeakReference<View> r0 = this.mInflatedViewRef;
        if (r0 == null) goto L10;
        View r1 = r0.get();
        if (r1 == null) goto L8;
        r1.setVisibility(r2);
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("setVisibility called on un-referenced view");
        return;
    L10:
        super.setVisibility(r2);
        if (r2 != 0) goto L13;
    L16:
        inflate();
        return;
    L13:
        if (r2 == 4) goto L16;
    }
}
