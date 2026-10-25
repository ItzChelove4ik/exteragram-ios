package androidx.media3.extractor.metadata.dvbsi;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class AppInfoTableDecoder extends SimpleMetadataDecoder {
    public AppInfoTableDecoder() {
    }

    private static Metadata parseAit(ParsableBitArray r14) {
        r14.skipBits(12);
        int r2 = (r14.getBytePosition() + r14.readBits(12)) - 4;
        r14.skipBits(44);
        r14.skipBytes(r14.readBits(12));
        r14.skipBits(16);
        ArrayList r4 = new ArrayList();
    L3:
        String r6 = null;
        if (r14.getBytePosition() >= r2) goto L26;
        r14.skipBits(48);
        int r7 = r14.readBits(8);
        r14.skipBits(4);
        int r9 = r14.getBytePosition() + r14.readBits(12);
        String r8 = null;
    L7:
        if (r14.getBytePosition() >= r9) goto L21;
        int r10 = r14.readBits(8);
        int r11 = r14.readBits(8);
        int r12 = r14.getBytePosition() + r11;
        if (r10 != 2) goto L18;
        int r102 = r14.readBits(16);
        r14.skipBits(8);
        if (r102 != 3) goto L20;
    L13:
        if (r14.getBytePosition() >= r12) goto L20;
        r6 = r14.readBytesAsString(r14.readBits(8), StandardCharsets.US_ASCII);
        int r103 = r14.readBits(8);
        int r112 = 0;
    L15:
        if (r112 >= r103) goto L13;
        r14.skipBytes(r14.readBits(8));
        r112 = r112 + 1;
    L20:
        r14.setPosition(r12 * 8);
        goto L7
    L18:
        if (r10 != 21) goto L20;
        r8 = r14.readBytesAsString(r11, StandardCharsets.US_ASCII);
        goto L20
    L21:
        r14.setPosition(r9 * 8);
        if (r6 == null) goto L3;
        if (r8 == null) goto L3;
        r4.add(new AppInfoTable(r7, r6.concat(r8)));
        goto L3
    L26:
        if (r4.isEmpty() == false) goto L29;
        return null;
    L29:
        return new Metadata(r4);
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer r1, ByteBuffer r2) {
        if (r2.get() == 116) goto L5;
        return null;
    L5:
        return parseAit(new ParsableBitArray(r2.array(), r2.limit()));
    }
}
