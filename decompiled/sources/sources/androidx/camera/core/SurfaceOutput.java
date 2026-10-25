package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraInternal;
import androidx.core.util.Consumer;
import java.io.Closeable;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public interface SurfaceOutput extends Closeable {

    public static abstract class CameraInputInfo {
        public CameraInputInfo() {
        }

        public static CameraInputInfo of(Size r6, Rect r7, CameraInternal r8, int r9, boolean r10) {
            return new AutoValue_SurfaceOutput_CameraInputInfo(r6, r7, r8, r9, r10);
        }

        public abstract CameraInternal getCameraInternal();

        public abstract Rect getInputCropRect();

        public abstract Size getInputSize();

        public abstract boolean getMirroring();

        public abstract int getRotationDegrees();
    }

    public static abstract class Event {
        public Event() {
        }

        public static Event of(int r1, SurfaceOutput r2) {
            return new AutoValue_SurfaceOutput_Event(r1, r2);
        }

        public abstract int getEventCode();

        public abstract SurfaceOutput getSurfaceOutput();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    int getFormat();

    Size getSize();

    Surface getSurface(Executor r1, Consumer<Event> r2);

    void updateTransformMatrix(float[] r1, float[] r2);

    void updateTransformMatrix(float[] r1, float[] r2, boolean r3);
}
