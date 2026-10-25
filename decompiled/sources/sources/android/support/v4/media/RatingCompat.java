package android.support.v4.media;

import android.annotation.SuppressLint;
import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = null;
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;

    /* renamed from: android.support.v4.media.RatingCompat$1, reason: invalid class name */
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

    public static class Api19Impl {
        public static float getPercentRating(Rating r0) {
            return r0.getPercentRating();
        }

        public static int getRatingStyle(Rating r0) {
            return r0.getRatingStyle();
        }

        public static float getStarRating(Rating r0) {
            return r0.getStarRating();
        }

        public static boolean hasHeart(Rating r0) {
            return r0.hasHeart();
        }

        public static boolean isRated(Rating r0) {
            return r0.isRated();
        }

        public static boolean isThumbUp(Rating r0) {
            return r0.isThumbUp();
        }

        public static Rating newHeartRating(boolean r0) {
            return Rating.newHeartRating(r0);
        }

        public static Rating newPercentageRating(float r0) {
            return Rating.newPercentageRating(r0);
        }

        public static Rating newStarRating(int r0, float r1) {
            return Rating.newStarRating(r0, r1);
        }

        public static Rating newThumbRating(boolean r0) {
            return Rating.newThumbRating(r0);
        }

        public static Rating newUnratedRating(int r0) {
            return Rating.newUnratedRating(r0);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public RatingCompat(int r1, float r2) {
        this.mRatingStyle = r1;
        this.mRatingValue = r2;
    }

    public static RatingCompat fromRating(Object r4) {
        RatingCompat r0 = null;
        if (r4 == null) goto L14;
        Rating r1 = (Rating) r4;
        int r2 = Api19Impl.getRatingStyle(r1);
        if (Api19Impl.isRated(r1) == false) goto L12;
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
        r0 = newPercentageRating(Api19Impl.getPercentRating(r1));
    L13:
        r0.mRatingObj = r4;
        goto L14
    L9:
        r0 = newStarRating(r2, Api19Impl.getStarRating(r1));
        goto L13
    L10:
        r0 = newThumbRating(Api19Impl.isThumbUp(r1));
        goto L13
    L11:
        r0 = newHeartRating(Api19Impl.hasHeart(r1));
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
        return this.mRatingStyle;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("Rating:style=");
        r0.append(this.mRatingStyle);
        r0.append(" rating=");
        float r2 = this.mRatingValue;
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
        r1.writeInt(this.mRatingStyle);
        r1.writeFloat(this.mRatingValue);
    }
}
