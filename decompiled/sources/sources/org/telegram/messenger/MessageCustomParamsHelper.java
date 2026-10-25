package org.telegram.messenger;

import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes.dex */
public class MessageCustomParamsHelper {

    public static class Params_v1 extends TLObject {
        private static final int VERSION = 1;
        int flags;
        final TLRPC.Message message;

        private Params_v1(TLRPC.Message r8) {
            boolean r0 = false;
            this.flags = 0;
            this.message = r8;
            if (r8.voiceTranscription == null) goto L5;
            int r1 = 1;
        L6:
            this.flags = r1;
            if (r8.voiceTranscriptionForce == false) goto L9;
            int r3 = 2;
        L10:
            int r12 = r1 | r3;
            this.flags = r12;
            if (r8.originalLanguage == null) goto L13;
            int r32 = 4;
        L14:
            int r13 = r12 | r32;
            this.flags = r13;
            if (r8.translatedToLanguage == null) goto L17;
            int r33 = 8;
        L18:
            int r14 = r13 | r33;
            this.flags = r14;
            if (r8.translatedText == null) goto L21;
            int r34 = 16;
        L22:
            int r15 = r14 | r34;
            this.flags = r15;
            if (r8.translatedPoll == null) goto L25;
            int r35 = 32;
        L26:
            int r16 = r15 | r35;
            this.flags = r16;
            if (r8.errorAllowedPriceStars == 0) goto L29;
            int r36 = 64;
        L30:
            int r17 = r16 | r36;
            this.flags = r17;
            if (r8.errorNewPriceStars == 0) goto L33;
            int r37 = 128;
        L34:
            int r18 = r17 | r37;
            this.flags = r18;
            if (r8.translatedVoiceTranscription == null) goto L37;
            int r38 = 256;
        L38:
            int r19 = r18 | r38;
            this.flags = r19;
            if (r8.summaryText == null) goto L41;
            boolean r39 = true;
        L42:
            int r110 = TLObject.setFlag(r19, 1024, r39);
            this.flags = r110;
            if (r8.translatedSummaryText == null) goto L45;
            boolean r310 = true;
        L46:
            int r111 = TLObject.setFlag(r110, 2048, r310);
            this.flags = r111;
            if (r8.translatedSummaryLanguage == null) goto L49;
            boolean r311 = true;
        L50:
            int r112 = TLObject.setFlag(r111, 4096, r311);
            this.flags = r112;
            if (r8.translatedRichMessage == null) goto L53;
            r0 = true;
        L53:
            this.flags = TLObject.setFlag(r112, 8192, r0);
            return;
        L49:
            r311 = false;
            goto L50
        L45:
            r310 = false;
            goto L46
        L41:
            r39 = false;
            goto L42
        L37:
            r38 = 0;
            goto L38
        L33:
            r37 = 0;
            goto L34
        L29:
            r36 = 0;
            goto L30
        L25:
            r35 = 0;
            goto L26
        L21:
            r34 = 0;
            goto L22
        L17:
            r33 = 0;
            goto L18
        L13:
            r32 = 0;
            goto L14
        L9:
            r3 = 0;
            goto L10
        L5:
            r1 = 0;
            goto L6
        }

