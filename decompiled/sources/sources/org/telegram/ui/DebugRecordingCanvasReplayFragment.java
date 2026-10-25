package org.telegram.ui;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Trace;
import android.view.Choreographer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.DebugRecordingCanvas;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;

/* loaded from: classes6.dex */
public class DebugRecordingCanvasReplayFragment extends BaseFragment {
    private FrameLayout contentView;
    private int currentFrame;
    private final DebugRecordingCanvas debugRecordingCanvas;
    private final Choreographer.FrameCallback frameCallback;
    private int framesCount;
    private boolean isPlaying;
    private ImageButton playButton;
    private View replayView;
    private SeekBarView seekBarView;

    /* renamed from: org.telegram.ui.DebugRecordingCanvasReplayFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements Choreographer.FrameCallback {
        final /* synthetic */ DebugRecordingCanvasReplayFragment this$0;

        public AnonymousClass1(DebugRecordingCanvasReplayFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long r2) {
            if (DebugRecordingCanvasReplayFragment.m14263$$Nest$fgetisPlaying(this.this$0) == true) goto L5;
            return;
        L5:
            DebugRecordingCanvasReplayFragment r22 = this.this$0;
            DebugRecordingCanvasReplayFragment.m14266$$Nest$fputcurrentFrame(r22, DebugRecordingCanvasReplayFragment.m14260$$Nest$fgetcurrentFrame(r22) + 1);
            if (DebugRecordingCanvasReplayFragment.m14260$$Nest$fgetcurrentFrame(this.this$0) <= DebugRecordingCanvasReplayFragment.m14262$$Nest$fgetframesCount(this.this$0)) goto L8;
            DebugRecordingCanvasReplayFragment.m14266$$Nest$fputcurrentFrame(this.this$0, 0);
            DebugRecordingCanvasReplayFragment.m14267$$Nest$fputisPlaying(this.this$0, false);
        L8:
            DebugRecordingCanvasReplayFragment.m14265$$Nest$fgetseekBarView(this.this$0).setProgress(DebugRecordingCanvasReplayFragment.m14260$$Nest$fgetcurrentFrame(this.this$0) / DebugRecordingCanvasReplayFragment.m14262$$Nest$fgetframesCount(this.this$0));
            DebugRecordingCanvasReplayFragment.m14264$$Nest$fgetreplayView(this.this$0).invalidate();
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    /* renamed from: org.telegram.ui.DebugRecordingCanvasReplayFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ DebugRecordingCanvasReplayFragment this$0;

        public AnonymousClass2(DebugRecordingCanvasReplayFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r4) {
            Trace.beginSection("render_" + DebugRecordingCanvasReplayFragment.m14260$$Nest$fgetcurrentFrame(this.this$0) + "_" + DebugRecordingCanvasReplayFragment.m14262$$Nest$fgetframesCount(this.this$0));
            super.onDraw(r4);
            if (DebugRecordingCanvasReplayFragment.m14263$$Nest$fgetisPlaying(this.this$0) == true) goto L5;
            invalidate();
        L5:
            int r0 = DebugRecordingCanvasReplayFragment.m14260$$Nest$fgetcurrentFrame(this.this$0);
            int r1 = DebugRecordingCanvasReplayFragment.m14262$$Nest$fgetframesCount(this.this$0);
            DebugRecordingCanvasReplayFragment r2 = this.this$0;
            if (r0 != r1) goto L8;
            DebugRecordingCanvasReplayFragment.m14261$$Nest$fgetdebugRecordingCanvas(r2).replayAll(r4);
        L9:
            Trace.endSection();
            return;
        L8:
            DebugRecordingCanvasReplayFragment.m14261$$Nest$fgetdebugRecordingCanvas(r2).replayCommands(r4, DebugRecordingCanvasReplayFragment.m14260$$Nest$fgetcurrentFrame(this.this$0));
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.DebugRecordingCanvasReplayFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ DebugRecordingCanvasReplayFragment this$0;

        public AnonymousClass3(DebugRecordingCanvasReplayFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r2, float r3) {
            DebugRecordingCanvasReplayFragment.m14268$$Nest$mstopPlayback(this.this$0);
            DebugRecordingCanvasReplayFragment.m14266$$Nest$fputcurrentFrame(this.this$0, Math.round(DebugRecordingCanvasReplayFragment.m14262$$Nest$fgetframesCount(r2) * r3));
            DebugRecordingCanvasReplayFragment.m14264$$Nest$fgetreplayView(this.this$0).invalidate();
        }
    }

    /* renamed from: $r8$lambda$DHAROo8J2oBJ7BWi2E-KOqa9og8, reason: not valid java name */
    public static /* synthetic */ void m14259$r8$lambda$DHAROo8J2oBJ7BWi2EKOqa9og8(DebugRecordingCanvasReplayFragment r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14260$$Nest$fgetcurrentFrame(DebugRecordingCanvasReplayFragment r0) {
        return r0.currentFrame;
    }

    /* renamed from: -$$Nest$fgetdebugRecordingCanvas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DebugRecordingCanvas m14261$$Nest$fgetdebugRecordingCanvas(DebugRecordingCanvasReplayFragment r0) {
        return r0.debugRecordingCanvas;
    }

    /* renamed from: -$$Nest$fgetframesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14262$$Nest$fgetframesCount(DebugRecordingCanvasReplayFragment r0) {
        return r0.framesCount;
    }

    /* renamed from: -$$Nest$fgetisPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14263$$Nest$fgetisPlaying(DebugRecordingCanvasReplayFragment r0) {
        return r0.isPlaying;
    }

    /* renamed from: -$$Nest$fgetreplayView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m14264$$Nest$fgetreplayView(DebugRecordingCanvasReplayFragment r0) {
        return r0.replayView;
    }

    /* renamed from: -$$Nest$fgetseekBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SeekBarView m14265$$Nest$fgetseekBarView(DebugRecordingCanvasReplayFragment r0) {
        return r0.seekBarView;
    }

    /* renamed from: -$$Nest$fputcurrentFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14266$$Nest$fputcurrentFrame(DebugRecordingCanvasReplayFragment r0, int r1) {
        r0.currentFrame = r1;
    }

    /* renamed from: -$$Nest$fputisPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14267$$Nest$fputisPlaying(DebugRecordingCanvasReplayFragment r0, boolean r1) {
        r0.isPlaying = r1;
    }

    /* renamed from: -$$Nest$mstopPlayback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14268$$Nest$mstopPlayback(DebugRecordingCanvasReplayFragment r0) {
        r0.stopPlayback();
    }

    public DebugRecordingCanvasReplayFragment(DebugRecordingCanvas r3) {
        this.currentFrame = 0;
        this.isPlaying = false;
        this.frameCallback = new AnonymousClass1(this);
        this.debugRecordingCanvas = r3;
        this.framesCount = r3.getCommandCount();
        this.currentFrame = 0;
        this.hasOwnBackground = true;
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        togglePlayback();
    }

    private void startPlayback() {
        this.isPlaying = true;
        this.currentFrame = 0;
        updatePlayButtonIcon();
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
    }

    private void stopPlayback() {
        this.isPlaying = false;
        Choreographer.getInstance().removeFrameCallback(this.frameCallback);
        updatePlayButtonIcon();
    }

    private void togglePlayback() {
        if (this.isPlaying == false) goto L6;
        stopPlayback();
        return;
    L6:
        startPlayback();
    }

    private void updatePlayButtonIcon() {
        ImageButton r0 = this.playButton;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.isPlaying == false) goto L8;
        int r1 = R.drawable.ic_media_pause;
    L9:
        r0.setImageResource(r1);
        return;
    L8:
        r1 = R.drawable.ic_media_play;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r1) {
        ActionBar r0 = super.createActionBar(r1);
        r0.setAddToContainer(false);
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r9) {
        FrameLayout r0 = new FrameLayout(r9);
        this.contentView = r0;
        this.fragmentView = r0;
        AnonymousClass2 r02 = new AnonymousClass2(this, r9);
        this.replayView = r02;
        this.contentView.addView(r02, LayoutHelper.createFrameMatchParent());
        this.playButton = new ImageButton(r9);
        updatePlayButtonIcon();
        this.playButton.setBackgroundColor(0);
        this.playButton.setOnClickListener(new DebugRecordingCanvasReplayFragment$$ExternalSyntheticLambda0(this));
        SeekBarView r03 = new SeekBarView(r9);
        this.seekBarView = r03;
        r03.setReportChanges(true);
        this.seekBarView.setDelegate(new AnonymousClass3(this));
        this.seekBarView.setProgress(this.currentFrame / this.framesCount);
        FrameLayout r04 = new FrameLayout(r9);
        r04.addView(this.playButton, LayoutHelper.createFrame(38, 38.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
        r04.addView(this.seekBarView, LayoutHelper.createFrame(-1, 38.0f, 16, 46.0f, 0.0f, 0.0f, 0.0f));
        this.contentView.addView(r04, LayoutHelper.createFrame(-1, 38.0f, 80, 16.0f, 0.0f, 16.0f, 16.0f));
        r04.setTranslationY(-AndroidUtilities.navigationBarHeight);
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        stopPlayback();
    }
}
