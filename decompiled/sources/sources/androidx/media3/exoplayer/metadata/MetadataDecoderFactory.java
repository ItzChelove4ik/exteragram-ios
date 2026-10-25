package androidx.media3.exoplayer.metadata;

import androidx.media3.common.Format;
import androidx.media3.extractor.metadata.MetadataDecoder;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import androidx.media3.extractor.metadata.icy.IcyDecoder;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.scte35.SpliceInfoDecoder;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;

/* loaded from: classes4.dex */
public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = null;

    /* renamed from: androidx.media3.exoplayer.metadata.MetadataDecoderFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements MetadataDecoderFactory {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public MetadataDecoder createDecoder(Format r2) {
            String r1 = r2.sampleMimeType;
            if (r1 == null) goto L39;
            char r0 = 65535;
            switch(r1.hashCode()) {
                case -1354451219: goto L24;
                case -1348231605: goto L20;
                case -1248341703: goto L16;
                case 1154383568: goto L12;
                case 1652648887: goto L8;
                default: goto L27;
            };
        L27:
            switch(r0) {
                case 0: goto L38;
                case 1: goto L36;
                case 2: goto L34;
                case 3: goto L32;
                case 4: goto L30;
                default: goto L39;
            };
        L30:
            return new SpliceInfoDecoder();
        L32:
            return new EventMessageDecoder();
        L34:
            return new Id3Decoder();
        L36:
            return new IcyDecoder();
        L38:
            return new AppInfoTableDecoder();
        L8:
            if (r1.equals("application/x-scte35") == false) goto L27;
            r0 = 4;
            goto L27
        L12:
            if (r1.equals("application/x-emsg") == false) goto L27;
            r0 = 3;
            goto L27
        L16:
            if (r1.equals("application/id3") == false) goto L27;
            r0 = 2;
            goto L27
        L20:
            if (r1.equals("application/x-icy") == false) goto L27;
            r0 = 1;
            goto L27
        L24:
            if (r1.equals("application/vnd.dvb.ait") == false) goto L27;
            r0 = 0;
        L39:
            Native$$ExternalSyntheticBUOutline5.m("Attempted to create decoder for unsupported MIME type: ", r1);
            return null;
        }

        @Override // androidx.media3.exoplayer.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format r1) {
            String r0 = r1.sampleMimeType;
            if ("application/id3".equals(r0) == false) goto L5;
            return true;
        L5:
            if ("application/x-emsg".equals(r0) == false) goto L7;
            return true;
        L7:
            if ("application/x-scte35".equals(r0) == false) goto L9;
            return true;
        L9:
            if ("application/x-icy".equals(r0) == false) goto L11;
            return true;
        L11:
            if ("application/vnd.dvb.ait".equals(r0) == true) goto L20;
            return false;
        L20:
            return true;
        }
    }

    static {
        DEFAULT = new AnonymousClass1();
    }

    MetadataDecoder createDecoder(Format r1);

    boolean supportsFormat(Format r1);
}
