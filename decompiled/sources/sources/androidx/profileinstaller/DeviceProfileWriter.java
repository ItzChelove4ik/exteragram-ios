package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.ProfileInstaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.Executor;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class DeviceProfileWriter {
    private final String mApkName;
    private final AssetManager mAssetManager;
    private final File mCurProfile;
    private final byte[] mDesiredVersion;
    private boolean mDeviceSupportsAotProfile;
    private final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;
    private DexProfileData[] mProfile;
    private final String mProfileMetaSourceLocation;
    private final String mProfileSourceLocation;
    private byte[] mTranscodedProfile;

    public static /* synthetic */ void $r8$lambda$jpYdnt3YIQ9nqhGCBG7jxxjpuLE(DeviceProfileWriter r0, int r1, Object r2) {
        r0.mDiagnostics.onResultReceived(r1, r2);
    }

    public DeviceProfileWriter(AssetManager r2, Executor r3, ProfileInstaller.DiagnosticsCallback r4, String r5, String r6, String r7, File r8) {
        this.mDeviceSupportsAotProfile = false;
        this.mAssetManager = r2;
        this.mExecutor = r3;
        this.mDiagnostics = r4;
        this.mApkName = r5;
        this.mProfileSourceLocation = r6;
        this.mProfileMetaSourceLocation = r7;
        this.mCurProfile = r8;
        this.mDesiredVersion = desiredVersion();
    }

    private DeviceProfileWriter addMetadata(DexProfileData[] r4, byte[] r5) {
        InputStream r1 = openStreamFromAssets(this.mAssetManager, this.mProfileMetaSourceLocation);     // Catch: IllegalStateException -> L8 IOException -> L10 FileNotFoundException -> L12
        if (r1 != null) goto L27;
        if (r1 == null) goto L26;
        r1.close();     // Catch: IllegalStateException -> L8 IOException -> L10 FileNotFoundException -> L12
    L26:
        return null;
    L27:
        this.mProfile = ProfileTranscoder.readMeta(r1, ProfileTranscoder.readHeader(r1, ProfileTranscoder.MAGIC_PROFM), r5, r4);     // Catch: Throwable -> L14
        r1.close();     // Catch: IllegalStateException -> L8 IOException -> L10 FileNotFoundException -> L12
        return this;
    L14:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L17
    L19:
        throw th;     // Catch: IllegalStateException -> L8 IOException -> L10 FileNotFoundException -> L12
    L17:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IllegalStateException -> L8 IOException -> L10 FileNotFoundException -> L12
    L12:
        e = move-exception;
        this.mDiagnostics.onResultReceived(9, e);
    L10:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);
    L8:
        e = move-exception;
        this.mProfile = null;
        this.mDiagnostics.onResultReceived(8, e);
        goto L26
    }

    private void assertDeviceAllowsProfileInstallerAotWritesCalled() {
        if (this.mDeviceSupportsAotProfile == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
    }

    private static byte[] desiredVersion() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 31) goto L5;
        switch(r0) {
            case 24: goto L16;
            case 25: goto L16;
            case 26: goto L14;
            case 27: goto L12;
            case 28: goto L10;
            case 29: goto L10;
            case 30: goto L10;
            default: goto L7;
        };
    L7:
        return null;
    L10:
        return ProfileVersion.V010_P;
    L12:
        return ProfileVersion.V009_O_MR1;
    L14:
        return ProfileVersion.V005_O;
    L16:
        return ProfileVersion.V001_N;
    L5:
        return ProfileVersion.V015_S;
    }

    private InputStream getProfileInputStream(AssetManager r2) {
        return openStreamFromAssets(r2, this.mProfileSourceLocation);
    L6:
        e = move-exception;
        this.mDiagnostics.onResultReceived(6, e);
        return null;
    L4:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);
        return null;
    }

    private InputStream openStreamFromAssets(AssetManager r2, String r3) {
        return r2.openFd(r3).createInputStream();
    L4:
        e = move-exception;
        String r22 = e.getMessage();
        if (r22 != null) goto L8;
    L10:
        return null;
    L8:
        if (r22.contains("compressed") == false) goto L10;
        this.mDiagnostics.onDiagnosticReceived(5, null);
        goto L10
    }

    private DexProfileData[] readProfileInternal(InputStream r5) {
        DexProfileData[] r1 = ProfileTranscoder.readProfile(r5, ProfileTranscoder.readHeader(r5, ProfileTranscoder.MAGIC_PROF), this.mApkName);     // Catch: Throwable -> L8 IllegalStateException -> L10 IOException -> L12
        r5.close();     // Catch: IOException -> L6
        return r1;
    L6:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);
        return r1;
    L8:
        th = move-exception;
        r5.close();     // Catch: IOException -> L25
    L27:
        throw th;
    L25:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);
    L10:
        e = move-exception;
        this.mDiagnostics.onResultReceived(8, e);     // Catch: Throwable -> L8
    L28:
        r5.close();     // Catch: IOException -> L17
    L21:
        return null;
    L17:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);
    L12:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);     // Catch: Throwable -> L8
        goto L28
    }

    private static boolean requiresMetadata() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L6;
        return true;
    L6:
        if (r0 != 24) goto L8;
    L11:
        return true;
    L8:
        if (r0 == 25) goto L11;
        return false;
    }

    private void result(int r3, Object r4) {
        this.mExecutor.execute(new DeviceProfileWriter$$ExternalSyntheticLambda0(this, r3, r4));
    }

    public boolean deviceAllowsProfileInstallerAotWrites() {
        if (this.mDesiredVersion != null) goto L6;
        result(3, Integer.valueOf(Build.VERSION.SDK_INT));
        return false;
    L6:
        boolean r0 = this.mCurProfile.exists();
        File r2 = this.mCurProfile;
        if (r0 == true) goto L9;
    L18:
        result(4, null);
        return false;
    L13:
        if (r2.createNewFile() == true) goto L16;
        result(4, null);     // Catch: IOException -> L18
        return false;
    L16:
        this.mDeviceSupportsAotProfile = true;
        return true;
    L9:
        if (r2.canWrite() == true) goto L16;
        result(4, null);
        return false;
    }

    public DeviceProfileWriter read() {
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        if (this.mDesiredVersion == null) goto L15;
        InputStream r0 = getProfileInputStream(this.mAssetManager);
        if (r0 == null) goto L8;
        this.mProfile = readProfileInternal(r0);
    L8:
        DexProfileData[] r02 = this.mProfile;
        if (r02 == null) goto L15;
        if (requiresMetadata() == false) goto L15;
        DeviceProfileWriter r03 = addMetadata(r02, this.mDesiredVersion);
        if (r03 == null) goto L15;
        return r03;
    L15:
        return this;
    }

    public DeviceProfileWriter transcodeIfNeeded() {
        DexProfileData[] r0 = this.mProfile;
        byte[] r1 = this.mDesiredVersion;
        if (r0 == null) goto L30;
        if (r1 == null) goto L30;
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        ByteArrayOutputStream r3 = new ByteArrayOutputStream();     // Catch: IllegalStateException -> L13 IOException -> L15
        ProfileTranscoder.writeHeader(r3, r1);     // Catch: Throwable -> L17
        if (ProfileTranscoder.transcodeAndWriteBody(r3, r1, r0) == true) goto L19;
        this.mDiagnostics.onResultReceived(5, null);     // Catch: Throwable -> L17
        this.mProfile = null;     // Catch: Throwable -> L17
        r3.close();     // Catch: IllegalStateException -> L13 IOException -> L15
        return this;
    L19:
        this.mTranscodedProfile = r3.toByteArray();     // Catch: Throwable -> L17
        r3.close();     // Catch: IllegalStateException -> L13 IOException -> L15
    L29:
        this.mProfile = null;
    L17:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L24
    L26:
        throw th;     // Catch: IllegalStateException -> L13 IOException -> L15
    L24:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IllegalStateException -> L13 IOException -> L15
    L15:
        e = move-exception;
        this.mDiagnostics.onResultReceived(7, e);
    L13:
        e = move-exception;
        this.mDiagnostics.onResultReceived(8, e);
    L30:
        return this;
    }

    public boolean write() {
        byte[] r0 = this.mTranscodedProfile;
        if (r0 != null) goto L5;
        return false;
    L5:
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        ByteArrayInputStream r3 = new ByteArrayInputStream(r0);     // Catch: Throwable -> L21 IOException -> L23 FileNotFoundException -> L25
        FileOutputStream r02 = new FileOutputStream(this.mCurProfile);     // Catch: Throwable -> L27
        FileChannel r4 = r02.getChannel();     // Catch: Throwable -> L29
        FileLock r5 = r4.tryLock();     // Catch: Throwable -> L14
        Encoding.writeAll(r3, r02, r5);     // Catch: Throwable -> L31
        result(1, null);     // Catch: Throwable -> L31
        if (r5 == null) goto L16;
        r5.close();     // Catch: Throwable -> L14
    L16:
        r4.close();     // Catch: Throwable -> L29
        r02.close();     // Catch: Throwable -> L27
        r3.close();     // Catch: Throwable -> L21 IOException -> L23 FileNotFoundException -> L25
        this.mTranscodedProfile = null;
        this.mProfile = null;
        return true;
    L31:
        th = move-exception;
        if (r5 != null) goto L75;
    L37:
        throw th;     // Catch: Throwable -> L14
    L75:
        r5.close();     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        if (r4 != null) goto L69;
    L43:
        throw th;     // Catch: Throwable -> L29
    L69:
        r4.close();     // Catch: Throwable -> L41
    L41:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        r02.close();     // Catch: Throwable -> L46
    L48:
        throw th;     // Catch: Throwable -> L27
    L46:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L27
    L27:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L51
    L53:
        throw th;     // Catch: Throwable -> L21 IOException -> L23 FileNotFoundException -> L25
    L51:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L21 IOException -> L23 FileNotFoundException -> L25
    L21:
        th = move-exception;
        this.mTranscodedProfile = null;
        this.mProfile = null;
        throw th;
    L25:
        e = move-exception;
        result(6, e);     // Catch: Throwable -> L21
    L56:
        this.mTranscodedProfile = null;
        this.mProfile = null;
        return false;
    L23:
        e = move-exception;
        result(7, e);     // Catch: Throwable -> L21
        goto L56
    }
}
