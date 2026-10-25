package org.telegram.messenger;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class UserNameResolver {
    private static final long CACHE_TIME = 3600000;
    private final int currentAccount;
    android.util.LruCache<String, CachedPeer> resolvedCache;
    HashMap<String, ArrayList<Utilities.Callback<Long>>> resolvingConsumers;

    /* loaded from: classes5.dex */
    public class CachedPeer {
        final long peerId;
        final /* synthetic */ UserNameResolver this$0;
        final long time;

        public CachedPeer(UserNameResolver r1, long r2) {
            this.this$0 = r1;
            this.peerId = r2;
            this.time = System.currentTimeMillis();
        }
    }

    /* renamed from: $r8$lambda$5oikyTxXx5G4mfQqCXXE2gvuo-U, reason: not valid java name */
    public static /* synthetic */ void m5047$r8$lambda$5oikyTxXx5G4mfQqCXXE2gvuoU(UserNameResolver r0, String r1, int r2) {
        r0.lambda$resolve$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AAxbrfaFM_Odb78sc700dSlzdtg(UserNameResolver r0, String r1, TLRPC.TL_error r2, TLObject r3) {
        r0.lambda$resolve$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$ve-6_M5vATNUu-36dqpXLE6eIH4, reason: not valid java name */
    public static /* synthetic */ void m5048$r8$lambda$ve6_M5vATNUu36dqpXLE6eIH4(UserNameResolver r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$resolve$1(r1, r2, r3);
    }

    public UserNameResolver(int r3) {
        this.resolvedCache = new android.util.LruCache(100);
        this.resolvingConsumers = new HashMap();
        this.currentAccount = r3;
    }

    private /* synthetic */ void lambda$resolve$0(String r6, TLRPC.TL_error r7, TLObject r8) {
        ArrayList<Utilities.Callback<Long>> r0 = this.resolvingConsumers.remove(r6);
        if (r0 == null) goto L36;
        int r1 = 0;
        if (r7 == null) goto L25;
        String r5 = r7.text;
        if (r5 == null) goto L15;
        if ("STARREF_EXPIRED".equals(r5) == false) goto L15;
    L12:
        if (r1 >= r0.size()) goto L37;
        r0.get(r1).run(Long.valueOf(LongCompanionObject.MAX_VALUE));
        r1 = r1 + 1;
        goto L12
    L37:
        return;
    L15:
        if (r1 >= r0.size()) goto L17;
        r0.get(r1).run(null);
        r1 = r1 + 1;
        goto L15
    L17:
        String r52 = r7.text;
        if (r52 != null) goto L20;
        return;
    L20:
        if (r52.contains("FLOOD_WAIT") == false) goto L34;
        BaseFragment r53 = LaunchActivity.getLastFragment();
        if (r53 == null) goto L35;
        BulletinFactory.of(r53).createErrorBulletin(LocaleController.getString(R.string.FloodWait)).show();
        return;
    L35:
        return;
    L34:
        return;
    L25:
        TLRPC.TL_contacts_resolvedPeer r82 = (TLRPC.TL_contacts_resolvedPeer) r8;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r82.users, r82.chats, false, true);
        long r72 = MessageObject.getPeerId(r82.peer);
        this.resolvedCache.put(r6, new CachedPeer(this, r72));
    L27:
        if (r1 >= r0.size()) goto L38;
        r0.get(r1).run(Long.valueOf(r72));
        r1 = r1 + 1;
        goto L27
    L38:
        return;
    }

    private /* synthetic */ void lambda$resolve$1(String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new UserNameResolver$$ExternalSyntheticLambda2(this, r2, r4, r3), 2);
    }

    private /* synthetic */ void lambda$resolve$2(String r2, int r3) {
        this.resolvingConsumers.remove(r2);
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r3, true);
    }

    public Runnable resolve(String r2, String r3, Utilities.Callback<Long> r4) {
        return resolve(r2, r3, false, r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Runnable resolve(String r7, String r8, boolean r9, Utilities.Callback<Long> r10) {
        if (TextUtils.isEmpty(r8) == false) goto L12;
        if (r9 == true) goto L12;
        CachedPeer r92 = this.resolvedCache.get(r7);
        if (r92 == null) goto L12;
        if ((System.currentTimeMillis() - r92.time) >= 3600000) goto L11;
        r10.run(Long.valueOf(r92.peerId));
        FileLog.d("resolve username from cache " + r7 + " " + r92.peerId);
        return null;
    L11:
        this.resolvedCache.remove(r7);
    L12:
        ArrayList<Utilities.Callback<Long>> r93 = this.resolvingConsumers.get(r7);
        if (r93 == null) goto L16;
        r93.add(r10);
        return null;
    L16:
        ArrayList<Utilities.Callback<Long>> r94 = new ArrayList();
        r94.add(r10);
        this.resolvingConsumers.put(r7, r94);
        if (AndroidUtilities.isNumeric(r7) == false) goto L19;
        TLRPC.TL_contacts_resolvePhone r82 = new TLRPC.TL_contacts_resolvePhone();
        r82.phone = r7;
        TLRPC.TL_contacts_resolveUsername r83 = r82;
    L24:
        return new UserNameResolver$$ExternalSyntheticLambda1(this, r7, ConnectionsManager.getInstance(this.currentAccount).sendRequest(r83, new UserNameResolver$$ExternalSyntheticLambda0(this, r7)));
    L19:
        TLRPC.TL_contacts_resolveUsername r95 = new TLRPC.TL_contacts_resolveUsername();
        r95.username = r7;
        if (TextUtils.isEmpty(r8) == true) goto L22;
        r95.flags |= 1;
        r95.referer = r8;
    L22:
        r83 = r95;
        goto L24
    }

    public Runnable resolve(String r2, Utilities.Callback<Long> r3) {
        return resolve(r2, null, r3);
    }

    public void update(TLRPC.Chat r5, TLRPC.Chat r6) {
        if (r5 == null) goto L13;
        if (r6 == null) goto L14;
        String r0 = r5.username;
        if (r0 != null) goto L7;
        return;
    L7:
        if (TextUtils.equals(r0, r6.username) == true) goto L17;
        this.resolvedCache.remove(r5.username);
        String r52 = r6.username;
        if (r52 == null) goto L16;
        this.resolvedCache.put(r52, new CachedPeer(this, -r6.id));
        return;
    L16:
        return;
    L17:
        return;
    L14:
        return;
    }

    public void update(TLRPC.User r5, TLRPC.User r6) {
        if (r5 == null) goto L13;
        if (r6 == null) goto L14;
        String r0 = r5.username;
        if (r0 != null) goto L7;
        return;
    L7:
        if (TextUtils.equals(r0, r6.username) == true) goto L17;
        this.resolvedCache.remove(r5.username);
        String r52 = r6.username;
        if (r52 == null) goto L16;
        this.resolvedCache.put(r52, new CachedPeer(this, r6.id));
        return;
    L16:
        return;
    L17:
        return;
    L14:
        return;
    }
}
