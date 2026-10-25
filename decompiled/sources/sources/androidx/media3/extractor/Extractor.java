package androidx.media3.extractor;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* loaded from: classes4.dex */
public interface Extractor {
    default List<SniffFailure> getSniffFailureDetails() {
        return ImmutableList.of();
    }

    @SideEffectFree
    default Extractor getUnderlyingImplementation() {
        return this;
    }

    void init(ExtractorOutput r1);

    int read(ExtractorInput r1, PositionHolder r2);

    void release();

    void seek(long r1, long r3);

    boolean sniff(ExtractorInput r1);
}
