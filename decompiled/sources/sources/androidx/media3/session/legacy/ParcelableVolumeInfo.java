package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes4.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = null;
    public int audioStream;
    public int controlType;
    public int currentVolume;
    public int maxVolume;
    public int volumeType;

    /* renamed from: androidx.media3.session.legacy.ParcelableVolumeInfo$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<ParcelableVolumeInfo> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableVolumeInfo createFromParcel(Parcel r1) {
            return new ParcelableVolumeInfo(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelableVolumeInfo createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableVolumeInfo[] newArray(int r1) {
            return new ParcelableVolumeInfo[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ ParcelableVolumeInfo[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public ParcelableVolumeInfo(Parcel r2) {
        this.volumeType = r2.readInt();
        this.controlType = r2.readInt();
        this.maxVolume = r2.readInt();
        this.currentVolume = r2.readInt();
        this.audioStream = r2.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeInt(this.volumeType);
        r1.writeInt(this.controlType);
        r1.writeInt(this.maxVolume);
        r1.writeInt(this.currentVolume);
        r1.writeInt(this.audioStream);
    }
}
