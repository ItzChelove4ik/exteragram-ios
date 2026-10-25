package org.telegram.ui.Components.Premium.boosts.cells.msg;

import android.R;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.util.StateSet;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class GiveawayResultsMessageCell {
    private AvatarDrawable[] avatarDrawables;
    private ImageReceiver[] avatarImageReceivers;
    private boolean[] avatarVisible;
    private int bottomHeight;
    private StaticLayout bottomLayout;
    private Paint chatBgPaint;
    private RectF chatRect;
    private TextPaint chatTextPaint;
    private Rect[] clickRect;
    private Paint clipRectPaint;
    private Rect containerRect;
    private RectF countRect;
    private Paint counterBgPaint;
    private Drawable counterIcon;
    private TextPaint counterStarsTextPaint;
    private String counterStr;
    private Rect counterTextBounds;
    private TextPaint counterTextPaint;
    private int countriesHeight;
    private StaticLayout countriesLayout;
    private TextPaint countriesTextPaint;
    private int diffTextWidth;
    private RLottieDrawable giftDrawable;
    private ImageReceiver giftReceiver;
    private boolean isButtonPressed;
    private boolean isContainerPressed;
    private boolean isStars;
    private LinkSpanDrawable.LinkCollector links;
    private int measuredHeight;
    private int measuredWidth;
    private MessageObject messageObject;
    private boolean[] needNewRow;
    private final ChatMessageCell parentView;
    private int pressedPos;
    private int[] pressedState;
    private Paint saveLayerPaint;
    private int selectorColor;
    private Drawable selectorDrawable;
    private int subTitleMarginLeft;
    private int subTitleMarginTop;
    private TextPaint textDividerPaint;
    private TextPaint textPaint;
    private int titleHeight;
    private StaticLayout titleLayout;
    private int topHeight;
    private StaticLayout topLayout;
    private SpannableStringBuilder topStringBuilder;
    private float[] userTitleWidths;
    private CharSequence[] userTitles;
    private TLRPC.User[] users;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.msg.GiveawayResultsMessageCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ GiveawayResultsMessageCell this$0;

        public AnonymousClass1(GiveawayResultsMessageCell r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            GiveawayResultsMessageCell.m12173$$Nest$fgetparentView(this.this$0).invalidate();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            GiveawayResultsMessageCell.m12173$$Nest$fgetparentView(this.this$0).invalidate();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            GiveawayResultsMessageCell.m12173$$Nest$fgetparentView(this.this$0).invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$1JzojX0L7FSRA15tM3_fIckqzWs(GiveawayResultsMessageCell r0, MessageObject r1, TLRPC.TL_messageMediaGiveawayResults r2) {
        r0.lambda$setMessageContent$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sCn9u08LMyK3e9caeaucnG2tN9s(GiveawayResultsMessageCell r0, MessageObject r1, TLRPC.TL_messageMediaGiveawayResults r2) {
        r0.lambda$setMessageContent$1(r1, r2);
    }

    /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m12173$$Nest$fgetparentView(GiveawayResultsMessageCell r0) {
        return r0.parentView;
    }

    public GiveawayResultsMessageCell(ChatMessageCell r3) {
        this.measuredHeight = 0;
        this.measuredWidth = 0;
        this.pressedPos = -1;
        this.isButtonPressed = false;
        this.isContainerPressed = false;
        this.parentView = r3;
    }

    private void checkArraysLimits(int r6) {
        ImageReceiver[] r0 = this.avatarImageReceivers;
        if (r0.length >= r6) goto L7;
        int r1 = r0.length;
        this.avatarImageReceivers = (ImageReceiver[]) Arrays.copyOf(r0, r6);
        this.avatarDrawables = (AvatarDrawable[]) Arrays.copyOf(this.avatarDrawables, r6);
        this.avatarVisible = Arrays.copyOf(this.avatarVisible, r6);
        this.userTitles = (CharSequence[]) Arrays.copyOf(this.userTitles, r6);
        this.userTitleWidths = Arrays.copyOf(this.userTitleWidths, r6);
        this.needNewRow = Arrays.copyOf(this.needNewRow, r6);
        this.clickRect = (Rect[]) Arrays.copyOf(this.clickRect, r6);
        this.users = (TLRPC.User[]) Arrays.copyOf(this.users, r6);
        int r12 = r1 - 1;
    L5:
        if (r12 >= r6) goto L9;
        this.avatarImageReceivers[r12] = new ImageReceiver(this.parentView);
        this.avatarImageReceivers[r12].setAllowLoadingOnAttachedOnly(true);
        this.avatarImageReceivers[r12].setRoundRadius(AndroidUtilities.dp(12.0f));
        this.avatarDrawables[r12] = new AvatarDrawable();
        this.avatarDrawables[r12].setTextSize(AndroidUtilities.dp(18.0f));
        this.clickRect[r12] = new Rect();
        r12 = r12 + 1;
        goto L5
    L9:
        return;
    }

    private void createImages() {
        if (this.avatarImageReceivers != null) goto L9;
        this.avatarImageReceivers = new ImageReceiver[10];
        this.avatarDrawables = new AvatarDrawable[10];
        this.avatarVisible = new boolean[10];
        int r0 = 0;
    L6:
        ImageReceiver[] r1 = this.avatarImageReceivers;
        if (r0 >= r1.length) goto L11;
        r1[r0] = new ImageReceiver(this.parentView);
        this.avatarImageReceivers[r0].setAllowLoadingOnAttachedOnly(true);
        this.avatarImageReceivers[r0].setRoundRadius(AndroidUtilities.dp(12.0f));
        this.avatarDrawables[r0] = new AvatarDrawable();
        this.avatarDrawables[r0].setTextSize(AndroidUtilities.dp(18.0f));
        this.clickRect[r0] = new Rect();
        r0 = r0 + 1;
        goto L6
    L11:
        return;
    }

    private int getUserColor(TLRPC.User r1, Theme.ResourcesProvider r2) {
        if (this.messageObject.isOutOwner() == true) goto L5;
        int r0 = UserObject.getColorId(r1);
        if (r0 < 7) goto L9;
        MessagesController.PeerColors r12 = MessagesController.getInstance(UserConfig.selectedAccount).peerColors;
        if (r12 != null) goto L13;
        MessagesController.PeerColor r02 = null;
    L14:
        if (r02 == null) goto L18;
        return r02.getColor1();
    L18:
        return Theme.getColor(Theme.keys_avatar_nameInMessage[0], r2);
    L13:
        r02 = r12.getColor(r0);
        goto L14
    L9:
        return Theme.getColor(Theme.keys_avatar_nameInMessage[r0], r2);
    L5:
        return Theme.getColor(Theme.key_chat_outPreviewInstantText, r2);
    }

    private void init() {
        if (this.counterTextPaint == null) goto L5;
        return;
    L5:
        this.counterTextPaint = new TextPaint(1);
        this.counterStarsTextPaint = new TextPaint(1);
        this.chatTextPaint = new TextPaint(1);
        this.textPaint = new TextPaint(1);
        this.textDividerPaint = new TextPaint(1);
        this.countriesTextPaint = new TextPaint(1);
        this.counterBgPaint = new Paint(1);
        this.chatBgPaint = new Paint(1);
        this.saveLayerPaint = new Paint();
        this.clipRectPaint = new Paint();
        this.countRect = new RectF();
        this.chatRect = new RectF();
        this.counterTextBounds = new Rect();
        this.containerRect = new Rect();
        this.pressedState = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        this.userTitles = new CharSequence[10];
        this.users = new TLRPC.User[10];
        this.userTitleWidths = new float[10];
        this.needNewRow = new boolean[10];
        this.clickRect = new Rect[10];
        ImageReceiver r0 = new ImageReceiver(this.parentView);
        this.giftReceiver = r0;
        r0.setAllowLoadingOnAttachedOnly(true);
        Paint r02 = this.clipRectPaint;
        PorterDuff.Mode r2 = PorterDuff.Mode.DST_OUT;
        r02.setXfermode(new PorterDuffXfermode(r2));
        this.counterTextPaint.setTypeface(AndroidUtilities.bold());
        this.counterTextPaint.setXfermode(new PorterDuffXfermode(r2));
        this.counterTextPaint.setTextSize(AndroidUtilities.dp(12.0f));
        TextPaint r03 = this.counterTextPaint;
        Paint.Align r22 = Paint.Align.CENTER;
        r03.setTextAlign(r22);
        this.counterStarsTextPaint.setTypeface(AndroidUtilities.bold());
        this.counterStarsTextPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.counterStarsTextPaint.setTextAlign(r22);
        this.counterStarsTextPaint.setColor(-1);
        this.chatTextPaint.setTypeface(AndroidUtilities.bold());
        this.chatTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.countriesTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.textPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.textDividerPaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.textDividerPaint.setTextAlign(r22);
    }

    private /* synthetic */ void lambda$setMessageContent$0(MessageObject r7, TLRPC.TL_messageMediaGiveawayResults r8) {
        if (r7.getDialogId() != (-r8.channel_id)) goto L6;
        this.parentView.getDelegate().didPressReplyMessage(this.parentView, r8.launch_msg_id, 0.0f, 0.0f, false);
        return;
    L6:
        Bundle r6 = new Bundle();
        r6.putLong("chat_id", r8.channel_id);
        r6.putInt("message_id", r8.launch_msg_id);
        LaunchActivity.getLastFragment().presentFragment(new ChatActivity(r6));
    }

    private /* synthetic */ void lambda$setMessageContent$1(MessageObject r2, TLRPC.TL_messageMediaGiveawayResults r3) {
        AndroidUtilities.runOnUIThread(new GiveawayResultsMessageCell$$ExternalSyntheticLambda1(this, r2, r3));
    }

    private void setGiftImage() {
        this.giftReceiver.setAllowStartLottieAnimation(false);
        if (this.giftDrawable != null) goto L5;
        this.giftDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.giveaway_results, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f));
    L5:
        this.giftReceiver.setImageBitmap(this.giftDrawable);
    }

    public boolean checkMotionEvent(MotionEvent r8) {
        MessageObject r0 = this.messageObject;
        if (r0 != null) goto L5;
    L62:
        return false;
    L5:
        if (r0.isGiveawayResults() == false) goto L62;
        if (this.links != null) goto L10;
        this.links = new LinkSpanDrawable.LinkCollector(this.parentView);
    L10:
        int r02 = r8.getAction();
        int r2 = (int) r8.getX();
        int r82 = (int) r8.getY();
        if (r02 == 1) goto L14;
        if (r02 == 0) goto L14;
    L30:
        if (r02 != 0) goto L43;
        int r03 = 0;
    L32:
        Rect[] r4 = this.clickRect;
        if (r03 >= r4.length) goto L40;
        if (r4[r03].contains(r2, r82) == true) goto L36;
        r03 = r03 + 1;
        goto L32
    L36:
        this.pressedPos = r03;
        this.selectorDrawable.setHotspot(r2, r82);
        this.isButtonPressed = true;
        setButtonPressed(true);
        return true;
    L40:
        if (this.containerRect.contains(r2, r82) == false) goto L62;
        this.isContainerPressed = true;
        return true;
    L43:
        if (r02 != 1) goto L54;
        if (this.isButtonPressed == false) goto L51;
        if (this.parentView.getDelegate() == null) goto L49;
        this.parentView.getDelegate().didPressGiveawayChatButton(this.parentView, this.pressedPos);
    L49:
        this.parentView.playSoundEffect(0);
        setButtonPressed(false);
        this.isButtonPressed = false;
    L51:
        if (this.isContainerPressed == false) goto L62;
        this.isContainerPressed = false;
        BoostDialogs.showBulletinAbout(this.messageObject);
        goto L62
    L54:
        if (r02 == 2) goto L62;
        if (r02 != 3) goto L62;
        this.links.clear();
        if (this.isButtonPressed == false) goto L61;
        setButtonPressed(false);
    L61:
        this.isButtonPressed = false;
        this.isContainerPressed = false;
    L14:
        if (this.topStringBuilder == null) goto L30;
        StaticLayout r42 = this.topLayout;
        if (r42 == null) goto L30;
        int r5 = this.subTitleMarginTop;
        if ((r82 - r5) <= 0) goto L30;
        int r43 = this.topLayout.getOffsetForHorizontal(r42.getLineForVertical((r82 - r5) - AndroidUtilities.dp(10.0f)), r2 - this.subTitleMarginLeft);
        ClickableSpan[] r44 = (ClickableSpan[]) this.topStringBuilder.getSpans(r43, r43, ClickableSpan.class);
        if (r44.length == 0) goto L29;
        if (r02 != 1) goto L23;
        this.links.clear();
        r44[0].onClick(this.parentView);
    L28:
        return true;
    L23:
        LinkSpanDrawable r04 = new LinkSpanDrawable(r44[0], null, r2, r82);
        this.links.addLink(r04);
        int r83 = this.topStringBuilder.getSpanStart(r44[0]);     // Catch: Exception -> L26
        LinkPath r05 = r04.obtainNewPath();     // Catch: Exception -> L26
        r05.setCurrentLayout(this.topLayout, r83, this.subTitleMarginLeft, this.subTitleMarginTop);     // Catch: Exception -> L26
        this.topLayout.getSelectionPath(r83, this.topStringBuilder.getSpanEnd(r44[0]), r05);     // Catch: Exception -> L26
    L26:
        e = move-exception;
        FileLog.e(e);
        goto L28
    L29:
        this.links.clear();
        this.parentView.invalidate();
        goto L30
    }

    public void draw(Canvas r22, int r23, int r24, Theme.ResourcesProvider r25) {
        MessageObject r2 = this.messageObject;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.isGiveawayResults() == true) goto L8;
        return;
    L8:
        if (this.selectorDrawable != null) goto L10;
        int r26 = Theme.getColor(Theme.key_listSelector);
        this.selectorColor = r26;
        Drawable r27 = Theme.createRadSelectorDrawable(r26, 12, 12);
        this.selectorDrawable = r27;
        r27.setCallback(this.parentView);
    L10:
        this.textPaint.setColor(Theme.chat_msgTextPaint.getColor());
        this.textDividerPaint.setColor(Theme.getColor(Theme.key_dialogTextGray2));
        this.countriesTextPaint.setColor(Theme.chat_msgTextPaint.getColor());
        boolean r28 = this.messageObject.isOutOwner();
        TextPaint r3 = this.chatTextPaint;
        if (r28 == false) goto L13;
        int r29 = Theme.key_chat_outPreviewInstantText;
        r3.setColor(Theme.getColor(r29, r25));
        this.counterBgPaint.setColor(Theme.getColor(r29, r25));
        this.chatBgPaint.setColor(Theme.getColor(Theme.key_chat_outReplyLine, r25));
    L15:
        if (this.isStars == false) goto L17;
        this.counterBgPaint.setColor(Theme.getColor(Theme.key_starsGradient1, r25));
    L17:
        r22.save();
        int r11 = r24 - AndroidUtilities.dp(4.0f);
        float r12 = r11;
        r22.translate(r12, r23);
        this.containerRect.set(r11, r23, getMeasuredWidth() + r11, getMeasuredHeight() + r23);
        r22.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.saveLayerPaint, 31);
        this.giftReceiver.draw(r22);
        float r14 = getMeasuredWidth() / 2.0f;
        float r210 = AndroidUtilities.dp(106.0f);
        int r32 = this.counterTextBounds.width() + AndroidUtilities.dp(12.0f);
        int r4 = this.counterTextBounds.height() + AndroidUtilities.dp(10.0f);
        this.countRect.set(r14 - ((AndroidUtilities.dp(2.0f) + r32) / 2.0f), r210 - ((r4 + AndroidUtilities.dp(2.0f)) / 2.0f), ((r32 + AndroidUtilities.dp(2.0f)) / 2.0f) + r14, ((r4 + AndroidUtilities.dp(2.0f)) / 2.0f) + r210);
        r22.drawRoundRect(this.countRect, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), this.clipRectPaint);
        float r33 = r32 / 2.0f;
        float r42 = r4 / 2.0f;
        this.countRect.set(r14 - r33, r210 - r42, r33 + r14, r210 + r42);
        r22.drawRoundRect(this.countRect, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.counterBgPaint);
        Drawable r211 = this.counterIcon;
        if (r211 == null) goto L20;
        r211.setBounds(((int) this.countRect.left) + AndroidUtilities.dp(5.0f), ((int) this.countRect.centerY()) - AndroidUtilities.dp(6.96f), ((int) this.countRect.left) + AndroidUtilities.dp(21.24f), ((int) this.countRect.centerY()) + AndroidUtilities.dp(6.96f));
        this.counterIcon.draw(r22);
    L20:
        String r212 = this.counterStr;
        float r34 = this.countRect.centerX();
        if (this.isStars == false) goto L23;
        float r43 = 8.0f;
    L24:
        float r35 = r34 + AndroidUtilities.dp(r43);
        float r44 = this.countRect.centerY() + AndroidUtilities.dp(4.0f);
        if (this.isStars == false) goto L27;
        TextPaint r5 = this.counterStarsTextPaint;
    L28:
        r22.drawText(r212, r35, r44, r5);
        r22.restore();
        r22.translate(0.0f, AndroidUtilities.dp(128.0f));
        int r213 = AndroidUtilities.dp(128.0f) + r23;
        this.subTitleMarginTop = this.titleHeight + r213;
        this.subTitleMarginLeft = (int) (r12 + (this.diffTextWidth / 2.0f));
        r22.save();
        r22.translate(this.diffTextWidth / 2.0f, 0.0f);
        this.titleLayout.draw(r22);
        r22.translate(0.0f, this.titleHeight);
        this.topLayout.draw(r22);
        r22.restore();
        float r8 = 6.0f;
        r22.translate(0.0f, this.topHeight + AndroidUtilities.dp(6.0f));
        int r36 = 0;
        int r122 = r213 + (this.topHeight + AndroidUtilities.dp(6.0f));
        int r214 = 0;
    L29:
        boolean[] r45 = this.avatarVisible;
        if (r36 >= r45.length) goto L58;
        if (r45[r36] == true) goto L33;
        r36 = r36 + 1;
        goto L29
    L33:
        r22.save();
        int r52 = r36;
        float r46 = 0.0f;
    L34:
        float r13 = 40.0f;
        float r232 = r8;
        r46 = r46 + (this.userTitleWidths[r52] + AndroidUtilities.dp(40.0f));
        r52 = r52 + 1;
        boolean[] r7 = this.avatarVisible;
        if (r52 >= r7.length) goto L42;
        if (this.needNewRow[r52] == true) goto L42;
        if (r7[r52] == false) goto L42;
        r8 = r232;
    L42:
        float r47 = r14 - (r46 / 2.0f);
        r22.translate(r47, 0.0f);
        int r48 = ((int) r47) + r11;
        int r82 = r36;
    L43:
        int r37 = getUserColor(this.users[r82], r25);
        int r53 = this.pressedPos;
        if (r53 < 0) goto L47;
        if (r53 != r82) goto L47;
        int r16 = r37;
    L48:
        this.chatTextPaint.setColor(r37);
        this.chatBgPaint.setColor(r37);
        this.chatBgPaint.setAlpha(25);
        this.avatarImageReceivers[r82].draw(r22);
        CharSequence r215 = this.userTitles[r82];
        int r38 = r48;
        float r242 = r13;
        r22.drawText(r215, 0, r215.length(), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(16.0f), this.chatTextPaint);
        this.chatRect.set(0.0f, 0.0f, this.userTitleWidths[r82] + AndroidUtilities.dp(r242), AndroidUtilities.dp(24.0f));
        r22.drawRoundRect(this.chatRect, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.chatBgPaint);
        float r39 = r38;
        this.clickRect[r82].set(r38, r122, (int) (this.chatRect.width() + r39), AndroidUtilities.dp(24.0f) + r122);
        r22.translate(this.chatRect.width() + AndroidUtilities.dp(r232), 0.0f);
        r48 = (int) (r39 + (this.chatRect.width() + AndroidUtilities.dp(r232)));
        r82 = r82 + 1;
        boolean[] r216 = this.avatarVisible;
        if (r82 >= r216.length) goto L56;
        if (this.needNewRow[r82] == true) goto L56;
        if (r216[r82] == false) goto L56;
        r13 = r242;
        r214 = r16;
    L56:
        r22.restore();
        r22.translate(0.0f, AndroidUtilities.dp(30.0f));
        r122 = r122 + AndroidUtilities.dp(30.0f);
        r36 = r82;
        r214 = r16;
        r8 = r232;
    L47:
        r16 = r214;
        goto L48
    L58:
        float r233 = r8;
        if (this.countriesLayout == null) goto L61;
        r22.save();
        r22.translate((this.measuredWidth - this.countriesLayout.getWidth()) / 2.0f, AndroidUtilities.dp(4.0f));
        this.countriesLayout.draw(r22);
        r22.restore();
        r22.translate(0.0f, this.countriesHeight);
    L61:
        r22.translate(0.0f, AndroidUtilities.dp(r233));
        r22.save();
        r22.translate(this.diffTextWidth / 2.0f, 0.0f);
        this.bottomLayout.draw(r22);
        r22.restore();
        r22.restore();
        if (this.pressedPos >= 0) goto L64;
    L71:
        LinkSpanDrawable.LinkCollector r217 = this.links;
        if (r217 != null) goto L74;
        return;
    L74:
        if (r217.draw(r22) == false) goto L91;
        this.parentView.invalidate();
        return;
    L91:
        return;
    L64:
        if (Theme.isCurrentThemeDark() == false) goto L66;
        float r310 = 0.12f;
    L67:
        int r218 = Theme.multAlpha(r214, r310);
        if (this.selectorColor == r218) goto L70;
        Drawable r311 = this.selectorDrawable;
        this.selectorColor = r218;
        Theme.setSelectorDrawableColor(r311, r218, true);
    L70:
        this.selectorDrawable.setBounds(this.clickRect[this.pressedPos]);
        this.selectorDrawable.setCallback(this.parentView);
        goto L71
    L66:
        r310 = 0.1f;
        goto L67
    L27:
        r5 = this.counterTextPaint;
        goto L28
    L23:
        r43 = 0.0f;
        goto L24
    L13:
        int r219 = Theme.key_chat_inPreviewInstantText;
        r3.setColor(Theme.getColor(r219, r25));
        this.counterBgPaint.setColor(Theme.getColor(r219, r25));
        this.chatBgPaint.setColor(Theme.getColor(Theme.key_chat_inReplyLine, r25));
        goto L15
    }

    public int getMeasuredHeight() {
        return this.measuredHeight;
    }

    public int getMeasuredWidth() {
        return this.measuredWidth;
    }

    public void onAttachedToWindow() {
        ImageReceiver r0 = this.giftReceiver;
        if (r0 == null) goto L5;
        r0.onAttachedToWindow();
    L5:
        ImageReceiver[] r3 = this.avatarImageReceivers;
        if (r3 == null) goto L10;
        int r02 = r3.length;
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L12;
        r3[r1].onAttachedToWindow();
        r1 = r1 + 1;
        goto L8
    L12:
        return;
    }

    public void onDetachedFromWindow() {
        ImageReceiver r0 = this.giftReceiver;
        if (r0 == null) goto L5;
        r0.onDetachedFromWindow();
    L5:
        ImageReceiver[] r3 = this.avatarImageReceivers;
        if (r3 == null) goto L10;
        int r02 = r3.length;
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L12;
        r3[r1].onDetachedFromWindow();
        r1 = r1 + 1;
        goto L8
    L12:
        return;
    }

    public void setButtonPressed(boolean r2) {
        MessageObject r0 = this.messageObject;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isGiveawayResults() == true) goto L7;
        return;
    L7:
        if (this.selectorDrawable == null) goto L20;
        LinkSpanDrawable.LinkCollector r02 = this.links;
        if (r02 == null) goto L12;
        r02.clear();
    L12:
        Drawable r03 = this.selectorDrawable;
        if (r2 == false) goto L16;
        r03.setCallback(new AnonymousClass1(this));
        this.selectorDrawable.setState(this.pressedState);
        this.parentView.invalidate();
        return;
    L16:
        r03.setState(StateSet.NOTHING);
        this.parentView.invalidate();
        return;
    }

    public void setMessageContent(MessageObject r19, int r20, int r21) {
        this.messageObject = null;
        this.titleLayout = null;
        this.topLayout = null;
        this.bottomLayout = null;
        this.countriesLayout = null;
        this.measuredHeight = 0;
        this.measuredWidth = 0;
        this.isStars = false;
        if (r19.isGiveawayResults() == false) goto L53;
        this.messageObject = r19;
        init();
        createImages();
        setGiftImage();
        TLRPC.TL_messageMediaGiveawayResults r4 = (TLRPC.TL_messageMediaGiveawayResults) r19.messageOwner.media;
        checkArraysLimits(r4.winners.size());
        int r5 = AndroidUtilities.dp(90.0f);
        int r9 = AndroidUtilities.dp(230.0f);
        SpannableStringBuilder r6 = AndroidUtilities.replaceTags(LocaleController.getString("BoostingGiveawayResultsMsgWinnersSelected", org.telegram.messenger.R.string.BoostingGiveawayResultsMsgWinnersSelected));
        SpannableStringBuilder r7 = new SpannableStringBuilder(r6);
        r7.setSpan(new RelativeSizeSpan(1.05f), 0, r6.length(), 33);
        this.topStringBuilder = new SpannableStringBuilder();
        SpannableStringBuilder r1 = AndroidUtilities.replaceSingleTag(LocaleController.getPluralString("BoostingGiveawayResultsMsgWinnersTitle", r4.winners_count), Theme.key_chat_messageLinkIn, 0, new GiveawayResultsMessageCell$$ExternalSyntheticLambda0(this, r19, r4));
        this.topStringBuilder.append(AndroidUtilities.replaceCharSequence("%1$d", r1, AndroidUtilities.replaceTags("**" + r4.winners_count + "**")));
        this.topStringBuilder.append("\n\n");
        this.topStringBuilder.setSpan(new RelativeSizeSpan(0.4f), this.topStringBuilder.length() + (-1), this.topStringBuilder.length(), 33);
        SpannableStringBuilder r62 = AndroidUtilities.replaceTags(LocaleController.getPluralString("BoostingGiveawayResultsMsgWinners", r4.winners_count));
        this.topStringBuilder.append(r62);
        this.topStringBuilder.setSpan(new RelativeSizeSpan(1.05f), r1.length() + 2, (r1.length() + 2) + r62.length(), 33);
        SpannableStringBuilder r12 = new SpannableStringBuilder();
        if (r4.winners_count == r4.winners.size()) goto L9;
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayResultsMsgAllAndMoreWinners", r4.winners_count - r4.winners.size(), new Object[0])));
        r12.setSpan(new RelativeSizeSpan(1.05f), 0, r12.length(), 33);
        r12.append("\n");
    L9:
        if ((r4.flags & 32) == 0) goto L11;
        boolean r63 = true;
    L12:
        this.isStars = r63;
        if (r63 == false) goto L15;
        r12.append(LocaleController.formatPluralStringSpaced("BoostingStarsGiveawayResultsMsgAllWinnersReceivedLinks", (int) r4.stars));
    L16:
        TextPaint r8 = this.textPaint;
        Layout.Alignment r10 = Layout.Alignment.ALIGN_CENTER;
        float r122 = AndroidUtilities.dp(2.0f);
        TextUtils.TruncateAt r14 = TextUtils.TruncateAt.END;
        this.titleLayout = StaticLayoutEx.createStaticLayout(r7, r8, r9, r10, 1.0f, r122, false, r14, r9, 10);
        this.topLayout = StaticLayoutEx.createStaticLayout(this.topStringBuilder, this.textPaint, r9, r10, 1.0f, AndroidUtilities.dp(2.0f), false, r14, r9, 10);
        this.bottomLayout = StaticLayoutEx.createStaticLayout(r12, this.textPaint, r9, r10, 1.0f, AndroidUtilities.dp(3.0f), false, r14, r9, 10);
        int r13 = Math.max(r21, r9);
        this.diffTextWidth = r13 - r9;
        float r82 = r13;
        float r52 = r5;
        float r64 = r52 / 2.0f;
        this.giftReceiver.setImageCoords((r82 / 2.0f) - r64, AndroidUtilities.dp(70.0f) - r64, r52, r52);
        int r53 = this.titleLayout.getLineBottom(r5.getLineCount() - 1) + AndroidUtilities.dp(5.0f);
        this.titleHeight = r53;
        this.topHeight = r53 + this.topLayout.getLineBottom(r6.getLineCount() - 1);
        this.bottomHeight = this.bottomLayout.getLineBottom(r5.getLineCount() - 1);
        StaticLayout r54 = this.countriesLayout;
        if (r54 == null) goto L19;
        int r55 = r54.getLineBottom(r54.getLineCount() - 1) + AndroidUtilities.dp(12.0f);
    L20:
        this.countriesHeight = r55;
        int r65 = ((this.measuredHeight + this.topHeight) + r55) + this.bottomHeight;
        this.measuredHeight = r65;
        this.measuredHeight = r65 + AndroidUtilities.dp(128.0f);
        this.measuredWidth = r13;
        if (this.isStars == true) goto L23;
        this.counterIcon = null;
        this.counterStr = "x" + r4.winners_count;
    L27:
        TextPaint r15 = this.counterTextPaint;
        String r56 = this.counterStr;
        r15.getTextBounds(r56, 0, r56.length(), this.counterTextBounds);
        if (this.isStars == false) goto L30;
        this.counterTextBounds.right += AndroidUtilities.dp(20.0f);
    L30:
        Arrays.fill(this.avatarVisible, false);
        this.measuredHeight += AndroidUtilities.dp(30.0f);
        ArrayList r16 = new ArrayList(r4.winners.size());
        ArrayList<Long> r42 = r4.winners;
        int r72 = r42.size();
        int r92 = 0;
    L31:
        if (r92 >= r72) goto L35;
        Long r102 = r42.get(r92);
        r92 = r92 + 1;
        Long r103 = r102;
        if (MessagesController.getInstance(UserConfig.selectedAccount).getUser(r103) == null) goto L31;
        r16.add(r103);
        goto L31
    L35:
        int r73 = 0;
        float r93 = 0.0f;
    L37:
        if (r73 >= r16.size()) goto L62;
        Long r104 = (Long) r16.get(r73);
        long r11 = r104.longValue();
        TLRPC.User r105 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(r104);
        if (r105 == null) goto L51;
        this.avatarVisible[r73] = true;
        this.users[r73] = r105;
        this.userTitles[r73] = TextUtils.ellipsize(Emoji.replaceEmoji(UserObject.getUserName(r105), this.chatTextPaint.getFontMetricsInt(), false), this.chatTextPaint, 0.8f * r82, TextUtils.TruncateAt.END);
        float[] r112 = this.userTitleWidths;
        TextPaint r123 = this.chatTextPaint;
        CharSequence r132 = this.userTitles[r73];
        r112[r73] = r123.measureText(r132, 0, r132.length());
        float r113 = this.userTitleWidths[r73] + AndroidUtilities.dp(40.0f);
        r93 = r93 + r113;
        boolean[] r124 = this.needNewRow;
        if (r73 > 0) goto L43;
        r124[r73] = false;
    L50:
        this.avatarDrawables[r73].setInfo(r105);
        this.avatarImageReceivers[r73].setForUserOrChat(r105, this.avatarDrawables[r73]);
        this.avatarImageReceivers[r73].setImageCoords(0.0f, 0.0f, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
    L52:
        r73 = r73 + 1;
        goto L37
    L43:
        if (r93 <= (0.9f * r82)) goto L45;
        boolean r133 = true;
    L46:
        r124[r73] = r133;
        if (r133 == false) goto L50;
        this.measuredHeight += AndroidUtilities.dp(30.0f);
        r93 = r113;
        goto L50
    L45:
        r133 = false;
        goto L46
    L51:
        this.users[r73] = null;
        this.avatarVisible[r73] = false;
        this.userTitles[r73] = _UrlKt.FRAGMENT_ENCODE_SET;
        this.needNewRow[r73] = false;
        this.userTitleWidths[r73] = AndroidUtilities.dp(20.0f);
        this.avatarDrawables[r73].setInfo(r11, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        goto L52
    L62:
        return;
    L23:
        if (this.counterIcon != null) goto L25;
        this.counterIcon = ApplicationLoader.applicationContext.getResources().getDrawable(org.telegram.messenger.R.drawable.filled_giveaway_stars).mutate();
    L25:
        this.counterStr = LocaleController.formatNumber((int) r4.stars, ',');
        goto L27
    L19:
        r55 = 0;
        goto L20
    L15:
        r12.append(LocaleController.getString(org.telegram.messenger.R.string.BoostingGiveawayResultsMsgAllWinnersReceivedLinks));
        goto L16
    L11:
        r63 = false;
        goto L12
    }
}
