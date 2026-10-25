package org.telegram.ui.Components.spoilers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;

/* loaded from: classes3.dex */
public class SpoilersTextView extends TextView implements TextSelectionHelper.SimpleSelectabeleView {
    private static Class editorClass;
    private static Field mEditor;
    private static Method mEditorInvalidateDisplayList;
    public boolean allowClickSpoilers;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmoji;
    private ColorFilter animatedEmojiColorFilter;
    public int cacheType;
    private boolean clearLinkOnLongPress;
    private SpoilersClickDetector clickDetector;
    private CharacterStyle currentLinkLoading;
    protected boolean disablePaddingInLinks;
    private boolean disablePaddingsOffset;
    private boolean disablePaddingsOffsetX;
    private boolean disablePaddingsOffsetY;
    private Object editor;
    private boolean isSpoilersRevealed;
    private Layout lastLayout;
    private int lastTextLength;
    private final LinkSpanDrawable.LinkCollector links;
    protected LinkSpanDrawable.LinksTextView.OnLinkPress onLongPressListener;
    protected LinkSpanDrawable.LinksTextView.OnLinkPress onPressListener;
    private Path path;
    private LinkSpanDrawable<ClickableSpan> pressedLink;
    private Theme.ResourcesProvider resourcesProvider;
    protected List<SpoilerEffect> spoilers;
    private Stack<SpoilerEffect> spoilersPool;
    private boolean triedGetInvalidate;
    private boolean useAlphaForEmoji;

