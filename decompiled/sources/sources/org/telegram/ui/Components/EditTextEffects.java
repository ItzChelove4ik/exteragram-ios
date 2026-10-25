package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.Looper;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.view.MotionEvent;
import android.widget.TextView;
import com.exteragram.messenger.components.ReceiveContentEditText;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Components.spoilers.SpoilersClickDetector;

/* loaded from: classes3.dex */
public abstract class EditTextEffects extends ReceiveContentEditText {
    private static final int SPOILER_TIMEOUT = 10000;
    private static Boolean allowHackingTextCanvasCache;
    private ColorFilter animatedEmojiColorFilter;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiDrawables;
    private SpoilersClickDetector clickDetector;
    private boolean clipToPadding;
    public boolean drawAnimatedEmojiDrawables;
    private boolean editedWhileQuoteUpdating;
    private Integer emojiColor;
    private boolean isSpoilersRevealed;
    private Layout lastLayout;
    private float lastRippleX;
    private float lastRippleY;
    private int lastText2Length;
    private int lastTextColor;
    private int lastTextLength;
    protected float offsetY;
    private Path path;
    private boolean postedSpoilerTimeout;
    private ArrayList<QuoteSpan.Block> quoteBlocks;
    private boolean quoteBlocksUpdating;
    public int quoteColor;
    private boolean[] quoteUpdateLayout;
    private int quoteUpdatesTries;
    private Rect rect;
    private int selEnd;
    private int selStart;
    private boolean shouldRevealSpoilersByTouch;
    private Runnable spoilerTimeout;
    private List<SpoilerEffect> spoilers;
    private Stack<SpoilerEffect> spoilersPool;
    public boolean suppressOnTextChanged;
    public boolean wrapCanvasToFixClipping;
    private NoClipCanvas wrappedCanvas;

