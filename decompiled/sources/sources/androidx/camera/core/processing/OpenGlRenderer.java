package androidx.camera.core.processing;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageProcessingUtil;
import androidx.camera.core.Logger;
import androidx.camera.core.processing.util.GLUtils;
import androidx.camera.core.processing.util.GraphicDeviceInfo;
import androidx.camera.core.processing.util.OutputSurface;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.EglBase;

/* loaded from: classes3.dex */
public class OpenGlRenderer {
    protected GLUtils.InputFormat mCurrentInputformat;
    protected GLUtils.Program2D mCurrentProgram;
    protected Surface mCurrentSurface;
    protected EGLConfig mEglConfig;
    protected EGLContext mEglContext;
    protected EGLDisplay mEglDisplay;
    private int mExternalTextureId;
    protected Thread mGlThread;
    protected final AtomicBoolean mInitialized;
    protected final Map<Surface, OutputSurface> mOutputSurfaceMap;
    protected Map<GLUtils.InputFormat, GLUtils.Program2D> mProgramHandles;
    protected int[] mSurfaceAttrib;
    protected EGLSurface mTempSurface;

    public OpenGlRenderer() {
        this.mInitialized = new AtomicBoolean(false);
        this.mOutputSurfaceMap = new HashMap();
        this.mEglDisplay = EGL14.EGL_NO_DISPLAY;
        this.mEglContext = EGL14.EGL_NO_CONTEXT;
        this.mSurfaceAttrib = GLUtils.EMPTY_ATTRIBS;
        this.mTempSurface = EGL14.EGL_NO_SURFACE;
        this.mProgramHandles = Collections.EMPTY_MAP;
        this.mCurrentProgram = null;
        this.mCurrentInputformat = GLUtils.InputFormat.UNKNOWN;
        this.mExternalTextureId = -1;
    }

    private void activateExternalTexture(int r1) {
        GLES20.glActiveTexture(33984);
        GLUtils.checkGlErrorOrThrow("glActiveTexture");
        GLES20.glBindTexture(36197, r1);
        GLUtils.checkGlErrorOrThrow("glBindTexture");
    }

    private void createEglContext(DynamicRange r35, GraphicDeviceInfo.Builder r36) {
        EGLDisplay r3 = EGL14.eglGetDisplay(0);
        this.mEglDisplay = r3;
        if (Objects.equals(r3, EGL14.EGL_NO_DISPLAY) == true) goto L36;
        int r32 = 2;
        int[] r4 = new int[2];
        if (EGL14.eglInitialize(this.mEglDisplay, r4, 0, r4, 1) == false) goto L34;
        if (r36 == null) goto L9;
        r36.setEglVersion(r4[0] + "." + r4[1]);
    L9:
        if (r35.is10BitHdr() == false) goto L11;
        int r8 = 10;
    L13:
        if (r35.is10BitHdr() == false) goto L15;
        int r14 = 2;
    L17:
        if (r35.is10BitHdr() == false) goto L20;
        int r1 = 64;
    L19:
        int r20 = r1;
        if (r35.is10BitHdr() == false) goto L24;
        int r22 = -1;
    L25:
        EGLConfig[] r42 = new EGLConfig[1];
        if (EGL14.eglChooseConfig(this.mEglDisplay, new int[]{12324, r8, 12323, r8, 12322, r8, 12321, r14, 12325, 0, 12326, 0, 12352, r20, EglBase.EGL_RECORDABLE_ANDROID, r22, 12339, 5, 12344}, 0, r42, 0, 1, new int[1], 0) == false) goto L32;
        EGLConfig r12 = r42[0];
        if (r35.is10BitHdr() == false) goto L30;
        r32 = 3;
    L30:
        EGLContext r33 = EGL14.eglCreateContext(this.mEglDisplay, r12, EGL14.EGL_NO_CONTEXT, new int[]{12440, r32, 12344}, 0);
        GLUtils.checkEglErrorOrThrow("eglCreateContext");
        this.mEglConfig = r12;
        this.mEglContext = r33;
        int[] r13 = new int[1];
        EGL14.eglQueryContext(this.mEglDisplay, r33, 12440, r13, 0);
        Log.d("OpenGlRenderer", "EGLContext created, client version " + r13[0]);
        return;
    L32:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to find a suitable EGLConfig");
        return;
    L24:
        r22 = 1;
        goto L25
    L20:
        r1 = 4;
        goto L19
    L15:
        r14 = 8;
        goto L17
    L11:
        r8 = 8;
        goto L13
    L34:
        this.mEglDisplay = EGL14.EGL_NO_DISPLAY;
        Segment$$ExternalSyntheticBUOutline1.m("Unable to initialize EGL14");
        return;
    L36:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to get EGL14 display");
    }

