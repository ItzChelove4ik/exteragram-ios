.class public final Lcom/google/android/recaptcha/internal/zzfs;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzfu;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzbb;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzbb;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzfs;->zza:Lcom/google/android/recaptcha/internal/zzbb;

    return-void
.end method


# virtual methods
.method public final varargs zza([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    const/4 v0, 0x0

    .line 1
    aget-object v0, p1, v0

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    const/4 v1, 0x1

    .line 2
    aget-object p1, p1, v1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    int-to-long v1, p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzfs;->zza:Lcom/google/android/recaptcha/internal/zzbb;

    .line 3
    invoke-virtual {p0, v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzbb;->zzc(IJ)V

    const/4 p0, 0x0

    return-object p0
.end method
