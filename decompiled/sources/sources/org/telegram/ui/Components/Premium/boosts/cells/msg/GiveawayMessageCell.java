package org.telegram.ui.Components.Premium.boosts.cells.msg;

import android.R;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.StateSet;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.StaticLayoutEx;

/* loaded from: classes7.dex */
public class GiveawayMessageCell {
    private static final Map<Integer, String> monthsToEmoticon = null;
    private int additionPrizeHeight;
    private StaticLayout additionPrizeLayout;
    private AvatarDrawable[] avatarDrawables;
    private ImageReceiver[] avatarImageReceivers;
    private boolean[] avatarVisible;
    private int bottomHeight;
    private StaticLayout bottomLayout;
    private Paint chatBgPaint;
    private RectF chatRect;
    private TextPaint chatTextPaint;
    private float[] chatTitleWidths;
    private CharSequence[] chatTitles;
    private TLRPC.Chat[] chats;
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
    private ImageReceiver giftReceiver;
    private boolean isButtonPressed;
    private boolean isContainerPressed;
    private boolean isStars;
    private Paint lineDividerPaint;
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
    private String textDivider;
    private TextPaint textDividerPaint;
    private float textDividerWidth;
    private TextPaint textPaint;
    private int titleHeight;
    private StaticLayout titleLayout;
    private int topHeight;
    private StaticLayout topLayout;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.msg.GiveawayMessageCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ GiveawayMessageCell this$0;

