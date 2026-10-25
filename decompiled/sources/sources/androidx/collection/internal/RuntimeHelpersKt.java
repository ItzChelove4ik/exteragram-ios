package androidx.collection.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u0017\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0004\u001a\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "message", _UrlKt.FRAGMENT_ENCODE_SET, "throwIllegalStateException", "(Ljava/lang/String;)V", "throwIndexOutOfBoundsException", "throwNoSuchElementException", "throwIllegalArgumentException", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class RuntimeHelpersKt {
    public static final void throwIllegalArgumentException(String r1) {
        throw new IllegalArgumentException(r1);
    }

    public static final void throwIllegalStateException(String r1) {
        throw new IllegalStateException(r1);
    }

    public static final void throwIndexOutOfBoundsException(String r1) {
        throw new IndexOutOfBoundsException(r1);
    }

    public static final void throwNoSuchElementException(String r1) {
        throw new NoSuchElementException(r1);
    }
}
