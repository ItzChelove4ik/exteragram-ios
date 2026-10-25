package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Looper;
import androidx.media3.common.util.Clock;

/* loaded from: classes4.dex */
public interface SuitableOutputChecker {

    public interface Callback {
        void onSelectedOutputSuitabilityChanged(boolean r1);
    }

    void disable();

    void enable(Callback r1, Context r2, Looper r3, Looper r4, Clock r5);

    boolean isSelectedOutputSuitableForPlayback();
}
