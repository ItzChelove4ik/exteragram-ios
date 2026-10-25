package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class RoundView extends EntityView {

    /* renamed from: a, reason: collision with root package name */
    private float f509a;
    private int anchor;
    private Size baseSize;
    private final Paint clipPaint;
    private final Path clipPath;
    private boolean draw;
    public final Rect dst;
    private final AnimatedFloat mirrorT;
    private boolean mirrored;
    private boolean shown;
    private AnimatedFloat shownT;
    public final Rect src;
    public TextureView textureView;
    private FrameLayout.LayoutParams textureViewParams;
    public Bitmap thumbBitmap;

    public class RoundViewSelectionView extends EntityView.SelectionView {
        private final RectF arcRect;
        final /* synthetic */ RoundView this$0;

        public RoundViewSelectionView(RoundView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.arcRect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            super.onDraw(r14);
            int r8 = r14.getSaveCount();
            float r1 = getShowAlpha();
            if (r1 > 0.0f) goto L6;
            return;
        L6:
            if (r1 >= 1.0f) goto L8;
            r14.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r1 * 255.0f), 31);
        L8:
            float r12 = AndroidUtilities.dp(1.0f);
            float r7 = AndroidUtilities.dpf2(5.66f);
            float r10 = (r12 + r7) + AndroidUtilities.dp(15.0f);
            float r11 = (getMeasuredWidth() / 2.0f) - r10;
            float r122 = r10 + (2.0f * r11);
            this.arcRect.set(r10, r10, r122, r122);
            r14.drawArc(this.arcRect, 0.0f, 180.0f, false, this.paint);
            r14.drawArc(this.arcRect, 180.0f, 180.0f, false, this.paint);
            float r112 = r11 + r10;
            r14.drawCircle(r10, r112, r7, this.dotStrokePaint);
            r14.drawCircle(r10, r112, r7 - AndroidUtilities.dp(1.0f), this.dotPaint);
            r14.drawCircle(r122, r112, r7, this.dotStrokePaint);
            r14.drawCircle(r122, r112, r7 - AndroidUtilities.dp(1.0f), this.dotPaint);
            r14.restoreToCount(r8);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.SelectionView
        public int pointInsideHandle(float r7, float r8) {
            float r0 = AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(19.5f);
            float r02 = r0 + r1;
            float r4 = r02 * 2.0f;
            float r2 = ((getMeasuredHeight() - r4) / 2.0f) + r02;
            if (r7 <= (r02 - r1)) goto L13;
            if (r8 <= (r2 - r1)) goto L13;
            if (r7 >= (r02 + r1)) goto L13;
            if (r8 >= (r2 + r1)) goto L13;
            return 1;
        L13:
            if (r7 <= (((getMeasuredWidth() - r4) + r02) - r1)) goto L23;
            if (r8 <= (r2 - r1)) goto L23;
            if (r7 >= ((r02 + (getMeasuredWidth() - r4)) + r1)) goto L23;
            if (r8 >= (r2 + r1)) goto L23;
            return 2;
        L23:
            if ((Math.pow(r7 - r6, 2.0d) + Math.pow(r8 - r6, 2.0d)) >= Math.pow(getMeasuredWidth() / 2.0f, 2.0d)) goto L26;
            return 3;
        L26:
            return 0;
        }
    }

    public RoundView(Context r12, PointF r13, float r14, float r15, Size r16, String r17) {
        super(r12, r13);
        this.anchor = -1;
        this.mirrored = false;
        Rect r1 = new Rect();
        this.src = r1;
        this.dst = new Rect();
        this.f509a = 1.0f;
        this.clipPath = new Path();
        this.draw = true;
        this.shown = true;
        CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.shownT = new AnimatedFloat(this, 0, 350, r9);
        Paint r3 = new Paint(1);
        this.clipPaint = r3;
        r3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        setRotation(r14);
        setScale(r15);
        this.baseSize = r16;
        Bitmap r142 = BitmapFactory.decodeFile(r17);
        this.thumbBitmap = r142;
        if (r142 == null) goto L5;
        this.f509a = r142.getWidth() / this.thumbBitmap.getHeight();
        r1.set(0, 0, this.thumbBitmap.getWidth(), this.thumbBitmap.getHeight());
    L5:
        TextureView r143 = new TextureView(r12);
        this.textureView = r143;
        FrameLayout.LayoutParams r122 = LayoutHelper.createFrame(-1, -1.0f);
        this.textureViewParams = r122;
        addView(r143, r122);
        this.mirrorT = new AnimatedFloat(this, 0, 500, r9);
        updatePosition();
        setWillNotDraw(false);
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public EntityView.SelectionView createSelectionView() {
        return new RoundViewSelectionView(this, getContext());
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r15, View r16, long r17) {
        if (this.draw == true) goto L6;
        return false;
    L6:
        if (r16 != this.textureView) goto L25;
        r15.save();
        float r1 = this.mirrorT.set(this.mirrored);
        r15.scale(1.0f - (r1 * 2.0f), 1.0f, getMeasuredWidth() / 2.0f, 0.0f);
        r15.skew(0.0f, ((4.0f * r1) * (1.0f - r1)) * 0.25f);
        float r9 = this.shownT.set(this.shown);
        float r10 = r16.getX() + (r16.getWidth() / 2.0f);
        float r11 = r16.getY() + (r16.getHeight() / 2.0f);
        float r12 = Math.min(r16.getWidth() / 2.0f, r16.getHeight() / 2.0f);
        if (r9 >= 1.0f) goto L13;
        r15.saveLayerAlpha(r16.getX(), r16.getY(), r16.getX() + r16.getWidth(), r16.getY() + r16.getHeight(), 128, 31);
        this.clipPath.rewind();
        this.clipPath.addCircle(r10, r11, r12, Path.Direction.CW);
        r15.clipPath(this.clipPath);
        if (this.thumbBitmap == null) goto L12;
        this.dst.set(0, 0, r16.getWidth(), r16.getHeight());
        r15.drawBitmap(this.thumbBitmap, this.src, this.dst, null);
    L12:
        super.drawChild(r15, r16, r17);
        r15.restore();
    L13:
        r15.save();
        this.clipPath.rewind();
        this.clipPath.addCircle(r10, r11, r12 * r9, Path.Direction.CW);
        r15.clipPath(this.clipPath);
        if (this.thumbBitmap == null) goto L17;
        this.dst.set(0, 0, r16.getWidth(), r16.getHeight());
        r15.drawBitmap(this.thumbBitmap, this.src, this.dst, null);
    L17:
        if ((getParent() instanceof EntitiesContainerView) == true) goto L19;
    L21:
        boolean r14 = super.drawChild(r15, r16, r17);
    L22:
        r15.restore();
        r15.restore();
        return r14;
    L19:
        if (((EntitiesContainerView) getParent()).drawForThumb == false) goto L21;
        r14 = true;
        goto L22
    L25:
        return super.drawChild(r15, r16, r17);
    }

    public int getAnchor() {
        return this.anchor;
    }

    public Size getBaseSize() {
        return this.baseSize;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        float r1 = (getMeasuredWidth() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r3 = (getMeasuredHeight() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r2 = (getPositionX() - (r1 / 2.0f)) * r02;
        return new RectOld(r2, (getPositionY() - (r3 / 2.0f)) * r02, ((r1 * r02) + r2) - r2, r3 * r02);
    L5:
        return new RectOld();
    }

    public boolean isMirrored() {
        return this.mirrored;
    }

    public void mirror(boolean r3) {
        boolean r0 = !this.mirrored;
        this.mirrored = r0;
        if (r3 == true) goto L5;
        this.mirrorT.set(r0, true);
    L5:
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        TextureView r12 = this.textureView;
        if (r12 == null) goto L6;
        int r52 = ((r5 - r3) - r12.getMeasuredHeight()) / 2;
        int r42 = ((r4 - r2) - this.textureView.getMeasuredWidth()) / 2;
        TextureView r13 = this.textureView;
        r13.layout(r42, r52, r13.getMeasuredWidth() + r42, this.textureView.getMeasuredHeight() + r52);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        Size r62 = this.baseSize;
        int r72 = (int) r62.width;
        int r63 = (int) r62.height;
        TextureView r0 = this.textureView;
        if (r0 == null) goto L13;
        float r1 = this.f509a;
        if (r1 < 1.0f) goto L7;
        int r12 = (int) (r1 * r63);
    L8:
        int r13 = View.MeasureSpec.makeMeasureSpec(r12, TLObject.FLAG_30);
        float r4 = this.f509a;
        if (r4 < 1.0f) goto L11;
        int r2 = r63;
    L12:
        r0.measure(r13, View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30));
        goto L13
    L11:
        r2 = (int) (r72 / r4);
        goto L12
    L7:
        r12 = r72;
    L13:
        setMeasuredDimension(r72, r63);
    }

    public void resizeTextureView(int r2, int r3) {
        float r22 = r2 / r3;
        if (Math.abs(this.f509a - r22) < 1.0E-4f) goto L6;
        this.f509a = r22;
        requestLayout();
        return;
    }

    public void setDraw(boolean r2) {
        if (this.draw == r2) goto L6;
        this.draw = r2;
        invalidate();
        return;
    }

    public void setShown(boolean r2, boolean r3) {
        if (this.shown == r2) goto L9;
        this.shown = r2;
        if (r3 == true) goto L7;
        this.shownT.set(r2, true);
    L7:
        invalidate();
        return;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public boolean trashCenter() {
        return true;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public void updatePosition() {
        Size r0 = this.baseSize;
        float r1 = r0.width / 2.0f;
        float r02 = r0.height / 2.0f;
        setX(getPositionX() - r1);
        setY(getPositionY() - r02);
        updateSelectionView();
    }
}
