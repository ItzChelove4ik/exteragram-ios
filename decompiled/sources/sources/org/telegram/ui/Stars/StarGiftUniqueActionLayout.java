package org.telegram.ui.Stars;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Gifts.GiftMessageDrawable;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;

/* loaded from: classes7.dex */
public class StarGiftUniqueActionLayout {
    TLRPC.TL_messageActionStarGiftUnique action;
    private final FactorAnimator animatorVisualWidth;
    private boolean attached;
    private TL_stars.starGiftAttributeBackdrop backdrop;
    private final Paint backgroundPaint;
    private final Path backgroundPath;
    private final RectF backgroundRect;
    private final ButtonBounce bounce;
    private boolean burned;
    private final Paint buttonBackgroundPaint;
    private final ButtonBounce buttonBounce;
    private float buttonHeight;
    private final StarsReactionsSheet.Particles buttonParticles;
    private final Path buttonPath;
    private final RectF buttonRect;
    private Text buttonText;
    private float buttonY;
    private final int currentAccount;
    MessageObject currentMessageObject;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emoji;
    private RadialGradient gradient;
    private int gradientRadius;
    private boolean hasGiftMessage;
    int height;
    public final ImageReceiver imageReceiver;
    private final Matrix matrix;
    private final GiftMessageDrawable messageDrawable;
    private float messageY;
    private TL_stars.starGiftAttributeModel model;
    private float nameWidth;
    private Runnable onButtonClick;
    private TL_stars.starGiftAttributePattern pattern;
    public boolean repost;
    private final Theme.ResourcesProvider resourcesProvider;
    private final GiftSheet.RibbonDrawable ribbon;
    private Text subtitle;
    private float subtitleY;
    private final ArrayList<Row> table;
    private Text title;
    private float titleY;
    private float valueWidth;
    private final View view;
    int width;
    private boolean widthExpanded;

    public static final class Row {
        public final Text name;
        public final Text value;
        public final float y;

        public Row(float r3, CharSequence r4, CharSequence r5) {
            this.name = new Text(r4, 12.0f);
            this.value = new Text(r5, 12.0f, AndroidUtilities.bold());
            this.y = r3 + (getHeight() / 2.0f);
        }

        public float getHeight() {
            return Math.max(this.name.getHeight(), this.value.getHeight());
        }
    }

    /* renamed from: $r8$lambda$TtxJ9b99f7-UptvrUxMqZxZ2ODo, reason: not valid java name */
    public static /* synthetic */ void m19093$r8$lambda$TtxJ9b99f7UptvrUxMqZxZ2ODo(StarGiftUniqueActionLayout r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public StarGiftUniqueActionLayout(int r11, View r12, Theme.ResourcesProvider r13) {
        this.backgroundPaint = new Paint(1);
        this.matrix = new Matrix();
        this.backgroundRect = new RectF();
        this.backgroundPath = new Path();
        this.table = new ArrayList();
        GiftMessageDrawable r0 = new GiftMessageDrawable();
        this.messageDrawable = r0;
        this.buttonRect = new RectF();
        this.buttonPath = new Path();
        this.buttonBackgroundPaint = new Paint();
        this.buttonParticles = new StarsReactionsSheet.Particles(1, 25);
        this.animatorVisualWidth = new FactorAnimator(0, new StarGiftUniqueActionLayout$$ExternalSyntheticLambda0(this), CubicBezierInterpolator.EASE_OUT_QUINT, 320);
        this.currentAccount = r11;
        this.view = r12;
        this.resourcesProvider = r13;
        this.ribbon = new GiftSheet.RibbonDrawable(r12, 1.0f);
        this.buttonBounce = new ButtonBounce(r12);
        this.bounce = new ButtonBounce(r12);
        this.imageReceiver = new ImageReceiver(r12);
        this.emoji = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r12, AndroidUtilities.dp(28.0f));
        r0.setParentView(r12);
    }

    private void checkAnimatedWidth(boolean r2) {
        FactorAnimator r0 = this.animatorVisualWidth;
        if (r2 == false) goto L8;
        int r22 = Math.round(r0.getToFactor());
        int r02 = this.width;
        if (r22 == r02) goto L10;
        this.animatorVisualWidth.animateTo(r02);
        return;
    L10:
        return;
    L8:
        r0.forceFactor(this.width);
    }

