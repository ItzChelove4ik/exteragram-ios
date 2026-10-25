package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0086\u0080\u0004¨\u0006\t"}, d2 = {"Lkotlinx/serialization/json/internal/CharArrayPool;", "Lkotlinx/serialization/json/internal/CharArrayPoolBase;", "<init>", "()V", "take", _UrlKt.FRAGMENT_ENCODE_SET, BuildConfig.BUILD_TYPE, _UrlKt.FRAGMENT_ENCODE_SET, "array", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CharArrayPool extends CharArrayPoolBase {
    public static final CharArrayPool INSTANCE = null;

    static {
        INSTANCE = new CharArrayPool();
    }

    private CharArrayPool() {
    }

    public final void release(char[] r1) {
        releaseImpl(r1);
    }

    public final char[] take() {
        return super.take(128);
    }
}
