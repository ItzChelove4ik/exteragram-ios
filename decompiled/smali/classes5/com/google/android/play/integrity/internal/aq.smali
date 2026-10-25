.class final Lcom/google/android/play/integrity/internal/aq;
.super Lcom/google/android/play/integrity/internal/ar;
.source "SourceFile"


# instance fields
.field final transient a:I

.field final transient b:I

.field final synthetic c:Lcom/google/android/play/integrity/internal/ar;


# direct methods
.method public constructor <init>(Lcom/google/android/play/integrity/internal/ar;II)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/google/android/play/integrity/internal/aq;->c:Lcom/google/android/play/integrity/internal/ar;

    invoke-direct {p0}, Lcom/google/android/play/integrity/internal/ar;-><init>()V

    iput p2, p0, Lcom/google/android/play/integrity/internal/aq;->a:I

    iput p3, p0, Lcom/google/android/play/integrity/internal/aq;->b:I

    return-void
.end method


# virtual methods
.method public final b()I
    .locals 2

    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aq;->c:Lcom/google/android/play/integrity/internal/ar;

    invoke-virtual {v0}, Lcom/google/android/play/integrity/internal/ao;->c()I

    move-result v0

    iget v1, p0, Lcom/google/android/play/integrity/internal/aq;->a:I

    add-int/2addr v0, v1

    iget p0, p0, Lcom/google/android/play/integrity/internal/aq;->b:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final c()I
    .locals 1

    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aq;->c:Lcom/google/android/play/integrity/internal/ar;

    invoke-virtual {v0}, Lcom/google/android/play/integrity/internal/ao;->c()I

    move-result v0

    iget p0, p0, Lcom/google/android/play/integrity/internal/aq;->a:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final e()[Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/play/integrity/internal/aq;->c:Lcom/google/android/play/integrity/internal/ar;

    invoke-virtual {p0}, Lcom/google/android/play/integrity/internal/ao;->e()[Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final f(II)Lcom/google/android/play/integrity/internal/ar;
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/play/integrity/internal/aq;->b:I

    invoke-static {p1, p2, v0}, Lcom/google/android/play/integrity/internal/al;->c(III)V

    iget v0, p0, Lcom/google/android/play/integrity/internal/aq;->a:I

    iget-object p0, p0, Lcom/google/android/play/integrity/internal/aq;->c:Lcom/google/android/play/integrity/internal/ar;

    add-int/2addr p1, v0

    add-int/2addr p2, v0

    .line 2
    invoke-virtual {p0, p1, p2}, Lcom/google/android/play/integrity/internal/ar;->f(II)Lcom/google/android/play/integrity/internal/ar;

    move-result-object p0

    return-object p0
.end method

.method public final get(I)Ljava/lang/Object;
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/play/integrity/internal/aq;->b:I

    const-string v1, "index"

    invoke-static {p1, v0, v1}, Lcom/google/android/play/integrity/internal/al;->a(IILjava/lang/String;)I

    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aq;->c:Lcom/google/android/play/integrity/internal/ar;

    iget p0, p0, Lcom/google/android/play/integrity/internal/aq;->a:I

    add-int/2addr p1, p0

    .line 2
    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    iget p0, p0, Lcom/google/android/play/integrity/internal/aq;->b:I

    return p0
.end method

.method public final bridge synthetic subList(II)Ljava/util/List;
    .locals 0

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/play/integrity/internal/ar;->f(II)Lcom/google/android/play/integrity/internal/ar;

    move-result-object p0

    return-object p0
.end method
