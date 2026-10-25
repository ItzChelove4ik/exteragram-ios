package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class TextInformationFrame extends Id3Frame {
    public final String description;

    @Deprecated
    public final String value;
    public final ImmutableList<String> values;

    public TextInformationFrame(String r1, String r2, List<String> r3) {
        super(r1);
        Assertions.checkArgument(!r3.isEmpty());
        this.description = r2;
        ImmutableList<String> r12 = ImmutableList.copyOf(r3);
        this.values = r12;
        this.value = r12.get(0);
    }

    private static List<Integer> parseId3v2point4TimestampFrameForDate(String r7) {
        ArrayList r0 = new ArrayList();
        if (r7.length() < 10) goto L8;
        r0.add(Integer.valueOf(Integer.parseInt(r7.substring(0, 4))));     // Catch: NumberFormatException -> L15
        r0.add(Integer.valueOf(Integer.parseInt(r7.substring(5, 7))));     // Catch: NumberFormatException -> L15
        r0.add(Integer.valueOf(Integer.parseInt(r7.substring(8, 10))));     // Catch: NumberFormatException -> L15
        return r0;
    L8:
        if (r7.length() < 7) goto L12;
        r0.add(Integer.valueOf(Integer.parseInt(r7.substring(0, 4))));     // Catch: NumberFormatException -> L15
        r0.add(Integer.valueOf(Integer.parseInt(r7.substring(5, 7))));     // Catch: NumberFormatException -> L15
        return r0;
    L12:
        if (r7.length() < 4) goto L14;
        r0.add(Integer.valueOf(Integer.parseInt(r7.substring(0, 4))));     // Catch: NumberFormatException -> L15
    L14:
        return r0;
    L16:
        return new ArrayList();
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (TextInformationFrame.class != r5.getClass()) goto L17;
        TextInformationFrame r52 = (TextInformationFrame) r5;
        if (Objects.equals(this.id, r52.id) == false) goto L17;
        if (Objects.equals(this.description, r52.description) == false) goto L17;
        if (this.values.equals(r52.values) == false) goto L17;
        return true;
    }

    public int hashCode() {
        int r1 = (527 + this.id.hashCode()) * 31;
        String r0 = this.description;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L7:
        return ((r1 + r02) * 31) + this.values.hashCode();
    L5:
        r02 = 0;
        goto L7
    }

    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder r9) {
        String r0 = this.id;
        r0.getClass();
        char r7 = 65535;
        switch(r0.hashCode()) {
            case 82815: goto L94;
            case 82878: goto L90;
            case 82897: goto L86;
            case 83253: goto L82;
            case 83254: goto L78;
            case 83255: goto L74;
            case 83341: goto L70;
            case 83378: goto L66;
            case 83536: goto L62;
            case 83552: goto L58;
            case 2567331: goto L54;
            case 2569357: goto L50;
            case 2569358: goto L46;
            case 2569891: goto L42;
            case 2570401: goto L38;
            case 2570410: goto L34;
            case 2571565: goto L30;
            case 2575251: goto L26;
            case 2581512: goto L22;
            case 2581513: goto L18;
            case 2581514: goto L14;
            case 2583398: goto L10;
            case 2590194: goto L6;
            default: goto L151;
        };
    L151:
        switch(r7) {
            case 0: goto L148;
            case 1: goto L146;
            case 2: goto L144;
            case 3: goto L142;
            case 4: goto L140;
            case 5: goto L138;
            case 6: goto L131;
            case 7: goto L129;
            case 8: goto L127;
            case 9: goto L125;
            case 10: goto L148;
            case 11: goto L146;
            case 12: goto L117;
            case 13: goto L144;
            case 14: goto L108;
            case 15: goto L99;
            case 16: goto L127;
            case 17: goto L129;
            case 18: goto L142;
            case 19: goto L140;
            case 20: goto L138;
            case 21: goto L131;
            case 22: goto L125;
            default: goto L153;
        };
    L99:
        List<Integer> r8 = parseId3v2point4TimestampFrameForDate(this.values.get(0));
        int r02 = r8.size();
        if (r02 == 1) goto L106;
        if (r02 == 2) goto L105;
        if (r02 != 3) goto L154;
        r9.setReleaseDay(r8.get(2));
        goto L105
    L154:
        return;
    L105:
        r9.setReleaseMonth(r8.get(1));
    L106:
        r9.setReleaseYear(r8.get(0));
        return;
    L108:
        List<Integer> r82 = parseId3v2point4TimestampFrameForDate(this.values.get(0));
        int r03 = r82.size();
        if (r03 == 1) goto L115;
        if (r03 == 2) goto L114;
        if (r03 != 3) goto L155;
        r9.setRecordingDay(r82.get(2));
        goto L114
    L155:
        return;
    L114:
        r9.setRecordingMonth(r82.get(1));
    L115:
        r9.setRecordingYear(r82.get(0));
        return;
    L117:
        Integer r04 = Ints.tryParse(this.values.get(0));
        if (r04 != null) goto L121;
        r9.setGenre(this.values.get(0));
        return;
    L121:
        String r83 = Id3Util.resolveV1Genre(r04.intValue());
        if (r83 == null) goto L152;
        r9.setGenre(r83);
        return;
    L152:
        return;
    L125:
        r9.setRecordingYear(Integer.valueOf(Integer.parseInt(this.values.get(0))));     // Catch: Throwable -> L150
        return;
    L127:
        r9.setWriter(this.values.get(0));
        return;
    L129:
        r9.setTitle(this.values.get(0));
        return;
    L131:
        String[] r84 = Util.split(this.values.get(0), "/");
        int r05 = Integer.parseInt(r84[0]);     // Catch: Throwable -> L150
        if (r84.length <= 1) goto L135;
        Integer r85 = Integer.valueOf(Integer.parseInt(r84[1]));     // Catch: Throwable -> L150
    L136:
        r9.setTrackNumber(Integer.valueOf(r05)).setTotalTrackCount(r85);     // Catch: Throwable -> L150
        return;
    L135:
        r85 = null;
        goto L136
    L138:
        r9.setConductor(this.values.get(0));
        return;
    L140:
        r9.setAlbumArtist(this.values.get(0));
        return;
    L142:
        r9.setArtist(this.values.get(0));
        return;
    L144:
        String r86 = this.values.get(0);     // Catch: Throwable -> L150
        r9.setRecordingMonth(Integer.valueOf(Integer.parseInt(r86.substring(2, 4)))).setRecordingDay(Integer.valueOf(Integer.parseInt(r86.substring(0, 2))));     // Catch: Throwable -> L150
        return;
    L146:
        r9.setComposer(this.values.get(0));
        return;
    L148:
        r9.setAlbumTitle(this.values.get(0));
        return;
    L153:
        return;
    L156:
        return;
    L6:
        if (r0.equals("TYER") == false) goto L151;
        r7 = 22;
        goto L151
    L10:
        if (r0.equals("TRCK") == false) goto L151;
        r7 = 21;
        goto L151
    L14:
        if (r0.equals("TPE3") == false) goto L151;
        r7 = 20;
        goto L151
    L18:
        if (r0.equals("TPE2") == false) goto L151;
        r7 = 19;
        goto L151
    L22:
        if (r0.equals("TPE1") == false) goto L151;
        r7 = 18;
        goto L151
    L26:
        if (r0.equals("TIT2") == false) goto L151;
        r7 = 17;
        goto L151
    L30:
        if (r0.equals("TEXT") == false) goto L151;
        r7 = 16;
        goto L151
    L34:
        if (r0.equals("TDRL") == false) goto L151;
        r7 = 15;
        goto L151
    L38:
        if (r0.equals("TDRC") == false) goto L151;
        r7 = 14;
        goto L151
    L42:
        if (r0.equals("TDAT") == false) goto L151;
        r7 = '\r';
        goto L151
    L46:
        if (r0.equals("TCON") == false) goto L151;
        r7 = '\f';
        goto L151
    L50:
        if (r0.equals("TCOM") == false) goto L151;
        r7 = 11;
        goto L151
    L54:
        if (r0.equals("TALB") == false) goto L151;
        r7 = '\n';
        goto L151
    L58:
        if (r0.equals("TYE") == false) goto L151;
        r7 = '\t';
        goto L151
    L62:
        if (r0.equals("TXT") == false) goto L151;
        r7 = '\b';
        goto L151
    L66:
        if (r0.equals("TT2") == false) goto L151;
        r7 = 7;
        goto L151
    L70:
        if (r0.equals("TRK") == false) goto L151;
        r7 = 6;
        goto L151
    L74:
        if (r0.equals("TP3") == false) goto L151;
        r7 = 5;
        goto L151
    L78:
        if (r0.equals("TP2") == false) goto L151;
        r7 = 4;
        goto L151
    L82:
        if (r0.equals("TP1") == false) goto L151;
        r7 = 3;
        goto L151
    L86:
        if (r0.equals("TDA") == false) goto L151;
        r7 = 2;
        goto L151
    L90:
        if (r0.equals("TCM") == false) goto L151;
        r7 = 1;
        goto L151
    L94:
        if (r0.equals("TAL") == false) goto L151;
        r7 = 0;
        goto L151
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": description=" + this.description + ": values=" + this.values;
    }
}
