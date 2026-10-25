package com.exteragram.messenger.utils.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes4.dex */
public final class ForwardsCountUiHelper {
    private int account;
    private boolean allowed;
    private float animateFromX;
    private StaticLayout animateLayout;
    private boolean animating;
    private final PorterDuffColorFilter[] colorFilters;
    private final int[] colors;
    private int count;
    private long dialogId;
    private Drawable drawable;
    private StaticLayout lastLayout;
    private float lastX;
    private StaticLayout layout;
    private int messageId;
    private float textSize;
    private Typeface typeface;
    private int width;

    public ForwardsCountUiHelper() {
        this.colorFilters = new PorterDuffColorFilter[2];
        this.colors = new int[2];
        this.allowed = true;
        this.account = -1;
    }

    private static int getCount(MessageObject r2) {
        if (ExteraConfig.getShowForwardsCount() == false) goto L22;
        if (r2 == null) goto L22;
        TLRPC.Message r0 = r2.messageOwner;
        if (r0 == null) goto L22;
        if ((r0.flags & 1024) == 0) goto L22;
        if (r2.scheduled == true) goto L22;
        if (r2.notime == true) goto L22;
        if (r2.isSponsored() == true) goto L22;
        if (r2.isQuickReply() == true) goto L22;
        if (r2.isWelcomeMessage() == true) goto L22;
        return Math.max(0, r2.messageOwner.forwards);
    L22:
        return 0;
    }

    public boolean animateChange() {
        this.animateFromX = this.lastX;
        StaticLayout r0 = this.layout;
        StaticLayout r1 = this.lastLayout;
        if (r0 == r1) goto L5;
        boolean r02 = true;
    L6:
        this.animating = r02;
        if (r02 == true) goto L10;
        r1 = null;
    L10:
        this.animateLayout = r1;
        return r02;
    L5:
        r02 = false;
        goto L6
    }

    public void appendAccessibilityText(SpannableStringBuilder r3) {
        if (this.count <= 0) goto L6;
        r3.append("\n").append(String.format(LocaleController.getPluralString("Shares", this.count), new Object[]{AndroidUtilities.formatCount(this.count)}));
        return;
    }

