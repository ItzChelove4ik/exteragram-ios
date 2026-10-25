package com.exteragram.messenger.ai.ui;

import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public abstract class OnDeviceModelDialogs {

    /* renamed from: com.exteragram.messenger.ai.ui.OnDeviceModelDialogs$1, reason: invalid class name */
    public class AnonymousClass1 implements OnDeviceAvailability.DownloadListener {
        final /* synthetic */ int val$modelKey;

        public AnonymousClass1(int r1) {
            this.val$modelKey = r1;
        }

        @Override // com.exteragram.messenger.ai.network.backend.OnDeviceAvailability.DownloadListener
        public void onCompleted() {
            OnDeviceModelNotifications.showCompleted(this.val$modelKey);
        }

        @Override // com.exteragram.messenger.ai.network.backend.OnDeviceAvailability.DownloadListener
        public void onFailed(Exception r2) {
            FileLog.e("AI_ON_DEVICE_DOWNLOAD_FAILED", r2);
            OnDeviceModelNotifications.cancel(this.val$modelKey);
        }

        @Override // com.exteragram.messenger.ai.network.backend.OnDeviceAvailability.DownloadListener
        public void onProgress(float r1, long r2) {
            int r0 = this.val$modelKey;
            if (r1 >= 0.0f) goto L5;
            int r12 = -1;
        L6:
            OnDeviceModelNotifications.showProgress(r0, r12);
            return;
        L5:
            r12 = (int) (r1 * 100.0f);
            goto L6
        }

        @Override // com.exteragram.messenger.ai.network.backend.OnDeviceAvailability.DownloadListener
        public void onStarted(long r1) {
            OnDeviceModelNotifications.showProgress(this.val$modelKey, -1);
        }
    }

    /* renamed from: $r8$lambda$BS9dtxm-GnVTcOEA5pz-4wMU78k, reason: not valid java name */
    public static /* synthetic */ void m1078$r8$lambda$BS9dtxmGnVTcOEA5pz4wMU78k(BaseFragment r0, AlertDialog r1, int r2) {
        startDownload(r0);
    }

    private static OnDeviceAvailability.DownloadListener createListener(int r1) {
        return new AnonymousClass1(r1);
    }

    public static void showDownloadDialog(BaseFragment r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.getContext() != null) goto L6;
        return;
    L6:
        if (OnDeviceAvailability.isDownloading() == true) goto L12;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3.getContext());
        r0.setTitle(LocaleController.getString(R.string.AIOnDeviceDownloadTitle));
        r0.setSubtitle(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AIOnDeviceDownloadInfo)));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.AIOnDeviceDownloadButton), new OnDeviceModelDialogs$$ExternalSyntheticLambda0(r3));
        r3.showDialog(r0.create());
        return;
    }

    private static void startDownload(BaseFragment r2) {
        int r0 = OnDeviceAvailability.getModelKey();
        OnDeviceAvailability.download(r0, createListener(r0));
        OnDeviceModelNotifications.showProgress(r0, -1);
        BulletinFactory.of(r2).createSimpleBulletin(R.raw.ic_download, LocaleController.getString(R.string.AIOnDeviceDownloadStarted)).show();
    }
}
