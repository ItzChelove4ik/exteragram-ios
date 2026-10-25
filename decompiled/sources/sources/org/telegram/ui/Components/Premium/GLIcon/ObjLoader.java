package org.telegram.ui.Components.Premium.GLIcon;

import android.content.Context;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public final class ObjLoader {
    public float[] normals;
    public int numFaces;
    public float[] positions;
    public float[] textureCoordinates;

    public ObjLoader(Context r13, String r14, float r15) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        DataInputStream r3 = new DataInputStream(r13.getAssets().open(r14));     // Catch: IOException -> L30
        int r132 = r3.readInt();     // Catch: IOException -> L30
        int r142 = 0;
        int r4 = 0;
    L4:
        if (r4 >= r132) goto L6;
        r0.add(Float.valueOf(r3.readFloat()));     // Catch: IOException -> L30
        r4 = r4 + 1;     // Catch: IOException -> L30
        goto L4
    L6:
        int r133 = r3.readInt();     // Catch: IOException -> L30
        int r42 = 0;
    L7:
        if (r42 >= r133) goto L9;
        r2.add(Float.valueOf(r3.readFloat()));     // Catch: IOException -> L30
        r42 = r42 + 1;     // Catch: IOException -> L30
        goto L7
    L9:
        int r134 = r3.readInt();     // Catch: IOException -> L30
        int r43 = 0;
    L10:
        if (r43 >= r134) goto L12;
        r1.add(Float.valueOf(r3.readFloat()));     // Catch: IOException -> L30
        r43 = r43 + 1;     // Catch: IOException -> L30
        goto L10
    L12:
        int r135 = r3.readInt();     // Catch: IOException -> L30
        this.numFaces = r135;     // Catch: IOException -> L30
        this.normals = new float[r135 * 3];     // Catch: IOException -> L30
        this.textureCoordinates = new float[r135 * 2];     // Catch: IOException -> L30
        this.positions = new float[r135 * 3];     // Catch: IOException -> L30
        int r44 = 0;
        int r5 = 0;
        int r6 = 0;
    L13:
        if (r142 >= r135) goto L29;
        int r7 = r3.readInt() * 3;     // Catch: IOException -> L30
        this.positions[r44] = ((Float) r0.get(r7)).floatValue() * r15;     // Catch: IOException -> L30
        float[] r8 = this.positions;     // Catch: IOException -> L30
        int r11 = r44 + 2;     // Catch: IOException -> L30
        r8[r44 + 1] = ((Float) r0.get(r7 + 1)).floatValue() * r15;     // Catch: IOException -> L30
        r44 = r44 + 3;     // Catch: IOException -> L30
        this.positions[r11] = ((Float) r0.get(r7 + 2)).floatValue() * r15;     // Catch: IOException -> L30
        int r72 = r3.readInt() * 2;     // Catch: IOException -> L30
        float[] r82 = this.textureCoordinates;     // Catch: IOException -> L30
        int r9 = r5 + 1;     // Catch: IOException -> L30
        float r10 = 0.0f;
        if (r72 >= 0) goto L17;
    L20:
        float r112 = 0.0f;
    L21:
        r82[r5] = r112;     // Catch: IOException -> L30
        int r73 = r72 + 1;     // Catch: IOException -> L30
        float[] r83 = this.textureCoordinates;     // Catch: IOException -> L30
        r5 = r5 + 2;     // Catch: IOException -> L30
        if (r73 >= 0) goto L24;
    L27:
        r83[r9] = r10;     // Catch: IOException -> L30
        int r74 = r3.readInt() * 3;     // Catch: IOException -> L30
        this.normals[r6] = ((Float) r1.get(r74)).floatValue();     // Catch: IOException -> L30
        float[] r84 = this.normals;     // Catch: IOException -> L30
        int r113 = r6 + 2;     // Catch: IOException -> L30
        r84[r6 + 1] = ((Float) r1.get(r74 + 1)).floatValue();     // Catch: IOException -> L30
        r6 = r6 + 3;     // Catch: IOException -> L30
        this.normals[r113] = ((Float) r1.get(r74 + 2)).floatValue();     // Catch: IOException -> L30
        r142 = r142 + 1;
        goto L13
    L24:
        if (r73 >= r2.size()) goto L27;
        r10 = 1.0f - ((Float) r2.get(r73)).floatValue();     // Catch: IOException -> L30
        goto L27
    L17:
        if (r72 >= r2.size()) goto L20;
        r112 = ((Float) r2.get(r72)).floatValue();     // Catch: IOException -> L30
        goto L21
    L29:
        return;
    L30:
        e = move-exception;
        e.printStackTrace();
    }
}
