package androidx.work;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0005\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "className", "Landroidx/work/InputMerger;", "fromClassName", "(Ljava/lang/String;)Landroidx/work/InputMerger;", "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class InputMergerKt {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("InputMerger");
    }

    public static final InputMerger fromClassName(String r6) {
        return (InputMerger) Class.forName(r6).getDeclaredConstructor(null).newInstance(null);
    L5:
        e = move-exception;
        Logger.get().error(TAG, "Trouble instantiating " + r6, e);
        return null;
    }
}
