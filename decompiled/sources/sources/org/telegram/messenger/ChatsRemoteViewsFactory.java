package org.telegram.messenger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import androidx.collection.LongSparseArray;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.Forum.ForumUtilities;

/* loaded from: classes5.dex */
class ChatsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private AccountInstance accountInstance;
    private int appWidgetId;
    private RectF bitmapRect;
    private boolean deleted;
    private LongSparseArray<TLRPC.Dialog> dialogs;
    private ArrayList<Long> dids;
    private Context mContext;
    private LongSparseArray<MessageObject> messageObjects;
    private Paint roundPaint;

    public ChatsRemoteViewsFactory(Context r3, Intent r4) {
        this.dids = new ArrayList();
        this.dialogs = new LongSparseArray();
        this.messageObjects = new LongSparseArray();
        this.mContext = r3;
        Theme.createDialogsResources(r3);
        boolean r1 = false;
        this.appWidgetId = r4.getIntExtra("appWidgetId", 0);
        SharedPreferences r32 = r3.getSharedPreferences("shortcut_widget", 0);
        int r42 = r32.getInt("account" + this.appWidgetId, -1);
        if (r42 < 0) goto L6;
        this.accountInstance = AccountInstance.getInstance(r42);
    L6:
        if (r32.getBoolean("deleted" + this.appWidgetId, false) == false) goto L8;
    L9:
        r1 = true;
    L10:
        this.deleted = r1;
        return;
    L8:
        if (this.accountInstance != null) goto L10;
        goto L9
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public int getCount() {
        if (this.deleted == false) goto L6;
        return 1;
    L6:
        return this.dids.size() + 1;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public long getItemId(int r1) {
        return r1;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public RemoteViews getViewAt(int r22) {
        if (this.deleted == false) goto L6;
        RemoteViews r0 = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_deleted);
        r0.setTextViewText(R.id.widget_deleted_text, LocaleController.getString(R.string.WidgetLoggedOff));
        return r0;
    L6:
        int r4 = 0;
        if (r22 < this.dids.size()) goto L10;
        RemoteViews r02 = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_edititem);
        r02.setTextViewText(R.id.widget_edititem_text, LocaleController.getString(R.string.TapToEditWidget));
        Bundle r2 = new Bundle();
        r2.putInt("appWidgetId", this.appWidgetId);
        r2.putInt("appWidgetType", 0);
        r2.putInt("currentAccount", this.accountInstance.getCurrentAccount());
        Intent r1 = new Intent();
        r1.putExtras(r2);
        r02.setOnClickFillInIntent(R.id.widget_edititem, r1);
        return r02;
    L10:
        Long r5 = this.dids.get(r22);
        boolean r03 = DialogObject.isUserDialog(r5.longValue());
        AccountInstance r6 = this.accountInstance;
        CharSequence r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r03 == false) goto L40;
        TLRPC.User r04 = r6.getMessagesController().getUser(r5);
        if (r04 != null) goto L15;
        String r11 = _UrlKt.FRAGMENT_ENCODE_SET;
    L38:
        TLRPC.Chat r62 = null;
        TLRPC.FileLocation r12 = null;
    L68:
        RemoteViews r13 = new RemoteViews(this.mContext.getPackageName(), R.layout.shortcut_widget_item);
        r13.setTextViewText(R.id.shortcut_widget_item_text, r11);
        if (r12 != null) goto L264;
        Bitmap r122 = null;
    L74:
        int r14 = AndroidUtilities.dp(48.0f);     // Catch: Throwable -> L71
        Bitmap r15 = Bitmap.createBitmap(r14, r14, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L71
        r15.eraseColor(0);     // Catch: Throwable -> L71
        Canvas r7 = new Canvas(r15);     // Catch: Throwable -> L71
        if (r122 != null) goto L91;
        if (r04 == null) goto L83;
        AvatarDrawable r8 = new AvatarDrawable(r04);     // Catch: Throwable -> L71
        if (UserObject.isReplyUser(r04) == false) goto L81;
        r8.setAvatarType(12);     // Catch: Throwable -> L71
    L84:
        float r05 = r14;
        if (r62 != null) goto L87;
    L89:
        boolean r123 = false;
    L90:
        r8.setRoundRadius(ExteraConfig.getAvatarCorners(r05, true, r123));     // Catch: Throwable -> L71
        r8.setBounds(0, 0, r14, r14);     // Catch: Throwable -> L71
        r8.draw(r7);     // Catch: Throwable -> L71
        boolean r16 = true;
    L103:
        r7.setBitmap(null);     // Catch: Throwable -> L105
        r13.setImageViewBitmap(R.id.shortcut_widget_item_avatar, r15);     // Catch: Throwable -> L105
    L107:
        MessageObject r72 = this.messageObjects.get(r5.longValue());
        TLRPC.Dialog r82 = this.dialogs.get(r5.longValue());
        if (r72 == null) goto L234;
        long r112 = r72.getFromChatId();
        boolean r06 = DialogObject.isUserDialog(r112);
        AccountInstance r142 = this.accountInstance;
        if (r06 == false) goto L112;
        TLRPC.User r113 = r142.getMessagesController().getUser(Long.valueOf(r112));
        TLRPC.Chat r07 = null;
    L113:
        int r124 = this.mContext.getResources().getColor(R.color.widget_text);
        if ((r72.messageOwner instanceof TLRPC.TL_messageService) == true) goto L116;
        String r152 = "📎 ";
        if (r62 == null) goto L188;
        if (r07 != null) goto L188;
        if (ChatObject.isChannel(r62) == false) goto L132;
        if (ChatObject.isMegagroup(r62) == false) goto L188;
    L132:
        if (r72.isOutOwner() == false) goto L135;
        String r08 = LocaleController.getString(R.string.FromYou);
    L134:
        String r63 = r08;
        CharSequence r09 = r72.caption;
        if (r09 == null) goto L158;
        String r010 = r09.toString();
        if (r010.length() <= 150) goto L144;
        r010 = r010.substring(0, 150);
    L144:
        if (r72.isVideo() == false) goto L147;
        r152 = "📹 ";
    L155:
        SpannableStringBuilder r011 = SpannableStringBuilder.valueOf(String.format("%2$s: \u2068%1$s\u2069", new Object[]{r152 + r010.replace('\n', ' '), r63}));
    L156:
        SpannableStringBuilder r92 = r011;
    L262:
        r92.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_nameMessage), 0, r63.length() + 1, 33);     // Catch: Exception -> L186
        CharSequence r93 = r92;
    L233:
        r13.setTextViewText(R.id.shortcut_widget_item_time, LocaleController.stringForMessageListDate(r72.messageOwner.date));
        r13.setTextViewText(R.id.shortcut_widget_item_message, r93.toString());
        r13.setTextColor(R.id.shortcut_widget_item_message, r124);
    L241:
        if (r82 == null) goto L248;
        int r64 = r82.unread_count;
        if (r64 <= 0) goto L248;
        r13.setTextViewText(R.id.shortcut_widget_item_badge, String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r64)}));
        r13.setViewVisibility(R.id.shortcut_widget_item_badge, 0);
        if (this.accountInstance.getMessagesController().isDialogMuted(r82.id, 0) == false) goto L247;
        r13.setBoolean(R.id.shortcut_widget_item_badge, "setEnabled", false);
        r13.setInt(R.id.shortcut_widget_item_badge, "setBackgroundResource", R.drawable.widget_badge_muted_background);
    L249:
        Bundle r65 = new Bundle();
        if (DialogObject.isUserDialog(r5.longValue()) == false) goto L252;
        r65.putLong("userId", r5.longValue());
    L253:
        r65.putInt("currentAccount", this.accountInstance.getCurrentAccount());
        Intent r3 = new Intent();
        r3.putExtras(r65);
        r13.setOnClickFillInIntent(R.id.shortcut_widget_item, r3);
        int r32 = R.id.shortcut_widget_item_divider;
        if (r22 != getCount()) goto L256;
        r4 = 8;
    L256:
        r13.setViewVisibility(r32, r4);
        return r13;
    L252:
        r65.putLong("chatId", -r5.longValue());
        goto L253
    L247:
        r13.setBoolean(R.id.shortcut_widget_item_badge, "setEnabled", r16);
        r13.setInt(R.id.shortcut_widget_item_badge, "setBackgroundResource", R.drawable.widget_badge_background);
    L248:
        r13.setViewVisibility(R.id.shortcut_widget_item_badge, 8);
    L186:
        e = move-exception;
        FileLog.e(e);
        r93 = r92;
        goto L233
    L147:
        if (r72.isVoice() == false) goto L150;
        r152 = "🎤 ";
        goto L155
    L150:
        if (r72.isMusic() == false) goto L153;
        r152 = "🎧 ";
        goto L155
    L153:
        if (r72.isPhoto() == false) goto L155;
        r152 = "🖼 ";
        goto L155
    L158:
        if (r72.messageOwner.media != null) goto L160;
    L177:
        String r012 = r72.messageOwner.message;
        if (r012 != null) goto L180;
        r011 = SpannableStringBuilder.valueOf(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L156
    L180:
        if (r012.length() <= 150) goto L182;
        r012 = r012.substring(0, 150);
    L182:
        r011 = SpannableStringBuilder.valueOf(String.format("%2$s: \u2068%1$s\u2069", new Object[]{r012.replace('\n', ' ').trim(), r63}));
        goto L156
    L160:
        if (r72.isMediaEmpty() == true) goto L177;
        r124 = this.mContext.getResources().getColor(R.color.widget_action_text);
        TLRPC.MessageMedia r013 = r72.messageOwner.media;
        if ((r013 instanceof TLRPC.TL_messageMediaPoll) == false) goto L166;
        String r014 = String.format("📊 \u2068%s\u2069", new Object[]{((TLRPC.TL_messageMediaPoll) r013).poll.question.text});
    L172:
        SpannableStringBuilder r94 = SpannableStringBuilder.valueOf(String.format("%2$s: \u2068%1$s\u2069", new Object[]{r014.replace('\n', ' '), r63}));
        r94.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_attachMessage), r63.length() + 2, r94.length(), 33);     // Catch: Exception -> L175
        r92 = r94;
    L175:
        e = move-exception;
        FileLog.e(e);
        r92 = r94;
        goto L262
    L166:
        if ((r013 instanceof TLRPC.TL_messageMediaGame) == false) goto L169;
        r014 = String.format("🎮 \u2068%s\u2069", new Object[]{r013.game.title});
        goto L172
    L169:
        if (r72.type != 14) goto L171;
        r014 = String.format("🎧 \u2068%s - %s\u2069", new Object[]{r72.getMusicAuthor(), r72.getMusicTitle()});
        goto L172
    L171:
        r014 = r72.messageText.toString();
        goto L172
    L135:
        if (r113 == null) goto L137;
        r08 = UserObject.getFirstName(r113).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET);
        goto L134
    L137:
        r08 = "DELETED";
    L188:
        TLRPC.MessageMedia r015 = r72.messageOwner.media;
        if ((r015 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L196;
        if ((r015.photo instanceof TLRPC.TL_photoEmpty) == false) goto L196;
        if (r015.ttl_seconds == 0) goto L196;
        r93 = LocaleController.getString(R.string.AttachPhotoExpired);
    L196:
        if ((r015 instanceof TLRPC.TL_messageMediaDocument) == false) goto L203;
        if ((r015.document instanceof TLRPC.TL_documentEmpty) == false) goto L203;
        if (r015.ttl_seconds == 0) goto L203;
        r93 = LocaleController.getString(R.string.AttachVideoExpired);
    L203:
        if (r72.caption == null) goto L218;
        if (r72.isVideo() == false) goto L208;
        r152 = "📹 ";
    L216:
        r93 = r152 + r72.caption;
        goto L233
    L208:
        if (r72.isVoice() == false) goto L211;
        r152 = "🎤 ";
        goto L216
    L211:
        if (r72.isMusic() == false) goto L214;
        r152 = "🎧 ";
        goto L216
    L214:
        if (r72.isPhoto() == false) goto L216;
        r152 = "🖼 ";
        goto L216
    L218:
        if ((r015 instanceof TLRPC.TL_messageMediaPoll) == false) goto L222;
        CharSequence r016 = "📊 " + ((TLRPC.TL_messageMediaPoll) r015).poll.question.text;
    L220:
        CharSequence r95 = r016;
        r93 = r95;
        if (r72.messageOwner.media == null) goto L233;
        r93 = r95;
        if (r72.isMediaEmpty() == true) goto L233;
        r124 = this.mContext.getResources().getColor(R.color.widget_action_text);
        r93 = r95;
        goto L233
    L222:
        if ((r015 instanceof TLRPC.TL_messageMediaGame) == false) goto L225;
        r016 = "🎮 " + r72.messageOwner.media.game.title;
        goto L220
    L225:
        if (r72.type != 14) goto L227;
        r016 = String.format("🎧 %s - %s", new Object[]{r72.getMusicAuthor(), r72.getMusicTitle()});
        goto L220
    L227:
        r016 = r72.messageText;
        AndroidUtilities.highlightText(r016, r72.highlightedWords, null);
        goto L220
    L116:
        if (ChatObject.isChannel(r62) == false) goto L122;
        TLRPC.MessageAction r017 = r72.messageOwner.action;
        CharSequence r96 = r9;
        if ((r017 instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L123;
        r96 = r9;
        if ((r017 instanceof TLRPC.TL_messageActionChannelMigrateFrom) == false) goto L122;
    L123:
        r124 = this.mContext.getResources().getColor(R.color.widget_action_text);
        r93 = r96;
    L122:
        r96 = r72.messageText;
        goto L123
    L112:
        r07 = r142.getMessagesController().getChat(Long.valueOf(-r112));
        r113 = null;
        goto L113
    L234:
        if (r82 == null) goto L238;
        int r018 = r82.last_message_date;
        if (r018 == 0) goto L238;
        r13.setTextViewText(R.id.shortcut_widget_item_time, LocaleController.stringForMessageListDate(r018));
    L239:
        r13.setTextViewText(R.id.shortcut_widget_item_message, _UrlKt.FRAGMENT_ENCODE_SET);
    L238:
        r13.setTextViewText(R.id.shortcut_widget_item_time, _UrlKt.FRAGMENT_ENCODE_SET);
    L105:
        th = th;
    L106:
        FileLog.e(th);
        goto L107
    L87:
        if (r62.forum == false) goto L89;
        r123 = true;
        goto L90
    L81:
        if (UserObject.isUserSelf(r04) == false) goto L84;
        r8.setAvatarType(1);     // Catch: Throwable -> L71
        goto L84
    L83:
        r8 = new AvatarDrawable();     // Catch: Throwable -> L71
        r8.setInfo(this.accountInstance.getCurrentAccount(), r62);     // Catch: Throwable -> L71
        goto L84
    L91:
        Shader.TileMode r83 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L71
        BitmapShader r019 = new BitmapShader(r122, r83, r83);     // Catch: Throwable -> L71
        if (this.roundPaint != null) goto L94;
        this.roundPaint = new Paint(1);     // Catch: Throwable -> L71
        this.bitmapRect = new RectF();     // Catch: Throwable -> L71
    L94:
        float r84 = r14 / r122.getWidth();     // Catch: Throwable -> L71
        r7.save();     // Catch: Throwable -> L71
        r7.scale(r84, r84);     // Catch: Throwable -> L71
        float r85 = r122.getWidth();     // Catch: Throwable -> L71
        if (r62 != null) goto L97;
    L99:
        boolean r143 = false;
    L100:
        float r86 = ExteraConfig.getAvatarCorners(r85, true, r143);     // Catch: Throwable -> L71
        this.roundPaint.setShader(r019);     // Catch: Throwable -> L71
        r16 = true;
        this.bitmapRect.set(0.0f, 0.0f, r122.getWidth(), r122.getHeight());     // Catch: Throwable -> L105
        r7.drawRoundRect(this.bitmapRect, r86, r86, this.roundPaint);     // Catch: Throwable -> L105
        r7.restore();     // Catch: Throwable -> L105
        goto L103
    L97:
        if (r62.forum == false) goto L99;
        r143 = true;
        goto L100
    L264:
        r122 = BitmapFactory.decodeFile(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r12, true).toString());     // Catch: Throwable -> L71
    L71:
        th = th;
        r16 = true;
        goto L106
    L15:
        if (UserObject.isUserSelf(r04) == false) goto L18;
        String r66 = LocaleController.getString(R.string.SavedMessages);
    L25:
        if (UserObject.isReplyUser(r04) == false) goto L27;
    L37:
        r11 = r66;
        goto L38
    L27:
        if (UserObject.isUserSelf(r04) == true) goto L37;
        TLRPC.UserProfilePhoto r114 = r04.photo;
        if (r114 == null) goto L37;
        TLRPC.FileLocation r115 = r114.photo_small;
        if (r115 == null) goto L37;
        if (r115.volume_id == 0) goto L37;
        if (r115.local_id == 0) goto L37;
        r12 = r115;
        r11 = r66;
        r62 = null;
        goto L68
    L18:
        if (UserObject.isReplyUser(r04) == false) goto L21;
        r66 = LocaleController.getString(R.string.RepliesTitle);
        goto L25
    L21:
        if (UserObject.isDeleted(r04) == false) goto L23;
        r66 = LocaleController.getString(R.string.HiddenName);
        goto L25
    L23:
        r66 = ContactsController.formatName(r04.first_name, r04.last_name);
        goto L25
    L40:
        TLRPC.Chat r020 = r6.getMessagesController().getChat(Long.valueOf(-r5.longValue()));
        if (r020 != null) goto L43;
        r62 = r020;
        r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        r04 = null;
        r12 = null;
        goto L68
    L43:
        if (ChatObject.isMonoForum(r020) == false) goto L57;
        String r67 = ForumUtilities.getMonoForumTitle(this.accountInstance.getCurrentAccount(), r020);
        TLRPC.Chat r116 = this.accountInstance.getMessagesController().getChat(Long.valueOf(r020.linked_monoforum_id));
        if (r116 == null) goto L55;
        TLRPC.ChatPhoto r117 = r116.photo;
        if (r117 == null) goto L55;
        TLRPC.FileLocation r118 = r117.photo_small;
        if (r118 == null) goto L55;
        if (r118.volume_id == 0) goto L55;
        if (r118.local_id == 0) goto L55;
    L56:
        r12 = r118;
        r11 = r67;
        r62 = r020;
        r04 = null;
    L55:
        r118 = null;
        goto L56
    L57:
        r67 = r020.title;
        TLRPC.ChatPhoto r119 = r020.photo;
        if (r119 == null) goto L66;
        r118 = r119.photo_small;
        if (r118 == null) goto L66;
        if (r118.volume_id == 0) goto L66;
        if (r118.local_id != 0) goto L56;
    L66:
        r11 = r67;
        r12 = null;
        r62 = r020;
        r04 = null;
        goto L68
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public int getViewTypeCount() {
        return 2;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onCreate() {
        ApplicationLoader.postInitApplication();
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onDataSetChanged() {
        this.dids.clear();
        this.messageObjects.clear();
        AccountInstance r0 = this.accountInstance;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getUserConfig().isClientActivated() == false) goto L12;
        ArrayList<TLRPC.User> r7 = new ArrayList();
        ArrayList<TLRPC.Chat> r8 = new ArrayList();
        LongSparseArray<TLRPC.Message> r6 = new LongSparseArray();
        this.accountInstance.getMessagesStorage().getWidgetDialogs(this.appWidgetId, 0, this.dids, this.dialogs, r6, r7, r8);
        this.accountInstance.getMessagesController().putUsers(r7, true);
        this.accountInstance.getMessagesController().putChats(r8, true);
        this.messageObjects.clear();
        int r02 = r6.size();
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L13;
        MessageObject r72 = new MessageObject(this.accountInstance.getCurrentAccount(), r6.valueAt(r1), null, null, false, true);
        this.messageObjects.put(r6.keyAt(r1), r72);
        r1 = r1 + 1;
        goto L8
    L13:
        return;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onDestroy() {
    }
}
