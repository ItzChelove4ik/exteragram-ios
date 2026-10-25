package org.telegram.messenger.audioinfo.mp3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.audioinfo.AudioInfo;

/* loaded from: classes5.dex */
public class ID3v2Info extends AudioInfo {
    static final Logger LOGGER = null;
    private byte coverPictureType;
    private final Level debugLevel;

    public static class AttachedPicture {
        final String description;
        final byte[] imageData;
        final String imageType;
        final byte type;

        public AttachedPicture(byte r1, String r2, String r3, byte[] r4) {
            this.type = r1;
            this.description = r2;
            this.imageType = r3;
            this.imageData = r4;
        }
    }

    public static class CommentOrUnsynchronizedLyrics {
        final String description;
        final String language;
        final String text;

        public CommentOrUnsynchronizedLyrics(String r1, String r2, String r3) {
            this.language = r1;
            this.description = r2;
            this.text = r3;
        }
    }

    static {
        LOGGER = Logger.getLogger(ID3v2Info.class.getName());
    }

    public ID3v2Info(InputStream r8, Level r9) {
        this.debugLevel = r9;
        if (isID3v2StartPosition(r8) == false) goto L56;
        ID3v2TagHeader r0 = new ID3v2TagHeader(r8);
        this.brand = "ID3";
        this.version = String.format("2.%d.%d", new Object[]{Integer.valueOf(r0.getVersion()), Integer.valueOf(r0.getRevision())});
        ID3v2TagBody r1 = r0.tagBody(r8);
    L44:
    L15:
        e = move-exception;
        Logger r2 = LOGGER;
        if (r2.isLoggable(r9) == false) goto L40;
        r2.log(r9, "ID3 exception occured: " + e.getMessage());
    L40:
        r1.getData().skipFully(r1.getRemainingLength());
        if (r0.getFooterSize() <= 0) goto L57;
        r8.skip(r0.getFooterSize());
        return;
    L57:
        return;
    L6:
        if (r1.getRemainingLength() <= 10) goto L40;
        ID3v2FrameHeader r22 = new ID3v2FrameHeader(r1);     // Catch: ID3v2Exception -> L15
        if (r22.isPadding() == true) goto L40;
        if (r22.getBodySize() > r1.getRemainingLength()) goto L12;
        if (r22.isValid() == false) goto L35;
        if (r22.isEncryption() == true) goto L35;
        ID3v2FrameBody r3 = r1.frameBody(r22);     // Catch: ID3v2Exception -> L15
        parseFrame(r3);     // Catch: Throwable -> L26 ID3v2Exception -> L28
        ID3v2DataInput r23 = r3.getData();     // Catch: ID3v2Exception -> L15
    L24:
        r23.skipFully(r3.getRemainingLength());     // Catch: ID3v2Exception -> L15
        goto L44
    L28:
        e = move-exception;
        if (LOGGER.isLoggable(r9) == false) goto L32;
        LOGGER.log(r9, String.format("ID3 exception occured in frame %s: %s", new Object[]{r22.getFrameId(), e.getMessage()}));     // Catch: Throwable -> L26
    L32:
        r23 = r3.getData();     // Catch: ID3v2Exception -> L15
    L26:
        th = move-exception;
        r3.getData().skipFully(r3.getRemainingLength());     // Catch: ID3v2Exception -> L15
        throw th;     // Catch: ID3v2Exception -> L15
    L35:
        r1.getData().skipFully(r22.getBodySize());     // Catch: ID3v2Exception -> L15
        goto L44
    L12:
        Logger r7 = LOGGER;     // Catch: ID3v2Exception -> L15
        if (r7.isLoggable(r9) == false) goto L40;
        r7.log(r9, "ID3 frame claims to extend frames area");     // Catch: ID3v2Exception -> L15
        goto L40
    }

    public static boolean isID3v2StartPosition(InputStream r2) {
        r2.mark(3);
    L11:
        th = move-exception;
        r2.reset();
        throw th;
    L4:
        if (r2.read() == 73) goto L6;
    L13:
        boolean r0 = false;
    L14:
        r2.reset();
        return r0;
    L6:
        if (r2.read() != 68) goto L13;
        if (r2.read() != 51) goto L13;
        r0 = true;
        goto L14
    }

