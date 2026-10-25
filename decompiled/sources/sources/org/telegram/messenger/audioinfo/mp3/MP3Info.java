package org.telegram.messenger.audioinfo.mp3;

import com.android.dx.io.Opcodes;
import java.io.EOFException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.time.DurationKt;
import org.mvel2.MVEL;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.audioinfo.mp3.MP3Frame;

/* loaded from: classes5.dex */
public class MP3Info extends AudioInfo {
    static final Logger LOGGER = null;

    /* renamed from: org.telegram.messenger.audioinfo.mp3.MP3Info$1, reason: invalid class name */
    public class AnonymousClass1 implements StopReadCondition {
        final long stopPosition;
        final /* synthetic */ MP3Info this$0;
        final /* synthetic */ long val$fileLength;

        public AnonymousClass1(MP3Info r3, long r4) {
            this.this$0 = r3;
            this.val$fileLength = r4;
            this.stopPosition = r4 - 128;
        }

        @Override // org.telegram.messenger.audioinfo.mp3.MP3Info.StopReadCondition
        public boolean stopRead(MP3Input r5) {
            if (r5.getPosition() == this.stopPosition) goto L5;
            return false;
        L5:
            if (ID3v1Info.isID3v1StartPosition(r5) == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public interface StopReadCondition {
        boolean stopRead(MP3Input r1);
    }

    static {
        LOGGER = Logger.getLogger(MP3Info.class.getName());
    }

    public MP3Info(InputStream r2, long r3) {
        this(r2, r3, Level.FINEST);
    }

    public MP3Info(InputStream r6, long r7, Level r9) {
        this.brand = "MP3";
        this.version = MVEL.VERSION_SUB;
        MP3Input r0 = new MP3Input(r6);
        if (ID3v2Info.isID3v2StartPosition(r0) == false) goto L5;
        ID3v2Info r1 = new ID3v2Info(r0, r9);
        this.album = r1.getAlbum();
        this.albumArtist = r1.getAlbumArtist();
        this.artist = r1.getArtist();
        this.comment = r1.getComment();
        this.cover = r1.getCover();
        this.smallCover = r1.getSmallCover();
        this.compilation = r1.isCompilation();
        this.composer = r1.getComposer();
        this.copyright = r1.getCopyright();
        this.disc = r1.getDisc();
        this.discs = r1.getDiscs();
        this.duration = r1.getDuration();
        this.genre = r1.getGenre();
        this.grouping = r1.getGrouping();
        this.lyrics = r1.getLyrics();
        this.title = r1.getTitle();
        this.track = r1.getTrack();
        this.tracks = r1.getTracks();
        this.year = r1.getYear();
    L5:
        long r12 = this.duration;
        if (r12 > 0) goto L8;
    L47:
        this.duration = calculateDuration(r0, r7, new AnonymousClass1(this, r7));     // Catch: MP3Exception -> L11
    L16:
        if (this.title != null) goto L18;
    L21:
        long r72 = r7 - 128;
        if (r0.getPosition() > r72) goto L50;
        r0.skipFully(r72 - r0.getPosition());
        if (ID3v1Info.isID3v1StartPosition(r6) == false) goto L51;
        ID3v1Info r73 = new ID3v1Info(r6);
        if (this.album != null) goto L29;
        this.album = r73.getAlbum();
    L29:
        if (this.artist != null) goto L32;
        this.artist = r73.getArtist();
    L32:
        if (this.comment != null) goto L35;
        this.comment = r73.getComment();
    L35:
        if (this.genre != null) goto L38;
        this.genre = r73.getGenre();
    L38:
        if (this.title != null) goto L41;
        this.title = r73.getTitle();
    L41:
        if (this.track != 0) goto L44;
        this.track = r73.getTrack();
    L44:
        if (this.year != 0) goto L52;
        this.year = r73.getYear();
        return;
    L52:
        return;
    L51:
        return;
    L50:
        return;
    L18:
        if (this.album == null) goto L21;
        if (this.artist == null) goto L21;
        return;
    L11:
        e = move-exception;
        Logger r2 = LOGGER;
        if (r2.isLoggable(r9) == false) goto L16;
        r2.log(r9, "Could not determine MP3 duration", e);
        goto L16
    L8:
        if (r12 < DurationKt.MILLIS_IN_HOUR) goto L16;
        goto L47
    }

    public long calculateDuration(MP3Input r18, long r19, StopReadCondition r21) {
        MP3Frame r3 = readFirstFrame(r18, r21);
        if (r3 == null) goto L24;
        if (r3.getNumberOfFrames() > 0) goto L7;
        long r4 = r18.getPosition() - r3.getSize();
        long r6 = r3.getSize();
        int r8 = r3.getHeader().getBitrate();
        long r9 = r8;
        int r12 = 10000 / r3.getHeader().getDuration();
        boolean r13 = false;
        int r14 = 1;
    L9:
        if (r14 != r12) goto L15;
        if (r13 == true) goto L15;
        if (r19 <= 0) goto L15;
        return r3.getHeader().getTotalDuration(r19 - r4);
    L15:
        r3 = readNextFrame(r18, r21, r3);
        if (r3 == null) goto L18;
        int r15 = r3.getHeader().getBitrate();
        if (r15 == r8) goto L22;
        r13 = true;
    L22:
        r9 = r9 + r15;
        r6 = r6 + r3.getSize();
        r14 = r14 + 1;
        r12 = r12;
        goto L9
    L18:
        return (((r6 * 1000) * r14) * 8) / r9;
    L7:
        return r3.getHeader().getTotalDuration(r4 * r3.getSize());
    L24:
        throw new MP3Exception("No audio frame");
    }

    public MP3Frame readFirstFrame(MP3Input r11, StopReadCondition r12) {
        if (r12.stopRead(r11) == false) goto L5;
        int r10 = -1;
    L6:
        int r2 = 0;
    L7:
        if (r10 == (-1)) goto L78;
        if (r2 != 255) goto L59;
        if ((r10 & Opcodes.SHL_INT_LIT8) != 224) goto L59;
        r11.mark(2);
        if (r12.stopRead(r11) == false) goto L15;
        int r4 = -1;
    L16:
        if (r4 == (-1)) goto L79;
        if (r12.stopRead(r11) == false) goto L21;
        int r5 = -1;
    L22:
        if (r5 == (-1)) goto L80;
        MP3Frame.Header r6 = new MP3Frame.Header(r10, r4, r5);
        r11.reset();
        r11.mark(r6.getFrameSize() + 2);
        int r42 = r6.getFrameSize();
        byte[] r52 = new byte[r42];
        r52[0] = -1;
        r52[1] = (byte) r10;
        int r43 = r42 - 2;
        r11.readFully(r52, 2, r43);     // Catch: EOFException -> L65
        MP3Frame r22 = new MP3Frame(r6, r52);
        if (r22.isChecksumError() == false) goto L29;
    L57:
        r11.reset();
        goto L59
    L29:
        if (r12.stopRead(r11) == false) goto L31;
        int r53 = -1;
    L33:
        if (r12.stopRead(r11) == false) goto L35;
        int r7 = -1;
    L36:
        if (r53 == (-1)) goto L56;
        if (r7 == (-1)) goto L56;
        if (r53 != 255) goto L57;
        if ((r7 & Opcodes.CONST_METHOD_HANDLE) != (r10 & Opcodes.CONST_METHOD_HANDLE)) goto L57;
        if (r12.stopRead(r11) == false) goto L45;
        int r3 = -1;
    L47:
        if (r12.stopRead(r11) == false) goto L49;
        int r54 = -1;
    L50:
        if (r3 == (-1)) goto L56;
        if (r54 == (-1)) goto L56;
        if (new MP3Frame.Header(r7, r3, r54).isCompatible(r6) == false) goto L57;
        r11.reset();
        r11.skipFully(r43);
        goto L56
    L49:
        r54 = r11.read();
        goto L50
    L45:
        r3 = r11.read();
    L56:
        return r22;
    L35:
        r7 = r11.read();
        goto L36
    L31:
        r53 = r11.read();
    L63:
        return null;
    L80:
        return null;
    L21:
        r5 = r11.read();
        goto L22
    L79:
        return null;
    L15:
        r4 = r11.read();
    L59:
        if (r12.stopRead(r11) == false) goto L61;
        int r23 = -1;
    L62:
        int r9 = r23;
        r2 = r10;
        r10 = r9;
        goto L7
    L61:
        r23 = r11.read();
        goto L62
    L78:
        return null;
    L5:
        r10 = r11.read();
        goto L6
    }

    public MP3Frame readNextFrame(MP3Input r9, StopReadCondition r10, MP3Frame r11) {
        MP3Frame.Header r8 = r11.getHeader();
        r9.mark(4);
        if (r10.stopRead(r9) == false) goto L5;
        int r0 = -1;
    L7:
        if (r10.stopRead(r9) == false) goto L9;
        int r2 = -1;
    L11:
        if (r0 == (-1)) goto L47;
        if (r2 == (-1)) goto L47;
        if (r0 == 255) goto L17;
    L46:
        r9.reset();
        goto L47
    L17:
        if ((r2 & Opcodes.SHL_INT_LIT8) != 224) goto L46;
        if (r10.stopRead(r9) == false) goto L21;
        int r4 = -1;
    L23:
        if (r10.stopRead(r9) == false) goto L25;
        int r102 = -1;
    L26:
        if (r4 == (-1)) goto L45;
        if (r102 == (-1)) goto L45;
        MP3Frame.Header r5 = new MP3Frame.Header(r2, r4, r102);     // Catch: MP3Exception -> L32
    L36:
        if (r5 == null) goto L46;
        if (r5.isCompatible(r8) == false) goto L46;
        int r82 = r5.getFrameSize();
        byte[] r6 = new byte[r82];
        r6[0] = (byte) r0;
        r6[1] = (byte) r2;
        r6[2] = (byte) r4;
        r6[3] = (byte) r102;
        r9.readFully(r6, 4, r82 - 4);     // Catch: EOFException -> L43
        return new MP3Frame(r5, r6);
    L43:
        return null;
    L32:
        e = move-exception;
        int r62 = r9.exceptionsCount + 1;
        r9.exceptionsCount = r62;
        if (r62 > 5) goto L44;
        r5 = null;
        goto L36
    L44:
        throw e;
    L45:
        return null;
    L25:
        r102 = r9.read();
        goto L26
    L21:
        r4 = r9.read();
    L47:
        return null;
    L9:
        r2 = r9.read();
        goto L11
    L5:
        r0 = r9.read();
        goto L7
    }
}
