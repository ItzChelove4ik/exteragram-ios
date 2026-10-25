.class public final synthetic Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field public final synthetic f$0:Lorg/simplifiles/files/DirectoryTransferOptions;


# direct methods
.method public synthetic constructor <init>(Lorg/simplifiles/files/DirectoryTransferOptions;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda2;->f$0:Lorg/simplifiles/files/DirectoryTransferOptions;

    return-void
.end method


# virtual methods
.method public final invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda2;->f$0:Lorg/simplifiles/files/DirectoryTransferOptions;

    check-cast p1, Lj$/nio/file/Path;

    invoke-static {p0, p1}, Lorg/simplifiles/internal/io/FileTreeCopier;->$r8$lambda$v-gR1FcIqk7lNtPkCVl7ezhx3g8(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z

    move-result p0

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method
