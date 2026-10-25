package androidx.media3.common;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public final class StreamKey implements Comparable<StreamKey>, Parcelable {
    public static final Parcelable.Creator<StreamKey> CREATOR = null;
    private static final String FIELD_GROUP_INDEX = null;
    private static final String FIELD_PERIOD_INDEX = null;
    private static final String FIELD_STREAM_INDEX = null;
    public final int groupIndex;
    public final int periodIndex;
    public final int streamIndex;

    /* renamed from: androidx.media3.common.StreamKey$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<StreamKey> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreamKey createFromParcel(Parcel r1) {
            return new StreamKey(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ StreamKey createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreamKey[] newArray(int r1) {
            return new StreamKey[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ StreamKey[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
        FIELD_PERIOD_INDEX = Util.intToStringMaxRadix(0);
        FIELD_GROUP_INDEX = Util.intToStringMaxRadix(1);
        FIELD_STREAM_INDEX = Util.intToStringMaxRadix(2);
    }

    public StreamKey(int r1, int r2, int r3) {
        this.periodIndex = r1;
        this.groupIndex = r2;
        this.streamIndex = r3;
    }

    public StreamKey(Parcel r2) {
        this.periodIndex = r2.readInt();
        this.groupIndex = r2.readInt();
        this.streamIndex = r2.readInt();
    }

    public static StreamKey fromBundle(Bundle r5) {
        return new StreamKey(r5.getInt(FIELD_PERIOD_INDEX, 0), r5.getInt(FIELD_GROUP_INDEX, 0), r5.getInt(FIELD_STREAM_INDEX, 0));
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(StreamKey r3) {
        int r0 = this.periodIndex - r3.periodIndex;
        if (r0 != 0) goto L8;
        r0 = this.groupIndex - r3.groupIndex;
        if (r0 != 0) goto L8;
        return this.streamIndex - r3.streamIndex;
    L8:
        return r0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(StreamKey r1) {
        return compareTo2(r1);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (StreamKey.class != r5.getClass()) goto L17;
        StreamKey r52 = (StreamKey) r5;
        if (this.periodIndex != r52.periodIndex) goto L17;
        if (this.groupIndex != r52.groupIndex) goto L17;
        if (this.streamIndex != r52.streamIndex) goto L17;
        return true;
    }

    public int hashCode() {
        return (((this.periodIndex * 31) + this.groupIndex) * 31) + this.streamIndex;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        int r1 = this.periodIndex;
        if (r1 == 0) goto L5;
        r0.putInt(FIELD_PERIOD_INDEX, r1);
    L5:
        int r12 = this.groupIndex;
        if (r12 == 0) goto L8;
        r0.putInt(FIELD_GROUP_INDEX, r12);
    L8:
        int r3 = this.streamIndex;
        if (r3 == 0) goto L11;
        r0.putInt(FIELD_STREAM_INDEX, r3);
    L11:
        return r0;
    }

    public String toString() {
        return this.periodIndex + "." + this.groupIndex + "." + this.streamIndex;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeInt(this.periodIndex);
        r1.writeInt(this.groupIndex);
        r1.writeInt(this.streamIndex);
    }
}
