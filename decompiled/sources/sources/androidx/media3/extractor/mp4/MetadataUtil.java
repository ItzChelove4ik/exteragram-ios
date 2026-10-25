package androidx.media3.extractor.mp4;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.metadata.id3.ApicFrame;
import androidx.media3.extractor.metadata.id3.CommentFrame;
import androidx.media3.extractor.metadata.id3.Id3Frame;
import androidx.media3.extractor.metadata.id3.Id3Util;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import com.google.common.collect.ImmutableList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.TranslateController;

/* loaded from: classes4.dex */
abstract class MetadataUtil {
    public static MdtaMetadataEntry findMdtaMetadataEntryWithKey(Metadata r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.length()) goto L11;
        Metadata.Entry r1 = r3.get(r0);
        if ((r1 instanceof MdtaMetadataEntry) == false) goto L10;
        MdtaMetadataEntry r12 = (MdtaMetadataEntry) r1;
        if (r12.key.equals(r4) == false) goto L10;
        return r12;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return null;
    }

    private static CommentFrame parseCommentAttribute(int r3, ParsableByteArray r4) {
        int r0 = r4.readInt();
        if (r4.readInt() != 1684108385) goto L6;
        r4.skipBytes(8);
        String r32 = r4.readNullTerminatedString(r0 - 16);
        return new CommentFrame(TranslateController.UNKNOWN_LANGUAGE, r32, r32);
    L6:
        Log.w("MetadataUtil", "Failed to parse comment attribute: " + Mp4Box.getBoxTypeString(r3));
        return null;
    }

    private static ApicFrame parseCoverArt(ParsableByteArray r5) {
        int r0 = r5.readInt();
        if (r5.readInt() != 1684108385) goto L16;
        int r1 = BoxParser.parseFullBoxFlags(r5.readInt());
        if (r1 != 13) goto L8;
        String r2 = "image/jpeg";
    L11:
        if (r2 != null) goto L14;
        Log.w("MetadataUtil", "Unrecognized cover art flags: " + r1);
        return null;
    L14:
        r5.skipBytes(4);
        int r02 = r0 - 16;
        byte[] r12 = new byte[r02];
        r5.readBytes(r12, 0, r02);
        return new ApicFrame(r2, null, 3, r12);
    L8:
        if (r1 != 14) goto L10;
        r2 = "image/png";
        goto L11
    L10:
        r2 = null;
        goto L11
    L16:
        Log.w("MetadataUtil", "Failed to parse cover art attribute");
        return null;
    }

    public static Metadata.Entry parseIlstElement(ParsableByteArray r6) {
        int r1 = r6.getPosition() + r6.readInt();
        int r2 = r6.readInt();
        int r3 = (r2 >> 24) & 255;
        if (r3 != 169) goto L5;
    L94:
        int r32 = 16777215 & r2;
        if (r32 != 6516084) goto L100;
        CommentFrame r0 = parseCommentAttribute(r2, r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r0;
    L100:
        if (r32 != 7233901) goto L102;
    L150:
        TextInformationFrame r02 = parseTextAttribute(r2, "TIT2", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r02;
    L102:
        if (r32 == 7631467) goto L150;
        if (r32 != 6516589) goto L107;
    L147:
        TextInformationFrame r03 = parseTextAttribute(r2, "TCOM", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r03;
    L107:
        if (r32 == 7828084) goto L147;
        if (r32 != 6578553) goto L115;
        TextInformationFrame r04 = parseTextAttribute(r2, "TDRC", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r04;
    L115:
        if (r32 != 4280916) goto L120;
        TextInformationFrame r05 = parseTextAttribute(r2, "TPE1", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r05;
    L120:
        if (r32 != 7630703) goto L125;
        TextInformationFrame r06 = parseTextAttribute(r2, "TSSE", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r06;
    L125:
        if (r32 != 6384738) goto L130;
        TextInformationFrame r07 = parseTextAttribute(r2, "TALB", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r07;
    L130:
        if (r32 != 7108978) goto L135;
        TextInformationFrame r08 = parseTextAttribute(r2, "USLT", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r08;
    L135:
        if (r32 != 6776174) goto L140;
        TextInformationFrame r09 = parseTextAttribute(r2, "TCON", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r09;
    L140:
        if (r32 != 6779504) goto L144;
        TextInformationFrame r010 = parseTextAttribute(r2, "TIT1", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r010;
    L144:
        Log.d("MetadataUtil", "Skipped unknown metadata entry: " + Mp4Box.getBoxTypeString(r2));     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return null;
    L5:
        if (r3 == 253) goto L94;
        if (r2 != 1735291493) goto L15;
        TextInformationFrame r011 = parseStandardGenreAttribute(r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r011;
    L15:
        if (r2 != 1684632427) goto L20;
        TextInformationFrame r012 = parseIndexAndCountAttribute(r2, "TPOS", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r012;
    L20:
        if (r2 != 1953655662) goto L25;
        TextInformationFrame r013 = parseIndexAndCountAttribute(r2, "TRCK", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r013;
    L25:
        if (r2 != 1953329263) goto L30;
        Id3Frame r014 = parseIntegerAttribute(r2, "TBPM", r6, true, false);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r014;
    L30:
        if (r2 != 1668311404) goto L35;
        Id3Frame r015 = parseIntegerAttribute(r2, "TCMP", r6, true, true);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r015;
    L35:
        if (r2 != 1668249202) goto L40;
        ApicFrame r016 = parseCoverArt(r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r016;
    L40:
        if (r2 != 1631670868) goto L45;
        TextInformationFrame r017 = parseTextAttribute(r2, "TPE2", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r017;
    L45:
        if (r2 != 1936682605) goto L50;
        TextInformationFrame r018 = parseTextAttribute(r2, "TSOT", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r018;
    L50:
        if (r2 != 1936679276) goto L55;
        TextInformationFrame r019 = parseTextAttribute(r2, "TSOA", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r019;
    L55:
        if (r2 != 1936679282) goto L60;
        TextInformationFrame r020 = parseTextAttribute(r2, "TSOP", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r020;
    L60:
        if (r2 != 1936679265) goto L65;
        TextInformationFrame r021 = parseTextAttribute(r2, "TSO2", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r021;
    L65:
        if (r2 != 1936679791) goto L70;
        TextInformationFrame r022 = parseTextAttribute(r2, "TSOC", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r022;
    L70:
        if (r2 != 1920233063) goto L75;
        Id3Frame r023 = parseIntegerAttribute(r2, "ITUNESADVISORY", r6, false, false);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r023;
    L75:
        if (r2 != 1885823344) goto L80;
        Id3Frame r024 = parseIntegerAttribute(r2, "ITUNESGAPLESS", r6, false, true);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r024;
    L80:
        if (r2 != 1936683886) goto L85;
        TextInformationFrame r025 = parseTextAttribute(r2, "TVSHOWSORT", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r025;
    L85:
        if (r2 != 1953919848) goto L90;
        TextInformationFrame r026 = parseTextAttribute(r2, "TVSHOW", r6);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r026;
    L90:
        if (r2 != 757935405) goto L144;
        Id3Frame r027 = parseInternalAttribute(r6, r1);     // Catch: Throwable -> L12
        r6.setPosition(r1);
        return r027;
    L12:
        th = move-exception;
        r6.setPosition(r1);
        throw th;
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int r4, String r5, ParsableByteArray r6) {
        int r0 = r6.readInt();
        if (r6.readInt() == 1684108385) goto L5;
    L13:
        Log.w("MetadataUtil", "Failed to parse index/count attribute: " + Mp4Box.getBoxTypeString(r4));
        return null;
    L5:
        if (r0 < 22) goto L13;
        r6.skipBytes(10);
        int r02 = r6.readUnsignedShort();
        if (r02 <= 0) goto L13;
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET + r02;
        int r62 = r6.readUnsignedShort();
        if (r62 <= 0) goto L12;
        r42 = r42 + "/" + r62;
    L12:
        return new TextInformationFrame(r5, null, ImmutableList.of(r42));
    }

    private static int parseIntegerAttribute(ParsableByteArray r3) {
        int r0 = r3.readInt();
        if (r3.readInt() != 1684108385) goto L23;
        r3.skipBytes(8);
        int r02 = r0 - 16;
        if (r02 == 1) goto L22;
        if (r02 == 2) goto L20;
        if (r02 == 3) goto L18;
        if (r02 != 4) goto L23;
        if ((r3.peekUnsignedByte() & 128) != 0) goto L23;
        return r3.readUnsignedIntToInt();
    L18:
        return r3.readUnsignedInt24();
    L20:
        return r3.readUnsignedShort();
    L22:
        return r3.readUnsignedByte();
    L23:
        Log.w("MetadataUtil", "Failed to parse data atom to int");
        return -1;
    }

    private static Id3Frame parseIntegerAttribute(int r0, String r1, ParsableByteArray r2, boolean r3, boolean r4) {
        int r22 = parseIntegerAttribute(r2);
        if (r4 == false) goto L6;
        r22 = Math.min(1, r22);
    L6:
        if (r22 < 0) goto L12;
        if (r3 == false) goto L11;
        return new TextInformationFrame(r1, null, ImmutableList.of(Integer.toString(r22)));
    L11:
        return new CommentFrame(TranslateController.UNKNOWN_LANGUAGE, r1, Integer.toString(r22));
    L12:
        Log.w("MetadataUtil", "Failed to parse uint8 attribute: " + Mp4Box.getBoxTypeString(r0));
        return null;
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray r10, int r11) {
        String r2 = null;
        String r3 = null;
        int r4 = -1;
        int r5 = -1;
    L4:
        if (r10.getPosition() >= r11) goto L15;
        int r6 = r10.getPosition();
        int r7 = r10.readInt();
        int r8 = r10.readInt();
        r10.skipBytes(4);
        if (r8 == 1835360622) goto L7;
        if (r8 == 1851878757) goto L10;
        if (r8 != 1684108385) goto L14;
        r4 = r6;
        r5 = r7;
    L14:
        r10.skipBytes(r7 - 12);
        goto L4
    L10:
        r3 = r10.readNullTerminatedString(r7 - 12);
        goto L4
    L7:
        r2 = r10.readNullTerminatedString(r7 - 12);
        goto L4
    L15:
        if (r2 == null) goto L21;
        if (r3 == null) goto L21;
        if (r4 == (-1)) goto L21;
        r10.setPosition(r4);
        r10.skipBytes(16);
        return new InternalFrame(r2, r3, r10.readNullTerminatedString(r5 - 16));
    L21:
        return null;
    }

    public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray r4, int r5, String r6) {
    L2:
        int r0 = r4.getPosition();
        if (r0 >= r5) goto L9;
        int r1 = r4.readInt();
        if (r4.readInt() == 1684108385) goto L6;
        r4.setPosition(r0 + r1);
        goto L2
    L6:
        int r52 = r4.readInt();
        int r02 = r4.readInt();
        int r12 = r1 - 16;
        byte[] r2 = new byte[r12];
        r4.readBytes(r2, 0, r12);
        return new MdtaMetadataEntry(r6, r2, r02, r52);
    L9:
        return null;
    }

    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray r3) {
        String r32 = Id3Util.resolveV1Genre(parseIntegerAttribute(r3) - 1);
        if (r32 != null) goto L5;
        Log.w("MetadataUtil", "Failed to parse standard genre code");
        return null;
    L5:
        return new TextInformationFrame("TCON", null, ImmutableList.of(r32));
    }

    private static TextInformationFrame parseTextAttribute(int r4, String r5, ParsableByteArray r6) {
        int r0 = r6.readInt();
        if (r6.readInt() != 1684108385) goto L6;
        r6.skipBytes(8);
        return new TextInformationFrame(r5, null, ImmutableList.of(r6.readNullTerminatedString(r0 - 16)));
    L6:
        Log.w("MetadataUtil", "Failed to parse text attribute: " + Mp4Box.getBoxTypeString(r4));
        return null;
    }

    public static void setFormatGaplessInfo(int r1, GaplessInfoHolder r2, Format.Builder r3) {
        if (r1 == 1) goto L5;
        return;
    L5:
        if (r2.hasGaplessInfo() == false) goto L9;
        r3.setEncoderDelay(r2.encoderDelay).setEncoderPadding(r2.encoderPadding);
        return;
    }

    public static void setFormatMetadata(int r5, Metadata r6, Format.Builder r7, Metadata r8, Metadata... r9) {
        int r0 = 0;
        if (r8 != null) goto L6;
        r8 = new Metadata(new Metadata.Entry[0]);
    L6:
        if (r6 == null) goto L19;
        int r1 = 0;
    L9:
        if (r1 >= r6.length()) goto L19;
        Metadata.Entry r2 = r6.get(r1);
        if ((r2 instanceof MdtaMetadataEntry) == false) goto L18;
        MdtaMetadataEntry r22 = (MdtaMetadataEntry) r2;
        if (r22.key.equals("com.android.capture.fps") == true) goto L15;
        r8 = r8.copyWithAppendedEntries(new Metadata.Entry[]{r22});
        goto L18
    L15:
        if (r5 != 2) goto L18;
        r8 = r8.copyWithAppendedEntries(new Metadata.Entry[]{r22});
    L18:
        r1 = r1 + 1;
    L19:
        int r52 = r9.length;
    L20:
        if (r0 >= r52) goto L23;
        r8 = r8.copyWithAppendedEntriesFrom(r9[r0]);
        r0 = r0 + 1;
        goto L20
    L23:
        if (r8.length() <= 0) goto L32;
        r7.setMetadata(r8);
        return;
    }
}
