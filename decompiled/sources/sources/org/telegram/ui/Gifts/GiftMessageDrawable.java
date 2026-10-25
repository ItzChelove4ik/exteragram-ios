package org.telegram.ui.Gifts;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.blur3.utils.NinePatchBuilder;

/* loaded from: classes7.dex */
public class GiftMessageDrawable extends Drawable {
    private boolean alwaysUseAvatarAnimator;
    private final BoolAnimator animatorAvatarVisible;
    private final AvatarDrawable avatarDrawable;
    private final int avatarLeftPadding;
    private final int avatarRadius;
    private final ImageReceiver avatarReceiver;
    private final int avatarSize;
    private NinePatchDrawable bubble;
    private NinePatchDrawable bubbleBorder;
    private AnimatedEmojiSpan.EmojiGroupedSpans emojiGroupedSpans;
    private final float firstBaselineTop;
    private boolean hasAvatar;
    private final float lastBaselineBottom;
    private int lastMeasuredWidth;
    private int measuredHeight;
    private int measuredWidth;
    private CharSequence message;
    private final int minHeight;
    private View parentView;
    private float textDrawX;
    private float textDrawY;
    private StaticLayout textLayout;
    private final int textPaddingH;
    private final TextPaint textPaint;

    public static /* synthetic */ void $r8$lambda$cmw4D0wk3797vUpjYaIluT9HWww(GiftMessageDrawable r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public GiftMessageDrawable() {
        TextPaint r0 = new TextPaint(1);
        this.textPaint = r0;
        ImageReceiver r1 = new ImageReceiver();
        this.avatarReceiver = r1;
        this.avatarDrawable = new AvatarDrawable();
        int r2 = AndroidUtilities.dp(10.66f);
        this.avatarRadius = r2;
        this.avatarSize = r2 * 2;
        this.avatarLeftPadding = AndroidUtilities.dp(4.0f);
        this.firstBaselineTop = AndroidUtilities.dpf2(15.33f);
        this.lastBaselineBottom = AndroidUtilities.dpf2(7.33f);
        this.textPaddingH = AndroidUtilities.dp(8.0f);
        this.minHeight = (int) AndroidUtilities.dpf2(22.66f);
        this.animatorAvatarVisible = new BoolAnimator(0, new GiftMessageDrawable$$ExternalSyntheticLambda0(this), CubicBezierInterpolator.EASE_OUT_QUINT, 320, true);
        r0.setTextSize(AndroidUtilities.dp(12.0f));
        r0.setColor(-1);
        r1.setRoundRadius(r2);
    }

    private static NinePatchDrawable createBubbleBorderNinePatch(int r18) {
        Drawable r0 = ApplicationLoader.applicationContext.getResources().getDrawable(r18);
        int r1 = r0.getIntrinsicWidth();
        int r2 = r0.getIntrinsicHeight();
        Bitmap r3 = Bitmap.createBitmap(r1, r2, Bitmap.Config.ARGB_8888);
        Canvas r4 = new Canvas(r3);
        r0.setBounds(0, 0, r1, r2);
        r0.draw(r4);
        Paint r9 = new Paint(1);
        float r7 = r1;
        float r8 = r2;
        r9.setShader(new LinearGradient(r7, 0.0f, 0.0f, r8, new int[]{1090519039, -805306369, 1090519039}, null, Shader.TileMode.CLAMP));
        r9.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        r4.drawRect(0.0f, 0.0f, r7, r8, r9);
        int r5 = (r2 * 4) / 144;
        return NinePatchBuilder.createNinePatch(r3, new Rect((r1 * 27) / 168, r5, (r1 * 5) / 168, r5), (r1 * 94) / 168, (r2 * 71) / 144);
    }

    private static NinePatchDrawable createBubbleNinePatch(int r6) {
        Drawable r62 = ApplicationLoader.applicationContext.getResources().getDrawable(r6);
        int r0 = r62.getIntrinsicWidth();
        int r1 = r62.getIntrinsicHeight();
        Bitmap r2 = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r2);
        r62.setBounds(0, 0, r0, r1);
        r62.draw(r3);
        int r4 = (r1 * 4) / 144;
        return NinePatchBuilder.createNinePatch(r2, new Rect((r0 * 27) / 168, r4, (r0 * 5) / 168, r4), (r0 * 94) / 168, (r1 * 71) / 144);
    }

