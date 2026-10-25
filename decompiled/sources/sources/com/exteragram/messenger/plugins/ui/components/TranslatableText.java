package com.exteragram.messenger.plugins.ui.components;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.text.Layout;
import android.widget.TextView;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LoadingDrawable;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010+\u001a\u00020\tJ\u0006\u0010,\u001a\u00020\tJ\u000e\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020#J\u000e\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020&J\u000e\u00101\u001a\u00020&2\u0006\u00102\u001a\u000203J\u0016\u00104\u001a\u00020\t2\u0006\u00102\u001a\u0002032\u0006\u00105\u001a\u00020&J\u0010\u00106\u001a\u00020\t2\u0006\u00102\u001a\u000203H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u0017R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;", _UrlKt.FRAGMENT_ENCODE_SET, "original", _UrlKt.FRAGMENT_ENCODE_SET, "format", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "onTransitionUpdate", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "getOriginal", "()Ljava/lang/String;", PluginsConstants.Settings.VIEW, "Landroid/widget/TextView;", "getView", "()Landroid/widget/TextView;", "setView", "(Landroid/widget/TextView;)V", "language", "getLanguage", "setLanguage", "(Ljava/lang/String;)V", "translation", "getTranslation", "setTranslation", "shownText", "loadingPath", "Lorg/telegram/ui/Components/LinkPath;", "loadingDrawable", "Lorg/telegram/ui/Components/LoadingDrawable;", "loadingLayout", "Landroid/text/Layout;", "loading", _UrlKt.FRAGMENT_ENCODE_SET, "oldLayout", "oldHeight", _UrlKt.FRAGMENT_ENCODE_SET, "transitionProgress", _UrlKt.FRAGMENT_ENCODE_SET, "transitionAnimator", "Landroid/animation/ValueAnimator;", "startLoading", "stopLoading", "show", "translated", "getHeight", "measuredHeight", "beforeDraw", "canvas", "Landroid/graphics/Canvas;", "afterDraw", "saveCount", "drawLoading", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
final class TranslatableText {
    private final Function1<String, CharSequence> format;
    private String language;
    private boolean loading;
    private final LoadingDrawable loadingDrawable;
    private Layout loadingLayout;
    private final LinkPath loadingPath;
    private int oldHeight;
    private Layout oldLayout;
    private final Function0<Unit> onTransitionUpdate;
    private final String original;
    private String shownText;
    private ValueAnimator transitionAnimator;
    private float transitionProgress;
    private String translation;
    private TextView view;

    public static void $r8$lambda$JRgU_sneSrKGk0HLUPOQr7eYGE4(TranslatableText r0, TextView r1, ValueAnimator r2) {
        r0.transitionProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.requestLayout();
        r1.invalidate();
        r0.onTransitionUpdate.invoke();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TranslatableText(String r1, Function1<? super String, ? extends CharSequence> r2, Function0<Unit> r3) {
        this.original = r1;
        this.format = r2;
        this.onTransitionUpdate = r3;
        this.shownText = r1;
        LinkPath r12 = new LinkPath(true);
        this.loadingPath = r12;
        LoadingDrawable r32 = new LoadingDrawable();
        r32.usePath(r12);
        r32.setAppearByGradient(true);
        r32.setRadiiDp(4.0f);
        this.loadingDrawable = r32;
        this.transitionProgress = 1.0f;
    }

    public static final /* synthetic */ void access$setOldLayout$p(TranslatableText r0, Layout r1) {
        r0.oldLayout = r1;
    }

    public static final /* synthetic */ void access$setTransitionAnimator$p(TranslatableText r0, ValueAnimator r1) {
        r0.transitionAnimator = r1;
    }

    public static final /* synthetic */ void access$setTransitionProgress$p(TranslatableText r0, float r1) {
        r0.transitionProgress = r1;
    }

    private final void drawLoading(Canvas r8) {
        TextView r0 = this.view;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.loading == true) goto L9;
        return;
    L9:
        if (this.loadingDrawable.isDisappeared() == false) goto L12;
        this.loading = false;
        return;
    L12:
        Layout r1 = r0.getLayout();
        if (r1 != null) goto L16;
        return;
    L16:
        if (r1 == this.loadingLayout) goto L18;
        this.loadingLayout = r1;
        this.loadingPath.rewind();
        this.loadingPath.setCurrentLayout(r1, 0, r0.getTotalPaddingLeft(), r0.getTotalPaddingTop());
        r1.getSelectionPath(0, r1.getText().length(), this.loadingPath);
        this.loadingDrawable.updateBounds();
    L18:
        int r12 = r0.getCurrentTextColor();
        this.loadingDrawable.setColors(Theme.multAlpha(r12, 0.05f), Theme.multAlpha(r12, 0.15f), Theme.multAlpha(r12, 0.1f), Theme.multAlpha(r12, 0.3f));
        this.loadingDrawable.draw(r8);
        r0.invalidate();
    }

