package com.exteragram.messenger.plugins.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0005J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J(\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;", "Landroid/view/View;", "context", "Landroid/content/Context;", "color", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroid/content/Context;I)V", "translateIcon", "Landroid/graphics/drawable/Drawable;", "resetIcon", "progressDrawable", "Lorg/telegram/ui/Components/CircularProgressDrawable;", "loadingProgress", "Lorg/telegram/ui/Components/AnimatedFloat;", "switchProgress", "icon", "previousIcon", "value", "state", "getState", "()I", "setState", _UrlKt.FRAGMENT_ENCODE_SET, "updateContentDescription", "onDraw", "canvas", "Landroid/graphics/Canvas;", "drawIcon", "drawable", "alpha", _UrlKt.FRAGMENT_ENCODE_SET, "scale", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginTranslateButton.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginTranslateButton.kt\ncom/exteragram/messenger/plugins/ui/components/PluginTranslateButton\n+ 2 Canvas.kt\nandroidx/core/graphics/CanvasKt\n*L\n1#1,134:1\n44#2,8:135\n81#2,8:143\n*S KotlinDebug\n*F\n+ 1 PluginTranslateButton.kt\ncom/exteragram/messenger/plugins/ui/components/PluginTranslateButton\n*L\n84#1:135,8\n123#1:143,8\n*E\n"})
/* loaded from: classes4.dex */
public final class PluginTranslateButton extends View {
    public static final Companion Companion = null;
    public static final int STATE_LOADING = 1;
    public static final int STATE_RESET = 2;
    public static final int STATE_TRANSLATE = 0;
    private Drawable icon;
    private final AnimatedFloat loadingProgress;
    private Drawable previousIcon;
    private final CircularProgressDrawable progressDrawable;
    private final Drawable resetIcon;
    private int state;
    private final AnimatedFloat switchProgress;
    private final Drawable translateIcon;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "STATE_TRANSLATE", _UrlKt.FRAGMENT_ENCODE_SET, "STATE_LOADING", "STATE_RESET", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public PluginTranslateButton(Context r12, int r13) {
        super(r12);
        Drawable r0 = ContextCompat.getDrawable(r12, R.drawable.msg_translate);
        if (r0 == null) goto L10;
        Drawable r02 = r0.mutate();
        this.translateIcon = r02;
        Drawable r122 = ContextCompat.getDrawable(r12, R.drawable.photo_undo2);
        if (r122 == null) goto L8;
        Drawable r123 = r122.mutate();
        this.resetIcon = r123;
        this.progressDrawable = new CircularProgressDrawable(r13);
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.loadingProgress = new AnimatedFloat(this, 0, 320, r8);
        this.switchProgress = new AnimatedFloat(1.0f, this, 0, 250, r8);
        this.icon = r02;
        PorterDuffColorFilter r11 = new PorterDuffColorFilter(r13, PorterDuff.Mode.SRC_IN);
        r02.setColorFilter(r11);
        r123.setColorFilter(r11);
        updateContentDescription();
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    }

    private final void drawIcon(Canvas r5, Drawable r6, float r7, float r8) {
        if (r7 > 0.0f) goto L5;
        return;
    L5:
        int r0 = (getWidth() - r6.getIntrinsicWidth()) / 2;
        int r1 = (getHeight() - r6.getIntrinsicHeight()) / 2;
        r6.setBounds(r0, r1, r6.getIntrinsicWidth() + r0, r6.getIntrinsicHeight() + r1);
        r6.setAlpha((int) (255.0f * r7));
        int r02 = r5.save();
        r5.scale(r8, r8, getWidth() / 2.0f, getHeight() / 2.0f);
        r6.draw(r5);     // Catch: Throwable -> L9
        r5.restoreToCount(r02);
        return;
    L9:
        th = move-exception;
        r5.restoreToCount(r02);
        throw th;
    }

    private final void updateContentDescription() {
        if (this.state != 2) goto L5;
        int r0 = R.string.ShowOriginalButton;
    L6:
        setContentDescription(LocaleController.getString(r0));
        return;
    L5:
        r0 = R.string.TranslateMessage;
        goto L6
    }

    public final int getState() {
        return this.state;
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        AnimatedFloat r0 = this.loadingProgress;
        if (this.state != 1) goto L5;
        float r1 = 1.0f;
    L6:
        float r02 = r0.set(r1);
        int r12 = AndroidUtilities.dp(10.0f);
        if (r02 >= 1.0f) goto L22;
        float r2 = this.switchProgress.set(1.0f);
        float r5 = 1.0f - r02;
        float r6 = AndroidUtilities.lerp(1.0f, 0.6f, r02);
        int r8 = r13.save();
        r13.translate(0.0f, (-r12) * r02);
        Drawable r7 = this.previousIcon;     // Catch: Throwable -> L14
        if (r7 != null) goto L12;
    L17:
        drawIcon(r13, this.icon, r5 * r2, r6 * AndroidUtilities.lerp(0.4f, 1.0f, r2));     // Catch: Throwable -> L14
        r13.restoreToCount(r8);
        goto L22
    L12:
        if (r2 >= 1.0f) goto L16;
        drawIcon(r13, r7, (1.0f - r2) * r5, AndroidUtilities.lerp(1.0f, 0.4f, r2) * r6);     // Catch: Throwable -> L14
        goto L17
    L16:
        this.previousIcon = null;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        r13.restoreToCount(r8);
        throw th;
    L22:
        if (r02 <= 0.0f) goto L27;
        int r14 = (int) ((1.0f - r02) * r12);
        this.progressDrawable.setBounds(0, r14, getWidth(), getHeight() + r14);
        this.progressDrawable.setAlpha((int) (255.0f * r02));
        this.progressDrawable.draw(r13);
        invalidate();
        return;
    L27:
        return;
    L5:
        r1 = 0.0f;
        goto L6
    }

    public final void setState(int r3) {
        if (this.state != r3) goto L5;
        return;
    L5:
        this.state = r3;
        if (r3 != 1) goto L8;
    L14:
        updateContentDescription();
        invalidate();
        return;
    L8:
        if (r3 != 2) goto L10;
        Drawable r32 = this.resetIcon;
    L11:
        Drawable r1 = this.icon;
        if (r32 == r1) goto L14;
        this.previousIcon = r1;
        this.icon = r32;
        this.switchProgress.set(0.0f, true);
        goto L14
    L10:
        r32 = this.translateIcon;
        goto L11
    }
}