    public static /* synthetic */ void $r8$lambda$5ElJgplKvGXN46JmPq6EymTVo8Q(SpoilersTextView r0, LinkSpanDrawable r1, ClickableSpan r2) {
        r0.lambda$dispatchTouchEvent$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Asx6Q99rfe7AiOriM_vJRi1dUy0(SpoilersTextView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$BTx0-PSVaIOSXyzKRTQBZ0iWB6Y, reason: not valid java name */
    public static /* synthetic */ void m13804$r8$lambda$BTx0PSVaIOSXyzKRTQBZ0iWB6Y(SpoilersTextView r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$vThpsFUiO-CKL8nQA1xKZ9G2jQ8, reason: not valid java name */
    public static /* synthetic */ void m13805$r8$lambda$vThpsFUiOCKL8nQA1xKZ9G2jQ8(SpoilersTextView r0, boolean r1, SpoilerEffect r2, float r3, float r4) {
        r0.lambda$new$2(r1, r2, r3, r4);
    }

    public SpoilersTextView(Context r2) {
        this(r2, null);
    }

    public SpoilersTextView(Context r2, Theme.ResourcesProvider r3) {
        this(r2, true, r3);
    }

    public SpoilersTextView(Context r2, boolean r3) {
        this(r2, r3, null);
    }

    public SpoilersTextView(Context r2, boolean r3, Theme.ResourcesProvider r4) {
        super(r2);
        this.spoilers = new ArrayList();
        this.spoilersPool = new Stack();
        this.path = new Path();
        this.allowClickSpoilers = true;
        this.cacheType = 0;
        this.useAlphaForEmoji = true;
        this.clearLinkOnLongPress = true;
        this.disablePaddingInLinks = true;
        this.lastLayout = null;
        this.links = new LinkSpanDrawable.LinkCollector(this);
        this.resourcesProvider = r4;
        this.clickDetector = new SpoilersClickDetector(this, this.spoilers, new SpoilersTextView$$ExternalSyntheticLambda1(this, r3));
        setTypeface(AndroidUtilities.regular());
    }

    private void invalidateSpoilers() {
        List<SpoilerEffect> r0 = this.spoilers;
        if (r0 != null) goto L5;
        return;
    L5:
        this.spoilersPool.addAll(r0);
        this.spoilers.clear();
        if (this.isSpoilersRevealed == false) goto L10;
        invalidate();
        return;
    L10:
        if (getLayout() != null) goto L12;
    L14:
        invalidate();
        return;
    L12:
        if ((getText() instanceof Spanned) == false) goto L14;
        SpoilerEffect.addSpoilers(this, this.spoilersPool, this.spoilers);
        goto L14
    }

    private /* synthetic */ void lambda$dispatchTouchEvent$3(LinkSpanDrawable r3, ClickableSpan r4) {
        LinkSpanDrawable.LinksTextView.OnLinkPress r0 = this.onLongPressListener;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.pressedLink != r3) goto L9;
        r0.run(r4);
        this.pressedLink = null;
        this.links.clear();
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        this.isSpoilersRevealed = true;
        invalidateSpoilers();
    }

    private /* synthetic */ void lambda$new$1() {
        post(new SpoilersTextView$$ExternalSyntheticLambda3(this));
    }

    private /* synthetic */ void lambda$new$2(boolean r5, SpoilerEffect r6, float r7, float r8) {
        if (this.isSpoilersRevealed == true) goto L10;
        if (r5 == false) goto L12;
        r6.setOnRippleEndCallback(new SpoilersTextView$$ExternalSyntheticLambda2(this));
        float r52 = (float) Math.sqrt(Math.pow(getWidth(), 2.0d) + Math.pow(getHeight(), 2.0d));
        Iterator<SpoilerEffect> r4 = this.spoilers.iterator();
    L8:
        if (r4.hasNext() == false) goto L13;
        r4.next().startRipple(r7, r8, r52);
        goto L8
    L13:
        return;
    L12:
        return;
    }

    public void clearLinks() {
        this.links.clear();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r8) {
        if (this.links == null) goto L33;
        Layout r0 = getLayout();
        ClickableSpan r2 = hit((int) r8.getX(), (int) r8.getY());
        if (r2 == null) goto L15;
        if (r8.getAction() != 0) goto L15;
        LinkSpanDrawable<ClickableSpan> r3 = new LinkSpanDrawable(r2, this.resourcesProvider, r8.getX(), r8.getY());
        r3.setColor(overrideLinkColor());
        this.pressedLink = r3;
        this.links.addLink(r3);
        SpannableString r82 = new SpannableString(r0.getText());
        int r4 = r82.getSpanStart(this.pressedLink.getSpan());
        int r83 = r82.getSpanEnd(this.pressedLink.getSpan());
        LinkPath r5 = this.pressedLink.obtainNewPath();
        if (this.disablePaddingInLinks == false) goto L11;
        float r6 = 0.0f;
    L12:
        r5.setCurrentLayout(r0, r4, r6);
        r0.getSelectionPath(r4, r83, r5);
        AndroidUtilities.runOnUIThread(new SpoilersTextView$$ExternalSyntheticLambda0(this, r3, r2), ViewConfiguration.getLongPressTimeout());
        return true;
    L11:
        r6 = getPaddingTop();
    L15:
        if (r8.getAction() != 1) goto L30;
        this.links.clear();
        LinkSpanDrawable<ClickableSpan> r02 = this.pressedLink;
        if (r02 != null) goto L19;
    L28:
        this.pressedLink = null;
        goto L30
    L19:
        if (r02.getSpan() != r2) goto L28;
        LinkSpanDrawable.LinksTextView.OnLinkPress r84 = this.onPressListener;
        LinkSpanDrawable<ClickableSpan> r03 = this.pressedLink;
        if (r84 == null) goto L24;
        r84.run((ClickableSpan) r03.getSpan());
    L26:
        this.pressedLink = null;
        return true;
    L24:
        if (r03.getSpan() == null) goto L26;
        ((ClickableSpan) this.pressedLink.getSpan()).onClick(this);
    L30:
        if (r8.getAction() != 3) goto L33;
        this.links.clear();
        this.pressedLink = null;
    L33:
        if (this.pressedLink == null) goto L36;
        return true;
    L36:
        if (this.allowClickSpoilers == false) goto L41;
        if (this.clickDetector.onTouchEvent(r8) == false) goto L41;
        return true;
    L41:
        return super.dispatchTouchEvent(r8);
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.SimpleSelectabeleView
    public Layout getStaticTextLayout() {
        return getLayout();
    }

    public ClickableSpan hit(int r6, int r7) {
        Layout r0 = getLayout();
        if (r0 != null) goto L5;
        return null;
    L5:
        int r62 = r6 - getPaddingLeft();
        int r72 = r7 - getPaddingTop();
        int r5 = r0.getLineForVertical(r72);
        float r63 = r62;
        int r2 = r0.getOffsetForHorizontal(r5, r63);
        float r3 = r0.getLineLeft(r5);
        if (r3 <= r63) goto L8;
    L18:
        return null;
    L8:
        if ((r3 + r0.getLineWidth(r5)) < r63) goto L18;
        if (r72 < 0) goto L18;
        if (r72 > r0.getHeight()) goto L18;
        ClickableSpan[] r52 = (ClickableSpan[]) new SpannableString(r0.getText()).getSpans(r2, r2, ClickableSpan.class);
        if (r52.length == 0) goto L18;
        if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == true) goto L18;
        return r52[0];
    }

    @Override // android.view.View, org.telegram.ui.Cells.TextSelectionHelper.SelectableView
    public void invalidate() {
        if (this.triedGetInvalidate == true) goto L12;
        this.triedGetInvalidate = true;
    L10:
        th = move-exception;
        FileLog.e(th);
        goto L12
    L6:
        if (editorClass != null) goto L12;
        Field r2 = TextView.class.getDeclaredField("mEditor");     // Catch: Throwable -> L10
        mEditor = r2;     // Catch: Throwable -> L10
        r2.setAccessible(true);     // Catch: Throwable -> L10
        Class<?> r22 = Class.forName("android.widget.Editor");     // Catch: Throwable -> L10
        editorClass = r22;     // Catch: Throwable -> L10
        Method r23 = r22.getDeclaredMethod("invalidateTextDisplayList", null);     // Catch: Throwable -> L10 Exception -> L24
        mEditorInvalidateDisplayList = r23;     // Catch: Throwable -> L10 Exception -> L24
        r23.setAccessible(true);     // Catch: Throwable -> L10 Exception -> L24
    L12:
        super.invalidate();
        if (isHardwareAccelerated() == false) goto L33;
        if (mEditorInvalidateDisplayList != null) goto L18;
        return;
    L18:
        if (this.editor != null) goto L20;
        this.editor = mEditor.get(this);     // Catch: Exception -> L25
    L20:
        Object r4 = this.editor;     // Catch: Exception -> L25
        if (r4 == null) goto L32;
        mEditorInvalidateDisplayList.invoke(r4, null);     // Catch: Exception -> L25
        return;
    L32:
        return;
    L34:
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateAnimatedEmoji(true);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatedEmojiSpan.release(this, this.animatedEmoji);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r15) {
        int r10 = getPaddingLeft();
        int r11 = getPaddingTop();
        r15.save();
        if (this.disablePaddingsOffset == true) goto L13;
        float r2 = 0.0f;
        if (this.disablePaddingsOffsetX == false) goto L7;
        float r1 = 0.0f;
    L9:
        if (this.disablePaddingsOffsetY == true) goto L12;
        r2 = r11;
    L12:
        r15.translate(r1, r2);
        goto L13
    L7:
        r1 = r10;
    L13:
        LinkSpanDrawable.LinkCollector r12 = this.links;
        if (r12 != null) goto L16;
    L18:
        r15.restore();
        boolean r122 = true;
        if (this.spoilers.isEmpty() == false) goto L21;
        super.onDraw(r15);
    L28:
        updateAnimatedEmoji(false);
        if (this.animatedEmoji == null) goto L32;
        r15.save();
        r15.translate(r10, r11);
        AnimatedEmojiSpan.drawAnimatedEmojis(r15, getLayout(), this.animatedEmoji, 0.0f, this.spoilers, 0.0f, getHeight(), 0.0f, 1.0f, this.animatedEmojiColorFilter);
        r15.restore();
    L32:
        if (this.spoilers.isEmpty() == false) goto L34;
        return;
    L34:
        if (this.spoilers.get(0).getRippleProgress() != (-1.0f)) goto L37;
        r122 = false;
    L37:
        if (r122 == false) goto L39;
        r15.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), null, 31);
    L40:
        r15.translate(r10, r11 + AndroidUtilities.dp(2.0f));
        Iterator<SpoilerEffect> r13 = this.spoilers.iterator();
    L42:
        if (r13.hasNext() == false) goto L44;
        SpoilerEffect r22 = r13.next();
        r22.setColor(getPaint().getColor());
        r22.draw(r15);
        goto L42
    L44:
        if (r122 == false) goto L46;
        this.path.rewind();
        this.spoilers.get(0).getRipplePath(this.path);
        r15.drawPath(this.path, Theme.PAINT_CLEAR);
    L46:
        r15.restore();
        return;
    L39:
        r15.save();
        goto L40
    L21:
        r15.save();
        this.path.rewind();
        Iterator<SpoilerEffect> r14 = this.spoilers.iterator();
    L23:
        if (r14.hasNext() == false) goto L25;
        Rect r23 = r14.next().getBounds();
        this.path.addRect(r23.left + r10, r23.top + r11, r23.right + r10, r23.bottom + r11, Path.Direction.CW);
        goto L23
    L25:
        r15.clipPath(this.path, Region.Op.DIFFERENCE);
        Emoji.emojiDrawingUseAlpha = this.useAlphaForEmoji;
        super.onDraw(r15);
        Emoji.emojiDrawingUseAlpha = true;
        r15.restore();
        if (this.spoilers.get(0).hasRipplePath() == false) goto L28;
        r15.save();
        r15.clipPath(this.path);
        this.path.rewind();
        this.spoilers.get(0).getRipplePath(this.path);
        r15.clipPath(this.path);
        super.onDraw(r15);
        r15.restore();
        goto L28
    L16:
        if (r12.draw(r15) == false) goto L18;
        invalidate();
        goto L18
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        invalidateSpoilers();
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        invalidateSpoilers();
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        invalidateSpoilers();
        updateAnimatedEmoji(true);
    }

