package androidx.camera.core.processing.concurrent;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLExt;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.processing.OpenGlRenderer;
import androidx.camera.core.processing.ShaderProvider;
import androidx.camera.core.processing.util.GLUtils;
import androidx.camera.core.processing.util.GraphicDeviceInfo;
import androidx.camera.core.processing.util.OutputSurface;
import androidx.core.util.Preconditions;
import java.util.Map;

/* loaded from: classes3.dex */
public final class DualOpenGlRenderer extends OpenGlRenderer {
    private final CompositionSettings mPrimaryCompositionSettings;
    private int mPrimaryExternalTextureId;
    private final CompositionSettings mSecondaryCompositionSettings;
    private int mSecondaryExternalTextureId;

    public DualOpenGlRenderer(CompositionSettings r2, CompositionSettings r3) {
        this.mPrimaryExternalTextureId = -1;
        this.mSecondaryExternalTextureId = -1;
        this.mPrimaryCompositionSettings = r2;
        this.mSecondaryCompositionSettings = r3;
    }

    private static float[] getTransformMatrix(Size r6, Size r7, CompositionSettings r8) {
        float[] r2 = GLUtils.create4x4IdentityMatrix();
        float[] r4 = GLUtils.create4x4IdentityMatrix();
        float[] r0 = GLUtils.create4x4IdentityMatrix();
        Matrix.scaleM(r2, 0, r6.getWidth() / r7.getWidth(), r6.getHeight() / r7.getHeight(), 1.0f);
        if (r8.getScale().first.floatValue() == 0.0f) goto L5;
    L6:
        Matrix.translateM(r4, 0, r8.getOffset().first.floatValue() / r8.getScale().first.floatValue(), r8.getOffset().second.floatValue() / r8.getScale().second.floatValue(), 0.0f);
    L7:
        Matrix.multiplyMM(r0, 0, r2, 0, r4, 0);
        return r0;
    L5:
        if (r8.getScale().second.floatValue() == 0.0f) goto L7;
        goto L6
    }

    private void renderInternal(OutputSurface r3, SurfaceOutput r4, SurfaceTexture r5, CompositionSettings r6, int r7, boolean r8) {
        useAndConfigureProgramWithTexture(r7);
        GLES20.glViewport(0, 0, r3.getWidth(), r3.getHeight());
        GLES20.glScissor(0, 0, r3.getWidth(), r3.getHeight());
        float[] r0 = new float[16];
        r5.getTransformMatrix(r0);
        float[] r52 = new float[16];
        r4.updateTransformMatrix(r52, r0, r8);
        GLUtils.Program2D r2 = (GLUtils.Program2D) Preconditions.checkNotNull(this.mCurrentProgram);
        if ((r2 instanceof GLUtils.SamplerShaderProgram) == false) goto L5;
        ((GLUtils.SamplerShaderProgram) r2).updateTextureMatrix(r52);
    L5:
        r2.updateTransformMatrix(getTransformMatrix(new Size((int) (r3.getWidth() * r6.getScale().first.floatValue()), (int) (r3.getHeight() * r6.getScale().second.floatValue())), new Size(r3.getWidth(), r3.getHeight()), r6));
        r2.updateAlpha(r6.getAlpha());
        GLES20.glEnable(3042);
        GLES20.glBlendFuncSeparate(770, 771, 1, 771);
        GLES20.glDrawArrays(5, 0, 4);
        GLUtils.checkGlErrorOrThrow("glDrawArrays");
        GLES20.glDisable(3042);
    }

    public int getTextureName(boolean r3) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        if (r3 == false) goto L7;
        return this.mPrimaryExternalTextureId;
    L7:
        return this.mSecondaryExternalTextureId;
    }

    @Override // androidx.camera.core.processing.OpenGlRenderer
    public GraphicDeviceInfo init(DynamicRange r1, Map<GLUtils.InputFormat, ShaderProvider> r2) {
        GraphicDeviceInfo r12 = super.init(r1, r2);
        this.mPrimaryExternalTextureId = GLUtils.createTexture();
        this.mSecondaryExternalTextureId = GLUtils.createTexture();
        return r12;
    }

    @Override // androidx.camera.core.processing.OpenGlRenderer
    public void release() {
        super.release();
        this.mPrimaryExternalTextureId = -1;
        this.mSecondaryExternalTextureId = -1;
    }

    public void render(long r10, Surface r12, SurfaceOutput r13, SurfaceTexture r14, SurfaceTexture r15) {
        GLUtils.checkInitializedOrThrow(this.mInitialized, true);
        GLUtils.checkGlThreadOrThrow(this.mGlThread);
        OutputSurface r0 = getOutSurfaceOrThrow(r12);
        if (r0 != GLUtils.NO_OUTPUT_SURFACE) goto L8;
        r0 = createOutputSurfaceInternal(r12);
        if (r0 == null) goto L16;
        this.mOutputSurfaceMap.put(r12, r0);
        goto L8
    L16:
        return;
    L8:
        OutputSurface r3 = r0;
        if (r12 == this.mCurrentSurface) goto L11;
        makeCurrent(r3.getEglSurface());
        this.mCurrentSurface = r12;
    L11:
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        renderInternal(r3, r13, r14, this.mPrimaryCompositionSettings, this.mPrimaryExternalTextureId, true);
        renderInternal(r3, r13, r15, this.mSecondaryCompositionSettings, this.mSecondaryExternalTextureId, false);
        EGLExt.eglPresentationTimeANDROID(this.mEglDisplay, r3.getEglSurface(), r10);
        if (EGL14.eglSwapBuffers(this.mEglDisplay, r3.getEglSurface()) == true) goto L15;
        Logger.w("DualOpenGlRenderer", "Failed to swap buffers with EGL error: 0x" + Integer.toHexString(EGL14.eglGetError()));
        removeOutputSurfaceInternal(r12, false);
        return;
    }
}
