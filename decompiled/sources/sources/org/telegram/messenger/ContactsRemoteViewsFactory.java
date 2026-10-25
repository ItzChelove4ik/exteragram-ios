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

/* loaded from: classes5.dex */
class ContactsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private AccountInstance accountInstance;
    private int appWidgetId;
    private RectF bitmapRect;
    private boolean deleted;
    private LongSparseArray<TLRPC.Dialog> dialogs;
    private ArrayList<Long> dids;
    private Context mContext;
    private Paint roundPaint;

    public ContactsRemoteViewsFactory(Context r3, Intent r4) {
        this.dids = new ArrayList();
        this.dialogs = new LongSparseArray();
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
        return ((int) Math.ceil(this.dids.size() / 2.0f)) + 1;
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
    public RemoteViews getViewAt(int r15) {
        if (this.deleted == false) goto L7;
        RemoteViews r152 = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_deleted);
        r152.setTextViewText(R.id.widget_deleted_text, LocaleController.getString(R.string.WidgetLoggedOff));
        return r152;
    L7:
        if (r15 < (getCount() - 1)) goto L10;
        RemoteViews r153 = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_edititem);
        r153.setTextViewText(R.id.widget_edititem_text, LocaleController.getString(R.string.TapToEditWidgetShort));
        Bundle r0 = new Bundle();
        r0.putInt("appWidgetId", this.appWidgetId);
        r0.putInt("appWidgetType", 1);
        r0.putInt("currentAccount", this.accountInstance.getCurrentAccount());
        Intent r14 = new Intent();
        r14.putExtras(r0);
        r153.setOnClickFillInIntent(R.id.widget_edititem, r14);
        return r153;
    L10:
        RemoteViews r02 = new RemoteViews(this.mContext.getPackageName(), R.layout.contacts_widget_item);
        int r4 = 0;
    L12:
        if (r4 >= 2) goto L135;
        int r5 = (2 * r15) + r4;
        if (r5 < this.dids.size()) goto L19;
        if (r4 != 0) goto L17;
        int r52 = R.id.contacts_widget_item1;
    L18:
        r02.setViewVisibility(r52, 4);
    L134:
        r4 = r4 + 1;
        goto L12
    L17:
        r52 = R.id.contacts_widget_item2;
        goto L18
    L19:
        if (r4 != 0) goto L21;
        int r6 = R.id.contacts_widget_item1;
    L22:
        r02.setViewVisibility(r6, 0);
        Long r53 = this.dids.get(r5);
        boolean r62 = DialogObject.isUserDialog(r53.longValue());
        AccountInstance r7 = this.accountInstance;
        if (r62 == false) goto L49;
        TLRPC.User r63 = r7.getMessagesController().getUser(r53);
        if (UserObject.isUserSelf(r63) == false) goto L28;
        String r72 = LocaleController.getString(R.string.SavedMessages);
    L35:
        if (UserObject.isReplyUser(r63) == false) goto L37;
    L48:
        String r8 = r72;
        TLRPC.Chat r73 = null;
        TLRPC.FileLocation r11 = null;
    L62:
        if (r4 != 0) goto L64;
        int r9 = R.id.contacts_widget_item_text1;
    L65:
        r02.setTextViewText(r9, r8);
        if (r11 != null) goto L136;
        Bitmap r82 = null;
    L71:
        int r92 = AndroidUtilities.dp(48.0f);     // Catch: Throwable -> L68
        Bitmap r112 = Bitmap.createBitmap(r92, r92, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L68
        r112.eraseColor(0);     // Catch: Throwable -> L68
        Canvas r12 = new Canvas(r112);     // Catch: Throwable -> L68
        if (r82 != null) goto L88;
        if (r63 == null) goto L80;
        AvatarDrawable r83 = new AvatarDrawable(r63);     // Catch: Throwable -> L68
        if (UserObject.isReplyUser(r63) == false) goto L78;
        r83.setAvatarType(12);     // Catch: Throwable -> L68
    L81:
        r83.setBounds(0, 0, r92, r92);     // Catch: Throwable -> L68
        float r64 = r92;
        if (r73 != null) goto L84;
    L86:
        boolean r74 = false;
    L87:
        r83.setRoundRadius(ExteraConfig.getAvatarCorners(r64, true, r74));     // Catch: Throwable -> L68
        r83.draw(r12);     // Catch: Throwable -> L68
    L98:
        r12.setBitmap(null);     // Catch: Throwable -> L68
        if (r4 != 0) goto L101;
        int r65 = R.id.contacts_widget_item_avatar1;     // Catch: Throwable -> L68
    L102:
        r02.setImageViewBitmap(r65, r112);     // Catch: Throwable -> L68
    L105:
        TLRPC.Dialog r66 = this.dialogs.get(r53.longValue());
        if (r66 == null) goto L121;
        int r67 = r66.unread_count;
        if (r67 <= 0) goto L121;
        if (r67 <= 99) goto L112;
        String r68 = String.format("%d+", new Object[]{99});
    L113:
        if (r4 != 0) goto L115;
        int r75 = R.id.contacts_widget_item_badge1;
    L116:
        r02.setTextViewText(r75, r68);
        if (r4 != 0) goto L119;
        int r69 = R.id.contacts_widget_item_badge_bg1;
    L120:
        r02.setViewVisibility(r69, 0);
    L125:
        Bundle r610 = new Bundle();
        if (DialogObject.isUserDialog(r53.longValue()) == false) goto L128;
        r610.putLong("userId", r53.longValue());
    L129:
        r610.putInt("currentAccount", this.accountInstance.getCurrentAccount());
        Intent r54 = new Intent();
        r54.putExtras(r610);
        if (r4 != 0) goto L132;
        int r611 = R.id.contacts_widget_item1;
    L133:
        r02.setOnClickFillInIntent(r611, r54);
        goto L134
    L132:
        r611 = R.id.contacts_widget_item2;
        goto L133
    L128:
        r610.putLong("chatId", -r53.longValue());
        goto L129
    L119:
        r69 = R.id.contacts_widget_item_badge_bg2;
        goto L120
    L115:
        r75 = R.id.contacts_widget_item_badge2;
        goto L116
    L112:
        r68 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r67)});
    L121:
        if (r4 != 0) goto L123;
        int r612 = R.id.contacts_widget_item_badge_bg1;
    L124:
        r02.setViewVisibility(r612, 8);
        goto L125
    L123:
        r612 = R.id.contacts_widget_item_badge_bg2;
        goto L124
    L101:
        r65 = R.id.contacts_widget_item_avatar2;     // Catch: Throwable -> L68
        goto L102
    L84:
        if (r73.forum == false) goto L86;
        r74 = true;
        goto L87
    L78:
        if (UserObject.isUserSelf(r63) == false) goto L81;
        r83.setAvatarType(1);     // Catch: Throwable -> L68
        goto L81
    L80:
        r83 = new AvatarDrawable();     // Catch: Throwable -> L68
        r83.setInfo(this.accountInstance.getCurrentAccount(), r73);     // Catch: Throwable -> L68
        goto L81
    L88:
        Shader.TileMode r13 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L68
        BitmapShader r613 = new BitmapShader(r82, r13, r13);     // Catch: Throwable -> L68
        if (this.roundPaint != null) goto L91;
        this.roundPaint = new Paint(1);     // Catch: Throwable -> L68
        this.bitmapRect = new RectF();     // Catch: Throwable -> L68
    L91:
        float r93 = r92 / r82.getWidth();     // Catch: Throwable -> L68
        r12.save();     // Catch: Throwable -> L68
        r12.scale(r93, r93);     // Catch: Throwable -> L68
        float r94 = r82.getWidth();     // Catch: Throwable -> L68
        if (r73 != null) goto L94;
    L96:
        boolean r76 = false;
    L97:
        float r77 = ExteraConfig.getAvatarCorners(r94, true, r76);     // Catch: Throwable -> L68
        this.roundPaint.setShader(r613);     // Catch: Throwable -> L68
        this.bitmapRect.set(0.0f, 0.0f, r82.getWidth(), r82.getHeight());     // Catch: Throwable -> L68
        r12.drawRoundRect(this.bitmapRect, r77, r77, this.roundPaint);     // Catch: Throwable -> L68
        r12.restore();     // Catch: Throwable -> L68
        goto L98
    L94:
        if (r73.forum == false) goto L96;
        r76 = true;
    L68:
        th = move-exception;
        FileLog.e(th);
        goto L105
    L136:
        r82 = BitmapFactory.decodeFile(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r11, true).toString());     // Catch: Throwable -> L68
        goto L71
    L64:
        r9 = R.id.contacts_widget_item_text2;
        goto L65
    L37:
        if (UserObject.isUserSelf(r63) == true) goto L48;
        if (r63 == null) goto L48;
        TLRPC.UserProfilePhoto r113 = r63.photo;
        if (r113 == null) goto L48;
        r11 = r113.photo_small;
        if (r11 == null) goto L48;
        if (r11.volume_id == 0) goto L48;
        if (r11.local_id == 0) goto L48;
        r8 = r72;
        r73 = null;
        goto L62
    L28:
        if (UserObject.isReplyUser(r63) == false) goto L31;
        r72 = LocaleController.getString(R.string.RepliesTitle);
        goto L35
    L31:
        if (UserObject.isDeleted(r63) == false) goto L33;
        r72 = LocaleController.getString(R.string.HiddenName);
        goto L35
    L33:
        r72 = UserObject.getFirstName(r63);
        goto L35
    L49:
        TLRPC.Chat r614 = r7.getMessagesController().getChat(Long.valueOf(-r53.longValue()));
        if (r614 == null) goto L61;
        String r78 = r614.title;
        TLRPC.ChatPhoto r114 = r614.photo;
        if (r114 == null) goto L60;
        r11 = r114.photo_small;
        if (r11 == null) goto L60;
        if (r11.volume_id == 0) goto L60;
        if (r11.local_id == 0) goto L60;
        r8 = r78;
        r73 = r614;
        r63 = null;
    L60:
        r8 = r78;
        r11 = null;
        r73 = r614;
        r63 = null;
        goto L62
    L61:
        r78 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L60
    L21:
        r6 = R.id.contacts_widget_item2;
        goto L22
    L135:
        return r02;
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
        AccountInstance r0 = this.accountInstance;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getUserConfig().isClientActivated() == false) goto L10;
        ArrayList<TLRPC.User> r7 = new ArrayList();
        ArrayList<TLRPC.Chat> r8 = new ArrayList();
        LongSparseArray<TLRPC.Message> r6 = new LongSparseArray();
        this.accountInstance.getMessagesStorage().getWidgetDialogs(this.appWidgetId, 1, this.dids, this.dialogs, r6, r7, r8);
        this.accountInstance.getMessagesController().putUsers(r7, true);
        this.accountInstance.getMessagesController().putChats(r8, true);
        return;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onDestroy() {
    }
}
