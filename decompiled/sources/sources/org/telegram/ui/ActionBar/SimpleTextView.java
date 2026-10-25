package org.telegram.ui.ActionBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.exteragram.messenger.utils.ui.TextPaint;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.EmptyStubSpan;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.Components.spoilers.SpoilerEffect;

/* loaded from: classes3.dex */
public class SimpleTextView extends View implements Drawable.Callback {
    private boolean attachedToWindow;
    private boolean buildFullLayout;
    private boolean canHideRightDrawable;
    private int currentScrollDelay;
    private int drawablePadding;
    private boolean ellipsizeByGradient;
    private boolean ellipsizeByGradientLeft;
    private int ellipsizeByGradientWidthDp;
    private int emojiCacheType;
    private AnimatedEmojiSpan.EmojiGroupedSpans emojiStack;
    private ColorFilter emojiStackColorFilter;
    private Paint fadeEllpsizePaint;
    private int fadeEllpsizePaintWidth;
    private Paint fadePaint;
    private Paint fadePaintBack;
    private Layout firstLineLayout;
    private Boolean forceEllipsizeByGradientLeft;
    private float fullAlpha;
    private Layout fullLayout;
    private int fullLayoutAdditionalWidth;
    private float fullLayoutLeftCharactersOffset;
    private int fullLayoutLeftOffset;
    private int fullTextMaxLines;
    private int gravity;
    private long lastUpdateTime;
    private int lastWidth;
    private Layout layout;
    private float layoutX;
    private float layoutY;
    private Drawable leftDrawable;
    private boolean leftDrawableOutside;
    private int leftDrawableTopPadding;
    private final Runnable longPressRunnable;
    private Layout.Alignment mAlignment;
    private int maxLines;
    private boolean maybeClickDrawable1;
    private boolean maybeClickDrawable2;
    private int minWidth;
    private int minusWidth;
    private int offsetX;
    private int offsetY;
    private int paddingRight;
    private Layout partLayout;
    private Path path;
    private Drawable replacedDrawable;
    private String replacedText;
    private int replacingDrawableTextIndex;
    private float replacingDrawableTextOffset;
    private Drawable rightDrawable;
    private Drawable rightDrawable2;
    private View.OnClickListener rightDrawable2OnClickListener;
    public int rightDrawable2X;
    public int rightDrawable2Y;
    private boolean rightDrawableHidden;
    private boolean rightDrawableInside;
    private View.OnClickListener rightDrawableOnClickListener;
    private boolean rightDrawableOutside;
    private float rightDrawableScale;
    private int rightDrawableTopPadding;
    public int rightDrawableX;
    public int rightDrawableY;
    private boolean scrollNonFitText;
    private float scrollingOffset;
    private List<SpoilerEffect> spoilers;
    private Stack<SpoilerEffect> spoilersPool;
    private CharSequence text;
    private boolean textDoesNotFit;
    private int textHeight;
    private TextPaint textPaint;
    private int textWidth;
    private int totalWidth;
    private float touchDownXDrawable1;
    private float touchDownXDrawable2;
    private float touchDownYDrawable1;
    private float touchDownYDrawable2;
    private boolean usaAlphaForEmoji;
    private boolean wasLayout;
    private boolean widthWrapContent;
    private Drawable wrapBackgroundDrawable;

    /* loaded from: classes6.dex */
    public interface PressableDrawable {
        void setPressed(boolean r1);
    }

    public static /* synthetic */ void $r8$lambda$INzI8479wtYP6zBpt2nO8OTcKn8(SimpleTextView r0) {
        r0.lambda$new$0();
    }

    public SimpleTextView(Context r3) {
        super(r3);
        this.gravity = 51;
        this.maxLines = 1;
        this.rightDrawableScale = 1.0f;
        this.drawablePadding = AndroidUtilities.dp(4.0f);
        this.ellipsizeByGradientWidthDp = 16;
        this.fullTextMaxLines = 3;
        this.spoilers = new ArrayList();
        this.spoilersPool = new Stack();
        this.path = new Path();
        this.emojiCacheType = 0;
        this.mAlignment = Layout.Alignment.ALIGN_NORMAL;
        this.maybeClickDrawable1 = false;
        this.touchDownXDrawable1 = 0.0f;
        this.touchDownYDrawable1 = 0.0f;
        this.maybeClickDrawable2 = false;
        this.touchDownXDrawable2 = 0.0f;
        this.touchDownYDrawable2 = 0.0f;
        this.longPressRunnable = new SimpleTextView$$ExternalSyntheticLambda0(this);
        this.textPaint = new TextPaint(1);
        setImportantForAccessibility(1);
    }