    private void ensureNinePatches() {
        if (this.bubble != null) goto L6;
        this.bubble = createBubbleNinePatch(R.drawable.gift_message_bubble_24);
    L6:
        if (this.bubbleBorder != null) goto L9;
        this.bubbleBorder = createBubbleBorderNinePatch(R.drawable.gift_message_bubble_border_24);
        return;
    }

    private int getTextLeftPadding() {
        if (this.hasAvatar == false) goto L5;
    L8:
        int r0 = this.avatarLeftPadding + this.avatarSize;
    L10:
        return r0 + this.textPaddingH;
    L5:
        if (this.alwaysUseAvatarAnimator == true) goto L8;
        r0 = 0;
        goto L10
    }

    private /* synthetic */ void lambda$new$0(int r1, float r2, float r3, FactorAnimator r4) {
        invalidateSelf();
    }

    public void attach() {
        this.avatarReceiver.onAttachedToWindow();
    }

    public void detach() {
        this.avatarReceiver.onDetachedFromWindow();
        AnimatedEmojiSpan.release(null, this.emojiGroupedSpans);
        this.emojiGroupedSpans = null;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r15) {
        ensureNinePatches();
        Rect r11 = getBounds();
        r15.save();
        float r3 = 1.0f;
        if (this.alwaysUseAvatarAnimator == false) goto L6;
        float r2 = this.animatorAvatarVisible.getFloatValue();
        r15.translate(((-(this.avatarLeftPadding + this.avatarSize)) / 2.0f) * (1.0f - r2), 0.0f);
        float r13 = r2;
    L10:
        NinePatchDrawable r22 = this.bubble;
        int r32 = r11.left;
        if (this.hasAvatar == false) goto L13;
        int r4 = this.avatarLeftPadding + this.avatarSize;
    L14:
        DrawableUtils.setBoundsIncreasePadding(r22, r32 + r4, r11.top, r11.right, r11.bottom);
        this.bubble.draw(r15);
        NinePatchDrawable r23 = this.bubbleBorder;
        int r33 = r11.left;
        if (this.hasAvatar == false) goto L17;
        int r42 = this.avatarLeftPadding + this.avatarSize;
    L18:
        DrawableUtils.setBoundsIncreasePadding(r23, r33 + r42, r11.top, r11.right, r11.bottom);
        this.bubbleBorder.draw(r15);
        if (this.textLayout == null) goto L27;
        r15.save();
        r15.translate(r11.left + this.textDrawX, r11.top + this.textDrawY);
        this.textLayout.draw(r15);
        View r24 = this.parentView;
        if (r24 != null) goto L23;
    L25:
        r15.restore();
        goto L27
    L23:
        if ((this.message instanceof Spanned) == false) goto L25;
        AnimatedEmojiSpan.EmojiGroupedSpans r34 = AnimatedEmojiSpan.update(0, r24, false, this.emojiGroupedSpans, new Layout[]{this.textLayout});
        this.emojiGroupedSpans = r34;
        AnimatedEmojiSpan.drawAnimatedEmojis(r15, this.textLayout, r34, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, null);
    L27:
        if (r13 <= 0.0f) goto L29;
        int r25 = r11.left;
        int r35 = r11.bottom;
        int r43 = this.avatarSize;
        this.avatarReceiver.setImageCoords(r25, r35 - r43, r43, r43);
        r15.save();
        r15.scale(r13, r13, this.avatarReceiver.getCenterX(), this.avatarReceiver.getCenterY());
        this.avatarReceiver.draw(r15);
        r15.restore();
    L29:
        r15.restore();
        return;
    L17:
        r42 = 0;
        goto L18
    L13:
        r4 = 0;
        goto L14
    L6:
        if (this.hasAvatar == true) goto L9;
        r3 = 0.0f;
    L9:
        r13 = r3;
        goto L10
    }

