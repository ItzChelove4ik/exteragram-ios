package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class StickerView extends EntityView {
    private int anchor;
    private Size baseSize;
    public final ImageReceiver centerImage;
    private FrameLayoutDrawer containerView;
    private final AnimatedFloat mirrorT;
    private boolean mirrored;
    private Object parentObject;
    private TLRPC.Document sticker;

    public class FrameLayoutDrawer extends FrameLayout {
        final /* synthetic */ StickerView this$0;

        public FrameLayoutDrawer(StickerView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            this.this$0.stickerDraw(r1);
        }
    }

    public class StickerViewSelectionView extends EntityView.SelectionView {
        private RectF arcRect;
        final /* synthetic */ StickerView this$0;

        public StickerViewSelectionView(StickerView r1, Context r2) {
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
            float r11 = (getMeasuredWidth() / 2) - r10;
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

    /* renamed from: $r8$lambda$-gbYPHfUuQ6vG8h2tRFUyrxOWk4, reason: not valid java name */
    public static /* synthetic */ void m11601$r8$lambda$gbYPHfUuQ6vG8h2tRFUyrxOWk4(StickerView r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public StickerView(Context r9, PointF r10, float r11, float r12, Size r13, TLRPC.Document r14, Object r15) {
        super(r9, r10);
        this.anchor = -1;
        int r0 = 0;
        this.mirrored = false;
        this.centerImage = new ImageReceiver();
        setRotation(r11);
        setScale(r12);
        this.sticker = r14;
        this.baseSize = r13;
        this.parentObject = r15;
    L4:
        if (r0 >= r14.attributes.size()) goto L11;
        TLRPC.DocumentAttribute r112 = r14.attributes.get(r0);
        if ((r112 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        TLRPC.TL_maskCoords r113 = r112.mask_coords;
        if (r113 == null) goto L11;
        this.anchor = r113.n;
    L11:
        FrameLayoutDrawer r114 = new FrameLayoutDrawer(this, r9);
        this.containerView = r114;
        addView(r114, LayoutHelper.createFrame(-1, -1.0f));
        this.mirrorT = new AnimatedFloat(this.containerView, 0, 500, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.centerImage.setAspectFit(true);
        this.centerImage.setInvalidateAll(true);
        this.centerImage.setParentView(this.containerView);
        TLRPC.PhotoSize r92 = FileLoader.getClosestPhotoSizeWithSize(r14.thumbs, 90);
        this.centerImage.setImage(ImageLocation.getForDocument(r14), null, ImageLocation.getForDocument(r92, r14), null, "webp", r15, 1);
        this.centerImage.setDelegate(new StickerView$$ExternalSyntheticLambda0(this));
        updatePosition();
    }

    public StickerView(Context r9, StickerView r10, PointF r11) {
        this(r9, r11, r10.getRotation(), r10.getScale(), r10.baseSize, r10.sticker, r10.parentObject);
        if (r10.mirrored == false) goto L6;
        mirror();
        return;
    }

    private /* synthetic */ void lambda$new$0(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        if (r2 == false) goto L8;
        if (r3 == true) goto L9;
        RLottieDrawable r12 = r1.getLottieAnimation();
        if (r12 == null) goto L10;
        didSetAnimatedSticker(r12);
        return;
    L10:
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public EntityView.SelectionView createSelectionView() {
        return new StickerViewSelectionView(this, getContext());
    }

    public void didSetAnimatedSticker(RLottieDrawable r1) {
    }

    public int getAnchor() {
        return this.anchor;
    }

    public Size getBaseSize() {
        return this.baseSize;
    }

    public long getDuration() {
        RLottieDrawable r0 = this.centerImage.getLottieAnimation();
        if (r0 == null) goto L7;
        return r0.getDuration();
    L7:
        if (this.centerImage.getAnimation() != null) goto L9;
        return 0;
    L9:
        return r2.getDurationMs();
    }

    public Object getParentObject() {
        return this.parentObject;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        float r1 = getMeasuredWidth() * (getScale() + 0.5f);
        float r4 = r1 / 2.0f;
        float r12 = r1 * r02;
        return new RectOld((getPositionX() - r4) * r02, (getPositionY() - r4) * r02, r12, r12);
    L5:
        return new RectOld();
    }

    public TLRPC.Document getSticker() {
        return this.sticker;
    }

    public boolean isMirrored() {
        return this.mirrored;
    }

    public void mirror() {
        mirror(false);
    }

    public void mirror(boolean r3) {
        boolean r0 = !this.mirrored;
        this.mirrored = r0;
        if (r3 == true) goto L5;
        this.mirrorT.set(r0, true);
    L5:
        this.containerView.invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.centerImage.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.centerImage.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) this.baseSize.width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) this.baseSize.height, TLObject.FLAG_30));
    }

    public void stickerDraw(Canvas r6) {
        if (this.containerView != null) goto L5;
        return;
    L5:
        r6.save();
        float r0 = this.mirrorT.set(this.mirrored);
        r6.scale(1.0f - (r0 * 2.0f), 1.0f, this.baseSize.width / 2.0f, 0.0f);
        r6.skew(0.0f, ((4.0f * r0) * (1.0f - r0)) * 0.25f);
        ImageReceiver r02 = this.centerImage;
        Size r2 = this.baseSize;
        r02.setImageCoords(0.0f, 0.0f, (int) r2.width, (int) r2.height);
        this.centerImage.draw(r6);
        r6.restore();
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
