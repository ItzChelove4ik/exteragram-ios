package org.telegram.messenger;

import com.android.dx.rop.code.RegisterSpec;
import java.util.ArrayList;
import org.telegram.messenger.DocumentObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes.dex */
public class ImageLocation {
    public static final int TYPE_BIG = 0;
    public static final int TYPE_SMALL = 1;
    public static final int TYPE_STRIPPED = 2;
    public static final int TYPE_VIDEO_BIG = 4;
    public static final int TYPE_VIDEO_SMALL = 3;
    public long access_hash;
    public long currentSize;
    public int dc_id;
    public TLRPC.Document document;
    public long documentId;
    public byte[] file_reference;
    public int imageType;
    public WebInstantView.WebPhoto instantFile;
    public byte[] iv;
    public byte[] key;
    public TLRPC.TL_fileLocationToBeDeprecated location;
    public String path;
    public TLRPC.Photo photo;
    public long photoId;
    public TLRPC.InputPeer photoPeer;
    public int photoPeerType;
    public TLRPC.PhotoSize photoSize;
    public SecureDocument secureDocument;
    public TLRPC.InputStickerSet stickerSet;
    public String thumbSize;
    public int thumbVersion;
    public long videoSeekTo;
    public WebFile webFile;

    public ImageLocation() {
    }

    public static ImageLocation getForChat(int r10, TLRPC.Chat r11, int r12) {
        if (r11 == null) goto L34;
        TLRPC.ChatPhoto r0 = r11.photo;
        if (r0 != null) goto L8;
        return null;
    L8:
        if (r12 == 2) goto L10;
        if (r12 != 0) goto L17;
        TLRPC.FileLocation r02 = r0.photo_big;
    L16:
        TLRPC.FileLocation r1 = r02;
        if (r1 != null) goto L21;
        return null;
    L21:
        if (ChatObject.isChannel(r11) == true) goto L23;
        TLRPC.InputPeer r102 = new TLRPC.TL_inputPeerChat();
        r102.chat_id = r11.id;
    L26:
        TLRPC.InputPeer r5 = r102;
        int r103 = r11.photo.dc_id;
        if (r103 != 0) goto L32;
        r103 = r1.dc_id;
    L32:
        ImageLocation r104 = getForPhoto(r1, 0, null, null, r5, r12, r103, null, null);
        r104.photoId = r11.photo.photo_id;
        return r104;
    L23:
        if (r11.access_hash != 0) goto L25;
        return null;
    L25:
        r102 = new TLRPC.TL_inputPeerChannel();
        r102.channel_id = r11.id;
        r102.access_hash = r11.access_hash;
        goto L26
    L17:
        r02 = r0.photo_small;
        goto L16
    L10:
        if (r0.stripped_thumb != null) goto L12;
        return null;
    L12:
        ImageLocation r105 = new ImageLocation();
        TLRPC.TL_photoStrippedSize r122 = new TLRPC.TL_photoStrippedSize();
        r105.photoSize = r122;
        r122.type = "s";
        r122.bytes = r11.photo.stripped_thumb;
        return r105;
    L34:
        return null;
    }

    public static ImageLocation getForChat(TLRPC.Chat r1, int r2) {
        return getForChat(UserConfig.selectedAccount, r1, r2);
    }

    public static ImageLocation getForDocument(TLRPC.Document r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        r0.document = r3;
        r0.key = r3.key;
        r0.iv = r3.iv;
        r0.currentSize = r3.size;
        return r0;
    }

    public static ImageLocation getForDocument(TLRPC.PhotoSize r10, TLRPC.Document r11) {
        if ((r10 instanceof TLRPC.TL_photoStrippedSize) == false) goto L5;
    L14:
        ImageLocation r112 = new ImageLocation();
        r112.photoSize = r10;
        return r112;
    L5:
        if ((r10 instanceof TLRPC.TL_photoPathSize) == true) goto L14;
        if (r10 == null) goto L12;
        if (r11 != null) goto L11;
        return null;
    L11:
        return getForPhoto(r10.location, r10.size, null, r11, null, 1, r11.dc_id, null, r10.type);
    L12:
        return null;
    }

