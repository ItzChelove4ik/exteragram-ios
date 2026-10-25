package com.exteragram.messenger.pillstack.ui.pills.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import com.exteragram.messenger.pillstack.ui.PillStackPreferencesActivity;
import com.exteragram.messenger.pillstack.ui.pills.BasePill;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.CacheControlActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.LaunchActivity;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
public class CachePill extends BasePill implements NotificationCenter.NotificationCenterDelegate {
    private static final AtomicLong lastKnownCacheSize = null;
    private static float lastKnownProgress;
    private final AtomicBoolean calculating;
    private final ImageView iconView;
    private final LinearLayout layout;
    private final StorageProgressDrawable progressDrawable;
    private final AnimatedTextView textView;

    public static class StorageProgressDrawable extends Drawable {
        private final AnimatedFloat animatedProgress;
        private int color;
        private final Paint paint;
        private float progress;
        private final RectF rectF;

        public StorageProgressDrawable(View r5) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.rectF = new RectF();
            this.progress = 0.0f;
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeCap(Paint.Cap.ROUND);
            this.animatedProgress = new AnimatedFloat(r5, 650, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r12) {
            int r0 = getBounds().width();
            int r1 = getBounds().height();
            float r2 = Math.min(r0, r1) - AndroidUtilities.dp(2.0f);
            float r02 = r0;
            float r4 = (r02 - r2) / 2.0f;
            float r13 = r1;
            float r5 = (r13 - r2) / 2.0f;
            this.rectF.set(r4, r5, r4 + r2, r5 + r2);
            float r42 = this.animatedProgress.set(this.progress);
            this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.paint.setColor(this.color);
            this.paint.setAlpha(50);
            r12.drawCircle(r02 / 2.0f, r13 / 2.0f, r2 / 2.0f, this.paint);
            this.paint.setAlpha(255);
            r12.drawArc(this.rectF, -90.0f, r42 * 360.0f, false, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        public void setColor(int r1) {
            this.color = r1;
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.paint.setColorFilter(r1);
        }

        public void setProgress(float r2, boolean r3) {
            float r22 = Math.max(0.05f, Math.min(r2, 1.0f));
            this.progress = r22;
            if (r3 == true) goto L5;
            this.animatedProgress.force(r22);
        L5:
            invalidateSelf();
        }
    }

    public static /* synthetic */ void $r8$lambda$22YZIGmj4Zq2xlQWgaxVRGBs_3o(CachePill r0) {
        r0.lambda$onUpdateData$2();
    }

    public static /* synthetic */ void $r8$lambda$4wWX0oSfWJvekLb_hgjDCzPff4U(BaseFragment r1) {
        r1.presentFragment(new PillStackPreferencesActivity());
    }

    public static /* synthetic */ void $r8$lambda$5VmvcBwn7Elt7GkAPMFkaRaGbxM(CachePill r0) {
        r0.lambda$onPillLongClicked$3();
    }

    /* renamed from: $r8$lambda$IwzvDTgKtVDincy6f-355PNJhSY, reason: not valid java name */
    public static /* synthetic */ void m1539$r8$lambda$IwzvDTgKtVDincy6f355PNJhSY(CachePill r0) {
        r0.openCacheSettings();
    }

    public static /* synthetic */ void $r8$lambda$KNdhRJhgF5ziU8G07PQ2lgZNtcw(CachePill r0, Long r1, Long r2, Long r3) {
        r0.lambda$onUpdateData$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$d8qz1Zvj3zmPaACuwccOnNsfqlY(CachePill r0, Long r1) {
        r0.lambda$onUpdateData$1(r1);
    }

    static {
        lastKnownCacheSize = new AtomicLong(-1);
        lastKnownProgress = -1.0f;
    }

    public CachePill(Context r13, Theme.ResourcesProvider r14) {
        super(r13, r14);
        this.calculating = new AtomicBoolean(false);
        LinearLayout r142 = new LinearLayout(r13);
        this.layout = r142;
        r142.setOrientation(0);
        r142.setGravity(17);
        r142.setMinimumWidth(AndroidUtilities.dp(48.0f));
        r142.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(8.0f), 0);
        if (LocaleController.isRTL == false) goto L5;
        int r1 = 3;
    L6:
        addView(r142, LayoutHelper.createFrame(-2, 28, r1 | 16));
        ImageView r12 = new ImageView(r13);
        this.iconView = r12;
        r12.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r142.addView(r12, LayoutHelper.createLinear(16, 16, 16, 0, 0, 6, 0));
        StorageProgressDrawable r4 = new StorageProgressDrawable(r12);
        this.progressDrawable = r4;
        r12.setImageDrawable(r4);
        AnimatedTextView r42 = new AnimatedTextView(r13, true, true, true);
        this.textView = r42;
        r42.setTextSize(AndroidUtilities.dp(13.0f));
        r42.setTypeface(AndroidUtilities.bold());
        r42.setIncludeFontPadding(false);
        r42.adaptWidth = true;
        r142.addView(r42, LayoutHelper.createLinear(-2, -2, 16));
        setLoadingTargetView(r142);
        updateColors();
        ScaleStateListAnimator.apply(r142);
        AtomicLong r132 = lastKnownCacheSize;
        if (r132.get() != (-1)) goto L9;
    L12:
        r12.setVisibility(8);
        r42.setVisibility(8);
        return;
    L9:
        if (isRefreshDue() == true) goto L12;
        setData(r132.get(), lastKnownProgress, false);
        return;
    L5:
        r1 = 5;
        goto L6
    }

    private /* synthetic */ void lambda$onPillLongClicked$3() {
        onUpdateData(true);
    }

    private /* synthetic */ void lambda$onUpdateData$0(Long r5, Long r6, Long r7) {
        if (r6.longValue() <= 0) goto L5;
        float r72 = (r6.longValue() - r7.longValue()) / r6.longValue();
    L6:
        lastKnownProgress = r72;
        this.calculating.set(false);
        setData(r5.longValue(), r72, true);
        return;
    L5:
        r72 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$onUpdateData$1(Long r4) {
        lastKnownCacheSize.set(r4.longValue());
        CacheControlActivity.getDeviceTotalSize(new CachePill$$ExternalSyntheticLambda5(this, r4));
    }

    private /* synthetic */ void lambda$onUpdateData$2() {
        CacheControlActivity.calculateTotalSize(new CachePill$$ExternalSyntheticLambda1(this));
    }

    private void openCacheSettings() {
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L6;
        r1.presentFragment(new CacheControlActivity());
        return;
    }

    private void setData(long r2, float r4, boolean r5) {
        stopLoading();
        String r22 = AndroidUtilities.formatFileSize(r2);
        if (r5 == true) goto L5;
    L11:
        this.textView.setText(r22, r5);
        this.progressDrawable.setProgress(r4, r5);
        this.iconView.setVisibility(0);
        this.textView.setVisibility(0);
        markDataUpdated();
        return;
    L5:
        if (this.textView.getText() != null) goto L7;
    L10:
        animateSizeChange();
        goto L11
    L7:
        if (TextUtils.equals(this.textView.getText(), r22) == false) goto L10;
        if (this.textView.getVisibility() != 8) goto L11;
        goto L10
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.pillStackSettingsChanged) goto L5;
        return;
    L5:
        if (PillStackConfig.shouldUpdatePill(r3, new int[]{getPillId()}) == false) goto L10;
        PillStackConfig.checkAndClearPendingUpdate(getPillId());
        onUpdateData(true);
        return;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public int getPillId() {
        return PillType.CACHE.getId();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public long getRefreshInterval() {
        return 180000;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pillStackSettingsChanged);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pillStackSettingsChanged);
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onPillClicked() {
        openCacheSettings();
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public boolean onPillLongClicked() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L6;
        ItemOptions.makeOptions(r0, this).add(R.drawable.msg2_data, LocaleController.getString(R.string.StorageUsage), new CachePill$$ExternalSyntheticLambda2(this)).addGap().add(R.drawable.msg_retry, LocaleController.getString(R.string.Refresh), new CachePill$$ExternalSyntheticLambda3(this)).add(R.drawable.msg_settings, LocaleController.getString(R.string.Settings), new CachePill$$ExternalSyntheticLambda4(r0)).setDrawScrim(false).setDimAlpha(0).show();
        return true;
    L6:
        return false;
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void onUpdateData(boolean r5) {
        if (lastKnownCacheSize.get() != (-1)) goto L5;
        boolean r0 = true;
    L6:
        if (r5 == true) goto L11;
        if (r0 == true) goto L11;
        if (isRefreshDue() == true) goto L11;
        return;
    L11:
        if (this.calculating.compareAndSet(false, true) == false) goto L18;
        if (r5 == true) goto L15;
        if (r0 == true) goto L15;
    L16:
        startLoading();
        ImageLoader.getInstance().checkMediaPaths(new CachePill$$ExternalSyntheticLambda0(this));
        return;
    L15:
        CacheControlActivity.resetCalculatedTotalSIze();
        goto L16
    L18:
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // com.exteragram.messenger.pillstack.ui.pills.BasePill
    public void updateColors() {
        int r0 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText, 0.75f);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        int r1 = getThemedColor(Theme.key_windowBackgroundWhite);
    L6:
        this.layout.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(14.0f), r1, Theme.multAlpha(r0, 0.1f)));
        this.textView.setTextColor(r0);
        this.progressDrawable.setColor(r0);
        updateLoadingColors();
        return;
    L5:
        r1 = Theme.multAlpha(r0, 0.09f);
        goto L6
    }
}