    public AttachedPicture parseAttachedPictureFrame(ID3v2FrameBody r6) {
        ID3v2Encoding r5 = r6.readEncoding();
        if (r6.getTagHeader().getVersion() != 2) goto L11;
        String r0 = r6.readFixedLengthString(3, ID3v2Encoding.ISO_8859_1).toUpperCase();
        r0.getClass();
        if (r0.equals("JPG") == false) goto L7;
        String r02 = "image/jpeg";
    L13:
        return new AttachedPicture(r6.getData().readByte(), r6.readZeroTerminatedString(200, r5), r02, r6.getData().readFully((int) r6.getRemainingLength()));
    L7:
        if (r0.equals("PNG") == true) goto L9;
        r02 = "image/unknown";
        goto L13
    L9:
        r02 = "image/png";
        goto L13
    L11:
        r02 = r6.readZeroTerminatedString(20, ID3v2Encoding.ISO_8859_1);
        goto L13
    }

    public CommentOrUnsynchronizedLyrics parseCommentOrUnsynchronizedLyricsFrame(ID3v2FrameBody r5) {
        ID3v2Encoding r4 = r5.readEncoding();
        return new CommentOrUnsynchronizedLyrics(r5.readFixedLengthString(3, ID3v2Encoding.ISO_8859_1), r5.readZeroTerminatedString(200, r4), r5.readFixedLengthString((int) r5.getRemainingLength(), r4));
    }

