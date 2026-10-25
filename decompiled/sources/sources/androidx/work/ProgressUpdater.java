package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* loaded from: classes4.dex */
public interface ProgressUpdater {
    ListenableFuture<Void> updateProgress(Context r1, UUID r2, Data r3);
}