    private void calcOffset(int r8) {
        Layout r0 = this.layout;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getLineCount() <= 0) goto L55;
        this.textWidth = (int) Math.max(Math.ceil(this.layout.getLineWidth(0)), Math.ceil(this.layout.getLineRight(0) - this.layout.getLineLeft(0)));
        Layout r02 = this.fullLayout;
        boolean r3 = true;
        if (r02 == null) goto L11;
        this.textHeight = r02.getLineBottom(r02.getLineCount() - 1);
    L16:
        int r03 = this.gravity;
        if ((r03 & 7) != 1) goto L20;
        this.offsetX = ((r8 - this.textWidth) / 2) - ((int) this.layout.getLineLeft(0));
    L32:
        this.offsetX += getPaddingLeft();
        if (this.rightDrawableInside == true) goto L35;
        int r04 = 0;
    L47:
        if ((this.textWidth + r04) > (r8 - this.paddingRight)) goto L50;
        r3 = false;
    L50:
        this.textDoesNotFit = r3;
        checkUi_layerType();
        Layout r82 = this.fullLayout;
        if (r82 == null) goto L55;
        if (this.fullLayoutAdditionalWidth <= 0) goto L55;
        this.fullLayoutLeftCharactersOffset = r82.getPrimaryHorizontal(0) - this.firstLineLayout.getPrimaryHorizontal(0);
        goto L55
    L35:
        if (this.rightDrawable != null) goto L37;
    L39:
        r04 = 0;
    L41:
        if (this.rightDrawable2 == null) goto L47;
        if (this.rightDrawableOutside == true) goto L47;
        r04 = r04 + ((int) (r4.getIntrinsicWidth() * this.rightDrawableScale));
        goto L47
    L37:
        if (this.rightDrawableOutside == true) goto L39;
        r04 = (int) (r0.getIntrinsicWidth() * this.rightDrawableScale);
        goto L41
    L20:
        if ((r03 & 7) != 3) goto L26;
        Layout r05 = this.firstLineLayout;
        if (r05 == null) goto L24;
        this.offsetX = -((int) r05.getLineLeft(0));
        goto L32
    L24:
        this.offsetX = -((int) this.layout.getLineLeft(0));
        goto L32
    L26:
        if (this.layout.getLineLeft(0) != 0.0f) goto L31;
        Layout r06 = this.firstLineLayout;
        if (r06 == null) goto L30;
        this.offsetX = (int) (r8 - r06.getLineWidth(0));
        goto L32
    L30:
        this.offsetX = r8 - this.textWidth;
        goto L32
    L31:
        this.offsetX = -AndroidUtilities.dp(8.0f);
        goto L32
    L11:
        if (this.maxLines > 1) goto L13;
    L15:
        this.textHeight = this.layout.getLineBottom(0);
        goto L16
    L13:
        if (this.layout.getLineCount() <= 0) goto L15;
        Layout r07 = this.layout;
        this.textHeight = r07.getLineBottom(r07.getLineCount() - 1);
    L55:
        int r83 = this.replacingDrawableTextIndex;
        if (r83 < 0) goto L59;
        this.replacingDrawableTextOffset = this.layout.getPrimaryHorizontal(r83);
        return;
    L59:
        this.replacingDrawableTextOffset = 0.0f;
    }

    private void checkUi_layerType() {
        if (this.scrollNonFitText == false) goto L10;
        if (this.textDoesNotFit == false) goto L7;
    L11:
        int r0 = 2;
    L14:
        if (getLayerType() == r0) goto L17;
        setLayerType(r0, null);
        invalidate();
        return;
    L17:
        return;
    L7:
        if (this.scrollingOffset != 0.0f) goto L11;
    L10:
        if (this.ellipsizeByGradient == true) goto L11;
        r0 = 0;
        goto L14
    }

    private void clipOutSpoilers(Canvas r9) {
        if (this.spoilers.isEmpty() == false) goto L5;
        return;
    L5:
        this.path.rewind();
        Iterator<SpoilerEffect> r0 = this.spoilers.iterator();
    L7:
        if (r0.hasNext() == false) goto L9;
        Rect r1 = r0.next().getBounds();
        this.path.addRect(r1.left, r1.top, r1.right, r1.bottom, Path.Direction.CW);
        goto L7
    L9:
        r9.clipPath(this.path, Region.Op.DIFFERENCE);
    }

    private void drawLayout(Canvas r11) {
        if (this.fullAlpha > 0.0f) goto L5;
    L11:
        r11.save();
        clipOutSpoilers(r11);
        AnimatedEmojiSpan.EmojiGroupedSpans r1 = this.emojiStack;
        if (r1 == null) goto L14;
        r1.clearPositions();
    L14:
        this.layout.draw(r11);
        r11.restore();
        AnimatedEmojiSpan.drawAnimatedEmojis(r11, this.layout, this.emojiStack, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, this.emojiStackColorFilter);
        drawSpoilers(r11);
        return;
    L5:
        if (this.fullLayoutLeftOffset == 0) goto L11;
        r11.save();
        float r12 = -this.fullLayoutLeftOffset;
        float r3 = this.fullAlpha;
        r11.translate((r12 * r3) + (this.fullLayoutLeftCharactersOffset * r3), 0.0f);
        float r13 = this.layoutX;
        float r2 = -this.fullLayoutLeftOffset;
        float r32 = this.fullAlpha;
        this.layoutX = r13 + ((r2 * r32) + (this.fullLayoutLeftCharactersOffset * r32));
        r11.save();
        clipOutSpoilers(r11);
        AnimatedEmojiSpan.EmojiGroupedSpans r14 = this.emojiStack;
        if (r14 == null) goto L9;
        r14.clearPositions();
    L9:
        this.layout.draw(r11);
        r11.restore();
        AnimatedEmojiSpan.drawAnimatedEmojis(r11, this.layout, this.emojiStack, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, this.emojiStackColorFilter);
        drawSpoilers(r11);
        r11.restore();
    }

    private void drawSpoilers(Canvas r2) {
        Iterator<SpoilerEffect> r1 = this.spoilers.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().draw(r2);
        goto L4
    }

    private Layout.Alignment getAlignment() {
        return this.mAlignment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean handleDrawableTouch(MotionEvent r8, Drawable r9, View.OnClickListener r10, boolean r11) {
        if (r11 == false) goto L4;
        boolean r0 = this.maybeClickDrawable1;
    L5:
        if (r11 == false) goto L7;
        float r1 = this.touchDownXDrawable1;
    L8:
        if (r11 == false) goto L10;
        float r2 = this.touchDownYDrawable1;
    L12:
        if (r8.getAction() != 0) goto L20;
        if (AndroidUtilities.rectTmp.contains((int) r8.getX(), (int) r8.getY()) == false) goto L20;
        r1 = r8.getX();
        r2 = r8.getY();
        getParent().requestDisallowInterceptTouchEvent(true);
        if ((r9 instanceof PressableDrawable) == false) goto L18;
        ((PressableDrawable) r9).setPressed(true);
    L18:
        AndroidUtilities.runOnUIThread(this.longPressRunnable, 500);
        r0 = true;
    L42:
        if (r11 == false) goto L45;
        this.maybeClickDrawable1 = r0;
        this.touchDownXDrawable1 = r1;
        this.touchDownYDrawable1 = r2;
        return r0;
    L45:
        this.maybeClickDrawable2 = r0;
        this.touchDownXDrawable2 = r1;
        this.touchDownYDrawable2 = r2;
        return r0;
    L20:
        if (r8.getAction() != 2) goto L31;
        if (r0 == false) goto L31;
        if (Math.abs(r8.getX() - r1) < AndroidUtilities.touchSlop) goto L25;
    L26:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        getParent().requestDisallowInterceptTouchEvent(false);
        if ((r9 instanceof PressableDrawable) == false) goto L29;
        ((PressableDrawable) r9).setPressed(false);
    L29:
        r0 = false;
        goto L42
    L25:
        if (Math.abs(r8.getY() - r2) < AndroidUtilities.touchSlop) goto L42;
    L31:
        if (r8.getAction() != 1) goto L33;
    L34:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        if (r0 == true) goto L37;
    L41:
        getParent().requestDisallowInterceptTouchEvent(false);
        goto L29
    L37:
        if (r8.getAction() != 1) goto L41;
        r10.onClick(this);
        if ((r9 instanceof PressableDrawable) == false) goto L41;
        ((PressableDrawable) r9).setPressed(false);
        goto L41
    L33:
        if (r8.getAction() != 3) goto L42;
    L10:
        r2 = this.touchDownYDrawable2;
        goto L12
    L7:
        r1 = this.touchDownXDrawable2;
        goto L8
    L4:
        r0 = this.maybeClickDrawable2;
        goto L5
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.maybeClickDrawable1 == false) goto L7;
        this.maybeClickDrawable1 = false;
        return;
    L7:
        if (this.maybeClickDrawable2 == false) goto L10;
        this.maybeClickDrawable2 = false;
        return;
    }

    private boolean recreateLayoutMaybe() {
        if (this.wasLayout == true) goto L5;
    L14:
        requestLayout();
        return true;
    L5:
        if (getMeasuredHeight() == 0) goto L14;
        if (this.buildFullLayout == true) goto L14;
        boolean r0 = createLayout(((getMaxTextWidth() - getPaddingLeft()) - getPaddingRight()) - this.minusWidth);
        if ((this.gravity & 112) != 16) goto L12;
        this.offsetY = (getMeasuredHeight() - this.textHeight) / 2;
        return r0;
    L12:
        this.offsetY = getPaddingTop();
        return r0;
    }

    private void updateFadePaints() {
        if (this.fadePaint == null) goto L7;
        if (this.fadePaintBack == null) goto L7;
    L9:
        Boolean r1 = this.forceEllipsizeByGradientLeft;
        if (r1 == null) goto L12;
        boolean r12 = r1.booleanValue();
    L14:
        if (this.fadeEllpsizePaint == null) goto L20;
        if (this.fadeEllpsizePaintWidth != AndroidUtilities.dp(this.ellipsizeByGradientWidthDp)) goto L20;
        if (this.ellipsizeByGradientLeft != r12) goto L20;
        return;
    L20:
        if (this.ellipsizeByGradient == true) goto L22;
        return;
    L22:
        if (this.fadeEllpsizePaint != null) goto L24;
        this.fadeEllpsizePaint = new Paint();
    L24:
        this.ellipsizeByGradientLeft = r12;
        Paint r5 = this.fadeEllpsizePaint;
        if (r12 == false) goto L27;
        int r13 = AndroidUtilities.dp(this.ellipsizeByGradientWidthDp);
        this.fadeEllpsizePaintWidth = r13;
        r5.setShader(new LinearGradient(0.0f, 0.0f, r13, 0.0f, new int[]{-1, 0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
    L28:
        this.fadeEllpsizePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        return;
    L27:
        int r14 = AndroidUtilities.dp(this.ellipsizeByGradientWidthDp);
        this.fadeEllpsizePaintWidth = r14;
        r5.setShader(new LinearGradient(0.0f, 0.0f, r14, 0.0f, new int[]{0, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        goto L28
    L12:
        r12 = false;
    L7:
        if (this.scrollNonFitText == false) goto L9;
        Paint r15 = new Paint();
        this.fadePaint = r15;
        Shader.TileMode r21 = Shader.TileMode.CLAMP;
        r15.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(6.0f), 0.0f, new int[]{-1, 0}, new float[]{0.0f, 1.0f}, r21));
        Paint r16 = this.fadePaint;
        PorterDuff.Mode r6 = PorterDuff.Mode.DST_OUT;
        r16.setXfermode(new PorterDuffXfermode(r6));
        Paint r17 = new Paint();
        this.fadePaintBack = r17;
        r17.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(6.0f), 0.0f, new int[]{0, -1}, new float[]{0.0f, 1.0f}, r21));
        this.fadePaintBack.setXfermode(new PorterDuffXfermode(r6));
        goto L9
    }

    private void updateScrollAnimation() {
        if (this.scrollNonFitText == true) goto L5;
        return;
    L5:
        if (this.textDoesNotFit == false) goto L7;
    L9:
        long r2 = SystemClock.elapsedRealtime();
        long r4 = r2 - this.lastUpdateTime;
        if (r4 <= 17) goto L12;
        r4 = 17;
    L12:
        int r0 = this.currentScrollDelay;
        if (r0 <= 0) goto L15;
        this.currentScrollDelay = (int) (r0 - r4);
    L25:
        invalidate();
        return;
    L15:
        int r02 = this.totalWidth + AndroidUtilities.dp(16.0f);
        float r6 = this.scrollingOffset;
        float r8 = AndroidUtilities.dp(100.0f);
        float r82 = this.scrollingOffset;
        if (r6 >= r8) goto L18;
        float r83 = ((r82 / AndroidUtilities.dp(100.0f)) * 20.0f) + 30.0f;
    L21:
        float r62 = this.scrollingOffset + ((r4 / 1000.0f) * AndroidUtilities.dp(r83));
        this.scrollingOffset = r62;
        this.lastUpdateTime = r2;
        if (r62 <= r02) goto L24;
        this.scrollingOffset = 0.0f;
        this.currentScrollDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
    L24:
        checkUi_layerType();
        goto L25
    L18:
        r83 = 50.0f;
        if (r82 < (r02 - AndroidUtilities.dp(100.0f))) goto L21;
        r83 = 50.0f - (((this.scrollingOffset - (r02 - AndroidUtilities.dp(100.0f))) / AndroidUtilities.dp(100.0f)) * 20.0f);
        goto L21
    L7:
        if (this.scrollingOffset != 0.0f) goto L9;
    }

    public void copyScrolling(SimpleTextView r1) {
        this.scrollingOffset = r1.scrollingOffset;
        checkUi_layerType();
    }

    public boolean createLayout(int r24) {
        CharSequence r2 = this.text;
        this.replacingDrawableTextIndex = -1;
        this.rightDrawableHidden = false;
        if (r2 != null) goto L117;
        this.layout = null;
        this.textWidth = 0;
        this.textHeight = 0;
    L111:
        AnimatedEmojiSpan.release(this, this.emojiStack);
        if (this.attachedToWindow == false) goto L114;
        this.emojiStack = AnimatedEmojiSpan.update(this.emojiCacheType, this, this.emojiStack, new Layout[]{this.layout});
    L114:
        invalidate();
        return true;
    L117:
        Drawable r5 = this.leftDrawable;     // Catch: Exception -> L116
        if (r5 != null) goto L7;
    L9:
        int r52 = r24;
    L11:
        if (this.rightDrawableInside == false) goto L13;
        int r7 = 0;
    L24:
        CharSequence r22 = r2;
        if (this.replacedText == null) goto L31;
        r22 = r2;
        if (this.replacedDrawable == null) goto L31;
        int r8 = r2.toString().indexOf(this.replacedText);     // Catch: Exception -> L116
        this.replacingDrawableTextIndex = r8;     // Catch: Exception -> L116
        if (r8 < 0) goto L32;
        SpannableStringBuilder r23 = SpannableStringBuilder.valueOf(r2);     // Catch: Exception -> L116
        DialogCell.FixedWidthSpan r82 = new DialogCell.FixedWidthSpan(this.replacedDrawable.getIntrinsicWidth());     // Catch: Exception -> L116
        int r9 = this.replacingDrawableTextIndex;     // Catch: Exception -> L116
        r23.setSpan(r82, r9, this.replacedText.length() + r9, 0);     // Catch: Exception -> L116
        r22 = r23;
        goto L31
    L32:
        r52 = (r52 - this.replacedDrawable.getIntrinsicWidth()) - this.drawablePadding;     // Catch: Exception -> L116
        r22 = r2;
    L31:
        CharSequence r83 = r22;
        if (this.canHideRightDrawable == false) goto L41;
        if (r7 == 0) goto L41;
        if (this.rightDrawableOutside == true) goto L41;
        if (r83.equals(TextUtils.ellipsize(r83, this.textPaint, r52, TextUtils.TruncateAt.END)) == true) goto L41;
        this.rightDrawableHidden = true;     // Catch: Exception -> L116
        r52 = (r52 + r7) + this.drawablePadding;     // Catch: Exception -> L116
    L41:
        int r10 = r52;
        if (this.buildFullLayout == false) goto L86;
        if (this.ellipsizeByGradient == true) goto L46;
        CharSequence r25 = TextUtils.ellipsize(r83, this.textPaint, r10, TextUtils.TruncateAt.END);     // Catch: Exception -> L116
    L48:
        if (this.ellipsizeByGradient == false) goto L50;
    L76:
        CharSequence r12 = r25;
        int r14 = r12.length();     // Catch: Exception -> L116
        TextPaint r15 = this.textPaint;     // Catch: Exception -> L116
        if (this.scrollNonFitText == false) goto L79;
    L83:
        int r1 = AndroidUtilities.dp(2000.0f);     // Catch: Exception -> L116
    L84:
        this.layout = new StaticLayout(r12, 0, r14, r15, r1, getAlignment(), 1.0f, 0.0f, false);     // Catch: Exception -> L116
        this.fullLayout = null;     // Catch: Exception -> L116
        this.partLayout = null;     // Catch: Exception -> L116
        this.firstLineLayout = null;     // Catch: Exception -> L116
    L103:
        this.spoilersPool.addAll(this.spoilers);     // Catch: Exception -> L116
        this.spoilers.clear();     // Catch: Exception -> L116
        Layout r13 = this.layout;     // Catch: Exception -> L116
        if (r13 != null) goto L106;
    L108:
        calcOffset(r10);     // Catch: Exception -> L116
        goto L111
    L106:
        if ((r13.getText() instanceof Spannable) == false) goto L108;
        SpoilerEffect.addSpoilers(this, this.layout, -2, -2, this.spoilersPool, this.spoilers);     // Catch: Exception -> L116
        goto L108
    L79:
        if (this.ellipsizeByGradient == true) goto L83;
        r1 = AndroidUtilities.dp(8.0f) + r10;     // Catch: Exception -> L116
        goto L84
    L50:
        if (r25.equals(r83) == true) goto L76;
        TextPaint r92 = this.textPaint;     // Catch: Exception -> L116
        Layout.Alignment r11 = getAlignment();     // Catch: Exception -> L116
        TextUtils.TruncateAt r152 = TextUtils.TruncateAt.END;     // Catch: Exception -> L116
        StaticLayout r4 = StaticLayoutEx.createStaticLayout(r83, r92, r10, r11, 1.0f, 0.0f, false, r152, r10, this.fullTextMaxLines, false);     // Catch: Exception -> L116
        this.fullLayout = r4;     // Catch: Exception -> L116
        if (r4 == null) goto L103;
        int r42 = r4.getLineEnd(0);     // Catch: Exception -> L116
        int r112 = this.fullLayout.getLineStart(1);     // Catch: Exception -> L116
        CharSequence r21 = r83.subSequence(0, r42);     // Catch: Exception -> L116
        SpannableStringBuilder r84 = SpannableStringBuilder.valueOf(r83);     // Catch: Exception -> L116
        r84.setSpan(new EmptyStubSpan(), 0, r112, 0);     // Catch: Exception -> L116
        if (r42 >= r25.length()) goto L56;
        CharSequence r43 = r25.subSequence(r42, r25.length());     // Catch: Exception -> L116
    L57:
        int r142 = r25.length();     // Catch: Exception -> L116
        TextPaint r153 = this.textPaint;     // Catch: Exception -> L116
        if (this.scrollNonFitText == false) goto L61;
        int r122 = AndroidUtilities.dp(2000.0f);     // Catch: Exception -> L116
    L60:
        int r16 = r122;
        this.firstLineLayout = new StaticLayout(r25, 0, r142, r153, r16, getAlignment(), 1.0f, 0.0f, false);     // Catch: Exception -> L116
        int r154 = r21.length();     // Catch: Exception -> L116
        TextPaint r26 = this.textPaint;     // Catch: Exception -> L116
        if (this.scrollNonFitText == false) goto L66;
        int r113 = AndroidUtilities.dp(2000.0f);     // Catch: Exception -> L116
    L67:
        StaticLayout r123 = new StaticLayout(r21, 0, r154, r26, r113, getAlignment(), 1.0f, 0.0f, false);     // Catch: Exception -> L116
        this.layout = r123;     // Catch: Exception -> L116
        if (r123.getLineLeft(0) == 0.0f) goto L70;
        r43 = "\u200f" + r43;     // Catch: Exception -> L116
    L70:
        CharSequence r124 = r43;
        int r143 = r124.length();     // Catch: Exception -> L116
        TextPaint r155 = this.textPaint;     // Catch: Exception -> L116
        if (this.scrollNonFitText == false) goto L74;
        int r17 = AndroidUtilities.dp(2000.0f);     // Catch: Exception -> L116
    L75:
        this.partLayout = new StaticLayout(r124, 0, r143, r155, r17, getAlignment(), 1.0f, 0.0f, false);     // Catch: Exception -> L116
        this.fullLayout = StaticLayoutEx.createStaticLayout(r84, this.textPaint, (AndroidUtilities.dp(8.0f) + r10) + this.fullLayoutAdditionalWidth, getAlignment(), 1.0f, 0.0f, false, r152, r10 + this.fullLayoutAdditionalWidth, this.fullTextMaxLines, false);     // Catch: Exception -> L116
        goto L103
    L74:
        r17 = AndroidUtilities.dp(8.0f) + r10;     // Catch: Exception -> L116
        goto L75
    L66:
        r113 = AndroidUtilities.dp(8.0f) + r10;     // Catch: Exception -> L116
        goto L67
    L61:
        r122 = AndroidUtilities.dp(8.0f) + r10;     // Catch: Exception -> L116
        goto L60
    L56:
        r43 = "…";
        goto L57
    L46:
        r25 = r83;
        goto L48
    L86:
        if (this.maxLines <= 1) goto L89;
        this.layout = StaticLayoutEx.createStaticLayout(r83, this.textPaint, r10, getAlignment(), 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r10, this.maxLines, false);     // Catch: Exception -> L116
        goto L103
    L89:
        if (this.scrollNonFitText == false) goto L91;
    L94:
        CharSequence r125 = r83;
        int r144 = r125.length();     // Catch: Exception -> L116
        TextPaint r156 = this.textPaint;     // Catch: Exception -> L116
        if (this.scrollNonFitText == false) goto L97;
    L101:
        int r18 = AndroidUtilities.dp(2000.0f);     // Catch: Exception -> L116
    L102:
        this.layout = new StaticLayout(r125, 0, r144, r156, r18, getAlignment(), 1.0f, 0.0f, false);     // Catch: Exception -> L116
        goto L103
    L97:
        if (this.ellipsizeByGradient == true) goto L101;
        r18 = AndroidUtilities.dp(8.0f) + r10;     // Catch: Exception -> L116
        goto L102
    L91:
        if (this.ellipsizeByGradient == true) goto L94;
        r83 = TextUtils.ellipsize(r83, this.textPaint, r10, TextUtils.TruncateAt.END);     // Catch: Exception -> L116
        goto L94
    L13:
        if (this.rightDrawable != null) goto L15;
    L17:
        r7 = 0;
    L19:
        if (this.rightDrawable2 == null) goto L24;
        if (this.rightDrawableOutside == true) goto L24;
        r7 = r7 + ((int) (r8.getIntrinsicWidth() * this.rightDrawableScale));     // Catch: Exception -> L116
        r52 = (r52 - r7) - this.drawablePadding;     // Catch: Exception -> L116
        goto L24
    L15:
        if (this.rightDrawableOutside == true) goto L17;
        r7 = (int) (r7.getIntrinsicWidth() * this.rightDrawableScale);     // Catch: Exception -> L116
        r52 = (r52 - r7) - this.drawablePadding;     // Catch: Exception -> L116
        goto L19
    L7:
        if (this.leftDrawableOutside == true) goto L9;
        r5.getIntrinsicWidth();     // Catch: Exception -> L116
        r52 = (r24 - this.leftDrawable.getIntrinsicWidth()) - this.drawablePadding;     // Catch: Exception -> L116
        goto L11
    }

    @Override // android.view.View
    public Drawable getBackground() {
        Drawable r0 = this.wrapBackgroundDrawable;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return super.getBackground();
    }

    public float getExactWidth() {
        float r0 = getPaint().measureText(getText().toString()) + getSideDrawablesSize();
        if (this.leftDrawable == null) goto L5;
    L10:
        int r2 = this.drawablePadding;
    L12:
        return r0 - r2;
    L5:
        if (this.rightDrawable != null) goto L10;
        if (this.rightDrawable2 != null) goto L10;
        r2 = 0;
        goto L12
    }

    public float getExactWidthIncludeDrawables() {
        float r0 = getExactWidth();
        Drawable r1 = this.leftDrawable;
        int r2 = 0;
        if (r1 == null) goto L5;
        int r12 = r1.getIntrinsicWidth();
    L6:
        float r02 = r0 + r12;
        Drawable r13 = this.rightDrawable;
        if (r13 == null) goto L9;
        int r14 = r13.getIntrinsicWidth();
    L10:
        float r03 = r02 + r14;
        Drawable r3 = this.rightDrawable2;
        if (r3 == null) goto L14;
        r2 = r3.getIntrinsicWidth();
    L14:
        return r03 + r2;
    L9:
        r14 = 0;
        goto L10
    L5:
        r12 = 0;
        goto L6
    }

    public float getFullAlpha() {
        return this.fullAlpha;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public float getLayoutX() {
        return this.layoutX;
    }

    public float getLayoutY() {
        return this.layoutY;
    }

    public Drawable getLeftDrawable() {
        return this.leftDrawable;
    }

    public int getLineCount() {
        Layout r0 = this.layout;
        if (r0 == null) goto L5;
        int r02 = r0.getLineCount();
    L6:
        Layout r1 = this.fullLayout;
        if (r1 != null) goto L9;
        return r02;
    L9:
        return r02 + r1.getLineCount();
    L5:
        r02 = 0;
        goto L6
    }

    public int getMaxTextWidth() {
        int r0 = getMeasuredWidth();
        int r2 = 0;
        if (this.rightDrawableOutside == false) goto L7;
        Drawable r1 = this.rightDrawable;
        if (r1 == null) goto L7;
        int r12 = r1.getIntrinsicWidth() + this.drawablePadding;
    L8:
        int r02 = r0 - r12;
        if (this.rightDrawableOutside == false) goto L14;
        Drawable r13 = this.rightDrawable2;
        if (r13 == null) goto L14;
        r2 = r13.getIntrinsicWidth() + this.drawablePadding;
    L14:
        return r02 - r2;
    L7:
        r12 = 0;
        goto L8
    }

    public TextPaint getPaint() {
        return this.textPaint;
    }

    public Drawable getRightDrawable() {
        return this.rightDrawable;
    }

    public Drawable getRightDrawable2() {
        return this.rightDrawable2;
    }

    public boolean getRightDrawableOutside() {
        return this.rightDrawableOutside;
    }

    public int getRightDrawableWidth() {
        if (this.rightDrawable != null) goto L7;
        return 0;
    L7:
        return (int) (this.drawablePadding + (r0.getIntrinsicWidth() * this.rightDrawableScale));
    }

    public int getRightDrawableX() {
        return this.rightDrawableX;
    }

    public int getRightDrawableY() {
        return this.rightDrawableY;
    }

    public int getSideDrawablesSize() {
        Drawable r0 = this.leftDrawable;
        if (r0 == null) goto L5;
        int r02 = r0.getIntrinsicWidth() + this.drawablePadding;
    L7:
        if (this.rightDrawable == null) goto L10;
        r02 = r02 + (((int) (r1.getIntrinsicWidth() * this.rightDrawableScale)) + this.drawablePadding);
    L10:
        if (this.rightDrawable2 != null) goto L12;
        return r02;
    L12:
        return r02 + (((int) (r1.getIntrinsicWidth() * this.rightDrawableScale)) + this.drawablePadding);
    L5:
        r02 = 0;
        goto L7
    }

    public CharSequence getText() {
        CharSequence r0 = this.text;
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r0;
    }

    public int getTextColor() {
        return this.textPaint.getColor();
    }

    public int getTextHeight() {
        return this.textHeight;
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    public int getTextStartX() {
        int r1 = 0;
        if (this.layout != null) goto L5;
        return 0;
    L5:
        Drawable r0 = this.leftDrawable;
        if (r0 != null) goto L8;
    L10:
        Drawable r02 = this.replacedDrawable;
        if (r02 == null) goto L18;
        if (this.replacingDrawableTextIndex >= 0) goto L18;
        if ((this.gravity & 7) != 3) goto L18;
        r1 = r1 + (this.drawablePadding + r02.getIntrinsicWidth());
    L18:
        return (getLeft() + this.offsetX) + r1;
    L8:
        if ((this.gravity & 7) != 3) goto L10;
        r1 = this.drawablePadding + r0.getIntrinsicWidth();
        goto L10
    }

    public int getTextStartY() {
        if (this.layout != null) goto L7;
        return 0;
    L7:
        return getTop() + this.offsetY;
    }

    public int getTextWidth() {
        int r0 = this.textWidth;
        int r2 = 0;
        if (this.rightDrawableInside == false) goto L13;
        if (this.rightDrawable == null) goto L7;
        int r1 = (int) (r1.getIntrinsicWidth() * this.rightDrawableScale);
    L9:
        if (this.rightDrawable2 == null) goto L11;
        r2 = (int) (r3.getIntrinsicWidth() * this.rightDrawableScale);
    L11:
        r2 = r2 + r1;
        goto L13
    L7:
        r1 = 0;
    L13:
        return r0 + r2;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r2) {
        Drawable r0 = this.leftDrawable;
        if (r2 != r0) goto L6;
        invalidate(r0.getBounds());
        return;
    L6:
        Drawable r02 = this.rightDrawable;
        if (r2 != r02) goto L10;
        invalidate(r02.getBounds());
        return;
    L10:
        Drawable r03 = this.rightDrawable2;
        if (r2 != r03) goto L14;
        invalidate(r03.getBounds());
        return;
    L14:
        Drawable r04 = this.replacedDrawable;
        if (r2 != r04) goto L18;
        invalidate(r04.getBounds());
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        this.emojiStack = AnimatedEmojiSpan.update(this.emojiCacheType, this, this.emojiStack, new Layout[]{this.layout});
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        AnimatedEmojiSpan.release(this, this.emojiStack);
        this.wasLayout = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r20) {
        super.onDraw(r20);
        this.layoutX = 0.0f;
        this.layoutY = 0.0f;
        if (this.scrollNonFitText == true) goto L5;
    L9:
        boolean r2 = false;
    L10:
        this.totalWidth = this.textWidth;
        Drawable r3 = this.leftDrawable;
        if (r3 == null) goto L31;
        if (this.leftDrawableOutside == true) goto L31;
        int r32 = (int) (-this.scrollingOffset);
        int r5 = this.gravity;
        if ((r5 & 7) != 1) goto L18;
        r32 = r32 + this.offsetX;
    L18:
        if ((r5 & 112) != 16) goto L21;
        int r52 = (getMeasuredHeight() - this.leftDrawable.getIntrinsicHeight()) / 2;
        int r6 = this.leftDrawableTopPadding;
    L20:
        int r53 = r52 + r6;
        Drawable r62 = this.leftDrawable;
        r62.setBounds(r32, r53, r62.getIntrinsicWidth() + r32, this.leftDrawable.getIntrinsicHeight() + r53);
        this.leftDrawable.draw(r20);
        int r33 = this.gravity;
        if ((r33 & 7) != 3) goto L25;
    L28:
        int r34 = this.drawablePadding + this.leftDrawable.getIntrinsicWidth();
    L29:
        this.totalWidth += this.drawablePadding + this.leftDrawable.getIntrinsicWidth();
    L35:
        Drawable r54 = this.replacedDrawable;
        if (r54 != null) goto L38;
    L54:
        int r11 = r34;
        if (this.rightDrawable == null) goto L76;
        if (this.rightDrawableHidden == true) goto L76;
        if (this.rightDrawableScale <= 0.0f) goto L76;
        if (this.rightDrawableOutside == true) goto L76;
        if (this.rightDrawableInside == true) goto L76;
        int r55 = ((this.textWidth + r11) + this.drawablePadding) + ((int) (-this.scrollingOffset));
        int r63 = this.gravity;
        if ((r63 & 7) != 1) goto L67;
    L68:
        r55 = r55 + this.offsetX;
    L69:
        int r35 = (int) (r3.getIntrinsicWidth() * this.rightDrawableScale);
        int r64 = (int) (this.rightDrawable.getIntrinsicHeight() * this.rightDrawableScale);
        if ((this.gravity & 112) != 16) goto L73;
        int r12 = (getMeasuredHeight() - r64) / 2;
        int r13 = this.rightDrawableTopPadding;
    L72:
        int r122 = r12 + r13;
        this.rightDrawable.setBounds(r55, r122, r55 + r35, r122 + r64);
        this.rightDrawableX = r55 + (r35 >> 1);
        this.rightDrawableY = r122 + (r64 >> 1);
        this.rightDrawable.draw(r20);
        this.totalWidth += this.drawablePadding + r35;
        goto L76
    L73:
        r12 = getPaddingTop() + ((this.textHeight - r64) / 2);
        r13 = this.rightDrawableTopPadding;
        goto L72
    L67:
        if ((r63 & 7) != 5) goto L69;
    L76:
        if (this.rightDrawable2 != null) goto L78;
    L99:
        int r123 = this.totalWidth + AndroidUtilities.dp(16.0f);
        float r36 = this.scrollingOffset;
        if (r36 != 0.0f) goto L102;
        float r16 = 16.0f;
    L137:
        if (this.layout != null) goto L139;
    L278:
        Drawable r22 = this.leftDrawable;
        if (r22 == null) goto L289;
        if (this.leftDrawableOutside == false) goto L289;
        int r23 = r22.getIntrinsicWidth();
        int r37 = this.leftDrawable.getIntrinsicHeight();
        if ((this.gravity & 112) != 16) goto L286;
        int r4 = (getMeasuredHeight() - r37) / 2;
        int r56 = this.leftDrawableTopPadding;
    L285:
        int r42 = r4 + r56;
        this.leftDrawable.setBounds(0, r42, r23, r37 + r42);
        this.leftDrawable.draw(r20);
        goto L289
    L286:
        r4 = getPaddingTop() + ((this.textHeight - r37) / 2);
        r56 = this.leftDrawableTopPadding;
    L289:
        if (this.rightDrawable == null) goto L303;
        if (this.rightDrawableOutside == false) goto L303;
        int r24 = (this.textWidth + r11) + this.drawablePadding;
        float r38 = this.scrollingOffset;
        if (r38 != 0.0f) goto L295;
        int r39 = -r123;
    L296:
        int r25 = Math.min((r24 + r39) + r123, (getMaxTextWidth() - this.paddingRight) + this.drawablePadding);
        int r310 = (int) (this.rightDrawable.getIntrinsicWidth() * this.rightDrawableScale);
        int r43 = (int) (this.rightDrawable.getIntrinsicHeight() * this.rightDrawableScale);
        if ((this.gravity & 112) != 16) goto L300;
        int r57 = (getMeasuredHeight() - r43) / 2;
        int r65 = this.rightDrawableTopPadding;
    L299:
        int r58 = r57 + r65;
        this.rightDrawable.setBounds(r25, r58, r25 + r310, r58 + r43);
        this.rightDrawableX = r25 + (r310 >> 1);
        this.rightDrawableY = r58 + (r43 >> 1);
        this.rightDrawable.draw(r20);
        goto L303
    L300:
        r57 = getPaddingTop() + ((this.textHeight - r43) / 2);
        r65 = this.rightDrawableTopPadding;
        goto L299
    L295:
        r39 = (int) (-r38);
    L303:
        if (this.rightDrawable2 != null) goto L305;
        return;
    L305:
        if (this.rightDrawableOutside == false) goto L321;
        int r112 = (r11 + this.textWidth) + this.drawablePadding;
        float r26 = this.scrollingOffset;
        if (r26 != 0.0f) goto L309;
        int r27 = -r123;
    L310:
        int r28 = Math.min((r112 + r27) + r123, (getMaxTextWidth() - this.paddingRight) + this.drawablePadding);
        if (this.rightDrawable == null) goto L313;
        r28 = r28 + (((int) (r3.getIntrinsicWidth() * this.rightDrawableScale)) + this.drawablePadding);
    L313:
        int r311 = (int) (this.rightDrawable2.getIntrinsicWidth() * this.rightDrawableScale);
        int r44 = (int) (this.rightDrawable2.getIntrinsicHeight() * this.rightDrawableScale);
        if ((this.gravity & 112) != 16) goto L317;
        int r59 = (getMeasuredHeight() - r44) / 2;
        int r66 = this.rightDrawableTopPadding;
    L316:
        int r510 = r59 + r66;
        this.rightDrawable2.setBounds(r28, r510, r28 + r311, r510 + r44);
        this.rightDrawable2X = r28 + (r311 >> 1);
        this.rightDrawable2Y = r510 + (r44 >> 1);
        this.rightDrawable2.draw(r20);
        return;
    L317:
        r59 = getPaddingTop() + ((this.textHeight - r44) / 2);
        r66 = this.rightDrawableTopPadding;
        goto L316
    L309:
        r27 = (int) (-r26);
        goto L310
    L321:
        return;
    L139:
        if (this.leftDrawableOutside == false) goto L141;
    L146:
        r20.save();
        int r312 = getMaxTextWidth() - this.paddingRight;
        Drawable r511 = this.rightDrawable;
        if (r511 != null) goto L149;
    L153:
        float r512 = 0.0f;
    L154:
        r20.clipRect(r11, 0, r312 - AndroidUtilities.dp(r512), getMeasuredHeight());
    L155:
        Emoji.emojiDrawingUseAlpha = this.usaAlphaForEmoji;
        if (this.wrapBackgroundDrawable == null) goto L159;
        int r313 = (int) ((this.offsetX + r11) - this.scrollingOffset);
        int r513 = this.textWidth;
        int r314 = r313 + (r513 / 2);
        int r514 = Math.max((r513 + getPaddingLeft()) + getPaddingRight(), this.minWidth);
        int r315 = r314 - (r514 / 2);
        this.wrapBackgroundDrawable.setBounds(r315, 0, r514 + r315, getMeasuredHeight());
        this.wrapBackgroundDrawable.draw(r20);
    L159:
        if ((this.offsetX + r11) == 0) goto L161;
    L164:
        r20.save();
        r20.translate((this.offsetX + r11) - this.scrollingOffset, this.offsetY);
        this.layoutX += (this.offsetX + r11) - this.scrollingOffset;
        this.layoutY += this.offsetY;
    L165:
        drawLayout(r20);
        if (this.partLayout == null) goto L183;
        if (this.fullAlpha >= 1.0f) goto L183;
        int r316 = this.textPaint.getAlpha();
        this.textPaint.setAlpha((int) ((1.0f - this.fullAlpha) * 255.0f));
        r20.save();
        if (this.partLayout.getText().length() == 1) goto L172;
        float r132 = 0.0f;
    L177:
        float r14 = this.layout.getLineLeft(0);
        Layout r15 = this.layout;
        if (r14 == 0.0f) goto L180;
        r20.translate((-r15.getLineWidth(0)) + r132, 0.0f);
    L181:
        float r133 = -this.fullLayoutLeftOffset;
        float r142 = this.fullAlpha;
        r20.translate((r133 * r142) + (this.fullLayoutLeftCharactersOffset * r142), 0.0f);
        this.partLayout.draw(r20);
        r20.restore();
        this.textPaint.setAlpha(r316);
        goto L183
    L180:
        r20.translate(r15.getLineWidth(0) - r132, 0.0f);
        goto L181
    L172:
        if (this.fullTextMaxLines != 1) goto L175;
        float r134 = 0.5f;
    L174:
        r132 = AndroidUtilities.dp(r134);
        goto L177
    L175:
        r134 = 4.0f;
    L183:
        if (this.fullLayout == null) goto L188;
        if (this.fullAlpha <= 0.0f) goto L188;
        int r317 = this.textPaint.getAlpha();
        this.textPaint.setAlpha((int) (this.fullAlpha * 255.0f));
        float r135 = -this.fullLayoutLeftOffset;
        float r143 = this.fullAlpha;
        float r152 = this.fullLayoutLeftCharactersOffset;
        r20.translate(((r135 * r143) + (r143 * r152)) - r152, 0.0f);
        this.fullLayout.draw(r20);
        this.textPaint.setAlpha(r317);
    L188:
        if (this.scrollingOffset == 0.0f) goto L191;
        r20.translate(r123, 0.0f);
        drawLayout(r20);
    L191:
        if ((this.offsetX + r11) == 0) goto L193;
    L196:
        r20.restore();
    L198:
        if (this.rightDrawable != null) goto L200;
    L217:
        float r17 = 1.0f;
        float r18 = 255.0f;
    L219:
        if (this.rightDrawable2 != null) goto L221;
    L242:
        if (r2 == false) goto L252;
        if (this.scrollingOffset >= AndroidUtilities.dp(10.0f)) goto L247;
        this.fadePaint.setAlpha((int) ((this.scrollingOffset / AndroidUtilities.dp(10.0f)) * r18));
    L250:
        r20.drawRect(r11, 0.0f, AndroidUtilities.dp(6.0f) + r11, getMeasuredHeight(), this.fadePaint);
        r20.save();
        r20.translate((getMaxTextWidth() - this.paddingRight) - AndroidUtilities.dp(6.0f), 0.0f);
        r20.drawRect(0.0f, 0.0f, AndroidUtilities.dp(6.0f), getMeasuredHeight(), this.fadePaintBack);
        r20.restore();
    L269:
        updateScrollAnimation();
        Emoji.emojiDrawingUseAlpha = true;
        if (this.leftDrawableOutside == false) goto L272;
    L277:
        r20.restore();
        goto L278
    L272:
        if (this.rightDrawableOutside == true) goto L277;
        if (this.ellipsizeByGradient == true) goto L277;
        if (this.paddingRight <= 0) goto L278;
    L247:
        if (this.scrollingOffset <= ((this.totalWidth + AndroidUtilities.dp(r16)) - AndroidUtilities.dp(10.0f))) goto L249;
        this.fadePaint.setAlpha((int) ((r17 - ((this.scrollingOffset - ((this.totalWidth + AndroidUtilities.dp(r16)) - AndroidUtilities.dp(10.0f))) / AndroidUtilities.dp(10.0f))) * r18));
        goto L250
    L249:
        this.fadePaint.setAlpha(255);
        goto L250
    L252:
        if (this.ellipsizeByGradient == false) goto L269;
        if (this.textDoesNotFit == false) goto L269;
        if (this.fadeEllpsizePaint == null) goto L269;
        r20.save();
        updateFadePaints();
        if (this.ellipsizeByGradientLeft == true) goto L268;
        int r29 = (getMaxTextWidth() - this.paddingRight) - this.fadeEllpsizePaintWidth;
        Drawable r318 = this.rightDrawable;
        if (r318 != null) goto L262;
    L266:
        float r45 = 0.0f;
    L267:
        r20.translate(r29 - AndroidUtilities.dp(r45), 0.0f);
        goto L268
    L262:
        if ((r318 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == true) goto L266;
        if (this.rightDrawableOutside == false) goto L266;
        r45 = 2.0f;
    L268:
        r20.drawRect(r11, 0.0f, this.fadeEllpsizePaintWidth, getMeasuredHeight(), this.fadeEllpsizePaint);
        r20.restore();
        goto L269
    L221:
        if (this.rightDrawableHidden == true) goto L242;
        if (this.rightDrawableScale <= 0.0f) goto L242;
        if (this.rightDrawableOutside == true) goto L242;
        if (this.rightDrawableInside == false) goto L242;
        int r319 = ((this.textWidth + r11) + this.drawablePadding) + ((int) (-this.scrollingOffset));
        if (this.rightDrawable == null) goto L231;
        r319 = r319 + (((int) (r4.getIntrinsicWidth() * this.rightDrawableScale)) + this.drawablePadding);
    L231:
        int r46 = this.gravity;
        if ((r46 & 7) != 1) goto L234;
    L235:
        r319 = r319 + this.offsetX;
    L236:
        int r47 = (int) (this.rightDrawable2.getIntrinsicWidth() * this.rightDrawableScale);
        int r515 = (int) (this.rightDrawable2.getIntrinsicHeight() * this.rightDrawableScale);
        if ((this.gravity & 112) != 16) goto L240;
        int r67 = (getMeasuredHeight() - r515) / 2;
        int r136 = this.rightDrawableTopPadding;
    L239:
        int r68 = r67 + r136;
        this.rightDrawable2.setBounds(r319, r68, r319 + r47, r68 + r515);
        this.rightDrawable2X = r319 + (r47 >> 1);
        this.rightDrawable2Y = r68 + (r515 >> 1);
        this.rightDrawable2.draw(r20);
        this.totalWidth += this.drawablePadding + r47;
        goto L242
    L240:
        r67 = getPaddingTop() + ((this.textHeight - r515) / 2);
        r136 = this.rightDrawableTopPadding;
        goto L239
    L234:
        if ((r46 & 7) != 5) goto L236;
    L200:
        if (this.rightDrawableHidden == true) goto L217;
        if (this.rightDrawableScale <= 0.0f) goto L217;
        if (this.rightDrawableOutside == true) goto L217;
        if (this.rightDrawableInside == false) goto L217;
        int r137 = ((this.textWidth + r11) + this.drawablePadding) + ((int) (-this.scrollingOffset));
        int r144 = this.gravity;
        if ((r144 & 7) != 1) goto L210;
    L211:
        r137 = r137 + this.offsetX;
    L212:
        int r320 = (int) (r3.getIntrinsicWidth() * this.rightDrawableScale);
        int r145 = (int) (this.rightDrawable.getIntrinsicHeight() * this.rightDrawableScale);
        if ((this.gravity & 112) != 16) goto L215;
        int r153 = ((getMeasuredHeight() - r145) / 2) + this.rightDrawableTopPadding;
    L216:
        r17 = 1.0f;
        r18 = 255.0f;
        this.rightDrawable.setBounds(r137, r153, r137 + r320, r153 + r145);
        this.rightDrawableX = r137 + (r320 >> 1);
        this.rightDrawableY = r153 + (r145 >> 1);
        this.rightDrawable.draw(r20);
        this.totalWidth += this.drawablePadding + r320;
        goto L219
    L215:
        r153 = this.rightDrawableTopPadding + (getPaddingTop() + ((this.textHeight - r145) / 2));
        goto L216
    L210:
        if ((r144 & 7) != 5) goto L212;
    L193:
        if (this.offsetY != 0) goto L196;
        if (this.scrollingOffset == 0.0f) goto L198;
    L161:
        if (this.offsetY != 0) goto L164;
        if (this.scrollingOffset == 0.0f) goto L165;
    L149:
        if ((r511 instanceof AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable) == true) goto L153;
        if (this.rightDrawableOutside == false) goto L153;
        r512 = 2.0f;
        goto L154
    L141:
        if (this.rightDrawableOutside == true) goto L146;
        if (this.ellipsizeByGradient == true) goto L146;
        if (this.paddingRight <= 0) goto L155;
    L102:
        if (this.leftDrawable == null) goto L112;
        if (this.leftDrawableOutside == true) goto L112;
        int r321 = ((int) (-r36)) + r123;
        if ((this.gravity & 112) != 16) goto L109;
        int r69 = (getMeasuredHeight() - this.leftDrawable.getIntrinsicHeight()) / 2;
        int r138 = this.leftDrawableTopPadding;
    L108:
        int r610 = r69 + r138;
        Drawable r139 = this.leftDrawable;
        r139.setBounds(r321, r610, r139.getIntrinsicWidth() + r321, this.leftDrawable.getIntrinsicHeight() + r610);
        this.leftDrawable.draw(r20);
        goto L112
    L109:
        r69 = getPaddingTop() + ((this.textHeight - this.leftDrawable.getIntrinsicHeight()) / 2);
        r138 = this.leftDrawableTopPadding;
    L112:
        if (this.rightDrawable != null) goto L114;
    L121:
        r16 = 16.0f;
    L123:
        if (this.rightDrawable2 == null) goto L137;
        if (this.rightDrawableOutside == true) goto L137;
        int r322 = (int) (r3.getIntrinsicWidth() * this.rightDrawableScale);
        int r48 = (int) (this.rightDrawable2.getIntrinsicHeight() * this.rightDrawableScale);
        int r516 = (((this.textWidth + r11) + this.drawablePadding) + ((int) (-this.scrollingOffset))) + r123;
        if (this.rightDrawable == null) goto L130;
        r516 = r516 + (((int) (r6.getIntrinsicWidth() * this.rightDrawableScale)) + this.drawablePadding);
    L130:
        if ((this.gravity & 112) != 16) goto L133;
        int r611 = (getMeasuredHeight() - r48) / 2;
        int r1310 = this.rightDrawableTopPadding;
    L132:
        int r612 = r611 + r1310;
        this.rightDrawable2.setBounds(r516, r612, r322 + r516, r48 + r612);
        this.rightDrawable2.draw(r20);
        goto L137
    L133:
        r611 = getPaddingTop() + ((this.textHeight - r48) / 2);
        r1310 = this.rightDrawableTopPadding;
        goto L132
    L114:
        if (this.rightDrawableOutside == true) goto L121;
        int r323 = (int) (r3.getIntrinsicWidth() * this.rightDrawableScale);
        int r613 = (int) (this.rightDrawable.getIntrinsicHeight() * this.rightDrawableScale);
        int r1311 = (((this.textWidth + r11) + this.drawablePadding) + ((int) (-this.scrollingOffset))) + r123;
        if ((this.gravity & 112) != 16) goto L119;
        int r146 = (getMeasuredHeight() - r613) / 2;
        int r154 = this.rightDrawableTopPadding;
    L118:
        int r147 = r146 + r154;
        r16 = 16.0f;
        this.rightDrawable.setBounds(r1311, r147, r1311 + r323, r147 + r613);
        this.rightDrawableX = r1311 + (r323 >> 1);
        this.rightDrawableY = r147 + (r613 >> 1);
        this.rightDrawable.draw(r20);
        goto L123
    L119:
        r146 = getPaddingTop() + ((this.textHeight - r613) / 2);
        r154 = this.rightDrawableTopPadding;
        goto L118
    L78:
        if (this.rightDrawableHidden == true) goto L99;
        if (this.rightDrawableScale <= 0.0f) goto L99;
        if (this.rightDrawableOutside == true) goto L99;
        if (this.rightDrawableInside == true) goto L99;
        int r324 = ((this.textWidth + r11) + this.drawablePadding) + ((int) (-this.scrollingOffset));
        if (this.rightDrawable == null) goto L88;
        r324 = r324 + (((int) (r5.getIntrinsicWidth() * this.rightDrawableScale)) + this.drawablePadding);
    L88:
        int r517 = this.gravity;
        if ((r517 & 7) != 1) goto L91;
    L92:
        r324 = r324 + this.offsetX;
    L93:
        int r518 = (int) (this.rightDrawable2.getIntrinsicWidth() * this.rightDrawableScale);
        int r614 = (int) (this.rightDrawable2.getIntrinsicHeight() * this.rightDrawableScale);
        if ((this.gravity & 112) != 16) goto L97;
        int r124 = (getMeasuredHeight() - r614) / 2;
        int r1312 = this.rightDrawableTopPadding;
    L96:
        int r125 = r124 + r1312;
        this.rightDrawable2.setBounds(r324, r125, r324 + r518, r125 + r614);
        this.rightDrawable2X = r324 + (r518 >> 1);
        this.rightDrawable2Y = r125 + (r614 >> 1);
        this.rightDrawable2.draw(r20);
        this.totalWidth += this.drawablePadding + r518;
        goto L99
    L97:
        r124 = getPaddingTop() + ((this.textHeight - r614) / 2);
        r1312 = this.rightDrawableTopPadding;
        goto L96
    L91:
        if ((r517 & 7) != 5) goto L93;
    L38:
        if (this.replacedText == null) goto L54;
        int r615 = (int) ((-this.scrollingOffset) + this.replacingDrawableTextOffset);
        int r113 = this.gravity;
        if ((r113 & 7) != 1) goto L43;
        r615 = r615 + this.offsetX;
    L43:
        if ((r113 & 112) != 16) goto L45;
        int r519 = ((getMeasuredHeight() - this.replacedDrawable.getIntrinsicHeight()) / 2) + this.leftDrawableTopPadding;
    L46:
        Drawable r114 = this.replacedDrawable;
        r114.setBounds(r615, r519, r114.getIntrinsicWidth() + r615, this.replacedDrawable.getIntrinsicHeight() + r519);
        this.replacedDrawable.draw(r20);
        if (this.replacingDrawableTextIndex >= 0) goto L54;
        int r520 = this.gravity;
        if ((r520 & 7) != 3) goto L51;
    L52:
        r34 = r34 + (this.drawablePadding + this.replacedDrawable.getIntrinsicWidth());
    L53:
        this.totalWidth += this.drawablePadding + this.replacedDrawable.getIntrinsicWidth();
        goto L54
    L51:
        if ((r520 & 7) != 1) goto L53;
    L45:
        r519 = this.leftDrawableTopPadding + ((this.textHeight - r54.getIntrinsicHeight()) / 2);
        goto L46
    L25:
        if ((r33 & 7) == 1) goto L28;
        r34 = 0;
        goto L29
    L21:
        r52 = getPaddingTop() + ((this.textHeight - this.leftDrawable.getIntrinsicHeight()) / 2);
        r6 = this.leftDrawableTopPadding;
    L31:
        if (this.leftDrawableOutside == false) goto L34;
        if (r3 == null) goto L34;
        r34 = r3.getIntrinsicWidth() + this.drawablePadding;
    L34:
        r34 = 0;
        goto L35
    L5:
        if (this.textDoesNotFit == false) goto L7;
    L8:
        r2 = true;
        goto L10
    L7:
        if (this.scrollingOffset == 0.0f) goto L9;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setVisibleToUser(true);
        r2.setClassName("android.widget.TextView");
        r2.setText(this.text);
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        this.wasLayout = true;
    }

    @Override // android.view.View
    public void onMeasure(int r6, int r7) {
        int r62 = View.MeasureSpec.getSize(r6);
        int r0 = View.MeasureSpec.getSize(r7);
        int r1 = this.lastWidth;
        int r2 = AndroidUtilities.displaySize.x;
        if (r1 == r2) goto L5;
        this.lastWidth = r2;
        this.scrollingOffset = 0.0f;
        this.currentScrollDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        checkUi_layerType();
    L5:
        int r12 = ((r62 - getPaddingLeft()) - getPaddingRight()) - this.minusWidth;
        if (this.leftDrawableOutside == false) goto L10;
        Drawable r22 = this.leftDrawable;
        if (r22 == null) goto L10;
        int r23 = r22.getIntrinsicWidth() + this.drawablePadding;
    L11:
        int r13 = r12 - r23;
        if (this.rightDrawableOutside == false) goto L16;
        Drawable r24 = this.rightDrawable;
        if (r24 == null) goto L16;
        int r25 = r24.getIntrinsicWidth() + this.drawablePadding;
    L17:
        int r14 = r13 - r25;
        if (this.rightDrawableOutside == false) goto L22;
        Drawable r26 = this.rightDrawable2;
        if (r26 == null) goto L22;
        int r27 = r26.getIntrinsicWidth() + this.drawablePadding;
    L23:
        createLayout(r14 - r27);
        if (View.MeasureSpec.getMode(r7) == 1073741824) goto L28;
        r0 = getPaddingBottom() + (getPaddingTop() + this.textHeight);
    L28:
        if (this.widthWrapContent == false) goto L48;
        int r72 = ((getPaddingLeft() + this.textWidth) + getPaddingRight()) + this.minusWidth;
        if (this.leftDrawableOutside == false) goto L34;
        Drawable r15 = this.leftDrawable;
        if (r15 == null) goto L34;
        int r16 = r15.getIntrinsicWidth() + this.drawablePadding;
    L35:
        int r73 = r72 + r16;
        if (this.rightDrawableOutside == false) goto L40;
        Drawable r17 = this.rightDrawable;
        if (r17 == null) goto L40;
        int r18 = r17.getIntrinsicWidth() + this.drawablePadding;
    L41:
        int r74 = r73 + r18;
        if (this.rightDrawableOutside == false) goto L46;
        Drawable r19 = this.rightDrawable2;
        if (r19 == null) goto L46;
        int r110 = r19.getIntrinsicWidth() + this.drawablePadding;
    L47:
        r62 = Math.max(Math.min(r62, r74 + r110), 0);
    L46:
        r110 = 0;
    L40:
        r18 = 0;
    L34:
        r16 = 0;
    L48:
        setMeasuredDimension(r62, r0);
        if ((this.gravity & 112) != 16) goto L52;
        this.offsetY = getPaddingTop() + ((((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - this.textHeight) / 2);
        return;
    L52:
        this.offsetY = getPaddingTop();
        return;
    L22:
        r27 = 0;
    L16:
        r25 = 0;
    L10:
        r23 = 0;
        goto L11
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r10) {
        if (this.rightDrawableOnClickListener != null) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.rightDrawable2OnClickListener == null) goto L14;
        if (this.rightDrawable2 == null) goto L14;
        AndroidUtilities.rectTmp.set(this.rightDrawable2X - AndroidUtilities.dp(16.0f), this.rightDrawable2Y - AndroidUtilities.dp(16.0f), this.rightDrawable2X + AndroidUtilities.dp(16.0f), this.rightDrawable2Y + AndroidUtilities.dp(16.0f));
        r0 = r0 | handleDrawableTouch(r10, this.rightDrawable2, this.rightDrawable2OnClickListener, false);
    L14:
        if (super.onTouchEvent(r10) == true) goto L18;
        if (r0 == true) goto L18;
        return false;
    L18:
        return true;
    L5:
        if (this.rightDrawable == null) goto L7;
        AndroidUtilities.rectTmp.set(this.rightDrawableX - AndroidUtilities.dp(16.0f), this.rightDrawableY - AndroidUtilities.dp(16.0f), this.rightDrawableX + AndroidUtilities.dp(16.0f), this.rightDrawableY + AndroidUtilities.dp(16.0f));
        r0 = handleDrawableTouch(r10, this.rightDrawable, this.rightDrawableOnClickListener, true);
        goto L9
    }

    public void replaceTextWithDrawable(Drawable r3, String r4) {
        Drawable r0 = this.replacedDrawable;
        if (r0 != r3) goto L5;
        return;
    L5:
        if (r0 == null) goto L7;
        r0.setCallback(null);
    L7:
        this.replacedDrawable = r3;
        if (r3 == null) goto L11;
        r3.setCallback(this);
    L11:
        if (recreateLayoutMaybe() == true) goto L13;
        invalidate();
    L13:
        this.replacedText = r4;
    }

    public void resetScrolling() {
        this.scrollingOffset = 0.0f;
        checkUi_layerType();
    }

    public void setAlignment(Layout.Alignment r1) {
        this.mAlignment = r1;
        requestLayout();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r3) {
        if (this.maxLines <= 1) goto L6;
        super.setBackgroundDrawable(r3);
        return;
    L6:
        this.wrapBackgroundDrawable = r3;
    }

    public void setBuildFullLayout(boolean r1) {
        this.buildFullLayout = r1;
    }

    public void setCanHideRightDrawable(boolean r1) {
        this.canHideRightDrawable = r1;
    }

    public void setDrawablePadding(int r2) {
        if (this.drawablePadding == r2) goto L10;
        this.drawablePadding = r2;
        if (recreateLayoutMaybe() == true) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void setEllipsizeByGradient(int r2) {
        setEllipsizeByGradient(r2, null);
    }

    public void setEllipsizeByGradient(int r2, Boolean r3) {
        setEllipsizeByGradient(true, r3);
        this.ellipsizeByGradientWidthDp = r2;
        updateFadePaints();
    }

    public void setEllipsizeByGradient(boolean r2) {
        setEllipsizeByGradient(r2, null);
    }

    public void setEllipsizeByGradient(boolean r2, Boolean r3) {
        if (this.scrollNonFitText != r2) goto L5;
        return;
    L5:
        this.ellipsizeByGradient = r2;
        this.forceEllipsizeByGradientLeft = r3;
        updateFadePaints();
        checkUi_layerType();
    }

    public void setEmojiCacheType(int r3) {
        if (r3 == this.emojiCacheType) goto L8;
        AnimatedEmojiSpan.release(this, this.emojiStack);
        this.emojiCacheType = r3;
        if (this.attachedToWindow == false) goto L9;
        this.emojiStack = AnimatedEmojiSpan.update(r3, this, this.emojiStack, new Layout[]{this.layout});
        return;
    L9:
        return;
    }

    public void setEmojiColor(int r3) {
        this.emojiStackColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
    }

    public void setFullAlpha(float r1) {
        this.fullAlpha = r1;
        invalidate();
    }

    public void setFullLayoutAdditionalWidth(int r2, int r3) {
        if (this.fullLayoutAdditionalWidth == r2) goto L5;
    L8:
        this.fullLayoutAdditionalWidth = r2;
        this.fullLayoutLeftOffset = r3;
        createLayout(((getMaxTextWidth() - getPaddingLeft()) - getPaddingRight()) - this.minusWidth);
        return;
    L5:
        if (this.fullLayoutLeftOffset != r3) goto L8;
    }

    public void setFullTextMaxLines(int r1) {
        this.fullTextMaxLines = r1;
    }

    public void setGravity(int r2) {
        if (this.gravity == r2) goto L8;
        this.gravity = r2;
        if (recreateLayoutMaybe() == true) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void setLeftDrawable(int r2) {
        if (r2 != 0) goto L4;
        Drawable r22 = null;
    L5:
        setLeftDrawable(r22);
        return;
    L4:
        r22 = getContext().getResources().getDrawable(r2);
        goto L5
    }

    public void setLeftDrawable(Drawable r3) {
        Drawable r0 = this.leftDrawable;
        if (r0 == r3) goto L15;
        if (r0 == null) goto L7;
        r0.setCallback(null);
    L7:
        this.leftDrawable = r3;
        if (r3 == null) goto L11;
        r3.setCallback(this);
    L11:
        if (recreateLayoutMaybe() == true) goto L14;
        invalidate();
        return;
    L14:
        return;
    }

    public void setLeftDrawableOutside(boolean r1) {
        this.leftDrawableOutside = r1;
    }

    public void setLeftDrawableTopPadding(int r1) {
        this.leftDrawableTopPadding = r1;
    }

    public void setLinkTextColor(int r2) {
        ((android.text.TextPaint) this.textPaint).linkColor = r2;
        invalidate();
    }

    public void setMaxLines(int r1) {
        this.maxLines = r1;
    }

    public void setMinWidth(int r1) {
        this.minWidth = r1;
    }

    public void setMinusWidth(int r2) {
        if (r2 == this.minusWidth) goto L10;
        this.minusWidth = r2;
        if (recreateLayoutMaybe() == true) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void setRightDrawable(int r2) {
        if (r2 != 0) goto L4;
        Drawable r22 = null;
    L5:
        setRightDrawable(r22);
        return;
    L4:
        r22 = getContext().getResources().getDrawable(r2);
        goto L5
    }

    public boolean setRightDrawable(Drawable r3) {
        Drawable r0 = this.rightDrawable;
        if (r0 != r3) goto L6;
        return false;
    L6:
        if (r0 == null) goto L8;
        r0.setCallback(null);
    L8:
        this.rightDrawable = r3;
        if (r3 == null) goto L12;
        r3.setCallback(this);
    L12:
        if (recreateLayoutMaybe() == true) goto L16;
        invalidate();
        return true;
    L16:
        return true;
    }

    public boolean setRightDrawable2(Drawable r3) {
        Drawable r0 = this.rightDrawable2;
        if (r0 != r3) goto L6;
        return false;
    L6:
        if (r0 == null) goto L8;
        r0.setCallback(null);
    L8:
        this.rightDrawable2 = r3;
        if (r3 == null) goto L12;
        r3.setCallback(this);
    L12:
        if (recreateLayoutMaybe() == true) goto L16;
        invalidate();
        return true;
    L16:
        return true;
    }

    public void setRightDrawable2OnClick(View.OnClickListener r1) {
        this.rightDrawable2OnClickListener = r1;
    }

    public void setRightDrawableInside(boolean r1) {
        this.rightDrawableInside = r1;
    }

    public void setRightDrawableOnClick(View.OnClickListener r1) {
        this.rightDrawableOnClickListener = r1;
    }

    public void setRightDrawableOutside(boolean r1) {
        this.rightDrawableOutside = r1;
    }

    public void setRightDrawableScale(float r1) {
        this.rightDrawableScale = r1;
    }

    public void setRightDrawableTopPadding(int r1) {
        this.rightDrawableTopPadding = r1;
    }

    public void setRightPadding(int r6) {
        if (this.paddingRight == r6) goto L38;
        this.paddingRight = r6;
        int r62 = ((getMaxTextWidth() - getPaddingLeft()) - getPaddingRight()) - this.minusWidth;
        Drawable r0 = this.leftDrawable;
        if (r0 != null) goto L7;
    L9:
        int r1 = 0;
        if (this.rightDrawableInside == true) goto L22;
        if (this.rightDrawable == null) goto L17;
        if (this.rightDrawableOutside == true) goto L17;
        r1 = (int) (r0.getIntrinsicWidth() * this.rightDrawableScale);
        r62 = (r62 - r1) - this.drawablePadding;
    L17:
        if (this.rightDrawable2 == null) goto L22;
        if (this.rightDrawableOutside == true) goto L22;
        r1 = (int) (r0.getIntrinsicWidth() * this.rightDrawableScale);
        r62 = (r62 - r1) - this.drawablePadding;
    L22:
        if (this.replacedText == null) goto L29;
        if (this.replacedDrawable == null) goto L29;
        int r02 = this.text.toString().indexOf(this.replacedText);
        this.replacingDrawableTextIndex = r02;
        if (r02 >= 0) goto L29;
        r62 = (r62 - this.replacedDrawable.getIntrinsicWidth()) - this.drawablePadding;
    L29:
        if (this.canHideRightDrawable == false) goto L36;
        if (r1 == 0) goto L36;
        if (this.rightDrawableOutside == true) goto L36;
        if (this.text.equals(TextUtils.ellipsize(this.text, this.textPaint, r62, TextUtils.TruncateAt.END)) == true) goto L36;
        this.rightDrawableHidden = true;
        r62 = (r62 + r1) + this.drawablePadding;
    L36:
        calcOffset(r62);
        invalidate();
        return;
    L7:
        if (this.leftDrawableOutside == true) goto L9;
        r62 = (r62 - r0.getIntrinsicWidth()) - this.drawablePadding;
        goto L9
    }

    public void setScrollNonFitText(boolean r2) {
        if (this.scrollNonFitText != r2) goto L5;
        return;
    L5:
        this.scrollNonFitText = r2;
        updateFadePaints();
        requestLayout();
        checkUi_layerType();
    }

    public void setSideDrawablesColor(int r2) {
        Theme.setDrawableColor(this.rightDrawable, r2);
        Theme.setDrawableColor(this.leftDrawable, r2);
    }

    public boolean setText(CharSequence r2) {
        return setText(r2, false);
    }

    public boolean setText(CharSequence r2, boolean r3) {
        CharSequence r0 = this.text;
        if (r0 != null) goto L5;
        if (r2 != null) goto L5;
        return false;
    L5:
        if (r3 == true) goto L11;
        if (r0 == null) goto L11;
        if (r0.equals(r2) == false) goto L11;
        return false;
    L11:
        this.text = r2;
        this.currentScrollDelay = MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        recreateLayoutMaybe();
        return true;
    }

    public void setTextColor(int r2) {
        this.textPaint.setColor(r2);
        invalidate();
    }

    public void setTextSize(int r1) {
        setTextSizePx(AndroidUtilities.dp(r1));
    }

    public void setTextSizePx(int r2) {
        float r22 = r2;
        if (r22 == this.textPaint.getTextSize()) goto L10;
        this.textPaint.setTextSize(r22);
        if (recreateLayoutMaybe() == true) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void setTypeface(Typeface r1) {
        this.textPaint.setTypeface(r1);
    }

    public void setWidthWrapContent(boolean r1) {
        this.widthWrapContent = r1;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.rightDrawable) goto L5;
        return true;
    L5:
        if (r2 != this.rightDrawable2) goto L7;
        return true;
    L7:
        if (r2 != this.leftDrawable) goto L9;
        return true;
    L9:
        if (super.verifyDrawable(r2) == true) goto L17;
        return false;
    L17:
        return true;
    }
}
