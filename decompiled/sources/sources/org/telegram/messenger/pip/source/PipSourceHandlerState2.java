package org.telegram.messenger.pip.source;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.media3.common.Player;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.pip.PipActivityController;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.PipSourceContentView;
import org.telegram.messenger.pip.activity.IPipActivityAnimationListener;
import org.telegram.messenger.pip.activity.IPipActivityListener;
import org.telegram.messenger.pip.utils.Trigger;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes5.dex */
public class PipSourceHandlerState2 implements IPipActivityListener, IPipActivityAnimationListener {
    private PipSourceSnapshot contentBackground;
    private PipSourceSnapshot contentForeground;
    private float lastProgress;
    private float lastRadius;
    private final Path path;
    public View pictureInPicturePlaceholderView;
    public View pictureInPictureView;
    private PipSourceContentView pictureInPictureWrapperView;
    private PipSourcePlaceholder pipSourcePlaceholder;
    public final Rect position;
    public final Rect positionSource;
    private final RectF rect;
    private boolean shouldBeAttached;
    private final PipSource source;
    private int state;

    public static /* synthetic */ void $r8$lambda$2ePjpj74bJmX64riKV1CKwKmSSE(PipSourceHandlerState2 r0) {
        r0.performDetach();
    }

    public static /* synthetic */ void $r8$lambda$4tl75OXwhrhJHAexkRxL73Rql_8(PipSourceHandlerState2 r0, boolean r1) {
        r0.lambda$performPreDetach2$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$TTSZ417R3PKlqASkqJgdtHQSqBc(PipSourceHandlerState2 r0) {
        r0.performAttach();
    }

    public static /* synthetic */ void $r8$lambda$UtcQfie2xb7zMrcc_IU35OrjEHY(PipSourceHandlerState2 r0, boolean r1) {
        r0.lambda$performPreAttach$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$WCq45mYC3CvQ5nveZPAMNxXMzWM(PipSourceHandlerState2 r0, boolean r1) {
        r0.lambda$performPreDetach1$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$Xu92nI7JDdH40_0IJIiXXMIe_Q0(PipSourceHandlerState2 r0, boolean r1) {
        r0.lambda$performAttach$1(r1);
    }

    /* renamed from: $r8$lambda$ZaKxMXStaSIm8f3x9SWNx3MBR-w, reason: not valid java name */
    public static /* synthetic */ void m5164$r8$lambda$ZaKxMXStaSIm8f3x9SWNx3MBRw(PipSourceHandlerState2 r0) {
        r0.performPreDetach2();
    }

    public static /* synthetic */ void $r8$lambda$nz2jHQlnOjEEfthMPfS8o8DoPaE(PipSourceHandlerState2 r0, boolean r1) {
        r0.lambda$performPreDetach2$3(r1);
    }

    public PipSourceHandlerState2(PipSource r2) {
        this.state = 0;
        this.positionSource = new Rect();
        this.position = new Rect();
        this.rect = new RectF();
        this.path = new Path();
        this.source = r2;
    }

    private void drawBackground(Canvas r4) {
        r4.drawColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), (int) Math.min(this.lastProgress * 420.0f, 255.0f)));
        this.contentBackground.draw(r4, 1.0f);
    }

    private void drawForeground(Canvas r3) {
        this.contentForeground.draw(r3, 1.0f - this.lastProgress);
    }

    private /* synthetic */ void lambda$performAttach$1(boolean r2) {
        this.pipSourcePlaceholder.stopPlaceholderForActivity();
        Log.i("PIP_DEBUG", "[HANDLER] on new source render first frame " + r2);
    }

    private /* synthetic */ void lambda$performPreAttach$0(boolean r1) {
        AndroidUtilities.runOnUIThread(new PipSourceHandlerState2$$ExternalSyntheticLambda4(this));
    }

    private /* synthetic */ void lambda$performPreDetach1$2(boolean r1) {
        AndroidUtilities.runOnUIThread(new PipSourceHandlerState2$$ExternalSyntheticLambda5(this));
    }

    private /* synthetic */ void lambda$performPreDetach2$3(boolean r3) {
        Log.i("PIP_DEBUG", "[HANDLER] on old source render first frame " + r3);
        PipSourcePlaceholder r2 = this.pipSourcePlaceholder;
        Objects.requireNonNull(r2);
        AndroidUtilities.runOnUIThread(new PipSourceHandlerState2$$ExternalSyntheticLambda9(r2));
    }

