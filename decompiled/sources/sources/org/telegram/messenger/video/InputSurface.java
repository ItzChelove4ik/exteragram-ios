package org.telegram.messenger.video;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class InputSurface {
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final int EGL_RECORDABLE_ANDROID = 12610;
    private EGLContext mEGLContext;
    private EGLDisplay mEGLDisplay;
    private EGLSurface mEGLSurface;
    private Surface mSurface;

    public InputSurface(Surface r1) {
        r1.getClass();
        this.mSurface = r1;
        eglSetup();
    }

    private void checkEglError(String r2) {
        boolean r1 = false;
    L4:
        if (EGL14.eglGetError() == 12288) goto L6;
        r1 = true;
        goto L4
    L6:
        if (r1 == true) goto L8;
        return;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("EGL error encountered (see log)");
    }

    private void eglSetup() {
        EGLDisplay r1 = EGL14.eglGetDisplay(0);
        this.mEGLDisplay = r1;
        if (r1 == EGL14.EGL_NO_DISPLAY) goto L21;
        int[] r3 = new int[2];
        if (EGL14.eglInitialize(r1, r3, 0, r3, 1) == false) goto L19;
        EGLConfig[] r8 = new EGLConfig[1];
        EGLDisplay r5 = this.mEGLDisplay;
        if (EGL14.eglChooseConfig(r5, new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344}, 0, r8, 0, 1, new int[1], 0) == false) goto L17;
        EGLDisplay r2 = this.mEGLDisplay;
        EGLConfig r4 = r8[0];
        EGLContext r52 = EGL14.EGL_NO_CONTEXT;
        this.mEGLContext = EGL14.eglCreateContext(r2, r4, r52, new int[]{12440, 2, 12344}, 0);
        checkEglError("eglCreateContext");
        if (this.mEGLContext == null) goto L15;
        EGLDisplay r22 = this.mEGLDisplay;
        EGLConfig r32 = r8[0];
        Surface r42 = this.mSurface;
        this.mEGLSurface = EGL14.eglCreateWindowSurface(r22, r32, r42, new int[]{12344}, 0);
        checkEglError("eglCreateWindowSurface");
        if (this.mEGLSurface == null) goto L13;
        return;
    L13:
        GlShader$$ExternalSyntheticBUOutline1.m("surface was null");
        return;
    L15:
        GlShader$$ExternalSyntheticBUOutline1.m("null context");
        return;
    L17:
        GlShader$$ExternalSyntheticBUOutline1.m("unable to find RGB888+recordable ES2 EGL config");
        return;
    L19:
        this.mEGLDisplay = null;
        GlShader$$ExternalSyntheticBUOutline1.m("unable to initialize EGL14");
        return;
    L21:
        GlShader$$ExternalSyntheticBUOutline1.m("unable to get EGL14 display");
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void makeCurrent() {
        EGLDisplay r0 = this.mEGLDisplay;
        EGLSurface r1 = this.mEGLSurface;
        if (EGL14.eglMakeCurrent(r0, r1, r1, this.mEGLContext) == false) goto L5;
        return;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("eglMakeCurrent failed");
    }

    public void release() {
        if (EGL14.eglGetCurrentContext().equals(this.mEGLContext) == false) goto L5;
        EGLDisplay r0 = this.mEGLDisplay;
        EGLSurface r1 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(r0, r1, r1, EGL14.EGL_NO_CONTEXT);
    L5:
        EGL14.eglDestroySurface(this.mEGLDisplay, this.mEGLSurface);
        EGL14.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
        this.mSurface.release();
        this.mEGLDisplay = null;
        this.mEGLContext = null;
        this.mEGLSurface = null;
        this.mSurface = null;
    }

    public void setPresentationTime(long r2) {
        EGLExt.eglPresentationTimeANDROID(this.mEGLDisplay, this.mEGLSurface, r2);
    }

    public boolean swapBuffers() {
        return EGL14.eglSwapBuffers(this.mEGLDisplay, this.mEGLSurface);
    }
}
