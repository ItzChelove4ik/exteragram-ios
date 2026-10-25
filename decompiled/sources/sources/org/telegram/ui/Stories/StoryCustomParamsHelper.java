package org.telegram.ui.Stories;

import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public abstract class StoryCustomParamsHelper {

    public static class Params_v1 extends TLObject {
        int flags;
        final TL_stories.StoryItem storyItem;

        private Params_v1(TL_stories.StoryItem r4) {
            int r0 = 0;
            this.flags = 0;
            this.storyItem = r4;
            boolean r1 = r4.translated;
            this.flags = r1 ? 1 : 0;
            if (r4.detectedLng == null) goto L5;
            int r2 = 2;
        L6:
            int r12 = (r1 ? 1 : 0) + r2;
            this.flags = r12;
            if (r4.translatedText == null) goto L9;
            int r22 = 4;
        L10:
            int r13 = r12 + r22;
            this.flags = r13;
            if (r4.translatedLng == null) goto L13;
            r0 = 8;
        L13:
            this.flags = r13 + r0;
            return;
        L9:
            r22 = 0;
            goto L10
        L5:
            r2 = 0;
            goto L6
        }

        public /* synthetic */ Params_v1(TL_stories.StoryItem r1, StoryCustomParamsHelperIA r2) {
            this(r1);
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            boolean r0 = true;
            int r1 = r5.readInt32(true);
            this.flags = r1;
            TL_stories.StoryItem r2 = this.storyItem;
            if ((r1 & 1) != 0) goto L6;
            r0 = false;
        L6:
            r2.translated = r0;
            if ((r1 & 2) == 0) goto L10;
            r2.detectedLng = r5.readString(r6);
        L10:
            if ((this.flags & 4) == 0) goto L13;
            TL_stories.StoryItem r02 = this.storyItem;
            r02.translatedText = TLRPC.TL_textWithEntities.TLdeserialize(r5, r5.readInt32(r6), r6);
        L13:
            if ((this.flags & 8) == 0) goto L16;
            TL_stories.StoryItem r4 = this.storyItem;
            r4.translatedLng = r5.readString(r6);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(1);
            r2.writeInt32(this.flags);
            if ((this.flags & 2) == 0) goto L6;
            r2.writeString(this.storyItem.detectedLng);
        L6:
            if ((this.flags & 4) == 0) goto L9;
            this.storyItem.translatedText.serializeToStream(r2);
        L9:
            if ((this.flags & 8) == 0) goto L12;
            r2.writeString(this.storyItem.translatedLng);
            return;
        }
    }

    public static boolean isEmpty(TL_stories.StoryItem r1) {
        if (r1.detectedLng == null) goto L5;
        return false;
    L5:
        if (r1.translatedLng == null) goto L7;
        return false;
    L7:
        if (r1.translated == false) goto L9;
        return false;
    L9:
        if (r1.translatedText != null) goto L16;
        return true;
    L16:
        return false;
    }

    public static void readLocalParams(TL_stories.StoryItem r3, NativeByteBuffer r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r1 = r4.readInt32(true);
        if (r1 != 1) goto L8;
        new Params_v1(r3, null).readParams(r4, true);
        return;
    L8:
        MathProcessor$$ExternalSyntheticBUOutline0.m("(story) can't read params version = ", r1);
    }

    public static NativeByteBuffer writeLocalParams(TL_stories.StoryItem r3) {
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
