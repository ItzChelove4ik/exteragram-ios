.class final Lorg/telegram/messenger/EmojiPack$Holder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/EmojiPack;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Holder"
.end annotation


# static fields
.field static final INSTANCE:Lorg/telegram/messenger/EmojiPack;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 55
    invoke-static {}, Lorg/telegram/messenger/EmojiPack$Holder;->open()Lorg/telegram/messenger/EmojiPack;

    move-result-object v0

    sput-object v0, Lorg/telegram/messenger/EmojiPack$Holder;->INSTANCE:Lorg/telegram/messenger/EmojiPack;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 54
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static open()Lorg/telegram/messenger/EmojiPack;
    .locals 2

    .line 58
    :try_start_0
    new-instance v0, Lorg/telegram/messenger/EmojiPack;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/telegram/messenger/EmojiPack;-><init>(Lorg/telegram/messenger/EmojiPack-IA;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    .line 60
    const-string v1, "Unable to open emoji.pack"

    invoke-static {v1, v0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 v0, 0x0

    return-object v0
.end method
