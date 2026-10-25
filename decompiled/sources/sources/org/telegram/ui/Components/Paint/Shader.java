package org.telegram.ui.Components.Paint;

import android.graphics.Color;
import android.opengl.GLES20;
import java.util.HashMap;
import java.util.Map;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

/* loaded from: classes7.dex */
public class Shader {
    protected int program;
    protected Map<String, Integer> uniformsMap;
    private int vertexShader;

    public static class CompilationResult {
        int shader;
        int status;

        public CompilationResult(int r1, int r2) {
            this.shader = r1;
            this.status = r2;
        }
    }

    public Shader(String r5, String r6, String[] r7, String[] r8) {
        this.uniformsMap = new HashMap();
        this.program = GLES20.glCreateProgram();
        CompilationResult r52 = compileShader(35633, r5);
        int r1 = 0;
        if (r52.status == 0) goto L5;
        CompilationResult r62 = compileShader(35632, r6);
        if (r62.status == 0) goto L12;
        GLES20.glAttachShader(this.program, r52.shader);
        GLES20.glAttachShader(this.program, r62.shader);
        int r0 = 0;
    L17:
        int r2 = r7.length;
        int r3 = this.program;
        if (r0 >= r2) goto L21;
        GLES20.glBindAttribLocation(r3, r0, r7[r0]);
        r0 = r0 + 1;
        goto L17
    L21:
        if (linkProgram(r3) != 0) goto L24;
        destroyShader(r52.shader, r62.shader, this.program);
        return;
    L24:
        int r72 = r8.length;
    L25:
        if (r1 >= r72) goto L27;
        String r02 = r8[r1];
        this.uniformsMap.put(r02, Integer.valueOf(GLES20.glGetUniformLocation(this.program, r02)));
        r1 = r1 + 1;
        goto L25
    L27:
        int r4 = r52.shader;
        if (r4 == 0) goto L30;
        GLES20.glDeleteShader(r4);
    L30:
        int r42 = r62.shader;
        if (r42 == 0) goto L36;
        GLES20.glDeleteShader(r42);
        return;
    L36:
        return;
    L12:
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("Fragment shader compilation failed");
    L14:
        destroyShader(r52.shader, r62.shader, this.program);
        return;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.e("Vertex shader compilation failed");
    L7:
        destroyShader(r52.shader, 0, this.program);
    }

    public static void SetColorUniform(int r4, int r5) {
        GLES20.glUniform4f(r4, Color.red(r5) / 255.0f, Color.green(r5) / 255.0f, Color.blue(r5) / 255.0f, Color.alpha(r5) / 255.0f);
    }

    private CompilationResult compileShader(int r2, String r3) {
        int r1 = GLES20.glCreateShader(r2);
        GLES20.glShaderSource(r1, r3);
        GLES20.glCompileShader(r1);
        int[] r22 = new int[1];
        GLES20.glGetShaderiv(r1, 35713, r22, 0);
        if (r22[0] != 0) goto L8;
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.e(GLES20.glGetShaderInfoLog(r1));
    L8:
        return new CompilationResult(r1, r22[0]);
    }

    private void destroyShader(int r1, int r2, int r3) {
        if (r1 == 0) goto L4;
        GLES20.glDeleteShader(r1);
    L4:
        if (r2 == 0) goto L6;
        GLES20.glDeleteShader(r2);
    L6:
        if (r3 == 0) goto L9;
        GLES20.glDeleteProgram(r1);
        return;
    }

    private int linkProgram(int r3) {
        GLES20.glLinkProgram(r3);
        int[] r2 = new int[1];
        GLES20.glGetProgramiv(r3, 35714, r2, 0);
        if (r2[0] != 0) goto L8;
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.e(GLES20.glGetProgramInfoLog(r3));
    L8:
        return r2[0];
    }

    public void cleanResources() {
        if (this.program == 0) goto L6;
        GLES20.glDeleteProgram(this.vertexShader);
        this.program = 0;
        return;
    }

    public int getUniform(String r1) {
        return this.uniformsMap.get(r1).intValue();
    }
}
