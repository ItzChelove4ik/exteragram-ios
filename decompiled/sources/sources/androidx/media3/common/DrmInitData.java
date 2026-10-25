package androidx.media3.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* loaded from: classes3.dex */
public final class DrmInitData implements Comparator<SchemeData>, Parcelable {
    public static final Parcelable.Creator<DrmInitData> CREATOR = null;
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;
    public final String schemeType;

    /* renamed from: androidx.media3.common.DrmInitData$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<DrmInitData> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DrmInitData createFromParcel(Parcel r1) {
            return new DrmInitData(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ DrmInitData createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DrmInitData[] newArray(int r1) {
            return new DrmInitData[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ DrmInitData[] newArray(int r1) {
            return newArray(r1);
        }
    }

    public static final class SchemeData implements Parcelable {
        public static final Parcelable.Creator<SchemeData> CREATOR = null;
        public final byte[] data;
        private int hashCode;
        public final String licenseServerUrl;
        public final String mimeType;
        public final UUID uuid;

        /* renamed from: androidx.media3.common.DrmInitData$SchemeData$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<SchemeData> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SchemeData createFromParcel(Parcel r1) {
                return new SchemeData(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SchemeData createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SchemeData[] newArray(int r1) {
                return new SchemeData[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ SchemeData[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SchemeData(Parcel r6) {
            this.uuid = new UUID(r6.readLong(), r6.readLong());
            this.licenseServerUrl = r6.readString();
            this.mimeType = (String) Util.castNonNull(r6.readString());
            this.data = r6.createByteArray();
        }

        public SchemeData(UUID r1, String r2, String r3, byte[] r4) {
            this.uuid = (UUID) Assertions.checkNotNull(r1);
            this.licenseServerUrl = r2;
            this.mimeType = MimeTypes.normalizeMimeType((String) Assertions.checkNotNull(r3));
            this.data = r4;
        }

        public SchemeData(UUID r2, String r3, byte[] r4) {
            this(r2, null, r3, r4);
        }

        public boolean canReplace(SchemeData r2) {
            if (hasData() == true) goto L5;
            return false;
        L5:
            if (r2.hasData() == false) goto L7;
            return false;
        L7:
            if (matches(r2.uuid) == false) goto L13;
            return true;
        L13:
            return false;
        }

        public SchemeData copyWithData(byte[] r4) {
            return new SchemeData(this.uuid, this.licenseServerUrl, this.mimeType, r4);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object r5) {
            if ((r5 instanceof SchemeData) == true) goto L6;
            return false;
        L6:
            if (r5 != this) goto L8;
            return true;
        L8:
            SchemeData r52 = (SchemeData) r5;
            if (Objects.equals(this.licenseServerUrl, r52.licenseServerUrl) == true) goto L11;
        L17:
            return false;
        L11:
            if (Objects.equals(this.mimeType, r52.mimeType) == false) goto L17;
            if (Objects.equals(this.uuid, r52.uuid) == false) goto L17;
            if (Arrays.equals(this.data, r52.data) == false) goto L17;
            return true;
        }

        public boolean hasData() {
            if (this.data == null) goto L6;
            return true;
        L6:
            return false;
        }

        public int hashCode() {
            if (this.hashCode != 0) goto L10;
            int r0 = this.uuid.hashCode() * 31;
            String r1 = this.licenseServerUrl;
            if (r1 != null) goto L7;
            int r12 = 0;
        L8:
            this.hashCode = ((((r0 + r12) * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            goto L10
        L7:
            r12 = r1.hashCode();
        L10:
            return this.hashCode;
        }

        public boolean matches(UUID r3) {
            if (C.UUID_NIL.equals(this.uuid) == false) goto L5;
            return true;
        L5:
            if (r3.equals(this.uuid) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r3, int r4) {
            r3.writeLong(this.uuid.getMostSignificantBits());
            r3.writeLong(this.uuid.getLeastSignificantBits());
            r3.writeString(this.licenseServerUrl);
            r3.writeString(this.mimeType);
            r3.writeByteArray(this.data);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public DrmInitData(Parcel r2) {
        this.schemeType = r2.readString();
        SchemeData[] r22 = (SchemeData[]) Util.castNonNull((SchemeData[]) r2.createTypedArray(SchemeData.CREATOR));
        this.schemeDatas = r22;
        this.schemeDataCount = r22.length;
    }

    public DrmInitData(String r3, List<SchemeData> r4) {
        this(r3, false, (SchemeData[]) r4.toArray(new SchemeData[0]));
    }

    private DrmInitData(String r1, boolean r2, SchemeData... r3) {
        this.schemeType = r1;
        if (r2 == false) goto L5;
        r3 = (SchemeData[]) r3.clone();
    L5:
        this.schemeDatas = r3;
        this.schemeDataCount = r3.length;
        Arrays.sort(r3, this);
    }

    public DrmInitData(String r2, SchemeData... r3) {
        this(r2, true, r3);
    }

    public DrmInitData(List<SchemeData> r3) {
        this(null, false, (SchemeData[]) r3.toArray(new SchemeData[0]));
    }

    public DrmInitData(SchemeData... r2) {
        this(null, r2);
    }

    private static boolean containsSchemeDataWithUuid(ArrayList<SchemeData> r3, int r4, UUID r5) {
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L9;
        if (r3.get(r1).uuid.equals(r5) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static DrmInitData createSessionCreationData(DrmInitData r8, DrmInitData r9) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        if (r8 == null) goto L10;
        String r3 = r8.schemeType;
        SchemeData[] r82 = r8.schemeDatas;
        int r4 = r82.length;
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L11;
        SchemeData r6 = r82[r5];
        if (r6.hasData() == false) goto L9;
        r0.add(r6);
    L9:
        r5 = r5 + 1;
    L11:
        if (r9 == null) goto L23;
        if (r3 != null) goto L14;
        r3 = r9.schemeType;
    L14:
        int r83 = r0.size();
        SchemeData[] r92 = r9.schemeDatas;
        int r42 = r92.length;
    L15:
        if (r1 >= r42) goto L23;
        SchemeData r52 = r92[r1];
        if (r52.hasData() == false) goto L21;
        if (containsSchemeDataWithUuid(r0, r83, r52.uuid) == true) goto L21;
        r0.add(r52);
    L21:
        r1 = r1 + 1;
    L23:
        if (r0.isEmpty() == false) goto L26;
        return null;
    L26:
        return new DrmInitData(r3, r0);
    L10:
        r3 = null;
        goto L11
    }

    /* renamed from: compare, reason: avoid collision after fix types in other method */
    public int compare2(SchemeData r2, SchemeData r3) {
        UUID r1 = C.UUID_NIL;
        if (r1.equals(r2.uuid) == false) goto L11;
        if (r1.equals(r3.uuid) == false) goto L8;
        return 0;
    L8:
        return 1;
    L11:
        return r2.uuid.compareTo(r3.uuid);
    }

