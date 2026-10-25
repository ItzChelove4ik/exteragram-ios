package org.telegram.messenger.utils.tlutils;

import android.text.TextUtils;
import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MediaDataController;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes.dex */
public abstract class TlUtils {
    public static /* synthetic */ int $r8$lambda$fWLjT7cEZkSDOEuup9eSQPcBPzY(TLRPC.PollAnswer r2, TLRPC.PollAnswer r3) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(r2.shuffle_hash, r3.shuffle_hash);
    }

    public static TLRPC.GroupCall applyGroupCallUpdate(TLRPC.GroupCall r2, TLRPC.GroupCall r3) {
        if ((r3 instanceof TLRPC.TL_groupCall) == true) goto L5;
    L9:
        return r3;
    L5:
        if ((r2 instanceof TLRPC.TL_groupCall) == false) goto L9;
        TLRPC.TL_groupCall r0 = (TLRPC.TL_groupCall) r3;
        if (r0.min == false) goto L9;
        TLRPC.TL_groupCall r22 = (TLRPC.TL_groupCall) r2;
        r0.can_change_join_muted = r22.can_change_join_muted;
        r0.can_start_video = r22.can_start_video;
        r0.creator = r22.creator;
        r0.can_change_messages_enabled = r22.can_change_messages_enabled;
        goto L9
    }

    public static void calculateAnswerShuffleHash(TLRPC.Poll r8, long r9) {
        if (r8 == null) goto L26;
        int r0 = r8.answers.size();
        int r1 = 0;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L8;
        r8.answers.get(r2).unshuffled_index = r2;
        r2 = r2 + 1;
        goto L5
    L8:
        if (r8.creator == false) goto L10;
        return;
    L10:
        if (r8.shuffle_answers == false) goto L25;
        CRC32 r02 = new CRC32();
        int r22 = r8.answers.size();
    L12:
        if (r1 >= r22) goto L18;
        TLRPC.PollAnswer r3 = r8.answers.get(r1);
        if (r3.option == null) goto L17;
        r02.reset();
        String r4 = Long.toString(r9);
        Charset r5 = StandardCharsets.UTF_8;
        r02.update(r4.getBytes(r5));
        r02.update(r3.option);
        r02.update(Long.toString(r8.id).getBytes(r5));
        r3.shuffle_hash = r02.getValue();
    L17:
        r1 = r1 + 1;
        goto L12
    L18:
        ArrayList<TLRPC.PollAnswer> r92 = new ArrayList(r8.answers);
        r8.shuffled_answers = r92;
        r92.sort(new TlUtils$$ExternalSyntheticLambda0());
        return;
    L25:
        return;
    }

    public static <T> ArrayList<T> findAllInstances(List<?> r3, Class<T> r4) {
        ArrayList<T> r0 = new ArrayList();
        if (r3 == null) goto L12;
        if (r4 == null) goto L12;
        Iterator<?> r32 = r3.iterator();
    L8:
        if (r32.hasNext() == false) goto L12;
        Object r1 = r32.next();
        if (r4.isInstance(r1) == false) goto L8;
        r0.add(r4.cast(r1));
    L12:
        return r0;
    }

    public static <T> T findFirstInstance(List<?> r3, Class<T> r4) {
        if (r3 == null) goto L13;
        if (r4 == null) goto L13;
        Iterator<?> r32 = r3.iterator();
    L8:
        if (r32.hasNext() == false) goto L13;
        Object r1 = r32.next();
        if (r4.isInstance(r1) == false) goto L8;
        return r4.cast(r1);
    L13:
        return null;
    }

    public static TLRPC.Document getGiftDocument(TL_stars.StarGift r5) {
        TLRPC.Document r0 = r5.sticker;
        ArrayList<TL_stars.StarGiftAttribute> r52 = r5.attributes;
        if (r52 == null) goto L11;
        if (r0 != null) goto L11;
        int r1 = r52.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        TL_stars.StarGiftAttribute r3 = r52.get(r2);
        r2 = r2 + 1;
        TL_stars.StarGiftAttribute r32 = r3;
        if ((r32 instanceof TL_stars.starGiftAttributeModel) == false) goto L6;
        return ((TL_stars.starGiftAttributeModel) r32).document;
    L11:
        return r0;
    }

    public static TLRPC.Document getGiftDocumentPattern(TL_stars.StarGift r5) {
        TLRPC.Document r0 = r5.sticker;
        ArrayList<TL_stars.StarGiftAttribute> r52 = r5.attributes;
        if (r52 == null) goto L11;
        if (r0 != null) goto L11;
        int r1 = r52.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        TL_stars.StarGiftAttribute r3 = r52.get(r2);
        r2 = r2 + 1;
        TL_stars.StarGiftAttribute r32 = r3;
        if ((r32 instanceof TL_stars.starGiftAttributePattern) == false) goto L6;
        return ((TL_stars.starGiftAttributePattern) r32).document;
    L11:
        return r0;
    }

    public static TLRPC.InputPeer getInputPeerFromSendMessageRequest(TLObject r1) {
        if ((r1 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        return ((TLRPC.TL_messages_sendMessage) r1).peer;
    L7:
        if ((r1 instanceof TLRPC.TL_messages_sendMedia) == false) goto L11;
        return ((TLRPC.TL_messages_sendMedia) r1).peer;
    L11:
        if ((r1 instanceof TL_ephemeral.TL_sendMessage) == false) goto L15;
        return ((TL_ephemeral.TL_sendMessage) r1).peer;
    L15:
        if ((r1 instanceof TLRPC.TL_messages_sendInlineBotResult) == false) goto L19;
        return ((TLRPC.TL_messages_sendInlineBotResult) r1).peer;
    L19:
        if ((r1 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L23;
        return ((TLRPC.TL_messages_forwardMessages) r1).to_peer;
    L23:
        if ((r1 instanceof TLRPC.TL_messages_sendMultiMedia) == true) goto L25;
        return null;
    L25:
        return ((TLRPC.TL_messages_sendMultiMedia) r1).peer;
    }

    public static TLRPC.InputReplyTo getInputReplyToFromSendMessageRequest(TLObject r1) {
        if ((r1 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        return ((TLRPC.TL_messages_sendMessage) r1).reply_to;
    L7:
        if ((r1 instanceof TLRPC.TL_messages_sendMedia) == false) goto L11;
        return ((TLRPC.TL_messages_sendMedia) r1).reply_to;
    L11:
        if ((r1 instanceof TL_ephemeral.TL_sendMessage) == false) goto L15;
        return ((TL_ephemeral.TL_sendMessage) r1).reply_to;
    L15:
        if ((r1 instanceof TLRPC.TL_messages_sendInlineBotResult) == false) goto L19;
        return ((TLRPC.TL_messages_sendInlineBotResult) r1).reply_to;
    L19:
        if ((r1 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L23;
        return ((TLRPC.TL_messages_forwardMessages) r1).reply_to;
    L23:
        if ((r1 instanceof TLRPC.TL_messages_sendMultiMedia) == true) goto L25;
        return null;
    L25:
        return ((TLRPC.TL_messages_sendMultiMedia) r1).reply_to;
    }

    public static String getMessageFromSendMessageRequest(TLObject r5) {
        if ((r5 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        return ((TLRPC.TL_messages_sendMessage) r5).message;
    L7:
        if ((r5 instanceof TLRPC.TL_messages_sendMedia) == false) goto L11;
        return ((TLRPC.TL_messages_sendMedia) r5).message;
    L11:
        if ((r5 instanceof TL_ephemeral.TL_sendMessage) == false) goto L15;
        return ((TL_ephemeral.TL_sendMessage) r5).message;
    L15:
        if ((r5 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L22;
        ArrayList<TLRPC.TL_inputSingleMedia> r52 = ((TLRPC.TL_messages_sendMultiMedia) r5).multi_media;
        int r0 = r52.size();
        int r2 = 0;
    L17:
        if (r2 >= r0) goto L22;
        TLRPC.TL_inputSingleMedia r3 = r52.get(r2);
        r2 = r2 + 1;
        TLRPC.TL_inputSingleMedia r32 = r3;
        if (TextUtils.isEmpty(r32.message) == true) goto L17;
        return r32.message;
    L22:
        return null;
    }

    public static long getOrCalculateRandomIdFromSendMessageRequest(TLObject r6) {
        if ((r6 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        return ((TLRPC.TL_messages_sendMessage) r6).random_id;
    L7:
        if ((r6 instanceof TLRPC.TL_messages_sendMedia) == false) goto L11;
        return ((TLRPC.TL_messages_sendMedia) r6).random_id;
    L11:
        if ((r6 instanceof TL_ephemeral.TL_sendMessage) == false) goto L15;
        return ((TL_ephemeral.TL_sendMessage) r6).random_id;
    L15:
        if ((r6 instanceof TLRPC.TL_messages_sendInlineBotResult) == true) goto L17;
        int r1 = 0;
        long r2 = 0;
        if ((r6 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L25;
        ArrayList<Long> r62 = ((TLRPC.TL_messages_forwardMessages) r6).random_id;
        int r0 = r62.size();
    L21:
        if (r1 >= r0) goto L23;
        Long r4 = r62.get(r1);
        r1 = r1 + 1;
        r2 = MediaDataController.calcHash(r2, r4.longValue());
        goto L21
    L23:
        return r2;
    L25:
        if ((r6 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L29;
        ArrayList<TLRPC.TL_inputSingleMedia> r63 = ((TLRPC.TL_messages_sendMultiMedia) r6).multi_media;
        int r02 = r63.size();
    L27:
        if (r1 >= r02) goto L29;
        TLRPC.TL_inputSingleMedia r42 = r63.get(r1);
        r1 = r1 + 1;
        r2 = MediaDataController.calcHash(r2, r42.random_id);
    L29:
        return r2;
    L17:
        return ((TLRPC.TL_messages_sendInlineBotResult) r6).random_id;
    }

    public static String getThemeEmoticonOrGiftTitle(TLRPC.ChatTheme r1) {
        if ((r1 instanceof TLRPC.TL_chatTheme) == false) goto L7;
        return ((TLRPC.TL_chatTheme) r1).emoticon;
    L7:
        if ((r1 instanceof TLRPC.TL_chatThemeUniqueGift) == true) goto L9;
        return null;
    L9:
        return ((TLRPC.TL_chatThemeUniqueGift) r1).gift.title;
    }

    public static boolean isInstance(Object r4, Class<?>... r5) {
        if (r4 == null) goto L13;
        if (r5 == null) goto L13;
        int r1 = r5.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L13;
        if (r5[r2].isInstance(r4) == true) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    public static void setInputReplyToFromSendMessageRequest(TLObject r1, TLRPC.InputReplyTo r2) {
        if ((r1 instanceof TLRPC.TL_messages_sendMessage) == false) goto L7;
        TLRPC.TL_messages_sendMessage r12 = (TLRPC.TL_messages_sendMessage) r1;
        r12.reply_to = r2;
        r12.flags |= 1;
        return;
    L7:
        if ((r1 instanceof TLRPC.TL_messages_sendMedia) == false) goto L11;
        TLRPC.TL_messages_sendMedia r13 = (TLRPC.TL_messages_sendMedia) r1;
        r13.reply_to = r2;
        r13.flags |= 1;
        return;
    L11:
        if ((r1 instanceof TL_ephemeral.TL_sendMessage) == false) goto L15;
        TL_ephemeral.TL_sendMessage r14 = (TL_ephemeral.TL_sendMessage) r1;
        r14.reply_to = r2;
        r14.flags |= 32;
        return;
    L15:
        if ((r1 instanceof TLRPC.TL_messages_sendInlineBotResult) == false) goto L19;
        TLRPC.TL_messages_sendInlineBotResult r15 = (TLRPC.TL_messages_sendInlineBotResult) r1;
        r15.reply_to = r2;
        r15.flags |= 1;
        return;
    L19:
        if ((r1 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L23;
        TLRPC.TL_messages_forwardMessages r16 = (TLRPC.TL_messages_forwardMessages) r1;
        r16.reply_to = r2;
        r16.flags |= 1;
        return;
    L23:
        if ((r1 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L26;
        TLRPC.TL_messages_sendMultiMedia r17 = (TLRPC.TL_messages_sendMultiMedia) r1;
        r17.reply_to = r2;
        r17.flags |= 1;
        return;
    }

    public static boolean tlEquals(TLObject r9, TLObject r10) {
        if (r9 != r10) goto L6;
        return true;
    L6:
        if (r9 == null) goto L28;
        if (r10 == null) goto L28;
        int r2 = r9.getObjectSize();
        int r3 = r10.getObjectSize();
        if (r2 == r3) goto L29;
        return false;
    L29:
        NativeByteBuffer r4 = new NativeByteBuffer(r2);     // Catch: Throwable -> L19
        r9.serializeToStream(r4);     // Catch: Throwable -> L19
        r4.rewind();     // Catch: Throwable -> L19
        NativeByteBuffer r92 = new NativeByteBuffer(r3);     // Catch: Throwable -> L19
        r10.serializeToStream(r92);     // Catch: Throwable -> L19
        r92.rewind();     // Catch: Throwable -> L19
    L14:
        if (r2 < 8) goto L21;
        if (r4.readInt64(true) != r92.readInt64(true)) goto L17;
        r2 = r2 - 8;
        goto L14
    L17:
        return false;
    L21:
        if (r2 <= 0) goto L26;
        if (r4.readByte(true) != r92.readByte(true)) goto L24;
        r2 = r2 - 1;
        goto L21
    L24:
        return false;
    L26:
        return true;
    L19:
        th = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(th);
    L28:
        return false;
    }

    public static TLRPC.InputMedia toInputMediaGeo(TLRPC.MessageMedia r4) {
        if ((r4 instanceof TLRPC.TL_messageMediaVenue) == false) goto L6;
        TLRPC.InputMedia r0 = new TLRPC.TL_inputMediaVenue();
        r0.address = r4.address;
        r0.title = r4.title;
        r0.provider = r4.provider;
        r0.venue_id = r4.venue_id;
        r0.venue_type = _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        TLRPC.TL_inputGeoPoint r1 = new TLRPC.TL_inputGeoPoint();
        r0.geo_point = r1;
        TLRPC.GeoPoint r42 = r4.geo;
        r1.lat = r42.lat;
        r1._long = r42._long;
        return r0;
    L6:
        if ((r4 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L13;
        r0 = new TLRPC.TL_inputMediaGeoLive();
        r0.period = r4.period;
        int r12 = r0.flags;
        r0.flags = r12 | 2;
        int r2 = r4.heading;
        if (r2 == 0) goto L10;
        r0.heading = r2;
        r0.flags = r12 | 6;
    L10:
        int r13 = r4.proximity_notification_radius;
        if (r13 == 0) goto L14;
        r0.proximity_notification_radius = r13;
        r0.flags |= 8;
        goto L14
    L13:
        r0 = new TLRPC.TL_inputMediaGeoPoint();
        goto L14
    }
}
