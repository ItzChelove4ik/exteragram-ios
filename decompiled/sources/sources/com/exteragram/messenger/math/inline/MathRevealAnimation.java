package com.exteragram.messenger.math.inline;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationBadge;
import org.telegram.ui.Components.CubicBezierInterpolator;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 .2\u00020\u0001:\u0002./B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\rJ.\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u0014J\u0006\u0010&\u001a\u00020\u001fJ\u0016\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\rJ\u0010\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001bH\u0002J\b\u0010-\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/exteragram/messenger/math/inline/MathRevealAnimation;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.VIEW, "Landroid/widget/TextView;", "onFinished", "Ljava/lang/Runnable;", "<init>", "(Landroid/widget/TextView;Ljava/lang/Runnable;)V", "paint", "Landroid/text/TextPaint;", "maskSpan", "Lcom/exteragram/messenger/math/inline/MathRevealAnimation$MaskSpan;", "rangeStart", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEnd", "startedAt", _UrlKt.FRAGMENT_ENCODE_SET, "duration", _UrlKt.FRAGMENT_ENCODE_SET, "fromX", _UrlKt.FRAGMENT_ENCODE_SET, "fromY", "toX", "toY", "targetLayout", "Landroid/text/Layout;", "isRunning", _UrlKt.FRAGMENT_ENCODE_SET, "isCaretOutside", "caret", "begin", _UrlKt.FRAGMENT_ENCODE_SET, "text", "Landroid/text/Editable;", "from", NotificationBadge.NewHtcHomeBadger.COUNT, "originX", "originY", "cancel", "draw", "canvas", "Landroid/graphics/Canvas;", PluginsConstants.Settings.ACCENT, "stop", "deferred", "removeMasks", "Companion", "MaskSpan", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMathRevealAnimation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MathRevealAnimation.kt\ncom/exteragram/messenger/math/inline/MathRevealAnimation\n+ 2 Canvas.kt\nandroidx/core/graphics/CanvasKt\n*L\n1#1,162:1\n81#2,8:163\n*S KotlinDebug\n*F\n+ 1 MathRevealAnimation.kt\ncom/exteragram/messenger/math/inline/MathRevealAnimation\n*L\n122#1:163,8\n*E\n"})
/* loaded from: classes4.dex */
public final class MathRevealAnimation {
    public static final Companion Companion = null;
    private float duration;
    private float[] fromX;
    private float[] fromY;
    private MaskSpan maskSpan;
    private final Runnable onFinished;
    private final TextPaint paint;
    private int rangeEnd;
    private int rangeStart;
    private long startedAt;
    private Layout targetLayout;
    private float[] toX;
    private float[] toY;
    private final TextView view;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/exteragram/messenger/math/inline/MathRevealAnimation$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "GHOST_ALPHA", _UrlKt.FRAGMENT_ENCODE_SET, "BASE_DURATION", "PER_CHAR", "MAX_DURATION", "HIGHLIGHT_DURATION", "WAVE", "SCALE", "PIVOT_RATIO", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/math/inline/MathRevealAnimation$MaskSpan;", "Landroid/text/style/CharacterStyle;", "Landroid/text/style/UpdateAppearance;", "owner", "Lcom/exteragram/messenger/math/inline/MathRevealAnimation;", "<init>", "(Lcom/exteragram/messenger/math/inline/MathRevealAnimation;)V", "updateDrawState", _UrlKt.FRAGMENT_ENCODE_SET, "tp", "Landroid/text/TextPaint;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MaskSpan extends CharacterStyle implements UpdateAppearance {
        private final MathRevealAnimation owner;

        public MaskSpan(MathRevealAnimation r1) {
            this.owner = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            if (MathRevealAnimation.access$getMaskSpan$p(this.owner) != this) goto L6;
            r2.setAlpha(0);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$xtxysdzaFb_CUf8ESSuwiBuX7eI(MathRevealAnimation r0) {
        r0.removeMasks();
    }

    static {
        Companion = new Companion(null);
    }

    public MathRevealAnimation(TextView r1, Runnable r2) {
        this.view = r1;
        this.onFinished = r2;
        this.paint = new TextPaint(1);
        this.rangeStart = -1;
        this.rangeEnd = -1;
        this.duration = 200.0f;
        this.fromX = new float[0];
        this.fromY = new float[0];
        this.toX = new float[0];
        this.toY = new float[0];
    }

    public static final /* synthetic */ MaskSpan access$getMaskSpan$p(MathRevealAnimation r0) {
        return r0.maskSpan;
    }

    private final void removeMasks() {
        CharSequence r3 = this.view.getText();
        if ((r3 instanceof Spannable) == false) goto L8;
        Spannable r32 = (Spannable) r3;
        Iterator r0 = ArrayIteratorKt.iterator(r32.getSpans(0, r32.length(), MaskSpan.class));
    L6:
        if (r0.hasNext() == false) goto L10;
        r32.removeSpan((MaskSpan) r0.next());
        goto L6
    L10:
        return;
    }

    private final void stop(boolean r3) {
        MaskSpan r0 = this.maskSpan;
        if (this.rangeStart >= 0) goto L6;
        if (r0 != null) goto L6;
        return;
    L6:
        this.rangeStart = -1;
        this.rangeEnd = -1;
        this.maskSpan = null;
        this.targetLayout = null;
        if (r0 == null) goto L11;
        if (r3 == false) goto L10;
        AndroidUtilities.runOnUIThread(new MathRevealAnimation$$ExternalSyntheticLambda0(this));
        goto L11
    L10:
        removeMasks();
    L11:
        this.onFinished.run();
        this.view.invalidate();
    }

    public final void begin(Editable r1, int r2, int r3, float[] r4, float[] r5) {
        this.rangeStart = r2;
        this.rangeEnd = r2 + r3;
        this.fromX = r4;
        this.fromY = r5;
        this.toX = new float[r3];
        this.toY = new float[r3];
        this.targetLayout = null;
        this.startedAt = SystemClock.elapsedRealtime();
        this.duration = Math.min(420.0f, (r3 * 28.0f) + 200.0f);
        MaskSpan r22 = new MaskSpan(this);
        this.maskSpan = r22;
        r1.setSpan(r22, this.rangeStart, this.rangeEnd, 33);
    }

    public final void cancel() {
        stop(false);
    }

    public final void draw(Canvas r18, int r19) {
        Layout r2 = this.view.getLayout();
        CharSequence r3 = this.view.getText();
        if (r2 == null) goto L30;
        if (r3 == null) goto L30;
        if (this.rangeEnd > r3.length()) goto L30;
        int r9 = this.rangeEnd - this.rangeStart;
        if (this.targetLayout == r2) goto L13;
        this.targetLayout = r2;
        int r4 = 0;
    L11:
        if (r4 >= r9) goto L13;
        this.toX[r4] = r2.getPrimaryHorizontal(this.rangeStart + r4);
        this.toY[r4] = r2.getLineBaseline(r2.getLineForOffset(r6));
        r4 = r4 + 1;
    L13:
        float r22 = SystemClock.elapsedRealtime() - this.startedAt;
        float r10 = 1.0f;
        float r11 = RangesKt.coerceIn(r22 / this.duration, 0.0f, 1.0f);
        float r12 = RangesKt.coerceIn(r22 / 800.0f, 0.0f, 1.0f);
        int r23 = ColorUtils.blendARGB(r19, this.view.getCurrentTextColor(), CubicBezierInterpolator.EASE_BOTH.getInterpolation(r12));
        int r13 = Color.alpha(r23);
        float r14 = this.view.getPaint().getTextSize() * 0.32f;
        this.paint.set(this.view.getPaint());
        this.paint.setColor(r23);
        int r15 = 0;
    L14:
        if (r15 >= r9) goto L21;
        int r24 = this.rangeStart + r15;
        float r42 = AndroidUtilities.cascade(r11, r15, r9, 3.5f);
        float r5 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r42);
        float r43 = CubicBezierInterpolator.EASE_OUT_BACK.getInterpolation(r42);
        float r6 = AndroidUtilities.lerp(this.fromX[r15], this.toX[r15], r5);
        float r7 = AndroidUtilities.lerp(this.fromY[r15], this.toY[r15], r5);
        float r44 = AndroidUtilities.lerp(1.12f, r10, r43);
        this.paint.setAlpha((int) (r13 * AndroidUtilities.lerp(0.4f, r10, r5)));
        int r62 = r24 + 1;
        float r16 = r10;
        int r102 = r18.save();
        r18.scale(r44, r44, r6 + (this.paint.measureText(r3, r24, r62) / 2.0f), r7 - r14);
        CharSequence r52 = r3;
        r18.drawText(r52, r24, r62, r6, r7, this.paint);     // Catch: Throwable -> L18
        r18.restoreToCount(r102);
        r15 = r15 + 1;
        r3 = r52;
        r10 = r16;
    L18:
        th = move-exception;
        r18.restoreToCount(r102);
        throw th;
    L21:
        float r162 = r10;
        if (r11 >= r162) goto L24;
    L28:
        this.view.invalidate();
        return;
    L24:
        if (r12 < r162) goto L28;
        stop(true);
        return;
    L30:
        stop(true);
    }

    public final boolean isCaretOutside(int r2) {
        if (r2 >= this.rangeStart) goto L5;
        return true;
    L5:
        if (r2 > this.rangeEnd) goto L11;
        return false;
    L11:
        return true;
    }

    public final boolean isRunning() {
        if (this.rangeStart < 0) goto L6;
        return true;
    L6:
        return false;
    }
}