    @Override // java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(SchemeData r1, SchemeData r2) {
        return compare2(r1, r2);
    }

    public DrmInitData copyWithSchemeType(String r3) {
        if (Objects.equals(this.schemeType, r3) == false) goto L6;
        return this;
    L6:
        return new DrmInitData(r3, false, this.schemeDatas);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (DrmInitData.class != r5.getClass()) goto L15;
        DrmInitData r52 = (DrmInitData) r5;
        if (Objects.equals(this.schemeType, r52.schemeType) == false) goto L15;
        if (Arrays.equals(this.schemeDatas, r52.schemeDatas) == false) goto L15;
        return true;
    }

    public SchemeData get(int r1) {
        return this.schemeDatas[r1];
    }

    public int hashCode() {
        if (this.hashCode != 0) goto L10;
        String r0 = this.schemeType;
        if (r0 != null) goto L7;
        int r02 = 0;
    L8:
        this.hashCode = (r02 * 31) + Arrays.hashCode(this.schemeDatas);
        goto L10
    L7:
        r02 = r0.hashCode();
    L10:
        return this.hashCode;
    }

    public DrmInitData merge(DrmInitData r3) {
        String r0 = this.schemeType;
        if (r0 == null) goto L10;
        String r1 = r3.schemeType;
        if (r1 == null) goto L10;
        if (TextUtils.equals(r0, r1) == true) goto L10;
        boolean r02 = false;
    L11:
        Assertions.checkState(r02);
        String r03 = this.schemeType;
        if (r03 != null) goto L16;
        r03 = r3.schemeType;
    L16:
        return new DrmInitData(r03, (SchemeData[]) Util.nullSafeArrayConcatenation(this.schemeDatas, r3.schemeDatas));
    L10:
        r02 = true;
        goto L11
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeString(this.schemeType);
        r1.writeTypedArray(this.schemeDatas, 0);
    }
}
