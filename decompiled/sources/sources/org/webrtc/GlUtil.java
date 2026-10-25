package org.webrtc;

import android.opengl.GLES20;
import android.opengl.GLException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes6.dex */
public class GlUtil {

    public static class GlOutOfMemoryException extends GLException {
        public GlOutOfMemoryException(int r1, String r2) {
            super(r1, r2);
        }
    }

    private GlUtil() {
    }

    public static void checkNoGLES2Error(String r3) {
        int r0 = GLES20.glGetError();
        if (r0 != 0) goto L5;
        return;
    L5:
        if (r0 != 1285) goto L8;
        throw new GlOutOfMemoryException(r0, r3);
    L8:
        throw new GLException(r0, r3 + ": GLES20 error: " + r0);
    }

    public static FloatBuffer createFloatBuffer(float[] r2) {
        ByteBuffer r0 = ByteBuffer.allocateDirect(r2.length * 4);
        r0.order(ByteOrder.nativeOrder());
        FloatBuffer r02 = r0.asFloatBuffer();
        r02.put(r2);
        r02.position(0);
        return r02;
    }

    public static int generateTexture(int r3) {
        int[] r1 = new int[1];
        GLES20.glGenTextures(1, r1, 0);
        int r0 = r1[0];
        GLES20.glBindTexture(r3, r0);
        GLES20.glTexParameterf(r3, 10241, 9729.0f);
        GLES20.glTexParameterf(r3, 10240, 9729.0f);
        GLES20.glTexParameterf(r3, 10242, 33071.0f);
        GLES20.glTexParameterf(r3, 10243, 33071.0f);
        checkNoGLES2Error("generateTexture");
        return r0;
    }
}
