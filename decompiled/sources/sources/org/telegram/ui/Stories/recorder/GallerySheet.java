package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes7.dex */
public class GallerySheet extends BottomSheet {
    private Boolean galleryListViewOpening;
    private ValueAnimator galleryOpenCloseAnimator;
    private SpringAnimation galleryOpenCloseSpringAnimator;
    private final GalleryListView listView;
    private Utilities.Callback<MediaController.PhotoEntry> onGalleryListener;

    /* renamed from: org.telegram.ui.Stories.recorder.GallerySheet$1, reason: invalid class name */
    public class AnonymousClass1 extends GalleryListView {
        final /* synthetic */ GallerySheet this$0;
        final /* synthetic */ String val$title;

        public AnonymousClass1(GallerySheet r1, int r2, Context r3, Theme.ResourcesProvider r4, MediaController.AlbumEntry r5, boolean r6, float r7, boolean r8, boolean r9, String r10) {
            this.this$0 = r1;
            this.val$title = r10;
            super(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        @Override // org.telegram.ui.Stories.recorder.GalleryListView
        public String getTitle() {
            return this.val$title;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GallerySheet$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ GallerySheet this$0;
        final /* synthetic */ Runnable val$done;

        public AnonymousClass2(GallerySheet r1, Runnable r2) {
            this.this$0 = r1;
            this.val$done = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            GallerySheet.m20042$$Nest$fputgalleryOpenCloseAnimator(this.this$0, null);
            GallerySheet.m20041$$Nest$fputgalleryListViewOpening(this.this$0, null);
            Runnable r1 = this.val$done;
            if (r1 == null) goto L6;
            r1.run();
            return;
        }
    }

    /* renamed from: $r8$lambda$2BEtMiN2oYlSt0-iUR3niWS788M, reason: not valid java name */
    public static /* synthetic */ void m20038$r8$lambda$2BEtMiN2oYlSt0iUR3niWS788M(GallerySheet r0, ValueAnimator r1) {
        r0.lambda$animate$4(r1);
    }

    /* renamed from: $r8$lambda$9S-6hkoMP7Hps2_tdM0G7Q7mQZ0, reason: not valid java name */
    public static /* synthetic */ void m20039$r8$lambda$9S6hkoMP7Hps2_tdM0G7Q7mQZ0(GallerySheet r0, float r1, Runnable r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        r0.lambda$animate$3(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$F4qURpsttEQ4jhDdqmX9leLh8Fk(GallerySheet r0, Object r1, Bitmap r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$QTBOD-C0-a1BJREOqYXpAEEr_vQ, reason: not valid java name */
    public static /* synthetic */ void m20040$r8$lambda$QTBODC0a1BJREOqYXpAEEr_vQ(GallerySheet r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$VtLKiMYqw2DQoremjpbrX1wZRTw(GallerySheet r0) {
        r0.lambda$dismiss$2();
    }

    /* renamed from: -$$Nest$fputgalleryListViewOpening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20041$$Nest$fputgalleryListViewOpening(GallerySheet r0, Boolean r1) {
        r0.galleryListViewOpening = r1;
    }

    /* renamed from: -$$Nest$fputgalleryOpenCloseAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20042$$Nest$fputgalleryOpenCloseAnimator(GallerySheet r0, ValueAnimator r1) {
        r0.galleryOpenCloseAnimator = r1;
    }

    public GallerySheet(Context r13, Theme.ResourcesProvider r14, String r15, boolean r16, float r17) {
        super(r13, false, r14);
        fixNavigationBar(-14737633);
        AnonymousClass1 r1 = new AnonymousClass1(this, UserConfig.selectedAccount, r13, new DarkThemeResourceProvider(), null, r16, r17, false, false, r15);
        this.listView = r1;
        r1.allowSearch(false);
        r1.setMultipleOnClick(false);
        r1.setOnBackClickListener(new GallerySheet$$ExternalSyntheticLambda0(this));
        r1.setOnSelectListener(new GallerySheet$$ExternalSyntheticLambda1(this));
        SizeNotifierFrameLayout r142 = new SizeNotifierFrameLayout(r13);
        this.containerView = r142;
        int r132 = this.backgroundPaddingLeft;
        r142.setPadding(r132, 0, r132, 0);
        this.containerView.addView(r1);
    }

    private void animate(boolean r5, Runnable r6) {
        float r0 = this.listView.getTranslationY();
        if (r5 == false) goto L5;
        float r1 = 0.0f;
    L6:
        this.galleryListViewOpening = Boolean.valueOf(r5);
        if (r5 == false) goto L10;
        SpringAnimation r52 = new SpringAnimation(this.listView, DynamicAnimation.TRANSLATION_Y, r1);
        this.galleryOpenCloseSpringAnimator = r52;
        r52.getSpring().setDampingRatio(0.75f);
        this.galleryOpenCloseSpringAnimator.getSpring().setStiffness(350.0f);
        this.galleryOpenCloseSpringAnimator.addEndListener(new GallerySheet$$ExternalSyntheticLambda2(this, r1, r6));
        this.galleryOpenCloseSpringAnimator.start();
        return;
    L10:
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r0, r1});
        this.galleryOpenCloseAnimator = r53;
        r53.addUpdateListener(new GallerySheet$$ExternalSyntheticLambda3(this));
        this.galleryOpenCloseAnimator.addListener(new AnonymousClass2(this, r6));
        this.galleryOpenCloseAnimator.setDuration(450);
        this.galleryOpenCloseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.galleryOpenCloseAnimator.start();
        return;
    L5:
        r1 = (this.containerView.getHeight() - this.listView.top()) + (AndroidUtilities.navigationBarHeight * 2.5f);
        goto L6
    }

    private /* synthetic */ void lambda$animate$3(float r1, Runnable r2, DynamicAnimation r3, boolean r4, float r5, float r6) {
        if (r4 == true) goto L9;
        this.listView.setTranslationY(r1);
        this.listView.ignoreScroll = false;
        this.galleryOpenCloseSpringAnimator = null;
        this.galleryListViewOpening = null;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$animate$4(ValueAnimator r1) {
        this.listView.setTranslationY(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$dismiss$2() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$new$0() {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(Object r1, Bitmap r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (this.galleryListViewOpening == null) goto L7;
        return;
    L7:
        if ((r1 instanceof MediaController.PhotoEntry) == false) goto L13;
        Utilities.Callback<MediaController.PhotoEntry> r0 = this.onGalleryListener;
        if (r0 == null) goto L14;
        r0.run((MediaController.PhotoEntry) r1);
        return;
    L14:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return !this.listView.actionBarShown;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        animate(false, new GallerySheet$$ExternalSyntheticLambda4(this));
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (r3.getAction() != 0) goto L9;
        if (r3.getY() >= this.listView.top()) goto L9;
        dismiss();
        return true;
    L9:
        return super.dispatchTouchEvent(r3);
    }

    public void setOnGalleryImage(Utilities.Callback<MediaController.PhotoEntry> r1) {
        this.onGalleryListener = r1;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        animate(true, null);
    }
}
