package org.telegram.tgnet.tl;

import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public class TL_fragment {

    public static class InputCollectible extends TLObject {
        public InputCollectible() {
        }

        public static InputCollectible TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-1562241884)) goto L5;
            TLObject r0 = new TL_inputCollectiblePhone();
        L10:
            return (InputCollectible) TLObject.TLdeserialize(InputCollectible.class, r0, r2, r3, r4);
        L5:
            if (r3 == (-476815191)) goto L7;
            r0 = null;
            goto L10
        L7:
            r0 = new TL_inputCollectibleUsername();
            goto L10
        }
    }

    public static class TL_collectibleInfo extends TLObject {
        public static final int constructor = 1857945489;
        public long amount;
        public long crypto_amount;
        public String crypto_currency;
        public String currency;
        public int purchase_date;
        public String url;

        public TL_collectibleInfo() {
        }

        public static TL_collectibleInfo TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1857945489 == r3) goto L5;
            TL_collectibleInfo r0 = null;
        L7:
            return (TL_collectibleInfo) TLObject.TLdeserialize(TL_collectibleInfo.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_collectibleInfo();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.purchase_date = r3.readInt32(r4);
            this.currency = r3.readString(r4);
            this.amount = r3.readInt64(r4);
            this.crypto_currency = r3.readString(r4);
            this.crypto_amount = r3.readInt64(r4);
            this.url = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.purchase_date);
            r3.writeString(this.currency);
            r3.writeInt64(this.amount);
            r3.writeString(this.crypto_currency);
            r3.writeInt64(this.crypto_amount);
            r3.writeString(this.url);
        }
    }

    public static class TL_getCollectibleInfo extends TLObject {
        public static final int constructor = -1105295942;
        public InputCollectible collectible;

        public TL_getCollectibleInfo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_collectibleInfo.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.collectible.serializeToStream(r2);
        }
    }

    public static class TL_inputCollectiblePhone extends InputCollectible {
        public static final int constructor = -1562241884;
        public String phone;

        public TL_inputCollectiblePhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.phone = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.phone);
        }
    }

    public static class TL_inputCollectibleUsername extends InputCollectible {
        public static final int constructor = -476815191;
        public String username;

        public TL_inputCollectibleUsername() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.username = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.username);
        }
    }

    public TL_fragment() {
    }
}