    public int getLineCount() {
        StaticLayout r0 = this.textLayout;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getLineCount();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.measuredHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.measuredWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    public int measure(int r20) {
        ensureNinePatches();
        if (r20 == this.lastMeasuredWidth) goto L5;
    L8:
        this.lastMeasuredWidth = r20;
        int r2 = getTextLeftPadding();
        int r7 = (r20 - r2) - this.textPaddingH;
        if (r7 > 0) goto L11;
    L29:
        this.textLayout = null;
        int r1 = this.minHeight;
        this.measuredWidth = r1;
        this.measuredHeight = r1;
        return r1;
    L11:
        if (TextUtils.isEmpty(this.message) == true) goto L29;
        StaticLayout r4 = new StaticLayout(this.message, this.textPaint, r7, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        int r12 = r4.getLineCount();
        float r3 = 0.0f;
        float r8 = 0.0f;
        int r6 = 0;
    L14:
        if (r6 >= r12) goto L17;
        r8 = Math.max(r8, r4.getLineWidth(r6));
        r6 = r6 + 1;
        goto L14
    L17:
        if (r12 <= 1) goto L27;
        int r14 = (int) Math.ceil(r8);
        if (r14 >= r7) goto L27;
        StaticLayout r11 = new StaticLayout(this.message, this.textPaint, r14, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (r11.getLineCount() != r12) goto L27;
        int r13 = 0;
    L24:
        if (r13 >= r11.getLineCount()) goto L26;
        r3 = Math.max(r3, r11.getLineWidth(r13));
        r13 = r13 + 1;
        goto L24
    L26:
        r8 = r3;
        r4 = r11;
    L27:
        this.textLayout = r4;
        this.textDrawX = r2;
        this.measuredWidth = (((int) Math.ceil(r8)) + r2) + this.textPaddingH;
        int r15 = this.textLayout.getLineCount() - 1;
        float r22 = this.textLayout.getLineBaseline(0);
        float r16 = this.textLayout.getLineBaseline(r15);
        int r17 = Math.max(this.minHeight, (int) Math.ceil((this.firstBaselineTop + (r16 - r22)) + this.lastBaselineBottom));
        this.measuredHeight = r17;
        this.textDrawY = this.firstBaselineTop - r22;
        return r17;
    L5:
        if (this.textLayout == null) goto L8;
        return this.measuredHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setMessage(CharSequence r1) {
        this.message = r1;
        this.lastMeasuredWidth = -1;
    }

    public void setParentView(View r1) {
        this.parentView = r1;
        this.avatarReceiver.setParentView(r1);
    }

    public void setUser(TLObject r10) {
        if (r10 == null) goto L5;
        boolean r1 = true;
    L6:
        this.hasAvatar = r1;
        if (r1 == false) goto L15;
        this.avatarDrawable.setInfo(r10);
        if ((r10 instanceof TLRPC.User) == false) goto L11;
        this.avatarReceiver.setImage(ImageLocation.getForUser((TLRPC.User) r10, 1), "48_48", this.avatarDrawable, null, null, 0);
        goto L15
    L11:
        boolean r12 = r10 instanceof TLRPC.Chat;
        ImageReceiver r2 = this.avatarReceiver;
        if (r12 == false) goto L14;
        r2.setImage(ImageLocation.getForChat((TLRPC.Chat) r10, 1), "48_48", this.avatarDrawable, null, null, 0);
        goto L15
    L14:
        r2.setImageBitmap(this.avatarDrawable);
    L15:
        this.lastMeasuredWidth = -1;
        return;
    L5:
        r1 = false;
        goto L6
    }
}
