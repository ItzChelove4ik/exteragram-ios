package org.telegram.messenger.audioinfo.mp3;

import java.io.InputStream;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.audioinfo.AudioInfo;

/* loaded from: classes5.dex */
public class ID3v1Info extends AudioInfo {
    public ID3v1Info(InputStream r4) {
        if (isID3v1StartPosition(r4) == false) goto L19;
        this.brand = "ID3";
        this.version = "1.0";
        byte[] r42 = readBytes(r4, 128);
        this.title = extractString(r42, 3, 30);
        this.artist = extractString(r42, 33, 30);
        this.album = extractString(r42, 63, 30);
        this.year = Short.parseShort(extractString(r42, 93, 4));     // Catch: NumberFormatException -> L7
    L8:
        this.comment = extractString(r42, 97, 30);
        ID3v1Genre r0 = ID3v1Genre.getGenre(r42[127(0x7f, float:1.78E-43)]);
        if (r0 == null) goto L12;
        this.genre = r0.getDescription();
    L12:
        if (r42[125(0x7d, float:1.75E-43)] != 0) goto L20;
        byte r43 = r42[126(0x7e, float:1.77E-43)];
        if (r43 == 0) goto L21;
        this.version = "1.1";
        this.track = (short) (r43 & UByte.MAX_VALUE);
        return;
    L21:
        return;
    L20:
        return;
    L7:
        this.year = 0;
        goto L8
    }

    public static boolean isID3v1StartPosition(InputStream r2) {
        r2.mark(3);
    L11:
        th = move-exception;
        r2.reset();
        throw th;
    L4:
        if (r2.read() == 84) goto L6;
    L13:
        boolean r0 = false;
    L14:
        r2.reset();
        return r0;
    L6:
        if (r2.read() != 65) goto L13;
        if (r2.read() != 71) goto L13;
        r0 = true;
        goto L14
    }

    public String extractString(byte[] r2, int r3, int r4) {
        String r1 = new String(r2, r3, r4, "ISO-8859-1");     // Catch: Exception -> L7
        int r32 = r1.indexOf(0);     // Catch: Exception -> L7
        if (r32 >= 0) goto L5;
        return r1;
    L5:
        return r1.substring(0, r32);
    L7:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public byte[] readBytes(InputStream r3, int r4) {
        byte[] r2 = new byte[r4];
        int r0 = 0;
    L3:
        if (r0 >= r4) goto L11;
        int r1 = r3.read(r2, r0, r4 - r0);
        if (r1 <= 0) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return null;
    L11:
        return r2;
    }
}
