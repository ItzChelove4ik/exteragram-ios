package org.telegram.messenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class ChannelBoostsController {
    public static final int BOOSTS_FOR_LEVEL_1 = 1;
    public static final int BOOSTS_FOR_LEVEL_2 = 1;
    private final ConnectionsManager connectionsManager;
    private final int currentAccount;
    private final MessagesController messagesController;

    /* loaded from: classes5.dex */
    public static class CanApplyBoost {
        public boolean alreadyActive;
        public int boostCount;
        public boolean boostedNow;
        public boolean canApply;
        public TLRPC.Chat currentChat;
        public long currentDialogId;
        public TLRPC.Peer currentPeer;
        public boolean empty;
        public int floodWait;
        public boolean isMaxLvl;
        public TL_stories.TL_premium_myBoosts myBoosts;
        public boolean needSelector;
        public long replaceDialogId;
        public int slot;

        public CanApplyBoost() {
            this.boostCount = 0;
        }

        public CanApplyBoost copy() {
            CanApplyBoost r0 = new CanApplyBoost();
            r0.canApply = this.canApply;
            r0.empty = this.empty;
            r0.replaceDialogId = this.replaceDialogId;
            r0.alreadyActive = this.alreadyActive;
            r0.needSelector = this.needSelector;
            r0.slot = this.slot;
            r0.myBoosts = this.myBoosts;
            r0.boostCount = this.boostCount;
            r0.currentPeer = this.currentPeer;
            r0.currentDialogId = this.currentDialogId;
            r0.currentChat = this.currentChat;
            r0.isMaxLvl = this.isMaxLvl;
            return r0;
        }

        public void setMyBoosts(TL_stories.TL_premium_myBoosts r11) {
            this.myBoosts = r11;
            this.boostCount = 0;
            this.slot = 0;
            this.alreadyActive = false;
            this.canApply = false;
            this.needSelector = false;
            this.replaceDialogId = 0;
            if (r11.my_boosts.isEmpty() == false) goto L5;
            this.empty = true;
        L5:
            ArrayList<TL_stories.TL_myBoost> r1 = r11.my_boosts;
            int r3 = r1.size();
            int r4 = 0;
        L6:
            if (r4 >= r3) goto L11;
            TL_stories.TL_myBoost r5 = r1.get(r4);
            r4 = r4 + 1;
            if (this.currentDialogId != DialogObject.getPeerDialogId(r5.peer)) goto L6;
            this.boostCount++;
            goto L6
        L11:
            if (this.boostCount <= 0) goto L13;
            this.alreadyActive = true;
        L13:
            ArrayList<TL_stories.TL_myBoost> r12 = r11.my_boosts;
            int r32 = r12.size();
            int r42 = 0;
        L14:
            if (r42 >= r32) goto L19;
            TL_stories.TL_myBoost r52 = r12.get(r42);
            r42 = r42 + 1;
            TL_stories.TL_myBoost r53 = r52;
            if (r53.peer != null) goto L14;
            this.slot = r53.slot;
        L19:
            if (this.slot != 0) goto L39;
            ArrayList r13 = new ArrayList();
            ArrayList<TL_stories.TL_myBoost> r112 = r11.my_boosts;
            int r33 = r112.size();
            int r43 = 0;
        L21:
            if (r43 >= r33) goto L28;
            TL_stories.TL_myBoost r54 = r112.get(r43);
            r43 = r43 + 1;
            TL_stories.TL_myBoost r55 = r54;
            TLRPC.Peer r6 = r55.peer;
            if (r6 == null) goto L21;
            if (DialogObject.getPeerDialogId(r6) == (-this.currentChat.id)) goto L21;
            r13.add(r55);
            goto L21
        L28:
            if (r13.size() != 1) goto L33;
            if (((TL_stories.TL_myBoost) r13.get(0)).cooldown_until_date != 0) goto L33;
            TL_stories.TL_myBoost r113 = (TL_stories.TL_myBoost) r13.get(0);
            this.replaceDialogId = DialogObject.getPeerDialogId(r113.peer);
            this.slot = r113.slot;
            this.canApply = true;
        L41:
            if (this.isMaxLvl == false) goto L59;
            this.canApply = false;
            return;
        L59:
            return;
        L33:
            if (r13.size() < 1) goto L38;
            this.needSelector = true;
            if (BoostRepository.isMultiBoostsAvailable() == true) goto L37;
            TL_stories.TL_myBoost r114 = (TL_stories.TL_myBoost) r13.get(0);
            this.replaceDialogId = DialogObject.getPeerDialogId(r114.peer);
            this.slot = r114.slot;
        L37:
            this.canApply = true;
            goto L41
        L38:
            this.canApply = false;
            goto L41
        L39:
            this.canApply = true;
            goto L41
        }
    }

    public static /* synthetic */ void $r8$lambda$01Fplo7yr8leugbyDScQPYOwcXo(TLObject r7, Utilities.Callback r8, TLRPC.TL_error r9) {
        if (r7 == null) goto L5;
        r8.run((TL_stories.TL_premium_boostsStatus) r7);
        return;
    L5:
        BaseFragment r72 = LaunchActivity.getLastFragment();
        if (r9 == null) goto L15;
        if (r72 == null) goto L15;
        if ("CHANNEL_PRIVATE".equals(r9.text) == false) goto L15;
        LaunchActivity r92 = LaunchActivity.instance;
        if (r92 != null) goto L13;
    L14:
        AlertDialog.Builder r1 = new AlertDialog.Builder(r72.getContext(), r72.getResourceProvider());
        r1.setTitle(LocaleController.getString(R.string.AppName));
        HashMap r6 = new HashMap();
        int r73 = Theme.key_dialogTopBackground;
        r6.put("info1", Integer.valueOf(Theme.getColor(r73)));
        r6.put("info2", Integer.valueOf(Theme.getColor(r73)));
        r1.setTopAnimation(R.raw.not_available, 52, false, Theme.getColor(r73), r6);
        r1.setTopAnimationIsNew(true);
        r1.setTitle(LocaleController.getString(R.string.ChannelPrivate));
        r1.setMessage(LocaleController.getString(R.string.ChannelCantOpenPrivate2));
        r1.setPositiveButton(LocaleController.getString(R.string.Close), null);
        r1.show();
    L16:
        r8.run(null);
        return;
    L13:
        if (r92.isFinishing() == true) goto L16;
    L15:
        BulletinFactory.global().showForError(r9);
        goto L16
    }

    public static /* synthetic */ void $r8$lambda$jS7ipboGIzjP5Pq2w1Aoiw_q3VM(CanApplyBoost r2, Utilities.Callback r3, TLRPC.TL_error r4) {
        boolean r0 = r4.text.startsWith("FLOOD_WAIT");
        String r42 = r4.text;
        if (r0 == false) goto L6;
        r2.floodWait = Utilities.parseInt(r42);
    L8:
        r2.canApply = false;
        r3.run(r2);
        return;
    L6:
        if (r42.startsWith("BOOSTS_EMPTY") == false) goto L8;
        r2.empty = true;
        goto L8
    }

    /* renamed from: $r8$lambda$v43EtJeDdm-KTu6HKdG-4hxOhP4, reason: not valid java name */
    public static /* synthetic */ void m3915$r8$lambda$v43EtJeDdmKTu6HKdG4hxOhP4(CanApplyBoost r0, TL_stories.TL_premium_boostsStatus r1, Utilities.Callback r2, TL_stories.TL_premium_myBoosts r3) {
        if (r1.next_level_boosts > 0) goto L5;
        boolean r12 = true;
    L6:
        r0.isMaxLvl = r12;
        r0.setMyBoosts(r3);
        r2.run(r0);
        return;
    L5:
        r12 = false;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$yTESJkbTEm_kXA770gSRm3mebzA(Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChannelBoostsController$$ExternalSyntheticLambda0(r2, r1, r3));
    }

    public ChannelBoostsController(int r2) {
        this.currentAccount = r2;
        this.messagesController = MessagesController.getInstance(r2);
        this.connectionsManager = ConnectionsManager.getInstance(r2);
    }

    public void applyBoost(long r1, int r3, Utilities.Callback<TL_stories.TL_premium_myBoosts> r4, Utilities.Callback<TLRPC.TL_error> r5) {
        BoostRepository.applyBoost(-r1, Arrays.asList(new Integer[]{Integer.valueOf(r3)}), r4, r5);
    }

    public void getBoostsStats(long r3, Utilities.Callback<TL_stories.TL_premium_boostsStatus> r5) {
        TL_stories.TL_premium_getBoostsStatus r0 = new TL_stories.TL_premium_getBoostsStatus();
        r0.peer = this.messagesController.getInputPeer(r3);
        this.connectionsManager.sendRequest(r0, new ChannelBoostsController$$ExternalSyntheticLambda3(r5));
    }

    public void userCanBoostChannel(long r3, TL_stories.TL_premium_boostsStatus r5, Utilities.Callback<CanApplyBoost> r6) {
        CanApplyBoost r0 = new CanApplyBoost();
        r0.currentPeer = this.messagesController.getPeer(r3);
        r0.currentDialogId = r3;
        r0.currentChat = this.messagesController.getChat(Long.valueOf(-r3));
        BoostRepository.getMyBoosts(new ChannelBoostsController$$ExternalSyntheticLambda1(r0, r5, r6), new ChannelBoostsController$$ExternalSyntheticLambda2(r0, r6));
    }
}
