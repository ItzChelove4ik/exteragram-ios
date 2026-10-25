package org.telegram.messenger.car;

import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.core.graphics.drawable.IconCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.TelegramMediaSession;

/* loaded from: classes5.dex */
public class MusicSongsScreen extends Screen implements DefaultLifecycleObserver, NotificationCenter.NotificationCenterDelegate {
    private final long dialogId;
    private final String title;

    public static /* synthetic */ void $r8$lambda$0aIkc1iNQsT3k6dPRZTlsLUOY04(MusicSongsScreen r0, int r1) {
        r0.lambda$onGetTemplate$0(r1);
    }

    public MusicSongsScreen(CarContext r1, long r2, String r4) {
        super(r1);
        this.dialogId = r2;
        if (r4 != null) goto L6;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        this.title = r4;
        getLifecycle().addObserver(this);
    }

    private /* synthetic */ void lambda$onGetTemplate$0(int r1) {
        playAtIndex(r1);
    }

    private void playAtIndex(int r5) {
        TelegramMediaSession.getInstance(getCarContext().getApplicationContext()).getSession().getController().getTransportControls().playFromMediaId(this.dialogId + "_" + r5, null);     // Catch: Throwable -> L4
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.activeAccountChanged) goto L6;
        getScreenManager().pop();
        return;
    L6:
        invalidate();
    }

    @Override // androidx.car.app.Screen
    public Template onGetTemplate() {
        TelegramMediaSession r0 = TelegramMediaSession.getInstance(getCarContext().getApplicationContext());
        if (this.title.isEmpty() == false) goto L5;
        String r1 = " ";
    L6:
        ArrayList<MessageObject> r3 = r0.getMusicMessages(this.dialogId);
        if (r3 != null) goto L11;
        r0.loadMusicForDialog(this.dialogId, new MusicSongsScreen$$ExternalSyntheticLambda0(this));
        return new ListTemplate.Builder().setTitle(r1).setHeaderAction(Action.BACK).setLoading(true).build();
    L11:
        if (r3.isEmpty() == true) goto L13;
        MessageObject r02 = MediaController.getInstance().getPlayingMessageObject();
        long r4 = 0;
        if (r02 == null) goto L17;
        long r6 = r02.getId();
    L18:
        if (r02 == null) goto L20;
        r4 = r02.getDialogId();
    L20:
        ItemList.Builder r8 = new ItemList.Builder();
        int r9 = Math.min(r3.size(), 200);
        int r10 = 0;
    L21:
        if (r10 >= r9) goto L43;
        MessageObject r11 = r3.get(r10);
        if (r11 == null) goto L41;
        String r12 = r11.getMusicTitle();
        String r13 = r11.getMusicAuthor();
        Row.Builder r14 = new Row.Builder();
        if (r12 != null) goto L29;
        r12 = " ";
    L29:
        Row.Builder r122 = r14.setTitle(r12);
        if (r13 != null) goto L32;
    L34:
        if (r02 != null) goto L36;
    L40:
        r122.setOnClickListener(new MusicSongsScreen$$ExternalSyntheticLambda1(this, r10));
        r8.addItem(r122.build());
        goto L41
    L36:
        if (r4 != r11.getDialogId()) goto L40;
        if (r6 != r11.getId()) goto L40;
        r122.setImage(new CarIcon.Builder(IconCompat.createWithResource(getCarContext(), R.drawable.ic_player)).build());
        goto L40
    L32:
        if (r13.isEmpty() == true) goto L34;
        r122.addText(r13);
    L41:
        r10 = r10 + 1;
        goto L21
    L43:
        return new ListTemplate.Builder().setTitle(r1).setHeaderAction(Action.BACK).setSingleList(r8.build()).build();
    L17:
        r6 = 0;
        goto L18
    L13:
        return new MessageTemplate.Builder(LocaleController.getString(R.string.NoCarMusic)).setTitle(r1).setHeaderAction(Action.BACK).build();
    L5:
        r1 = this.title;
        goto L6
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onPause(LifecycleOwner r3) {
        int r32 = 0;
    L4:
        if (r32 >= 16) goto L6;
        NotificationCenter.getInstance(r32).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(r32).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        r32 = r32 + 1;
        goto L4
    L6:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.activeAccountChanged);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner r3) {
        int r32 = 0;
    L4:
        if (r32 >= 16) goto L6;
        NotificationCenter.getInstance(r32).addObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(r32).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        r32 = r32 + 1;
        goto L4
    L6:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.activeAccountChanged);
    }
}
