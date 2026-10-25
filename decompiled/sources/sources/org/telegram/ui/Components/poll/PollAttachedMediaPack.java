package org.telegram.ui.Components.poll;

import android.net.Uri;
import android.util.Base64;
import android.util.SparseArray;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLink;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLocation;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaSticker;

/* loaded from: classes7.dex */
public class PollAttachedMediaPack {
    public final SparseArray<PollAttachedMedia> medias;

    public PollAttachedMediaPack() {
        this.medias = new SparseArray();
    }

    public static int findInputMedia(TLRPC.TL_inputMediaPoll r3, TLRPC.InputMedia r4) {
        if (r3.attached_media != r4) goto L7;
        return -2;
    L7:
        if (r3.solution_media != r4) goto L10;
        return -3;
    L10:
        int r0 = r3.poll.answers.size();
        int r1 = 0;
    L11:
        if (r1 >= r0) goto L16;
        if (r3.poll.answers.get(r1).input_media == r4) goto L14;
        r1 = r1 + 1;
        goto L11
    L14:
        return r1;
    L16:
        return -1;
    }

    public static String getAttachPath(TLRPC.Message r0, int r1) {
        if (r0 == null) goto L8;
        SparseArray<String> r02 = r0.pollMediaAttachPaths;
        if (r02 != null) goto L7;
        return null;
    L7:
        return r02.get(r1);
    L8:
        return null;
    }

    public static TLRPC.InputMedia getFirstInputMedia(TLRPC.TL_inputMediaPoll r3) {
        TLRPC.InputMedia r0 = r3.attached_media;
        if (r0 == null) goto L5;
        return r0;
    L5:
        TLRPC.InputMedia r02 = r3.solution_media;
        if (r02 == null) goto L8;
        return r02;
    L8:
        int r03 = r3.poll.answers.size();
        int r1 = 0;
    L9:
        if (r1 >= r03) goto L14;
        TLRPC.InputMedia r2 = r3.poll.answers.get(r1).input_media;
        if (r2 != null) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return r2;
    L14:
        return null;
    }

    public static TLRPC.InputMedia getInputMedia(TLRPC.TL_inputMediaPoll r2, int r3) {
        if (r3 != (-2)) goto L7;
        return r2.attached_media;
    L7:
        if (r3 != (-3)) goto L11;
        return r2.solution_media;
    L11:
        if (r3 >= 0) goto L13;
    L18:
        return null;
    L13:
        if (r3 >= r2.poll.answers.size()) goto L18;
        TLRPC.PollAnswer r22 = r2.poll.answers.get(r3);
        if (r22 == null) goto L18;
        return r22.input_media;
    }

    public static TLRPC.MessageMedia getMedia(TLRPC.TL_messageMediaPoll r2, int r3) {
        if (r3 != (-2)) goto L7;
        return r2.attached_media;
    L7:
        if (r3 != (-3)) goto L11;
        return r2.results.solution_media;
    L11:
        if (r3 >= 0) goto L13;
    L18:
        return null;
    L13:
        if (r3 >= r2.poll.answers.size()) goto L18;
        TLRPC.PollAnswer r22 = r2.poll.answers.get(r3);
        if (r22 == null) goto L18;
        return r22.media;
    }

    public static byte[] getOptionIdQueryParameter(Uri r0, String r1) {
        String r02 = r0.getQueryParameter(r1);     // Catch: Throwable -> L8
        if (r02 == null) goto L6;
        return Base64.decode(r02, 9);
    L6:
        return null;
    L11:
        return null;
    }

