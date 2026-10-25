package org.telegram.tgnet.tl;

import java.util.ArrayList;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLParseException;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.TLRPC$TL_messages_preparedInlineMessage$$ExternalSyntheticLambda0;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes6.dex */
public class TL_keyboard {

    public static abstract class ButtonType extends ButtonTypeProto {
        public ButtonType() {
        }

        public static ButtonType TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (ButtonType) TLObject.TLdeserialize(ButtonType.class, fromConstructor(r3), r2, r3, r4);
        }

        private static ButtonType fromConstructor(int r1) {
            switch(r1) {
                case -1678843584: goto L18;
                case -1429209212: goto L16;
                case -1072014982: goto L14;
                case -908226327: goto L12;
                case -549636359: goto L10;
                case 1071802622: goto L8;
                case 1331208759: goto L6;
                default: goto L4;
            };
        L4:
            return null;
        L6:
            return new TL_buttonTypeRequestPeer();
        L8:
            return new TL_inputButtonTypeRequestPeer(null);
        L10:
            return new TL_buttonTypeRequestPhone();
        L12:
            return new TL_buttonTypeDefault();
        L14:
            return new TL_buttonTypeSimpleWebView();
        L16:
            return new TL_buttonTypeRequestPoll();
        L18:
            return new TL_buttonTypeRequestGeoLocation();
        }
    }

    public static abstract class ButtonTypeProto extends TLObject {
        public ButtonTypeProto() {
        }
    }

    public static abstract class InlineButtonType extends ButtonTypeProto {
        public InlineButtonType() {
        }

        public static InlineButtonType TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (InlineButtonType) TLObject.TLdeserialize(InlineButtonType.class, fromConstructor(r3), r2, r3, r4);
        }

        private static InlineButtonType fromConstructor(int r1) {
            switch(r1) {
                case -1820901387: goto L28;
                case -1721647948: goto L26;
                case -1539808867: goto L24;
                case -1273154958: goto L22;
                case -1076875870: goto L20;
                case -324732716: goto L18;
                case 693484600: goto L16;
                case 1003140532: goto L14;
                case 1067663311: goto L12;
                case 1220204453: goto L10;
                case 1408487002: goto L8;
                case 1557360797: goto L6;
                default: goto L4;
            };
        L4:
            return null;
        L6:
            return new TL_inlineButtonTypeGame();
        L8:
            return new TL_inputInlineButtonTypeUserProfile(null);
        L10:
            return new TL_inlineButtonTypeBuy();
        L12:
            return new TL_inlineButtonTypeUserProfile();
        L14:
            return new TL_inlineButtonTypeWebView();
        L16:
            return new TL_inlineButtonTypeCallback();
        L18:
            return new TL_inlineButtonTypeUrl();
        L20:
            return new TL_inlineButtonTypeUrlAuth();
        L22:
            return new TL_inlineButtonTypeCopy();
        L24:
            return new TL_inlineButtonTypeDisabled();
        L26:
            return new TL_inputInlineButtonTypeUrlAuth(null);
        L28:
            return new TL_inlineButtonTypeSwitchInline();
        }
    }

    public static abstract class KeyboardButton extends TLObject implements KeyboardButtonProto {
        protected int flags;
        public KeyboardButtonStyle style;
        public String text;
        public ButtonType type;

        public KeyboardButton() {
        }

        public static KeyboardButton TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            KeyboardInlineButton r0 = KeyboardInlineButton.fromConstructor(r3);
            if (r0 == null) goto L7;
            r0.readParams(r2, r4);
            TL_keyboardButton_layer223 r22 = new TL_keyboardButton_layer223(null);
            r22.text = r0.text;
            r22.type = new TL_buttonTypeDefault();
            return r22;
        L7:
            return (KeyboardButton) TLObject.TLdeserialize(KeyboardButton.class, fromConstructor(r3), r2, r3, r4);
        }

        public static KeyboardButton fromConstructor(int r1) {
            switch(r1) {
                case -1598009252: goto L36;
                case -1560655744: goto L34;
                case -1438582451: goto L32;
                case -1318425559: goto L30;
                case -1144565411: goto L28;
                case -916050683: goto L26;
                case -514047120: goto L24;
                case -59151553: goto L22;
                case 45580630: goto L20;
                case 218842764: goto L18;
                case 795322159: goto L16;
                case 1098841487: goto L14;
                case 1406648280: goto L12;
                case 1527715317: goto L10;
                case 2047989634: goto L8;
                case 2098662655: goto L6;
                default: goto L4;
            };
        L4:
            return null;
        L6:
            return new TL_keyboardButton_layer228(null);
        L8:
            return new TL_keyboardButtonRequestPoll_layer228();
        L10:
            return new TL_keyboardButtonRequestPeer_layer228();
        L12:
            return new TL_keyboardButtonRequestPeer_layer223(null);
        L14:
            return new TL_keyboardButtonRequestPhone_layer228(null);
        L16:
            return new TL_keyboardButton();
        L18:
            return new TL_keyboardButtonRequestPeer_layer168(null);
        L20:
            return new TL_inputKeyboardButtonRequestPeer_layer228();
        L22:
            return new TL_keyboardButtonRequestGeoLocation_layer223(null);
        L24:
            return new TL_keyboardButtonSimpleWebView_layer228();
        L26:
            return new TL_inputKeyboardButtonRequestPeer_layer221(null);
        L28:
            return new TL_keyboardButtonRequestPoll_layer223(null);
        L30:
            return new TL_keyboardButtonRequestPhone_layer223(null);
        L32:
            return new TL_keyboardButtonRequestGeoLocation_layer228(null);
        L34:
            return new TL_keyboardButton_layer223(null);
        L36:
            return new TL_keyboardButtonSimpleWebView_layer223(null);
        }
    }

    public interface KeyboardButtonProto {
        @Deprecated
        default byte[] getData() {
            ButtonTypeProto r1 = getType();
            if ((r1 instanceof TL_inlineButtonTypeCallback) == true) goto L5;
            return null;
        L5:
            return ((TL_inlineButtonTypeCallback) r1).data;
        }

        String getText();

        ButtonTypeProto getType();

        @Deprecated
        default String getUrl() {
            ButtonTypeProto r1 = getType();
            if ((r1 instanceof TL_inlineButtonTypeUrl) == false) goto L7;
            return ((TL_inlineButtonTypeUrl) r1).url;
        L7:
            if ((r1 instanceof TL_inlineButtonTypeUrlAuth) == false) goto L11;
            return ((TL_inlineButtonTypeUrlAuth) r1).url;
        L11:
            if ((r1 instanceof TL_inlineButtonTypeWebView) == false) goto L15;
            return ((TL_inlineButtonTypeWebView) r1).url;
        L15:
            if ((r1 instanceof TL_buttonTypeSimpleWebView) == true) goto L17;
            return null;
        L17:
            return ((TL_buttonTypeSimpleWebView) r1).url;
        }
    }

    public static class KeyboardButtonRow extends TLObject {
        public static final int constructor = 2002815875;
        public ArrayList<KeyboardButton> buttons;

        public KeyboardButtonRow() {
            this.buttons = new ArrayList();
        }

        public static KeyboardButtonRow TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (2002815875 == r3) goto L5;
            KeyboardButtonRow r0 = null;
        L7:
            return (KeyboardButtonRow) TLObject.TLdeserialize(KeyboardButtonRow.class, r0, r2, r3, r4);
        L5:
            r0 = new KeyboardButtonRow();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.buttons = Vector.deserialize(r2, new TL_keyboard$KeyboardButtonRow$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(2002815875);
            Vector.serialize(r2, this.buttons);
        }
    }

    public static class KeyboardButtonStyle extends TLObject {
        public static final int constructor = 1339896880;
        public boolean bg_danger;
        public boolean bg_primary;
        public boolean bg_success;
        public int flags;
        public long icon;

        public KeyboardButtonStyle() {
        }

        public static KeyboardButtonStyle TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1339896880) goto L5;
            KeyboardButtonStyle r0 = null;
        L7:
            return (KeyboardButtonStyle) TLObject.TLdeserialize(KeyboardButtonStyle.class, r0, r2, r3, r4);
        L5:
            r0 = new KeyboardButtonStyle();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.bg_primary = TLObject.hasFlag(r0, 1);
            this.bg_danger = TLObject.hasFlag(this.flags, 2);
            this.bg_success = TLObject.hasFlag(this.flags, 4);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            this.icon = r3.readInt64(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.bg_primary);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.bg_danger);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.bg_success);
            this.flags = r03;
            r4.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 8) == false) goto L6;
            r4.writeInt64(this.icon);
            return;
        }
    }

    public static abstract class KeyboardInlineButton extends TLObject implements KeyboardButtonProto {
        public int flags;
        public KeyboardButtonStyle style;
        public String text;
        public InlineButtonType type;

        public KeyboardInlineButton() {
        }

        public static KeyboardInlineButton TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            KeyboardButton r0 = KeyboardButton.fromConstructor(r3);
            if (r0 == null) goto L7;
            r0.readParams(r2, r4);
            TL_keyboardInlineButton r22 = new TL_keyboardInlineButton();
            r22.text = r0.text;
            r22.type = new TL_inlineButtonTypeDisabled();
            return r22;
        L7:
            return (KeyboardInlineButton) TLObject.TLdeserialize(KeyboardInlineButton.class, fromConstructor(r3), r2, r3, r4);
        }

        public static KeyboardInlineButton fromConstructor(int r1) {
            if (r1 != 297902882) goto L7;
            return new TL_keyboardInlineButton();
        L7:
            return fromConstructorLegacy(r1);
        }

        private static KeyboardInlineButton fromConstructorLegacy(int r1) {
            switch(r1) {
                case -1983540999: goto L54;
                case -1816527947: goto L52;
                case -1726768644: goto L50;
                case -1675883435: goto L48;
                case -1344716869: goto L46;
                case -1127960816: goto L44;
                case -1057137399: goto L42;
                case -802258988: goto L40;
                case -670292500: goto L38;
                case -433338016: goto L36;
                case -398020192: goto L34;
                case -376962181: goto L32;
                case -183499015: goto L30;
                case 90744648: goto L28;
                case 280464681: goto L26;
                case 326529584: goto L24;
                case 629866245: goto L22;
                case 814112961: goto L20;
                case 901503851: goto L18;
                case 1067792645: goto L16;
                case 1358175439: goto L14;
                case 1744911986: goto L12;
                case 1748655686: goto L10;
                case 1976723854: goto L8;
                case 2103314375: goto L6;
                default: goto L4;
            };
        L4:
            return null;
        L6:
            return new TL_inputKeyboardButtonUserProfile_layer228();
        L8:
            return new TL_keyboardButtonCopy_layer223(null);
        L10:
            return new TL_keyboardButtonCallback_layer117(null);
        L12:
            return new TL_inputKeyboardButtonUrlAuth_layer228();
        L14:
            return new TL_keyboardButtonGame_layer223(null);
        L16:
            return new TL_keyboardButtonBuy_layer228(null);
        L18:
            return new TL_keyboardButtonCallback_layer223(null);
        L20:
            return new TL_keyboardButtonUserProfile_layer223(null);
        L22:
            return new TL_keyboardButtonUrl_layer223(null);
        L24:
            return new TL_keyboardButtonWebView_layer223(null);
        L26:
            return new TL_keyboardButtonUrlAuth_layer223(null);
        L28:
            return new TL_keyboardButtonSwitchInline_layer157(null);
        L30:
            return new TL_keyboardButtonUrlAuth_layer228();
        L32:
            return new TL_inputKeyboardButtonUserProfile_layer223(null);
        L34:
            return new TL_keyboardButtonWebView_layer228();
        L36:
            return new TL_keyboardButtonCallback_layer228();
        L38:
            return new TL_keyboardButtonUrl_layer228();
        L40:
            return new TL_inputKeyboardButtonUrlAuth_layer223(null);
        L42:
            return new TL_keyboardButtonUserProfile_layer228();
        L44:
            return new TL_keyboardButtonCopy_layer228();
        L46:
            return new TL_keyboardButtonBuy_layer223(null);
        L48:
            return new TL_keyboardInlineButton_legacy();
        L50:
            return new TL_keyboardButtonSwitchInline_layer228();
        L52:
            return new TL_keyboardButtonSwitchInline_layer223(null);
        L54:
            return new TL_keyboardButtonGame_layer228(null);
        }
    }

    public static abstract class KeyboardInlineButtonRow extends TLObject {
        public ArrayList<KeyboardInlineButton> buttons;

        public KeyboardInlineButtonRow() {
            this.buttons = new ArrayList();
        }

        public static KeyboardInlineButtonRow TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            return (KeyboardInlineButtonRow) TLObject.TLdeserialize(KeyboardInlineButtonRow.class, fromConstructor(r3), r2, r3, r4);
        }

        public static KeyboardInlineButtonRow fromConstructor(int r2) {
            if (r2 == 423758582) goto L10;
            if (r2 == 2002815875) goto L8;
            return null;
        L8:
            return new TL_keyboardInlineButtonRow_layer228(null);
        L10:
            return new TL_keyboardInlineButtonRow();
        }
    }

    public static class PageButton extends TLObject implements KeyboardButtonProto {
        public static final int constructor = 1764381832;
        public int flags;
        public RichButtonStyle style;
        public TL_iv.RichText text;
        public InlineButtonType type;

        public PageButton() {
        }

        public static PageButton TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 1764381832) goto L5;
            PageButton r0 = null;
        L7:
            return (PageButton) TLObject.TLdeserialize(PageButton.class, r0, r2, r3, r4);
        L5:
            r0 = new PageButton();
            goto L7
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public String getText() {
            TL_iv.RichText r1 = this.text;
            if ((r1 instanceof TL_iv.textPlain) == true) goto L5;
            return null;
        L5:
            return ((TL_iv.textPlain) r1).text;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public /* bridge */ /* synthetic */ ButtonTypeProto getType() {
            return getType();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public InlineButtonType getType() {
            return this.type;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.flags = r3.readInt32(r4);
            this.text = TL_iv.RichText.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.type = InlineButtonType.TLdeserialize(r3, r3.readInt32(r4), r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            this.style = RichButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            this.text.serializeToStream(r4);
            this.type.serializeToStream(r4);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L10;
            this.style.serializeToStream(r4);
            return;
        L10:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class RichButtonStyle extends TLObject {
        public static final int constructor = 63312061;
        public boolean bg_danger;
        public boolean bg_primary;
        public boolean bg_success;
        public int flags;
        public boolean link;

        public RichButtonStyle() {
        }

        public static RichButtonStyle TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 63312061) goto L5;
            RichButtonStyle r0 = null;
        L7:
            return (RichButtonStyle) TLObject.TLdeserialize(RichButtonStyle.class, r0, r2, r3, r4);
        L5:
            r0 = new RichButtonStyle();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            int r12 = r1.readInt32(r2);
            this.flags = r12;
            this.bg_primary = TLObject.hasFlag(r12, 1);
            this.bg_danger = TLObject.hasFlag(this.flags, 2);
            this.bg_success = TLObject.hasFlag(this.flags, 4);
            this.link = TLObject.hasFlag(this.flags, 8);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.bg_primary);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.bg_danger);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.bg_success);
            this.flags = r03;
            int r04 = TLObject.setFlag(r03, 8, this.link);
            this.flags = r04;
            r4.writeInt32(r04);
        }
    }

    public static class TL_buttonTypeDefault extends ButtonType {
        public static final int constructor = -908226327;

        public TL_buttonTypeDefault() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_buttonTypeRequestGeoLocation extends ButtonType {
        public static final int constructor = -1678843584;

        public TL_buttonTypeRequestGeoLocation() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_buttonTypeRequestPeer extends ButtonType {
        public static final int constructor = 1331208759;
        public int button_id;
        public int flags;
        public int max_quantity;
        public TLRPC.RequestPeerType peer_type;

        public TL_buttonTypeRequestPeer() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.flags = r2.readInt32(r3);
            this.button_id = r2.readInt32(r3);
            this.peer_type = TLRPC.RequestPeerType.TLdeserialize(r2, r2.readInt32(r3), r3);
            this.max_quantity = r2.readInt32(r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeInt32(this.flags);
            r2.writeInt32(this.button_id);
            this.peer_type.serializeToStream(r2);
            r2.writeInt32(this.max_quantity);
        }
    }

    public static class TL_buttonTypeRequestPhone extends ButtonType {
        public static final int constructor = -549636359;

        public TL_buttonTypeRequestPhone() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_buttonTypeRequestPoll extends ButtonType {
        public static final int constructor = -1429209212;
        public int flags;
        public boolean quiz;

        public TL_buttonTypeRequestPoll() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L6;
            this.quiz = r3.readBool(r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L6;
            r3.writeBool(this.quiz);
            return;
        }
    }

    public static class TL_buttonTypeSimpleWebView extends ButtonType {
        public static final int constructor = -1072014982;
        public String url;

        public TL_buttonTypeSimpleWebView() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.url = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.url);
        }
    }

    public static class TL_inlineButtonTypeBuy extends InlineButtonType {
        public static final int constructor = 1220204453;

        public TL_inlineButtonTypeBuy() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_inlineButtonTypeCallback extends InlineButtonType {
        public static final int constructor = 693484600;
        public byte[] data;
        public int flags;
        public boolean requires_password;

        public TL_inlineButtonTypeCallback() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.requires_password = TLObject.hasFlag(r0, 1);
            this.data = r3.readByteArray(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.requires_password);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeByteArray(this.data);
        }
    }

    public static class TL_inlineButtonTypeCopy extends InlineButtonType {
        public static final int constructor = -1273154958;
        public String copy_text;

        public TL_inlineButtonTypeCopy() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.copy_text = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.copy_text);
        }
    }

    public static class TL_inlineButtonTypeDisabled extends InlineButtonType {
        public static final int constructor = -1539808867;

        public TL_inlineButtonTypeDisabled() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_inlineButtonTypeGame extends InlineButtonType {
        public static final int constructor = 1557360797;

        public TL_inlineButtonTypeGame() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r1) {
            r1.writeInt32(constructor);
        }
    }

    public static class TL_inlineButtonTypeSwitchInline extends InlineButtonType {
        public static final int constructor = -1820901387;
        public int flags;
        public ArrayList<TLRPC.InlineQueryPeerType> peer_types;
        public String query;
        public boolean same_peer;

        public TL_inlineButtonTypeSwitchInline() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.same_peer = TLObject.hasFlag(r0, 1);
            this.query = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L6;
            this.peer_types = Vector.deserialize(r3, new TLRPC$TL_messages_preparedInlineMessage$$ExternalSyntheticLambda0(), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.same_peer);
            this.flags = r0;
            if (this.peer_types != null) goto L6;
            r2 = false;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r2);
            this.flags = r02;
            r4.writeInt32(r02);
            r4.writeString(this.query);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L10;
            Vector.serialize(r4, this.peer_types);
            return;
        }
    }

    public static class TL_inlineButtonTypeUrl extends InlineButtonType {
        public static final int constructor = -324732716;
        public String url;

        public TL_inlineButtonTypeUrl() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.url = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.url);
        }
    }

    public static class TL_inlineButtonTypeUrlAuth extends InlineButtonType {
        public static final int constructor = -1076875870;
        public int button_id;
        public int flags;
        public String fwd_text;
        public String url;

        public TL_inlineButtonTypeUrlAuth() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            this.fwd_text = r3.readString(r4);
        L5:
            this.url = r3.readString(r4);
            this.button_id = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.fwd_text == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L9;
            r4.writeString(this.fwd_text);
        L9:
            r4.writeString(this.url);
            r4.writeInt32(this.button_id);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_inlineButtonTypeUserProfile extends InlineButtonType {
        public static final int constructor = 1067663311;
        public long user_id;

        public TL_inlineButtonTypeUserProfile() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.user_id = r1.readInt64(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt64(this.user_id);
        }
    }

    public static class TL_inlineButtonTypeWebView extends InlineButtonType {
        public static final int constructor = 1003140532;
        public String url;

        public TL_inlineButtonTypeWebView() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.url = r1.readString(r2);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.url);
        }
    }

    public static class TL_inputButtonTypeRequestPeer extends ButtonType {
        public static final int constructor = 1071802622;
        public int button_id;
        public int flags;
        public int max_quantity;
        public boolean name_requested;
        public TLRPC.RequestPeerType peer_type;
        public boolean photo_requested;
        public boolean username_requested;

        private TL_inputButtonTypeRequestPeer() {
        }

        public /* synthetic */ TL_inputButtonTypeRequestPeer(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.name_requested = TLObject.hasFlag(r0, 1);
            this.username_requested = TLObject.hasFlag(this.flags, 2);
            this.photo_requested = TLObject.hasFlag(this.flags, 4);
            this.button_id = r3.readInt32(r4);
            this.peer_type = TLRPC.RequestPeerType.TLdeserialize(r3, r3.readInt32(r4), r4);
            this.max_quantity = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.name_requested);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.username_requested);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.photo_requested);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeInt32(this.button_id);
            this.peer_type.serializeToStream(r4);
            r4.writeInt32(this.max_quantity);
        }
    }

    public static class TL_inputInlineButtonTypeUrlAuth extends InlineButtonType {
        public static final int constructor = -1721647948;
        public TLRPC.InputUser bot;
        public int flags;
        public String fwd_text;
        public boolean request_write_access;
        public String url;

        private TL_inputInlineButtonTypeUrlAuth() {
        }

        public /* synthetic */ TL_inputInlineButtonTypeUrlAuth(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            this.request_write_access = TLObject.hasFlag(r0, 1);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.fwd_text = r3.readString(r4);
        L5:
            this.url = r3.readString(r4);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L9;
            this.bot = TLRPC.InputUser.TLdeserialize(r3, r3.readInt32(r4), r4);
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.request_write_access);
            this.flags = r0;
            if (this.fwd_text == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 2, r1);
            this.flags = r02;
            if (this.bot != null) goto L10;
            r2 = false;
        L10:
            int r03 = TLObject.setFlag(r02, 4, r2);
            this.flags = r03;
            r6.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L13;
            r6.writeString(this.fwd_text);
        L13:
            r6.writeString(this.url);
            if (TLObject.hasFlag(this.flags, 4) == false) goto L17;
            this.bot.serializeToStream(r6);
            return;
        L17:
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_inputInlineButtonTypeUserProfile extends InlineButtonType {
        public static final int constructor = 1408487002;
        public TLRPC.InputUser user_id;

        private TL_inputInlineButtonTypeUserProfile() {
        }

        public /* synthetic */ TL_inputInlineButtonTypeUserProfile(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.user_id = TLRPC.InputUser.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            this.user_id.serializeToStream(r2);
        }
    }

    public static class TL_inputKeyboardButtonRequestPeer_layer221 extends TL_inputKeyboardButtonRequestPeer_layer228 {
        public static final int constructor = -916050683;

        private TL_inputKeyboardButtonRequestPeer_layer221() {
        }

        public /* synthetic */ TL_inputKeyboardButtonRequestPeer_layer221(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_inputKeyboardButtonRequestPeer_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            int r0 = r6.readInt32(r7);
            this.flags = r0;
            TL_inputButtonTypeRequestPeer r1 = this.mType;
            r1.name_requested = TLObject.hasFlag(r0, 1);
            TL_inputButtonTypeRequestPeer r02 = this.mType;
            r02.username_requested = TLObject.hasFlag(this.flags, 2);
            TL_inputButtonTypeRequestPeer r03 = this.mType;
            r03.photo_requested = TLObject.hasFlag(this.flags, 4);
            this.text = r6.readString(r7);
            TL_inputButtonTypeRequestPeer r04 = this.mType;
            r04.button_id = r6.readInt32(r7);
            TL_inputButtonTypeRequestPeer r05 = this.mType;
            r05.peer_type = TLRPC.RequestPeerType.TLdeserialize(r6, r6.readInt32(r7), r7);
            TL_inputButtonTypeRequestPeer r06 = this.mType;
            r06.max_quantity = r6.readInt32(r7);
            TL_inputButtonTypeRequestPeer r62 = this.mType;
            r62.flags = TLObject.setFlag(r62.flags, 1, r62.name_requested);
            TL_inputButtonTypeRequestPeer r63 = this.mType;
            r63.flags = TLObject.setFlag(r63.flags, 2, r63.username_requested);
            TL_inputButtonTypeRequestPeer r5 = this.mType;
            r5.flags = TLObject.setFlag(r5.flags, 4, r5.photo_requested);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_inputKeyboardButtonRequestPeer_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.name_requested);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.mType.username_requested);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.mType.photo_requested);
            this.flags = r03;
            r4.writeInt32(r03);
            r4.writeString(this.text);
            r4.writeInt32(this.mType.button_id);
            this.mType.peer_type.serializeToStream(r4);
            r4.writeInt32(this.mType.max_quantity);
        }
    }

    public static class TL_inputKeyboardButtonRequestPeer_layer228 extends TL_keyboardButton {
        public static final int constructor = 45580630;
        public final TL_inputButtonTypeRequestPeer mType;

        public TL_inputKeyboardButtonRequestPeer_layer228() {
            TL_inputButtonTypeRequestPeer r0 = new TL_inputButtonTypeRequestPeer(null);
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r6, boolean r7) {
            int r0 = r6.readInt32(r7);
            this.flags = r0;
            TL_inputButtonTypeRequestPeer r1 = this.mType;
            r1.name_requested = TLObject.hasFlag(r0, 1);
            TL_inputButtonTypeRequestPeer r02 = this.mType;
            r02.username_requested = TLObject.hasFlag(this.flags, 2);
            TL_inputButtonTypeRequestPeer r03 = this.mType;
            r03.photo_requested = TLObject.hasFlag(this.flags, 4);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r6, r6.readInt32(r7), r7);
        L5:
            this.text = r6.readString(r7);
            TL_inputButtonTypeRequestPeer r04 = this.mType;
            r04.button_id = r6.readInt32(r7);
            TL_inputButtonTypeRequestPeer r05 = this.mType;
            r05.peer_type = TLRPC.RequestPeerType.TLdeserialize(r6, r6.readInt32(r7), r7);
            TL_inputButtonTypeRequestPeer r06 = this.mType;
            r06.max_quantity = r6.readInt32(r7);
            TL_inputButtonTypeRequestPeer r62 = this.mType;
            r62.flags = TLObject.setFlag(r62.flags, 1, r62.name_requested);
            TL_inputButtonTypeRequestPeer r63 = this.mType;
            r63.flags = TLObject.setFlag(r63.flags, 2, r63.username_requested);
            TL_inputButtonTypeRequestPeer r5 = this.mType;
            r5.flags = TLObject.setFlag(r5.flags, 4, r5.photo_requested);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.name_requested);
            this.flags = r0;
            int r02 = TLObject.setFlag(r0, 2, this.mType.username_requested);
            this.flags = r02;
            int r03 = TLObject.setFlag(r02, 4, this.mType.photo_requested);
            this.flags = r03;
            if (this.style != null) goto L6;
            r2 = false;
        L6:
            int r04 = TLObject.setFlag(r03, 1024, r2);
            this.flags = r04;
            r5.writeInt32(r04);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r5);
        L9:
            r5.writeString(this.text);
            r5.writeInt32(this.mType.button_id);
            this.mType.peer_type.serializeToStream(r5);
            r5.writeInt32(this.mType.max_quantity);
        }
    }

    public static class TL_inputKeyboardButtonUrlAuth_layer223 extends TL_inputKeyboardButtonUrlAuth_layer228 {
        public static final int constructor = -802258988;

        private TL_inputKeyboardButtonUrlAuth_layer223() {
        }

        public /* synthetic */ TL_inputKeyboardButtonUrlAuth_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_inputKeyboardButtonUrlAuth_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            boolean r2 = true;
            this.mType.request_write_access = TLObject.hasFlag(r0, 1);
            this.text = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            this.mType.fwd_text = r5.readString(r6);
        L5:
            this.mType.url = r5.readString(r6);
            this.mType.bot = TLRPC.InputUser.TLdeserialize(r5, r5.readInt32(r6), r6);
            TL_inputInlineButtonTypeUrlAuth r52 = this.mType;
            r52.flags = TLObject.setFlag(r52.flags, 1, r52.request_write_access);
            TL_inputInlineButtonTypeUrlAuth r53 = this.mType;
            int r62 = r53.flags;
            if (r53.fwd_text == null) goto L8;
            boolean r02 = true;
        L9:
            r53.flags = TLObject.setFlag(r62, 2, r02);
            TL_inputInlineButtonTypeUrlAuth r4 = this.mType;
            int r54 = r4.flags;
            if (r4.bot != null) goto L13;
            r2 = false;
        L13:
            r4.flags = TLObject.setFlag(r54, 4, r2);
            return;
        L8:
            r02 = false;
            goto L9
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_inputKeyboardButtonUrlAuth_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.request_write_access);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.text);
            String r02 = this.mType.fwd_text;
            if (r02 == null) goto L5;
            r4.writeString(r02);
        L5:
            r4.writeString(this.mType.url);
            this.mType.bot.serializeToStream(r4);
        }
    }

    public static class TL_inputKeyboardButtonUrlAuth_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = 1744911986;
        public final TL_inputInlineButtonTypeUrlAuth mType;

        public TL_inputKeyboardButtonUrlAuth_layer228() {
            TL_inputInlineButtonTypeUrlAuth r0 = new TL_inputInlineButtonTypeUrlAuth(null);
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r5, r5.readInt32(r6), r6);
        L5:
            boolean r2 = true;
            this.mType.request_write_access = TLObject.hasFlag(this.flags, 1);
            this.text = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            this.mType.fwd_text = r5.readString(r6);
        L8:
            this.mType.url = r5.readString(r6);
            this.mType.bot = TLRPC.InputUser.TLdeserialize(r5, r5.readInt32(r6), r6);
            TL_inputInlineButtonTypeUrlAuth r52 = this.mType;
            r52.flags = TLObject.setFlag(r52.flags, 1, r52.request_write_access);
            TL_inputInlineButtonTypeUrlAuth r53 = this.mType;
            int r62 = r53.flags;
            if (r53.fwd_text == null) goto L11;
            boolean r02 = true;
        L12:
            r53.flags = TLObject.setFlag(r62, 2, r02);
            TL_inputInlineButtonTypeUrlAuth r4 = this.mType;
            int r54 = r4.flags;
            if (r4.bot != null) goto L16;
            r2 = false;
        L16:
            r4.flags = TLObject.setFlag(r54, 4, r2);
            return;
        L11:
            r02 = false;
            goto L12
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.request_write_access);
            this.flags = r0;
            if (this.style != null) goto L6;
            r2 = false;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r2);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            String r03 = this.mType.fwd_text;
            if (r03 == null) goto L12;
            r4.writeString(r03);
        L12:
            r4.writeString(this.mType.url);
            this.mType.bot.serializeToStream(r4);
        }
    }

    public static class TL_inputKeyboardButtonUserProfile_layer223 extends TL_inputKeyboardButtonUserProfile_layer228 {
        public static final int constructor = -376962181;

        private TL_inputKeyboardButtonUserProfile_layer223() {
        }

        public /* synthetic */ TL_inputKeyboardButtonUserProfile_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_inputKeyboardButtonUserProfile_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_inputInlineButtonTypeUserProfile r1 = this.mType;
            r1.user_id = TLRPC.InputUser.TLdeserialize(r2, r2.readInt32(r3), r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_inputKeyboardButtonUserProfile_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            this.mType.user_id.serializeToStream(r2);
        }
    }

    public static class TL_inputKeyboardButtonUserProfile_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = 2103314375;
        public final TL_inputInlineButtonTypeUserProfile mType;

        public TL_inputKeyboardButtonUserProfile_layer228() {
            TL_inputInlineButtonTypeUserProfile r0 = new TL_inputInlineButtonTypeUserProfile(null);
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_inputInlineButtonTypeUserProfile r2 = this.mType;
            r2.user_id = TLRPC.InputUser.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            this.mType.user_id.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButton extends KeyboardButton {
        public static final int constructor = 795322159;

        public TL_keyboardButton() {
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public String getText() {
            return this.text;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public ButtonTypeProto getType() {
            return this.type;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            this.type = ButtonType.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            this.type.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonBuy_layer223 extends TL_keyboardButtonBuy_layer228 {
        public static final int constructor = -1344716869;

        private TL_keyboardButtonBuy_layer223() {
            super(null);
        }

        public /* synthetic */ TL_keyboardButtonBuy_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonBuy_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.type = new TL_inlineButtonTypeBuy();
            this.text = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonBuy_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
        }
    }

    public static class TL_keyboardButtonBuy_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = 1067792645;

        private TL_keyboardButtonBuy_layer228() {
        }

        public /* synthetic */ TL_keyboardButtonBuy_layer228(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.type = new TL_inlineButtonTypeBuy();
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonCallback_layer117 extends TL_keyboardButtonCallback_layer228 {
        public static final int constructor = 1748655686;

        private TL_keyboardButtonCallback_layer117() {
        }

        public /* synthetic */ TL_keyboardButtonCallback_layer117(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonCallback_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_inlineButtonTypeCallback r1 = this.mType;
            r1.data = r2.readByteArray(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonCallback_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeByteArray(this.mType.data);
        }
    }

    public static class TL_keyboardButtonCallback_layer223 extends TL_keyboardButtonCallback_layer228 {
        public static final int constructor = 901503851;

        private TL_keyboardButtonCallback_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonCallback_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonCallback_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            TL_inlineButtonTypeCallback r1 = this.mType;
            r1.requires_password = TLObject.hasFlag(r0, 1);
            this.text = r4.readString(r5);
            TL_inlineButtonTypeCallback r02 = this.mType;
            r02.data = r4.readByteArray(r5);
            TL_inlineButtonTypeCallback r3 = this.mType;
            r3.flags = TLObject.setFlag(r3.flags, 1, r3.requires_password);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonCallback_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.requires_password);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.text);
            r4.writeByteArray(this.mType.data);
        }
    }

    public static class TL_keyboardButtonCallback_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -433338016;
        public final TL_inlineButtonTypeCallback mType;

        public TL_keyboardButtonCallback_layer228() {
            TL_inlineButtonTypeCallback r0 = new TL_inlineButtonTypeCallback();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            TL_inlineButtonTypeCallback r1 = this.mType;
            r1.requires_password = TLObject.hasFlag(r0, 1);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r4, r4.readInt32(r5), r5);
        L5:
            this.text = r4.readString(r5);
            TL_inlineButtonTypeCallback r02 = this.mType;
            r02.data = r4.readByteArray(r5);
            TL_inlineButtonTypeCallback r3 = this.mType;
            r3.flags = TLObject.setFlag(r3.flags, 1, r3.requires_password);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.requires_password);
            this.flags = r0;
            if (this.style != null) goto L6;
            r2 = false;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r2);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeByteArray(this.mType.data);
        }
    }

    public static class TL_keyboardButtonCopy_layer223 extends TL_keyboardButtonCopy_layer228 {
        public static final int constructor = 1976723854;

        private TL_keyboardButtonCopy_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonCopy_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonCopy_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_inlineButtonTypeCopy r1 = this.mType;
            r1.copy_text = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonCopy_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeString(this.mType.copy_text);
        }
    }

    public static class TL_keyboardButtonCopy_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -1127960816;
        public final TL_inlineButtonTypeCopy mType;

        public TL_keyboardButtonCopy_layer228() {
            TL_inlineButtonTypeCopy r0 = new TL_inlineButtonTypeCopy();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_inlineButtonTypeCopy r2 = this.mType;
            r2.copy_text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeString(this.mType.copy_text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonGame_layer223 extends TL_keyboardButtonGame_layer228 {
        public static final int constructor = 1358175439;

        private TL_keyboardButtonGame_layer223() {
            super(null);
        }

        public /* synthetic */ TL_keyboardButtonGame_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonGame_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.type = new TL_inlineButtonTypeGame();
            this.text = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonGame_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
        }
    }

    public static class TL_keyboardButtonGame_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -1983540999;

        private TL_keyboardButtonGame_layer228() {
        }

        public /* synthetic */ TL_keyboardButtonGame_layer228(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.type = new TL_inlineButtonTypeGame();
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonRequestGeoLocation_layer223 extends TL_keyboardButtonRequestGeoLocation_layer228 {
        public static final int constructor = -59151553;

        private TL_keyboardButtonRequestGeoLocation_layer223() {
            super(null);
        }

        public /* synthetic */ TL_keyboardButtonRequestGeoLocation_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestGeoLocation_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.type = new TL_buttonTypeRequestGeoLocation();
            this.text = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestGeoLocation_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
        }
    }

    public static class TL_keyboardButtonRequestGeoLocation_layer228 extends TL_keyboardButton {
        public static final int constructor = -1438582451;

        private TL_keyboardButtonRequestGeoLocation_layer228() {
        }

        public /* synthetic */ TL_keyboardButtonRequestGeoLocation_layer228(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.type = new TL_buttonTypeRequestGeoLocation();
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonRequestPeer_layer168 extends TL_keyboardButtonRequestPeer_layer228 {
        public static final int constructor = 218842764;

        private TL_keyboardButtonRequestPeer_layer168() {
        }

        public /* synthetic */ TL_keyboardButtonRequestPeer_layer168(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPeer_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.text = r3.readString(r4);
            TL_buttonTypeRequestPeer r0 = this.mType;
            r0.button_id = r3.readInt32(r4);
            TL_buttonTypeRequestPeer r2 = this.mType;
            r2.peer_type = TLRPC.RequestPeerType.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPeer_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeInt32(this.mType.button_id);
            this.mType.peer_type.serializeToStream(r2);
        }
    }

    public static class TL_keyboardButtonRequestPeer_layer223 extends TL_keyboardButtonRequestPeer_layer228 {
        public static final int constructor = 1406648280;

        private TL_keyboardButtonRequestPeer_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonRequestPeer_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPeer_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.text = r3.readString(r4);
            TL_buttonTypeRequestPeer r0 = this.mType;
            r0.button_id = r3.readInt32(r4);
            TL_buttonTypeRequestPeer r02 = this.mType;
            r02.peer_type = TLRPC.RequestPeerType.TLdeserialize(r3, r3.readInt32(r4), r4);
            TL_buttonTypeRequestPeer r2 = this.mType;
            r2.max_quantity = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPeer_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeInt32(this.mType.button_id);
            this.mType.peer_type.serializeToStream(r2);
            r2.writeInt32(this.mType.max_quantity);
        }
    }

    public static class TL_keyboardButtonRequestPeer_layer228 extends TL_keyboardButton {
        public static final int constructor = 1527715317;
        public final TL_buttonTypeRequestPeer mType;

        public TL_keyboardButtonRequestPeer_layer228() {
            TL_buttonTypeRequestPeer r0 = new TL_buttonTypeRequestPeer();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_buttonTypeRequestPeer r02 = this.mType;
            r02.button_id = r3.readInt32(r4);
            TL_buttonTypeRequestPeer r03 = this.mType;
            r03.peer_type = TLRPC.RequestPeerType.TLdeserialize(r3, r3.readInt32(r4), r4);
            TL_buttonTypeRequestPeer r2 = this.mType;
            r2.max_quantity = r3.readInt32(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeInt32(this.mType.button_id);
            this.mType.peer_type.serializeToStream(r4);
            r4.writeInt32(this.mType.max_quantity);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonRequestPhone_layer223 extends TL_keyboardButtonRequestPhone_layer228 {
        public static final int constructor = -1318425559;

        private TL_keyboardButtonRequestPhone_layer223() {
            super(null);
        }

        public /* synthetic */ TL_keyboardButtonRequestPhone_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPhone_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.type = new TL_buttonTypeRequestPhone();
            this.text = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPhone_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
        }
    }

    public static class TL_keyboardButtonRequestPhone_layer228 extends TL_keyboardButton {
        public static final int constructor = 1098841487;

        private TL_keyboardButtonRequestPhone_layer228() {
        }

        public /* synthetic */ TL_keyboardButtonRequestPhone_layer228(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.type = new TL_buttonTypeRequestPhone();
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonRequestPoll_layer223 extends TL_keyboardButtonRequestPoll_layer228 {
        public static final int constructor = -1144565411;

        private TL_keyboardButtonRequestPoll_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonRequestPoll_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPoll_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            int r2 = r0 & 1;
            this.mType.flags = r2;
            if (TLObject.hasFlag(r0, 1) == false) goto L5;
            TL_buttonTypeRequestPoll r02 = this.mType;
            r02.quiz = r4.readBool(r5);
        L5:
            this.text = r4.readString(r5);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonRequestPoll_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeBool(this.mType.quiz);
        L5:
            r3.writeString(this.text);
        }
    }

    public static class TL_keyboardButtonRequestPoll_layer228 extends TL_keyboardButton {
        public static final int constructor = 2047989634;
        public final TL_buttonTypeRequestPoll mType;

        public TL_keyboardButtonRequestPoll_layer228() {
            TL_buttonTypeRequestPoll r0 = new TL_buttonTypeRequestPoll();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r4, r4.readInt32(r5), r5);
        L5:
            TL_buttonTypeRequestPoll r02 = this.mType;
            int r1 = this.flags;
            r02.flags = r1 & 1;
            if (TLObject.hasFlag(r1, 1) == false) goto L8;
            TL_buttonTypeRequestPoll r03 = this.mType;
            r03.quiz = r4.readBool(r5);
        L8:
            this.text = r4.readString(r5);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r5.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L10;
            this.style.serializeToStream(r5);
        L10:
            if (TLObject.hasFlag(this.flags, 1) == false) goto L12;
            r5.writeBool(this.mType.quiz);
        L12:
            r5.writeString(this.text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonSimpleWebView_layer223 extends TL_keyboardButtonSimpleWebView_layer228 {
        public static final int constructor = -1598009252;

        private TL_keyboardButtonSimpleWebView_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonSimpleWebView_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonSimpleWebView_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_buttonTypeSimpleWebView r1 = this.mType;
            r1.url = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonSimpleWebView_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeString(this.mType.url);
        }
    }

    public static class TL_keyboardButtonSimpleWebView_layer228 extends TL_keyboardButton {
        public static final int constructor = -514047120;
        public final TL_buttonTypeSimpleWebView mType;

        public TL_keyboardButtonSimpleWebView_layer228() {
            TL_buttonTypeSimpleWebView r0 = new TL_buttonTypeSimpleWebView();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_buttonTypeSimpleWebView r2 = this.mType;
            r2.url = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeString(this.mType.url);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonSwitchInline_layer157 extends TL_keyboardButtonSwitchInline_layer228 {
        public static final int constructor = 90744648;

        private TL_keyboardButtonSwitchInline_layer157() {
        }

        public /* synthetic */ TL_keyboardButtonSwitchInline_layer157(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonSwitchInline_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            TL_inlineButtonTypeSwitchInline r1 = this.mType;
            r1.same_peer = TLObject.hasFlag(r0, 1);
            this.text = r4.readString(r5);
            TL_inlineButtonTypeSwitchInline r02 = this.mType;
            r02.query = r4.readString(r5);
            TL_inlineButtonTypeSwitchInline r3 = this.mType;
            r3.flags = TLObject.setFlag(r3.flags, 1, r3.same_peer);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonSwitchInline_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.same_peer);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.text);
            r4.writeString(this.mType.query);
        }
    }

    public static class TL_keyboardButtonSwitchInline_layer223 extends TL_keyboardButtonSwitchInline_layer228 {
        public static final int constructor = -1816527947;

        private TL_keyboardButtonSwitchInline_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonSwitchInline_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonSwitchInline_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            TL_inlineButtonTypeSwitchInline r1 = this.mType;
            boolean r2 = true;
            r1.same_peer = TLObject.hasFlag(r0, 1);
            this.text = r5.readString(r6);
            TL_inlineButtonTypeSwitchInline r02 = this.mType;
            r02.query = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L5;
            TL_inlineButtonTypeSwitchInline r03 = this.mType;
            r03.peer_types = Vector.deserialize(r5, new TLRPC$TL_messages_preparedInlineMessage$$ExternalSyntheticLambda0(), r6);
        L5:
            TL_inlineButtonTypeSwitchInline r52 = this.mType;
            r52.flags = TLObject.setFlag(r52.flags, 1, r52.same_peer);
            TL_inlineButtonTypeSwitchInline r4 = this.mType;
            int r53 = r4.flags;
            if (r4.peer_types != null) goto L9;
            r2 = false;
        L9:
            r4.flags = TLObject.setFlag(r53, 2, r2);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonSwitchInline_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.same_peer);
            this.flags = r0;
            r4.writeInt32(r0);
            r4.writeString(this.text);
            r4.writeString(this.mType.query);
            ArrayList<TLRPC.InlineQueryPeerType> r3 = this.mType.peer_types;
            if (r3 == null) goto L6;
            Vector.serialize(r4, r3);
            return;
        }
    }

    public static class TL_keyboardButtonSwitchInline_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -1726768644;
        public final TL_inlineButtonTypeSwitchInline mType;

        public TL_keyboardButtonSwitchInline_layer228() {
            TL_inlineButtonTypeSwitchInline r0 = new TL_inlineButtonTypeSwitchInline();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r5, r5.readInt32(r6), r6);
        L5:
            TL_inlineButtonTypeSwitchInline r02 = this.mType;
            boolean r2 = true;
            r02.same_peer = TLObject.hasFlag(this.flags, 1);
            this.text = r5.readString(r6);
            TL_inlineButtonTypeSwitchInline r03 = this.mType;
            r03.query = r5.readString(r6);
            if (TLObject.hasFlag(this.flags, 2) == false) goto L8;
            TL_inlineButtonTypeSwitchInline r04 = this.mType;
            r04.peer_types = Vector.deserialize(r5, new TLRPC$TL_messages_preparedInlineMessage$$ExternalSyntheticLambda0(), r6);
        L8:
            TL_inlineButtonTypeSwitchInline r52 = this.mType;
            r52.flags = TLObject.setFlag(r52.flags, 1, r52.same_peer);
            TL_inlineButtonTypeSwitchInline r4 = this.mType;
            int r53 = r4.flags;
            if (r4.peer_types != null) goto L12;
            r2 = false;
        L12:
            r4.flags = TLObject.setFlag(r53, 2, r2);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            boolean r2 = true;
            int r0 = TLObject.setFlag(this.flags, 1, this.mType.same_peer);
            this.flags = r0;
            if (this.style != null) goto L6;
            r2 = false;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r2);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeString(this.mType.query);
            ArrayList<TLRPC.InlineQueryPeerType> r3 = this.mType.peer_types;
            if (r3 == null) goto L13;
            Vector.serialize(r4, r3);
            return;
        }
    }

    public static class TL_keyboardButtonUrlAuth_layer223 extends TL_keyboardButtonUrlAuth_layer228 {
        public static final int constructor = 280464681;

        private TL_keyboardButtonUrlAuth_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonUrlAuth_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonUrlAuth_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            this.flags = r4.readInt32(r5);
            this.text = r4.readString(r5);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            TL_inlineButtonTypeUrlAuth r0 = this.mType;
            r0.fwd_text = r4.readString(r5);
        L5:
            TL_inlineButtonTypeUrlAuth r02 = this.mType;
            r02.url = r4.readString(r5);
            TL_inlineButtonTypeUrlAuth r03 = this.mType;
            r03.button_id = r4.readInt32(r5);
            TL_inlineButtonTypeUrlAuth r42 = this.mType;
            r42.flags = this.flags & 1;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonUrlAuth_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeInt32(this.flags);
            r3.writeString(this.text);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L5;
            r3.writeString(this.mType.fwd_text);
        L5:
            r3.writeString(this.mType.url);
            r3.writeInt32(this.mType.button_id);
        }
    }

    public static class TL_keyboardButtonUrlAuth_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -183499015;
        public final TL_inlineButtonTypeUrlAuth mType;

        public TL_keyboardButtonUrlAuth_layer228() {
            TL_inlineButtonTypeUrlAuth r0 = new TL_inlineButtonTypeUrlAuth();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            int r0 = r4.readInt32(r5);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r4, r4.readInt32(r5), r5);
        L5:
            this.text = r4.readString(r5);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L8;
            TL_inlineButtonTypeUrlAuth r02 = this.mType;
            r02.fwd_text = r4.readString(r5);
        L8:
            TL_inlineButtonTypeUrlAuth r03 = this.mType;
            r03.url = r4.readString(r5);
            TL_inlineButtonTypeUrlAuth r04 = this.mType;
            r04.button_id = r4.readInt32(r5);
            TL_inlineButtonTypeUrlAuth r42 = this.mType;
            r42.flags = this.flags & 1;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r5) {
            r5.writeInt32(constructor);
            int r0 = this.flags;
            boolean r2 = false;
            if (this.mType.fwd_text == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1, r1);
            this.flags = r02;
            if (this.style == null) goto L9;
            r2 = true;
        L9:
            int r03 = TLObject.setFlag(r02, 1024, r2);
            this.flags = r03;
            r5.writeInt32(r03);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L12;
            this.style.serializeToStream(r5);
        L12:
            r5.writeString(this.text);
            if (TLObject.hasFlag(this.flags, 1) == false) goto L15;
            r5.writeString(this.mType.fwd_text);
        L15:
            r5.writeString(this.mType.url);
            r5.writeInt32(this.mType.button_id);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonUrl_layer223 extends TL_keyboardButtonUrl_layer228 {
        public static final int constructor = 629866245;

        private TL_keyboardButtonUrl_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonUrl_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonUrl_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_inlineButtonTypeUrl r1 = this.mType;
            r1.url = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonUrl_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeString(this.mType.url);
        }
    }

    public static class TL_keyboardButtonUrl_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -670292500;
        public final TL_inlineButtonTypeUrl mType;

        public TL_keyboardButtonUrl_layer228() {
            TL_inlineButtonTypeUrl r0 = new TL_inlineButtonTypeUrl();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_inlineButtonTypeUrl r2 = this.mType;
            r2.url = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeString(this.mType.url);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonUserProfile_layer223 extends TL_keyboardButtonUserProfile_layer228 {
        public static final int constructor = 814112961;

        private TL_keyboardButtonUserProfile_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonUserProfile_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonUserProfile_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_inlineButtonTypeUserProfile r1 = this.mType;
            r1.user_id = r2.readInt64(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonUserProfile_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(constructor);
            r3.writeString(this.text);
            r3.writeInt64(this.mType.user_id);
        }
    }

    public static class TL_keyboardButtonUserProfile_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -1057137399;
        public final TL_inlineButtonTypeUserProfile mType;

        public TL_keyboardButtonUserProfile_layer228() {
            TL_inlineButtonTypeUserProfile r0 = new TL_inlineButtonTypeUserProfile();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_inlineButtonTypeUserProfile r2 = this.mType;
            r2.user_id = r3.readInt64(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeInt64(this.mType.user_id);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButtonWebView_layer223 extends TL_keyboardButtonWebView_layer228 {
        public static final int constructor = 326529584;

        private TL_keyboardButtonWebView_layer223() {
        }

        public /* synthetic */ TL_keyboardButtonWebView_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonWebView_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.text = r2.readString(r3);
            TL_inlineButtonTypeWebView r1 = this.mType;
            r1.url = r2.readString(r3);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButtonWebView_layer228, org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
            r2.writeString(this.mType.url);
        }
    }

    public static class TL_keyboardButtonWebView_layer228 extends TL_keyboardInlineButton {
        public static final int constructor = -398020192;
        public final TL_inlineButtonTypeWebView mType;

        public TL_keyboardButtonWebView_layer228() {
            TL_inlineButtonTypeWebView r0 = new TL_inlineButtonTypeWebView();
            this.mType = r0;
            this.type = r0;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            TL_inlineButtonTypeWebView r2 = this.mType;
            r2.url = r3.readString(r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            r4.writeString(this.mType.url);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardButton_layer223 extends TL_keyboardButton {
        public static final int constructor = -1560655744;

        private TL_keyboardButton_layer223() {
        }

        public /* synthetic */ TL_keyboardButton_layer223(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r1, boolean r2) {
            this.text = r1.readString(r2);
            this.type = new TL_buttonTypeDefault();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            r2.writeString(this.text);
        }
    }

    public static class TL_keyboardButton_layer228 extends TL_keyboardButton {
        public static final int constructor = 2098662655;

        private TL_keyboardButton_layer228() {
        }

        public /* synthetic */ TL_keyboardButton_layer228(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            this.type = new TL_buttonTypeDefault();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardInlineButton extends KeyboardInlineButton {
        public static final int constructor = 297902882;

        public TL_keyboardInlineButton() {
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public String getText() {
            return this.text;
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.KeyboardButtonProto
        public ButtonTypeProto getType() {
            return this.type;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            this.type = InlineButtonType.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            this.type.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public static class TL_keyboardInlineButtonRow extends KeyboardInlineButtonRow {
        public static final int constructor = 423758582;

        public TL_keyboardInlineButtonRow() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            this.buttons = Vector.deserialize(r2, new TL_keyboard$TL_keyboardInlineButtonRow$$ExternalSyntheticLambda0(), r3);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(constructor);
            Vector.serialize(r2, this.buttons);
        }
    }

    public static class TL_keyboardInlineButtonRow_layer228 extends TL_keyboardInlineButtonRow {
        public static final int constructor = 2002815875;

        /* renamed from: $r8$lambda$Lzq_D-ZORgLZNH96BDFHYOdkw5Q, reason: not valid java name */
        public static /* synthetic */ TLObject m5398$r8$lambda$Lzq_DZORgLZNH96BDFHYOdkw5Q(InputSerializedData r0, int r1, boolean r2) {
            return TL_keyboard.m5397$$Nest$smTLdeserializeLegacy(r0, r1, r2);
        }

        private TL_keyboardInlineButtonRow_layer228() {
        }

        public /* synthetic */ TL_keyboardInlineButtonRow_layer228(TL_keyboardIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButtonRow, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r4, boolean r5) {
            ArrayList r42 = Vector.deserialize(r4, new TL_keyboard$TL_keyboardInlineButtonRow_layer228$$ExternalSyntheticLambda0(), r5);
            this.buttons = new ArrayList(r42.size());
            int r52 = r42.size();
            int r0 = 0;
        L3:
            if (r0 >= r52) goto L7;
            Object r1 = r42.get(r0);
            r0 = r0 + 1;
            TLObject r12 = (TLObject) r1;
            if ((r12 instanceof KeyboardInlineButton) == false) goto L3;
            this.buttons.add((KeyboardInlineButton) r12);
            goto L3
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButtonRow, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            r2.writeInt32(2002815875);
            Vector.serialize(r2, this.buttons);
        }
    }

    public static class TL_keyboardInlineButton_legacy extends TL_keyboardInlineButton {
        public static final int constructor = -1675883435;

        public TL_keyboardInlineButton_legacy() {
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            int r0 = r3.readInt32(r4);
            this.flags = r0;
            if (TLObject.hasFlag(r0, 1024) == false) goto L5;
            this.style = KeyboardButtonStyle.TLdeserialize(r3, r3.readInt32(r4), r4);
        L5:
            this.text = r3.readString(r4);
            this.type = InlineButtonType.TLdeserialize(r3, r3.readInt32(r4), r4);
        }

        @Override // org.telegram.tgnet.tl.TL_keyboard.TL_keyboardInlineButton, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(constructor);
            int r0 = this.flags;
            if (this.style == null) goto L5;
            boolean r1 = true;
        L6:
            int r02 = TLObject.setFlag(r0, 1024, r1);
            this.flags = r02;
            r4.writeInt32(r02);
            if (TLObject.hasFlag(this.flags, 1024) == false) goto L9;
            this.style.serializeToStream(r4);
        L9:
            r4.writeString(this.text);
            this.type.serializeToStream(r4);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* renamed from: -$$Nest$smTLdeserializeLegacy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLObject m5397$$Nest$smTLdeserializeLegacy(InputSerializedData r0, int r1, boolean r2) {
        return TLdeserializeLegacy(r0, r1, r2);
    }

    private TL_keyboard() {
    }

    private static TLObject TLdeserializeLegacy(InputSerializedData r1, int r2, boolean r3) {
        KeyboardButton r0 = KeyboardButton.fromConstructor(r2);
        if (r0 == null) goto L6;
        r0.readParams(r1, r3);
        return r0;
    L6:
        KeyboardInlineButton r02 = KeyboardInlineButton.fromConstructor(r2);
        if (r02 == null) goto L10;
        r02.readParams(r1, r3);
        return r02;
    L10:
        TLParseException.doThrowOrLog(r1, KeyboardButtonProto.class.getName(), r2, r3);
        return null;
    }
}
