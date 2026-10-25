package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.media3.common.util.Assertions;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes4.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = null;
    private Object ratingObj;
    private final int ratingStyle;
    private final float ratingValue;

    /* renamed from: androidx.media3.session.legacy.RatingCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<RatingCompat> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat createFromParcel(Parcel r2) {
            return new RatingCompat(r2.readInt(), r2.readFloat());
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ RatingCompat createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat[] newArray(int r1) {
            return new RatingCompat[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ RatingCompat[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public RatingCompat(int r1, float r2) {
        this.ratingStyle = r1;
        this.ratingValue = r2;
    }

    @SuppressLint({"WrongConstant"})
    public static RatingCompat fromRating(Object r4) {
        RatingCompat r0 = null;
        if (r4 == null) goto L14;
        Rating r1 = (Rating) r4;
        int r2 = r1.getRatingStyle();
        if (r1.isRated() == false) goto L12;
        switch(r2) {
            case 1: goto L11;
            case 2: goto L10;
            case 3: goto L9;
            case 4: goto L9;
            case 5: goto L9;
            case 6: goto L8;
            default: goto L7;
        };
    L7:
        return null;
    L8:
        r0 = newPercentageRating(r1.getPercentRating());
    L13:
        ((RatingCompat) Assertions.checkNotNull(r0)).ratingObj = r4;
        goto L14
    L9:
        r0 = newStarRating(r2, r1.getStarRating());
        goto L13
    L10:
        r0 = newThumbRating(r1.isThumbUp());
        goto L13
    L11:
        r0 = newHeartRating(r1.hasHeart());
        goto L13
    L12:
        r0 = newUnratedRating(r2);
    L14:
        return r0;
    }

    public static RatingCompat newHeartRating(boolean r2) {
        if (r2 == false) goto L5;
        float r22 = 1.0f;
    L7:
        return new RatingCompat(1, r22);
    L5:
        r22 = 0.0f;
        goto L7
    }

    public static RatingCompat newPercentageRating(float r2) {
        if (r2 >= 0.0f) goto L5;
    L9:
        Log.e("Rating", "Invalid percentage-based rating value");
        return null;
    L5:
        if (r2 > 100.0f) goto L9;
        return new RatingCompat(6, r2);
    }

    public static RatingCompat newStarRating(int r4, float r5) {
        if (r4 != 3) goto L5;
        float r0 = 3.0f;
    L14:
        if (r5 >= 0.0f) goto L16;
    L20:
        Log.e("Rating", "Trying to set out of range star-based rating");
        return null;
    L16:
        if (r5 > r0) goto L20;
        return new RatingCompat(r4, r5);
    L5:
        if (r4 != 4) goto L7;
        r0 = 4.0f;
        goto L14
    L7:
        if (r4 == 5) goto L10;
        Log.e("Rating", "Invalid rating style (" + r4 + ") for a star rating");
        return null;
    L10:
        r0 = 5.0f;
        goto L14
    }

    public static RatingCompat newThumbRating(boolean r2) {
        if (r2 == false) goto L5;
        float r22 = 1.0f;
    L7:
        return new RatingCompat(2, r22);
    L5:
        r22 = 0.0f;
        goto L7
    }

    public static RatingCompat newUnratedRating(int r2) {
        switch(r2) {
            case 1: goto L6;
            case 2: goto L6;
            case 3: goto L6;
            case 4: goto L6;
            case 5: goto L6;
            case 6: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return new RatingCompat(r2, -1.0f);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.ratingStyle;
    }

    public float getPercentRating() {
        if (this.ratingStyle == 6) goto L5;
        return -1.0f;
    L5:
        if (isRated() == true) goto L8;
        return -1.0f;
    L8:
        return this.ratingValue;
    }

    public Object getRating() {
        if (this.ratingObj != null) goto L15;
        boolean r0 = isRated();
        int r1 = this.ratingStyle;
        if (r0 == false) goto L13;
        switch(r1) {
            case 1: goto L12;
            case 2: goto L11;
            case 3: goto L10;
            case 4: goto L10;
            case 5: goto L10;
            case 6: goto L9;
            default: goto L7;
        };
    L7:
        return null;
    L9:
        this.ratingObj = Rating.newPercentageRating(getPercentRating());
        goto L15
    L10:
        this.ratingObj = Rating.newStarRating(r1, getStarRating());
        goto L15
    L11:
        this.ratingObj = Rating.newThumbRating(isThumbUp());
        goto L15
    L12:
        this.ratingObj = Rating.newHeartRating(hasHeart());
        goto L15
    L13:
        this.ratingObj = Rating.newUnratedRating(r1);
    L15:
        return this.ratingObj;
    }

    public int getRatingStyle() {
        return this.ratingStyle;
    }

    public float getStarRating() {
        int r0 = this.ratingStyle;
        if (r0 == 3) goto L10;
        if (r0 == 4) goto L10;
        if (r0 == 5) goto L10;
        return -1.0f;
    L10:
        if (isRated() == true) goto L12;
        return -1.0f;
    L12:
        return this.ratingValue;
    }

    public boolean hasHeart() {
        if (this.ratingStyle == 1) goto L6;
        return false;
    L6:
        if (this.ratingValue != 1.0f) goto L8;
        return true;
    L8:
        return false;
    }

    public boolean isRated() {
        if (this.ratingValue < 0.0f) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isThumbUp() {
        if (this.ratingStyle == 2) goto L6;
        return false;
    L6:
        if (this.ratingValue != 1.0f) goto L9;
        return true;
    L9:
        return false;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("Rating:style=");
        r0.append(this.ratingStyle);
        r0.append(" rating=");
        float r2 = this.ratingValue;
        if (r2 >= 0.0f) goto L5;
        String r22 = "unrated";
    L6:
        r0.append(r22);
        return r0.toString();
    L5:
        r22 = String.valueOf(r2);
        goto L6
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeInt(this.ratingStyle);
        r1.writeFloat(this.ratingValue);
    }
}