    public final void afterDraw(Canvas r1, int r2) {
        if (r2 < 0) goto L4;
        r1.restoreToCount(r2);
    L4:
        drawLoading(r1);
    }

    public final int beforeDraw(Canvas r11) {
        TextView r7 = this.view;
        if (r7 != null) goto L5;
        return -1;
    L5:
        Layout r8 = this.oldLayout;
        if (r8 != null) goto L8;
        return -1;
    L8:
        r11.save();
        r11.clipRect(0, 0, r7.getWidth(), r7.getHeight());
        r11.translate(r7.getTotalPaddingLeft(), r7.getTotalPaddingTop());
        r11.saveLayerAlpha(0.0f, 0.0f, r8.getWidth(), r8.getHeight(), (int) ((1.0f - this.transitionProgress) * 255.0f));
        r8.draw(r11);
        r11.restore();
        r11.restore();
        return r11.saveLayerAlpha(0.0f, 0.0f, r7.getWidth(), r7.getHeight(), (int) (255.0f * this.transitionProgress));
    }

    public final int getHeight(int r2) {
        if (this.oldLayout != null) goto L6;
        return r2;
    L6:
        return AndroidUtilities.lerp(this.oldHeight, r2, this.transitionProgress);
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getOriginal() {
        return this.original;
    }

    public final String getTranslation() {
        return this.translation;
    }

    public final TextView getView() {
        return this.view;
    }

    public final void setLanguage(String r1) {
        this.language = r1;
    }

    public final void setTranslation(String r1) {
        this.translation = r1;
    }

    public final void setView(TextView r1) {
        this.view = r1;
    }

    public final void show(boolean r4) {
        TextView r0 = this.view;
        if (r0 == null) goto L17;
        if (r4 == false) goto L8;
        String r42 = this.translation;
        if (r42 == null) goto L8;
    L10:
        if (Intrinsics.areEqual(r42, this.shownText) == false) goto L12;
        return;
    L12:
        this.shownText = r42;
        ValueAnimator r1 = this.transitionAnimator;
        if (r1 == null) goto L15;
        r1.cancel();
    L15:
        this.oldLayout = r0.getLayout();
        this.oldHeight = r0.getHeight();
        r0.setText(this.format.invoke(r42));
        this.transitionProgress = 0.0f;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r43.setDuration(350);
        r43.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r43.addUpdateListener(new TranslatableText$$ExternalSyntheticLambda0(this, r0));
        r43.addListener(new TranslatableText$show$1$2(this, r0));
        r43.start();
        this.transitionAnimator = r43;
        return;
    L8:
        r42 = this.original;
        goto L10
    }

    public final void startLoading() {
        this.loading = true;
        this.loadingDrawable.reset();
        this.loadingDrawable.resetDisappear();
        TextView r1 = this.view;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }

    public final void stopLoading() {
        this.loadingDrawable.disappear();
        TextView r1 = this.view;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }
}
