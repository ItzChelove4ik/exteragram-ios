package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: classes3.dex */
public class IconCompatParcelizer {
    public IconCompatParcelizer() {
    }

    public static IconCompat read(VersionedParcel r3) {
        IconCompat r0 = new IconCompat();
        r0.mType = r3.readInt(r0.mType, 1);
        r0.mData = r3.readByteArray(r0.mData, 2);
        r0.mParcelable = r3.readParcelable(r0.mParcelable, 3);
        r0.mInt1 = r3.readInt(r0.mInt1, 4);
        r0.mInt2 = r3.readInt(r0.mInt2, 5);
        r0.mTintList = (ColorStateList) r3.readParcelable(r0.mTintList, 6);
        r0.mTintModeStr = r3.readString(r0.mTintModeStr, 7);
        r0.mString1 = r3.readString(r0.mString1, 8);
        r0.onPostParceling();
        return r0;
    }

    public static void write(IconCompat r3, VersionedParcel r4) {
        r4.setSerializationFlags(true, true);
        r3.onPreParceling(r4.isStream());
        int r1 = r3.mType;
        if ((-1) == r1) goto L5;
        r4.writeInt(r1, 1);
    L5:
        byte[] r0 = r3.mData;
        if (r0 == null) goto L8;
        r4.writeByteArray(r0, 2);
    L8:
        Parcelable r02 = r3.mParcelable;
        if (r02 == null) goto L11;
        r4.writeParcelable(r02, 3);
    L11:
        int r03 = r3.mInt1;
        if (r03 == 0) goto L14;
        r4.writeInt(r03, 4);
    L14:
        int r04 = r3.mInt2;
        if (r04 == 0) goto L17;
        r4.writeInt(r04, 5);
    L17:
        ColorStateList r05 = r3.mTintList;
        if (r05 == null) goto L20;
        r4.writeParcelable(r05, 6);
    L20:
        String r06 = r3.mTintModeStr;
        if (r06 == null) goto L23;
        r4.writeString(r06, 7);
    L23:
        String r32 = r3.mString1;
        if (r32 == null) goto L27;
        r4.writeString(r32, 8);
        return;
    }
}
