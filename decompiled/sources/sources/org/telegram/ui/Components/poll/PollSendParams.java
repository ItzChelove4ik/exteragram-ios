package org.telegram.ui.Components.poll;

import android.text.TextUtils;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public class PollSendParams {
    public final String caption;
    public final ArrayList<TLRPC.MessageEntity> entities;
    public final long groupId;
    public final TLRPC.TL_inputMediaPoll inputMediaPoll;
    public final PollAttachedMediaPack mediaPack;
    public final TLRPC.TL_messageMediaPoll poll;

    public PollSendParams(PollAttachedMediaPack r3, TLRPC.TL_messageMediaPoll r4, long r5, String r7, ArrayList<TLRPC.MessageEntity> r8, ArrayList<Integer> r9) {
        this.mediaPack = r3;
        this.groupId = r5;
        this.caption = r7;
        this.entities = r8;
        TLRPC.TL_inputMediaPoll r52 = new TLRPC.TL_inputMediaPoll();
        TLRPC.TL_poll r6 = new TLRPC.TL_poll();
        r52.poll = r6;
        TLRPC.Poll r72 = r4.poll;
        r6.id = r72.id;
        r6.flags = r72.flags;
        r6.closed = r72.closed;
        r6.public_voters = r72.public_voters;
        r6.multiple_choice = r72.multiple_choice;
        r6.open_answers = r72.open_answers;
        r6.revoting_disabled = r72.revoting_disabled;
        r6.shuffle_answers = r72.shuffle_answers;
        r6.subscribers_only = r72.subscribers_only;
        r6.countries_iso2 = r72.countries_iso2;
        r6.hide_results_until_close = r72.hide_results_until_close;
        r6.creator = r72.creator;
        r6.quiz = r72.quiz;
        r6.answers = new ArrayList(r4.poll.answers);
        ArrayList<TLRPC.PollAnswer> r62 = r4.poll.answers;
        int r73 = r62.size();
        int r82 = 0;
    L3:
        if (r82 >= r73) goto L5;
        TLRPC.PollAnswer r0 = r62.get(r82);
        r82 = r82 + 1;
        new TLRPC.TL_inputPollAnswer().text = r0.text;
        goto L3
    L5:
        TLRPC.Poll r63 = r52.poll;
        TLRPC.Poll r74 = r4.poll;
        r63.question = r74.question;
        r63.close_period = r74.close_period;
        r63.close_date = r74.close_date;
        r63.hash = r74.hash;
        TLRPC.PollResults r64 = r4.results;
        if (r64 != null) goto L8;
    L10:
        if (r9 != null) goto L12;
    L14:
        if (r3 == null) goto L16;
        r3.applyAllQuickMedia(r52);
        r3.applyAllQuickMedia(r4);
    L16:
        this.poll = r4;
        this.inputMediaPoll = r52;
        return;
    L12:
        if (r9.isEmpty() == true) goto L14;
        r52.correct_answers = new ArrayList(r9);
        r52.flags |= 1;
        goto L14
    L8:
        if (TextUtils.isEmpty(r64.solution) == true) goto L10;
        TLRPC.PollResults r65 = r4.results;
        r52.solution = r65.solution;
        r52.solution_entities = r65.solution_entities;
        r52.flags |= 2;
        goto L10
    }
}