    public static ImageLocation getForDocument(TLRPC.VideoSize r9, TLRPC.Document r10) {
        if (r9 == null) goto L11;
        if (r10 == null) goto L13;
        ImageLocation r102 = getForPhoto(r9.location, r9.size, null, r10, null, 1, r10.dc_id, null, r9.type);
        if ("f".equals(r9.type) == false) goto L9;
        r102.imageType = 1;
        return r102;
    L9:
        r102.imageType = 2;
        return r102;
    L13:
        return null;
    L11:
        return null;
    }

    public static ImageLocation getForInstantFile(WebInstantView.WebPhoto r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        r0.instantFile = r1;
        return r0;
    }

    public static ImageLocation getForLocal(TLRPC.FileLocation r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        TLRPC.TL_fileLocationToBeDeprecated r1 = new TLRPC.TL_fileLocationToBeDeprecated();
        r0.location = r1;
        r1.local_id = r4.local_id;
        r1.volume_id = r4.volume_id;
        r1.secret = r4.secret;
        r1.dc_id = r4.dc_id;
        return r0;
    }

    public static ImageLocation getForMessage(TLRPC.PhotoSize r0, TLRPC.Message r1) {
        if ((r0 instanceof TLRPC.TL_photoStrippedSize) == false) goto L5;
    L9:
        ImageLocation r12 = new ImageLocation();
        r12.photoSize = r0;
        return r12;
    L5:
        if ((r0 instanceof TLRPC.TL_photoPathSize) == true) goto L9;
        return null;
    }

    public static ImageLocation getForObject(TLRPC.PhotoSize r1, TLObject r2) {
        if ((r2 instanceof TLRPC.Photo) == false) goto L7;
        return getForPhoto(r1, (TLRPC.Photo) r2);
    L7:
        if ((r2 instanceof TLRPC.Document) == false) goto L11;
        return getForDocument(r1, (TLRPC.Document) r2);
    L11:
        if ((r2 instanceof TLRPC.Message) == true) goto L13;
        return null;
    L13:
        return getForMessage(r1, (TLRPC.Message) r2);
    }

    public static ImageLocation getForPath(String r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        r0.path = r1;
        return r0;
    }

    private static ImageLocation getForPhoto(TLRPC.FileLocation r3, int r4, TLRPC.Photo r5, TLRPC.Document r6, TLRPC.InputPeer r7, int r8, int r9, TLRPC.InputStickerSet r10, String r11) {
        if (r3 == null) goto L19;
        if (r5 != null) goto L8;
        if (r7 != null) goto L8;
        if (r10 != null) goto L8;
        if (r6 != null) goto L8;
        return null;
    L8:
        ImageLocation r0 = new ImageLocation();
        r0.dc_id = r9;
        r0.photo = r5;
        r0.currentSize = r4;
        r0.photoPeer = r7;
        r0.photoPeerType = r8;
        r0.stickerSet = r10;
        if ((r3 instanceof TLRPC.TL_fileLocationToBeDeprecated) == false) goto L17;
        r0.location = (TLRPC.TL_fileLocationToBeDeprecated) r3;
        if (r5 == null) goto L14;
        r0.file_reference = r5.file_reference;
        r0.access_hash = r5.access_hash;
        r0.photoId = r5.id;
        r0.thumbSize = r11;
        return r0;
    L14:
        if (r6 == null) goto L16;
        r0.file_reference = r6.file_reference;
        r0.access_hash = r6.access_hash;
        r0.documentId = r6.id;
        r0.thumbSize = r11;
    L16:
        return r0;
    L17:
        TLRPC.TL_fileLocationToBeDeprecated r42 = new TLRPC.TL_fileLocationToBeDeprecated();
        r0.location = r42;
        r42.local_id = r3.local_id;
        r42.volume_id = r3.volume_id;
        r42.secret = r3.secret;
        r0.dc_id = r3.dc_id;
        r0.file_reference = r3.file_reference;
        r0.key = r3.key;
        r0.iv = r3.iv;
        r0.access_hash = r3.secret;
        return r0;
    L19:
        return null;
    }

