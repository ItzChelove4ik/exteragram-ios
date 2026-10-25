package androidx.media3.extractor.metadata.emsg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class EventMessage implements Metadata.Entry {
    private static final Format ID3_FORMAT = null;
    private static final Format SCTE35_FORMAT = null;
    public final long durationMs;
    private int hashCode;
    public final long id;
    public final byte[] messageData;
    public final String schemeIdUri;
    public final String value;

    static {
        ID3_FORMAT = new Format.Builder().setSampleMimeType("application/id3").build();
        SCTE35_FORMAT = new Format.Builder().setSampleMimeType("application/x-scte35").build();
    }

    public EventMessage(String r1, String r2, long r3, long r5, byte[] r7) {
        this.schemeIdUri = r1;
        this.value = r2;
        this.durationMs = r3;
        this.id = r5;
        this.messageData = r7;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L21:
        return false;
    L8:
        if (EventMessage.class != r7.getClass()) goto L21;
        EventMessage r72 = (EventMessage) r7;
        if (this.durationMs != r72.durationMs) goto L21;
        if (this.id != r72.id) goto L21;
        if (Objects.equals(this.schemeIdUri, r72.schemeIdUri) == false) goto L21;
        if (Objects.equals(this.value, r72.value) == false) goto L21;
        if (Arrays.equals(this.messageData, r72.messageData) == false) goto L21;
        return true;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public byte[] getWrappedMetadataBytes() {
        if (getWrappedMetadataFormat() != null) goto L5;
        return null;
    L5:
        return this.messageData;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public Format getWrappedMetadataFormat() {
        String r2 = this.schemeIdUri;
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1468477611: goto L14;
            case -795945609: goto L10;
            case 1303648457: goto L6;
            default: goto L17;
        };
    L17:
        switch(r1) {
            case 0: goto L23;
            case 1: goto L21;
            case 2: goto L21;
            default: goto L18;
        };
    L18:
        return null;
    L21:
        return ID3_FORMAT;
    L23:
        return SCTE35_FORMAT;
    L6:
        if (r2.equals("https://developer.apple.com/streaming/emsg-id3") == false) goto L17;
        r1 = 2;
        goto L17
    L10:
        if (r2.equals("https://aomedia.org/emsg/ID3") == false) goto L17;
        r1 = 1;
        goto L17
    L14:
        if (r2.equals("urn:scte:scte35:2014:bin") == false) goto L17;
        r1 = 0;
        goto L17
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L13;
        String r0 = this.schemeIdUri;
        int r1 = 0;
        if (r0 == null) goto L7;
        int r02 = r0.hashCode();
    L8:
        int r2 = (527 + r02) * 31;
        String r03 = this.value;
        if (r03 == null) goto L11;
        r1 = r03.hashCode();
    L11:
        long r04 = this.durationMs;
        int r22 = (((r2 + r1) * 31) + ((int) (r04 ^ (r04 >>> 32)))) * 31;
        long r05 = this.id;
        this.hashCode = ((r22 + ((int) (r05 ^ (r05 >>> 32)))) * 31) + Arrays.hashCode(this.messageData);
        goto L13
    L7:
        r02 = 0;
    L13:
        return this.hashCode;
    }

    public String toString() {
        return "EMSG: scheme=" + this.schemeIdUri + ", id=" + this.id + ", durationMs=" + this.durationMs + ", value=" + this.value;
    }
}
