package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes4.dex */
public class ApiWrap$Poll {
    public ArrayList<Answer> answers;
    public boolean closed;
    public long id;
    public String question;
    public int totalVotes;

    public static final class Answer extends RecordTag {
        private final boolean my;
        private final byte[] option;
        private final String text;
        private final int votes;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof Answer) == false) goto L14;
            Answer r32 = (Answer) r3;
            if (this.my == r32.my) goto L7;
            return false;
        L7:
            if (this.votes == r32.votes) goto L9;
            return false;
        L9:
            if (Objects.equals(this.text, r32.text) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.option, r32.option) == false) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.text, this.option, Integer.valueOf(this.votes), Boolean.valueOf(this.my)};
        }

        public Answer(String r1, byte[] r2, int r3, boolean r4) {
            this.text = r1;
            this.option = r2;
            this.votes = r3;
            this.my = r4;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return ApiWrap$Poll$Answer$$ExternalSyntheticRecord0.m(this.my, this.votes, this.text, this.option);
        }

        public boolean my() {
            return this.my;
        }

        public String text() {
            return this.text;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), Answer.class, "text;option;votes;my");
        }

        public int votes() {
            return this.votes;
        }
    }

    public ApiWrap$Poll() {
        this.id = 0;
        this.totalVotes = 0;
        this.closed = false;
        this.answers = new ArrayList();
    }
}
