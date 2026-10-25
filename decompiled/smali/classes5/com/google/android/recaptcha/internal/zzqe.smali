.class public Lcom/google/android/recaptcha/internal/zzqe;
.super Lcom/google/android/recaptcha/internal/zzpz;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzqi;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "V:",
        "Ljava/lang/Object;",
        ">",
        "Lcom/google/android/recaptcha/internal/zzpz<",
        "TK;TV;>;",
        "Lcom/google/android/recaptcha/internal/zzqi<",
        "TK;TV;>;"
    }
.end annotation


# instance fields
.field private final transient emptySet:Lcom/google/android/recaptcha/internal/zzqb;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/google/android/recaptcha/internal/zzqb<",
            "TV;>;"
        }
    .end annotation
.end field

.field private transient zza:Lcom/google/android/recaptcha/internal/zzqb;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpw;ILjava/util/Comparator;)V
    .locals 0

    .line 1
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzpz;-><init>(Lcom/google/android/recaptcha/internal/zzpw;I)V

    .line 2
    sget-object p1, Lcom/google/android/recaptcha/internal/zzqr;->zza:Lcom/google/android/recaptcha/internal/zzqr;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqe;->emptySet:Lcom/google/android/recaptcha/internal/zzqb;

    return-void
.end method


# virtual methods
.method public final zzb()Lcom/google/android/recaptcha/internal/zzqb;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzqe;->zza:Lcom/google/android/recaptcha/internal/zzqb;

    if-nez v0, :cond_0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzqd;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzqd;-><init>(Lcom/google/android/recaptcha/internal/zzqe;)V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzqe;->zza:Lcom/google/android/recaptcha/internal/zzqb;

    :cond_0
    return-object v0
.end method
