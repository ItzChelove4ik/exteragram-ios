package org.webrtc;

import android.content.Context;
import android.view.OrientationEventListener;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes6.dex */
public class OrientationHelper {
    private static final int ORIENTATION_HYSTERESIS = 5;
    public static volatile int cameraOrientation;
    public static volatile int cameraRotation;
    public static volatile boolean cameraRotationDisabled;
    private OrientationEventListener orientationEventListener;
    private int rotation;

    /* renamed from: org.webrtc.OrientationHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends OrientationEventListener {
        final /* synthetic */ OrientationHelper this$0;

        public AnonymousClass1(OrientationHelper r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int r3) {
            if (OrientationHelper.m22368$$Nest$fgetorientationEventListener(this.this$0) != null) goto L5;
            return;
        L5:
            if (r3 == (-1)) goto L13;
            OrientationHelper r0 = this.this$0;
            int r32 = OrientationHelper.m22371$$Nest$mroundOrientation(r0, r3, OrientationHelper.m22369$$Nest$fgetrotation(r0));
            if (r32 == OrientationHelper.m22369$$Nest$fgetrotation(this.this$0)) goto L12;
            OrientationHelper r2 = this.this$0;
            OrientationHelper.m22370$$Nest$fputrotation(r2, r32);
            r2.onOrientationUpdate(r32);
            return;
        L12:
            return;
        }
    }

    /* renamed from: -$$Nest$fgetorientationEventListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OrientationEventListener m22368$$Nest$fgetorientationEventListener(OrientationHelper r0) {
        return r0.orientationEventListener;
    }

    /* renamed from: -$$Nest$fgetrotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22369$$Nest$fgetrotation(OrientationHelper r0) {
        return r0.rotation;
    }

    /* renamed from: -$$Nest$fputrotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22370$$Nest$fputrotation(OrientationHelper r0, int r1) {
        r0.rotation = r1;
    }

    /* renamed from: -$$Nest$mroundOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m22371$$Nest$mroundOrientation(OrientationHelper r0, int r1, int r2) {
        return r0.roundOrientation(r1, r2);
    }

    public OrientationHelper() {
        this.orientationEventListener = new AnonymousClass1(this, ApplicationLoader.applicationContext);
    }

    private int roundOrientation(int r2, int r3) {
        if (r3 == (-1)) goto L8;
        int r1 = Math.abs(r2 - r3);
        if (Math.min(r1, 360 - r1) >= 50) goto L8;
        return r3;
    L8:
        return (((r2 + 45) / 90) * 90) % 360;
    }

    public int getOrientation() {
        if (cameraRotationDisabled == false) goto L7;
        return 0;
    L7:
        return this.rotation;
    }

    public void onOrientationUpdate(int r1) {
    }

    public void start() {
        boolean r0 = this.orientationEventListener.canDetectOrientation();
        OrientationEventListener r1 = this.orientationEventListener;
        if (r0 == false) goto L6;
        r1.enable();
        return;
    L6:
        r1.disable();
        this.orientationEventListener = null;
    }

    public void stop() {
        OrientationEventListener r0 = this.orientationEventListener;
        if (r0 == null) goto L6;
        r0.disable();
        this.orientationEventListener = null;
        return;
    }
}