    private void invalidate() {
        View r1 = this.view;
        if ((r1 instanceof ChatActionCell) == false) goto L6;
        ((ChatActionCell) r1).invalidateOutbounds();
        return;
    L6:
        r1.invalidate();
    }

    private /* synthetic */ void lambda$new$0(int r1, float r2, float r3, FactorAnimator r4) {
        invalidate();
    }

    private void setInternal(MessageObject r24, TLRPC.TL_messageActionStarGiftUnique r25, TL_stars.TL_starGiftUnique r26, boolean r27) {
        float r3 = this.width;
        if ((!r25.upgrade) != r24.isOutOwner()) goto L5;
        long r4 = UserConfig.getInstance(this.currentAccount).getClientUserId();
    L6:
        TLRPC.Peer r7 = r25.from_id;
        if (r7 == null) goto L9;
        r4 = DialogObject.getPeerDialogId(r7);
    L9:
        String r42 = DialogObject.getShortName(r4);
        float r72 = ((AndroidUtilities.dp(10.0f) + 0.0f) + AndroidUtilities.dp(110.0f)) + AndroidUtilities.dp(9.33f);
        if (this.repost == false) goto L13;
        this.title = new Text(r26.title, 14.0f, AndroidUtilities.bold());
    L28:
        this.titleY = (this.title.getHeight() / 2.0f) + r72;
        float r73 = (r72 + this.title.getHeight()) + AndroidUtilities.dp(3.0f);
        if (this.repost == false) goto L31;
        this.subtitle = new Text(LocaleController.formatPluralStringComma("Gift2CollectionNumber", r26.num), 12.0f, AndroidUtilities.bold());
        float r16 = 3.0f;
    L32:
        this.subtitleY = (this.subtitle.getHeight() / 2.0f) + r73;
        float r74 = r73 + this.subtitle.getHeight();
        if (this.repost == false) goto L35;
        float r43 = 14.0f;
    L36:
        float r75 = r74 + AndroidUtilities.dp(r43);
        this.table.clear();
        this.nameWidth = 0.0f;
        this.valueWidth = 0.0f;
        TLRPC.TL_textWithEntities r44 = r25.message;
        TLObject r11 = null;
        if (r44 == null) goto L53;
        TextPaint r12 = this.messageDrawable.getTextPaint();
        SpannableStringBuilder r13 = new SpannableStringBuilder(r44.text);
        MessageObject.addEntitiesToText(r13, r44.entities, false, false, false, false);
        Spannable r45 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r13, r12.getFontMetricsInt(), false), r44.entities, r12.getFontMetricsInt());
        if (r25.name_hidden == true) goto L45;
        TLRPC.Peer r8 = r25.from_id;
        int r112 = this.currentAccount;
        if (r8 == null) goto L44;
        r11 = MessagesController.getInstance(r112).getUserOrChat(DialogObject.getPeerDialogId(r25.from_id));
        goto L45
    L44:
        r11 = MessagesController.getInstance(r112).getUserOrChat(r24.getFromChatId());
    L45:
        this.hasGiftMessage = true;
        this.messageDrawable.setUser(r11);
        this.messageDrawable.setMessage(r45);
        this.messageDrawable.measure(((int) r3) - AndroidUtilities.dp(24.0f));
        if (this.widthExpanded == false) goto L48;
    L51:
        float r76 = r75 + AndroidUtilities.dp(4.0f);
        this.messageY = r76;
        r75 = r76 + this.messageDrawable.getMinimumHeight();
        float r1 = AndroidUtilities.dp(r16);
    L52:
        r75 = r75 + r1;
    L71:
        float r77 = r75 + AndroidUtilities.dp(11.66f);
        if (this.repost == true) goto L75;
        this.buttonY = r77;
        this.buttonText = new Text(LocaleController.getString(R.string.Gift2UniqueView), 14.0f, AndroidUtilities.bold());
        float r14 = AndroidUtilities.dp(30.0f);
        this.buttonHeight = r14;
        r77 = r77 + r14;
        int r15 = AndroidUtilities.dp(11.0f);
    L76:
        this.height = (int) (r77 + r15);
        return;
    L75:
        r15 = AndroidUtilities.dp(10.0f);
        goto L76
    L48:
        if (this.messageDrawable.getLineCount() <= 3) goto L51;
        this.widthExpanded = true;
        this.width = (int) (this.width * (Math.min(0.4f, (this.messageDrawable.getLineCount() - 3) * 0.1f) + 1.0f));
        setInternal(r24, r25, r26, r27);
        return;
    L53:
        this.hasGiftMessage = false;
        this.messageDrawable.setUser(null);
        this.messageDrawable.setMessage(null);
        if (this.model == null) goto L60;
        if (this.table.isEmpty() == true) goto L58;
        r75 = r75 + AndroidUtilities.dp(6.0f);
    L58:
        Row r17 = new Row(r75, LocaleController.getString(R.string.Gift2AttributeModel), this.model.name);
        this.table.add(r17);
        float r82 = r3 * 0.5f;
        r17.name.ellipsize(r82);
        this.nameWidth = Math.max(this.nameWidth, r17.name.getCurrentWidth());
        r17.value.ellipsize(r82);
        this.valueWidth = Math.max(this.valueWidth, r17.value.getCurrentWidth());
        r75 = r75 + r17.getHeight();
    L60:
        if (this.backdrop == null) goto L66;
        if (this.table.isEmpty() == true) goto L64;
        r75 = r75 + AndroidUtilities.dp(6.0f);
    L64:
        Row r18 = new Row(r75, LocaleController.getString(R.string.Gift2AttributeBackdrop), this.backdrop.name);
        this.table.add(r18);
        float r83 = r3 * 0.5f;
        r18.name.ellipsize(r83);
        this.nameWidth = Math.max(this.nameWidth, r18.name.getCurrentWidth());
        r18.value.ellipsize(r83);
        this.valueWidth = Math.max(this.valueWidth, r18.value.getCurrentWidth());
        r75 = r75 + r18.getHeight();
    L66:
        if (this.pattern == null) goto L71;
        if (this.table.isEmpty() == true) goto L70;
        r75 = r75 + AndroidUtilities.dp(6.0f);
    L70:
        Row r19 = new Row(r75, LocaleController.getString(R.string.Gift2AttributeSymbol), this.pattern.name);
        this.table.add(r19);
        float r32 = r3 * 0.5f;
        r19.name.ellipsize(r32);
        this.nameWidth = Math.max(this.nameWidth, r19.name.getCurrentWidth());
        r19.value.ellipsize(r32);
        this.valueWidth = Math.max(this.valueWidth, r19.value.getCurrentWidth());
        r1 = r19.getHeight();
        goto L52
    L35:
        r43 = 11.0f;
        goto L36
    L31:
        StringBuilder r132 = new StringBuilder();
        r132.append(r26.title);
        r132.append(" #");
        r16 = 3.0f;
        r132.append(LocaleController.formatNumber(r26.num, ','));
        this.subtitle = new Text(r132.toString(), 12.0f);
        goto L32
    L13:
        if (r25.peer == null) goto L15;
    L27:
        this.title = new Text(LocaleController.getString(R.string.Gift2UniqueTitle2), 14.0f, AndroidUtilities.bold());
        goto L28
    L15:
        if (UserObject.isService(r24.getDialogId()) == true) goto L27;
        if (r24.getDialogId() == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L20;
        this.title = new Text(LocaleController.formatString(R.string.Gift2UniqueTitle, new Object[]{r42}), 14.0f, AndroidUtilities.bold());
        goto L28
    L20:
        if (r26.crafted == false) goto L23;
        this.title = new Text(LocaleController.getString(R.string.Gift2ActionCraftedTitle), 14.0f, AndroidUtilities.bold());
        goto L28
    L23:
        if (r25.resale_amount == null) goto L25;
        this.title = new Text(LocaleController.getString(R.string.Gift2ActionPurchasedTitle), 14.0f, AndroidUtilities.bold());
        goto L28
    L25:
        this.title = new Text(LocaleController.getString(R.string.Gift2ActionUpgradedTitle), 14.0f, AndroidUtilities.bold());
        goto L28
    L5:
        r4 = r24.getDialogId();
        goto L6
    }

    private void setInternal2(TL_stars.TL_starGiftUnique r20, long r21, TLRPC.TL_textWithEntities r23, String r24) {
        float r5 = this.width;
        float r6 = ((AndroidUtilities.dp(10.0f) + 0.0f) + AndroidUtilities.dp(110.0f)) + AndroidUtilities.dp(9.33f);
        Text r9 = new Text(LocaleController.formatString(R.string.Gift2UniqueTitle, new Object[]{DialogObject.getShortName(r21)}), 14.0f, AndroidUtilities.bold());
        this.title = r9;
        this.titleY = (r9.getHeight() / 2.0f) + r6;
        float r62 = (r6 + this.title.getHeight()) + AndroidUtilities.dp(3.0f);
        Text r10 = new Text(r20.title + " #" + LocaleController.formatNumber(r20.num, ','), 12.0f);
        this.subtitle = r10;
        this.subtitleY = (r10.getHeight() / 2.0f) + r62;
        float r63 = (r62 + this.subtitle.getHeight()) + ((float) AndroidUtilities.dp(11.0f));
        this.table.clear();
        this.nameWidth = 0.0f;
        this.valueWidth = 0.0f;
        TextPaint r7 = this.messageDrawable.getTextPaint();
        if (r23 != null) goto L5;
    L7:
        SpannableStringBuilder r72 = new SpannableStringBuilder(LocaleController.getString(R.string.GiftMessageHint));
        r72.setSpan(new ForegroundColorSpan(-1593835521), 0, r72.length(), 33);
        Spanned r73 = r72;
    L9:
        if (r21 == 0) goto L11;
        TLObject r102 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r21);
    L12:
        this.hasGiftMessage = true;
        this.messageDrawable.setUser(r102);
        this.messageDrawable.setMessage(r73);
        this.messageDrawable.measure(((int) r5) - AndroidUtilities.dp(24.0f));
        if (this.widthExpanded == false) goto L15;
    L18:
        float r64 = r63 + AndroidUtilities.dp(4.0f);
        this.messageY = r64;
        float r65 = ((r64 + this.messageDrawable.getMinimumHeight()) + AndroidUtilities.dp(3.0f)) + AndroidUtilities.dp(11.66f);
        this.buttonY = r65;
        this.buttonText = new Text(r24, 14.0f, AndroidUtilities.bold());
        float r1 = AndroidUtilities.dp(30.0f);
        this.buttonHeight = r1;
        this.height = (int) ((r65 + r1) + AndroidUtilities.dp(11.0f));
        return;
    L15:
        if (this.messageDrawable.getLineCount() <= 3) goto L18;
        this.widthExpanded = true;
        this.width = (int) (this.width * (Math.min(0.4f, (this.messageDrawable.getLineCount() - 3) * 0.1f) + 1.0f));
        setInternal2(r20, r21, r23, r24);
        return;
    L11:
        r102 = null;
        goto L12
    L5:
        if (TextUtils.isEmpty(r23.text) == true) goto L7;
        SpannableStringBuilder r13 = new SpannableStringBuilder(r23.text);
        MessageObject.addEntitiesToText(r13, r23.entities, false, false, false, false);
        r73 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r13, r7.getFontMetricsInt(), false), r23.entities, r7.getFontMetricsInt());
        goto L9
    }

    public void attach() {
        this.attached = true;
        if (this.action == null) goto L6;
        this.imageReceiver.onAttachedToWindow();
        this.emoji.attach();
        this.messageDrawable.attach();
        return;
    }

    public void detach() {
        this.attached = false;
        this.imageReceiver.onDetachedFromWindow();
        this.emoji.detach();
        this.messageDrawable.detach();
    }

    public void draw(Canvas r19) {
        float r9 = getWidth() / 2.0f;
        this.backgroundRect.set(0.0f, 0.0f, getWidth(), getHeight());
        int r1 = ((int) (this.backgroundRect.width() + this.backgroundRect.height())) / 2;
        if (this.backdrop == null) goto L10;
        if (this.gradient != null) goto L7;
    L8:
        this.gradientRadius = r1;
        float r14 = r1;
        TL_stars.starGiftAttributeBackdrop r12 = this.backdrop;
        this.gradient = new RadialGradient(0.0f, 0.0f, r14, new int[]{r12.center_color | (-16777216), r12.edge_color | (-16777216)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        goto L10
    L7:
        if (this.gradientRadius != r1) goto L8;
    L10:
        if (this.gradient == null) goto L12;
        this.matrix.reset();
        this.matrix.postTranslate(r9, r9);
        this.gradient.setLocalMatrix(this.matrix);
        this.backgroundPaint.setShader(this.gradient);
    L12:
        this.backgroundPath.rewind();
        this.backgroundPath.addRoundRect(this.backgroundRect, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), Path.Direction.CW);
        r19.save();
        float r13 = this.bounce.getScale(0.0125f);
        r19.scale(r13, r13, this.backgroundRect.centerX(), this.backgroundRect.centerY());
        r19.save();
        r19.clipPath(this.backgroundPath);
        r19.drawPaint(this.backgroundPaint);
        r19.save();
        r19.translate(r9, AndroidUtilities.dp(65.0f));
        TL_stars.starGiftAttributeBackdrop r15 = this.backdrop;
        if (r15 == null) goto L15;
        this.emoji.setColor(Integer.valueOf(r15.pattern_color | (-16777216)));
    L15:
        StarGiftPatterns.drawPattern(r19, 1, this.emoji, this.backgroundRect.width(), this.backgroundRect.height(), 1.0f, 1.1f);
        r19.restore();
        this.imageReceiver.setImageCoords(r9 - (AndroidUtilities.dp(110.0f) / 2.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(110.0f), AndroidUtilities.dp(110.0f));
        this.imageReceiver.draw(r19);
        int r16 = Theme.multAlpha(-1, 0.6f);
        TL_stars.starGiftAttributeBackdrop r3 = this.backdrop;
        if (r3 == null) goto L18;
        r16 = r3.text_color | (-16777216);
    L18:
        int r7 = r16;
        this.title.ellipsize(getWidth() - AndroidUtilities.dp(12.0f));
        Text r17 = this.title;
        r17.draw(r19, r9 - (r17.getCurrentWidth() / 2.0f), this.titleY, -1, 1.0f);
        this.subtitle.ellipsize(getWidth() - AndroidUtilities.dp(12.0f));
        Text r18 = this.subtitle;
        Canvas r2 = r19;
        int r5 = r7;
        r18.draw(r2, r9 - (r18.getCurrentWidth() / 2.0f), this.subtitleY, r5, 1.0f);
        if (this.hasGiftMessage == false) goto L22;
        int r110 = this.messageDrawable.getMinimumWidth();
        int r32 = this.messageDrawable.getMinimumHeight();
        int r4 = (int) (r9 - (r110 / 2.0f));
        GiftMessageDrawable r6 = this.messageDrawable;
        float r72 = this.messageY;
        r6.setBounds(r4, (int) r72, r110 + r4, ((int) r72) + r32);
        this.messageDrawable.draw(r2);
    L21:
        int r152 = r5;
        if (this.repost == true) goto L28;
        this.buttonRect.set(r9 - ((this.buttonText.getCurrentWidth() + AndroidUtilities.dp(30.0f)) / 2.0f), this.buttonY, r9 + ((this.buttonText.getCurrentWidth() + AndroidUtilities.dp(30.0f)) / 2.0f), this.buttonY + this.buttonHeight);
        this.buttonPath.rewind();
        Path r111 = this.buttonPath;
        RectF r33 = this.buttonRect;
        float r42 = this.buttonHeight;
        r111.addRoundRect(r33, r42 / 2.0f, r42 / 2.0f, Path.Direction.CW);
        this.buttonBackgroundPaint.setColor(Theme.multAlpha(-16777216, 0.13f));
        float r112 = this.buttonBounce.getScale(0.075f);
        r2.scale(r112, r112, this.buttonRect.centerX(), this.buttonRect.centerY());
        r2.drawPath(this.buttonPath, this.buttonBackgroundPaint);
        r2.restore();
        this.ribbon.setBounds(((int) this.backgroundRect.right) - AndroidUtilities.dp(46.67f), ((int) this.backgroundRect.top) - AndroidUtilities.dp(1.33f), ((int) this.backgroundRect.right) + AndroidUtilities.dp(1.33f), ((int) this.backgroundRect.top) + AndroidUtilities.dp(46.67f));
        this.ribbon.setTextColor(r152);
        this.ribbon.draw(r2);
    L28:
        r2.restore();
        return;
    L22:
        float r11 = (this.nameWidth + AndroidUtilities.dp(9.0f)) + this.valueWidth;
        ArrayList<Row> r122 = this.table;
        int r132 = r122.size();
        int r113 = 0;
    L23:
        if (r113 >= r132) goto L21;
        int r142 = r113 + 1;
        Row r153 = r122.get(r113);
        Text r114 = r153.name;
        float r162 = r9 - (r11 / 2.0f);
        r114.draw(r2, (r162 + this.nameWidth) - r114.getCurrentWidth(), r153.y, r5, 1.0f);
        r2 = r19;
        r153.value.draw(r2, (r162 + this.nameWidth) + AndroidUtilities.dp(9.0f), r153.y, -1, 1.0f);
        r113 = r142;
        r5 = r5;
        goto L23
    }

    public void drawOutbounds(Canvas r9) {
        if (this.repost == false) goto L5;
        return;
    L5:
        r9.save();
        float r0 = this.bounce.getScale(0.0125f);
        r9.scale(r0, r0, this.backgroundRect.centerX(), this.backgroundRect.centerY());
        float r02 = this.buttonBounce.getScale(0.075f);
        r9.scale(r02, r02, this.buttonRect.centerX(), this.buttonRect.centerY());
        r9.clipPath(this.buttonPath);
        this.buttonParticles.setBounds(this.buttonRect);
        this.buttonParticles.process();
        this.buttonParticles.draw(r9, Theme.multAlpha(-1, 0.7f));
        this.buttonText.draw(r9, this.buttonRect.left + AndroidUtilities.dp(15.0f), this.buttonRect.centerY(), -1, 1.0f);
        r9.restore();
        invalidate();
    }

    public float getHeight() {
        return this.height;
    }

    public GiftMessageDrawable getMessageDrawable() {
        return this.messageDrawable;
    }

    public float getWidth() {
        return this.animatorVisualWidth.getFactor();
    }

    public boolean has() {
        if (this.action == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean onTouchEvent(float r10, float r11, MotionEvent r12) {
        boolean r0 = this.buttonRect.contains(r12.getX() - r10, r12.getY() - r11);
        boolean r102 = this.backgroundRect.contains(r12.getX() - r10, r12.getY() - r11);
        if (r12.getAction() != 0) goto L11;
        ButtonBounce r112 = this.bounce;
        if (r102 == false) goto L8;
        if (r0 == true) goto L8;
        boolean r103 = true;
    L9:
        r112.setPressed(r103);
        this.buttonBounce.setPressed(r0);
    L48:
        if (this.buttonBounce.isPressed() == false) goto L50;
    L53:
        return true;
    L50:
        if (this.bounce.isPressed() == true) goto L53;
        return false;
    L8:
        r103 = false;
        goto L9
    L11:
        if (r12.getAction() != 2) goto L21;
        if (this.buttonBounce.isPressed() == false) goto L17;
        if (r0 == true) goto L17;
        this.buttonBounce.setPressed(false);
    L17:
        if (this.bounce.isPressed() == false) goto L48;
        if (r102 == true) goto L48;
        this.bounce.setPressed(false);
        goto L48
    L21:
        if (r12.getAction() != 1) goto L40;
        if (this.buttonBounce.isPressed() == true) goto L27;
        if (this.bounce.isPressed() == false) goto L40;
    L27:
        if (this.onButtonClick == null) goto L32;
        if (this.buttonBounce.isPressed() == false) goto L37;
        this.onButtonClick.run();
    L37:
        this.buttonBounce.setPressed(false);
        this.bounce.setPressed(false);
        return true;
    L32:
        if (this.burned == false) goto L36;
        BaseFragment r104 = LaunchActivity.getSafeLastFragment();
        if (r104 == null) goto L37;
        BulletinFactory.of(r104).createSimpleBulletin(R.raw.fire_on, LocaleController.getString(R.string.UniqueGiftNotFoundBurned)).show();
        goto L37
    L36:
        new StarGiftSheet(this.view.getContext(), this.currentAccount, this.currentMessageObject.getDialogId(), this.resourcesProvider).set(this.currentMessageObject).show();
    L40:
        if (r12.getAction() != 3) goto L48;
        if (this.buttonBounce.isPressed() == false) goto L44;
    L45:
        this.buttonBounce.setPressed(false);
        this.bounce.setPressed(false);
        return true;
    L44:
        if (this.bounce.isPressed() == false) goto L48;
        goto L45
    }

    public void set(MessageObject r10, boolean r11) {
        this.widthExpanded = false;
        this.currentMessageObject = r10;
        if (r10 == null) goto L9;
        TLRPC.Message r2 = r10.messageOwner;
        if (r2 == null) goto L9;
        TLRPC.MessageAction r22 = r2.action;
        if ((r22 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L9;
        TLRPC.TL_messageActionStarGiftUnique r23 = (TLRPC.TL_messageActionStarGiftUnique) r22;
    L10:
        if (r23 != null) goto L12;
    L15:
        r23 = null;
    L17:
        if (this.attached == false) goto L22;
        if (r23 == null) goto L22;
        if (this.action != null) goto L22;
        this.imageReceiver.onAttachedToWindow();
        this.emoji.attach();
        this.messageDrawable.attach();
    L22:
        this.action = r23;
        if (r10 != null) goto L25;
    L27:
        boolean r4 = false;
    L28:
        this.repost = r4;
        if (r23 != null) goto L31;
        return;
    L31:
        TL_stars.TL_starGiftUnique r42 = (TL_stars.TL_starGiftUnique) r23.gift;
        this.backdrop = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r42.attributes, TL_stars.starGiftAttributeBackdrop.class);
        this.pattern = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r42.attributes, TL_stars.starGiftAttributePattern.class);
        TL_stars.starGiftAttributeModel r5 = this.model;
        this.model = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r42.attributes, TL_stars.starGiftAttributeModel.class);
        Paint r6 = this.backgroundPaint;
        this.gradient = null;
        r6.setShader(null);
        TL_stars.starGiftAttributePattern r62 = this.pattern;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r7 = this.emoji;
        if (r62 == null) goto L34;
        r7.set(r62.document, r11);
    L35:
        TL_stars.starGiftAttributeModel r1 = this.model;
        if (r1 == null) goto L45;
        if (r5 != null) goto L39;
    L40:
        boolean r12 = this.repost;
        ImageReceiver r52 = this.imageReceiver;
        if (r12 == false) goto L43;
        r52.setAllowStartLottieAnimation(true);
        this.imageReceiver.setAllowStartAnimation(true);
        this.imageReceiver.setAutoRepeat(1);
    L44:
        StarsIntroActivity.setGiftImage(this.imageReceiver, this.model.document, 110);
        goto L45
    L43:
        r52.setAutoRepeatCount(0);
        this.imageReceiver.clearDecorators();
        this.imageReceiver.setAutoRepeat(0);
        goto L44
    L39:
        if (r5.document.id != r1.document.id) goto L40;
    L45:
        boolean r13 = r42.burned;
        this.burned = r13;
        GiftSheet.RibbonDrawable r53 = this.ribbon;
        if (r13 == false) goto L48;
        r53.setColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
        this.ribbon.setText(11, LocaleController.getString(R.string.Gift2UniqueRibbonBurned), true);
    L50:
        if (this.repost == false) goto L53;
        this.width = AndroidUtilities.dp(200.0f);
    L60:
        setInternal(r10, r23, r42, r11);
        checkAnimatedWidth(r11);
        return;
    L53:
        if (AndroidUtilities.isTablet() == false) goto L55;
        float r0 = AndroidUtilities.getMinTabletSide() * 0.6f;
    L56:
        this.width = Math.min((int) r0, ((AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(64.0f));
        if (AndroidUtilities.isTablet() == true) goto L59;
        this.width = (int) (this.width * 1.2f);
    L59:
        this.width -= AndroidUtilities.dp(8.0f);
        goto L60
    L55:
        r0 = (AndroidUtilities.displaySize.x * 0.62f) - AndroidUtilities.dp(34.0f);
        goto L56
    L48:
        r53.setBackdrop(this.backdrop, true, false);
        this.ribbon.setText(11, LocaleController.getString(R.string.Gift2UniqueRibbon), true);
        goto L50
    L34:
        r7.set(null, r11);
        goto L35
    L25:
        if (r10.isRepostPreview == false) goto L27;
        r4 = true;
        goto L28
    L12:
        if (r23.refunded == true) goto L15;
        if ((r23.gift instanceof TL_stars.TL_starGiftUnique) == true) goto L17;
    L9:
        r23 = null;
        goto L10
    }

    public void set(TL_stars.TL_starGiftUnique r7, long r8, TLRPC.TL_textWithEntities r10, String r11, boolean r12) {
        this.widthExpanded = false;
        this.action = null;
        this.currentMessageObject = null;
        this.repost = false;
        if (r7 != null) goto L5;
        return;
    L5:
        this.backdrop = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r7.attributes, TL_stars.starGiftAttributeBackdrop.class);
        this.pattern = (TL_stars.starGiftAttributePattern) StarsController.findAttribute(r7.attributes, TL_stars.starGiftAttributePattern.class);
        TL_stars.starGiftAttributeModel r2 = this.model;
        this.model = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r7.attributes, TL_stars.starGiftAttributeModel.class);
        Paint r3 = this.backgroundPaint;
        this.gradient = null;
        r3.setShader(null);
        TL_stars.starGiftAttributePattern r32 = this.pattern;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r4 = this.emoji;
        if (r32 == null) goto L8;
        r4.set(r32.document, false);
    L9:
        TL_stars.starGiftAttributeModel r1 = this.model;
        if (r1 == null) goto L15;
        if (r2 != null) goto L13;
    L14:
        this.imageReceiver.setAutoRepeatCount(0);
        this.imageReceiver.clearDecorators();
        this.imageReceiver.setAutoRepeat(0);
        StarsIntroActivity.setGiftImage(this.imageReceiver, this.model.document, 110);
        goto L15
    L13:
        if (r2.document.id != r1.document.id) goto L14;
    L15:
        boolean r13 = r7.burned;
        this.burned = r13;
        GiftSheet.RibbonDrawable r22 = this.ribbon;
        if (r13 == false) goto L18;
        r22.setColor(Theme.getColor(Theme.key_text_RedBold, this.resourcesProvider));
        this.ribbon.setText(11, LocaleController.getString(R.string.Gift2UniqueRibbonBurned), true);
    L20:
        if (this.attached == false) goto L23;
        this.imageReceiver.onAttachedToWindow();
        this.emoji.attach();
        this.messageDrawable.attach();
    L23:
        if (AndroidUtilities.isTablet() == false) goto L25;
        float r0 = AndroidUtilities.getMinTabletSide() * 0.6f;
    L26:
        this.width = Math.min((int) r0, ((AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(64.0f));
        if (AndroidUtilities.isTablet() == true) goto L29;
        this.width = (int) (this.width * 1.2f);
    L29:
        this.width -= AndroidUtilities.dp(8.0f);
        setInternal2(r7, r8, r10, r11);
        checkAnimatedWidth(r12);
        return;
    L25:
        r0 = (AndroidUtilities.displaySize.x * 0.62f) - AndroidUtilities.dp(34.0f);
        goto L26
    L18:
        r22.setBackdrop(this.backdrop, true, false);
        this.ribbon.setText(11, LocaleController.getString(R.string.Gift2UniqueRibbon), true);
        goto L20
    L8:
        r4.set(null, false);
        goto L9
    }

    public void setOnButtonClickListener(Runnable r1) {
        this.onButtonClick = r1;
    }
}
