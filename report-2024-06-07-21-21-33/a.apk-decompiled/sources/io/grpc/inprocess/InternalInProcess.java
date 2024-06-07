package io.grpc.inprocess;

import io.grpc.Attributes;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes2.dex */
public final class InternalInProcess {
    private InternalInProcess() {
    }

    public static ConnectionClientTransport createInProcessTransport(String str, int i, String str2, String str3, Attributes attributes, ObjectPool<ScheduledExecutorService> objectPool, List<ServerStreamTracer.Factory> list, ServerListener serverListener, boolean z) {
        return new InProcessTransport(str, i, str2, str3, attributes, objectPool, list, serverListener, z);
    }
}
