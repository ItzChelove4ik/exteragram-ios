package com.exteragram.messenger.export.output.html;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.camera.core.impl.Quirks$$ExternalSyntheticBackport0;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.export.api.ApiWrap$ActionChatEditPhoto;
import com.exteragram.messenger.export.api.ApiWrap$ActionSuggestProfilePhoto;
import com.exteragram.messenger.export.api.ApiWrap$Chat;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$Document;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$Game;
import com.exteragram.messenger.export.api.ApiWrap$GeoPoint;
import com.exteragram.messenger.export.api.ApiWrap$GiveawayResults;
import com.exteragram.messenger.export.api.ApiWrap$GiveawayStart;
import com.exteragram.messenger.export.api.ApiWrap$HistoryMessageMarkupButton;
import com.exteragram.messenger.export.api.ApiWrap$Invoice;
import com.exteragram.messenger.export.api.ApiWrap$Media;
import com.exteragram.messenger.export.api.ApiWrap$MediaData;
import com.exteragram.messenger.export.api.ApiWrap$Message;
import com.exteragram.messenger.export.api.ApiWrap$PaidMedia;
import com.exteragram.messenger.export.api.ApiWrap$Peer;
import com.exteragram.messenger.export.api.ApiWrap$Poll;
import com.exteragram.messenger.export.api.ApiWrap$Reaction;
import com.exteragram.messenger.export.api.ApiWrap$SharedContact;
import com.exteragram.messenger.export.api.ApiWrap$StoryData;
import com.exteragram.messenger.export.api.ApiWrap$Tag;
import com.exteragram.messenger.export.api.ApiWrap$TextPart;
import com.exteragram.messenger.export.api.ApiWrap$UnsupportedMedia;
import com.exteragram.messenger.export.api.ApiWrap$User;
import com.exteragram.messenger.export.api.ApiWrap$Venue;
import com.exteragram.messenger.export.api.DataTypesUtils;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.MediaTrack;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import com.google.zxing.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class HtmlContext {
    private final String _base;
    private boolean _closed;
    private final String _composedStart;
    private final OutputFile _file;
    private final ArrayList<ApiWrap$Tag> _tags;

    public static /* synthetic */ Dimension $r8$lambda$IDS2iDiaoey4r44KFrBNEkJs1Sk(boolean r8, int r9, int r10, int r11, int r12, Dimension r13) {
        if (r8 == false) goto L5;
        int r82 = 2;
    L6:
        int r2 = r13.getWidth() * r82;
        int r3 = r13.getHeight() * r82;
        if (r2 > r9) goto L11;
        if (r3 > r10) goto L11;
        Dimension r83 = new Dimension(r13.getWidth(), r13.getHeight());
    L13:
        if ((r83.getWidth() % 2) != 0) goto L15;
        int r102 = r83.getWidth();
    L16:
        int r132 = r83.getHeight() % 2;
        int r84 = r83.getHeight();
        if (r132 == 0) goto L20;
        r84 = r84 - 1;
    L20:
        Dimension r92 = new Dimension(r102, r84);
        if (r92.getWidth() < r11) goto L27;
        if (r92.getHeight() < r12) goto L27;
        return r92;
    L27:
        return new Dimension(0, 0);
    L15:
        r102 = r83.getWidth() - 1;
    L11:
        double r4 = r2;
        double r22 = r3;
        double r85 = Math.min(r9 / r4, r10 / r22);
        r83 = new Dimension((int) (r4 * r85), (int) (r22 * r85));
        goto L13
    L5:
        r82 = 1;
        goto L6
    }

    /* renamed from: $r8$lambda$NQmNSWBFypYJJj_9YIdSy44-Sh4, reason: not valid java name */
    public static /* synthetic */ String m1337$r8$lambda$NQmNSWBFypYJJj_9YIdSy44Sh4(Integer r2) {
        if (r2.intValue() <= 1) goto L7;
        return DataTypesUtils.NumberToString(r2.intValue()) + " votes";
    L7:
        if (r2.intValue() > 0) goto L9;
        return "No votes";
    L9:
        return DataTypesUtils.NumberToString(r2.intValue()) + " vote";
    }

    /* renamed from: $r8$lambda$pbHCCbDwwIyim-hpdjqWjW9ppqA, reason: not valid java name */
    public static /* synthetic */ String m1338$r8$lambda$pbHCCbDwwIyimhpdjqWjW9ppqA(Utilities.CallbackReturn r2, ApiWrap$Poll.Answer r3) {
        if (r3.votes() != 0) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        if (r3.my() == true) goto L11;
        return " <span class=\"details\">" + ((String) r2.run(Integer.valueOf(r3.votes()))) + "</span>";
    L11:
        return " <span class=\"details\">" + ((String) r2.run(Integer.valueOf(r3.votes()))) + ", chosen vote</span>";
    }

    public HtmlContext(String r2, String r3, OutputFile.Stats r4) {
        this._tags = new ArrayList();
        this._closed = false;
        this._file = new OutputFile(r2, r4);
        String r22 = r2.substring(r3.length() + 1);
        this._base = HtmlContext$$ExternalSyntheticBackport1.m("../", r22.length() - r22.replace("/", _UrlKt.FRAGMENT_ENCODE_SET).length());
        this._composedStart = composeStart();
    }

    public static Function<Dimension, Dimension> CalculateThumbSize(int r6, int r7, int r8, int r9, boolean r10) {
        return new HtmlContext$$ExternalSyntheticLambda4(r10, r6, r7, r8, r9);
    }

    public static String SerializeString(String r8) {
        StringBuilder r0 = new StringBuilder();
        int r1 = r8.length();
        ArrayList r2 = new ArrayList();
        char[] r82 = r8.toCharArray();
        int r3 = r82.length;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r5 >= r3) goto L6;
        r2.add(Character.valueOf(r82[r5]));
        r5 = r5 + 1;
    L6:
        if (r4 >= r2.size()) goto L49;
        char r83 = ((Character) r2.get(r4)).charValue();
        if (r83 != '\n') goto L11;
        r0.append("<br>");
    L47:
        r4 = r4 + 1;
        goto L6
    L11:
        if (r83 != '\"') goto L14;
        r0.append("&quot;");
        goto L47
    L14:
        if (r83 != '&') goto L17;
        r0.append("&amp;");
        goto L47
    L17:
        if (r83 != '\'') goto L20;
        r0.append("&apos;");
        goto L47
    L20:
        if (r83 != '<') goto L23;
        r0.append("&lt;");
        goto L47
    L23:
        if (r83 != '>') goto L25;
        r0.append("&gt;");
        goto L47
    L25:
        if (r83 < 0) goto L34;
        if (r83 >= ' ') goto L34;
        r0.append("&#x");
        r0.append((r83 >> 4) + 48);
        int r84 = r83 & 15;
        if (r84 < 10) goto L31;
        r0.append(r84 + 55);
    L32:
        r0.append(';');
        goto L47
    L31:
        r0.append(r84 + 48);
    L34:
        if (r83 != 226) goto L46;
        int r32 = r4 + 2;
        if (r32 >= r1) goto L46;
        if (((Character) r2.get(r4 + 1)).charValue() != 128) goto L46;
        if (((Character) r2.get(r32)).charValue() != 168) goto L43;
        r0.append("<br>");
        goto L47
    L43:
        if (((Character) r2.get(r32)).charValue() != 169) goto L45;
        r0.append("<br>");
        goto L47
    L45:
        r0.append(r83);
    L46:
        r0.append(r83);
        goto L47
    L49:
        return r0.toString();
    }

    public static Pair<String, Dimension> WriteImageThumb(String r9, String r10, Function<Dimension, Dimension> r11, String r12, int r13, String r14) {
        if (r14 != null) goto L5;
        r14 = "_thumb";
    L5:
        if (r10.isEmpty() == true) goto L7;
        String r0 = r9 + "/" + r10;
        BitmapFactory.Options r3 = new BitmapFactory.Options();
        Bitmap r4 = BitmapFactory.decodeFile(r0, r3);
        if (new File(r0).length() == 0) goto L38;
        int r5 = r3.outWidth;
        if (r5 >= 10000) goto L38;
        int r32 = r3.outHeight;
        if (r32 >= 10000) goto L38;
        Dimension r112 = r11.apply(new Dimension(r5, r32));
        if (r112 == null) goto L18;
        Bitmap r02 = Bitmap.createScaledBitmap(r4, r112.getWidth(), r112.getHeight(), true);
        if (r13 != (-1)) goto L22;
        r13 = 100;
    L22:
        int r33 = r10.indexOf(46, r10.lastIndexOf(47) + 1);
        if (r33 < 0) goto L25;
        String r102 = r10.substring(0, r33) + r14 + r10.substring(r33);
    L26:
        String r103 = OutputFile.PrepareRelativePath(r9, r102);
        File r142 = new File(r9 + "/" + r103);     // Catch: Exception -> L30
        r142.createNewFile();     // Catch: Exception -> L30
        FileOutputStream r92 = new FileOutputStream(r142);     // Catch: Exception -> L30
        if ("PNG".equals(r12) == false) goto L32;
        Bitmap.CompressFormat r122 = Bitmap.CompressFormat.PNG;     // Catch: Exception -> L30
    L33:
        r02.compress(r122, r13, r92);     // Catch: Exception -> L30
        r92.flush();     // Catch: Exception -> L30
        r92.close();     // Catch: Exception -> L30
        r4.recycle();     // Catch: Exception -> L30
        r02.recycle();     // Catch: Exception -> L30
        return new Pair(r103, r112);
    L32:
        r122 = Bitmap.CompressFormat.JPEG;     // Catch: Exception -> L30
    L30:
        e = move-exception;
        FileLog.e(e);
        e.printStackTrace();
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L25:
        r102 = r10.concat(r14);
        goto L26
    L18:
        return new Pair(null, null);
    L38:
        Log.e("exteraGram", "width or height are more than 10000, path: ".concat(r0));
        return new Pair(null, null);
    L7:
        return new Pair(null, null);
    }

    private static String countryToEmoji(String r3) {
        int r0 = Character.codePointAt(r3, 0) - (-127397);
        int r32 = Character.codePointAt(r3, 1) - (-127397);
        return new String(Character.toChars(r0)).concat(new String(Character.toChars(r32)));
    }

    public static String pathWithRelativePath(String r2) {
        return FileManager.defaultSavePath + "/" + r2;
    }

    private ApiWrap$MediaData prepareMediaData(ApiWrap$Message r10, String r11, HashMap<Long, ApiWrap$Peer> r12, String r13) {
        ApiWrap$MediaData r9 = new ApiWrap$MediaData();
        TLRPC.MessageAction r112 = r10.action;
        boolean r0 = r112 instanceof TLRPC.TL_messageActionPhoneCall;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == false) goto L25;
        TLRPC.TL_messageActionPhoneCall r113 = (TLRPC.TL_messageActionPhoneCall) r112;
        r9.classes = "media_call";
        if (r10.out == false) goto L7;
        long r2 = r10.peerId;
    L8:
        ApiWrap$Peer r122 = r12.get(Long.valueOf(r2));
        if (r122 == null) goto L11;
        r1 = r122.name();
    L11:
        r9.title = r1;
        TLRPC.PhoneCallDiscardReason r123 = r113.reason;
        boolean r132 = r123 instanceof TLRPC.TL_phoneCallDiscardReasonMissed;
        boolean r124 = r123 instanceof TLRPC.TL_phoneCallDiscardReasonBusy;
        if (r10.out == false) goto L16;
        if (r132 == false) goto L15;
        String r102 = "Cancelled";
    L21:
        r9.status = r102;
        if (r113.duration <= 0) goto L138;
        r9.classes += " success";
        r9.status += " (" + LocaleController.formatCallDuration(r113.duration) + " seconds)";
        return r9;
    L138:
        return r9;
    L15:
        r102 = "Outgoing";
        goto L21
    L16:
        if (r132 == false) goto L18;
        r102 = "Missed";
        goto L21
    L18:
        if (r124 == false) goto L20;
        r102 = "Declined";
        goto L21
    L20:
        r102 = "Incoming";
        goto L21
    L7:
        r2 = r10.selfId;
        goto L8
    L25:
        ApiWrap$Media r114 = r10.media;
        Object r02 = r114.content;
        String r3 = "Expired";
        if ((r02 instanceof HtmlWriter.Photo) == false) goto L35;
        HtmlWriter.Photo r03 = (HtmlWriter.Photo) r02;
        if (r114.ttl == 0) goto L138;
        r9.title = "Self-destructing photo";
        if (r03.id != 0) goto L32;
        r3 = "Please view it on your mobile";
    L32:
        r9.status = r3;
        r9.classes = "media_photo";
        return r9;
    L35:
        if ((r02 instanceof ApiWrap$Document) == false) goto L94;
        ApiWrap$Document r04 = (ApiWrap$Document) r02;
        if (r114.ttl == 0) goto L43;
        r9.title = "Self-destructing video";
        if (r04.id == 0) goto L41;
        r3 = "Please view it on your mobile";
    L41:
        r9.status = r3;
        r9.classes = "media_video";
        return r9;
    L43:
        boolean r115 = r04.file.relativePath.isEmpty();
        r9.link = r04.file.relativePath;
        r9.description = DataTypesUtils.NoFileDescription(r10.skipReason);
        if (r04.isSticker == true) goto L138;
        if (r04.isVideoMessage == false) goto L54;
        r9.title = "Video message";
        r9.status = LocaleController.formatDuration(r04.duration);
        if (r115 == false) goto L51;
        r9.status += ", " + AndroidUtilities.formatFileSize(r04.file.size);
    L51:
        r9.thumb = r04.thumb.file.relativePath;
        r9.classes = "media_video";
        return r9;
    L54:
        if (r04.isVoiceMessage == false) goto L61;
        r9.title = "Voice message";
        r9.status = LocaleController.formatDuration(r04.duration);
        if (r115 == false) goto L58;
        r9.status += ", " + AndroidUtilities.formatFileSize(r04.file.size);
    L58:
        r9.classes = "media_voice_message";
        return r9;
    L61:
        if (r04.isAnimated == true) goto L138;
        if (r04.isVideoFile == true) goto L138;
        if (r04.isAudioFile == false) goto L84;
        String r103 = r04.songPerformer;
        if (r103 != null) goto L71;
    L78:
        String r104 = "Audio file";
    L79:
        r9.title = r104;
        r9.status = AndroidUtilities.formatLongDuration(r04.duration);
        if (r115 == false) goto L82;
        r9.status += ", " + AndroidUtilities.formatFileSize(r04.file.size);
    L82:
        r9.classes = "media_audio_file";
        return r9;
    L71:
        if (r103.isEmpty() == true) goto L78;
        String r105 = r04.songTitle;
        if (r105 == null) goto L78;
        if (r105.isEmpty() == true) goto L78;
        r104 = r04.songPerformer + " – " + r04.songTitle;
        goto L79
    L84:
        String r106 = r04.name;
        if (r106 != null) goto L87;
    L90:
        String r107 = "File";
    L91:
        r9.title = r107;
        r9.status = AndroidUtilities.formatFileSize(r04.file.size);
        r9.classes = "media_file";
        return r9;
    L87:
        if (r106.isEmpty() == true) goto L90;
        r107 = r04.name;
        goto L91
    L94:
        if ((r02 instanceof ApiWrap$SharedContact) == false) goto L104;
        ApiWrap$SharedContact r05 = (ApiWrap$SharedContact) r02;
        r9.title = r05.info.firstName + " " + r05.info.lastName;
        r9.classes = "media_contact";
        r9.status = PhoneFormat.getInstance().format(r05.info.phoneNumber);
        ApiWrap$File r108 = r05.vcard;
        if (r108 == null) goto L138;
        byte[] r109 = r108.content;
        if (r109 == null) goto L138;
        if (r109.length <= 0) goto L138;
        r9.status += " - vCard";
        r9.link = r05.vcard.relativePath;
        return r9;
    L104:
        if ((r02 instanceof ApiWrap$GeoPoint) == false) goto L112;
        ApiWrap$GeoPoint r06 = (ApiWrap$GeoPoint) r02;
        if (r114.ttl == 0) goto L108;
        r9.classes = "media_live_location";
        r9.title = "Live location";
        r9.status = _UrlKt.FRAGMENT_ENCODE_SET;
    L109:
        String r1010 = String.valueOf(r06.latitude);
        String r116 = String.valueOf(r06.longitude);
        String r125 = r1010 + ',' + r116;
        r9.status = r1010 + ", " + r116;
        r9.link = "https://maps.google.com/maps?q=" + r125 + "&ll=" + r125 + "&z=16";
        return r9;
    L108:
        r9.classes = "media_location";
        r9.title = "Location";
        goto L109
    L112:
        if ((r02 instanceof ApiWrap$Venue) == false) goto L120;
        ApiWrap$Venue r07 = (ApiWrap$Venue) r02;
        r9.classes = "media_venue";
        r9.title = r07.title;
        r9.description = r07.address;
        ApiWrap$GeoPoint r1011 = r07.point;
        if (r1011 == null) goto L138;
        if (r1011.valid == false) goto L138;
        String r1012 = String.valueOf(r1011.latitude) + ',' + String.valueOf(r07.point.longitude);
        r9.link = "https://maps.google.com/maps?q=" + r1012 + "&ll=" + r1012 + "&z=16";
        return r9;
    L120:
        if ((r02 instanceof ApiWrap$Game) == false) goto L140;
        ApiWrap$Game r08 = (ApiWrap$Game) r02;
        r9.classes = "media_game";
        r9.title = r08.title;
        r9.description = r08.description;
        if (r08.botId == 0) goto L138;
        String r1013 = r08.shortName;
        if (r1013 == null) goto L138;
        if (r1013.isEmpty() == true) goto L138;
        ApiWrap$Peer r1014 = r12.get(Long.valueOf(r08.botId));
        if (r1014 == null) goto L138;
        ApiWrap$User r117 = r1014.user;
        if (r117 == null) goto L138;
        if (r117.isBot == false) goto L138;
        String r118 = r117.username;
        if (r118 == null) goto L138;
        if (r118.isEmpty() == true) goto L138;
        String r1015 = r13 + r1014.user.username + "?game=" + r08.shortName;
        r9.link = r1015;
        r9.status = r1015;
        goto L138
    L140:
        if ((r02 instanceof ApiWrap$Invoice) == false) goto L144;
        ApiWrap$Invoice r09 = (ApiWrap$Invoice) r02;
        r9.classes = "media_invoice";
        r9.title = r09.title;
        r9.description = r09.description;
        r9.status = LocaleController.getInstance().formatCurrencyString(r09.amount, r09.currency);
        return r9;
    L144:
        if ((r02 instanceof ApiWrap$PaidMedia) == false) goto L146;
        r9.classes = "media_invoice";
        r9.status = LocaleController.getInstance().formatCurrencyString(((ApiWrap$PaidMedia) r02).stars, "XTR");
    L146:
        return r9;
    }

    private String pushAnimatedMedia(ApiWrap$Document r8, String r9) {
        Dimension r92 = new Dimension(r8.width, r8.height);
        Function<Dimension, Dimension> r0 = CalculateThumbSize(520, 520, 80, 80, true);
        if (r8.thumb.file.relativePath.isEmpty() == false) goto L5;
    L13:
        ApiWrap$MediaData r93 = new ApiWrap$MediaData();
        r93.title = "Animation";
        r93.status = AndroidUtilities.formatFileSize(r8.file.size);
        ApiWrap$File r82 = r8.file;
        r93.link = r82.relativePath;
        r93.description = DataTypesUtils.NoFileDescription(r82.skipReason);
        r93.classes = "media_video";
        return pushGenericMedia(r93);
    L5:
        if (r8.file.relativePath.isEmpty() == true) goto L13;
        if (r0.apply(r92).getWidth() == 0) goto L13;
        if (r0.apply(r92).getHeight() == 0) goto L13;
        StringBuilder r1 = new StringBuilder(pushDiv("media_wrap clearfix"));
        r1.append(pushTag("a", new Pair[]{new Pair("class", "animated_wrap clearfix pull_left"), new Pair("href", relativePath(r8.file.relativePath))}));
        r1.append(pushDiv("video_play_bg"));
        r1.append(pushDiv("gif_play"));
        r1.append("GIF");
        r1.append(popTag());
        r1.append(popTag());
        r1.append(pushTag("img", new Pair[]{new Pair("class", "animated"), new Pair("style", "width: " + DataTypesUtils.NumberToString(r0.apply(r92).getWidth() / 2) + "px; height: " + DataTypesUtils.NumberToString(r0.apply(r92).getHeight() / 2) + "px"), new Pair("src", r8.thumb.file.relativePath), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r1.append(popTag());
        r1.append(popTag());
        return r1.toString();
    }

    private String pushGenericMedia(ApiWrap$MediaData r7) {
        StringBuilder r0 = new StringBuilder(pushDiv("media_wrap clearfix"));
        if (r7.link.isEmpty() == false) goto L5;
        r0.append(pushDiv("media clearfix pull_left " + r7.classes));
    L14:
        if (r7.thumb.isEmpty() == false) goto L16;
        r0.append(pushDiv("fill pull_left"));
        r0.append(popTag());
    L17:
        r0.append(pushDiv("body"));
        if (r7.title.isEmpty() == true) goto L21;
        r0.append(pushDiv("title bold"));
        r0.append(SerializeString(r7.title));
        r0.append(popTag());
    L21:
        if (r7.description.isEmpty() == true) goto L24;
        r0.append(pushDiv(MediaTrack.ROLE_DESCRIPTION));
        r0.append(SerializeString(r7.description));
        r0.append(popTag());
    L24:
        if (r7.status.isEmpty() == true) goto L26;
        r0.append(pushDiv("status details"));
        r0.append(SerializeString(r7.status));
        r0.append(popTag());
    L26:
        r0.append(popTag());
        r0.append(popTag());
        r0.append(popTag());
        return r0.toString();
    L16:
        r0.append(pushTag("img", new Pair[]{new Pair("class", "thumb pull_left"), new Pair("src", relativePath(r7.thumb)), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        goto L17
    L5:
        Pair r1 = new Pair("class", "media clearfix pull_left block_link " + r7.classes);
        if (r7.link.toLowerCase().startsWith("http://") == false) goto L8;
    L11:
        String r4 = r7.link;
    L12:
        r0.append(pushTag("a", new Pair[]{r1, new Pair("href", r4)}));
        goto L14
    L8:
        if (r7.link.toLowerCase().startsWith("https://") == true) goto L11;
        r4 = relativePath(r7.link);
        goto L12
    }

    private String pushGiveaway(HashMap<Long, ApiWrap$Peer> r13, ApiWrap$GiveawayResults r14, Utilities.Callback2Return<Integer, String, String> r15) {
        StringBuilder r132 = new StringBuilder(pushDiv("media_wrap clearfix"));
        r132.append(pushDiv("media_giveaway"));
        r132.append(pushDiv("section_title bold"));
        if (r14.winnersCount <= 1) goto L5;
        String r1 = SerializeString("Winners Selected!");
    L6:
        r132.append(r1);
        r132.append(popTag());
        r132.append(pushDiv("section_body"));
        r132.append("<b>");
        r132.append(DataTypesUtils.NumberToString(r14.winnersCount));
        r132.append("</b> ");
        if (r14.winnersCount <= 1) goto L9;
        String r4 = "winners";
    L10:
        r132.append(SerializeString(r4));
        r132.append(" of the ");
        r132.append(r15.run(Integer.valueOf(r14.launchId), "Giveaway"));
        r132.append(" was randomly selected by Telegram.");
        r132.append(popTag());
        r132.append(pushDiv("section_title bold"));
        if (r14.winnersCount <= 1) goto L13;
        String r152 = SerializeString("Winners");
    L14:
        r132.append(r152);
        r132.append(popTag());
        r132.append(pushDiv("section_body"));
        ArrayList r153 = new ArrayList();
        ArrayList<Long> r0 = r14.winners;
        int r42 = r0.size();
        boolean r5 = false;
        int r6 = 0;
    L16:
        if (r6 >= r42) goto L18;
        Long r8 = r0.get(r6);
        r6 = r6 + 1;
        r153.add("<b>" + HtmlWriter.wrapPeerName(r8.longValue()) + "</b>");
        goto L16
    L18:
        int r02 = r14.winnersCount;
        int r43 = r14.winners.size();
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r02 <= r43) goto L21;
        String r03 = SerializeString(" and ") + DataTypesUtils.NumberToString(r14.winnersCount - r14.winners.size()) + SerializeString(" more!");
    L22:
        r132.append(Quirks$$ExternalSyntheticBackport0.m(", ", r153));
        r132.append(r03);
        r132.append(popTag());
        r132.append(pushDiv("section_body"));
        long r04 = r14.credits;
        if (r04 != 1) goto L26;
        r5 = true;
    L26:
        if (r04 == 0) goto L35;
        if (r14.winnersCount != 1) goto L35;
        StringBuilder r154 = new StringBuilder();
        r154.append(SerializeString("The winner received "));
        r154.append("<b>");
        r154.append(DataTypesUtils.NumberToString((int) r14.credits));
        r154.append("</b>");
        if (r5 == false) goto L32;
        String r142 = " Star.";
    L33:
        r154.append(SerializeString(r142));
        r62 = r154.toString();
    L51:
        r132.append(r62);
        r132.append(popTag());
        r132.append(popTag());
        r132.append(popTag());
        return r132.toString();
    L32:
        r142 = " Stars.";
    L35:
        if (r04 == 0) goto L44;
        if (r14.winnersCount <= 1) goto L44;
        StringBuilder r155 = new StringBuilder();
        r155.append(SerializeString("All winners received "));
        r155.append("<b>");
        r155.append(DataTypesUtils.NumberToString((int) r14.credits));
        r155.append("</b>");
        if (r5 == false) goto L41;
        String r143 = " Star in total.";
    L42:
        r155.append(SerializeString(r143));
        r62 = r155.toString();
        goto L51
    L41:
        r143 = " Stars in total.";
    L44:
        if (r14.unclaimedCount == 0) goto L46;
        r62 = SerializeString("Some winners couldn't be selected.");
        goto L51
    L46:
        int r144 = r14.winnersCount;
        if (r144 != 1) goto L49;
        r62 = SerializeString("The winner received their gift link in a private message.");
        goto L51
    L49:
        if (r144 <= 1) goto L51;
        r62 = SerializeString("All winners received gift links in private messages.");
        goto L51
    L21:
        r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L22
    L13:
        r152 = SerializeString("Winner");
        goto L14
    L9:
        r4 = "winner";
        goto L10
    L5:
        r1 = SerializeString("Winner Selected!");
        goto L6
    }

    private String pushGiveaway(HashMap<Long, ApiWrap$Peer> r20, ApiWrap$GiveawayStart r21) {
        StringBuilder r2 = new StringBuilder(pushDiv("media_wrap clearfix"));
        r2.append(pushDiv("media_giveaway"));
        r2.append(pushDiv("section_title bold"));
        if (r21.quantity <= 1) goto L5;
        String r4 = SerializeString("Giveaway Prizes");
    L6:
        r2.append(r4);
        r2.append(popTag());
        r2.append(pushDiv("section_body"));
        r2.append("<b>");
        r2.append(DataTypesUtils.NumberToString(r21.quantity));
        r2.append("</b> ");
        r2.append(SerializeString(r21.additionalPrize));
        r2.append(popTag());
        r2.append(pushDiv("section_title bold"));
        r2.append(SerializeString("with"));
        r2.append(popTag());
        r2.append(pushDiv("section_body"));
        if (r21.credits <= 0) goto L16;
        r2.append("<b>");
        r2.append(DataTypesUtils.NumberToString((int) r21.credits));
        if (r21.credits != 1) goto L11;
        String r8 = " Star";
    L12:
        r2.append(SerializeString(r8));
        r2.append("/<b>");
        r2.append(SerializeString("will be distributed "));
        if (r21.quantity != 1) goto L15;
        r2.append(SerializeString("to "));
        r2.append(SerializeString("<b>"));
        r2.append(DataTypesUtils.NumberToString(r21.quantity));
        r2.append(SerializeString("</b> "));
        r2.append(SerializeString("winner."));
    L24:
        r2.append(popTag());
        r2.append(pushDiv("section_title bold"));
        r2.append(SerializeString("Participants"));
        r2.append(popTag());
        r2.append(pushDiv("section_body"));
        ArrayList r7 = new ArrayList();
        ArrayList<Long> r82 = r21.channels;
        int r9 = r82.size();
        boolean r11 = false;
        boolean r12 = false;
        int r13 = 0;
    L25:
        if (r13 >= r9) goto L38;
        Long r14 = r82.get(r13);
        r13 = r13 + 1;
        Long r142 = r14;
        ApiWrap$Peer r10 = r20.get(r142);
        if (r10 == null) goto L36;
        ApiWrap$Chat r102 = r10.chat;
        if (r102 == null) goto L36;
        if (r102.isBroadcast == false) goto L34;
        r12 = true;
        goto L36
    L34:
        if (r102.isSupergroup == false) goto L36;
        r11 = true;
    L36:
        r7.add("<b>" + HtmlWriter.wrapPeerName(r142.longValue()) + "</b>");
        goto L25
    L38:
        if (r21.all == false) goto L44;
        if (r11 == true) goto L44;
        if (r12 == false) goto L44;
        if (r7.size() != 1) goto L44;
        String r5 = "All subscribers of the channel:";
    L46:
        if (r21.all == false) goto L52;
        if (r11 == true) goto L52;
        if (r12 == false) goto L52;
        if (r7.size() <= 1) goto L52;
        r5 = "All subscribers of the channels:";
    L52:
        String r92 = "All members of the group:";
        if (r21.all == false) goto L60;
        if (r11 == false) goto L60;
        if (r12 == true) goto L60;
        if (r7.size() != 1) goto L60;
        r5 = "All members of the group:";
    L60:
        if (r21.all == false) goto L67;
        if (r11 == false) goto L67;
        if (r12 == true) goto L67;
        if (r7.size() <= 1) goto L67;
        r5 = "All members of the groups:";
    L67:
        if (r21.all == false) goto L73;
        if (r11 == false) goto L73;
        if (r12 == false) goto L73;
        if (r7.size() != 1) goto L73;
    L75:
        if (r21.all == false) goto L82;
        if (r11 == false) goto L82;
        if (r12 == false) goto L82;
        if (r7.size() <= 1) goto L82;
        r92 = "All members of the groups and channels:";
    L82:
        if (r21.all == true) goto L89;
        if (r11 == true) goto L89;
        if (r12 == false) goto L89;
        if (r7.size() != 1) goto L89;
        r92 = "All users who joined the channel below after this date:";
    L89:
        if (r21.all == true) goto L95;
        if (r11 == true) goto L95;
        if (r12 == false) goto L95;
        if (r7.size() <= 1) goto L95;
        r92 = "All users who joined the channels below after this date:";
    L95:
        String r83 = "All users who joined the group below after this date:";
        if (r21.all == true) goto L103;
        if (r11 == false) goto L103;
        if (r12 == true) goto L103;
        if (r7.size() != 1) goto L103;
        r92 = "All users who joined the group below after this date:";
    L103:
        if (r21.all == true) goto L110;
        if (r11 == false) goto L110;
        if (r12 == true) goto L110;
        if (r7.size() <= 1) goto L110;
        r92 = "All users who joined the groups below after this date:";
    L110:
        if (r21.all == true) goto L116;
        if (r11 == false) goto L116;
        if (r12 == false) goto L116;
        if (r7.size() != 1) goto L116;
    L118:
        if (r21.all == true) goto L124;
        if (r11 == false) goto L124;
        if (r12 == false) goto L124;
        int r103 = 1;
        if (r7.size() <= 1) goto L125;
        r83 = "All users who joined the groups and channels below after this date:";
    L125:
        r2.append(SerializeString(r83));
        r2.append(TextUtils.join(", ", r7));
        r2.append(popTag());
        ArrayList r52 = new ArrayList();
        HashMap r72 = new HashMap();
        String[] r84 = Locale.getISOCountries();
        int r93 = r84.length;
        int r112 = 0;
    L126:
        if (r112 >= r93) goto L128;
        String r122 = r84[r112];
        r72.put(new Locale(_UrlKt.FRAGMENT_ENCODE_SET, r122).getDisplayCountry(), r122);
        r112 = r112 + 1;
        goto L126
    L128:
        ArrayList<String> r6 = r21.countries;
        int r85 = r6.size();
        int r94 = 0;
    L129:
        if (r94 >= r85) goto L132;
        String r113 = r6.get(r94);
        r94 = r94 + 1;
        String r114 = (String) r72.get(r113);
        r52.add(countryToEmoji(r114) + "\t " + r114);
        goto L129
    L132:
        if (r52.isEmpty() == true) goto L141;
        int r62 = r52.size();
        String r86 = (String) r52.get(0);
    L134:
        if (r103 == r62) goto L140;
        int r73 = r103 + 1;
        if (r73 != r62) goto L138;
        String r95 = "%1s and %2s";
    L139:
        r86 = String.format(r95, new Object[]{r86, r52.get(r103)});
        r103 = r73;
        goto L134
    L138:
        r95 = "%1s, %2s";
        goto L139
    L140:
        r2.append(pushDiv("section_body"));
        r2.append(SerializeString(String.format("from %s", new Object[]{r86})));
        r2.append(popTag());
    L141:
        r2.append(pushDiv("section_title bold"));
        r2.append(SerializeString("Winners Selection Date"));
        r2.append(popTag());
        r2.append(pushDiv("section_body"));
        r2.append(LocaleController.formatDateTime(r21.untilDate, false));
        r2.append(popTag());
        r2.append(popTag());
        r2.append(popTag());
        return r2.toString();
    L124:
        r103 = 1;
    L116:
        r83 = r92;
    L73:
        r92 = r5;
    L44:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L46
    L15:
        r2.append(SerializeString("among "));
        r2.append("<b>");
        r2.append(DataTypesUtils.NumberToString(r21.quantity));
        r2.append(SerializeString("</b> "));
        r2.append(SerializeString("winners."));
        goto L24
    L11:
        r8 = " Stars";
        goto L12
    L16:
        r2.append("<b>");
        r2.append(DataTypesUtils.NumberToString(r21.quantity));
        r2.append("</b> ");
        if (r21.quantity <= 1) goto L19;
        r2.append(SerializeString("Telegram Premium Subscriptions"));
    L20:
        r2.append(" for <b>");
        r2.append(DataTypesUtils.NumberToString(r21.months));
        r2.append("</b> ");
        if (r21.months <= 1) goto L23;
        r2.append("months.");
        goto L24
    L23:
        r2.append("month.");
        goto L24
    L19:
        r2.append(SerializeString("Telegram Premium Subscription"));
        goto L20
    L5:
        r4 = SerializeString("Giveaway Prize");
        goto L6
    }

    private String pushPhotoMedia(HtmlWriter.Photo r4, String r5) {
        ApiWrap$MediaData r52 = new ApiWrap$MediaData();
        r52.title = "Photo";
        r52.status = r4.image.width + "×" + r4.image.height;
        if (r4.image.file.relativePath.isEmpty() == false) goto L5;
        r52.status += ", " + AndroidUtilities.formatFileSize(r4.image.file.size);
    L6:
        r52.description = DataTypesUtils.NoFileDescription(r4.image.file.skipReason);
        r52.classes = "media_photo";
        return pushGenericMedia(r52);
    L5:
        r52.link = r4.image.file.relativePath;
        goto L6
    }

    private String pushPoll(ApiWrap$Poll r9) {
        StringBuilder r0 = new StringBuilder(pushDiv("media_wrap clearfix"));
        r0.append(pushDiv("media_poll"));
        r0.append(pushDiv("question bold"));
        r0.append(SerializeString(r9.question));
        r0.append(popTag());
        r0.append(pushDiv("details"));
        if (r9.closed == false) goto L5;
        r0.append(SerializeString("Final results"));
    L6:
        r0.append(popTag());
        HtmlContext$$ExternalSyntheticLambda2 r1 = new HtmlContext$$ExternalSyntheticLambda2();
        HtmlContext$$ExternalSyntheticLambda3 r2 = new HtmlContext$$ExternalSyntheticLambda3(r1);
        ArrayList<ApiWrap$Poll.Answer> r3 = r9.answers;
        int r4 = r3.size();
        int r5 = 0;
    L7:
        if (r5 >= r4) goto L9;
        ApiWrap$Poll.Answer r6 = r3.get(r5);
        r5 = r5 + 1;
        ApiWrap$Poll.Answer r62 = r6;
        r0.append(pushDiv("answer"));
        r0.append("- ");
        r0.append(SerializeString(r62.text()));
        r0.append((String) r2.run(r62));
        r0.append(popTag());
        goto L7
    L9:
        r0.append(pushDiv("total details\t"));
        r0.append((String) r1.run(Integer.valueOf(r9.totalVotes)));
        r0.append(popTag());
        r0.append(popTag());
        r0.append(popTag());
        return r0.toString();
    L5:
        r0.append(SerializeString("Anonymous poll"));
        goto L6
    }

    private String pushStickerMedia(ApiWrap$Document r8, String r9) {
        Pair<String, Dimension> r92 = WriteImageThumb(r9, r8.file.relativePath, CalculateThumbSize(384, 384, 80, 80, false), "PNG", -1, _UrlKt.FRAGMENT_ENCODE_SET);
        String r0 = (String) r92.first;
        Dimension r93 = (Dimension) r92.second;
        if (r0 != null) goto L5;
    L9:
        ApiWrap$MediaData r94 = new ApiWrap$MediaData();
        r94.title = "Sticker";
        r94.status = r8.stickerEmoji;
        if (r8.file.relativePath.isEmpty() == true) goto L12;
        r94.link = r8.file.relativePath;
    L16:
        r94.description = DataTypesUtils.NoFileDescription(r8.file.skipReason);
        r94.classes = "media_photo";
        return pushGenericMedia(r94);
    L12:
        if (r94.status.isEmpty() == true) goto L14;
        r94.status += ", ";
    L14:
        r94.status += AndroidUtilities.formatFileSize(r8.file.size);
        goto L16
    L5:
        if (r0.isEmpty() == true) goto L9;
        StringBuilder r1 = new StringBuilder(pushDiv("media_wrap clearfix"));
        r1.append(pushTag("a", new Pair[]{new Pair("class", "sticker_wrap clearfix pull_left"), new Pair("href", r8.file.relativePath)}));
        r1.append(pushTag("img", new Pair[]{new Pair("class", "sticker"), new Pair("style", "width: " + DataTypesUtils.NumberToString(r93.getWidth() / 2) + "px; height: " + DataTypesUtils.NumberToString(r93.getHeight() / 2) + "px"), new Pair("src", r0), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r1.append(popTag());
        r1.append(popTag());
        return r1.toString();
    }

    private String pushVideoFileMedia(ApiWrap$Document r7, String r8) {
        Dimension r82 = CalculateThumbSize(520, 520, 80, 80, true).apply(new Dimension(r7.width, r7.height));
        if (r7.thumb.file.relativePath.isEmpty() == false) goto L5;
    L13:
        ApiWrap$MediaData r83 = new ApiWrap$MediaData();
        r83.title = "Video file";
        r83.status = AndroidUtilities.formatLongDuration(r7.duration);
        if (r7.file.relativePath.isEmpty() == false) goto L16;
        r83.status += ", " + AndroidUtilities.formatFileSize(r7.file.size);
    L17:
        r83.description = DataTypesUtils.NoFileDescription(r7.file.skipReason);
        r83.classes = "media_video";
        return pushGenericMedia(r83);
    L16:
        r83.link = r7.file.relativePath;
        goto L17
    L5:
        if (r7.file.relativePath.isEmpty() == true) goto L13;
        if (r82.getWidth() == 0) goto L13;
        if (r82.getHeight() == 0) goto L13;
        StringBuilder r0 = new StringBuilder(pushDiv("media_wrap clearfix"));
        r0.append(pushTag("a", new Pair[]{new Pair("class", "video_file_wrap clearfix pull_left"), new Pair("href", r7.file.relativePath)}));
        r0.append(pushDiv("video_play_bg"));
        r0.append(pushDiv("video_play"));
        r0.append(popTag());
        r0.append(popTag());
        r0.append(pushDiv("video_duration"));
        r0.append(AndroidUtilities.formatLongDuration(r7.duration));
        r0.append(popTag());
        r0.append(pushTag("img", new Pair[]{new Pair("class", "video_file"), new Pair("style", "width: " + DataTypesUtils.NumberToString(r82.getWidth() / 2) + "px; height: " + DataTypesUtils.NumberToString(r82.getHeight() / 2) + "px"), new Pair("src", relativePath(r7.thumb.file.relativePath)), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r0.append(popTag());
        r0.append(popTag());
        return r0.toString();
    }

    public AbstractWriter.Result close() {
        if (this._closed == true) goto L13;
        if (this._file.empty() == true) goto L13;
        this._closed = true;
        StringBuilder r0 = new StringBuilder();
    L8:
        if (isTagsEmpty() == true) goto L11;
        r0.append(popTag());
        goto L8
    L11:
        return this._file.writeBlock(r0.toString());
    L13:
        return AbstractWriter.Result.Success();
    }

    public String composeStart() {
        StringBuilder r0 = new StringBuilder("<!DOCTYPE html>" + pushTag("html", new Pair[0]));
        r0.append(pushTag("head", new Pair[0]));
        r0.append(pushTag("meta", new Pair[]{new Pair("charset", "utf-8"), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r0.append(pushTag("title", new Pair[]{new Pair("inline", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r0.append("Exported Data");
        r0.append(popTag());
        r0.append(pushTag("meta", new Pair[]{new Pair("name", "viewport"), new Pair("content", "width=device-width, initial-scale=1.0"), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r0.append(pushTag("link", new Pair[]{new Pair("href", relativePath("css/style.css")), new Pair("rel", "stylesheet"), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r0.append(pushTag("script", new Pair[]{new Pair("src", relativePath("js/script.js")), new Pair("type", "text/javascript")}));
        r0.append(popTag());
        r0.append(popTag());
        r0.append(pushTag("body", new Pair[]{new Pair("onload", "CheckLocation();")}));
        r0.append(pushDiv("page_wrap", _UrlKt.FRAGMENT_ENCODE_SET));
        return String.valueOf(r0);
    }

    public String indent() {
        return HtmlContext$$ExternalSyntheticBackport1.m(" ", this._tags.size());
    }

    public boolean isTagsEmpty() {
        return this._tags.isEmpty();
    }

    public String popTag() {
        ApiWrap$Tag r0 = this._tags.get(r0.size() - 1);
        this._tags.remove(r1.size() - 1);
        StringBuilder r1 = new StringBuilder();
        boolean r2 = r0.block;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r2 == false) goto L5;
        String r5 = "\n" + indent();
    L6:
        r1.append(r5);
        r1.append("</");
        r1.append(r0.name);
        r1.append(">");
        if (r0.block == false) goto L9;
        r3 = "\n";
    L9:
        r1.append(r3);
        return r1.toString();
    L5:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public String pushAbout(String r3, boolean r4) {
        StringBuilder r0 = new StringBuilder();
        if (r4 == false) goto L5;
        String r42 = "page_about details with_divider";
    L6:
        r0.append(pushDiv(r42, _UrlKt.FRAGMENT_ENCODE_SET));
        r0.append(HtmlWriter.MakeLinks(SerializeString(r3)));
        r0.append(popTag());
        return r0.toString();
    L5:
        r42 = "page_about details";
        goto L6
    }

    public String pushDiv(String r2) {
        return pushDiv(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public String pushDiv(String r4, String r5) {
        if (r5.isEmpty() == false) goto L7;
        return pushTag("div", new Pair[]{new Pair("class", r4)});
    L7:
        return pushTag("div", new Pair[]{new Pair("class", r4), new Pair("style", r5)});
    }

    public String pushGenericListEntry(String r5, HtmlWriter.UserpicData r6, String r7, String r8, List<String> r9, String r10) {
        if (r5.isEmpty() == false) goto L5;
        String r52 = pushDiv("entry clearfix");
    L6:
        StringBuilder r0 = new StringBuilder(r52);
        r0.append(pushDiv("pull_left userpic_wrap"));
        r0.append(pushUserpic(r6));
        r0.append(popTag());
        r0.append(pushDiv("body"));
        if (r10.isEmpty() == true) goto L10;
        r0.append(pushDiv("pull_right info details"));
        r0.append(SerializeString(r10));
        r0.append(popTag());
    L10:
        if (r7.isEmpty() == true) goto L12;
        r0.append(pushDiv("name bold"));
        r0.append(SerializeString(r7));
        r0.append(popTag());
    L12:
        if (r8 != null) goto L14;
    L16:
        Iterator<String> r53 = r9.iterator();
    L18:
        if (r53.hasNext() == false) goto L20;
        String r62 = r53.next();
        r0.append(pushDiv("details_entry details"));
        r0.append(SerializeString(r62));
        r0.append(popTag());
        goto L18
    L20:
        r0.append(popTag());
        r0.append(popTag());
        return r0.toString();
    L14:
        if (r8.isEmpty() == true) goto L16;
        r0.append(pushDiv("subname bold"));
        r0.append(SerializeString(r8));
        r0.append(popTag());
        goto L16
    L5:
        r52 = pushTag("a", new Pair[]{new Pair("class", "entry block_link clearfix"), new Pair("href", relativePath(r5) + "#allow_back")});
        goto L6
    }

    public String pushHeader(String r7, String r8) {
        StringBuilder r0 = new StringBuilder(pushDiv("page_header", _UrlKt.FRAGMENT_ENCODE_SET));
        if (r8.isEmpty() == false) goto L5;
        String r82 = pushDiv("content", _UrlKt.FRAGMENT_ENCODE_SET);
    L6:
        r0.append(r82);
        r0.append(pushDiv("text bold", _UrlKt.FRAGMENT_ENCODE_SET));
        r0.append(SerializeString(r7));
        r0.append(popTag());
        r0.append(popTag());
        r0.append(popTag());
        return r0.toString();
    L5:
        r82 = pushTag("a", new Pair[]{new Pair("class", "content block_link"), new Pair("href", relativePath(r8)), new Pair("onclick", "return GoBack(this)")});
        goto L6
    }

    public String pushListEntry(HtmlWriter.UserpicData r8, String r9, String r10, String r11, String r12) {
        return pushGenericListEntry(r12, r8, r9, null, Collections.singletonList(r10), r11);
    }

    public String pushMedia(ApiWrap$Message r2, String r3, HashMap<Long, ApiWrap$Peer> r4, String r5, Utilities.Callback2Return<Integer, String, String> r6) {
        ApiWrap$MediaData r52 = prepareMediaData(r2, r3, r4, r5);
        String r0 = r52.classes;
        if (r0 == null) goto L42;
        if (r0.isEmpty() == false) goto L42;
        Object r22 = r2.media.content;
        if ((r22 instanceof ApiWrap$Document) == false) goto L24;
        ApiWrap$Document r23 = (ApiWrap$Document) r22;
        if (r23.isSticker == false) goto L14;
        return pushStickerMedia(r23, r3);
    L14:
        if (r23.isAnimated == false) goto L18;
        return pushAnimatedMedia(r23, r3);
    L18:
        if (r23.isVideoFile == true) goto L20;
        GlShader$$ExternalSyntheticBUOutline1.m("Non generic document in pushMedia.");
        return null;
    L20:
        return pushVideoFileMedia(r23, r3);
    L24:
        if ((r22 instanceof HtmlWriter.Photo) == false) goto L28;
        return pushPhotoMedia((HtmlWriter.Photo) r22, r3);
    L28:
        if ((r22 instanceof ApiWrap$Poll) == false) goto L32;
        return pushPoll((ApiWrap$Poll) r22);
    L32:
        if ((r22 instanceof ApiWrap$GiveawayStart) == false) goto L36;
        return pushGiveaway(r4, (ApiWrap$GiveawayStart) r22);
    L36:
        if ((r22 instanceof ApiWrap$GiveawayResults) == true) goto L38;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        return pushGiveaway(r4, (ApiWrap$GiveawayResults) r22, r6);
    L42:
        return pushGenericMedia(r52);
    }

    public Pair<HtmlWriter.MessageInfo, String> pushMessage(ApiWrap$Message r23, HtmlWriter.MessageInfo r24, ApiWrap$DialogInfo r25, String r26, HashMap<Long, ApiWrap$Peer> r27, HtmlWriter r28, String r29, Utilities.Callback2Return<Integer, String, String> r30) {
        if (r23 == null) goto L5;
        if (r25 == null) goto L146;
        HtmlWriter.MessageInfo r7 = new HtmlWriter.MessageInfo();
        r7.id = r23.id;
        long r4 = r23.fromId;
        r7.fromId = r4;
        r7.viaBotId = r23.viaBotId;
        r7.date = r23.date;
        r7.forwardedFromId = r23.forwardedFromId;
        r7.forwardedFromName = r23.forwardedFromName;
        r7.forwardedDate = r23.forwardedDate;
        r7.forwarded = r23.forwarded;
        r7.showForwardedAsOriginal = r23.showForwardedAsOriginal;
        if ((r23.media.content instanceof ApiWrap$UnsupportedMedia) == true) goto L9;
        ApiWrap$DialogInfo.Type r0 = r25.type;
        if (r0 != ApiWrap$DialogInfo.Type.PrivateChannel) goto L13;
    L16:
        boolean r02 = true;
    L17:
        String r42 = r28.getTextFromAction(r23, HtmlWriter.wrapPeerName(r4), r02);
        if (r42.isEmpty() == true) goto L29;
        Object r03 = r23.parsedAction;
        if ((r03 instanceof ApiWrap$ActionSuggestProfilePhoto) == false) goto L24;
        HtmlWriter.Photo r6 = ((ApiWrap$ActionSuggestProfilePhoto) r03).photo();
    L22:
        HtmlWriter.Photo r5 = r6;
    L28:
        return new Pair(r7, pushServiceMessage(r23.id, r25, r26, r42, r5));
    L24:
        if ((r03 instanceof ApiWrap$ActionChatEditPhoto) == false) goto L26;
        r6 = ((ApiWrap$ActionChatEditPhoto) r03).photo();
        goto L22
    L26:
        r5 = null;
        goto L28
    L29:
        r7.type = HtmlWriter.MessageInfo.Type.Default;
        boolean r2 = DataTypesUtils.messageNeedsWrap(r23, r24);
        long r43 = r23.fromId;
        if (r23.forwarded == true) goto L32;
    L34:
        boolean r8 = false;
    L35:
        HtmlWriter.UserpicData r11 = new HtmlWriter.UserpicData();
        if (r23.forwarded == false) goto L45;
        long r14 = r23.forwardedFromId;
        if (r14 == 0) goto L40;
        int r12 = DataTypesUtils.PeerColorIndex(r14);
    L41:
        r11.colorIndex = r12;
        r11.pixelSize = 42;
        long r142 = r23.forwardedFromId;
        if (r142 == 0) goto L44;
        DataTypesUtils.FillUserpicNames(r11, r27.get(Long.valueOf(r142)));
        goto L45
    L44:
        DataTypesUtils.FillUserpicNames(r11, r23.forwardedFromName);
        goto L45
    L40:
        r12 = DataTypesUtils.PeerColorIndex(r23.id);
    L45:
        HtmlWriter.UserpicData r122 = new HtmlWriter.UserpicData();
        if (r23.showForwardedAsOriginal == false) goto L48;
        r122 = r11;
    L49:
        long r44 = r23.viaBotId;
        if (r44 == 0) goto L56;
        ApiWrap$Peer r45 = r27.get(Long.valueOf(r44));
        if (r45 == null) goto L56;
        if (r45.user.username.isEmpty() == true) goto L56;
        SerializeString(r45.user.username);
    L56:
        if (r2 == false) goto L58;
        String r46 = "message default clearfix";
    L59:
        StringBuilder r13 = new StringBuilder(pushTag("div", new Pair[]{new Pair("class", r46), new Pair("id", "message" + r23.id)}));
        if (r2 == false) goto L62;
        r13.append(pushDiv("pull_left userpic_wrap"));
        r13.append(pushUserpic(r122));
        r13.append(popTag());
    L62:
        r13.append(pushDiv("body"));
        r13.append(pushTag("div", new Pair[]{new Pair("class", "pull_right date details"), new Pair("title", LocaleController.getInstance().getExportFullDateFormatter().format(r23.date * 1000))}));
        r13.append(LocaleController.getInstance().getFormatterDay().format(r23.date * 1000));
        r13.append(popTag());
        if (r2 == false) goto L66;
        r13.append(pushDiv("from_name"));
        r13.append(SerializeString(DataTypesUtils.ComposeName(r122, "Deleted Account")));
        r13.append(popTag());
    L66:
        if (r8 == false) goto L75;
        boolean r47 = DataTypesUtils.forwardedNeedsWrap(r23, r24);
        if (r47 == false) goto L70;
        r13.append(pushDiv("pull_left forwarded userpic_wrap"));
        HtmlWriter.UserpicData r52 = r11;
        r13.append(pushUserpic(r52));
        r13.append(popTag());
    L71:
        r13.append(pushDiv("forwarded body"));
        if (r47 == false) goto L75;
        r13.append(pushDiv("from_name"));
        r13.append(SerializeString(DataTypesUtils.ComposeName(r52, "Deleted Account")));
        r13.append(pushTag("span", new Pair[]{new Pair("class", "date details"), new Pair("title", LocaleController.formatDate(r23.forwardedDate)), new Pair("inline", _UrlKt.FRAGMENT_ENCODE_SET)}));
        r13.append(" " + LocaleController.formatDate(r23.forwardedDate));
        r13.append(popTag());
        r13.append(popTag());
        goto L75
    L70:
        r52 = r11;
    L75:
        if (r23.replyToMsgId == 0) goto L82;
        r13.append(pushDiv("reply_to details"));
        if (r23.replyToPeerId == 0) goto L79;
        r13.append("In reply to a message in another chat");
        Utilities.Callback2Return<Integer, String, String> r53 = r30;
    L80:
        r13.append(popTag());
    L83:
        r13.append(pushMedia(r23, r26, r27, r29, r53));
        String r22 = DataTypesUtils.FormatText(r23.text, r29, this._base);
        if (r22.isEmpty() == true) goto L86;
        r13.append(pushDiv("text"));
        r13.append(r22);
        r13.append(popTag());
    L86:
        ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> r210 = r23.inlineButtonRows;
        if (r210 == null) goto L128;
        if (r210.isEmpty() == true) goto L128;
        r13.append(pushTag("table", new Pair[]{new Pair("class", "bot_button_table")}));
        r13.append(pushTag("tbody", new Pair[0]));
        ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> r211 = r23.inlineButtonRows;
        int r3 = r211.size();
        int r48 = 0;
    L91:
        if (r48 >= r3) goto L126;
        ArrayList<ApiWrap$HistoryMessageMarkupButton> r54 = r211.get(r48);
        r48 = r48 + 1;
        ArrayList<ApiWrap$HistoryMessageMarkupButton> r55 = r54;
        r13.append(pushTag("tr", new Pair[0]));
        r13.append(pushTag("td", new Pair[]{new Pair("class", "bot_button_row")}));
        int r10 = r55.size();
        int r112 = 0;
    L93:
        if (r112 >= r10) goto L125;
        ApiWrap$HistoryMessageMarkupButton r123 = r55.get(r112);
        r112 = r112 + 1;
        ApiWrap$HistoryMessageMarkupButton r124 = r123;
        ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> r16 = r211;
        if (r124.data() != null) goto L97;
    L99:
        int r242 = r3;
        int r252 = r48;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L101:
        if (r124.forwardText() != null) goto L103;
    L105:
        String r212 = r32 + "Type: " + ApiWrap$HistoryMessageMarkupButton.TypeToString(r124);
        ApiWrap$HistoryMessageMarkupButton.Type r33 = r124.type();
        ApiWrap$HistoryMessageMarkupButton.Type r49 = ApiWrap$HistoryMessageMarkupButton.Type.Url;
        if (r33 != r49) goto L108;
        String r34 = ChatUtils.getInstance().getTextFromCallback(r124.data());
    L110:
        if (r124.type() == r49) goto L112;
        String r213 = "return ShowTextCopied('" + r212 + "');";
    L113:
        r13.append(pushTag("div", new Pair[]{new Pair("class", "bot_button")}));
        if (r34.isEmpty() == false) goto L116;
        Pair r35 = new Pair(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
    L118:
        if (r213.isEmpty() == false) goto L120;
        Pair r214 = new Pair(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
    L121:
        r13.append(pushTag("a", new Pair[]{r35, r214}));
        r13.append(pushTag("div", new Pair[0]));
        r13.append(r124.text());
        r13.append(popTag());
        r13.append(popTag());
        r13.append(popTag());
        if (r124 == r55.get(r55.size() - 1)) goto L124;
        r13.append(pushTag("div", new Pair[]{new Pair("class", "bot_button_column_separator")}));
        r13.append(popTag());
    L124:
        r3 = r242;
        r48 = r252;
        r211 = r16;
        goto L93
    L120:
        r214 = new Pair("onclick", r213);
        goto L121
    L116:
        r35 = new Pair("href", r34);
        goto L118
    L112:
        r213 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L113
    L108:
        r34 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L110
    L103:
        if (r124.forwardText().isEmpty() == true) goto L105;
        r32 = r32 + "Forward text: " + r124.forwardText() + " | ";
        goto L105
    L97:
        if (r124.data().length == 0) goto L99;
        r242 = r3;
        StringBuilder r15 = new StringBuilder("Data: ");
        r252 = r48;
        r15.append(ChatUtils.getInstance().getTextFromCallback(r124.data()));
        r15.append(" | ");
        r32 = r15.toString();
        goto L101
    L125:
        r13.append(popTag());
        r13.append(popTag());
        r3 = r3;
        r211 = r211;
        goto L91
    L126:
        r13.append(popTag());
        r13.append(popTag());
    L128:
        if (r23.signature.isEmpty() == true) goto L130;
        r13.append(pushDiv("signature details"));
        r13.append(SerializeString(r23.signature));
        r13.append(popTag());
    L130:
        if (r8 == false) goto L133;
        r13.append(popTag());
    L133:
        if (r23.reactions.isEmpty() == true) goto L139;
        r13.append(pushDiv("reactions"));
        Iterator<ApiWrap$Reaction> r1 = r23.reactions.iterator();
        if (r1.hasNext() == true) goto L137;
        r13.append(popTag());
        goto L139
    L137:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    L139:
        r13.append(popTag());
        r13.append(popTag());
        return new Pair(r7, r13.toString());
    L79:
        r13.append("In reply to ");
        r53 = r30;
        r13.append(r53.run(Integer.valueOf(r23.replyToMsgId), "this message"));
        goto L80
    L82:
        r53 = r30;
        goto L83
    L58:
        r46 = "message default clearfix joined";
        goto L59
    L48:
        r122.colorIndex = DataTypesUtils.PeerColorIndex(r43);
        r122.pixelSize = 42;
        DataTypesUtils.FillUserpicNames(r122, r27.get(Long.valueOf(r43)));
        goto L49
    L32:
        if (r23.showForwardedAsOriginal == true) goto L34;
        r8 = true;
        goto L35
    L13:
        if (r0 == ApiWrap$DialogInfo.Type.PublicChannel) goto L16;
        r02 = false;
        goto L17
    L9:
        return new Pair(r7, pushServiceMessage(r23.id, r25, r26, LocaleController.getString(R.string.UnsupportedMedia2), null));
    L146:
        return null;
    L5:
        return null;
    }

    public String pushSection(String r5, String r6, int r7, String r8) {
        StringBuilder r0 = new StringBuilder();
        r0.append(pushTag("a", new Pair[]{new Pair("class", "section block_link " + r6), new Pair("href", r8 + "#allow_back")}));
        r0.append(pushDiv("counter details", _UrlKt.FRAGMENT_ENCODE_SET));
        r0.append(r7);
        r0.append(popTag());
        r0.append(pushDiv("label bold", _UrlKt.FRAGMENT_ENCODE_SET));
        r0.append(SerializeString(r5));
        r0.append(popTag());
        r0.append(popTag());
        return r0.toString();
    }

    public String pushServiceMessage(int r6, ApiWrap$DialogInfo r7, String r8, String r9, HtmlWriter.Photo r10) {
        StringBuilder r0 = new StringBuilder(pushTag("div", new Pair[]{new Pair("class", "message service"), new Pair("id", "message" + r6)}));
        r0.append(pushDiv("body details"));
        r0.append(r9);
        r0.append(popTag());
        if (r10 == null) goto L5;
        HtmlWriter.UserpicData r62 = new HtmlWriter.UserpicData();
        r62.colorIndex = r7.colorIndex;
        r62.firstName = r7.name;
        r62.lastName = r7.lastName;
        r62.pixelSize = 60;
        String r72 = r10.image.file.relativePath;
        r62.largeLink = r72;
        r62.imageLink = HtmlWriter.WriteUserpicThumb(r8, r72, r62);
        r0.append(pushDiv("userpic_wrap"));
        r0.append(pushUserpic(r62));
        r0.append(popTag());
    L5:
        r0.append(popTag());
        return String.valueOf(r0);
    }

    public String pushSessionListEntry(int r8, String r9, String r10, ArrayList<String> r11, String r12) {
        HtmlWriter.UserpicData r2 = new HtmlWriter.UserpicData();
        r2.colorIndex = DataTypesUtils.ApplicationColorIndex(r8);
        r2.pixelSize = 48;
        r2.firstName = r9;
        return pushGenericListEntry(_UrlKt.FRAGMENT_ENCODE_SET, r2, r9, r10, r11, r12);
    }

    public String pushStoriesListEntry(ApiWrap$StoryData r12, String r13, ArrayList<String> r14, String r15, ArrayList<ApiWrap$TextPart> r16, String r17, String r18) {
        StringBuilder r1 = new StringBuilder(pushDiv("entry clearfix"));
        if (r18.isEmpty() == true) goto L5;
        r1.append(pushTag("a", new Pair[]{new Pair("class", "pull_left userpic_wrap"), new Pair("href", relativePath(r18) + "#allow_back")}));
    L6:
        boolean r2 = r12.imageLink.isEmpty();
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r2 == true) goto L9;
        r1.append(pushTag("img", new Pair[]{new Pair("class", "story"), new Pair("style", "width: 45px; height: 80px"), new Pair("src", relativePath(r12.imageLink)), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
    L9:
        r1.append(popTag());
        r1.append(pushDiv("body"));
        if (r15.isEmpty() == true) goto L13;
        r1.append(pushDiv("pull_right info details"));
        r1.append(SerializeString(r15));
        r1.append(popTag());
    L13:
        if (r13.isEmpty() == true) goto L21;
        if (r18.isEmpty() == true) goto L17;
        r1.append(pushTag("a", new Pair[]{new Pair("class", "block_link expanded"), new Pair("href", relativePath(r18) + "#allow_back")}));
    L17:
        r1.append(pushDiv("name bold"));
        r1.append(SerializeString(r13));
        r1.append(popTag());
        if (r18.isEmpty() == true) goto L21;
        r1.append(popTag());
    L21:
        if (r16.isEmpty() == true) goto L25;
        r5 = DataTypesUtils.FormatText(r16, r17, this._base);
    L25:
        if (r5.isEmpty() == true) goto L27;
        r1.append(pushDiv("text"));
        r1.append(r5);
        r1.append(popTag());
    L27:
        int r122 = r14.size();
        int r132 = 0;
    L28:
        if (r132 >= r122) goto L30;
        String r22 = r14.get(r132);
        r132 = r132 + 1;
        r1.append(pushDiv("details_entry details"));
        r1.append(SerializeString(r22));
        r1.append(popTag());
        goto L28
    L30:
        r1.append(popTag());
        r1.append(popTag());
        return r1.toString();
    L5:
        r1.append(pushDiv("pull_left userpic_wrap"));
        goto L6
    }

    public String pushTag(String r9, Pair<String, String>... r10) {
        ApiWrap$Tag r0 = new ApiWrap$Tag();
        r0.name = r9;
        StringBuilder r92 = new StringBuilder();
        int r1 = r10.length;
        int r3 = 0;
        boolean r4 = false;
    L3:
        if (r3 >= r1) goto L12;
        Pair<String, String> r5 = r10[r3];
        String r6 = (String) r5.first;
        String r52 = (String) r5.second;
        if (r6.equals("inline") == false) goto L8;
        r0.block = false;
    L11:
        r3 = r3 + 1;
        goto L3
    L8:
        if (r6.equals("empty") == false) goto L10;
        r4 = true;
        goto L11
    L10:
        r92.append(' ');
        r92.append(r6);
        r92.append("=\"");
        r92.append(SerializeString(r52));
        r92.append("\"");
        goto L11
    L12:
        StringBuilder r102 = new StringBuilder();
        String r2 = "\n";
        if (r0.block == false) goto L15;
        String r12 = "\n" + indent();
    L16:
        r102.append(r12);
        r102.append("<");
        r102.append(r0.name);
        r102.append(r92);
        if (r4 == false) goto L19;
        String r93 = "/";
    L20:
        r102.append(r93);
        r102.append(">");
        if (r0.block == true) goto L24;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        r102.append(r2);
        String r94 = r102.toString();
        if (r4 == true) goto L27;
        this._tags.add(r0);
    L27:
        return r94;
    L19:
        r93 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    L15:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    }

    public String pushUserpic(HtmlWriter.UserpicData r11) {
        String r0 = r11.pixelSize + "px";
        StringBuilder r1 = new StringBuilder();
        if (r11.largeLink.isEmpty() == true) goto L5;
        r1.append(pushTag("a", new Pair[]{new Pair("class", "userpic_link"), new Pair("href", pathWithRelativePath(r11.largeLink))}));
    L5:
        String r2 = "width: " + r0 + "; height: " + r0;
        String r4 = r11.imageLink;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 != null) goto L8;
    L10:
        r1.append(pushTag("div", new Pair[]{new Pair("class", "userpic userpic" + (r11.colorIndex + 1)), new Pair("style", r2)}));
        if (r11.tooltip.isEmpty() == false) goto L13;
        r1.append(pushDiv("initials", "line-height: ".concat(r0)));
    L14:
        String r02 = r11.firstName;
        if (r02.trim().isEmpty() == false) goto L17;
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r1.append(r03);
        String r04 = r11.lastName;
        if (r04.trim().isEmpty() == true) goto L22;
        r5 = SerializeString(r04.trim().substring(0, 1));
    L22:
        r1.append(r5);
        r1.append(popTag());
        r1.append(popTag());
    L24:
        if (r11.largeLink.isEmpty() == true) goto L27;
        r1.append(popTag());
    L27:
        return r1.toString();
    L17:
        r03 = SerializeString(r02.trim().substring(0, 1));
        goto L18
    L13:
        r1.append(pushTag("div", new Pair[]{new Pair("class", "initials"), new Pair("style", "line-height: ".concat(r0)), new Pair("title", r11.tooltip)}));
        goto L14
    L8:
        if (r4.isEmpty() == true) goto L10;
        r1.append(pushTag("img", new Pair[]{new Pair("class", "userpic"), new Pair("style", r2), new Pair("src", pathWithRelativePath(r11.imageLink)), new Pair("empty", _UrlKt.FRAGMENT_ENCODE_SET)}));
        goto L24
    }

    public String relativePath(String r2) {
        return this._base + r2;
    }

    public AbstractWriter.Result writeBlock(String r4) {
        if (this._closed == true) goto L15;
        boolean r0 = r4.isEmpty();
        OutputFile r1 = this._file;
        if (r0 == false) goto L7;
        AbstractWriter.Result r42 = r1.writeBlock(r4);
    L12:
        if (r42.isSuccess() == true) goto L14;
        this._closed = true;
    L14:
        return r42;
    L7:
        boolean r02 = r1.empty();
        OutputFile r12 = this._file;
        if (r02 == false) goto L10;
        r42 = r12.writeBlock(this._composedStart + r4);
        goto L12
    L10:
        r42 = r12.writeBlock(r4);
        goto L12
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("file is closed!");
        return null;
    }

    public AbstractWriter.Result writeBlock(ArrayList<String> r5) {
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        String r2 = r5.get(r1);
        r1 = r1 + 1;
        String r22 = r2;
        if (writeBlock(r22).isSuccess() == true) goto L3;
        DexMaker$$ExternalSyntheticBUOutline0.m("writeBlock : ", r22);
        return null;
    L10:
        return AbstractWriter.Result.Success();
    }
}
