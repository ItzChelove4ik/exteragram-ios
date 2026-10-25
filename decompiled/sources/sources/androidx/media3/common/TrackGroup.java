package androidx.media3.common;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.TranslateController;

/* loaded from: classes3.dex */
public final class TrackGroup {
    private static final String FIELD_FORMATS = null;
    private static final String FIELD_ID = null;
    private final Format[] formats;
    private int hashCode;
    public final String id;
    public final int length;
    public final int type;

    static {
        FIELD_FORMATS = Util.intToStringMaxRadix(0);
        FIELD_ID = Util.intToStringMaxRadix(1);
    }

    public TrackGroup(String r3, Format... r4) {
        if (r4.length <= 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        this.id = r3;
        this.formats = r4;
        this.length = r4.length;
        int r32 = MimeTypes.getTrackType(r4[0].sampleMimeType);
        if (r32 != (-1)) goto L9;
        r32 = MimeTypes.getTrackType(r4[0].containerMimeType);
    L9:
        this.type = r32;
        verifyCorrectness();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public TrackGroup(Format... r2) {
        this(_UrlKt.FRAGMENT_ENCODE_SET, r2);
    }

    public static TrackGroup fromBundle(Bundle r3) {
        ArrayList r0 = r3.getParcelableArrayList(FIELD_FORMATS);
        if (r0 != null) goto L5;
        ImmutableList r02 = ImmutableList.of();
    L7:
        return new TrackGroup(r3.getString(FIELD_ID, _UrlKt.FRAGMENT_ENCODE_SET), (Format[]) r02.toArray(new Format[0]));
    L5:
        r02 = BundleCollectionUtil.fromBundleList(new TrackGroup$$ExternalSyntheticLambda0(), r0);
        goto L7
    }

    private static void logErrorMessage(String r3, String r4, String r5, int r6) {
        Log.e("TrackGroup", _UrlKt.FRAGMENT_ENCODE_SET, new IllegalStateException("Different " + r3 + " combined in one TrackGroup: '" + r4 + "' (track 0) and '" + r5 + "' (track " + r6 + ")"));
    }

    private static String normalizeLanguage(String r1) {
        if (r1 != null) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        if (r1.equals(TranslateController.UNKNOWN_LANGUAGE) == true) goto L9;
        return r1;
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static int normalizeRoleFlags(int r0) {
        return r0 | 16384;
    }

    private void verifyCorrectness() {
        String r0 = normalizeLanguage(this.formats[0].language);
        int r2 = normalizeRoleFlags(this.formats[0].roleFlags);
        int r3 = 1;
    L3:
        Format[] r4 = this.formats;
        if (r3 >= r4.length) goto L14;
        boolean r42 = r0.equals(normalizeLanguage(r4[r3].language));
        Format[] r5 = this.formats;
        if (r42 == false) goto L7;
        if (r2 != normalizeRoleFlags(r5[r3].roleFlags)) goto L11;
        r3 = r3 + 1;
        goto L3
    L11:
        logErrorMessage("role flags", Integer.toBinaryString(this.formats[0].roleFlags), Integer.toBinaryString(this.formats[r3].roleFlags), r3);
        return;
    L7:
        logErrorMessage("languages", r5[0].language, r5[r3].language, r3);
        return;
    }

    public TrackGroup copyWithId(String r2) {
        return new TrackGroup(r2, this.formats);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (TrackGroup.class != r5.getClass()) goto L15;
        TrackGroup r52 = (TrackGroup) r5;
        if (this.id.equals(r52.id) == false) goto L15;
        if (Arrays.equals(this.formats, r52.formats) == false) goto L15;
        return true;
    }

    public Format getFormat(int r1) {
        return this.formats[r1];
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L6;
        this.hashCode = ((527 + this.id.hashCode()) * 31) + Arrays.hashCode(this.formats);
    L6:
        return this.hashCode;
    }

    public int indexOf(Format r4) {
        int r0 = 0;
    L3:
        Format[] r1 = this.formats;
        if (r0 >= r1.length) goto L9;
        if (r4 == r1[r0]) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return r0;
    L9:
        return -1;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        ArrayList<? extends Parcelable> r1 = new ArrayList(this.formats.length);
        Format[] r2 = this.formats;
        int r3 = r2.length;
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        r1.add(r2[r4].toBundle());
        r4 = r4 + 1;
        goto L3
    L5:
        r0.putParcelableArrayList(FIELD_FORMATS, r1);
        r0.putString(FIELD_ID, this.id);
        return r0;
    }

    public String toString() {
        return this.id + ": " + Arrays.toString(this.formats);
    }
}
