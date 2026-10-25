package org.webrtc;

import android.opengl.GLES20;
import java.nio.FloatBuffer;

/* loaded from: classes6.dex */
public class GlShader {
    private static final String TAG = "GlShader";
    private int program;

    public GlShader(String r6, String r7) {
        int r62 = compileShader(35633, r6);
        int r72 = compileShader(35632, r7);
        int r0 = GLES20.glCreateProgram();
        this.program = r0;
        if (r0 == 0) goto L10;
        GLES20.glAttachShader(r0, r62);
        GLES20.glAttachShader(this.program, r72);
        GLES20.glLinkProgram(this.program);
        int[] r2 = {0};
        GLES20.glGetProgramiv(this.program, 35714, r2, 0);
        if (r2[0] != 1) goto L8;
        GLES20.glDeleteShader(r62);
        GLES20.glDeleteShader(r72);
        GlUtil.checkNoGLES2Error("Creating GlShader");
        return;
    L8:
        Logging.e(TAG, "Could not link program: " + GLES20.glGetProgramInfoLog(this.program));
        GlShader$$ExternalSyntheticBUOutline1.m(GLES20.glGetProgramInfoLog(this.program));
        throw null;
    L10:
        GlShader$$ExternalSyntheticBUOutline2.m("glCreateProgram() failed. GLES20 error: ", GLES20.glGetError());
        throw null;
    }

    private static int compileShader(int r3, String r4) {
        int r32 = GLES20.glCreateShader(r3);
        if (r32 == 0) goto L10;
        GLES20.glShaderSource(r32, r4);
        GLES20.glCompileShader(r32);
        int[] r1 = {0};
        GLES20.glGetShaderiv(r32, 35713, r1, 0);
        if (r1[0] != 1) goto L8;
        GlUtil.checkNoGLES2Error("compileShader");
        return r32;
    L8:
        Logging.e(TAG, "Compile error " + GLES20.glGetShaderInfoLog(r32) + " in shader:\n" + r4);
        GlShader$$ExternalSyntheticBUOutline1.m(GLES20.glGetShaderInfoLog(r32));
        return 0;
    L10:
        GlShader$$ExternalSyntheticBUOutline2.m("glCreateShader() failed. GLES20 error: ", GLES20.glGetError());
        return 0;
    }

    public int getAttribLocation(String r2) {
        int r1 = this.program;
        if (r1 == (-1)) goto L10;
        int r12 = GLES20.glGetAttribLocation(r1, r2);
        if (r12 < 0) goto L7;
        return r12;
    L7:
        GlShader$$ExternalSyntheticBUOutline0.m("Could not locate '", r2, "' in program");
        return 0;
    L10:
        GlShader$$ExternalSyntheticBUOutline1.m("The program has been released");
        return 0;
    }

    public int getUniformLocation(String r2) {
        int r1 = this.program;
        if (r1 == (-1)) goto L10;
        int r12 = GLES20.glGetUniformLocation(r1, r2);
        if (r12 < 0) goto L7;
        return r12;
    L7:
        GlShader$$ExternalSyntheticBUOutline0.m("Could not locate uniform '", r2, "' in program");
        return 0;
    L10:
        GlShader$$ExternalSyntheticBUOutline1.m("The program has been released");
        return 0;
    }

    public void release() {
        Logging.d(TAG, "Deleting shader.");
        int r0 = this.program;
        if (r0 == (-1)) goto L6;
        GLES20.glDeleteProgram(r0);
        this.program = -1;
        return;
    }

    public void setVertexAttribArray(String r9, int r10, int r11, FloatBuffer r12) {
        if (this.program == (-1)) goto L6;
        int r2 = getAttribLocation(r9);
        GLES20.glEnableVertexAttribArray(r2);
        GLES20.glVertexAttribPointer(r2, r10, 5126, false, r11, r12);
        GlUtil.checkNoGLES2Error("setVertexAttribArray");
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("The program has been released");
    }

    public void setVertexAttribArray(String r2, int r3, FloatBuffer r4) {
        setVertexAttribArray(r2, r3, 0, r4);
    }

    public void useProgram() {
        if (this.program == (-1)) goto L13;
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        GLES20.glUseProgram(this.program);     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        GlUtil.checkNoGLES2Error("glUseProgram");
        return;
    L10:
        th = move-exception;
        throw th;
    L13:
        GlShader$$ExternalSyntheticBUOutline1.m("The program has been released");
    }
}
