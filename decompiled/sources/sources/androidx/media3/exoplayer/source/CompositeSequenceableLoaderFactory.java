package androidx.media3.exoplayer.source;

import java.util.List;

/* loaded from: classes4.dex */
public interface CompositeSequenceableLoaderFactory {
    SequenceableLoader create(List<? extends SequenceableLoader> r1, List<List<Integer>> r2);

    SequenceableLoader empty();
}
