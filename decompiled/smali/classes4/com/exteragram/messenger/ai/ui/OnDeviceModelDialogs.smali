.class public abstract Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static synthetic $r8$lambda$BS9dtxm-GnVTcOEA5pz-4wMU78k(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 39
    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs;->startDownload(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    return-void
.end method

.method private static createListener(I)Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;
    .locals 1

    .line 51
    new-instance v0, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$1;-><init>(I)V

    return-object v0
.end method

.method public static showDownloadDialog(Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 3

    if-eqz p0, :cond_1

    .line 31
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isDownloading()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 35
    :cond_0
    new-instance v0, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 36
    sget v1, Lorg/telegram/messenger/R$string;->AIOnDeviceDownloadTitle:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 37
    sget v1, Lorg/telegram/messenger/R$string;->AIOnDeviceDownloadInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setSubtitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 38
    sget v1, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 39
    sget v1, Lorg/telegram/messenger/R$string;->AIOnDeviceDownloadButton:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    .line 40
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    :cond_1
    :goto_0
    return-void
.end method

.method private static startDownload(Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 2

    .line 44
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelKey()I

    move-result v0

    .line 45
    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelDialogs;->createListener(I)Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->download(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V

    const/4 v1, -0x1

    .line 46
    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/ui/OnDeviceModelNotifications;->showProgress(II)V

    .line 47
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/R$raw;->ic_download:I

    sget v1, Lorg/telegram/messenger/R$string;->AIOnDeviceDownloadStarted:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method
