package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes4.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = null;
    private final CharSequence description;
    private MediaDescription descriptionFwk;
    private final Bundle extras;
    private final Bitmap icon;
    private final Uri iconUri;
    private final String mediaId;
    private final Uri mediaUri;
    private final CharSequence subtitle;
    private final CharSequence title;

    /* renamed from: androidx.media3.session.legacy.MediaDescriptionCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<MediaDescriptionCompat> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat createFromParcel(Parcel r1) {
            return MediaDescriptionCompat.fromMediaDescription((MediaDescription) MediaDescription.CREATOR.createFromParcel(r1));
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaDescriptionCompat createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat[] newArray(int r1) {
            return new MediaDescriptionCompat[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaDescriptionCompat[] newArray(int r1) {
            return newArray(r1);
        }
    }

    public static class Api23Impl {
        public static Uri getMediaUri(MediaDescription r0) {
            return r0.getMediaUri();
        }

        public static void setMediaUri(MediaDescription.Builder r0, Uri r1) {
            r0.setMediaUri(r1);
        }
    }

    public static final class Builder {
        private CharSequence description;
        private Bundle extras;
        private Bitmap icon;
        private Uri iconUri;
        private String mediaId;
        private Uri mediaUri;
        private CharSequence subtitle;
        private CharSequence title;

        public Builder() {
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mediaId, this.title, this.subtitle, this.description, this.icon, this.iconUri, this.extras, this.mediaUri);
        }

        public Builder setDescription(CharSequence r1) {
            this.description = r1;
            return this;
        }

        public Builder setExtras(Bundle r1) {
            this.extras = r1;
            return this;
        }

        public Builder setIconBitmap(Bitmap r1) {
            this.icon = r1;
            return this;
        }

        public Builder setIconUri(Uri r1) {
            this.iconUri = r1;
            return this;
        }

        public Builder setMediaId(String r1) {
            this.mediaId = r1;
            return this;
        }

        public Builder setMediaUri(Uri r1) {
            this.mediaUri = r1;
            return this;
        }

        public Builder setSubtitle(CharSequence r1) {
            this.subtitle = r1;
            return this;
        }

        public Builder setTitle(CharSequence r1) {
            this.title = r1;
            return this;
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public MediaDescriptionCompat(String r1, CharSequence r2, CharSequence r3, CharSequence r4, Bitmap r5, Uri r6, Bundle r7, Uri r8) {
        this.mediaId = r1;
        this.title = r2;
        this.subtitle = r3;
        this.description = r4;
        this.icon = r5;
        this.iconUri = r6;
        this.extras = r7;
        this.mediaUri = r8;
    }

    public static MediaDescriptionCompat fromMediaDescription(MediaDescription r8) {
        Builder r0 = new Builder();
        r0.setMediaId(r8.getMediaId());
        r0.setTitle(r8.getTitle());
        r0.setSubtitle(r8.getSubtitle());
        r0.setDescription(r8.getDescription());
        r0.setIconBitmap(r8.getIconBitmap());
        r0.setIconUri(r8.getIconUri());
        Bundle r1 = MediaSessionCompat.unparcelWithClassLoader(r8.getExtras());
        if (r1 == null) goto L5;
        r1 = new Bundle(r1);
    L5:
        Uri r2 = null;
        if (r1 == null) goto L16;
        Uri r4 = (Uri) r1.getParcelable("android.support.v4.media.description.MEDIA_URI");
        if (r4 != null) goto L10;
    L14:
        r2 = r4;
        goto L16
    L10:
        if (r1.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") == true) goto L12;
    L15:
        r1.remove("android.support.v4.media.description.MEDIA_URI");
        r1.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
        goto L14
    L12:
        if (r1.size() != 2) goto L15;
        r1 = null;
    L16:
        r0.setExtras(r1);
        if (r2 == null) goto L19;
        r0.setMediaUri(r2);
    L20:
        MediaDescriptionCompat r02 = r0.build();
        r02.descriptionFwk = r8;
        return r02;
    L19:
        r0.setMediaUri(Api23Impl.getMediaUri(r8));
        goto L20
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CharSequence getDescription() {
        return this.description;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public Bitmap getIconBitmap() {
        return this.icon;
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public MediaDescription getMediaDescription() {
        MediaDescription r0 = this.descriptionFwk;
        if (r0 == null) goto L5;
        return r0;
    L5:
        MediaDescription.Builder r02 = new MediaDescription.Builder();
        r02.setMediaId(this.mediaId);
        r02.setTitle(this.title);
        r02.setSubtitle(this.subtitle);
        r02.setDescription(this.description);
        r02.setIconBitmap(this.icon);
        r02.setIconUri(this.iconUri);
        r02.setExtras(this.extras);
        Api23Impl.setMediaUri(r02, this.mediaUri);
        MediaDescription r03 = r02.build();
        this.descriptionFwk = r03;
        return r03;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public Uri getMediaUri() {
        return this.mediaUri;
    }

    public CharSequence getSubtitle() {
        return this.subtitle;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public String toString() {
        return this.title + ", " + this.subtitle + ", " + this.description;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        getMediaDescription().writeToParcel(r1, r2);
    }
}
