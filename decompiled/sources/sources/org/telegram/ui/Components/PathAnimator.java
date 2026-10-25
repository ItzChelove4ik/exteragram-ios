package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

/* loaded from: classes3.dex */
public class PathAnimator {
    private float durationScale;
    private ArrayList<KeyFrame> keyFrames;
    private Path path;
    private float pathTime;
    private float scale;
    private float tx;
    private float ty;

    public static class CurveTo {
        public float x;
        public float x1;
        public float x2;
        public float y;
        public float y1;
        public float y2;

        private CurveTo() {
        }

        public /* synthetic */ CurveTo(PathAnimatorIA r1) {
            this();
        }
    }

    public static class KeyFrame {
        public ArrayList<Object> commands;
        public float time;

        private KeyFrame() {
            this.commands = new ArrayList();
        }

        public /* synthetic */ KeyFrame(PathAnimatorIA r1) {
            this();
        }
    }

    public static class LineTo {
        public float x;
        public float y;

        private LineTo() {
        }

        public /* synthetic */ LineTo(PathAnimatorIA r1) {
            this();
        }
    }

    public static class MoveTo {
        public float x;
        public float y;

        private MoveTo() {
        }

        public /* synthetic */ MoveTo(PathAnimatorIA r1) {
            this();
        }
    }

    public PathAnimator(float r2, float r3, float r4, float r5) {
        this.path = new Path();
        this.pathTime = -1.0f;
        this.keyFrames = new ArrayList();
        this.scale = r2;
        this.tx = r3;
        this.ty = r4;
        this.durationScale = r5;
    }

    public void addSvgKeyFrame(String r7, float r8) {
        if (r7 != null) goto L23;
        return;
    L23:
        KeyFrame r0 = new KeyFrame(null);     // Catch: Exception -> L20
        r0.time = r8 * this.durationScale;     // Catch: Exception -> L20
        String[] r72 = r7.split(" ");     // Catch: Exception -> L20
        int r2 = 0;
    L6:
        if (r2 >= r72.length) goto L18;
        char r3 = r72[r2].charAt(0);     // Catch: Exception -> L20
        if (r3 != 'C') goto L10;
        CurveTo r32 = new CurveTo(null);     // Catch: Exception -> L20
        r32.x1 = (Float.parseFloat(r72[r2 + 1]) + this.tx) * this.scale;     // Catch: Exception -> L20
        r32.y1 = (Float.parseFloat(r72[r2 + 2]) + this.ty) * this.scale;     // Catch: Exception -> L20
        r32.x2 = (Float.parseFloat(r72[r2 + 3]) + this.tx) * this.scale;     // Catch: Exception -> L20
        r32.y2 = (Float.parseFloat(r72[r2 + 4]) + this.ty) * this.scale;     // Catch: Exception -> L20
        r32.x = (Float.parseFloat(r72[r2 + 5]) + this.tx) * this.scale;     // Catch: Exception -> L20
        r2 = r2 + 6;     // Catch: Exception -> L20
        r32.y = (Float.parseFloat(r72[r2]) + this.ty) * this.scale;     // Catch: Exception -> L20
        r0.commands.add(r32);     // Catch: Exception -> L20
    L17:
        r2 = r2 + 1;     // Catch: Exception -> L20
        goto L6
    L10:
        if (r3 != 'L') goto L12;
        LineTo r33 = new LineTo(null);     // Catch: Exception -> L20
        r33.x = (Float.parseFloat(r72[r2 + 1]) + this.tx) * this.scale;     // Catch: Exception -> L20
        r2 = r2 + 2;     // Catch: Exception -> L20
        r33.y = (Float.parseFloat(r72[r2]) + this.ty) * this.scale;     // Catch: Exception -> L20
        r0.commands.add(r33);     // Catch: Exception -> L20
        goto L17
    L12:
        if (r3 != 'M') goto L17;
        MoveTo r34 = new MoveTo(null);     // Catch: Exception -> L20
        r34.x = (Float.parseFloat(r72[r2 + 1]) + this.tx) * this.scale;     // Catch: Exception -> L20
        r2 = r2 + 2;     // Catch: Exception -> L20
        r34.y = (Float.parseFloat(r72[r2]) + this.ty) * this.scale;     // Catch: Exception -> L20
        r0.commands.add(r34);     // Catch: Exception -> L20
        goto L17
    L18:
        this.keyFrames.add(r0);     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        FileLog.e(e);
    }