    private void createTempSurface() {
        EGLDisplay r0 = this.mEglDisplay;
        EGLConfig r1 = this.mEglConfig;
        Objects.requireNonNull(r1);
        this.mTempSurface = GLUtils.createPBufferSurface(r0, r1, 1, 1);
    }

    private Pair<String, String> getExtensionsBeforeInitialized(DynamicRange r5) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, false);
        createEglContext(r5, null);     // Catch: Throwable -> L13 IllegalStateException -> L15
        createTempSurface();     // Catch: Throwable -> L13 IllegalStateException -> L15
        makeCurrent(this.mTempSurface);     // Catch: Throwable -> L13 IllegalStateException -> L15
        String r52 = GLES20.glGetString(7939);     // Catch: Throwable -> L13 IllegalStateException -> L15
        String r2 = EGL14.eglQueryString(this.mEglDisplay, 12373);     // Catch: Throwable -> L13 IllegalStateException -> L15
        if (r52 != null) goto L7;
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        if (r2 != null) goto L10;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        Pair<String, String> r3 = new Pair(r52, r2);     // Catch: Throwable -> L13 IllegalStateException -> L15
        releaseInternal();
        return r3;
    L13:
        th = move-exception;
        releaseInternal();
        throw th;
    L15:
        e = move-exception;
        Logger.w("OpenGlRenderer", "Failed to get GL or EGL extensions: " + e.getMessage(), e);     // Catch: Throwable -> L13
        Pair<String, String> r53 = new Pair(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L13
        releaseInternal();
        return r53;
    }

    private void releaseInternal() {
        Iterator<GLUtils.Program2D> r0 = this.mProgramHandles.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().delete();
        goto L4
    L6:
        this.mProgramHandles = Collections.EMPTY_MAP;
        this.mCurrentProgram = null;
        if (Objects.equals(this.mEglDisplay, EGL14.EGL_NO_DISPLAY) == true) goto L23;
        EGLDisplay r1 = this.mEglDisplay;
        EGLSurface r2 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(r1, r2, r2, EGL14.EGL_NO_CONTEXT);
        Iterator<OutputSurface> r12 = this.mOutputSurfaceMap.values().iterator();
    L10:
        if (r12.hasNext() == false) goto L16;
        OutputSurface r22 = r12.next();
        if (Objects.equals(r22.getEglSurface(), EGL14.EGL_NO_SURFACE) == true) goto L10;
        if (EGL14.eglDestroySurface(this.mEglDisplay, r22.getEglSurface()) == true) goto L10;
        GLUtils.checkEglErrorOrLog("eglDestroySurface");
        goto L10
    L16:
        this.mOutputSurfaceMap.clear();
        if (Objects.equals(this.mTempSurface, EGL14.EGL_NO_SURFACE) == true) goto L20;
        EGL14.eglDestroySurface(this.mEglDisplay, this.mTempSurface);
        this.mTempSurface = EGL14.EGL_NO_SURFACE;
    L20:
        if (Objects.equals(this.mEglContext, EGL14.EGL_NO_CONTEXT) == true) goto L22;
        EGL14.eglDestroyContext(this.mEglDisplay, this.mEglContext);
        this.mEglContext = EGL14.EGL_NO_CONTEXT;
    L22:
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.mEglDisplay);
        this.mEglDisplay = EGL14.EGL_NO_DISPLAY;
    L23:
        this.mEglConfig = null;
        this.mExternalTextureId = -1;
        this.mCurrentInputformat = GLUtils.InputFormat.UNKNOWN;
        this.mCurrentSurface = null;
        this.mGlThread = null;
    }

    private void snapshot(ByteBuffer r17, Size r18, float[] r19) {
        if (r17.capacity() != ((r18.getWidth() * r18.getHeight()) * 4)) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "ByteBuffer capacity is not equal to width * height * 4.");
        Preconditions.checkArgument(r17.isDirect(), "ByteBuffer is not direct.");
        int r12 = GLUtils.generateTexture();
        GLES20.glActiveTexture(33985);
        GLUtils.checkGlErrorOrThrow("glActiveTexture");
        GLES20.glBindTexture(3553, r12);
        GLUtils.checkGlErrorOrThrow("glBindTexture");
        GLES20.glTexImage2D(3553, 0, 6407, r18.getWidth(), r18.getHeight(), 0, 6407, 5121, null);
        GLUtils.checkGlErrorOrThrow("glTexImage2D");
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        int r7 = GLUtils.generateFbo();
        GLES20.glBindFramebuffer(36160, r7);
        GLUtils.checkGlErrorOrThrow("glBindFramebuffer");
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, r12, 0);
        GLUtils.checkGlErrorOrThrow("glFramebufferTexture2D");
        GLES20.glActiveTexture(33984);
        GLUtils.checkGlErrorOrThrow("glActiveTexture");
        GLES20.glBindTexture(36197, this.mExternalTextureId);
        GLUtils.checkGlErrorOrThrow("glBindTexture");
        this.mCurrentSurface = null;
        GLES20.glViewport(0, 0, r18.getWidth(), r18.getHeight());
        GLES20.glScissor(0, 0, r18.getWidth(), r18.getHeight());
        GLUtils.Program2D r2 = (GLUtils.Program2D) Preconditions.checkNotNull(this.mCurrentProgram);
        if ((r2 instanceof GLUtils.SamplerShaderProgram) == false) goto L9;
        ((GLUtils.SamplerShaderProgram) r2).updateTextureMatrix(r19);
    L9:
        GLES20.glDrawArrays(5, 0, 4);
        GLUtils.checkGlErrorOrThrow("glDrawArrays");
        GLES20.glReadPixels(0, 0, r18.getWidth(), r18.getHeight(), 6408, 5121, r17);
        GLUtils.checkGlErrorOrThrow("glReadPixels");
        GLES20.glBindFramebuffer(36160, 0);
        GLUtils.deleteTexture(r12);
        GLUtils.deleteFbo(r7);
        activateExternalTexture(this.mExternalTextureId);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public OutputSurface createOutputSurfaceInternal(Surface r4) {
        EGLDisplay r0 = this.mEglDisplay;     // Catch: Throwable -> L5
        EGLConfig r1 = this.mEglConfig;     // Catch: Throwable -> L5
        Objects.requireNonNull(r1);     // Catch: Throwable -> L5
        EGLSurface r42 = GLUtils.createWindowSurface(r0, r1, r4, this.mSurfaceAttrib);     // Catch: Throwable -> L5
        Size r3 = GLUtils.getSurfaceSize(this.mEglDisplay, r42);
        return OutputSurface.of(r42, r3.getWidth(), r3.getHeight());
    L5:
        e = move-exception;
        Logger.w("OpenGlRenderer", "Failed to create EGL surface: " + e.getMessage(), e);
        return null;
    }

    public OutputSurface getOutSurfaceOrThrow(Surface r3) {
        Preconditions.checkState(this.mOutputSurfaceMap.containsKey(r3), "The surface is not registered.");
        OutputSurface r2 = this.mOutputSurfaceMap.get(r3);
        Objects.requireNonNull(r2);
        return r2;
    }

    public int getTextureName() {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        return this.mExternalTextureId;
    }

    public GraphicDeviceInfo init(DynamicRange r5, Map<GLUtils.InputFormat, ShaderProvider> r6) {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            this = this;
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.mInitialized
            r1 = 0
            androidx.camera.core.processing.util.GLUtils.checkInitializedOrThrow(r0, r1)
            androidx.camera.core.processing.util.GraphicDeviceInfo$Builder r0 = androidx.camera.core.processing.util.GraphicDeviceInfo.builder()
            boolean r1 = r5.is10BitHdr()     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            if (r1 == 0) goto L4a
            androidx.core.util.Pair r1 = r4.getExtensionsBeforeInitialized(r5)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            F r2 = r1.first     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.Object r2 = androidx.core.util.Preconditions.checkNotNull(r2)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            S r1 = r1.second     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.Object r1 = androidx.core.util.Preconditions.checkNotNull(r1)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.String r3 = "GL_EXT_YUV_target"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            if (r3 != 0) goto L3e
            java.lang.String r5 = "OpenGlRenderer"
            java.lang.String r3 = "Device does not support GL_EXT_YUV_target. Fallback to SDR."
            androidx.camera.core.Logger.w(r5, r3)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            androidx.camera.core.DynamicRange r5 = androidx.camera.core.DynamicRange.SDR     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            goto L3e
        L3a:
            r5 = move-exception
            goto L7c
        L3c:
            r5 = move-exception
            goto L7c
        L3e:
            int[] r3 = androidx.camera.core.processing.util.GLUtils.chooseSurfaceAttrib(r1, r5)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r4.mSurfaceAttrib = r3     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r0.setGlExtensions(r2)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r0.setEglExtensions(r1)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
        L4a:
            r4.createEglContext(r5, r0)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r4.createTempSurface()     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            android.opengl.EGLSurface r1 = r4.mTempSurface     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r4.makeCurrent(r1)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.String r1 = androidx.camera.core.processing.util.GLUtils.getGlVersionNumber()     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r0.setGlVersion(r1)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.util.Map r5 = androidx.camera.core.processing.util.GLUtils.createPrograms(r5, r6)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r4.mProgramHandles = r5     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            int r5 = androidx.camera.core.processing.util.GLUtils.createTexture()     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r4.mExternalTextureId = r5     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            r4.useAndConfigureProgramWithTexture(r5)     // Catch: java.lang.IllegalArgumentException -> L3a java.lang.IllegalStateException -> L3c
            java.lang.Thread r5 = java.lang.Thread.currentThread()
            r4.mGlThread = r5
            java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInitialized
            r5 = 1
            r4.set(r5)
            androidx.camera.core.processing.util.GraphicDeviceInfo r4 = r0.build()
            return r4
        L7c:
            r4.releaseInternal()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.OpenGlRenderer.init(androidx.camera.core.DynamicRange, java.util.Map):androidx.camera.core.processing.util.GraphicDeviceInfo");
    }

    public void makeCurrent(EGLSurface r2) {
        Preconditions.checkNotNull(this.mEglDisplay);
        Preconditions.checkNotNull(this.mEglContext);
        if (EGL14.eglMakeCurrent(this.mEglDisplay, r2, r2, this.mEglContext) == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("eglMakeCurrent failed");
    }

    public void registerOutputSurface(Surface r3) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        if (this.mOutputSurfaceMap.containsKey(r3) == true) goto L6;
        this.mOutputSurfaceMap.put(r3, GLUtils.NO_OUTPUT_SURFACE);
        return;
    }

    public void release() {
        if (this.mInitialized.getAndSet(false) == true) goto L5;
        return;
    L5:
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        releaseInternal();
    }

    public void removeOutputSurfaceInternal(Surface r2, boolean r3) {
        if (this.mCurrentSurface != r2) goto L5;
        this.mCurrentSurface = null;
        makeCurrent(this.mTempSurface);
    L5:
        Map<Surface, OutputSurface> r0 = this.mOutputSurfaceMap;
        if (r3 == false) goto L8;
        OutputSurface r22 = r0.remove(r2);
    L9:
        if (r22 != null) goto L11;
        return;
    L11:
        if (r22 == GLUtils.NO_OUTPUT_SURFACE) goto L20;
        EGL14.eglDestroySurface(this.mEglDisplay, r22.getEglSurface());     // Catch: RuntimeException -> L14
        return;
    L14:
        e = move-exception;
        Logger.w("OpenGlRenderer", "Failed to destroy EGL surface: " + e.getMessage(), e);
        return;
    L20:
        return;
    L8:
        r22 = r0.put(r2, GLUtils.NO_OUTPUT_SURFACE);
        goto L9
    }

    public void render(long r5, float[] r7, Surface r8) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        OutputSurface r0 = getOutSurfaceOrThrow(r8);
        if (r0 != GLUtils.NO_OUTPUT_SURFACE) goto L9;
        r0 = createOutputSurfaceInternal(r8);
        if (r0 == null) goto L19;
        this.mOutputSurfaceMap.put(r8, r0);
        goto L9
    L19:
        return;
    L9:
        if (r8 == this.mCurrentSurface) goto L11;
        makeCurrent(r0.getEglSurface());
        this.mCurrentSurface = r8;
        GLES20.glViewport(0, 0, r0.getWidth(), r0.getHeight());
        GLES20.glScissor(0, 0, r0.getWidth(), r0.getHeight());
    L11:
        GLUtils.Program2D r1 = (GLUtils.Program2D) Preconditions.checkNotNull(this.mCurrentProgram);
        if ((r1 instanceof GLUtils.SamplerShaderProgram) == false) goto L14;
        ((GLUtils.SamplerShaderProgram) r1).updateTextureMatrix(r7);
    L14:
        GLES20.glDrawArrays(5, 0, 4);
        GLUtils.checkGlErrorOrThrow("glDrawArrays");
        EGLExt.eglPresentationTimeANDROID(this.mEglDisplay, r0.getEglSurface(), r5);
        if (EGL14.eglSwapBuffers(this.mEglDisplay, r0.getEglSurface()) == true) goto L18;
        Logger.w("OpenGlRenderer", "Failed to swap buffers with EGL error: 0x" + Integer.toHexString(EGL14.eglGetError()));
        removeOutputSurfaceInternal(r8, false);
        return;
    }

    public void setInputFormat(GLUtils.InputFormat r3) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        if (this.mCurrentInputformat == r3) goto L6;
        this.mCurrentInputformat = r3;
        useAndConfigureProgramWithTexture(this.mExternalTextureId);
        return;
    }

    public Bitmap snapshot(Size r3, float[] r4) {
        ByteBuffer r0 = ByteBuffer.allocateDirect((r3.getWidth() * r3.getHeight()) * 4);
        snapshot(r0, r3, r4);
        Bitmap r2 = Bitmap.createBitmap(r3.getWidth(), r3.getHeight(), Bitmap.Config.ARGB_8888);
        r0.rewind();
        ImageProcessingUtil.copyByteBufferToBitmap(r2, r0, r3.getWidth() * 4);
        return r2;
    }

    public void unregisterOutputSurface(Surface r3) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        removeOutputSurfaceInternal(r3, true);
    }

    public void useAndConfigureProgramWithTexture(int r3) {
        GLUtils.Program2D r0 = this.mProgramHandles.get(this.mCurrentInputformat);
        if (r0 != null) goto L5;
        CstInsn$$ExternalSyntheticBUOutline0.m("Unable to configure program for input format: ", this.mCurrentInputformat);
        return;
    L5:
        if (this.mCurrentProgram == r0) goto L7;
        this.mCurrentProgram = r0;
        r0.use();
        Log.d("OpenGlRenderer", "Using program for input format " + this.mCurrentInputformat + ": " + this.mCurrentProgram);
    L7:
        activateExternalTexture(r3);
    }
}
