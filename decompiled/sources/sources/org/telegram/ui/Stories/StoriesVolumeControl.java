package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioManager;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes7.dex */
public class StoriesVolumeControl extends View {
    float currentProgress;
    Runnable hideRunnable;
    boolean isVisible;
    Paint paint;
    AnimatedFloat progressToVisible;
    AnimatedFloat volumeProgress;

    /* renamed from: org.telegram.ui.Stories.StoriesVolumeControl$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ StoriesVolumeControl this$0;

        public AnonymousClass1(StoriesVolumeControl r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            StoriesVolumeControl r1 = this.this$0;
            r1.isVisible = false;
            r1.invalidate();
        }
    }

    public StoriesVolumeControl(Context r2) {
        super(r2);
        this.paint = new Paint(1);
        this.hideRunnable = new AnonymousClass1(this);
        this.progressToVisible = new AnimatedFloat(this);
        this.volumeProgress = new AnimatedFloat(this);
        this.paint.setColor(-1);
    }

    private void adjustVolume(boolean r8) {
        AudioManager r0 = (AudioManager) getContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        int r2 = r0.getStreamMaxVolume(3);
        int r3 = r0.getStreamVolume(3);
        float r4 = r2;
        int r5 = (int) Math.max(1.0f, r4 / 15.0f);
        if (r8 == false) goto L8;
        int r32 = r3 + r5;
        if (r32 > r2) goto L11;
        r2 = r32;
    L11:
        r0.setStreamVolume(3, r2, 0);
        float r82 = r2 / r4;
        this.currentProgress = r82;
        if (this.isVisible == true) goto L14;
        this.volumeProgress.set(r82, true);
    L14:
        invalidate();
        this.isVisible = true;
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        AndroidUtilities.runOnUIThread(this.hideRunnable, 2000);
        return;
    L8:
        r2 = r3 - r5;
        if (r2 >= 0) goto L11;
        r2 = 0;
        goto L11
    }

    public void hide() {
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        this.hideRunnable.run();
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        super.onDraw(r6);
        this.volumeProgress.set(this.currentProgress);
        AnimatedFloat r0 = this.progressToVisible;
        if (this.isVisible == false) goto L5;
        float r1 = 1.0f;
    L6:
        r0.set(r1);
        if (this.progressToVisible.get() == 0.0f) goto L10;
        float r02 = getMeasuredHeight() / 2.0f;
        this.paint.setAlpha((int) (this.progressToVisible.get() * 255.0f));
        RectF r12 = AndroidUtilities.rectTmp;
        r12.set(0.0f, 0.0f, getMeasuredWidth() * this.volumeProgress.get(), getMeasuredHeight());
        r6.drawRoundRect(r12, r02, r02, this.paint);
        return;
    L10:
        return;
    L5:
        r1 = 0.0f;
        goto L6
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r3, KeyEvent r4) {
        if (r4.getAction() != 0) goto L9;
        if (r3 != 24) goto L9;
        adjustVolume(true);
        return true;
    L9:
        if (r4.getAction() != 0) goto L15;
        if (r3 != 25) goto L15;
        adjustVolume(false);
        return true;
    L15:
        return super.onKeyDown(r3, r4);
    }

    public void unmute() {
        AudioManager r0 = (AudioManager) getContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        int r2 = r0.getStreamMaxVolume(3);
        if (Build.VERSION.SDK_INT < 28) goto L5;
        int r3 = r0.getStreamMinVolume(3);
    L6:
        int r02 = r0.getStreamVolume(3);
        if (r02 > r3) goto L11;
        adjustVolume(true);
        return;
    L11:
        if (this.isVisible == true) goto L14;
        float r03 = r02 / r2;
        this.currentProgress = r03;
        this.volumeProgress.set(r03, true);
        this.isVisible = true;
        invalidate();
        AndroidUtilities.cancelRunOnUIThread(this.hideRunnable);
        AndroidUtilities.runOnUIThread(this.hideRunnable, 2000);
        return;
    L14:
        return;
    L5:
        r3 = 0;
        goto L6
    }
}
