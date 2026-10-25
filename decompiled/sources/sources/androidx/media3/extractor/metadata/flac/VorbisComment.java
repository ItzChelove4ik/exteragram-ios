package androidx.media3.extractor.metadata.flac;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import com.google.common.base.Ascii;
import com.google.common.primitives.Ints;

@Deprecated
/* loaded from: classes4.dex */
public abstract class VorbisComment implements Metadata.Entry {
    public final String key;
    public final String value;

    public VorbisComment(String r1, String r2) {
        this.key = Ascii.toUpperCase(r1);
        this.value = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L15;
        VorbisComment r52 = (VorbisComment) r5;
        if (this.key.equals(r52.key) == false) goto L15;
        if (this.value.equals(r52.value) == false) goto L15;
        return true;
    }

    public int hashCode() {
        return ((527 + this.key.hashCode()) * 31) + this.value.hashCode();
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder r4) {
        String r0 = this.key;
        r0.getClass();
        char r2 = 65535;
        switch(r0.hashCode()) {
            case -1935137620: goto L42;
            case -215998278: goto L38;
            case -113312716: goto L34;
            case 62359119: goto L30;
            case 67703139: goto L26;
            case 79833656: goto L22;
            case 428414940: goto L18;
            case 993300766: goto L14;
            case 1746739798: goto L10;
            case 1939198791: goto L6;
            default: goto L45;
        };
    L45:
        switch(r2) {
            case 0: goto L71;
            case 1: goto L67;
            case 2: goto L63;
            case 3: goto L61;
            case 4: goto L59;
            case 5: goto L57;
            case 6: goto L55;
            case 7: goto L51;
            case 8: goto L49;
            case 9: goto L47;
            default: goto L79;
        };
    L47:
        r4.setArtist(this.value);
        return;
    L49:
        r4.setAlbumArtist(this.value);
        return;
    L51:
        Integer r3 = Ints.tryParse(this.value);
        if (r3 == null) goto L75;
        r4.setDiscNumber(r3);
        return;
    L75:
        return;
    L55:
        r4.setDescription(this.value);
        return;
    L57:
        r4.setTitle(this.value);
        return;
    L59:
        r4.setGenre(this.value);
        return;
    L61:
        r4.setAlbumTitle(this.value);
        return;
    L63:
        Integer r32 = Ints.tryParse(this.value);
        if (r32 == null) goto L76;
        r4.setTrackNumber(r32);
        return;
    L76:
        return;
    L67:
        Integer r33 = Ints.tryParse(this.value);
        if (r33 == null) goto L77;
        r4.setTotalDiscCount(r33);
        return;
    L77:
        return;
    L71:
        Integer r34 = Ints.tryParse(this.value);
        if (r34 == null) goto L78;
        r4.setTotalTrackCount(r34);
        return;
    L78:
        return;
    L79:
        return;
    L6:
        if (r0.equals("ARTIST") == false) goto L45;
        r2 = '\t';
        goto L45
    L10:
        if (r0.equals("ALBUMARTIST") == false) goto L45;
        r2 = '\b';
        goto L45
    L14:
        if (r0.equals("DISCNUMBER") == false) goto L45;
        r2 = 7;
        goto L45
    L18:
        if (r0.equals("DESCRIPTION") == false) goto L45;
        r2 = 6;
        goto L45
    L22:
        if (r0.equals("TITLE") == false) goto L45;
        r2 = 5;
        goto L45
    L26:
        if (r0.equals("GENRE") == false) goto L45;
        r2 = 4;
        goto L45
    L30:
        if (r0.equals("ALBUM") == false) goto L45;
        r2 = 3;
        goto L45
    L34:
        if (r0.equals("TRACKNUMBER") == false) goto L45;
        r2 = 2;
        goto L45
    L38:
        if (r0.equals("TOTALDISCS") == false) goto L45;
        r2 = 1;
        goto L45
    L42:
        if (r0.equals("TOTALTRACKS") == false) goto L45;
        r2 = 0;
        goto L45
    }

    public String toString() {
        return "VC: " + this.key + "=" + this.value;
    }
}
