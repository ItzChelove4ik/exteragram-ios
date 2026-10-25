package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public class VideoEditTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    private VideoPlayer currentVideoPlayer;
    private VideoEditTextureViewDelegate delegate;
    private FilterGLThread eglThread;
    private int gradientBottom;
    private int gradientTop;
    public StoryEntry.HDRInfo hdrInfo;
    private BlurringShader.BlurManager uiBlurManager;
    private int videoHeight;
    private int videoWidth;
    private RectOld viewRect;

    public interface VideoEditTextureViewDelegate {
        void onEGLThreadAvailable(FilterGLThread r1);
    }

    public static /* synthetic */ void $r8$lambda$_qbpWZyLGo5d4ZvINhYTDNG3_kU(VideoEditTextureView r0) {
        r0.lambda$onSurfaceTextureSizeChanged$1();
    }

    public static /* synthetic */ void $r8$lambda$kpI0GSMx1TrqdQHaLZudJkEC9sg(VideoEditTextureView r0, SurfaceTexture r1) {
        r0.lambda$onSurfaceTextureAvailable$0(r1);
    }

    public VideoEditTextureView(Context r1, VideoPlayer r2) {
        super(r1);
        this.viewRect = new RectOld();
        this.currentVideoPlayer = r2;
        setSurfaceTextureListener(this);
    }

    private /* synthetic */ void lambda$onSurfaceTextureAvailable$0(SurfaceTexture r2) {
        if (this.currentVideoPlayer != null) goto L5;
        return;
    L5:
        Surface r0 = new Surface(r2);
        this.currentVideoPlayer.setSurface(r0);
    }

    private /* synthetic */ void lambda$onSurfaceTextureSizeChanged$1() {
        FilterGLThread r2 = this.eglThread;
        if (r2 == null) goto L6;
        r2.requestRender(false, true, false);
        return;
    }

    public boolean containsPoint(float r3, float r4) {
        RectOld r2 = this.viewRect;
        float r0 = r2.x;
        if (r3 >= r0) goto L5;
        return false;
    L5:
        if (r3 > (r0 + r2.width)) goto L14;
        float r32 = r2.y;
        if (r4 >= r32) goto L9;
        return false;
    L9:
        if (r4 > (r32 + r2.height)) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    }

    public Bitmap getUiBlurBitmap() {
        FilterGLThread r0 = this.eglThread;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getUiBlurBitmap();
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture r9, int r10, int r11) {
        if (this.eglThread != null) goto L16;
        if (r9 != null) goto L6;
        return;
    L6:
        if (this.currentVideoPlayer == null) goto L18;
        FilterGLThread r1 = new FilterGLThread(r9, new VideoEditTextureView$$ExternalSyntheticLambda1(this), this.hdrInfo, this.uiBlurManager, r10, r11);
        this.eglThread = r1;
        r1.updateUiBlurGradient(this.gradientTop, this.gradientBottom);
        this.eglThread.updateUiBlurManager(this.uiBlurManager);
        int r92 = this.videoWidth;
        if (r92 == 0) goto L12;
        int r102 = this.videoHeight;
        if (r102 == 0) goto L12;
        this.eglThread.setVideoSize(r92, r102);
    L12:
        this.eglThread.requestRender(true, true, false);
        VideoEditTextureViewDelegate r93 = this.delegate;
        if (r93 == null) goto L19;
        r93.onEGLThreadAvailable(this.eglThread);
        return;
    L19:
        return;
    L18:
        return;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
        FilterGLThread r12 = this.eglThread;
        if (r12 == null) goto L7;
        r12.shutdown();
        this.eglThread = null;
        return true;
    L7:
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
        FilterGLThread r12 = this.eglThread;
        if (r12 == null) goto L6;
        r12.setSurfaceTextureSize(r2, r3);
        this.eglThread.requestRender(false, true, false);
        this.eglThread.postRunnable(new VideoEditTextureView$$ExternalSyntheticLambda0(this));
        return;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture r1) {
    }

    public void release() {
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L5;
        r0.shutdown();
    L5:
        this.currentVideoPlayer = null;
    }

    public void setDelegate(VideoEditTextureViewDelegate r1) {
        this.delegate = r1;
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L9;
        if (r1 != null) goto L7;
        r0.setFilterGLThreadDelegate(null);
        return;
    L7:
        r1.onEGLThreadAvailable(r0);
        return;
    }

    public void setHDRInfo(StoryEntry.HDRInfo r1) {
        this.hdrInfo = r1;
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L6;
        r0.updateHDRInfo(r1);
        return;
    }

    @Override // android.view.TextureView
    public void setTransform(Matrix r3) {
        super.setTransform(r3);
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L6;
        r0.updateUiBlurTransform(r3, getWidth(), getHeight());
        return;
    }

    public void setVideoSize(int r1, int r2) {
        this.videoWidth = r1;
        this.videoHeight = r2;
        FilterGLThread r0 = this.eglThread;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setVideoSize(r1, r2);
    }

    public void setViewRect(float r1, float r2, float r3, float r4) {
        RectOld r0 = this.viewRect;
        r0.x = r1;
        r0.y = r2;
        r0.width = r3;
        r0.height = r4;
    }

    public void updateUiBlurGradient(int r2, int r3) {
        FilterGLThread r0 = this.eglThread;
        if (r0 != null) goto L6;
        this.gradientTop = r2;
        this.gradientBottom = r3;
        return;
    L6:
        r0.updateUiBlurGradient(r2, r3);
    }

    public void updateUiBlurManager(BlurringShader.BlurManager r1) {
        this.uiBlurManager = r1;
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L6;
        r0.updateUiBlurManager(r1);
        return;
    }
}
