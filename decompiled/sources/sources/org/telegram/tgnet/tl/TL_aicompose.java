package org.telegram.tgnet.tl;

import android.text.TextUtils;
import java.util.ArrayList;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.Vector;

/* loaded from: classes6.dex */
public class TL_aicompose {

    public static class AiComposeTone extends TLObject {
        public long emoji_id;
        public String title;

        public AiComposeTone() {
        }

        public static AiComposeTone TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (AiComposeTone) TLObject.TLdeserialize(AiComposeTone.class, fromConstructor(r3), r2, r3, r4);
        }

        private static AiComposeTone fromConstructor(int r1) {
            if (r1 == (-1683135468)) goto L11;
            if (r1 == (-805945687)) goto L9;
            return null;
        L9:
            return new TL_aiComposeTone();
        L11:
            return new TL_aiComposeToneDefault();
        }
    }

    public static class InputAiComposeTone extends TLObject {
        public InputAiComposeTone() {
        }

        public static InputAiComposeTone TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (InputAiComposeTone) TLObject.TLdeserialize(InputAiComposeTone.class, fromConstructor(r3), r2, r3, r4);
        }

        public static boolean equals(InputAiComposeTone r6, InputAiComposeTone r7) {
            if (r6 != null) goto L7;
            if (r7 != null) goto L7;
            return true;
        L7:
            if (r6 == null) goto L43;
            if (r7 == null) goto L43;
            if ((r6 instanceof inputAiComposeToneDefault) == false) goto L19;
            if ((r7 instanceof inputAiComposeToneDefault) == true) goto L15;
        L17:
            return false;
        L15:
            if (TextUtils.equals(((inputAiComposeToneDefault) r6).tone, ((inputAiComposeToneDefault) r7).tone) == false) goto L17;
            return true;
        L19:
            if ((r6 instanceof inputAiComposeToneID) == false) goto L29;
            if ((r7 instanceof inputAiComposeToneID) == false) goto L27;
            inputAiComposeToneID r62 = (inputAiComposeToneID) r6;
            inputAiComposeToneID r72 = (inputAiComposeToneID) r7;
            if (r62.id != r72.id) goto L27;
            if (r62.access_hash != r72.access_hash) goto L27;
            return true;
        L27:
            return false;
        L29:
            if ((r6 instanceof inputAiComposeToneSlug) == false) goto L37;
            if ((r7 instanceof inputAiComposeToneSlug) == true) goto L33;
        L35:
            return false;
        L33:
            if (TextUtils.equals(((inputAiComposeToneSlug) r6).slug, ((inputAiComposeToneSlug) r7).slug) == false) goto L35;
            return true;
        L37:
            if ((r6 instanceof inputAiComposeToneSingleUse) == false) goto L43;
            if ((r7 instanceof inputAiComposeToneSingleUse) == false) goto L43;
            if (TextUtils.equals(((inputAiComposeToneSingleUse) r6).custom_prompt, ((inputAiComposeToneSingleUse) r7).custom_prompt) == false) goto L43;
            return true;
        L43:
            return false;
        }

        public static InputAiComposeTone from(AiComposeTone r3) {
            if ((r3 instanceof TL_aiComposeTone) == false) goto L7;
            inputAiComposeToneID r0 = new inputAiComposeToneID();
            TL_aiComposeTone r32 = (TL_aiComposeTone) r3;
            r0.id = r32.id;
            r0.access_hash = r32.access_hash;
            return r0;
        L7:
            if ((r3 instanceof TL_aiComposeToneDefault) == false) goto L10;
            inputAiComposeToneDefault r02 = new inputAiComposeToneDefault();
            r02.tone = ((TL_aiComposeToneDefault) r3).tone;
            return r02;
        L10:
            return null;
        }

        private static InputAiComposeTone fromConstructor(int r0) {
            switch(r0) {
                case 125026432: goto L12;
                case 235681199: goto L10;
                case 530584407: goto L8;
                case 535407039: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new inputAiComposeToneDefault();
        L8:
            return new inputAiComposeToneSlug();
        L10:
            return new inputAiComposeToneSingleUse();
        L12:
            return new inputAiComposeToneID();
        }

        public static inputAiComposeToneDefault fromDefault(String r1) {
            inputAiComposeToneDefault r0 = new inputAiComposeToneDefault();
            r0.tone = r1;
            return r0;
        }
    }

    public static class TL_aiComposeTone extends AiComposeTone {
        public static final int constructor = -805945687;
        public long access_hash;
        public long author_id;
        public boolean creator;
        public aiComposeToneExample example_english;
        public int flags;
        public long id;
        public int installs_count;
        public String prompt;
        public String slug;

        public TL_aiComposeTone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.creator = TLObject.hasFlag(r0, 1);
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
            this.slug = r3.readString(r4);
            this.title = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.emoji_id = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L9;
            this.prompt = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.installs_count = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.author_id = r3.readInt64(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L18;
            this.example_english = aiComposeToneExample.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.creator);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.id);
            r4.writeInt64(this.access_hash);
            r4.writeString(this.slug);
            r4.writeString(this.title);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            r4.writeInt64(this.emoji_id);
        L6:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L9;
            r4.writeString(this.prompt);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r4.writeInt32(this.installs_count);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r4.writeInt64(this.author_id);
        L15:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L18;
            this.example_english.serializeToStream(r4);
            return;
        }
    }

    public static class TL_aiComposeToneDefault extends AiComposeTone {
        public static final int constructor = -1683135468;
        public String tone;

        public TL_aiComposeToneDefault() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.tone = r3.readString(r4);
            this.emoji_id = r3.readInt64(r4);
            this.title = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.tone);
            r3.writeInt64(this.emoji_id);
            r3.writeString(this.title);
        }
    }

    public static class TL_tones extends Tones {
        public static final int constructor = 1822232318;
        public long hash;
        public ArrayList<AiComposeTone> tones;

        public TL_tones() {
            this.tones = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.hash = r3.readInt64(r4);
            this.tones = Vector.deserialize(r3, new TL_aicompose$TL_tones$$ExternalSyntheticLambda0(), r4);
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
            Vector.serialize(r3, this.tones);
            Vector.serialize(r3, this.users);
        }
    }

    public static class TL_tonesNotModified extends Tones {
        public static final int constructor = -1040948989;

        public TL_tonesNotModified() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class Tones extends TLObject {
        public ArrayList<TLRPC.User> users;

        public Tones() {
            this.users = new ArrayList();
        }

        public static Tones TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (Tones) TLObject.TLdeserialize(Tones.class, fromConstructor(r3), r2, r3, r4);
        }

        private static Tones fromConstructor(int r1) {
            if (r1 == (-1040948989)) goto L11;
            if (r1 == 1822232318) goto L9;
            return null;
        L9:
            return new TL_tones();
        L11:
            return new TL_tonesNotModified();
        }
    }

    public static class aiComposeToneExample extends TLObject {
        public static final int constructor = -237623060;
        public TLRPC.TL_textWithEntities from;
        public TLRPC.TL_textWithEntities to;

        public aiComposeToneExample() {
        }

        public static aiComposeToneExample TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-237623060)) goto L5;
            aiComposeToneExample r0 = new aiComposeToneExample();
        L7:
            return (aiComposeToneExample) TLObject.TLdeserialize(aiComposeToneExample.class, r0, r2, r3, r4);
        L5:
            r0 = null;
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.from = TLRPC.TL_textWithEntities.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.to = TLRPC.TL_textWithEntities.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.from.serializeToStream(r2);
            this.to.serializeToStream(r2);
        }
    }

    public static class createTone extends TLMethod<AiComposeTone> {
        public static final int constructor = 1252538643;
        public boolean display_author;
        public long emoji_id;
        public int flags;
        public String prompt;
        public String title;

        public createTone() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public AiComposeTone deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return AiComposeTone.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.display_author);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.emoji_id);
            r4.writeString(this.title);
            r4.writeString(this.prompt);
        }
    }

    public static class deleteTone extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -583454358;
        public InputAiComposeTone tone;

        public deleteTone() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.tone.serializeToStream(r2);
        }
    }

    public static class getTone extends TLMethod<Tones> {
        public static final int constructor = -1293370877;
        public InputAiComposeTone tone;

        public getTone() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Tones deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Tones.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.tone.serializeToStream(r2);
        }
    }

    public static class getToneExample extends TLMethod<aiComposeToneExample> {
        public static final int constructor = -776688876;
        public int num;
        public InputAiComposeTone tone;

        public getToneExample() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public aiComposeToneExample deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return aiComposeToneExample.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.tone.serializeToStream(r2);
            r2.writeInt32(this.num);
        }
    }

    public static class getTones extends TLMethod<Tones> {
        public static final int constructor = -1412066815;
        public long hash;

        public getTones() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public Tones deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return Tones.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.hash);
        }
    }

    public static class inputAiComposeToneDefault extends InputAiComposeTone {
        public static final int constructor = 535407039;
        public String tone;

        public inputAiComposeToneDefault() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.tone = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.tone);
        }
    }

    public static class inputAiComposeToneID extends InputAiComposeTone {
        public static final int constructor = 125026432;
        public long access_hash;
        public long id;

        public inputAiComposeToneID() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.id = r3.readInt64(r4);
            this.access_hash = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.id);
            r3.writeInt64(this.access_hash);
        }
    }

    public static class inputAiComposeToneSingleUse extends InputAiComposeTone {
        public static final int constructor = 235681199;
        public String custom_prompt;

        public inputAiComposeToneSingleUse() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.custom_prompt = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.custom_prompt);
        }
    }

    public static class inputAiComposeToneSlug extends InputAiComposeTone {
        public static final int constructor = 530584407;
        public String slug;

        public inputAiComposeToneSlug() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.slug = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.slug);
        }
    }

    public static class saveTone extends TLMethod<TLRPC.Bool> {
        public static final int constructor = 394447793;
        public InputAiComposeTone tone;
        public boolean unsave;

        public saveTone() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.Bool deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.tone.serializeToStream(r2);
            r2.writeBool(this.unsave);
        }
    }

    public static class updateTone extends TLMethod<AiComposeTone> {
        public static final int constructor = -1875128487;
        public boolean display_author;
        public long emoji_id;
        public int flags;
        public String prompt;
        public String title;
        public InputAiComposeTone tone;

        public updateTone() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public AiComposeTone deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return AiComposeTone.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.tone.serializeToStream(r3);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeBool(this.display_author);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt64(this.emoji_id);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeString(this.title);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r3.writeString(this.prompt);
            return;
        }
    }

    public TL_aicompose() {
    }
}
