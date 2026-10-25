package androidx.media3.extractor.avi;

import androidx.media3.common.Format;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
final class StreamFormatChunk implements AviChunk {
    public final Format format;

    public StreamFormatChunk(Format r1) {
        this.format = r1;
    }

    private static String getMimeTypeFromCompression(int r0) {
        switch(r0) {
            case 808802372: goto L13;
            case 826496577: goto L11;
            case 828601953: goto L11;
            case 842289229: goto L9;
            case 859066445: goto L7;
            case 875967048: goto L11;
            case 877677894: goto L13;
            case 1145656883: goto L13;
            case 1145656920: goto L13;
            case 1196444237: goto L5;
            case 1482049860: goto L13;
            case 1684633208: goto L13;
            case 1735420525: goto L5;
            case 2021026148: goto L13;
            default: goto L3;
        };
    L3:
        return null;
    L5:
        return "video/mjpeg";
    L7:
        return "video/mp43";
    L9:
        return "video/mp42";
    L11:
        return MediaController.VIDEO_MIME_TYPE;
    L13:
        return "video/mp4v-es";
    }

    private static String getMimeTypeFromTag(int r1) {
        if (r1 != 1) goto L5;
        return "audio/raw";
    L5:
        if (r1 != 85) goto L7;
        return "audio/mpeg";
    L7:
        if (r1 != 255) goto L9;
        return MediaController.AUDIO_MIME_TYPE;
    L9:
        if (r1 != 8192) goto L11;
        return "audio/ac3";
    L11:
        if (r1 == 8193) goto L14;
        return null;
    L14:
        return "audio/vnd.dts";
    }

    private static AviChunk parseBitmapInfoHeader(ParsableByteArray r3) {
        r3.skipBytes(4);
        int r1 = r3.readLittleEndianInt();
        int r2 = r3.readLittleEndianInt();
        r3.skipBytes(4);
        int r32 = r3.readLittleEndianInt();
        String r0 = getMimeTypeFromCompression(r32);
        if (r0 != null) goto L6;
        Log.w("StreamFormatChunk", "Ignoring track with unsupported compression " + r32);
        return null;
    L6:
        Format.Builder r33 = new Format.Builder();
        r33.setWidth(r1).setHeight(r2).setSampleMimeType(r0);
        return new StreamFormatChunk(r33.build());
    }

    public static AviChunk parseFrom(int r1, ParsableByteArray r2) {
        if (r1 != 2) goto L7;
        return parseBitmapInfoHeader(r2);
    L7:
        if (r1 == 1) goto L9;
        Log.w("StreamFormatChunk", "Ignoring strf box for unsupported track type: " + Util.getTrackTypeString(r1));
        return null;
    L9:
        return parseWaveFormatEx(r2);
    }

    private static AviChunk parseWaveFormatEx(ParsableByteArray r8) {
        int r0 = r8.readLittleEndianUnsignedShort();
        String r1 = getMimeTypeFromTag(r0);
        if (r1 != null) goto L6;
        Log.w("StreamFormatChunk", "Ignoring track with unsupported format tag " + r0);
        return null;
    L6:
        int r02 = r8.readLittleEndianUnsignedShort();
        int r2 = r8.readLittleEndianInt();
        r8.skipBytes(6);
        int r3 = Util.getPcmEncoding(r8.readLittleEndianUnsignedShort());
        if (r8.bytesLeft() <= 0) goto L9;
        int r4 = r8.readLittleEndianUnsignedShort();
    L10:
        Format.Builder r6 = new Format.Builder();
        r6.setSampleMimeType(r1).setChannelCount(r02).setSampleRate(r2);
        if (r1.equals("audio/raw") == false) goto L15;
        if (r3 == 0) goto L15;
        r6.setPcmEncoding(r3);
    L15:
        if (r1.equals(MediaController.AUDIO_MIME_TYPE) == false) goto L19;
        if (r4 <= 0) goto L19;
        byte[] r03 = new byte[r4];
        r8.readBytes(r03, 0, r4);
        r6.setInitializationData(ImmutableList.of(r03));
    L19:
        return new StreamFormatChunk(r6.build());
    L9:
        r4 = 0;
        goto L10
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return 1718776947;
    }
}
