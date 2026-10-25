package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* loaded from: classes4.dex */
public class ApiFeatureRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApiFeatureRequest> CREATOR = null;
    private static final Comparator zaa = null;
    private final List zab;
    private final boolean zac;
    private final String zad;
    private final String zae;

    static {
        CREATOR = new zac();
        zaa = zab.zaa;
    }

    public ApiFeatureRequest(List r1, boolean r2, String r3, String r4) {
        Preconditions.checkNotNull(r1);
        this.zab = r1;
        this.zac = r2;
        this.zad = r3;
        this.zae = r4;
    }

    public static ApiFeatureRequest fromModuleInstallRequest(ModuleInstallRequest r1) {
        return zaa(r1.getApis(), true);
    }

    public static ApiFeatureRequest zaa(List r2, boolean r3) {
        TreeSet r0 = new TreeSet(zaa);
        Iterator r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        Collections.addAll(r0, ((OptionalModuleApi) r22.next()).getOptionalFeatures());
        goto L4
    L7:
        return new ApiFeatureRequest(new ArrayList(r0), r3, null, null);
    }

    public final boolean equals(Object r4) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if ((r4 instanceof ApiFeatureRequest) == true) goto L8;
        return false;
    L8:
        ApiFeatureRequest r42 = (ApiFeatureRequest) r4;
        if (this.zac == r42.zac) goto L11;
    L18:
        return false;
    L11:
        if (Objects.equal(this.zab, r42.zab) == false) goto L18;
        if (Objects.equal(this.zad, r42.zad) == false) goto L18;
        if (Objects.equal(this.zae, r42.zae) == false) goto L18;
        return true;
    }

    public List<Feature> getApiFeatures() {
        return this.zab;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zac), this.zab, this.zad, this.zae});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeTypedList(r4, 1, getApiFeatures(), false);
        SafeParcelWriter.writeBoolean(r4, 2, this.zac);
        SafeParcelWriter.writeString(r4, 3, this.zad, false);
        SafeParcelWriter.writeString(r4, 4, this.zae, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