    public void draw(Canvas r19, Paint r20, float r21) {
        if (this.pathTime == r21) goto L64;
        this.pathTime = r21;
        int r2 = this.keyFrames.size();
        int r3 = 0;
        int r5 = 0;
        KeyFrame r6 = null;
        KeyFrame r7 = null;
    L5:
        if (r5 >= r2) goto L20;
        KeyFrame r8 = this.keyFrames.get(r5);
        if (r7 == null) goto L11;
        if (r7.time < r8.time) goto L11;
    L13:
        if (r6 == null) goto L17;
        if (r6.time > r8.time) goto L17;
    L19:
        r5 = r5 + 1;
    L17:
        if (r8.time < r21) goto L19;
        r6 = r8;
    L11:
        if (r8.time > r21) goto L13;
        r7 = r8;
        goto L13
    L20:
        if (r6 != r7) goto L22;
        r7 = null;
    L22:
        if (r7 == null) goto L25;
        if (r6 != null) goto L25;
        r6 = r7;
        r7 = null;
    L25:
        if (r6 == null) goto L63;
        if (r7 != null) goto L28;
    L30:
        this.path.reset();
        int r22 = r6.commands.size();
    L31:
        if (r3 >= r22) goto L62;
        if (r7 == null) goto L34;
        Object r52 = r7.commands.get(r3);
    L35:
        Object r82 = r6.commands.get(r3);
        if (r52 != null) goto L38;
    L40:
        if (r7 == null) goto L42;
        float r9 = r7.time;
        float r10 = (r21 - r9) / (r6.time - r9);
    L44:
        if ((r82 instanceof MoveTo) == false) goto L50;
        MoveTo r83 = (MoveTo) r82;
        MoveTo r53 = (MoveTo) r52;
        Path r92 = this.path;
        if (r53 == null) goto L48;
        float r11 = r53.x;
        float r112 = AndroidUtilities.dpf2(r11 + ((r83.x - r11) * r10));
        float r54 = r53.y;
        r92.moveTo(r112, AndroidUtilities.dpf2(r54 + ((r83.y - r54) * r10)));
    L61:
        r3 = r3 + 1;
        goto L31
    L48:
        r92.moveTo(AndroidUtilities.dpf2(r83.x), AndroidUtilities.dpf2(r83.y));
        goto L61
    L50:
        if ((r82 instanceof LineTo) == false) goto L56;
        LineTo r84 = (LineTo) r82;
        LineTo r55 = (LineTo) r52;
        Path r93 = this.path;
        if (r55 == null) goto L54;
        float r113 = r55.x;
        float r114 = AndroidUtilities.dpf2(r113 + ((r84.x - r113) * r10));
        float r56 = r55.y;
        r93.lineTo(r114, AndroidUtilities.dpf2(r56 + ((r84.y - r56) * r10)));
        goto L61
    L54:
        r93.lineTo(AndroidUtilities.dpf2(r84.x), AndroidUtilities.dpf2(r84.y));
        goto L61
    L56:
        if ((r82 instanceof CurveTo) == false) goto L61;
        CurveTo r85 = (CurveTo) r82;
        CurveTo r57 = (CurveTo) r52;
        Path r115 = this.path;
        if (r57 == null) goto L60;
        float r94 = r57.x1;
        float r12 = AndroidUtilities.dpf2(r94 + ((r85.x1 - r94) * r10));
        float r95 = r57.y1;
        float r13 = AndroidUtilities.dpf2(r95 + ((r85.y1 - r95) * r10));
        float r96 = r57.x2;
        float r14 = AndroidUtilities.dpf2(r96 + ((r85.x2 - r96) * r10));
        float r97 = r57.y2;
        float r15 = AndroidUtilities.dpf2(r97 + ((r85.y2 - r97) * r10));
        float r98 = r57.x;
        float r16 = AndroidUtilities.dpf2(r98 + ((r85.x - r98) * r10));
        float r4 = r57.y;
        r115.cubicTo(r12, r13, r14, r15, r16, AndroidUtilities.dpf2(r4 + ((r85.y - r4) * r10)));
        goto L61
    L60:
        r115.cubicTo(AndroidUtilities.dpf2(r85.x1), AndroidUtilities.dpf2(r85.y1), AndroidUtilities.dpf2(r85.x2), AndroidUtilities.dpf2(r85.y2), AndroidUtilities.dpf2(r85.x), AndroidUtilities.dpf2(r85.y));
        goto L61
    L42:
        r10 = 1.0f;
        goto L44
    L38:
        if (r52.getClass() == r82.getClass()) goto L40;
        return;
    L34:
        r52 = null;
        goto L35
    L62:
        this.path.close();
        goto L64
    L28:
        if (r7.commands.size() == r6.commands.size()) goto L30;
        return;
    L63:
        return;
    L64:
        r19.drawPath(this.path, r20);
    }
}