    public static /* synthetic */ void $r8$lambda$4cPFRWlPxnBkgSwpPDmij1iMpXA(EditTextEffects r0, SpoilerEffect r1, float r2, float r3) {
        r0.onSpoilerClicked(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$I32ThbVk2-wk83OO20clpCP4R94, reason: not valid java name */
    public static /* synthetic */ void m10150$r8$lambda$I32ThbVk2wk83OO20clpCP4R94(EditTextEffects r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$Pk9jDh9uz-1bgUercWo7dht6vkE, reason: not valid java name */
    public static /* synthetic */ void m10151$r8$lambda$Pk9jDh9uz1bgUercWo7dht6vkE(EditTextEffects r0) {
        r0.lambda$dispatchTouchEvent$5();
    }

    /* renamed from: $r8$lambda$R1UmakiP5bWSE7akw6bK7YW-tKc, reason: not valid java name */
    public static /* synthetic */ void m10152$r8$lambda$R1UmakiP5bWSE7akw6bK7YWtKc(EditTextEffects r0) {
        r0.lambda$onSpoilerClicked$3();
    }

    public static /* synthetic */ void $r8$lambda$S3YmEGQUplFFnhofjcLy5LhRXzU(EditTextEffects r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$ZXLEYtlsJXGYbjzPpK8AUxU1Hoo(EditTextEffects r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$xbGxylfPiKfxbFGVWv7vqCoa-bU, reason: not valid java name */
    public static /* synthetic */ void m10153$r8$lambda$xbGxylfPiKfxbFGVWv7vqCoabU(EditTextEffects r0) {
        r0.lambda$onSpoilerClicked$4();
    }

    public EditTextEffects(Context r4) {
        super(r4, null, 0, R.style.EditTextNoBackgroundStyle);
        this.spoilers = new ArrayList();
        this.spoilersPool = new Stack();
        this.quoteBlocks = new ArrayList();
        this.shouldRevealSpoilersByTouch = true;
        this.path = new Path();
        this.drawAnimatedEmojiDrawables = true;
        this.lastLayout = null;
        this.spoilerTimeout = new EditTextEffects$$ExternalSyntheticLambda3(this);
        this.rect = new Rect();
        this.wrapCanvasToFixClipping = allowHackingTextCanvas();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) goto L6;
        this.clickDetector = new SpoilersClickDetector(this, this.spoilers, new EditTextEffects$$ExternalSyntheticLambda4(this));
        return;
    }

    public static boolean allowHackingTextCanvas() {
        if (allowHackingTextCanvasCache != null) goto L21;
        String r0 = Build.MANUFACTURER;
        if (r0 != null) goto L7;
    L12:
        String r02 = Build.MODEL;
        if (r02 != null) goto L15;
    L18:
        boolean r03 = true;
    L19:
        allowHackingTextCanvasCache = Boolean.valueOf(r03);
        goto L21
    L15:
        if (r02.toLowerCase().contains("mediapad") == false) goto L18;
    L17:
        r03 = false;
        goto L19
    L7:
        if (r0.toLowerCase().contains("honor") == true) goto L17;
        if (r0.toLowerCase().contains("huawei") == true) goto L17;
        if (r0.toLowerCase().contains("alps") == true) goto L17;
    L21:
        return allowHackingTextCanvasCache.booleanValue();
    }

    private void checkSpoilerTimeout() {
        if (getLayout() == null) goto L5;
        CharSequence r0 = getLayout().getText();
    L6:
        boolean r3 = false;
        if ((r0 instanceof Spannable) == false) goto L24;
        Spannable r02 = (Spannable) r0;
        TextStyleSpan[] r1 = (TextStyleSpan[]) r02.getSpans(0, r02.length(), TextStyleSpan.class);
        int r4 = r1.length;
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L24;
        TextStyleSpan r6 = r1[r5];
        int r7 = r02.getSpanStart(r6);
        int r8 = r02.getSpanEnd(r6);
        if (r6.isSpoiler() == false) goto L22;
        int r62 = this.selStart;
        if (r7 > r62) goto L15;
    L16:
        if (r62 <= r7) goto L18;
        if (r62 >= r8) goto L18;
    L21:
        removeCallbacks(this.spoilerTimeout);
        this.postedSpoilerTimeout = false;
        r3 = true;
    L18:
        int r63 = this.selEnd;
        if (r63 <= r7) goto L22;
        if (r63 >= r8) goto L22;
    L15:
        if (r8 < this.selEnd) goto L21;
    L22:
        r5 = r5 + 1;
    L24:
        if (this.isSpoilersRevealed == false) goto L37;
        if (r3 == false) goto L27;
        return;
    L27:
        if (this.postedSpoilerTimeout == true) goto L39;
        this.postedSpoilerTimeout = true;
        postDelayed(this.spoilerTimeout, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        return;
    L39:
        return;
    L37:
        return;
    L5:
        r0 = null;
        goto L6
    }

    private /* synthetic */ void lambda$dispatchTouchEvent$5() {
        invalidateQuotes(true);
    }

    private /* synthetic */ void lambda$new$0() {
        setSpoilersRevealed(false, true);
    }

    private /* synthetic */ void lambda$new$1() {
        post(new EditTextEffects$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$new$2() {
        this.postedSpoilerTimeout = false;
        this.isSpoilersRevealed = false;
        invalidateSpoilers();
        if (this.spoilers.isEmpty() == true) goto L9;
        this.spoilers.get(0).setOnRippleEndCallback(new EditTextEffects$$ExternalSyntheticLambda5(this));
        float r0 = (float) Math.sqrt(Math.pow(getWidth(), 2.0d) + Math.pow(getHeight(), 2.0d));
        Iterator<SpoilerEffect> r1 = this.spoilers.iterator();
    L7:
        if (r1.hasNext() == false) goto L11;
        r1.next().startRipple(this.lastRippleX, this.lastRippleY, r0, true);
        goto L7
    L11:
        return;
    }

    private /* synthetic */ void lambda$onSpoilerClicked$3() {
        invalidateSpoilers();
        checkSpoilerTimeout();
    }

    private /* synthetic */ void lambda$onSpoilerClicked$4() {
        post(new EditTextEffects$$ExternalSyntheticLambda2(this));
    }

    private void onSpoilerClicked(SpoilerEffect r7, float r8, float r9) {
        if (this.isSpoilersRevealed == true) goto L9;
        this.lastRippleX = r8;
        this.lastRippleY = r9;
        this.postedSpoilerTimeout = false;
        removeCallbacks(this.spoilerTimeout);
        setSpoilersRevealed(true, false);
        r7.setOnRippleEndCallback(new EditTextEffects$$ExternalSyntheticLambda1(this));
        float r72 = (float) Math.sqrt(Math.pow(getWidth(), 2.0d) + Math.pow(getHeight(), 2.0d));
        Iterator<SpoilerEffect> r6 = this.spoilers.iterator();
    L7:
        if (r6.hasNext() == false) goto L11;
        r6.next().startRipple(r8, r9, r72);
        goto L7
    L11:
        return;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r12) {
        if (QuoteSpan.onTouch(r12, getPaddingTop() - getScrollY(), this.quoteBlocks, new EditTextEffects$$ExternalSyntheticLambda0(this)) == false) goto L6;
        return true;
    L6:
        if (this.shouldRevealSpoilersByTouch == false) goto L15;
        SpoilersClickDetector r0 = this.clickDetector;
        if (r0 == null) goto L15;
        if (r0.onTouchEvent(r12) == false) goto L15;
        if (r12.getActionMasked() != 1) goto L14;
        MotionEvent r02 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
        super.dispatchTouchEvent(r02);
        r02.recycle();
    L14:
        boolean r03 = true;
    L17:
        if (super.dispatchTouchEvent(r12) == true) goto L21;
        if (r03 == true) goto L21;
        return false;
    L21:
        return true;
    L15:
        r03 = false;
        goto L17
    }

    public int emojiCacheType() {
        return AnimatedEmojiDrawable.getCacheTypeForEnterView();
    }

    public int getDrawScrollY() {
        return computeVerticalScrollOffset();
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public CharSequence getTextToUse() {
        return QuoteSpan.stripNewlineHacks(getText());
    }

    public void invalidateEffects() {
        Editable r0 = getText();
        if (r0 == null) goto L10;
        int r3 = 0;
        TextStyleSpan[] r02 = (TextStyleSpan[]) r0.getSpans(0, r0.length(), TextStyleSpan.class);
        int r1 = r02.length;
    L5:
        if (r3 >= r1) goto L10;
        TextStyleSpan r2 = r02[r3];
        if (r2.isSpoiler() == false) goto L9;
        r2.setSpoilerRevealed(this.isSpoilersRevealed);
    L9:
        r3 = r3 + 1;
    L10:
        invalidateSpoilers();
    }

    public void invalidateQuotes(boolean r5) {
        if (this.quoteBlocksUpdating == false) goto L6;
        this.editedWhileQuoteUpdating = true;
        return;
    L6:
        int r2 = 0;
        if (getLayout() != null) goto L9;
    L12:
        int r0 = 0;
    L13:
        if (r5 == false) goto L15;
    L16:
        this.quoteUpdatesTries = 2;
        this.lastText2Length = r0;
    L18:
        if (this.quoteUpdatesTries > 0) goto L20;
        return;
    L20:
        if (this.quoteUpdateLayout != null) goto L22;
        this.quoteUpdateLayout = new boolean[1];
    L22:
        this.quoteUpdateLayout[0] = false;
        this.editedWhileQuoteUpdating = false;
        this.quoteBlocksUpdating = true;
        this.quoteBlocks = QuoteSpan.updateQuoteBlocks(this, getLayout(), this.quoteBlocks, this.quoteUpdateLayout);
        if (this.editedWhileQuoteUpdating == false) goto L25;
        this.quoteBlocks = QuoteSpan.updateQuoteBlocks(this, getLayout(), this.quoteBlocks, this.quoteUpdateLayout);
    L25:
        this.quoteBlocksUpdating = false;
        this.editedWhileQuoteUpdating = false;
        if (this.quoteUpdateLayout[0] == false) goto L28;
        resetFontMetricsCache();
    L28:
        this.quoteUpdatesTries--;
        if (getLayout() != null) goto L31;
    L34:
        this.lastText2Length = r2;
        return;
    L31:
        if (getLayout().getText() == null) goto L34;
        r2 = getLayout().getText().length();
        goto L34
    L15:
        if (this.lastText2Length == r0) goto L18;
    L9:
        if (getLayout().getText() == null) goto L12;
        r0 = getLayout().getText().length();
        goto L13
    }

    public void invalidateSpoilers() {
        List<SpoilerEffect> r0 = this.spoilers;
        if (r0 != null) goto L5;
        return;
    L5:
        this.spoilersPool.addAll(r0);
        this.spoilers.clear();
        if (this.isSpoilersRevealed == false) goto L9;
        invalidate();
        return;
    L9:
        Layout r02 = getLayout();
        if (r02 != null) goto L12;
    L23:
        invalidate();
        return;
    L12:
        if ((r02.getText() instanceof Spannable) == false) goto L23;
        if (this.drawAnimatedEmojiDrawables == false) goto L18;
        AnimatedEmojiSpan.EmojiGroupedSpans r03 = this.animatedEmojiDrawables;
        if (r03 == null) goto L18;
        r03.recordPositions(false);
    L18:
        SpoilerEffect.addSpoilers(this, this.spoilersPool, this.spoilers, this.quoteBlocks);
        if (this.drawAnimatedEmojiDrawables == false) goto L23;
        AnimatedEmojiSpan.EmojiGroupedSpans r04 = this.animatedEmojiDrawables;
        if (r04 == null) goto L23;
        r04.recordPositions(true);
        goto L23
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateAnimatedEmoji(true);
        invalidateQuotes(false);
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.spoilerTimeout);
        AnimatedEmojiSpan.release(this, this.animatedEmojiDrawables);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r15) {
        Canvas r0 = r15;
        r15.save();
        if (this.clipToPadding == true) goto L5;
    L7:
        int r10 = getPaddingLeft();
        if (this.spoilers.isEmpty() == true) goto L14;
        this.path.rewind();
        Iterator<SpoilerEffect> r1 = this.spoilers.iterator();
    L11:
        if (r1.hasNext() == false) goto L13;
        Rect r2 = r1.next().getBounds();
        this.path.addRect(r2.left + r10, r2.top, r2.right + r10, r2.bottom, Path.Direction.CW);
        goto L11
    L13:
        r15.clipPath(this.path, Region.Op.DIFFERENCE);
    L14:
        invalidateQuotes(false);
        int r7 = 0;
    L16:
        if (r7 >= this.quoteBlocks.size()) goto L18;
        Canvas r12 = r0;
        this.quoteBlocks.get(r7).draw(r12, 0.0f, getWidth(), this.quoteColor, 1.0f, getPaint());
        r0 = r12;
        r7 = r7 + 1;
        goto L16
    L18:
        updateAnimatedEmoji(false);
        if (this.wrapCanvasToFixClipping == true) goto L21;
        super.onDraw(r15);
    L26:
        if (this.drawAnimatedEmojiDrawables == true) goto L28;
    L30:
        r15.restore();
        if (this.spoilers.isEmpty() == false) goto L33;
        return;
    L33:
        if (this.spoilers.get(0).hasRipplePath() == false) goto L42;
        r15.save();
        r15.clipPath(this.path);
        this.path.rewind();
        this.spoilers.get(0).getRipplePath(this.path);
        r15.clipPath(this.path);
        r15.translate(0.0f, -getPaddingTop());
        if (this.wrapCanvasToFixClipping == true) goto L37;
        super.onDraw(r15);
    L41:
        r15.restore();
        goto L42
    L37:
        if (this.wrappedCanvas != null) goto L39;
        this.wrappedCanvas = new NoClipCanvas();
    L39:
        NoClipCanvas r13 = this.wrappedCanvas;
        r13.canvas = r0;
        super.onDraw(r13);
    L42:
        this.rect.set(0, (int) ((getDrawScrollY() - super.getExtendedPaddingTop()) - this.offsetY), getWidth(), (int) (((getMeasuredHeight() + getDrawScrollY()) + super.getExtendedPaddingBottom()) - this.offsetY));
        r15.save();
        r15.clipRect(this.rect);
        r15.translate(r10, 0.0f);
        Iterator<SpoilerEffect> r14 = this.spoilers.iterator();
    L44:
        if (r14.hasNext() == false) goto L57;
        SpoilerEffect r22 = r14.next();
        Rect r3 = r22.getBounds();
        Rect r4 = this.rect;
        int r5 = r4.top;
        int r6 = r3.bottom;
        if (r5 > r6) goto L50;
        if (r4.bottom < r3.top) goto L50;
    L53:
        if (r22.insideQuote == false) goto L55;
        int r32 = this.quoteColor;
    L56:
        r22.setColor(r32);
        r22.draw(r15);
        goto L44
    L55:
        r32 = getPaint().getColor();
    L50:
        if (r3.top > r4.bottom) goto L44;
        if (r6 < r5) goto L44;
    L57:
        r15.restore();
        return;
    L28:
        if (this.animatedEmojiDrawables == null) goto L30;
        r15.save();
        r15.translate(getPaddingLeft(), 0.0f);
        int r16 = getDrawScrollY();
        AnimatedEmojiSpan.drawAnimatedEmojis(r0, getLayout(), this.animatedEmojiDrawables, 0.0f, this.spoilers, r16 - AndroidUtilities.dp(6.0f), r16 + computeVerticalScrollExtent(), 0.0f, 1.0f, this.animatedEmojiColorFilter);
        r15.restore();
        goto L30
    L21:
        if (this.wrappedCanvas != null) goto L23;
        this.wrappedCanvas = new NoClipCanvas();
    L23:
        NoClipCanvas r17 = this.wrappedCanvas;
        r17.canvas = r0;
        super.onDraw(r17);
        goto L26
    L5:
        if (getScrollY() == 0) goto L7;
        r15.clipRect(-AndroidUtilities.dp(3.0f), (getScrollY() - super.getExtendedPaddingTop()) - this.offsetY, getMeasuredWidth(), ((getMeasuredHeight() + getScrollY()) + super.getExtendedPaddingBottom()) - this.offsetY);
        goto L7
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        invalidateQuotes(false);
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onSelectionChanged(int r2, int r3) {
        super.onSelectionChanged(r2, r3);
        if (this.suppressOnTextChanged == false) goto L5;
        return;
    L5:
        this.selStart = r2;
        this.selEnd = r3;
        checkSpoilerTimeout();
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        invalidateEffects();
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
    public void onTextChanged(CharSequence r4, int r5, int r6, int r7) {
        super.onTextChanged(r4, r5, r6, r7);
        if (this.suppressOnTextChanged == true) goto L17;
        invalidateEffects();
        Layout r0 = getLayout();     // Catch: Exception -> L15
        if ((r4 instanceof Spannable) == false) goto L17;
        if (r0 == null) goto L17;
        int r42 = r0.getLineForOffset(r5);     // Catch: Exception -> L15
        int r52 = (int) r0.getPrimaryHorizontal(r5);     // Catch: Exception -> L15
        int r43 = (int) ((r0.getLineTop(r42) + r0.getLineBottom(r42)) / 2.0f);     // Catch: Exception -> L15
        Iterator<SpoilerEffect> r02 = this.spoilers.iterator();     // Catch: Exception -> L15
    L10:
        if (r02.hasNext() == false) goto L17;
        SpoilerEffect r1 = r02.next();     // Catch: Exception -> L15
        if (r1.getBounds().contains(r52, r43) == false) goto L10;
        int r72 = r7 - r6;     // Catch: Exception -> L15
        this.selStart += r72;
        this.selEnd += r72;
        onSpoilerClicked(r1, r52, r43);     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
    L17:
        updateAnimatedEmoji(true);
        invalidateQuotes(true);
        invalidate();
    }

    public void recycleEmojis() {
        AnimatedEmojiSpan.release(this, this.animatedEmojiDrawables);
    }

    public void resetFontMetricsCache() {
        float r0 = getTextSize();
        setTextSize(0, 1.0f + r0);
        setTextSize(0, r0);
    }

    public void setClipToPadding(boolean r1) {
        this.clipToPadding = r1;
    }

    public void setEmojiColor(Integer r3) {
        this.emojiColor = r3;
        if (r3 != null) goto L5;
        int r32 = this.lastTextColor;
    L6:
        this.animatedEmojiColorFilter = new PorterDuffColorFilter(r32, PorterDuff.Mode.SRC_IN);
        invalidate();
        return;
    L5:
        r32 = r3.intValue();
        goto L6
    }

    public void setOffsetY(float r1) {
        this.offsetY = r1;
        invalidate();
    }

    public void setShouldRevealSpoilersByTouch(boolean r1) {
        this.shouldRevealSpoilersByTouch = r1;
    }

    public void setSpoilersRevealed(boolean r8, boolean r9) {
        this.isSpoilersRevealed = r8;
        Editable r0 = getText();
        if (r0 == null) goto L10;
        TextStyleSpan[] r2 = (TextStyleSpan[]) r0.getSpans(0, r0.length(), TextStyleSpan.class);
        int r3 = r2.length;
        int r4 = 0;
    L5:
        if (r4 >= r3) goto L10;
        TextStyleSpan r5 = r2[r4];
        if (r5.isSpoiler() == false) goto L9;
        r5.setSpoilerRevealed(r8);
    L9:
        r4 = r4 + 1;
    L10:
        this.suppressOnTextChanged = true;
        setText(r0, TextView.BufferType.EDITABLE);
        setSelection(this.selStart, this.selEnd);
        this.suppressOnTextChanged = false;
        if (r9 == false) goto L17;
        invalidateSpoilers();
        return;
    }

    @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
    public void setText(CharSequence r2, TextView.BufferType r3) {
        if (this.suppressOnTextChanged == true) goto L7;
        this.isSpoilersRevealed = false;
        Stack<SpoilerEffect> r0 = this.spoilersPool;
        if (r0 == null) goto L7;
        r0.clear();
    L7:
        super.setText(r2, r3);
    }

    @Override // android.widget.TextView
    public void setTextColor(int r3) {
        this.lastTextColor = r3;
        super.setTextColor(r3);
        Integer r1 = this.emojiColor;
        if (r1 == null) goto L6;
        r3 = r1.intValue();
    L6:
        this.animatedEmojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
    }

    public void updateAnimatedEmoji(boolean r4) {
        if (this.drawAnimatedEmojiDrawables == true) goto L6;
        return;
    L6:
        if (getLayout() != null) goto L8;
    L11:
        int r0 = 0;
    L12:
        if (r4 == false) goto L14;
    L19:
        this.animatedEmojiDrawables = AnimatedEmojiSpan.update(emojiCacheType(), this, this.animatedEmojiDrawables, new Layout[]{getLayout()});
        this.lastLayout = getLayout();
        this.lastTextLength = r0;
        return;
    L14:
        if (this.lastLayout != getLayout()) goto L19;
        if (this.lastTextLength != r0) goto L19;
        return;
    L8:
        if (getLayout().getText() == null) goto L11;
        r0 = getLayout().getText().length();
        goto L12
    }
}
