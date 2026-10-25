.class public final Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;
.super Lorg/telegram/ui/Cells/TextCell;
.source "SourceFile"


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "ViewConstructor"
    }
.end annotation

.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "EditorIconCell"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell$Factory;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0002\u0008\u0002\u0008\u0007\u0018\u00002\u00020\u0001:\u0001\nB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0008\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"
    }
    d2 = {
        "Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;",
        "Lorg/telegram/ui/Cells/TextCell;",
        "context",
        "Landroid/content/Context;",
        "resourcesProvider",
        "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;",
        "<init>",
        "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V",
        "boundResId",
        "",
        "Factory",
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
.field private boundResId:I


# direct methods
.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 0

    .line 289
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Cells/TextCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void
.end method

.method public static final synthetic access$getBoundResId$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;)I
    .locals 0

    .line 288
    iget p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;->boundResId:I

    return p0
.end method

.method public static final synthetic access$setBoundResId$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;I)V
    .locals 0

    .line 288
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$EditorIconCell;->boundResId:I

    return-void
.end method
