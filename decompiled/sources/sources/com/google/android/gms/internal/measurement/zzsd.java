package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public final class zzsd implements zzsx {
    public zzsd() {
        new zzsh();
    }

    public zzsd(zzsh r1) {
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final String zzc() {
        return "file";
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final InputStream zzd(Uri r1) {
        return zzsl.zzb(zzsc.zza(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final boolean zze(Uri r1) {
        return zzsc.zza(r1).exists();
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final File zzg(Uri r1) {
        return zzsc.zza(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final OutputStream zzj(Uri r2) {
        File r1 = zzsc.zza(r2);
        Files.createParentDirs(r1);
        return new zzsm(new FileOutputStream(r1), r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final void zzk(Uri r2) {
        File r1 = zzsc.zza(r2);
        if (r1.isDirectory() == true) goto L14;
        if (r1.delete() == false) goto L7;
        return;
    L7:
        if (r1.exists() == true) goto L11;
        throw new FileNotFoundException(String.format("%s does not exist", new Object[]{r2}));
    L11:
        throw new IOException(String.format("%s could not be deleted", new Object[]{r2}));
    L14:
        throw new FileNotFoundException(String.format("%s is a directory", new Object[]{r2}));
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final void zzl(Uri r2, Uri r3) {
        File r1 = zzsc.zza(r2);
        File r0 = zzsc.zza(r3);
        Files.createParentDirs(r0);
        if (r1.renameTo(r0) == false) goto L6;
        return;
    L6:
        throw new IOException(String.format("%s could not be renamed to %s", new Object[]{r2, r3}));
    }
}
