.class public final Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0008\u0010\u0008\u001a\u0004\u0018\u00010\t2\u0008\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u00a8\u0006\u000c"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;",
        "",
        "<init>",
        "()V",
        "asIcon",
        "Lorg/telegram/ui/Components/UItem;",
        "id",
        "",
        "text",
        "",
        "pack",
        "Lcom/exteragram/messenger/icons/IconPack;",
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


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 363
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory$Companion;-><init>()V

    return-void
.end method


# virtual methods
.method public final asIcon(ILjava/lang/CharSequence;Lcom/exteragram/messenger/icons/IconPack;)Lorg/telegram/ui/Components/UItem;
    .locals 0
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 370
    const-class p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->ofFactory(Ljava/lang/Class;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    .line 371
    iput p1, p0, Lorg/telegram/ui/Components/UItem;->id:I

    .line 372
    iput-object p2, p0, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    .line 373
    iput-object p3, p0, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    return-object p0
.end method