    public int overrideLinkColor() {
        return Theme.getColor(Theme.key_chat_linkSelectBackground, this.resourcesProvider);
    }

    public void setClearLinkOnLongPress(boolean r1) {
        this.clearLinkOnLongPress = r1;
    }

    public void setDisablePaddingsOffset(boolean r1) {
        this.disablePaddingsOffset = r1;
    }

    public void setDisablePaddingsOffsetX(boolean r1) {
        this.disablePaddingsOffsetX = r1;
    }

    public void setDisablePaddingsOffsetY(boolean r1) {
        this.disablePaddingsOffsetY = r1;
    }

    public void setLoading(CharacterStyle r6) {
        if (this.currentLinkLoading == r6) goto L8;
        this.links.clearLoading(true);
        this.currentLinkLoading = r6;
        LoadingDrawable r62 = LinkSpanDrawable.LinkCollector.makeLoading(getLayout(), r6, getPaddingTop());
        if (r62 == null) goto L9;
        int r0 = Theme.getColor(Theme.key_chat_linkSelectBackground, this.resourcesProvider);
        r62.setColors(Theme.multAlpha(r0, 0.8f), Theme.multAlpha(r0, 1.3f), Theme.multAlpha(r0, 1.0f), Theme.multAlpha(r0, 4.0f));
        r62.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
        this.links.addLoading(r62);
        return;
    L9:
        return;
    }