    private /* synthetic */ void lambda$performPreDetach2$4(boolean r1) {
        AndroidUtilities.runOnUIThread(new PipSourceHandlerState2$$ExternalSyntheticLambda10(this));
    }

    private void performAttach() {
        if (this.state == 1) goto L6;
        FileLog.e("[PIP_DEBUG] wrong pip state STATE_PRE_ATTACHED: " + this.state);
        return;
    L6:
        Log.i("PIP_DEBUG", "[HANDLER] attach");
        this.pipSourcePlaceholder.stopPlaceholderForSource();
        this.source.delegate.pipHidePrimaryWindowView(Trigger.run(new PipSourceHandlerState2$$ExternalSyntheticLambda6(this), 400));     // Catch: Throwable -> L12
    L8:
        this.state = 2;
        if (this.shouldBeAttached == true) goto L15;
        performPreDetach1();
        return;
    }

    private void performDetach() {
        if (this.state == 4) goto L6;
        FileLog.e("[PIP_DEBUG] wrong pip state STATE_PRE_DETACHED_2: " + this.state);
        return;
    L6:
        this.source.controller.getPipContentView().removeView(this.pictureInPictureWrapperView);
        this.pictureInPictureView = null;
        this.pictureInPictureWrapperView = null;
        this.pictureInPicturePlaceholderView = null;
        PipSourceSnapshot r1 = this.contentForeground;
        if (r1 == null) goto L9;
        r1.release();
        this.contentForeground = null;
    L9:
        PipSourceSnapshot r12 = this.contentBackground;
        if (r12 == null) goto L12;
        r12.release();
        this.contentBackground = null;
    L12:
        this.pipSourcePlaceholder.stopPlaceholderForActivity();
        this.state = 0;
        Log.i("PIP_DEBUG", "[HANDLER] detach");
        if (this.shouldBeAttached == false) goto L16;
        performPreAttach();
        return;
    }

    private void performPreAttach() {
        if (this.state == 0) goto L6;
        FileLog.e("[PIP_DEBUG] wrong pip state STATE_DETACHED: " + this.state);
        return;
    L6:
        this.source.params.getPosition(this.positionSource);
        Log.i("PIP_DEBUG", "[HANDLER] pre attach start " + this.positionSource);
        int r0 = this.source.controller.activity.getWindow().getDecorView().getMeasuredWidth();
        int r2 = this.source.controller.activity.getWindow().getDecorView().getMeasuredHeight();
        Bitmap r3 = this.source.delegate.pipCreatePrimaryWindowViewBitmap();
        IPipSourceDelegate r5 = this.source.delegate;
        Objects.requireNonNull(r5);
        this.contentBackground = new PipSourceSnapshot(r0, r2, new PipSourceHandlerState2$$ExternalSyntheticLambda1(r5));
        IPipSourceDelegate r52 = this.source.delegate;
        Objects.requireNonNull(r52);
        this.contentForeground = new PipSourceSnapshot(r0, r2, new PipSourceHandlerState2$$ExternalSyntheticLambda2(r52));
        this.pictureInPictureView = this.source.delegate.pipCreatePictureInPictureView();
        this.pictureInPicturePlaceholderView = new View(this.source.controller.activity);
        PipSourceContentView r02 = new PipSourceContentView(this.source.controller.activity, this);
        this.pictureInPictureWrapperView = r02;
        r02.addView(this.pictureInPicturePlaceholderView);
        this.pictureInPictureWrapperView.addView(this.pictureInPictureView);
        PipSourcePlaceholder r03 = new PipSourcePlaceholder(this.pictureInPicturePlaceholderView, this.source.placeholderView);
        this.pipSourcePlaceholder = r03;
        r03.setPlaceholder(r3);
        this.source.controller.getPipContentView().addView(this.pictureInPictureWrapperView);
        this.state = 1;
        this.pictureInPictureWrapperView.invalidate();
        AndroidUtilities.doOnPreDraw(this.pictureInPictureView, Trigger.run(new PipSourceHandlerState2$$ExternalSyntheticLambda3(this), 300));
        Log.i("PIP_DEBUG", "[HANDLER] pre attach end");
    }

