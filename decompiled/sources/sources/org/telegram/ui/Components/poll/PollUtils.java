package org.telegram.ui.Components.poll;

import android.text.TextUtils;
import java.util.ArrayList;
import me.vkryl.core.BitwiseUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public abstract class PollUtils {
    public static int getVoteRestrictedFlags(MessageObject r6) {
        int r2 = 0;
        if (r6.type == 17) goto L5;
        return 0;
    L5:
        TLRPC.Message r0 = r6.messageOwner;
        int r1 = r6.currentAccount;
        TLRPC.TL_messageMediaPoll r3 = (TLRPC.TL_messageMediaPoll) MessageObject.getMedia(r0, TLRPC.TL_messageMediaPoll.class);
        if (r3 != null) goto L8;
        return 0;
    L8:
        TLRPC.Poll r4 = r3.poll;
        if (r4.closed == false) goto L12;
        r2 = 8;
    L12:
        if (r4.subscribers_only == false) goto L31;
        TLRPC.MessageFwdHeader r42 = r0.fwd_from;
        if (r42 == null) goto L16;
        long r43 = DialogObject.getPeerDialogId(r42.from_id);
    L17:
        TLRPC.Chat r62 = MessagesController.getInstance(r1).getChat(Long.valueOf(-r43));
        if (r62 == null) goto L31;
        if (r62.left == false) goto L22;
    L23:
        r2 = r2 | 1;
    L24:
        TLRPC.MessageFwdHeader r44 = r0.fwd_from;
        if (r44 == null) goto L27;
        int r02 = r44.date;
    L29:
        if ((r02 - r62.date) >= 86400) goto L31;
        r2 = r2 | 2;
        goto L31
    L27:
        r02 = r0.date;
        goto L29
    L22:
        if (r62.kicked == false) goto L24;
    L16:
        r43 = r6.getDialogId();
    L31:
        ArrayList<String> r63 = r3.poll.countries_iso2;
        if (r63 != null) goto L34;
    L39:
        return r2;
    L34:
        if (r63.isEmpty() == true) goto L39;
        String r64 = MessagesController.getInstance(r1).config.phoneCountryIso2.get();
        if (r3.poll.countries_iso2.contains(r64) == true) goto L39;
        return r2 | 4;
    }

    public static CharSequence getVoteRestrictedToastText(MessageObject r8, int r9) {
        if (r8.type == 17) goto L5;
        return null;
    L5:
        TLRPC.Message r0 = r8.messageOwner;
        int r1 = r8.currentAccount;
        TLRPC.TL_messageMediaPoll r3 = (TLRPC.TL_messageMediaPoll) MessageObject.getMedia(r0, TLRPC.TL_messageMediaPoll.class);
        if (r3 != null) goto L9;
        return null;
    L9:
        if (BitwiseUtils.hasFlag(r9, 4) == false) goto L37;
        ArrayList r82 = new ArrayList(r3.poll.countries_iso2.size());
        ArrayList<String> r92 = r3.poll.countries_iso2;
        int r02 = r92.size();
        int r12 = 0;
        int r2 = 0;
    L11:
        if (r2 >= r02) goto L17;
        String r4 = r92.get(r2);
        r2 = r2 + 1;
        String r42 = r4;
        String r6 = LocaleController.getCountryName(r42);
        if (TextUtils.isEmpty(r6) == true) goto L16;
        r42 = r6;
    L16:
        r82.add(r42);
        goto L11
    L17:
        boolean r93 = r3.poll.subscribers_only;
        if (r82.size() != 1) goto L24;
        if (r93 == false) goto L21;
        int r94 = R.string.PollV2ToastOnlySubscribersFromCountriesCanVoteOne;
    L23:
        return AndroidUtilities.replaceTags(LocaleController.formatString(r94, new Object[]{r82.get(0)}));
    L21:
        r94 = R.string.PollV2ToastOnlyUsersFromCountriesCanVoteOne;
        goto L23
    L24:
        StringBuffer r03 = new StringBuffer();
    L26:
        if (r12 >= (r82.size() - 1)) goto L31;
        if (r03.length() <= 0) goto L30;
        r03.append(", ");
    L30:
        r03.append((String) r82.get(r12));
        r12 = r12 + 1;
        goto L26
    L31:
        if (r93 == false) goto L33;
        int r95 = R.string.PollV2ToastOnlySubscribersFromCountriesCanVoteOther;
    L35:
        return AndroidUtilities.replaceTags(LocaleController.formatString(r95, new Object[]{r03, r82.get(r82.size() - 1)}));
    L33:
        r95 = R.string.PollV2ToastOnlyUsersFromCountriesCanVoteOther;
        goto L35
    L37:
        if (BitwiseUtils.hasFlag(r9, 1) == false) goto L45;
        TLRPC.MessageFwdHeader r96 = r0.fwd_from;
        if (r96 == null) goto L41;
        long r83 = DialogObject.getPeerDialogId(r96.from_id);
    L43:
        return AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PollV2ToastOnlySubscribersCanVote, new Object[]{DialogObject.getShortName(MessagesController.getInstance(r1).getChat(Long.valueOf(-r83)))}));
    L41:
        r83 = r8.getDialogId();
        goto L43
    L45:
        if (BitwiseUtils.hasFlag(r9, 2) == true) goto L47;
        return null;
    L47:
        return AndroidUtilities.replaceTags(LocaleController.getString(R.string.PollV2ToastOnlySubscribersJoined24hCanVote));
    }
}
