package chainOfResponsibility;

interface RequestHandler {

	void setNextStep(RequestHandler r);

	void process(Request r);
}

class Request {

}

class CacheHandler implements RequestHandler {

	private RequestHandler nextStep;

	@Override
	public void setNextStep(RequestHandler r) {
		this.nextStep = r;

	}

	@Override
	public void process(Request r) {
		System.out.println("in cache process");
		if (null != nextStep) {
			nextStep.process(r);
		}
	}

}

class DbHandler implements RequestHandler {

	private RequestHandler nextStep;

	@Override
	public void setNextStep(RequestHandler r) {
		this.nextStep = r;

	}

	@Override
	public void process(Request r) {
		System.out.println("in db process");
		if (null != nextStep) {
			nextStep.process(r);
		}
	}

}

public class Demo {

	public static void main(String args[]) {

		RequestHandler cacheHandler = new CacheHandler();
		RequestHandler dbHandler = new DbHandler();

		cacheHandler.setNextStep(dbHandler);

		Request r = new Request();

		cacheHandler.process(r);

	}

}