    public void parseFrame(ID3v2FrameBody r9) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, "Parsing frame: " + r9.getFrameHeader().getFrameId());
    L5:
        String r02 = r9.getFrameHeader().getFrameId();
        r02.getClass();
        char r7 = 65535;
        switch(r02.hashCode()) {
            case 66913: goto L137;
            case 79210: goto L133;
            case 82815: goto L129;
            case 82878: goto L125;
            case 82880: goto L121;
            case 82881: goto L117;
            case 82883: goto L113;
            case 83149: goto L109;
            case 83253: goto L105;
            case 83254: goto L101;
            case 83269: goto L97;
            case 83341: goto L93;
            case 83377: goto L89;
            case 83378: goto L85;
            case 83552: goto L81;
            case 84125: goto L77;
            case 2015625: goto L73;
            case 2074380: goto L69;
            case 2567331: goto L65;
            case 2569298: goto L61;
            case 2569357: goto L57;
            case 2569358: goto L53;
            case 2569360: goto L49;
            case 2570401: goto L45;
            case 2575250: goto L41;
            case 2575251: goto L37;
            case 2577697: goto L33;
            case 2581512: goto L29;
            case 2581513: goto L25;
            case 2581856: goto L21;
            case 2583398: goto L17;
            case 2590194: goto L13;
            case 2614438: goto L9;
            default: goto L141;
        };
    L9:
        if (r02.equals("USLT") == false) goto L141;
        r7 = ' ';
        goto L141
    L13:
        if (r02.equals("TYER") == false) goto L141;
        r7 = 31;
        goto L141
    L17:
        if (r02.equals("TRCK") == false) goto L141;
        r7 = 30;
        goto L141
    L21:
        if (r02.equals("TPOS") == false) goto L141;
        r7 = 29;
        goto L141
    L25:
        if (r02.equals("TPE2") == false) goto L141;
        r7 = 28;
        goto L141
    L29:
        if (r02.equals("TPE1") == false) goto L141;
        r7 = 27;
        goto L141
    L33:
        if (r02.equals("TLEN") == false) goto L141;
        r7 = 26;
        goto L141
    L37:
        if (r02.equals("TIT2") == false) goto L141;
        r7 = 25;
        goto L141
    L41:
        if (r02.equals("TIT1") == false) goto L141;
        r7 = 24;
        goto L141
    L45:
        if (r02.equals("TDRC") == false) goto L141;
        r7 = 23;
        goto L141
    L49:
        if (r02.equals("TCOP") == false) goto L141;
        r7 = 22;
        goto L141
    L53:
        if (r02.equals("TCON") == false) goto L141;
        r7 = 21;
        goto L141
    L57:
        if (r02.equals("TCOM") == false) goto L141;
        r7 = 20;
        goto L141
    L61:
        if (r02.equals("TCMP") == false) goto L141;
        r7 = 19;
        goto L141
    L65:
        if (r02.equals("TALB") == false) goto L141;
        r7 = 18;
        goto L141
    L69:
        if (r02.equals("COMM") == false) goto L141;
        r7 = 17;
        goto L141
    L73:
        if (r02.equals("APIC") == false) goto L141;
        r7 = 16;
        goto L141
    L77:
        if (r02.equals("ULT") == false) goto L141;
        r7 = 15;
        goto L141
    L81:
        if (r02.equals("TYE") == false) goto L141;
        r7 = 14;
        goto L141
    L85:
        if (r02.equals("TT2") == false) goto L141;
        r7 = '\r';
        goto L141
    L89:
        if (r02.equals("TT1") == false) goto L141;
        r7 = '\f';
        goto L141
    L93:
        if (r02.equals("TRK") == false) goto L141;
        r7 = 11;
        goto L141
    L97:
        if (r02.equals("TPA") == false) goto L141;
        r7 = '\n';
        goto L141
    L101:
        if (r02.equals("TP2") == false) goto L141;
        r7 = '\t';
        goto L141
    L105:
        if (r02.equals("TP1") == false) goto L141;
        r7 = '\b';
        goto L141
    L109:
        if (r02.equals("TLE") == false) goto L141;
        r7 = 7;
        goto L141
    L113:
        if (r02.equals("TCR") == false) goto L141;
        r7 = 6;
        goto L141
    L117:
        if (r02.equals("TCP") == false) goto L141;
        r7 = 5;
        goto L141
    L121:
        if (r02.equals("TCO") == false) goto L141;
        r7 = 4;
        goto L141
    L125:
        if (r02.equals("TCM") == false) goto L141;
        r7 = 3;
        goto L141
    L129:
        if (r02.equals("TAL") == false) goto L141;
        r7 = 2;
        goto L141
    L133:
        if (r02.equals("PIC") == false) goto L141;
        r7 = 1;
        goto L141
    L137:
        if (r02.equals("COM") == false) goto L141;
        r7 = 0;
    L141:
        switch(r7) {
            case 0: goto L272;
            case 1: goto L241;
            case 2: goto L238;
            case 3: goto L236;
            case 4: goto L219;
            case 5: goto L217;
            case 6: goto L215;
            case 7: goto L209;
            case 8: goto L207;
            case 9: goto L205;
            case 10: goto L185;
            case 11: goto L165;
            case 12: goto L163;
            case 13: goto L161;
            case 14: goto L154;
            case 15: goto L151;
            case 16: goto L241;
            case 17: goto L272;
            case 18: goto L238;
            case 19: goto L217;
            case 20: goto L236;
            case 21: goto L219;
            case 22: goto L215;
            case 23: goto L143;
            case 24: goto L163;
            case 25: goto L161;
            case 26: goto L209;
            case 27: goto L207;
            case 28: goto L205;
            case 29: goto L185;
            case 30: goto L165;
            case 31: goto L154;
            case 32: goto L151;
            default: goto L330;
        };
    L143:
        String r92 = parseTextFrame(r9);
        if (r92.length() >= 4) goto L293;
        return;
    L293:
        this.year = Short.valueOf(r92.substring(0, 4)).shortValue();     // Catch: NumberFormatException -> L147
        return;
    L147:
        Logger r03 = LOGGER;
        if (r03.isLoggable(this.debugLevel) == false) goto L306;
        r03.log(this.debugLevel, "Could not parse year from: ".concat(r92));
        return;
    L306:
        return;
    L154:
        String r93 = parseTextFrame(r9);
        if (r93.length() <= 0) goto L309;
        this.year = Short.valueOf(r93).shortValue();     // Catch: NumberFormatException -> L158
        return;
    L158:
        Logger r04 = LOGGER;
        if (r04.isLoggable(this.debugLevel) == false) goto L310;
        r04.log(this.debugLevel, "Could not parse year: ".concat(r93));
        return;
    L310:
        return;
    L309:
        return;
    L161:
        this.title = parseTextFrame(r9);
        return;
    L163:
        this.grouping = parseTextFrame(r9);
        return;
    L165:
        String r94 = parseTextFrame(r9);
        if (r94.length() <= 0) goto L312;
        int r05 = r94.indexOf(47);
        if (r05 < 0) goto L299;
        this.track = Short.valueOf(r94.substring(0, r05)).shortValue();     // Catch: NumberFormatException -> L176
    L285:
        this.tracks = Short.valueOf(r94.substring(r05 + 1)).shortValue();     // Catch: NumberFormatException -> L182
        return;
    L182:
        Logger r06 = LOGGER;
        if (r06.isLoggable(this.debugLevel) == false) goto L315;
        r06.log(this.debugLevel, "Could not parse number of tracks: ".concat(r94));
        return;
    L315:
        return;
    L176:
        Logger r2 = LOGGER;
        if (r2.isLoggable(this.debugLevel) == false) goto L285;
        r2.log(this.debugLevel, "Could not parse track number: ".concat(r94));
        goto L285
    L299:
        this.track = Short.valueOf(r94).shortValue();     // Catch: NumberFormatException -> L171
        return;
    L171:
        Logger r07 = LOGGER;
        if (r07.isLoggable(this.debugLevel) == false) goto L313;
        r07.log(this.debugLevel, "Could not parse track number: ".concat(r94));
        return;
    L313:
        return;
    L312:
        return;
    L185:
        String r95 = parseTextFrame(r9);
        if (r95.length() <= 0) goto L317;
        int r08 = r95.indexOf(47);
        if (r08 < 0) goto L295;
        this.disc = Short.valueOf(r95.substring(0, r08)).shortValue();     // Catch: NumberFormatException -> L196
    L283:
        this.discs = Short.valueOf(r95.substring(r08 + 1)).shortValue();     // Catch: NumberFormatException -> L202
        return;
    L202:
        Logger r09 = LOGGER;
        if (r09.isLoggable(this.debugLevel) == false) goto L320;
        r09.log(this.debugLevel, "Could not parse number of discs: ".concat(r95));
        return;
    L320:
        return;
    L196:
        Logger r22 = LOGGER;
        if (r22.isLoggable(this.debugLevel) == false) goto L283;
        r22.log(this.debugLevel, "Could not parse disc number: ".concat(r95));
        goto L283
    L295:
        this.disc = Short.valueOf(r95).shortValue();     // Catch: NumberFormatException -> L191
        return;
    L191:
        Logger r010 = LOGGER;
        if (r010.isLoggable(this.debugLevel) == false) goto L318;
        r010.log(this.debugLevel, "Could not parse disc number: ".concat(r95));
        return;
    L318:
        return;
    L317:
        return;
    L205:
        this.albumArtist = parseTextFrame(r9);
        return;
    L207:
        this.artist = parseTextFrame(r9);
        return;
    L209:
        String r96 = parseTextFrame(r9);
        this.duration = Long.valueOf(r96).longValue();     // Catch: NumberFormatException -> L212
        return;
    L212:
        Logger r011 = LOGGER;
        if (r011.isLoggable(this.debugLevel) == false) goto L322;
        r011.log(this.debugLevel, "Could not parse track duration: " + r96);
        return;
    L322:
        return;
    L215:
        this.copyright = parseTextFrame(r9);
        return;
    L217:
        this.compilation = "1".equals(parseTextFrame(r9));
        return;
    L219:
        String r97 = parseTextFrame(r9);
        if (r97.length() <= 0) goto L324;
        this.genre = r97;
        if (r97.charAt(0) != '(') goto L232;
        int r012 = r97.indexOf(41);     // Catch: NumberFormatException -> L282
        if (r012 <= 1) goto L231;
        ID3v1Genre r1 = ID3v1Genre.getGenre(Integer.parseInt(r97.substring(1, r012)));     // Catch: NumberFormatException -> L282
        if (r1 != null) goto L233;
        int r013 = r012 + 1;     // Catch: NumberFormatException -> L282
        if (r97.length() <= r013) goto L233;
        this.genre = r97.substring(r013);     // Catch: NumberFormatException -> L282
    L233:
        if (r1 == null) goto L325;
        this.genre = r1.getDescription();     // Catch: NumberFormatException -> L282
        return;
    L325:
        return;
    L231:
        r1 = null;
        goto L233
    L232:
        r1 = ID3v1Genre.getGenre(Integer.parseInt(r97));     // Catch: NumberFormatException -> L282
    L335:
        return;
    L324:
        return;
    L236:
        this.composer = parseTextFrame(r9);
        return;
    L238:
        this.album = parseTextFrame(r9);
        return;
    L272:
        CommentOrUnsynchronizedLyrics r98 = parseCommentOrUnsynchronizedLyricsFrame(r9);
        if (this.comment == null) goto L280;
        String r014 = r98.description;
        if (r014 == null) goto L280;
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r014) == true) goto L280;
        return;
    L280:
        this.comment = r98.text;
        return;
    L330:
        return;
    L151:
        if (this.lyrics != null) goto L308;
        this.lyrics = parseCommentOrUnsynchronizedLyricsFrame(r9).text;
        return;
    L308:
        return;
    L241:
        if (this.cover != null) goto L243;
    L244:
        AttachedPicture r99 = parseAttachedPictureFrame(r9);
        if (this.cover == null) goto L289;
        byte r015 = r99.type;
        if (r015 == 3) goto L289;
        if (r015 == 0) goto L289;
        return;
    L289:
        byte[] r016 = r99.imageData;     // Catch: Throwable -> L254
        BitmapFactory.Options r12 = new BitmapFactory.Options();     // Catch: Throwable -> L254
        r12.inJustDecodeBounds = true;     // Catch: Throwable -> L254
        r12.inSampleSize = 1;     // Catch: Throwable -> L254
        BitmapFactory.decodeByteArray(r016, 0, r016.length, r12);     // Catch: Throwable -> L254
        int r3 = r12.outWidth;     // Catch: Throwable -> L254
        if (r3 <= 800) goto L252;
    L256:
        int r32 = Math.max(r3, r12.outHeight);     // Catch: Throwable -> L254
    L257:
        if (r32 <= 800) goto L259;
        r12.inSampleSize *= 2;
        r32 = r32 / 2;     // Catch: Throwable -> L254
    L259:
        r12.inJustDecodeBounds = false;     // Catch: Throwable -> L254
        Bitmap r017 = BitmapFactory.decodeByteArray(r016, 0, r016.length, r12);     // Catch: Throwable -> L254
        this.cover = r017;     // Catch: Throwable -> L254
        if (r017 != null) goto L262;
    L271:
        this.coverPictureType = r99.type;
        return;
    L262:
        float r018 = Math.max(r017.getWidth(), this.cover.getHeight()) / 120.0f;
        Bitmap r23 = this.cover;
        if (r018 <= 0.0f) goto L265;
        this.smallCover = Bitmap.createScaledBitmap(r23, (int) (r23.getWidth() / r018), (int) (this.cover.getHeight() / r018), true);     // Catch: Throwable -> L254
    L267:
        if (this.smallCover != null) goto L271;
        this.smallCover = this.cover;     // Catch: Throwable -> L254
        goto L271
    L265:
        this.smallCover = r23;     // Catch: Throwable -> L254
        goto L267
    L252:
        if (r12.outHeight <= 800) goto L259;
    L254:
        th = move-exception;
        th.printStackTrace();
        goto L271
    L243:
        if (this.coverPictureType != 3) goto L244;
    }

    public String parseTextFrame(ID3v2FrameBody r3) {
        ID3v2Encoding r2 = r3.readEncoding();
        return r3.readFixedLengthString((int) r3.getRemainingLength(), r2);
    }
}
