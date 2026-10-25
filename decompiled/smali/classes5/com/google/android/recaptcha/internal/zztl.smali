.class final Lcom/google/android/recaptcha/internal/zztl;
.super Lcom/google/android/recaptcha/internal/zztk;
.source "SourceFile"


# instance fields
.field private final zzd:[J


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzto;)V
    .locals 5

    const/16 v0, 0xa

    .line 2
    new-array v1, v0, [J

    new-array v2, v0, [J

    new-array v3, v0, [J

    new-array v4, v0, [J

    invoke-direct {p0, v1, v2, v4}, Lcom/google/android/recaptcha/internal/zztk;-><init>([J[J[J)V

    iput-object v3, p0, Lcom/google/android/recaptcha/internal/zztl;->zzd:[J

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zztk;->zza:[J

    iget-object v2, p1, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v4, v2, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    iget-object v2, v2, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    .line 3
    invoke-static {v1, v4, v2}, Lcom/google/android/recaptcha/internal/zztw;->zza([J[J[J)V

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zztk;->zzb:[J

    iget-object v2, p1, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v4, v2, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    iget-object v2, v2, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    .line 4
    invoke-static {v1, v4, v2}, Lcom/google/android/recaptcha/internal/zztw;->zzb([J[J[J)V

    iget-object v1, p1, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    const/4 v2, 0x0

    .line 5
    invoke-static {v1, v2, v3, v2, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zztk;->zzc:[J

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zzto;->zzb:[J

    .line 6
    sget-object v0, Lcom/google/android/recaptcha/internal/zztr;->zzb:[J

    invoke-static {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    return-void
.end method


# virtual methods
.method public final zza([J[J)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zztl;->zzd:[J

    invoke-static {p1, p2, p0}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    return-void
.end method