        public /* synthetic */ Params_v1(TLRPC.Message r1, MessageCustomParamsHelperIA r2) {
            this(r1);
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            boolean r0 = true;
            int r1 = r6.readInt32(true);
            this.flags = r1;
            if ((r1 & 1) == 0) goto L5;
            this.message.voiceTranscription = r6.readString(r7);
        L5:
            TLRPC.Message r12 = this.message;
            int r2 = this.flags;
            if ((r2 & 2) == 0) goto L8;
            boolean r3 = true;
        L9:
            r12.voiceTranscriptionForce = r3;
            if ((r2 & 512) != 0) goto L13;
            r0 = false;
        L13:
            r12.summarizedOpen = r0;
            r12.voiceTranscriptionOpen = r6.readBool(r7);
            this.message.voiceTranscriptionFinal = r6.readBool(r7);
            this.message.voiceTranscriptionRated = r6.readBool(r7);
            this.message.voiceTranscriptionId = r6.readInt64(r7);
            this.message.premiumEffectWasPlayed = r6.readBool(r7);
            if ((this.flags & 4) == 0) goto L17;
            this.message.originalLanguage = r6.readString(r7);
        L17:
            if ((this.flags & 8) == 0) goto L20;
            this.message.translatedToLanguage = r6.readString(r7);
        L20:
            if ((this.flags & 16) == 0) goto L23;
            this.message.translatedText = TLRPC.TL_textWithEntities.TLdeserialize(r6, r6.readInt32(r7), r7);
        L23:
            if ((this.flags & 32) == 0) goto L26;
            this.message.translatedPoll = TranslateController.PollText.TLdeserialize(r6, r6.readInt32(r7), r7);
        L26:
            if ((this.flags & 64) == 0) goto L29;
            this.message.errorAllowedPriceStars = r6.readInt64(r7);
        L29:
            if ((this.flags & 128) == 0) goto L32;
            this.message.errorNewPriceStars = r6.readInt64(r7);
        L32:
            if ((this.flags & 256) == 0) goto L35;
            this.message.translatedVoiceTranscription = TLRPC.TL_textWithEntities.TLdeserialize(r6, r6.readInt32(r7), r7);
        L35:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L38;
            this.message.summaryText = TLRPC.TL_textWithEntities.TLdeserialize(r6, r6.readInt32(r7), r7);
        L38:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L41;
            this.message.translatedSummaryText = TLRPC.TL_textWithEntities.TLdeserialize(r6, r6.readInt32(r7), r7);
        L41:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L44;
            this.message.translatedSummaryLanguage = r6.readString(r7);
        L44:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L47;
            this.message.translatedRichMessage = TL_iv.RichMessage.TLdeserialize(r6, r6.readInt32(r7), r7);
            return;
        L47:
            return;
        L8:
            r3 = false;
            goto L9
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(1);
            TLRPC.Message r1 = this.message;
            boolean r2 = r1.voiceTranscriptionForce;
            int r3 = this.flags;
            if (r2 == false) goto L5;
            int r22 = r3 | 2;
        L6:
            this.flags = r22;
            if (r1.summarizedOpen == false) goto L9;
            int r12 = r22 | 512;
        L10:
            this.flags = r12;
            r5.writeInt32(r12);
            if ((1 & this.flags) == 0) goto L13;
            r5.writeString(this.message.voiceTranscription);
        L13:
            r5.writeBool(this.message.voiceTranscriptionOpen);
            r5.writeBool(this.message.voiceTranscriptionFinal);
            r5.writeBool(this.message.voiceTranscriptionRated);
            r5.writeInt64(this.message.voiceTranscriptionId);
            r5.writeBool(this.message.premiumEffectWasPlayed);
            if ((this.flags & 4) == 0) goto L17;
            r5.writeString(this.message.originalLanguage);
        L17:
            if ((this.flags & 8) == 0) goto L20;
            r5.writeString(this.message.translatedToLanguage);
        L20:
            if ((this.flags & 16) == 0) goto L23;
            this.message.translatedText.serializeToStream(r5);
        L23:
            if ((this.flags & 32) == 0) goto L26;
            this.message.translatedPoll.serializeToStream(r5);
        L26:
            if ((this.flags & 64) == 0) goto L29;
            r5.writeInt64(this.message.errorAllowedPriceStars);
        L29:
            if ((this.flags & 128) == 0) goto L32;
            r5.writeInt64(this.message.errorNewPriceStars);
        L32:
            if ((this.flags & 256) == 0) goto L35;
            this.message.translatedVoiceTranscription.serializeToStream(r5);
        L35:
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L38;
            this.message.summaryText.serializeToStream(r5);
        L38:
            if (TLObject.hasFlag(this.flags, 2048) == false) goto L41;
            this.message.translatedSummaryText.serializeToStream(r5);
        L41:
            if (TLObject.hasFlag(this.flags, 4096) == false) goto L44;
            r5.writeString(this.message.translatedSummaryLanguage);
        L44:
            if (TLObject.hasFlag(this.flags, 8192) == false) goto L47;
            this.message.translatedRichMessage.serializeToStream(r5);
            return;
        L47:
            return;
        L9:
            r12 = r22 & (-513);
            goto L10
        L5:
            r22 = r3 & (-3);
            goto L6
        }
    }

