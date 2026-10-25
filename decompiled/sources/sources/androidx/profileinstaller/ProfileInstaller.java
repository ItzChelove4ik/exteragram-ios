package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class ProfileInstaller {
    private static final DiagnosticsCallback EMPTY_DIAGNOSTICS = null;
    static final DiagnosticsCallback LOG_DIAGNOSTICS = null;

    /* renamed from: androidx.profileinstaller.ProfileInstaller$1, reason: invalid class name */
    public class AnonymousClass1 implements DiagnosticsCallback {
        public AnonymousClass1() {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onDiagnosticReceived(int r1, Object r2) {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onResultReceived(int r1, Object r2) {
        }
    }

    /* renamed from: androidx.profileinstaller.ProfileInstaller$2, reason: invalid class name */
    public class AnonymousClass2 implements DiagnosticsCallback {
        public AnonymousClass2() {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onDiagnosticReceived(int r1, Object r2) {
            if (r1 != 1) goto L5;
            String r0 = "DIAGNOSTIC_CURRENT_PROFILE_EXISTS";
        L18:
            Log.d("ProfileInstaller", r0);
            return;
        L5:
            if (r1 != 2) goto L7;
            r0 = "DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST";
            goto L18
        L7:
            if (r1 != 3) goto L9;
            r0 = "DIAGNOSTIC_REF_PROFILE_EXISTS";
            goto L18
        L9:
            if (r1 != 4) goto L11;
            r0 = "DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST";
            goto L18
        L11:
            if (r1 == 5) goto L13;
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L18
        L13:
            r0 = "DIAGNOSTIC_PROFILE_IS_COMPRESSED";
            goto L18
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onResultReceived(int r3, Object r4) {
            switch(r3) {
                case 1: goto L13;
                case 2: goto L12;
                case 3: goto L11;
                case 4: goto L10;
                case 5: goto L9;
                case 6: goto L8;
                case 7: goto L7;
                case 8: goto L6;
                case 9: goto L3;
                case 10: goto L5;
                case 11: goto L4;
                default: goto L3;
            };
        L3:
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        L15:
            if (r3 != 6) goto L17;
        L22:
            Log.e("ProfileInstaller", r2, (Throwable) r4);
            return;
        L17:
            if (r3 == 7) goto L22;
            if (r3 == 8) goto L22;
            Log.d("ProfileInstaller", r2);
            return;
        L4:
            r2 = "RESULT_DELETE_SKIP_FILE_SUCCESS";
            goto L15
        L5:
            r2 = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
            goto L15
        L6:
            r2 = "RESULT_PARSE_EXCEPTION";
            goto L15
        L7:
            r2 = "RESULT_IO_EXCEPTION";
            goto L15
        L8:
            r2 = "RESULT_BASELINE_PROFILE_NOT_FOUND";
            goto L15
        L9:
            r2 = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
            goto L15
        L10:
            r2 = "RESULT_NOT_WRITABLE";
            goto L15
        L11:
            r2 = "RESULT_UNSUPPORTED_ART_VERSION";
            goto L15
        L12:
            r2 = "RESULT_ALREADY_INSTALLED";
            goto L15
        L13:
            r2 = "RESULT_INSTALL_SUCCESS";
            goto L15
        }
    }

    /* loaded from: classes4.dex */
    public interface DiagnosticsCallback {
        void onDiagnosticReceived(int r1, Object r2);

        void onResultReceived(int r1, Object r2);
    }

    public static /* synthetic */ void $r8$lambda$GDxgaqGf4HZQYZi5aULGHDIzm4Y(DiagnosticsCallback r0, int r1, Object r2) {
        r0.onResultReceived(r1, r2);
    }

    static {
        EMPTY_DIAGNOSTICS = new AnonymousClass1();
        LOG_DIAGNOSTICS = new AnonymousClass2();
    }

    public static boolean deleteProfileWrittenFor(File r2) {
        return new File(r2, "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
    }

    public static void deleteSkipFile(Context r1, Executor r2, DiagnosticsCallback r3) {
        deleteProfileWrittenFor(r1.getFilesDir());
        result(r2, r3, 11, null);
    }

    public static boolean hasAlreadyWrittenProfileForThisInstall(PackageInfo r4, File r5, DiagnosticsCallback r6) {
        File r0 = new File(r5, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
        boolean r1 = false;
        if (r0.exists() == true) goto L23;
        return false;
    L23:
        DataInputStream r52 = new DataInputStream(new FileInputStream(r0));     // Catch: IOException -> L20
        long r2 = r52.readLong();     // Catch: Throwable -> L14
        r52.close();     // Catch: IOException -> L20
        if (r2 != r4.lastUpdateTime) goto L11;
        r1 = true;
    L11:
        if (r1 == false) goto L13;
        r6.onResultReceived(2, null);
    L13:
        return r1;
    L14:
        th = move-exception;
        r52.close();     // Catch: Throwable -> L17
    L19:
        throw th;     // Catch: IOException -> L20
    L17:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L20
    L20:
        return false;
    }

    public static void noteProfileWrittenFor(PackageInfo r2, File r3) {
        DataOutputStream r32 = new DataOutputStream(new FileOutputStream(new File(r3, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));     // Catch: IOException -> L13
        r32.writeLong(r2.lastUpdateTime);     // Catch: Throwable -> L7
        r32.close();     // Catch: IOException -> L13
        return;
    L7:
        th = move-exception;
        r32.close();     // Catch: Throwable -> L10
    L12:
        throw th;     // Catch: IOException -> L13
    L10:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L13
    }

    public static void result(Executor r1, DiagnosticsCallback r2, int r3, Object r4) {
        r1.execute(new ProfileInstaller$$ExternalSyntheticLambda0(r2, r3, r4));
    }

    private static boolean transcodeAndWrite(AssetManager r8, String r9, PackageInfo r10, File r11, String r12, Executor r13, DiagnosticsCallback r14) {
        DeviceProfileWriter r0 = new DeviceProfileWriter(r8, r13, r14, r12, "dexopt/baseline.prof", "dexopt/baseline.profm", new File(new File("/data/misc/profiles/cur/0", r9), "primary.prof"));
        if (r0.deviceAllowsProfileInstallerAotWrites() == true) goto L6;
        return false;
    L6:
        boolean r82 = r0.read().transcodeIfNeeded().write();
        if (r82 == false) goto L9;
        noteProfileWrittenFor(r10, r11);
    L9:
        return r82;
    }

    public static void writeProfile(Context r2) {
        writeProfile(r2, new CredentialManager$$ExternalSyntheticLambda0(), EMPTY_DIAGNOSTICS);
    }

    public static void writeProfile(Context r1, Executor r2, DiagnosticsCallback r3) {
        writeProfile(r1, r2, r3, false);
    }

    public static void writeProfile(Context r9, Executor r10, DiagnosticsCallback r11, boolean r12) {
        Context r0 = r9.getApplicationContext();
        String r2 = r0.getPackageName();
        ApplicationInfo r1 = r0.getApplicationInfo();
        AssetManager r02 = r0.getAssets();
        String r5 = new File(r1.sourceDir).getName();
        boolean r8 = false;
        PackageInfo r3 = r9.getPackageManager().getPackageInfo(r2, 0);     // Catch: PackageManager.NameNotFoundException -> L17
        File r4 = r9.getFilesDir();
        if (r12 == false) goto L7;
    L11:
        Log.d("ProfileInstaller", "Installing profile for " + r9.getPackageName());
        if (transcodeAndWrite(r02, r2, r3, r4, r5, r10, r11) == false) goto L15;
        if (r12 == false) goto L15;
        r8 = true;
    L15:
        ProfileVerifier.writeProfileVerification(r9, r8);
        return;
    L7:
        if (hasAlreadyWrittenProfileForThisInstall(r3, r4, r11) == false) goto L11;
        Log.d("ProfileInstaller", "Skipping profile installation for " + r9.getPackageName());
        ProfileVerifier.writeProfileVerification(r9, false);
        return;
    L17:
        e = move-exception;
        r11.onResultReceived(7, e);
        ProfileVerifier.writeProfileVerification(r9, false);
    }

    public static void writeSkipFile(Context r3, Executor r4, DiagnosticsCallback r5) {
        String r0 = r3.getApplicationContext().getPackageName();
        PackageInfo r02 = r3.getPackageManager().getPackageInfo(r0, 0);     // Catch: PackageManager.NameNotFoundException -> L6
        noteProfileWrittenFor(r02, r3.getFilesDir());
        result(r4, r5, 10, null);
        return;
    L6:
        e = move-exception;
        result(r4, r5, 7, e);
    }
}
