package com.exteragram.messenger.appicons.ui.components;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.appicons.AppIcon;
import com.exteragram.messenger.appicons.AppIconController;
import com.exteragram.messenger.appicons.AppIconPreviewLoader;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class AppIconHeroView extends LinearLayout implements Theme.Colorable {
    private float collapse;
    private final BaseFragment fragment;
    private AppIcon icon;
    private int linkColor;
    private ValueAnimator linkColorAnimator;
    private ClickableSpan pressedLink;
    private Runnable previewReadyListener;
    private int previewSizeDp;
    private final AppIconPreviewView previewView;
    private final Theme.ResourcesProvider resourcesProvider;
    private CharSequence subtitleLinks;
    private final AnimatedTextView subtitleView;
    private final AnimatedTextView titleView;

    /* renamed from: com.exteragram.messenger.appicons.ui.components.AppIconHeroView$1, reason: invalid class name */
    public class AnonymousClass1 extends CharacterStyle {
        final /* synthetic */ AppIconHeroView this$0;

        public AnonymousClass1(AppIconHeroView r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setColor(Theme.multAlpha(AppIconHeroView.m1136$$Nest$fgetlinkColor(this.this$0), r3.getAlpha() / 255.0f));
        }
    }

    public static /* synthetic */ void $r8$lambda$9K0gJ7jOfMPSZITWc4TGIn0joxA(AppIconHeroView r0) {
        r0.onPreviewReady();
    }

    /* renamed from: $r8$lambda$tbNgnbraldDO241-lnwtdjJUujg, reason: not valid java name */
    public static /* synthetic */ void m1135$r8$lambda$tbNgnbraldDO241lnwtdjJUujg(AppIconHeroView r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$updateLinkColor$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetlinkColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1136$$Nest$fgetlinkColor(AppIconHeroView r0) {
        return r0.linkColor;
    }

    public AppIconHeroView(Context r5, BaseFragment r6) {
        super(r5);
        this.previewSizeDp = 128;
        this.fragment = r6;
        Theme.ResourcesProvider r62 = r6.getResourceProvider();
        this.resourcesProvider = r62;
        setOrientation(1);
        setGravity(1);
        int r2 = 0;
        setClipToPadding(false);
        setClipChildren(false);
        AppIconPreviewView r3 = new AppIconPreviewView(r5, r62);
        this.previewView = r3;
        r3.setCrossfade(true);
        r3.setOnPreviewReady(new AppIconHeroView$$ExternalSyntheticLambda1(this));
        addView(r3, LayoutHelper.createLinear(128, 128, 1));
        this.titleView = addText(22, AndroidUtilities.bold(), 28, 18);
        this.subtitleView = addText(14, AndroidUtilities.regular(), 20, 6);
        updateColors();
        Iterator<AppIcon> r52 = AppIconController.getAvailableIcons().iterator();
        boolean r63 = false;
    L4:
        if (r52.hasNext() == false) goto L10;
        if (subtitleOf(r52.next()) == null) goto L8;
        boolean r0 = true;
    L9:
        r63 = r63 | r0;
        goto L4
    L8:
        r0 = false;
        goto L9
    L10:
        AnimatedTextView r4 = this.subtitleView;
        if (r63 == true) goto L14;
        r2 = 8;
    L14:
        r4.setVisibility(r2);
    }

    private AnimatedTextView addText(int r9, Typeface r10, int r11, int r12) {
        AnimatedTextView r0 = new AnimatedTextView(getContext(), true, true, false);
        r0.setGravity(17);
        r0.setTypeface(r10);
        r0.setTextSize(AndroidUtilities.dp(r9));
        r0.setIncludeFontPadding(false);
        r0.setAllowCancel(true);
        r0.setAnimationProperties(0.35f, 0, 260, CubicBezierInterpolator.EASE_OUT_QUINT);
        addView(r0, LayoutHelper.createLinear(-1, r11, 1, 24, r12, 24, 0));
        return r0;
    }

    private void applyCollapse() {
        float r0 = Math.min(1.0f, this.collapse / 0.35f);
        float r2 = (-AndroidUtilities.dp(12.0f)) * r0;
        float r02 = 1.0f - r0;
        this.titleView.setAlpha(r02);
        this.titleView.setTranslationY(r2);
        this.subtitleView.setAlpha(r02);
        this.subtitleView.setTranslationY(r2);
        int r03 = this.previewView.getMeasuredHeight();
        if (r03 > 0) goto L5;
        return;
    L5:
        float r04 = r03;
        float r4 = r04 / 2.0f;
        float r5 = (AndroidUtilities.statusBarHeight + (ActionBar.getCurrentActionBarHeight() / 2.0f)) - getTop();
        float r05 = AndroidUtilities.lerp(1.0f, AndroidUtilities.dp(40.0f) / r04, this.collapse);
        this.previewView.setPivotX(r1.getMeasuredWidth() / 2.0f);
        this.previewView.setPivotY(r4);
        this.previewView.setScaleX(r05);
        this.previewView.setScaleY(r05);
        this.previewView.setTranslationY((r5 - (this.previewView.getTop() + r4)) * this.collapse);
    }

    private CharSequence colorizeLinks(CharSequence r8) {
        if ((r8 instanceof Spanned) == true) goto L5;
        return r8;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder(r8);
        int r2 = 0;
        ClickableSpan[] r82 = (ClickableSpan[]) r0.getSpans(0, r0.length(), ClickableSpan.class);
        int r1 = r82.length;
    L6:
        if (r2 >= r1) goto L8;
        ClickableSpan r3 = r82[r2];
        int r4 = r0.getSpanStart(r3);
        int r5 = r0.getSpanEnd(r3);
        r0.removeSpan(r3);
        r0.setSpan(new AnonymousClass1(this), r4, r5, 33);
        r2 = r2 + 1;
        goto L6
    L8:
        return r0;
    }

    private ClickableSpan hitLink(float r13, float r14) {
        if (this.subtitleView.getVisibility() == 0) goto L5;
    L20:
        return null;
    L5:
        if (this.subtitleView.getAlpha() <= 0.0f) goto L20;
        if (r14 < 0.0f) goto L20;
        if (r14 > this.subtitleView.getHeight()) goto L20;
        CharSequence r142 = this.subtitleLinks;
        if ((r142 instanceof Spanned) == false) goto L20;
        Spanned r143 = (Spanned) r142;
        TextPaint r0 = this.subtitleView.getPaint();
        float r2 = (this.subtitleView.getWidth() - this.subtitleView.getDrawable().getCurrentWidth()) / 2.0f;
        ClickableSpan[] r12 = (ClickableSpan[]) r143.getSpans(0, r143.length(), ClickableSpan.class);
        int r3 = r12.length;
        int r5 = 0;
    L13:
        if (r5 >= r3) goto L20;
        ClickableSpan r6 = r12[r5];
        int r7 = r143.getSpanStart(r6);
        int r8 = r143.getSpanEnd(r6);
        float r9 = (r0.measureText(r143, 0, r7) + r2) - AndroidUtilities.dp(4.0f);
        float r72 = (r0.measureText(r143, r7, r8) + r9) + (AndroidUtilities.dp(4.0f) * 2);
        if (r13 < r9) goto L19;
        if (r13 > r72) goto L19;
        return r6;
    L19:
        r5 = r5 + 1;
        goto L13
    }

    private /* synthetic */ void lambda$updateLinkColor$0(int r1, int r2, ValueAnimator r3) {
        this.linkColor = ColorUtils.blendARGB(r1, r2, ((Float) r3.getAnimatedValue()).floatValue());
        this.subtitleView.invalidate();
    }

    private void onPreviewReady() {
        updateLinkColor(true);
        Runnable r1 = this.previewReadyListener;
        if (r1 == null) goto L6;
        r1.run();
        return;
    }

    private static CharSequence subtitleOf(AppIcon r2) {
        CharSequence r0 = r2.getDescription();
        if (TextUtils.isEmpty(r0) == true) goto L6;
        return r0;
    L6:
        return r2.getAuthor();
    }

    private void updateLinkColor(boolean r5) {
        int r0 = AppIconPreviewLoader.getAccentTextColor(this.icon, Theme.key_windowBackgroundWhiteLinkText, this.resourcesProvider);
        if (this.linkColor != r0) goto L5;
        return;
    L5:
        ValueAnimator r1 = this.linkColorAnimator;
        if (r1 == null) goto L8;
        r1.cancel();
        this.linkColorAnimator = null;
    L8:
        if (r5 == false) goto L14;
        int r52 = this.linkColor;
        if (r52 == 0) goto L14;
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(280);
        this.linkColorAnimator = r12;
        r12.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.linkColorAnimator.addUpdateListener(new AppIconHeroView$$ExternalSyntheticLambda0(this, r52, r0));
        this.linkColorAnimator.start();
        return;
    L14:
        this.linkColor = r0;
        this.subtitleView.invalidate();
    }

    public boolean dispatchLinkTouch(MotionEvent r6) {
        float r0 = (r6.getX() - getX()) - this.subtitleView.getX();
        float r1 = (r6.getY() - getY()) - this.subtitleView.getY();
        int r62 = r6.getActionMasked();
        if (r62 != 0) goto L8;
        ClickableSpan r63 = hitLink(r0, r1);
        this.pressedLink = r63;
        if (r63 == null) goto L7;
        return true;
    L7:
        return false;
    L8:
        ClickableSpan r4 = this.pressedLink;
        if (r4 != null) goto L11;
        return false;
    L11:
        if (r62 == 1) goto L13;
    L15:
        if (r62 != 1) goto L17;
    L18:
        this.pressedLink = null;
    L19:
        return true;
    L17:
        if (r62 != 3) goto L19;
    L13:
        if (r4 != hitLink(r0, r1)) goto L15;
        this.pressedLink.onClick(this.subtitleView);
        goto L15
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public int getPreviewSize() {
        return AndroidUtilities.dp(this.previewSizeDp);
    }

    public int getTextBlockHeight() {
        int r0 = AndroidUtilities.dp(46.0f);
        if (this.subtitleView.getVisibility() == 0) goto L5;
        return r0;
    L5:
        return r0 + AndroidUtilities.dp(26.0f);
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        applyCollapse();
    }

    public void set(AppIcon r4) {
        AppIcon r0 = this.icon;
        if (r0 != r4) goto L5;
        return;
    L5:
        if (r0 == null) goto L7;
        boolean r02 = true;
    L8:
        this.icon = r4;
        this.previewView.setIcon(r4);
        updateLinkColor(r02);
        this.titleView.setText(r4.getTitle(), r02);
        CharSequence r42 = subtitleOf(r4);
        if (r42 != null) goto L11;
        CharSequence r43 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        this.subtitleLinks = r43;
        this.subtitleView.setText(colorizeLinks(r43), r02);
        return;
    L11:
        r43 = LocaleUtils.formatWithUsernames(r42, this.fragment);
        goto L12
    L7:
        r02 = false;
        goto L8
    }

    public void setCollapse(float r2) {
        if (this.collapse != r2) goto L5;
        return;
    L5:
        this.collapse = r2;
        applyCollapse();
    }

    public void setOnPreviewReady(Runnable r1) {
        this.previewReadyListener = r1;
    }

    public void setPreviewSizeDp(int r2) {
        if (this.previewSizeDp != r2) goto L5;
        return;
    L5:
        this.previewSizeDp = r2;
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) this.previewView.getLayoutParams();
        int r22 = AndroidUtilities.dp(r2);
        r0.height = r22;
        r0.width = r22;
        this.previewView.requestLayout();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider);
        this.titleView.setTextColor(r0);
        this.subtitleView.setTextColor(ColorUtils.setAlphaComponent(r0, 179));
        updateLinkColor(false);
    }
}
