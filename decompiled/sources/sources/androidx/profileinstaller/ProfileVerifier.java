package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.concurrent.futures.ResolvableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.mvel2.asm.Opcodes;

/* loaded from: classes.dex */
public abstract class ProfileVerifier {
    private static final Object SYNC_OBJ = null;
    private static CompilationStatus sCompilationStatus;
    private static final ResolvableFuture<CompilationStatus> sFuture = null;

    public static class Api33Impl {
        public static PackageInfo getPackageInfo(PackageManager r2, Context r3) {
            return r2.getPackageInfo(r3.getPackageName(), PackageManager.PackageInfoFlags.of(0));
        }
    }

    public static class Cache {
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        public Cache(int r1, int r2, long r3, long r5) {
            this.mSchema = r1;
            this.mResultCode = r2;
            this.mPackageLastUpdateTime = r3;
            this.mInstalledCurrentProfileSize = r5;
        }

        public static Cache readFromFile(File r9) {
            DataInputStream r1 = new DataInputStream(new FileInputStream(r9));
            Cache r2 = new Cache(r1.readInt(), r1.readInt(), r1.readLong(), r1.readLong());     // Catch: Throwable -> L6
            r1.close();
            return r2;
        L6:
            th = move-exception;
            r1.close();     // Catch: Throwable -> L10
            throw th;
        L10:
            th = move-exception;
            th.addSuppressed(th);
            throw th;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L19:
            return false;
        L8:
            if ((r7 instanceof Cache) == false) goto L19;
            Cache r72 = (Cache) r7;
            if (this.mResultCode != r72.mResultCode) goto L19;
            if (this.mPackageLastUpdateTime != r72.mPackageLastUpdateTime) goto L19;
            if (this.mSchema != r72.mSchema) goto L19;
            if (this.mInstalledCurrentProfileSize != r72.mInstalledCurrentProfileSize) goto L19;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize)});
        }

        public void writeOnFile(File r4) {
            r4.delete();
            DataOutputStream r0 = new DataOutputStream(new FileOutputStream(r4));
            r0.writeInt(this.mSchema);     // Catch: Throwable -> L6
            r0.writeInt(this.mResultCode);     // Catch: Throwable -> L6
            r0.writeLong(this.mPackageLastUpdateTime);     // Catch: Throwable -> L6
            r0.writeLong(this.mInstalledCurrentProfileSize);     // Catch: Throwable -> L6
            r0.close();
            return;
        L6:
            th = move-exception;
            r0.close();     // Catch: Throwable -> L9
        L11:
            throw th;
        L9:
            th = move-exception;
            th.addSuppressed(th);
            goto L11
        }
    }

    public static class CompilationStatus {
        private final boolean mHasCurrentProfile;
        private final boolean mHasEmbeddedProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        public CompilationStatus(int r1, boolean r2, boolean r3, boolean r4) {
            this.mResultCode = r1;
            this.mHasCurrentProfile = r3;
            this.mHasReferenceProfile = r2;
            this.mHasEmbeddedProfile = r4;
        }
    }

    static {
        sFuture = ResolvableFuture.create();
        SYNC_OBJ = new Object();
        sCompilationStatus = null;
    }

    private static long getPackageLastUpdateTime(Context r3) {
        PackageManager r0 = r3.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return Api33Impl.getPackageInfo(r0, r3).lastUpdateTime;
    L7:
        return r0.getPackageInfo(r3.getPackageName(), 0).lastUpdateTime;
    }

    private static CompilationStatus setCompilationStatus(int r1, boolean r2, boolean r3, boolean r4) {
        CompilationStatus r0 = new CompilationStatus(r1, r2, r3, r4);
        sCompilationStatus = r0;
        sFuture.set(r0);
        return sCompilationStatus;
    }

    public static CompilationStatus writeProfileVerification(Context r19, boolean r20) {
        if (r20 == true) goto L6;
        CompilationStatus r0 = sCompilationStatus;
        if (r0 == null) goto L6;
        return r0;
    L6:
        Object r1 = SYNC_OBJ;
        monitor-enter(r1);
        if (r20 == false) goto L105;
    L15:
        int r5 = 0;
        AssetFileDescriptor r6 = r19.getAssets().openFd("dexopt/baseline.prof");     // Catch: Throwable -> L13 IOException -> L32
        if (r6.getLength() <= 0) goto L21;
        boolean r02 = true;
    L22:
        r6.close();     // Catch: Throwable -> L13 IOException -> L32
    L33:
        int r62 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L13
        if (r62 >= 28) goto L36;
    L98:
        CompilationStatus r03 = setCompilationStatus(262144, false, false, r02);     // Catch: Throwable -> L13
        monitor-exit(r1);     // Catch: Throwable -> L13
        return r03;
    L36:
        if (r62 == 30) goto L98;
        File r63 = new File(new File("/data/misc/profiles/ref/", r19.getPackageName()), "primary.prof");     // Catch: Throwable -> L13
        long r7 = r63.length();     // Catch: Throwable -> L13
        if (r63.exists() == true) goto L41;
    L43:
        boolean r64 = false;
    L44:
        File r9 = new File(new File("/data/misc/profiles/cur/0/", r19.getPackageName()), "primary.prof");     // Catch: Throwable -> L13
        long r17 = r9.length();     // Catch: Throwable -> L13
        if (r9.exists() == true) goto L47;
    L49:
        boolean r2 = false;
    L106:
        long r15 = getPackageLastUpdateTime(r19);     // Catch: Throwable -> L13 PackageManager.NameNotFoundException -> L95
        File r3 = new File(r19.getFilesDir(), "profileInstalled");     // Catch: Throwable -> L13
        if (r3.exists() == true) goto L110;
        Cache r92 = null;
    L61:
        if (r92 != null) goto L63;
    L68:
        if (r02 == true) goto L70;
        r5 = Opcodes.ASM5;
    L74:
        if (r20 == false) goto L78;
        if (r2 == false) goto L78;
        if (r5 == 1) goto L78;
        r5 = 2;
    L78:
        if (r92 != null) goto L80;
    L85:
        int r14 = r5;
        Cache r12 = new Cache(1, r14, r15, r17);     // Catch: Throwable -> L13
        if (r92 != null) goto L88;
    L113:
        r12.writeOnFile(r3);     // Catch: Throwable -> L13 IOException -> L91
    L92:
        CompilationStatus r04 = setCompilationStatus(r14, r64, r2, r02);     // Catch: Throwable -> L13
        monitor-exit(r1);     // Catch: Throwable -> L13
        return r04;
    L91:
        r14 = 196608;
        goto L92
    L88:
        if (r92.equals(r12) == true) goto L92;
    L80:
        if (r92.mResultCode != 2) goto L85;
        if (r5 != 1) goto L85;
        if (r7 >= r92.mInstalledCurrentProfileSize) goto L85;
        r5 = 3;
        goto L85
    L70:
        if (r64 == false) goto L72;
        r5 = 1;
        goto L74
    L72:
        if (r2 == false) goto L74;
        r5 = 2;
        goto L74
    L63:
        if (r92.mPackageLastUpdateTime != r15) goto L68;
        int r11 = r92.mResultCode;     // Catch: Throwable -> L13
        if (r11 == 2) goto L68;
        r5 = r11;
        goto L74
    L110:
        r92 = Cache.readFromFile(r3);     // Catch: Throwable -> L13 IOException -> L55
    L58:
        return setCompilationStatus(131072, r64, r2, r02);
    L13:
        th = move-exception;
        throw th;
    L97:
        return setCompilationStatus(65536, r64, r2, r02);
    L47:
        if (r17 <= 0) goto L49;
        r2 = true;
        goto L106
    L41:
        if (r7 <= 0) goto L43;
        r64 = true;
        goto L44
    L21:
        r02 = false;
    L24:
        th = move-exception;
        if (r6 == null) goto L115;
        r6.close();     // Catch: Throwable -> L29
        throw th;     // Catch: Throwable -> L13 IOException -> L32
    L29:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L13 IOException -> L32
        throw th;     // Catch: Throwable -> L13 IOException -> L32
    L115:
        throw th;     // Catch: Throwable -> L13 IOException -> L32
    L32:
        r02 = false;
        goto L33
    L105:
        CompilationStatus r05 = sCompilationStatus;     // Catch: Throwable -> L13
        if (r05 == null) goto L15;
        monitor-exit(r1);     // Catch: Throwable -> L13
        return r05;
    }
}
