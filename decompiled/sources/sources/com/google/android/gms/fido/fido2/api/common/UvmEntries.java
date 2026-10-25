package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class UvmEntries extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UvmEntries> CREATOR = null;
    private final List zza;

    static {
        CREATOR = new zzbd();
    }

    public UvmEntries(List r1) {
        this.zza = r1;
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof UvmEntries) == true) goto L5;
        return false;
    L5:
        UvmEntries r52 = (UvmEntries) r5;
        List r0 = this.zza;
        if (r0 == null) goto L8;
    L11:
        if (r0 == null) goto L19;
        List r3 = r52.zza;
        if (r3 == null) goto L19;
        if (r0.containsAll(r3) == false) goto L19;
        if (r52.zza.containsAll(this.zza) == false) goto L19;
        return true;
    L19:
        return false;
    L8:
        if (r52.zza != null) goto L11;
        return true;
    }

    public List<UvmEntry> getUvmEntryList() {
        return this.zza;
    }

    public int hashCode() {
        List r1 = this.zza;
        if (r1 != null) goto L5;
        HashSet r12 = null;
    L7:
        return Objects.hashCode(new Object[]{r12});
    L5:
        r12 = new HashSet(r1);
        goto L7
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 1, getUvmEntryList(), false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final JSONArray zza() {
        JSONArray r0 = new JSONArray();     // Catch: JSONException -> L10
        if (this.zza == null) goto L9;
        int r1 = 0;
    L5:
        if (r1 >= this.zza.size()) goto L9;
        UvmEntry r2 = (UvmEntry) this.zza.get(r1);     // Catch: JSONException -> L10
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L10
        r3.put(r2.getMatcherProtectionType());     // Catch: JSONException -> L10
        r3.put(r2.getKeyProtectionType());     // Catch: JSONException -> L10
        r3.put(r2.getMatcherProtectionType());     // Catch: JSONException -> L10
        r0.put(r1, r3);     // Catch: JSONException -> L10
        r1 = r1 + 1;
    L9:
        return r0;
    L10:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding UvmEntries to JSON object", e);
        return null;
    }
}