        public AnonymousClass1(GiveawayMessageCell r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            GiveawayMessageCell.m12172$$Nest$fgetparentView(this.this$0).invalidate();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            GiveawayMessageCell.m12172$$Nest$fgetparentView(this.this$0).invalidate();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            GiveawayMessageCell.m12172$$Nest$fgetparentView(this.this$0).invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m12172$$Nest$fgetparentView(GiveawayMessageCell r0) {
        return r0.parentView;
    }

    static {
        HashMap r0 = new HashMap();
        monthsToEmoticon = r0;
        r0.put(1, "1⃣");
        r0.put(3, "2⃣");
        r0.put(6, "3⃣");
        r0.put(12, "4⃣");
        r0.put(24, "5⃣");
    }

    public GiveawayMessageCell(ChatMessageCell r3) {
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
        this.chatTitles = (CharSequence[]) Arrays.copyOf(this.chatTitles, r6);
        this.chatTitleWidths = Arrays.copyOf(this.chatTitleWidths, r6);
        this.needNewRow = Arrays.copyOf(this.needNewRow, r6);
        this.clickRect = (Rect[]) Arrays.copyOf(this.clickRect, r6);
        this.chats = (TLRPC.Chat[]) Arrays.copyOf(this.chats, r6);
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

    private int getChatColor(TLRPC.Chat r1, Theme.ResourcesProvider r2) {
        if (this.messageObject.isOutOwner() == true) goto L5;
        int r0 = ChatObject.getColorId(r1);
        if (r0 < 7) goto L9;
        MessagesController.PeerColors r12 = MessagesController.getInstance(UserConfig.selectedAccount).peerColors;
        if (r12 != null) goto L13;
        MessagesController.PeerColor r02 = null;
    L15:
        if (r02 == null) goto L19;
        return r02.getColor(0, r2);
    L19:
        return Theme.getColor(Theme.keys_avatar_nameInMessage[0], r2);
    L13:
        r02 = r12.getColor(r0);
        goto L15
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
        this.lineDividerPaint = new Paint(1);
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
        this.chatTitles = new CharSequence[10];
        this.chats = new TLRPC.Chat[10];
        this.chatTitleWidths = new float[10];
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

    private void setGiftImage(MessageObject r19) {
        TLRPC.TL_messageMediaGiveaway r0 = (TLRPC.TL_messageMediaGiveaway) r19.messageOwner.media;
        String r1 = UserConfig.getInstance(UserConfig.selectedAccount).premiumGiftsStickerPack;
        if (r1 != null) goto L6;
        MediaDataController.getInstance(UserConfig.selectedAccount).checkPremiumGiftStickers();
        return;
    L6:
        TLRPC.TL_messages_stickerSet r2 = MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSetByName(r1);
        if (r2 != null) goto L9;
        r2 = MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSetByEmojiOrName(r1);
    L9:
        TLRPC.TL_messages_stickerSet r8 = r2;
        TLRPC.Document r3 = null;
        if (r8 == null) goto L36;
        int r02 = r0.months;
        String r03 = monthsToEmoticon.get(Integer.valueOf(r02));
        ArrayList<TLRPC.TL_stickerPack> r4 = r8.packs;
        int r5 = r4.size();
        int r6 = 0;
    L12:
        if (r6 >= r5) goto L32;
        TLRPC.TL_stickerPack r7 = r4.get(r6);
        r6 = r6 + 1;
        TLRPC.TL_stickerPack r72 = r7;
        if (Objects.equals(r72.emoticon, r03) == false) goto L28;
        ArrayList<Long> r73 = r72.documents;
        int r9 = r73.size();
        int r10 = 0;
    L16:
        if (r10 >= r9) goto L28;
        Long r11 = r73.get(r10);
        r10 = r10 + 1;
        long r112 = r11.longValue();
        ArrayList<TLRPC.Document> r13 = r8.documents;
        int r14 = r13.size();
        int r15 = 0;
    L18:
        if (r15 >= r14) goto L23;
        TLRPC.Document r16 = r13.get(r15);
        r15 = r15 + 1;
        TLRPC.Document r22 = r16;
        TLRPC.Document r17 = r3;
        ArrayList<TLRPC.TL_stickerPack> r162 = r4;
        if (r22.id == r112) goto L21;
        r4 = r162;
        r3 = r17;
        goto L18
    L21:
        r3 = r22;
    L24:
        if (r3 != null) goto L29;
        r4 = r162;
    L29:
        if (r3 != null) goto L32;
        r4 = r162;
        goto L12
    L23:
        r162 = r4;
    L28:
        r162 = r4;
    L32:
        if (r3 != null) goto L36;
        if (r8.documents.isEmpty() == true) goto L36;
        r3 = r8.documents.get(0);
    L36:
        if (r3 == null) goto L42;
        SvgHelper.SvgDrawable r62 = DocumentObject.getSvgThumb(r3.thumbs, Theme.key_emptyListPlaceholder, 0.2f);
        if (r62 == null) goto L40;
        r62.overrideWidthAndHeight(512, 512);
    L40:
        this.giftReceiver.setImage(ImageLocation.getForDocument(r3), "160_160_firstframe", r62, "tgs", r8, 1);
        return;
    L42:
        MediaDataController r04 = MediaDataController.getInstance(UserConfig.selectedAccount);
        if (r8 != null) goto L46;
        boolean r23 = true;
    L47:
        r04.loadStickersByEmojiOrName(r1, false, r23);
        return;
    L46:
        r23 = false;
        goto L47
    }

    public boolean checkMotionEvent(MotionEvent r7) {
        MessageObject r0 = this.messageObject;
        if (r0 != null) goto L5;
    L41:
        return false;
    L5:
        if (r0.isGiveaway() == false) goto L41;
        int r02 = (int) r7.getX();
        int r2 = (int) r7.getY();
        if (r7.getAction() != 0) goto L22;
        int r72 = 0;
    L10:
        Rect[] r3 = this.clickRect;
        if (r72 >= r3.length) goto L18;
        if (r3[r72].contains(r02, r2) == true) goto L14;
        r72 = r72 + 1;
        goto L10
    L14:
        this.pressedPos = r72;
        this.selectorDrawable.setHotspot(r02, r2);
        this.isButtonPressed = true;
        setButtonPressed(true);
        return true;
    L18:
        if (this.containerRect.contains(r02, r2) == false) goto L41;
        this.isContainerPressed = true;
        return true;
    L22:
        if (r7.getAction() != 1) goto L33;
        if (this.isButtonPressed == false) goto L30;
        if (this.parentView.getDelegate() == null) goto L28;
        this.parentView.getDelegate().didPressGiveawayChatButton(this.parentView, this.pressedPos);
    L28:
        this.parentView.playSoundEffect(0);
        setButtonPressed(false);
        this.isButtonPressed = false;
    L30:
        if (this.isContainerPressed == false) goto L41;
        this.isContainerPressed = false;
        BoostDialogs.showBulletinAbout(this.messageObject);
        goto L41
    L33:
        if (r7.getAction() == 2) goto L41;
        if (r7.getAction() != 3) goto L41;
        if (this.isButtonPressed == false) goto L40;
        setButtonPressed(false);
    L40:
        this.isButtonPressed = false;
        this.isContainerPressed = false;
        goto L41
    }

    public void draw(Canvas r24, int r25, int r26, Theme.ResourcesProvider r27) {
        Canvas r1 = r24;
        MessageObject r2 = this.messageObject;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.isGiveaway() == true) goto L8;
        return;
    L8:
        if (this.selectorDrawable != null) goto L10;
        int r22 = Theme.getColor(Theme.key_listSelector);
        this.selectorColor = r22;
        Drawable r23 = Theme.createRadSelectorDrawable(r22, 12, 12);
        this.selectorDrawable = r23;
        r23.setCallback(this.parentView);
    L10:
        this.textPaint.setColor(Theme.chat_msgTextPaint.getColor());
        this.textDividerPaint.setColor(Theme.multAlpha(Theme.chat_msgTextPaint.getColor(), 0.45f));
        this.lineDividerPaint.setColor(Theme.multAlpha(Theme.chat_msgTextPaint.getColor(), 0.15f));
        this.countriesTextPaint.setColor(Theme.chat_msgTextPaint.getColor());
        boolean r28 = this.messageObject.isOutOwner();
        TextPaint r3 = this.chatTextPaint;
        if (r28 == false) goto L13;
        int r29 = Theme.key_chat_outPreviewInstantText;
        r3.setColor(Theme.getColor(r29, r27));
        this.counterBgPaint.setColor(Theme.getColor(r29, r27));
        this.chatBgPaint.setColor(Theme.getColor(Theme.key_chat_outReplyLine, r27));
    L15:
        if (this.isStars == false) goto L17;
        this.counterBgPaint.setColor(Theme.getColor(Theme.key_starsGradient1, r27));
    L17:
        r1.save();
        int r11 = r26 - AndroidUtilities.dp(4.0f);
        r1.translate(r11, r25);
        this.containerRect.set(r11, r25, getMeasuredWidth() + r11, getMeasuredHeight() + r25);
        r1.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.saveLayerPaint, 31);
        this.giftReceiver.draw(r1);
        float r12 = 2.0f;
        float r13 = getMeasuredWidth() / 2.0f;
        float r210 = AndroidUtilities.dp(106.0f);
        float r14 = 12.0f;
        int r32 = this.counterTextBounds.width() + AndroidUtilities.dp(12.0f);
        int r4 = this.counterTextBounds.height() + AndroidUtilities.dp(10.0f);
        this.countRect.set(r13 - ((AndroidUtilities.dp(2.0f) + r32) / 2.0f), r210 - ((AndroidUtilities.dp(2.0f) + r4) / 2.0f), ((r32 + AndroidUtilities.dp(2.0f)) / 2.0f) + r13, ((r4 + AndroidUtilities.dp(2.0f)) / 2.0f) + r210);
        r1.drawRoundRect(this.countRect, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), this.clipRectPaint);
        float r33 = r32 / 2.0f;
        float r42 = r4 / 2.0f;
        this.countRect.set(r13 - r33, r210 - r42, r33 + r13, r210 + r42);
        r1.drawRoundRect(this.countRect, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.counterBgPaint);
        Drawable r211 = this.counterIcon;
        if (r211 == null) goto L20;
        r211.setBounds(((int) this.countRect.left) + AndroidUtilities.dp(5.0f), ((int) this.countRect.centerY()) - AndroidUtilities.dp(6.96f), ((int) this.countRect.left) + AndroidUtilities.dp(21.24f), ((int) this.countRect.centerY()) + AndroidUtilities.dp(6.96f));
        this.counterIcon.draw(r1);
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
        r1.drawText(r212, r35, r44, r5);
        r1.restore();
        r1.translate(0.0f, AndroidUtilities.dp(128.0f));
        int r7 = r25 + AndroidUtilities.dp(128.0f);
        r1.save();
        r1.translate(this.diffTextWidth / 2.0f, 0.0f);
        this.titleLayout.draw(r1);
        r1.translate(0.0f, this.titleHeight);
        if (this.additionPrizeLayout == null) goto L31;
        r1.restore();
        r1.save();
        float r213 = (this.titleHeight + this.additionPrizeHeight) - AndroidUtilities.dp(6.0f);
        float r36 = this.measuredWidth / 2.0f;
        r1.drawText(this.textDivider, r36, r213, this.textDividerPaint);
        float r252 = 16.0f;
        r1.drawLine(AndroidUtilities.dp(17.0f), r213 - AndroidUtilities.dp(4.0f), (r36 - (this.textDividerWidth / 2.0f)) - AndroidUtilities.dp(6.0f), r213 - AndroidUtilities.dp(4.0f), this.lineDividerPaint);
        r1 = r24;
        r1.drawLine((r36 + (this.textDividerWidth / 2.0f)) + AndroidUtilities.dp(6.0f), r213 - AndroidUtilities.dp(4.0f), this.measuredWidth - AndroidUtilities.dp(16.0f), r213 - AndroidUtilities.dp(4.0f), this.lineDividerPaint);
        r1.translate((this.measuredWidth - this.additionPrizeLayout.getWidth()) / 2.0f, this.titleHeight);
        this.additionPrizeLayout.draw(r1);
        r1.restore();
        r1.save();
        r1.translate(this.diffTextWidth / 2.0f, this.additionPrizeHeight + this.titleHeight);
    L32:
        this.topLayout.draw(r1);
        r1.restore();
        r1.translate(0.0f, this.topHeight + AndroidUtilities.dp(6.0f));
        int r214 = 0;
        int r37 = 0;
        int r8 = r7 + (this.topHeight + AndroidUtilities.dp(6.0f));
    L33:
        boolean[] r45 = this.avatarVisible;
        if (r214 >= r45.length) goto L62;
        if (r45[r214] == true) goto L37;
        r214 = r214 + 1;
        goto L33
    L37:
        r1.save();
        int r52 = r214;
        float r46 = 0.0f;
    L38:
        float r262 = r12;
        r46 = r46 + (this.chatTitleWidths[r52] + AndroidUtilities.dp(40.0f));
        r52 = r52 + 1;
        boolean[] r72 = this.avatarVisible;
        if (r52 >= r72.length) goto L46;
        if (this.needNewRow[r52] == true) goto L46;
        if (r72[r52] == false) goto L46;
        r12 = r262;
    L46:
        float r47 = r13 - (r46 / r262);
        r1.translate(r47, 0.0f);
        int r48 = ((int) r47) + r11;
        int r122 = r214;
    L47:
        int r215 = getChatColor(this.chats[r122], r27);
        int r53 = this.pressedPos;
        if (r53 < 0) goto L51;
        if (r53 != r122) goto L51;
        int r18 = r215;
    L52:
        this.chatTextPaint.setColor(r215);
        this.chatBgPaint.setColor(r215);
        this.chatBgPaint.setAlpha(25);
        this.avatarImageReceivers[r122].draw(r1);
        CharSequence r216 = this.chatTitles[r122];
        int r38 = r48;
        float r21 = r14;
        r1.drawText(r216, 0, r216.length(), AndroidUtilities.dp(30.0f), AndroidUtilities.dp(r252), this.chatTextPaint);
        this.chatRect.set(0.0f, 0.0f, this.chatTitleWidths[r122] + AndroidUtilities.dp(40.0f), AndroidUtilities.dp(24.0f));
        r1.drawRoundRect(this.chatRect, AndroidUtilities.dp(r21), AndroidUtilities.dp(r21), this.chatBgPaint);
        float r39 = r38;
        this.clickRect[r122].set(r38, r8, (int) (this.chatRect.width() + r39), AndroidUtilities.dp(24.0f) + r8);
        r1.translate(this.chatRect.width() + AndroidUtilities.dp(6.0f), 0.0f);
        r48 = (int) (r39 + (this.chatRect.width() + AndroidUtilities.dp(6.0f)));
        r122 = r122 + 1;
        boolean[] r217 = this.avatarVisible;
        if (r122 >= r217.length) goto L60;
        if (this.needNewRow[r122] == true) goto L60;
        if (r217[r122] == false) goto L60;
        r37 = r18;
        r14 = r21;
    L60:
        r1.restore();
        r1.translate(0.0f, AndroidUtilities.dp(30.0f));
        r8 = r8 + AndroidUtilities.dp(30.0f);
        r214 = r122;
        r37 = r18;
        r14 = r21;
        r12 = r262;
    L51:
        r18 = r37;
        goto L52
    L62:
        float r263 = r12;
        if (this.countriesLayout == null) goto L65;
        r1.save();
        r1.translate((this.measuredWidth - this.countriesLayout.getWidth()) / r263, AndroidUtilities.dp(4.0f));
        this.countriesLayout.draw(r1);
        r1.restore();
        r1.translate(0.0f, this.countriesHeight);
    L65:
        r1.translate(0.0f, AndroidUtilities.dp(6.0f));
        r1.save();
        r1.translate(this.diffTextWidth / r263, 0.0f);
        this.bottomLayout.draw(r1);
        r1.restore();
        r1.restore();
        if (this.pressedPos >= 0) goto L68;
        return;
    L68:
        if (Theme.isCurrentThemeDark() == false) goto L70;
        float r15 = 0.12f;
    L71:
        int r16 = Theme.multAlpha(r37, r15);
        if (this.selectorColor == r16) goto L74;
        Drawable r218 = this.selectorDrawable;
        this.selectorColor = r16;
        Theme.setSelectorDrawableColor(r218, r16, true);
    L74:
        this.selectorDrawable.setBounds(this.clickRect[this.pressedPos]);
        this.selectorDrawable.setCallback(this.parentView);
        return;
    L70:
        r15 = 0.1f;
        goto L71
    L31:
        r252 = 16.0f;
        goto L32
    L27:
        r5 = this.counterTextPaint;
        goto L28
    L23:
        r43 = 0.0f;
        goto L24
    L13:
        int r219 = Theme.key_chat_inPreviewInstantText;
        r3.setColor(Theme.getColor(r219, r27));
        this.counterBgPaint.setColor(Theme.getColor(r219, r27));
        this.chatBgPaint.setColor(Theme.getColor(Theme.key_chat_inReplyLine, r27));
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
        if (r0.isGiveaway() == false) goto L15;
        Drawable r02 = this.selectorDrawable;
        if (r02 == null) goto L16;
        if (r2 == false) goto L12;
        r02.setCallback(new AnonymousClass1(this));
        this.selectorDrawable.setState(this.pressedState);
        this.parentView.invalidate();
        return;
    L12:
        r02.setState(StateSet.NOTHING);
        this.parentView.invalidate();
        return;
    L16:
        return;
    }