    public void bind(MessageObject r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (this.account == r5.currentAccount) goto L6;
    L9:
        this.account = r5.currentAccount;
        this.dialogId = r5.getDialogId();
        this.messageId = r5.getId();
        this.layout = null;
        this.lastLayout = null;
        this.count = 0;
        this.width = 0;
        this.lastX = 0.0f;
        this.allowed = true;
        resetAnimation();
        return;
    L6:
        if (this.dialogId != r5.getDialogId()) goto L9;
        if (this.messageId != r5.getId()) goto L9;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float draw(Canvas r17, ChatMessageCell r18, float r19, float r20, float r21, float r22, float r23, boolean r24) {
        StaticLayout r4 = this.layout;
        if (r4 == null) goto L5;
    L7:
        ChatMessageCell.TransitionParams r6 = r18.transitionParams;
        float r7 = r6.animateChangeProgress;
        boolean r8 = this.animating;
        boolean r9 = false;
        if (r8 == true) goto L10;
    L12:
        Object[] r11 = false;
    L13:
        if (r8 == false) goto L16;
        if (r4 != null) goto L16;
        r9 = true;
    L16:
        boolean r42 = r6.shouldAnimateTimeX;
        if (r42 == false) goto L19;
        float r82 = r18.timeX;
    L20:
        float r83 = r82 + r21;
        if (r42 == false) goto L31;
        if (r11 == true) goto L31;
        if (r18.getMessageObject().isRoundVideo() == true) goto L26;
    L27:
        float r43 = this.animateFromX;
        if (r9 == false) goto L30;
    L29:
        r83 = r43;
        goto L31
    L30:
        r43 = AndroidUtilities.lerp(r43, r83, r7);
        goto L29
    L26:
        if (r6.animateDrawBackground == false) goto L27;
    L31:
        MessageObject.GroupedMessages r44 = r18.getCurrentMessagesGroup();
        if (r44 == null) goto L37;
        MessageObject.GroupedMessages.TransitionParams r45 = r44.transitionParams;
        if (r45.backgroundChangeBounds == false) goto L37;
        r83 = r83 + r45.offsetRight;
    L37:
        if (r6.animateBackgroundBoundsInner == false) goto L39;
        r83 = r83 + r18.getAnimationOffsetX();
    L39:
        this.lastX = r83;
        TextPaint r46 = Theme.chat_timePaint;
        int r62 = r46.getAlpha();
        int r10 = (int) (r62 * r23);
        if (r18.shouldDrawTimeOnMedia() == false) goto L46;
        if (r18.getMessageObject().shouldDrawWithoutBackground() == false) goto L44;
        int r12 = Theme.key_chat_serviceText;
    L53:
        int r2 = r18.getThemedColor(r12);
        PorterDuffColorFilter[] r122 = this.colorFilters;
        if (r122[r24 ? 1 : 0] != null) goto L56;
    L57:
        this.colors[r24 ? 1 : 0] = r2;
        r122[r24 ? 1 : 0] = new PorterDuffColorFilter(r2, PorterDuff.Mode.SRC_IN);
    L58:
        this.drawable.setColorFilter(this.colorFilters[r24 ? 1 : 0]);
        float r25 = BaseCell.setDrawableBounds(this.drawable, r83, r20, r46.getTextSize());
        r17.save();
        if (r23 == 1.0f) goto L65;
        float r15 = (r23 * 0.5f) + 0.5f;
        StaticLayout r13 = this.layout;
        if (r13 == null) goto L63;
    L62:
        float r182 = 3.0f;
        r17.scale(r15, r15, (((AndroidUtilities.dp(3.0f) + r25) + r13.getWidth()) / 2.0f) + r83, this.drawable.getBounds().centerY());
    L66:
        if (r11 == false) goto L68;
        float r92 = r7;
    L71:
        this.drawable.setAlpha((int) ((255.0f * r22) * r92));
        r17.save();
        r17.scale(-1.0f, 1.0f, this.drawable.getBounds().exactCenterX(), 0.0f);
        this.drawable.draw(r17);
        r17.restore();
        r17.translate((r83 + r25) + AndroidUtilities.dp(r182), r20);
        if (this.animateLayout == null) goto L75;
        r46.setAlpha((int) (r10 * (1.0f - r7)));
        this.animateLayout.draw(r17);
    L75:
        if (this.layout == null) goto L81;
        float r26 = r10;
        if (this.animating == true) goto L80;
        r7 = 1.0f;
    L80:
        r46.setAlpha((int) (r26 * r7));
        this.layout.draw(r17);
    L81:
        r17.restore();
        r46.setAlpha(r62);
        return this.width;
    L68:
        if (r9 == false) goto L70;
        r92 = 1.0f - r7;
        goto L71
    L70:
        r92 = 1.0f;
        goto L71
    L63:
        r13 = this.animateLayout;
        goto L62
    L65:
        r182 = 3.0f;
        goto L66
    L56:
        if (this.colors[r24 ? 1 : 0] == r2) goto L58;
    L44:
        r12 = Theme.key_chat_mediaViews;
        goto L53
    L46:
        if (r18.getMessageObject().isOutOwner() == false) goto L50;
        if (r24 == false) goto L49;
        r12 = Theme.key_chat_outViewsSelected;
        goto L53
    L49:
        r12 = Theme.key_chat_outViews;
        goto L53
    L50:
        if (r24 == false) goto L52;
        r12 = Theme.key_chat_inViewsSelected;
        goto L53
    L52:
        r12 = Theme.key_chat_inViews;
        goto L53
    L19:
        r82 = r19;
        goto L20
    L10:
        if (this.animateLayout != null) goto L12;
        r11 = true;
        goto L13
    L5:
        if (this.animateLayout != null) goto L7;
        return 0.0f;
    }

    public boolean isChanged(MessageObject r3) {
        int r0 = this.count;
        if (this.allowed == false) goto L5;
        int r2 = getCount(r3);
    L6:
        if (r0 == r2) goto L9;
        return true;
    L9:
        return false;
    L5:
        r2 = 0;
        goto L6
    }

    public int measure(Context r9, MessageObject r10, boolean r11) {
        this.allowed = r11;
        if (r11 == false) goto L5;
        int r102 = getCount(r10);
    L6:
        this.count = r102;
        if (r102 != 0) goto L11;
        this.layout = null;
        this.width = 0;
        return 0;
    L11:
        if (this.drawable != null) goto L13;
        this.drawable = r9.getResources().getDrawable(R.drawable.msg_reply_small).mutate();
    L13:
        TextPaint r2 = Theme.chat_timePaint;
        String r1 = LocaleController.formatShortNumber(this.count, null);
        StaticLayout r92 = this.layout;
        if (r92 != null) goto L16;
    L21:
        this.textSize = r2.getTextSize();
        this.typeface = r2.getTypeface();
        this.layout = new StaticLayout(r1, r2, Math.max(1, (int) Math.ceil(r2.measureText(r1))), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L22:
        float r93 = (this.drawable.getIntrinsicWidth() * r2.getTextSize()) / this.drawable.getIntrinsicHeight();
        int r94 = (int) Math.ceil((this.layout.getWidth() + r93) + AndroidUtilities.dp(10.0f));
        this.width = r94;
        return r94;
    L16:
        if (TextUtils.equals(r92.getText(), r1) == false) goto L21;
        if (this.textSize != r2.getTextSize()) goto L21;
        if (this.typeface == r2.getTypeface()) goto L22;
    L5:
        r102 = 0;
        goto L6
    }

    public void recordDrawingState() {
        this.lastLayout = this.layout;
    }

    public void resetAnimation() {
        this.animating = false;
        this.animateLayout = null;
    }
}
