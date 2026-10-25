package androidx.media3.exoplayer.source;

import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes4.dex */
public final class DefaultCompositeSequenceableLoaderFactory implements CompositeSequenceableLoaderFactory {
    public DefaultCompositeSequenceableLoaderFactory() {
    }

    @Override // androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory
    public SequenceableLoader create(List<? extends SequenceableLoader> r1, List<List<Integer>> r2) {
        return new CompositeSequenceableLoader(r1, r2);
    }

    @Override // androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory
    public SequenceableLoader empty() {
        return new CompositeSequenceableLoader(ImmutableList.of(), ImmutableList.of());
    }
}
