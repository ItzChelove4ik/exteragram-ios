.class public final synthetic Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/TelegramMediaSession;

.field public final synthetic f$1:I

.field public final synthetic f$2:Ljava/util/ArrayList;

.field public final synthetic f$3:Landroidx/collection/LongSparseArray;

.field public final synthetic f$4:Landroidx/collection/LongSparseArray;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/TelegramMediaSession;ILjava/util/ArrayList;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/messenger/TelegramMediaSession;

    iput p2, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$1:I

    iput-object p3, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$2:Ljava/util/ArrayList;

    iput-object p4, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$3:Landroidx/collection/LongSparseArray;

    iput-object p5, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$4:Landroidx/collection/LongSparseArray;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 4

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$0:Lorg/telegram/messenger/TelegramMediaSession;

    iget v1, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$1:I

    iget-object v2, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$2:Ljava/util/ArrayList;

    iget-object v3, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$3:Landroidx/collection/LongSparseArray;

    iget-object p0, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda5;->f$4:Landroidx/collection/LongSparseArray;

    invoke-static {v0, v1, v2, v3, p0}, Lorg/telegram/messenger/TelegramMediaSession;->$r8$lambda$n-6arz7B2lQmEQiBjxjPjVnaB40(Lorg/telegram/messenger/TelegramMediaSession;ILjava/util/ArrayList;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;)V

    return-void
.end method
