.class public final Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->translateText(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\u0008\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0008\u0010\u0006\u001a\u00020\u0003H\u0016\u00a8\u0006\u0007"
    }
    d2 = {
        "com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1",
        "Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;",
        "onSuccess",
        "",
        "translatedText",
        "",
        "onFailed",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nInstallPluginBottomSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstallPluginBottomSheet.kt\ncom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1369:1\n1#2:1370\n*E\n"
    }
.end annotation


# instance fields
.field final synthetic $finish:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "Ljava/lang/String;",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lkotlin/jvm/functions/Function1;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/String;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1;->$finish:Lkotlin/jvm/functions/Function1;

    .line 978
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFailed()V
    .locals 1

    .line 984
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1;->$finish:Lkotlin/jvm/functions/Function1;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public bridge synthetic onFailed(Lcom/exteragram/messenger/translator/core/TranslationError;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;->onFailed(Lcom/exteragram/messenger/translator/core/TranslationError;)V

    return-void
.end method

.method public bridge synthetic onReqId(I)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;->onReqId(I)V

    return-void
.end method

.method public onSuccess(Ljava/lang/String;)V
    .locals 2

    .line 980
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$translateText$1;->$finish:Lkotlin/jvm/functions/Function1;

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    invoke-static {p1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    :cond_0
    move-object p1, v0

    :goto_0
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public bridge synthetic onSuccess(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;->onSuccess(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public bridge synthetic onSuccess(Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Lcom/exteragram/messenger/translator/TranslatorUtils$TranslateCallback;->onSuccess(Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;)V

    return-void
.end method
