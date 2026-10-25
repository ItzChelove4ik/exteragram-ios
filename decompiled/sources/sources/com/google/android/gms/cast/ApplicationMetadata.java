package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class ApplicationMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = null;
    String zza;
    String zzb;
    final List zzc;
    String zzd;
    Uri zze;
    String zzf;
    private String zzg;
    private Boolean zzh;
    private Boolean zzi;
    private final int zzj;

    static {
        CREATOR = new zzd();
    }

    private ApplicationMetadata() {
        this.zzc = new ArrayList();
        this.zzj = 1;
    }

    public ApplicationMetadata(String r1, String r2, List r3, List r4, String r5, Uri r6, String r7, String r8, Boolean r9, Boolean r10, int r11) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
        this.zzj = r11;
    }

    public boolean areNamespacesSupported(List<String> r1) {
        List r0 = this.zzc;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.containsAll(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ApplicationMetadata) == true) goto L8;
        return false;
    L8:
        ApplicationMetadata r52 = (ApplicationMetadata) r5;
        if (CastUtils.zza(this.zza, r52.zza) == true) goto L11;
    L25:
        return false;
    L11:
        if (CastUtils.zza(this.zzb, r52.zzb) == false) goto L25;
        if (CastUtils.zza(this.zzc, r52.zzc) == false) goto L25;
        if (CastUtils.zza(this.zzd, r52.zzd) == false) goto L25;
        if (CastUtils.zza(this.zze, r52.zze) == false) goto L25;
        if (CastUtils.zza(this.zzf, r52.zzf) == false) goto L25;
        if (CastUtils.zza(this.zzg, r52.zzg) == false) goto L25;
        if (this.zzj != r52.zzj) goto L25;
        return true;
    }

    public String getApplicationId() {
        return this.zza;
    }

    public String getIconUrl() {
        return this.zzf;
    }

    @Deprecated
    public List<WebImage> getImages() {
        return null;
    }

    public String getName() {
        return this.zzb;
    }

    public String getSenderAppIdentifier() {
        return this.zzd;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzc);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, Integer.valueOf(this.zzj)});
    }

    public boolean isNamespaceSupported(String r1) {
        List r0 = this.zzc;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void setIconUrl(String r1) {
        this.zzf = r1;
    }

    public String toString() {
        String r0 = this.zza;
        String r1 = this.zzb;
        List r2 = this.zzc;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        String r3 = this.zzd;
        String r4 = String.valueOf(this.zze);
        String r5 = this.zzf;
        String r9 = this.zzg;
        int r6 = String.valueOf(r0).length();
        int r62 = (r6 + 23) + String.valueOf(r1).length();
        int r7 = String.valueOf(r22).length();
        int r63 = ((((r62 + 20) + r7) + 23) + String.valueOf(r3).length()) + 22;
        int r72 = r4.length();
        int r64 = (r63 + r72) + 11;
        int r73 = String.valueOf(r5).length();
        StringBuilder r8 = new StringBuilder(((r64 + r73) + 8) + String.valueOf(r9).length());
        r8.append("applicationId: ");
        r8.append(r0);
        r8.append(", name: ");
        r8.append(r1);
        r8.append(", namespaces.count: ");
        r8.append(r22);
        r8.append(", senderAppIdentifier: ");
        r8.append(r3);
        r8.append(", senderAppLaunchUrl: ");
        r8.append(r4);
        r8.append(", iconUrl: ");
        r8.append(r5);
        r8.append(", type: ");
        r8.append(r9);
        return r8.toString();
    L5:
        r22 = r2.size();
        goto L6
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, getApplicationId(), false);
        SafeParcelWriter.writeString(r5, 3, getName(), false);
        SafeParcelWriter.writeTypedList(r5, 4, getImages(), false);
        SafeParcelWriter.writeStringList(r5, 5, getSupportedNamespaces(), false);
        SafeParcelWriter.writeString(r5, 6, getSenderAppIdentifier(), false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zze, r6, false);
        SafeParcelWriter.writeString(r5, 8, getIconUrl(), false);
        SafeParcelWriter.writeString(r5, 9, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(r5, 10, this.zzh, false);
        SafeParcelWriter.writeBooleanObject(r5, 11, this.zzi, false);
        SafeParcelWriter.writeInt(r5, 12, this.zzj);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
