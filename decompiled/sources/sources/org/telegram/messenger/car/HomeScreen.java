package org.telegram.messenger.car;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.messaging.model.CarMessage;
import androidx.car.app.messaging.model.ConversationCallback;
import androidx.car.app.messaging.model.ConversationItem;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Tab;
import androidx.car.app.model.TabContents;
import androidx.car.app.model.TabTemplate;
import androidx.car.app.model.Template;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.TelegramMediaSession;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public class HomeScreen extends Screen implements DefaultLifecycleObserver, NotificationCenter.NotificationCenterDelegate {
    private String activeTabId;
    private int currentAccount;
    private boolean musicLoadKicked;
    private final long sessionStartMillis;

    /* renamed from: org.telegram.messenger.car.HomeScreen$1, reason: invalid class name */
    public class AnonymousClass1 implements TabTemplate.TabCallback {
        final /* synthetic */ HomeScreen this$0;

        public AnonymousClass1(HomeScreen r1) {
            this.this$0 = r1;
        }

        @Override // androidx.car.app.model.TabTemplate.TabCallback
        public void onTabSelected(String r2) {
            HomeScreen.m5139$$Nest$fputactiveTabId(this.this$0, r2);
            this.this$0.invalidate();
        }
    }

    public static final class TelegramConversationCallback implements ConversationCallback {
        private final int currentAccount;
        private final long dialogId;
        private final int maxId;

        /* renamed from: $r8$lambda$-dQld8PWbzKMFfksBAmUlgjQtYo, reason: not valid java name */
        public static /* synthetic */ void m5140$r8$lambda$dQld8PWbzKMFfksBAmUlgjQtYo(TelegramConversationCallback r0) {
            r0.lambda$onMarkAsRead$0();
        }

        /* renamed from: $r8$lambda$bedXiha-wqzzo8K7JT5h7SRnTuA, reason: not valid java name */
        public static /* synthetic */ void m5141$r8$lambda$bedXihawqzzo8K7JT5h7SRnTuA(TelegramConversationCallback r0, String r1) {
            r0.lambda$onTextReply$1(r1);
        }

        public TelegramConversationCallback(int r1, long r2, int r4) {
            this.currentAccount = r1;
            this.dialogId = r2;
            this.maxId = r4;
        }

        private /* synthetic */ void lambda$onMarkAsRead$0() {
            MessagesController r1 = MessagesController.getInstance(this.currentAccount);
            long r2 = this.dialogId;
            int r4 = this.maxId;
            r1.markDialogAsRead(r2, r4, r4, 0, false, 0, 0, true, 0);
            MessagesController.getInstance(this.currentAccount).markReactionsAsRead(this.dialogId, 0);
        }

        private /* synthetic */ void lambda$onTextReply$1(String r18) {
            AccountInstance.getInstance(this.currentAccount).getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r18, this.dialogId, null, null, null, true, null, null, null, true, 0, 0, null, false));
            MessagesController r2 = MessagesController.getInstance(this.currentAccount);
            long r3 = this.dialogId;
            int r5 = this.maxId;
            r2.markDialogAsRead(r3, r5, r5, 0, false, 0, 0, true, 0);
        }

        @Override // androidx.car.app.messaging.model.ConversationCallback
        public void onMarkAsRead() {
            AndroidUtilities.runOnUIThread(new HomeScreen$TelegramConversationCallback$$ExternalSyntheticLambda0(this));
        }

        @Override // androidx.car.app.messaging.model.ConversationCallback
        public void onTextReply(String r2) {
            AndroidUtilities.runOnUIThread(new HomeScreen$TelegramConversationCallback$$ExternalSyntheticLambda1(this, r2));
        }
    }

    public static /* synthetic */ void $r8$lambda$lg05nMvz86bSR6ca6FQRWJSVMPE(HomeScreen r0, long r1, String r3) {
        r0.lambda$buildMusicTemplate$0(r1, r3);
    }

    /* renamed from: -$$Nest$fputactiveTabId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5139$$Nest$fputactiveTabId(HomeScreen r0, String r1) {
        r0.activeTabId = r1;
    }

    public HomeScreen(CarContext r3) {
        super(r3);
        this.activeTabId = "tab_notifications";
        this.sessionStartMillis = System.currentTimeMillis();
        this.currentAccount = UserConfig.selectedAccount;
        TelegramMediaSession.getInstance(r3.getApplicationContext()).enablePlaybackMirroring();
        getLifecycle().addObserver(this);
    }

    private CarMessage buildCarMessage(MessageObject r5, long r6, boolean r8, TLRPC.User r9, TLRPC.Chat r10) {
        String r82 = _UrlKt.FRAGMENT_ENCODE_SET;
        String[] r1 = new String[1];
        String r4 = NotificationsController.getInstance(this.currentAccount).getShortStringForMessage(r5, r1, new boolean[1]);     // Catch: Throwable -> L5
    L9:
        if (r4 != null) goto L12;
        return null;
    L12:
        Person.Builder r0 = new Person.Builder();
        String r12 = r1[0];
        if (r12 == null) goto L16;
        r0.setName(r12).setKey("u" + r5.getSenderId());
    L26:
        return new CarMessage.Builder().setBody(CarText.create(r4)).setReceivedTimeEpochMillis(r5.messageOwner.date * 1000).setSender(r0.build()).setRead(false).build();
    L16:
        if (DialogObject.isUserDialog(r6) == false) goto L19;
        if (r9 == null) goto L19;
        r0.setName(ContactsController.formatName(r9.first_name, r9.last_name)).setKey("u" + r9.id);
    L19:
        if (r10 == null) goto L24;
        String r62 = r10.title;
        if (r62 == null) goto L23;
        r82 = r62;
    L23:
        r0.setName(r82).setKey("c" + r10.id);
        goto L26
    L24:
        r0.setName(_UrlKt.FRAGMENT_ENCODE_SET).setKey("d" + r6);
    L5:
        CharSequence r42 = r5.messageText;
        if (r42 == null) goto L8;
        r4 = r42.toString();
        goto L9
    L8:
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    private ConversationItem buildConversationItem(long r20, ArrayList<MessageObject> r22) {
        if (r22.isEmpty() == false) goto L5;
        return null;
    L5:
        Collections.reverse(r22);
        MessagesController r1 = AccountInstance.getInstance(this.currentAccount).getMessagesController();
        if (DialogObject.isUserDialog(r20) == false) goto L11;
        TLRPC.User r12 = r1.getUser(Long.valueOf(r20));
        if (r12 != null) goto L10;
        return null;
    L10:
        String r10 = ContactsController.formatName(r12.first_name, r12.last_name);
        boolean r4 = false;
        TLRPC.Chat r6 = null;
    L26:
        Person.Builder r11 = new Person.Builder().setName(r10).setKey(String.valueOf(r20));
        IconCompat r122 = loadAvatarIcon(r12, r6);
        if (r122 == null) goto L29;
        r11.setIcon(r122);
    L29:
        TLRPC.User r112 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        Person.Builder r8 = new Person.Builder().setName(LocaleController.getString(R.string.FromYou)).setKey("self_" + UserConfig.getInstance(this.currentAccount).clientUserId);
        if (r112 == null) goto L34;
        IconCompat r9 = loadUserAvatarIcon(r112);
        if (r9 == null) goto L34;
        r8.setIcon(r9);
    L34:
        Person r82 = r8.build();
        ArrayList r92 = new ArrayList();
        int r113 = Math.max(0, r22.size() - 5);
    L36:
        if (r113 >= r22.size()) goto L42;
        TLRPC.User r5 = r12;
        CarMessage r13 = buildCarMessage(r22.get(r113), r20, r4, r5, r6);
        if (r13 == null) goto L40;
        r92.add(r13);
    L40:
        r113 = r113 + 1;
        r12 = r5;
        goto L36
    L42:
        if (r92.isEmpty() == false) goto L44;
        return null;
    L44:
        ConversationItem.Builder r0 = new ConversationItem.Builder().setId(String.valueOf(r20)).setTitle(CarText.create(r10)).setSelf(r82).setMessages(r92).setGroupConversation(r4).setConversationCallback(new TelegramConversationCallback(this.currentAccount, r20, r22.get(r22.size() - 1).getId()));
        if (r122 == null) goto L48;
        r0.setIcon(new CarIcon.Builder(r122).build());
    L48:
        return r0.build();
    L11:
        TLRPC.Chat r14 = r1.getChat(Long.valueOf(-r20));
        if (r14 != null) goto L14;
        return null;
    L14:
        String r42 = r14.title;
        if (r42 != null) goto L19;
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        if (ChatObject.isChannel(r14) == true) goto L21;
    L24:
        boolean r62 = true;
    L25:
        r10 = r42;
        r4 = r62;
        r6 = r14;
        r12 = null;
        goto L26
    L21:
        if (ChatObject.isMegagroup(r14) == true) goto L24;
        r62 = false;
        goto L25
    }

    private Template buildMusicTemplate() {
        TelegramMediaSession r1 = TelegramMediaSession.getInstance(getCarContext().getApplicationContext());
        if (r1.isChatsLoaded() == false) goto L5;
        ArrayList<Long> r2 = r1.getMusicDialogsSortedByVisibleOrder();
        if (r2 == null) goto L66;
        if (r2.isEmpty() == true) goto L66;
        ItemList.Builder r4 = new ItemList.Builder();
        int r6 = 0;
        int r7 = 0;
    L16:
        if (r6 >= r2.size()) goto L64;
        if (r7 >= 50) goto L64;
        long r8 = r2.get(r6).longValue();
        ArrayList<MessageObject> r10 = r1.getMusicMessages(r8);
        if (r10 != null) goto L22;
    L24:
        IconCompat r12 = null;
        if (DialogObject.isUserDialog(r8) == false) goto L37;
        TLRPC.User r11 = r1.getMusicUser(r8);
        if (r11 == null) goto L62;
        if (UserObject.isUserSelf(r11) == false) goto L32;
        String r112 = LocaleController.getString(R.string.SavedMessages);
    L50:
        TLRPC.FileLocation r13 = null;
    L52:
        if (TextUtils.isEmpty(r112) == true) goto L62;
        Row.Builder r82 = new Row.Builder().setTitle(r112).setBrowsable(true).setOnClickListener(new HomeScreen$$ExternalSyntheticLambda1(this, r8, r112));
        if (r10 == null) goto L57;
        r82.addText(LocaleController.formatPluralString("MusicFiles", r10.size(), new Object[0]));
    L57:
        if (r13 == null) goto L59;
        r12 = iconFromAvatar(r13);
    L59:
        if (r12 == null) goto L61;
        r82.setImage(new CarIcon.Builder(r12).build(), 2);
    L61:
        r4.addItem(r82.build());
        r7 = r7 + 1;
        goto L62
    L32:
        String r132 = ContactsController.formatName(r11.first_name, r11.last_name);
        TLRPC.UserProfilePhoto r113 = r11.photo;
        if (r113 == null) goto L49;
        TLRPC.FileLocation r114 = r113.photo_small;
        if ((r114 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L49;
    L48:
        String r16 = r132;
        r13 = r114;
        r112 = r16;
    L49:
        r112 = r132;
    L62:
        r6 = r6 + 1;
        goto L16
    L37:
        TLRPC.Chat r115 = r1.getMusicChat(-r8);
        if (r115 == null) goto L62;
        r132 = r115.title;
        if (r132 != null) goto L44;
        r132 = _UrlKt.FRAGMENT_ENCODE_SET;
    L44:
        TLRPC.ChatPhoto r116 = r115.photo;
        if (r116 == null) goto L49;
        r114 = r116.photo_small;
        if ((r114 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L49;
    L22:
        if (r10.isEmpty() == false) goto L24;
    L64:
        return new ListTemplate.Builder().setSingleList(r4.build()).build();
    L66:
        return new MessageTemplate.Builder(LocaleController.getString(R.string.NoCarMusic)).build();
    L5:
        if (this.musicLoadKicked == true) goto L8;
        this.musicLoadKicked = true;
        r1.ensureLoaded(new HomeScreen$$ExternalSyntheticLambda0(this));
    L8:
        return new ListTemplate.Builder().setLoading(true).build();
    }

    private Template buildNotificationsTemplate() {
        Map<Long, ArrayList<MessageObject>> r0 = collectUnreadDuringDrive();
        if (r0.isEmpty() == true) goto L5;
        ItemList.Builder r1 = new ItemList.Builder();
        Iterator<Map.Entry<Long, ArrayList<MessageObject>>> r02 = r0.entrySet().iterator();
        int r2 = 0;
    L8:
        if (r02.hasNext() == false) goto L17;
        Map.Entry<Long, ArrayList<MessageObject>> r3 = r02.next();
        int r4 = r2 + 1;
        if (r2 >= 6) goto L17;
        ConversationItem r22 = buildConversationItem(r3.getKey().longValue(), r3.getValue());
        if (r22 == null) goto L15;
        r1.addItem(r22);
    L15:
        r2 = r4;
    L17:
        return new ListTemplate.Builder().setSingleList(r1.build()).build();
    L5:
        return new MessageTemplate.Builder(LocaleController.getString(R.string.NoNewCarMessages)).build();
    }

    private Template buildTabContent(String r3) {
        int r0 = r3.hashCode();
        if (r0 != (-2006925890)) goto L5;
        r3.equals("tab_notifications");
    L13:
        return buildNotificationsTemplate();
    L5:
        if (r0 != 1942637819) goto L13;
        if (r3.equals("tab_music") == false) goto L13;
        return buildMusicTemplate();
    }

    private Map<Long, ArrayList<MessageObject>> collectUnreadDuringDrive() {
        LinkedHashMap r0 = new LinkedHashMap();
        ArrayList<MessageObject> r1 = NotificationsController.getInstance(this.currentAccount).getPushMessagesSnapshot();
        long r2 = this.sessionStartMillis / 1000;
        int r9 = r1.size();
        int r4 = 0;
    L3:
        if (r4 >= r9) goto L22;
        MessageObject r5 = r1.get(r4);
        r4 = r4 + 1;
        MessageObject r52 = r5;
        if (r52 == null) goto L3;
        if (r52.messageOwner == null) goto L3;
        if (r6.date < r2) goto L3;
        long r6 = r52.getDialogId();
        if (DialogObject.isEncryptedDialog(r6) == true) goto L3;
        if (UserObject.isReplyUser(r6) == true) goto L3;
        ArrayList r8 = (ArrayList) r0.get(Long.valueOf(r6));
        if (r8 != null) goto L21;
        r8 = new ArrayList();
        r0.put(Long.valueOf(r6), r8);
    L21:
        r8.add(r52);
        goto L3
    L22:
        return r0;
    }

    private IconCompat iconFromAvatar(TLRPC.FileLocation r4) {
        if (r4 != null) goto L5;
    L18:
        return null;
    L5:
        if ((r4 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L18;
        File r42 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r4, true);     // Catch: Throwable -> L19
        if (r42.exists() == true) goto L10;
        return null;
    L10:
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Throwable -> L19
        r1.inSampleSize = 2;     // Catch: Throwable -> L19
        Bitmap r43 = BitmapFactory.decodeFile(r42.getAbsolutePath(), r1);     // Catch: Throwable -> L19
        if (r43 != null) goto L13;
        return null;
    L13:
        Bitmap r3 = makeRound(r43);     // Catch: Throwable -> L19
        if (r3 == null) goto L16;
        r43 = r3;
    L16:
        return IconCompat.createWithBitmap(r43);
    }

    private CarIcon iconResource(int r2) {
        return new CarIcon.Builder(IconCompat.createWithResource(getCarContext(), r2)).build();
    }

    private /* synthetic */ void lambda$buildMusicTemplate$0(long r3, String r5) {
        getScreenManager().push(new MusicSongsScreen(getCarContext(), r3, r5));
    }

    private IconCompat loadAvatarIcon(TLRPC.User r1, TLRPC.Chat r2) {
        if (r1 == null) goto L6;
        TLRPC.UserProfilePhoto r12 = r1.photo;
        if (r12 == null) goto L6;
        TLRPC.FileLocation r13 = r12.photo_small;
    L12:
        return iconFromAvatar(r13);
    L6:
        if (r2 == null) goto L10;
        TLRPC.ChatPhoto r14 = r2.photo;
        if (r14 == null) goto L10;
        r13 = r14.photo_small;
    L10:
        r13 = null;
        goto L12
    }

    private IconCompat loadUserAvatarIcon(TLRPC.User r1) {
        if (r1 == null) goto L8;
        TLRPC.UserProfilePhoto r12 = r1.photo;
        if (r12 != null) goto L7;
        return null;
    L7:
        return iconFromAvatar(r12.photo_small);
    L8:
        return null;
    }

    private Bitmap makeRound(Bitmap r7) {
        Bitmap r6 = Bitmap.createBitmap(r7.getWidth(), r7.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L4
        r6.eraseColor(0);     // Catch: Throwable -> L4
        Canvas r0 = new Canvas(r6);     // Catch: Throwable -> L4
        Paint r1 = new Paint(1);     // Catch: Throwable -> L4
        Shader.TileMode r3 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L4
        r1.setShader(new BitmapShader(r7, r3, r3));     // Catch: Throwable -> L4
        r0.drawRoundRect(new RectF(0.0f, 0.0f, r7.getWidth(), r7.getHeight()), r7.getWidth(), r7.getHeight(), r1);     // Catch: Throwable -> L4
        return r6;
    L4:
        return null;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.activeAccountChanged) goto L7;
        this.currentAccount = UserConfig.selectedAccount;
        this.musicLoadKicked = false;
        invalidate();
        return;
    L7:
        if (r1 == NotificationCenter.pushMessagesUpdated) goto L11;
        if (r1 == NotificationCenter.notificationsCountUpdated) goto L11;
        return;
    L11:
        if ("tab_notifications".equals(this.activeTabId) == false) goto L15;
        invalidate();
        return;
    }

    @Override // androidx.car.app.Screen
    public Template onGetTemplate() {
        TabTemplate.Builder r0 = new TabTemplate.Builder(new AnonymousClass1(this));
        r0.setHeaderAction(Action.APP_ICON);
        r0.addTab(new Tab.Builder().setContentId("tab_notifications").setIcon(iconResource(R.drawable.msg_notifications)).setTitle(LocaleController.getString(R.string.Notifications)).build());
        r0.addTab(new Tab.Builder().setContentId("tab_music").setIcon(iconResource(R.drawable.filled_widget_music)).setTitle(LocaleController.getString(R.string.Music)).build());
        r0.setActiveTabContentId(this.activeTabId);
        r0.setTabContents(new TabContents.Builder(buildTabContent(this.activeTabId)).build());
        return r0.build();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onPause(LifecycleOwner r2) {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pushMessagesUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.notificationsCountUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.activeAccountChanged);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner r2) {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pushMessagesUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.notificationsCountUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.activeAccountChanged);
    }
}
