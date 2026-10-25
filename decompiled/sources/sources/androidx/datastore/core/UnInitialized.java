package androidx.datastore.core;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/datastore/core/UnInitialized;", "Landroidx/datastore/core/State;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UnInitialized extends State<Object> {
    public static final UnInitialized INSTANCE = null;

    static {
        INSTANCE = new UnInitialized();
    }

    private UnInitialized() {
        super(-1, null);
    }
}
