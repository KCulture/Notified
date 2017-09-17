package com.github.KCulture.Notified.grpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.EmailContactable;
import com.github.KCulture.Notified.Repository.Responder;
import com.github.KCulture.Notified.Service.MailCommando;
import com.github.KCulture.Notified.application.Notified;

public class ResponseService extends
    ResponderServiceGrpc.ResponderServiceImplBase {

	@Override
	public void notifyResponder(Messages.ResponderRequest request,
	    io.grpc.stub.StreamObserver<Messages.ResponderResponse> responseObserver) {
		System.out.println("started");
		Messages.Responder messageResponder = request.getResponderList().get(0);
		List<String> answers = Arrays.asList(messageResponder.getAnswersList()
		    .toArray(new String[0]));
		List<String> questions = Arrays.asList(messageResponder.getQuestionsList()
		    .toArray(new String[0]));
		System.out.println("generating response");
		Responder modelResponder = new Responder(messageResponder.getFirstName(),
		    messageResponder.getLastname(), messageResponder.getEmail(), questions,
		    answers);
		List<EmailContactable> emails = new ArrayList<>();
		emails.add(modelResponder);
		System.out.println("start email");
		MailCommando ems = new MailCommando(emails, loadProps());
		ems.execute();
		System.out.println("making response");
		Messages.ResponderResponse messageResponse = Messages.ResponderResponse
		    .newBuilder().build();
		System.out.println("after making response");
		responseObserver.onNext(messageResponse);
		responseObserver.onCompleted();

	}

	private static Properties loadProps() {
		Properties properties = new Properties();
		try {
			properties.load(Notified.class.getResourceAsStream("config.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out
			    .println("redownload or rebuild because default file is corrupted");
		}
		return properties;
	}
}
