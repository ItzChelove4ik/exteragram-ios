package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* loaded from: classes4.dex */
public interface ForegroundUpdater {
    ListenableFuture<Void> setForegroundAsync(Context r1, UUID r2, ForegroundInfo r3);
}