    private boolean hasKeyBiggerThan(int r5) {
        int r0 = this.medias.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (this.medias.keyAt(r2) > r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static boolean hasWrongInputMediaTypes(TLRPC.TL_inputMediaPoll r6) {
        TLRPC.InputMedia r0 = getInputMedia(r6, -2);
        if ((r0 instanceof TLRPC.TL_inputMediaUploadedPhoto) == false) goto L5;
    L22:
        return true;
    L5:
        if ((r0 instanceof TLRPC.TL_inputMediaUploadedDocument) == true) goto L22;
        TLRPC.InputMedia r02 = getInputMedia(r6, -3);
        if ((r02 instanceof TLRPC.TL_inputMediaUploadedPhoto) == true) goto L22;
        if ((r02 instanceof TLRPC.TL_inputMediaUploadedDocument) == true) goto L22;
        int r03 = r6.poll.answers.size();
        int r3 = 0;
    L13:
        if (r3 >= r03) goto L21;
        TLRPC.InputMedia r4 = getInputMedia(r6, r3);
        if ((r4 instanceof TLRPC.TL_inputMediaUploadedPhoto) == true) goto L20;
        if ((r4 instanceof TLRPC.TL_inputMediaUploadedDocument) == true) goto L20;
        r3 = r3 + 1;
    L20:
        return true;
    L21:
        return false;
    }

    private void removeAndShiftKeys(int r7) {
        if (r7 < 0) goto L12;
        SparseArray<PollAttachedMedia> r0 = this.medias.clone();
        this.medias.clear();
        int r1 = r0.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L16;
        int r3 = r0.keyAt(r2);
        PollAttachedMedia r4 = r0.valueAt(r2);
        if (r3 >= r7) goto L9;
        this.medias.put(r3, r4);
    L9:
        if (r3 <= r7) goto L11;
        this.medias.put(r3 - 1, r4);
    L11:
        r2 = r2 + 1;
        goto L5
    L16:
        return;
    }

    public static void removeInputMedia(TLRPC.TL_inputMediaPoll r2, int r3) {
        if (r3 != (-2)) goto L7;
        r2.attached_media = null;
        return;
    L7:
        if (r3 != (-3)) goto L10;
        r2.solution_media = null;
        return;
    L10:
        if (r3 >= 0) goto L12;
        return;
    L12:
        if (r3 >= r2.poll.answers.size()) goto L18;
        TLRPC.PollAnswer r22 = r2.poll.answers.get(r3);
        if (r22 == null) goto L19;
        r22.input_media = null;
        return;
    L19:
        return;
    }

    public static void setAttachPath(TLRPC.Message r1, String r2, int r3) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.pollMediaAttachPaths != null) goto L7;
        r1.pollMediaAttachPaths = new SparseArray();
    L7:
        r1.pollMediaAttachPaths.put(r3, r2);
    }

    public static void setInputMedia(TLRPC.TL_inputMediaPoll r2, int r3, TLRPC.InputMedia r4) {
        if (r3 != (-2)) goto L7;
        r2.attached_media = r4;
        return;
    L7:
        if (r3 != (-3)) goto L10;
        r2.solution_media = r4;
        return;
    L10:
        if (r3 >= 0) goto L12;
        return;
    L12:
        if (r3 >= r2.poll.answers.size()) goto L20;
        TLRPC.PollAnswer r0 = r2.poll.answers.get(r3);
        if ((r0 instanceof TLRPC.TL_inputPollAnswer) == false) goto L17;
        r0.input_media = r4;
        return;
    L17:
        TLRPC.TL_inputPollAnswer r1 = new TLRPC.TL_inputPollAnswer();
        r1.input_media = r4;
        r1.text = r0.text;
        r1.media = r0.media;
        r1.option = r0.option;
        r2.poll.answers.set(r3, r1);
        return;
    }

