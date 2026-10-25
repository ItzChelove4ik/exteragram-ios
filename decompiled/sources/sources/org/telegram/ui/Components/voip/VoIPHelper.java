package org.telegram.ui.Components.voip;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BetterRatingView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.JoinCallAlert;
import org.telegram.ui.Components.JoinCallByUrlAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.GroupCallActivity;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public abstract class VoIPHelper {
    public static long lastCallTime;

    /* renamed from: org.telegram.ui.Components.voip.VoIPHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends JoinCallByUrlAlert {
        final /* synthetic */ AccountInstance val$accountInstance;
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ boolean val$canVideoCall;
        final /* synthetic */ TLRPC.Chat val$chat;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ String val$hash;
        final /* synthetic */ TLRPC.InputPeer val$inputPeer;
        final /* synthetic */ TLRPC.User val$user;
        final /* synthetic */ boolean val$videoCall;

        public AnonymousClass1(Context r1, TLRPC.Chat r2, TLRPC.User r3, TLRPC.Chat r4, String r5, TLRPC.InputPeer r6, boolean r7, boolean r8, Activity r9, BaseFragment r10, AccountInstance r11) {
            this.val$user = r3;
            this.val$chat = r4;
            this.val$hash = r5;
            this.val$inputPeer = r6;
            this.val$videoCall = r7;
            this.val$canVideoCall = r8;
            this.val$activity = r9;
            this.val$fragment = r10;
            this.val$accountInstance = r11;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.JoinCallByUrlAlert
        public void onJoin() {
            VoIPHelper.m13963$$Nest$smdoInitiateCall(this.val$user, this.val$chat, this.val$hash, this.val$inputPeer, true, this.val$videoCall, this.val$canVideoCall, false, this.val$activity, this.val$fragment, this.val$accountInstance, false, false);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPHelper$2, reason: invalid class name */
    public class AnonymousClass2 extends JoinCallByUrlAlert {
        final /* synthetic */ AccountInstance val$accountInstance;
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ boolean val$canVideoCall;
        final /* synthetic */ TLRPC.Chat val$chat;
        final /* synthetic */ boolean val$createCall;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ String val$hash;
        final /* synthetic */ boolean val$rtmp;
        final /* synthetic */ TLRPC.InputPeer val$selectedPeer;
        final /* synthetic */ TLRPC.User val$user;
        final /* synthetic */ boolean val$videoCall;

        public AnonymousClass2(Context r1, TLRPC.Chat r2, TLRPC.User r3, TLRPC.Chat r4, String r5, TLRPC.InputPeer r6, boolean r7, boolean r8, boolean r9, Activity r10, BaseFragment r11, AccountInstance r12, boolean r13) {
            this.val$user = r3;
            this.val$chat = r4;
            this.val$hash = r5;
            this.val$selectedPeer = r6;
            this.val$videoCall = r7;
            this.val$canVideoCall = r8;
            this.val$createCall = r9;
            this.val$activity = r10;
            this.val$fragment = r11;
            this.val$accountInstance = r12;
            this.val$rtmp = r13;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.JoinCallByUrlAlert
        public void onJoin() {
            VoIPHelper.m13964$$Nest$smdoInitiateCall(this.val$user, this.val$chat, this.val$hash, this.val$selectedPeer, false, this.val$videoCall, this.val$canVideoCall, this.val$createCall, this.val$activity, this.val$fragment, this.val$accountInstance, false, true, this.val$rtmp);
        }
    }

    /* renamed from: $r8$lambda$-45fvM_jdYsMLk9xAT5S9Awjjas, reason: not valid java name */
    public static /* synthetic */ void m13955$r8$lambda$45fvM_jdYsMLk9xAT5S9Awjjas(TLRPC.User r13, TLRPC.Chat r14, String r15, TLRPC.InputPeer r16, boolean r17, boolean r18, boolean r19, Activity r20, BaseFragment r21, AccountInstance r22, AlertDialog r23, int r24) {
        doInitiateCall(r13, r14, r15, r16, false, r17, r18, r19, r20, r21, r22, false, false);
    }

    public static /* synthetic */ void $r8$lambda$0kWTlzM1M5MSQKwMb4D1Jv8V5gc(boolean[] r2, CheckBoxCell r3, View r4) {
        boolean r0 = !r2[0];
        r2[0] = r0;
        r3.setChecked(r0, true);
    }

    public static /* synthetic */ void $r8$lambda$2LXwkBEgWvcEr0X1w5eAF4NUtc0(SharedPreferences r1, TextCheckCell r2, View r3) {
        boolean r32 = r1.getBoolean("dbg_dump_call_stats", false);
        SharedPreferences.Editor r12 = r1.edit();
        boolean r33 = !r32;
        r12.putBoolean("dbg_dump_call_stats", r33);
        r12.apply();
        r2.setChecked(r33);
    }

    /* renamed from: $r8$lambda$2o7tLPhFx5P-tbnDRgrzU5sWjIg, reason: not valid java name */
    public static /* synthetic */ void m13956$r8$lambda$2o7tLPhFx5PtbnDRgrzU5sWjIg(BetterRatingView r7, int[] r8, LinearLayout r9, EditTextBoldCursor r10, boolean[] r11, long r12, long r14, boolean r16, int r17, File r18, Context r19, AlertDialog r20, TextView r21, CheckBoxCell r22, TextView r23, View r24, View r25) {
        if (r7.getRating() >= 4) goto L12;
        if (r8[0] == 1) goto L12;
        r8[0] = 1;
        r7.setVisibility(8);
        r21.setVisibility(8);
        r20.setTitle(LocaleController.getString(R.string.CallReportHint));
        r10.setVisibility(0);
        if (r18.exists() == false) goto L10;
        r22.setVisibility(0);
        r23.setVisibility(0);
    L10:
        r9.setVisibility(0);
        ((TextView) r24).setText(LocaleController.getString(R.string.Send).toUpperCase());
        return;
    L12:
        int r1 = UserConfig.selectedAccount;
        TL_phone.setCallRating r4 = new TL_phone.setCallRating();
        r4.rating = r7.getRating();
        ArrayList r5 = new ArrayList();
        int r72 = 0;
    L14:
        if (r72 >= r9.getChildCount()) goto L20;
        CheckBoxCell r0 = (CheckBoxCell) r9.getChildAt(r72);
        if (r0.isChecked() == false) goto L18;
        r5.add("#" + r0.getTag());
    L18:
        r72 = r72 + 1;
        goto L14
    L20:
        if (r4.rating >= 5) goto L22;
        r4.comment = r10.getText().toString();
    L24:
        if (r5.isEmpty() == false) goto L26;
    L28:
        TLRPC.TL_inputPhoneCall r73 = new TLRPC.TL_inputPhoneCall();
        r4.peer = r73;
        r73.access_hash = r12;
        r73.id = r14;
        r4.user_initiative = r16;
        ConnectionsManager.getInstance(r17).sendRequest(r4, new VoIPHelper$$ExternalSyntheticLambda14(r1, r11, r18, r4, r5, r19));
        r20.dismiss();
        return;
    L26:
        if (r11[0] == true) goto L28;
        r4.comment += " " + TextUtils.join(" ", r5);
        goto L28
    L22:
        r4.comment = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L24
    }

    /* renamed from: $r8$lambda$3G-Ma3hKZd7eKI-bVcOcnpSYULo, reason: not valid java name */
    public static /* synthetic */ void m13957$r8$lambda$3GMa3hKZd7eKIbVcOcnpSYULo(TLRPC.User r15, TLRPC.Chat r16, String r17, boolean r18, boolean r19, boolean r20, Activity r21, BaseFragment r22, AccountInstance r23, AlertDialog r24, int r25) {
        if (VoIPService.getSharedInstance() == null) goto L6;
        VoIPService.getSharedInstance().hangUp(new VoIPHelper$$ExternalSyntheticLambda19(r15, r16, r17, r18, r19, r20, r21, r22, r23));
        return;
    L6:
        doInitiateCall(r15, r16, r17, null, false, r18, r19, r20, r21, r22, r23, true, true);
    }

    /* renamed from: $r8$lambda$7KXQyz01-xjcfi5gz80JsoQnDao, reason: not valid java name */
    public static /* synthetic */ void m13958$r8$lambda$7KXQyz01xjcfi5gz80JsoQnDao(TLRPC.User r15, TLRPC.Chat r16, String r17, boolean r18, boolean r19, boolean r20, Activity r21, BaseFragment r22, AccountInstance r23) {
        lastCallTime = 0;
        doInitiateCall(r15, r16, r17, null, false, r18, r19, r20, r21, r22, r23, true, true);
    }

    /* renamed from: $r8$lambda$FOFDPztKGTZ-_breLHbon9i3KBo, reason: not valid java name */
    public static /* synthetic */ void m13959$r8$lambda$FOFDPztKGTZ_breLHbon9i3KBo(SharedPreferences r1, TextCheckCell r2, View r3) {
        boolean r32 = r1.getBoolean("dbg_force_connection_service", false);
        SharedPreferences.Editor r12 = r1.edit();
        boolean r33 = !r32;
        r12.putBoolean("dbg_force_connection_service", r33);
        r12.apply();
        r2.setChecked(r33);
    }

    /* renamed from: $r8$lambda$LGaFiyEg835xXSzydomc_-Ff6Lk, reason: not valid java name */
    public static /* synthetic */ void m13960$r8$lambda$LGaFiyEg835xXSzydomc_Ff6Lk(Activity r0, Intent r1, AlertDialog r2, int r3) {
        r0.startActivity(r1);
    }

    public static /* synthetic */ void $r8$lambda$LQkupkbvOnTxhUG1iYs7odOkR3Y(Activity r2, AlertDialog r3, int r4) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        r32.setData(Uri.fromParts("package", r2.getPackageName(), null));
        r2.startActivity(r32);
    }

    public static /* synthetic */ void $r8$lambda$NcM93ipqfAV8nIwMmscu1aQaf9g(SharedPreferences r1, TextCheckCell r2, View r3) {
        boolean r32 = r1.getBoolean("dbg_force_tcp_in_calls", false);
        SharedPreferences.Editor r12 = r1.edit();
        boolean r33 = !r32;
        r12.putBoolean("dbg_force_tcp_in_calls", r33);
        r12.apply();
        r2.setChecked(r33);
    }

    public static /* synthetic */ void $r8$lambda$R3Z9SKtbcvtQj4pSaqFwYp3ObMA(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$TsJm6mZiInRlsZAcxmdcnU5r8IU(View r1, int r2) {
        if (r2 <= 0) goto L4;
        boolean r0 = true;
    L5:
        r1.setEnabled(r0);
        TextView r12 = (TextView) r1;
        if (r2 >= 4) goto L9;
        int r22 = R.string.Next;
    L10:
        r12.setText(LocaleController.getString(r22).toUpperCase());
        return;
    L9:
        r22 = R.string.Send;
        goto L10
    L4:
        r0 = false;
        goto L5
    }

    public static /* synthetic */ void $r8$lambda$XfLAPtKUZaa2hirO_HUl3Q6H5Ls(Activity r0, Intent r1, AlertDialog r2, int r3) {
        r0.startActivity(r1);
    }

    public static /* synthetic */ void $r8$lambda$Zot_BVY_wR6YUwOD4KisCMnlB1c(Activity r2, int r3, TLRPC.InputGroupCall r4, boolean r5, TLRPC.GroupCall r6, HashSet r7) {
        lastCallTime = 0;
        joinConference(r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$_IMzZcdN3bYb-4XsU6s2akXo1Wk, reason: not valid java name */
    public static /* synthetic */ void m13961$r8$lambda$_IMzZcdN3bYb4XsU6s2akXo1Wk(int r19, boolean[] r20, File r21, TL_phone.setCallRating r22, ArrayList r23, Context r24, TLObject r25, TLRPC.TL_error r26) {
        if ((r25 instanceof TLRPC.TL_updates) == false) goto L6;
        MessagesController r1 = MessagesController.getInstance(r19);
        r1.processUpdates((TLRPC.TL_updates) r25, false);
    L6:
        if (r20[0] == true) goto L8;
        return;
    L8:
        if (r21.exists() == true) goto L10;
        return;
    L10:
        if (r22.rating >= 4) goto L15;
        SendMessagesHelper.prepareSendingDocument(AccountInstance.getInstance(UserConfig.selectedAccount), r21.getAbsolutePath(), r21.getAbsolutePath(), null, TextUtils.join(" ", r23), "text/plain", 4244000, null, null, null, null, null, true, 0, null, null, false);
        Toast.makeText(r24, LocaleController.getString(R.string.CallReportSent), 1).show();
        return;
    }

    /* renamed from: $r8$lambda$kMm0kcmkB-GJnCiOp19D94oJaDk, reason: not valid java name */
    public static /* synthetic */ void m13962$r8$lambda$kMm0kcmkBGJnCiOp19D94oJaDk(TLRPC.Chat r7, TLRPC.InputPeer r8, BaseFragment r9, AccountInstance r10, boolean r11) {
        startCall(r7, r8, null, true, r9.getParentActivity(), r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$n6KOmKqZ5QwCdjJvnWLyzWzLbY4(View r2) {
        ((CheckBoxCell) r2).setChecked(!r2.isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$pIyYqwERhIj7RBJmIwxwhVz91rM(Context r0, File r1, AlertDialog r2, int r3) {
        Intent r22 = new Intent(r0, LaunchActivity.class);
        r22.setAction("android.intent.action.SEND");
        r22.putExtra("android.intent.extra.STREAM", Uri.fromFile(r1));
        r0.startActivity(r22);
    }

    public static /* synthetic */ void $r8$lambda$sGMxExiFipC_lKgMmu9ERiHOWvw(int r0, TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_updates) == false) goto L6;
        MessagesController r02 = MessagesController.getInstance(r0);
        r02.processUpdates((TLRPC.TL_updates) r1, false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$tAYfOx6f4zxFcRF_VdkkDlujSXE(String r13, Activity r14, TLRPC.Chat r15, TLRPC.User r16, TLRPC.InputPeer r17, boolean r18, boolean r19, BaseFragment r20, AccountInstance r21, boolean r22) {
        if (r22 == true) goto L8;
        if (r13 == null) goto L8;
        AnonymousClass1 r0 = new AnonymousClass1(r14, r15, r16, r15, r13, r17, r18, r19, r14, r20, r21);
        if (r20 == null) goto L10;
        r20.showDialog(r0);
        return;
    L10:
        return;
    L8:
        doInitiateCall(r16, r15, r13, r17, !r22, r18, r19, false, r14, r20, r21, false, false);
    }

    public static /* synthetic */ void $r8$lambda$ughoGj68usXSB2IOxLqz6uhBjN8(boolean r14, Activity r15, AccountInstance r16, TLRPC.Chat r17, String r18, TLRPC.User r19, boolean r20, boolean r21, BaseFragment r22, TLRPC.InputPeer r23, boolean r24, boolean r25, boolean r26) {
        if (r14 == false) goto L6;
        if (r25 == false) goto L6;
        GroupCallActivity.create((LaunchActivity) r15, r16, r17, r23, r24, r18);
        return;
    L6:
        if (r24 == true) goto L12;
        if (r18 == null) goto L12;
        AnonymousClass2 r0 = new AnonymousClass2(r15, r17, r19, r17, r18, r23, r20, r21, r14, r15, r22, r16, r26);
        if (r22 == null) goto L14;
        r22.showDialog(r0);
        return;
    L14:
        return;
    L12:
        doInitiateCall(r19, r17, r18, r23, r24, r20, r21, r14, r15, r22, r16, false, true, r26);
    }

    public static /* synthetic */ void $r8$lambda$xP7Nq6k27RDMaIBj6wg36i6EIP0(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$xnMlA0qHzx_eZwdg6IbAtpJAqf4(AlertDialog r0, int r1) {
    }

    /* renamed from: -$$Nest$smdoInitiateCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13963$$Nest$smdoInitiateCall(TLRPC.User r0, TLRPC.Chat r1, String r2, TLRPC.InputPeer r3, boolean r4, boolean r5, boolean r6, boolean r7, Activity r8, BaseFragment r9, AccountInstance r10, boolean r11, boolean r12) {
        doInitiateCall(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    /* renamed from: -$$Nest$smdoInitiateCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13964$$Nest$smdoInitiateCall(TLRPC.User r0, TLRPC.Chat r1, String r2, TLRPC.InputPeer r3, boolean r4, boolean r5, boolean r6, boolean r7, Activity r8, BaseFragment r9, AccountInstance r10, boolean r11, boolean r12, boolean r13) {
        doInitiateCall(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    static {
    }

    public static boolean canRateCall(TLRPC.TL_messageActionPhoneCall r6) {
        TLRPC.PhoneCallDiscardReason r0 = r6.reason;
        if ((r0 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L5;
    L16:
        return false;
    L5:
        if ((r0 instanceof TLRPC.TL_phoneCallDiscardReasonMissed) == true) goto L16;
        Iterator<String> r02 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).getStringSet("calls_access_hashes", Collections.EMPTY_SET).iterator();
    L8:
        if (r02.hasNext() == false) goto L16;
        String[] r1 = r02.next().split(" ");
        if (r1.length < 2) goto L8;
        if (r1[0].equals(r6.call_id + _UrlKt.FRAGMENT_ENCODE_SET) == false) goto L8;
        return true;
    }

    private static void doInitiateCall(TLRPC.User r14, TLRPC.Chat r15, String r16, TLRPC.InputPeer r17, boolean r18, boolean r19, boolean r20, boolean r21, Activity r22, BaseFragment r23, AccountInstance r24, boolean r25, boolean r26) {
        doInitiateCall(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, false);
    }

    private static void doInitiateCall(TLRPC.User r13, TLRPC.Chat r14, String r15, TLRPC.InputPeer r16, boolean r17, boolean r18, boolean r19, boolean r20, Activity r21, BaseFragment r22, AccountInstance r23, boolean r24, boolean r25, boolean r26) {
        if (r21 == null) goto L92;
        if (r13 != null) goto L7;
        if (r14 != null) goto L7;
        return;
    L7:
        long r0 = SystemClock.elapsedRealtime() - lastCallTime;
        if (r14 == null) goto L10;
        int r2 = 200;
    L12:
        if (r0 < r2) goto L94;
        if (r24 == false) goto L24;
        if (r14 == null) goto L24;
        if (r20 == true) goto L24;
        TLRPC.ChatFull r02 = r23.getMessagesController().getChatFull(r14.id);
        if (r02 == null) goto L24;
        TLRPC.Peer r03 = r02.groupcall_default_join_as;
        if (r03 == null) goto L24;
        JoinCallAlert.checkFewUsers(r21, -r14.id, r23, new VoIPHelper$$ExternalSyntheticLambda16(r15, r21, r14, r13, r23.getMessagesController().getInputPeer(MessageObject.getPeerId(r03)), r18, r19, r22, r23));
        return;
    L24:
        if (r24 == false) goto L29;
        if (r14 == null) goto L29;
        JoinCallAlert.open(r21, -r14.id, r23, r22, !r20 ? 1 : 0, null, new VoIPHelper$$ExternalSyntheticLambda17(r20, r21, r23, r14, r15, r13, r18, r19, r22));
        return;
    L29:
        if (r25 == false) goto L53;
        if (r17 == true) goto L53;
        if ((r16 instanceof TLRPC.TL_inputPeerUser) == false) goto L53;
        if (ChatObject.shouldSendAnonymously(r14) == false) goto L53;
        if (ChatObject.isChannel(r14) == true) goto L38;
    L41:
        AlertDialog.Builder r04 = new AlertDialog.Builder(r21);
        if (ChatObject.isChannelOrGiga(r14) == false) goto L45;
        int r1 = R.string.VoipChannelVoiceChat;
    L46:
        AlertDialog.Builder r05 = r04.setTitle(LocaleController.getString(r1));
        if (ChatObject.isChannelOrGiga(r14) == false) goto L50;
        int r12 = R.string.VoipChannelJoinAnonymouseAlert;
    L51:
        r05.setMessage(LocaleController.getString(r12)).setPositiveButton(LocaleController.getString(R.string.VoipChatJoin), new VoIPHelper$$ExternalSyntheticLambda18(r13, r14, r15, r16, r18, r19, r20, r21, r22, r23)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L50:
        r12 = R.string.VoipGroupJoinAnonymouseAlert;
        goto L51
    L45:
        r1 = R.string.VoipGroupVoiceChat;
        goto L46
    L38:
        if (r14.megagroup == true) goto L41;
    L53:
        if (r14 == null) goto L70;
        if (r16 == null) goto L70;
        TLRPC.ChatFull r06 = r23.getMessagesController().getChatFull(r14.id);
        if (r06 == null) goto L70;
        if ((r16 instanceof TLRPC.TL_inputPeerUser) == false) goto L61;
        TLRPC.TL_peerUser r110 = new TLRPC.TL_peerUser();
        r06.groupcall_default_join_as = r110;
        r110.user_id = r16.user_id;
    L66:
        boolean r111 = r06 instanceof TLRPC.TL_chatFull;
        int r5 = r06.flags;
        if (r111 == false) goto L69;
        r06.flags = 32768 | r5;
        goto L70
    L69:
        r06.flags = 67108864 | r5;
        goto L70
    L61:
        if ((r16 instanceof TLRPC.TL_inputPeerChat) == false) goto L64;
        TLRPC.TL_peerChat r112 = new TLRPC.TL_peerChat();
        r06.groupcall_default_join_as = r112;
        r112.chat_id = r16.chat_id;
        goto L66
    L64:
        if ((r16 instanceof TLRPC.TL_inputPeerChannel) == false) goto L66;
        TLRPC.TL_peerChannel r113 = new TLRPC.TL_peerChannel();
        r06.groupcall_default_join_as = r113;
        r113.channel_id = r16.channel_id;
    L70:
        if (r14 == null) goto L78;
        if (r20 == true) goto L78;
        ChatObject.Call r07 = r23.getMessagesController().getGroupCall(r14.id, false);
        if (r07 == null) goto L78;
        if (r07.isScheduled() == false) goto L78;
        GroupCallActivity.create((LaunchActivity) r21, r23, r14, r16, r17, r15);
        return;
    L78:
        lastCallTime = SystemClock.elapsedRealtime();
        Intent r114 = new Intent(r21, VoIPService.class);
        if (r13 == null) goto L81;
        r114.putExtra("user_id", r13.id);
    L84:
        r114.putExtra("is_outgoing", true);
        r114.putExtra("start_incall_activity", true);
        r114.putExtra("video_call", r18);
        r114.putExtra("can_video_call", r19);
        r114.putExtra("account", UserConfig.selectedAccount);
        r21.startService(r114);     // Catch: Throwable -> L87
        return;
    L87:
        th = move-exception;
        FileLog.e(th);
        return;
    L81:
        r114.putExtra("chat_id", r14.id);
        r114.putExtra("createGroupCall", r20);
        r114.putExtra("hasFewPeers", r17);
        r114.putExtra("isRtmpStream", r26);
        r114.putExtra("hash", r15);
        if (r16 == null) goto L84;
        r114.putExtra("peerChannelId", r16.channel_id);
        r114.putExtra("peerChatId", r16.chat_id);
        r114.putExtra("peerUserId", r16.user_id);
        r114.putExtra("peerAccessHash", r16.access_hash);
        goto L84
    L94:
        return;
    L10:
        r2 = CastStatusCodes.AUTHENTICATION_FAILED;
        goto L12
    }

    public static int getDataSavingDefault() {
        boolean r1 = DownloadController.getInstance(0).lowPreset.lessCallData;
        boolean r2 = DownloadController.getInstance(0).mediumPreset.lessCallData;
        boolean r3 = DownloadController.getInstance(0).highPreset.lessCallData;
        if (r1 == true) goto L7;
        if (r2 == true) goto L7;
        if (r3 == true) goto L7;
        return 0;
    L7:
        if (r1 == false) goto L12;
        if (r2 == true) goto L12;
        if (r3 == true) goto L12;
        return 3;
    L12:
        if (r1 == false) goto L17;
        if (r2 == false) goto L17;
        if (r3 == true) goto L17;
        return 1;
    L17:
        if (r1 == false) goto L23;
        if (r2 == false) goto L23;
        if (r3 == false) goto L23;
        return 2;
    L23:
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.w("Invalid call data saving preset configuration: " + r1 + "/" + r2 + "/" + r3);
    L25:
        return 0;
    }

    private static File getLogFile(long r7) {
        if (BuildVars.DEBUG_VERSION == false) goto L14;
        File r0 = new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), "logs");
        String[] r1 = r0.list();
        if (r1 == null) goto L14;
        int r2 = r1.length;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L14;
        String r4 = r1[r3];
        if (r4.endsWith("voip" + r7 + ".txt") == true) goto L11;
        r3 = r3 + 1;
        goto L7
    L11:
        return new File(r0, r4);
    L14:
        return new File(getLogsDir(), r7 + ".log");
    }

    public static String getLogFilePath(String r12) {
        Calendar r0 = Calendar.getInstance();
        return new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), String.format(Locale.US, "logs/%02d_%02d_%04d_%02d_%02d_%02d_%s.txt", new Object[]{Integer.valueOf(r0.get(5)), Integer.valueOf(r0.get(2) + 1), Integer.valueOf(r0.get(1)), Integer.valueOf(r0.get(11)), Integer.valueOf(r0.get(12)), Integer.valueOf(r0.get(13)), r12})).getAbsolutePath();
    }

    public static String getLogFilePath(String r11, boolean r12) {
        File r0 = getLogsDir();
        if (BuildVars.DEBUG_VERSION == true) goto L17;
        File[] r1 = r0.listFiles();
        if (r1 == null) goto L17;
        ArrayList r3 = new ArrayList(Arrays.asList(r1));
    L8:
        if (r3.size() <= 20) goto L17;
        int r13 = 0;
        File r4 = (File) r3.get(0);
        int r5 = r3.size();
    L10:
        if (r13 >= r5) goto L16;
        Object r6 = r3.get(r13);
        r13 = r13 + 1;
        File r62 = (File) r6;
        if (r62.getName().endsWith(".log") == false) goto L10;
        if (r62.lastModified() >= r4.lastModified()) goto L10;
        r4 = r62;
        goto L10
    L16:
        r4.delete();
        r3.remove(r4);
    L17:
        if (r12 == false) goto L21;
        return new File(r0, r11 + "_stats.log").getAbsolutePath();
    L21:
        return new File(r0, r11 + ".log").getAbsolutePath();
    }

    public static File getLogsDir() {
        File r0 = new File(ApplicationLoader.applicationContext.getCacheDir(), "voip_logs");
        if (r0.exists() == true) goto L5;
        r0.mkdirs();
    L5:
        return r0;
    }

    private static void initiateCall(TLRPC.User r22, TLRPC.Chat r23, String r24, boolean r25, boolean r26, boolean r27, Boolean r28, Activity r29, BaseFragment r30, AccountInstance r31) {
        if (r29 == null) goto L90;
        if (r22 != null) goto L7;
        if (r23 != null) goto L7;
        return;
    L7:
        VoIPService r2 = VoIPService.getSharedInstance();
        if (r2 == null) goto L80;
        if (r22 == null) goto L11;
        long r4 = r22.id;
    L12:
        long r8 = r2.getCallerId();
        if (r8 != r4) goto L33;
        if (r2.getAccount() != r31.getCurrentAccount()) goto L33;
        if (r22 == null) goto L19;
    L26:
        Intent r1 = new Intent(r29, LaunchActivity.class);
        if (r22 == null) goto L29;
        String r0 = "voip";
    L30:
        r29.startActivity(r1.setAction(r0));
        return;
    L29:
        r0 = "voip_chat";
        goto L30
    L19:
        if ((r29 instanceof LaunchActivity) == false) goto L26;
        if (TextUtils.isEmpty(r24) == true) goto L24;
        r2.setGroupCallHash(r24);
    L24:
        GroupCallActivity.create((LaunchActivity) r29, AccountInstance.getInstance(UserConfig.selectedAccount), null, null, false, null);
        return;
    L33:
        if (r2.isConference() == false) goto L59;
        StringBuilder r10 = new StringBuilder();
        if (r2.groupCall == null) goto L53;
        int r13 = r2.getAccount();
        int r15 = 0;
        int r16 = 0;
    L38:
        if (r15 >= r2.groupCall.participants.size()) goto L49;
        long r18 = 0;
        long r11 = DialogObject.getPeerDialogId(r2.groupCall.participants.valueAt(r15).peer);
        if (r11 == UserConfig.getInstance(r13).getClientUserId()) goto L48;
        int r3 = r16 + 1;
        if (r10.length() <= 0) goto L44;
        r10.append(", ");
    L44:
        r10.append(DialogObject.getShortName(r13, r11));
        if (r3 >= 2) goto L51;
        r16 = r3;
    L51:
        if (r3 >= (r2.groupCall.participants.size() - 1)) goto L55;
        r10.append(LocaleController.formatPluralString("AndOther", (r2.groupCall.participants.size() - 1) - r3, new Object[0]));
    L55:
        if (r4 <= r18) goto L57;
        int r210 = R.string.VoipOngoingConferenceChatAlert;
    L58:
        String r32 = r10.toString();
    L69:
        if (r22 == null) goto L71;
        String r42 = ContactsController.formatName(r22.first_name, r22.last_name);
    L72:
        AlertDialog.Builder r5 = new AlertDialog.Builder(r29);
        if (r8 >= r18) goto L76;
        int r82 = R.string.VoipOngoingChatAlertTitle;
    L77:
        r5.setTitle(LocaleController.getString(r82)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(r210, new Object[]{r32, r42}))).setPositiveButton(LocaleController.getString(R.string.OK), new VoIPHelper$$ExternalSyntheticLambda5(r22, r23, r24, r25, r26, r27, r29, r30, r31)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        return;
    L76:
        r82 = R.string.VoipOngoingAlertTitle;
        goto L77
    L71:
        r42 = r23.title;
        goto L72
    L57:
        r210 = R.string.VoipOngoingConferenceChatAlert2;
    L48:
        r15 = r15 + 1;
        goto L38
    L49:
        r18 = 0;
        r3 = r16;
        goto L51
    L53:
        r18 = 0;
        goto L55
    L59:
        r18 = 0;
        if (r8 <= 0) goto L65;
        TLRPC.User r211 = r2.getUser();
        r32 = ContactsController.formatName(r211.first_name, r211.last_name);
        if (r4 <= 0) goto L64;
        r210 = R.string.VoipOngoingAlert;
        goto L69
    L64:
        r210 = R.string.VoipOngoingAlert2;
        goto L69
    L65:
        r32 = r2.getChat().title;
        if (r4 <= 0) goto L68;
        r210 = R.string.VoipOngoingChatAlert2;
        goto L69
    L68:
        r210 = R.string.VoipOngoingChatAlert;
        goto L69
    L11:
        r4 = -r23.id;
        goto L12
    L80:
        if (VoIPService.callIShouldHavePutIntoIntent != null) goto L91;
        if (r28 == null) goto L83;
        boolean r112 = r28.booleanValue();
    L84:
        doInitiateCall(r22, r23, r24, null, false, r25, r26, r27, r29, r30, r31, r112, true);
        return;
    L83:
        r112 = true;
        goto L84
    L91:
        return;
    }

    public static void joinConference(Activity r6, int r7, TLRPC.InputGroupCall r8, boolean r9, TLRPC.GroupCall r10) {
        joinConference(r6, r7, r8, r9, r10, null);
    }

    public static void joinConference(Activity r8, int r9, TLRPC.InputGroupCall r10, boolean r11, TLRPC.GroupCall r12, HashSet<Long> r13) {
        if (r8 != null) goto L5;
        return;
    L5:
        if (VoIPService.getSharedInstance() == null) goto L8;
        VoIPService.getSharedInstance().hangUp(new VoIPHelper$$ExternalSyntheticLambda13(r8, r9, r10, r11, r12, r13));
        return;
    L8:
        lastCallTime = SystemClock.elapsedRealtime();
        Intent r82 = new Intent(r8, VoIPService.class);
        r82.putExtra("chat_id", 0);
        int r102 = 0;
        r82.putExtra("createGroupCall", false);
        r82.putExtra("hasFewPeers", false);
        r82.putExtra("isRtmpStream", false);
        r82.putExtra("hash", null);
        r82.putExtra("is_outgoing", true);
        r82.putExtra("start_incall_activity", true);
        r82.putExtra("video_call", false);
        SerializedData r122 = new SerializedData(r10.getObjectSize());
        r10.serializeToStream(r122);
        r82.putExtra("joinConference", r122.toByteArray());
        if (r12 == null) goto L11;
        SerializedData r123 = new SerializedData(r12.getObjectSize());
        r12.serializeToStream(r123);
        r82.putExtra("joinConferenceCall", r123.toByteArray());
    L11:
        if (r13 == null) goto L17;
        long[] r124 = new long[r13.size()];
        Iterator<Long> r132 = r13.iterator();
    L14:
        if (r132.hasNext() == false) goto L16;
        r124[r102] = r132.next().longValue();
        r102 = r102 + 1;
        goto L14
    L16:
        r82.putExtra("inviteUsers", r124);
    L17:
        r82.putExtra("account", r9);
        r82.putExtra("video_call", r11);
        r82.putExtra("can_video_call", true);
        r8.startService(r82);     // Catch: Throwable -> L20
        return;
    L20:
        th = move-exception;
        FileLog.e(th);
    }

    @TargetApi(23)
    public static void permissionDenied(Activity r4, Runnable r5, int r6) {
        if (r6 != 102) goto L5;
        boolean r62 = true;
    L7:
        if (r4.shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO") == false) goto L13;
        if (r62 == true) goto L10;
        return;
    L10:
        if (r4.shouldShowRequestPermissionRationale("android.permission.CAMERA") == false) goto L13;
        return;
    L13:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r4);
        if (r62 == false) goto L17;
        int r2 = R.string.VoipNeedMicCameraPermissionWithHint;
    L18:
        AlertDialog.Builder r42 = r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(r2))).setPositiveButton(LocaleController.getString(R.string.Settings), new VoIPHelper$$ExternalSyntheticLambda2(r4)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).setOnDismissListener(new VoIPHelper$$ExternalSyntheticLambda3(r5));
        if (r62 == false) goto L21;
        int r52 = R.raw.permission_request_camera;
    L22:
        r42.setTopAnimation(r52, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).show();
        return;
    L21:
        r52 = R.raw.permission_request_microphone;
        goto L22
    L17:
        r2 = R.string.VoipNeedMicPermissionWithHint;
        goto L18
    L5:
        r62 = false;
        goto L7
    }

    public static void sendCallRating(long r2, long r4, int r6, int r7) {
        int r0 = UserConfig.selectedAccount;
        TL_phone.setCallRating r1 = new TL_phone.setCallRating();
        r1.rating = r7;
        r1.comment = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.TL_inputPhoneCall r72 = new TLRPC.TL_inputPhoneCall();
        r1.peer = r72;
        r72.access_hash = r4;
        r72.id = r2;
        r1.user_initiative = false;
        ConnectionsManager.getInstance(r6).sendRequest(r1, new VoIPHelper$$ExternalSyntheticLambda0(r0));
    }

    public static void showCallDebugSettings(Context r10) {
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();
        LinearLayout r1 = new LinearLayout(r10);
        r1.setOrientation(1);
        TextView r3 = new TextView(r10);
        r3.setTextSize(1, 15.0f);
        r3.setText("Please only change these settings if you know exactly what they do.");
        r3.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r1.addView(r3, LayoutHelper.createLinear(-1, -2, 16.0f, 8.0f, 16.0f, 8.0f));
        TextCheckCell r2 = new TextCheckCell(r10);
        r2.setTextAndCheck("Force TCP", r0.getBoolean("dbg_force_tcp_in_calls", false), false);
        r2.setOnClickListener(new VoIPHelper$$ExternalSyntheticLambda20(r0, r2));
        r1.addView(r2);
        if (BuildVars.DEBUG_VERSION == false) goto L8;
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        TextCheckCell r22 = new TextCheckCell(r10);
        r22.setTextAndCheck("Dump detailed stats", r0.getBoolean("dbg_dump_call_stats", false), false);
        r22.setOnClickListener(new VoIPHelper$$ExternalSyntheticLambda21(r0, r22));
        r1.addView(r22);
    L8:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        TextCheckCell r23 = new TextCheckCell(r10);
        r23.setTextAndCheck("Enable ConnectionService", r0.getBoolean("dbg_force_connection_service", false), false);
        r23.setOnClickListener(new VoIPHelper$$ExternalSyntheticLambda22(r0, r23));
        r1.addView(r23);
    L10:
        new AlertDialog.Builder(r10).setTitle(LocaleController.getString(R.string.DebugMenuCallSettings)).setView(r1).show();
    }

    public static void showGroupCallAlert(BaseFragment r3, TLRPC.Chat r4, TLRPC.InputPeer r5, boolean r6, AccountInstance r7) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.getParentActivity() == null) goto L9;
        JoinCallAlert.checkFewUsers(r3.getParentActivity(), -r4.id, r7, new VoIPHelper$$ExternalSyntheticLambda15(r4, r5, r3, r7));
        return;
    }

    public static void showRateAlert(Context r25, Runnable r26, boolean r27, long r28, long r30, int r32, boolean r33) {
        File r12 = getLogFile(r28);
        int[] r2 = {0};
        LinearLayout r1 = new LinearLayout(r25);
        r1.setOrientation(1);
        int r5 = AndroidUtilities.dp(16.0f);
        r1.setPadding(r5, r5, r5, 0);
        TextView r15 = new TextView(r25);
        r15.setTextSize(2, 16.0f);
        r15.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r15.setGravity(17);
        r15.setText(LocaleController.getString(R.string.VoipRateCallAlert));
        r1.addView(r15);
        BetterRatingView r4 = new BetterRatingView(r25);
        r1.addView(r4, LayoutHelper.createLinear(-2, -2, 1, 0, 16, 0, 0));
        LinearLayout r6 = new LinearLayout(r25);
        r6.setOrientation(1);
        VoIPHelper$$ExternalSyntheticLambda6 r7 = new VoIPHelper$$ExternalSyntheticLambda6();
        if (r27 == false) goto L5;
        String r16 = "distorted_video";
    L6:
        if (r27 == false) goto L8;
        String r17 = "pixelated_video";
    L9:
        String[] r9 = {r16, r17, "echo", "noise", "interruptions", "distorted_speech", "silent_local", "silent_remote", "dropped"};
        int r10 = 0;
    L11:
        if (r10 >= 9) goto L29;
        if (r9[r10] == null) goto L28;
        CheckBoxCell r11 = new CheckBoxCell(r25, 1);
        r11.setClipToPadding(false);
        r11.setTag(r9[r10]);
        switch(r10) {
            case 0: goto L26;
            case 1: goto L25;
            case 2: goto L24;
            case 3: goto L23;
            case 4: goto L22;
            case 5: goto L21;
            case 6: goto L20;
            case 7: goto L19;
            case 8: goto L18;
            default: goto L17;
        };
    L17:
        String r14 = null;
    L27:
        r11.setText(r14, null, false, false);
        r11.setOnClickListener(r7);
        r11.setTag(r9[r10]);
        r6.addView(r11);
        goto L28
    L18:
        r14 = LocaleController.getString(R.string.RateCallDropped);
        goto L27
    L19:
        r14 = LocaleController.getString(R.string.RateCallSilentRemote);
        goto L27
    L20:
        r14 = LocaleController.getString(R.string.RateCallSilentLocal);
        goto L27
    L21:
        r14 = LocaleController.getString(R.string.RateCallDistorted);
        goto L27
    L22:
        r14 = LocaleController.getString(R.string.RateCallInterruptions);
        goto L27
    L23:
        r14 = LocaleController.getString(R.string.RateCallNoise);
        goto L27
    L24:
        r14 = LocaleController.getString(R.string.RateCallEcho);
        goto L27
    L25:
        r14 = LocaleController.getString(R.string.RateCallVideoPixelated);
        goto L27
    L26:
        r14 = LocaleController.getString(R.string.RateCallVideoDistorted);
    L28:
        r10 = r10 + 1;
        goto L11
    L29:
        r1.addView(r6, LayoutHelper.createLinear(-1, -2, -8.0f, 0.0f, -8.0f, 0.0f));
        r6.setVisibility(8);
        EditTextBoldCursor r92 = new EditTextBoldCursor(r25);
        r92.setHint(LocaleController.getString(R.string.VoipFeedbackCommentHint));
        r92.setInputType(147457);
        r92.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r92.setHintTextColor(Theme.getColor(Theme.key_dialogTextHint));
        r92.setBackground(null);
        r92.setLineColors(Theme.getColor(Theme.key_dialogInputField), Theme.getColor(Theme.key_dialogInputFieldActivated), Theme.getColor(Theme.key_text_RedBold));
        r92.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
        r92.setTextSize(1, 18.0f);
        r92.setVisibility(8);
        r1.addView(r92, LayoutHelper.createLinear(-1, -2, 8.0f, 8.0f, 8.0f, 0.0f));
        boolean[] r102 = {true};
        CheckBoxCell r112 = new CheckBoxCell(r25, 1);
        VoIPHelper$$ExternalSyntheticLambda7 r142 = new VoIPHelper$$ExternalSyntheticLambda7(r102, r112);
        r112.setText(LocaleController.getString(R.string.CallReportIncludeLogs), null, true, false);
        r112.setClipToPadding(false);
        r112.setOnClickListener(r142);
        r1.addView(r112, LayoutHelper.createLinear(-1, -2, -8.0f, 0.0f, -8.0f, 0.0f));
        TextView r3 = new TextView(r25);
        r3.setTextSize(2, 14.0f);
        r3.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
        r3.setText(LocaleController.getString(R.string.CallReportLogsExplain));
        r3.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r3.setOnClickListener(r142);
        r1.addView(r3);
        r112.setVisibility(8);
        r3.setVisibility(8);
        if (r12.exists() == true) goto L32;
        r102[0] = false;
    L32:
        AlertDialog r143 = new AlertDialog.Builder(r25).setTitle(LocaleController.getString(R.string.CallMessageReportProblem)).setView(r1).setPositiveButton(LocaleController.getString(R.string.Send), new VoIPHelper$$ExternalSyntheticLambda8()).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setOnDismissListener(new VoIPHelper$$ExternalSyntheticLambda9(r26)).create();
        if (BuildVars.LOGS_ENABLED == true) goto L35;
    L37:
        r143.show();
        r143.getWindow().setSoftInputMode(3);
        View r13 = r143.getButton(-1);
        r13.setEnabled(false);
        r4.setOnRatingChangeListener(new VoIPHelper$$ExternalSyntheticLambda11(r13));
        r13.setOnClickListener(new VoIPHelper$$ExternalSyntheticLambda12(r4, r2, r6, r92, r102, r30, r28, r33, r32, r12, r25, r143, r15, r112, r3, r13));
        return;
    L35:
        if (r12.exists() == false) goto L37;
        r143.setNeutralButton("Send log", new VoIPHelper$$ExternalSyntheticLambda10(r25, r12));
        goto L37
    L8:
        r17 = null;
        goto L9
    L5:
        r16 = null;
        goto L6
    }

    public static void showRateAlert(Context r10, TLRPC.TL_messageActionPhoneCall r11) {
        Iterator<String> r0 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).getStringSet("calls_access_hashes", Collections.EMPTY_SET).iterator();
    L4:
        if (r0.hasNext() == false) goto L23;
        String[] r1 = r0.next().split(" ");
        if (r1.length < 2) goto L4;
        if (r1[0].equals(r11.call_id + _UrlKt.FRAGMENT_ENCODE_SET) == false) goto L4;
        showRateAlert(r10, null, r11.video, r11.call_id, Long.parseLong(r1[1]), UserConfig.selectedAccount, true);     // Catch: Exception -> L13
        return;
    L22:
        return;
    }

    public static void startCall(TLRPC.Chat r8, TLRPC.InputPeer r9, String r10, boolean r11, Activity r12, BaseFragment r13, AccountInstance r14) {
        startCall(r8, r9, r10, r11, null, r12, r13, r14);
    }

    public static void startCall(TLRPC.Chat r10, TLRPC.InputPeer r11, String r12, boolean r13, Boolean r14, Activity r15, BaseFragment r16, AccountInstance r17) {
        if (r15 != null) goto L5;
        return;
    L5:
        if (ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState() == 3) goto L29;
        boolean r122 = false;
        if (Settings.System.getInt(r15.getContentResolver(), "airplane_mode_on", 0) == 0) goto L9;
        r122 = true;
    L9:
        AlertDialog.Builder r102 = new AlertDialog.Builder(r15);
        if (r122 == false) goto L13;
        int r112 = R.string.VoipOfflineAirplaneTitle;
    L14:
        AlertDialog.Builder r103 = r102.setTitle(LocaleController.getString(r112));
        if (r122 == false) goto L18;
        int r113 = R.string.VoipGroupOfflineAirplane;
    L19:
        AlertDialog.Builder r104 = r103.setMessage(LocaleController.getString(r113)).setPositiveButton(LocaleController.getString(R.string.OK), null);
        if (r122 == false) goto L31;
        Intent r114 = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
        if (r114.resolveActivity(r15.getPackageManager()) == null) goto L31;
        r104.setNeutralButton(LocaleController.getString(R.string.VoipOfflineOpenSettings), new VoIPHelper$$ExternalSyntheticLambda1(r15, r114));
    L31:
        r104.show();     // Catch: Exception -> L26
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
        return;
    L18:
        r113 = R.string.VoipGroupOffline;
        goto L19
    L13:
        r112 = R.string.VoipOfflineTitle;
        goto L14
    L29:
        initiateCall(null, r10, r12, false, false, r13, r14, r15, r16, r17);
    }

    public static void startCall(TLRPC.User r7, boolean r8, boolean r9, Activity r10, TLRPC.UserFull r11, AccountInstance r12) {
        startCall(r7, r8, r9, r10, r11, r12, false);
    }

    public static void startCall(TLRPC.User r10, boolean r11, boolean r12, Activity r13, TLRPC.UserFull r14, AccountInstance r15, boolean r16) {
        if (r15 != null) goto L7;
        if (MessagesController.getInstance(UserConfig.selectedAccount).isFrozen() == false) goto L14;
    L8:
        if (r15 != null) goto L10;
        int r0 = UserConfig.selectedAccount;
    L11:
        AccountFrozenAlert.show(r0);
        return;
    L10:
        r0 = r15.getCurrentAccount();
    L14:
        if (r14 != null) goto L16;
    L19:
        if (r16 == true) goto L24;
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L68;
        AlertsCreator.createCallDialogAlert(r1, r1.getMessagesController().getUser(Long.valueOf(r10.id)), r11);
        return;
    L68:
        return;
    L24:
        boolean r5 = false;
        if (ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState() != 3) goto L27;
        ArrayList r17 = new ArrayList();
        if (r13.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L52;
        r17.add("android.permission.RECORD_AUDIO");
    L52:
        if (r11 == false) goto L57;
        if (r13.checkSelfPermission("android.permission.CAMERA") == 0) goto L57;
        r17.add("android.permission.CAMERA");
    L57:
        if (r17.isEmpty() == false) goto L60;
        initiateCall(r10, null, null, r11, r12, false, null, r13, null, r15);
        return;
    L60:
        String[] r02 = (String[]) r17.toArray(new String[0]);
        if (r11 == false) goto L63;
        int r18 = 102;
    L64:
        r13.requestPermissions(r02, r18);
        return;
    L63:
        r18 = 101;
        goto L64
    L27:
        if (Settings.System.getInt(r13.getContentResolver(), "airplane_mode_on", 0) == 0) goto L29;
        r5 = true;
    L29:
        AlertDialog.Builder r03 = new AlertDialog.Builder(r13);
        if (r5 == false) goto L33;
        int r19 = R.string.VoipOfflineAirplaneTitle;
    L34:
        AlertDialog.Builder r04 = r03.setTitle(LocaleController.getString(r19));
        if (r5 == false) goto L38;
        int r110 = R.string.VoipOfflineAirplane;
    L39:
        AlertDialog.Builder r05 = r04.setMessage(LocaleController.getString(r110)).setPositiveButton(LocaleController.getString(R.string.OK), null);
        if (r5 == false) goto L66;
        Intent r111 = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
        if (r111.resolveActivity(r13.getPackageManager()) == null) goto L66;
        r05.setNeutralButton(LocaleController.getString(R.string.VoipOfflineOpenSettings), new VoIPHelper$$ExternalSyntheticLambda4(r13, r111));
    L66:
        r05.show();     // Catch: Exception -> L46
        return;
    L46:
        e = move-exception;
        FileLog.e(e);
        return;
    L38:
        r110 = R.string.VoipOffline;
        goto L39
    L33:
        r19 = R.string.VoipOfflineTitle;
        goto L34
    L16:
        if (r14.phone_calls_private == false) goto L19;
        AlertsCreator.showCallsForbidden(r13, r15.getCurrentAccount(), r10.id, null);
        return;
    L7:
        if (r15.getMessagesController().isFrozen() == false) goto L14;
        goto L8
    }
}
