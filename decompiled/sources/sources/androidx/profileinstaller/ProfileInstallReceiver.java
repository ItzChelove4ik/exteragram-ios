package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import androidx.profileinstaller.ProfileInstaller;

/* loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {

    public class ResultDiagnostics implements ProfileInstaller.DiagnosticsCallback {
        final /* synthetic */ ProfileInstallReceiver this$0;

        public ResultDiagnostics(ProfileInstallReceiver r1) {
            this.this$0 = r1;
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onDiagnosticReceived(int r1, Object r2) {
            ProfileInstaller.LOG_DIAGNOSTICS.onDiagnosticReceived(r1, r2);
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onResultReceived(int r2, Object r3) {
            ProfileInstaller.LOG_DIAGNOSTICS.onResultReceived(r2, r3);
            this.this$0.setResultCode(r2);
        }
    }

    public ProfileInstallReceiver() {
    }

    public static void saveProfile(ProfileInstaller.DiagnosticsCallback r2) {
        Process.sendSignal(Process.myPid(), 10);
        r2.onResultReceived(12, null);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r3, Intent r4) {
        if (r4 == null) goto L38;
        String r0 = r4.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(r0) == false) goto L9;
        ProfileInstaller.writeProfile(r3, new CredentialManager$$ExternalSyntheticLambda0(), new ResultDiagnostics(this), true);
        return;
    L9:
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(r0) == false) goto L21;
        Bundle r42 = r4.getExtras();
        if (r42 == null) goto L34;
        String r43 = r42.getString("EXTRA_SKIP_FILE_OPERATION");
        if ("WRITE_SKIP_FILE".equals(r43) == false) goto L17;
        ProfileInstaller.writeSkipFile(r3, new CredentialManager$$ExternalSyntheticLambda0(), new ResultDiagnostics(this));
        return;
    L17:
        if ("DELETE_SKIP_FILE".equals(r43) == false) goto L35;
        ProfileInstaller.deleteSkipFile(r3, new CredentialManager$$ExternalSyntheticLambda0(), new ResultDiagnostics(this));
        return;
    L35:
        return;
    L34:
        return;
    L21:
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(r0) == false) goto L25;
        saveProfile(new ResultDiagnostics(this));
        return;
    L25:
        if ("androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(r0) == false) goto L36;
        Bundle r44 = r4.getExtras();
        if (r44 == null) goto L37;
        String r45 = r44.getString("EXTRA_BENCHMARK_OPERATION");
        ResultDiagnostics r02 = new ResultDiagnostics(this);
        if ("DROP_SHADER_CACHE".equals(r45) == false) goto L32;
        BenchmarkOperation.dropShaderCache(r3, r02);
        return;
    L32:
        r02.onResultReceived(16, null);
        return;
    L37:
        return;
    L36:
        return;
    }
}
