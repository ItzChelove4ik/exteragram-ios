package org.telegram.messenger.video;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.view.Surface;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes6.dex */
public class OutputSurface implements SurfaceTexture.OnFrameAvailableListener {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private EGL10 mEGL;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private boolean mFrameAvailable;
    private final Object mFrameSyncObject;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private TextureRenderer mTextureRender;

    public OutputSurface(MediaController.SavedFilterState r21, String r22, String r23, String r24, ArrayList<VideoEditedInfo.MediaEntity> r25, MediaController.CropState r26, int r27, int r28, int r29, int r30, int r31, float r32, boolean r33, Integer r34, Integer r35, StoryEntry.HDRInfo r36, MediaCodecVideoConvertor.ConvertVideoParams r37) {
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mFrameSyncObject = new Object();
        TextureRenderer r2 = new TextureRenderer(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37);
        this.mTextureRender = r2;
        r2.surfaceCreated();
        SurfaceTexture r1 = new SurfaceTexture(this.mTextureRender.getTextureId());
        this.mSurfaceTexture = r1;
        r1.setOnFrameAvailableListener(this);
        this.mSurface = new Surface(this.mSurfaceTexture);
    }

    private void checkEglError(String r2) {
        if (EGL14.eglGetError() != 12288) goto L5;
        return;
    L5:
        MVEL$$ExternalSyntheticBUOutline0.m("EGL error encountered (see log) at: ", r2);
    }

    public void awaitNewImage() {
        Object r0 = this.mFrameSyncObject;
        monitor-enter(r0);
    L24:
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.mFrameAvailable == true) goto L16;
        this.mFrameSyncObject.wait(2500);     // Catch: Throwable -> L11 InterruptedException -> L13
        if (this.mFrameAvailable == true) goto L24;
        throw new RuntimeException("Surface frame wait timed out");     // Catch: Throwable -> L11 InterruptedException -> L13
    L13:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L11
    L16:
        this.mFrameAvailable = false;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        this.mSurfaceTexture.updateTexImage();
    }

    public void changeFragmentShader(String r1, String r2, boolean r3) {
        this.mTextureRender.changeFragmentShader(r1, r2, r3);
    }

    public void drawImage(long r2) {
        this.mTextureRender.drawFrame(this.mSurfaceTexture, r2);
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture r2) {
        Object r22 = this.mFrameSyncObject;
        monitor-enter(r22);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mFrameAvailable == true) goto L12;
        this.mFrameAvailable = true;     // Catch: Throwable -> L9
        this.mFrameSyncObject.notifyAll();     // Catch: Throwable -> L9
        monitor-exit(r22);     // Catch: Throwable -> L9
        return;
    L12:
        throw new RuntimeException("mFrameAvailable already set, frame could be dropped");     // Catch: Throwable -> L9
    }

    public void release() {
        TextureRenderer r0 = this.mTextureRender;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        this.mSurface.release();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mEGL = null;
        this.mTextureRender = null;
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }

    public boolean supportsEXTYUV() {
        return GLES20.glGetString(7939).contains("GL_EXT_YUV_target");
    L5:
        e = move-exception;
        FileLog.e(e);
        return false;
    }
}
