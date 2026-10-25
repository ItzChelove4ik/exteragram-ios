package org.telegram.tgnet.tl;

import android.graphics.Path;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLMethod;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes6.dex */
public class TL_bots {

    /* loaded from: classes.dex */
    public static abstract class BotInfo extends TLObject {
        public botAppSettings app_settings;
        public ArrayList<TLRPC.BotCommand> commands;
        public String description;
        public TLRPC.Document description_document;
        public TLRPC.Photo description_photo;
        public int flags;
        public boolean has_preview_medias;
        public BotMenuButton menu_button;
        public String privacy_policy_url;
        public long user_id;
        public botVerifierSettings verifier_settings;
        public int version;

        public BotInfo() {
            this.commands = new ArrayList();
        }

        public static BotInfo TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (BotInfo) TLObject.TLdeserialize(BotInfo.class, fromConstructor(r3), r2, r3, r4);
        }

        private static BotInfo fromConstructor(int r0) {
            switch(r0) {
                case -2109505932: goto L22;
                case -1892676777: goto L20;
                case -1729618630: goto L18;
                case -1154598962: goto L16;
                case -468280483: goto L14;
                case 164583517: goto L12;
                case 460632885: goto L10;
                case 912290611: goto L8;
                case 1300890265: goto L6;
                default: goto L3;
            };
        L3:
            return null;
        L6:
            return new TL_botInfo();
        L8:
            return new TL_botInfo_layer195();
        L10:
            return new TL_botInfo_layer139();
        L12:
            return new TL_botInfo_layer48();
        L14:
            return new TL_botInfo_layer140();
        L16:
            return new TL_botInfoEmpty_layer48();
        L18:
            return new TL_botInfo_layer131();
        L20:
            return new TL_botInfo_layer185();
        L22:
            return new TL_botInfo_layer192();
        }
    }

    public static abstract class BotMenuButton extends TLObject {
        public BotMenuButton() {
        }

        public static BotMenuButton TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 != (-944407322)) goto L5;
            TLObject r0 = new TL_botMenuButton();
        L13:
            return (BotMenuButton) TLObject.TLdeserialize(BotMenuButton.class, r0, r2, r3, r4);
        L5:
            if (r3 != 1113113093) goto L7;
            r0 = new TL_botMenuButtonCommands();
            goto L13
        L7:
            if (r3 == 1966318984) goto L9;
            r0 = null;
            goto L13
        L9:
            r0 = new TL_botMenuButtonDefault();
            goto L13
        }
    }

    /* loaded from: classes.dex */
    public static class TL_botInfo extends BotInfo {
        public static final int constructor = 1300890265;

        public TL_botInfo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_preview_medias = TLObject.hasFlag(r0, 64);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo = TLRPC.Photo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button = BotMenuButton.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.privacy_policy_url = r3.readString(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.app_settings = botAppSettings.TLdeserialize(r3, r3.readInt32(r4), r4);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            this.verifier_settings = botVerifierSettings.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.has_preview_medias);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt64(this.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document.serializeToStream(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serialize(r4, this.commands);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeString(this.privacy_policy_url);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.app_settings.serializeToStream(r4);
        L27:
            if (TLObject.hasFlag(this.flags, 512) == false) goto L30;
            this.verifier_settings.serializeToStream(r4);
            return;
        }
    }

    public static class TL_botInfoEmpty_layer48 extends TL_botInfo {
        public static final int constructor = -1154598962;

        public TL_botInfoEmpty_layer48() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_botInfo_layer131 extends TL_botInfo {
        public static final int constructor = -1729618630;

        public TL_botInfo_layer131() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt32(r4);
            this.description = r3.readString(r4);
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32((int) this.user_id);
            r3.writeString(this.description);
            Vector.serialize(r3, this.commands);
        }
    }

    public static class TL_botInfo_layer139 extends BotInfo {
        public static final int constructor = 460632885;

        public TL_botInfo_layer139() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.description = r3.readString(r4);
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeString(this.description);
            Vector.serialize(r3, this.commands);
        }
    }

    public static class TL_botInfo_layer140 extends TL_botInfo {
        public static final int constructor = -468280483;

        public TL_botInfo_layer140() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt64(r4);
            this.description = r3.readString(r4);
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
            this.menu_button = BotMenuButton.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
            r3.writeString(this.description);
            Vector.serialize(r3, this.commands);
            this.menu_button.serializeToStream(r3);
        }
    }

    public static class TL_botInfo_layer185 extends TL_botInfo {
        public static final int constructor = -1892676777;

        public TL_botInfo_layer185() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_preview_medias = TLObject.hasFlag(r0, 64);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo = TLRPC.Photo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button = BotMenuButton.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.has_preview_medias);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt64(this.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document.serializeToStream(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serialize(r4, this.commands);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button.serializeToStream(r4);
            return;
        }
    }

    public static class TL_botInfo_layer192 extends TL_botInfo {
        public static final int constructor = -2109505932;

        public TL_botInfo_layer192() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_preview_medias = TLObject.hasFlag(r0, 64);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo = TLRPC.Photo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button = BotMenuButton.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.privacy_policy_url = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.has_preview_medias);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt64(this.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document.serializeToStream(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serialize(r4, this.commands);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeString(this.privacy_policy_url);
            return;
        }
    }

    public static class TL_botInfo_layer195 extends TL_botInfo {
        public static final int constructor = 912290611;

        public TL_botInfo_layer195() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.has_preview_medias = TLObject.hasFlag(r0, 64);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.user_id = r3.readInt64(r4);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.description = r3.readString(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo = TLRPC.Photo.TLdeserialize(r3, r3.readInt32(r4), r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document = TLRPC.Document.TLdeserialize(r3, r3.readInt32(r4), r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button = BotMenuButton.TLdeserialize(r3, r3.readInt32(r4), r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            this.privacy_policy_url = r3.readString(r4);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.app_settings = botAppSettings.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 64, this.has_preview_medias);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeInt64(this.user_id);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r4.writeString(this.description);
        L9:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L12;
            this.description_photo.serializeToStream(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 32) == false) goto L15;
            this.description_document.serializeToStream(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L18;
            Vector.serialize(r4, this.commands);
        L18:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L21;
            this.menu_button.serializeToStream(r4);
        L21:
            if (TLObject.hasFlag(this.flags, 128) == false) goto L24;
            r4.writeString(this.privacy_policy_url);
        L24:
            if (TLObject.hasFlag(this.flags, 256) == false) goto L27;
            this.app_settings.serializeToStream(r4);
            return;
        }
    }

    public static class TL_botInfo_layer48 extends TL_botInfo {
        public static final int constructor = 164583517;

        public TL_botInfo_layer48() {
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.user_id = r3.readInt32(r4);
            this.version = r3.readInt32(r4);
            r3.readString(r4);
            this.description = r3.readString(r4);
            this.commands = Vector.deserialize(r3, new TL_bots$TL_botInfo$$ExternalSyntheticLambda0(), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_bots.TL_botInfo, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32((int) this.user_id);
            r3.writeInt32(this.version);
            r3.writeString(_UrlKt.FRAGMENT_ENCODE_SET);
            r3.writeString(this.description);
            Vector.serialize(r3, this.commands);
        }
    }

    public static class TL_botMenuButton extends BotMenuButton {
        public static final int constructor = -944407322;
        public String text;
        public String url;

        public TL_botMenuButton() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            this.url = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeString(this.url);
        }
    }

    public static class TL_botMenuButtonCommands extends BotMenuButton {
        public static final int constructor = 1113113093;

        public TL_botMenuButtonCommands() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_botMenuButtonDefault extends BotMenuButton {
        public static final int constructor = 1966318984;

        public TL_botMenuButtonDefault() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_updateBotMenuButton extends TLRPC.Update {
        public static final int constructor = 347625491;
        public long bot_id;
        public BotMenuButton button;

        public TL_updateBotMenuButton() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.bot_id = r3.readInt64(r4);
            this.button = BotMenuButton.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.bot_id);
            this.button.serializeToStream(r3);
        }
    }

    public static class addPreviewMedia extends TLObject {
        public static final int constructor = 397326170;
        public TLRPC.InputUser bot;
        public String lang_code;
        public TLRPC.InputMedia media;

        public addPreviewMedia() {
            this.lang_code = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return botPreviewMedia.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.lang_code);
            this.media.serializeToStream(r2);
        }
    }

    public static class allowSendMessage extends TLObject {
        public static final int constructor = -248323089;
        public TLRPC.InputUser bot;

        public allowSendMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Updates.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
        }
    }

    public static class botAppSettings extends TLObject {
        public static final int constructor = -912582320;
        public int background_color;
        public int background_dark_color;
        public int flags;
        public int header_color;
        public int header_dark_color;
        public byte[] placeholder_path;
        public Path placeholder_svg_path;

        public botAppSettings() {
        }

        public static botAppSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-912582320) == r3) goto L5;
            botAppSettings r0 = null;
        L7:
            return (botAppSettings) TLObject.TLdeserialize(botAppSettings.class, r0, r2, r3, r4);
        L5:
            r0 = new botAppSettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            byte[] r02 = r3.readByteArray(r4);
            this.placeholder_path = r02;
            this.placeholder_svg_path = SvgHelper.doPath(SvgHelper.decompress(r02));
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            this.background_color = r3.readInt32(r4);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            this.background_dark_color = r3.readInt32(r4);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            this.header_color = r3.readInt32(r4);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            this.header_dark_color = r3.readInt32(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeByteArray(this.placeholder_path);
        L6:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L9;
            r3.writeInt32(this.background_color);
        L9:
            if (TLObject.hasFlag(this.flags, 4) == false) goto L12;
            r3.writeInt32(this.background_dark_color);
        L12:
            if (TLObject.hasFlag(this.flags, 8) == false) goto L15;
            r3.writeInt32(this.header_color);
        L15:
            if (TLObject.hasFlag(this.flags, 16) == false) goto L18;
            r3.writeInt32(this.header_dark_color);
            return;
        }
    }

    public static class botPreviewMedia extends TLObject {
        public static final int constructor = 602479523;
        public int date;
        public TLRPC.MessageMedia media;

        public botPreviewMedia() {
        }

        public static botPreviewMedia TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (602479523 == r3) goto L5;
            botPreviewMedia r0 = null;
        L7:
            return (botPreviewMedia) TLObject.TLdeserialize(botPreviewMedia.class, r0, r2, r3, r4);
        L5:
            r0 = new botPreviewMedia();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.date = r2.readInt32(r3);
            this.media = TLRPC.MessageMedia.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.date);
            this.media.serializeToStream(r2);
        }
    }

    public static class botVerification extends TLObject {
        public static final int constructor = -113453988;
        public long bot_id;
        public String description;
        public long icon;

        public botVerification() {
        }

        public static botVerification TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-113453988) == r3) goto L5;
            botVerification r0 = null;
        L7:
            return (botVerification) TLObject.TLdeserialize(botVerification.class, r0, r2, r3, r4);
        L5:
            r0 = new botVerification();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.bot_id = r3.readInt64(r4);
            this.icon = r3.readInt64(r4);
            this.description = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.bot_id);
            r3.writeInt64(this.icon);
            r3.writeString(this.description);
        }
    }

    public static class botVerifierSettings extends TLObject {
        public static final int constructor = -1328716265;
        public boolean can_modify_custom_description;
        public String company;
        public String custom_description;
        public int flags;
        public long icon;

        public botVerifierSettings() {
        }

        public static botVerifierSettings TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-1328716265) == r3) goto L5;
            botVerifierSettings r0 = null;
        L7:
            return (botVerifierSettings) TLObject.TLdeserialize(botVerifierSettings.class, r0, r2, r3, r4);
        L5:
            r0 = new botVerifierSettings();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.can_modify_custom_description = TLObject.hasFlag(r0, 2);
            this.icon = r3.readInt64(r4);
            this.company = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.custom_description = r3.readString(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.can_modify_custom_description);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeInt64(this.icon);
            r4.writeString(this.company);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r4.writeString(this.custom_description);
            return;
        }
    }

    public static class canSendMessage extends TLObject {
        public static final int constructor = 324662502;
        public TLRPC.InputUser bot;

        public canSendMessage() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
        }
    }

    public static class checkDownloadFileParams extends TLObject {
        public static final int constructor = 1342666121;
        public TLRPC.InputUser bot;
        public String file_name;
        public String url;

        public checkDownloadFileParams() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.file_name);
            r2.writeString(this.url);
        }
    }

    public static class checkUsername extends TLMethod<TLRPC.Bool> {
        public static final int constructor = -2014174821;
        public String username;

        public checkUsername() {
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
            r2.writeString(this.username);
        }
    }

    public static class createBot extends TLMethod<TLRPC.User> {
        public static final int constructor = -441352405;
        public int flags;
        public TLRPC.InputUser manager_id;
        public String name;
        public String username;
        public boolean via_deeplink;

        public createBot() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TLRPC.User deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.User.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.via_deeplink);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.name);
            r4.writeString(this.username);
            this.manager_id.serializeToStream(r4);
        }
    }

    public static class deletePreviewMedia extends TLObject {
        public static final int constructor = 755054003;
        public TLRPC.InputUser bot;
        public String lang_code;
        public ArrayList<TLRPC.InputMedia> media;

        public deletePreviewMedia() {
            this.lang_code = _UrlKt.FRAGMENT_ENCODE_SET;
            this.media = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.lang_code);
            Vector.serialize(r2, this.media);
        }
    }

    public static class editPreviewMedia extends TLObject {
        public static final int constructor = -2061148049;
        public TLRPC.InputUser bot;
        public String lang_code;
        public TLRPC.InputMedia media;
        public TLRPC.InputMedia new_media;

        public editPreviewMedia() {
            this.lang_code = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return botPreviewMedia.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.lang_code);
            this.media.serializeToStream(r2);
            this.new_media.serializeToStream(r2);
        }
    }

    public static class exportBotToken extends TLMethod<exportedBotToken> {
        public static final int constructor = 6533257;
        public long bot_id;
        public boolean revoke;

        public exportBotToken() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public exportedBotToken deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return exportedBotToken.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.bot_id);
            r3.writeBool(this.revoke);
        }
    }

    public static class exportedBotToken extends TLObject {
        public static final int constructor = 1012971041;
        public String token;

        public exportedBotToken() {
        }

        public static exportedBotToken TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (1012971041 == r3) goto L5;
            exportedBotToken r0 = null;
        L7:
            return (exportedBotToken) TLObject.TLdeserialize(exportedBotToken.class, r0, r2, r3, r4);
        L5:
            r0 = new exportedBotToken();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.token = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.token);
        }
    }

    public static class getAdminedBots extends TLObject {
        public static final int constructor = -1334764157;

        public getAdminedBots() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class getBotInfo extends TLObject {
        public static final int constructor = -589753091;
        public TLRPC.InputUser bot;
        public int flags;
        public String lang_code;

        public getBotInfo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return BotInfo.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.bot.serializeToStream(r3);
        L5:
            r3.writeString(this.lang_code);
        }
    }

    public static class getBotMenuButton extends TLObject {
        public static final int constructor = -1671369944;
        public TLRPC.InputUser user_id;

        public getBotMenuButton() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return BotMenuButton.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.user_id.serializeToStream(r2);
        }
    }

    public static class getBotRecommendations extends TLObject {
        public static final int constructor = -1581840363;
        public TLRPC.InputUser bot;

        public getBotRecommendations() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Users.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
        }
    }

    public static class getPopularAppBots extends TLObject {
        public static final int constructor = -1034878574;
        public int limit;
        public String offset;

        public getPopularAppBots() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return popularAppBots.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.offset);
            r2.writeInt32(this.limit);
        }
    }

    public static class getPreviewInfo extends TLObject {
        public static final int constructor = 1111143341;
        public TLRPC.InputUser bot;
        public String lang_code;

        public getPreviewInfo() {
            this.lang_code = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return previewInfo.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.lang_code);
        }
    }

    public static class getPreviewMedias extends TLObject {
        public static final int constructor = -1566222003;
        public TLRPC.InputUser bot;

        public getPreviewMedias() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return Vector.TLDeserialize(r1, r2, r3, new TL_bots$getPreviewMedias$$ExternalSyntheticLambda0());
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
        }
    }

    public static class getRequestedWebViewButton extends TLMethod<TL_keyboard.KeyboardButton> {
        public static final int constructor = -1088047117;
        public TLRPC.InputUser bot;
        public String webapp_req_id;

        public getRequestedWebViewButton() {
        }

        @Override // org.telegram.tgnet.TLMethod
        public /* bridge */ /* synthetic */ TLObject deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return deserializeResponseT(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLMethod
        public TL_keyboard.KeyboardButton deserializeResponseT(InputSerializedData r1, int r2, boolean r3) {
            return TL_keyboard.KeyboardButton.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.webapp_req_id);
        }
    }

    public static class invokeWebViewCustomMethod extends TLObject {
        public static final int constructor = 142591463;
        public TLRPC.InputUser bot;
        public String custom_method;
        public TLRPC.TL_dataJSON params;

        public invokeWebViewCustomMethod() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.TL_dataJSON.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.custom_method);
            this.params.serializeToStream(r2);
        }
    }

    public static class popularAppBots extends TLObject {
        public static final int constructor = 428978491;
        public int flags;
        public String next_offset;
        public ArrayList<TLRPC.User> users;

        public popularAppBots() {
            this.users = new ArrayList();
        }

        public static popularAppBots TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (428978491 == r3) goto L5;
            popularAppBots r0 = null;
        L7:
            return (popularAppBots) TLObject.TLdeserialize(popularAppBots.class, r0, r2, r3, r4);
        L5:
            r0 = new popularAppBots();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            this.next_offset = r3.readString(r4);
        L5:
            this.users = Vector.deserialize(r3, new TLRPC$TL_attachMenuBots$$ExternalSyntheticLambda1(), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeString(this.next_offset);
        L5:
            Vector.serialize(r3, this.users);
        }
    }

    public static class previewInfo extends TLObject {
        public static final int constructor = 212278628;
        public ArrayList<String> lang_codes;
        public ArrayList<botPreviewMedia> media;

        public previewInfo() {
            this.media = new ArrayList();
            this.lang_codes = new ArrayList();
        }

        public static previewInfo TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (212278628 == r3) goto L5;
            previewInfo r0 = null;
        L7:
            return (previewInfo) TLObject.TLdeserialize(previewInfo.class, r0, r2, r3, r4);
        L5:
            r0 = new previewInfo();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.media = Vector.deserialize(r2, new TL_bots$getPreviewMedias$$ExternalSyntheticLambda0(), r3);
            this.lang_codes = Vector.deserializeString(r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.media);
            Vector.serializeString(r2, this.lang_codes);
        }
    }

    public static class reorderPreviewMedias extends TLObject {
        public static final int constructor = -1238895702;
        public TLRPC.InputUser bot;
        public String lang_code;
        public ArrayList<TLRPC.InputMedia> order;

        public reorderPreviewMedias() {
            this.lang_code = _UrlKt.FRAGMENT_ENCODE_SET;
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.lang_code);
            Vector.serialize(r2, this.order);
        }
    }

    public static class reorderUsernames extends TLObject {
        public static final int constructor = -1760972350;
        public TLRPC.InputUser bot;
        public ArrayList<String> order;

        public reorderUsernames() {
            this.order = new ArrayList();
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            Vector.serializeString(r2, this.order);
        }
    }

    public static class requestedButton extends TLObject {
        public static final int constructor = -247743273;
        public String webapp_req_id;

        public requestedButton() {
        }

        public static requestedButton TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if ((-247743273) == r3) goto L5;
            requestedButton r0 = null;
        L7:
            return (requestedButton) TLObject.TLdeserialize(requestedButton.class, r0, r2, r3, r4);
        L5:
            r0 = new requestedButton();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.webapp_req_id = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.webapp_req_id);
        }
    }

    public static class setBotInfo extends TLObject {
        public static final int constructor = 282013987;
        public String about;
        public TLRPC.InputUser bot;
        public String description;
        public int flags;
        public String lang_code;
        public String name;

        public setBotInfo() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L5;
            this.bot.serializeToStream(r3);
        L5:
            r3.writeString(this.lang_code);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L9;
            r3.writeString(this.name);
        L9:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            r3.writeString(this.about);
        L12:
            if (TLObject.hasFlag(this.flags, 2) == false) goto L15;
            r3.writeString(this.description);
            return;
        }
    }

    public static class setBotMenuButton extends TLObject {
        public static final int constructor = 1157944655;
        public BotMenuButton button;
        public TLRPC.InputUser user_id;

        public setBotMenuButton() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.user_id.serializeToStream(r2);
            this.button.serializeToStream(r2);
        }
    }

    public static class setCustomVerification extends TLObject {
        public static final int constructor = -1953898563;
        public TLRPC.InputUser bot;
        public String custom_description;
        public boolean enabled;
        public int flags;
        public TLRPC.InputPeer peer;

        public setCustomVerification() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 2, this.enabled);
            this.flags = r0;
            r4.writeInt32(r0);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            this.bot.serializeToStream(r4);
        L5:
            this.peer.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            r4.writeString(this.custom_description);
            return;
        }
    }

    public static class toggleUserEmojiStatusPermission extends TLObject {
        public static final int constructor = 115237778;
        public TLRPC.InputUser bot;
        public boolean enabled;

        public toggleUserEmojiStatusPermission() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeBool(this.enabled);
        }
    }

    public static class toggleUsername extends TLObject {
        public static final int constructor = 87861619;
        public boolean active;
        public TLRPC.InputUser bot;
        public String username;

        public toggleUsername() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TLRPC.Bool.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.bot.serializeToStream(r2);
            r2.writeString(this.username);
            r2.writeBool(this.active);
        }
    }

    public static class updateStarRefProgram extends TLObject {
        public static final int constructor = 2005621427;
        public TLRPC.InputUser bot;
        public int commission_permille;
        public int duration_months;
        public int flags;

        public updateStarRefProgram() {
        }

        @Override // org.telegram.tgnet.TLObject
        public TLObject deserializeResponse(InputSerializedData r1, int r2, boolean r3) {
            return TL_payments.starRefProgram.TLdeserialize(r1, r2, r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            this.bot.serializeToStream(r3);
            r3.writeInt32(this.commission_permille);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeInt32(this.duration_months);
            return;
        }
    }

    public TL_bots() {
    }
}
