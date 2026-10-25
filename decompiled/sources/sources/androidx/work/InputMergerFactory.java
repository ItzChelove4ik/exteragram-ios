package androidx.work;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\t"}, d2 = {"Landroidx/work/InputMergerFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "createInputMerger", "Landroidx/work/InputMerger;", "className", _UrlKt.FRAGMENT_ENCODE_SET, "createInputMergerWithDefaultFallback", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class InputMergerFactory {
    public InputMergerFactory() {
    }

    public abstract InputMerger createInputMerger(String r1);

    public final InputMerger createInputMergerWithDefaultFallback(String r1) {
        InputMerger r0 = createInputMerger(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return InputMergerKt.fromClassName(r1);
    }
}
