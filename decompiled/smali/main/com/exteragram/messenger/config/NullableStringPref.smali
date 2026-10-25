.class public final Lcom/exteragram/messenger/config/NullableStringPref;
.super Lcom/exteragram/messenger/config/BasePref;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/exteragram/messenger/config/BasePref<",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u0002\n\u0002\u0008\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u001d\u0012\u0008\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\u0008\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\u0008\u0005\u0010\u0006J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000c\u001a\u00020\u00022\u0008\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000c\u001a\u00020\u00022\u0008\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0016R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\u0008\u00030\u0008X\u0094\u0004\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\t\u0010\n\u00a8\u0006\u0011"
    }
    d2 = {
        "Lcom/exteragram/messenger/config/NullableStringPref;",
        "Lcom/exteragram/messenger/config/BasePref;",
        "",
        "def",
        "backupKey",
        "<init>",
        "(Ljava/lang/String;Ljava/lang/String;)V",
        "backupClass",
        "Ljava/lang/Class;",
        "getBackupClass",
        "()Ljava/lang/Class;",
        "fetch",
        "key",
        "default",
        "save",
        "",
        "value",
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


# instance fields
.field private final backupClass:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 105
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/config/BasePref;-><init>(Ljava/lang/Object;Ljava/lang/String;)V

    .line 106
    const-class p1, Ljava/lang/String;

    iput-object p1, p0, Lcom/exteragram/messenger/config/NullableStringPref;->backupClass:Ljava/lang/Class;

    return-void
.end method

.method public synthetic constructor <init>(Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 105
    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/config/NullableStringPref;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public bridge synthetic fetch(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 105
    check-cast p2, Ljava/lang/String;

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/config/NullableStringPref;->fetch(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public fetch(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 107
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object p0

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public getBackupClass()Ljava/lang/Class;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    .line 106
    iget-object p0, p0, Lcom/exteragram/messenger/config/NullableStringPref;->backupClass:Ljava/lang/Class;

    return-object p0
.end method

.method public bridge synthetic save(Ljava/lang/String;Ljava/lang/Object;)V
    .locals 0

    .line 105
    check-cast p2, Ljava/lang/String;

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/config/NullableStringPref;->save(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public save(Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    .line 108
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method
