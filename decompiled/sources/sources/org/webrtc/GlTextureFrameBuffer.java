package org.webrtc;

import android.opengl.GLES20;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.io.encoding.Base64$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class GlTextureFrameBuffer {
    private int frameBufferId;
    private int height;
    private final int pixelFormat;
    private int textureId;
    private int width;

    public GlTextureFrameBuffer(int r1) {
        switch(r1) {
            case 6407: goto L6;
            case 6408: goto L6;
            case 6409: goto L6;
            default: goto L4;
        };
    L4:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid pixel format: ", r1);
        throw null;
    L6:
        this.pixelFormat = r1;
        this.width = 0;
        this.height = 0;
    }

    public int getFrameBufferId() {
        return this.frameBufferId;
    }

    public int getHeight() {
        return this.height;
    }

    public int getTextureId() {
        return this.textureId;
    }

    public int getWidth() {
        return this.width;
    }

    public void release() {
        GLES20.glDeleteTextures(1, new int[]{this.textureId}, 0);
        this.textureId = 0;
        GLES20.glDeleteFramebuffers(1, new int[]{this.frameBufferId}, 0);
        this.frameBufferId = 0;
        this.width = 0;
        this.height = 0;
    }

    public void setSize(int r13, int r14) {
        if (r13 <= 0) goto L21;
        if (r14 <= 0) goto L21;
        if (r13 == this.width) goto L7;
    L9:
        this.width = r13;
        this.height = r14;
        if (this.textureId != 0) goto L13;
        this.textureId = GlUtil.generateTexture(3553);
    L13:
        if (this.frameBufferId != 0) goto L15;
        int[] r3 = new int[1];
        GLES20.glGenFramebuffers(1, r3, 0);
        this.frameBufferId = r3[0];
    L15:
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.textureId);
        int r5 = this.pixelFormat;
        GLES20.glTexImage2D(3553, 0, r5, r13, r14, 0, r5, 5121, null);
        GLES20.glBindTexture(3553, 0);
        GlUtil.checkNoGLES2Error("GlTextureFrameBuffer setSize");
        GLES20.glBindFramebuffer(36160, this.frameBufferId);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.textureId, 0);
        int r12 = GLES20.glCheckFramebufferStatus(36160);
        if (r12 != 36053) goto L19;
        GLES20.glBindFramebuffer(36160, 0);
        return;
    L19:
        JsonArray$$ExternalSyntheticBUOutline0.m("Framebuffer not complete, status: ", r12);
        return;
    L7:
        if (r14 != this.height) goto L9;
        return;
    L21:
        Base64$$ExternalSyntheticBUOutline0.m("Invalid size: ", r13, "x", r14);
    }
}
