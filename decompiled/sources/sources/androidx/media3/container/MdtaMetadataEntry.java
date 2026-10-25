package androidx.media3.container;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public final class MdtaMetadataEntry implements Metadata.Entry {
    public final String key;
    public final int localeIndicator;
    public final int typeIndicator;
    public final byte[] value;

    public MdtaMetadataEntry(String r1, byte[] r2, int r3, int r4) {
        validateData(r1, r2, r4);
        this.key = r1;
        this.value = r2;
        this.localeIndicator = r3;
        this.typeIndicator = r4;
    }

    private static String getFormattedValueForAuxiliaryTracksMap(List<Integer> r2) {
        StringBuilder r0 = new StringBuilder();
        r0.append("track types = ");
        Joiner.on(',').appendTo(r0, r2);
        return r0.toString();
    }

    private static void validateData(String r5, byte[] r6, int r7) {
        r5.getClass();
        boolean r2 = false;
        char r4 = 65535;
        switch(r5.hashCode()) {
            case -1949883051: goto L22;
            case -269399509: goto L18;
            case 1011693540: goto L14;
            case 1098277265: goto L10;
            case 2002123038: goto L6;
            default: goto L25;
        };
    L25:
        switch(r4) {
            case 0: goto L49;
            case 1: goto L39;
            case 2: goto L32;
            case 3: goto L32;
            case 4: goto L27;
            default: goto L26;
        };
    L26:
        return;
    L27:
        if (r7 != 0) goto L29;
        r2 = true;
    L29:
        Assertions.checkArgument(r2);
        return;
    L32:
        if (r7 == 78) goto L34;
    L36:
        Assertions.checkArgument(r2);
        return;
    L34:
        if (r6.length != 8) goto L36;
        r2 = true;
        goto L36
    L39:
        if (r7 == 75) goto L41;
    L46:
        Assertions.checkArgument(r2);
        return;
    L41:
        if (r6.length != 1) goto L46;
        byte r52 = r6[0];
        if (r52 == 0) goto L45;
        if (r52 != 1) goto L46;
    L45:
        r2 = true;
        goto L46
    L49:
        if (r7 == 23) goto L51;
    L53:
        Assertions.checkArgument(r2);
        return;
    L51:
        if (r6.length != 4) goto L53;
        r2 = true;
        goto L53
    L6:
        if (r5.equals("auxiliary.tracks.map") == false) goto L25;
        r4 = 4;
        goto L25
    L10:
        if (r5.equals("auxiliary.tracks.offset") == false) goto L25;
        r4 = 3;
        goto L25
    L14:
        if (r5.equals("auxiliary.tracks.length") == false) goto L25;
        r4 = 2;
        goto L25
    L18:
        if (r5.equals("auxiliary.tracks.interleaved") == false) goto L25;
        r4 = 1;
        goto L25
    L22:
        if (r5.equals("com.android.capture.fps") == false) goto L25;
        r4 = 0;
        goto L25
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L19:
        return false;
    L8:
        if (MdtaMetadataEntry.class != r5.getClass()) goto L19;
        MdtaMetadataEntry r52 = (MdtaMetadataEntry) r5;
        if (this.key.equals(r52.key) == false) goto L19;
        if (Arrays.equals(this.value, r52.value) == false) goto L19;
        if (this.localeIndicator != r52.localeIndicator) goto L19;
        if (this.typeIndicator != r52.typeIndicator) goto L19;
        return true;
    }

    public List<Integer> getAuxiliaryTrackTypesFromMap() {
        Assertions.checkState(this.key.equals("auxiliary.tracks.map"), "Metadata is not an auxiliary tracks map");
        byte r0 = this.value[1];
        ArrayList r1 = new ArrayList();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.add(Integer.valueOf(this.value[r2 + 2]));
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public int hashCode() {
        return ((((((527 + this.key.hashCode()) * 31) + Arrays.hashCode(this.value)) * 31) + this.localeIndicator) * 31) + this.typeIndicator;
    }

    public String toString() {
        int r0 = this.typeIndicator;
        if (r0 == 0) goto L21;
        if (r0 != 1) goto L7;
        String r02 = Util.fromUtf8Bytes(this.value);
    L25:
        return "mdta: key=" + this.key + ", value=" + r02;
    L7:
        if (r0 != 23) goto L9;
        r02 = String.valueOf(Float.intBitsToFloat(Ints.fromByteArray(this.value)));
        goto L25
    L9:
        if (r0 != 67) goto L11;
        r02 = String.valueOf(Ints.fromByteArray(this.value));
        goto L25
    L11:
        if (r0 != 75) goto L13;
        r02 = String.valueOf(MdtaMetadataEntry$$ExternalSyntheticBackport0.m(this.value[0]));
        goto L25
    L13:
        if (r0 != 78) goto L23;
        r02 = String.valueOf(new ParsableByteArray(this.value).readUnsignedLongToLong());
    L23:
        r02 = Util.toHexString(this.value);
        goto L25
    L21:
        if (this.key.equals("auxiliary.tracks.map") == false) goto L23;
        r02 = getFormattedValueForAuxiliaryTracksMap(getAuxiliaryTrackTypesFromMap());
        goto L25
    }
}
