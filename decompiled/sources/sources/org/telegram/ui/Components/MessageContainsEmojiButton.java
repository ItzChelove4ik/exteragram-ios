package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class MessageContainsEmojiButton extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    public boolean checkWidth;
    private int currentAccount;
    private AnimatedEmojiDrawable emojiDrawable;
    private Rect emojiDrawableBounds;
    private CharSequence endText;
    private TLRPC.InputStickerSet inputStickerSet;
    private int lastLineHeight;
    private int lastLineMargin;
    private int lastLineTop;
    private CharSequence lastMainTextText;
    private int lastMainTextWidth;
    private CharSequence lastSecondPartText;
    private int lastSecondPartTextWidth;
    private int lastWidth;
    private ValueAnimator loadAnimator;
    private float loadT;
    private Rect loadingBoundsFrom;
    private Rect loadingBoundsTo;
    private LoadingDrawable loadingDrawable;
    private boolean loadingDrawableBoundsSet;
    private CharSequence mainText;
    private StaticLayout mainTextLayout;
    private final Theme.ResourcesProvider resourcesProvider;
    private CharSequence secondPartText;
    private StaticLayout secondPartTextLayout;
    private TextPaint textPaint;
    int type;

    /* renamed from: org.telegram.ui.Components.MessageContainsEmojiButton$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedEmojiSpan {
        final /* synthetic */ MessageContainsEmojiButton this$0;

        public AnonymousClass1(MessageContainsEmojiButton r1, TLRPC.Document r2, Paint.FontMetricsInt r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            int r82 = r8 + r6;
            int r0 = this.measuredSize;
            MessageContainsEmojiButton.m11259$$Nest$fgetemojiDrawableBounds(this.this$0).set((int) r5, (r82 - r0) / 2, (int) (r5 + r0), (r82 + r0) / 2);
        }
    }

    /* renamed from: org.telegram.ui.Components.MessageContainsEmojiButton$2, reason: invalid class name */
    public class AnonymousClass2 extends ReplacementSpan {
        final /* synthetic */ MessageContainsEmojiButton this$0;

        public AnonymousClass2(MessageContainsEmojiButton r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return MessageContainsEmojiButton.m11260$$Nest$fgetlastLineMargin(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.MessageContainsEmojiButton$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatedEmojiSpan {
        final /* synthetic */ MessageContainsEmojiButton this$0;

        public AnonymousClass3(MessageContainsEmojiButton r1, TLRPC.Document r2, Paint.FontMetricsInt r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            int r32 = MessageContainsEmojiButton.m11261$$Nest$fgetlastLineTop(this.this$0);
            int r82 = r8 + r6;
            int r42 = this.measuredSize;
            MessageContainsEmojiButton.m11259$$Nest$fgetemojiDrawableBounds(this.this$0).set((int) r5, r32 + ((r82 - r42) / 2), (int) (r5 + r42), MessageContainsEmojiButton.m11261$$Nest$fgetlastLineTop(this.this$0) + ((r82 + this.measuredSize) / 2));
        }
    }

    public class BoldAndAccent extends CharacterStyle {
        final /* synthetic */ MessageContainsEmojiButton this$0;

        private BoldAndAccent(MessageContainsEmojiButton r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BoldAndAccent(MessageContainsEmojiButton r1, MessageContainsEmojiButtonIA r2) {
            this(r1);
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setTypeface(AndroidUtilities.bold());
            int r0 = r3.getAlpha();
            r3.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, MessageContainsEmojiButton.m11262$$Nest$fgetresourcesProvider(this.this$0)));
            r3.setAlpha(r0);
        }
    }

    public static /* synthetic */ void $r8$lambda$MTCuaqe5NAShdRlLzPsxkmBmgFI(MessageContainsEmojiButton r0, boolean r1, ValueAnimator r2) {
        r0.lambda$didReceivedNotification$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetemojiDrawableBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m11259$$Nest$fgetemojiDrawableBounds(MessageContainsEmojiButton r0) {
        return r0.emojiDrawableBounds;
    }

    /* renamed from: -$$Nest$fgetlastLineMargin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11260$$Nest$fgetlastLineMargin(MessageContainsEmojiButton r0) {
        return r0.lastLineMargin;
    }

    /* renamed from: -$$Nest$fgetlastLineTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11261$$Nest$fgetlastLineTop(MessageContainsEmojiButton r0) {
        return r0.lastLineTop;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m11262$$Nest$fgetresourcesProvider(MessageContainsEmojiButton r0) {
        return r0.resourcesProvider;
    }

    public MessageContainsEmojiButton(int r10, Context r11, Theme.ResourcesProvider r12, ArrayList<TLRPC.InputStickerSet> r13, int r14) {
        super(r11);
        this.emojiDrawableBounds = new Rect();
        int r112 = 0;
        this.loadingDrawableBoundsSet = false;
        this.lastWidth = -1;
        this.checkWidth = true;
        this.loadT = 0.0f;
        this.resourcesProvider = r12;
        this.currentAccount = r10;
        this.type = r14;
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r12), 0, 16));
        TextPaint r1 = new TextPaint(1);
        this.textPaint = r1;
        r1.setTextSize(AndroidUtilities.dp(13.0f));
        this.textPaint.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r12));
        if (r13.size() <= 1) goto L15;
        if (r14 != 5) goto L6;
        String r102 = LocaleController.formatString(R.string.ProfileUsesEmojiPacks, new Object[]{Integer.valueOf(r13.size())});
    L9:
        SpannableStringBuilder r103 = AndroidUtilities.replaceTags(r102);
        this.mainText = r103;
        SpannableStringBuilder r122 = r103;
        TypefaceSpan[] r104 = (TypefaceSpan[]) r122.getSpans(0, r103.length(), TypefaceSpan.class);
    L10:
        if (r104 == null) goto L70;
        if (r112 >= r104.length) goto L71;
        int r132 = r122.getSpanStart(r104[r112]);
        int r142 = r122.getSpanEnd(r104[r112]);
        r122.removeSpan(r104[r112]);
        r122.setSpan(new BoldAndAccent(this, null), r132, r142, 33);
        r112 = r112 + 1;
        goto L10
    L71:
        return;
    L70:
        return;
    L6:
        if (r14 != 0) goto L8;
        r102 = LocaleController.formatPluralString("MessageContainsEmojiPacks", r13.size(), new Object[0]);
        goto L9
    L8:
        r102 = LocaleController.formatPluralString("MessageContainsReactionsPacks", r13.size(), new Object[0]);
        goto L9
    L15:
        if (r13.size() != 1) goto L61;
        if (r14 != 4) goto L19;
        String r143 = LocaleController.getString(R.string.ReplyUsesEmojiPacks);
    L27:
        String[] r15 = r143.split("%s");
        if (r15.length > 1) goto L31;
        this.mainText = r143;
        return;
    L31:
        TLRPC.InputStickerSet r133 = r13.get(0);
        this.inputStickerSet = r133;
        if (r133 == null) goto L53;
        TLRPC.TL_messages_stickerSet r134 = MediaDataController.getInstance(r10).getStickerSet(this.inputStickerSet, false);
        if (r134 == null) goto L53;
        TLRPC.StickerSet r144 = r134.set;
        if (r144 == null) goto L53;
        String r145 = r144.title;
        int r2 = 0;
    L38:
        ArrayList<TLRPC.Document> r5 = r134.documents;
        if (r5 == null) goto L46;
        if (r2 >= r5.size()) goto L46;
        if (r134.documents.get(r2).id == r134.set.thumb_document_id) goto L44;
        r2 = r2 + 1;
        goto L38
    L44:
        TLRPC.Document r22 = r134.documents.get(r2);
    L47:
        if (r22 != null) goto L54;
        ArrayList<TLRPC.Document> r52 = r134.documents;
        if (r52 == null) goto L54;
        if (r52.size() <= 0) goto L54;
        r22 = r134.documents.get(0);
    L54:
        if (r145 == null) goto L58;
        if (r22 == null) goto L58;
        SpannableString r135 = new SpannableString(MessageObject.findAnimatedEmojiEmoticon(r22));
        r135.setSpan(new AnonymousClass1(this, r22, this.textPaint.getFontMetricsInt()), 0, r135.length(), 33);
        AnimatedEmojiDrawable r105 = AnimatedEmojiDrawable.make(r10, 0, r22);
        this.emojiDrawable = r105;
        r105.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, r12), PorterDuff.Mode.SRC_IN));
        this.emojiDrawable.addView(this);
        SpannableString r106 = new SpannableString(r145);
        r106.setSpan(new BoldAndAccent(this, null), 0, r106.length(), 33);
        this.mainText = new SpannableStringBuilder().append(r15[0]).append(r135).append(' ').append(r106).append(r15[1]);
        this.loadT = 1.0f;
        this.inputStickerSet = null;
        return;
    L58:
        this.mainText = r15[0];
        this.endText = r15[1];
        LoadingDrawable r107 = new LoadingDrawable(r12);
        this.loadingDrawable = r107;
        r107.colorKey1 = Theme.key_actionBarDefaultSubmenuBackground;
        r107.colorKey2 = Theme.key_listSelector;
        r107.setRadiiDp(4.0f);
        return;
    L46:
        r22 = null;
    L53:
        r145 = null;
        r22 = null;
        goto L54
    L19:
        if (r14 != 5) goto L21;
        r143 = LocaleController.getString(R.string.ProfileUsesEmojiPack);
        goto L27
    L21:
        if (r14 != 0) goto L24;
        r143 = LocaleController.getString(R.string.MessageContainsEmojiPack);
        goto L27
    L24:
        if (r14 != 3) goto L26;
        r143 = LocaleController.getString(R.string.MessageContainsReactionPack);
        goto L27
    L26:
        r143 = LocaleController.getString(R.string.MessageContainsReactionsPack);
        goto L27
    L61:
        if (r14 != 6) goto L69;
        this.mainText = AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StickersCheckStickersBotForMoreOptions), Theme.key_chat_messageLinkIn, 2, null, r12);
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$0(boolean r1, ValueAnimator r2) {
        this.loadT = ((Float) r2.getAnimatedValue()).floatValue();
        invalidate();
        if (r1 == false) goto L6;
        requestLayout();
        return;
    }

    private int updateLayout(int r23, boolean r24) {
        int r11 = 0;
        if (r23 > 0) goto L5;
        return 0;
    L5:
        CharSequence r1 = this.mainText;
        if (r1 == this.lastMainTextText) goto L8;
    L9:
        if (r1 == null) goto L18;
        CharSequence r13 = this.mainText;
        StaticLayout r12 = new StaticLayout(r13, 0, r13.length(), this.textPaint, Math.max(r23, 0), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.mainTextLayout = r12;
        if (this.loadingDrawable != null) goto L13;
    L19:
        this.lastMainTextText = this.mainText;
        this.lastMainTextWidth = r23;
    L20:
        CharSequence r14 = this.secondPartText;
        if (r14 == this.lastSecondPartText) goto L23;
    L24:
        if (r14 == null) goto L26;
        CharSequence r2 = this.secondPartText;
        this.secondPartTextLayout = new StaticLayout(r2, 0, r2.length(), this.textPaint, r23, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L27:
        this.lastSecondPartText = this.secondPartText;
        this.lastSecondPartTextWidth = r23;
    L28:
        StaticLayout r15 = this.mainTextLayout;
        if (r15 == null) goto L32;
        r11 = r15.getHeight();
    L32:
        StaticLayout r16 = this.secondPartTextLayout;
        if (r16 == null) goto L39;
        float r17 = r16.getHeight() - this.lastLineHeight;
        if (r24 == false) goto L37;
        float r0 = 1.0f;
    L38:
        float r18 = r17 * r0;
    L41:
        return r11 + ((int) r18);
    L37:
        r0 = this.loadT;
        goto L38
    L39:
        r18 = 0.0f;
        goto L41
    L26:
        this.secondPartTextLayout = null;
        goto L27
    L23:
        if (this.lastSecondPartTextWidth == r23) goto L28;
    L13:
        if (this.loadingBoundsTo != null) goto L19;
        int r19 = r12.getLineCount() - 1;
        this.lastLineMargin = ((int) this.mainTextLayout.getPrimaryHorizontal(this.mainText.length())) + AndroidUtilities.dp(2.0f);
        this.lastLineTop = this.mainTextLayout.getLineTop(r19);
        this.lastLineHeight = r1 - this.lastLineTop;
        float r4 = Math.min(AndroidUtilities.dp(100.0f), this.mainTextLayout.getWidth() - this.lastLineMargin);
        if (this.loadingBoundsFrom != null) goto L17;
        this.loadingBoundsFrom = new Rect();
    L17:
        Rect r5 = this.loadingBoundsFrom;
        int r7 = this.lastLineMargin;
        r5.set(r7, this.lastLineTop, (int) (r7 + r4), r1);
        this.loadingDrawable.setBounds(this.loadingBoundsFrom);
        this.loadingDrawableBoundsSet = true;
        goto L19
    L18:
        this.mainTextLayout = null;
        this.loadingDrawableBoundsSet = false;
        goto L19
    L8:
        if (this.lastMainTextWidth == r23) goto L20;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 == NotificationCenter.groupStickersDidLoad) goto L5;
        return;
    L5:
        if (this.inputStickerSet == null) goto L52;
        TLRPC.TL_messages_stickerSet r82 = MediaDataController.getInstance(this.currentAccount).getStickerSet(this.inputStickerSet, false);
        if (r82 == null) goto L54;
        TLRPC.StickerSet r92 = r82.set;
        if (r92 == null) goto L27;
        String r93 = r92.title;
        int r1 = 0;
    L12:
        ArrayList<TLRPC.Document> r2 = r82.documents;
        if (r2 == null) goto L20;
        if (r1 >= r2.size()) goto L20;
        if (r82.documents.get(r1).id == r82.set.thumb_document_id) goto L18;
        r1 = r1 + 1;
        goto L12
    L18:
        TLRPC.Document r12 = r82.documents.get(r1);
    L21:
        if (r12 != null) goto L28;
        ArrayList<TLRPC.Document> r22 = r82.documents;
        if (r22 == null) goto L28;
        if (r22.size() <= 0) goto L28;
        r12 = r82.documents.get(0);
    L28:
        if (r93 == null) goto L53;
        if (r12 == null) goto L55;
        AnimatedEmojiDrawable r83 = AnimatedEmojiDrawable.make(this.currentAccount, 0, r12);
        this.emojiDrawable = r83;
        r83.addView(this);
        invalidate();
        SpannableString r84 = new SpannableString(" ");
        r84.setSpan(new AnonymousClass2(this), 0, 1, 33);
        SpannableString r23 = new SpannableString(MessageObject.findAnimatedEmojiEmoticon(r12));
        r23.setSpan(new AnonymousClass3(this, r12, this.textPaint.getFontMetricsInt()), 0, r23.length(), 33);
        SpannableString r13 = new SpannableString(r93);
        r13.setSpan(new BoldAndAccent(this, null), 0, r13.length(), 33);
        this.secondPartText = new SpannableStringBuilder().append(r84).append(r23).append(' ').append(r13).append(this.endText);
        int r85 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int r94 = updateLayout((this.lastWidth - getPaddingLeft()) - getPaddingRight(), true);
        if (this.loadingBoundsFrom != null) goto L34;
    L39:
        this.inputStickerSet = null;
        ValueAnimator r0 = this.loadAnimator;
        if (r0 == null) goto L43;
        r0.cancel();
    L43:
        if (Math.abs(r85 - r94) <= AndroidUtilities.dp(3.0f)) goto L45;
        boolean r86 = true;
    L46:
        ValueAnimator r95 = ValueAnimator.ofFloat(new float[]{this.loadT, 1.0f});
        this.loadAnimator = r95;
        r95.addUpdateListener(new MessageContainsEmojiButton$$ExternalSyntheticLambda0(this, r86));
        this.loadAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.loadAnimator.setStartDelay(150);
        this.loadAnimator.setDuration(400);
        this.loadAnimator.start();
        return;
    L45:
        r86 = false;
        goto L46
    L34:
        if (this.secondPartTextLayout == null) goto L39;
        if (this.loadingBoundsTo != null) goto L38;
        this.loadingBoundsTo = new Rect();
    L38:
        StaticLayout r14 = this.secondPartTextLayout;
        float r15 = r14.getPrimaryHorizontal(r14.getLineEnd(0));
        Rect r24 = this.loadingBoundsTo;
        Rect r4 = this.loadingBoundsFrom;
        r24.set(r4.left, r4.top, (int) r15, r4.bottom);
        goto L39
    L55:
        return;
    L53:
        return;
    L20:
        r12 = null;
        goto L21
    L27:
        r93 = null;
        r12 = null;
        goto L28
    L54:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        AnimatedEmojiDrawable r0 = this.emojiDrawable;
        if (r0 == null) goto L5;
        r0.addView(this);
    L5:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatedEmojiDrawable r0 = this.emojiDrawable;
        if (r0 == null) goto L5;
        r0.removeView(this);
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        super.onDraw(r6);
        if (this.mainTextLayout == null) goto L22;
        r6.save();
        r6.translate(getPaddingLeft(), getPaddingTop());
        this.textPaint.setAlpha(255);
        this.mainTextLayout.draw(r6);
        LoadingDrawable r0 = this.loadingDrawable;
        if (r0 == null) goto L15;
        if (this.loadingDrawableBoundsSet == false) goto L15;
        r0.setAlpha((int) ((1.0f - this.loadT) * 255.0f));
        Rect r02 = this.loadingBoundsFrom;
        if (r02 == null) goto L13;
        Rect r2 = this.loadingBoundsTo;
        if (r2 == null) goto L13;
        float r3 = this.loadT;
        Rect r4 = AndroidUtilities.rectTmp2;
        AndroidUtilities.lerp(r02, r2, r3, r4);
        this.loadingDrawable.setBounds(r4);
    L13:
        this.loadingDrawable.draw(r6);
        invalidate();
    L15:
        if (this.secondPartTextLayout == null) goto L17;
        r6.save();
        r6.translate(0.0f, this.lastLineTop);
        this.textPaint.setAlpha((int) (this.loadT * 255.0f));
        this.secondPartTextLayout.draw(r6);
        r6.restore();
    L17:
        AnimatedEmojiDrawable r03 = this.emojiDrawable;
        if (r03 == null) goto L20;
        r03.setAlpha((int) (this.loadT * 255.0f));
        this.emojiDrawable.setBounds(this.emojiDrawableBounds);
        this.emojiDrawable.draw(r6);
    L20:
        r6.restore();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        int r52 = View.MeasureSpec.getSize(r4);
        if (this.checkWidth == false) goto L7;
        int r0 = this.lastWidth;
        if (r0 <= 0) goto L7;
        r52 = Math.min(r52, r0);
    L7:
        this.lastWidth = r52;
        int r53 = (r52 - getPaddingLeft()) - getPaddingRight();
        if (r53 >= 0) goto L10;
        r53 = 0;
    L10:
        super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((updateLayout(r53, false) + getPaddingTop()) + getPaddingBottom(), TLObject.FLAG_30));
    }
}