    public static void setMessageMedia(TLRPC.TL_messageMediaPoll r4, int r5, TLRPC.MessageMedia r6) {
        if (r5 != (-2)) goto L7;
        r4.attached_media = r6;
        return;
    L7:
        if (r5 != (-3)) goto L10;
        r4.results.solution_media = r6;
        return;
    L10:
        if (r5 >= 0) goto L12;
        return;
    L12:
        if (r5 >= r4.poll.answers.size()) goto L20;
        TLRPC.PollAnswer r0 = r4.poll.answers.get(r5);
        if ((r0 instanceof TLRPC.TL_inputPollAnswer) == false) goto L17;
        TLRPC.TL_pollAnswer r1 = new TLRPC.TL_pollAnswer();
        r1.text = r0.text;
        r1.option = new byte[]{(byte) (r5 + 48)};
        r1.media = r6;
        r4.poll.answers.set(r5, r1);
        return;
    L17:
        r0.media = r6;
        TLRPC.TL_inputPollAnswer r42 = new TLRPC.TL_inputPollAnswer();
        r42.text = r0.text;
        r42.media = r0.media;
        r42.option = r0.option;
        return;
    }

    public void applyAllQuickMedia(TLRPC.TL_inputMediaPoll r9) {
        int r0 = this.medias.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        int r2 = this.medias.keyAt(r1);
        PollAttachedMedia r3 = this.medias.valueAt(r1);
        if ((r3 instanceof PollAttachedMediaLink) == false) goto L8;
        TLRPC.TL_inputMediaWebPage r4 = new TLRPC.TL_inputMediaWebPage();
        r4.url = ((PollAttachedMediaLink) r3).url;
        r4.optional = true;
        setInputMedia(r9, r2, r4);
    L13:
        r1 = r1 + 1;
        goto L3
    L8:
        if ((r3 instanceof PollAttachedMediaLocation) == false) goto L11;
        setInputMedia(r9, r2, TlUtils.toInputMediaGeo(((PollAttachedMediaLocation) r3).media));
        goto L13
    L11:
        if ((r3 instanceof PollAttachedMediaSticker) == false) goto L13;
        TLRPC.TL_inputMediaDocument r42 = new TLRPC.TL_inputMediaDocument();
        TLRPC.TL_inputDocument r5 = new TLRPC.TL_inputDocument();
        TLRPC.Document r32 = ((PollAttachedMediaSticker) r3).sticker;
        r5.id = r32.id;
        r5.access_hash = r32.access_hash;
        r5.file_reference = r32.file_reference;
        r42.id = r5;
        setInputMedia(r9, r2, r42);
        goto L13
    }

    public void applyAllQuickMedia(TLRPC.TL_messageMediaPoll r7) {
        int r0 = this.medias.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        int r2 = this.medias.keyAt(r1);
        PollAttachedMedia r3 = this.medias.valueAt(r1);
        if ((r3 instanceof PollAttachedMediaLink) == false) goto L8;
        TLRPC.TL_messageMediaWebPage r4 = new TLRPC.TL_messageMediaWebPage();
        TLRPC.TL_webPage r5 = new TLRPC.TL_webPage();
        r4.webpage = r5;
        String r32 = ((PollAttachedMediaLink) r3).url;
        r5.display_url = r32;
        r5.url = r32;
        setMessageMedia(r7, r2, r4);
    L13:
        r1 = r1 + 1;
        goto L3
    L8:
        if ((r3 instanceof PollAttachedMediaLocation) == false) goto L11;
        setMessageMedia(r7, r2, ((PollAttachedMediaLocation) r3).media);
        goto L13
    L11:
        if ((r3 instanceof PollAttachedMediaSticker) == false) goto L13;
        TLRPC.TL_messageMediaDocument r42 = new TLRPC.TL_messageMediaDocument();
        r42.document = ((PollAttachedMediaSticker) r3).sticker;
        setMessageMedia(r7, r2, r42);
        goto L13
    }

    public PollAttachedMedia get(int r1) {
        return this.medias.get(r1);
    }

    public void remove(int r1) {
        this.medias.remove(r1);
    }

    public void removeAnswerAndShift(int r2) {
        if (hasKeyBiggerThan(r2) == false) goto L6;
        removeAndShiftKeys(r2);
        return;
    L6:
        this.medias.remove(r2);
    }

    public void set(int r1, PollAttachedMedia r2) {
        this.medias.put(r1, r2);
    }
}
