.class public Lcom/exteragram/messenger/ai/AiController;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/AiController$SingletonHolder;
    }
.end annotation


# instance fields
.field private final roles:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Role;",
            ">;"
        }
    .end annotation
.end field

.field private final services:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Service;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(Ljava/lang/Runnable;Landroid/content/Context;ILorg/telegram/ui/Components/BulletinFactory;ILjava/lang/CharSequence;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 0

    if-eqz p0, :cond_1

    if-eqz p1, :cond_1

    .line 202
    invoke-static {p2}, Lcom/exteragram/messenger/ai/AiController;->isOnDeviceError(I)Z

    move-result p2

    if-nez p2, :cond_0

    goto :goto_0

    .line 206
    :cond_0
    new-instance p2, Lorg/telegram/ui/Components/Bulletin$TwoLineLottieLayout;

    invoke-direct {p2, p1, p7}, Lorg/telegram/ui/Components/Bulletin$TwoLineLottieLayout;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 207
    invoke-virtual {p2, p6}, Lorg/telegram/ui/Components/Bulletin$TwoLineLottieLayout;->setSticker(I)V

    .line 208
    iget-object p6, p2, Lorg/telegram/ui/Components/Bulletin$TwoLineLottieLayout;->titleTextView:Lorg/telegram/ui/Components/LinkSpanDrawable$LinksTextView;

    invoke-static {p4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p4

    invoke-virtual {p6, p4}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 209
    iget-object p4, p2, Lorg/telegram/ui/Components/Bulletin$TwoLineLottieLayout;->subtitleTextView:Lorg/telegram/ui/Components/LinkSpanDrawable$LinksTextView;

    invoke-virtual {p4, p5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 210
    new-instance p4, Lorg/telegram/ui/Components/Bulletin$UndoButton;

    const/4 p5, 0x1

    invoke-direct {p4, p1, p5, p7}, Lorg/telegram/ui/Components/Bulletin$UndoButton;-><init>(Landroid/content/Context;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    sget p1, Lorg/telegram/messenger/R$string;->AIChangeModel:I

    .line 211
    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p4, p1}, Lorg/telegram/ui/Components/Bulletin$UndoButton;->setText(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin$UndoButton;

    move-result-object p1

    .line 212
    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/Bulletin$UndoButton;->setUndoAction(Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin$UndoButton;

    move-result-object p0

    .line 210
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/Bulletin$ButtonLayout;->setButton(Lorg/telegram/ui/Components/Bulletin$Button;)V

    const/16 p0, 0x1388

    .line 213
    invoke-virtual {p3, p2, p0}, Lorg/telegram/ui/Components/BulletinFactory;->create(Lorg/telegram/ui/Components/Bulletin$Layout;I)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 203
    :cond_1
    :goto_0
    invoke-static {p4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p3, p0, p5, p6}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(Ljava/lang/CharSequence;Ljava/lang/CharSequence;I)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static synthetic $r8$lambda$k4Ykokjerf7UwZXW-V7hhEULXCk(Ljava/lang/Runnable;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 66
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->clearConversationHistory()V

    if-eqz p0, :cond_0

    .line 68
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    .line 70
    :cond_0
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$raw;->ic_delete:I

    sget p2, Lorg/telegram/messenger/R$string;->HistoryCleared:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static synthetic $r8$lambda$ml9V0PZBMdpgTeM8PwcrlItS8AE(Lcom/exteragram/messenger/ai/data/Role;)Z
    .locals 1

    if-eqz p0, :cond_1

    .line 247
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public constructor <init>()V
    .locals 1

    .line 40
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 44
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    .line 45
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    .line 48
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->loadRoles()V

    .line 49
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->loadServices()V

    return-void
.end method

.method public static canSendImage(Ljava/lang/String;)Z
    .locals 3

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 417
    :cond_0
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 418
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-virtual {v1}, Ljava/io/File;->isFile()Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_1

    .line 421
    :cond_1
    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    .line 422
    const-string v1, ".png"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    const-string v1, ".jpg"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    const-string v1, ".jpeg"

    .line 423
    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    const-string v1, ".webp"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    const-string v1, ".heic"

    .line 424
    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    const-string v1, ".heif"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_2

    goto :goto_0

    :cond_2
    return v0

    :cond_3
    :goto_0
    const/4 p0, 0x1

    return p0

    :cond_4
    :goto_1
    return v0
.end method

.method public static canSendImage(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 411
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getPathToMessage(Lorg/telegram/messenger/MessageObject;)Ljava/lang/String;

    move-result-object v0

    if-eqz p0, :cond_0

    .line 412
    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiController;->canSendImage(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static canUseAI()Z
    .locals 2

    .line 236
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->getSelected()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v0

    .line 237
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 238
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isReady()Z

    move-result v0

    return v0

    .line 240
    :cond_0
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getKey()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public static clearHistory(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)V
    .locals 1

    const/4 v0, 0x0

    .line 53
    invoke-static {p0, p1, p2, v0}, Lcom/exteragram/messenger/ai/AiController;->clearHistory(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ZLjava/lang/Runnable;)V

    return-void
.end method

.method public static clearHistory(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ZLjava/lang/Runnable;)V
    .locals 1

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_2

    .line 61
    new-instance p2, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p2, v0, p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 62
    sget p1, Lorg/telegram/messenger/R$string;->ClearConversationHistoryInfo:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object p1

    invoke-virtual {p2, p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 63
    sget p1, Lorg/telegram/messenger/R$string;->ClearHistory:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 64
    sget p1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    invoke-virtual {p2, p1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 65
    sget p1, Lorg/telegram/messenger/R$string;->ClearButton:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    new-instance v0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda6;

    invoke-direct {v0, p3, p0}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda6;-><init>(Ljava/lang/Runnable;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {p2, p1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 72
    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p1

    .line 73
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    const/4 p0, -0x1

    .line 74
    invoke-virtual {p1, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    check-cast p0, Landroid/widget/TextView;

    if-eqz p0, :cond_1

    .line 76
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p1

    invoke-virtual {p0, p1}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_1
    :goto_0
    return-void

    .line 79
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->clearConversationHistory()V

    if-eqz p3, :cond_3

    .line 81
    invoke-interface {p3}, Ljava/lang/Runnable;->run()V

    .line 83
    :cond_3
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$raw;->ic_delete:I

    sget p2, Lorg/telegram/messenger/R$string;->HistoryCleared:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static getInstance()Lcom/exteragram/messenger/ai/AiController;
    .locals 1

    .line 232
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController$SingletonHolder;->-$$Nest$sfgetINSTANCE()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    return-object v0
.end method

.method public static isOnDeviceError(I)Z
    .locals 1

    const/4 v0, -0x1

    if-eq p0, v0, :cond_1

    const/4 v0, -0x2

    if-eq p0, v0, :cond_1

    const/4 v0, -0x3

    if-eq p0, v0, :cond_1

    const/4 v0, -0x4

    if-eq p0, v0, :cond_1

    const/4 v0, -0x5

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public static showErrorBulletin(Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILjava/lang/String;Ljava/lang/Runnable;)V
    .locals 6

    const/4 v0, 0x0

    move-object v1, p0

    move-object v2, p1

    move v3, p2

    move-object v4, p3

    move-object v5, p4

    .line 92
    invoke-static/range {v0 .. v5}, Lcom/exteragram/messenger/ai/AiController;->showErrorBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILjava/lang/String;Ljava/lang/Runnable;)V

    return-void
.end method

.method public static showErrorBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;ILjava/lang/String;)V
    .locals 6

    const/4 v2, 0x0

    const/4 v5, 0x0

    const/4 v1, 0x0

    move-object v0, p0

    move v3, p1

    move-object v4, p2

    .line 100
    invoke-static/range {v0 .. v5}, Lcom/exteragram/messenger/ai/AiController;->showErrorBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILjava/lang/String;Ljava/lang/Runnable;)V

    return-void
.end method

.method private static showErrorBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILjava/lang/String;Ljava/lang/Runnable;)V
    .locals 10

    const/4 v0, -0x5

    const/4 v1, 0x3

    const/4 v2, 0x0

    if-eq p3, v0, :cond_9

    const/4 v0, -0x4

    const/4 v3, 0x2

    if-eq p3, v0, :cond_8

    const/4 v0, -0x3

    const/4 v4, 0x5

    if-eq p3, v0, :cond_7

    const/4 v0, -0x2

    const/16 v5, 0x8

    if-eq p3, v0, :cond_6

    const/4 v0, -0x1

    if-eq p3, v0, :cond_5

    const/16 v0, 0xcc

    if-eq p3, v0, :cond_4

    const/16 v0, 0x198

    if-eq p3, v0, :cond_3

    const/16 v0, 0x1ad

    if-eq p3, v0, :cond_2

    const/16 v0, 0x1f6

    if-eq p3, v0, :cond_1

    const/16 v0, 0x1f7

    if-eq p3, v0, :cond_0

    packed-switch p3, :pswitch_data_0

    .line 188
    sget v0, Lorg/telegram/messenger/R$string;->AIError:I

    .line 189
    sget v2, Lorg/telegram/messenger/R$string;->AIErrorInfo:I

    const/4 v3, 0x1

    move v8, v1

    move v1, v2

    move v2, v3

    goto :goto_4

    .line 163
    :pswitch_0
    sget v0, Lorg/telegram/messenger/R$string;->AIError403:I

    .line 164
    sget v1, Lorg/telegram/messenger/R$string;->AIError403Info:I

    const/16 v3, 0x9

    :goto_0
    move v8, v3

    goto :goto_4

    .line 158
    :pswitch_1
    sget v0, Lorg/telegram/messenger/R$string;->AIError402:I

    .line 159
    sget v1, Lorg/telegram/messenger/R$string;->AIError402Info:I

    :goto_1
    move v8, v5

    goto :goto_4

    .line 153
    :pswitch_2
    sget v0, Lorg/telegram/messenger/R$string;->AIError401:I

    .line 154
    sget v3, Lorg/telegram/messenger/R$string;->AIError401Info:I

    :goto_2
    move v8, v1

    move v1, v3

    goto :goto_4

    .line 148
    :pswitch_3
    sget v0, Lorg/telegram/messenger/R$string;->AIError400:I

    .line 149
    sget v1, Lorg/telegram/messenger/R$string;->AIError400Info:I

    goto :goto_0

    .line 183
    :cond_0
    sget v0, Lorg/telegram/messenger/R$string;->AIError503:I

    .line 184
    sget v3, Lorg/telegram/messenger/R$string;->AIError503Info:I

    goto :goto_2

    .line 178
    :cond_1
    sget v0, Lorg/telegram/messenger/R$string;->AIError502:I

    .line 179
    sget v3, Lorg/telegram/messenger/R$string;->AIError502Info:I

    goto :goto_2

    .line 173
    :cond_2
    sget v0, Lorg/telegram/messenger/R$string;->AIError429:I

    .line 174
    sget v1, Lorg/telegram/messenger/R$string;->AIError429Info:I

    const/4 v3, 0x6

    goto :goto_0

    .line 168
    :cond_3
    sget v0, Lorg/telegram/messenger/R$string;->AIError408:I

    .line 169
    sget v1, Lorg/telegram/messenger/R$string;->AIError408Info:I

    :goto_3
    move v8, v4

    goto :goto_4

    .line 143
    :cond_4
    sget v0, Lorg/telegram/messenger/R$string;->AIErrorEmpty:I

    .line 144
    sget v1, Lorg/telegram/messenger/R$string;->AIErrorEmptyInfo:I

    goto :goto_0

    .line 118
    :cond_5
    sget v0, Lorg/telegram/messenger/R$string;->AIErrorOnDevice:I

    .line 119
    sget v3, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceInfo:I

    goto :goto_2

    .line 123
    :cond_6
    sget v0, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceDownload:I

    .line 124
    sget v1, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceDownloadInfo:I

    goto :goto_1

    .line 128
    :cond_7
    sget v0, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceDownloading:I

    .line 129
    sget v1, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceDownloadingInfo:I

    goto :goto_3

    .line 133
    :cond_8
    sget v0, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceTooLong:I

    .line 134
    sget v1, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceTooLongInfo:I

    goto :goto_0

    .line 138
    :cond_9
    sget v0, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceStopped:I

    .line 139
    sget v3, Lorg/telegram/messenger/R$string;->AIErrorOnDeviceStoppedInfo:I

    goto :goto_2

    :goto_4
    const/4 v3, 0x0

    if-nez v2, :cond_b

    const/16 v4, 0x190

    if-eq p3, v4, :cond_b

    const/16 v4, 0x193

    if-ne p3, v4, :cond_a

    goto :goto_5

    :cond_a
    move-object p4, v3

    goto :goto_6

    .line 194
    :cond_b
    :goto_5
    invoke-static {p4}, Lcom/exteragram/messenger/ai/AiController;->trimErrorMessage(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p4

    :goto_6
    if-eqz v2, :cond_c

    if-eqz p4, :cond_c

    .line 196
    sget v0, Lorg/telegram/messenger/R$string;->UnknownError:I

    :cond_c
    move v6, v0

    if-eqz p4, :cond_d

    :goto_7
    move-object v7, p4

    goto :goto_8

    .line 197
    :cond_d
    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p4

    goto :goto_7

    :goto_8
    if-eqz p1, :cond_e

    .line 199
    move-object p4, p1

    check-cast p4, Landroid/widget/FrameLayout;

    invoke-static {p4, p2}, Lorg/telegram/ui/Components/BulletinFactory;->of(Landroid/widget/FrameLayout;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p4

    :goto_9
    move-object v5, p4

    goto :goto_a

    :cond_e
    if-eqz p0, :cond_f

    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p4

    goto :goto_9

    :cond_f
    invoke-static {}, Lorg/telegram/ui/Components/BulletinFactory;->global()Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p4

    goto :goto_9

    :goto_a
    if-eqz p1, :cond_10

    .line 200
    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v3

    goto :goto_b

    :cond_10
    if-eqz p0, :cond_11

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v3

    .line 201
    :cond_11
    :goto_b
    new-instance v1, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;

    move-object v9, p2

    move v4, p3

    move-object v2, p5

    invoke-direct/range {v1 .. v9}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda0;-><init>(Ljava/lang/Runnable;Landroid/content/Context;ILorg/telegram/ui/Components/BulletinFactory;ILjava/lang/CharSequence;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x190
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private static trimErrorMessage(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 219
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 222
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 223
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    return-object v1

    .line 226
    :cond_1
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/16 v1, 0xa0

    if-le v0, v1, :cond_2

    .line 227
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v2, 0x0

    invoke-virtual {p0, v2, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\u2026"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    :cond_2
    return-object p0
.end method


# virtual methods
.method public addRole(Lcom/exteragram/messenger/ai/data/Role;)Z
    .locals 2

    .line 288
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/ai/AiController;->isSuggestedRole(Lcom/exteragram/messenger/ai/data/Role;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_1

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/ai/AiController;->isCustomRole(Lcom/exteragram/messenger/ai/data/Role;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 292
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {v0, v1, p1}, Ljava/util/List;->add(ILjava/lang/Object;)V

    .line 293
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->saveRoles()V

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    return v1
.end method

.method public addService(Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 1

    if-eqz p1, :cond_1

    .line 354
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 357
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 358
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->saveServices()V

    :cond_1
    :goto_0
    return-void
.end method

.method public getAll()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Service;",
            ">;"
        }
    .end annotation

    .line 342
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-static {p0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public getRoles()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Role;",
            ">;"
        }
    .end annotation

    .line 252
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-static {p0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public getSelected()Lcom/exteragram/messenger/ai/data/Service;
    .locals 2

    .line 385
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda2;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda2;-><init>()V

    invoke-interface {v0, v1}, Ljava/util/stream/Stream;->filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/stream/Stream;->findFirst()Ljava/util/Optional;

    move-result-object v0

    .line 386
    invoke-virtual {v0}, Ljava/util/Optional;->isPresent()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 387
    invoke-virtual {v0}, Ljava/util/Optional;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/ai/data/Service;

    return-object p0

    .line 389
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Service;

    .line 390
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v1

    if-nez v1, :cond_1

    return-object v0

    .line 394
    :cond_2
    sget-object p0, Lcom/exteragram/messenger/ai/AiConfig;->DEFAULT_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    return-object p0
.end method

.method public getSelectedRole()Lcom/exteragram/messenger/ai/data/Role;
    .locals 3

    .line 274
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/ai/data/Role;

    .line 275
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result v2

    if-eqz v2, :cond_0

    return-object v1

    .line 279
    :cond_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->getSuggestedRoles()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_2
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Role;

    .line 280
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result v1

    if-eqz v1, :cond_2

    return-object v0

    .line 284
    :cond_3
    sget-object p0, Lcom/exteragram/messenger/ai/data/Suggestions;->ASSISTANT:Lcom/exteragram/messenger/ai/data/Suggestions;

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Suggestions;->getRole()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object p0

    return-object p0
.end method

.method public getSuggestedRoles()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Role;",
            ">;"
        }
    .end annotation

    .line 257
    invoke-static {}, Lcom/exteragram/messenger/ai/data/Suggestions;->values()[Lcom/exteragram/messenger/ai/data/Suggestions;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Arrays;->stream([Ljava/lang/Object;)Ljava/util/stream/Stream;

    move-result-object p0

    new-instance v0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda5;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda5;-><init>()V

    .line 258
    invoke-interface {p0, v0}, Ljava/util/stream/Stream;->map(Ljava/util/function/Function;)Ljava/util/stream/Stream;

    move-result-object p0

    .line 259
    invoke-static {}, Ljava/util/stream/Collectors;->toList()Ljava/util/stream/Collector;

    move-result-object v0

    invoke-interface {p0, v0}, Ljava/util/stream/Stream;->collect(Ljava/util/stream/Collector;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    return-object p0
.end method

.method public isCustomRole(Lcom/exteragram/messenger/ai/data/Role;)Z
    .locals 0

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 264
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {p0, p1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public isServicesEmpty()Z
    .locals 0

    .line 346
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->isEmpty()Z

    move-result p0

    return p0
.end method

.method public isSuggestedRole(Lcom/exteragram/messenger/ai/data/Role;)Z
    .locals 0

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 269
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->getSuggestedRoles()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0, p1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public loadRoles()V
    .locals 2

    .line 244
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getRoles()Ljava/util/ArrayList;

    move-result-object v0

    .line 245
    iget-object v1, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->clear()V

    .line 246
    iget-object v1, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 247
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    new-instance v0, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda3;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda3;-><init>()V

    invoke-interface {p0, v0}, Ljava/util/Collection;->removeIf(Ljava/util/function/Predicate;)Z

    return-void
.end method

.method public loadServices()V
    .locals 3

    .line 326
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getServices()Ljava/util/ArrayList;

    move-result-object v0

    .line 327
    iget-object v1, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->clear()V

    .line 328
    iget-object v1, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 329
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    new-instance v1, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda4;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda4;-><init>()V

    invoke-interface {v0, v1}, Ljava/util/Collection;->removeIf(Ljava/util/function/Predicate;)Z

    .line 330
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isSupported()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 331
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelName()Ljava/lang/String;

    move-result-object v0

    .line 332
    sget-object v1, Lcom/exteragram/messenger/ai/AiConfig;->ON_DEVICE_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    const-string v0, "Gemini Nano"

    :cond_0
    invoke-virtual {v1, v0}, Lcom/exteragram/messenger/ai/data/Service;->setModel(Ljava/lang/String;)V

    .line 333
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isThinkingSupported()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDeviceReasoning()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Lcom/exteragram/messenger/ai/data/Service;->setReasoningEnabled(Z)V

    .line 334
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {p0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 336
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isReady()Z

    move-result p0

    if-nez p0, :cond_3

    sget-object p0, Lcom/exteragram/messenger/ai/AiConfig;->ON_DEVICE_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Service;->getId()Ljava/lang/String;

    move-result-object p0

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSelectedServiceId()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_3

    const/4 p0, 0x0

    .line 337
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedServiceId(Ljava/lang/String;)V

    :cond_3
    return-void
.end method

.method public removeRole(Lcom/exteragram/messenger/ai/data/Role;)Z
    .locals 1

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return p0

    .line 300
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 302
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->saveRoles()V

    :cond_1
    return p1
.end method

.method public removeService(Lcom/exteragram/messenger/ai/data/Service;)Z
    .locals 1

    if-eqz p1, :cond_2

    .line 374
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 377
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    .line 379
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->saveServices()V

    :cond_1
    return p1

    :cond_2
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public saveRoles()V
    .locals 1

    .line 322
    new-instance v0, Ljava/util/ArrayList;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-direct {v0, p0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->saveRoles(Ljava/util/ArrayList;)V

    return-void
.end method

.method public saveServices()V
    .locals 3

    .line 398
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    new-instance v1, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lcom/exteragram/messenger/ai/AiController$$ExternalSyntheticLambda1;-><init>()V

    .line 399
    invoke-static {}, Ljava/util/Comparator;->naturalOrder()Ljava/util/Comparator;

    move-result-object v2

    invoke-static {v2}, Ljava/util/Comparator;->nullsLast(Ljava/util/Comparator;)Ljava/util/Comparator;

    move-result-object v2

    .line 398
    invoke-static {v1, v2}, Ljava/util/Comparator;->comparing(Ljava/util/function/Function;Ljava/util/Comparator;)Ljava/util/Comparator;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->sort(Ljava/util/Comparator;)V

    .line 401
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 402
    iget-object p0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/ai/data/Service;

    .line 403
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v2

    if-nez v2, :cond_0

    .line 404
    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 407
    :cond_1
    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->saveServices(Ljava/util/ArrayList;)V

    return-void
.end method

.method public updateRole(Lcom/exteragram/messenger/ai/data/Role;Lcom/exteragram/messenger/ai/data/Role;)Z
    .locals 3

    .line 308
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v0

    const/4 v1, -0x1

    const/4 v2, 0x0

    if-eq v0, v1, :cond_1

    .line 310
    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/ai/AiController;->isSuggestedRole(Lcom/exteragram/messenger/ai/data/Role;)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/ai/data/Role;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_0

    return v2

    .line 314
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/AiController;->roles:Ljava/util/List;

    invoke-interface {p1, v0, p2}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 315
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->saveRoles()V

    const/4 p0, 0x1

    return p0

    :cond_1
    return v2
.end method

.method public updateService(Lcom/exteragram/messenger/ai/data/Service;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 2

    if-eqz p1, :cond_1

    .line 362
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v0

    if-nez v0, :cond_1

    if-eqz p2, :cond_1

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 365
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v0

    const/4 v1, -0x1

    if-eq v0, v1, :cond_1

    .line 367
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->getId()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Lcom/exteragram/messenger/ai/data/Service;->setId(Ljava/lang/String;)V

    .line 368
    iget-object p1, p0, Lcom/exteragram/messenger/ai/AiController;->services:Ljava/util/List;

    invoke-interface {p1, v0, p2}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 369
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->saveServices()V

    :cond_1
    :goto_0
    return-void
.end method
