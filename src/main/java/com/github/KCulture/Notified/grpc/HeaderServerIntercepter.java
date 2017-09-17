package com.github.KCulture.Notified.grpc;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class HeaderServerIntercepter implements ServerInterceptor {

	@Override
	public <ReqT, RespT> Listener<ReqT> interceptCall(
	    ServerCall<ReqT, RespT> call, Metadata headers,
	    ServerCallHandler<ReqT, RespT> next) {
		System.out.println("At least I got in here");
		System.out.println("method name is "
		    + call.getMethodDescriptor().getFullMethodName());
		if ("user.ResponderService/NotifyResponder".equalsIgnoreCase(call
		    .getMethodDescriptor().getFullMethodName())) {
			System.out.println("We now know the name of the call descriptor");
			for (String key : headers.keys()) {
				System.out.println(key
				    + " : "
				    + headers.get(Metadata.Key
				        .of(key, Metadata.ASCII_STRING_MARSHALLER)));
				next.startCall(call, headers);

			}
		}
		System.out.println("Delight in HIM");
		return next.startCall(call, headers);
	}

}
