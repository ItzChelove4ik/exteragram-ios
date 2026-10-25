package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class CameraCaptureCallbacks {

    public static final class ComboCameraCaptureCallback extends CameraCaptureCallback {
        private final List<CameraCaptureCallback> mCallbacks;

        public ComboCameraCaptureCallback(List<CameraCaptureCallback> r3) {
            this.mCallbacks = new ArrayList();
            Iterator<CameraCaptureCallback> r32 = r3.iterator();
        L4:
            if (r32.hasNext() == false) goto L8;
            CameraCaptureCallback r0 = r32.next();
            if ((r0 instanceof NoOpCameraCaptureCallback) == true) goto L4;
            this.mCallbacks.add(r0);
            goto L4
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled(int r2) {
            Iterator<CameraCaptureCallback> r1 = this.mCallbacks.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onCaptureCancelled(r2);
            goto L4
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(int r2, CameraCaptureResult r3) {
            Iterator<CameraCaptureCallback> r1 = this.mCallbacks.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onCaptureCompleted(r2, r3);
            goto L4
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(int r2, CameraCaptureFailure r3) {
            Iterator<CameraCaptureCallback> r1 = this.mCallbacks.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onCaptureFailed(r2, r3);
            goto L4
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureProcessProgressed(int r2, int r3) {
            Iterator<CameraCaptureCallback> r1 = this.mCallbacks.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onCaptureProcessProgressed(r2, r3);
            goto L4
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureStarted(int r2) {
            Iterator<CameraCaptureCallback> r1 = this.mCallbacks.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().onCaptureStarted(r2);
            goto L4
        }
    }

    public static final class NoOpCameraCaptureCallback extends CameraCaptureCallback {
        public NoOpCameraCaptureCallback() {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(int r1, CameraCaptureResult r2) {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(int r1, CameraCaptureFailure r2) {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureStarted(int r1) {
        }
    }

    public static CameraCaptureCallback createComboCallback(List<CameraCaptureCallback> r2) {
        if (r2.isEmpty() == false) goto L7;
        return createNoOpCallback();
    L7:
        if (r2.size() != 1) goto L11;
        return r2.get(0);
    L11:
        return new ComboCameraCaptureCallback(r2);
    }

    public static CameraCaptureCallback createComboCallback(CameraCaptureCallback... r0) {
        return createComboCallback(Arrays.asList(r0));
    }

    public static CameraCaptureCallback createNoOpCallback() {
        return new NoOpCameraCaptureCallback();
    }
}
