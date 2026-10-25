package androidx.media3.common;

import android.content.Context;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public interface VideoGraph {

    public interface Factory {
        VideoGraph create(Context r1, ColorInfo r2, DebugViewProvider r3, Listener r4, Executor r5, long r6, boolean r8);
    }

    public interface Listener {
    }
}