    public MessageCustomParamsHelper() {
    }

    public static void copyParams(TLRPC.Message r2, TLRPC.Message r3) {
        r3.voiceTranscription = r2.voiceTranscription;
        r3.voiceTranscriptionOpen = r2.voiceTranscriptionOpen;
        r3.voiceTranscriptionFinal = r2.voiceTranscriptionFinal;
        r3.voiceTranscriptionForce = r2.voiceTranscriptionForce;
        r3.voiceTranscriptionRated = r2.voiceTranscriptionRated;
        r3.voiceTranscriptionId = r2.voiceTranscriptionId;
        r3.premiumEffectWasPlayed = r2.premiumEffectWasPlayed;
        r3.originalLanguage = r2.originalLanguage;
        r3.translatedToLanguage = r2.translatedToLanguage;
        r3.translatedPoll = r2.translatedPoll;
        r3.translatedText = r2.translatedText;
        r3.translatedRichMessage = r2.translatedRichMessage;
        r3.errorAllowedPriceStars = r2.errorAllowedPriceStars;
        r3.errorNewPriceStars = r2.errorNewPriceStars;
        r3.translatedVoiceTranscription = r2.translatedVoiceTranscription;
        r3.summarizedOpen = r2.summarizedOpen;
        r3.summaryText = r2.summaryText;
        r3.translatedSummaryText = r2.translatedSummaryText;
        r3.translatedSummaryLanguage = r2.translatedSummaryLanguage;
    }

    public static boolean isEmpty(TLRPC.Message r4) {
        if (r4.voiceTranscription == null) goto L5;
        return false;
    L5:
        if (r4.translatedVoiceTranscription == null) goto L7;
        return false;
    L7:
        if (r4.voiceTranscriptionOpen == false) goto L9;
        return false;
    L9:
        if (r4.summarizedOpen == false) goto L11;
        return false;
    L11:
        if (r4.summaryText == null) goto L13;
        return false;
    L13:
        if (r4.translatedSummaryLanguage == null) goto L15;
        return false;
    L15:
        if (r4.translatedSummaryText == null) goto L17;
        return false;
    L17:
        if (r4.voiceTranscriptionFinal == false) goto L19;
        return false;
    L19:
        if (r4.voiceTranscriptionRated == false) goto L21;
        return false;
    L21:
        if (r4.voiceTranscriptionForce == false) goto L23;
        return false;
    L23:
        if (r4.voiceTranscriptionId == 0) goto L25;
        return false;
    L25:
        if (r4.premiumEffectWasPlayed == false) goto L27;
        return false;
    L27:
        if (r4.originalLanguage == null) goto L29;
        return false;
    L29:
        if (r4.translatedToLanguage == null) goto L31;
        return false;
    L31:
        if (r4.translatedPoll == null) goto L33;
        return false;
    L33:
        if (r4.translatedText == null) goto L35;
        return false;
    L35:
        if (r4.translatedRichMessage == null) goto L37;
        return false;
    L37:
        if (r4.errorAllowedPriceStars == 0) goto L39;
        return false;
    L39:
        if (r4.errorNewPriceStars != 0) goto L61;
        return true;
    L61:
        return false;
    }

    public static void readLocalParams(TLRPC.Message r3, NativeByteBuffer r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r1 = r4.readInt32(true);
        if (r1 != 1) goto L8;
        new Params_v1(r3, null).readParams(r4, true);
        return;
    L8:
        MathProcessor$$ExternalSyntheticBUOutline0.m("can't read params version = ", r1);
    }

    public static NativeByteBuffer writeLocalParams(TLRPC.Message r3) {
        if (isEmpty(r3) == false) goto L5;
        return null;
    L5:
        Params_v1 r0 = new Params_v1(r3, null);
        NativeByteBuffer r32 = new NativeByteBuffer(r0.getObjectSize());     // Catch: Exception -> L8
        r0.serializeToStream(r32);     // Catch: Exception -> L8
        return r32;
    L8:
        e = move-exception;
        e.printStackTrace();
        return null;
    }
}
