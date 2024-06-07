package io.grpc.internal;

import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface TransportProvider {
    @Nullable
    ClientTransport obtainActiveTransport();
}
