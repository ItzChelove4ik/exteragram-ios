package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = null;
    private final CharSequence mDescription;
    private MediaDescription mDescriptionFwk;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    /* renamed from: android.support.v4.media.MediaDescriptionCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<MediaDescriptionCompat> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaDescriptionCompat createFromParcel(Parcel r1) {
            return MediaDescriptionCompat.fromMediaDescription(MediaDescription.CREATOR.createFromParcel(r1));
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

    public static class Api21Impl {
        public static MediaDescription build(MediaDescription.Builder r0) {
            return r0.build();
        }

        public static MediaDescription.Builder createBuilder() {
            return new MediaDescription.Builder();
        }

        public static CharSequence getDescription(MediaDescription r0) {
            return r0.getDescription();
        }

        public static Bundle getExtras(MediaDescription r0) {
            return r0.getExtras();
        }

        public static Bitmap getIconBitmap(MediaDescription r0) {
            return r0.getIconBitmap();
        }

        public static Uri getIconUri(MediaDescription r0) {
            return r0.getIconUri();
        }

        public static String getMediaId(MediaDescription r0) {
            return r0.getMediaId();
        }

        public static CharSequence getSubtitle(MediaDescription r0) {
            return r0.getSubtitle();
        }

        public static CharSequence getTitle(MediaDescription r0) {
            return r0.getTitle();
        }

        public static void setDescription(MediaDescription.Builder r0, CharSequence r1) {
            r0.setDescription(r1);
        }

        public static void setExtras(MediaDescription.Builder r0, Bundle r1) {
            r0.setExtras(r1);
        }

        public static void setIconBitmap(MediaDescription.Builder r0, Bitmap r1) {
            r0.setIconBitmap(r1);
        }

        public static void setIconUri(MediaDescription.Builder r0, Uri r1) {
            r0.setIconUri(r1);
        }

        public static void setMediaId(MediaDescription.Builder r0, String r1) {
            r0.setMediaId(r1);
        }

        public static void setSubtitle(MediaDescription.Builder r0, CharSequence r1) {
            r0.setSubtitle(r1);
        }

        public static void setTitle(MediaDescription.Builder r0, CharSequence r1) {
            r0.setTitle(r1);
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
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder() {
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }

        public Builder setDescription(CharSequence r1) {
            this.mDescription = r1;
            return this;
        }

        public Builder setExtras(Bundle r1) {
            this.mExtras = r1;
            return this;
        }

        public Builder setIconBitmap(Bitmap r1) {
            this.mIcon = r1;
            return this;
        }

        public Builder setIconUri(Uri r1) {
            this.mIconUri = r1;
            return this;
        }

        public Builder setMediaId(String r1) {
            this.mMediaId = r1;
            return this;
        }

        public Builder setMediaUri(Uri r1) {
            this.mMediaUri = r1;
            return this;
        }

        public Builder setSubtitle(CharSequence r1) {
            this.mSubtitle = r1;
            return this;
        }

        public Builder setTitle(CharSequence r1) {
            this.mTitle = r1;
            return this;
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public MediaDescriptionCompat(String r1, CharSequence r2, CharSequence r3, CharSequence r4, Bitmap r5, Uri r6, Bundle r7, Uri r8) {
        this.mMediaId = r1;
        this.mTitle = r2;
        this.mSubtitle = r3;
        this.mDescription = r4;
        this.mIcon = r5;
        this.mIconUri = r6;
        this.mExtras = r7;
        this.mMediaUri = r8;
    }

    public static MediaDescriptionCompat fromMediaDescription(Object r8) {
        Bundle r0 = null;
        if (r8 == null) goto L25;
        Builder r1 = new Builder();
        MediaDescription r82 = (MediaDescription) r8;
        r1.setMediaId(Api21Impl.getMediaId(r82));
        r1.setTitle(Api21Impl.getTitle(r82));
        r1.setSubtitle(Api21Impl.getSubtitle(r82));
        r1.setDescription(Api21Impl.getDescription(r82));
        r1.setIconBitmap(Api21Impl.getIconBitmap(r82));
        r1.setIconUri(Api21Impl.getIconUri(r82));
        Bundle r2 = Api21Impl.getExtras(r82);
        if (r2 == null) goto L8;
        r2 = MediaSessionCompat.unparcelWithClassLoader(r2);
    L8:
        if (r2 == null) goto L10;
        Uri r4 = (Uri) r2.getParcelable("android.support.v4.media.description.MEDIA_URI");
    L11:
        if (r4 != null) goto L13;
    L18:
        r0 = r2;
    L19:
        r1.setExtras(r0);
        if (r4 == null) goto L22;
        r1.setMediaUri(r4);
    L23:
        MediaDescriptionCompat r02 = r1.build();
        r02.mDescriptionFwk = r82;
        return r02;
    L22:
        r1.setMediaUri(Api23Impl.getMediaUri(r82));
        goto L23
    L13:
        if (r2.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") == true) goto L15;
    L17:
        r2.remove("android.support.v4.media.description.MEDIA_URI");
        r2.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
        goto L18
    L15:
        if (r2.size() != 2) goto L17;
    L10:
        r4 = null;
        goto L11
    L25:
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public Object getMediaDescription() {
        MediaDescription r0 = this.mDescriptionFwk;
        if (r0 != null) goto L6;
        MediaDescription.Builder r02 = Api21Impl.createBuilder();
        Api21Impl.setMediaId(r02, this.mMediaId);
        Api21Impl.setTitle(r02, this.mTitle);
        Api21Impl.setSubtitle(r02, this.mSubtitle);
        Api21Impl.setDescription(r02, this.mDescription);
        Api21Impl.setIconBitmap(r02, this.mIcon);
        Api21Impl.setIconUri(r02, this.mIconUri);
        Api21Impl.setExtras(r02, this.mExtras);
        Api23Impl.setMediaUri(r02, this.mMediaUri);
        MediaDescription r03 = Api21Impl.build(r02);
        this.mDescriptionFwk = r03;
        return r03;
    L6:
        return r0;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public String toString() {
        return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        ((MediaDescription) getMediaDescription()).writeToParcel(r1, r2);
    }
}
