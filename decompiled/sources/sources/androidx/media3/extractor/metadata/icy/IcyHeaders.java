package androidx.media3.extractor.metadata.icy;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class IcyHeaders implements Metadata.Entry {
    public final int bitrate;
    public final String genre;
    public final boolean isPublic;
    public final int metadataInterval;
    public final String name;
    public final String url;

    public IcyHeaders(int r2, String r3, String r4, String r5, boolean r6, int r7) {
        if (r7 == (-1)) goto L7;
        if (r7 > 0) goto L7;
        boolean r0 = false;
    L8:
        Assertions.checkArgument(r0);
        this.bitrate = r2;
        this.genre = r3;
        this.name = r4;
        this.url = r5;
        this.isPublic = r6;
        this.metadataInterval = r7;
        return;
    L7:
        r0 = true;
        goto L8
    }

    public static IcyHeaders parse(Map<String, List<String>> r14) {
        List<String> r2 = r14.get("icy-br");
        boolean r4 = true;
        int r6 = -1;
        if (r2 == null) goto L14;
        String r22 = r2.get(0);
        int r7 = Integer.parseInt(r22) * 1000;
        if (r7 <= 0) goto L50;
        boolean r1 = true;
    L11:
        int r8 = r7;
    L15:
        List<String> r23 = r14.get("icy-genre");
        if (r23 == null) goto L18;
        String r9 = r23.get(0);
        r1 = true;
    L19:
        List<String> r24 = r14.get("icy-name");
        if (r24 == null) goto L22;
        String r10 = r24.get(0);
        r1 = true;
    L23:
        List<String> r25 = r14.get("icy-url");
        if (r25 == null) goto L26;
        String r11 = r25.get(0);
        r1 = true;
    L27:
        List<String> r26 = r14.get("icy-pub");
        if (r26 == null) goto L30;
        boolean r12 = r26.get(0).equals("1");
        r1 = true;
    L31:
        List<String> r142 = r14.get("icy-metaint");
        if (r142 == null) goto L40;
        String r143 = r142.get(0);
        int r27 = Integer.parseInt(r143);     // Catch: NumberFormatException -> L46
        if (r27 <= 0) goto L52;
        r6 = r27;
    L39:
        r1 = r4;
        goto L40
    L52:
        Log.w("IcyHeaders", "Invalid metadata interval: " + r143);     // Catch: NumberFormatException -> L41
        r4 = r1;
    L41:
        r6 = r27;
    L42:
        Log.w("IcyHeaders", "Invalid metadata interval: " + r143);
    L40:
        int r13 = r6;
        if (r1 == true) goto L45;
        return null;
    L45:
        return new IcyHeaders(r8, r9, r10, r11, r12, r13);
    L30:
        r12 = false;
        goto L31
    L26:
        r11 = null;
        goto L27
    L22:
        r10 = null;
        goto L23
    L18:
        r9 = null;
        goto L19
    L50:
        Log.w("IcyHeaders", "Invalid bitrate: " + r22);     // Catch: NumberFormatException -> L47
        r1 = false;
        r7 = -1;
    L13:
        Log.w("IcyHeaders", "Invalid bitrate header: " + r22);
        r1 = false;
    L12:
        r7 = -1;
        goto L13
    L14:
        r1 = false;
        r8 = -1;
        goto L15
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L23:
        return false;
    L8:
        if (IcyHeaders.class != r5.getClass()) goto L23;
        IcyHeaders r52 = (IcyHeaders) r5;
        if (this.bitrate != r52.bitrate) goto L23;
        if (Objects.equals(this.genre, r52.genre) == false) goto L23;
        if (Objects.equals(this.name, r52.name) == false) goto L23;
        if (Objects.equals(this.url, r52.url) == false) goto L23;
        if (this.isPublic != r52.isPublic) goto L23;
        if (this.metadataInterval != r52.metadataInterval) goto L23;
        return true;
    }

    public int hashCode() {
        int r0 = (527 + this.bitrate) * 31;
        String r1 = this.genre;
        int r2 = 0;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L6:
        int r02 = (r0 + r12) * 31;
        String r13 = this.name;
        if (r13 == null) goto L9;
        int r14 = r13.hashCode();
    L10:
        int r03 = (r02 + r14) * 31;
        String r15 = this.url;
        if (r15 == null) goto L14;
        r2 = r15.hashCode();
    L14:
        return ((((r03 + r2) * 31) + (this.isPublic ? 1 : 0)) * 31) + this.metadataInterval;
    L9:
        r14 = 0;
        goto L10
    L5:
        r12 = 0;
        goto L6
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder r2) {
        String r0 = this.name;
        if (r0 == null) goto L5;
        r2.setStation(r0);
    L5:
        String r1 = this.genre;
        if (r1 == null) goto L9;
        r2.setGenre(r1);
        return;
    }

    public String toString() {
        return "IcyHeaders: name=\"" + this.name + "\", genre=\"" + this.genre + "\", bitrate=" + this.bitrate + ", metadataInterval=" + this.metadataInterval;
    }
}
