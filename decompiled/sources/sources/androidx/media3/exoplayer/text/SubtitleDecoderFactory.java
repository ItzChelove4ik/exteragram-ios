package androidx.media3.exoplayer.text;

import androidx.media3.common.Format;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.cea.Cea608Decoder;
import androidx.media3.extractor.text.cea.Cea708Decoder;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Objects;

/* loaded from: classes4.dex */
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = null;

    /* renamed from: androidx.media3.exoplayer.text.SubtitleDecoderFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements SubtitleDecoderFactory {
        private final DefaultSubtitleParserFactory delegate;

        public AnonymousClass1() {
            this.delegate = new DefaultSubtitleParserFactory();
        }

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format r4) {
            String r0 = r4.sampleMimeType;
            if (r0 == null) goto L26;
            char r2 = 65535;
            switch(r0.hashCode()) {
                case 930165504: goto L16;
                case 1566015601: goto L12;
                case 1566016562: goto L8;
                default: goto L19;
            };
        L19:
            switch(r2) {
                case 0: goto L24;
                case 1: goto L24;
                case 2: goto L22;
                default: goto L26;
            };
        L22:
            return new Cea708Decoder(r4.accessibilityChannel, r4.initializationData);
        L24:
            return new Cea608Decoder(r0, r4.accessibilityChannel, 16000);
        L8:
            if (r0.equals("application/cea-708") == false) goto L19;
            r2 = 2;
            goto L19
        L12:
            if (r0.equals("application/cea-608") == false) goto L19;
            r2 = 1;
            goto L19
        L16:
            if (r0.equals("application/x-mp4-cea-608") == false) goto L19;
            r2 = 0;
        L26:
            if (this.delegate.supportsFormat(r4) == false) goto L29;
            SubtitleParser r3 = this.delegate.create(r4);
            return new DelegatingSubtitleDecoder(r3.getClass().getSimpleName().concat("Decoder"), r3);
        L29:
            Native$$ExternalSyntheticBUOutline5.m("Attempted to create decoder for unsupported MIME type: ", r0);
            return null;
        }

        @Override // androidx.media3.exoplayer.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format r2) {
            String r0 = r2.sampleMimeType;
            if (this.delegate.supportsFormat(r2) == false) goto L5;
            return true;
        L5:
            if (Objects.equals(r0, "application/cea-608") == false) goto L7;
            return true;
        L7:
            if (Objects.equals(r0, "application/x-mp4-cea-608") == false) goto L9;
            return true;
        L9:
            if (Objects.equals(r0, "application/cea-708") == true) goto L17;
            return false;
        L17:
            return true;
        }
    }

    static {
        DEFAULT = new AnonymousClass1();
    }

    SubtitleDecoder createDecoder(Format r1);

    boolean supportsFormat(Format r1);
}
