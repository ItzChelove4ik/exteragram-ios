package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.CameraType;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.ImageReceiver;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.utils.settings.SharedSettings;

/* loaded from: classes7.dex */
public abstract class InstantCameraViewBase extends FrameLayout {
    private AnimationCallback animationCallback;
    private RecordingUiFrameCallback recordingUiFrameCallback;
    private boolean recordingUiFrameClockActive;
    private TrimCallback trimCallback;

    public interface AnimationCallback {
        void onAnimation(boolean r1, boolean r2);
    }

    public static abstract class InstantViewCameraContainer extends FrameLayout {
        public InstantViewCameraContainer(Context r1) {
            super(r1);
        }

        public abstract void setImageReceiver(ImageReceiver r1);
    }

    public interface RecordingUiFrameCallback {
        void onActiveChanged(boolean r1);

        void onFrame(long r1);
    }

    public interface TrimCallback {
        void onTrimChanged(float r1, float r2);
    }

    public InstantCameraViewBase(Context r1) {
        super(r1);
    }

    public static InstantCameraViewBase create(Context r2, InstantCameraView.Delegate r3, Theme.ResourcesProvider r4, boolean r5) {
        if (SharedSettings.roundVideoCamera2Enabled.get() == false) goto L9;
        if (ExteraConfig.getCameraType() != CameraType.CAMERA_2) goto L9;
        return new InstantCameraView2(r2, r3, r4, r5);
    L9:
        return new InstantCameraView(r2, r3, r4, r5);
    }

    public static void setUseCamera2Implementation(boolean r1) {
        SharedSettings.roundVideoCamera2Enabled.set(r1);
    }

    public abstract void cancel(boolean r1);

    public abstract void changeVideoPreviewState(int r1, float r2);

    public final void dispatchAnimationState(boolean r1, boolean r2) {
        AnimationCallback r0 = this.animationCallback;
        if (r0 == null) goto L6;
        r0.onAnimation(r1, r2);
        return;
    }

    public final void dispatchRecordingUiFrame(long r1) {
        RecordingUiFrameCallback r0 = this.recordingUiFrameCallback;
        if (r0 == null) goto L6;
        r0.onFrame(r1);
        return;
    }

    public final void dispatchRecordingUiFrameClockActive(boolean r2) {
        if (this.recordingUiFrameClockActive == r2) goto L10;
        this.recordingUiFrameClockActive = r2;
        RecordingUiFrameCallback r1 = this.recordingUiFrameCallback;
        if (r1 == null) goto L9;
        r1.onActiveChanged(r2);
        return;
    L9:
        return;
    }

    public final void dispatchTrimRange(float r1, float r2) {
        TrimCallback r0 = this.trimCallback;
        if (r0 == null) goto L6;
        r0.onTrimChanged(r1, r2);
        return;
    }

    public abstract View getButtonsLayout();

    public abstract InstantViewCameraContainer getCameraContainer();

    public abstract RectF getCameraRect();

    public abstract View getMuteImageView();

    public abstract Paint getPaint();

    public abstract TextureView getTextureView();

    public abstract void hideCamera(boolean r1);

    public abstract boolean isPaused();

    public abstract void onPanTranslationUpdate(float r1);

    public abstract void send(int r1, boolean r2, int r3, int r4, int r5, long r6, long r8);

    public final void setAnimationCallback(AnimationCallback r1) {
        this.animationCallback = r1;
    }

    public abstract void setButtonsBackground(BlurredBackgroundDrawableViewFactory r1, BlurredBackgroundColorProvider r2);

    public abstract void setInternalPadding(int r1);

    public abstract void setIsMessageTransition(boolean r1);

    public final void setRecordingUiFrameCallback(RecordingUiFrameCallback r1) {
        this.recordingUiFrameCallback = r1;
        if (r1 == null) goto L6;
        r1.onActiveChanged(this.recordingUiFrameClockActive);
        return;
    }

    public final void setTrimCallback(TrimCallback r1) {
        this.trimCallback = r1;
    }

    public abstract void showCamera(boolean r1);

    public abstract void togglePause();
}