    public static ImageLocation getForPhoto(TLRPC.PhotoSize r10, TLRPC.Photo r11) {
        if ((r10 instanceof TLRPC.TL_photoStrippedSize) == false) goto L5;
    L18:
        ImageLocation r112 = new ImageLocation();
        r112.photoSize = r10;
        return r112;
    L5:
        if ((r10 instanceof TLRPC.TL_photoPathSize) == true) goto L18;
        if (r10 == null) goto L16;
        if (r11 == null) goto L20;
        int r0 = r11.dc_id;
        if (r0 == 0) goto L13;
    L12:
        int r7 = r0;
        return getForPhoto(r10.location, r10.size, r11, null, null, 1, r7, null, r10.type);
    L13:
        r0 = r10.location.dc_id;
        goto L12
    L20:
        return null;
    L16:
        return null;
    }

    public static ImageLocation getForPhoto(TLRPC.VideoSize r9, TLRPC.Photo r10) {
        if (r9 == null) goto L9;
        if (r10 == null) goto L11;
        ImageLocation r102 = getForPhoto(r9.location, r9.size, r10, null, null, 1, r10.dc_id, null, r9.type);
        r102.imageType = 2;
        if ((r9.flags & 1) == 0) goto L8;
        r102.videoSeekTo = (int) (r9.video_start_ts * 1000.0d);
    L8:
        return r102;
    L11:
        return null;
    L9:
        return null;
    }

    public static ImageLocation getForSecureDocument(SecureDocument r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        r0.secureDocument = r1;
        return r0;
    }

    public static ImageLocation getForSticker(TLRPC.PhotoSize r10, TLRPC.Document r11, int r12) {
        if ((r10 instanceof TLRPC.TL_photoStrippedSize) == false) goto L5;
    L23:
        ImageLocation r112 = new ImageLocation();
        r112.photoSize = r10;
        return r112;
    L5:
        if ((r10 instanceof TLRPC.TL_photoPathSize) == true) goto L23;
        if (r10 == null) goto L22;
        if (r11 == null) goto L22;
        TLRPC.InputStickerSet r8 = MediaDataController.getInputStickerSet(r11);
        if (r8 != null) goto L14;
        return null;
    L14:
        ImageLocation r113 = getForPhoto(r10.location, r10.size, null, null, null, 1, r11.dc_id, r8, r10.type);
        if (r10.type.equalsIgnoreCase("a") == false) goto L18;
        r113.imageType = 1;
    L20:
        r113.thumbVersion = r12;
        return r113;
    L18:
        if (r10.type.equalsIgnoreCase(RegisterSpec.PREFIX) == false) goto L20;
        r113.imageType = 2;
    L22:
        return null;
    }

