package org.telegram.ui.Stories;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.TypefaceSpan;

/* loaded from: classes7.dex */
public class StoryContainsEmojiButton extends View {
    private static Object lastRequestParentObject;
    private static Vector<TLRPC.StickerSetCovered> lastResponse;
    private final ColorFilter colorFilter;
    private boolean emoji;
    private ArrayList<TLRPC.InputStickerSet> inputSets;
    private int lastContentWidth;
    private StaticLayout layout;
    private float layoutLeft;
    private float layoutWidth;
    private ValueAnimator loadAnimator;
    private float loadT;
    private final LoadingDrawable loadingDrawable;
    private final Path loadingPath;
    private Object parentObject;
    private final Theme.ResourcesProvider resourcesProvider;
    private ArrayList<TLRPC.StickerSetCovered> sets;
    private int shiftDp;
    private AnimatedEmojiSpan.EmojiGroupedSpans stack;
    private boolean stickers;
    private final TextPaint textPaint;
    private CharSequence toSetText;
    private Vector<TLRPC.StickerSetCovered> vector;

    /* renamed from: $r8$lambda$3uwdjbMItAQP71n-oUkp_KIWSQY, reason: not valid java name */
    public static /* synthetic */ void m19758$r8$lambda$3uwdjbMItAQP71noUkp_KIWSQY(StoryContainsEmojiButton r0, boolean r1, ValueAnimator r2) {
        r0.lambda$animateLoad$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LVT7ELWdFWzKEa4Dt0HExLhucuw(StoryContainsEmojiButton r0, TLObject r1, Object r2, ArrayList r3, boolean[] r4, int r5) {
        r0.lambda$load$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$a7Uds_bMamhQAjaesJpsk6HylQo(StoryContainsEmojiButton r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$load$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jYnsKX_xKpmcvsJtYXJsLyWT_po(Object r1, int r2, TLRPC.TL_messages_getAttachedStickers r3, RequestDelegate r4, TLObject r5, TLRPC.TL_error r6) {
        if (r6 != null) goto L4;
    L8:
        r4.run(r5, r6);
        return;
    L4:
        if (FileRefController.isFileRefError(r6.text) == false) goto L8;
        if (r1 == null) goto L8;
        FileRefController.getInstance(r2).requestReference(r1, new Object[]{r3, r4});
    }

    /* renamed from: $r8$lambda$kUbC5uPWii6qjpC7ZGhSO-3KI3I, reason: not valid java name */
    public static /* synthetic */ void m19759$r8$lambda$kUbC5uPWii6qjpC7ZGhSO3KI3I(StoryContainsEmojiButton r0, Object r1, ArrayList r2, boolean[] r3, int r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$load$2(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$wzmlZ83Uhvx_2h_W74lQ6AkKdxE(StoryContainsEmojiButton r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$load$4(r1);
    }

    public StoryContainsEmojiButton(Context r7, int r8, TLObject r9, Object r10, boolean r11, ArrayList<TLRPC.InputStickerSet> r12, Theme.ResourcesProvider r13) {
        super(r7);
        this.shiftDp = -12;
        this.resourcesProvider = r13;
        setMinimumWidth(AndroidUtilities.dp(196.0f));
        setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r13), 0, 8));
        setClickable(true);
        TextPaint r1 = new TextPaint(1);
        this.textPaint = r1;
        r1.setTextSize(AndroidUtilities.dp(13.0f));
        int r72 = Theme.key_actionBarDefaultSubmenuItem;
        r1.setColor(Theme.getColor(r72, r13));
        this.colorFilter = new PorterDuffColorFilter(Theme.getColor(r72, r13), PorterDuff.Mode.SRC_IN);
        LoadingDrawable r73 = new LoadingDrawable(r13);
        this.loadingDrawable = r73;
        r73.setCallback(this);
        r73.setColors(Theme.multAlpha(-1, 0.2f), Theme.multAlpha(-1, 0.05f));
        Path r132 = new Path();
        this.loadingPath = r132;
        r73.usePath(r132);
        r73.setRadiiDp(4.0f);
        load(r8, r11, r9, r12, r10);
    }

    private void animateLoad(boolean r5) {
        ValueAnimator r0 = this.loadAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (r5 == false) goto L14;
        boolean r2 = false;
        this.loadAnimator = ValueAnimator.ofFloat(new float[]{this.loadT, 1.0f});
        if (this.layout != null) goto L10;
    L11:
        r2 = true;
    L12:
        this.loadAnimator.addUpdateListener(new StoryContainsEmojiButton$$ExternalSyntheticLambda4(this, r2));
        this.loadAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.loadAnimator.setStartDelay(150);
        this.loadAnimator.setDuration(400);
        this.loadAnimator.start();
        return;
    L10:
        if (Math.abs(getMeasuredHeight() - ((getPaddingTop() + this.layout.getHeight()) + getPaddingBottom())) <= AndroidUtilities.dp(3.0f)) goto L12;
    L14:
        this.loadT = 1.0f;
        invalidate();
        post(new StoryContainsEmojiButton$$ExternalSyntheticLambda5(this));
    }

    private /* synthetic */ void lambda$animateLoad$5(boolean r1, ValueAnimator r2) {
        this.loadT = ((Float) r2.getAnimatedValue()).floatValue();
        invalidate();
        if (r1 == false) goto L6;
        requestLayout();
        return;
    }

    private /* synthetic */ void lambda$load$0(TLRPC.TL_messages_stickerSet r1) {
        set(r1);
        animateLoad(false);
    }

    private /* synthetic */ void lambda$load$1(TLObject r9, Object r10, ArrayList r11, boolean[] r12, int r13) {
        if ((r9 instanceof Vector) == true) goto L5;
        return;
    L5:
        Vector<TLRPC.StickerSetCovered> r92 = (Vector) r9;
        this.vector = r92;
        lastRequestParentObject = r10;
        lastResponse = r92;
        int r0 = 0;
    L7:
        if (r0 >= r92.objects.size()) goto L17;
        TLRPC.StickerSetCovered r1 = (TLRPC.StickerSetCovered) r92.objects.get(r0);
        this.sets.add(r1);
        TLRPC.StickerSet r3 = r1.set;
        if (r3 == null) goto L16;
        this.inputSets.add(MediaDataController.getInputStickerSet(r3));
        TLRPC.StickerSet r14 = r1.set;
        if (r14.emojis == false) goto L14;
        this.emoji = true;
        goto L16
    L14:
        if (r14.masks == true) goto L16;
        this.stickers = true;
    L16:
        r0 = r0 + 1;
        goto L7
    L17:
        if (r11 == null) goto L19;
        int r93 = r11.size();
    L20:
        ArrayList<TLRPC.StickerSetCovered> r02 = this.sets;
        if (r02 != null) goto L23;
        int r03 = 0;
    L24:
        int r94 = r93 + r03;
        if (this.inputSets == null) goto L42;
        if (r11 == null) goto L42;
        if (r11.isEmpty() == true) goto L42;
        int r04 = 0;
    L31:
        if (r04 >= r11.size()) goto L41;
        TLRPC.InputStickerSet r15 = (TLRPC.InputStickerSet) r11.get(r04);
        long r32 = r15.id;
        int r5 = 0;
    L33:
        int r6 = this.inputSets.size();
        ArrayList<TLRPC.InputStickerSet> r7 = this.inputSets;
        if (r5 >= r6) goto L39;
        if (r7.get(r5).id == r32) goto L40;
        r5 = r5 + 1;
    L40:
        r04 = r04 + 1;
        goto L31
    L39:
        r7.add(r15);
        goto L40
    L41:
        this.emoji = true;
        this.vector = null;
    L42:
        if (r94 == 1) goto L44;
        set(r94);
    L53:
        animateLoad(r12[0]);
        return;
    L44:
        if (this.sets.size() < 1) goto L46;
        set(this.sets.get(0));
        goto L53
    L46:
        if (r11 != null) goto L48;
    L51:
        set(0);
        goto L53
    L48:
        if (r11.size() < 1) goto L51;
        r12[0] = false;
        MediaDataController.getInstance(r13).getStickerSet((TLRPC.InputStickerSet) r11.get(0), 0, false, new StoryContainsEmojiButton$$ExternalSyntheticLambda0(this));
        return;
    L23:
        r03 = r02.size();
        goto L24
    L19:
        r93 = 0;
        goto L20
    }

    private /* synthetic */ void lambda$load$2(Object r1, ArrayList r2, boolean[] r3, int r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new StoryContainsEmojiButton$$ExternalSyntheticLambda6(this, r5, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$load$4(TLRPC.TL_messages_stickerSet r1) {
        set(r1);
        animateLoad(true);
    }

    private void set(int r5) {
        boolean r0 = this.emoji;
        if (r0 == true) goto L5;
    L8:
        if (r0 == false) goto L11;
        setText(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("StoryContainsEmoji", r5, new Object[0]), 0, Theme.getColor(Theme.key_chat_messageLinkIn, this.loadingDrawable.resourcesProvider), null));
        return;
    L11:
        setText(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("StoryContainsStickers", r5, new Object[0]), 0, Theme.getColor(Theme.key_chat_messageLinkIn, this.loadingDrawable.resourcesProvider), null));
        return;
    L5:
        if (this.stickers == false) goto L8;
        setText(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("StoryContainsStickersEmoji", r5, new Object[0]), 0, Theme.getColor(Theme.key_chat_messageLinkIn, this.loadingDrawable.resourcesProvider), null));
    }

    private void set(TLRPC.StickerSetCovered r11) {
        SpannableString r0 = new SpannableString("x " + r11.set.title);
        r0.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chat_messageLinkIn, this.loadingDrawable.resourcesProvider)), 0, r0.length(), 33);
        r0.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r0.length(), 33);
        TLRPC.Document r1 = r11.cover;
        if (r1 == null) goto L5;
    L17:
        if (r1 == null) goto L19;
        r0.setSpan(new AnimatedEmojiSpan(r1, this.textPaint.getFontMetricsInt()), 0, 1, 33);
        CharSequence r02 = r0;
    L20:
        boolean r112 = this.emoji;
        if (r112 == true) goto L23;
    L25:
        if (r112 == false) goto L27;
        String r113 = LocaleController.getString(R.string.StoryContainsEmojiFrom);
    L28:
        setText(AndroidUtilities.replaceCharSequence("%s", r113, r02));
        return;
    L27:
        r113 = LocaleController.getString(R.string.StoryContainsStickersFrom);
        goto L28
    L23:
        if (this.stickers == false) goto L25;
        r113 = LocaleController.getString(R.string.StoryContainsStickersEmojiFrom);
        goto L28
    L19:
        r02 = r0.subSequence(2, r0.length());
        goto L20
    L5:
        if ((r11 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L17;
        ArrayList<TLRPC.Document> r2 = ((TLRPC.TL_stickerSetFullCovered) r11).documents;
        int r5 = 0;
    L8:
        if (r5 >= r2.size()) goto L13;
        if (r2.get(r5).id != r11.set.thumb_document_id) goto L12;
        r1 = r2.get(r5);
    L12:
        r5 = r5 + 1;
        goto L8
    L13:
        if (r1 != null) goto L17;
        if (r2.isEmpty() == true) goto L17;
        r1 = r2.get(0);
        goto L17
    }

    private void set(TLRPC.TL_messages_stickerSet r10) {
        if (r10 != null) goto L4;
        return;
    L4:
        SpannableString r0 = new SpannableString("x " + r10.set.title);
        r0.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chat_messageLinkIn, this.loadingDrawable.resourcesProvider)), 0, r0.length(), 33);
        r0.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r0.length(), 33);
        ArrayList<TLRPC.Document> r1 = r10.documents;
        int r2 = 0;
    L6:
        if (r2 >= r1.size()) goto L11;
        if (r1.get(r2).id == r10.set.thumb_document_id) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        TLRPC.Document r102 = r1.get(r2);
    L12:
        if (r102 == null) goto L14;
    L16:
        if (r102 == null) goto L18;
        r0.setSpan(new AnimatedEmojiSpan(r102, this.textPaint.getFontMetricsInt()), 0, 1, 33);
        CharSequence r02 = r0;
    L19:
        boolean r103 = this.emoji;
        if (r103 == true) goto L22;
    L24:
        if (r103 == false) goto L26;
        String r104 = LocaleController.getString(R.string.StoryContainsEmojiFrom);
    L27:
        setText(AndroidUtilities.replaceCharSequence("%s", r104, r02));
        return;
    L26:
        r104 = LocaleController.getString(R.string.StoryContainsStickersFrom);
        goto L27
    L22:
        if (this.stickers == false) goto L24;
        r104 = LocaleController.getString(R.string.StoryContainsStickersEmojiFrom);
        goto L27
    L18:
        r02 = r0.subSequence(2, r0.length());
        goto L19
    L14:
        if (r1.isEmpty() == true) goto L16;
        r102 = r1.get(0);
        goto L16
    L11:
        r102 = null;
        goto L12
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r18) {
        super.dispatchDraw(r18);
        float r2 = this.loadT;
        if (r2 >= 1.0f) goto L6;
        this.loadingDrawable.setAlpha((int) ((1.0f - r2) * 255.0f));
        this.loadingPath.rewind();
        Path r6 = this.loadingPath;
        float r7 = getPaddingLeft();
        float r8 = getPaddingTop();
        float r9 = getMeasuredWidth() - getPaddingRight();
        float r10 = getPaddingTop() + AndroidUtilities.dp(12.0f);
        Path.Direction r16 = Path.Direction.CW;
        r6.addRect(r7, r8, r9, r10, r16);
        this.loadingPath.addRect(getPaddingLeft(), getPaddingTop() + AndroidUtilities.dp(16.0f), getPaddingLeft() + (((getMeasuredWidth() - getPaddingRight()) - getPaddingLeft()) * 0.46f), getPaddingTop() + AndroidUtilities.dp(28.0f), r16);
        this.loadingDrawable.draw(r18);
        invalidate();
    L6:
        if (this.layout == null) goto L15;
        float r3 = 0.0f;
        if (this.loadT <= 0.0f) goto L16;
        r18.save();
        float r22 = getPaddingLeft();
        if (LocaleController.isRTL == true) goto L13;
        r3 = this.layoutLeft;
    L13:
        r18.translate(r22 - r3, getPaddingTop());
        this.textPaint.setAlpha((int) (this.loadT * 255.0f));
        this.layout.draw(r18);
        AnimatedEmojiSpan.drawAnimatedEmojis(r18, this.layout, this.stack, 0.0f, null, 0.0f, 0.0f, 0.0f, this.loadT, this.colorFilter);
        r18.restore();
        return;
    L16:
        return;
    }

    public EmojiPacksAlert getAlert() {
        if (this.inputSets != null) goto L7;
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(this, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return null;
    L7:
        return new EmojiPacksAlert(null, getContext(), this.resourcesProvider, this.inputSets);
    }

    public void load(int r7, boolean r8, TLObject r9, ArrayList<TLRPC.InputStickerSet> r10, Object r11) {
        boolean[] r102 = {true};
        this.parentObject = r11;
        if (r8 == false) goto L25;
        this.sets = new ArrayList();
        this.inputSets = new ArrayList();
        this.emoji = false;
        this.stickers = false;
        TLRPC.TL_messages_getAttachedStickers r0 = new TLRPC.TL_messages_getAttachedStickers();
        if ((r9 instanceof TLRPC.Photo) == false) goto L12;
        TLRPC.Photo r82 = (TLRPC.Photo) r9;
        TLRPC.TL_inputStickeredMediaPhoto r1 = new TLRPC.TL_inputStickeredMediaPhoto();
        TLRPC.TL_inputPhoto r3 = new TLRPC.TL_inputPhoto();
        r1.id = r3;
        r3.id = r82.id;
        r3.access_hash = r82.access_hash;
        byte[] r83 = r82.file_reference;
        r3.file_reference = r83;
        if (r83 != null) goto L9;
        r3.file_reference = new byte[0];
    L9:
        r0.media = r1;
    L17:
        StoryContainsEmojiButton$$ExternalSyntheticLambda1 r6 = new StoryContainsEmojiButton$$ExternalSyntheticLambda1(this, r11, r10, r102, r7);
        if (lastRequestParentObject != r11) goto L23;
        Vector<TLRPC.StickerSetCovered> r72 = lastResponse;
        if (r72 == null) goto L23;
        r102[0] = false;
        r6.run(r72, null);
        return;
    L23:
        ConnectionsManager.getInstance(r7).sendRequest(r0, new StoryContainsEmojiButton$$ExternalSyntheticLambda2(r11, r7, r0, r6));
        return;
    L12:
        if ((r9 instanceof TLRPC.Document) == false) goto L17;
        TLRPC.Document r84 = (TLRPC.Document) r9;
        TLRPC.TL_inputStickeredMediaDocument r12 = new TLRPC.TL_inputStickeredMediaDocument();
        TLRPC.TL_inputDocument r32 = new TLRPC.TL_inputDocument();
        r12.id = r32;
        r32.id = r84.id;
        r32.access_hash = r84.access_hash;
        byte[] r85 = r84.file_reference;
        r32.file_reference = r85;
        if (r85 != null) goto L16;
        r32.file_reference = new byte[0];
    L16:
        r0.media = r12;
        goto L17
    L25:
        this.emoji = true;
        this.stickers = false;
        ArrayList<TLRPC.InputStickerSet> r62 = new ArrayList();
        this.inputSets = r62;
        r62.addAll(r10);
        if (this.inputSets.size() != 1) goto L29;
        MediaDataController.getInstance(r7).getStickerSet(this.inputSets.get(0), 0, false, new StoryContainsEmojiButton$$ExternalSyntheticLambda3(this));
        return;
    L29:
        set(this.inputSets.size());
        animateLoad(false);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        StaticLayout r0 = this.layout;
        if (r0 == null) goto L6;
        this.stack = AnimatedEmojiSpan.update(0, this, this.stack, new Layout[]{r0});
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatedEmojiSpan.release(this, this.stack);
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        if (View.MeasureSpec.getMode(r5) != 1073741824) goto L5;
        boolean r62 = true;
    L6:
        int r0 = getPaddingTop();
        int r2 = AndroidUtilities.dp(29.0f);
        StaticLayout r3 = this.layout;
        if (r3 != null) goto L9;
        int r1 = AndroidUtilities.dp(29.0f);
    L10:
        int r02 = (r0 + AndroidUtilities.lerp(r2, r1, this.loadT)) + getPaddingBottom();
        if (r62 == false) goto L13;
        int r12 = View.MeasureSpec.getSize(r5);
    L14:
        setMeasuredDimension(r12, r02);
        int r52 = (View.MeasureSpec.getSize(r5) - getPaddingLeft()) - getPaddingRight();
        if (r62 == false) goto L27;
        CharSequence r63 = this.toSetText;
        if (r63 == null) goto L19;
    L22:
        if (r63 != null) goto L25;
        r63 = this.layout.getText();
    L25:
        setText(r63);
        this.toSetText = null;
        this.lastContentWidth = r52;
        return;
    L19:
        if (this.layout != null) goto L21;
        return;
    L21:
        if (this.lastContentWidth != r52) goto L22;
        return;
    L27:
        return;
    L13:
        r12 = getMinimumWidth();
        goto L14
    L9:
        r1 = r3.getHeight();
        goto L10
    L5:
        r62 = false;
        goto L6
    }

    public void setText(CharSequence r11) {
        if (getMeasuredWidth() > 0) goto L6;
        this.toSetText = r11;
        return;
    L6:
        int r5 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        if (r5 > 0) goto L10;
        this.toSetText = r11;
        return;
    L10:
        StaticLayout r2 = new StaticLayout(r11, this.textPaint, r5, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.layout = r2;
        float r0 = 0.0f;
        if (r2.getLineCount() <= 0) goto L13;
        float r112 = this.layout.getLineLeft(0);
    L14:
        this.layoutLeft = r112;
        if (this.layout.getLineCount() <= 0) goto L17;
        r0 = this.layout.getLineWidth(0);
    L17:
        this.layoutWidth = r0;
        this.stack = AnimatedEmojiSpan.update(0, this, this.stack, new Layout[]{this.layout});
        return;
    L13:
        r112 = 0.0f;
        goto L14
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.loadingDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
