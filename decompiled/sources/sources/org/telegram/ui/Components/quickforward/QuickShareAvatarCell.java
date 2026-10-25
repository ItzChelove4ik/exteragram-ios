package org.telegram.ui.Components.quickforward;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.quickforward.QuickShareSelectorDrawable;

/* loaded from: classes7.dex */
class QuickShareAvatarCell implements ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator alphaAnimator;
    private float alphaFactor;
    private boolean alphaValue;
    private final AvatarDrawable avatarDrawable;
    private float bgX1;
    private float bgY;
    private BlurVisibilityDrawable blurredAvatarDrawable;
    private BlurVisibilityDrawable blurredTextDrawable;
    private Paint blurredTextPaint;
    private final ChatMessageCell cell;
    private final int currentAccount;
    public final long dialogId;
    private final ImageReceiver imageReceiver;
    private final QuickShareSelectorDrawable parent;
    private ValueAnimator selectedAnimator;
    private float selectedFactor;
    private boolean selectedValue;
    private StaticLayout textLayout;

    /* renamed from: $r8$lambda$1GlC_xcghvsz8gUko-bOqzkf2qg, reason: not valid java name */
    public static /* synthetic */ void m13754$r8$lambda$1GlC_xcghvsz8gUkobOqzkf2qg(QuickShareAvatarCell r0, Canvas r1, int r2) {
        r0.renderText(r1, r2);
    }

    /* renamed from: $r8$lambda$WUW-Z006pbbb93WeTKn0sryijYg, reason: not valid java name */
    public static /* synthetic */ void m13755$r8$lambda$WUWZ006pbbb93WeTKn0sryijYg(QuickShareAvatarCell r0, Canvas r1, int r2) {
        r0.renderAvatar(r1, r2);
    }

    public QuickShareAvatarCell(QuickShareSelectorDrawable r3, long r4) {
        this.avatarDrawable = new AvatarDrawable();
        this.currentAccount = UserConfig.selectedAccount;
        this.alphaFactor = 1.0f;
        this.alphaValue = true;
        this.selectedFactor = 0.0f;
        this.selectedValue = false;
        this.imageReceiver = new ImageReceiver(r3.parent);
        this.parent = r3;
        ChatMessageCell r32 = r3.cell;
        this.cell = r32;
        this.dialogId = r4;
        setDialog(r32, r4);
    }

    private void drawAvatarImpl(Canvas r1, float r2, float r3, float r4, float r5) {
        r1.save();
        r1.translate(r2 - r4, r3 - r4);
        r1.scale(r4 / AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR_RADIUS), r4 / AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR_RADIUS));
        this.imageReceiver.setAlpha(((this.alphaFactor * 0.25f) + 0.75f) * r5);
        this.imageReceiver.draw(r1);
        r1.restore();
    }

    private void drawTextImpl(Canvas r11, float r12, float r13, float r14) {
        RectF r0 = AndroidUtilities.rectTmp;
        float r2 = AndroidUtilities.dp(21.0f) / 2.0f;
        r0.set(r12, r13, (this.textLayout.getWidth() + r12) + (AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.TEXT_PADDING_INTERNAL) * 2), AndroidUtilities.dp(21.0f) + r13);
        boolean r4 = this.cell.hasGradientService();
        Paint r5 = this.blurredTextPaint;
        if (r5 == null) goto L5;
        int r6 = r5.getAlpha();
        r5.setAlpha((int) (255.0f * r14));
        r11.drawRoundRect(r0, r2, r2, r5);
        r5.setAlpha(r6);
    L10:
        if (r4 == true) goto L12;
        if (r5 != null) goto L12;
    L13:
        r11.save();
        r11.translate(r12 + AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.TEXT_PADDING_INTERNAL), r13 + ((AndroidUtilities.dp(21.0f) - this.textLayout.getHeight()) / 2.0f));
        int r122 = this.textLayout.getPaint().getAlpha();
        this.textLayout.getPaint().setAlpha((int) (r122 * r14));
        this.textLayout.draw(r11);
        this.textLayout.getPaint().setAlpha(r122);
        r11.restore();
        return;
    L12:
        int r42 = Theme.chat_actionBackgroundGradientDarkenPaint.getAlpha();
        Theme.chat_actionBackgroundGradientDarkenPaint.setAlpha((int) (r42 * r14));
        r11.drawRoundRect(r0, r2, r2, Theme.chat_actionBackgroundGradientDarkenPaint);
        Theme.chat_actionBackgroundGradientDarkenPaint.setAlpha(r42);
        goto L13
    L5:
        ChatMessageCell r62 = this.cell;
        Point r7 = AndroidUtilities.displaySize;
        r62.applyServiceShaderMatrix(r7.x, r7.y, 0.0f, 0.0f);
        Paint r63 = this.cell.getThemedPaint("paintChatActionBackground");
        int r72 = r63.getAlpha();
        if (r4 == false) goto L8;
        float r8 = r72;
    L9:
        r63.setAlpha((int) (r8 * r14));
        r11.drawRoundRect(r0, r2, r2, r63);
        r63.setAlpha(r72);
        goto L10
    L8:
        r8 = 229.5f;
        goto L9
    }

    private float fixX(float r6, float r7, float r8, float r9) {
        float r0 = r7 / 2.0f;
        float r1 = r6 - r0;
        float r2 = r6 + r0;
        float r3 = r9 - r8;
        if (r7 <= r3) goto L10;
        float r62 = (r8 + r9) / 2.0f;
        float r72 = r7 - r3;
        float r82 = Math.max(0.0f, r8 - r1);
        float r92 = Math.max(0.0f, r2 - r9);
        float r02 = r82 + r92;
        if (r02 >= 0.1f) goto L7;
        return r62;
    L7:
        float r73 = r72 / 2.0f;
        return r62 + (r73 * ((r82 - r92) / r02));
    L10:
        if (r1 >= r8) goto L14;
        return r8 + r0;
    L14:
        if (r2 > r9) goto L16;
        return r6;
    L16:
        return r9 - r0;
    }

    private void renderAvatar(Canvas r8, int r9) {
        float r3 = AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR_RADIUS);
        drawAvatarImpl(r8, r3, r3, r3, r9 / 255.0f);
    }

    private void renderText(Canvas r4, int r5) {
        r4.save();
        r4.translate(-this.bgX1, -this.bgY);
        drawTextImpl(r4, this.bgX1, this.bgY, r5 / 255.0f);
        r4.restore();
    }

    private void setDialog(ChatMessageCell r15, long r16) {
        this.avatarDrawable.setScaleSize(1.0f);
        boolean r0 = DialogObject.isUserDialog(r16);
        int r1 = this.currentAccount;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == false) goto L10;
        TLRPC.User r12 = MessagesController.getInstance(r1).getUser(Long.valueOf(r16));
        this.avatarDrawable.setInfo(this.currentAccount, r12);
        if (UserObject.isUserSelf(r12) == false) goto L7;
        String r02 = LocaleController.getString(R.string.SavedMessages);
        this.avatarDrawable.setAvatarType(1);
        this.avatarDrawable.setScaleSize(0.75f);
        this.imageReceiver.setImage(null, null, null, null, this.avatarDrawable, 0, null, r12, 0);
    L15:
        this.imageReceiver.setRoundRadius(AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR / 2.0f));
        this.imageReceiver.setImageCoords(0.0f, 0.0f, AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR), AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR));
        Paint r152 = r15.getThemedPaint("paintChatActionText");
        if (r02 == null) goto L20;
        if (r152 == null) goto L21;
        int r22 = AndroidUtilities.displaySize.x - AndroidUtilities.dp((QuickShareSelectorDrawable.Sizes.TEXT_PADDING_INTERNAL * 2) + (QuickShareSelectorDrawable.Sizes.TEXT_PADDING_EXTERNAL * 2));
        TextPaint r5 = new TextPaint(r152);
        this.textLayout = new StaticLayout(TextUtils.ellipsize(r02, r5, r22, TextUtils.TruncateAt.END), r5, (int) Math.ceil(r5.measureText(r4, 0, r4.length())), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        return;
    L21:
        return;
    L20:
        return;
    L7:
        if (r12 == null) goto L9;
        r2 = ContactsController.formatName(r12.first_name, r12.last_name);
    L9:
        this.imageReceiver.setForUserOrChat(r12, this.avatarDrawable);
    L14:
        r02 = r2;
        goto L15
    L10:
        TLRPC.Chat r03 = MessagesController.getInstance(r1).getChat(Long.valueOf(-r16));
        if (r03 == null) goto L13;
        r2 = r03.title;
    L13:
        this.avatarDrawable.setInfo(this.currentAccount, r03);
        this.imageReceiver.setForUserOrChat(r03, this.avatarDrawable);
        goto L14
    }

    public void draw(Canvas r9, float r10, float r11, float r12, float r13, float r14, float r15, float r16, float r17, boolean r18) {
        if (r18 == true) goto L5;
        drawAvatarImpl(r9, r14, r15, r16 + (AndroidUtilities.dp(2.0f) * this.selectedFactor), r17);
    L5:
        float r4 = this.selectedFactor;
        if (r4 > 0.0f) goto L8;
        return;
    L8:
        if (this.textLayout == null) goto L20;
        float r42 = (r4 * 0.15f) + 0.85f;
        r9.save();
        r9.scale(r42, r42, r14, r15);
        float r43 = this.selectedFactor * r17;
        float r5 = this.textLayout.getWidth() + (AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.TEXT_PADDING_INTERNAL) * 2);
        this.bgX1 = fixX(fixX(r14, r5, r12, r13), r5, r10, r11) - (r5 / 2.0f);
        this.bgY = r15 - AndroidUtilities.dp(58.0f);
        if (this.blurredTextDrawable == null) goto L12;
    L14:
        BlurVisibilityDrawable r102 = this.blurredTextDrawable;
        if (r102 == null) goto L17;
        float r122 = this.bgX1;
        float r2 = this.bgY;
        r102.setBounds((int) r122, (int) r2, (int) (r122 + r5), (int) (r2 + AndroidUtilities.dp(21.0f)));
        this.blurredTextDrawable.setAlpha((int) (r43 * 255.0f));
        this.blurredTextDrawable.draw(r9);
    L17:
        r9.restore();
        return;
    L12:
        if (this.parent.isDestroyed() == true) goto L14;
        this.blurredTextPaint = this.parent.getBlurBitmapPaint();
        BlurVisibilityDrawable r103 = new BlurVisibilityDrawable(new QuickShareAvatarCell$$ExternalSyntheticLambda0(this));
        this.blurredTextDrawable = r103;
        r103.render((int) r5, AndroidUtilities.dp(21.0f), AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.TEXT_BLUR_RADIUS), 3.0f);
        goto L14
    }

    public void drawBlurredAvatar(Canvas r6, float r7, float r8, float r9, float r10) {
        if (this.blurredAvatarDrawable != null) goto L5;
        BlurVisibilityDrawable r0 = new BlurVisibilityDrawable(new QuickShareAvatarCell$$ExternalSyntheticLambda1(this));
        this.blurredAvatarDrawable = r0;
        r0.render(AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR), AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR), AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.BLUR_RADIUS), 4.0f);
    L5:
        r6.save();
        r6.translate(r7 - r9, r8 - r9);
        r6.scale(r9 / AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR_RADIUS), r9 / AndroidUtilities.dp(QuickShareSelectorDrawable.Sizes.AVATAR_RADIUS));
        this.blurredAvatarDrawable.setAlpha((int) (r10 * 255.0f));
        this.blurredAvatarDrawable.draw(r6);
        r6.restore();
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator r2) {
        if (r2 != this.selectedAnimator) goto L6;
        this.selectedFactor = ((Float) r2.getAnimatedValue()).floatValue();
    L8:
        this.parent.invalidateSelf();
        return;
    L6:
        if (r2 != this.alphaAnimator) goto L8;
        this.alphaFactor = ((Float) r2.getAnimatedValue()).floatValue();
        goto L8
    }

    public void recycle() {
        BlurVisibilityDrawable r0 = this.blurredTextDrawable;
        if (r0 == null) goto L5;
        r0.recycle();
    L5:
        BlurVisibilityDrawable r1 = this.blurredAvatarDrawable;
        if (r1 == null) goto L9;
        r1.recycle();
        return;
    }

    public void setFullVisible(boolean r3, boolean r4) {
        if (this.alphaValue != r3) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.alphaAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.alphaValue = r3;
        float r02 = 0.0f;
        if (r4 == false) goto L15;
        float r42 = this.alphaFactor;
        if (r3 == false) goto L13;
        r02 = 1.0f;
    L13:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r42, r02});
        this.alphaAnimator = r32;
        r32.setDuration(180);
        this.alphaAnimator.addUpdateListener(this);
        this.alphaAnimator.setInterpolator(QuickShareSelectorDrawable.Interpolators.DECELERATE_INTERPOLATOR);
        this.alphaAnimator.start();
        return;
    L15:
        if (r3 == false) goto L17;
        r02 = 1.0f;
    L17:
        this.alphaFactor = r02;
        this.parent.invalidateSelf();
    }

    public void setSelected(boolean r3, boolean r4) {
        if (this.selectedValue != r3) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.selectedAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.selectedValue = r3;
        float r02 = 0.0f;
        if (r4 == false) goto L15;
        float r42 = this.selectedFactor;
        if (r3 == false) goto L13;
        r02 = 1.0f;
    L13:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r42, r02});
        this.selectedAnimator = r32;
        r32.setDuration(180);
        this.selectedAnimator.addUpdateListener(this);
        this.selectedAnimator.setInterpolator(QuickShareSelectorDrawable.Interpolators.DECELERATE_INTERPOLATOR);
        this.selectedAnimator.start();
        return;
    L15:
        if (r3 == false) goto L17;
        r02 = 1.0f;
    L17:
        this.selectedFactor = r02;
        this.parent.invalidateSelf();
    }
}
