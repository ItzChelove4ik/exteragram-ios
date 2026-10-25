package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = null;
    final int zaa;
    private final Uri zab;
    private final int zac;
    private final int zad;

    static {
        CREATOR = new zah();
    }

    public WebImage(int r1, Uri r2, int r3, int r4) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r4;
    }

    public WebImage(Uri r2) {
        this(r2, 0, 0);
    }

    public WebImage(Uri r2, int r3, int r4) {
        this(1, r2, r3, r4);
        if (r2 == null) goto L9;
        if (r3 < 0) goto L7;
        if (r4 < 0) goto L7;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("width and height must not be negative");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("url cannot be null");
        throw null;
    }

    public WebImage(JSONObject r5) {
        Uri r0 = Uri.EMPTY;
        if (r5.has("url") == true) goto L8;
    L5:
        this(r0, r5.optInt("width", 0), r5.optInt("height", 0));
        return;
    L8:
        r0 = Uri.parse(r5.getString("url"));     // Catch: JSONException -> L7
        goto L5
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if ((r5 instanceof WebImage) == false) goto L17;
        WebImage r52 = (WebImage) r5;
        if (Objects.equal(this.zab, r52.zab) == false) goto L17;
        if (this.zac != r52.zac) goto L17;
        if (this.zad != r52.zad) goto L17;
        return true;
    }

    public int getHeight() {
        return this.zad;
    }

    public Uri getUrl() {
        return this.zab;
    }

    public int getWidth() {
        return this.zac;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zab, Integer.valueOf(this.zac), Integer.valueOf(this.zad)});
    }

    public JSONObject toJson() {
        JSONObject r0 = new JSONObject();
        r0.put("url", this.zab.toString());     // Catch: JSONException -> L5
        r0.put("width", this.zac);     // Catch: JSONException -> L5
        r0.put("height", this.zad);     // Catch: JSONException -> L5
    L4:
        return r0;
    }

    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", new Object[]{Integer.valueOf(this.zac), Integer.valueOf(this.zad), this.zab.toString()});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zaa;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeParcelable(r5, 2, getUrl(), r6, false);
        SafeParcelWriter.writeInt(r5, 3, getWidth());
        SafeParcelWriter.writeInt(r5, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
