package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class DefaultTsPayloadReaderFactory implements TsPayloadReader.Factory {
    private final List<Format> closedCaptionFormats;
    private final int flags;

    public DefaultTsPayloadReaderFactory(int r2) {
        this(r2, ImmutableList.of());
    }

    public DefaultTsPayloadReaderFactory(int r1, List<Format> r2) {
        this.flags = r1;
        this.closedCaptionFormats = r2;
    }

    private SeiReader buildSeiReader(TsPayloadReader.EsInfo r2) {
        return new SeiReader(getClosedCaptionFormats(r2), "video/mp2t");
    }

    private UserDataReader buildUserDataReader(TsPayloadReader.EsInfo r2) {
        return new UserDataReader(getClosedCaptionFormats(r2), "video/mp2t");
    }

    private List<Format> getClosedCaptionFormats(TsPayloadReader.EsInfo r11) {
        if (isSet(32) == true) goto L5;
        ParsableByteArray r0 = new ParsableByteArray(r11.descriptorBytes);
        List<Format> r10 = this.closedCaptionFormats;
    L8:
        if (r0.bytesLeft() <= 0) goto L30;
        int r112 = r0.readUnsignedByte();
        int r2 = r0.getPosition() + r0.readUnsignedByte();
        if (r112 != 134) goto L29;
        r10 = new ArrayList();
        int r113 = r0.readUnsignedByte() & 31;
        int r3 = 0;
    L12:
        if (r3 >= r113) goto L29;
        String r4 = r0.readString(3);
        int r5 = r0.readUnsignedByte();
        boolean r7 = true;
        if ((r5 & 128) == 0) goto L16;
        boolean r6 = true;
    L17:
        if (r6 == false) goto L19;
        int r52 = r5 & 63;
        String r8 = "application/cea-708";
    L20:
        byte r9 = (byte) r0.readUnsignedByte();
        r0.skipBytes(1);
        if (r6 == true) goto L23;
        List<byte[]> r62 = null;
    L28:
        r10.add(new Format.Builder().setSampleMimeType(r8).setLanguage(r4).setAccessibilityChannel(r52).setInitializationData(r62).build());
        r3 = r3 + 1;
        goto L12
    L23:
        if ((r9 & 64) != 0) goto L26;
        r7 = false;
    L26:
        r62 = CodecSpecificDataUtil.buildCea708InitializationData(r7);
        goto L28
    L19:
        r8 = "application/cea-608";
        r52 = 1;
        goto L20
    L16:
        r6 = false;
    L29:
        r0.setPosition(r2);
        goto L8
    L30:
        return r10;
    L5:
        return this.closedCaptionFormats;
    }

    private boolean isSet(int r1) {
        if ((this.flags & r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader.Factory
    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray();
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader.Factory
    public TsPayloadReader createPayloadReader(int r6, TsPayloadReader.EsInfo r7) {
        if (r6 == 2) goto L76;
        if (r6 == 3) goto L74;
        if (r6 == 4) goto L74;
        if (r6 == 21) goto L72;
        if (r6 == 27) goto L67;
        if (r6 == 36) goto L65;
        if (r6 == 45) goto L63;
        if (r6 == 89) goto L61;
        if (r6 == 172) goto L59;
        if (r6 == 257) goto L57;
        if (r6 == 138) goto L55;
        if (r6 == 139) goto L53;
        switch(r6) {
            case 15: goto L48;
            case 16: goto L46;
            case 17: goto L41;
            default: goto L27;
        };
    L27:
        switch(r6) {
            case 128: goto L76;
            case 129: goto L39;
            case 130: goto L36;
            default: goto L28;
        };
    L28:
        switch(r6) {
            case 134: goto L31;
            case 135: goto L39;
            case 136: goto L55;
            default: goto L29;
        };
    L29:
        return null;
    L31:
        if (isSet(16) == false) goto L34;
        return null;
    L34:
        return new SectionReader(new PassthroughSectionPayloadReader("application/x-scte35", "video/mp2t"));
    L36:
        if (isSet(64) == true) goto L55;
        return null;
    L39:
        return new PesReader(new Ac3Reader(r7.language, r7.getRoleFlags(), "video/mp2t"));
    L41:
        if (isSet(2) == false) goto L44;
        return null;
    L44:
        return new PesReader(new LatmReader(r7.language, r7.getRoleFlags(), "video/mp2t"));
    L46:
        return new PesReader(new H263Reader(buildUserDataReader(r7), "video/mp2t"));
    L48:
        if (isSet(2) == false) goto L51;
        return null;
    L51:
        return new PesReader(new AdtsReader(false, r7.language, r7.getRoleFlags(), "video/mp2t"));
    L53:
        return new PesReader(new DtsReader(r7.language, r7.getRoleFlags(), 5408, "video/mp2t"));
    L55:
        return new PesReader(new DtsReader(r7.language, r7.getRoleFlags(), 4096, "video/mp2t"));
    L57:
        return new SectionReader(new PassthroughSectionPayloadReader("application/vnd.dvb.ait", "video/mp2t"));
    L59:
        return new PesReader(new Ac4Reader(r7.language, r7.getRoleFlags(), "video/mp2t"));
    L61:
        return new PesReader(new DvbSubtitleReader(r7.dvbSubtitleInfos, "video/mp2t"));
    L63:
        return new PesReader(new MpeghReader("video/mp2t"));
    L65:
        return new PesReader(new H265Reader(buildSeiReader(r7), "video/mp2t"));
    L67:
        if (isSet(4) == false) goto L70;
        return null;
    L70:
        return new PesReader(new H264Reader(buildSeiReader(r7), isSet(1), isSet(8), "video/mp2t"));
    L72:
        return new PesReader(new Id3Reader("video/mp2t"));
    L74:
        return new PesReader(new MpegAudioReader(r7.language, r7.getRoleFlags(), "video/mp2t"));
    L76:
        return new PesReader(new H262Reader(buildUserDataReader(r7), "video/mp2t"));
    }
}
