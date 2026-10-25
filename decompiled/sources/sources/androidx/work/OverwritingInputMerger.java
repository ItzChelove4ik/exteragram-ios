package androidx.work;

import androidx.work.Data;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016¨\u0006\b"}, d2 = {"Landroidx/work/OverwritingInputMerger;", "Landroidx/work/InputMerger;", "<init>", "()V", "merge", "Landroidx/work/Data;", "inputs", _UrlKt.FRAGMENT_ENCODE_SET, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OverwritingInputMerger extends InputMerger {
    public OverwritingInputMerger() {
    }

    @Override // androidx.work.InputMerger
    public Data merge(List<Data> r3) {
        Data.Builder r2 = new Data.Builder();
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<Data> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        r0.putAll(r32.next().getKeyValueMap());
        goto L4
    L6:
        r2.putAll(r0);
        return r2.build();
    }
}
