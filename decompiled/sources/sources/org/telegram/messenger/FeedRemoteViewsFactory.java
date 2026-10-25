package org.telegram.messenger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import androidx.core.content.FileProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
class FeedRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory, NotificationCenter.NotificationCenterDelegate {
    private AccountInstance accountInstance;
    private int classGuid;
    private CountDownLatch countDownLatch;
    private long dialogId;
    private Context mContext;
    private ArrayList<MessageObject> messages;

    /* renamed from: $r8$lambda$VEPY1VgyhNJe9AoKrbR-aSe50_U, reason: not valid java name */
    public static /* synthetic */ void m4003$r8$lambda$VEPY1VgyhNJe9AoKrbRaSe50_U(FeedRemoteViewsFactory r0) {
        r0.lambda$onDataSetChanged$1();
    }

    public static /* synthetic */ void $r8$lambda$v12pvoGishCMExjEcF4g11zsA0w(FeedRemoteViewsFactory r0) {
        r0.lambda$onDestroy$0();
    }

    public FeedRemoteViewsFactory(Context r4, Intent r5) {
        this.messages = new ArrayList();
        this.countDownLatch = new CountDownLatch(1);
        this.mContext = r4;
        int r52 = r5.getIntExtra("appWidgetId", 0);
        SharedPreferences r42 = r4.getSharedPreferences("shortcut_widget", 0);
        int r0 = r42.getInt("account" + r52, -1);
        if (r0 < 0) goto L6;
        this.dialogId = r42.getLong("dialogId" + r52, 0);
        this.accountInstance = AccountInstance.getInstance(r0);
        return;
    }

    private /* synthetic */ void lambda$onDataSetChanged$1() {
        this.accountInstance.getNotificationCenter().addObserver(this, NotificationCenter.messagesDidLoad);
        if (this.classGuid != 0) goto L5;
        this.classGuid = ConnectionsManager.generateClassGuid();
    L5:
        this.accountInstance.getMessagesController().loadMessages(this.dialogId, 0, false, 20, 0, 0, true, 0, this.classGuid, 0, 0, 0, 0, 0, 1, false);
    }

    private /* synthetic */ void lambda$onDestroy$0() {
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.messagesDidLoad);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.messagesDidLoad) goto L5;
        return;
    L5:
        if (((Integer) r3[10]).intValue() != this.classGuid) goto L9;
        this.messages.clear();
        ArrayList r12 = (ArrayList) r3[2];
        this.messages.addAll(r12);
        this.countDownLatch.countDown();
        return;
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public int getCount() {
        return this.messages.size();
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
    public RemoteViews getViewAt(int r6) {
        MessageObject r62 = this.messages.get(r6);
        RemoteViews r0 = new RemoteViews(this.mContext.getPackageName(), R.layout.feed_widget_item);
        if (r62.type != 0) goto L6;
        r0.setTextViewText(R.id.feed_widget_item_text, r62.messageText);
        r0.setViewVisibility(R.id.feed_widget_item_text, 0);
    L9:
        ArrayList<TLRPC.PhotoSize> r1 = r62.photoThumbs;
        if (r1 != null) goto L12;
    L18:
        r0.setViewVisibility(R.id.feed_widget_item_image, 8);
    L19:
        Bundle r12 = new Bundle();
        r12.putLong("chatId", -r62.getDialogId());
        r12.putInt("message_id", r62.getId());
        r12.putInt("currentAccount", this.accountInstance.getCurrentAccount());
        Intent r5 = new Intent();
        r5.putExtras(r12);
        r0.setOnClickFillInIntent(R.id.shortcut_widget_item, r5);
        return r0;
    L12:
        if (r1.isEmpty() == true) goto L18;
        File r13 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(FileLoader.getClosestPhotoSizeWithSize(r62.photoThumbs, AndroidUtilities.getPhotoSize()));
        if (r13.exists() == false) goto L17;
        r0.setViewVisibility(R.id.feed_widget_item_image, 0);
        Uri r14 = FileProvider.getUriForFile(this.mContext, ApplicationLoader.getApplicationId() + ".provider", r13);
        grantUriAccessToWidget(this.mContext, r14);
        r0.setImageViewUri(R.id.feed_widget_item_image, r14);
        goto L19
    L17:
        r0.setViewVisibility(R.id.feed_widget_item_image, 8);
        goto L19
    L6:
        if (TextUtils.isEmpty(r62.caption) == false) goto L8;
        r0.setViewVisibility(R.id.feed_widget_item_text, 8);
        goto L9
    L8:
        r0.setTextViewText(R.id.feed_widget_item_text, r62.caption);
        r0.setViewVisibility(R.id.feed_widget_item_text, 0);
        goto L9
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public int getViewTypeCount() {
        return 1;
    }

    public void grantUriAccessToWidget(Context r3, Uri r4) {
        Intent r2 = new Intent("android.intent.action.MAIN");
        r2.addCategory("android.intent.category.HOME");
        Iterator<ResolveInfo> r22 = r3.getPackageManager().queryIntentActivities(r2, 65536).iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r3.grantUriPermission(r22.next().activityInfo.packageName, r4, 1);
        goto L4
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
        AccountInstance r0 = this.accountInstance;
        if (r0 != null) goto L5;
    L13:
        this.messages.clear();
        return;
    L5:
        if (r0.getUserConfig().isClientActivated() == false) goto L13;
        AndroidUtilities.runOnUIThread(new FeedRemoteViewsFactory$$ExternalSyntheticLambda1(this));
        this.countDownLatch.await();     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // android.widget.RemoteViewsService.RemoteViewsFactory
    public void onDestroy() {
        if (this.accountInstance == null) goto L6;
        AndroidUtilities.runOnUIThread(new FeedRemoteViewsFactory$$ExternalSyntheticLambda0(this));
        return;
    }
}
