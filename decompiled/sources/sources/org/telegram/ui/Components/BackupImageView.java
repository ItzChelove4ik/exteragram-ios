package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.SecureDocument;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes3.dex */
public class BackupImageView extends View {
    public AnimatedEmojiDrawable animatedEmojiDrawable;
    public ColorFilter animatedEmojiDrawableColorFilter;
    public boolean applyAttach;
    boolean attached;
    private AvatarDrawable avatarDrawable;
    protected boolean blurAllowed;
    protected ImageReceiver blurImageReceiver;
    public Text blurText;
    private ColorFilter blurTextBgColorFilter;
    private Path blurTextClipPath;
    public boolean drawFromStart;
    protected boolean hasBlur;
    protected int height;
    protected ImageReceiver imageReceiver;
    ValueAnimator roundRadiusAnimator;
    protected int width;

    /* renamed from: org.telegram.ui.Components.BackupImageView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ BackupImageView this$0;

        public AnonymousClass1(BackupImageView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.this$0.setRoundRadius(((Integer) r1.getAnimatedValue()).intValue());
        }
    }

    /* renamed from: $r8$lambda$m2c-sR_7zkVVg2XgQkPoTqrkliA, reason: not valid java name */
    public static /* synthetic */ void m8989$r8$lambda$m2csR_7zkVVg2XgQkPoTqrkliA(BackupImageView r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public BackupImageView(Context r3) {
        super(r3);
        this.width = -1;
        this.height = -1;
        this.applyAttach = true;
        ImageReceiver r0 = createImageReciever();
        this.imageReceiver = r0;
        r0.setCrossfadeByScale(0.0f);
        this.imageReceiver.setAllowLoadingOnAttachedOnly(true);
        this.imageReceiver.setDelegate(new BackupImageView$$ExternalSyntheticLambda0(this));
    }

    private void checkCreateBlurredImage() {
        if (this.hasBlur == true) goto L5;
        return;
    L5:
        if (this.blurImageReceiver.getBitmap() == null) goto L7;
        return;
    L7:
        if (this.imageReceiver.getBitmap() == null) goto L16;
        Bitmap r0 = this.imageReceiver.getBitmap();
        if (r0 != null) goto L11;
        return;
    L11:
        if (r0.isRecycled() == true) goto L18;
        this.blurImageReceiver.setImageBitmap(Utilities.stackBlurBitmapMax(r0));
        invalidate();
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$new$0(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        if (r2 == false) goto L6;
        if (r3 == true) goto L7;
        checkCreateBlurredImage();
        return;
    L7:
        return;
    }

    public void animateToRoundRadius(int r3) {
        if (getRoundRadius()[0] == r3) goto L9;
        ValueAnimator r0 = this.roundRadiusAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        ValueAnimator r32 = ValueAnimator.ofInt(new int[]{getRoundRadius()[0], r3});
        this.roundRadiusAnimator = r32;
        r32.addUpdateListener(new AnonymousClass1(this));
        this.roundRadiusAnimator.setDuration(200);
        this.roundRadiusAnimator.start();
        return;
    }

    public void clearImage() {
        this.imageReceiver.clearImage();
    }

    public ImageReceiver createImageReciever() {
        return new ImageReceiver(this);
    }

    public void drawBlurredText(Canvas r12, float r13) {
        if (this.blurText != null) goto L5;
        return;
    L5:
        Path r0 = this.blurTextClipPath;
        if (r0 != null) goto L8;
        this.blurTextClipPath = new Path();
    L9:
        int r02 = this.width;
        if (r02 == (-1)) goto L14;
        int r2 = this.height;
        if (r2 == (-1)) goto L14;
        float r03 = r02;
        float r1 = r2;
    L15:
        float r22 = this.blurText.getCurrentWidth() + AndroidUtilities.dp(18.0f);
        float r3 = AndroidUtilities.dp(28.0f);
        float r04 = (r03 - r22) / 2.0f;
        float r8 = r1 / 2.0f;
        RectF r14 = AndroidUtilities.rectTmp;
        float r32 = r3 / 2.0f;
        r14.set(r04, r8 - r32, r22 + r04, r8 + r32);
        this.blurTextClipPath.addRoundRect(r14, r32, r32, Path.Direction.CW);
        r12.save();
        r12.clipPath(this.blurTextClipPath);
        ImageReceiver r15 = this.blurImageReceiver;
        if (r15 != null) goto L18;
    L20:
        this.blurText.draw(r12, r04 + AndroidUtilities.dp(9.0f), r8, -1, r13);
        r12.restore();
        return;
    L18:
        if (this.blurAllowed == false) goto L20;
        r15.setColorFilter(this.blurTextBgColorFilter);
        float r16 = this.blurImageReceiver.getAlpha();
        this.blurImageReceiver.setAlpha(r13);
        this.blurImageReceiver.draw(r12);
        this.blurImageReceiver.setAlpha(r16);
        this.blurImageReceiver.setColorFilter(null);
    L14:
        r03 = getMeasuredWidth();
        r1 = getMeasuredHeight();
        goto L15
    L8:
        r0.rewind();
        goto L9
    }

    public AnimatedEmojiDrawable getAnimatedEmojiDrawable() {
        return this.animatedEmojiDrawable;
    }

    public AvatarDrawable getAvatarDrawable() {
        if (this.avatarDrawable != null) goto L6;
        this.avatarDrawable = new AvatarDrawable();
    L6:
        return this.avatarDrawable;
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    public int[] getRoundRadius() {
        return this.imageReceiver.getRoundRadius();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        if (this.applyAttach == false) goto L6;
        this.imageReceiver.onAttachedToWindow();
    L6:
        if (this.blurAllowed == false) goto L8;
        this.blurImageReceiver.onAttachedToWindow();
    L8:
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L12;
        r0.addView(this);
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        if (this.applyAttach == false) goto L6;
        this.imageReceiver.onDetachedFromWindow();
    L6:
        if (this.blurAllowed == false) goto L8;
        this.blurImageReceiver.onDetachedFromWindow();
    L8:
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L12;
        r0.removeView(this);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L5;
        ImageReceiver r02 = r0.getImageReceiver();
    L6:
        if (r02 == null) goto L33;
        AnimatedEmojiDrawable r1 = this.animatedEmojiDrawable;
        if (r1 == null) goto L13;
        ColorFilter r2 = this.animatedEmojiDrawableColorFilter;
        if (r2 == null) goto L13;
        r1.setColorFilter(r2);
    L13:
        int r12 = this.width;
        if (r12 == (-1)) goto L25;
        int r4 = this.height;
        if (r4 == (-1)) goto L25;
        if (this.drawFromStart == false) goto L22;
        r02.setImageCoords(0.0f, 0.0f, r12, r4);
        if (this.blurAllowed == false) goto L28;
        this.blurImageReceiver.setImageCoords(0.0f, 0.0f, this.width, this.height);
    L28:
        r02.draw(r7);
        if (this.blurAllowed == false) goto L32;
        this.blurImageReceiver.draw(r7);
        return;
    L32:
        return;
    L22:
        float r13 = (getWidth() - this.width) / 2;
        int r22 = getHeight();
        r02.setImageCoords(r13, (r22 - r3) / 2, this.width, this.height);
        if (this.blurAllowed == false) goto L28;
        ImageReceiver r14 = this.blurImageReceiver;
        float r23 = (getWidth() - this.width) / 2;
        int r3 = getHeight();
        r14.setImageCoords(r23, (r3 - r4) / 2, this.width, this.height);
    L25:
        r02.setImageCoords(0.0f, 0.0f, getWidth(), getHeight());
        if (this.blurAllowed == false) goto L28;
        this.blurImageReceiver.setImageCoords(0.0f, 0.0f, getWidth(), getHeight());
        goto L28
    L33:
        return;
    L5:
        r02 = this.imageReceiver;
        goto L6
    }

    public void onNewImageSet() {
        if (this.hasBlur == true) goto L5;
        return;
    L5:
        if (this.blurImageReceiver.getBitmap() != null) goto L7;
    L9:
        this.blurImageReceiver.setImageBitmap(null);
        checkCreateBlurredImage();
        return;
    L7:
        if (this.blurImageReceiver.getBitmap().isRecycled() == true) goto L9;
        this.blurImageReceiver.getBitmap().recycle();
        goto L9
    }

    public void setAnimatedEmojiDrawable(AnimatedEmojiDrawable r3) {
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 != r3) goto L6;
        return;
    L6:
        if (this.attached == false) goto L9;
        if (r0 == null) goto L9;
        r0.removeView(this);
    L9:
        this.animatedEmojiDrawable = r3;
        if (this.attached == false) goto L13;
        if (r3 == null) goto L13;
        r3.addView(this);
    L13:
        invalidate();
    }

    public void setAspectFit(boolean r1) {
        this.imageReceiver.setAspectFit(r1);
    }

    public void setBlurAllowed(boolean r2) {
        if (this.attached == true) goto L8;
        this.blurAllowed = r2;
        if (r2 == false) goto L10;
        this.blurImageReceiver = new ImageReceiver();
        return;
    L10:
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("You should call setBlurAllowed(...) only when detached!");
    }

    public void setBlurredText(CharSequence r4) {
        if (TextUtils.isEmpty(r4) == false) goto L6;
        this.blurText = null;
        return;
    L6:
        this.blurText = new Text(r4, 16.5f, AndroidUtilities.bold());
        if (this.blurTextBgColorFilter != null) goto L10;
        ColorMatrix r42 = new ColorMatrix();
        r42.setSaturation(1.2f);
        AndroidUtilities.adjustBrightnessColorMatrix(r42, -0.2f);
        this.blurTextBgColorFilter = new ColorMatrixColorFilter(r42);
        return;
    }

    public void setColorFilter(ColorFilter r1) {
        this.imageReceiver.setColorFilter(r1);
    }

    public void setEmojiColorFilter(ColorFilter r1) {
        this.animatedEmojiDrawableColorFilter = r1;
        invalidate();
    }

    public void setForUserOrChat(TLObject r2, AvatarDrawable r3) {
        this.imageReceiver.setForUserOrChat(r2, r3);
        onNewImageSet();
    }

    public void setForUserOrChat(TLObject r2, AvatarDrawable r3, Object r4) {
        this.imageReceiver.setForUserOrChat(r2, r3, r4);
        onNewImageSet();
    }

    public void setHasBlur(boolean r2) {
        if (r2 == true) goto L4;
    L8:
        this.hasBlur = r2;
        if (r2 == false) goto L11;
    L16:
        checkCreateBlurredImage();
        return;
    L11:
        if (this.blurImageReceiver.getBitmap() != null) goto L13;
    L15:
        this.blurImageReceiver.setImageBitmap(null);
        goto L16
    L13:
        if (this.blurImageReceiver.getBitmap().isRecycled() == true) goto L15;
        this.blurImageReceiver.getBitmap().recycle();
        goto L15
    L4:
        if (this.blurAllowed == true) goto L8;
        Segment$$ExternalSyntheticBUOutline1.m("You should call setBlurAllowed(...) before calling setHasBlur(true)!");
    }

    public void setImage(String r11, String r12, Drawable r13) {
        setImage(ImageLocation.getForPath(r11), r12, null, null, r13, null, null, 0, null);
    }

    public void setImage(String r11, String r12, String r13, String r14) {
        setImage(ImageLocation.getForPath(r11), r12, ImageLocation.getForPath(r13), r14, null, null, null, 0, null);
    }

    public void setImage(ImageLocation r11, String r12, Drawable r13, int r14, Object r15) {
        setImage(r11, r12, null, null, r13, null, null, r14, r15);
    }

    public void setImage(ImageLocation r11, String r12, Drawable r13, Object r14) {
        setImage(r11, r12, null, null, r13, null, null, 0, r14);
    }

    public void setImage(ImageLocation r11, String r12, String r13, Drawable r14, Object r15) {
        setImage(r11, r12, null, null, r14, null, r13, 0, r15);
    }

    public void setImage(ImageLocation r11, String r12, ImageLocation r13, String r14, int r15, Object r16) {
        setImage(r11, r12, r13, r14, null, null, null, r15, r16);
    }

    public void setImage(ImageLocation r15, String r16, ImageLocation r17, String r18, Drawable r19, Bitmap r20, String r21, int r22, Object r23) {
        if (r20 == null) goto L5;
        Drawable r8 = new BitmapDrawable(null, r20);
    L6:
        this.imageReceiver.setImage(r15, r16, r17, r18, r8, r22, r21, r23, 0);
        onNewImageSet();
        return;
    L5:
        r8 = r19;
        goto L6
    }

    public void setImage(ImageLocation r14, String r15, ImageLocation r16, String r17, Drawable r18, Object r19) {
        this.imageReceiver.setImage(r14, r15, r16, r17, null, null, r18, 0, null, r19, 1);
        onNewImageSet();
    }

    public void setImage(ImageLocation r12, String r13, ImageLocation r14, String r15, Drawable r16, String r17, long r18, int r20, Object r21) {
        this.imageReceiver.setImage(r12, r13, r14, r15, r16, r18, r17, r21, r20);
        onNewImageSet();
    }

    public void setImage(ImageLocation r12, String r13, ImageLocation r14, String r15, String r16, long r17, int r19, Object r20) {
        this.imageReceiver.setImage(r12, r13, r14, r15, null, r17, r16, r20, r19);
        onNewImageSet();
    }

    public void setImage(ImageLocation r14, String r15, ImageLocation r16, String r17, ImageLocation r18, String r19, Drawable r20, Object r21) {
        this.imageReceiver.setImage(r14, r15, r16, r17, r18, r19, r20, 0, null, r21, 1);
        onNewImageSet();
    }

    public void setImage(SecureDocument r11, String r12) {
        setImage(ImageLocation.getForSecureDocument(r11), r12, null, null, null, null, null, 0, null);
    }

    public void setImageBitmap(Bitmap r2) {
        this.imageReceiver.setImageBitmap(r2);
        onNewImageSet();
    }

    public void setImageDrawable(Drawable r2) {
        this.imageReceiver.setImageBitmap(r2);
        onNewImageSet();
    }

    public void setImageMedia(ImageLocation r17, String r18, ImageLocation r19, String r20, Bitmap r21, int r22, int r23, Object r24) {
        if (r21 == null) goto L6;
        BitmapDrawable r10 = new BitmapDrawable(null, r21);
    L7:
        this.imageReceiver.setImage(r17, r18, r19, r20, null, null, r10, r22, null, r24, r23);
        onNewImageSet();
        return;
    L6:
        r10 = null;
        goto L7
    }

    public void setImageMedia(VectorAvatarThumbDrawable r14, ImageLocation r15, String r16, ImageLocation r17, String r18, ImageLocation r19, String r20, String r21, int r22, int r23, Object r24) {
        ImageReceiver r0 = this.imageReceiver;
        if (r14 == null) goto L5;
        r0.setImageBitmap(r14);
    L6:
        onNewImageSet();
        return;
    L5:
        r0.setImage(r15, r16, r17, r18, r19, r20, null, r22, r21, r24, r23);
        goto L6
    }

    public void setImageResource(int r2) {
        Drawable r22 = getResources().getDrawable(r2);
        this.imageReceiver.setImageBitmap(r22);
        invalidate();
        onNewImageSet();
    }

    public void setImageResource(int r3, int r4) {
        Drawable r32 = getResources().getDrawable(r3);
        if (r32 == null) goto L5;
        r32.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
    L5:
        this.imageReceiver.setImageBitmap(r32);
        invalidate();
        onNewImageSet();
    }

    public void setLayerNum(int r1) {
        this.imageReceiver.setLayerNum(r1);
    }

    public void setOrientation(int r1, int r2, boolean r3) {
        this.imageReceiver.setOrientation(r1, r2, r3);
    }

    public void setOrientation(int r1, boolean r2) {
        this.imageReceiver.setOrientation(r1, r2);
    }

    public void setRoundRadius(int r2) {
        this.imageReceiver.setRoundRadius(r2);
        if (this.blurAllowed == false) goto L5;
        this.blurImageReceiver.setRoundRadius(r2);
    L5:
        invalidate();
    }

    public void setRoundRadius(int r2, int r3, int r4, int r5) {
        this.imageReceiver.setRoundRadius(r2, r3, r4, r5);
        if (this.blurAllowed == false) goto L5;
        this.blurImageReceiver.setRoundRadius(r2, r3, r4, r5);
    L5:
        invalidate();
    }

    public void setSize(int r1, int r2) {
        this.width = r1;
        this.height = r2;
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.imageReceiver.getDrawable()) goto L5;
        return true;
    L5:
        if (r2 != this.imageReceiver.getImageDrawable()) goto L7;
        return true;
    L7:
        if (super.verifyDrawable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
