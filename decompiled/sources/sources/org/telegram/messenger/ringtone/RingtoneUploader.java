package org.telegram.messenger.ringtone;

import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes3.dex */
public class RingtoneUploader implements NotificationCenter.NotificationCenterDelegate {
    private boolean canceled;
    private int currentAccount;
    public final String filePath;

    public static /* synthetic */ void $r8$lambda$6mtolB236e36yjWsy5SAN2ksqss(RingtoneUploader r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$didReceivedNotification$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BDqmGPzHVmhiop7SqG4MVpCs51Y(RingtoneUploader r0, TLRPC.TL_error r1) {
        r0.lambda$error$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$OQWv3nMlKP06SFIoHHiOaHqIY64(RingtoneUploader r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$didReceivedNotification$0(r1, r2);
    }

    public RingtoneUploader(String r3, int r4) {
        this.currentAccount = r4;
        this.filePath = r3;
        subscribe();
        FileLoader.getInstance(r4).uploadFile(r3, false, true, ConnectionsManager.FileTypeAudio);
    }

    private /* synthetic */ void lambda$didReceivedNotification$0(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L4;
        onComplete((TLRPC.Document) r1);
    L5:
        unsubscribe();
        return;
    L4:
        error(r2);
        goto L5
    }

    private /* synthetic */ void lambda$didReceivedNotification$1(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new RingtoneUploader$$ExternalSyntheticLambda2(this, r2, r3));
    }

    private /* synthetic */ void lambda$error$2(TLRPC.TL_error r6) {
        if (r6.text.equals("RINGTONE_DURATION_TOO_LONG") == false) goto L7;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{4, LocaleController.formatString("TooLongError", R.string.TooLongError, new Object[0]), LocaleController.formatString("ErrorRingtoneDurationTooLong", R.string.ErrorRingtoneDurationTooLong, new Object[]{Integer.valueOf(MessagesController.getInstance(this.currentAccount).ringtoneDurationMax)})});
        return;
    L7:
        if (r6.text.equals("RINGTONE_SIZE_TOO_BIG") == false) goto L10;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{4, LocaleController.formatString("TooLargeError", R.string.TooLargeError, new Object[0]), LocaleController.formatString("ErrorRingtoneSizeTooBig", R.string.ErrorRingtoneSizeTooBig, new Object[]{Integer.valueOf(MessagesController.getInstance(this.currentAccount).ringtoneSizeMax / 1024)})});
        return;
    L10:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{4, LocaleController.formatString("InvalidFormatError", R.string.InvalidFormatError, new Object[0]), LocaleController.getString(R.string.ErrorRingtoneInvalidFormat)});
    }

    private void onComplete(TLRPC.Document r3) {
        MediaDataController.getInstance(this.currentAccount).onRingtoneUploaded(this.filePath, r3, false);
    }

    private void subscribe() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
    }

    private void unsubscribe() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
    }

    public void cancel() {
        this.canceled = true;
        unsubscribe();
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(this.filePath, false);
        MediaDataController.getInstance(this.currentAccount).onRingtoneUploaded(this.filePath, null, true);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.fileUploaded) goto L16;
        String r22 = (String) r4[0];
        if (this.canceled == false) goto L8;
        return;
    L8:
        if (r22.equals(this.filePath) == false) goto L24;
        TLRPC.InputFile r23 = (TLRPC.InputFile) r4[1];
        TL_account.uploadRingtone r32 = new TL_account.uploadRingtone();
        r32.file = r23;
        r32.file_name = r23.name;
        String r24 = FileLoader.getFileExtension(new File(r23.name));
        r32.mime_type = r24;
        if ("ogg".equals(r24) == false) goto L12;
        r32.mime_type = "audio/ogg";
    L13:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new RingtoneUploader$$ExternalSyntheticLambda0(this));
        return;
    L12:
        r32.mime_type = "audio/mpeg";
        goto L13
    L24:
        return;
    L16:
        if (r2 != NotificationCenter.fileUploadFailed) goto L25;
        String r25 = (String) r4[0];
        if (this.canceled == false) goto L21;
        return;
    L21:
        if (r25.equals(this.filePath) == false) goto L26;
        error(null);
        return;
    L26:
        return;
    }

    public void error(TLRPC.TL_error r5) {
        unsubscribe();
        MediaDataController.getInstance(this.currentAccount).onRingtoneUploaded(this.filePath, null, true);
        if (r5 == null) goto L6;
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new RingtoneUploader$$ExternalSyntheticLambda1(this, r5));
        return;
    }
}
