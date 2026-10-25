package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class StarRatingView extends View {
    private final Colors colors;
    private Delegate delegate;
    private final BadgeLevelDrawable drawable;
    private boolean isVisible;
    private final AnimatedFloat isVisibleAnimator;
    private boolean isVisibleExternal;
    private boolean isVisibleInternal;

    public static class Colors {
        public int backgroundColor;
        public int fillingColor;
        private float parentExpanded;
        public MessagesController.PeerColor peerColor;
        private Theme.ResourcesProvider resourcesProvider;

        /* renamed from: -$$Nest$fputresourcesProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13047$$Nest$fputresourcesProvider(Colors r0, Theme.ResourcesProvider r1) {
            r0.resourcesProvider = r1;
        }

        private Colors() {
            this.backgroundColor = -16777216;
            this.fillingColor = -1;
        }

        public /* synthetic */ Colors(StarRatingViewIA r1) {
            this();
        }

        public void reset() {
            this.backgroundColor = Theme.getColor(Theme.key_actionBarDefaultTitle, this.resourcesProvider);
            this.fillingColor = Theme.getColor(Theme.key_actionBarDefault, this.resourcesProvider);
            this.backgroundColor = ColorUtils.blendARGB(this.backgroundColor, 603979776, this.parentExpanded);
            this.fillingColor = ColorUtils.blendARGB(this.fillingColor, -1, this.parentExpanded);
        }

        public void setParentExpanded(float r1) {
            this.parentExpanded = r1;
            update(this.peerColor);
        }

        public void update(MessagesController.PeerColor r4) {
            this.peerColor = r4;
            if (r4 != null) goto L6;
            reset();
            return;
        L6:
            int r0 = r4.getBgColor1(Theme.isCurrentThemeDark());
            int r42 = StarRatingView.getTabsViewBackgroundColor(this.resourcesProvider, r4.getBgColor2(Theme.isCurrentThemeDark()), r0);
            this.backgroundColor = r42;
            if (AndroidUtilities.computePerceivedBrightness(r42) <= 0.721f) goto L9;
            int r43 = -16777216;
        L10:
            this.fillingColor = r43;
            this.backgroundColor = ColorUtils.blendARGB(this.backgroundColor, 603979776, this.parentExpanded);
            this.fillingColor = ColorUtils.blendARGB(this.fillingColor, -1, this.parentExpanded);
            return;
        L9:
            r43 = -1;
            goto L10
        }
    }

    public interface Delegate {
        void onUpdateState(float r1);
    }

    public static /* synthetic */ void $r8$lambda$DgR0_0vkWpPLzR31oYUi4CXSreY(StarRatingView r0) {
        r0.lambda$onUpdateVisibilityFactor$0();
    }

    /* renamed from: $r8$lambda$tf0AziSWudVxZK9-wReoCn9zmMo, reason: not valid java name */
    public static /* synthetic */ void m13046$r8$lambda$tf0AziSWudVxZK9wReoCn9zmMo(StarRatingView r0) {
        r0.onUpdateVisibilityFactor();
    }

    public StarRatingView(Context r6) {
        super(r6);
        this.colors = new Colors(null);
        AnimatedFloat r0 = new AnimatedFloat(new StarRatingView$$ExternalSyntheticLambda0(this), 380, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.isVisibleAnimator = r0;
        BadgeLevelDrawable r1 = new BadgeLevelDrawable(r6);
        this.drawable = r1;
        r1.setCallback(this);
        this.isVisible = false;
        r0.set(false);
        checkVisibility();
    }

    private void checkVisibility() {
        if (this.isVisibleExternal == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        this.isVisible = r0;
        this.isVisibleAnimator.set(r0);
        setEnabled(this.isVisible);
        setClickable(this.isVisible);
        invalidate();
        return;
    L5:
        if (this.isVisibleInternal == false) goto L7;
        r0 = true;
        goto L8
    }

    public static int getTabsViewBackgroundColor(Theme.ResourcesProvider r1, int r2, int r3) {
        int r22 = ColorUtils.blendARGB(r2, r3, 0.75f);
        if (AndroidUtilities.computePerceivedBrightness(r22) <= 0.721f) goto L7;
        return Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, r1);
    L7:
        return Theme.adaptHSV(r22, 0.08f, -0.08f);
    }

    private /* synthetic */ void lambda$onUpdateVisibilityFactor$0() {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onUpdateState(getVisibilityFactor());
        return;
    }

    private void onUpdateVisibilityFactor() {
        invalidate();
        AndroidUtilities.runOnUIThread(new StarRatingView$$ExternalSyntheticLambda1(this));
    }

    public float getVisibilityFactor() {
        return this.isVisibleAnimator.get();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.drawable.debugUpdateStart();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.drawable.debugUpdateStop();
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        float r0 = this.isVisibleAnimator.set(this.isVisible);
        int r1 = (getMeasuredWidth() - AndroidUtilities.dp(24.0f)) / 2;
        int r3 = (getMeasuredHeight() - AndroidUtilities.dp(24.0f)) / 2;
        r6.save();
        r6.translate(r1, r3);
        r6.scale(r0, r0, 0.0f, AndroidUtilities.dp(12.0f));
        this.drawable.setBounds(0, 0, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.drawable.setOuterColor(this.colors.backgroundColor);
        this.drawable.setInnerColor(this.colors.fillingColor);
        this.drawable.setTextColor(this.colors.backgroundColor | (-16777216));
        this.drawable.draw(r6);
        r6.restore();
    }

    public void set(TL_stars.Tl_starsRating r4) {
        if (r4 == null) goto L5;
        boolean r1 = true;
    L6:
        this.isVisibleInternal = r1;
        checkVisibility();
        if (r4 != null) goto L9;
        return;
    L9:
        this.drawable.setBadgeLevel(r4.level, true);
        setContentDescription(LocaleController.getString(R.string.AccDescrProfileRatingLevel) + " " + r4.level);
        invalidate();
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setParentExpanded(float r2) {
        this.colors.setParentExpanded(r2);
        invalidate();
    }

    public void setResourcesProvider(Theme.ResourcesProvider r1) {
        Colors.m13047$$Nest$fputresourcesProvider(this.colors, r1);
    }

    public void setVisibility(boolean r1) {
        this.isVisibleExternal = r1;
        checkVisibility();
    }

    public void updateColors(MessagesController.PeerColor r2) {
        this.colors.update(r2);
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.drawable) goto L11;
        return false;
    L11:
        return true;
    }
}