    public void setMessageContent(MessageObject r31, int r32, int r33) {
        TLRPC.Chat r2 = null;
        this.messageObject = null;
        this.titleLayout = null;
        this.additionPrizeLayout = null;
        this.topLayout = null;
        this.bottomLayout = null;
        this.countriesLayout = null;
        this.measuredHeight = 0;
        this.measuredWidth = 0;
        this.additionPrizeHeight = 0;
        this.textDividerWidth = 0.0f;
        if (r31.isGiveaway() == false) goto L102;
        this.messageObject = r31;
        init();
        createImages();
        setGiftImage(r31);
        TLRPC.TL_messageMediaGiveaway r5 = (TLRPC.TL_messageMediaGiveaway) r31.messageOwner.media;
        if ((r5.flags & 32) == 0) goto L8;
        boolean r6 = true;
    L9:
        this.isStars = r6;
        checkArraysLimits(r5.channels.size());
        int r62 = AndroidUtilities.dp(148.0f);
        if (AndroidUtilities.isTablet() == false) goto L13;
        int r8 = AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(80.0f);
    L12:
        int r11 = r8;
        MessagesController r82 = MessagesController.getInstance(UserConfig.selectedAccount);
        boolean r9 = r31.isForwarded();
        TLRPC.Message r1 = r31.messageOwner;
        if (r9 == false) goto L17;
        TLRPC.Peer r12 = r1.fwd_from.from_id;
    L18:
        boolean r13 = ChatObject.isChannelAndNotMegaGroup(r82.getChat(Long.valueOf(-MessageObject.getPeerId(r12))));
        SpannableStringBuilder r83 = AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.BoostingGiveawayPrizes));
        SpannableStringBuilder r92 = new SpannableStringBuilder(r83);
        r92.setSpan(new RelativeSizeSpan(1.05f), 0, r83.length(), 33);
        SpannableStringBuilder r84 = new SpannableStringBuilder();
        if (this.isStars == false) goto L21;
        r84.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("BoostingStarsGiveawayMsgInfoPlural1", (int) r5.stars)));
        r84.append("\n");
        r84.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingStarsGiveawayMsgInfoPlural2", r5.quantity, new Object[0])));
    L22:
        SpannableStringBuilder r10 = new SpannableStringBuilder();
        r10.append(r84);
        r10.append("\n\n");
        r10.setSpan(new RelativeSizeSpan(0.4f), r10.length() - 1, r10.length(), 33);
        SpannableStringBuilder r7 = AndroidUtilities.replaceTags(LocaleController.getString("BoostingGiveawayMsgParticipants", org.telegram.messenger.R.string.BoostingGiveawayMsgParticipants));
        r10.append(r7);
        r10.setSpan(new RelativeSizeSpan(1.05f), r84.length() + 2, (r84.length() + 2) + r7.length(), 33);
        r10.append("\n");
        if (r5.only_new_subscribers == false) goto L28;
        if (r13 == false) goto L26;
        String r14 = "BoostingGiveawayMsgNewSubsPlural";
    L27:
        r10.append(LocaleController.formatPluralString(r14, r5.channels.size(), new Object[0]));
    L32:
        SpannableStringBuilder r15 = AndroidUtilities.replaceTags(LocaleController.getString("BoostingWinnersDate", org.telegram.messenger.R.string.BoostingWinnersDate));
        SpannableStringBuilder r72 = new SpannableStringBuilder(r15);
        r72.setSpan(new RelativeSizeSpan(1.05f), 0, r15.length(), 33);
        Date r16 = new Date(r5.until_date * 1000);
        String r85 = LocaleController.getInstance().getFormatterGiveawayCard().format(r16);
        String r17 = LocaleController.getInstance().getFormatterDay().format(r16);
        r72.append("\n");
        r72.append(LocaleController.formatString("formatDateAtTime", org.telegram.messenger.R.string.formatDateAtTime, new Object[]{r85, r17}));
        TextPaint r102 = this.textPaint;
        Layout.Alignment r122 = Layout.Alignment.ALIGN_CENTER;
        float r86 = 2.0f;
        float r142 = AndroidUtilities.dp(2.0f);
        TextUtils.TruncateAt r162 = TextUtils.TruncateAt.END;
        this.titleLayout = StaticLayoutEx.createStaticLayout(r92, r102, r11, r122, 1.0f, r142, false, r162, r11, 10);
        this.topLayout = StaticLayoutEx.createStaticLayout(r10, this.textPaint, r11, r122, 1.0f, AndroidUtilities.dp(2.0f), false, r162, r11, 10);
        this.bottomLayout = StaticLayoutEx.createStaticLayout(r72, this.textPaint, r11, r122, 1.0f, AndroidUtilities.dp(3.0f), false, r162, r11, 10);
        int r18 = 0;
        int r73 = 0;
    L34:
        if (r18 >= this.titleLayout.getLineCount()) goto L36;
        r73 = (int) Math.max(r73, Math.ceil(this.titleLayout.getLineWidth(r18)));
        r18 = r18 + 1;
        goto L34
    L36:
        int r19 = 0;
    L38:
        if (r19 >= this.topLayout.getLineCount()) goto L40;
        r73 = (int) Math.max(r73, Math.ceil(this.topLayout.getLineWidth(r19)));
        r19 = r19 + 1;
        goto L38
    L40:
        int r110 = 0;
    L42:
        if (r110 >= this.bottomLayout.getLineCount()) goto L45;
        r73 = (int) Math.max(r73, Math.ceil(this.bottomLayout.getLineWidth(r110)));
        r110 = r110 + 1;
        goto L42
    L45:
        if (r73 >= AndroidUtilities.dp(180.0f)) goto L47;
        r73 = AndroidUtilities.dp(180.0f);
    L47:
        int r22 = r73;
        String r111 = r5.prize_description;
        if (r111 == null) goto L53;
        if (r111.isEmpty() == true) goto L53;
        StaticLayout r112 = StaticLayoutEx.createStaticLayout(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayMsgPrizes", r5.quantity, new Object[]{r5.prize_description})), this.countriesTextPaint.getFontMetricsInt(), false), this.textPaint, r22, Layout.Alignment.ALIGN_CENTER, 1.0f, AndroidUtilities.dp(2.0f), false, TextUtils.TruncateAt.END, r22, 20);
        this.additionPrizeLayout = r112;
        this.additionPrizeHeight = r112.getLineBottom(r112.getLineCount() - 1) + AndroidUtilities.dp(22.0f);
        String r113 = LocaleController.getString(org.telegram.messenger.R.string.BoostingGiveawayMsgWithDivider);
        this.textDivider = r113;
        this.textDividerWidth = this.textDividerPaint.measureText(r113, 0, r113.length());
    L53:
        if (r5.countries_iso2.size() <= 0) goto L64;
        ArrayList r114 = new ArrayList();
        ArrayList<String> r93 = r5.countries_iso2;
        int r103 = r93.size();
        int r123 = 0;
    L55:
        if (r123 >= r103) goto L61;
        String r132 = r93.get(r123);
        r123 = r123 + 1;
        String r133 = r132;
        String r143 = new Locale(_UrlKt.FRAGMENT_ENCODE_SET, r133).getDisplayCountry(Locale.getDefault());
        String r134 = LocaleController.getLanguageFlag(r133);
        SpannableStringBuilder r152 = new SpannableStringBuilder();
        if (r134 == null) goto L59;
        float r312 = r86;
        r152.append(r134).append(" ");
    L60:
        r152.append(r143);
        r114.add(r152);
        r86 = r312;
        goto L55
    L59:
        r312 = r86;
        goto L60
    L61:
        float r313 = r86;
        if (r114.isEmpty() == true) goto L65;
        this.countriesLayout = StaticLayoutEx.createStaticLayout(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingGiveAwayFromCountries", org.telegram.messenger.R.string.BoostingGiveAwayFromCountries, new Object[]{TextUtils.join(", ", r114)})), this.countriesTextPaint.getFontMetricsInt(), false), this.countriesTextPaint, r22, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r22, 10);
    L65:
        int r115 = Math.max(r33, Math.min(r22 + AndroidUtilities.dp(38.0f), r11));
        this.diffTextWidth = r115 - r11;
        float r94 = r115;
        float r63 = r62;
        float r116 = r63 / r313;
        this.giftReceiver.setImageCoords((r94 / r313) - r116, AndroidUtilities.dp(42.0f) - r116, r63, r63);
        int r64 = this.titleLayout.getLineBottom(r6.getLineCount() - 1) + AndroidUtilities.dp(5.0f);
        this.titleHeight = r64;
        this.topHeight = (r64 + this.additionPrizeHeight) + this.topLayout.getLineBottom(r8.getLineCount() - 1);
        this.bottomHeight = this.bottomLayout.getLineBottom(r6.getLineCount() - 1);
        StaticLayout r65 = this.countriesLayout;
        if (r65 == null) goto L68;
        int r66 = r65.getLineBottom(r65.getLineCount() - 1) + AndroidUtilities.dp(12.0f);
    L69:
        this.countriesHeight = r66;
        int r87 = ((this.measuredHeight + this.topHeight) + r66) + this.bottomHeight;
        this.measuredHeight = r87;
        this.measuredHeight = r87 + AndroidUtilities.dp(128.0f);
        this.measuredWidth = r115;
        if (this.isStars == true) goto L72;
        this.counterIcon = null;
        this.counterStr = "x" + r5.quantity;
    L76:
        TextPaint r117 = this.counterTextPaint;
        String r67 = this.counterStr;
        r117.getTextBounds(r67, 0, r67.length(), this.counterTextBounds);
        if (r5.stars == 0) goto L79;
        this.counterTextBounds.right += AndroidUtilities.dp(20.0f);
    L79:
        Arrays.fill(this.avatarVisible, false);
        this.measuredHeight += AndroidUtilities.dp(30.0f);
        ArrayList r118 = new ArrayList(r5.channels.size());
        ArrayList<Long> r52 = r5.channels;
        int r104 = r52.size();
        int r119 = 0;
    L80:
        if (r119 >= r104) goto L84;
        Long r124 = r52.get(r119);
        r119 = r119 + 1;
        Long r125 = r124;
        if (MessagesController.getInstance(UserConfig.selectedAccount).getChat(r125) == null) goto L80;
        r118.add(r125);
        goto L80
    L84:
        int r53 = 0;
        float r105 = 0.0f;
    L86:
        if (r53 >= r118.size()) goto L117;
        Long r1110 = (Long) r118.get(r53);
        long r126 = r1110.longValue();
        TLRPC.Chat r1111 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(r1110);
        if (r1111 == null) goto L100;
        this.avatarVisible[r53] = true;
        this.chats[r53] = r1111;
        TLRPC.Chat r163 = r2;
        this.chatTitles[r53] = TextUtils.ellipsize(Emoji.replaceEmoji(r1111.title, this.chatTextPaint.getFontMetricsInt(), false), this.chatTextPaint, 0.8f * r94, TextUtils.TruncateAt.END);
        float[] r23 = this.chatTitleWidths;
        TextPaint r127 = this.chatTextPaint;
        CharSequence r135 = this.chatTitles[r53];
        r23[r53] = r127.measureText(r135, 0, r135.length());
        float r24 = this.chatTitleWidths[r53] + AndroidUtilities.dp(40.0f);
        r105 = r105 + r24;
        boolean[] r128 = this.needNewRow;
        if (r53 > 0) goto L92;
        r128[r53] = false;
    L99:
        this.avatarDrawables[r53].setInfo(r1111);
        this.avatarImageReceivers[r53].setForUserOrChat(r1111, this.avatarDrawables[r53]);
        this.avatarImageReceivers[r53].setImageCoords(0.0f, 0.0f, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
    L101:
        r53 = r53 + 1;
        r2 = r163;
        goto L86
    L92:
        if (r105 <= (0.9f * r94)) goto L94;
        boolean r136 = true;
    L95:
        r128[r53] = r136;
        if (r136 == false) goto L99;
        this.measuredHeight += AndroidUtilities.dp(30.0f);
        r105 = r24;
        goto L99
    L94:
        r136 = false;
        goto L95
    L100:
        r163 = r2;
        this.chats[r53] = r163;
        this.avatarVisible[r53] = false;
        this.chatTitles[r53] = _UrlKt.FRAGMENT_ENCODE_SET;
        this.needNewRow[r53] = false;
        this.chatTitleWidths[r53] = AndroidUtilities.dp(20.0f);
        this.avatarDrawables[r53].setInfo(r126, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        goto L101
    L117:
        return;
    L72:
        if (this.counterIcon != null) goto L74;
        this.counterIcon = ApplicationLoader.applicationContext.getResources().getDrawable(org.telegram.messenger.R.drawable.filled_giveaway_stars).mutate();
    L74:
        this.counterStr = LocaleController.formatNumber((int) r5.stars, ',');
        goto L76
    L68:
        r66 = 0;
        goto L69
    L64:
        r313 = 2.0f;
        goto L65
    L26:
        r14 = "BoostingGiveawayMsgNewSubsGroupPlural";
        goto L27
    L28:
        if (r13 == false) goto L30;
        String r120 = "BoostingGiveawayMsgAllSubsPlural";
    L31:
        r10.append(LocaleController.formatPluralString(r120, r5.channels.size(), new Object[0]));
        goto L32
    L30:
        r120 = "BoostingGiveawayMsgAllSubsGroupPlural";
        goto L31
    L21:
        r84.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("BoostingGiveawayMsgInfoPlural1", r5.quantity)));
        r84.append("\n");
        r84.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayMsgInfoPlural2", r5.quantity, new Object[]{LocaleController.formatPluralString("BoldMonths", r5.months, new Object[0])})));
        goto L22
    L17:
        r12 = r1.peer_id;
        goto L18
    L13:
        r8 = r32 - AndroidUtilities.dp(80.0f);
        goto L12
    L8:
        r6 = false;
        goto L9
    }
}