    public static ImageLocation getForStickerSet(TLRPC.StickerSet r11) {
        if (r11 != null) goto L5;
        return null;
    L5:
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r11.thumbs, 90);
        if (r1 != null) goto L9;
        return null;
    L9:
        if (r11.access_hash == 0) goto L12;
        TLRPC.InputStickerSet r0 = new TLRPC.TL_inputStickerSetID();
        r0.id = r11.id;
        r0.access_hash = r11.access_hash;
    L11:
        TLRPC.InputStickerSet r9 = r0;
        TLRPC.FileLocation r2 = r1.location;
        return getForPhoto(r2, r1.size, null, null, null, 1, r2.dc_id, r9, r1.type);
    L12:
        r0 = new TLRPC.TL_inputStickerSetShortName();
        r0.short_name = r11.short_name;
        goto L11
    }

    public static ImageLocation getForUser(int r11, TLRPC.User r12, int r13) {
        if (r12 == null) goto L96;
        TLRPC.UserProfilePhoto r1 = r12.photo;
        if (r1 == null) goto L96;
        if (r13 == 4) goto L42;
        if (r13 == 3) goto L42;
        if (r13 == 2) goto L15;
        if (r13 != 0) goto L22;
        TLRPC.FileLocation r14 = r1.photo_big;
    L21:
        TLRPC.FileLocation r2 = r14;
        if (r2 != null) goto L26;
        return null;
    L26:
        if (r12.access_hash == 0) goto L28;
        TLRPC.InputPeer r0 = new TLRPC.TL_inputPeerUser();
        r0.user_id = r12.id;
        r0.access_hash = r12.access_hash;
    L32:
        TLRPC.InputPeer r6 = r0;
        int r112 = r12.photo.dc_id;
        if (r112 != 0) goto L39;
        r112 = r2.dc_id;
    L39:
        ImageLocation r113 = getForPhoto(r2, 0, null, null, r6, r13, r112, null, null);
        r113.photoId = r12.photo.photo_id;
        return r113;
    L28:
        if (r12.fromMessageDialogId != 0) goto L30;
    L33:
        return null;
    L30:
        if (r12.fromMessageId == 0) goto L33;
        r0 = new TLRPC.TL_inputPeerUserFromMessage();
        r0.user_id = r12.id;
        r0.peer = MessagesController.getInstance(r11).getInputPeer(r12.fromMessageDialogId);
        r0.msg_id = r12.fromMessageId;
        goto L32
    L22:
        r14 = r1.photo_small;
        goto L21
    L15:
        if (r1.stripped_thumb != null) goto L17;
        return null;
    L17:
        ImageLocation r114 = new ImageLocation();
        TLRPC.TL_photoStrippedSize r132 = new TLRPC.TL_photoStrippedSize();
        r114.photoSize = r132;
        r132.type = "s";
        r132.bytes = r12.photo.stripped_thumb;
        return r114;
    L42:
        if (MessagesController.getInstance(r11).isPremiumUser(r12) == false) goto L96;
        if (r12.photo.has_video == false) goto L96;
        TLRPC.UserFull r115 = MessagesController.getInstance(r11).getUserFull(r12.id);
        if (r115 != null) goto L48;
    L81:
        TLRPC.Photo r116 = null;
    L82:
        if (r116 == null) goto L96;
        ArrayList<TLRPC.VideoSize> r122 = r116.video_sizes;
        if (r13 == 4) goto L86;
        TLRPC.VideoSize r123 = FileLoader.getClosestVideoSizeWithSize(r122, 100);
        int r133 = 0;
    L89:
        if (r133 >= r116.video_sizes.size()) goto L95;
        if ("p".equals(r116.video_sizes.get(r133).type) == true) goto L92;
        r133 = r133 + 1;
        goto L89
    L92:
        r123 = r116.video_sizes.get(r133);
    L95:
        return getForPhoto(r123, r116);
    L86:
        return getForPhoto(FileLoader.getClosestVideoSizeWithSize(r122, 1000), r116);
    L48:
        if (r12.photo.personal == false) goto L56;
        TLRPC.Photo r134 = r115.personal_photo;
        if (r134 == null) goto L56;
        ArrayList<TLRPC.VideoSize> r135 = r134.video_sizes;
        if (r135 == null) goto L56;
        if (r135.isEmpty() == true) goto L56;
        r116 = r115.personal_photo;
    L56:
        TLRPC.Photo r136 = r115.profile_photo;
        if (r136 != null) goto L59;
    L65:
        TLRPC.Photo r137 = r115.fallback_photo;
        if (r137 != null) goto L68;
    L74:
        TLRPC.Photo r124 = r115.profile_photo;
        if (r124 == null) goto L81;
        ArrayList<TLRPC.VideoSize> r125 = r124.video_sizes;
        if (r125 == null) goto L81;
        if (r125.isEmpty() == true) goto L81;
        r116 = r115.profile_photo;
        goto L82
    L68:
        if (r137.id != r12.photo.photo_id) goto L74;
        ArrayList<TLRPC.VideoSize> r126 = r137.video_sizes;
        if (r126 == null) goto L74;
        if (r126.isEmpty() == true) goto L74;
        r116 = r115.fallback_photo;
        goto L82
    L59:
        if (r136.id != r12.photo.photo_id) goto L65;
        ArrayList<TLRPC.VideoSize> r138 = r136.video_sizes;
        if (r138 == null) goto L65;
        if (r138.isEmpty() == true) goto L65;
        r116 = r115.profile_photo;
    L96:
        return null;
    }

    public static ImageLocation getForUser(TLRPC.User r1, int r2) {
        return getForUser(UserConfig.selectedAccount, r1, r2);
    }

    public static ImageLocation getForUserOrChat(int r1, TLObject r2, int r3) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        return getForUser(r1, (TLRPC.User) r2, r3);
    L7:
        if ((r2 instanceof TLRPC.Chat) == true) goto L9;
        return null;
    L9:
        return getForChat(r1, (TLRPC.Chat) r2, r3);
    }

    public static ImageLocation getForUserOrChat(TLObject r1, int r2) {
        return getForUserOrChat(UserConfig.selectedAccount, r1, r2);
    }

    public static ImageLocation getForVideoPath(String r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        r0.path = r1;
        r0.imageType = 2;
        return r0;
    }

    public static ImageLocation getForWebFile(WebFile r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        ImageLocation r0 = new ImageLocation();
        if (r3.noproxy == false) goto L9;
        r0.path = r3.url;
        return r0;
    L9:
        r0.webFile = r3;
        r0.currentSize = r3.size;
        return r0;
    }

    public static String getStrippedKey(Object r2, Object r3, Object r4) {
        String r0 = getStrippedKeyInternal(r2, r3, r4);
        if (BuildVars.LOGS_ENABLED == true) goto L5;
    L19:
        return r0;
    L5:
        if ((r2 instanceof MessageObject) == false) goto L19;
        TLRPC.Message r22 = ((MessageObject) r2).messageOwner;
        if (r22 == null) goto L19;
        if (r22.rich_message == null) goto L19;
        StringBuilder r23 = new StringBuilder("[richmedia] strippedKey=");
        r23.append(r0);
        r23.append(" fullObject=");
        String r1 = "null";
        if (r3 != null) goto L13;
        String r32 = "null";
    L14:
        r23.append(r32);
        r23.append(" stripped=");
        if (r4 == null) goto L18;
        r1 = r4.getClass().getSimpleName();
    L18:
        r23.append(r1);
        FileLog.d(r23.toString());
        goto L19
    L13:
        r32 = r3.getClass().getSimpleName();
        goto L14
    }

    private static String getStrippedKeyInternal(Object r4, Object r5, Object r6) {
        if ((r4 instanceof TLRPC.WebPage) == true) goto L13;
        if ((r4 instanceof MessageObject) == false) goto L48;
        MessageObject r0 = (MessageObject) r4;
        if (r0.type == 29) goto L13;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L48;
        if (r02.rich_message != null) goto L13;
    L48:
        return "stripped" + FileRefController.getKeyForParentObject(r4);
    L13:
        if ((r5 instanceof ImageLocation) == false) goto L24;
        ImageLocation r03 = (ImageLocation) r5;
        Object r2 = r03.document;
        if (r2 == null) goto L17;
    L16:
        r5 = r2;
        goto L24
    L17:
        r2 = r03.photoSize;
        if (r2 != null) goto L16;
        TLRPC.Photo r04 = r03.photo;
        if (r04 == null) goto L24;
        r5 = r04;
    L24:
        if (r5 != null) goto L28;
        return "stripped" + FileRefController.getKeyForParentObject(r4) + "_" + r6;
    L28:
        if ((r5 instanceof TLRPC.Document) == false) goto L32;
        return "stripped" + FileRefController.getKeyForParentObject(r4) + "_" + ((TLRPC.Document) r5).id;
    L32:
        if ((r5 instanceof TLRPC.Photo) == false) goto L36;
        return "stripped" + FileRefController.getKeyForParentObject(r4) + "_" + ((TLRPC.Photo) r5).id;
    L36:
        if ((r5 instanceof TLRPC.PhotoSize) == false) goto L44;
        TLRPC.PhotoSize r52 = (TLRPC.PhotoSize) r5;
        if (r52.location == null) goto L42;
        return "stripped" + FileRefController.getKeyForParentObject(r4) + "_" + r52.location.local_id + "_" + r52.location.volume_id;
    L42:
        return "stripped" + FileRefController.getKeyForParentObject(r4);
    L44:
        if ((r5 instanceof TLRPC.FileLocation) == false) goto L48;
        TLRPC.FileLocation r53 = (TLRPC.FileLocation) r5;
        return "stripped" + FileRefController.getKeyForParentObject(r4) + "_" + r53.local_id + "_" + r53.volume_id;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String getKey(Object r5, Object r6, boolean r7) {
        if (this.secureDocument != null) goto L5;
        TLRPC.PhotoSize r0 = this.photoSize;
        if ((r0 instanceof TLRPC.TL_photoStrippedSize) == true) goto L48;
        if ((r0 instanceof TLRPC.TL_photoPathSize) == true) goto L48;
        if (this.location != null) goto L14;
        WebFile r52 = this.webFile;
        if (r52 != null) goto L18;
        WebInstantView.WebPhoto r53 = this.instantFile;
        if (r53 != null) goto L22;
        TLRPC.Document r54 = this.document;
        if (r54 == null) goto L43;
        if (r7 == true) goto L38;
        if ((r54 instanceof DocumentObject.ThemeDocument) == false) goto L38;
        DocumentObject.ThemeDocument r55 = (DocumentObject.ThemeDocument) r54;
        StringBuilder r62 = new StringBuilder();
        r62.append(this.document.dc_id);
        r62.append("_");
        r62.append(this.document.id);
        r62.append("_");
        r62.append(Theme.getBaseThemeKey(r55.themeSettings));
        r62.append("_");
        r62.append(r55.themeSettings.accent_color);
        r62.append("_");
        int r02 = 0;
        if (r55.themeSettings.message_colors.size() <= 1) goto L31;
        int r4 = r55.themeSettings.message_colors.get(1).intValue();
    L32:
        r62.append(r4);
        r62.append("_");
        if (r55.themeSettings.message_colors.size() <= 0) goto L35;
        r02 = r55.themeSettings.message_colors.get(0).intValue();
    L35:
        r62.append(r02);
        return r62.toString();
    L31:
        r4 = 0;
    L38:
        if (r54.id != 0) goto L40;
        return null;
    L40:
        if (r54.dc_id != 0) goto L42;
        return null;
    L42:
        return this.document.dc_id + "_" + this.document.id;
    L43:
        String r42 = this.path;
        if (r42 != null) goto L46;
        return null;
    L46:
        return Utilities.MD5(r42);
    L22:
        return Utilities.MD5(r53.url);
    L18:
        return Utilities.MD5(r52.url);
    L14:
        return this.location.volume_id + "_" + this.location.local_id;
    L48:
        if (r0.bytes.length <= 0) goto L58;
        if (r6 == 0) goto L53;
        ImageLocation this = r6;
    L53:
        return getStrippedKey(r5, this, r0);
    L58:
        return null;
    L5:
        return this.secureDocument.secureFile.dc_id + "_" + this.secureDocument.secureFile.id;
    }

    public long getSize() {
        TLRPC.PhotoSize r0 = this.photoSize;
        if (r0 == null) goto L7;
        int r2 = r0.size;
    L6:
        return r2;
    L7:
        SecureDocument r02 = this.secureDocument;
        if (r02 == null) goto L13;
        TLRPC.TL_secureFile r03 = r02.secureFile;
        if (r03 == null) goto L21;
        return r03.size;
    L21:
        return this.currentSize;
    L13:
        TLRPC.Document r04 = this.document;
        if (r04 != null) goto L16;
        WebFile r05 = this.webFile;
        if (r05 == null) goto L21;
        r2 = r05.size;
        goto L6
    L16:
        return r04.size;
    }

    public boolean isEncrypted() {
        if (this.key == null) goto L6;
        return true;
    L6:
        return false;
    }
}
