package android.support.v4.media;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaBrowserCompat$MediaItem> CREATOR = null;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;

    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaItem$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<MediaBrowserCompat$MediaItem> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaBrowserCompat$MediaItem createFromParcel(Parcel r1) {
            return new MediaBrowserCompat$MediaItem(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaBrowserCompat$MediaItem createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaBrowserCompat$MediaItem[] newArray(int r1) {
            return new MediaBrowserCompat$MediaItem[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaBrowserCompat$MediaItem[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public MediaBrowserCompat$MediaItem(Parcel r2) {
        this.mFlags = r2.readInt();
        this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(r2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "MediaItem{mFlags=" + this.mFlags + ", mDescription=" + this.mDescription + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r2, int r3) {
        r2.writeInt(this.mFlags);
        this.mDescription.writeToParcel(r2, r3);
    }
}
