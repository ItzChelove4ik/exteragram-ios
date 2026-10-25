.class final Lcom/google/android/play/integrity/internal/ap;
.super Lcom/google/android/play/integrity/internal/an;
.source "SourceFile"


# instance fields
.field private final a:Lcom/google/android/play/integrity/internal/ar;


# direct methods
.method public constructor <init>(Lcom/google/android/play/integrity/internal/ar;I)V
    .locals 1

    .line 1
    invoke-virtual {p1}, Ljava/util/AbstractCollection;->size()I

    move-result v0

    invoke-direct {p0, v0, p2}, Lcom/google/android/play/integrity/internal/an;-><init>(II)V

    iput-object p1, p0, Lcom/google/android/play/integrity/internal/ap;->a:Lcom/google/android/play/integrity/internal/ar;

    return-void
.end method


# virtual methods
.method public final a(I)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/play/integrity/internal/ap;->a:Lcom/google/android/play/integrity/internal/ar;

    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
