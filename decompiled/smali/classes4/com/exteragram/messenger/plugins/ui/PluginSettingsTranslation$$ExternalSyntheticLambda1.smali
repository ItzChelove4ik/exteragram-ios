.class public final synthetic Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/tgnet/RequestDelegate;


# instance fields
.field public final synthetic f$0:Ljava/util/List;

.field public final synthetic f$1:Z

.field public final synthetic f$2:Lkotlin/jvm/functions/Function0;

.field public final synthetic f$3:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

.field public final synthetic f$4:Ljava/util/List;

.field public final synthetic f$5:Ljava/lang/String;

.field public final synthetic f$6:[Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Ljava/util/List;ZLkotlin/jvm/functions/Function0;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$0:Ljava/util/List;

    iput-boolean p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$1:Z

    iput-object p3, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$2:Lkotlin/jvm/functions/Function0;

    iput-object p4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$3:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    iput-object p5, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$4:Ljava/util/List;

    iput-object p6, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$5:Ljava/lang/String;

    iput-object p7, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$6:[Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final run(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 9

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$0:Ljava/util/List;

    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$1:Z

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$2:Lkotlin/jvm/functions/Function0;

    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$3:Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$4:Ljava/util/List;

    iget-object v5, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$5:Ljava/lang/String;

    iget-object v6, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda1;->f$6:[Ljava/lang/String;

    move-object v7, p1

    move-object v8, p2

    invoke-static/range {v0 .. v8}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->$r8$lambda$6sDJjyrsuB15zfDuAj74Z4Tce9A(Ljava/util/List;ZLkotlin/jvm/functions/Function0;Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method
