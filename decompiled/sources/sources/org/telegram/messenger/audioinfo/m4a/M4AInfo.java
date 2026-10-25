package org.telegram.messenger.audioinfo.m4a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.audioinfo.mp3.ID3v1Genre;

/* loaded from: classes5.dex */
public class M4AInfo extends AudioInfo {
    static final Logger LOGGER = null;
    private final Level debugLevel;
    private byte rating;
    private BigDecimal speed;
    private short tempo;
    private BigDecimal volume;

    static {
        LOGGER = Logger.getLogger(M4AInfo.class.getName());
    }

    public M4AInfo(InputStream r2) {
        this(r2, Level.FINEST);
    }

    public M4AInfo(InputStream r3, Level r4) {
        this.debugLevel = r4;
        MP4Input r0 = new MP4Input(r3);
        Logger r32 = LOGGER;
        if (r32.isLoggable(r4) == false) goto L5;
        r32.log(r4, r0.toString());
    L5:
        ftyp(r0.nextChild("ftyp"));
        moov(r0.nextChildUpTo("moov"));
    }

    public void data(MP4Atom r9) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r9.toString());
    L5:
        r9.skip(4);
        r9.skip(4);
        String r1 = r9.getParent().getType();
        r1.getClass();
        char r6 = 65535;
        switch(r1.hashCode()) {
            case 2954818: goto L85;
            case 3059752: goto L81;
            case 3060304: goto L77;
            case 3060591: goto L73;
            case 3083677: goto L69;
            case 3177818: goto L65;
            case 3511163: goto L61;
            case 3564088: goto L57;
            case 3568737: goto L53;
            case 5099770: goto L49;
            case 5131342: goto L45;
            case 5133313: goto L41;
            case 5133368: goto L37;
            case 5133411: goto L33;
            case 5133907: goto L29;
            case 5136903: goto L25;
            case 5137308: goto L21;
            case 5142332: goto L17;
            case 5143505: goto L13;
            case 5152688: goto L9;
            default: goto L89;
        };
    L9:
        if (r1.equals("©wrt") == false) goto L89;
        r6 = 19;
        goto L89
    L13:
        if (r1.equals("©nam") == false) goto L89;
        r6 = 18;
        goto L89
    L17:
        if (r1.equals("©lyr") == false) goto L89;
        r6 = 17;
        goto L89
    L21:
        if (r1.equals("©grp") == false) goto L89;
        r6 = 16;
        goto L89
    L25:
        if (r1.equals("©gen") == false) goto L89;
        r6 = 15;
        goto L89
    L29:
        if (r1.equals("©day") == false) goto L89;
        r6 = 14;
        goto L89
    L33:
        if (r1.equals("©cpy") == false) goto L89;
        r6 = '\r';
        goto L89
    L37:
        if (r1.equals("©com") == false) goto L89;
        r6 = '\f';
        goto L89
    L41:
        if (r1.equals("©cmt") == false) goto L89;
        r6 = 11;
        goto L89
    L45:
        if (r1.equals("©alb") == false) goto L89;
        r6 = '\n';
        goto L89
    L49:
        if (r1.equals("©ART") == false) goto L89;
        r6 = '\t';
        goto L89
    L53:
        if (r1.equals("trkn") == false) goto L89;
        r6 = '\b';
        goto L89
    L57:
        if (r1.equals("tmpo") == false) goto L89;
        r6 = 7;
        goto L89
    L61:
        if (r1.equals("rtng") == false) goto L89;
        r6 = 6;
        goto L89
    L65:
        if (r1.equals("gnre") == false) goto L89;
        r6 = 5;
        goto L89
    L69:
        if (r1.equals("disk") == false) goto L89;
        r6 = 4;
        goto L89
    L73:
        if (r1.equals("cprt") == false) goto L89;
        r6 = 3;
        goto L89
    L77:
        if (r1.equals("cpil") == false) goto L89;
        r6 = 2;
        goto L89
    L81:
        if (r1.equals("covr") == false) goto L89;
        r6 = 1;
        goto L89
    L85:
        if (r1.equals("aART") == false) goto L89;
        r6 = 0;
    L89:
        switch(r6) {
            case 0: goto L168;
            case 1: goto L173;
            case 2: goto L145;
            case 3: goto L139;
            case 4: goto L137;
            case 5: goto L125;
            case 6: goto L123;
            case 7: goto L121;
            case 8: goto L119;
            case 9: goto L117;
            case 10: goto L115;
            case 11: goto L113;
            case 12: goto L107;
            case 13: goto L139;
            case 14: goto L103;
            case 15: goto L97;
            case 16: goto L95;
            case 17: goto L93;
            case 18: goto L91;
            case 19: goto L107;
            default: goto L184;
        };
    L91:
        this.title = r9.readString("UTF-8");
        return;
    L93:
        this.lyrics = r9.readString("UTF-8");
        return;
    L95:
        this.grouping = r9.readString("UTF-8");
        return;
    L97:
        String r02 = this.genre;
        if (r02 != null) goto L100;
    L101:
        this.genre = r9.readString("UTF-8");
        return;
    L100:
        if (r02.trim().length() == 0) goto L101;
        return;
    L103:
        String r92 = r9.readString("UTF-8").trim();
        if (r92.length() < 4) goto L177;
        this.year = Short.valueOf(r92.substring(0, 4)).shortValue();     // Catch: NumberFormatException -> L170
        return;
    L185:
        return;
    L177:
        return;
    L107:
        String r03 = this.composer;
        if (r03 != null) goto L110;
    L111:
        this.composer = r9.readString("UTF-8");
        return;
    L110:
        if (r03.trim().length() == 0) goto L111;
        return;
    L113:
        this.comment = r9.readString("UTF-8");
        return;
    L115:
        this.album = r9.readString("UTF-8");
        return;
    L117:
        this.artist = r9.readString("UTF-8");
        return;
    L119:
        r9.skip(2);
        this.track = r9.readShort();
        this.tracks = r9.readShort();
        return;
    L121:
        this.tempo = r9.readShort();
        return;
    L123:
        this.rating = r9.readByte();
        return;
    L125:
        String r04 = this.genre;
        if (r04 == null) goto L130;
        if (r04.trim().length() == 0) goto L130;
        return;
    L130:
        if (r9.getRemaining() != 2) goto L135;
        ID3v1Genre r93 = ID3v1Genre.getGenre(r9.readShort() - 1);
        if (r93 == null) goto L180;
        this.genre = r93.getDescription();
        return;
    L180:
        return;
    L135:
        this.genre = r9.readString("UTF-8");
        return;
    L137:
        r9.skip(2);
        this.disc = r9.readShort();
        this.discs = r9.readShort();
        return;
    L139:
        String r05 = this.copyright;
        if (r05 != null) goto L142;
    L143:
        this.copyright = r9.readString("UTF-8");
        return;
    L142:
        if (r05.trim().length() == 0) goto L143;
        return;
    L145:
        this.compilation = r9.readBoolean();
        return;
    L168:
        this.albumArtist = r9.readString("UTF-8");
        return;
    L173:
        byte[] r94 = r9.readBytes();     // Catch: Exception -> L165
        BitmapFactory.Options r06 = new BitmapFactory.Options();     // Catch: Exception -> L165
        r06.inJustDecodeBounds = true;     // Catch: Exception -> L165
        r06.inSampleSize = 1;     // Catch: Exception -> L165
        BitmapFactory.decodeByteArray(r94, 0, r94.length, r06);     // Catch: Exception -> L165
        int r12 = r06.outWidth;     // Catch: Exception -> L165
        if (r12 <= 800) goto L150;
    L151:
        int r13 = Math.max(r12, r06.outHeight);     // Catch: Exception -> L165
    L152:
        if (r13 <= 800) goto L154;
        r06.inSampleSize *= 2;
        r13 = r13 / 2;     // Catch: Exception -> L165
    L154:
        r06.inJustDecodeBounds = false;     // Catch: Exception -> L165
        Bitmap r95 = BitmapFactory.decodeByteArray(r94, 0, r94.length, r06);     // Catch: Exception -> L165
        this.cover = r95;     // Catch: Exception -> L165
        if (r95 != null) goto L157;
        return;
    L157:
        float r96 = Math.max(r95.getWidth(), this.cover.getHeight()) / 120.0f;
        Bitmap r14 = this.cover;
        if (r96 <= 0.0f) goto L160;
        this.smallCover = Bitmap.createScaledBitmap(r14, (int) (r14.getWidth() / r96), (int) (this.cover.getHeight() / r96), true);     // Catch: Exception -> L165
    L162:
        if (this.smallCover != null) goto L183;
        this.smallCover = this.cover;     // Catch: Exception -> L165
        return;
    L183:
        return;
    L160:
        this.smallCover = r14;     // Catch: Exception -> L165
        goto L162
    L150:
        if (r06.outHeight <= 800) goto L154;
    L165:
        e = move-exception;
        e.printStackTrace();
        return;
    }

    public void ftyp(MP4Atom r5) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r5.toString());
    L5:
        String r1 = r5.readString(4, "ISO8859_1").trim();
        this.brand = r1;
        if (r1.matches("M4V|MP4|mp42|isom") == false) goto L9;
        r0.warning(r5.getPath() + ": brand=" + this.brand + " (experimental)");
    L11:
        this.version = String.valueOf(r5.readInt());
        return;
    L9:
        if (this.brand.matches("M4A|M4P") == true) goto L11;
        r0.warning(r5.getPath() + ": brand=" + this.brand + " (expected M4A or M4P)");
        goto L11
    }

    public void ilst(MP4Atom r7) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L6;
        r0.log(this.debugLevel, r7.toString());
    L6:
        if (r7.hasMoreChildren() == false) goto L16;
        MP4Atom r02 = r7.nextChild();
        Logger r1 = LOGGER;
        if (r1.isLoggable(this.debugLevel) == false) goto L11;
        r1.log(this.debugLevel, r02.toString());
    L11:
        if (r02.getRemaining() == 0) goto L13;
        data(r02.nextChildUpTo("data"));
        goto L6
    L13:
        if (r1.isLoggable(this.debugLevel) == false) goto L6;
        r1.log(this.debugLevel, r02.getPath() + ": contains no value");
        goto L6
    }

    public void mdhd(MP4Atom r9) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r9.toString());
    L5:
        byte r1 = r9.readByte();
        r9.skip(3);
        if (r1 != 1) goto L8;
        int r3 = 16;
    L9:
        r9.skip(r3);
        int r32 = r9.readInt();
        if (r1 != 1) goto L12;
        long r12 = r9.readLong();
    L14:
        if (this.duration != 0) goto L18;
        this.duration = (r12 * 1000) / r32;
        return;
    L18:
        if (r0.isLoggable(this.debugLevel) == false) goto L23;
        long r13 = (r12 * 1000) / r32;
        if (Math.abs(this.duration - r13) <= 2) goto L24;
        r0.log(this.debugLevel, "mdhd: duration " + this.duration + " -> " + r13);
        return;
    L24:
        return;
    L23:
        return;
    L12:
        r12 = r9.readInt();
        goto L14
    L8:
        r3 = 8;
        goto L9
    }

    public void mdia(MP4Atom r4) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r4.toString());
    L5:
        mdhd(r4.nextChild("mdhd"));
    }

    public void meta(MP4Atom r4) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r4.toString());
    L5:
        r4.skip(4);
    L7:
        if (r4.hasMoreChildren() == false) goto L15;
        MP4Atom r02 = r4.nextChild();
        if ("ilst".equals(r02.getType()) == false) goto L7;
        ilst(r02);
        return;
    }

    public void moov(MP4Atom r5) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L6;
        r0.log(this.debugLevel, r5.toString());
    L6:
        if (r5.hasMoreChildren() == false) goto L27;
        MP4Atom r02 = r5.nextChild();
        String r1 = r02.getType();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case 3363941: goto L19;
            case 3568424: goto L15;
            case 3585340: goto L11;
            default: goto L22;
        };
    L22:
        switch(r3) {
            case 0: goto L26;
            case 1: goto L25;
            case 2: goto L24;
            default: goto L6;
        };
    L24:
        udta(r02);
        goto L6
    L25:
        trak(r02);
        goto L6
    L26:
        mvhd(r02);
        goto L6
    L11:
        if (r1.equals("udta") == false) goto L22;
        r3 = 2;
        goto L22
    L15:
        if (r1.equals("trak") == false) goto L22;
        r3 = 1;
        goto L22
    L19:
        if (r1.equals("mvhd") == false) goto L22;
        r3 = 0;
        goto L22
    }

    public void mvhd(MP4Atom r10) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r10.toString());
    L5:
        byte r1 = r10.readByte();
        r10.skip(3);
        if (r1 != 1) goto L8;
        int r3 = 16;
    L9:
        r10.skip(r3);
        int r32 = r10.readInt();
        if (r1 != 1) goto L12;
        long r12 = r10.readLong();
    L14:
        if (this.duration != 0) goto L17;
        this.duration = (r12 * 1000) / r32;
    L21:
        this.speed = r10.readIntegerFixedPoint();
        this.volume = r10.readShortFixedPoint();
        return;
    L17:
        if (r0.isLoggable(this.debugLevel) == false) goto L21;
        long r13 = (r12 * 1000) / r32;
        if (Math.abs(this.duration - r13) <= 2) goto L21;
        r0.log(this.debugLevel, "mvhd: duration " + this.duration + " -> " + r13);
        goto L21
    L12:
        r12 = r10.readInt();
        goto L14
    L8:
        r3 = 8;
        goto L9
    }

    public void trak(MP4Atom r4) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L5;
        r0.log(this.debugLevel, r4.toString());
    L5:
        mdia(r4.nextChildUpTo("mdia"));
    }

    public void udta(MP4Atom r4) {
        Logger r0 = LOGGER;
        if (r0.isLoggable(this.debugLevel) == false) goto L6;
        r0.log(this.debugLevel, r4.toString());
    L6:
        if (r4.hasMoreChildren() == false) goto L15;
        MP4Atom r02 = r4.nextChild();
        if ("meta".equals(r02.getType()) == false) goto L6;
        meta(r02);
        return;
    }
}
