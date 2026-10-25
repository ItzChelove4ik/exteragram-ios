package androidx.media3.extractor.metadata;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public abstract class SimpleMetadataDecoder implements MetadataDecoder {
    public SimpleMetadataDecoder() {
    }

    @Override // androidx.media3.extractor.metadata.MetadataDecoder
    public final Metadata decode(MetadataInputBuffer r3) {
        ByteBuffer r0 = (ByteBuffer) Assertions.checkNotNull(r3.data);
        if (r0.position() == 0) goto L5;
    L9:
        boolean r1 = false;
    L10:
        Assertions.checkArgument(r1);
        return decode(r3, r0);
    L5:
        if (r0.hasArray() == false) goto L9;
        if (r0.arrayOffset() != 0) goto L9;
        r1 = true;
        goto L10
    }

    public abstract Metadata decode(MetadataInputBuffer r1, ByteBuffer r2);
}