    private void performPreDetach1() {
        if (this.state == 2) goto L6;
        FileLog.e("[PIP_DEBUG] wrong pip state STATE_ATTACHED: " + this.state);
        return;
    L6:
        this.pipSourcePlaceholder.setPlaceholder(this.source.delegate.pipCreatePictureInPictureViewBitmap());
        this.state = 3;
        this.pictureInPictureWrapperView.removeView(this.pictureInPictureView);
        this.pictureInPictureWrapperView.invalidate();
        this.pictureInPictureView = null;
        AndroidUtilities.doOnPreDraw(this.pictureInPictureWrapperView, Trigger.run(new PipSourceHandlerState2$$ExternalSyntheticLambda0(this), 300));
        Log.i("PIP_DEBUG", "[HANDLER] pre detach 1");
    }

    private void performPreDetach2() {
        if (this.state == 3) goto L6;
        FileLog.e("[PIP_DEBUG] wrong pip state STATE_PRE_DETACHED_1: " + this.state);
        return;
    L6:
        this.source.delegate.pipShowPrimaryWindowView(Trigger.run(new PipSourceHandlerState2$$ExternalSyntheticLambda7(this), 400));
        this.pictureInPictureWrapperView.invalidate();
        this.state = 4;
        AndroidUtilities.doOnPreDraw(this.source.contentView, Trigger.run(new PipSourceHandlerState2$$ExternalSyntheticLambda8(this), 300));
        Log.i("PIP_DEBUG", "[HANDLER] pre detach 2");
    }

    private void rebuildPath(float r4) {
        if (this.lastRadius != r4) goto L5;
        return;
    L5:
        this.lastRadius = r4;
        this.rect.set(this.position);
        this.path.reset();
        this.path.addRoundRect(this.rect, r4, r4, Path.Direction.CW);
        this.path.close();
    }

    public void draw(Canvas r4, Utilities.Callback<Canvas> r5) {
        float r0 = this.source.cornerRadius * (1.0f - this.lastProgress);
        if (r0 <= 1.0f) goto L5;
        boolean r1 = true;
    L6:
        drawBackground(r4);
        if (r1 == false) goto L9;
        rebuildPath(r0);
        r4.save();
        r4.clipPath(this.path);
    L9:
        r5.run(r4);
        drawForeground(r4);
        if (r1 == false) goto L13;
        r4.restore();
        return;
    L13:
        return;
    L5:
        r1 = false;
        goto L6
    }

    public boolean isAttachedToPip() {
        if (this.state == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityListener
    public void onCompleteExitFromPip(boolean r1) {
        this.shouldBeAttached = false;
        performPreDetach1();
    }

    public void onLoseMaxPriority() {
        this.shouldBeAttached = false;
        performPreDetach1();
        this.source.controller.removePipListener(this);
        this.source.controller.removeAnimationListener(this);
        PipSource r2 = this.source;
        PipActivityController r0 = r2.controller;
        String r1 = r2.tag;
        r2.getClass();
        r0.removeActionListener(r1, null);
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityListener
    public void onPipStashEnd() {
        PipSource r0 = this.source;
        if (r0 == null) goto L8;
        Player r02 = r0.getPlayer();
        if (r02 == null) goto L9;
        r02.play();
        return;
    L9:
        return;
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityListener
    public void onPipStashStart() {
        PipSource r0 = this.source;
        if (r0 == null) goto L8;
        Player r02 = r0.getPlayer();
        if (r02 == null) goto L9;
        r02.pause();
        return;
    L9:
        return;
    }

    public void onReceiveMaxPriority() {
        this.source.controller.addPipListener(this);
        this.source.controller.addAnimationListener(this);
        PipSource r2 = this.source;
        PipActivityController r0 = r2.controller;
        String r1 = r2.tag;
        r2.getClass();
        r0.addActionListener(r1, null);
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityListener
    public void onStartEnterToPip() {
        this.shouldBeAttached = true;
        performPreAttach();
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
    public void onTransitionAnimationFrame() {
        PipSourceContentView r0 = this.pictureInPictureWrapperView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // org.telegram.messenger.pip.activity.IPipActivityAnimationListener
    public void onTransitionAnimationProgress(float r1) {
        this.lastProgress = r1;
        PipSourceContentView r0 = this.pictureInPictureWrapperView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void updatePositionViewRect(int r2, int r3, boolean r4) {
        Rect r0 = this.position;
        if (r4 == false) goto L6;
        r0.set(0, 0, r2, r3);
        return;
    L6:
        r0.set(this.positionSource);
    }
}
