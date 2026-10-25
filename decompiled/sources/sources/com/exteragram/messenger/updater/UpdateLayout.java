package com.exteragram.messenger.updater;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.IUpdateLayout;

/* loaded from: classes.dex */
public class UpdateLayout extends IUpdateLayout {
    private final Activity activity;
    private final ViewGroup sideMenuContainer;
    private FrameLayout updateLayout;
    private RadialProgress2 updateLayoutIcon;
    private AnimatedTextView.AnimatedTextDrawable updateSizeTextView;
    private AnimatedTextView updateTextView;

    /* renamed from: com.exteragram.messenger.updater.UpdateLayout$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 extends AnimatedTextView {
        final /* synthetic */ UpdateLayout this$0;

        public AnonymousClass1(UpdateLayout r1, Context r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
        public void onDraw(Canvas r5) {
            UpdateLayout.m1759$$Nest$fgetupdateSizeTextView(this.this$0).setBounds(0, 0, getMeasuredWidth() - AndroidUtilities.dp(20.0f), getMeasuredHeight());
            UpdateLayout.m1759$$Nest$fgetupdateSizeTextView(this.this$0).draw(r5);
            r5.save();
            r5.translate(AndroidUtilities.dp(15.0f), 0.0f);
            super.onDraw(r5);
            r5.translate(((getMeasuredWidth() - width()) / 2.0f) - AndroidUtilities.dp(30.0f), AndroidUtilities.dp(11.0f));
            UpdateLayout.m1758$$Nest$fgetupdateLayoutIcon(this.this$0).draw(r5);
            r5.restore();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (super.verifyDrawable(r2) == false) goto L5;
            return true;
        L5:
            if (r2 == UpdateLayout.m1759$$Nest$fgetupdateSizeTextView(this.this$0)) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: com.exteragram.messenger.updater.UpdateLayout$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ UpdateLayout this$0;

        public AnonymousClass2(UpdateLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (UpdateLayout.m1757$$Nest$fgetupdateLayout(this.this$0).getTag() != null) goto L6;
            UpdateLayout.m1757$$Nest$fgetupdateLayout(this.this$0).setVisibility(4);
            return;
        }
    }

    /* renamed from: $r8$lambda$-ihnw8KsDlwydN4wKZ2Q--wjQMg, reason: not valid java name */
    public static /* synthetic */ void m1756$r8$lambda$ihnw8KsDlwydN4wKZ2QwjQMg(UpdateLayout r0, int r1, View r2) {
        r0.lambda$createUpdateUI$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m1757$$Nest$fgetupdateLayout(UpdateLayout r0) {
        return r0.updateLayout;
    }

    /* renamed from: -$$Nest$fgetupdateLayoutIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgress2 m1758$$Nest$fgetupdateLayoutIcon(UpdateLayout r0) {
        return r0.updateLayoutIcon;
    }

    /* renamed from: -$$Nest$fgetupdateSizeTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView.AnimatedTextDrawable m1759$$Nest$fgetupdateSizeTextView(UpdateLayout r0) {
        return r0.updateSizeTextView;
    }

    public UpdateLayout(Activity r1, ViewGroup r2) {
        super(r1, r2);
        this.activity = r1;
        this.sideMenuContainer = r2;
    }

    private /* synthetic */ void lambda$createUpdateUI$0(int r4, View r5) {
        if (SharedConfig.isAppUpdateAvailable() == true) goto L6;
        return;
    L6:
        if (this.updateLayoutIcon.getIcon() != 2) goto L10;
        FileLoader.getInstance(r4).loadFile(SharedConfig.pendingAppUpdate.document, PluginsConstants.UPDATE, 1, 1);
        updateAppUpdateViews(r4, true);
        return;
    L10:
        if (this.updateLayoutIcon.getIcon() != 3) goto L13;
        FileLoader.getInstance(r4).cancelLoadFile(SharedConfig.pendingAppUpdate.document);
        updateAppUpdateViews(r4, true);
        return;
    L13:
        AndroidUtilities.openForView(SharedConfig.pendingAppUpdate.document, this.activity);
    }

    private void setUpdateText(String r1, boolean r2) {
        this.updateTextView.setText(r1, r2);
    }

    @Override // org.telegram.ui.IUpdateLayout
    public void createUpdateUI(int r10) {
        if (this.sideMenuContainer != null) goto L5;
        return;
    L5:
        if (this.updateLayout != null) goto L10;
        FrameLayout r0 = new FrameLayout(this.activity);
        this.updateLayout = r0;
        r0.setVisibility(4);
        this.updateLayout.setTranslationY(AndroidUtilities.dp(44.0f));
        this.updateLayout.setBackground(Theme.getSelectorDrawable(1090519039, false));
        this.sideMenuContainer.addView(this.updateLayout, LayoutHelper.createFrame(-1, 44, 83));
        this.updateLayout.setOnClickListener(new UpdateLayout$$ExternalSyntheticLambda0(this, r10));
        AnonymousClass1 r3 = new AnonymousClass1(this, this.activity, true, true, true);
        this.updateTextView = r3;
        r3.setTextSize(AndroidUtilities.dp(15.0f));
        this.updateTextView.setTypeface(AndroidUtilities.bold());
        int r9 = Theme.getColor(Theme.key_featuredStickers_buttonText);
        this.updateTextView.setTextColor(r9);
        this.updateTextView.setGravity(17);
        this.updateLayout.addView(this.updateTextView, LayoutHelper.createFrameMatchParent());
        this.updateTextView.setText(LocaleController.getString(R.string.AppUpdateBeta), false);
        RadialProgress2 r102 = new RadialProgress2(this.updateTextView);
        this.updateLayoutIcon = r102;
        int r02 = Theme.key_featuredStickers_addButton;
        r102.setColors(r9, r9, Theme.getColor(r02), Theme.getColor(r02));
        this.updateLayoutIcon.setProgressRect(0, 0, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f));
        this.updateLayoutIcon.setCircleRadius(AndroidUtilities.dp(11.0f));
        this.updateLayoutIcon.setAsMini();
        AnimatedTextView.AnimatedTextDrawable r103 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
        this.updateSizeTextView = r103;
        r103.setCallback(this.updateTextView);
        this.updateSizeTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.updateSizeTextView.setTypeface(AndroidUtilities.bold());
        this.updateSizeTextView.setGravity(21);
        this.updateSizeTextView.setTextColor(Theme.multAlpha(r9, 0.75f));
        return;
    }

    @Override // org.telegram.ui.IUpdateLayout
    public void updateAppUpdateViews(int r11, boolean r12) {
        if (this.sideMenuContainer != null) goto L6;
        return;
    L6:
        if (SharedConfig.isAppUpdateAvailable() == false) goto L32;
        createUpdateUI(r11);
        String r0 = FileLoader.getAttachFileName(SharedConfig.pendingAppUpdate.document);
        if (FileLoader.getInstance(r11).getPathToAttach(SharedConfig.pendingAppUpdate.document, true).exists() == false) goto L11;
        this.updateLayoutIcon.setIcon(15, true, r12);
        setUpdateText(LocaleController.getString(R.string.AppUpdateNow), r12);
    L10:
        boolean r112 = false;
    L19:
        AnimatedTextView.AnimatedTextDrawable r02 = this.updateSizeTextView;
        if (r112 == false) goto L22;
        String r113 = AndroidUtilities.formatFileSize(SharedConfig.pendingAppUpdate.document.size);
    L23:
        r02.setText(r113, r12);
        if (this.updateLayout.getTag() != null) goto L45;
        this.updateLayout.setVisibility(0);
        this.updateLayout.setTag(1);
        FrameLayout r10 = this.updateLayout;
        if (r12 == false) goto L30;
        r10.animate().translationY(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT).setListener(null).setDuration(180).start();
        return;
    L30:
        r10.setTranslationY(0.0f);
        return;
    L45:
        return;
    L22:
        r113 = null;
        goto L23
    L11:
        boolean r114 = FileLoader.getInstance(r11).isLoadingFile(r0);
        RadialProgress2 r4 = this.updateLayoutIcon;
        if (r114 == false) goto L18;
        r4.setIcon(3, true, r12);
        this.updateLayoutIcon.setProgress(0.0f, false);
        Float r115 = ImageLoader.getInstance().getFileProgress(r0);
        int r03 = R.string.AppUpdateDownloading;
        if (r115 == null) goto L16;
        float r116 = r115.floatValue();
    L17:
        setUpdateText(LocaleController.formatString(r03, new Object[]{Integer.valueOf((int) (r116 * 100.0f))}), r12);
        goto L10
    L16:
        r116 = 0.0f;
        goto L17
    L18:
        r4.setIcon(2, true, r12);
        setUpdateText(LocaleController.getString(R.string.AppUpdate), r12);
        r112 = true;
        goto L19
    L32:
        FrameLayout r117 = this.updateLayout;
        if (r117 != null) goto L35;
        return;
    L35:
        if (r117.getTag() == null) goto L46;
        this.updateLayout.setTag(null);
        FrameLayout r118 = this.updateLayout;
        if (r12 == false) goto L41;
        r118.animate().translationY(AndroidUtilities.dp(44.0f)).setInterpolator(CubicBezierInterpolator.EASE_OUT).setListener(new AnonymousClass2(this)).setDuration(180).start();
        return;
    L41:
        r118.setTranslationY(AndroidUtilities.dp(44.0f));
        this.updateLayout.setVisibility(4);
        return;
    }

    @Override // org.telegram.ui.IUpdateLayout
    public void updateFileProgress(Object[] r5) {
        if (this.updateTextView == null) goto L14;
        if (r5 != null) goto L7;
        return;
    L7:
        if (SharedConfig.isAppUpdateAvailable() == false) goto L15;
        String r0 = (String) r5[0];
        String r1 = FileLoader.getAttachFileName(SharedConfig.pendingAppUpdate.document);
        if (r1 != null) goto L11;
        return;
    L11:
        if (r1.equals(r0) == false) goto L17;
        Long r12 = (Long) r5[1];
        Long r52 = (Long) r5[2];
        float r13 = r12.longValue() / r52.longValue();
        this.updateLayoutIcon.setProgress(r13, true);
        this.updateTextView.setText(LocaleController.formatString(R.string.AppUpdateDownloading, new Object[]{Integer.valueOf((int) (r13 * 100.0f))}));
        return;
    L17:
        return;
    L15:
        return;
    }
}
