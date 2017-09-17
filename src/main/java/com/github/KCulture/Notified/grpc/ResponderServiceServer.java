package com.github.KCulture.Notified.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;

import java.io.File;

public class ResponderServiceServer {

	/**
	 * @param args
	 */
	private Server server;

	public static void main(String[] args) {
		try {
			ResponderServiceServer responderServiceServer = new ResponderServiceServer();
			responderServiceServer.start();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void start() throws Exception {
		final int port = 9000;

		File cert = new File("cert.pem");
		File key = new File("key.pem");

		ResponseService responseService = new ResponseService();
		ServerServiceDefinition srvdef = ServerInterceptors.interceptForward(
		    responseService, new HeaderServerIntercepter());

		server = ServerBuilder.forPort(port).useTransportSecurity(cert, key)
		    .addService(srvdef).build().start();
		System.out.println("Listening on port " + port);

		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				System.out.println("Shutting down the server");
				ResponderServiceServer.this.stop();
			}
		});

		server.awaitTermination();

	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

}
