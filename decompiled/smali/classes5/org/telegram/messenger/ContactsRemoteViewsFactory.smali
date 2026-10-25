.class Lorg/telegram/messenger/ContactsRemoteViewsFactory;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/widget/RemoteViewsService$RemoteViewsFactory;


# instance fields
.field private accountInstance:Lorg/telegram/messenger/AccountInstance;

.field private appWidgetId:I

.field private bitmapRect:Landroid/graphics/RectF;

.field private deleted:Z

.field private dialogs:Landroidx/collection/LongSparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/collection/LongSparseArray<",
            "Lorg/telegram/tgnet/TLRPC$Dialog;",
            ">;"
        }
    .end annotation
.end field

.field private dids:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private mContext:Landroid/content/Context;

.field private roundPaint:Landroid/graphics/Paint;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 2

    .line 51
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 42
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dids:Ljava/util/ArrayList;

    .line 48
    new-instance v0, Landroidx/collection/LongSparseArray;

    invoke-direct {v0}, Landroidx/collection/LongSparseArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dialogs:Landroidx/collection/LongSparseArray;

    .line 52
    iput-object p1, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->mContext:Landroid/content/Context;

    .line 53
    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->createDialogsResources(Landroid/content/Context;)V

    .line 54
    const-string v0, "appWidgetId"

    const/4 v1, 0x0

    invoke-virtual {p2, v0, v1}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p2

    iput p2, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->appWidgetId:I

    .line 55
    const-string p2, "shortcut_widget"

    invoke-virtual {p1, p2, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p1

    .line 56
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "account"

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->appWidgetId:I

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    const/4 v0, -0x1

    invoke-interface {p1, p2, v0}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result p2

    if-ltz p2, :cond_0

    .line 58
    invoke-static {p2}, Lorg/telegram/messenger/AccountInstance;->getInstance(I)Lorg/telegram/messenger/AccountInstance;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    .line 60
    :cond_0
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "deleted"

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->appWidgetId:I

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-interface {p1, p2, v1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p1

    if-nez p1, :cond_1

    iget-object p1, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    if-nez p1, :cond_2

    :cond_1
    const/4 v1, 0x1

    :cond_2
    iput-boolean v1, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->deleted:Z

    return-void
.end method


# virtual methods
.method public getCount()I
    .locals 4

    .line 72
    iget-boolean v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->deleted:Z

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    return v1

    .line 75
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dids:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result p0

    int-to-float p0, p0

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p0, v0

    float-to-double v2, p0

    invoke-static {v2, v3}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v2

    double-to-int p0, v2

    add-int/2addr p0, v1

    return p0
.end method

.method public getItemId(I)J
    .locals 0

    int-to-long p0, p1

    return-wide p0
.end method

.method public getLoadingView()Landroid/widget/RemoteViews;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public getViewAt(I)Landroid/widget/RemoteViews;
    .locals 14

    .line 80
    iget-boolean v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->deleted:Z

    if-eqz v0, :cond_0

    .line 81
    new-instance p1, Landroid/widget/RemoteViews;

    iget-object p0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->mContext:Landroid/content/Context;

    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/R$layout;->widget_deleted:I

    invoke-direct {p1, p0, v0}, Landroid/widget/RemoteViews;-><init>(Ljava/lang/String;I)V

    .line 82
    sget p0, Lorg/telegram/messenger/R$id;->widget_deleted_text:I

    sget v0, Lorg/telegram/messenger/R$string;->WidgetLoggedOff:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Landroid/widget/RemoteViews;->setTextViewText(ILjava/lang/CharSequence;)V

    return-object p1

    .line 84
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->getCount()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    const-string v2, "currentAccount"

    if-lt p1, v0, :cond_1

    .line 85
    new-instance p1, Landroid/widget/RemoteViews;

    iget-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    sget v3, Lorg/telegram/messenger/R$layout;->widget_edititem:I

    invoke-direct {p1, v0, v3}, Landroid/widget/RemoteViews;-><init>(Ljava/lang/String;I)V

    .line 86
    sget v0, Lorg/telegram/messenger/R$id;->widget_edititem_text:I

    sget v3, Lorg/telegram/messenger/R$string;->TapToEditWidgetShort:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1, v0, v3}, Landroid/widget/RemoteViews;->setTextViewText(ILjava/lang/CharSequence;)V

    .line 87
    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    .line 88
    const-string v3, "appWidgetId"

    iget v4, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->appWidgetId:I

    invoke-virtual {v0, v3, v4}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 89
    const-string v3, "appWidgetType"

    invoke-virtual {v0, v3, v1}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 90
    iget-object p0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    invoke-virtual {p0}, Lorg/telegram/messenger/AccountInstance;->getCurrentAccount()I

    move-result p0

    invoke-virtual {v0, v2, p0}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 91
    new-instance p0, Landroid/content/Intent;

    invoke-direct {p0}, Landroid/content/Intent;-><init>()V

    .line 92
    invoke-virtual {p0, v0}, Landroid/content/Intent;->putExtras(Landroid/os/Bundle;)Landroid/content/Intent;

    .line 93
    sget v0, Lorg/telegram/messenger/R$id;->widget_edititem:I

    invoke-virtual {p1, v0, p0}, Landroid/widget/RemoteViews;->setOnClickFillInIntent(ILandroid/content/Intent;)V

    return-object p1

    .line 96
    :cond_1
    new-instance v0, Landroid/widget/RemoteViews;

    iget-object v3, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->mContext:Landroid/content/Context;

    invoke-virtual {v3}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$layout;->contacts_widget_item:I

    invoke-direct {v0, v3, v4}, Landroid/widget/RemoteViews;-><init>(Ljava/lang/String;I)V

    const/4 v3, 0x0

    move v4, v3

    :goto_0
    const/4 v5, 0x2

    if-ge v4, v5, :cond_1d

    mul-int/2addr v5, p1

    add-int/2addr v5, v4

    .line 99
    iget-object v6, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dids:Ljava/util/ArrayList;

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-lt v5, v6, :cond_3

    if-nez v4, :cond_2

    .line 100
    sget v5, Lorg/telegram/messenger/R$id;->contacts_widget_item1:I

    goto :goto_1

    :cond_2
    sget v5, Lorg/telegram/messenger/R$id;->contacts_widget_item2:I

    :goto_1
    const/4 v6, 0x4

    invoke-virtual {v0, v5, v6}, Landroid/widget/RemoteViews;->setViewVisibility(II)V

    goto/16 :goto_16

    :cond_3
    if-nez v4, :cond_4

    .line 102
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item1:I

    goto :goto_2

    :cond_4
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item2:I

    :goto_2
    invoke-virtual {v0, v6, v3}, Landroid/widget/RemoteViews;->setViewVisibility(II)V

    .line 104
    iget-object v6, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dids:Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/Long;

    .line 110
    invoke-virtual {v5}, Ljava/lang/Long;->longValue()J

    move-result-wide v6

    invoke-static {v6, v7}, Lorg/telegram/messenger/DialogObject;->isUserDialog(J)Z

    move-result v6

    .line 125
    iget-object v7, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    const-wide/16 v8, 0x0

    const/4 v10, 0x0

    if-eqz v6, :cond_9

    .line 111
    invoke-virtual {v7}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v6

    invoke-virtual {v6, v5}, Lorg/telegram/messenger/MessagesController;->getUser(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v6

    .line 112
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isUserSelf(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v7

    if-eqz v7, :cond_5

    .line 113
    sget v7, Lorg/telegram/messenger/R$string;->SavedMessages:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    goto :goto_3

    .line 114
    :cond_5
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isReplyUser(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v7

    if-eqz v7, :cond_6

    .line 115
    sget v7, Lorg/telegram/messenger/R$string;->RepliesTitle:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    goto :goto_3

    .line 116
    :cond_6
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isDeleted(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v7

    if-eqz v7, :cond_7

    .line 117
    sget v7, Lorg/telegram/messenger/R$string;->HiddenName:I

    invoke-static {v7}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    goto :goto_3

    .line 119
    :cond_7
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->getFirstName(Lorg/telegram/tgnet/TLRPC$User;)Ljava/lang/String;

    move-result-object v7

    .line 121
    :goto_3
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isReplyUser(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v11

    if-nez v11, :cond_8

    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isUserSelf(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v11

    if-nez v11, :cond_8

    if-eqz v6, :cond_8

    iget-object v11, v6, Lorg/telegram/tgnet/TLRPC$User;->photo:Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;

    if-eqz v11, :cond_8

    iget-object v11, v11, Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    if-eqz v11, :cond_8

    iget-wide v12, v11, Lorg/telegram/tgnet/TLRPC$FileLocation;->volume_id:J

    cmp-long v8, v12, v8

    if-eqz v8, :cond_8

    iget v8, v11, Lorg/telegram/tgnet/TLRPC$FileLocation;->local_id:I

    if-eqz v8, :cond_8

    move-object v8, v7

    move-object v7, v10

    goto :goto_5

    :cond_8
    move-object v8, v7

    move-object v7, v10

    move-object v11, v7

    goto :goto_5

    .line 125
    :cond_9
    invoke-virtual {v7}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v6

    invoke-virtual {v5}, Ljava/lang/Long;->longValue()J

    move-result-wide v11

    neg-long v11, v11

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v7

    invoke-virtual {v6, v7}, Lorg/telegram/messenger/MessagesController;->getChat(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object v6

    if-eqz v6, :cond_b

    .line 127
    iget-object v7, v6, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    .line 128
    iget-object v11, v6, Lorg/telegram/tgnet/TLRPC$Chat;->photo:Lorg/telegram/tgnet/TLRPC$ChatPhoto;

    if-eqz v11, :cond_a

    iget-object v11, v11, Lorg/telegram/tgnet/TLRPC$ChatPhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    if-eqz v11, :cond_a

    iget-wide v12, v11, Lorg/telegram/tgnet/TLRPC$FileLocation;->volume_id:J

    cmp-long v8, v12, v8

    if-eqz v8, :cond_a

    iget v8, v11, Lorg/telegram/tgnet/TLRPC$FileLocation;->local_id:I

    if-eqz v8, :cond_a

    move-object v8, v7

    move-object v7, v6

    move-object v6, v10

    goto :goto_5

    :cond_a
    :goto_4
    move-object v8, v7

    move-object v11, v10

    move-object v7, v6

    move-object v6, v11

    goto :goto_5

    .line 132
    :cond_b
    const-string v7, ""

    goto :goto_4

    :goto_5
    if-nez v4, :cond_c

    .line 135
    sget v9, Lorg/telegram/messenger/R$id;->contacts_widget_item_text1:I

    goto :goto_6

    :cond_c
    sget v9, Lorg/telegram/messenger/R$id;->contacts_widget_item_text2:I

    :goto_6
    invoke-virtual {v0, v9, v8}, Landroid/widget/RemoteViews;->setTextViewText(ILjava/lang/CharSequence;)V

    if-eqz v11, :cond_d

    .line 140
    :try_start_0
    sget v8, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v8}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v8

    invoke-virtual {v8, v11, v1}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;Z)Ljava/io/File;

    move-result-object v8

    .line 141
    invoke-virtual {v8}, Ljava/io/File;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v8}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v8

    goto :goto_7

    :catchall_0
    move-exception v6

    goto/16 :goto_d

    :cond_d
    move-object v8, v10

    :goto_7
    const/high16 v9, 0x42400000    # 48.0f

    .line 144
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v9

    .line 145
    sget-object v11, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v9, v9, v11}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v11

    .line 146
    invoke-virtual {v11, v3}, Landroid/graphics/Bitmap;->eraseColor(I)V

    .line 147
    new-instance v12, Landroid/graphics/Canvas;

    invoke-direct {v12, v11}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    if-nez v8, :cond_12

    if-eqz v6, :cond_f

    .line 151
    new-instance v8, Lorg/telegram/ui/Components/AvatarDrawable;

    invoke-direct {v8, v6}, Lorg/telegram/ui/Components/AvatarDrawable;-><init>(Lorg/telegram/tgnet/TLRPC$User;)V

    .line 152
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isReplyUser(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v13

    if-eqz v13, :cond_e

    const/16 v6, 0xc

    .line 153
    invoke-virtual {v8, v6}, Lorg/telegram/ui/Components/AvatarDrawable;->setAvatarType(I)V

    goto :goto_8

    .line 154
    :cond_e
    invoke-static {v6}, Lorg/telegram/messenger/UserObject;->isUserSelf(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v6

    if-eqz v6, :cond_10

    .line 155
    invoke-virtual {v8, v1}, Lorg/telegram/ui/Components/AvatarDrawable;->setAvatarType(I)V

    goto :goto_8

    .line 158
    :cond_f
    new-instance v8, Lorg/telegram/ui/Components/AvatarDrawable;

    invoke-direct {v8}, Lorg/telegram/ui/Components/AvatarDrawable;-><init>()V

    .line 159
    iget-object v6, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    invoke-virtual {v6}, Lorg/telegram/messenger/AccountInstance;->getCurrentAccount()I

    move-result v6

    invoke-virtual {v8, v6, v7}, Lorg/telegram/ui/Components/AvatarDrawable;->setInfo(ILorg/telegram/tgnet/TLRPC$Chat;)V

    .line 161
    :cond_10
    :goto_8
    invoke-virtual {v8, v3, v3, v9, v9}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    int-to-float v6, v9

    if-eqz v7, :cond_11

    .line 162
    iget-boolean v7, v7, Lorg/telegram/tgnet/TLRPC$Chat;->forum:Z

    if-eqz v7, :cond_11

    move v7, v1

    goto :goto_9

    :cond_11
    move v7, v3

    :goto_9
    invoke-static {v6, v1, v7}, Lcom/exteragram/messenger/ExteraConfig;->getAvatarCorners(FZZ)I

    move-result v6

    invoke-virtual {v8, v6}, Lorg/telegram/ui/Components/AvatarDrawable;->setRoundRadius(I)V

    .line 163
    invoke-virtual {v8, v12}, Lorg/telegram/ui/Components/AvatarDrawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_b

    .line 165
    :cond_12
    new-instance v6, Landroid/graphics/BitmapShader;

    sget-object v13, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct {v6, v8, v13, v13}, Landroid/graphics/BitmapShader;-><init>(Landroid/graphics/Bitmap;Landroid/graphics/Shader$TileMode;Landroid/graphics/Shader$TileMode;)V

    .line 166
    iget-object v13, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->roundPaint:Landroid/graphics/Paint;

    if-nez v13, :cond_13

    .line 167
    new-instance v13, Landroid/graphics/Paint;

    invoke-direct {v13, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v13, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->roundPaint:Landroid/graphics/Paint;

    .line 168
    new-instance v13, Landroid/graphics/RectF;

    invoke-direct {v13}, Landroid/graphics/RectF;-><init>()V

    iput-object v13, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->bitmapRect:Landroid/graphics/RectF;

    :cond_13
    int-to-float v9, v9

    .line 170
    invoke-virtual {v8}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v13

    int-to-float v13, v13

    div-float/2addr v9, v13

    .line 171
    invoke-virtual {v12}, Landroid/graphics/Canvas;->save()I

    .line 172
    invoke-virtual {v12, v9, v9}, Landroid/graphics/Canvas;->scale(FF)V

    .line 173
    invoke-virtual {v8}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v9

    int-to-float v9, v9

    if-eqz v7, :cond_14

    iget-boolean v7, v7, Lorg/telegram/tgnet/TLRPC$Chat;->forum:Z

    if-eqz v7, :cond_14

    move v7, v1

    goto :goto_a

    :cond_14
    move v7, v3

    :goto_a
    invoke-static {v9, v1, v7}, Lcom/exteragram/messenger/ExteraConfig;->getAvatarCorners(FZZ)I

    move-result v7

    int-to-float v7, v7

    .line 174
    iget-object v9, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->roundPaint:Landroid/graphics/Paint;

    invoke-virtual {v9, v6}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 175
    iget-object v6, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->bitmapRect:Landroid/graphics/RectF;

    invoke-virtual {v8}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v9

    int-to-float v9, v9

    invoke-virtual {v8}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v8

    int-to-float v8, v8

    const/4 v13, 0x0

    invoke-virtual {v6, v13, v13, v9, v8}, Landroid/graphics/RectF;->set(FFFF)V

    .line 176
    iget-object v6, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->bitmapRect:Landroid/graphics/RectF;

    iget-object v8, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->roundPaint:Landroid/graphics/Paint;

    invoke-virtual {v12, v6, v7, v7, v8}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 177
    invoke-virtual {v12}, Landroid/graphics/Canvas;->restore()V

    .line 179
    :goto_b
    invoke-virtual {v12, v10}, Landroid/graphics/Canvas;->setBitmap(Landroid/graphics/Bitmap;)V

    if-nez v4, :cond_15

    .line 180
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item_avatar1:I

    goto :goto_c

    :cond_15
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item_avatar2:I

    :goto_c
    invoke-virtual {v0, v6, v11}, Landroid/widget/RemoteViews;->setImageViewBitmap(ILandroid/graphics/Bitmap;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_e

    .line 182
    :goto_d
    invoke-static {v6}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 185
    :goto_e
    iget-object v6, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dialogs:Landroidx/collection/LongSparseArray;

    invoke-virtual {v5}, Ljava/lang/Long;->longValue()J

    move-result-wide v7

    invoke-virtual {v6, v7, v8}, Landroidx/collection/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/TLRPC$Dialog;

    if-eqz v6, :cond_19

    .line 187
    iget v6, v6, Lorg/telegram/tgnet/TLRPC$Dialog;->unread_count:I

    if-lez v6, :cond_19

    const/16 v7, 0x63

    if-le v6, v7, :cond_16

    .line 190
    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    filled-new-array {v6}, [Ljava/lang/Object;

    move-result-object v6

    const-string v7, "%d+"

    invoke-static {v7, v6}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    goto :goto_f

    .line 192
    :cond_16
    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    filled-new-array {v6}, [Ljava/lang/Object;

    move-result-object v6

    const-string v7, "%d"

    invoke-static {v7, v6}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v6

    :goto_f
    if-nez v4, :cond_17

    .line 194
    sget v7, Lorg/telegram/messenger/R$id;->contacts_widget_item_badge1:I

    goto :goto_10

    :cond_17
    sget v7, Lorg/telegram/messenger/R$id;->contacts_widget_item_badge2:I

    :goto_10
    invoke-virtual {v0, v7, v6}, Landroid/widget/RemoteViews;->setTextViewText(ILjava/lang/CharSequence;)V

    if-nez v4, :cond_18

    .line 195
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item_badge_bg1:I

    goto :goto_11

    :cond_18
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item_badge_bg2:I

    :goto_11
    invoke-virtual {v0, v6, v3}, Landroid/widget/RemoteViews;->setViewVisibility(II)V

    goto :goto_13

    :cond_19
    if-nez v4, :cond_1a

    .line 197
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item_badge_bg1:I

    goto :goto_12

    :cond_1a
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item_badge_bg2:I

    :goto_12
    const/16 v7, 0x8

    invoke-virtual {v0, v6, v7}, Landroid/widget/RemoteViews;->setViewVisibility(II)V

    .line 200
    :goto_13
    new-instance v6, Landroid/os/Bundle;

    invoke-direct {v6}, Landroid/os/Bundle;-><init>()V

    .line 202
    invoke-virtual {v5}, Ljava/lang/Long;->longValue()J

    move-result-wide v7

    invoke-static {v7, v8}, Lorg/telegram/messenger/DialogObject;->isUserDialog(J)Z

    move-result v7

    if-eqz v7, :cond_1b

    .line 203
    const-string/jumbo v7, "userId"

    invoke-virtual {v5}, Ljava/lang/Long;->longValue()J

    move-result-wide v8

    invoke-virtual {v6, v7, v8, v9}, Landroid/os/BaseBundle;->putLong(Ljava/lang/String;J)V

    goto :goto_14

    .line 205
    :cond_1b
    invoke-virtual {v5}, Ljava/lang/Long;->longValue()J

    move-result-wide v7

    neg-long v7, v7

    const-string v5, "chatId"

    invoke-virtual {v6, v5, v7, v8}, Landroid/os/BaseBundle;->putLong(Ljava/lang/String;J)V

    .line 207
    :goto_14
    iget-object v5, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    invoke-virtual {v5}, Lorg/telegram/messenger/AccountInstance;->getCurrentAccount()I

    move-result v5

    invoke-virtual {v6, v2, v5}, Landroid/os/BaseBundle;->putInt(Ljava/lang/String;I)V

    .line 209
    new-instance v5, Landroid/content/Intent;

    invoke-direct {v5}, Landroid/content/Intent;-><init>()V

    .line 210
    invoke-virtual {v5, v6}, Landroid/content/Intent;->putExtras(Landroid/os/Bundle;)Landroid/content/Intent;

    if-nez v4, :cond_1c

    .line 211
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item1:I

    goto :goto_15

    :cond_1c
    sget v6, Lorg/telegram/messenger/R$id;->contacts_widget_item2:I

    :goto_15
    invoke-virtual {v0, v6, v5}, Landroid/widget/RemoteViews;->setOnClickFillInIntent(ILandroid/content/Intent;)V

    :goto_16
    add-int/lit8 v4, v4, 0x1

    goto/16 :goto_0

    :cond_1d
    return-object v0
.end method

.method public getViewTypeCount()I
    .locals 0

    const/4 p0, 0x2

    return p0
.end method

.method public hasStableIds()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public onCreate()V
    .locals 0

    .line 64
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->postInitApplication()V

    return-void
.end method

.method public onDataSetChanged()V
    .locals 9

    .line 234
    iget-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dids:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 235
    iget-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/telegram/messenger/AccountInstance;->getUserConfig()Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->isClientActivated()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 238
    :cond_0
    new-instance v7, Ljava/util/ArrayList;

    invoke-direct {v7}, Ljava/util/ArrayList;-><init>()V

    .line 239
    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    .line 240
    new-instance v6, Landroidx/collection/LongSparseArray;

    invoke-direct {v6}, Landroidx/collection/LongSparseArray;-><init>()V

    .line 241
    iget-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    invoke-virtual {v0}, Lorg/telegram/messenger/AccountInstance;->getMessagesStorage()Lorg/telegram/messenger/MessagesStorage;

    move-result-object v1

    iget v2, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->appWidgetId:I

    iget-object v4, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dids:Ljava/util/ArrayList;

    iget-object v5, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->dialogs:Landroidx/collection/LongSparseArray;

    const/4 v3, 0x1

    invoke-virtual/range {v1 .. v8}, Lorg/telegram/messenger/MessagesStorage;->getWidgetDialogs(IILjava/util/ArrayList;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    .line 242
    iget-object v0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    invoke-virtual {v0}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v7, v1}, Lorg/telegram/messenger/MessagesController;->putUsers(Ljava/util/ArrayList;Z)V

    .line 243
    iget-object p0, p0, Lorg/telegram/messenger/ContactsRemoteViewsFactory;->accountInstance:Lorg/telegram/messenger/AccountInstance;

    invoke-virtual {p0}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    invoke-virtual {p0, v8, v1}, Lorg/telegram/messenger/MessagesController;->putChats(Ljava/util/ArrayList;Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method public onDestroy()V
    .locals 0

    return-void
.end method
