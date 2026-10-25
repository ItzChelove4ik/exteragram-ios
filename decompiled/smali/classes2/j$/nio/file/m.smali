.class public final Lj$/nio/file/m;
.super Ljava/lang/Object;
.source "r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0"


# instance fields
.field public final a:Lj$/nio/file/Path;

.field public final b:Ljava/lang/Object;

.field public final c:Ljava/nio/file/DirectoryStream;

.field public final d:Ljava/util/Iterator;

.field public e:Z


# direct methods
.method public constructor <init>(Lj$/nio/file/Path;Ljava/lang/Object;Ljava/nio/file/DirectoryStream;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 2
    .line 3
    .line 4
    iput-object p1, p0, Lj$/nio/file/m;->a:Lj$/nio/file/Path;

    .line 5
    .line 6
    iput-object p2, p0, Lj$/nio/file/m;->b:Ljava/lang/Object;

    .line 7
    .line 8
    iput-object p3, p0, Lj$/nio/file/m;->c:Ljava/nio/file/DirectoryStream;

    .line 9
    .line 10
    invoke-interface {p3}, Ljava/nio/file/DirectoryStream;->iterator()Ljava/util/Iterator;

    .line 11
    .line 12
    .line 13
    move-result-object p1

    .line 14
    iput-object p1, p0, Lj$/nio/file/m;->d:Ljava/util/Iterator;

    .line 15
    .line 16
    return-void
.end method
