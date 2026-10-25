package androidx.media3.extractor.metadata.emsg;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class EventMessageDecoder extends SimpleMetadataDecoder {
    public EventMessageDecoder() {
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer r3, ByteBuffer r4) {
        return new Metadata(new Metadata.Entry[]{decode(new ParsableByteArray(r4.array(), r4.limit()))});
    }

    public EventMessage decode(ParsableByteArray r9) {
        return new EventMessage((String) Assertions.checkNotNull(r9.readNullTerminatedString()), (String) Assertions.checkNotNull(r9.readNullTerminatedString()), r9.readLong(), r9.readLong(), Arrays.copyOfRange(r9.getData(), r9.getPosition(), r9.limit()));
    }
}
