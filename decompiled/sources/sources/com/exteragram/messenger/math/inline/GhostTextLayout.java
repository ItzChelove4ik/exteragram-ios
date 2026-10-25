package com.exteragram.messenger.math.inline;

import android.graphics.Canvas;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.widget.TextView;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u000b\u0018\u0000 >2\u00020\u0001:\u0002>?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\rJ\u0006\u0010!\u001a\u00020\rJ6\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020+J&\u0010,\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010(\u001a\u00020\t2\u0006\u0010*\u001a\u00020+J\u000e\u0010-\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u000200J&\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205J(\u00107\u001a\u00020\r2\u0006\u0010%\u001a\u00020&2\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\tH\u0002J*\u0010;\u001a\u0004\u0018\u00010\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010<\u001a\u00020+2\u0006\u0010=\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u001e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001b¨\u0006@"}, d2 = {"Lcom/exteragram/messenger/math/inline/GhostTextLayout;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ghostAlpha", "Lcom/exteragram/messenger/math/inline/GhostTextLayout$GhostAlphaSpan;", "layout", "Landroid/text/StaticLayout;", "insertOffset", _UrlKt.FRAGMENT_ENCODE_SET, "drawTop", _UrlKt.FRAGMENT_ENCODE_SET, "movedText", _UrlKt.FRAGMENT_ENCODE_SET, "value", "paragraphStart", "getParagraphStart", "()I", "paragraphEnd", "getParagraphEnd", "detached", "getDetached", "()Z", "extraHeight", "getExtraHeight", "cursorShiftX", "getCursorShiftX", "()F", "cursorShiftY", "getCursorShiftY", "clear", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", "hasMovedText", "build", PluginsConstants.Settings.VIEW, "Landroid/widget/TextView;", "real", "Landroid/text/Layout;", "start", "end", "caret", "insert", _UrlKt.FRAGMENT_ENCODE_SET, "buildDetached", "setAlpha", "draw", "canvas", "Landroid/graphics/Canvas;", "readInsertedPositions", "from", NotificationBadge.NewHtcHomeBadger.COUNT, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "moved", "shadow", "realOffset", "shadowOffset", "newLayout", "text", "width", "Companion", "GhostAlphaSpan", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GhostTextLayout {
    public static final Companion Companion = null;
    private float cursorShiftX;
    private float cursorShiftY;
    private boolean detached;
    private float drawTop;
    private int extraHeight;
    private final GhostAlphaSpan ghostAlpha;
    private int insertOffset;
    private StaticLayout layout;
    private boolean movedText;
    private int paragraphEnd;
    private int paragraphStart;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/math/inline/GhostTextLayout$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EPSILON", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/math/inline/GhostTextLayout$GhostAlphaSpan;", "Landroid/text/style/CharacterStyle;", "Landroid/text/style/UpdateAppearance;", "<init>", "()V", "Landroid/text/TextPaint;", "tp", _UrlKt.FRAGMENT_ENCODE_SET, "updateDrawState", "(Landroid/text/TextPaint;)V", _UrlKt.FRAGMENT_ENCODE_SET, "alpha", "F", "getAlpha", "()F", "setAlpha", "(F)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class GhostAlphaSpan extends CharacterStyle implements UpdateAppearance {
        private float alpha;

        public GhostAlphaSpan() {
            this.alpha = 1.0f;
        }

        public final void setAlpha(float r1) {
            this.alpha = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            r2.setAlpha((int) (r2.getAlpha() * this.alpha));
        }
    }

    static {
        Companion = new Companion(null);
    }

    public GhostTextLayout() {
        this.ghostAlpha = new GhostAlphaSpan();
    }

    private final boolean moved(Layout r4, StaticLayout r5, int r6, int r7) {
        if (Math.abs(r5.getPrimaryHorizontal(r7) - r4.getPrimaryHorizontal(r6)) < 0.5f) goto L6;
        return true;
    L6:
        if (Math.abs(r5.getLineTop(r5.getLineForOffset(r7)) - (r4.getLineTop(r4.getLineForOffset(r6)) - this.drawTop)) < 0.5f) goto L8;
        return true;
    L8:
        return false;
    }

    private final StaticLayout newLayout(TextView r3, Layout r4, CharSequence r5, int r6) {
        StaticLayout.Builder r2 = StaticLayout.Builder.obtain(r5, 0, r5.length(), r3.getPaint(), r6).setAlignment(r4.getAlignment()).setLineSpacing(r3.getLineSpacingExtra(), r3.getLineSpacingMultiplier()).setIncludePad(r3.getIncludeFontPadding()).setBreakStrategy(r3.getBreakStrategy()).setHyphenationFrequency(r3.getHyphenationFrequency()).setTextDirection(TextDirectionHeuristics.FIRSTSTRONG_LTR);     // Catch: Exception -> L10
        int r42 = Build.VERSION.SDK_INT;     // Catch: Exception -> L10
        if (r42 < 26) goto L6;
        r2.setJustificationMode(r3.getJustificationMode());     // Catch: Exception -> L10
    L6:
        if (r42 < 28) goto L8;
        r2.setUseLineSpacingFromFallbacks(r3.isFallbackLineSpacing());     // Catch: Exception -> L10
    L8:
        return r2.build();
    L10:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public final boolean build(TextView r7, Layout r8, int r9, int r10, int r11, CharSequence r12) {
        clear();
        CharSequence r0 = r7.getText();
        boolean r1 = false;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r9 >= 0) goto L7;
    L24:
        return false;
    L7:
        if (r10 > r0.length()) goto L24;
        if (r11 < r9) goto L24;
        if (r11 > r10) goto L24;
        int r2 = r8.getWidth();
        if (r2 > 0) goto L14;
        return false;
    L14:
        int r3 = r11 - r9;
        SpannableStringBuilder r4 = new SpannableStringBuilder(r0, r9, r10);
        r4.insert(r3, r12);
        r4.setSpan(this.ghostAlpha, r3, r12.length() + r3, 33);
        StaticLayout r72 = newLayout(r7, r8, r4, r2);
        if (r72 != null) goto L17;
        return false;
    L17:
        int r122 = r8.getLineForOffset(r9);
        int r02 = r8.getLineForOffset(r10);
        this.layout = r72;
        this.insertOffset = r3;
        this.paragraphStart = r9;
        this.paragraphEnd = r10;
        this.drawTop = r8.getLineTop(r122);
        this.extraHeight = Math.max(0, r72.getHeight() - (r8.getLineTop(r02 + 1) - r8.getLineTop(r122)));
        if (r3 > 0) goto L20;
    L22:
        this.movedText = r1;
        this.cursorShiftX = r72.getPrimaryHorizontal(r3) - r8.getPrimaryHorizontal(r11);
        this.cursorShiftY = (r72.getLineTop(r72.getLineForOffset(r3)) + this.drawTop) - r8.getLineTop(r8.getLineForOffset(r11));
        return true;
    L20:
        if (moved(r8, r72, (r9 + r3) - 1, r3 - 1) == false) goto L22;
        r1 = true;
        goto L22
    }

    public final boolean buildDetached(TextView r6, Layout r7, int r8, CharSequence r9) {
        clear();
        int r0 = r7.getWidth();
        if (r0 > 0) goto L5;
        return false;
    L5:
        SpannableStringBuilder r2 = new SpannableStringBuilder(r9);
        r2.setSpan(this.ghostAlpha, 0, r2.length(), 33);
        StaticLayout r62 = newLayout(r6, r7, r2, r0);
        if (r62 != null) goto L8;
        return false;
    L8:
        this.layout = r62;
        this.detached = true;
        this.drawTop = r7.getLineBottom(r7.getLineForOffset(r8));
        this.extraHeight = r62.getHeight();
        return true;
    }

    public final void clear() {
        this.layout = null;
        this.insertOffset = 0;
        this.drawTop = 0.0f;
        this.movedText = false;
        this.detached = false;
        this.paragraphStart = 0;
        this.paragraphEnd = 0;
        this.extraHeight = 0;
        this.cursorShiftX = 0.0f;
        this.cursorShiftY = 0.0f;
    }

    public final void draw(Canvas r3) {
        StaticLayout r0 = this.layout;
        if (r0 != null) goto L5;
        return;
    L5:
        r3.save();
        r3.translate(0.0f, this.drawTop);
        r0.draw(r3);
        r3.restore();
    }

    public final float getCursorShiftX() {
        return this.cursorShiftX;
    }

    public final float getCursorShiftY() {
        return this.cursorShiftY;
    }

    public final boolean getDetached() {
        return this.detached;
    }

    public final int getExtraHeight() {
        return this.extraHeight;
    }

    public final int getParagraphEnd() {
        return this.paragraphEnd;
    }

    public final int getParagraphStart() {
        return this.paragraphStart;
    }

    public final boolean hasMovedText() {
        return this.movedText;
    }

    public final boolean isEmpty() {
        if (this.layout != null) goto L6;
        return true;
    L6:
        return false;
    }

    public final void readInsertedPositions(int r5, int r6, float[] r7, float[] r8) {
        StaticLayout r0 = this.layout;
        if (r0 == null) goto L8;
        int r1 = 0;
    L6:
        if (r1 >= r6) goto L10;
        r7[r1] = r0.getPrimaryHorizontal((this.insertOffset + r5) + r1);
        r8[r1] = this.drawTop + r0.getLineBaseline(r0.getLineForOffset(r2));
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    public final void setAlpha(float r1) {
        this.ghostAlpha.setAlpha(r1);
    }
}
