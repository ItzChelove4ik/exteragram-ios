package com.exteragram.messenger.plugins;

import com.exteragram.messenger.plugins.PythonPluginsEngine;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J(\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"com/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion$savePythonSdkArchive$1", "Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;", "onFailedDownload", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "canceled", _UrlKt.FRAGMENT_ENCODE_SET, "onSuccessDownload", "onProgressDownload", "downloadSize", _UrlKt.FRAGMENT_ENCODE_SET, "totalSize", "onProgressUpload", "isEncrypted", "getObserverTag", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PythonPluginsEngine$Updater$Companion$savePythonSdkArchive$1 implements DownloadController.FileDownloadProgressListener {
    final /* synthetic */ boolean $autoRestartEngine;
    final /* synthetic */ TLRPC.Document $document;
    final /* synthetic */ PythonPluginsEngine.Updater.Companion.PythonSdkUpdateInfo $update;

    public PythonPluginsEngine$Updater$Companion$savePythonSdkArchive$1(PythonPluginsEngine.Updater.Companion.PythonSdkUpdateInfo r1, TLRPC.Document r2, boolean r3) {
        this.$update = r1;
        this.$document = r2;
        this.$autoRestartEngine = r3;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return PythonPluginsEngine.Updater.access$getTAG$cp();
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        FileLog.e("Failed to load plugins-sdk file");
        PythonPluginsEngine.Updater.access$setLoading$cp(false);
        PythonPluginsEngine.Updater.Companion.access$updateStatus(PythonPluginsEngine.Updater.Companion, 2);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r3) {
        PythonPluginsEngine.Updater.Companion.access$copyArchiveToPluginsDirectory(PythonPluginsEngine.Updater.Companion, this.$update, this.$document, this.$autoRestartEngine);
    }
}
