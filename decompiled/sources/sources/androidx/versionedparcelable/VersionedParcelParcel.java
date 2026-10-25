package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.collection.ArrayMap;
import java.lang.reflect.Method;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
class VersionedParcelParcel extends VersionedParcel {
    private int mCurrentField;
    private final int mEnd;
    private int mFieldId;
    private int mNextRead;
    private final int mOffset;
    private final Parcel mParcel;
    private final SparseIntArray mPositionLookup;
    private final String mPrefix;

    public VersionedParcelParcel(Parcel r9) {
        this(r9, r9.dataPosition(), r9.dataSize(), _UrlKt.FRAGMENT_ENCODE_SET, new ArrayMap(), new ArrayMap(), new ArrayMap());
    }

    private VersionedParcelParcel(Parcel r1, int r2, int r3, String r4, ArrayMap<String, Method> r5, ArrayMap<String, Method> r6, ArrayMap<String, Class> r7) {
        super(r5, r6, r7);
        this.mPositionLookup = new SparseIntArray();
        this.mCurrentField = -1;
        this.mFieldId = -1;
        this.mParcel = r1;
        this.mOffset = r2;
        this.mEnd = r3;
        this.mNextRead = r2;
        this.mPrefix = r4;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void closeField() {
        int r0 = this.mCurrentField;
        if (r0 < 0) goto L6;
        int r02 = this.mPositionLookup.get(r0);
        int r1 = this.mParcel.dataPosition();
        int r2 = r1 - r02;
        this.mParcel.setDataPosition(r02);
        this.mParcel.writeInt(r2);
        this.mParcel.setDataPosition(r1);
        return;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public VersionedParcel createSubParcel() {
        Parcel r1 = this.mParcel;
        int r2 = r1.dataPosition();
        int r3 = this.mNextRead;
        if (r3 != this.mOffset) goto L6;
        r3 = this.mEnd;
    L6:
        return new VersionedParcelParcel(r1, r2, r3, this.mPrefix + "  ", this.mReadCache, this.mWriteCache, this.mParcelizerCache);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean readBoolean() {
        if (this.mParcel.readInt() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public byte[] readByteArray() {
        int r0 = this.mParcel.readInt();
        if (r0 >= 0) goto L6;
        return null;
    L6:
        byte[] r02 = new byte[r0];
        this.mParcel.readByteArray(r02);
        return r02;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public CharSequence readCharSequence() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.mParcel);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public boolean readField(int r6) {
    L2:
        int r0 = this.mNextRead;
        int r1 = this.mEnd;
        int r2 = this.mFieldId;
        if (r0 >= r1) goto L10;
        if (r2 == r6) goto L5;
        if (String.valueOf(r2).compareTo(String.valueOf(r6)) > 0) goto L8;
        this.mParcel.setDataPosition(this.mNextRead);
        int r02 = this.mParcel.readInt();
        this.mFieldId = this.mParcel.readInt();
        this.mNextRead += r02;
        goto L2
    L8:
        return false;
    L5:
        return true;
    L10:
        if (r2 != r6) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public int readInt() {
        return this.mParcel.readInt();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public <T extends Parcelable> T readParcelable() {
        return (T) this.mParcel.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public String readString() {
        return this.mParcel.readString();
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void setOutputField(int r3) {
        closeField();
        this.mCurrentField = r3;
        this.mPositionLookup.put(r3, this.mParcel.dataPosition());
        writeInt(0);
        writeInt(r3);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeBoolean(boolean r1) {
        this.mParcel.writeInt(r1 ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] r3) {
        Parcel r0 = this.mParcel;
        if (r3 == null) goto L6;
        r0.writeInt(r3.length);
        this.mParcel.writeByteArray(r3);
        return;
    L6:
        r0.writeInt(-1);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeCharSequence(CharSequence r2) {
        TextUtils.writeToParcel(r2, this.mParcel, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeInt(int r1) {
        this.mParcel.writeInt(r1);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeParcelable(Parcelable r2) {
        this.mParcel.writeParcelable(r2, 0);
    }

    @Override // androidx.versionedparcelable.VersionedParcel
    public void writeString(String r1) {
        this.mParcel.writeString(r1);
    }
}