    public void setOnLinkLongPressListener(LinkSpanDrawable.LinksTextView.OnLinkPress r1) {
        this.onLongPressListener = r1;
    }

    public void setOnLinkPressListener(LinkSpanDrawable.LinksTextView.OnLinkPress r1) {
        this.onPressListener = r1;
    }

    @Override // android.widget.TextView
    public void setText(CharSequence r2, TextView.BufferType r3) {
        this.isSpoilersRevealed = false;
        super.setText(r2, r3);
    }

    @Override // android.widget.TextView
    public void setTextColor(int r3) {
        super.setTextColor(r3);
        this.animatedEmojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
    }

    public void setUseAlphaForEmoji(boolean r1) {
        this.useAlphaForEmoji = r1;
    }

    public void updateAnimatedEmoji(boolean r4) {
        if (getLayout() != null) goto L5;
    L8:
        int r0 = 0;
    L9:
        if (r4 == false) goto L11;
    L16:
        this.animatedEmoji = AnimatedEmojiSpan.update(this.cacheType, this, this.animatedEmoji, new Layout[]{getLayout()});
        this.lastLayout = getLayout();
        this.lastTextLength = r0;
        return;
    L11:
        if (this.lastLayout != getLayout()) goto L16;
        if (this.lastTextLength != r0) goto L16;
        return;
    L5:
        if (getLayout().getText() == null) goto L8;
        r0 = getLayout().getText().length();
        goto L9
    }
}
